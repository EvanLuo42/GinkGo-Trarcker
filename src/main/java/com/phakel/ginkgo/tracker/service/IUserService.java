package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.TokenDTO;
import com.phakel.ginkgo.tracker.dto.UserDTO;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.NotFoundError;
import com.phakel.ginkgo.tracker.form.user.LoginForm;
import com.phakel.ginkgo.tracker.form.user.RegisterForm;

public interface IUserService {
    Result<UserDTO, NotFoundError> getUserById(String userId);

    Result<UserDTO, ? extends Error> register(RegisterForm form);

    Result<TokenDTO, ? extends Error> login(LoginForm form);
}
