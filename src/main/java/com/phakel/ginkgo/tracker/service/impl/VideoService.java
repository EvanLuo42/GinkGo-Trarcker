package com.phakel.ginkgo.tracker.service.impl;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.VideoDto;
import com.phakel.ginkgo.tracker.entity.Video;
import com.phakel.ginkgo.tracker.entity.VideoSlice;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.FormError;
import com.phakel.ginkgo.tracker.error.NotFoundError;
import com.phakel.ginkgo.tracker.form.node.UploadVideoForm;
import com.phakel.ginkgo.tracker.form.video.AddVideoForm;
import com.phakel.ginkgo.tracker.repository.UserRepository;
import com.phakel.ginkgo.tracker.repository.VideoRepository;
import com.phakel.ginkgo.tracker.repository.VideoSliceRepository;
import com.phakel.ginkgo.tracker.service.INodeRestService;
import com.phakel.ginkgo.tracker.service.IVideoService;
import com.phakel.ginkgo.tracker.util.Md5Util;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

@ApplicationScoped
public class VideoService implements IVideoService {
    @Inject
    VideoRepository videoRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    VideoSliceRepository videoSliceRepository;

    @Inject
    INodeRestService nodeRestService;

    @Inject
    Validator validator;

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

        var author = userRepository.findByUserIdOptional(form.getAuthorId());
        if (author.isEmpty())
            return new Result.Failure<>(new NotFoundError("user.notfound"));

        var video = new Video();
        video.setAuthor(author.get());
        video.setDescription(form.getDescription());
        video.setTitle(form.getTitle());
        video.persistAndFlush();

        InputStream inputStream;
        for (var slice : form.getVideoSlices()) {
            var videoSlice = new VideoSlice();
            videoSlice.setVideo(video);
            try {
                inputStream = new FileInputStream(slice);
            } catch (FileNotFoundException e) {
                video.delete();
                return new Result.Failure<>(new NotFoundError("video.slice.upload.error"));
            }
            var sliceHash = Md5Util.fileMd5(inputStream).orElse("");
            var apiUrl = URI.create("");
            var api = RestClientBuilder.newBuilder()
                    .baseUri(apiUrl)
                    .build(INodeRestService.class);
            api.postVideo(new UploadVideoForm(inputStream, sliceHash + video.getId()));
            videoSlice.setHash(sliceHash);
            videoSliceRepository.persistAndFlush(videoSlice);
        }
        return new Result.Success<>(video.toDto());
    }
}
