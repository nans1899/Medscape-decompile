package com.wbmd.wbmddrugscommons.contentmanagers;

import android.content.Context;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.callbacks.IContentManagerCallback;
import com.wbmd.wbmddrugscommons.data.DrugMonographCache;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import com.wbmd.wbmddrugscommons.tasks.GetDrugMonographTask;
import com.wbmd.wbmddrugscommons.util.AsyncTaskHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrugMonographContentManager {
    private static DrugMonographContentManager sInstance = new DrugMonographContentManager();
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    GetDrugMonographTask mActiveTask;
    IContentManagerCallback mCallback;

    public static DrugMonographContentManager get() {
        return sInstance;
    }

    public GetDrugMonographTask getActiveTask() {
        return this.mActiveTask;
    }

    public void fetchDrugMonograph(Context context, DrugMonograph drugMonograph, IContentManagerCallback iContentManagerCallback) {
        this.mCallback = iContentManagerCallback;
        if (context != null) {
            DrugMonograph drugMonograph2 = DrugMonographCache.get().getDrugMonograph();
            if (drugMonograph2 == null || !isRequestedDrugMonographCached(drugMonograph2, drugMonograph)) {
                Trace.d("cache:", "Drug Monograph not in cache, call task");
            } else {
                IContentManagerCallback iContentManagerCallback2 = this.mCallback;
                if (iContentManagerCallback2 != null) {
                    iContentManagerCallback2.onContentLoaded(drugMonograph2);
                    Trace.d("cache:", "Drug Monograph read from cache");
                    return;
                }
            }
            GetDrugMonographTask getDrugMonographTask = new GetDrugMonographTask(context, new ICallbackEvent<DrugMonograph, String>() {
                public void onError(String str) {
                    if (!DrugMonographContentManager.this.mActiveTask.isCancelled() && str != null && DrugMonographContentManager.this.mCallback != null) {
                        DrugMonographContentManager.this.mCallback.onContentLoadingError(str);
                    }
                }

                public void onCompleted(DrugMonograph drugMonograph) {
                    if (!DrugMonographContentManager.this.mActiveTask.isCancelled()) {
                        if (drugMonograph != null) {
                            DrugMonographCache.get().setDrugMonograph(drugMonograph);
                        }
                        if (drugMonograph != null && DrugMonographContentManager.this.mCallback != null) {
                            DrugMonographContentManager.this.mCallback.onContentLoaded(drugMonograph);
                        }
                    }
                }
            });
            this.mActiveTask = getDrugMonographTask;
            AsyncTaskHelper.execute(threadPoolExecutor, getDrugMonographTask, drugMonograph);
        } else if (iContentManagerCallback != null) {
            iContentManagerCallback.onContentLoadingError("Context is null");
        }
    }

    public boolean isRequestedDrugMonographCached(DrugMonograph drugMonograph, DrugMonograph drugMonograph2) {
        if (drugMonograph == null || drugMonograph2 == null || drugMonograph.idType == null || drugMonograph.id == null || drugMonograph.FDB_id == null || drugMonograph.monographId == null || !drugMonograph.idType.equals(drugMonograph2.idType) || !drugMonograph.FDB_id.equals(drugMonograph2.FDB_id) || !drugMonograph.monographId.equals(drugMonograph2.monographId)) {
            return false;
        }
        return true;
    }
}
