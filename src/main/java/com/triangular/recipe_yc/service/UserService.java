package com.triangular.recipe_yc.service;

import com.triangular.recipe_yc.dto.UserInfo;
import com.triangular.recipe_yc.web.request.SignUpRequest;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {
    UserInfo register(SignUpRequest signUpRequest);

    @PreAuthorize("isAuthenticated()")
    UserInfo getCurrentUserInfo();
}
