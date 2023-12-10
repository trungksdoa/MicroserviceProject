package com.module.userservice.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;

public class MethodArgumentNotValidException extends org.springframework.web.bind.MethodArgumentNotValidException {
    /**
     * Constructor for {@link MethodArgumentNotValidException}.
     *
     * @param parameter     the parameter that failed validation
     * @param bindingResult the results of the validation
     */
    public MethodArgumentNotValidException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }


}
