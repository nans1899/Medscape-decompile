package org.apache.commons.io.input;

import java.io.File;
import java.nio.charset.Charset;

public class Tailer implements Runnable {
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final String RAF_MODE = "r";
    private final Charset charset;
    private final long delayMillis;
    private final boolean end;
    private final File file;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;

    public Tailer(File file2, TailerListener tailerListener) {
        this(file2, tailerListener, 1000);
    }

    public Tailer(File file2, TailerListener tailerListener, long j) {
        this(file2, tailerListener, j, false);
    }

    public Tailer(File file2, TailerListener tailerListener, long j, boolean z) {
        this(file2, tailerListener, j, z, 8192);
    }

    public Tailer(File file2, TailerListener tailerListener, long j, boolean z, boolean z2) {
        this(file2, tailerListener, j, z, z2, 8192);
    }

    public Tailer(File file2, TailerListener tailerListener, long j, boolean z, int i) {
        this(file2, tailerListener, j, z, false, i);
    }

    public Tailer(File file2, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this(file2, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public Tailer(File file2, Charset charset2, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this.run = true;
        this.file = file2;
        this.delayMillis = j;
        this.end = z;
        this.inbuf = new byte[i];
        this.listener = tailerListener;
        tailerListener.init(this);
        this.reOpen = z2;
        this.charset = charset2;
    }

    public static Tailer create(File file2, TailerListener tailerListener, long j, boolean z, int i) {
        return create(file2, tailerListener, j, z, false, i);
    }

    public static Tailer create(File file2, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        return create(file2, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public static Tailer create(File file2, Charset charset2, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        Tailer tailer = new Tailer(file2, charset2, tailerListener, j, z, z2, i);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file2, TailerListener tailerListener, long j, boolean z) {
        return create(file2, tailerListener, j, z, 8192);
    }

    public static Tailer create(File file2, TailerListener tailerListener, long j, boolean z, boolean z2) {
        return create(file2, tailerListener, j, z, z2, 8192);
    }

    public static Tailer create(File file2, TailerListener tailerListener, long j) {
        return create(file2, tailerListener, j, false);
    }

    public static Tailer create(File file2, TailerListener tailerListener) {
        return create(file2, tailerListener, 1000, false);
    }

    public File getFile() {
        return this.file;
    }

    /* access modifiers changed from: protected */
    public boolean getRun() {
        return this.run;
    }

    public long getDelay() {
        return this.delayMillis;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r14.listener.fileNotFound();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00f1 A[SYNTHETIC, Splitter:B:85:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0117 A[SYNTHETIC, Splitter:B:99:0x0117] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:91:0x0100=Splitter:B:91:0x0100, B:82:0x00ea=Splitter:B:82:0x00ea} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            r0 = 0
            r2 = 0
            r3 = r0
            r5 = r3
        L_0x0005:
            boolean r7 = r14.getRun()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.lang.String r8 = "r"
            if (r7 == 0) goto L_0x003c
            if (r2 != 0) goto L_0x003c
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x0018 }
            java.io.File r9 = r14.file     // Catch:{ FileNotFoundException -> 0x0018 }
            r7.<init>(r9, r8)     // Catch:{ FileNotFoundException -> 0x0018 }
            r2 = r7
            goto L_0x001d
        L_0x0018:
            org.apache.commons.io.input.TailerListener r7 = r14.listener     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r7.fileNotFound()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
        L_0x001d:
            if (r2 != 0) goto L_0x0025
            long r7 = r14.delayMillis     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            goto L_0x0005
        L_0x0025:
            boolean r3 = r14.end     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            if (r3 == 0) goto L_0x0031
            java.io.File r3 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r3 = r3.length()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r5 = r3
            goto L_0x0032
        L_0x0031:
            r5 = r0
        L_0x0032:
            java.io.File r3 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r3 = r3.lastModified()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r2.seek(r5)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            goto L_0x0005
        L_0x003c:
            boolean r7 = r14.getRun()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            if (r7 == 0) goto L_0x00df
            java.io.File r7 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            boolean r7 = org.apache.commons.io.FileUtils.isFileNewer((java.io.File) r7, (long) r3)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.io.File r9 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r9 = r9.length()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 >= 0) goto L_0x009c
            org.apache.commons.io.input.TailerListener r7 = r14.listener     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r7.fileRotated()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ all -> 0x0075 }
            java.io.File r9 = r14.file     // Catch:{ all -> 0x0075 }
            r7.<init>(r9, r8)     // Catch:{ all -> 0x0075 }
            r14.readLines(r2)     // Catch:{ IOException -> 0x0064 }
            goto L_0x006a
        L_0x0062:
            r9 = move-exception
            goto L_0x0077
        L_0x0064:
            r9 = move-exception
            org.apache.commons.io.input.TailerListener r10 = r14.listener     // Catch:{ all -> 0x0062 }
            r10.handle((java.lang.Exception) r9)     // Catch:{ all -> 0x0062 }
        L_0x006a:
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch:{ FileNotFoundException -> 0x0070 }
            goto L_0x0072
        L_0x0070:
            r5 = r0
            goto L_0x0090
        L_0x0072:
            r5 = r0
        L_0x0073:
            r2 = r7
            goto L_0x003c
        L_0x0075:
            r9 = move-exception
            r7 = r2
        L_0x0077:
            throw r9     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r10 = move-exception
            if (r2 == 0) goto L_0x0083
            r2.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r2 = move-exception
            r9.addSuppressed(r2)     // Catch:{ FileNotFoundException -> 0x0090 }
        L_0x0083:
            throw r10     // Catch:{ FileNotFoundException -> 0x0090 }
        L_0x0084:
            r0 = move-exception
            r2 = r7
            goto L_0x0115
        L_0x0088:
            r0 = move-exception
            r2 = r7
            goto L_0x00ea
        L_0x008c:
            r0 = move-exception
            r2 = r7
            goto L_0x0100
        L_0x0090:
            r2 = r7
            org.apache.commons.io.input.TailerListener r7 = r14.listener     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r7.fileNotFound()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r9 = r14.delayMillis     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.lang.Thread.sleep(r9)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            goto L_0x003c
        L_0x009c:
            if (r11 <= 0) goto L_0x00ac
            long r3 = r14.readLines(r2)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.io.File r5 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r5 = r5.lastModified()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
        L_0x00a8:
            r12 = r3
            r3 = r5
            r5 = r12
            goto L_0x00bc
        L_0x00ac:
            if (r7 == 0) goto L_0x00bc
            r2.seek(r0)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r3 = r14.readLines(r2)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.io.File r5 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            long r5 = r5.lastModified()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            goto L_0x00a8
        L_0x00bc:
            boolean r7 = r14.reOpen     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            if (r7 == 0) goto L_0x00c5
            if (r2 == 0) goto L_0x00c5
            r2.close()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
        L_0x00c5:
            long r9 = r14.delayMillis     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.lang.Thread.sleep(r9)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            boolean r7 = r14.getRun()     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            if (r7 == 0) goto L_0x003c
            boolean r7 = r14.reOpen     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            if (r7 == 0) goto L_0x003c
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            java.io.File r9 = r14.file     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r7.<init>(r9, r8)     // Catch:{ InterruptedException -> 0x00ff, Exception -> 0x00e9 }
            r7.seek(r5)     // Catch:{ InterruptedException -> 0x008c, Exception -> 0x0088, all -> 0x0084 }
            goto L_0x0073
        L_0x00df:
            if (r2 == 0) goto L_0x00fb
            r2.close()     // Catch:{ IOException -> 0x00e5 }
            goto L_0x00fb
        L_0x00e5:
            r0 = move-exception
            goto L_0x00f6
        L_0x00e7:
            r0 = move-exception
            goto L_0x0115
        L_0x00e9:
            r0 = move-exception
        L_0x00ea:
            org.apache.commons.io.input.TailerListener r1 = r14.listener     // Catch:{ all -> 0x00e7 }
            r1.handle((java.lang.Exception) r0)     // Catch:{ all -> 0x00e7 }
            if (r2 == 0) goto L_0x00fb
            r2.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x00fb
        L_0x00f5:
            r0 = move-exception
        L_0x00f6:
            org.apache.commons.io.input.TailerListener r1 = r14.listener
            r1.handle((java.lang.Exception) r0)
        L_0x00fb:
            r14.stop()
            goto L_0x0114
        L_0x00ff:
            r0 = move-exception
        L_0x0100:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00e7 }
            r1.interrupt()     // Catch:{ all -> 0x00e7 }
            org.apache.commons.io.input.TailerListener r1 = r14.listener     // Catch:{ all -> 0x00e7 }
            r1.handle((java.lang.Exception) r0)     // Catch:{ all -> 0x00e7 }
            if (r2 == 0) goto L_0x00fb
            r2.close()     // Catch:{ IOException -> 0x0112 }
            goto L_0x00fb
        L_0x0112:
            r0 = move-exception
            goto L_0x00f6
        L_0x0114:
            return
        L_0x0115:
            if (r2 == 0) goto L_0x0121
            r2.close()     // Catch:{ IOException -> 0x011b }
            goto L_0x0121
        L_0x011b:
            r1 = move-exception
            org.apache.commons.io.input.TailerListener r2 = r14.listener
            r2.handle((java.lang.Exception) r1)
        L_0x0121:
            r14.stop()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.Tailer.run():void");
    }

