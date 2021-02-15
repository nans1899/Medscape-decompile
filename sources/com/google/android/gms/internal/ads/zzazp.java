package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzazp {
    private Handler handler;
    private int state;
    private String zzbre;
    private String zzbuu;
    private String zzdps;
    private String zzdti;
    private int zzebo;
    private PointF zzebp;
    private PointF zzebq;
    private Runnable zzebr;
    private final Context zzvr;

    public zzazp(Context context) {
        this.state = 0;
        this.zzebr = new zzazo(this);
        this.zzvr = context;
        this.zzebo = ViewConfiguration.get(context).getScaledTouchSlop();
        zzp.zzlf().zzyj();
        this.handler = zzp.zzlf().getHandler();
    }

    public zzazp(Context context, String str) {
        this(context);
        this.zzdps = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(android.view.MotionEvent r11) {
        /*
            r10 = this;
            int r0 = r11.getActionMasked()
            int r1 = r11.getHistorySize()
            int r2 = r11.getPointerCount()
            r3 = 0
            if (r0 != 0) goto L_0x0021
            r10.state = r3
            android.graphics.PointF r0 = new android.graphics.PointF
            float r1 = r11.getX(r3)
            float r11 = r11.getY(r3)
            r0.<init>(r1, r11)
            r10.zzebp = r0
            return
        L_0x0021:
            int r4 = r10.state
            r5 = -1
            if (r4 != r5) goto L_0x0027
            return
        L_0x0027:
            r6 = 5
            r7 = 1
            if (r4 != 0) goto L_0x0056
            if (r0 != r6) goto L_0x0056
            r10.state = r6
            android.graphics.PointF r0 = new android.graphics.PointF
            float r1 = r11.getX(r7)
            float r11 = r11.getY(r7)
            r0.<init>(r1, r11)
            r10.zzebq = r0
            android.os.Handler r11 = r10.handler
            java.lang.Runnable r0 = r10.zzebr
            com.google.android.gms.internal.ads.zzaaq<java.lang.Long> r1 = com.google.android.gms.internal.ads.zzabb.zzctl
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r1 = r2.zzd(r1)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r11.postDelayed(r0, r1)
            return
        L_0x0056:
            int r4 = r10.state
            if (r4 != r6) goto L_0x00a2
            r4 = 2
            if (r2 == r4) goto L_0x005f
        L_0x005d:
            r3 = 1
            goto L_0x0097
        L_0x005f:
            if (r0 != r4) goto L_0x0097
            r0 = 0
            r2 = 0
        L_0x0063:
            if (r0 >= r1) goto L_0x007f
            float r4 = r11.getHistoricalX(r3, r0)
            float r6 = r11.getHistoricalY(r3, r0)
            float r8 = r11.getHistoricalX(r7, r0)
            float r9 = r11.getHistoricalY(r7, r0)
            boolean r4 = r10.zza(r4, r6, r8, r9)
            if (r4 != 0) goto L_0x007c
            r2 = 1
        L_0x007c:
            int r0 = r0 + 1
            goto L_0x0063
        L_0x007f:
            float r0 = r11.getX()
            float r1 = r11.getY()
            float r3 = r11.getX(r7)
            float r11 = r11.getY(r7)
            boolean r11 = r10.zza(r0, r1, r3, r11)
            if (r11 != 0) goto L_0x0096
            goto L_0x005d
        L_0x0096:
            r3 = r2
        L_0x0097:
            if (r3 == 0) goto L_0x00a2
            r10.state = r5
            android.os.Handler r11 = r10.handler
            java.lang.Runnable r0 = r10.zzebr
            r11.removeCallbacks(r0)
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzazp.zzd(android.view.MotionEvent):void");
    }

    private final boolean zza(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzebp.x - f) < ((float) this.zzebo) && Math.abs(this.zzebp.y - f2) < ((float) this.zzebo) && Math.abs(this.zzebq.x - f3) < ((float) this.zzebo) && Math.abs(this.zzebq.y - f4) < ((float) this.zzebo);
    }

    public final void showDialog() {
        try {
            if (!(this.zzvr instanceof Activity)) {
                zzayp.zzfd("Can not create dialog without Activity Context");
                return;
            }
            String str = !TextUtils.isEmpty(zzp.zzlb().zzyf()) ? "Creative Preview (Enabled)" : "Creative Preview";
            String str2 = zzp.zzlb().zzyg() ? "Troubleshooting (Enabled)" : "Troubleshooting";
            ArrayList arrayList = new ArrayList();
            int zza = zza((List<String>) arrayList, "Ad Information", true);
            int zza2 = zza((List<String>) arrayList, str, true);
            int zza3 = zza((List<String>) arrayList, str2, true);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.zzvr, zzp.zzkt().zzxz());
            builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new zzazr(this, zza, zza2, zza3));
            builder.create().show();
        } catch (WindowManager.BadTokenException e) {
            zzayp.zza("", e);
        }
    }

    public final void setAdUnitId(String str) {
        this.zzbuu = str;
    }

    public final void zzae(String str) {
        this.zzbre = str;
    }

    public final void zzet(String str) {
        this.zzdps = str;
    }

    public final void zzeu(String str) {
        this.zzdti = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        list.add(str);
        return list.size() - 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("{Dialog: ");
        sb.append(this.zzdps);
        sb.append(",DebugSignal: ");
        sb.append(this.zzdti);
        sb.append(",AFMA Version: ");
        sb.append(this.zzbre);
        sb.append(",Ad Unit ID: ");
        sb.append(this.zzbuu);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzyc() {
        zzp.zzlb().zza(this.zzvr, this.zzbuu, this.zzbre, this.zzdti);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzyd() {
        zzp.zzlb().zze(this.zzvr, this.zzbuu, this.zzbre);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, DialogInterface dialogInterface, int i) {
        zzp.zzkr();
        zzayu.zza(this.zzvr, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006e, code lost:
        if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(int r1, int r2, int r3, android.content.DialogInterface r4, int r5) {
        /*
            r0 = this;
            if (r5 != r1) goto L_0x009b
            android.content.Context r1 = r0.zzvr
            boolean r1 = r1 instanceof android.app.Activity
            if (r1 != 0) goto L_0x000e
            java.lang.String r1 = "Can not create dialog without Activity Context"
            com.google.android.gms.internal.ads.zzayp.zzfd(r1)
            return
        L_0x000e:
            java.lang.String r1 = r0.zzdps
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0071
            java.lang.String r2 = "\\+"
            java.lang.String r3 = "%20"
            java.lang.String r1 = r1.replaceAll(r2, r3)
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            android.net.Uri$Builder r1 = r2.encodedQuery(r1)
            android.net.Uri r1 = r1.build()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.google.android.gms.ads.internal.zzp.zzkr()
            java.util.Map r1 = com.google.android.gms.internal.ads.zzayu.zzj((android.net.Uri) r1)
            java.util.Set r3 = r1.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x003f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0062
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            r2.append(r4)
            java.lang.String r5 = " = "
            r2.append(r5)
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            r2.append(r4)
            java.lang.String r4 = "\n\n"
            r2.append(r4)
            goto L_0x003f
        L_0x0062:
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.trim()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            java.lang.String r1 = "No debug information"
        L_0x0073:
            android.app.AlertDialog$Builder r2 = new android.app.AlertDialog$Builder
            android.content.Context r3 = r0.zzvr
            r2.<init>(r3)
            r2.setMessage(r1)
            java.lang.String r3 = "Ad Information"
            r2.setTitle(r3)
            com.google.android.gms.internal.ads.zzazq r3 = new com.google.android.gms.internal.ads.zzazq
            r3.<init>(r0, r1)
            java.lang.String r1 = "Share"
            r2.setPositiveButton(r1, r3)
            android.content.DialogInterface$OnClickListener r1 = com.google.android.gms.internal.ads.zzazt.zzebv
            java.lang.String r3 = "Close"
            r2.setNegativeButton(r3, r1)
            android.app.AlertDialog r1 = r2.create()
            r1.show()
            return
        L_0x009b:
            if (r5 != r2) goto L_0x00ad
            java.lang.String r1 = "Debug mode [Creative Preview] selected."
            com.google.android.gms.internal.ads.zzayp.zzef(r1)
            com.google.android.gms.internal.ads.zzdzb r1 = com.google.android.gms.internal.ads.zzbbz.zzeep
            com.google.android.gms.internal.ads.zzazs r2 = new com.google.android.gms.internal.ads.zzazs
            r2.<init>(r0)
            r1.execute(r2)
            return
        L_0x00ad:
            if (r5 != r3) goto L_0x00be
            java.lang.String r1 = "Debug mode [Troubleshooting] selected."
            com.google.android.gms.internal.ads.zzayp.zzef(r1)
            com.google.android.gms.internal.ads.zzdzb r1 = com.google.android.gms.internal.ads.zzbbz.zzeep
            com.google.android.gms.internal.ads.zzazv r2 = new com.google.android.gms.internal.ads.zzazv
            r2.<init>(r0)
            r1.execute(r2)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzazp.zza(int, int, int, android.content.DialogInterface, int):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzye() {
        this.state = 4;
        showDialog();
    }
}
