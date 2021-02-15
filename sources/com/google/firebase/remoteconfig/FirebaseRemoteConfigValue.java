package com.google.firebase.remoteconfig;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public interface FirebaseRemoteConfigValue {
    boolean asBoolean() throws IllegalArgumentException;

    byte[] asByteArray();

    double asDouble() throws IllegalArgumentException;

    long asLong() throws IllegalArgumentException;

    String asString();

    int getSource();
}
