package com.triangular.recipe_yc.web.advice;

import com.triangular.recipe_yc.enums.StatusCode;
import com.triangular.recipe_yc.web.response.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class AdviceUtils {
    public static ResponseEntity<ErrorResponse> createResponse(StatusCode s) {
        return ResponseEntity.status(s.getHttpCode()).body(new ErrorResponse(s));
    }
}
