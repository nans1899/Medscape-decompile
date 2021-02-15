package com.wbmd.wbmddrugscommons.contentmanagers;

import android.content.Context;
import android.os.AsyncTask;
import com.example.wbmddrugscommons.R;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.callbacks.IDrugsAtoZContentManagerCallback;
import com.wbmd.wbmddrugscommons.data.DrugAtoZCache;
import com.wbmd.wbmddrugscommons.model.Drug;
import com.wbmd.wbmddrugscommons.parsers.DrugsResponseParser;
import com.wbmd.wbmddrugscommons.tasks.GetDrugsAtoZTask;
import com.wbmd.wbmddrugscommons.util.AsyncTaskHelper;
import com.wbmd.wbmddrugscommons.util.CacheUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class DrugsAtoZContentManager {
    private static DrugsAtoZContentManager sInstance = new DrugsAtoZContentManager();
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private final String TAG = "Cache :";
    /* access modifiers changed from: private */
    public GetDrugsAtoZTask mActiveTask;
    private List<Drug> mAtoZDrugsList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mCacheDrugsData = false;
    private CacheUtil mCacheUtil;
    private IDrugsAtoZContentManagerCallback mCallback;
    private Context mContext;
    private String mEntryKey = "atozDrugsData";
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<IDrugsAtoZContentManagerCallback> mRequestCompletedListeners = new ConcurrentLinkedQueue<>();

    public static DrugsAtoZContentManager get() {
        return sInstance;
    }

    public void fetchDrugsAtoZ(Context context, IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback) {
        this.mCallback = iDrugsAtoZContentManagerCallback;
        this.mContext = context;
        Trace.i("Cache :", "Cache : active Task - " + this.mActiveTask + " - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        if (this.mContext == null) {
            IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback2 = this.mCallback;
            if (iDrugsAtoZContentManagerCallback2 != null) {
                iDrugsAtoZContentManagerCallback2.onContentLoadingError("Context is null");
                return;
            }
            return;
        }
        initCacheProvider();
        if (!doesDataExistInCache(false)) {
            if (this.mCallback != null) {
                getDrugsFromResourceFile();
                Trace.i("Cache :", "Cache : read drugs from resource file and return - " + this.mActiveTask);
                return;
            }
            GetDrugsAtoZTask getDrugsAtoZTask = this.mActiveTask;
            if (getDrugsAtoZTask == null || getDrugsAtoZTask.getStatus() != AsyncTask.Status.RUNNING) {
                Trace.i("Cache :", "GetDrugsAtoZTask: Recreating additional config task");
                this.mRequestCompletedListeners.clear();
                GetDrugsAtoZTask getDrugsAtoZTask2 = new GetDrugsAtoZTask(context, new ICallbackEvent<List<Drug>, String>() {
                    public void onError(String str) {
                        if (!DrugsAtoZContentManager.this.mActiveTask.isCancelled()) {
                            DrugsAtoZContentManager.this.mActiveTask.cancel(true);
                            GetDrugsAtoZTask unused = DrugsAtoZContentManager.this.mActiveTask = null;
                            Trace.i("Cache :", "Cache : onAllDrugsTaskError - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                            if (!DrugsAtoZContentManager.this.doesDataExistInCache(true) && !DrugsAtoZContentManager.this.getDrugsFromResourceFile()) {
                                while (!DrugsAtoZContentManager.this.mRequestCompletedListeners.isEmpty()) {
                                    if (((IDrugsAtoZContentManagerCallback) DrugsAtoZContentManager.this.mRequestCompletedListeners.poll()) != null) {
                                        Trace.i("Cache :", "GetDrugsAtoZTask: onError - Additional config request complete, returning to other listeners");
                                        boolean unused2 = DrugsAtoZContentManager.this.doesDataExistInCache(true);
                                    }
                                }
                            }
                        }
                    }

                    public void onCompleted(List<Drug> list) {
                        Trace.i("Cache :", "Cache : onAllDrugsContentReceived - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                        if (!DrugsAtoZContentManager.this.mActiveTask.isCancelled()) {
                            DrugsAtoZContentManager.this.mActiveTask.cancel(true);
                            GetDrugsAtoZTask unused = DrugsAtoZContentManager.this.mActiveTask = null;
                            boolean unused2 = DrugsAtoZContentManager.this.mCacheDrugsData = true;
                            DrugsAtoZContentManager.this.cacheDrugs(list);
                            while (!DrugsAtoZContentManager.this.mRequestCompletedListeners.isEmpty()) {
                                if (((IDrugsAtoZContentManagerCallback) DrugsAtoZContentManager.this.mRequestCompletedListeners.poll()) != null) {
                                    Trace.i("Cache :", "GetDrugsAtoZTask: onReceived - Additional config request complete, returning to other listeners");
                                    boolean unused3 = DrugsAtoZContentManager.this.doesDataExistInCache(false);
                                }
                            }
                        }
                    }
                });
                this.mActiveTask = getDrugsAtoZTask2;
                AsyncTaskHelper.execute(threadPoolExecutor, getDrugsAtoZTask2, new Void[0]);
                return;
            }
            IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback3 = this.mCallback;
            if (iDrugsAtoZContentManagerCallback3 != null) {
                this.mRequestCompletedListeners.add(iDrugsAtoZContentManagerCallback3);
            }
            Trace.w("Cache :", "GetDrugsAtoZTask: Added listener for additional config");
        }
    }

    /* access modifiers changed from: private */
    public boolean getDrugsFromResourceFile() {
        String dataFromResource = getDataFromResource(R.raw.drugs_atoz);
        if (StringExtensions.isNullOrEmpty(dataFromResource)) {
            return false;
        }
        this.mCacheDrugsData = false;
        Trace.i("Cache :", "Cache : getDrugsFromResourceFile - " + Thread.currentThread().getName() + " - " + dataFromResource.substring(1, 100));
        try {
            cacheDrugs(new DrugsResponseParser(new JSONObject(getDataFromResource(R.raw.drugs_atoz))).parse());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getDataFromResource(int i) {
        StringBuilder sb;
        InputStream openRawResource = this.mContext.getResources().openRawResource(i);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource));
        try {
            sb = new StringBuilder(openRawResource.available());
        } catch (IOException e) {
            e.printStackTrace();
            sb = null;
        }
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            } catch (Exception e2) {
                Trace.i("Cache :", "Error:" + e2.getMessage());
            }
        }
        return sb.toString();
    }

    public void cacheDrugs(List<Drug> list) {
        CacheUtil cacheUtil;
        if (!(list == null || (cacheUtil = this.mCacheUtil) == null)) {
            if (this.mCacheDrugsData) {
                cacheUtil.setExpiryTime(Long.valueOf(System.currentTimeMillis() + 604800000));
                this.mCacheUtil.saveDataToCache(list, this.mEntryKey);
            } else {
                DrugAtoZCache.getInstance().setIsDrugsReadFromResourceFile(true);
                Trace.i("Cache :", "Cache : DrugsReadFromResourceFile, mCacheDrugsData: " + this.mCacheDrugsData);
            }
            DrugAtoZCache.getInstance().setAllDrugs(list);
            Trace.i("Cache :", "Cache : onContentParsed drugs size, mCacheDrugsData: " + this.mCacheDrugsData + " - " + list.size());
            Trace.i("Cache :", "Cache : onContentParsed - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        }
        organizeDrugsList(list);
        IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback = this.mCallback;
        if (iDrugsAtoZContentManagerCallback != null) {
            iDrugsAtoZContentManagerCallback.onContentLoaded(list, this.mAtoZDrugsList);
        }
    }

    private void initCacheProvider() {
        CacheUtil cacheUtil = new CacheUtil(this.mContext);
        this.mCacheUtil = cacheUtil;
        cacheUtil.initCacheProvider("WebMDCache");
    }

    /* access modifiers changed from: private */
    public boolean doesDataExistInCache(Boolean bool) {
        List<Drug> dataFromCache;
        List<Drug> allDrugs = DrugAtoZCache.getInstance().getAllDrugs();
        List<Drug> atoZDrugsList = DrugAtoZCache.getInstance().getAtoZDrugsList();
        if (allDrugs == null || allDrugs.size() <= 0 || atoZDrugsList == null || atoZDrugsList.size() <= 0) {
            CacheUtil cacheUtil = this.mCacheUtil;
            if (cacheUtil == null || (dataFromCache = cacheUtil.getDataFromCache(bool, this.mEntryKey)) == null) {
                return false;
            }
            Trace.i("Cache :", "Cache : cached - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            organizeDrugsList(dataFromCache);
            IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback = this.mCallback;
            if (iDrugsAtoZContentManagerCallback != null) {
                iDrugsAtoZContentManagerCallback.onContentLoaded(dataFromCache, this.mAtoZDrugsList);
            }
            return true;
        }
        Trace.i("Cache :", "Cache : singleton - " + Thread.currentThread().getName() + " - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        IDrugsAtoZContentManagerCallback iDrugsAtoZContentManagerCallback2 = this.mCallback;
        if (iDrugsAtoZContentManagerCallback2 != null) {
            iDrugsAtoZContentManagerCallback2.onContentLoaded(allDrugs, atoZDrugsList);
        }
        return true;
    }

    private void organizeDrugsList(List<Drug> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Drug next : list) {
                if (next != null) {
                    if (next.getStatus().equalsIgnoreCase("Active")) {
                        arrayList.add(next);
                    } else {
                        arrayList3.add(next);
                    }
                    if (next.getIsTop().booleanValue()) {
                        next.setDedupe(true);
                        linkedHashSet.add(next);
                    }
                }
            }
            this.mAtoZDrugsList.clear();
            this.mAtoZDrugsList.addAll(arrayList);
            arrayList2.addAll(linkedHashSet);
            DrugAtoZCache.getInstance().setAllDrugs(list);
            DrugAtoZCache.getInstance().setAtoZDrugsList(arrayList);
            DrugAtoZCache.getInstance().setOffMarketDrugsList(arrayList3);
            DrugAtoZCache.getInstance().setTopSearchesDrugsList(arrayList2);
            Trace.i("Cache :", "all drugs list - " + list.size());
            Trace.i("Cache :", "filtered AtoZ drugs list - " + arrayList.size() + "....." + this.mAtoZDrugsList.size());
            StringBuilder sb = new StringBuilder();
            sb.append("filtered Top searches drugs list - ");
            sb.append(arrayList2.size());
            Trace.i("Cache :", sb.toString());
            Trace.i("Cache :", "filtered off Market drugs list - " + arrayList3.size());
        }
    }
}
