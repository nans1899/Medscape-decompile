package net.media.android.bidder.base.logging;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.an;
import mnetinternal.da;
import mnetinternal.m;
import mnetinternal.n;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.models.internal.ActivityTrackerEvent;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.Logger;
import net.media.android.bidder.base.models.internal.LoggerResponse;

final class c implements a {
    /* access modifiers changed from: private */
    public static final Object a = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean b = new AtomicBoolean(false);
    private WeakReference<Application> c;
    /* access modifiers changed from: private */
    public m<Logger> d;
    /* access modifiers changed from: private */
    public ScheduledFuture e;
    private long f = System.currentTimeMillis();
    private File g;

    c(final Application application) {
        this.c = new WeakReference<>(application);
        aa.a().submit(new ac() {
            public void a() {
                c.this.a(application);
            }
        });
        b();
        c();
    }

    /* access modifiers changed from: private */
    public void a(Application application) {
        File file = new File(application.getFilesDir().getAbsolutePath() + "logger_cache");
        this.g = file;
        try {
            a(file);
        } catch (IOException unused) {
            File file2 = new File(application.getFilesDir().getAbsolutePath() + "logger_cache");
            this.g = file2;
            try {
                a(file2);
            } catch (IOException unused2) {
            }
        }
    }

    private void a(File file) {
        if (this.d == null) {
            m<Logger> mVar = new m<>(file, new a());
            this.d = mVar;
            mVar.a((n.a<Logger>) new n.a<Logger>() {
                public void a(n<Logger> nVar) {
                }

                public void a(n<Logger> nVar, Logger logger) {
                    if (!c.this.b.get()) {
                        c.this.a(false);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        File file;
        if (!da.a((WeakReference) this.c) && (file = this.g) != null) {
            if ((file.length() >= CoroutineLiveDataKt.DEFAULT_TIMEOUT || z || System.currentTimeMillis() - this.f >= 30000) && !this.b.getAndSet(true)) {
                this.f = System.currentTimeMillis();
                Logger.debug("##RemoteLogger##", "syncing with server");
                aa.a().submit(new ac() {
                    public void a() {
                        final ArrayList arrayList = new ArrayList();
                        synchronized (c.a) {
                            int i = 0;
                            while (c.this.d.b() != null && i < 30) {
                                arrayList.add(c.this.d.b());
                                c.this.d.c();
                                i++;
                            }
                        }
                        if (arrayList.isEmpty()) {
                            Logger.debug("##RemoteLogger##", "empty pulse log");
                            c.this.b.set(false);
                            return;
                        }
                        af.b(new ai.a(an.d()).a(net.media.android.bidder.base.gson.a.b().b((Object) arrayList)).a(), new ak<LoggerResponse>() {
                            public Class<LoggerResponse> a() {
                                return LoggerResponse.class;
                            }

                            public void a(final LoggerResponse loggerResponse) {
                                aa.a().submit(new ac() {
                                    public void a() {
                                        if (loggerResponse.isSuccess()) {
                                            Logger.debug("##RemoteLogger##", "logger cache sync completed");
                                            arrayList.clear();
                                        }
                                        c.this.b.set(false);
                                    }
                                });
                            }

                            public void a(final Throwable th) {
                                aa.a().submit(new ac() {
                                    public void a() {
                                        Log.e("##RemoteLogger##", "error while logger cache sync", th);
                                        c.this.b.set(false);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
    }

    public void a(final Logger logger) {
        if (logger != null) {
            aa.a().submit(new ac() {
                public void a() {
                    synchronized (c.a) {
                        c.this.d.a(logger);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.e = aa.a().scheduleAtFixedRate(new ac() {
            public void a() {
                c.this.a(false);
            }
        }, 30000, 30000, TimeUnit.MILLISECONDS);
    }

    private void c() {
        AnonymousClass6 r0 = new z<ActivityTrackerEvent>() {
            /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
            /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(net.media.android.bidder.base.models.internal.ActivityTrackerEvent r5) {
                /*
                    r4 = this;
                    java.lang.String r5 = r5.getEventType()
                    int r0 = r5.hashCode()
                    r1 = 975015852(0x3a1d8fac, float:6.0104835E-4)
                    r2 = 0
                    r3 = 1
                    if (r0 == r1) goto L_0x001f
                    r1 = 1601408133(0x5f738c85, float:1.7549548E19)
                    if (r0 == r1) goto L_0x0015
                    goto L_0x0029
                L_0x0015:
                    java.lang.String r0 = "event_app_in_background"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0029
                    r5 = 0
                    goto L_0x002a
                L_0x001f:
                    java.lang.String r0 = "event_activity_resumed"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0029
                    r5 = 1
                    goto L_0x002a
                L_0x0029:
                    r5 = -1
                L_0x002a:
                    java.lang.String r0 = "##RemoteLogger##"
                    if (r5 == 0) goto L_0x0044
                    if (r5 == r3) goto L_0x0031
                    goto L_0x0060
                L_0x0031:
                    net.media.android.bidder.base.logging.c r5 = net.media.android.bidder.base.logging.c.this
                    java.util.concurrent.ScheduledFuture r5 = r5.e
                    if (r5 != 0) goto L_0x0060
                    java.lang.String r5 = "starting sync task"
                    net.media.android.bidder.base.logging.Logger.debug(r0, r5)
                    net.media.android.bidder.base.logging.c r5 = net.media.android.bidder.base.logging.c.this
                    r5.b()
                    goto L_0x0060
                L_0x0044:
                    net.media.android.bidder.base.logging.c r5 = net.media.android.bidder.base.logging.c.this
                    java.util.concurrent.ScheduledFuture r5 = r5.e
                    if (r5 == 0) goto L_0x0060
                    java.lang.String r5 = "stopping sync task"
                    net.media.android.bidder.base.logging.Logger.debug(r0, r5)
                    net.media.android.bidder.base.logging.c r5 = net.media.android.bidder.base.logging.c.this
                    java.util.concurrent.ScheduledFuture r5 = r5.e
                    r5.cancel(r2)
                    net.media.android.bidder.base.logging.c r5 = net.media.android.bidder.base.logging.c.this
                    r0 = 0
                    java.util.concurrent.ScheduledFuture unused = r5.e = r0
                L_0x0060:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.logging.c.AnonymousClass6.a(net.media.android.bidder.base.models.internal.ActivityTrackerEvent):void");
            }
        };
        x.a().a(AdTrackerEvent.EVENT_APP_IN_BACKGROUND, r0);
        x.a().a(AdTrackerEvent.EVENT_ACTIVITY_RESUMED, r0);
    }

    private static class a implements m.a<Logger> {
        a() {
        }

        /* renamed from: b */
        public Logger a(byte[] bArr) {
            return (Logger) net.media.android.bidder.base.gson.a.b().a((Reader) new InputStreamReader(new ByteArrayInputStream(bArr), "UTF-8"), Logger.class);
        }

        public void a(Logger logger, OutputStream outputStream) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            net.media.android.bidder.base.gson.a.b().a((Object) logger, (Appendable) outputStreamWriter);
            outputStreamWriter.close();
        }
    }
}
