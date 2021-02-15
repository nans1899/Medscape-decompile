package com.qxmd.eventssdkandroid.api;

import android.content.Context;
import android.os.AsyncTask;
import com.qxmd.eventssdkandroid.managers.TaskManager;
import java.util.List;

public abstract class BackgroundTask extends AsyncTask<Void, Integer, List<APIResponse>> {
    protected Context context;

    public BackgroundTask() {
    }

    public BackgroundTask(Context context2) {
        this.context = context2;
    }

    public void startTask() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<APIResponse> list) {
        TaskManager.getInstance().removeTask(this);
    }

    /* access modifiers changed from: protected */
    public void onCancelled(List<APIResponse> list) {
        TaskManager.getInstance().removeTask(this);
    }
}
