package com.tapstream.sdk;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static <T> T getOrDefault(T t, T t2) {
        return t != null ? t : t2;
    }

    public static byte[] readFully(InputStream inputStream) throws IOException {
        int read;
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        do {
            read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
                continue;
            }
        } while (read != -1);
        return byteArrayOutputStream.toByteArray();
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }
}
