package com.triangular.recipe_yc.web.controller;

import com.triangular.recipe_yc.dto.UserInfo;
import com.triangular.recipe_yc.enums.StatusCode;
import com.triangular.recipe_yc.web.annotation.ApiV1;
import com.triangular.recipe_yc.service.UserService;
import com.triangular.recipe_yc.web.request.SignInRequest;
import com.triangular.recipe_yc.web.request.SignUpRequest;
import com.triangular.recipe_yc.web.response.ApiResponse;
import com.triangular.recipe_yc.web.response.EmptyResponse;
import com.triangular.recipe_yc.web.response.ErrorResponse;
import com.triangular.recipe_yc.web.response.ItemResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@ApiV1
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    UserService userService;
    AuthenticationManager authenticationManager;
    RememberMeServices rememberMeServices;

    @PostMapping("/register")
    public ItemResponse<UserInfo> register(@Valid @RequestBody SignUpRequest request) {
        return new ItemResponse<>(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody SignInRequest authRequest, HttpServletRequest request,
                                             HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));

        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);
            if (auth != null && auth.isAuthenticated()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
                rememberMeServices.loginSuccess(request, response, auth);
            } else {
                throw new BadCredentialsException(null);
            }
        } catch (BadCredentialsException e) {
            SecurityContextHolder.getContext().setAuthentication(null);

            var status = StatusCode.WRONG_CREDENTIALS;
            return ResponseEntity
                    .status(status.getHttpCode())
                    .body(new ErrorResponse(status));
        }

        return ResponseEntity.ok(new ItemResponse<>(userService.getCurrentUserInfo()));
    }

    @GetMapping("/logout")
    public EmptyResponse logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        rememberMeServices.loginFail(request, response);
        return new EmptyResponse();
    }
}
