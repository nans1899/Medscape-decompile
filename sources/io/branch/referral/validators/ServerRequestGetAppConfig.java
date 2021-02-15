package io.branch.referral.validators;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerResponse;
import org.json.JSONObject;

class ServerRequestGetAppConfig extends ServerRequest {
    private final IGetAppConfigEvents callback;

    public interface IGetAppConfigEvents {
        void onAppConfigAvailable(JSONObject jSONObject);
    }

    public void clearCallbacks() {
    }

    public boolean handleErrors(Context context) {
        return false;
    }

    public boolean isGetRequest() {
        return true;
    }

    public ServerRequestGetAppConfig(Context context, IGetAppConfigEvents iGetAppConfigEvents) {
        super(context, "");
        this.callback = iGetAppConfigEvents;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        IGetAppConfigEvents iGetAppConfigEvents = this.callback;
        if (iGetAppConfigEvents != null) {
            iGetAppConfigEvents.onAppConfigAvailable(serverResponse.getObject());
        }
    }

    public void handleFailure(int i, String str) {
        IGetAppConfigEvents iGetAppConfigEvents = this.callback;
        if (iGetAppConfigEvents != null) {
            iGetAppConfigEvents.onAppConfigAvailable((JSONObject) null);
        }
    }

    public String getRequestUrl() {
        return this.prefHelper_.getAPIBaseUrl() + Defines.RequestPath.GetApp.getPath() + "/" + this.prefHelper_.getBranchKey();
    }
}
