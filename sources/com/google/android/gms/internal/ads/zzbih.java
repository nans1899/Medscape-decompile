package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbih implements zzdwe {
    private final Context zzcyt;
    private final String zzdjk;
    private final zzbhy zzenr;
    private final boolean zzens;
    private final boolean zzent;
    private final zzeg zzenu;
    private final zzacb zzenv;
    private final zzbbx zzenw;
    private final zzabs zzenx;
    private final zzk zzeny;
    private final zzb zzenz;
    private final zzts zzeoa;
    private final zzsu zzeob;
    private final boolean zzeoc;
    private final zzdnv zzeod;
    private final zzdnw zzeoe;

    zzbih(Context context, zzbhy zzbhy, String str, boolean z, boolean z2, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzabs zzabs, zzk zzk, zzb zzb, zzts zzts, zzsu zzsu, boolean z3, zzdnv zzdnv, zzdnw zzdnw) {
        this.zzcyt = context;
        this.zzenr = zzbhy;
        this.zzdjk = str;
        this.zzens = z;
        this.zzent = z2;
        this.zzenu = zzeg;
        this.zzenv = zzacb;
        this.zzenw = zzbbx;
        this.zzenx = zzabs;
        this.zzeny = zzk;
        this.zzenz = zzb;
        this.zzeoa = zzts;
        this.zzeob = zzsu;
        this.zzeoc = z3;
        this.zzeod = zzdnv;
        this.zzeoe = zzdnw;
    }

    public final Object get() {
        Context context = this.zzcyt;
        zzbhy zzbhy = this.zzenr;
        String str = this.zzdjk;
        boolean z = this.zzens;
        boolean z2 = this.zzent;
        zzeg zzeg = this.zzenu;
        zzacb zzacb = this.zzenv;
        zzbbx zzbbx = this.zzenw;
        zzabs zzabs = this.zzenx;
        zzk zzk = this.zzeny;
        zzb zzb = this.zzenz;
        zzts zzts = this.zzeoa;
        zzsu zzsu = this.zzeob;
        boolean z3 = this.zzeoc;
        zzdnv zzdnv = this.zzeod;
        zzdnw zzdnw = this.zzeoe;
        zzbib zzbib = r3;
        zzbib zzbib2 = new zzbib();
        zzbib zzbib3 = zzbib2;
        zzbhz zzbhz = r0;
        zzbhz zzbhz2 = new zzbhz(context);
        boolean z4 = z2;
        zzbig zzbig = new zzbig(zzbhz, zzbib, zzbhy, str, z, z2, zzeg, zzacb, zzbbx, zzabs, zzk, zzb, zzts, zzsu, z3, zzdnv, zzdnw);
        zzbgu zzbgu = new zzbgu(zzbig);
        zzbig.setWebChromeClient(new zzbgb(zzbgu));
        zzbib3.zza((zzbgj) zzbgu, z4);
        return zzbgu;
    }
}
