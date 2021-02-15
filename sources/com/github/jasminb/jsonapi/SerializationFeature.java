package com.github.jasminb.jsonapi;

import java.util.HashSet;
import java.util.Set;

public enum SerializationFeature {
    INCLUDE_RELATIONSHIP_ATTRIBUTES(false),
    INCLUDE_META(true),
    INCLUDE_LINKS(true);
    
    final boolean enabled;

    private SerializationFeature(boolean z) {
        this.enabled = z;
    }

    public static Set<SerializationFeature> getDefaultFeatures() {
        HashSet hashSet = new HashSet();
        for (SerializationFeature serializationFeature : values()) {
            if (serializationFeature.enabled) {
                hashSet.add(serializationFeature);
            }
        }
        return hashSet;
    }
}
