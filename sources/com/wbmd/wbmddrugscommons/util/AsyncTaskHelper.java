package com.wbmd.wbmddrugscommons.util;

import android.os.AsyncTask;
import android.os.Build;
import java.util.concurrent.ExecutorService;

public class AsyncTaskHelper {
    public static <P> void execute(ExecutorService executorService, AsyncTask<P, ?, ?> asyncTask, P... pArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(executorService, pArr);
        } else {
            asyncTask.execute(pArr);
        }
    }

    public static <P> void executeReplacement(ExecutorService executorService, AsyncTask<P, ?, ?> asyncTask, AsyncTask<P, ?, ?> asyncTask2, P... pArr) {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask2.executeOnExecutor(executorService, pArr);
        } else {
            asyncTask2.execute(pArr);
        }
    }

    public static <P> void cancel(ExecutorService executorService, AsyncTask<P, ?, ?> asyncTask) {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
    }
}
