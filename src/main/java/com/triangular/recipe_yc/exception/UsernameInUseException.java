package com.triangular.recipe_yc.exception;

import com.triangular.recipe_yc.enums.StatusCode;

public class UsernameInUseException extends BaseException {
    public UsernameInUseException() {
        super(StatusCode.USERNAME_IN_USE);
    }
}
