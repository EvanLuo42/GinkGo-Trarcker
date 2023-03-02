package com.phakel.ginkgo.tracker.form.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Data
@AllArgsConstructor
public class UploadVideoForm {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;

    @FormParam("fileName")
    @PartType(MediaType.TEXT_PLAIN)
    private String fileName;
}
