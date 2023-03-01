package com.phakel.ginkgo.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto implements Dto {
    private String token;
}
