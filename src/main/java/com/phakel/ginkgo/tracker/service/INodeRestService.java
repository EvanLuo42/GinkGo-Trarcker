package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.dto.Dto;
import com.phakel.ginkgo.tracker.form.Form;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
@RegisterRestClient
public interface INodeRestService {
    @GET
    @Path("/publicKey")
    Dto getPublicKey();

    @POST
    @Path("/video")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Dto postVideo(Form videoForm);

    @GET
    @Path("/video/{id}")
    Dto getVideo(@PathParam("id") String id);
}
