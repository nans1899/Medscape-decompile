package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.api.APIRequest;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.api.APITask;
import java.util.List;

public class AuthManager {
    public static final String KEY_NEEDS_LOGIN = "KEY_NEEDS_LOGIN";
    private static final String TASK_ID_REAUTH = "TASK_ID_REAUTH";
    protected static Context context;
    private static AuthManager ourInstance;
    /* access modifiers changed from: private */
    public boolean reauthorizationInProgress;

    public static AuthManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AuthManager();
        }
        return ourInstance;
    }

    public void setContext(Context context2) {
        context = context2;
    }

    private AuthManager() {
    }

    public boolean reauthorizationInProgress() {
        return this.reauthorizationInProgress;
    }

    public void sendReauthorizationRequest() {
        if (!this.reauthorizationInProgress) {
            this.reauthorizationInProgress = true;
            new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
                public APIRequest prepareRequest() {
                    return APIRequest.loginUserWithAccounts(UserManager.getInstance().getUserEmail(), UserManager.getInstance().getPassword(), UserManager.getInstance().getActiveUserFacebookAccessToken(), UserManager.getInstance().getFacebookId(), (Long) null);
                }
            }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
                public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                    if (z) {
                        aPIResponse.user.password = UserManager.getInstance().getPassword();
                        aPIResponse.user.facebookAccessToken = UserManager.getInstance().getActiveUserFacebookAccessToken();
                        aPIResponse.user.facebookId = UserManager.getInstance().getFacebookId();
                        DataManager.getInstance().updateUserLocally(aPIResponse.user);
                    }
                    return aPIResponse;
                }
            }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
                public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                    if (z || Integer.valueOf(aPIResponse.httpStatusCode).intValue() != 400) {
                        boolean unused = AuthManager.this.reauthorizationInProgress = false;
                        TaskManager.getInstance().reauthorizationCompleted(z, list);
                        return null;
                    }
                    LocalBroadcastManager.getInstance(AuthManager.context).sendBroadcastSync(new Intent(AuthManager.KEY_NEEDS_LOGIN));
                    return null;
                }
            }, TASK_ID_REAUTH).startTask(true);
        }
    }

    public void didJustReauth() {
        this.reauthorizationInProgress = false;
        TaskManager.getInstance().reauthorizationCompleted(true, (List<QxError>) null);
    }

    public void reset() {
        this.reauthorizationInProgress = false;
    }
}
