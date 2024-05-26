package com.triangular.recipe_yc.factory;

import com.triangular.recipe_yc.dto.UserInfo;
import com.triangular.recipe_yc.entity.User;
import com.triangular.recipe_yc.web.request.SignUpRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFactory {
    PasswordEncoder passwordEncoder;

    public User userFrom(SignUpRequest signUpRequest) {
        return User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
    }

    public UserInfo userInfoFrom(User user) {
        return new UserInfo(user.getId(), user.getUsername(), user.getEmail());
    }
}
