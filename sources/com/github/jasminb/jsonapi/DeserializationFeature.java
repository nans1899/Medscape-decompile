package com.github.jasminb.jsonapi;

import java.util.HashSet;
import java.util.Set;

public enum DeserializationFeature {
    REQUIRE_RESOURCE_ID(true),
    ALLOW_UNKNOWN_INCLUSIONS(false);
    
    private final boolean enabledByDefault;

    private DeserializationFeature(boolean z) {
        this.enabledByDefault = z;
    }

    public static Set<DeserializationFeature> getDefaultFeatures() {
        HashSet hashSet = new HashSet();
        for (DeserializationFeature deserializationFeature : values()) {
            if (deserializationFeature.enabledByDefault) {
                hashSet.add(deserializationFeature);
            }
        }
        return hashSet;
    }
}
