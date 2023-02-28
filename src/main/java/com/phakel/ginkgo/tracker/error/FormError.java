package com.phakel.ginkgo.tracker.error;

import java.util.List;

public record FormError(List<String> violations) implements Error {
}
