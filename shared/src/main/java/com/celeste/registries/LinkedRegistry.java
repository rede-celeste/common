package com.celeste.registries;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedRegistry<K, V>  {

    private final Map<K, V> map;

    public LinkedRegistry() {
        this.map = new LinkedHashMap<>();
    }

    public void register(K k, V v) {
        map.put(k, v);
    }

    public V getByValue(K k) {
        return map.get(k);
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public Set<Map.Entry<K, V>> getKeys() {
        return map.entrySet();
    }

    public Collection<V> getAll() {
        return map.values();
    }

    private void wipe() {
        map.clear();
    }

}
