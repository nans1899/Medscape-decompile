package com.wbmd.wbmddrugscommons.contentmanagers;

import android.content.Context;
import android.os.AsyncTask;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.callbacks.IDrugTTSDataCallback;
import com.wbmd.wbmddrugscommons.data.DrugTTSData;
import com.wbmd.wbmddrugscommons.model.Tug;
import com.wbmd.wbmddrugscommons.tasks.GetDrugTTSDataTask;
import com.wbmd.wbmddrugscommons.util.AsyncTaskHelper;
import com.wbmd.wbmddrugscommons.util.CacheUtil;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrugsTTSContentManager {
    private static DrugsTTSContentManager sInstance = new DrugsTTSContentManager();
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public final String TAG = getClass().getSimpleName();
    /* access modifiers changed from: private */
    public GetDrugTTSDataTask mActiveTask;
    private CacheUtil mCacheTTS;
    /* access modifiers changed from: private */
    public IDrugTTSDataCallback mCallback;
    private Context mContext;
    private String mEntryKey = "drugsTTSData";
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<IDrugTTSDataCallback> mRequestCompletedListeners = new ConcurrentLinkedQueue<>();

    public static DrugsTTSContentManager get() {
        return sInstance;
    }

    public boolean fetchTTSDrugData(Context context, IDrugTTSDataCallback iDrugTTSDataCallback) {
        this.mCallback = iDrugTTSDataCallback;
        this.mContext = context;
        if (context == null) {
            if (iDrugTTSDataCallback != null) {
                iDrugTTSDataCallback.onTTSDrugDataError("Context is null");
            }
            return false;
        }
        initTTSCacheProvider();
        if (doesDataExistInCache(false)) {
            return true;
        }
        GetDrugTTSDataTask getDrugTTSDataTask = this.mActiveTask;
        if (getDrugTTSDataTask == null || getDrugTTSDataTask.getStatus() != AsyncTask.Status.RUNNING) {
            Trace.i(this.TAG, "GetDrugTTSDataTask: Recreating additional config task");
            this.mRequestCompletedListeners.clear();
            GetDrugTTSDataTask getDrugTTSDataTask2 = new GetDrugTTSDataTask(this.mContext, new ICallbackEvent<HashMap<String, Tug>, String>() {
                public void onError(String str) {
                    if (DrugsTTSContentManager.this.mActiveTask != null) {
                        DrugsTTSContentManager.this.mActiveTask.cancel(true);
                    }
                    GetDrugTTSDataTask unused = DrugsTTSContentManager.this.mActiveTask = null;
                    if (DrugsTTSContentManager.this.mCallback != null) {
                        DrugsTTSContentManager.this.mCallback.onTTSDrugDataError(str);
                    }
                    while (!DrugsTTSContentManager.this.mRequestCompletedListeners.isEmpty()) {
                        IDrugTTSDataCallback iDrugTTSDataCallback = (IDrugTTSDataCallback) DrugsTTSContentManager.this.mRequestCompletedListeners.poll();
                        if (iDrugTTSDataCallback != null) {
                            Trace.i(DrugsTTSContentManager.this.TAG, "GetDrugTTSDataTask: onError - Additional config request complete, returning to other listeners");
                            iDrugTTSDataCallback.onTTSDrugDataError(str);
                        }
                    }
                }

                public void onCompleted(HashMap<String, Tug> hashMap) {
                    if (DrugsTTSContentManager.this.mActiveTask != null) {
                        DrugsTTSContentManager.this.mActiveTask.cancel(true);
                    }
                    GetDrugTTSDataTask unused = DrugsTTSContentManager.this.mActiveTask = null;
                    DrugsTTSContentManager.this.cacheTTSData(hashMap);
                    while (!DrugsTTSContentManager.this.mRequestCompletedListeners.isEmpty()) {
                        if (((IDrugTTSDataCallback) DrugsTTSContentManager.this.mRequestCompletedListeners.poll()) != null) {
                            Trace.i(DrugsTTSContentManager.this.TAG, "GetDrugTTSDataTask: onReceived - Additional config request complete, returning to other listeners");
                            boolean unused2 = DrugsTTSContentManager.this.doesDataExistInCache(false);
                        }
                    }
                }
            });
            this.mActiveTask = getDrugTTSDataTask2;
            AsyncTaskHelper.execute(threadPoolExecutor, getDrugTTSDataTask2, new Void[0]);
        } else {
            IDrugTTSDataCallback iDrugTTSDataCallback2 = this.mCallback;
            if (iDrugTTSDataCallback2 != null) {
                this.mRequestCompletedListeners.add(iDrugTTSDataCallback2);
            }
            Trace.w(this.TAG, "GetDrugTTSDataTask: Added listener for additional config");
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void cacheTTSData(HashMap<String, Tug> hashMap) {
        CacheUtil cacheUtil;
        if (hashMap != null && (cacheUtil = this.mCacheTTS) != null) {
            cacheUtil.setExpiryTime(Long.valueOf(System.currentTimeMillis() + 10800000));
            this.mCacheTTS.saveTugsToCache(hashMap, this.mEntryKey);
        }
    }

    private void initTTSCacheProvider() {
        if (this.mCacheTTS == null) {
            CacheUtil cacheUtil = new CacheUtil(this.mContext);
            this.mCacheTTS = cacheUtil;
            cacheUtil.initCacheProvider("WebMDTTSCache");
        }
    }

    /* access modifiers changed from: private */
    public boolean doesDataExistInCache(Boolean bool) {
        HashMap<String, Tug> tTSDataFromCache;
        HashMap<String, Tug> tTSData = DrugTTSData.get().getTTSData();
        if (tTSData == null || tTSData.size() <= 0) {
            CacheUtil cacheUtil = this.mCacheTTS;
            if (cacheUtil == null || (tTSDataFromCache = cacheUtil.getTTSDataFromCache(bool, this.mEntryKey)) == null) {
                return false;
            }
            IDrugTTSDataCallback iDrugTTSDataCallback = this.mCallback;
            if (iDrugTTSDataCallback != null) {
                iDrugTTSDataCallback.onTTSDrugDataReceived(tTSDataFromCache);
            }
            return true;
        }
        IDrugTTSDataCallback iDrugTTSDataCallback2 = this.mCallback;
        if (iDrugTTSDataCallback2 != null) {
            iDrugTTSDataCallback2.onTTSDrugDataReceived(tTSData);
        }
        return true;
    }
}
