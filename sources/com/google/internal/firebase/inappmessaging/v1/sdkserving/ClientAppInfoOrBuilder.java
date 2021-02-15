package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface ClientAppInfoOrBuilder extends MessageLiteOrBuilder {
    String getAppInstanceId();

    ByteString getAppInstanceIdBytes();

    String getAppInstanceIdToken();

    ByteString getAppInstanceIdTokenBytes();

    String getGmpAppId();

    ByteString getGmpAppIdBytes();
}
