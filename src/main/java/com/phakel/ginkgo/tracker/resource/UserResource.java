package com.phakel.ginkgo.tracker.resource;

import com.phakel.ginkgo.tracker.entity.UserRole;
import com.phakel.ginkgo.tracker.form.user.LoginForm;
import com.phakel.ginkgo.tracker.form.user.RegisterForm;
import com.phakel.ginkgo.tracker.service.IUserService;
import com.phakel.ginkgo.tracker.util.ResponseUtil;
import io.quarkus.security.Authenticated;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    IUserService userService;

    @GET
    @Path("/{userId}")
    @Authenticated
    public Response getUserByUserId(String userId) {
        return ResponseUtil.withResult(userService.getUserById(userId));
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response register(RegisterForm form) {
        return ResponseUtil.withResult(userService.register(form));
    }

    @POST
    @PermitAll
    @Path("/login")
    public Response login(LoginForm form) {
        return ResponseUtil.withResult(userService.login(form));
    }
}