package com.phakel.ginkgo.tracker.form.video;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddCommentForm extends Form {
    @NotBlank(message = "form.comment.author.id.blank")
    @NotNull(message = "form.comment.author.id.null")
    private String authorId;

    @NotBlank(message = "form.comment.text.blank")
    @NotNull(message = "form.comment.text.null")
    private String text;
}
