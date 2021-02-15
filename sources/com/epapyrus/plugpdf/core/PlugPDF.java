package com.epapyrus.plugpdf.core;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.epapyrus.plugpdf.core.LicenseInfo;
import com.epapyrus.plugpdf.core.PlugPDFException;
import com.epapyrus.plugpdf.core.annotation.menu.AnnotMenuManager;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;

public final class PlugPDF {
    private static Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
    private static LicenseInfo mLicenseInfo;
    /* access modifiers changed from: private */
    public static Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    private static boolean mUpdateCheckEnabled = true;

    public static void init(Context context, String str) throws PlugPDFException.InvalidLicense {
        System.loadLibrary("plugpdf");
        LicenseInfo validateLicense = PDFDocument.validateLicense(str, context.getPackageName(), sdkMajorVersion());
        mLicenseInfo = validateLicense;
        LicenseInfo.VerificationResult verificationResult = validateLicense.getVerificationResult();
        if (verificationResult == LicenseInfo.VerificationResult.INVALID) {
            throw new PlugPDFException.InvalidLicense();
        } else if (verificationResult == LicenseInfo.VerificationResult.WRONG_PRODUCT_VERSION) {
            throw new PlugPDFException.LicenseWrongProductVersion();
        } else if (verificationResult == LicenseInfo.VerificationResult.TRIAL_TIME_OUT) {
            throw new PlugPDFException.LicenseTrialTimeOut();
        } else if (verificationResult == LicenseInfo.VerificationResult.UNUSABLE_SDK_VERSION) {
            throw new PlugPDFException.LicenseUnusableSDKVersion();
        } else if (verificationResult == LicenseInfo.VerificationResult.UNUSABLE_OS) {
            throw new PlugPDFException.LicenseUnusableOS();
        } else if (verificationResult != LicenseInfo.VerificationResult.MISMATCH_APP_ID) {
            CopyAssets(context);
            PDFDocument.setCMapPath(context.getExternalCacheDir().getAbsolutePath() + "/cmap");
            PDFDocument.pdfInit(context);
            AnnotMenuManager.init(context);
            AnnotSetting.init(context);
            if (Build.VERSION.SDK_INT >= 14) {
                PDFDocument.setFontMap("*=Roboto-Regular.ttf");
            }
            Log.i("PlugPDF", "[INFO] PlugPDF inizialized. Version: " + Version.getVersionName());
        } else {
            throw new PlugPDFException.LicenseMismatchAppID();
        }
    }

    private static void CopyAssets(Context context) {
        String[] strArr;
        AssetManager assets = context.getAssets();
        String str = null;
        try {
            strArr = assets.list("cmap");
        } catch (IOException e) {
            Log.e(Constants.WBMDDrugKeyTag, e.getMessage());
            strArr = null;
        }
        for (int i = 0; i < strArr.length; i++) {
            try {
                InputStream open = assets.open("cmap/" + strArr[i]);
                if (Environment.getExternalStorageState().equals("mounted")) {
                    str = context.getExternalCacheDir().getAbsolutePath() + File.separator + "cmap/";
                } else {
                    Environment.getExternalStorageDirectory();
                }
                File file = new File(str);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(context.getExternalCacheDir().getAbsolutePath() + File.separator + "cmap/" + strArr[i]);
                copyFile(open, fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e2) {
                Log.e(Constants.WBMDDrugKeyTag, e2.getMessage());
            }
        }
    }

    private static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void enableUncaughtExceptionHandler() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication());
    }

    public static String deployAssetFontResource(Context context) throws Exception {
        String deployAssetFontResource = PlugPDFUtility.deployAssetFontResource(context);
        PDFDocument.setFontPath(deployAssetFontResource + File.separator + "Fonts");
        return deployAssetFontResource;
    }

    public static LicenseInfo getLicenseInfo() {
        return mLicenseInfo;
    }

    public static String getVersionName() {
        return Version.getVersionName();
    }

    private static String sdkMajorVersion() {
        return Integer.toString(Version.getMajorVersion());
    }

    private static class UncaughtExceptionHandlerApplication implements Thread.UncaughtExceptionHandler {
        private UncaughtExceptionHandlerApplication() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            if (PropertyManager.isEnableLog() && "mounted".equals(Environment.getExternalStorageState())) {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                try {
                    FileWriter fileWriter = new FileWriter(externalStorageDirectory.getAbsolutePath() + File.separator + "PlugPDF.log");
                    fileWriter.write(Log.getStackTraceString(th));
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            PlugPDF.mUncaughtExceptionHandler.uncaughtException(thread, th);
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    public static boolean isUpdateCheckEnabled() {
        return mUpdateCheckEnabled;
    }

    public static void setUpdateCheckEnabled(boolean z) {
        mUpdateCheckEnabled = z;
    }

    public static Bitmap.Config bitmapConfig() {
        return mBitmapConfig;
    }

    public static void setBitmapConfig(Bitmap.Config config) {
        mBitmapConfig = config;
    }
}
