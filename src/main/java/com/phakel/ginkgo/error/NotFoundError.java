package com.phakel.ginkgo.error;

public record NotFoundError(String message) implements Error {
}
