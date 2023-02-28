package com.phakel.ginkgo.service;

import com.phakel.ginkgo.Result;
import com.phakel.ginkgo.dto.TokenDTO;
import com.phakel.ginkgo.dto.UserDTO;
import com.phakel.ginkgo.error.Error;
import com.phakel.ginkgo.error.NotFoundError;
import com.phakel.ginkgo.form.user.LoginForm;
import com.phakel.ginkgo.form.user.RegisterForm;

public interface IUserService {
    Result<UserDTO, NotFoundError> getUserById(String userId);

    Result<UserDTO, ? extends Error> register(RegisterForm form);

    Result<TokenDTO, ? extends Error> login(LoginForm form);
}
