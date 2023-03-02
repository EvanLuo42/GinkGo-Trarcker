package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.entity.VideoSlice;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VideoSliceRepository implements PanacheRepository<VideoSlice> {
    public boolean isSliceExistByHash(String hash) {
        return find("hash", hash).firstResultOptional().isPresent();
    }
}
