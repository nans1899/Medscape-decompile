package com.epapyrus.plugpdf.core;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class PlugPDFUtility {
    public static void setRotationLock(Activity activity, boolean z) {
        if (z) {
            Toast.makeText(activity, ResourceManager.getStringId(activity, "text_rotate_lock"), 0).show();
            Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int rotation = defaultDisplay.getRotation();
            if (rotation != 0) {
                if (rotation != 1) {
                    if (rotation != 2) {
                        if (rotation == 3) {
                            if (point.x > point.y) {
                                activity.setRequestedOrientation(8);
                            } else {
                                activity.setRequestedOrientation(1);
                            }
                        }
                    } else if (point.x > point.y) {
                        activity.setRequestedOrientation(8);
                    } else {
                        activity.setRequestedOrientation(9);
                    }
                } else if (point.x > point.y) {
                    activity.setRequestedOrientation(0);
                } else {
                    activity.setRequestedOrientation(9);
                }
            } else if (point.x > point.y) {
                activity.setRequestedOrientation(0);
            } else {
                activity.setRequestedOrientation(1);
            }
        } else {
            Toast.makeText(activity, ResourceManager.getStringId(activity, "text_rotate_unlock"), 0).show();
            activity.setRequestedOrientation(4);
        }
    }

    public static void setDisplayBrightness(Window window, float f) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = f;
        window.setAttributes(attributes);
    }

    public static String deployAssetFontResource(Context context) throws Exception {
        String externalStorageState = Environment.getExternalStorageState();
        if ("unmounted".equals(externalStorageState)) {
            throw new IOException("external storage unmounted");
        } else if (!"mounted_ro".equals(externalStorageState)) {
            PackageManager packageManager = context.getPackageManager();
            try {
                String str = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
                AssetManager assets = context.getAssets();
                File externalCacheDir = context.getExternalCacheDir();
                File file = new File(externalCacheDir, File.separator + ContentParser.RESOURCE);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file.getAbsolutePath() + File.separator + "Fonts");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                copyAssets(assets, "Fonts", file.getAbsolutePath() + File.separator + "Fonts");
                return file.getAbsolutePath();
            } catch (PackageManager.NameNotFoundException unused) {
                throw new Exception("application name is null");
            }
        } else {
            throw new IOException("external storage mounted read only");
        }
    }

    private static void copyAssets(AssetManager assetManager, String str, String str2) throws IOException {
        for (String str3 : assetManager.list(str)) {
            InputStream open = assetManager.open(str + File.separator + str3);
            String str4 = str2 + File.separator + str3;
            File file = new File(str2);
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            open.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public static boolean IsNetConnected(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1 || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo == null) {
                return false;
            }
            boolean z = false;
            for (NetworkInfo state : allNetworkInfo) {
                if (state.getState() == NetworkInfo.State.CONNECTED) {
                    z = true;
                }
            }
            return z;
        } catch (Exception e) {
            Log.d("PlugPDF", e.getMessage());
            return false;
        }
    }

    public static String getRandomFilePath(Context context, String str) {
        return context.getCacheDir().getAbsolutePath() + File.separator + UUID.randomUUID() + str;
    }

    public static float convertDipToPx(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
