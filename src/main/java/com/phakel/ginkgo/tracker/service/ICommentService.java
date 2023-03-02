package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.form.video.AddCommentForm;

import java.util.List;

public interface ICommentService {
    Result<List<CommentDto>, ? extends Error> getCommentsByVideoId(String videoId);

    Result<CommentDto, ? extends Error> addCommentToVideo(String videoId, AddCommentForm form);
}
