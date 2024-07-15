package com.alten.producttrialfeat.handler;

import com.alten.producttrialfeat.dto.ErrorDto;
import com.alten.producttrialfeat.exception.CategoryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto processValidationError(CategoryException ex) {
        return ErrorDto.builder()
                .message(ex.getMessage())
                .code(ex.getErrorCode())
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }
}