package com.phakel.ginkgo.tracker.resource;

import com.phakel.ginkgo.tracker.form.video.AddCommentForm;
import com.phakel.ginkgo.tracker.form.video.AddReplyForm;
import com.phakel.ginkgo.tracker.form.video.AddVideoForm;
import com.phakel.ginkgo.tracker.service.ICommentService;
import com.phakel.ginkgo.tracker.service.IVideoService;
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

    @Inject
    IVideoService videoService;

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

    @GET
    @Path("/comment/{commentId}/replies")
    @Authenticated
    public Response getRepliesByCommentId(String commentId) {
        return ResponseUtil.withResult(commentService.getRepliesByCommentId(commentId));
    }

    @POST
    @Path("/comment/{commentId}/reply")
    @Authenticated
    public Response addReplyToComment(String commentId, AddReplyForm form) {
        return ResponseUtil.withResult(commentService.addReplyToComment(commentId, form));
    }

    @POST
    @Authenticated
    public Response postVideo(AddVideoForm form) {
        return ResponseUtil.withResult(videoService.postVideo(form));
    }

    @GET
    @Path("/{videoId}")
    @Authenticated
    public Response getVideoByVideoId(String videoId) {
        return ResponseUtil.withResult(videoService.getVideoByVideoId(videoId));
    }
}
