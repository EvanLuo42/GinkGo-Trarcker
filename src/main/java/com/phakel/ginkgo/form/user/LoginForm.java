package com.phakel.ginkgo.form.user;

import com.phakel.ginkgo.form.Form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginForm extends Form {
    @NotBlank(message = "form.username.blank")
    @NotNull(message = "form.username.null")
    @Length(max = 40, min = 3, message = "form.username.length")
    private String username;

    @NotBlank(message = "form.password.blank")
    @NotNull(message = "form.password.null")
    @Length(max = 100, min = 5, message = "form.password.length")
    private String password;
}
