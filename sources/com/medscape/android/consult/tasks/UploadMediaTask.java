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
import com.medscape.android.consult.interfaces.IUploadMediaListener;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class UploadMediaTask extends AsyncTask<Void, Void, List<String>> {
    private static final String TAG = UploadMediaTask.class.getSimpleName();
    private Context mContext;
    private ConsultPost mPost;
    private IUploadMediaListener mUploadMediaListener;
    private String mZimbraGroupId;
    private String mZimbraMediaGalleryId;

    public UploadMediaTask(Context context, ConsultPost consultPost, String str, String str2, IUploadMediaListener iUploadMediaListener) {
        this.mContext = context;
        this.mPost = consultPost;
        this.mZimbraGroupId = str;
        this.mZimbraMediaGalleryId = str2;
        this.mUploadMediaListener = iUploadMediaListener;
    }

    /* access modifiers changed from: protected */
    public List<String> doInBackground(Void... voidArr) {
        ArrayList arrayList = new ArrayList();
        ConsultPost consultPost = this.mPost;
        return (consultPost == null || consultPost.getConsultAssets() == null || this.mPost.getConsultAssets().isEmpty()) ? arrayList : uploadMedia(this.mPost.getConsultAssets());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<String> list) {
        super.onPostExecute(list);
        IUploadMediaListener iUploadMediaListener = this.mUploadMediaListener;
        if (iUploadMediaListener != null && list != null) {
            iUploadMediaListener.onMediaUploadSuccess(list);
        }
    }

    private List<String> uploadMedia(List<ConsultAsset> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (ConsultAsset next : list) {
                if (next != null) {
                    if (StringUtil.isNotEmpty(next.getAssetUrl())) {
                        arrayList.add(next.getAssetUrl());
                    } else {
                        String assetUrlFromResponse = getAssetUrlFromResponse(uploadAsset(next));
                        if (StringUtil.isNotEmpty(assetUrlFromResponse)) {
                            arrayList.add(assetUrlFromResponse);
                        } else {
                            this.mUploadMediaListener.onMediaUploadFail(new MedscapeException(this.mContext.getResources().getString(R.string.consult_error_uploading_image)));
                            return null;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private String getAssetUrlFromResponse(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("StatusCode");
            int optInt2 = jSONObject.optInt("code");
            if (optInt == 200 && optInt2 == 1) {
                return jSONObject.optString("data");
            }
            return null;
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to parse response from server for media upload");
            return null;
        }
    }

    private String uploadAsset(ConsultAsset consultAsset) {
        if (consultAsset == null || consultAsset.getBitmap() == null) {
            return null;
        }
        String uploadImageUrl = ConsultUrls.getUploadImageUrl(this.mContext);
        String generateBoundaryString = generateBoundaryString();
        byte[] body = getBody(consultAsset.getBitmap(), generateBoundaryString);
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
            HttpPost httpPost = new HttpPost(uploadImageUrl);
            httpPost.setEntity(new ByteArrayEntity(body));
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    httpPost.setHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
            return getStringResponse(defaultHttpClient.execute(httpPost));
        } catch (Exception e) {
            Trace.w(TAG, "Failed to post photo for post");
            if (e instanceof UnknownHostException) {
                this.mUploadMediaListener.onMediaUploadFail(new MedscapeException(this.mContext.getString(R.string.error_connection_required)));
                return "error";
            } else if (!(e instanceof SocketTimeoutException)) {
                return null;
            } else {
                this.mUploadMediaListener.onMediaUploadFail(new MedscapeException(this.mContext.getString(R.string.error_service_unavailable)));
                return "error";
            }
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
        byte[] bytes2 = "Content-Disposition: form-data; name=\"groupId\"\r\n\r\n".getBytes(forName);
        byte[] bytes3 = this.mZimbraGroupId.getBytes(forName);
        byte[] bytes4 = IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(forName);
        byte[] bytes5 = "Content-Disposition: form-data; name=\"mediaGalleryId\"\r\n\r\n".getBytes(forName);
        byte[] bytes6 = this.mZimbraMediaGalleryId.getBytes(forName);
        byte[] bytes7 = "Content-Disposition: form-data; name=\"Width\"\r\n\r\n".getBytes(forName);
        byte[] bytes8 = String.format("%s", new Object[]{Integer.valueOf(bitmap.getWidth())}).getBytes(forName);
        byte[] bytes9 = "Content-Disposition: form-data; name=\"Height\"\r\n\r\n".getBytes(forName);
        byte[] bytes10 = String.format("%s", new Object[]{Integer.valueOf(bitmap.getHeight())}).getBytes(forName);
        byte[] bytes11 = "Content-Disposition: form-data; name=\"FileData\"; filename=\"image.jpg\"\r\n".getBytes(forName);
        byte[] bytes12 = String.format("Content-Type: image/%s\r\n\r\n", new Object[]{"jpeg"}).getBytes(forName);
        byte[] bytesFromBitmap = getBytesFromBitmap(bitmap, 100);
        if (bytesFromBitmap == null) {
            return null;
        }
        byte[] bytes13 = IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(forName);
        byte[] bytes14 = String.format("--%s--\r\n", new Object[]{str}).getBytes(forName);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes2);
            byteArrayOutputStream.write(bytes3);
            byteArrayOutputStream.write(bytes4);
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes5);
            byteArrayOutputStream.write(bytes6);
            byteArrayOutputStream.write(bytes4);
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes7);
            byteArrayOutputStream.write(bytes8);
            byteArrayOutputStream.write(bytes4);
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes9);
            byteArrayOutputStream.write(bytes10);
            byteArrayOutputStream.write(bytes4);
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(bytes11);
            byteArrayOutputStream.write(bytes12);
            byteArrayOutputStream.write(bytesFromBitmap);
            byteArrayOutputStream.write(bytes13);
            byteArrayOutputStream.write(bytes14);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytesFromBitmap(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        return byteArrayOutputStream.toByteArray();
    }

    private String generateBoundaryString() {
        return String.format("Boundary-%s", new Object[]{UUID.randomUUID().toString()});
    }
}
