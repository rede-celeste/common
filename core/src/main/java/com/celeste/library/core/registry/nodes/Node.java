package com.celeste.library.core.registry.nodes;

import com.celeste.library.core.registry.entry.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<K,V> implements Entry<K,V> {

  private final int hash;

  private final K key;
  private V value;

  private Node<K,V> next;

  public V setValue(final V value) {
    final V oldValue = this.value;

    this.value = value;
    return oldValue;
  }

}
