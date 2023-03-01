package com.phakel.ginkgo.tracker.error.mapper;

import com.phakel.ginkgo.tracker.error.BadRequestError;
import io.quarkus.arc.Priority;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new BadRequestError("form.illegal"))
                .build();
    }
}
