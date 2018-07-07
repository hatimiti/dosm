package com.github.hatimiti.dosm.base;

import java.io.Serializable;

public class FlashAttribute<V extends Serializable> implements Serializable {

    private String name;
    private V value;

    public FlashAttribute(final String name, final V value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public V getValue() {
        return value;
    }
}
