package com.phakel.ginkgo.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoDto implements Dto {
    private String id;

    private String title;

    private String description;

    private String author;
}
