package com.phakel.ginkgo.tracker.dto;

import lombok.Data;

@Data
public class UserDto implements Dto {
    private String id;

    private String username;

    private String email;
}
