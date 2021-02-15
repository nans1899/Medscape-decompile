package com.adobe.mobile;

import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.jsoup.helper.HttpConnection;

final class RequestHandler {
    private static final int BUF_SIZE = 1024;
    private static final int CONNECTION_TIMEOUT = 2000;
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int MAX_REDIRECT_COUNT = 21;
    private static final int TO_MILLI = 1000;

    protected interface HeaderCallback {
        void call(Map<String, List<String>> map);
    }

    RequestHandler() {
    }

    protected static byte[] retrieveData(String str, final Map<String, String> map, int i, String str2) {
        if (StaticMethods.isWearableApp()) {
            return WearableFunctionBridge.retrieveData(str, i);
        }
        AnonymousClass1 r0 = new Callable<Map<String, String>>() {
            public Map<String, String> call() throws Exception {
                return map;
            }
        };
        if (map == null) {
            r0 = null;
        }
        return retrieveData(str, i, str2, r0, (HeaderCallback) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        com.adobe.mobile.StaticMethods.logErrorFormat("%s - Too many redirects for (%s) - %d", r15, r13, java.lang.Integer.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0108, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010d, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0138, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0139, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0164, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0165, code lost:
        r3 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x019b A[SYNTHETIC, Splitter:B:109:0x019b] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0124 A[SYNTHETIC, Splitter:B:73:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0150 A[SYNTHETIC, Splitter:B:86:0x0150] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x017c A[SYNTHETIC, Splitter:B:99:0x017c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static byte[] retrieveData(java.lang.String r13, int r14, java.lang.String r15, java.util.concurrent.Callable<java.util.Map<java.lang.String, java.lang.String>> r16, com.adobe.mobile.RequestHandler.HeaderCallback r17) {
        /*
            r0 = r17
            r1 = 0
            r2 = 0
            r4 = r13
            r5 = r1
            r3 = 0
            r6 = 0
        L_0x0008:
            r7 = 21
            java.lang.String r8 = "%s - Unable to close stream (%s)"
            r9 = 2
            r10 = 1
            if (r3 <= r7) goto L_0x0023
            java.lang.String r0 = "%s - Too many redirects for (%s) - %d"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r4[r2] = r15     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r4[r10] = r13     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r4[r9] = r3     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r4)     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            goto L_0x0091
        L_0x0023:
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r6.<init>(r4)     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            java.net.URLConnection r6 = r6.openConnection()     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r5 = 2000(0x7d0, float:2.803E-42)
            r6.setConnectTimeout(r5)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r5 = r14
            r6.setReadTimeout(r14)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r6.setInstanceFollowRedirects(r2)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.String r7 = "Accept-Language"
            java.lang.String r11 = com.adobe.mobile.StaticMethods.getDefaultAcceptLanguage()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r6.setRequestProperty(r7, r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.String r7 = "User-Agent"
            java.lang.String r11 = com.adobe.mobile.StaticMethods.getDefaultUserAgent()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r6.setRequestProperty(r7, r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            if (r16 == 0) goto L_0x007a
            java.lang.Object r7 = r16.call()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.util.Map r7 = (java.util.Map) r7     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            if (r7 == 0) goto L_0x007a
            java.util.Set r7 = r7.entrySet()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
        L_0x005e:
            boolean r11 = r7.hasNext()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            if (r11 == 0) goto L_0x007a
            java.lang.Object r11 = r7.next()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.Object r12 = r11.getKey()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.Object r11 = r11.getValue()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r6.setRequestProperty(r12, r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            goto L_0x005e
        L_0x007a:
            int r7 = r6.getResponseCode()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            if (r0 == 0) goto L_0x0087
            java.util.Map r11 = r6.getHeaderFields()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r0.call(r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
        L_0x0087:
            r11 = 301(0x12d, float:4.22E-43)
            if (r7 == r11) goto L_0x00dd
            r11 = 302(0x12e, float:4.23E-43)
            if (r7 == r11) goto L_0x00dd
            r5 = r6
            r6 = r7
        L_0x0091:
            r0 = 200(0xc8, float:2.8E-43)
            if (r6 != r0) goto L_0x00d7
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ IOException -> 0x0164, Exception -> 0x0138, Error -> 0x010c, all -> 0x0108 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            r4.<init>()     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
        L_0x00a2:
            int r6 = r3.read(r0)     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            r7 = -1
            if (r6 != r7) goto L_0x00cb
            r3.close()     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            byte[] r1 = r4.toByteArray()     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            if (r5 == 0) goto L_0x00b5
            r5.disconnect()
        L_0x00b5:
            if (r3 == 0) goto L_0x00ca
            r3.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00ca
        L_0x00bb:
            r0 = move-exception
            r3 = r0
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r2] = r15
            java.lang.String r2 = r3.getLocalizedMessage()
            r0[r10] = r2
            com.adobe.mobile.StaticMethods.logWarningFormat(r8, r0)
        L_0x00ca:
            return r1
        L_0x00cb:
            r4.write(r0, r2, r6)     // Catch:{ IOException -> 0x00d4, Exception -> 0x00d1, Error -> 0x00cf }
            goto L_0x00a2
        L_0x00cf:
            r0 = move-exception
            goto L_0x010e
        L_0x00d1:
            r0 = move-exception
            goto L_0x013a
        L_0x00d4:
            r0 = move-exception
            goto L_0x0166
        L_0x00d7:
            if (r5 == 0) goto L_0x00dc
            r5.disconnect()
        L_0x00dc:
            return r1
        L_0x00dd:
            int r3 = r3 + 1
            java.lang.String r11 = "Location"
            java.lang.String r11 = r6.getHeaderField(r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.net.URL r12 = new java.net.URL     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r12.<init>(r4)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r4.<init>(r12, r11)     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            java.lang.String r4 = r4.toExternalForm()     // Catch:{ IOException -> 0x0104, Exception -> 0x0100, Error -> 0x00fc, all -> 0x00f7 }
            r5 = r6
            r6 = r7
            goto L_0x0008
        L_0x00f7:
            r0 = move-exception
            r3 = r0
            r5 = r6
            goto L_0x0194
        L_0x00fc:
            r0 = move-exception
            r3 = r1
            r5 = r6
            goto L_0x010e
        L_0x0100:
            r0 = move-exception
            r3 = r1
            r5 = r6
            goto L_0x013a
        L_0x0104:
            r0 = move-exception
            r3 = r1
            r5 = r6
            goto L_0x0166
        L_0x0108:
            r0 = move-exception
        L_0x0109:
            r3 = r0
            goto L_0x0194
        L_0x010c:
            r0 = move-exception
            r3 = r1
        L_0x010e:
            java.lang.String r4 = "%s - Unexpected error while sending request (%s)"
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ all -> 0x0190 }
            r6[r2] = r15     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0190 }
            r6[r10] = r0     // Catch:{ all -> 0x0190 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r6)     // Catch:{ all -> 0x0190 }
            if (r5 == 0) goto L_0x0122
            r5.disconnect()
        L_0x0122:
            if (r3 == 0) goto L_0x0137
            r3.close()     // Catch:{ IOException -> 0x0128 }
            goto L_0x0137
        L_0x0128:
            r0 = move-exception
            r3 = r0
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r2] = r15
            java.lang.String r2 = r3.getLocalizedMessage()
            r0[r10] = r2
            com.adobe.mobile.StaticMethods.logWarningFormat(r8, r0)
        L_0x0137:
            return r1
        L_0x0138:
            r0 = move-exception
            r3 = r1
        L_0x013a:
            java.lang.String r4 = "%s - Exception while sending request (%s)"
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ all -> 0x0190 }
            r6[r2] = r15     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0190 }
            r6[r10] = r0     // Catch:{ all -> 0x0190 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r6)     // Catch:{ all -> 0x0190 }
            if (r5 == 0) goto L_0x014e
            r5.disconnect()
        L_0x014e:
            if (r3 == 0) goto L_0x0163
            r3.close()     // Catch:{ IOException -> 0x0154 }
            goto L_0x0163
        L_0x0154:
            r0 = move-exception
            r3 = r0
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r2] = r15
            java.lang.String r2 = r3.getLocalizedMessage()
            r0[r10] = r2
            com.adobe.mobile.StaticMethods.logWarningFormat(r8, r0)
        L_0x0163:
            return r1
        L_0x0164:
            r0 = move-exception
            r3 = r1
        L_0x0166:
            java.lang.String r4 = "%s - IOException while sending request (%s)"
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ all -> 0x0190 }
            r6[r2] = r15     // Catch:{ all -> 0x0190 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0190 }
            r6[r10] = r0     // Catch:{ all -> 0x0190 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r6)     // Catch:{ all -> 0x0190 }
            if (r5 == 0) goto L_0x017a
            r5.disconnect()
        L_0x017a:
            if (r3 == 0) goto L_0x018f
            r3.close()     // Catch:{ IOException -> 0x0180 }
            goto L_0x018f
        L_0x0180:
            r0 = move-exception
            r3 = r0
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r2] = r15
            java.lang.String r2 = r3.getLocalizedMessage()
            r0[r10] = r2
            com.adobe.mobile.StaticMethods.logWarningFormat(r8, r0)
        L_0x018f:
            return r1
        L_0x0190:
            r0 = move-exception
            r1 = r3
            goto L_0x0109
        L_0x0194:
            if (r5 == 0) goto L_0x0199
            r5.disconnect()
        L_0x0199:
            if (r1 == 0) goto L_0x01ae
            r1.close()     // Catch:{ IOException -> 0x019f }
            goto L_0x01ae
        L_0x019f:
            r0 = move-exception
            r1 = r0
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r2] = r15
            java.lang.String r1 = r1.getLocalizedMessage()
            r0[r10] = r1
            com.adobe.mobile.StaticMethods.logWarningFormat(r8, r0)
        L_0x01ae:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.RequestHandler.retrieveData(java.lang.String, int, java.lang.String, java.util.concurrent.Callable, com.adobe.mobile.RequestHandler$HeaderCallback):byte[]");
    }

    protected static void sendGenericRequest(String str, Map<String, String> map, int i, String str2) {
        if (str != null) {
            if (StaticMethods.isWearableApp()) {
                WearableFunctionBridge.sendGenericRequest(str, i, str2);
                return;
            }
            try {
                HttpURLConnection requestConnect = requestConnect(str);
                if (requestConnect != null) {
                    requestConnect.setConnectTimeout(i);
                    requestConnect.setReadTimeout(i);
                    if (map != null) {
                        for (Map.Entry next : map.entrySet()) {
                            String str3 = (String) next.getValue();
                            if (str3.trim().length() > 0) {
                                requestConnect.setRequestProperty((String) next.getKey(), str3);
                            }
                        }
                    }
                    StaticMethods.logDebugFormat("%s - Request Sent(%s)", str2, str);
                    requestConnect.getResponseCode();
                    requestConnect.getInputStream().close();
                    requestConnect.disconnect();
                }
            } catch (SocketTimeoutException unused) {
                StaticMethods.logWarningFormat("%s - Timed out sending request(%s)", str2, str);
            } catch (IOException e) {
                StaticMethods.logWarningFormat("%s - IOException while sending request, may retry(%s)", str2, e.getLocalizedMessage());
            } catch (Exception e2) {
                StaticMethods.logWarningFormat("%s - Exception while attempting to send hit, will not retry(%s)", str2, e2.getLocalizedMessage());
            } catch (Error e3) {
                StaticMethods.logWarningFormat("%s - Exception while attempting to send hit, will not retry(%s)", str2, e3.getLocalizedMessage());
            }
        }
    }

    protected static byte[] retrieveAnalyticsRequestData(String str, String str2, Map<String, String> map, int i, String str3) {
        if (str == null) {
            return null;
        }
        if (StaticMethods.isWearableApp()) {
            return WearableFunctionBridge.retrieveAnalyticsRequestData(str, str2, i, str3);
        }
        HttpURLConnection requestConnect = requestConnect(str);
        if (requestConnect == null) {
            return null;
        }
        try {
            requestConnect.setConnectTimeout(i);
            requestConnect.setReadTimeout(i);
            requestConnect.setRequestMethod("POST");
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.setRequestProperty("connection", "close");
            }
            byte[] bytes = str2.getBytes("UTF-8");
            requestConnect.setFixedLengthStreamingMode(bytes.length);
            requestConnect.setRequestProperty("Content-Type", HttpConnection.FORM_URL_ENCODED);
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    requestConnect.setRequestProperty((String) next.getKey(), (String) next.getValue());
                }
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(requestConnect.getOutputStream());
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
            InputStream inputStream = requestConnect.getInputStream();
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (MobileConfig.getInstance().getSSL() || requestConnect.getResponseCode() == 200) {
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
            inputStream.close();
            StaticMethods.logDebugFormat("%s - Request Sent(%s)", str3, str2);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            return byteArray;
        } catch (SocketTimeoutException unused) {
            StaticMethods.logDebugFormat("%s - Timed out sending request(%s)", str3, str2);
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            return null;
        } catch (IOException e) {
            StaticMethods.logDebugFormat("%s - IOException while sending request, may retry(%s)", str3, e.getLocalizedMessage());
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            return null;
        } catch (Exception e2) {
            StaticMethods.logErrorFormat("%s - Exception while attempting to send hit, will not retry(%s)", str3, e2.getLocalizedMessage());
            byte[] bArr2 = new byte[0];
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            return bArr2;
        } catch (Error e3) {
            StaticMethods.logErrorFormat("%s - Exception while attempting to send hit, will not retry(%s)", str3, e3.getLocalizedMessage());
            byte[] bArr3 = new byte[0];
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            return bArr3;
        } catch (Throwable th) {
            if (!MobileConfig.getInstance().getSSL()) {
                requestConnect.disconnect();
            }
            throw th;
        }
    }

    protected static boolean sendThirdPartyRequest(String str, String str2, Map<String, String> map, int i, String str3, String str4) {
        if (str == null) {
            return false;
        }
        if (StaticMethods.isWearableApp()) {
            return WearableFunctionBridge.sendThirdPartyRequest(str, str2, i, str3, str4);
        }
        HttpURLConnection requestConnect = requestConnect(str);
        if (requestConnect == null) {
            return false;
        }
        try {
            requestConnect.setConnectTimeout(i);
            requestConnect.setReadTimeout(i);
            requestConnect.setRequestMethod("GET");
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    requestConnect.setRequestProperty((String) next.getKey(), (String) next.getValue());
                }
            }
            if (str2 != null && str2.length() > 0) {
                requestConnect.setRequestMethod("POST");
                String str5 = (str3 == null || str3.length() <= 0) ? HttpConnection.FORM_URL_ENCODED : str3;
                byte[] bytes = str2.getBytes("UTF-8");
                requestConnect.setFixedLengthStreamingMode(bytes.length);
                requestConnect.setRequestProperty("Content-Type", str5);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(requestConnect.getOutputStream());
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            InputStream inputStream = requestConnect.getInputStream();
            while (inputStream.read(new byte[10]) > 0) {
            }
            inputStream.close();
            StaticMethods.logDebugFormat("%s - Successfully forwarded hit (%s body: %s type: %s)", str4, str, str2, str3);
        } catch (SocketTimeoutException unused) {
            StaticMethods.logDebugFormat("%s - Timed out sending request (%s)", str4, str2);
            return false;
        } catch (IOException e) {
            StaticMethods.logDebugFormat("%s - IOException while sending request, will not retry (%s)", str4, e.getLocalizedMessage());
        } catch (Exception e2) {
            StaticMethods.logErrorFormat("%s - Exception while attempting to send hit, will not retry (%s)", str4, e2.getLocalizedMessage());
        } catch (Error e3) {
            StaticMethods.logErrorFormat("%s - Exception while attempting to send hit, will not retry (%s)", str4, e3.getLocalizedMessage());
        }
        return true;
    }

    protected static HttpURLConnection requestConnect(String str) {
        try {
            return (HttpURLConnection) new URL(str).openConnection();
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Adobe Mobile - Exception opening URL(%s)", e.getLocalizedMessage());
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.adobe.mobile.NetworkObject, java.io.InputStream] */
    protected static NetworkObject retrieveNetworkObject(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7) {
        HttpURLConnection requestConnect = requestConnect(str);
        ? r2 = 0;
        if (requestConnect == null) {
            return r2;
        }
        NetworkObject networkObject = new NetworkObject();
        try {
            requestConnect.setRequestMethod(str2);
            int i2 = i * 1000;
            requestConnect.setReadTimeout(i2);
            requestConnect.setConnectTimeout(i2);
            if (str5 != null && !str5.isEmpty()) {
                requestConnect.setRequestProperty("Content-Type", str5);
            }
            if (str3 != null && !str3.isEmpty()) {
                requestConnect.setRequestProperty("Accept", str3);
            }
            requestConnect.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
            requestConnect.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, StaticMethods.getDefaultAcceptLanguage());
            requestConnect.setRequestProperty("User-Agent", StaticMethods.getDefaultUserAgent());
            if (str7 != null && !str7.isEmpty()) {
                requestConnect.setRequestProperty("session-id", str7);
            }
            if (str2 != null && (str2.equalsIgnoreCase("POST") || str2.equalsIgnoreCase("PUT"))) {
                requestConnect.setDoOutput(true);
            }
            if (str4 != null) {
                if (!str4.isEmpty()) {
                    byte[] bytes = str4.getBytes("UTF-8");
                    requestConnect.setFixedLengthStreamingMode(bytes.length);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(requestConnect.getOutputStream());
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                }
            }
            networkObject.responseCode = requestConnect.getResponseCode();
            InputStream inputStream = requestConnect.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            networkObject.response = sb.toString();
            networkObject.responseHeaders = requestConnect.getHeaderFields();
            requestConnect.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e.getLocalizedMessage());
                }
            }
        } catch (ProtocolException e2) {
            StaticMethods.logErrorFormat("%s - ProtocolException while trying to get content (%s)", str6, e2);
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e3) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e3.getLocalizedMessage());
                }
            }
        } catch (NullPointerException e4) {
            StaticMethods.logErrorFormat("%s - NullPointerException while trying to get content (%s)", str6, e4);
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e5) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e5.getLocalizedMessage());
                }
            }
        } catch (IOException e6) {
            StaticMethods.logErrorFormat("%s - IOException while trying to get content (%s)", str6, e6);
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e7) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e7.getLocalizedMessage());
                }
            }
        } catch (Error e8) {
            StaticMethods.logErrorFormat("%s - Exception while trying to get content (%s)", str6, e8);
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e9) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e9.getLocalizedMessage());
                }
            }
        } catch (Exception e10) {
            StaticMethods.logErrorFormat("%s - Exception while trying to get content (%s)", str6, e10.getLocalizedMessage());
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e11) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e11.getLocalizedMessage());
                }
            }
        } catch (Throwable th) {
            requestConnect.disconnect();
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e12) {
                    StaticMethods.logWarningFormat("%s - Unable to close stream (%s)", str6, e12.getLocalizedMessage());
                }
            }
            throw th;
        }
        return networkObject;
    }
}
