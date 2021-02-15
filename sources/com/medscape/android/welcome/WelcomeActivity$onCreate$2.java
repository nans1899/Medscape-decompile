package com.medscape.android.welcome;

import com.medscape.android.util.Util;
import com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "shouldShow", "", "shouldShowAcceptancePromptResult"}, k = 3, mv = {1, 4, 0})
/* compiled from: WelcomeActivity.kt */
final class WelcomeActivity$onCreate$2 implements IShowAcceptDialogCallback {
    final /* synthetic */ WelcomeActivity this$0;

    WelcomeActivity$onCreate$2(WelcomeActivity welcomeActivity) {
        this.this$0 = welcomeActivity;
    }

    public final void shouldShowAcceptancePromptResult(boolean z) {
        if (z) {
            this.this$0.startActivity(Util.getGDPRRoadBlock(this.this$0));
            return;
        }
        this.this$0.registerLoginReceiver();
        this.this$0.initializeForceUp();
        this.this$0.performAppStartup();
    }
}
