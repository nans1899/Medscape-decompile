package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaqc extends zzaqn {
    private final Map<String, String> zzczo;
    private String zzdmx = zzdv("description");
    private long zzdmy = zzdw("start_ticks");
    private long zzdmz = zzdw("end_ticks");
    private String zzdna = zzdv("summary");
    private String zzdnb = zzdv("location");
    /* access modifiers changed from: private */
    public final Context zzvr;

    public zzaqc(zzbgj zzbgj, Map<String, String> map) {
        super(zzbgj, "createCalendarEvent");
        this.zzczo = map;
        this.zzvr = zzbgj.zzzu();
    }

    private final String zzdv(String str) {
        return TextUtils.isEmpty(this.zzczo.get(str)) ? "" : this.zzczo.get(str);
    }

    private final long zzdw(String str) {
        String str2 = this.zzczo.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final void execute() {
        if (this.zzvr == null) {
            zzdx("Activity context is not available.");
            return;
        }
        zzp.zzkr();
        if (!zzayu.zzat(this.zzvr).zzre()) {
            zzdx("This feature is not available on the device.");
            return;
        }
        zzp.zzkr();
        AlertDialog.Builder zzas = zzayu.zzas(this.zzvr);
        Resources resources = zzp.zzkv().getResources();
        zzas.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
        zzas.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        zzas.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzaqb(this));
        zzas.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaqe(this));
        zzas.create().show();
    }

    /* access modifiers changed from: package-private */
    public final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.zzdmx);
        data.putExtra("eventLocation", this.zzdnb);
        data.putExtra("description", this.zzdna);
        long j = this.zzdmy;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zzdmz;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(268435456);
        return data;
    }
}
