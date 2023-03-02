package com.phakel.ginkgo.tracker.form.video;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddReplyForm extends Form {
    @NotBlank(message = "form.reply.text.blank")
    @NotNull(message = "form.reply.text.null")
    private String text;

    @NotBlank(message = "form.reply.author.id.blank")
    @NotNull(message = "form.reply.author.id.null")
    private String authorId;
}
