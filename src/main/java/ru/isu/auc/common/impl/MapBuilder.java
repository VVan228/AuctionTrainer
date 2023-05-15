package ru.isu.auc.common.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

    private Map<K, V> map;
    private final boolean immutable;

    public MapBuilder(boolean immutable) {
        this.immutable = immutable;
        this.map = new HashMap<>();
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public Map<K, V> build() {
        if (immutable) {
            return Collections.unmodifiableMap(map);
        }

        return map;
    }
}