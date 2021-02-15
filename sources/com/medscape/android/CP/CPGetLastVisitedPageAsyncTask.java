package com.medscape.android.CP;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.util.JSONParser;
import org.json.JSONObject;

public class CPGetLastVisitedPageAsyncTask extends AsyncTask<Void, Void, JSONObject> {
    private static final String ACTIVITES = "/activities/";
    private static final String CP_URL = "https://api.medscape.com/cp/ipplastvisit/user/";
    private String mActivityId;
    private Context mContext;
    CPGetLastEventListener mListener;

    public interface CPGetLastEventListener {
        void onCPLastEventRecieved(JSONObject jSONObject);
    }

    public CPGetLastVisitedPageAsyncTask(Context context, String str, CPGetLastEventListener cPGetLastEventListener) {
        CookieSyncManager.createInstance(context);
        this.mContext = context;
        this.mListener = cPGetLastEventListener;
        this.mActivityId = str;
    }

    /* access modifiers changed from: protected */
    public JSONObject doInBackground(Void... voidArr) {
        JSONObject jSONObject = new JSONObject();
        if (UserProfileProvider.INSTANCE.getUserProfile() == null) {
            return jSONObject;
        }
        String str = CP_URL + UserProfileProvider.INSTANCE.getUserProfile().getRegisteredId() + ACTIVITES + this.mActivityId;
        Log.d("CP Request", str);
        try {
            return new JSONParser().getJSONFromUrl(str, false, this.mContext);
        } catch (Exception e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mListener.onCPLastEventRecieved(jSONObject);
        }
    }
}
