package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.TypedValue;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.ResourceFile;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBResourceFile;
import com.wbmd.qxcalculator.util.CrashLogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileHelper {
    private static String COMMON_FILE_PREFIX = "common_file";
    private static String PATH_COMMON_FILES_FOLDER;
    private static String PATH_DATA_STORAGE_FOLDER;
    private static String PATH_RESOURCES_FOLDER;
    private static FileHelper mInstance;
    private Long activeUserId;
    private Context context;

    public static FileHelper getInstance() {
        if (UserManager.getInstance().getActiveUserId() != null && !UserManager.getInstance().getActiveUserId().equals(mInstance.activeUserId)) {
            mInstance.initializeFolderConstants();
            mInstance.activeUserId = UserManager.getInstance().getActiveUserId();
        }
        return mInstance;
    }

    public static void initializeWithContext(Context context2) {
        FileHelper fileHelper = new FileHelper();
        mInstance = fileHelper;
        fileHelper.context = context2;
    }

    public void initializeFolderConstants() {
        PATH_DATA_STORAGE_FOLDER = this.context.getFilesDir().getAbsolutePath() + File.separator + "calculate_data" + File.separator + UserManager.getInstance().getActiveUserId() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(PATH_DATA_STORAGE_FOLDER);
        sb.append("resources");
        sb.append(File.separator);
        PATH_RESOURCES_FOLDER = sb.toString();
        PATH_COMMON_FILES_FOLDER = PATH_RESOURCES_FOLDER + "common" + File.separator;
        File file = new File(PATH_COMMON_FILES_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private FileHelper() {
    }

    public File getTempFileWithName(String str) {
        try {
            return new File(this.context.getCacheDir(), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File createRandomFileForTempPdf() {
        try {
            Date date = new Date();
            File cacheDir = this.context.getCacheDir();
            return new File(cacheDir, "tmp" + date.getTime() + ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFileForResourceFile(DBResourceFile dBResourceFile) {
        File file;
        if (dBResourceFile.getContentItemIdentifier().contains(COMMON_FILE_PREFIX)) {
            file = new File(PATH_COMMON_FILES_FOLDER + dBResourceFile.getName());
        } else {
            file = new File(PATH_RESOURCES_FOLDER + dBResourceFile.getContentItemIdentifier() + File.separator + dBResourceFile.getName());
        }
        return file.delete();
    }

    public boolean deleteFolderForContentItem(DBContentItem dBContentItem) {
        if (dBContentItem.getIdentifier().contains(COMMON_FILE_PREFIX)) {
            return true;
        }
        return new File(PATH_RESOURCES_FOLDER + dBContentItem.getIdentifier()).delete();
    }

    public void deleteFile(File file) {
        deleteFile(file, true);
    }

    public void deleteFile(File file, boolean z) {
        if (z) {
            deleteFileRecursively(file);
        } else {
            file.delete();
        }
    }

    private void deleteFileRecursively(File file) {
        File[] listFiles = file.isDirectory() ? file.listFiles() : null;
        if (listFiles != null) {
            for (File deleteFileRecursively : listFiles) {
                deleteFileRecursively(deleteFileRecursively);
            }
        }
        file.delete();
    }

    public void clearCachedFiles() {
        File[] listFiles = this.context.getCacheDir().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!(file == null || file.getName() == null || !file.getName().contains("master_zip.zip"))) {
                    deleteFile(file, true);
                }
            }
        }
    }

    public void deleteMasterZip() {
        deleteFile(getMasterZipCacheFile(), true);
    }

    public void reinitUserDataFolders() {
        deleteFile(new File(PATH_DATA_STORAGE_FOLDER), true);
        File file = new File(PATH_COMMON_FILES_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public String getResourceFolderPath() {
        return PATH_RESOURCES_FOLDER;
    }

    public String getResourceFolderPathForContentItem(ContentItem contentItem) {
        return PATH_RESOURCES_FOLDER + contentItem.identifier + File.separator;
    }

    public String getResourceFolderPathForContentItemIdentifier(String str) {
        return PATH_RESOURCES_FOLDER + str + File.separator;
    }

    public File getMasterZipCacheFile() {
        return new File(this.context.getCacheDir().getAbsolutePath() + File.separator + "master_zip.zip");
    }

    public boolean unzipMasterFileZip() {
        File masterZipCacheFile = getMasterZipCacheFile();
        File file = new File(PATH_RESOURCES_FOLDER);
        if (!file.exists()) {
            file.mkdir();
        }
        boolean unZip = unZip(masterZipCacheFile, file);
        if (unZip) {
            for (File file2 : new ArrayList(Arrays.asList(file.listFiles()))) {
                if (file2.getName().contains("common_file_")) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null) {
                        for (File file3 : listFiles) {
                            file3.renameTo(new File(PATH_COMMON_FILES_FOLDER + file3.getName()));
                        }
                    }
                    deleteFile(file2, true);
                }
            }
        }
        return unZip;
    }

    public BitmapDrawable getScaledDrawable(DBContentItem dBContentItem, String str, double d) {
        ArrayList arrayList = new ArrayList();
        for (DBResourceFile resourceFile : dBContentItem.getResourceFiles()) {
            arrayList.add(new ResourceFile(resourceFile));
        }
        return getScaledDrawable(dBContentItem.getIdentifier(), arrayList, str, d);
    }

    public BitmapDrawable getScaledDrawable(ContentItem contentItem, String str, double d) {
        return getScaledDrawable(contentItem.identifier, contentItem.resourceFiles, str, d);
    }

    public BitmapDrawable getScaledDrawable(String str, List<ResourceFile> list, String str2, double d) {
        ResourceFile resourceFileForScaledDrawable = getResourceFileForScaledDrawable(str, list, str2, d);
        if (resourceFileForScaledDrawable == null) {
            return null;
        }
        double d2 = 1.0d;
        if (resourceFileForScaledDrawable.scaleFactor != null) {
            d2 = resourceFileForScaledDrawable.scaleFactor.doubleValue();
        }
        String str3 = getInstance().getResourceFolderPathForContentItemIdentifier(str) + resourceFileForScaledDrawable.name;
        CrashLogger.getInstance().leaveBreadcrumb("imagePath: " + str3);
        Log.d("API", "chosen candidate path " + str3);
        if (resourceFileForScaledDrawable.dipWidth == null || resourceFileForScaledDrawable.dipHeight == null) {
            Bitmap decodeFile = BitmapFactory.decodeFile(str3);
            if (decodeFile == null) {
                CrashLogger.getInstance().leaveBreadcrumb("b no bitmap created");
                File file = new File(str3);
                CrashLogger.getInstance().leaveBreadcrumb("b does file exist? " + file.exists());
                return null;
            }
            int applyDimension = (int) TypedValue.applyDimension(1, (float) (((double) decodeFile.getWidth()) / d2), this.context.getResources().getDisplayMetrics());
            int applyDimension2 = (int) TypedValue.applyDimension(1, (float) (((double) decodeFile.getHeight()) / d2), this.context.getResources().getDisplayMetrics());
            CrashLogger.getInstance().leaveBreadcrumb("B desired width " + applyDimension + "; height " + applyDimension2);
            return new BitmapDrawable(this.context.getResources(), Bitmap.createScaledBitmap(decodeFile, applyDimension, applyDimension2, true));
        }
        Bitmap decodeFile2 = BitmapFactory.decodeFile(str3);
        if (decodeFile2 == null) {
            CrashLogger.getInstance().leaveBreadcrumb("a no bitmap created");
            File file2 = new File(str3);
            CrashLogger.getInstance().leaveBreadcrumb("does file exist? " + file2.exists());
            return null;
        }
        int applyDimension3 = (int) TypedValue.applyDimension(1, resourceFileForScaledDrawable.dipWidth.floatValue(), this.context.getResources().getDisplayMetrics());
        int applyDimension4 = (int) TypedValue.applyDimension(1, resourceFileForScaledDrawable.dipHeight.floatValue(), this.context.getResources().getDisplayMetrics());
        CrashLogger.getInstance().leaveBreadcrumb("A desired width " + applyDimension3 + "; height " + applyDimension4);
        return new BitmapDrawable(this.context.getResources(), Bitmap.createScaledBitmap(decodeFile2, applyDimension3, applyDimension4, true));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.wbmd.qxcalculator.model.contentItems.common.ResourceFile} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.wbmd.qxcalculator.model.contentItems.common.ResourceFile getResourceFileForScaledDrawable(java.lang.String r10, java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r11, java.lang.String r12, double r13) {
        /*
            r9 = this;
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r11 = r11.iterator()
        L_0x0009:
            boolean r0 = r11.hasNext()
            java.lang.String r1 = "API"
            if (r0 == 0) goto L_0x0049
            java.lang.Object r0 = r11.next()
            com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r0 = (com.wbmd.qxcalculator.model.contentItems.common.ResourceFile) r0
            java.lang.String r2 = r0.name
            boolean r2 = r2.equalsIgnoreCase(r12)
            if (r2 != 0) goto L_0x002f
            java.lang.String r2 = r0.baseName
            if (r2 == 0) goto L_0x0009
            java.lang.String r2 = r0.baseName
            java.lang.String r3 = r12.toLowerCase()
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x0009
        L_0x002f:
            r10.add(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "adding candidate "
            r2.append(r3)
            java.lang.String r0 = r0.srcId
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.d(r1, r0)
            goto L_0x0009
        L_0x0049:
            r11 = 0
            r2 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            int r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r0 <= 0) goto L_0x0090
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r12 = r10.iterator()
            r4 = r2
        L_0x005e:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0089
            java.lang.Object r0 = r12.next()
            com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r0 = (com.wbmd.qxcalculator.model.contentItems.common.ResourceFile) r0
            java.lang.Double r6 = r0.aspectRatio
            if (r6 == 0) goto L_0x005e
            java.lang.Double r6 = r0.aspectRatio
            double r6 = r6.doubleValue()
            double r6 = r6 - r13
            double r6 = java.lang.Math.abs(r6)
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x0081
            r11.clear()
            r4 = r6
        L_0x0081:
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 != 0) goto L_0x005e
            r11.add(r0)
            goto L_0x005e
        L_0x0089:
            boolean r12 = r11.isEmpty()
            if (r12 != 0) goto L_0x0090
            r10 = r11
        L_0x0090:
            android.content.Context r11 = r9.context
            android.content.res.Resources r11 = r11.getResources()
            android.util.DisplayMetrics r11 = r11.getDisplayMetrics()
            float r11 = r11.density
            double r11 = (double) r11
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "my screen density "
            r13.append(r14)
            r13.append(r11)
            java.lang.String r13 = r13.toString()
            android.util.Log.d(r1, r13)
            java.util.Iterator r13 = r10.iterator()
            r14 = 0
            r0 = r14
        L_0x00b7:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x00f4
            java.lang.Object r4 = r13.next()
            com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r4 = (com.wbmd.qxcalculator.model.contentItems.common.ResourceFile) r4
            java.lang.Double r5 = r4.scaleFactor
            if (r5 == 0) goto L_0x00b7
            java.lang.Double r5 = r4.scaleFactor
            double r5 = r5.doubleValue()
            double r5 = r5 - r11
            double r5 = java.lang.Math.abs(r5)
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x00b7
            java.lang.Double r0 = r4.scaleFactor
            r0.doubleValue()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "best candidate so far candidate "
            r0.append(r2)
            java.lang.String r2 = r4.srcId
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            r0 = r4
            r2 = r5
            goto L_0x00b7
        L_0x00f4:
            if (r0 != 0) goto L_0x0106
            boolean r11 = r10.isEmpty()
            if (r11 != 0) goto L_0x0105
            r11 = 0
            java.lang.Object r10 = r10.get(r11)
            r0 = r10
            com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r0 = (com.wbmd.qxcalculator.model.contentItems.common.ResourceFile) r0
            goto L_0x0106
        L_0x0105:
            return r14
        L_0x0106:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "chosen candidate "
            r10.append(r11)
            java.lang.String r11 = r0.srcId
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r1, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.FileHelper.getResourceFileForScaledDrawable(java.lang.String, java.util.List, java.lang.String, double):com.wbmd.qxcalculator.model.contentItems.common.ResourceFile");
    }

    public int dpToPx(int i) {
        return (int) (((float) i) * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(int i) {
        return (int) (((float) i) / Resources.getSystem().getDisplayMetrics().density);
    }

    public boolean renameOldResourceFolderPath(String str) {
        if (str == null) {
            return false;
        }
        return directoryMove(new File(str), new File(PATH_RESOURCES_FOLDER));
    }

    public void copyHardCodedResourcesFromAssetsToUserFolder() throws IOException {
        String str = this.context.getCacheDir() + File.separator + AppConfiguration.getInstance().resourcesZipFileName() + ".zip";
        File file = new File(str);
        copyRawResourceItemToDisk("calculate_data/" + AppConfiguration.getInstance().resourcesZipFileName() + ".zip", file);
        if (unZip(new File(str), new File(PATH_RESOURCES_FOLDER))) {
            for (File file2 : new ArrayList(Arrays.asList(new File(PATH_RESOURCES_FOLDER).listFiles()))) {
                if (file2.getName().contains("common_file_")) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null) {
                        for (File file3 : listFiles) {
                            file3.renameTo(new File(PATH_COMMON_FILES_FOLDER + file3.getName()));
                        }
                    }
                    deleteFile(file2, true);
                }
            }
        }
        deleteFile(file, true);
    }

    public APIResponse loadHardCodedRegistrationData() {
        APIResponse aPIResponse = new APIResponse();
        try {
            APIParser.parseApiResponse(this.context.getAssets().open("calculate_data/registration_data.json"), aPIResponse);
            aPIResponse.httpStatusCode = 200;
            return aPIResponse;
        } catch (Exception e) {
            CrashLogger.getInstance().logHandledException(e);
            CrashLogger.getInstance().leaveBreadcrumb("loadHardCodedRegistrationData failed");
            e.printStackTrace();
            return aPIResponse;
        }
    }

    public APIResponse loadHardCodedApiResponse() {
        APIResponse aPIResponse = new APIResponse();
        try {
            AssetManager assets = this.context.getAssets();
            APIParser.parseApiResponse(assets.open("calculate_data/" + AppConfiguration.getInstance().contentJSONFileName() + ".json"), aPIResponse);
            return aPIResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return aPIResponse;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyRawResourceItemToDisk(java.lang.String r6, java.io.File r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = r5.context     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            java.io.InputStream r1 = r1.open(r6)     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0034, all -> 0x0030 }
            r2.<init>(r7)     // Catch:{ Exception -> 0x0034, all -> 0x0030 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x002e, all -> 0x002c }
        L_0x0014:
            int r0 = r1.read(r7)     // Catch:{ Exception -> 0x002e, all -> 0x002c }
            r3 = -1
            if (r0 == r3) goto L_0x0020
            r3 = 0
            r2.write(r7, r3, r0)     // Catch:{ Exception -> 0x002e, all -> 0x002c }
            goto L_0x0014
        L_0x0020:
            r2.flush()     // Catch:{ Exception -> 0x002e, all -> 0x002c }
            if (r1 == 0) goto L_0x0028
            r1.close()
        L_0x0028:
            r2.close()
            goto L_0x0067
        L_0x002c:
            r6 = move-exception
            goto L_0x0032
        L_0x002e:
            r7 = move-exception
            goto L_0x0036
        L_0x0030:
            r6 = move-exception
            r2 = r0
        L_0x0032:
            r0 = r1
            goto L_0x0069
        L_0x0034:
            r7 = move-exception
            r2 = r0
        L_0x0036:
            r0 = r1
            goto L_0x003d
        L_0x0038:
            r6 = move-exception
            r2 = r0
            goto L_0x0069
        L_0x003b:
            r7 = move-exception
            r2 = r0
        L_0x003d:
            com.wbmd.qxcalculator.util.CrashLogger r1 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0068 }
            r1.logHandledException(r7)     // Catch:{ all -> 0x0068 }
            com.wbmd.qxcalculator.util.CrashLogger r1 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r3.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.String r4 = "copyRawResourceItemToDisk failed: "
            r3.append(r4)     // Catch:{ all -> 0x0068 }
            r3.append(r6)     // Catch:{ all -> 0x0068 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0068 }
            r1.leaveBreadcrumb(r6)     // Catch:{ all -> 0x0068 }
            r7.printStackTrace()     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x0064
            r0.close()
        L_0x0064:
            if (r2 == 0) goto L_0x0067
            goto L_0x0028
        L_0x0067:
            return
        L_0x0068:
            r6 = move-exception
        L_0x0069:
            if (r0 == 0) goto L_0x006e
            r0.close()
        L_0x006e:
            if (r2 == 0) goto L_0x0073
            r2.close()
        L_0x0073:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.FileHelper.copyRawResourceItemToDisk(java.lang.String, java.io.File):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cd A[SYNTHETIC, Splitter:B:45:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d8 A[SYNTHETIC, Splitter:B:51:0x00d8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean unZip(java.io.File r8, java.io.File r9) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            java.util.zip.ZipInputStream r2 = new java.util.zip.ZipInputStream     // Catch:{ Exception -> 0x00a4 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00a4 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00a4 }
            r4.<init>(r8)     // Catch:{ Exception -> 0x00a4 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00a4 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00a4 }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x009f, all -> 0x009c }
        L_0x0015:
            java.util.zip.ZipEntry r3 = r2.getNextEntry()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r3 == 0) goto L_0x0092
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r5 = r3.getName()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r4.<init>(r9, r5)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            boolean r5 = r3.isDirectory()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r5 == 0) goto L_0x002c
            r5 = r4
            goto L_0x0030
        L_0x002c:
            java.io.File r5 = r4.getParentFile()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
        L_0x0030:
            boolean r6 = r5.isDirectory()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r6 != 0) goto L_0x0058
            boolean r6 = r5.mkdirs()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r6 == 0) goto L_0x003d
            goto L_0x0058
        L_0x003d:
            java.io.FileNotFoundException r9 = new java.io.FileNotFoundException     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r1.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r3 = "Failed to ensure directory: "
            r1.append(r3)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r3 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r1.append(r3)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r9.<init>(r1)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            throw r9     // Catch:{ Exception -> 0x009f, all -> 0x009c }
        L_0x0058:
            boolean r3 = r3.isDirectory()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r3 == 0) goto L_0x005f
            goto L_0x0015
        L_0x005f:
            java.lang.String r3 = "API"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r5.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r6 = "unzip "
            r5.append(r6)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r6 = r4.getName()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r5.append(r6)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            android.util.Log.d(r3, r5)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            r3.<init>(r4)     // Catch:{ Exception -> 0x009f, all -> 0x009c }
        L_0x007e:
            int r4 = r2.read(r1)     // Catch:{ all -> 0x008d }
            r5 = -1
            if (r4 == r5) goto L_0x0089
            r3.write(r1, r0, r4)     // Catch:{ all -> 0x008d }
            goto L_0x007e
        L_0x0089:
            r3.close()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            goto L_0x0015
        L_0x008d:
            r9 = move-exception
            r3.close()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            throw r9     // Catch:{ Exception -> 0x009f, all -> 0x009c }
        L_0x0092:
            r2.close()     // Catch:{ Exception -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r8 = move-exception
            r8.printStackTrace()
        L_0x009a:
            r8 = 1
            return r8
        L_0x009c:
            r8 = move-exception
            r1 = r2
            goto L_0x00d6
        L_0x009f:
            r9 = move-exception
            r1 = r2
            goto L_0x00a5
        L_0x00a2:
            r8 = move-exception
            goto L_0x00d6
        L_0x00a4:
            r9 = move-exception
        L_0x00a5:
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x00a2 }
            r2.logHandledException(r9)     // Catch:{ all -> 0x00a2 }
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x00a2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a2 }
            r3.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r4 = "unZip failed: "
            r3.append(r4)     // Catch:{ all -> 0x00a2 }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x00a2 }
            r3.append(r8)     // Catch:{ all -> 0x00a2 }
            java.lang.String r8 = r3.toString()     // Catch:{ all -> 0x00a2 }
            r2.leaveBreadcrumb(r8)     // Catch:{ all -> 0x00a2 }
            r9.printStackTrace()     // Catch:{ all -> 0x00a2 }
            if (r1 == 0) goto L_0x00d5
            r1.close()     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00d5
        L_0x00d1:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00d5:
            return r0
        L_0x00d6:
            if (r1 == 0) goto L_0x00e0
            r1.close()     // Catch:{ Exception -> 0x00dc }
            goto L_0x00e0
        L_0x00dc:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00e0:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.FileHelper.unZip(java.io.File, java.io.File):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (directoryMove(r5, r6) != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        if (r5.renameTo(r6) != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean directoryMove(java.io.File r9, java.io.File r10) {
        /*
            r8 = this;
            boolean r0 = r10.exists()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0011
            boolean r0 = r10.mkdirs()
            if (r0 == 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            r0 = 0
            goto L_0x0012
        L_0x0011:
            r0 = 1
        L_0x0012:
            if (r0 == 0) goto L_0x0061
            java.io.File[] r9 = r9.listFiles()
            if (r9 == 0) goto L_0x0061
            int r3 = r9.length
            r4 = 0
        L_0x001c:
            if (r4 >= r3) goto L_0x0061
            r5 = r9[r4]
            boolean r6 = r5.isDirectory()
            if (r6 == 0) goto L_0x003b
            java.io.File r6 = new java.io.File
            java.lang.String r7 = r5.getName()
            r6.<init>(r10, r7)
            if (r0 == 0) goto L_0x0039
            boolean r0 = r8.directoryMove(r5, r6)
            if (r0 == 0) goto L_0x0039
        L_0x0037:
            r0 = 1
            goto L_0x005e
        L_0x0039:
            r0 = 0
            goto L_0x005e
        L_0x003b:
            java.io.File r6 = new java.io.File
            java.lang.String r7 = r5.getName()
            r6.<init>(r10, r7)
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0055
            if (r0 == 0) goto L_0x0054
            boolean r0 = r6.delete()
            if (r0 == 0) goto L_0x0054
            r0 = 1
            goto L_0x0055
        L_0x0054:
            r0 = 0
        L_0x0055:
            if (r0 == 0) goto L_0x0039
            boolean r0 = r5.renameTo(r6)
            if (r0 == 0) goto L_0x0039
            goto L_0x0037
        L_0x005e:
            int r4 = r4 + 1
            goto L_0x001c
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.FileHelper.directoryMove(java.io.File, java.io.File):boolean");
    }
}
