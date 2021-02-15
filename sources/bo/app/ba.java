package bo.app;

import android.graphics.Bitmap;
import bo.app.bb;
import com.appboy.support.AppboyLogger;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ba {
    private static final String a = AppboyLogger.getAppboyLogTag(ba.class);
    private final bb b;

    public ba(File file, int i, int i2, long j) {
        this.b = bb.a(file, i, i2, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r7 = r6.c(r7)
            r0 = 0
            bo.app.bb r1 = r6.b     // Catch:{ IOException -> 0x001e, all -> 0x001c }
            bo.app.bb$b r1 = r1.a((java.lang.String) r7)     // Catch:{ IOException -> 0x001e, all -> 0x001c }
            r2 = 0
            java.io.InputStream r2 = r1.a(r2)     // Catch:{ IOException -> 0x001a }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r2)     // Catch:{ IOException -> 0x001a }
            if (r1 == 0) goto L_0x0019
            r1.close()
        L_0x0019:
            return r7
        L_0x001a:
            r2 = move-exception
            goto L_0x0020
        L_0x001c:
            r7 = move-exception
            goto L_0x0054
        L_0x001e:
            r2 = move-exception
            r1 = r0
        L_0x0020:
            java.lang.String r3 = a     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r4.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r5 = "Failed to get bitmap from disk cache for key "
            r4.append(r5)     // Catch:{ all -> 0x0052 }
            r4.append(r7)     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0052 }
            com.appboy.support.AppboyLogger.e(r3, r4, r2)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x003b
            r1.close()
        L_0x003b:
            java.lang.String r1 = a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to load image from disk cache: "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.appboy.support.AppboyLogger.d(r1, r7)
            return r0
        L_0x0052:
            r7 = move-exception
            r0 = r1
        L_0x0054:
            if (r0 == 0) goto L_0x0059
            r0.close()
        L_0x0059:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ba.a(java.lang.String):android.graphics.Bitmap");
    }

    public void a(String str, Bitmap bitmap) {
        String str2;
        StringBuilder sb;
        String c = c(str);
        OutputStream outputStream = null;
        try {
            bb.a b2 = this.b.b(c);
            outputStream = b2.a(0);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            b2.a();
            if (outputStream != null) {
                try {
                    outputStream.close();
                    return;
                } catch (IOException e) {
                    e = e;
                    str2 = a;
                    sb = new StringBuilder();
                }
            } else {
                return;
            }
            sb.append("Exception while closing disk cache output stream for key");
            sb.append(c);
            AppboyLogger.e(str2, sb.toString(), e);
        } catch (IOException e2) {
            String str3 = a;
            AppboyLogger.e(str3, "Exception while producing output stream or compressing bitmap for key " + c, e2);
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e3) {
                    e = e3;
                    str2 = a;
                    sb = new StringBuilder();
                }
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e4) {
                    String str4 = a;
                    AppboyLogger.e(str4, "Exception while closing disk cache output stream for key" + c, e4);
                }
            }
            throw th;
        }
    }

    public boolean b(String str) {
        String c = c(str);
        boolean z = false;
        try {
            bb.b a2 = this.b.a(c);
            if (a2 != null) {
                z = true;
            }
            if (a2 != null) {
                a2.close();
            }
            return z;
        } catch (IOException e) {
            String str2 = a;
            AppboyLogger.e(str2, "Error while retrieving disk for key " + c, e);
            return false;
        }
    }

    private String c(String str) {
        return Integer.toString(str.hashCode());
    }
}
