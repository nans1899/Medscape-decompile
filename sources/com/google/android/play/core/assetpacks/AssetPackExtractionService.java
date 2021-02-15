package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.facebook.share.internal.ShareConstants;
import com.google.android.play.core.internal.aa;

public class AssetPackExtractionService extends Service {
    Context a;
    i b;
    bb c;
    private final aa d = new aa("AssetPackExtractionService");
    private b e;
    private NotificationManager f;

    private final synchronized void b(Bundle bundle) {
        String string = bundle.getString("notification_title");
        String string2 = bundle.getString("notification_subtext");
        long j = bundle.getLong("notification_timeout");
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("notification_on_click_intent");
        Notification.Builder timeoutAfter = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this.a, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j) : new Notification.Builder(this.a).setPriority(-2);
        if (pendingIntent != null) {
            timeoutAfter.setContentIntent(pendingIntent);
        }
        timeoutAfter.setSmallIcon(17301633).setOngoing(false).setContentTitle(string).setSubText(string2);
        if (Build.VERSION.SDK_INT >= 21) {
            timeoutAfter.setColor(bundle.getInt("notification_color")).setVisibility(-1);
        }
        Notification build = timeoutAfter.build();
        this.d.c("Starting foreground service.", new Object[0]);
        this.b.a(true);
        if (Build.VERSION.SDK_INT >= 26) {
            this.f.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", bundle.getString("notification_channel_name"), 2));
        }
        startForeground(-1883842196, build);
    }

    public final synchronized Bundle a(Bundle bundle) {
        int i = bundle.getInt(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE);
        aa aaVar = this.d;
        Integer valueOf = Integer.valueOf(i);
        aaVar.a("updateServiceState: %d", valueOf);
        if (i == 1) {
            b(bundle);
        } else if (i != 2) {
            this.d.b("Unknown action type received: %d", valueOf);
        } else {
            a();
        }
        return new Bundle();
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        this.d.c("Stopping service.", new Object[0]);
        this.b.a(false);
        stopForeground(true);
        stopSelf();
    }

    public final IBinder onBind(Intent intent) {
        return this.e;
    }

    public final void onCreate() {
        super.onCreate();
        this.d.a("onCreate", new Object[0]);
        dd.a(getApplicationContext()).a(this);
        this.e = new b(this.a, this, this.c);
        this.f = (NotificationManager) this.a.getSystemService("notification");
    }
}
