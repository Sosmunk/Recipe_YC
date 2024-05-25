package com.triangular.recipe_yc.web.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest extends ApiRequest {
    @NotEmpty
    String username;

    @NotEmpty
    String password;

    public void setUsername(String username) {
        this.username = trim(username);
    }

    public void setPassword(String password) {
        this.password = trim(password);
    }
}
