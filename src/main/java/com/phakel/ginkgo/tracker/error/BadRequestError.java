package com.phakel.ginkgo.tracker.error;

public record BadRequestError(String message) implements Error {
}
