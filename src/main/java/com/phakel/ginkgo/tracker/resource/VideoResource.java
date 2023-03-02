package com.phakel.ginkgo.tracker.resource;

import com.phakel.ginkgo.tracker.service.IVideoService;
import com.phakel.ginkgo.tracker.util.ResponseUtil;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/video")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {
    @Inject
    IVideoService videoService;

    @GET
    @Path("/{videoId}")
    @Authenticated
    public Response getCommentsByVideoId(String videoId) {
        return ResponseUtil.withResult(videoService.getCommentsByVideoId(videoId));
    }
}
