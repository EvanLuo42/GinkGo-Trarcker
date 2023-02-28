package com.phakel.ginkgo.tracker.error;

import com.phakel.ginkgo.tracker.util.Peer;

import java.util.List;
import java.util.Optional;

public class ErrorToHttpCode {
    private static final List<Peer<Class<? extends Error>, Integer>> peers = List.of(
            Peer.of(ConflictError.class, 409),
            Peer.of(FormError.class, 400),
            Peer.of(NotFoundError.class, 404)
    );

    public static <T> Optional<Integer> from(T error) {
        for (var peer : peers) {
            if (error.getClass().equals(peer.getKey())) return Optional.of(peer.getValue());
        }
        return Optional.empty();
    }
}
