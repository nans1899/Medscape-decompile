package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;

/* compiled from: JsonDataEncoderBuilder */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$5 implements ValueEncoder {
    private static final JsonDataEncoderBuilder$$Lambda$5 instance = new JsonDataEncoderBuilder$$Lambda$5();

    private JsonDataEncoderBuilder$$Lambda$5() {
    }

    public static ValueEncoder lambdaFactory$() {
        return instance;
    }

    public void encode(Object obj, Object obj2) {
        ((ValueEncoderContext) obj2).add(((Boolean) obj).booleanValue());
    }
}
