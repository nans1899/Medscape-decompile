package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* compiled from: JsonDataEncoderBuilder */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$1 implements ObjectEncoder {
    private static final JsonDataEncoderBuilder$$Lambda$1 instance = new JsonDataEncoderBuilder$$Lambda$1();

    private JsonDataEncoderBuilder$$Lambda$1() {
    }

    public static ObjectEncoder lambdaFactory$() {
        return instance;
    }

    public void encode(Object obj, Object obj2) {
        JsonDataEncoderBuilder.lambda$static$0(obj, (ObjectEncoderContext) obj2);
    }
}
