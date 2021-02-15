package com.adobe.mobile;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.FeedDetail;
import java.io.File;

class WearableDataResponse {
    protected boolean success = false;

    WearableDataResponse() {
    }

    /* access modifiers changed from: protected */
    public boolean isSuccess() {
        return this.success;
    }

    static class GetResponse extends WearableDataResponse {
        protected byte[] result;

        protected GetResponse(DataMap dataMap) {
            byte[] byteArray = dataMap.getByteArray("Result");
            this.result = byteArray;
            if (byteArray != null) {
                this.success = true;
            }
        }

        /* access modifiers changed from: protected */
        public byte[] getResult() {
            return this.result;
        }
    }

    static class PostResponse extends WearableDataResponse {
        protected byte[] result;

        protected PostResponse(DataMap dataMap) {
            byte[] byteArray = dataMap.getByteArray("Result");
            this.result = byteArray;
            if (byteArray != null && byteArray.length > 0) {
                this.success = true;
            }
        }

        /* access modifiers changed from: protected */
        public byte[] getResult() {
            return this.result;
        }
    }

    static class ThirdPartyResponse extends WearableDataResponse {
        protected ThirdPartyResponse(DataMap dataMap) {
            this.success = dataMap.getBoolean("Result");
        }
    }

    static class ShareConfigResponse extends WearableDataResponse {
        final DataMap result;

        protected ShareConfigResponse(DataMap dataMap) {
            this.result = dataMap;
        }

        /* access modifiers changed from: protected */
        public DataMap getResult() {
            return this.result;
        }
    }

    static class CacheResponse extends WearableDataResponse {
        final boolean result;

        protected CacheResponse(DataMap dataMap, GoogleApiClient googleApiClient) {
            if (!dataMap.getBoolean("FileFound")) {
                RemoteDownload.deleteFilesInDirectory("adbdownloadcache");
                this.result = false;
                return;
            }
            boolean z = dataMap.getBoolean("Updated");
            this.result = z;
            if (z) {
                RemoteDownload.deleteCachedDataForURL(dataMap.getString(FeedDetail.F_URL), "adbdownloadcache");
                Asset asset = dataMap.getAsset("FileContent");
                String string = dataMap.getString("FileName");
                File downloadCacheDirectory = RemoteDownload.getDownloadCacheDirectory("adbdownloadcache");
                if (downloadCacheDirectory != null) {
                    WearableDataResponse.saveFileFromAsset(asset, downloadCacheDirectory.getPath() + File.separator + string, googleApiClient);
                }
            }
        }

        /* access modifiers changed from: protected */
        public boolean getResult() {
            return this.result;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006b A[SYNTHETIC, Splitter:B:37:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0077 A[SYNTHETIC, Splitter:B:42:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveFileFromAsset(com.google.android.gms.wearable.Asset r4, java.lang.String r5, com.google.android.gms.common.api.GoogleApiClient r6) {
        /*
            java.lang.String r0 = "Wearable - Failed to close file output stream"
            if (r4 == 0) goto L_0x0081
            if (r6 != 0) goto L_0x0008
            goto L_0x0081
        L_0x0008:
            r1 = 15000(0x3a98, double:7.411E-320)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
            com.google.android.gms.common.ConnectionResult r1 = com.adobe.mobile.GoogleApiClientWrapper.blockingConnect(r6, r1, r3)
            if (r1 == 0) goto L_0x0081
            boolean r1 = r1.isSuccess()
            if (r1 != 0) goto L_0x001a
            goto L_0x0081
        L_0x001a:
            com.google.android.gms.wearable.DataApi r1 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.PendingResult r4 = r1.getFdForAsset(r6, r4)
            com.google.android.gms.common.api.Result r4 = com.adobe.mobile.GoogleApiClientWrapper.await(r4)
            boolean r1 = r4 instanceof com.google.android.gms.wearable.DataApi.GetFdForAssetResult
            r2 = 0
            if (r1 == 0) goto L_0x0030
            com.google.android.gms.wearable.DataApi$GetFdForAssetResult r4 = (com.google.android.gms.wearable.DataApi.GetFdForAssetResult) r4
            java.io.InputStream r4 = r4.getInputStream()
            goto L_0x0031
        L_0x0030:
            r4 = r2
        L_0x0031:
            com.adobe.mobile.GoogleApiClientWrapper.disconnect(r6)
            if (r4 != 0) goto L_0x0037
            return
        L_0x0037:
            java.io.File r6 = new java.io.File
            r6.<init>(r5)
            r5 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0062 }
            r1.<init>(r6)     // Catch:{ IOException -> 0x0062 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x005e, all -> 0x005b }
        L_0x0046:
            int r2 = r4.read(r6)     // Catch:{ IOException -> 0x005e, all -> 0x005b }
            r3 = -1
            if (r2 == r3) goto L_0x0051
            r1.write(r6, r5, r2)     // Catch:{ IOException -> 0x005e, all -> 0x005b }
            goto L_0x0046
        L_0x0051:
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0074
        L_0x0055:
            java.lang.Object[] r4 = new java.lang.Object[r5]
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r4)
            goto L_0x0074
        L_0x005b:
            r4 = move-exception
            r2 = r1
            goto L_0x0075
        L_0x005e:
            r2 = r1
            goto L_0x0062
        L_0x0060:
            r4 = move-exception
            goto L_0x0075
        L_0x0062:
            java.lang.String r4 = "Wearable - Failed to save cache file"
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0060 }
            com.adobe.mobile.StaticMethods.logErrorFormat(r4, r6)     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x0074
            r2.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0074
        L_0x006f:
            java.lang.Object[] r4 = new java.lang.Object[r5]
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r4)
        L_0x0074:
            return
        L_0x0075:
            if (r2 == 0) goto L_0x0080
            r2.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x0080
        L_0x007b:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r5)
        L_0x0080:
            throw r4
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.WearableDataResponse.saveFileFromAsset(com.google.android.gms.wearable.Asset, java.lang.String, com.google.android.gms.common.api.GoogleApiClient):void");
    }

    protected static WearableDataResponse createResponseFromDataMap(DataMap dataMap, GoogleApiClient googleApiClient) {
        if (!dataMap.containsKey(CalcsContract.TYPE)) {
            return null;
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("POST")) {
            return new PostResponse(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("GET")) {
            return new GetResponse(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("Config")) {
            return new ShareConfigResponse(dataMap);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("File")) {
            return new CacheResponse(dataMap, googleApiClient);
        }
        if (dataMap.getString(CalcsContract.TYPE).equals("ThirdParty")) {
            return new ThirdPartyResponse(dataMap);
        }
        return null;
    }
}
