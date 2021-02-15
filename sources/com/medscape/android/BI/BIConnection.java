package com.medscape.android.BI;

import com.medscape.android.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

public class BIConnection {
    private static final String TAG = "BIConnection";

    public static boolean sendInfo(String str, String str2) {
        if (str == null) {
            return false;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("Cookie", str2);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.connect();
            byte[] bArr = new byte[4096];
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(httpURLConnection.getContentLength());
            while (true) {
                int read = inputStream.read(bArr, 0, 4096);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return false;
        } catch (SocketTimeoutException e3) {
            e3.printStackTrace();
            return false;
        } catch (UnknownHostException e4) {
            e4.printStackTrace();
            return false;
        } catch (IOException e5) {
            e5.printStackTrace();
            return false;
        } catch (Exception e6) {
            e6.printStackTrace();
            return false;
        }
    }
}