    public void stop() {
        this.run = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0087, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        r14.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0090, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long readLines(java.io.RandomAccessFile r14) throws java.io.IOException {
        /*
            r13 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 64
            r0.<init>(r1)
            long r1 = r14.getFilePointer()     // Catch:{ all -> 0x0085 }
            r3 = 0
            r4 = r1
            r6 = 0
        L_0x000e:
            boolean r7 = r13.getRun()     // Catch:{ all -> 0x0085 }
            if (r7 == 0) goto L_0x0071
            byte[] r7 = r13.inbuf     // Catch:{ all -> 0x0085 }
            int r7 = r14.read(r7)     // Catch:{ all -> 0x0085 }
            r8 = -1
            if (r7 == r8) goto L_0x0071
            r8 = 0
        L_0x001e:
            if (r8 >= r7) goto L_0x006c
            byte[] r9 = r13.inbuf     // Catch:{ all -> 0x0085 }
            byte r9 = r9[r8]     // Catch:{ all -> 0x0085 }
            r10 = 10
            r11 = 1
            if (r9 == r10) goto L_0x0052
            r10 = 13
            if (r9 == r10) goto L_0x004b
            if (r6 == 0) goto L_0x0047
            org.apache.commons.io.input.TailerListener r1 = r13.listener     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0085 }
            byte[] r6 = r0.toByteArray()     // Catch:{ all -> 0x0085 }
            java.nio.charset.Charset r10 = r13.charset     // Catch:{ all -> 0x0085 }
            r2.<init>(r6, r10)     // Catch:{ all -> 0x0085 }
            r1.handle((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            r0.reset()     // Catch:{ all -> 0x0085 }
            long r1 = (long) r8     // Catch:{ all -> 0x0085 }
            long r1 = r1 + r4
            long r1 = r1 + r11
            r6 = 0
        L_0x0047:
            r0.write(r9)     // Catch:{ all -> 0x0085 }
            goto L_0x0069
        L_0x004b:
            if (r6 == 0) goto L_0x0050
            r0.write(r10)     // Catch:{ all -> 0x0085 }
        L_0x0050:
            r6 = 1
            goto L_0x0069
        L_0x0052:
            org.apache.commons.io.input.TailerListener r1 = r13.listener     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0085 }
            byte[] r6 = r0.toByteArray()     // Catch:{ all -> 0x0085 }
            java.nio.charset.Charset r9 = r13.charset     // Catch:{ all -> 0x0085 }
            r2.<init>(r6, r9)     // Catch:{ all -> 0x0085 }
            r1.handle((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            r0.reset()     // Catch:{ all -> 0x0085 }
            long r1 = (long) r8     // Catch:{ all -> 0x0085 }
            long r1 = r1 + r4
            long r1 = r1 + r11
            r6 = 0
        L_0x0069:
            int r8 = r8 + 1
            goto L_0x001e
        L_0x006c:
            long r4 = r14.getFilePointer()     // Catch:{ all -> 0x0085 }
            goto L_0x000e
        L_0x0071:
            r14.seek(r1)     // Catch:{ all -> 0x0085 }
            org.apache.commons.io.input.TailerListener r14 = r13.listener     // Catch:{ all -> 0x0085 }
            boolean r14 = r14 instanceof org.apache.commons.io.input.TailerListenerAdapter     // Catch:{ all -> 0x0085 }
            if (r14 == 0) goto L_0x0081
            org.apache.commons.io.input.TailerListener r14 = r13.listener     // Catch:{ all -> 0x0085 }
            org.apache.commons.io.input.TailerListenerAdapter r14 = (org.apache.commons.io.input.TailerListenerAdapter) r14     // Catch:{ all -> 0x0085 }
            r14.endOfFileReached()     // Catch:{ all -> 0x0085 }
        L_0x0081:
            r0.close()
            return r1
        L_0x0085:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            r14.addSuppressed(r0)
        L_0x0090:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.Tailer.readLines(java.io.RandomAccessFile):long");
    }
}
