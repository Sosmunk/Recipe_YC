package com.triangular.recipe_yc.web.advice;


import com.triangular.recipe_yc.enums.StatusCode;
import com.triangular.recipe_yc.exception.BaseException;
import com.triangular.recipe_yc.web.annotation.ApiAdvice;
import com.triangular.recipe_yc.web.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ApiAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultAdvice {
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ErrorResponse> statusException(BaseException e) {
        return AdviceUtils.createResponse(e.getStatusCode());
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationCredentialsNotFoundException.class})
    public ResponseEntity<ErrorResponse> accessDenied(Exception e) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
            return AdviceUtils.createResponse(StatusCode.ACCESS_DENIED);
        return AdviceUtils.createResponse(StatusCode.UNAUTHORIZED);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> noBody(Exception e) {
        return AdviceUtils.createResponse(StatusCode.MISSING_BODY);
    }

    @ExceptionHandler({MethodNotAllowedException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorResponse> notAllowed(Exception e) {
        return AdviceUtils.createResponse(StatusCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> anyException(Exception e) {
        return AdviceUtils.createResponse(StatusCode.UNKNOWN);
    }
}
