package com.adobe.mobile;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import com.google.common.net.HttpHeaders;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.FeedDetail;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

abstract class WearableDataRequest {
    protected int timeOut;
    protected String uuid;

    /* access modifiers changed from: protected */
    public abstract DataMap getDataMap();

    /* access modifiers changed from: protected */
    public abstract DataMap handle(Context context);

    protected WearableDataRequest() {
        this.uuid = UUID.randomUUID().toString();
    }

    protected WearableDataRequest(int i) {
        this();
        this.timeOut = i;
    }

    /* access modifiers changed from: protected */
    public String getUUID() {
        return this.uuid;
    }

    /* access modifiers changed from: protected */
    public int getTimeOut() {
        return this.timeOut;
    }

    static class Get extends WearableDataRequest {
        protected static final String logPrefix = "Wearable GET Requested Forward";
        protected String url;

        protected Get(String str, int i) {
            super(i);
            this.url = str;
        }

        protected Get(DataMap dataMap) {
            this.url = dataMap.getString(FeedDetail.F_URL);
            this.uuid = dataMap.getString(Constants.WBMDTugStringID);
            this.timeOut = dataMap.getInt("Timeout");
        }

        /* access modifiers changed from: protected */
        public DataMap getDataMap() {
            DataMap dataMap = new DataMap();
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putInt("Timeout", this.timeOut);
            dataMap.putString(CalcsContract.TYPE, "GET");
            dataMap.putString(FeedDetail.F_URL, this.url);
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap handle(Context context) {
            DataMap dataMap = new DataMap();
            dataMap.putByteArray("Result", RequestHandler.retrieveData(this.url, (Map<String, String>) null, this.timeOut, logPrefix));
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "GET");
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public String getURL() {
            return this.url;
        }
    }

    static class Post extends WearableDataRequest {
        private static final Object _userAgentMutex = new Object();
        protected static final String logPrefix = "Wearable POST Request Forward";
        private static String userAgent;
        protected String body;
        protected String url;

        protected Post() {
        }

        protected Post(String str, String str2, int i) {
            super(i);
            this.url = str;
            this.body = str2;
        }

        protected Post(DataMap dataMap) {
            this.timeOut = dataMap.getInt("Timeout");
            this.url = dataMap.getString(FeedDetail.F_URL);
            this.body = dataMap.getString("Body");
            this.uuid = dataMap.getString(Constants.WBMDTugStringID);
        }

        /* access modifiers changed from: protected */
        public DataMap getDataMap() {
            DataMap dataMap = new DataMap();
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "POST");
            dataMap.putString(FeedDetail.F_URL, this.url);
            dataMap.putInt("Timeout", this.timeOut);
            dataMap.putString("Body", this.body);
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap handle(Context context) {
            DataMap dataMap = new DataMap();
            HashMap hashMap = new HashMap();
            hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, getDefaultAcceptLanguage(context));
            hashMap.put("User-Agent", getDefaultUserAgent(context));
            dataMap.putByteArray("Result", RequestHandler.retrieveAnalyticsRequestData(this.url, this.body, hashMap, this.timeOut, logPrefix));
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "POST");
            return dataMap;
        }

        protected static String getDefaultAcceptLanguage(Context context) {
            Resources resources;
            Configuration configuration;
            Locale locale;
            if (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null || (locale = configuration.locale) == null) {
                return null;
            }
            return locale.toString().replace('_', '-');
        }

        protected static String getDefaultUserAgent(Context context) {
            String str;
            synchronized (_userAgentMutex) {
                if (userAgent == null) {
                    userAgent = "Mozilla/5.0 (Linux; U; Android " + Build.VERSION.RELEASE + "; " + getDefaultAcceptLanguage(context) + "; " + Build.MODEL + " Build/" + Build.ID + ")";
                }
                str = userAgent;
            }
            return str;
        }

