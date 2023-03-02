package com.phakel.ginkgo.tracker.service.impl;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.VideoDto;
import com.phakel.ginkgo.tracker.entity.Video;
import com.phakel.ginkgo.tracker.error.ConflictError;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.FormError;
import com.phakel.ginkgo.tracker.error.NotFoundError;
import com.phakel.ginkgo.tracker.form.video.AddVideoForm;
import com.phakel.ginkgo.tracker.repository.UserRepository;
import com.phakel.ginkgo.tracker.repository.VideoRepository;
import com.phakel.ginkgo.tracker.service.IVideoService;
import com.phakel.ginkgo.tracker.util.Md5Util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;

@ApplicationScoped
public class VideoService implements IVideoService {
    @Inject
    VideoRepository videoRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    Validator validator;

    @Override
    public Result<VideoDto, ? extends Error> getVideoByVideoId(String videoId) {
        var video = videoRepository.findByVideoIdOptional(videoId);
        return video.isPresent() ?
                new Result.Success<>(video.get().toDto()) :
                new Result.Failure<>(new NotFoundError("video.notfound"));
    }

    @Override
    public Result<VideoDto, ? extends Error> postVideo(AddVideoForm form) {
        form.setViolations(validator.validate(form));
        if (!form.getViolations().isEmpty())
            return new Result.Failure<>(new FormError(form.getViolations()));

        var hash = Md5Util.fileMd5(form.getVideo()).orElse("");
        if (videoRepository.isVideoExistByHash(hash))
            return new Result.Failure<>(new ConflictError("video.conflict"));

        var author = userRepository.findByUserIdOptional(form.getAuthorId());
        if (author.isEmpty())
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var video = new Video();
        video.setAuthor(author.get());
        video.setDescription(form.getDescription());
        video.setHash(hash);
        video.setTitle(form.getTitle());

        // TODO Video Slice and Distribution

        video.persistAndFlush();
        return new Result.Success<>(video.toDto());
    }
}
