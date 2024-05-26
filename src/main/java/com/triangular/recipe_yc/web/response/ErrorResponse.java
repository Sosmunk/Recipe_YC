package com.triangular.recipe_yc.web.response;

import com.triangular.recipe_yc.enums.StatusCode;

public class ErrorResponse extends ApiResponse {
    public ErrorResponse(StatusCode statusCode) {
        super(statusCode.getCode(), statusCode.getMessage());
    }
}
