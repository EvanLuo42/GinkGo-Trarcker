package com.phakel.ginkgo.tracker.form.video;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jboss.resteasy.reactive.PartType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddVideoForm extends Form {
    @NotBlank(message = "form.video.title.blank")
    @NotNull(message = "form.video.title.null")
    private String title;

    @NotBlank(message = "form.video.description.blank")
    @NotNull(message = "form.video.description.null")
    private String description;

    @NotBlank(message = "form.video.author.id.blank")
    @NotNull(message = "form.video.author.id.null")
    private String authorId;

    @NotNull(message = "form.video.null")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream video;
}
