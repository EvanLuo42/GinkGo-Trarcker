package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.dto.VideoDto;
import com.phakel.ginkgo.tracker.entity.Video;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class VideoRepository implements PanacheRepository<Video> {
    public Video findByVideoId(String videoId) {
        return find("videoId", videoId).firstResult();
    }

    public Optional<Video> findByVideoIdOptional(String videoId) {
        return find("videoId", videoId).firstResultOptional();
    }

    public Optional<VideoDto> findByVideoIdOptionalToDto(String videoId) {
        return find("videoId", videoId).project(VideoDto.class).firstResultOptional();
    }

    public boolean isVideoExistByVideoId(String videoId) {
        return findByVideoId(videoId) != null;
    }
}
