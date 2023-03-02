package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.dto.VideoDto;
import com.phakel.ginkgo.tracker.entity.Video;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class VideoRepository implements PanacheRepository<Video> {
    public Video findByVideoId(String videoId) {
        return find("id", videoId).firstResult();
    }

    public Optional<Video> findByVideoIdOptional(String videoId) {
        return find("id", videoId).firstResultOptional();
    }

    public Optional<VideoDto> findByVideoIdOptionalToDto(String videoId) {
        return find("id", videoId)
                .stream()
                .map(Video::toDto)
                .findFirst();
    }

    public boolean isVideoExistByVideoId(String videoId) {
        return findByVideoIdOptional(videoId).isPresent();
    }
}
