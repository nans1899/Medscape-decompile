package io.branch.referral;

import android.content.Context;

public class ServerRequestPing extends ServerRequest {
    public void clearCallbacks() {
    }

    public void handleFailure(int i, String str) {
    }

    public boolean isGetRequest() {
        return false;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
    }

    public ServerRequestPing(Context context) {
        super(context, (String) null);
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        PrefHelper.Debug("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }
}
