package com.wbmd.qxcalculator.model.api;

import android.os.AsyncTask;
import android.os.Bundle;
import com.wbmd.qxcalculator.managers.AuthManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.InternetConnectivityManager;
import com.wbmd.qxcalculator.managers.TaskManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.util.CrashLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class APITask extends AsyncTask<Void, Integer, List<APIResponse>> {
    protected OnCompletionBlock completionBlock;
    public boolean hasStarted;
    private int indexOfLastResponse;
    public boolean isRunning;
    public int maxRetryAttempts;
    protected MultiRequestOnCompletionBlock multiRequestOnCompletionBlock;
    protected List<OnPrepareRequestBlock> prepareRequestBlocks;
    protected List<OnProcessResponseBlock> processResponseBlocks;
    public String requiredAuthKey;
    protected List<APIResponse> responses;
    public int retryNumber;
    public boolean shouldAutoReauth;
    public boolean shouldAutoRetryTimeouts;
    public boolean success;
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
        APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask);
    }

    public APITask(APITask aPITask) {
        this(aPITask.prepareRequestBlocks, aPITask.processResponseBlocks, aPITask.completionBlock, aPITask.multiRequestOnCompletionBlock, aPITask.taskId, aPITask.requiredAuthKey);
        this.shouldAutoReauth = aPITask.shouldAutoReauth;
        this.shouldAutoRetryTimeouts = aPITask.shouldAutoRetryTimeouts;
        this.maxRetryAttempts = aPITask.maxRetryAttempts;
        this.requiredAuthKey = aPITask.requiredAuthKey;
        this.retryNumber = aPITask.retryNumber;
    }

    public APITask(OnPrepareRequestBlock onPrepareRequestBlock, OnProcessResponseBlock onProcessResponseBlock, OnCompletionBlock onCompletionBlock, String str) {
        this(Arrays.asList(new OnPrepareRequestBlock[]{onPrepareRequestBlock}), Arrays.asList(new OnProcessResponseBlock[]{onProcessResponseBlock}), onCompletionBlock, (MultiRequestOnCompletionBlock) null, str, (String) null);
    }

    public APITask(List<OnPrepareRequestBlock> list, List<OnProcessResponseBlock> list2, MultiRequestOnCompletionBlock multiRequestOnCompletionBlock2, String str) {
        this(list, list2, (OnCompletionBlock) null, multiRequestOnCompletionBlock2, str, (String) null);
    }

    public APITask(OnPrepareRequestBlock onPrepareRequestBlock, OnProcessResponseBlock onProcessResponseBlock, OnCompletionBlock onCompletionBlock, String str, String str2) {
        this(Arrays.asList(new OnPrepareRequestBlock[]{onPrepareRequestBlock}), Arrays.asList(new OnProcessResponseBlock[]{onProcessResponseBlock}), onCompletionBlock, (MultiRequestOnCompletionBlock) null, str, str2);
    }

    public APITask(List<OnPrepareRequestBlock> list, List<OnProcessResponseBlock> list2, MultiRequestOnCompletionBlock multiRequestOnCompletionBlock2, String str, String str2) {
        this(list, list2, (OnCompletionBlock) null, multiRequestOnCompletionBlock2, str, str2);
    }

    public APITask(List<OnPrepareRequestBlock> list, List<OnProcessResponseBlock> list2, OnCompletionBlock onCompletionBlock, MultiRequestOnCompletionBlock multiRequestOnCompletionBlock2, String str, String str2) {
        this.success = false;
        this.indexOfLastResponse = 0;
        this.prepareRequestBlocks = list;
        this.processResponseBlocks = list2;
        this.completionBlock = onCompletionBlock;
        this.multiRequestOnCompletionBlock = multiRequestOnCompletionBlock2;
        this.taskId = str;
        this.requiredAuthKey = str2;
        this.responses = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.responses.add((Object) null);
        }
        this.shouldAutoReauth = str2 == null;
        this.shouldAutoRetryTimeouts = true;
        this.maxRetryAttempts = 1;
        this.retryNumber = 0;
        Set<DataManager.DataManagerListener> listenersForTaskId = DataManager.getInstance().getListenersForTaskId(str);
        if (listenersForTaskId != null) {
            for (DataManager.DataManagerListener dataManagerMethodQueued : listenersForTaskId) {
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
            if (!AuthManager.getInstance().reauthorizationInProgress() || z) {
                String str = this.requiredAuthKey;
                if (str == null || str.equals(UserManager.getInstance().getAuthKey())) {
                    this.isRunning = true;
                    this.hasStarted = true;
                    executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    Set<DataManager.DataManagerListener> listenersForTaskId = DataManager.getInstance().getListenersForTaskId(this.taskId);
                    if (listenersForTaskId != null) {
                        for (DataManager.DataManagerListener dataManagerMethodStarted : listenersForTaskId) {
                            dataManagerMethodStarted.dataManagerMethodStarted(this.taskId);
                        }
                        return;
                    }
                    return;
                }
                TaskManager.getInstance().removeTask(this);
                if (this.completionBlock != null) {
                    QxError qxError = new QxError(QxError.ErrorType.API, 401, (String) null, (String) null);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(qxError);
                    this.completionBlock.onCompletion((APIResponse) null, false, arrayList);
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
                this.responses.set(i, aPIResponse);
                this.success = aPIResponse.success();
                APIResponse processResponse = this.processResponseBlocks.get(i).processResponse(aPIResponse, this.success, aPIResponse.errors, this);
                if (!processResponse.success()) {
                    return this.responses;
                }
                this.responses.set(i, processResponse);
            } else {
                APIResponse aPIResponse2 = new APIResponse();
                aPIResponse2.isNonCalledResponse = true;
                this.responses.set(i, this.processResponseBlocks.get(i).processResponse(aPIResponse2, this.success, aPIResponse2.errors, this));
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
            ArrayList<QxError> arrayList = null;
            if (aPIResponse != null) {
                Integer valueOf = Integer.valueOf(aPIResponse.httpStatusCode);
                if ((valueOf.intValue() == 258 || valueOf.intValue() == 408 || valueOf.intValue() == 504) && this.shouldAutoRetryTimeouts) {
                    if (this.taskId == null) {
                        CrashLogger instance = CrashLogger.getInstance();
                        instance.leaveBreadcrumb("api task timeout, success: " + this.success);
                    } else {
                        CrashLogger instance2 = CrashLogger.getInstance();
                        instance2.leaveBreadcrumb("api task timeout, taskID: " + this.taskId + "; success: " + this.success);
                    }
                    int i2 = this.retryNumber;
                    if (i2 < this.maxRetryAttempts) {
                        this.retryNumber = i2 + 1;
                        CrashLogger.getInstance().leaveBreadcrumb("retry timeout request");
                        TaskManager.getInstance().copyTask(this, true);
                        return;
                    }
                } else if (aPIResponse.invalidAuthKey() && this.shouldAutoReauth) {
                    if (this.taskId == null) {
                        CrashLogger instance3 = CrashLogger.getInstance();
                        instance3.leaveBreadcrumb("api task invalid auth, success: " + this.success);
                    } else {
                        CrashLogger instance4 = CrashLogger.getInstance();
                        instance4.leaveBreadcrumb("api task invalid auth, taskID: " + this.taskId + "; success: " + this.success);
                    }
                    int i3 = this.retryNumber;
                    if (i3 < this.maxRetryAttempts) {
                        this.retryNumber = i3 + 1;
                        CrashLogger.getInstance().leaveBreadcrumb("invalid auth key");
                        TaskManager.getInstance().copyTask(this, false);
                        if (!AuthManager.getInstance().reauthorizationInProgress()) {
                            AuthManager.getInstance().sendReauthorizationRequest();
                            return;
                        }
                        return;
                    }
                } else if (aPIResponse.invalidDeviceId() && (i = this.retryNumber) < this.maxRetryAttempts) {
                    this.retryNumber = i + 1;
                    UserManager.getInstance().setDeviceId((String) null);
                    TaskManager.getInstance().copyTask(this, true);
                    return;
                }
            }
            TaskManager.getInstance().removeTask(this);
            if (this.completionBlock != null || this.multiRequestOnCompletionBlock != null) {
                if (aPIResponse != null) {
                    z = aPIResponse.success();
                    arrayList = aPIResponse.errors;
                }
                if (this.taskId == null) {
                    CrashLogger instance5 = CrashLogger.getInstance();
                    instance5.leaveBreadcrumb("api task on completion, success: " + z);
                } else {
                    CrashLogger instance6 = CrashLogger.getInstance();
                    instance6.leaveBreadcrumb("api task on completion, taskID: " + this.taskId + "; success: " + z);
                }
                OnCompletionBlock onCompletionBlock = this.completionBlock;
                if (onCompletionBlock != null) {
                    bundle = onCompletionBlock.onCompletion(aPIResponse, z, arrayList);
                } else {
                    bundle = this.multiRequestOnCompletionBlock.onCompletion(list, z, arrayList);
                }
                if (aPIResponse != null) {
                    z = aPIResponse.success();
                    arrayList = aPIResponse.errors;
                }
                Set<DataManager.DataManagerListener> listenersForTaskId = DataManager.getInstance().getListenersForTaskId(this.taskId);
                if (listenersForTaskId != null) {
                    for (DataManager.DataManagerListener dataManagerMethodFinished : listenersForTaskId) {
                        dataManagerMethodFinished.dataManagerMethodFinished(this.taskId, z, arrayList, bundle);
                    }
                }
            }
        }
    }

    public void reauthFailed(List<QxError> list) {
        Bundle bundle;
        TaskManager.getInstance().removeTask(this);
        OnCompletionBlock onCompletionBlock = this.completionBlock;
        if (onCompletionBlock != null) {
            bundle = onCompletionBlock.onCompletion(this.responses.get(this.indexOfLastResponse), false, list);
        } else {
            bundle = this.multiRequestOnCompletionBlock.onCompletion(this.responses, false, list);
        }
        Set<DataManager.DataManagerListener> listenersForTaskId = DataManager.getInstance().getListenersForTaskId(this.taskId);
        if (listenersForTaskId != null) {
            for (DataManager.DataManagerListener dataManagerMethodFinished : listenersForTaskId) {
                dataManagerMethodFinished.dataManagerMethodFinished(this.taskId, this.success, list, bundle);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled(List<APIResponse> list) {
        TaskManager.getInstance().removeTask(this);
    }
}
