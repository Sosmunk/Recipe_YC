package com.triangular.recipe_yc.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StatusCode {
    UNKNOWN(-1, 500, "Internal server error"),
    OK(0, 200, null),
    VALIDATION_ERROR(1, 400, "Validation error"),
    METHOD_NOT_ALLOWED(2, 405, "Method not allowed"),
    MISSING_BODY(3, 400, "Missing request body"),
    ACCESS_DENIED(4, 403, "Access denied"),
    UNAUTHORIZED(5, 401, "You need to login to access this resource"),

    // user account
    EMAIL_IN_USE(100, 400, "Email is already in use"),
    USERNAME_IN_USE(101, 400, "Username is already in use"),
    WRONG_CREDENTIALS(102, 400, "Wrong username or password"),
    WRONG_PASSWORD(103, 400, "Wrong password");

    int code;
    int httpCode;
    String message;
}
