package com.phakel.ginkgo.tracker.error;

import com.phakel.ginkgo.tracker.util.Peer;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class ErrorToHttpCode {
    private static final List<Peer<Class<? extends Error>, Response.Status>> peers = List.of(
            Peer.of(ConflictError.class, Response.Status.CONFLICT),
            Peer.of(FormError.class, Response.Status.BAD_REQUEST),
            Peer.of(NotFoundError.class, Response.Status.NOT_FOUND)
    );

    public static <T> Optional<Integer> from(T error) {
        for (var peer : peers) {
            if (error.getClass().equals(peer.getKey()))
                return Optional.of(peer.getValue().getStatusCode());
        }
        return Optional.empty();
    }
}
