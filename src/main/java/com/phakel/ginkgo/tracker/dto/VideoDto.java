package com.phakel.ginkgo.tracker.dto;

import com.phakel.ginkgo.tracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoDto implements Dto {
    private String id;

    private String title;

    private String description;

    private UserDto author;

    private String hash;
}
