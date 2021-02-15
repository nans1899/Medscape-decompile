package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.InAppMessage;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface FirebaseInAppMessagingClickListener {
    void messageClicked(InAppMessage inAppMessage, Action action);
}
