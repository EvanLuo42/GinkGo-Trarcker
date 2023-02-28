package com.phakel.ginkgo.error;

import java.util.List;

public record FormError(List<String> violations) implements Error {
}
