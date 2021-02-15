package com.wbmd.qxcalculator.model.download;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.managers.HttpClientManager;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class PdfDownloader {
    private static final String OK_HTTP_TAG = "PdfDownloader.OK_HTTP_TAG.";
    /* access modifiers changed from: private */
    public static final String TAG = PdfDownloader.class.getSimpleName();
    private static final OkHttpClient client = HttpClientManager.getInstance().getHttpClient();
    /* access modifiers changed from: private */
    public boolean cancelled;
    /* access modifiers changed from: private */
    public final Context context;
    public PdfDownloaderListener downloaderListener;
    /* access modifiers changed from: private */
    public File file;
    /* access modifiers changed from: private */
    public boolean success;
    private String tag;
    private final String url;

    public interface PdfDownloaderListener {
        void onFinished(boolean z, File file);

        void onProgressChanged(long j, long j2);
    }

    public PdfDownloader(String str, String str2, Context context2) {
        String str3 = TAG;
        Log.d(str3, "PDF is at url: " + str);
        this.url = str;
        this.context = context2;
        FileHelper instance = FileHelper.getInstance();
        this.file = instance.getTempFileWithName(str2 + ".pdf");
        this.tag = OK_HTTP_TAG + str2;
    }

    public PdfDownloader(String str, File file2, Context context2) {
        String str2 = TAG;
        Log.d(str2, "PDF is at url: " + str);
        this.url = str;
        this.context = context2;
        this.file = file2;
        this.tag = OK_HTTP_TAG + file2.getAbsolutePath();
    }

    /* access modifiers changed from: private */
    public void deletePartialDownload() {
        FileHelper.getInstance().deleteFile(this.file);
    }

    public void startDownload() {
        String str = TAG;
        Log.d(str, "start download to file: " + this.file.getAbsolutePath());
        if (this.file == null) {
            PdfDownloaderListener pdfDownloaderListener = this.downloaderListener;
            if (pdfDownloaderListener != null) {
                pdfDownloaderListener.onFinished(false, (File) null);
                return;
            }
            return;
        }
        client.newCall(new Request.Builder().url(this.url).tag(this.tag).build()).enqueue(new Callback() {
            Handler mainHandler = new Handler(PdfDownloader.this.context.getMainLooper());

            public void onFailure(Call call, IOException iOException) {
                String access$100 = PdfDownloader.TAG;
                Log.d(access$100, "PdfDownload on Failure " + iOException.toString());
                PdfDownloader.this.deletePartialDownload();
                if (!PdfDownloader.this.cancelled) {
                    this.mainHandler.post(new Runnable() {
                        public void run() {
                            if (PdfDownloader.this.downloaderListener != null) {
                                PdfDownloader.this.downloaderListener.onFinished(false, (File) null);
                            }
                        }
                    });
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00fa, code lost:
                r6 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                com.wbmd.qxcalculator.model.download.PdfDownloader.access$502(r5.this$0, false);
                com.wbmd.qxcalculator.model.download.PdfDownloader.access$400(r5.this$0).delete();
                com.wbmd.qxcalculator.model.download.PdfDownloader.access$402(r5.this$0, (java.io.File) null);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x0110, code lost:
                r7.body().close();
                r6 = r5.mainHandler;
                r7 = new com.wbmd.qxcalculator.model.download.PdfDownloader.AnonymousClass1.AnonymousClass3(r5);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x011f, code lost:
                r7.body().close();
                r5.mainHandler.post(new com.wbmd.qxcalculator.model.download.PdfDownloader.AnonymousClass1.AnonymousClass3(r5));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x0130, code lost:
                throw r6;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x00fc */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(okhttp3.Call r6, okhttp3.Response r7) throws java.io.IOException {
                /*
                    r5 = this;
                    java.lang.String r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.TAG
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "PdfDownload onResponse, cancelled?"
                    r0.append(r1)
                    com.wbmd.qxcalculator.model.download.PdfDownloader r1 = com.wbmd.qxcalculator.model.download.PdfDownloader.this
                    boolean r1 = r1.cancelled
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    android.util.Log.d(r6, r0)
                    com.wbmd.qxcalculator.model.download.PdfDownloader r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.this
                    boolean r6 = r6.cancelled
                    if (r6 == 0) goto L_0x0038
                    okhttp3.ResponseBody r6 = r7.body()
                    r6.close()
                    android.os.Handler r6 = r5.mainHandler
                    com.wbmd.qxcalculator.model.download.PdfDownloader$1$2 r7 = new com.wbmd.qxcalculator.model.download.PdfDownloader$1$2
                    r7.<init>()
                    r6.post(r7)
                    return
                L_0x0038:
                    okhttp3.Headers r6 = r7.headers()
                    r0 = 0
                    r1 = 0
                L_0x003e:
                    int r2 = r6.size()
                    if (r1 >= r2) goto L_0x006f
                    java.lang.String r2 = com.wbmd.qxcalculator.model.download.PdfDownloader.TAG
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "Header: "
                    r3.append(r4)
                    java.lang.String r4 = r6.name(r1)
                    r3.append(r4)
                    java.lang.String r4 = ": "
                    r3.append(r4)
                    java.lang.String r4 = r6.value(r1)
                    r3.append(r4)
                    java.lang.String r3 = r3.toString()
                    android.util.Log.v(r2, r3)
                    int r1 = r1 + 1
                    goto L_0x003e
                L_0x006f:
                    java.lang.String r1 = "Content-Type"
                    java.lang.String r1 = r6.get(r1)
                    java.lang.String r2 = "Content-Disposition"
                    java.lang.String r6 = r6.get(r2)
                    java.lang.String r2 = ".pdf"
                    if (r1 == 0) goto L_0x008b
                    java.lang.String r1 = r1.toLowerCase()
                    java.lang.String r3 = "pdf"
                    boolean r1 = r1.contains(r3)
                    if (r1 != 0) goto L_0x0097
                L_0x008b:
                    if (r6 == 0) goto L_0x0131
                    java.lang.String r1 = r6.toLowerCase()
                    boolean r1 = r1.contains(r2)
                    if (r1 == 0) goto L_0x0131
                L_0x0097:
                    java.lang.String r1 = "filename"
                    if (r6 == 0) goto L_0x00dd
                    boolean r3 = r6.contains(r1)
                    if (r3 == 0) goto L_0x00dd
                    int r1 = r6.indexOf(r1)
                    int r1 = r1 + 8
                    java.lang.String r6 = r6.substring(r1)
                    boolean r1 = r6.contains(r2)
                    if (r1 == 0) goto L_0x00c9
                    int r1 = r6.indexOf(r2)
                    int r1 = r1 + 4
                    java.lang.String r6 = r6.substring(r0, r1)
                    java.lang.String r1 = "\""
                    java.lang.String r2 = ""
                    java.lang.String r6 = r6.replace(r1, r2)
                    java.lang.String r1 = "="
                    java.lang.String r6 = r6.replace(r1, r2)
                L_0x00c9:
                    com.wbmd.qxcalculator.model.download.PdfDownloader r1 = com.wbmd.qxcalculator.model.download.PdfDownloader.this
                    java.io.File r2 = new java.io.File
                    com.wbmd.qxcalculator.model.download.PdfDownloader r3 = com.wbmd.qxcalculator.model.download.PdfDownloader.this
                    java.io.File r3 = r3.file
                    java.lang.String r3 = r3.getParent()
                    r2.<init>(r3, r6)
                    java.io.File unused = r1.file = r2
                L_0x00dd:
                    com.wbmd.qxcalculator.model.download.PdfDownloader r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.this     // Catch:{ IOException -> 0x00fc }
                    com.wbmd.qxcalculator.model.download.PdfDownloader r1 = com.wbmd.qxcalculator.model.download.PdfDownloader.this     // Catch:{ IOException -> 0x00fc }
                    boolean r1 = r1.downloadPdf(r7)     // Catch:{ IOException -> 0x00fc }
                    boolean unused = r6.success = r1     // Catch:{ IOException -> 0x00fc }
                    okhttp3.ResponseBody r6 = r7.body()
                    r6.close()
                    android.os.Handler r6 = r5.mainHandler
                    com.wbmd.qxcalculator.model.download.PdfDownloader$1$3 r7 = new com.wbmd.qxcalculator.model.download.PdfDownloader$1$3
                    r7.<init>()
                L_0x00f6:
                    r6.post(r7)
                    goto L_0x0142
                L_0x00fa:
                    r6 = move-exception
                    goto L_0x011f
                L_0x00fc:
                    com.wbmd.qxcalculator.model.download.PdfDownloader r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.this     // Catch:{ all -> 0x00fa }
                    boolean unused = r6.success = r0     // Catch:{ all -> 0x00fa }
                    com.wbmd.qxcalculator.model.download.PdfDownloader r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.this     // Catch:{ all -> 0x00fa }
                    java.io.File r6 = r6.file     // Catch:{ all -> 0x00fa }
                    r6.delete()     // Catch:{ all -> 0x00fa }
                    com.wbmd.qxcalculator.model.download.PdfDownloader r6 = com.wbmd.qxcalculator.model.download.PdfDownloader.this     // Catch:{ all -> 0x00fa }
                    r0 = 0
                    java.io.File unused = r6.file = r0     // Catch:{ all -> 0x00fa }
                    okhttp3.ResponseBody r6 = r7.body()
                    r6.close()
                    android.os.Handler r6 = r5.mainHandler
                    com.wbmd.qxcalculator.model.download.PdfDownloader$1$3 r7 = new com.wbmd.qxcalculator.model.download.PdfDownloader$1$3
                    r7.<init>()
                    goto L_0x00f6
                L_0x011f:
                    okhttp3.ResponseBody r7 = r7.body()
                    r7.close()
                    android.os.Handler r7 = r5.mainHandler
                    com.wbmd.qxcalculator.model.download.PdfDownloader$1$3 r0 = new com.wbmd.qxcalculator.model.download.PdfDownloader$1$3
                    r0.<init>()
                    r7.post(r0)
                    throw r6
                L_0x0131:
                    okhttp3.ResponseBody r6 = r7.body()
                    r6.close()
                    android.os.Handler r6 = r5.mainHandler
                    com.wbmd.qxcalculator.model.download.PdfDownloader$1$4 r7 = new com.wbmd.qxcalculator.model.download.PdfDownloader$1$4
                    r7.<init>()
                    r6.post(r7)
                L_0x0142:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.download.PdfDownloader.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean downloadPdf(Response response) throws IOException {
        Log.d(TAG, "downloadPDF");
        Handler handler = new Handler(this.context.getMainLooper());
        int i = 0;
        if (this.file == null) {
            return false;
        }
        long contentLength = response.body().contentLength();
        long j = 0;
        byte[] bArr = new byte[2048];
        BufferedSink buffer = Okio.buffer(Okio.sink(this.file));
        BufferedSource source = response.body().source();
        int i2 = 0;
        while (true) {
            int read = source.read(bArr);
            if (read != -1) {
                long j2 = j + ((long) read);
                buffer.write(bArr, i, read);
                if (i2 % 25 == 0) {
                    final long j3 = j2;
                    AnonymousClass2 r8 = r0;
                    final long j4 = contentLength;
                    AnonymousClass2 r0 = new Runnable() {
                        public void run() {
                            if (PdfDownloader.this.downloaderListener != null) {
                                PdfDownloader.this.downloaderListener.onProgressChanged(j3, j4);
                            }
                        }
                    };
                    handler.post(r8);
                }
                i2++;
                j = j2;
                i = 0;
            } else {
                buffer.flush();
                buffer.close();
                source.close();
                return true;
            }
        }
    }

    public void cancel() {
        this.cancelled = true;
        for (Call next : client.dispatcher().queuedCalls()) {
            if (next.request().tag().equals(this.tag)) {
                next.cancel();
            }
        }
    }
}
