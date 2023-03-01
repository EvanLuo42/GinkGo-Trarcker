package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.TokenDto;
import com.phakel.ginkgo.tracker.dto.UserDto;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.form.user.LoginForm;
import com.phakel.ginkgo.tracker.form.user.RegisterForm;

public interface IUserService {
    Result<UserDto, ? extends Error> getUserById(String userId);

    Result<UserDto, ? extends Error> register(RegisterForm form);

    Result<TokenDto, ? extends Error> login(LoginForm form);
}
