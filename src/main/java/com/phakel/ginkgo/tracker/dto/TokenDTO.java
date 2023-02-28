package com.phakel.ginkgo.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO implements DTO {
    private String token;
}
