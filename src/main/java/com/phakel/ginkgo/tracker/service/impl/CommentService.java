package com.phakel.ginkgo.tracker.service.impl;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.entity.Comment;
import com.phakel.ginkgo.tracker.error.ConflictError;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.FormError;
import com.phakel.ginkgo.tracker.form.video.AddCommentForm;
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
        if (!videoRepository.isVideoExistByVideoId(videoId))
            return new Result.Failure<>(new ConflictError("video.notfound"));

        return new Result.Success<>(commentRepository.findCommentsByVideoIdToDto(videoId));
    }

    @Override
    public Result<CommentDto, ? extends Error> addCommentToVideo(String videoId, AddCommentForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        if (!videoRepository.isVideoExistByVideoId(videoId))
            return new Result.Failure<>(new ConflictError("video.notfound"));

        var author = userRepository.findByUserIdOptional(form.getUserId());
        if (author.isEmpty())
            return new Result.Failure<>(new ConflictError("user.notfound"));

        var comment = new Comment();
        comment.setText(form.getText());
        comment.setAuthor(author.get());
        comment.persistAndFlush();

        return new Result.Success<>(comment.toDto());
    }
}
