package com.google.android.gms.internal.ads;

import java.io.File;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdus {
    private final zzgr zzhny;
    private final File zzhnz;
    private final File zzhoa;
    private final File zzhob;
    private byte[] zzhoc;

    public zzdus(zzgr zzgr, File file, File file2, File file3) {
        this.zzhny = zzgr;
        this.zzhnz = file;
        this.zzhoa = file3;
        this.zzhob = file2;
    }

    public final zzgr zzawv() {
        return this.zzhny;
    }

    public final File zzaww() {
        return this.zzhnz;
    }

    public final File zzawx() {
        return this.zzhoa;
    }

    public final byte[] zzawy() {
        if (this.zzhoc == null) {
            this.zzhoc = zzduu.zzf(this.zzhob);
        }
        byte[] bArr = this.zzhoc;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public final boolean zza() {
        return System.currentTimeMillis() / 1000 > this.zzhny.zzdi();
    }

    public final boolean zzfg(long j) {
        return this.zzhny.zzdi() - (System.currentTimeMillis() / 1000) < 3600;
    }
}
