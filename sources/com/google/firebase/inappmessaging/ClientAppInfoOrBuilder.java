package com.google.firebase.inappmessaging;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface ClientAppInfoOrBuilder extends MessageLiteOrBuilder {
    String getFirebaseInstanceId();

    ByteString getFirebaseInstanceIdBytes();

    String getGoogleAppId();

    ByteString getGoogleAppIdBytes();

    boolean hasFirebaseInstanceId();

    boolean hasGoogleAppId();
}
