package com.phakel.ginkgo.tracker.error.mapper;

import com.phakel.ginkgo.tracker.error.AuthenticationFailedError;
import io.quarkus.arc.Priority;
import io.quarkus.security.AuthenticationFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {
    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(new AuthenticationFailedError("user.authentication.failed"))
                .build();
    }
}
