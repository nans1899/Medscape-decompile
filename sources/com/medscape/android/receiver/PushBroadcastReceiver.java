package com.medscape.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.medscape.android.notifications.NotificationAuthenticationGateActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/medscape/android/receiver/PushBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PushBroadcastReceiver.kt */
public final class PushBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("uri");
            if (stringExtra == null) {
                stringExtra = "";
            }
            Intent intent2 = new Intent(context, NotificationAuthenticationGateActivity.class);
            intent2.putExtra("payload", stringExtra);
            intent2.addFlags(268435456);
            if (context != null) {
                context.startActivity(intent2);
            }
        }
    }
}
