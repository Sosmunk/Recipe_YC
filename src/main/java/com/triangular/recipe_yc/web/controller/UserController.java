package com.triangular.recipe_yc.web.controller;

import com.triangular.recipe_yc.dto.UserInfo;
import com.triangular.recipe_yc.service.UserService;
import com.triangular.recipe_yc.web.annotation.ApiV1;
import com.triangular.recipe_yc.web.response.ItemResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;

@ApiV1
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/me")
    public ItemResponse<UserInfo> getAccountInfo() {
        return new ItemResponse<>(userService.getCurrentUserInfo());
    }
}
