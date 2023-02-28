package com.phakel.ginkgo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO implements DTO {
    private String token;
}
