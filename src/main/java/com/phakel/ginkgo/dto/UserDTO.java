package com.phakel.ginkgo.dto;

import lombok.Data;

@Data
public class UserDTO implements DTO {
    private String username;

    private String email;
}
