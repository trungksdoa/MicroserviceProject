package com.module.userservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.model.ErrorResponse;
import com.module.userservice.model.ErrorValidateDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse> handleException(Exception ex, HttpServletRequest request) {
        int status = getStatusFromException(ex);
        ErrorResponse errorResponse = new ErrorResponse(status, "Internal Server Error", ex.getMessage());
        errorResponse.setTimeStamp(getTimestamp());

        BaseResponse response = BaseResponse.builder()
                .timeStamp(null)
                .message("Something error user service.")
                .data(errorResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BaseResponse> handleServerErrorException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = null;
        if (ex instanceof HttpServerErrorException) {
            String responseBody = ((HttpServerErrorException) ex).getResponseBodyAsString();
            // Now responseBody contains the error message from course Service
            Gson gson = new Gson();

            errorResponse = new ErrorResponse(((HttpServerErrorException) ex).getStatusCode().value(), ((HttpServerErrorException) ex).getStatusText(), gson.fromJson(responseBody, ErrorResponse.class).getMessage());
            errorResponse.setTimeStamp(getTimestamp());
        }
        BaseResponse response = BaseResponse.builder()
                .timeStamp(null)
                .message("Response from course service.")
                .data(errorResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ErrorValidateDetail> errorDetails = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String value = ((FieldError) error).getRejectedValue() != null ? ((FieldError) error).getRejectedValue().toString() : "";
            String location = ((FieldError) error).isBindingFailure() + ""; // Điền thông tin vị trí nếu có thể xác định
            String issue = ((FieldError) error).getObjectName();//xem lại
            String description = error.getDefaultMessage();
            ErrorValidateDetail errorDetail = new ErrorValidateDetail(field, value, location, issue, description);
            errorDetails.add(errorDetail);
        });

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "ERROR_NAME", "Error in request validation.");
        errorResponse.setDetails(errorDetails);
        errorResponse.setPath(request.getMethod() + "::" + request.getRequestURI());
        errorResponse.setTimeStamp(getTimestamp());

        BaseResponse response = BaseResponse.builder()
                .timeStamp(null)
                .message("Error in request validation.")
                .data(errorResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Phương thức để lấy mã trạng thái từ ngoại lệ
    private int getStatusFromException(Exception ex) {
        if (ex instanceof ResponseStatusException) {
            return ((ResponseStatusException) ex).getBody().getStatus();
        } else if (ex instanceof HttpClientErrorException) {
            return ((HttpClientErrorException) ex).getStatusCode().value();
        } else if (ex instanceof NestedServletException && ((NestedServletException) ex).getRootCause() instanceof ResponseStatusException) {
            return ((ResponseStatusException) ((NestedServletException) ex).getRootCause()).getBody().getStatus();
        } else if (ex instanceof NoSuchElementException || ex instanceof NullPointerException) {
            return HttpStatus.NOT_FOUND.value();
        } else if (ex instanceof DataAlreadyExistsException) {
            return HttpStatus.CONFLICT.value();
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    public String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return now.format(formatter);
    }
    // Thêm các phương thức xử lý ngoại lệ khác nếu cần

    // Class ErrorResponse để định nghĩa cấu trúc trả về lỗi


}

