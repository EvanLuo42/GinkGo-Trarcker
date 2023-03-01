package com.phakel.ginkgo.tracker.error;

public record AuthenticationFailedError(String message) implements Error {
}
