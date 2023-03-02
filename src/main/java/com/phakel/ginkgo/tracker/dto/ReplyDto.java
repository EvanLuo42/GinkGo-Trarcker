package com.phakel.ginkgo.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyDto implements Dto {
    private String id;

    private String text;

    private UserDto author;
}
