package com.triangular.recipe_yc.web.advice;

import com.triangular.recipe_yc.enums.StatusCode;
import com.triangular.recipe_yc.web.annotation.ApiAdvice;
import com.triangular.recipe_yc.web.response.ValidationErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.stream.Collectors;

@ApiAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrorResponse> notValid(MethodArgumentNotValidException e) {
        var fields = e.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationErrorResponse> constraintViolation(ConstraintViolationException e) {
        var fields = e.getConstraintViolations().stream().map((violation) -> {
            var path = violation.getPropertyPath().toString();
            return path.substring(path.lastIndexOf('.') + 1);
        }).collect(Collectors.toSet());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    ResponseEntity<ValidationErrorResponse> requestPartMissing(MissingServletRequestPartException e) {
        var fields = new HashSet<String>();
        fields.add(e.getRequestPartName());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }
}
