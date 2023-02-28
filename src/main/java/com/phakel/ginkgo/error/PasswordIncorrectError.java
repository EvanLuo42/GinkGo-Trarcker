package com.phakel.ginkgo.error;

public record PasswordIncorrectError(String message) implements Error {
}
