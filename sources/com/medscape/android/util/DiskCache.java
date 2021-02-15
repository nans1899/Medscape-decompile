package com.medscape.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DiskCache {
    public static final String DEFAULT_PREFIX = "cache";
    private final int EXPIRATION_IN_DAYS;
    private final String NO_MEDIA_FILE;
    private final String TAG;
    private File mCacheDir;
    private final String mThumbnailPrefix;

    public DiskCache(Context context, String str) {
        this.TAG = "DiskCache";
        this.EXPIRATION_IN_DAYS = 15;
        this.NO_MEDIA_FILE = ".nomedia";
        this.mThumbnailPrefix = str;
        File diskCacheDir = getDiskCacheDir(context);
        this.mCacheDir = diskCacheDir;
        if (!diskCacheDir.exists()) {
            this.mCacheDir.mkdir();
        }
        File file = new File(this.mCacheDir, ".nomedia");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            LogUtil.e("DiskCache", "Failed to .nomedia file = %s", e.getMessage().toString());
        }
    }

    public DiskCache(Context context) {
        this(context, DEFAULT_PREFIX);
    }

    public Bitmap download(String str, String str2) {
        String createFilePath = createFilePath(str2);
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(createFilePath));
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(read);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("DiskCache", "Error getting the image from server : Carousel = %s", e.getMessage().toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return BitmapFactory.decodeFile(createFilePath);
    }

    private File getDiskCacheDir(Context context) {
        String path = context.getCacheDir().getPath();
        return new File(path + File.separator);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
        if (((new java.util.Date().getTime() - r4.lastModified()) / com.medscape.android.Constants.DAY_IN_MILLIS) < 15) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        r4.delete();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sanitizeDiskCache(java.lang.String[] r11) {
        /*
            r10 = this;
            java.io.File r0 = r10.mCacheDir
            java.io.File[] r0 = r0.listFiles()
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x005b
            r4 = r0[r3]
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = ".nomedia"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x001d
            goto L_0x0058
        L_0x001d:
            int r5 = r11.length
            r6 = 0
        L_0x001f:
            if (r6 >= r5) goto L_0x0058
            r7 = r11[r6]
            java.lang.String r8 = r4.getName()
            boolean r7 = r8.startsWith(r7)
            if (r7 != 0) goto L_0x003d
            java.lang.String r7 = r4.getName()
            java.lang.String r8 = "cache"
            boolean r7 = r7.startsWith(r8)
            if (r7 == 0) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            int r6 = r6 + 1
            goto L_0x001f
        L_0x003d:
            long r5 = r4.lastModified()
            java.util.Date r7 = new java.util.Date
            r7.<init>()
            long r7 = r7.getTime()
            long r7 = r7 - r5
            r5 = 86400000(0x5265c00, double:4.2687272E-316)
            long r7 = r7 / r5
            r5 = 15
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r9 < 0) goto L_0x0058
            r4.delete()
        L_0x0058:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.DiskCache.sanitizeDiskCache(java.lang.String[]):void");
    }

    public String createFilePath(String str) {
        return this.mCacheDir.getAbsolutePath() + File.separator + this.mThumbnailPrefix + str;
    }

    public Bitmap get(String str) {
        File file = new File(createFilePath(str));
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        return null;
    }

    public boolean containsKey(String str) {
        return new File(createFilePath(str)).exists();
    }
}
