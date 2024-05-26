package com.triangular.recipe_yc.web.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest extends ApiRequest {
    @Pattern(regexp = "^[a-zA-Z-_\\d]{3,32}$")
    @NotEmpty
    String username;

    @Email
    @NotEmpty
    String email;

    @Size(min=8, max=72)
    @NotEmpty
    String password;

    public void setUsername(String username) {
        this.username = trim(username);
    }

    public void setEmail(String email) {
        this.email = trim(email);
    }
}
