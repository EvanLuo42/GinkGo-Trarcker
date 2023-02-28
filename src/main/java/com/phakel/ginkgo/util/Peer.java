package com.phakel.ginkgo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Peer<K, V> {
    private K key;

    private V value;

    public static <K, V> Peer<K, V> of(K key, V value) {
        return new Peer<>(key, value);
    }
}
