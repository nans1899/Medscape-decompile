package com.medscape.android.welcome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.wbmd.wbmddatacompliance.utils.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/medscape/android/welcome/WelcomeActivity$gdprReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WelcomeActivity.kt */
public final class WelcomeActivity$gdprReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ WelcomeActivity this$0;

    WelcomeActivity$gdprReceiver$1(WelcomeActivity welcomeActivity) {
        this.this$0 = welcomeActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String action;
        Intrinsics.checkNotNullParameter(context, "context");
        if (intent != null && (action = intent.getAction()) != null) {
            if (Intrinsics.areEqual((Object) action, (Object) Constants.BROADCAST_ACCEPT_ACTION)) {
                this.this$0.performAppStartup();
                OmnitureManager.get().trackModuleAbsolute(this.this$0.getApplicationContext(), (String) null, "gdpr-accept", (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) action, (Object) Constants.BROADCAST_ACTIVITY_VIEW)) {
                OmnitureManager.get().trackPageView(this.this$0.getApplicationContext(), (String) null, "gdpr-roadblock", (String) null, (String) null, (String) null, (Map<String, Object>) null);
            }
        }
    }
}
