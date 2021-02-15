package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdxt<I, O, F, T> extends zzdyk<O> implements Runnable {
    @NullableDecl
    private zzdzc<? extends I> zzhrl;
    @NullableDecl
    private F zzhsh;

    static <I, O> zzdzc<O> zza(zzdzc<I> zzdzc, zzdya<? super I, ? extends O> zzdya, Executor executor) {
        zzdwd.checkNotNull(executor);
        zzdxs zzdxs = new zzdxs(zzdzc, zzdya);
        zzdzc.addListener(zzdxs, zzdze.zza(executor, zzdxs));
        return zzdxs;
    }

    /* access modifiers changed from: package-private */
    public abstract void setResult(@NullableDecl T t);

    /* access modifiers changed from: package-private */
    @NullableDecl
    public abstract T zzd(F f, @NullableDecl I i) throws Exception;

    static <I, O> zzdzc<O> zza(zzdzc<I> zzdzc, zzdvu<? super I, ? extends O> zzdvu, Executor executor) {
        zzdwd.checkNotNull(zzdvu);
        zzdxv zzdxv = new zzdxv(zzdzc, zzdvu);
        zzdzc.addListener(zzdxv, zzdze.zza(executor, zzdxv));
        return zzdxv;
    }

    zzdxt(zzdzc<? extends I> zzdzc, F f) {
        this.zzhrl = (zzdzc) zzdwd.checkNotNull(zzdzc);
        this.zzhsh = zzdwd.checkNotNull(f);
    }

    public final void run() {
        zzdzc<? extends I> zzdzc = this.zzhrl;
        F f = this.zzhsh;
        boolean z = true;
        boolean isCancelled = isCancelled() | (zzdzc == null);
        if (f != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.zzhrl = null;
            if (zzdzc.isCancelled()) {
                setFuture(zzdzc);
                return;
            }
            try {
                try {
                    Object zzd = zzd(f, zzdyq.zza(zzdzc));
                    this.zzhsh = null;
                    setResult(zzd);
                } catch (Throwable th) {
                    this.zzhsh = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e) {
                setException(e.getCause());
            } catch (RuntimeException e2) {
                setException(e2);
            } catch (Error e3) {
                setException(e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.zzhrl);
        this.zzhrl = null;
        this.zzhsh = null;
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        String str;
        zzdzc<? extends I> zzdzc = this.zzhrl;
        F f = this.zzhsh;
        String pendingToString = super.pendingToString();
        if (zzdzc != null) {
            String valueOf = String.valueOf(zzdzc);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (f != null) {
            String valueOf2 = String.valueOf(f);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(valueOf2).length());
            sb2.append(str);
            sb2.append("function=[");
            sb2.append(valueOf2);
            sb2.append("]");
            return sb2.toString();
        } else if (pendingToString == null) {
            return null;
        } else {
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf(pendingToString);
            return valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        }
    }
}
