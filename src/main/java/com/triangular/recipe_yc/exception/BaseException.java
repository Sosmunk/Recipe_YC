package com.triangular.recipe_yc.exception;

import com.triangular.recipe_yc.enums.StatusCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseException extends RuntimeException {
    StatusCode statusCode;

    public BaseException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public BaseException(StatusCode statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }
}
