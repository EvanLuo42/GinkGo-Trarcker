package com.phakel.ginkgo.tracker.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.TokenDto;
import com.phakel.ginkgo.tracker.dto.UserDto;
import com.phakel.ginkgo.tracker.entity.User;
import com.phakel.ginkgo.tracker.entity.UserRole;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.*;
import com.phakel.ginkgo.tracker.form.user.LoginForm;
import com.phakel.ginkgo.tracker.form.user.RegisterForm;
import com.phakel.ginkgo.tracker.repository.UserRepository;
import com.phakel.ginkgo.tracker.service.IUserService;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Optional;

@ApplicationScoped
public class UserService implements IUserService {
    @Inject
    Validator validator;

    @Inject
    UserRepository userRepository;

    @ConfigProperty(name = "jwt.issuer")
    Optional<String> issuer;

    @Override
    public Result<UserDto, ? extends Error> getUserById(String userId) {
        Optional<UserDto> user = userRepository.findByUsernameOptionalToDto(userId);
        return user.isPresent() ?
                new Result.Success<>(user.get()) :
                new Result.Failure<>(new NotFoundError("user.notfound"));
    }

    @Transactional
    @Override
    public Result<UserDto, ? extends Error> register(RegisterForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (userRepository.isUserExistByUsername(form.getUsername()))
            return new Result.Failure<>(new ConflictError("user.name.conflict"));

        var newUser = new User();
        newUser.setUsername(form.getUsername());
        newUser.setPassword(form.getPassword());
        newUser.setEmail(form.getEmail());
        newUser.setRole(UserRole.USER);
        newUser.persistAndFlush();

        return new Result.Success<>(newUser.toDto());
    }

    @Override
    public Result<TokenDto, ? extends Error> login(LoginForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (!userRepository.isUserExistByUsername(form.getUsername()))
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var user = userRepository.findByUsername(form.getUsername());

        if (!BCrypt.verifyer().verify(form.getPassword().toCharArray(), user.getPassword()).verified)
            return new Result.Failure<>(new PasswordIncorrectError("user.password.incorrect"));

        return new Result.Success<>(
                new TokenDto(
                        Jwt
                                .issuer(issuer.orElse("gink-go.com"))
                                .preferredUserName(user.getUsername())
                                .groups(String.valueOf(user.getRole()).toLowerCase())
                                .sign()
                )
        );
    }
}
