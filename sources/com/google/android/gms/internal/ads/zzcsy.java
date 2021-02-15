package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsy extends zzaqs {
    private final zzcsp zzgha;
    private final zzbbu zzgnp;
    private final Context zzvr;

    public zzcsy(Context context, zzcsp zzcsp, zzbbu zzbbu) {
        this.zzvr = context;
        this.zzgnp = zzbbu;
        this.zzgha = zzcsp;
    }

    public final void zzc(Intent intent) {
        String stringExtra = intent.getStringExtra("offline_notification_action");
        if (stringExtra.equals("offline_notification_clicked") || stringExtra.equals("offline_notification_dismissed")) {
            String stringExtra2 = intent.getStringExtra("gws_query_id");
            String stringExtra3 = intent.getStringExtra("uri");
            zzp.zzkr();
            boolean zzbf = zzayu.zzbf(this.zzvr);
            int i = zzcsq.zzgnh;
            if (stringExtra.equals("offline_notification_clicked")) {
                if (zzbf) {
                    i = zzcsq.zzgng;
                }
                Context context = this.zzvr;
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(stringExtra3);
                if (launchIntentForPackage == null) {
                    launchIntentForPackage = new Intent("android.intent.action.VIEW");
                    launchIntentForPackage.setData(Uri.parse(stringExtra3));
                }
                launchIntentForPackage.addFlags(268435456);
                context.startActivity(launchIntentForPackage);
            }
            try {
                SQLiteDatabase writableDatabase = this.zzgha.getWritableDatabase();
                if (i == zzcsq.zzgng) {
                    this.zzgha.zza(writableDatabase, this.zzgnp, stringExtra2);
                    return;
                }
                writableDatabase.delete("offline_buffered_pings", "gws_query_id = ? AND event_state = ?", new String[]{stringExtra2, Integer.toString(0)});
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51);
                sb.append("Failed to get writable offline buffering database: ");
                sb.append(valueOf);
                zzayp.zzfc(sb.toString());
            }
        }
    }
}
