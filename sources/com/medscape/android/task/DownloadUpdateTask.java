package com.medscape.android.task;

import android.os.AsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.helper.ZipUtils;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.model.Update;
import java.io.File;
import java.io.FileInputStream;

public class DownloadUpdateTask extends AsyncTask<Update, Integer, Update> {
    private int mContentLength;
    private int mError;
    private int mFailureCount;
    private OnUpdateListener mListener;
    private String mSaveDirectory;
    private Update mUpdate;

    public DownloadUpdateTask(OnUpdateListener onUpdateListener) {
        this(onUpdateListener, Constants.DIRECTORY_MAIN);
    }

    public DownloadUpdateTask(OnUpdateListener onUpdateListener, String str) {
        this.mContentLength = 0;
        this.mError = -1;
        this.mFailureCount = 0;
        this.mListener = onUpdateListener;
        this.mSaveDirectory = str;
    }

    /* access modifiers changed from: protected */
    public Update doInBackground(Update... updateArr) {
        Update update = updateArr[0];
        this.mUpdate = update;
        this.mFailureCount = 0;
        Update connect = connect(update);
        while (connect == null && this.mFailureCount <= 3) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connect = connect(this.mUpdate);
        }
        return connect;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b5, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b6, code lost:
        r13.mError = 8;
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00bc, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00bd, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c1, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c2, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c6, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c7, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ca, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00bc A[ExcHandler: UnknownHostException (r14v3 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c1 A[ExcHandler: SocketTimeoutException (r14v2 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c6 A[ExcHandler: SocketException (r14v1 'e' java.net.SocketException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.updater.model.Update connect(com.medscape.android.updater.model.Update r14) {
        /*
            r13 = this;
            java.lang.String r0 = "/"
            r1 = 0
            r2 = 8
            int r3 = r13.mFailureCount     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r3 = r3 + 1
            r13.mFailureCount = r3     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.net.URL r3 = new java.net.URL     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r4 = r14.getUrl()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r3.<init>(r4)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r4 = com.medscape.android.util.Util.TIMEOUT     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r3.setReadTimeout(r4)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.io.InputStream r4 = r3.getInputStream()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r3 = r3.getContentLength()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r13.mContentLength = r3     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r3 = r14.getUrl()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r5 = r3.lastIndexOf(r0)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r5 = r5 + 1
            java.lang.String r3 = r3.substring(r5)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.io.File r5 = new java.io.File     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r6 = r13.mSaveDirectory     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r5.<init>(r6)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            boolean r6 = r5.canWrite()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            if (r6 != 0) goto L_0x0047
            r5.mkdirs()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
        L_0x0047:
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r7 = 23552(0x5c00, float:3.3003E-41)
            r6.<init>(r4, r7)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            com.medscape.android.updater.OnUpdateListener r8 = r13.mListener     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r9 = "Downloading..."
            r8.setProgressMessage(r9)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            com.medscape.android.updater.OnUpdateListener r8 = r13.mListener     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r9 = r13.mContentLength     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r8.setMaxProgress(r9)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.io.File r8 = new java.io.File     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r9.<init>()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r5 = r5.getPath()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r9.append(r5)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r9.append(r0)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r9.append(r3)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.lang.String r0 = r9.toString()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r8.<init>(r0)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r0.<init>(r8)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            byte[] r5 = new byte[r7]     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0081:
            r12 = -1
            if (r10 == r12) goto L_0x0092
            r0.write(r5, r9, r10)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r10 = r6.read(r5, r9, r7)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r11 = r11 + r7
            com.medscape.android.updater.OnUpdateListener r12 = r13.mListener     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r12.onProgressUpdate(r11)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            goto L_0x0081
        L_0x0092:
            r0.close()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            com.medscape.android.updater.OnUpdateListener r0 = r13.mListener     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            int r5 = r13.mContentLength     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r0.onProgressUpdate(r5)     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            com.medscape.android.updater.model.Update r0 = r13.mUpdate     // Catch:{ Exception -> 0x00ae, SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc }
            boolean r0 = r0.isCompressed()     // Catch:{ Exception -> 0x00ae, SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc }
            if (r0 == 0) goto L_0x00aa
            r13.upzipFile(r3)     // Catch:{ Exception -> 0x00ae, SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc }
            r8.delete()     // Catch:{ Exception -> 0x00ae, SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc }
        L_0x00aa:
            r4.close()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            return r14
        L_0x00ae:
            r14 = move-exception
            r14.printStackTrace()     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            r13.mError = r2     // Catch:{ SocketException -> 0x00c6, SocketTimeoutException -> 0x00c1, UnknownHostException -> 0x00bc, Exception -> 0x00b5 }
            return r1
        L_0x00b5:
            r14 = move-exception
            r13.mError = r2
            r14.printStackTrace()
            goto L_0x00ca
        L_0x00bc:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x00ca
        L_0x00c1:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x00ca
        L_0x00c6:
            r14 = move-exception
            r14.printStackTrace()
        L_0x00ca:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.task.DownloadUpdateTask.connect(com.medscape.android.updater.model.Update):com.medscape.android.updater.model.Update");
    }

    public void upzipFile(String str) throws Exception {
        File file = new File(this.mSaveDirectory);
        if (!file.canWrite()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(new File(file.getPath() + "/" + str));
        if (!this.mUpdate.isPasswordProtected() || UpdateManager.isNotEncrypted) {
            ZipUtils.unzipWOPassword(fileInputStream, file.getPath(), UpdateManager.PASSWORD, this.mListener, this.mContentLength);
        } else {
            ZipUtils.unzip11(fileInputStream, file.getPath(), UpdateManager.PASSWORD, this.mListener, this.mContentLength);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Update update) {
        int i;
        OnUpdateListener onUpdateListener = this.mListener;
        if (onUpdateListener == null) {
            return;
        }
        if (update != null) {
            onUpdateListener.onUpdateComplete(1, update);
        } else if (update == null && (i = this.mError) == 8) {
            onUpdateListener.onNetworkError(i);
        } else {
            this.mListener.onNetworkError(9);
        }
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Integer... numArr) {
        this.mListener.onProgressUpdate(numArr[0].intValue());
    }
}
