package com.phakel.ginkgo.tracker.service;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.dto.VideoDto;
import com.phakel.ginkgo.tracker.form.video.AddVideoForm;
import com.phakel.ginkgo.tracker.error.Error;

public interface IVideoService {
    Result<VideoDto, ? extends Error> getVideoByVideoId(String videoId);

    Result<VideoDto, ? extends Error> postVideo(AddVideoForm form);
}
