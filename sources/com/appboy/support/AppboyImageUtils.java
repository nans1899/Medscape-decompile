package com.appboy.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.enums.AppboyViewBounds;
import java.io.File;
import java.io.InputStream;

public class AppboyImageUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyImageUtils.class);

    public static Bitmap getBitmap(Uri uri) {
        return getBitmap((Context) null, uri, (AppboyViewBounds) null);
    }

    public static Bitmap getBitmap(Context context, Uri uri, AppboyViewBounds appboyViewBounds) {
        if (uri == null) {
            AppboyLogger.i(a, "Null Uri received. Not getting image.");
            return null;
        } else if (AppboyFileUtils.isLocalUri(uri)) {
            return a(uri);
        } else {
            if (!AppboyFileUtils.isRemoteUri(uri)) {
                AppboyLogger.w(a, "Uri with unknown scheme received. Not getting image.");
                return null;
            } else if (context == null || appboyViewBounds == null) {
                return a(uri, 0, 0);
            } else {
                DisplayMetrics defaultScreenDisplayMetrics = getDefaultScreenDisplayMetrics(context);
                return a(uri, getPixelsFromDensityAndDp(defaultScreenDisplayMetrics.densityDpi, appboyViewBounds.getWidthDp()), getPixelsFromDensityAndDp(defaultScreenDisplayMetrics.densityDpi, appboyViewBounds.getHeightDp()));
            }
        }
    }

    public static DisplayMetrics getDefaultScreenDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getPixelsFromDensityAndDp(int i, int i2) {
        return Math.abs((i * i2) / 160);
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01bc A[SYNTHETIC, Splitter:B:106:0x01bc] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01e5 A[SYNTHETIC, Splitter:B:115:0x01e5] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x020e A[SYNTHETIC, Splitter:B:124:0x020e] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0160 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0165 A[SYNTHETIC, Splitter:B:86:0x0165] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x018a A[SYNTHETIC, Splitter:B:95:0x018a] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:110:0x01c3=Splitter:B:110:0x01c3, B:81:0x0148=Splitter:B:81:0x0148, B:101:0x019a=Splitter:B:101:0x019a, B:119:0x01ec=Splitter:B:119:0x01ec, B:90:0x016d=Splitter:B:90:0x016d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap a(android.net.Uri r9, int r10, int r11) {
        /*
            java.lang.String r0 = " width "
            java.lang.String r1 = "IOException during closing of bitmap metadata download stream."
            java.lang.String r9 = r9.toString()
            boolean r2 = com.appboy.Appboy.getOutboundNetworkRequestsOffline()
            r3 = 0
            if (r2 == 0) goto L_0x0026
            java.lang.String r10 = a
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "SDK is in offline mode, not downloading remote bitmap with uri: "
            r11.append(r0)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            com.appboy.support.AppboyLogger.i(r10, r9)
            return r3
        L_0x0026:
            java.net.URL r2 = new java.net.URL     // Catch:{ OutOfMemoryError -> 0x01e9, UnknownHostException -> 0x01c0, MalformedURLException -> 0x0197, Exception -> 0x016a, all -> 0x0145 }
            r2.<init>(r9)     // Catch:{ OutOfMemoryError -> 0x01e9, UnknownHostException -> 0x01c0, MalformedURLException -> 0x0197, Exception -> 0x016a, all -> 0x0145 }
            java.net.URLConnection r4 = bo.app.k.a(r2)     // Catch:{ OutOfMemoryError -> 0x01e9, UnknownHostException -> 0x01c0, MalformedURLException -> 0x0197, Exception -> 0x016a, all -> 0x0145 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ OutOfMemoryError -> 0x01e9, UnknownHostException -> 0x01c0, MalformedURLException -> 0x0197, Exception -> 0x016a, all -> 0x0145 }
            int r5 = r4.getResponseCode()     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x0062
            java.lang.String r10 = a     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            r11.<init>()     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            java.lang.String r0 = "HTTP response code was "
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            r11.append(r5)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            java.lang.String r0 = ". Bitmap with url "
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            r11.append(r2)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            java.lang.String r0 = " could not be downloaded."
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            java.lang.String r11 = r11.toString()     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            com.appboy.support.AppboyLogger.w((java.lang.String) r10, (java.lang.String) r11)     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            if (r4 == 0) goto L_0x0061
            r4.disconnect()
        L_0x0061:
            return r3
        L_0x0062:
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ OutOfMemoryError -> 0x0141, UnknownHostException -> 0x013d, MalformedURLException -> 0x013a, Exception -> 0x0137, all -> 0x0134 }
            if (r10 == 0) goto L_0x0111
            if (r11 == 0) goto L_0x0111
            java.lang.String r6 = a     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            r7.<init>()     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.lang.String r8 = "Sampling bitmap with destination image bounds: (height "
            r7.append(r8)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            r7.append(r11)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            r7.append(r0)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            r7.append(r10)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.lang.String r8 = ")"
            r7.append(r8)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.lang.String r7 = r7.toString()     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            com.appboy.support.AppboyLogger.d(r6, r7)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            android.graphics.BitmapFactory$Options r6 = a((java.io.InputStream) r5)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            r4.disconnect()     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.net.URLConnection r7 = bo.app.k.a(r2)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            java.io.InputStream r5 = r7.getInputStream()     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            int r4 = r6.outHeight     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            if (r4 == 0) goto L_0x00bb
            int r4 = r6.outWidth     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            if (r4 != 0) goto L_0x00a5
            goto L_0x00bb
        L_0x00a5:
            android.graphics.Bitmap r9 = a(r5, r6, r10, r11)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            if (r7 == 0) goto L_0x00ae
            r7.disconnect()
        L_0x00ae:
            if (r5 == 0) goto L_0x00ba
            r5.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00ba
        L_0x00b4:
            r10 = move-exception
            java.lang.String r11 = a
            com.appboy.support.AppboyLogger.e(r11, r1, r10)
        L_0x00ba:
            return r9
        L_0x00bb:
            java.lang.String r10 = a     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            r11.<init>()     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            java.lang.String r4 = "The bitmap metadata with image url "
            r11.append(r4)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            r11.append(r2)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            java.lang.String r2 = " had bounds: (height "
            r11.append(r2)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            int r2 = r6.outHeight     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            r11.append(r2)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            int r0 = r6.outWidth     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            java.lang.String r0 = "). Returning a bitmap with no sampling."
            r11.append(r0)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            java.lang.String r11 = r11.toString()     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            com.appboy.support.AppboyLogger.w((java.lang.String) r10, (java.lang.String) r11)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch:{ OutOfMemoryError -> 0x010d, UnknownHostException -> 0x0109, MalformedURLException -> 0x0105, Exception -> 0x0101, all -> 0x00fe }
            if (r7 == 0) goto L_0x00f1
            r7.disconnect()
        L_0x00f1:
            if (r5 == 0) goto L_0x00fd
            r5.close()     // Catch:{ IOException -> 0x00f7 }
            goto L_0x00fd
        L_0x00f7:
            r10 = move-exception
            java.lang.String r11 = a
            com.appboy.support.AppboyLogger.e(r11, r1, r10)
        L_0x00fd:
            return r9
        L_0x00fe:
            r10 = move-exception
            r4 = r7
            goto L_0x0148
        L_0x0101:
            r10 = move-exception
            r4 = r7
            goto L_0x016d
        L_0x0105:
            r10 = move-exception
            r4 = r7
            goto L_0x019a
        L_0x0109:
            r10 = move-exception
            r4 = r7
            goto L_0x01c3
        L_0x010d:
            r10 = move-exception
            r4 = r7
            goto L_0x01ec
        L_0x0111:
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch:{ OutOfMemoryError -> 0x0131, UnknownHostException -> 0x012e, MalformedURLException -> 0x012b, Exception -> 0x0129, all -> 0x0127 }
            if (r4 == 0) goto L_0x011a
            r4.disconnect()
        L_0x011a:
            if (r5 == 0) goto L_0x0126
            r5.close()     // Catch:{ IOException -> 0x0120 }
            goto L_0x0126
        L_0x0120:
            r10 = move-exception
            java.lang.String r11 = a
            com.appboy.support.AppboyLogger.e(r11, r1, r10)
        L_0x0126:
            return r9
        L_0x0127:
            r10 = move-exception
            goto L_0x0148
        L_0x0129:
            r10 = move-exception
            goto L_0x016d
        L_0x012b:
            r10 = move-exception
            goto L_0x019a
        L_0x012e:
            r10 = move-exception
            goto L_0x01c3
        L_0x0131:
            r10 = move-exception
            goto L_0x01ec
        L_0x0134:
            r10 = move-exception
            r5 = r3
            goto L_0x0148
        L_0x0137:
            r10 = move-exception
            r5 = r3
            goto L_0x016d
        L_0x013a:
            r10 = move-exception
            r5 = r3
            goto L_0x019a
        L_0x013d:
            r10 = move-exception
            r5 = r3
            goto L_0x01c3
        L_0x0141:
            r10 = move-exception
            r5 = r3
            goto L_0x01ec
        L_0x0145:
            r10 = move-exception
            r4 = r3
            r5 = r4
        L_0x0148:
            java.lang.String r11 = a     // Catch:{ all -> 0x0212 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0212 }
            r0.<init>()     // Catch:{ all -> 0x0212 }
            java.lang.String r2 = "Throwable caught in image bitmap download for Uri: "
            r0.append(r2)     // Catch:{ all -> 0x0212 }
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0212 }
            com.appboy.support.AppboyLogger.e(r11, r9, r10)     // Catch:{ all -> 0x0212 }
            if (r4 == 0) goto L_0x0163
            r4.disconnect()
        L_0x0163:
            if (r5 == 0) goto L_0x0211
            r5.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0211
        L_0x016a:
            r10 = move-exception
            r4 = r3
            r5 = r4
        L_0x016d:
            java.lang.String r11 = a     // Catch:{ all -> 0x0212 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0212 }
            r0.<init>()     // Catch:{ all -> 0x0212 }
            java.lang.String r2 = "Exception in image bitmap download for Uri: "
            r0.append(r2)     // Catch:{ all -> 0x0212 }
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0212 }
            com.appboy.support.AppboyLogger.e(r11, r9, r10)     // Catch:{ all -> 0x0212 }
            if (r4 == 0) goto L_0x0188
            r4.disconnect()
        L_0x0188:
            if (r5 == 0) goto L_0x0211
            r5.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0211
        L_0x018f:
            r9 = move-exception
            java.lang.String r10 = a
            com.appboy.support.AppboyLogger.e(r10, r1, r9)
            goto L_0x0211
        L_0x0197:
            r10 = move-exception
            r4 = r3
            r5 = r4
        L_0x019a:
            java.lang.String r11 = a     // Catch:{ all -> 0x0212 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0212 }
            r0.<init>()     // Catch:{ all -> 0x0212 }
            java.lang.String r2 = "Malformed URL Exception in image bitmap download for Uri: "
            r0.append(r2)     // Catch:{ all -> 0x0212 }
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = ". Image Uri may be corrupted."
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0212 }
            com.appboy.support.AppboyLogger.e(r11, r9, r10)     // Catch:{ all -> 0x0212 }
            if (r4 == 0) goto L_0x01ba
            r4.disconnect()
        L_0x01ba:
            if (r5 == 0) goto L_0x0211
            r5.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0211
        L_0x01c0:
            r10 = move-exception
            r4 = r3
            r5 = r4
        L_0x01c3:
            java.lang.String r11 = a     // Catch:{ all -> 0x0212 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0212 }
            r0.<init>()     // Catch:{ all -> 0x0212 }
            java.lang.String r2 = "Unknown Host Exception in image bitmap download for Uri: "
            r0.append(r2)     // Catch:{ all -> 0x0212 }
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = ". Device may be offline."
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0212 }
            com.appboy.support.AppboyLogger.e(r11, r9, r10)     // Catch:{ all -> 0x0212 }
            if (r4 == 0) goto L_0x01e3
            r4.disconnect()
        L_0x01e3:
            if (r5 == 0) goto L_0x0211
            r5.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0211
        L_0x01e9:
            r10 = move-exception
            r4 = r3
            r5 = r4
        L_0x01ec:
            java.lang.String r11 = a     // Catch:{ all -> 0x0212 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0212 }
            r0.<init>()     // Catch:{ all -> 0x0212 }
            java.lang.String r2 = "Out of Memory Error in image bitmap download for Uri: "
            r0.append(r2)     // Catch:{ all -> 0x0212 }
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = "."
            r0.append(r9)     // Catch:{ all -> 0x0212 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0212 }
            com.appboy.support.AppboyLogger.e(r11, r9, r10)     // Catch:{ all -> 0x0212 }
            if (r4 == 0) goto L_0x020c
            r4.disconnect()
        L_0x020c:
            if (r5 == 0) goto L_0x0211
            r5.close()     // Catch:{ IOException -> 0x018f }
        L_0x0211:
            return r3
        L_0x0212:
            r9 = move-exception
            if (r4 == 0) goto L_0x0218
            r4.disconnect()
        L_0x0218:
            if (r5 == 0) goto L_0x0224
            r5.close()     // Catch:{ IOException -> 0x021e }
            goto L_0x0224
        L_0x021e:
            r10 = move-exception
            java.lang.String r11 = a
            com.appboy.support.AppboyLogger.e(r11, r1, r10)
        L_0x0224:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.AppboyImageUtils.a(android.net.Uri, int, int):android.graphics.Bitmap");
    }

    private static Bitmap a(Uri uri) {
        try {
            File file = new File(uri.getPath());
            if (!file.exists()) {
                return null;
            }
            String str = a;
            AppboyLogger.i(str, "Retrieving image from path: " + file.getAbsolutePath());
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (OutOfMemoryError e) {
            String str2 = a;
            AppboyLogger.e(str2, "Out of Memory Error in local bitmap file retrieval for Uri: " + uri.toString() + ".", e);
            return null;
        } catch (Exception e2) {
            AppboyLogger.e(a, "Exception occurred when attempting to retrieve local bitmap.", e2);
            return null;
        } catch (Throwable th) {
            String str3 = a;
            AppboyLogger.e(str3, "Throwable caught in local bitmap file retrieval for Uri: " + uri.toString(), th);
            return null;
        }
    }

    public static int getImageLoaderCacheSize() {
        return Math.max(1024, Math.min((int) Math.min(Runtime.getRuntime().maxMemory() / 8, 2147483647L), 33554432));
    }

    static BitmapFactory.Options a(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        return options;
    }

    private static Bitmap a(InputStream inputStream, BitmapFactory.Options options, int i, int i2) {
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
    }

    static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        String str = a;
        AppboyLogger.d(str, "Calculating sample size for source image bounds: (height " + options.outHeight + " width " + options.outWidth + ") and destination image bounds: (height " + i2 + " width " + i + ")");
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        String str2 = a;
        AppboyLogger.d(str2, "Using image sample size of " + i5);
        return i5;
    }
}
