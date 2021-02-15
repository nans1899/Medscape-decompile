package com.google.firebase.inappmessaging;

import com.google.android.gms.tasks.Task;
import com.google.firebase.inappmessaging.model.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface FirebaseInAppMessagingDisplayCallbacks {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum InAppMessagingDismissType {
        UNKNOWN_DISMISS_TYPE,
        AUTO,
        CLICK,
        SWIPE
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum InAppMessagingErrorReason {
        UNSPECIFIED_RENDER_ERROR,
        IMAGE_FETCH_ERROR,
        IMAGE_DISPLAY_ERROR,
        IMAGE_UNSUPPORTED_FORMAT
    }

    Task<Void> displayErrorEncountered(InAppMessagingErrorReason inAppMessagingErrorReason);

    Task<Void> impressionDetected();

    Task<Void> messageClicked(Action action);

    Task<Void> messageDismissed(InAppMessagingDismissType inAppMessagingDismissType);
}
