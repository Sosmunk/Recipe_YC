package com.triangular.recipe_yc.exception;

import com.triangular.recipe_yc.enums.StatusCode;

public class EmailInUseException extends BaseException {
    public EmailInUseException() {
        super(StatusCode.EMAIL_IN_USE);
    }
}
