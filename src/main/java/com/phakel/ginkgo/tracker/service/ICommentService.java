package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.dto.ReplyDto;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.form.video.AddCommentForm;
import com.phakel.ginkgo.tracker.form.video.AddReplyForm;

import java.util.List;

public interface ICommentService {
    Result<List<CommentDto>, ? extends Error> getCommentsByVideoId(String videoId);

    Result<CommentDto, ? extends Error> addCommentToVideo(String videoId, AddCommentForm form);

    Result<ReplyDto, ? extends Error> addReplyToComment(String commentId, AddReplyForm form);

    Result<List<ReplyDto>, ? extends Error> getRepliesByCommentId(String commentId);
}
