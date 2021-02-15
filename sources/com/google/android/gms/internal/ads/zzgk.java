package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzgk {
    private static final String TAG = zzgk.class.getSimpleName();
    private final String className;
    private final String zzabk;
    private final int zzabl = 2;
    private volatile Method zzabm = null;
    private final Class<?>[] zzabn;
    private CountDownLatch zzabo = new CountDownLatch(1);
    private final zzex zzwg;

    public zzgk(zzex zzex, String str, String str2, Class<?>... clsArr) {
        this.zzwg = zzex;
        this.className = str;
        this.zzabk = str2;
        this.zzabn = clsArr;
        zzex.zzcg().submit(new zzgj(this));
    }

    /* access modifiers changed from: private */
    public final void zzda() {
        try {
            Class loadClass = this.zzwg.zzch().loadClass(zzb(this.zzwg.zzcj(), this.className));
            if (loadClass != null) {
                this.zzabm = loadClass.getMethod(zzb(this.zzwg.zzcj(), this.zzabk), this.zzabn);
                if (this.zzabm == null) {
                    this.zzabo.countDown();
                } else {
                    this.zzabo.countDown();
                }
            }
        } catch (zzeh unused) {
        } catch (UnsupportedEncodingException unused2) {
        } catch (ClassNotFoundException unused3) {
        } catch (NoSuchMethodException unused4) {
        } catch (NullPointerException unused5) {
        } finally {
            this.zzabo.countDown();
        }
    }

    private final String zzb(byte[] bArr, String str) throws zzeh, UnsupportedEncodingException {
        return new String(this.zzwg.zzci().zza(bArr, str), "UTF-8");
    }

    public final Method zzdb() {
        if (this.zzabm != null) {
            return this.zzabm;
        }
        try {
            if (!this.zzabo.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zzabm;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
