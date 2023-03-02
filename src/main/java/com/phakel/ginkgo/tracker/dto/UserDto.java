package com.phakel.ginkgo.tracker.dto;

import com.phakel.ginkgo.tracker.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto implements Dto {
    private String id;

    private String username;

    private String email;

    private UserRole role;
}
