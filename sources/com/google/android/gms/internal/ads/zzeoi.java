package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeoi implements zzbs, Closeable, Iterator<zzbp> {
    private static zzeoq zzdc = zzeoq.zzn(zzeoi.class);
    private static final zzbp zzitw = new zzeoh("eof ");
    long zzawj = 0;
    long zzbgl = 0;
    protected zzeok zzitt;
    protected zzbo zzitx;
    private zzbp zzity = null;
    long zzitz = 0;
    private List<zzbp> zziua = new ArrayList();

    public final List<zzbp> zzbkg() {
        if (this.zzitt == null || this.zzity == zzitw) {
            return this.zziua;
        }
        return new zzeoo(this.zziua, this);
    }

    public void zza(zzeok zzeok, long j, zzbo zzbo) throws IOException {
        this.zzitt = zzeok;
        long position = zzeok.position();
        this.zzbgl = position;
        this.zzitz = position;
        zzeok.zzfc(zzeok.position() + j);
        this.zzawj = zzeok.position();
        this.zzitx = zzbo;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        zzbp zzbp = this.zzity;
        if (zzbp == zzitw) {
            return false;
        }
        if (zzbp != null) {
            return true;
        }
        try {
            this.zzity = (zzbp) next();
            return true;
        } catch (NoSuchElementException unused) {
            this.zzity = zzitw;
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbkh */
    public final zzbp next() {
        zzbp zza;
        zzbp zzbp = this.zzity;
        if (zzbp == null || zzbp == zzitw) {
            zzeok zzeok = this.zzitt;
            if (zzeok == null || this.zzitz >= this.zzawj) {
                this.zzity = zzitw;
                throw new NoSuchElementException();
            }
            try {
                synchronized (zzeok) {
                    this.zzitt.zzfc(this.zzitz);
                    zza = this.zzitx.zza(this.zzitt, this);
                    this.zzitz = this.zzitt.position();
                }
                return zza;
            } catch (EOFException unused) {
                throw new NoSuchElementException();
            } catch (IOException unused2) {
                throw new NoSuchElementException();
            }
        } else {
            this.zzity = null;
            return zzbp;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        for (int i = 0; i < this.zziua.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(this.zziua.get(i).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public void close() throws IOException {
        this.zzitt.close();
    }
}
