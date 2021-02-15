package com.medscape.android.util;

import android.content.Context;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.helper.FileHelper;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String str, boolean z, Context context) {
        if (str != null) {
            if (!str.startsWith("http")) {
                str = "http://" + str;
            }
            if (z) {
                jObj = postHttpUrlResponseWithCookies(str, Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, ""), "", HttpConnection.FORM_URL_ENCODED);
            } else {
                jObj = getHttpsUrlResponseWithCookies(str, Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, ""));
            }
        }
        return jObj;
    }

    public static JSONObject getHttpsUrlResponseWithCookies(String str, String str2) {
        HttpURLConnection httpURLConnection;
        JSONObject jSONObject;
        try {
            if (str.startsWith(RequestBuilders.DEFAULT_SCHEME)) {
                httpURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            if (str2 != null && !str2.equalsIgnoreCase("")) {
                httpURLConnection.setRequestProperty("Cookie", str2);
            }
            httpURLConnection.setConnectTimeout(Util.TIMEOUT);
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            try {
                jSONObject = new JSONObject(new String(byteArrayOutputStream.toByteArray()));
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.w("JSONParaser", "Failed to get");
            return null;
        }
    }

    public static JSONObject postHttpUrlResponseWithCookies(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = null;
        try {
            URLEncoder.encode(str3, "UTF-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            if (str2 != null && !str2.equalsIgnoreCase("")) {
                httpURLConnection.setRequestProperty("Cookie", str2);
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setRequestProperty("Content-Type", str4);
            httpURLConnection.setConnectTimeout(Util.TIMEOUT);
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            String num = Integer.toString(str3.getBytes().length);
            Log.d("JSONParser", "contentLength::" + num);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, num);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LANGUAGE, "en-US");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(str3);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str5 = new String(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            inputStream.close();
            try {
                jSONObject = new JSONObject(str5);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public JSONObject getJSONFromLocalUrl(String str) {
        try {
            return new JSONObject(FileHelper.readFileAsString(new File(str)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public JSONObject getJSONFromInputStream(InputStream inputStream) {
        try {
            return new JSONObject(FileHelper.readInputStreamAsString(inputStream));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
