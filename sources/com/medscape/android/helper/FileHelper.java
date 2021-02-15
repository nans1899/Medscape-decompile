package com.medscape.android.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.parser.model.Article;
import com.medscape.android.provider.SharedPreferenceProvider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileHelper {
    public static final int BUFFER_SIZE = 20480;

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFileAsString(java.io.File r4) throws java.io.IOException {
        /*
            long r0 = r4.length()
            int r1 = (int) r0
            byte[] r0 = new byte[r1]
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0026 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0026 }
            r3.<init>(r4)     // Catch:{ all -> 0x0026 }
            r2.<init>(r3)     // Catch:{ all -> 0x0026 }
            r2.read(r0)     // Catch:{ all -> 0x0023 }
            r2.close()     // Catch:{ IOException -> 0x001d, Exception -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r4 = move-exception
            r4.printStackTrace()
        L_0x001d:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r0)
            return r4
        L_0x0023:
            r4 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch:{ IOException -> 0x0031, Exception -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0031:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.FileHelper.readFileAsString(java.io.File):java.lang.String");
    }

    public static String getRssFilepath() {
        return getDataDirectory(MedscapeApplication.get()) + "/Medscape/Rss/";
    }

    public static String readInputStreamAsString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    public static File saveToFile(InputStream inputStream, String str, String str2) {
        File file;
        String str3;
        File file2 = new File(getRssFilepath());
        if (!file2.canWrite()) {
            file2.mkdir();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            file = null;
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                str3 = null;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        str3 = byteArrayOutputStream.toString();
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            inputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        if (str3 == null || str3.length() <= 0) {
            return null;
        }
        try {
            File file3 = new File(getRssFilepath() + str2);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file3));
                bufferedWriter.write(str3);
                bufferedWriter.close();
                return file3;
            } catch (Exception e5) {
                e = e5;
                file = file3;
                e.printStackTrace();
                return file;
            }
        } catch (Exception e6) {
            e = e6;
            e.printStackTrace();
            return file;
        }
    }

    public static List<Article> getAllArticlesFromFileUsingJSON(String str, int i) {
        return getAllArticlesFromFileUsingJSON(getRssFilepath() + str, i, -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A[Catch:{ Exception -> 0x0134 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.medscape.android.parser.model.Article> getAllArticlesFromFileUsingJSON(java.lang.String r17, int r18, int r19) {
        /*
            r0 = r18
            r1 = r19
            java.lang.String r2 = "Knowledge"
            java.lang.String r3 = "Latest"
            java.lang.String r4 = "Features"
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.io.File r6 = new java.io.File
            r7 = r17
            r6.<init>(r7)
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0138
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0134 }
            java.io.FileReader r8 = new java.io.FileReader     // Catch:{ Exception -> 0x0134 }
            r8.<init>(r6)     // Catch:{ Exception -> 0x0134 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0134 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0134 }
            r6.<init>()     // Catch:{ Exception -> 0x0134 }
        L_0x002b:
            java.lang.String r8 = r7.readLine()     // Catch:{ Exception -> 0x0134 }
            if (r8 == 0) goto L_0x0035
            r6.append(r8)     // Catch:{ Exception -> 0x0134 }
            goto L_0x002b
        L_0x0035:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x0134 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0134 }
            java.lang.String r6 = r6.trim()     // Catch:{ Exception -> 0x0134 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r6 = "ipadhomepage"
            org.json.JSONArray r6 = r7.getJSONArray(r6)     // Catch:{ Exception -> 0x0134 }
            int r7 = r6.length()     // Catch:{ Exception -> 0x0134 }
            r9 = 0
        L_0x004d:
            if (r9 >= r7) goto L_0x0138
            org.json.JSONObject r10 = r6.getJSONObject(r9)     // Catch:{ Exception -> 0x0134 }
            r11 = 0
            r12 = 7
            java.lang.String r13 = "LegacyInfo"
            java.lang.String r14 = "LegacyFeatured"
            if (r0 != r12) goto L_0x0066
            boolean r12 = r10.isNull(r4)     // Catch:{ Exception -> 0x0134 }
            if (r12 != 0) goto L_0x00a2
            org.json.JSONArray r11 = r10.getJSONArray(r4)     // Catch:{ Exception -> 0x0134 }
            goto L_0x00a2
        L_0x0066:
            r12 = 8
            if (r0 != r12) goto L_0x0084
            boolean r12 = r10.isNull(r13)     // Catch:{ Exception -> 0x0134 }
            if (r12 != 0) goto L_0x0075
            org.json.JSONArray r11 = r10.getJSONArray(r13)     // Catch:{ Exception -> 0x0134 }
            goto L_0x00a2
        L_0x0075:
            boolean r12 = r10.isNull(r3)     // Catch:{ Exception -> 0x0134 }
            if (r12 != 0) goto L_0x00a2
            org.json.JSONArray r11 = r10.getJSONArray(r3)     // Catch:{ Exception -> 0x0134 }
            int r12 = r5.size()     // Catch:{ Exception -> 0x0134 }
            goto L_0x00a3
        L_0x0084:
            r12 = 9
            if (r0 != r12) goto L_0x00a2
            boolean r12 = r10.isNull(r14)     // Catch:{ Exception -> 0x0134 }
            if (r12 != 0) goto L_0x0093
            org.json.JSONArray r11 = r10.getJSONArray(r14)     // Catch:{ Exception -> 0x0134 }
            goto L_0x00a2
        L_0x0093:
            boolean r12 = r10.isNull(r2)     // Catch:{ Exception -> 0x0134 }
            if (r12 != 0) goto L_0x00a2
            org.json.JSONArray r11 = r10.getJSONArray(r2)     // Catch:{ Exception -> 0x0134 }
            int r12 = r5.size()     // Catch:{ Exception -> 0x0134 }
            goto L_0x00a3
        L_0x00a2:
            r12 = 0
        L_0x00a3:
            if (r11 != 0) goto L_0x00a9
        L_0x00a5:
            r16 = r2
            goto L_0x012a
        L_0x00a9:
            boolean r15 = r10.isNull(r14)     // Catch:{ Exception -> 0x0134 }
            if (r15 != 0) goto L_0x00ef
            int r15 = r11.length()     // Catch:{ Exception -> 0x0134 }
            r8 = 2
            if (r15 <= r8) goto L_0x00ef
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ Exception -> 0x0134 }
            r10.<init>()     // Catch:{ Exception -> 0x0134 }
            int r12 = r11.length()     // Catch:{ Exception -> 0x0134 }
            r13 = 0
        L_0x00c0:
            if (r13 >= r12) goto L_0x00d1
            org.json.JSONObject r14 = r11.getJSONObject(r13)     // Catch:{ Exception -> 0x0134 }
            r15 = 1
            com.medscape.android.parser.model.Article r14 = com.medscape.android.parser.model.Article.createFromJSON(r14, r15)     // Catch:{ Exception -> 0x0134 }
            r10.add(r14)     // Catch:{ Exception -> 0x0134 }
            int r13 = r13 + 1
            goto L_0x00c0
        L_0x00d1:
            java.util.Random r11 = new java.util.Random     // Catch:{ Exception -> 0x0134 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0134 }
            r11.<init>(r12)     // Catch:{ Exception -> 0x0134 }
            r12 = 0
        L_0x00db:
            if (r12 >= r8) goto L_0x00a5
            int r13 = r10.size()     // Catch:{ Exception -> 0x0134 }
            int r13 = r11.nextInt(r13)     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r13 = r10.remove(r13)     // Catch:{ Exception -> 0x0134 }
            r5.add(r12, r13)     // Catch:{ Exception -> 0x0134 }
            int r12 = r12 + 1
            goto L_0x00db
        L_0x00ef:
            r15 = 1
            int r8 = r11.length()     // Catch:{ Exception -> 0x0134 }
            r15 = 0
        L_0x00f5:
            if (r15 >= r8) goto L_0x00a5
            r0 = -1
            if (r1 == r0) goto L_0x0100
            int r0 = r5.size()     // Catch:{ Exception -> 0x0134 }
            if (r0 >= r1) goto L_0x00a5
        L_0x0100:
            int r0 = r12 + r15
            org.json.JSONObject r1 = r11.getJSONObject(r15)     // Catch:{ Exception -> 0x0134 }
            boolean r16 = r10.isNull(r14)     // Catch:{ Exception -> 0x0134 }
            if (r16 == 0) goto L_0x0117
            boolean r16 = r10.isNull(r13)     // Catch:{ Exception -> 0x0134 }
            if (r16 != 0) goto L_0x0113
            goto L_0x0117
        L_0x0113:
            r16 = r2
            r2 = 0
            goto L_0x011a
        L_0x0117:
            r16 = r2
            r2 = 1
        L_0x011a:
            com.medscape.android.parser.model.Article r1 = com.medscape.android.parser.model.Article.createFromJSON(r1, r2)     // Catch:{ Exception -> 0x0134 }
            r5.add(r0, r1)     // Catch:{ Exception -> 0x0134 }
            int r15 = r15 + 1
            r0 = r18
            r1 = r19
            r2 = r16
            goto L_0x00f5
        L_0x012a:
            int r9 = r9 + 1
            r0 = r18
            r1 = r19
            r2 = r16
            goto L_0x004d
        L_0x0134:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0138:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.FileHelper.getAllArticlesFromFileUsingJSON(java.lang.String, int, int):java.util.List");
    }

    public static String getFileName(int i, String str) {
        if (i == 7) {
            return "carouselspecialties-" + str + ".json";
        } else if (i == 8) {
            return "new-" + str + ".json";
        } else if (i != 9) {
            return null;
        } else {
            return "cme-" + str + ".json";
        }
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return file.delete();
    }

    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String file2 : list) {
                if (!deleteDir(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e A[SYNTHETIC, Splitter:B:21:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[SYNTHETIC, Splitter:B:32:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0053=Splitter:B:24:0x0053, B:18:0x0049=Splitter:B:18:0x0049} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveToFile(java.io.InputStream r4, java.io.File r5, java.lang.String r6) {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r4, r2)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r3.<init>()     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.lang.String r5 = r5.getPath()     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r3.append(r5)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.lang.String r5 = "/"
            r3.append(r5)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r3.append(r6)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.lang.String r5 = r3.toString()     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0052, Exception -> 0x0048 }
            byte[] r4 = new byte[r2]     // Catch:{ IOException -> 0x0043, Exception -> 0x0040, all -> 0x003d }
            r6 = 0
            r0 = 0
        L_0x002e:
            r3 = -1
            if (r0 == r3) goto L_0x0039
            r5.write(r4, r6, r0)     // Catch:{ IOException -> 0x0043, Exception -> 0x0040, all -> 0x003d }
            int r0 = r1.read(r4, r6, r2)     // Catch:{ IOException -> 0x0043, Exception -> 0x0040, all -> 0x003d }
            goto L_0x002e
        L_0x0039:
            r5.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x003d:
            r4 = move-exception
            r0 = r5
            goto L_0x0061
        L_0x0040:
            r4 = move-exception
            r0 = r5
            goto L_0x0049
        L_0x0043:
            r4 = move-exception
            r0 = r5
            goto L_0x0053
        L_0x0046:
            r4 = move-exception
            goto L_0x0061
        L_0x0048:
            r4 = move-exception
        L_0x0049:
            r4.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x0052:
            r4 = move-exception
        L_0x0053:
            r4.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0060:
            return
        L_0x0061:
            if (r0 == 0) goto L_0x006b
            r0.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r5 = move-exception
            r5.printStackTrace()
        L_0x006b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.FileHelper.saveToFile(java.io.InputStream, java.io.File, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFiles(java.io.File r7, java.io.File r8) throws java.io.IOException {
        /*
            boolean r0 = r7.exists()
            java.lang.String r1 = "."
            if (r0 == 0) goto L_0x00ea
            boolean r0 = r7.canRead()
            if (r0 == 0) goto L_0x00cc
            boolean r0 = r7.isDirectory()
            r2 = 0
            if (r0 == 0) goto L_0x005b
            boolean r0 = r8.exists()
            if (r0 != 0) goto L_0x0040
            boolean r0 = r8.mkdirs()
            if (r0 == 0) goto L_0x0022
            goto L_0x0040
        L_0x0022:
            java.io.IOException r7 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "copyFiles: Could not create direcotry: "
            r0.append(r2)
            java.lang.String r8 = r8.getAbsolutePath()
            r0.append(r8)
            r0.append(r1)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        L_0x0040:
            java.lang.String[] r0 = r7.list()
        L_0x0044:
            int r1 = r0.length
            if (r2 >= r1) goto L_0x007a
            java.io.File r1 = new java.io.File
            r3 = r0[r2]
            r1.<init>(r8, r3)
            java.io.File r3 = new java.io.File
            r4 = r0[r2]
            r3.<init>(r7, r4)
            copyFiles(r3, r1)
            int r2 = r2 + 1
            goto L_0x0044
        L_0x005b:
            r0 = 20480(0x5000, float:2.8699E-41)
            byte[] r0 = new byte[r0]
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x008a, all -> 0x0087 }
            r4.<init>(r7)     // Catch:{ IOException -> 0x008a, all -> 0x0087 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0083, all -> 0x007f }
            r5.<init>(r8)     // Catch:{ IOException -> 0x0083, all -> 0x007f }
        L_0x006a:
            int r3 = r4.read(r0)     // Catch:{ IOException -> 0x007d, all -> 0x007b }
            if (r3 < 0) goto L_0x0074
            r5.write(r0, r2, r3)     // Catch:{ IOException -> 0x007d, all -> 0x007b }
            goto L_0x006a
        L_0x0074:
            r4.close()
            r5.close()
        L_0x007a:
            return
        L_0x007b:
            r7 = move-exception
            goto L_0x0081
        L_0x007d:
            r0 = move-exception
            goto L_0x0085
        L_0x007f:
            r7 = move-exception
            r5 = r3
        L_0x0081:
            r3 = r4
            goto L_0x00c1
        L_0x0083:
            r0 = move-exception
            r5 = r3
        L_0x0085:
            r3 = r4
            goto L_0x008c
        L_0x0087:
            r7 = move-exception
            r5 = r3
            goto L_0x00c1
        L_0x008a:
            r0 = move-exception
            r5 = r3
        L_0x008c:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x00c0 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            r4.<init>()     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "copyFiles: Unable to copy file: "
            r4.append(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ all -> 0x00c0 }
            r4.append(r7)     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = "to"
            r4.append(r7)     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = r8.getAbsolutePath()     // Catch:{ all -> 0x00c0 }
            r4.append(r7)     // Catch:{ all -> 0x00c0 }
            r4.append(r1)     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x00c0 }
            r2.<init>(r7)     // Catch:{ all -> 0x00c0 }
            r2.initCause(r0)     // Catch:{ all -> 0x00c0 }
            java.lang.StackTraceElement[] r7 = r0.getStackTrace()     // Catch:{ all -> 0x00c0 }
            r2.setStackTrace(r7)     // Catch:{ all -> 0x00c0 }
            throw r2     // Catch:{ all -> 0x00c0 }
        L_0x00c0:
            r7 = move-exception
        L_0x00c1:
            if (r3 == 0) goto L_0x00c6
            r3.close()
        L_0x00c6:
            if (r5 == 0) goto L_0x00cb
            r5.close()
        L_0x00cb:
            throw r7
        L_0x00cc:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "copyFiles: No right to source: "
            r0.append(r2)
            java.lang.String r7 = r7.getAbsolutePath()
            r0.append(r7)
            r0.append(r1)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7)
            throw r8
        L_0x00ea:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "copyFiles: Can not find source: "
            r0.append(r2)
            java.lang.String r7 = r7.getAbsolutePath()
            r0.append(r7)
            r0.append(r1)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.FileHelper.copyFiles(java.io.File, java.io.File):void");
    }

    public static boolean moveFile(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        return file.renameTo(file2);
    }

    public static String getDataDirectory(Context context) {
        if (!(ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
            return context.getFilesDir().getPath();
        }
        if (!new File(Environment.getExternalStorageDirectory() + "/Medscape/").exists()) {
            Log.e("_ER", context.getFilesDir().getPath());
            return context.getFilesDir().getPath();
        }
        int i = SharedPreferenceProvider.get().get(Constants.COPY_STATUS, (int) Constants.COPY_STATUS_UNKNOWN);
        if (i == 924603 || i == 924601) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return context.getFilesDir().getPath();
    }
}
