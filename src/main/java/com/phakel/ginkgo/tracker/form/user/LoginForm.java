package com.phakel.ginkgo.tracker.form.user;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginForm extends Form {
    @NotBlank(message = "form.user.name.blank")
    @NotNull(message = "form.user.name.null")
    @Length(max = 40, min = 3, message = "form.username.length")
    private String username;

    @NotBlank(message = "form.user.password.blank")
    @NotNull(message = "form.user.password.null")
    @Length(max = 100, min = 5, message = "form.user.password.length")
    private String password;
}
