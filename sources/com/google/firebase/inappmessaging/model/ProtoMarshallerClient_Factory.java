package com.google.firebase.inappmessaging.model;

import dagger.internal.Factory;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProtoMarshallerClient_Factory implements Factory<ProtoMarshallerClient> {
    private static final ProtoMarshallerClient_Factory INSTANCE = new ProtoMarshallerClient_Factory();

    public ProtoMarshallerClient get() {
        return new ProtoMarshallerClient();
    }

    public static ProtoMarshallerClient_Factory create() {
        return INSTANCE;
    }

    public static ProtoMarshallerClient newInstance() {
        return new ProtoMarshallerClient();
    }
}