        /* access modifiers changed from: protected */
        public String getURL() {
            return this.url;
        }
    }

    static class ThirdPartyRequest extends Post {
        protected static final String logPrefix = "Wearable Third Party Request Forward";
        protected String postType;

        protected ThirdPartyRequest(String str, String str2, int i) {
            super(str, str2, i);
        }

        protected ThirdPartyRequest(String str, String str2, int i, String str3) {
            this(str, str2, i);
            this.postType = str3;
        }

        protected ThirdPartyRequest(DataMap dataMap) {
            super(dataMap);
            this.postType = dataMap.getString("PostType");
        }

        /* access modifiers changed from: protected */
        public DataMap getDataMap() {
            DataMap dataMap = super.getDataMap();
            dataMap.putString(CalcsContract.TYPE, "ThirdParty");
            dataMap.putString("PostType", this.postType);
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap handle(Context context) {
            DataMap dataMap = new DataMap();
            HashMap hashMap = new HashMap();
            hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, getDefaultAcceptLanguage(context));
            hashMap.put("User-Agent", getDefaultUserAgent(context));
            dataMap.putBoolean("Result", RequestHandler.sendThirdPartyRequest(this.url, this.body, hashMap, this.timeOut, this.postType, logPrefix));
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "ThirdParty");
            return dataMap;
        }
    }

    static class ShareConfig extends WearableDataRequest {
        protected ShareConfig(int i) {
            super(i);
        }

        protected ShareConfig(DataMap dataMap) {
            this.uuid = dataMap.getString(Constants.WBMDTugStringID);
        }

        /* access modifiers changed from: protected */
        public DataMap getDataMap() {
            DataMap dataMap = new DataMap();
            dataMap.putString(CalcsContract.TYPE, "Config");
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap handle(Context context) {
            DataMap dataMap = new DataMap();
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "Config");
            dataMap.putAll(ConfigSynchronizer.getSharedConfig());
            return dataMap;
        }
    }

    static class Cache extends WearableDataRequest {
        protected String fileName;
        protected String url;

        protected Cache(String str, String str2, int i) {
            super(i);
            this.url = str;
            this.fileName = str2;
        }

        protected Cache(DataMap dataMap) {
            this.uuid = dataMap.getString(Constants.WBMDTugStringID);
            this.fileName = dataMap.getString("FileName");
            this.url = dataMap.getString(FeedDetail.F_URL);
        }

        /* access modifiers changed from: protected */
        public DataMap getDataMap() {
            DataMap dataMap = new DataMap();
            dataMap.putString(CalcsContract.TYPE, "File");
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(FeedDetail.F_URL, this.url);
            dataMap.putString("FileName", this.fileName);
            return dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap handle(Context context) {
            DataMap dataMap = new DataMap();
            dataMap.putString(Constants.WBMDTugStringID, this.uuid);
            dataMap.putString(CalcsContract.TYPE, "File");
            dataMap.putString(FeedDetail.F_URL, this.url);
            File fileForCachedURL = RemoteDownload.getFileForCachedURL(this.url);
            if (fileForCachedURL == null) {
                dataMap.putBoolean("FileFound", false);
            } else {
                dataMap.putBoolean("FileFound", true);
                if (fileForCachedURL.getName().equals(this.fileName)) {
                    dataMap.putBoolean("Updated", false);
                } else {
                    dataMap.putBoolean("Updated", true);
                    dataMap.putString("FileName", fileForCachedURL.getName());
                    byte[] access$000 = WearableDataRequest.readFile(fileForCachedURL);
                    if (access$000 != null && access$000.length > 0) {
                        dataMap.putAsset("FileContent", Asset.createFromBytes(access$000));
                    }
                }
            }
            return dataMap;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        com.adobe.mobile.StaticMethods.logErrorFormat("Wearable - Failed to read cached file", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        if (r5 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
        com.adobe.mobile.StaticMethods.logDebugFormat("Wearable - Failed to close the file input stream", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0043, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        com.adobe.mobile.StaticMethods.logErrorFormat("Wearable - Failed to read cached file", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004a, code lost:
        if (r5 != null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0050, code lost:
        com.adobe.mobile.StaticMethods.logDebugFormat("Wearable - Failed to close the file input stream", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0055, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:22:0x0033, B:32:0x0045] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0058 A[SYNTHETIC, Splitter:B:41:0x0058] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0033=Splitter:B:22:0x0033, B:32:0x0045=Splitter:B:32:0x0045} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFile(java.io.File r6) {
        /*
            java.lang.String r0 = "Wearable - Failed to read cached file"
            java.lang.String r1 = "Wearable - Failed to close the file input stream"
            long r2 = r6.length()
            int r3 = (int) r2
            byte[] r2 = new byte[r3]
            r3 = 0
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0044, Exception -> 0x0032, all -> 0x0030 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0044, Exception -> 0x0032, all -> 0x0030 }
            int r6 = r5.read(r2)     // Catch:{ IOException -> 0x0045, Exception -> 0x0033 }
            r0 = -1
            if (r6 != r0) goto L_0x0023
            r5.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0022
        L_0x001d:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r6)
        L_0x0022:
            return r3
        L_0x0023:
            r5.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x002c
        L_0x0027:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r6)
        L_0x002c:
            return r2
        L_0x002d:
            r6 = move-exception
            r3 = r5
            goto L_0x0056
        L_0x0030:
            r6 = move-exception
            goto L_0x0056
        L_0x0032:
            r5 = r3
        L_0x0033:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x002d }
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r6)     // Catch:{ all -> 0x002d }
            if (r5 == 0) goto L_0x0043
            r5.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0043
        L_0x003e:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r6)
        L_0x0043:
            return r3
        L_0x0044:
            r5 = r3
        L_0x0045:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x002d }
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r6)     // Catch:{ all -> 0x002d }
            if (r5 == 0) goto L_0x0055
            r5.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0055
        L_0x0050:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r6)
        L_0x0055:
            return r3
        L_0x0056:
            if (r3 == 0) goto L_0x0061
            r3.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0061
        L_0x005c:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r0)
        L_0x0061:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.WearableDataRequest.readFile(java.io.File):byte[]");
    }

    protected static WearableDataRequest createGetRequest(String str, int i) {
        return new Get(str, i);
    }

    protected static WearableDataRequest createPostRequest(String str, String str2, int i) {
        return new Post(str, str2, i);
    }

    protected static WearableDataRequest createThirdPartyRequest(String str, String str2, int i, String str3) {
        return new ThirdPartyRequest(str, str2, i, str3);
    }

    protected static WearableDataRequest createShareConfigRequest(int i) {
        return new ShareConfig(i);
    }

    protected static WearableDataRequest createFileRequest(String str, String str2, int i) {
        return new Cache(str, str2, i);
    }

    protected static WearableDataRequest createRequestFromDataMap(DataMap dataMap) {
        if (!dataMap.containsKey(CalcsContract.TYPE)) {
            return null;
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("POST")) {
            return new Post(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("GET")) {
            return new Get(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("Config")) {
            return new ShareConfig(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("File")) {
            return new Cache(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("ThirdParty")) {
            return new ThirdPartyRequest(dataMap);
        }
        return null;
    }
}
