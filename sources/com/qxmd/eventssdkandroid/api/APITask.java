package com.qxmd.eventssdkandroid.api;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import com.qxmd.eventssdkandroid.managers.InternetConnectivityManager;
import com.qxmd.eventssdkandroid.managers.QXEEventsManager;
import com.qxmd.eventssdkandroid.managers.TaskManager;
import com.qxmd.eventssdkandroid.model.QxError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class APITask extends BackgroundTask {
    protected OnCompletionBlock completionBlock;
    protected Context context;
    public boolean hasStarted;
    private int indexOfLastResponse;
    public boolean isRunning;
    public int maxRetryAttempts;
    protected MultiRequestOnCompletionBlock multiRequestOnCompletionBlock;
    protected List<OnPrepareRequestBlock> prepareRequestBlocks;
    protected List<OnProcessResponseBlock> processResponseBlocks;
    protected List<APIResponse> responses;
    protected int retryNumber;
    public boolean shouldAutoRetryTimeouts;
    public String taskId;

    public interface MultiRequestOnCompletionBlock {
        Bundle onCompletion(List<APIResponse> list, boolean z, List<QxError> list2);
    }

    public interface OnCompletionBlock {
        Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list);
    }

    public interface OnPrepareRequestBlock {
        APIRequest prepareRequest();
    }

    public interface OnProcessResponseBlock {
        APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list);
    }

    public APITask(APITask aPITask) {
        this(aPITask.prepareRequestBlocks, aPITask.processResponseBlocks, aPITask.completionBlock, aPITask.multiRequestOnCompletionBlock, aPITask.taskId, aPITask.context);
        this.shouldAutoRetryTimeouts = aPITask.shouldAutoRetryTimeouts;
        this.maxRetryAttempts = aPITask.maxRetryAttempts;
    }

    public APITask(OnPrepareRequestBlock onPrepareRequestBlock, OnProcessResponseBlock onProcessResponseBlock, OnCompletionBlock onCompletionBlock, String str, Context context2) {
        this(Arrays.asList(new OnPrepareRequestBlock[]{onPrepareRequestBlock}), Arrays.asList(new OnProcessResponseBlock[]{onProcessResponseBlock}), onCompletionBlock, (MultiRequestOnCompletionBlock) null, str, context2);
    }

    public APITask(List<OnPrepareRequestBlock> list, List<OnProcessResponseBlock> list2, MultiRequestOnCompletionBlock multiRequestOnCompletionBlock2, String str, Context context2) {
        this(list, list2, (OnCompletionBlock) null, multiRequestOnCompletionBlock2, str, context2);
    }

    public APITask(List<OnPrepareRequestBlock> list, List<OnProcessResponseBlock> list2, OnCompletionBlock onCompletionBlock, MultiRequestOnCompletionBlock multiRequestOnCompletionBlock2, String str, Context context2) {
        super(context2);
        this.indexOfLastResponse = 0;
        this.prepareRequestBlocks = list;
        this.processResponseBlocks = list2;
        this.completionBlock = onCompletionBlock;
        this.multiRequestOnCompletionBlock = multiRequestOnCompletionBlock2;
        this.taskId = str;
        this.context = context2;
        this.responses = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.responses.add((Object) null);
        }
        this.shouldAutoRetryTimeouts = true;
        this.maxRetryAttempts = 2;
        this.retryNumber = 0;
        Set<QXEEventsManager.QXEEventsManagerListener> listenersForTaskId = QXEEventsManager.getInstance().getListenersForTaskId(str);
        if (listenersForTaskId != null) {
            for (QXEEventsManager.QXEEventsManagerListener dataManagerMethodQueued : listenersForTaskId) {
                dataManagerMethodQueued.dataManagerMethodQueued(str);
            }
        }
    }

    public void startTask() {
        startTask(false);
    }

    public void startTask(boolean z) {
        List<OnPrepareRequestBlock> list = this.prepareRequestBlocks;
        if (list != null && !list.isEmpty() && !isCancelled()) {
            this.isRunning = true;
            this.hasStarted = true;
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            Set<QXEEventsManager.QXEEventsManagerListener> listenersForTaskId = QXEEventsManager.getInstance().getListenersForTaskId(this.taskId);
            if (listenersForTaskId != null) {
                for (QXEEventsManager.QXEEventsManagerListener dataManagerMethodStarted : listenersForTaskId) {
                    dataManagerMethodStarted.dataManagerMethodStarted(this.taskId);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<APIResponse> doInBackground(Void... voidArr) {
        APIResponse aPIResponse;
        for (int i = 0; i < this.prepareRequestBlocks.size(); i++) {
            this.indexOfLastResponse = i;
            if (isCancelled()) {
                return this.responses;
            }
            APIRequest prepareRequest = this.prepareRequestBlocks.get(i).prepareRequest();
            if (prepareRequest != null) {
                if (InternetConnectivityManager.getInstance().isConnectedToInternet()) {
                    aPIResponse = prepareRequest.send();
                } else {
                    aPIResponse = new APIResponse();
                    aPIResponse.httpStatusCode = -1;
                }
                APIResponse processResponse = this.processResponseBlocks.get(i).processResponse(aPIResponse, aPIResponse.success(), aPIResponse.errors);
                this.responses.set(i, processResponse);
                if (!processResponse.success()) {
                    return this.responses;
                }
                this.responses.set(i, processResponse);
            } else {
                APIResponse aPIResponse2 = new APIResponse();
                aPIResponse2.isNonCalledResponse = true;
                this.responses.set(i, this.processResponseBlocks.get(i).processResponse(aPIResponse2, false, aPIResponse2.errors));
            }
        }
        return this.responses;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<APIResponse> list) {
        Bundle bundle;
        int i;
        boolean z = false;
        this.isRunning = false;
        if (!isCancelled()) {
            APIResponse aPIResponse = list.get(this.indexOfLastResponse);
            if (aPIResponse != null) {
                Integer valueOf = Integer.valueOf(aPIResponse.httpStatusCode);
                if ((valueOf.intValue() == 258 || valueOf.intValue() == 408 || valueOf.intValue() == 504) && this.shouldAutoRetryTimeouts && (i = this.retryNumber) < this.maxRetryAttempts) {
                    this.retryNumber = i + 1;
                    TaskManager.getInstance().copyTask(this, true);
                    return;
                }
            }
            TaskManager.getInstance().removeTask(this);
            if (this.completionBlock != null || this.multiRequestOnCompletionBlock != null) {
                List<QxError> list2 = null;
                if (aPIResponse != null) {
                    z = aPIResponse.success();
                    list2 = aPIResponse.errors;
                }
                OnCompletionBlock onCompletionBlock = this.completionBlock;
                if (onCompletionBlock != null) {
                    bundle = onCompletionBlock.onCompletion(list.get(this.indexOfLastResponse), z, list2);
                } else {
                    bundle = this.multiRequestOnCompletionBlock.onCompletion(list, z, list2);
                }
                Set<QXEEventsManager.QXEEventsManagerListener> listenersForTaskId = QXEEventsManager.getInstance().getListenersForTaskId(this.taskId);
                if (listenersForTaskId != null) {
                    for (QXEEventsManager.QXEEventsManagerListener dataManagerMethodFinished : listenersForTaskId) {
                        dataManagerMethodFinished.dataManagerMethodFinished(this.taskId, z, list2, bundle);
                    }
                }
            }
        }
    }
}
