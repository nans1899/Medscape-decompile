package com.medscape.android.util;

import android.content.Context;
import android.util.Log;
import com.medscape.android.helper.FileHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AutomationHelper {
    private static AutomationHelper mInstance;
    public boolean isInUITestMode = false;
    public boolean isOffline = false;
    public int stubbedLegalVer = -1;
    public String stubbedVerXmlResponse;

    public static AutomationHelper getInstance() {
        if (mInstance == null) {
            mInstance = new AutomationHelper();
        }
        return mInstance;
    }

    public static boolean copyFilesToMedscapeTemp(Context context, int i, String str) {
        InputStream openRawResource = context.getResources().openRawResource(i);
        File file = new File(FileHelper.getDataDirectory(context) + "/Medscape/temp");
        File file2 = new File(file.getPath() + "/" + str);
        file.mkdirs();
        if (file2.exists()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openRawResource.read(bArr, 0, 1024);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    openRawResource.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            Log.i("Test", "Setup::copyResources - " + e.getMessage());
            return false;
        } catch (IOException e2) {
            Log.i("Test", "Setup::copyResources - " + e2.getMessage());
            return false;
        }
    }
}
