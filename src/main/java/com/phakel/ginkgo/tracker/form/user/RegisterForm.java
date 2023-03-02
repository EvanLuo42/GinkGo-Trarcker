package com.phakel.ginkgo.tracker.form.user;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterForm extends Form {
    @NotBlank(message = "form.user.name.blank")
    @NotNull(message = "form.user.name.null")
    @Length(max = 40, min = 3, message = "form.user.name.length")
    private String username;

    @NotBlank(message = "form.user.password.blank")
    @NotNull(message = "form.user.password.null")
    @Length(max = 100, min = 5, message = "form.user.password.length")
    private String password;

    @Email(message = "form.user.email.regex")
    @NotBlank(message = "form.user.email.blank")
    @NotNull(message = "form.user.email.null")
    @Length(max = 40, min = 3, message = "form.user.email.length")
    private String email;
}
