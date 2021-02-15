package com.medscape.android.consult.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.google.common.net.HttpHeaders;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.MedscapeException;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

public class UploadProfileImageTask extends AsyncTask<Void, String, String> {
    static final String TAG = UploadProfileImageTask.class.getSimpleName();
    private Bitmap mBitmap;
    private Context mContext;
    private IHttpRequestCompleteListener mListener;

    public UploadProfileImageTask(Context context, Bitmap bitmap, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        this.mContext = context;
        this.mListener = iHttpRequestCompleteListener;
        this.mBitmap = bitmap;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        String uploadProfileImageUrl = ConsultUrls.getUploadProfileImageUrl(this.mContext);
        String generateBoundaryString = generateBoundaryString();
        byte[] body = getBody(this.mBitmap, generateBoundaryString);
        if (body == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "multipart/form-data; boundary=" + generateBoundaryString);
        hashMap.put("Accept", "*/*");
        String setting = Settings.singleton(this.mContext).getSetting(Constants.PREF_COOKIE_STRING, "");
        if (setting != null && !setting.isEmpty()) {
            hashMap.put("Cookie", setting);
        }
        AuthenticationManager instance = AuthenticationManager.getInstance(this.mContext);
        hashMap.put(HttpHeaders.AUTHORIZATION, "bearer " + instance.getAuthenticationToken());
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        try {
            HttpPost httpPost = new HttpPost(uploadProfileImageUrl);
            httpPost.setEntity(new ByteArrayEntity(body));
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    httpPost.setHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
            return getStringResponse(defaultHttpClient.execute(httpPost));
        } catch (Exception e) {
            Trace.w(TAG, "Failed to post photo");
            if (e instanceof UnknownHostException) {
                return this.mContext.getString(R.string.error_connection_required);
            }
            if (e instanceof SocketTimeoutException) {
                return this.mContext.getString(R.string.error_service_unavailable);
            }
            return null;
        }
    }

    public String getStringResponse(HttpResponse httpResponse) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
        }
    }

    private byte[] getBody(Bitmap bitmap, String str) {
        Charset forName = Charset.forName("UTF-8");
        byte[] bytes = String.format("--%s\r\n", new Object[]{str}).getBytes(forName);
        byte[] bytes2 = "Content-Disposition: form-data; name=\"fileName\"\r\n\r\n".getBytes(forName);
        byte[] bytes3 = "image.jpg\r\n".getBytes(forName);
        byte[] bytes4 = String.format("Content-Type: image/%s\r\n\r\n", new Object[]{"jpeg"}).getBytes(forName);
        byte[] bytes5 = "Content-Disposition: form-data; name=\"fileUpload\"; filename=\"image.jpg\"\r\n".getBytes(forName);
        byte[] bytesFromBitmap = getBytesFromBitmap(bitmap, 100);
        if (bytesFromBitmap == null) {
            return null;
        }
        byte[] bytes6 = IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(forName);
        byte[] bytes7 = String.format("--%s--\r\n", new Object[]{str}).getBytes(forName);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes2);
            byteArrayOutputStream.write(bytes3);
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes5);
            byteArrayOutputStream.write(bytes4);
            byteArrayOutputStream.write(bytesFromBitmap);
            byteArrayOutputStream.write(bytes6);
            byteArrayOutputStream.write(bytes7);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytesFromBitmap(Bitmap bitmap, int i) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            return byteArrayOutputStream.toByteArray();
        } finally {
            bitmap.recycle();
        }
    }

    private String generateBoundaryString() {
        return String.format("Boundary-%s", new Object[]{UUID.randomUUID().toString()});
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        Trace.i(TAG, "Finished upload image task");
        try {
            if (!str.equalsIgnoreCase(this.mContext.getString(R.string.error_connection_required))) {
                if (!str.equalsIgnoreCase(this.mContext.getString(R.string.error_service_unavailable))) {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("StatusCode");
                    int optInt2 = jSONObject.optInt("code");
                    if (optInt == 200 && optInt2 == 1) {
                        this.mListener.onHttpRequestSucceeded((HttpResponseObject) null);
                        return;
                    }
                    this.mListener.onHttpRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.image_upload_failed)));
                    return;
                }
            }
            this.mListener.onHttpRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), str));
        } catch (Exception unused) {
            this.mListener.onHttpRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.image_upload_failed)));
        }
    }
}
