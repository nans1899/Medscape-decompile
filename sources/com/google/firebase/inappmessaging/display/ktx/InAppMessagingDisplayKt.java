package com.google.firebase.inappmessaging.display.ktx;

import com.google.firebase.inappmessaging.display.FirebaseInAppMessagingDisplay;
import com.google.firebase.ktx.Firebase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"LIBRARY_NAME", "", "inAppMessagingDisplay", "Lcom/google/firebase/inappmessaging/display/FirebaseInAppMessagingDisplay;", "Lcom/google/firebase/ktx/Firebase;", "getInAppMessagingDisplay", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/inappmessaging/display/FirebaseInAppMessagingDisplay;", "com.google.firebase-firebase-inappmessaging-display-ktx"}, k = 2, mv = {1, 1, 13})
/* compiled from: com.google.firebase:firebase-inappmessaging-display-ktx@@19.0.5 */
public final class InAppMessagingDisplayKt {
    public static final String LIBRARY_NAME = "fire-iamd-ktx";

    public static final FirebaseInAppMessagingDisplay getInAppMessagingDisplay(Firebase firebase2) {
        Intrinsics.checkParameterIsNotNull(firebase2, "receiver$0");
        FirebaseInAppMessagingDisplay instance = FirebaseInAppMessagingDisplay.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "FirebaseInAppMessagingDisplay.getInstance()");
        return instance;
    }
}
