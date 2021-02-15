package com.adobe.mobile;

import com.facebook.appevents.AppEventsConstants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

class RemoteDownload {
    private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    private static final int DEFAULT_READ_TIMEOUT = 10000;

    protected interface RemoteDownloadBlock {
        void call(boolean z, File file);
    }

    RemoteDownload() {
    }

    protected static boolean stringIsUrl(String str) {
        if (str != null && str.length() > 0) {
            try {
                new URL(str);
                return true;
            } catch (MalformedURLException unused) {
            }
        }
        return false;
    }

    protected static void remoteDownloadAsync(String str, int i, int i2, RemoteDownloadBlock remoteDownloadBlock, String str2) {
        new Thread(new DownloadFileTask(str, remoteDownloadBlock, i, i2, str2)).start();
    }

    protected static void remoteDownloadAsync(String str, int i, int i2, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadAsync(str, i, i2, remoteDownloadBlock, "adbdownloadcache");
    }

    protected static void remoteDownloadAsync(String str, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadAsync(str, 10000, 10000, remoteDownloadBlock, "adbdownloadcache");
    }

    protected static void remoteDownloadAsync(String str, String str2, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadAsync(str, 10000, 10000, remoteDownloadBlock, str2);
    }

    protected static void remoteDownloadSync(String str, int i, int i2, RemoteDownloadBlock remoteDownloadBlock, String str2) {
        new DownloadFileTask(str, remoteDownloadBlock, i, i2, str2).run();
    }

    protected static void remoteDownloadSync(String str, int i, int i2, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadSync(str, i, i2, remoteDownloadBlock, "adbdownloadcache");
    }

    protected static void remoteDownloadSync(String str, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadSync(str, 10000, 10000, remoteDownloadBlock, "adbdownloadcache");
    }

    protected static void remoteDownloadSync(String str, String str2, RemoteDownloadBlock remoteDownloadBlock) {
        remoteDownloadSync(str, 10000, 10000, remoteDownloadBlock, str2);
    }

    protected static File getFileForCachedURL(String str) {
        return getFileForCachedURL(str, "adbdownloadcache");
    }

    protected static File getFileForCachedURL(String str, String str2) {
        File downloadCacheDirectory;
        if (str == null || str.length() < 1 || (downloadCacheDirectory = getDownloadCacheDirectory(str2)) == null) {
            return null;
        }
        String[] list = downloadCacheDirectory.list();
        if (list == null || list.length < 1) {
            StaticMethods.logDebugFormat("Cached Files - Directory is empty (%s).", downloadCacheDirectory.getAbsolutePath());
            return null;
        }
        String md5hash = md5hash(str);
        for (String str3 : list) {
            if (str3.substring(0, str3.lastIndexOf(46)).equals(md5hash)) {
                return new File(downloadCacheDirectory, str3);
            }
        }
        StaticMethods.logDebugFormat("Cached Files - This file has not previously been cached (%s).", str);
        return null;
    }

    protected static void deleteFilesForDirectoryNotInList(String str, List<String> list) {
        File[] listFiles;
        if (list == null || list.size() <= 0) {
            deleteFilesInDirectory(str);
            return;
        }
        File downloadCacheDirectory = getDownloadCacheDirectory(str);
        if (downloadCacheDirectory != null && (listFiles = downloadCacheDirectory.listFiles()) != null && listFiles.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (String md5hash : list) {
                arrayList.add(md5hash(md5hash));
            }
            for (File file : listFiles) {
                String name = file.getName();
                if (!arrayList.contains(name.substring(0, name.indexOf(".")))) {
                    if (file.delete()) {
                        StaticMethods.logDebugFormat("Cached File - Removed unused cache file", new Object[0]);
                    } else {
                        StaticMethods.logWarningFormat("Cached File - Failed to remove unused cache file", new Object[0]);
                    }
                }
            }
        }
    }

