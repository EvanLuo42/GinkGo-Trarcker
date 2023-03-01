package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.dto.DTO;
import com.phakel.ginkgo.tracker.form.Form;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
@RegisterRestClient
public interface INodeRestService {
    @GET
    @Path("/publicKey")
    DTO getPublicKey();

    @POST
    @Path("/video")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    DTO postVideo(Form videoForm);

    @GET
    @Path("/video/{id}")
    DTO getVideo(@PathParam("id") String id);
}
