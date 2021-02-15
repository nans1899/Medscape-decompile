package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbhz extends MutableContextWrapper {
    private Context zzaai;
    private Activity zzeea;
    private Context zzeqn;

    public zzbhz(Context context) {
        super(context);
        setBaseContext(context);
    }

    public final void setBaseContext(Context context) {
        this.zzaai = context.getApplicationContext();
        this.zzeea = context instanceof Activity ? (Activity) context : null;
        this.zzeqn = context;
        super.setBaseContext(this.zzaai);
    }

    public final void startActivity(Intent intent) {
        Activity activity = this.zzeea;
        if (activity != null) {
            activity.startActivity(intent);
            return;
        }
        intent.setFlags(268435456);
        this.zzaai.startActivity(intent);
    }

    public final Activity zzzu() {
        return this.zzeea;
    }

    public final Object getSystemService(String str) {
        return this.zzeqn.getSystemService(str);
    }

    public final Context zzabr() {
        return this.zzeqn;
    }
}
