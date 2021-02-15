package io.branch.referral.network;

import android.content.Context;
import io.branch.referral.PrefHelper;
import io.branch.referral.network.BranchRemoteInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class BranchRemoteInterfaceUrlConnection extends BranchRemoteInterface {
    private static final int DEFAULT_TIMEOUT = 3000;
    PrefHelper prefHelper;

    BranchRemoteInterfaceUrlConnection(Context context) {
        this.prefHelper = PrefHelper.getInstance(context);
    }

    public BranchRemoteInterface.BranchResponse doRestfulGet(String str) throws BranchRemoteInterface.BranchRemoteException {
        return doRestfulGet(str, 0);
    }

    public BranchRemoteInterface.BranchResponse doRestfulPost(String str, JSONObject jSONObject) throws BranchRemoteInterface.BranchRemoteException {
        return doRestfulPost(str, jSONObject, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cc, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0109, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:54:0x00d0, B:60:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fe A[SYNTHETIC, Splitter:B:60:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0119 A[SYNTHETIC, Splitter:B:69:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x014a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulGet(java.lang.String r8, int r9) throws io.branch.referral.network.BranchRemoteInterface.BranchRemoteException {
        /*
            r7 = this;
            java.lang.String r0 = "?"
            r1 = -113(0xffffffffffffff8f, float:NaN)
            r2 = 0
            io.branch.referral.PrefHelper r3 = r7.prefHelper     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            int r3 = r3.getTimeout()     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            if (r3 > 0) goto L_0x000f
            r3 = 3000(0xbb8, float:4.204E-42)
        L_0x000f:
            boolean r4 = r8.contains(r0)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            if (r4 == 0) goto L_0x0017
            java.lang.String r0 = "&"
        L_0x0017:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r4.<init>()     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r4.append(r8)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r4.append(r0)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            java.lang.String r0 = "retryNumber"
            r4.append(r0)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            java.lang.String r0 = "="
            r4.append(r0)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r4.append(r9)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            java.lang.String r0 = r4.toString()     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            java.net.URL r4 = new java.net.URL     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r4.<init>(r0)     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            java.net.URLConnection r0 = r4.openConnection()     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ SocketException -> 0x0121, SocketTimeoutException -> 0x00f6, IOException -> 0x00cf }
            r0.setConnectTimeout(r3)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            r0.setReadTimeout(r3)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            int r3 = r0.getResponseCode()     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            r4 = 500(0x1f4, float:7.0E-43)
            if (r3 < r4) goto L_0x006f
            io.branch.referral.PrefHelper r4 = r7.prefHelper     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            int r4 = r4.getRetryCount()     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            if (r9 >= r4) goto L_0x006f
            io.branch.referral.PrefHelper r2 = r7.prefHelper     // Catch:{ InterruptedException -> 0x005f }
            int r2 = r2.getRetryInterval()     // Catch:{ InterruptedException -> 0x005f }
            long r2 = (long) r2     // Catch:{ InterruptedException -> 0x005f }
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
        L_0x0063:
            int r9 = r9 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r8 = r7.doRestfulGet(r8, r9)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            if (r0 == 0) goto L_0x006e
            r0.disconnect()
        L_0x006e:
            return r8
        L_0x006f:
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == r4) goto L_0x008c
            java.io.InputStream r4 = r0.getErrorStream()     // Catch:{ FileNotFoundException -> 0x009f }
            if (r4 == 0) goto L_0x008c
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r4 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x009f }
            java.io.InputStream r5 = r0.getErrorStream()     // Catch:{ FileNotFoundException -> 0x009f }
            java.lang.String r5 = r7.getResponseString(r5)     // Catch:{ FileNotFoundException -> 0x009f }
            r4.<init>(r5, r3)     // Catch:{ FileNotFoundException -> 0x009f }
            if (r0 == 0) goto L_0x008b
            r0.disconnect()
        L_0x008b:
            return r4
        L_0x008c:
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r4 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x009f }
            java.io.InputStream r5 = r0.getInputStream()     // Catch:{ FileNotFoundException -> 0x009f }
            java.lang.String r5 = r7.getResponseString(r5)     // Catch:{ FileNotFoundException -> 0x009f }
            r4.<init>(r5, r3)     // Catch:{ FileNotFoundException -> 0x009f }
            if (r0 == 0) goto L_0x009e
            r0.disconnect()
        L_0x009e:
            return r4
        L_0x009f:
            java.lang.String r4 = "BranchSDK"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            r5.<init>()     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            java.lang.String r6 = "A resource conflict occurred with this request "
            r5.append(r6)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            r5.append(r8)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            java.lang.String r5 = r5.toString()     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            io.branch.referral.PrefHelper.Debug(r4, r5)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r4 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            r4.<init>(r2, r3)     // Catch:{ SocketException -> 0x00c9, SocketTimeoutException -> 0x00c7, IOException -> 0x00c4, all -> 0x00c0 }
            if (r0 == 0) goto L_0x00bf
            r0.disconnect()
        L_0x00bf:
            return r4
        L_0x00c0:
            r8 = move-exception
            r2 = r0
            goto L_0x0148
        L_0x00c4:
            r8 = move-exception
            r2 = r0
            goto L_0x00d0
        L_0x00c7:
            r2 = r0
            goto L_0x00f6
        L_0x00c9:
            r8 = move-exception
            r2 = r0
            goto L_0x0122
        L_0x00cc:
            r8 = move-exception
            goto L_0x0148
        L_0x00cf:
            r8 = move-exception
        L_0x00d0:
            java.lang.Class r9 = r7.getClass()     // Catch:{ all -> 0x00cc }
            java.lang.String r9 = r9.getSimpleName()     // Catch:{ all -> 0x00cc }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            r0.<init>()     // Catch:{ all -> 0x00cc }
            java.lang.String r3 = "Branch connect exception: "
            r0.append(r3)     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00cc }
            r0.append(r8)     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x00cc }
            io.branch.referral.PrefHelper.Debug(r9, r8)     // Catch:{ all -> 0x00cc }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cc }
            r8.<init>(r1)     // Catch:{ all -> 0x00cc }
            throw r8     // Catch:{ all -> 0x00cc }
        L_0x00f6:
            io.branch.referral.PrefHelper r0 = r7.prefHelper     // Catch:{ all -> 0x00cc }
            int r0 = r0.getRetryCount()     // Catch:{ all -> 0x00cc }
            if (r9 >= r0) goto L_0x0119
            io.branch.referral.PrefHelper r0 = r7.prefHelper     // Catch:{ InterruptedException -> 0x0109 }
            int r0 = r0.getRetryInterval()     // Catch:{ InterruptedException -> 0x0109 }
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0109 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0109 }
            goto L_0x010d
        L_0x0109:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00cc }
        L_0x010d:
            int r9 = r9 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r8 = r7.doRestfulGet(r8, r9)     // Catch:{ all -> 0x00cc }
            if (r2 == 0) goto L_0x0118
            r2.disconnect()
        L_0x0118:
            return r8
        L_0x0119:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cc }
            r9 = -111(0xffffffffffffff91, float:NaN)
            r8.<init>(r9)     // Catch:{ all -> 0x00cc }
            throw r8     // Catch:{ all -> 0x00cc }
        L_0x0121:
            r8 = move-exception
        L_0x0122:
            java.lang.Class r9 = r7.getClass()     // Catch:{ all -> 0x00cc }
            java.lang.String r9 = r9.getSimpleName()     // Catch:{ all -> 0x00cc }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            r0.<init>()     // Catch:{ all -> 0x00cc }
            java.lang.String r3 = "Http connect exception: "
            r0.append(r3)     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00cc }
            r0.append(r8)     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x00cc }
            io.branch.referral.PrefHelper.Debug(r9, r8)     // Catch:{ all -> 0x00cc }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cc }
            r8.<init>(r1)     // Catch:{ all -> 0x00cc }
            throw r8     // Catch:{ all -> 0x00cc }
        L_0x0148:
            if (r2 == 0) goto L_0x014d
            r2.disconnect()
        L_0x014d:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulGet(java.lang.String, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:46|45|47|48|(2:50|51)|(1:56)|57) */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x017d, code lost:
        throw new io.branch.referral.network.BranchRemoteInterface.BranchRemoteException(io.branch.referral.BranchError.ERR_BRANCH_REQ_TIMED_OUT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a8, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cd, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00e2, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e4, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e5, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e7, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00e9, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0124, code lost:
        r6.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        java.lang.Thread.sleep((long) r9.prefHelper.getRetryInterval());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0167, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0172, code lost:
        r5.disconnect();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:86:0x012c, B:92:0x015c] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00ae */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0176 A[SYNTHETIC, Splitter:B:101:0x0176] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a A[SYNTHETIC, Splitter:B:34:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c9 A[SYNTHETIC, Splitter:B:50:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d9 A[SYNTHETIC, Splitter:B:59:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e2 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:10:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:10:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x015c A[SYNTHETIC, Splitter:B:92:0x015c] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulPost(java.lang.String r10, org.json.JSONObject r11, int r12) throws io.branch.referral.network.BranchRemoteInterface.BranchRemoteException {
        /*
            r9 = this;
            java.lang.String r0 = "BranchSDK"
            java.lang.String r1 = "application/json"
            io.branch.referral.PrefHelper r2 = r9.prefHelper
            int r2 = r2.getTimeout()
            if (r2 > 0) goto L_0x000e
            r2 = 3000(0xbb8, float:4.204E-42)
        L_0x000e:
            java.lang.String r3 = "retryNumber"
            r11.put(r3, r12)     // Catch:{ JSONException -> 0x0013 }
        L_0x0013:
            r3 = 500(0x1f4, float:7.0E-43)
            r4 = 1
            r5 = 0
            java.net.URL r6 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x0154, IOException -> 0x012b, Exception -> 0x00ec }
            r6.<init>(r10)     // Catch:{ SocketTimeoutException -> 0x0154, IOException -> 0x012b, Exception -> 0x00ec }
            java.net.URLConnection r6 = r6.openConnection()     // Catch:{ SocketTimeoutException -> 0x0154, IOException -> 0x012b, Exception -> 0x00ec }
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ SocketTimeoutException -> 0x0154, IOException -> 0x012b, Exception -> 0x00ec }
            r6.setConnectTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r6.setReadTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r6.setDoInput(r4)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r6.setDoOutput(r4)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.lang.String r2 = "Content-Type"
            r6.setRequestProperty(r2, r1)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.lang.String r2 = "Accept"
            r6.setRequestProperty(r2, r1)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.lang.String r1 = "POST"
            r6.setRequestMethod(r1)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.io.OutputStream r2 = r6.getOutputStream()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r1.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            java.lang.String r2 = r11.toString()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r1.write(r2)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r1.flush()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            r1.close()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            int r1 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            if (r1 < r3) goto L_0x007c
            io.branch.referral.PrefHelper r2 = r9.prefHelper     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            int r2 = r2.getRetryCount()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            if (r12 >= r2) goto L_0x007c
            io.branch.referral.PrefHelper r1 = r9.prefHelper     // Catch:{ InterruptedException -> 0x006c }
            int r1 = r1.getRetryInterval()     // Catch:{ InterruptedException -> 0x006c }
            long r1 = (long) r1     // Catch:{ InterruptedException -> 0x006c }
            java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
        L_0x0070:
            int r12 = r12 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r10 = r9.doRestfulPost(r10, r11, r12)     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
            if (r6 == 0) goto L_0x007b
            r6.disconnect()
        L_0x007b:
            return r10
        L_0x007c:
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L_0x008b
            java.io.InputStream r2 = r6.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00ad, all -> 0x00aa }
            if (r2 == 0) goto L_0x008b
            java.io.InputStream r2 = r6.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00ad, all -> 0x00aa }
            goto L_0x008f
        L_0x008b:
            java.io.InputStream r2 = r6.getInputStream()     // Catch:{ FileNotFoundException -> 0x00ad, all -> 0x00aa }
        L_0x008f:
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r7 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x00ae }
            java.lang.String r8 = r9.getResponseString(r2)     // Catch:{ FileNotFoundException -> 0x00ae }
            r7.<init>(r8, r1)     // Catch:{ FileNotFoundException -> 0x00ae }
            if (r2 == 0) goto L_0x00a2
            r2.close()     // Catch:{ IOException -> 0x009e, SocketTimeoutException -> 0x00e7, Exception -> 0x00e2 }
            goto L_0x00a2
        L_0x009e:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
        L_0x00a2:
            if (r6 == 0) goto L_0x00a7
            r6.disconnect()
        L_0x00a7:
            return r7
        L_0x00a8:
            r1 = move-exception
            goto L_0x00d7
        L_0x00aa:
            r1 = move-exception
            r2 = r5
            goto L_0x00d7
        L_0x00ad:
            r2 = r5
        L_0x00ae:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            r7.<init>()     // Catch:{ all -> 0x00a8 }
            java.lang.String r8 = "A resource conflict occurred with this request "
            r7.append(r8)     // Catch:{ all -> 0x00a8 }
            r7.append(r10)     // Catch:{ all -> 0x00a8 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00a8 }
            io.branch.referral.PrefHelper.Debug(r0, r7)     // Catch:{ all -> 0x00a8 }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r7 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ all -> 0x00a8 }
            r7.<init>(r5, r1)     // Catch:{ all -> 0x00a8 }
            if (r2 == 0) goto L_0x00d1
            r2.close()     // Catch:{ IOException -> 0x00cd, SocketTimeoutException -> 0x00e7, Exception -> 0x00e2 }
            goto L_0x00d1
        L_0x00cd:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
        L_0x00d1:
            if (r6 == 0) goto L_0x00d6
            r6.disconnect()
        L_0x00d6:
            return r7
        L_0x00d7:
            if (r2 == 0) goto L_0x00e1
            r2.close()     // Catch:{ IOException -> 0x00dd, SocketTimeoutException -> 0x00e7, Exception -> 0x00e2 }
            goto L_0x00e1
        L_0x00dd:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
        L_0x00e1:
            throw r1     // Catch:{ SocketTimeoutException -> 0x00e7, IOException -> 0x00e4, Exception -> 0x00e2 }
        L_0x00e2:
            r10 = move-exception
            goto L_0x00ee
        L_0x00e4:
            r10 = move-exception
            r5 = r6
            goto L_0x012c
        L_0x00e7:
            r5 = r6
            goto L_0x0154
        L_0x00e9:
            r10 = move-exception
            goto L_0x017e
        L_0x00ec:
            r10 = move-exception
            r6 = r5
        L_0x00ee:
            java.lang.Class r11 = r9.getClass()     // Catch:{ all -> 0x0128 }
            java.lang.String r11 = r11.getSimpleName()     // Catch:{ all -> 0x0128 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0128 }
            r12.<init>()     // Catch:{ all -> 0x0128 }
            java.lang.String r1 = "Exception: "
            r12.append(r1)     // Catch:{ all -> 0x0128 }
            java.lang.String r1 = r10.getMessage()     // Catch:{ all -> 0x0128 }
            r12.append(r1)     // Catch:{ all -> 0x0128 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0128 }
            io.branch.referral.PrefHelper.Debug(r11, r12)     // Catch:{ all -> 0x0128 }
            int r11 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0128 }
            r12 = 11
            if (r11 < r12) goto L_0x011d
            boolean r10 = r10 instanceof android.os.NetworkOnMainThreadException     // Catch:{ all -> 0x0128 }
            if (r10 == 0) goto L_0x011d
            java.lang.String r10 = "Branch Error: Don't call our synchronous methods on the main thread!!!"
            android.util.Log.i(r0, r10)     // Catch:{ all -> 0x0128 }
        L_0x011d:
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r10 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ all -> 0x0128 }
            r10.<init>(r5, r3)     // Catch:{ all -> 0x0128 }
            if (r6 == 0) goto L_0x0127
            r6.disconnect()
        L_0x0127:
            return r10
        L_0x0128:
            r10 = move-exception
            r5 = r6
            goto L_0x017e
        L_0x012b:
            r10 = move-exception
        L_0x012c:
            java.lang.Class r11 = r9.getClass()     // Catch:{ all -> 0x00e9 }
            java.lang.String r11 = r11.getSimpleName()     // Catch:{ all -> 0x00e9 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e9 }
            r12.<init>()     // Catch:{ all -> 0x00e9 }
            java.lang.String r0 = "Http connect exception: "
            r12.append(r0)     // Catch:{ all -> 0x00e9 }
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x00e9 }
            r12.append(r10)     // Catch:{ all -> 0x00e9 }
            java.lang.String r10 = r12.toString()     // Catch:{ all -> 0x00e9 }
            io.branch.referral.PrefHelper.Debug(r11, r10)     // Catch:{ all -> 0x00e9 }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r10 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00e9 }
            r11 = -113(0xffffffffffffff8f, float:NaN)
            r10.<init>(r11)     // Catch:{ all -> 0x00e9 }
            throw r10     // Catch:{ all -> 0x00e9 }
        L_0x0154:
            io.branch.referral.PrefHelper r0 = r9.prefHelper     // Catch:{ all -> 0x00e9 }
            int r0 = r0.getRetryCount()     // Catch:{ all -> 0x00e9 }
            if (r12 >= r0) goto L_0x0176
            io.branch.referral.PrefHelper r0 = r9.prefHelper     // Catch:{ InterruptedException -> 0x0167 }
            int r0 = r0.getRetryInterval()     // Catch:{ InterruptedException -> 0x0167 }
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0167 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0167 }
            goto L_0x016b
        L_0x0167:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00e9 }
        L_0x016b:
            int r12 = r12 + r4
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r10 = r9.doRestfulPost(r10, r11, r12)     // Catch:{ all -> 0x00e9 }
            if (r5 == 0) goto L_0x0175
            r5.disconnect()
        L_0x0175:
            return r10
        L_0x0176:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r10 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00e9 }
            r11 = -111(0xffffffffffffff91, float:NaN)
            r10.<init>(r11)     // Catch:{ all -> 0x00e9 }
            throw r10     // Catch:{ all -> 0x00e9 }
        L_0x017e:
            if (r5 == 0) goto L_0x0183
            r5.disconnect()
        L_0x0183:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulPost(java.lang.String, org.json.JSONObject, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    private String getResponseString(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return new BufferedReader(new InputStreamReader(inputStream)).readLine();
            } catch (IOException unused) {
            }
        }
        return null;
    }
}
