package com.module.userservice.exception;


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
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        int status = getStatusFromException(ex);
        ErrorResponse errorResponse = new ErrorResponse(status, "Internal Server Error", ex.getMessage());
        errorResponse.setPath(request.getMethod() + "::" + request.getRequestURI());
        errorResponse.setTimeStamp(getTimestamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("error"); // "err
//        int status = getStatusFromException(ex);// or" là tên của trang HTML xử lý lỗi
//        mav.addObject("status", status);
//        mav.addObject("errorName", "InternalServerError");
//        mav.addObject("message", ex.getMessage());
//        mav.addObject("path", request.getMethod() + "::" + request.getRequestURI());
//        mav.addObject("timestamp", getTimestamp());
//        return mav;
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
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
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
        } else if (ex instanceof DataConflicException) {
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

//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler
//    public ResponseEntity<Object> handleExceptions(Exception ex, HttpServletRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        HttpStatus status = determineHttpStatus(ex);
//
//        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
//            body.put("message", methodArgumentNotValidException.getBindingResult()
//                    .getFieldErrors()
//                    .stream()
//                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                    .collect(Collectors.toList()));
//        }else{
//            Map<String, Object> value = new LinkedHashMap<>();
//            value.put("cause", ex.getMessage());
//            body.put("message", value);
//        }
//
//        body.put("error", status.value());
//
//        LocalDateTime now = LocalDateTime.now();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        body.put("timestamp", now.format(formatter));
//        body.put("path", request.getMethod() + "::" + request.getRequestURI());
//
//        return new ResponseEntity<>(body, new HttpHeaders(), status);
//    }
//
//    private HttpStatus determineHttpStatus(Exception ex) {
//        if (ex instanceof MethodArgumentNotValidException) {
//            return HttpStatus.BAD_REQUEST;
//        } else if (ex instanceof DataConflicException) {
//            return HttpStatus.CONFLICT;
//        } else if (ex instanceof NoSuchElementException || ex instanceof NullPointerException) {
//            return HttpStatus.NOT_FOUND;
//        } else if (ex instanceof IllegalArgumentException) {
//            return HttpStatus.BAD_REQUEST;
//        } else {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//    }
//}
