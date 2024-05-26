package com.triangular.recipe_yc.service.impl;

import com.triangular.recipe_yc.dto.UserInfo;
import com.triangular.recipe_yc.exception.EmailInUseException;
import com.triangular.recipe_yc.exception.UsernameInUseException;
import com.triangular.recipe_yc.factory.UserFactory;
import com.triangular.recipe_yc.repository.UserRepository;
import com.triangular.recipe_yc.service.UserService;
import com.triangular.recipe_yc.web.request.SignUpRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserFactory userFactory;
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfo register(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new UsernameInUseException();
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new EmailInUseException();

        var user = userFactory.userFrom(signUpRequest);
        user = userRepository.save(user);

        return userFactory.userInfoFrom(user);
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        return userFactory.userInfoFrom(userRepository.getCurrentUser());
    }
}
