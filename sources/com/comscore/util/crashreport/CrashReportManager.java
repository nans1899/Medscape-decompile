package com.comscore.util.crashreport;

import androidx.lifecycle.CoroutineLiveDataKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrashReportManager {
    public static final int MAX_REPORTS_TO_SEND = 1;
    public static final String REPORT_URL = "https://sb.scorecardresearch.com/p2";
    public static final int TIME_WINDOW = 5000;
    /* access modifiers changed from: private */
    public final List<CrashReport> a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    private final Object c = new Object();
    /* access modifiers changed from: private */
    public CrashReportFlusher d;
    private ExecutorService e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public CrashReportParser g;
    /* access modifiers changed from: private */
    public CrashReportDecorator h;
    private Runnable i = null;

    class a implements Runnable {
        a() {
        }

        public void run() {
            try {
                Thread.sleep(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
            } catch (InterruptedException unused) {
            }
            boolean unused2 = CrashReportManager.this.f = true;
            CrashReportManager.this.flushReportsErrors();
        }
    }

    class b implements Runnable {
        b() {
        }

        public void run() {
            ArrayList<CrashReport> arrayList;
            try {
                synchronized (CrashReportManager.this.b) {
                    arrayList = new ArrayList<>(CrashReportManager.this.a);
                }
                if (arrayList.size() != 0) {
                    LinkedList linkedList = new LinkedList();
                    for (CrashReport crashReport : arrayList) {
                        CrashReportManager.this.h.fillCrashReport(crashReport);
                        if (CrashReportManager.this.d.flush(CrashReportManager.REPORT_URL, CrashReportManager.this.g, crashReport)) {
                            linkedList.add(crashReport);
                        }
                    }
                    if (linkedList.size() > 0) {
                        synchronized (CrashReportManager.this.b) {
                            CrashReportManager.this.a.removeAll(linkedList);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public CrashReportManager(CrashReportDecorator crashReportDecorator) {
        this.h = crashReportDecorator;
        this.a = Collections.synchronizedList(new ArrayList());
        this.f = false;
        this.g = new HttpGetCrashReportParser();
        this.d = createCrashReportFlusher();
        this.e = new ThreadPoolExecutor(0, 1, 150, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    }

    public void addReport(CrashReport crashReport) {
        if (crashReport != null) {
            synchronized (this.b) {
                if (this.a.size() < 1) {
                    this.a.add(crashReport);
                }
            }
            flushReportsErrors();
        }
    }

    /* access modifiers changed from: protected */
    public CrashReportFlusher createCrashReportFlusher() {
        return new CrashReportHttpFlusher();
    }

    /* access modifiers changed from: protected */
    public void flushReportsErrors() {
        synchronized (this.c) {
            if (this.f) {
                this.e.execute(new b());
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<CrashReport> getReports() {
        return this.a;
    }

    public void start() {
        synchronized (this.c) {
            if (this.i == null) {
                a aVar = new a();
                this.i = aVar;
                this.e.execute(aVar);
            }
        }
    }
}
