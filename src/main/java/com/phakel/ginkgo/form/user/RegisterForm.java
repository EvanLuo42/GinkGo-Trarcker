package com.phakel.ginkgo.form.user;

import com.phakel.ginkgo.form.Form;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterForm extends Form {
    @NotBlank(message = "form.username.blank")
    @NotNull(message = "form.username.null")
    @Length(max = 40, min = 3, message = "form.username.length")
    private String username;

    @NotBlank(message = "form.password.blank")
    @NotNull(message = "form.password.null")
    @Length(max = 100, min = 5, message = "form.password.length")
    private String password;

    @Email(message = "form.email.regex")
    @NotBlank(message = "form.email.blank")
    @NotNull(message = "form.email.null")
    @Length(max = 40, min = 3, message = "form.username.length")
    private String email;
}
