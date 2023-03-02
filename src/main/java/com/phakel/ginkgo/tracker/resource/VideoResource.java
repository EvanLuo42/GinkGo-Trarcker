package com.phakel.ginkgo.tracker.resource;

import com.phakel.ginkgo.tracker.form.video.AddCommentForm;
import com.phakel.ginkgo.tracker.service.ICommentService;
import com.phakel.ginkgo.tracker.util.ResponseUtil;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/video")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {
    @Inject
    ICommentService commentService;

    @GET
    @Path("/{videoId}/comments")
    @Authenticated
    public Response getCommentsByVideoId(String videoId) {
        return ResponseUtil.withResult(commentService.getCommentsByVideoId(videoId));
    }

    @POST
    @Path("/{videoId}/comment")
    @Authenticated
    public Response addCommentToVideo(String videoId, AddCommentForm form) {
        return ResponseUtil.withResult(commentService.addCommentToVideo(videoId, form));
    }
}
