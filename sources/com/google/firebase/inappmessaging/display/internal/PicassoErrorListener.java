package com.google.firebase.inappmessaging.display.internal;

import android.net.Uri;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class PicassoErrorListener implements Picasso.Listener {
    private FirebaseInAppMessagingDisplayCallbacks displayCallbacks;
    private InAppMessage inAppMessage;

    @Inject
    PicassoErrorListener() {
    }

    public void setInAppMessage(InAppMessage inAppMessage2, FirebaseInAppMessagingDisplayCallbacks firebaseInAppMessagingDisplayCallbacks) {
        this.inAppMessage = inAppMessage2;
        this.displayCallbacks = firebaseInAppMessagingDisplayCallbacks;
    }

    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exc) {
        if (this.inAppMessage != null && this.displayCallbacks != null) {
            if (!(exc instanceof IOException) || !exc.getLocalizedMessage().contains("Failed to decode")) {
                this.displayCallbacks.displayErrorEncountered(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.UNSPECIFIED_RENDER_ERROR);
            } else {
                this.displayCallbacks.displayErrorEncountered(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason.IMAGE_UNSUPPORTED_FORMAT);
            }
        }
    }
}
