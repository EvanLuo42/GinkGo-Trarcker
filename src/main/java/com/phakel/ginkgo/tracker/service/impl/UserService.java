package com.phakel.ginkgo.tracker.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.TokenDTO;
import com.phakel.ginkgo.tracker.dto.UserDTO;
import com.phakel.ginkgo.tracker.entity.User;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.*;
import com.phakel.ginkgo.tracker.form.user.LoginForm;
import com.phakel.ginkgo.tracker.form.user.RegisterForm;
import com.phakel.ginkgo.tracker.service.IUserService;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@ApplicationScoped
public class UserService implements IUserService {
    @Inject
    Validator validator;

    @Override
    public Result<UserDTO, NotFoundError> getUserById(String userId) {
        Optional<User> user = User.findByIdOptional(userId);
        return user.isPresent() ?
                new Result.Success<>(user.get().to()) :
                new Result.Failure<>(new NotFoundError("user.notfound"));
    }

    @Transactional
    @Override
    public Result<UserDTO, ? extends Error> register(RegisterForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        var newUser = new User();
        newUser.setUsername(form.getUsername());
        newUser.setPassword(form.getPassword());
        newUser.setEmail(form.getEmail());

        if (User.count("username", newUser.getUsername()) != 0)
            return new Result.Failure<>(new ConflictError("user.name.conflict"));

        User.persist(newUser);
        return new Result.Success<>(((User) User.find("username", newUser.getUsername()).firstResult()).to());
    }

    @Override
    public Result<TokenDTO, ? extends Error> login(LoginForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (User.count("username", form.getUsername()) == 0)
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var user = (User) User.find("username", form.getUsername()).firstResult();

        if (!BCrypt.verifyer().verify(form.getPassword().toCharArray(), user.getPassword()).verified)
            return new Result.Failure<>(new PasswordIncorrectError("user.password.incorrect"));

        return new Result.Success<>(
                new TokenDTO(
                        Jwt.preferredUserName(user.getUsername())
                                .issuer("https://example.com/issuer")
                                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                                .sign()
                )
        );
    }
}
