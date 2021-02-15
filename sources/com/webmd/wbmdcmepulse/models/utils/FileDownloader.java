package com.webmd.wbmdcmepulse.models.utils;

import android.os.Build;
import android.os.Environment;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileDownloader {
    private static final String TAG = FileDownloader.class.getSimpleName();

    public void downloadFile(String str, String str2) throws IOException {
        URL url = new URL(str);
        url.openConnection().getContentLength();
        if (!saveStream(new File(getPublicDirectory()), str2, new DataInputStream(url.openStream()))) {
            throw new IOException("Could not save file");
        }
    }

    public boolean saveStream(File file, String str, InputStream inputStream) {
        try {
            if (!file.exists()) {
                file.mkdir();
            }
            if (str == null || inputStream == null) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file.getPath() + "/" + str));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    fileOutputStream.close();
                    System.out.println("File copied.");
                    return true;
                }
            }
        } catch (Exception e) {
            String str2 = TAG;
            Trace.e(str2, "Error saving file" + e);
            return false;
        }
    }

    public boolean fileExists(String str) {
        File file = new File(getPublicDirectory());
        if (!file.exists()) {
            file.mkdir();
            return false;
        }
        if (!Extensions.isStringNullOrEmpty(str)) {
            if (new File(getPublicDirectory() + "/" + str).exists()) {
                return true;
            }
        }
        return false;
    }

    public File getDownloadedFile(String str) {
        return new File(getPublicDirectory(), str);
    }

    public String getPublicDirectory() {
        if (Build.VERSION.SDK_INT >= 19) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();
        }
        return Environment.getExternalStorageDirectory() + "/Documents";
    }

    public boolean deleteFile(String str) {
        if (Extensions.isStringNullOrEmpty(str)) {
            return false;
        }
        File file = new File(getPublicDirectory() + "/" + str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