    protected static void deleteFilesInDirectory(String str) {
        File[] listFiles;
        File downloadCacheDirectory = getDownloadCacheDirectory(str);
        if (downloadCacheDirectory != null && (listFiles = downloadCacheDirectory.listFiles()) != null && listFiles.length > 0) {
            for (File delete : listFiles) {
                if (delete.delete()) {
                    StaticMethods.logDebugFormat("Cached File - Removed unused cache file", new Object[0]);
                } else {
                    StaticMethods.logWarningFormat("Cached File - Failed to remove unused cache file", new Object[0]);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static File getNewCachedFile(String str, Date date, String str2, String str3) {
        String md5hash;
        if (str == null || str.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - Invalid url parameter while attempting to create cache file. Could not save data.", new Object[0]);
            return null;
        } else if (date == null) {
            StaticMethods.logWarningFormat("Cached File - Invalid lastModified parameter while attempting to create cache file. Could not save data.", new Object[0]);
            return null;
        } else if (str2 == null || str2.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - Invalid etag parameter while attempting to create cache file. Could not save data.", new Object[0]);
            return null;
        } else {
            File downloadCacheDirectory = getDownloadCacheDirectory(str3);
            if (downloadCacheDirectory == null || (md5hash = md5hash(str)) == null || md5hash.length() < 1) {
                return null;
            }
            return new File(downloadCacheDirectory.getPath() + File.separator + md5hash(str) + "." + date.getTime() + "_" + str2);
        }
    }

    protected static File getDownloadCacheDirectory(String str) {
        File file = new File(StaticMethods.getCacheDirectory(), str);
        if (file.exists() || file.mkdir()) {
            return file;
        }
        StaticMethods.logWarningFormat("Cached File - unable to open/make download cache directory", new Object[0]);
        return null;
    }

    protected static boolean deleteCachedDataForURL(String str, String str2) {
        if (str == null || str.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - tried to delete cached file, but file path was empty", new Object[0]);
            return false;
        }
        File fileForCachedURL = getFileForCachedURL(str, str2);
        if (fileForCachedURL == null || !fileForCachedURL.delete()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static long getLastModifiedOfFile(String str) {
        if (str == null || str.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - Path was null or empty for Cache File. Could not get Last Modified Date.", new Object[0]);
            return 0;
        }
        String[] splitPathExtension = splitPathExtension(getPathExtension(str));
        if (splitPathExtension != null && splitPathExtension.length >= 1) {
            return Long.parseLong(splitPathExtension[0]);
        }
        StaticMethods.logWarningFormat("Cached File - No last modified date for file. Extension had no values after split.", new Object[0]);
        return 0;
    }

    /* access modifiers changed from: private */
    public static String getEtagOfFile(String str) {
        if (str == null || str.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - Path was null or empty for Cache File", new Object[0]);
            return null;
        }
        String[] splitPathExtension = splitPathExtension(getPathExtension(str));
        if (splitPathExtension != null && splitPathExtension.length >= 2) {
            return splitPathExtension[1];
        }
        StaticMethods.logWarningFormat("Cached File - No etag for file. Extension had no second value after split.", new Object[0]);
        return null;
    }

    private static String getPathExtension(String str) {
        if (str != null && str.length() >= 1) {
            return str.substring(str.lastIndexOf(".") + 1);
        }
        StaticMethods.logWarningFormat("Cached File - Path was null or empty for Cache File", new Object[0]);
        return null;
    }

    private static String[] splitPathExtension(String str) {
        if (str == null || str.length() < 1) {
            StaticMethods.logWarningFormat("Cached File - Extension was null or empty on Cache File", new Object[0]);
            return null;
        }
        String[] split = str.split("_");
        if (split.length == 2) {
            return split;
        }
        StaticMethods.logWarningFormat("Cached File - Invalid Extension on Cache File (%s)", str);
        return null;
    }

    /* access modifiers changed from: private */
    public static SimpleDateFormat createRFC2822Formatter() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    private static String md5hash(String str) {
        if (str != null && str.length() >= 1) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    String hexString = Integer.toHexString(b & 255);
                    while (hexString.length() < 2) {
                        hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
                    }
                    sb.append(hexString);
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                StaticMethods.logErrorFormat("Cached Files - unable to get md5 hash (%s)", e.getMessage());
            } catch (UnsupportedEncodingException e2) {
                StaticMethods.logErrorFormat("Cached Files - Unsupported Encoding: UTF-8 (%s)", e2.getMessage());
            }
        }
        return null;
    }

    private static class DownloadFileTask implements Runnable {
        private final RemoteDownloadBlock callback;
        private final int connectionTimeout;
        private final String directory;
        private final int readTimeout;
        private final String url;

        private DownloadFileTask(String str, RemoteDownloadBlock remoteDownloadBlock, int i, int i2, String str2) {
            this.url = str;
            this.callback = remoteDownloadBlock;
            this.connectionTimeout = i;
            this.readTimeout = i2;
            this.directory = str2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:107:0x01df A[Catch:{ all -> 0x02a4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:109:0x01e6 A[SYNTHETIC, Splitter:B:109:0x01e6] */
        /* JADX WARNING: Removed duplicated region for block: B:114:0x01ee A[Catch:{ IOException -> 0x01ea }] */
        /* JADX WARNING: Removed duplicated region for block: B:123:0x0217 A[Catch:{ all -> 0x02a4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:125:0x021e A[SYNTHETIC, Splitter:B:125:0x021e] */
        /* JADX WARNING: Removed duplicated region for block: B:130:0x0226 A[Catch:{ IOException -> 0x0222 }] */
        /* JADX WARNING: Removed duplicated region for block: B:139:0x024f A[Catch:{ all -> 0x02a4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:141:0x0256 A[SYNTHETIC, Splitter:B:141:0x0256] */
        /* JADX WARNING: Removed duplicated region for block: B:146:0x025e A[Catch:{ IOException -> 0x025a }] */
        /* JADX WARNING: Removed duplicated region for block: B:156:0x0282 A[Catch:{ all -> 0x02a4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:158:0x0289 A[SYNTHETIC, Splitter:B:158:0x0289] */
        /* JADX WARNING: Removed duplicated region for block: B:163:0x0291 A[Catch:{ IOException -> 0x028d }] */
        /* JADX WARNING: Removed duplicated region for block: B:169:0x02a8 A[SYNTHETIC, Splitter:B:169:0x02a8] */
        /* JADX WARNING: Removed duplicated region for block: B:174:0x02b0 A[Catch:{ IOException -> 0x02ac }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r14 = this;
                java.lang.String r0 = "Cached Files - Exception while attempting to close streams (%s)"
                java.lang.String r1 = r14.url
                r2 = 0
                r3 = 0
                if (r1 != 0) goto L_0x0017
                java.lang.Object[] r0 = new java.lang.Object[r3]
                java.lang.String r1 = "Cached Files - url is null and cannot be cached"
                com.adobe.mobile.StaticMethods.logDebugFormat(r1, r0)
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r0 = r14.callback
                if (r0 == 0) goto L_0x0016
                r0.call(r3, r2)
            L_0x0016:
                return
            L_0x0017:
                boolean r1 = com.adobe.mobile.RemoteDownload.stringIsUrl(r1)
                r4 = 1
                if (r1 != 0) goto L_0x0031
                java.lang.Object[] r0 = new java.lang.Object[r4]
                java.lang.String r1 = r14.url
                r0[r3] = r1
                java.lang.String r1 = "Cached Files - given url is not valid and cannot be cached (\"%s\")"
                com.adobe.mobile.StaticMethods.logDebugFormat(r1, r0)
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r0 = r14.callback
                if (r0 == 0) goto L_0x0030
                r0.call(r3, r2)
            L_0x0030:
                return
            L_0x0031:
                java.lang.String r1 = r14.url
                java.lang.String r5 = r14.directory
                java.io.File r1 = com.adobe.mobile.RemoteDownload.getFileForCachedURL(r1, r5)
                java.text.SimpleDateFormat r5 = com.adobe.mobile.RemoteDownload.createRFC2822Formatter()
                java.lang.String r6 = r14.url
                java.net.HttpURLConnection r6 = requestConnect(r6)
                if (r6 != 0) goto L_0x004d
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r0 = r14.callback
                if (r0 == 0) goto L_0x004c
                r0.call(r3, r2)
            L_0x004c:
                return
            L_0x004d:
                int r7 = r14.connectionTimeout
                r6.setConnectTimeout(r7)
                int r7 = r14.readTimeout
                r6.setReadTimeout(r7)
                if (r1 == 0) goto L_0x008b
                java.lang.String r7 = r1.getPath()
                java.lang.String r7 = com.adobe.mobile.RemoteDownload.getEtagOfFile(r7)
                java.lang.String r7 = com.adobe.mobile.StaticMethods.hexToString(r7)
                java.lang.String r8 = r1.getPath()
                long r8 = com.adobe.mobile.RemoteDownload.getLastModifiedOfFile(r8)
                java.lang.Long r8 = java.lang.Long.valueOf(r8)
                long r9 = r8.longValue()
                r11 = 0
                int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r13 == 0) goto L_0x0084
                java.lang.String r5 = r5.format(r8)
                java.lang.String r8 = "If-Modified-Since"
                r6.setRequestProperty(r8, r5)
            L_0x0084:
                if (r7 == 0) goto L_0x008b
                java.lang.String r5 = "If-None-Match"
                r6.setRequestProperty(r5, r7)
            L_0x008b:
                r6.connect()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                int r5 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7 = 304(0x130, float:4.26E-43)
                if (r5 != r7) goto L_0x00bb
                java.lang.String r5 = "Cached Files - File has not been modified since last download. (%s)"
                java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r8 = r14.url     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7[r3] = r8     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.StaticMethods.logDebugFormat(r5, r7)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r5 == 0) goto L_0x00aa
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r5.call(r3, r1)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x00aa:
                r6.disconnect()     // Catch:{ IOException -> 0x00ae }
                goto L_0x00ba
            L_0x00ae:
                r1 = move-exception
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
            L_0x00ba:
                return
            L_0x00bb:
                int r5 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7 = 404(0x194, float:5.66E-43)
                if (r5 != r7) goto L_0x00e8
                java.lang.String r5 = "Cached Files - File not found. (%s)"
                java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r8 = r14.url     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7[r3] = r8     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.StaticMethods.logDebugFormat(r5, r7)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r5 == 0) goto L_0x00d7
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r5.call(r3, r1)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x00d7:
                r6.disconnect()     // Catch:{ IOException -> 0x00db }
                goto L_0x00e7
            L_0x00db:
                r1 = move-exception
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
            L_0x00e7:
                return
            L_0x00e8:
                int r5 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7 = 200(0xc8, float:2.8E-43)
                if (r5 == r7) goto L_0x0127
                java.lang.String r5 = "Cached Files - File could not be downloaded from URL (%s) Response: (%d) Message: (%s)"
                r7 = 3
                java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r8 = r14.url     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7[r3] = r8     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                int r8 = r6.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7[r4] = r8     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r8 = 2
                java.lang.String r9 = r6.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r7[r8] = r9     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.StaticMethods.logWarningFormat(r5, r7)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r5 == 0) goto L_0x0116
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r5 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r5.call(r3, r1)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x0116:
                r6.disconnect()     // Catch:{ IOException -> 0x011a }
                goto L_0x0126
            L_0x011a:
                r1 = move-exception
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
            L_0x0126:
                return
            L_0x0127:
                if (r1 == 0) goto L_0x0130
                java.lang.String r1 = r14.url     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r5 = r14.directory     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                com.adobe.mobile.RemoteDownload.deleteCachedDataForURL(r1, r5)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x0130:
                java.util.Date r1 = new java.util.Date     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                long r7 = r6.getLastModified()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r1.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r5 = "ETag"
                java.lang.String r5 = r6.getHeaderField(r5)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r5 == 0) goto L_0x0145
                java.lang.String r5 = com.adobe.mobile.StaticMethods.getHexString(r5)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x0145:
                java.lang.String r7 = r14.url     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.lang.String r8 = r14.directory     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.io.File r1 = com.adobe.mobile.RemoteDownload.getNewCachedFile(r7, r1, r5, r8)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r1 != 0) goto L_0x0169
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                if (r1 == 0) goto L_0x0158
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                r1.call(r3, r2)     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
            L_0x0158:
                r6.disconnect()     // Catch:{ IOException -> 0x015c }
                goto L_0x0168
            L_0x015c:
                r1 = move-exception
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
            L_0x0168:
                return
            L_0x0169:
                java.io.InputStream r5 = r6.getInputStream()     // Catch:{ SocketTimeoutException -> 0x0271, IOException -> 0x023b, Exception -> 0x0203, Error -> 0x01cb, all -> 0x01c7 }
                java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ SocketTimeoutException -> 0x01c4, IOException -> 0x01c0, Exception -> 0x01bd, Error -> 0x01ba, all -> 0x01b7 }
                r7.<init>(r1)     // Catch:{ SocketTimeoutException -> 0x01c4, IOException -> 0x01c0, Exception -> 0x01bd, Error -> 0x01ba, all -> 0x01b7 }
                r8 = 4096(0x1000, float:5.74E-42)
                byte[] r8 = new byte[r8]     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
            L_0x0176:
                int r9 = r5.read(r8)     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                r10 = -1
                if (r9 == r10) goto L_0x0181
                r7.write(r8, r3, r9)     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                goto L_0x0176
            L_0x0181:
                java.lang.String r8 = "Cached Files - Caching successful (%s)"
                java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                java.lang.String r10 = r14.url     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                r9[r3] = r10     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                com.adobe.mobile.StaticMethods.logDebugFormat(r8, r9)     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r8 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                if (r8 == 0) goto L_0x0195
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r8 = r14.callback     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
                r8.call(r4, r1)     // Catch:{ SocketTimeoutException -> 0x0273, IOException -> 0x01b4, Exception -> 0x01b2, Error -> 0x01b0 }
            L_0x0195:
                r7.close()     // Catch:{ IOException -> 0x01a2 }
                if (r5 == 0) goto L_0x019d
                r5.close()     // Catch:{ IOException -> 0x01a2 }
            L_0x019d:
                r6.disconnect()     // Catch:{ IOException -> 0x01a2 }
                goto L_0x02a3
            L_0x01a2:
                r1 = move-exception
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
                goto L_0x02a3
            L_0x01b0:
                r1 = move-exception
                goto L_0x01ce
            L_0x01b2:
                r1 = move-exception
                goto L_0x0206
            L_0x01b4:
                r1 = move-exception
                goto L_0x023e
            L_0x01b7:
                r1 = move-exception
                goto L_0x02a6
            L_0x01ba:
                r1 = move-exception
                r7 = r2
                goto L_0x01ce
            L_0x01bd:
                r1 = move-exception
                r7 = r2
                goto L_0x0206
            L_0x01c0:
                r1 = move-exception
                r7 = r2
                goto L_0x023e
            L_0x01c4:
                r7 = r2
                goto L_0x0273
            L_0x01c7:
                r1 = move-exception
                r5 = r2
                goto L_0x02a6
            L_0x01cb:
                r1 = move-exception
                r5 = r2
                r7 = r5
            L_0x01ce:
                java.lang.String r8 = "Cached Files - Unexpected error while attempting to get remote file (%s)"
                java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch:{ all -> 0x02a4 }
                java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x02a4 }
                r9[r3] = r1     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.StaticMethods.logWarningFormat(r8, r9)     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                if (r1 == 0) goto L_0x01e4
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                r1.call(r3, r2)     // Catch:{ all -> 0x02a4 }
            L_0x01e4:
                if (r7 == 0) goto L_0x01ec
                r7.close()     // Catch:{ IOException -> 0x01ea }
                goto L_0x01ec
            L_0x01ea:
                r1 = move-exception
                goto L_0x01f6
            L_0x01ec:
                if (r5 == 0) goto L_0x01f1
                r5.close()     // Catch:{ IOException -> 0x01ea }
            L_0x01f1:
                r6.disconnect()     // Catch:{ IOException -> 0x01ea }
                goto L_0x02a3
            L_0x01f6:
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
                goto L_0x02a3
            L_0x0203:
                r1 = move-exception
                r5 = r2
                r7 = r5
            L_0x0206:
                java.lang.String r8 = "Cached Files - Unexpected exception while attempting to get remote file (%s)"
                java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch:{ all -> 0x02a4 }
                java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x02a4 }
                r9[r3] = r1     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.StaticMethods.logWarningFormat(r8, r9)     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                if (r1 == 0) goto L_0x021c
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                r1.call(r3, r2)     // Catch:{ all -> 0x02a4 }
            L_0x021c:
                if (r7 == 0) goto L_0x0224
                r7.close()     // Catch:{ IOException -> 0x0222 }
                goto L_0x0224
            L_0x0222:
                r1 = move-exception
                goto L_0x022e
            L_0x0224:
                if (r5 == 0) goto L_0x0229
                r5.close()     // Catch:{ IOException -> 0x0222 }
            L_0x0229:
                r6.disconnect()     // Catch:{ IOException -> 0x0222 }
                goto L_0x02a3
            L_0x022e:
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
                goto L_0x02a3
            L_0x023b:
                r1 = move-exception
                r5 = r2
                r7 = r5
            L_0x023e:
                java.lang.String r8 = "Cached Files - IOException while making request (%s)"
                java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch:{ all -> 0x02a4 }
                java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x02a4 }
                r9[r3] = r1     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.StaticMethods.logWarningFormat(r8, r9)     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                if (r1 == 0) goto L_0x0254
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                r1.call(r3, r2)     // Catch:{ all -> 0x02a4 }
            L_0x0254:
                if (r7 == 0) goto L_0x025c
                r7.close()     // Catch:{ IOException -> 0x025a }
                goto L_0x025c
            L_0x025a:
                r1 = move-exception
                goto L_0x0265
            L_0x025c:
                if (r5 == 0) goto L_0x0261
                r5.close()     // Catch:{ IOException -> 0x025a }
            L_0x0261:
                r6.disconnect()     // Catch:{ IOException -> 0x025a }
                goto L_0x02a3
            L_0x0265:
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
                goto L_0x02a3
            L_0x0271:
                r5 = r2
                r7 = r5
            L_0x0273:
                java.lang.String r1 = "Cached Files - Timed out making request (%s)"
                java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x02a4 }
                java.lang.String r9 = r14.url     // Catch:{ all -> 0x02a4 }
                r8[r3] = r9     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.StaticMethods.logWarningFormat(r1, r8)     // Catch:{ all -> 0x02a4 }
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                if (r1 == 0) goto L_0x0287
                com.adobe.mobile.RemoteDownload$RemoteDownloadBlock r1 = r14.callback     // Catch:{ all -> 0x02a4 }
                r1.call(r3, r2)     // Catch:{ all -> 0x02a4 }
            L_0x0287:
                if (r7 == 0) goto L_0x028f
                r7.close()     // Catch:{ IOException -> 0x028d }
                goto L_0x028f
            L_0x028d:
                r1 = move-exception
                goto L_0x0298
            L_0x028f:
                if (r5 == 0) goto L_0x0294
                r5.close()     // Catch:{ IOException -> 0x028d }
            L_0x0294:
                r6.disconnect()     // Catch:{ IOException -> 0x028d }
                goto L_0x02a3
            L_0x0298:
                java.lang.Object[] r2 = new java.lang.Object[r4]
                java.lang.String r1 = r1.getLocalizedMessage()
                r2[r3] = r1
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r2)
            L_0x02a3:
                return
            L_0x02a4:
                r1 = move-exception
                r2 = r7
            L_0x02a6:
                if (r2 == 0) goto L_0x02ae
                r2.close()     // Catch:{ IOException -> 0x02ac }
                goto L_0x02ae
            L_0x02ac:
                r2 = move-exception
                goto L_0x02b7
            L_0x02ae:
                if (r5 == 0) goto L_0x02b3
                r5.close()     // Catch:{ IOException -> 0x02ac }
            L_0x02b3:
                r6.disconnect()     // Catch:{ IOException -> 0x02ac }
                goto L_0x02c2
            L_0x02b7:
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.String r2 = r2.getLocalizedMessage()
                r4[r3] = r2
                com.adobe.mobile.StaticMethods.logWarningFormat(r0, r4)
            L_0x02c2:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.RemoteDownload.DownloadFileTask.run():void");
        }

        protected static HttpURLConnection requestConnect(String str) {
            try {
                return (HttpURLConnection) new URL(str).openConnection();
            } catch (Exception e) {
                StaticMethods.logErrorFormat("Cached Files - Exception opening URL(%s)", e.getLocalizedMessage());
                return null;
            }
        }
    }
}
