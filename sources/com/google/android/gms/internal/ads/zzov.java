package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.ads.zzoy;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzov<T extends zzoy> extends Handler implements Runnable {
    private volatile boolean zzagi;
    private final T zzbjj;
    private final zzow<T> zzbjk;
    public final int zzbjl;
    private final long zzbjm;
    private IOException zzbjn;
    private int zzbjo;
    private volatile Thread zzbjp;
    private final /* synthetic */ zzot zzbjq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzov(zzot zzot, Looper looper, T t, zzow<T> zzow, int i, long j) {
        super(looper);
        this.zzbjq = zzot;
        this.zzbjj = t;
        this.zzbjk = zzow;
        this.zzbjl = i;
        this.zzbjm = j;
    }

    public final void zzbj(int i) throws IOException {
        IOException iOException = this.zzbjn;
        if (iOException != null && this.zzbjo > i) {
            throw iOException;
        }
    }

    public final void zzek(long j) {
        zzoz.checkState(this.zzbjq.zzbjf == null);
        zzov unused = this.zzbjq.zzbjf = this;
        if (j > 0) {
            sendEmptyMessageDelayed(0, j);
        } else {
            execute();
        }
    }

    public final void zzl(boolean z) {
        this.zzagi = z;
        this.zzbjn = null;
        if (hasMessages(0)) {
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
            }
        } else {
            this.zzbjj.cancelLoad();
            if (this.zzbjp != null) {
                this.zzbjp.interrupt();
            }
        }
        if (z) {
            finish();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbjk.zza(this.zzbjj, elapsedRealtime, elapsedRealtime - this.zzbjm, true);
        }
    }

    public final void run() {
        try {
            this.zzbjp = Thread.currentThread();
            if (!this.zzbjj.zzhx()) {
                String valueOf = String.valueOf(this.zzbjj.getClass().getSimpleName());
                zzpn.beginSection(valueOf.length() != 0 ? "load:".concat(valueOf) : new String("load:"));
                this.zzbjj.zzhy();
                zzpn.endSection();
            }
            if (!this.zzagi) {
                sendEmptyMessage(2);
            }
        } catch (IOException e) {
            if (!this.zzagi) {
                obtainMessage(3, e).sendToTarget();
            }
        } catch (InterruptedException unused) {
            zzoz.checkState(this.zzbjj.zzhx());
            if (!this.zzagi) {
                sendEmptyMessage(2);
            }
        } catch (Exception e2) {
            Log.e("LoadTask", "Unexpected exception loading stream", e2);
            if (!this.zzagi) {
                obtainMessage(3, new zzox(e2)).sendToTarget();
            }
        } catch (OutOfMemoryError e3) {
            Log.e("LoadTask", "OutOfMemory error loading stream", e3);
            if (!this.zzagi) {
                obtainMessage(3, new zzox(e3)).sendToTarget();
            }
        } catch (Error e4) {
            Log.e("LoadTask", "Unexpected error loading stream", e4);
            if (!this.zzagi) {
                obtainMessage(4, e4).sendToTarget();
            }
            throw e4;
        } catch (Throwable th) {
            zzpn.endSection();
            throw th;
        }
    }

    public final void handleMessage(Message message) {
        int i;
        if (!this.zzagi) {
            if (message.what == 0) {
                execute();
            } else if (message.what != 4) {
                finish();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j = elapsedRealtime - this.zzbjm;
                if (this.zzbjj.zzhx()) {
                    this.zzbjk.zza(this.zzbjj, elapsedRealtime, j, false);
                    return;
                }
                int i2 = message.what;
                if (i2 == 1) {
                    this.zzbjk.zza(this.zzbjj, elapsedRealtime, j, false);
                } else if (i2 == 2) {
                    this.zzbjk.zza(this.zzbjj, elapsedRealtime, j);
                } else if (i2 == 3) {
                    IOException iOException = (IOException) message.obj;
                    this.zzbjn = iOException;
                    int zza = this.zzbjk.zza(this.zzbjj, elapsedRealtime, j, iOException);
                    if (zza == 3) {
                        IOException unused = this.zzbjq.zzbjg = this.zzbjn;
                    } else if (zza != 2) {
                        if (zza == 1) {
                            i = 1;
                        } else {
                            i = this.zzbjo + 1;
                        }
                        this.zzbjo = i;
                        zzek((long) Math.min((i - 1) * 1000, 5000));
                    }
                }
            } else {
                throw ((Error) message.obj);
            }
        }
    }

    private final void execute() {
        this.zzbjn = null;
        this.zzbjq.zzbje.execute(this.zzbjq.zzbjf);
    }

    private final void finish() {
        zzov unused = this.zzbjq.zzbjf = null;
    }
}
