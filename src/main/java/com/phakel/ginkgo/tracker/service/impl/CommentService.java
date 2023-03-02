package com.phakel.ginkgo.tracker.service.impl;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.dto.ReplyDto;
import com.phakel.ginkgo.tracker.entity.Comment;
import com.phakel.ginkgo.tracker.entity.Reply;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.FormError;
import com.phakel.ginkgo.tracker.error.NotFoundError;
import com.phakel.ginkgo.tracker.form.video.AddCommentForm;
import com.phakel.ginkgo.tracker.form.video.AddReplyForm;
import com.phakel.ginkgo.tracker.repository.CommentRepository;
import com.phakel.ginkgo.tracker.repository.UserRepository;
import com.phakel.ginkgo.tracker.repository.VideoRepository;
import com.phakel.ginkgo.tracker.service.ICommentService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@ApplicationScoped
public class CommentService implements ICommentService {
    @Inject
    VideoRepository videoRepository;

    @Inject
    CommentRepository commentRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    Validator validator;

    @Override
    public Result<List<CommentDto>, ? extends Error> getCommentsByVideoId(String videoId) {
        if (videoRepository.isVideoNotExistByVideoId(videoId))
            return new Result.Failure<>(new NotFoundError("video.notfound"));

        return new Result.Success<>(commentRepository.findByVideoIdToDto(videoId));
    }

    @Override
    public Result<CommentDto, ? extends Error> addCommentToVideo(String videoId, AddCommentForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (videoRepository.isVideoNotExistByVideoId(videoId))
            return new Result.Failure<>(new NotFoundError("video.notfound"));

        var author = userRepository.findByUserIdOptional(form.getAuthorId());
        if (author.isEmpty())
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var comment = new Comment();
        comment.setText(form.getText());
        comment.setAuthor(author.get());
        comment.persistAndFlush();

        return new Result.Success<>(comment.toDto());
    }

    @Override
    public Result<ReplyDto, ? extends Error> addReplyToComment(String commentId, AddReplyForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (!commentRepository.isCommentExistByCommentId(commentId))
            return new Result.Failure<>(new NotFoundError("comment.notfound"));

        var author = userRepository.findByUserIdOptional(form.getAuthorId());
        if (author.isEmpty())
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var reply = new Reply();
        reply.setText(form.getText());
        reply.setAuthor(author.get());
        reply.persistAndFlush();

        return new Result.Success<>(reply.toDto());
    }

    @Override
    public Result<List<ReplyDto>, ? extends Error> getRepliesByCommentId(String commentId) {
        if (!commentRepository.isCommentExistByCommentId(commentId))
            return new Result.Failure<>(new NotFoundError("comment.notfound"));

        return new Result.Success<>(commentRepository.findRepliesByCommentIdToDto(commentId));
    }
}
