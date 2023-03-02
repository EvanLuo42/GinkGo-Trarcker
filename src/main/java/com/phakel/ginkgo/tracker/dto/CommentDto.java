package com.phakel.ginkgo.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto implements Dto {
    private String id;

    private UserDto authorName;

    private String text;
}
