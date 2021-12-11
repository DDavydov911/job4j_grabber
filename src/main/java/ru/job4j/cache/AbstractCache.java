package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> val = new SoftReference<>(value);
        cache.put(key, val);
    }

    public V get(K key) {
        V value;
        if (cache.get(key) == null) {
            value = load(key);
            this.put(key, value);
        } else {
            value = cache.get(key).get();
        }
        return value;
    }

    protected abstract V load(K key);

}