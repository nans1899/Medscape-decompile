package com.medscape.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.appindexing.Indexable;
import com.medscape.android.util.JSONParser;
import com.medscape.android.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class GetOpenMyInvitationsCount extends AsyncTask<String, Void, JSONObject> {
    private Context mContext;
    private GetOpenMyInvitationsCountListener mListener;
    private String mOpenInvitationServiceEndpoint;
    private boolean mTestMode;
    private String testResult = "{\"campaigns\":[{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475},{\"cid\":3475}]}";

    public interface GetOpenMyInvitationsCountListener {
        void onGetOpenMyInvitationsCountComplete(JSONObject jSONObject) throws JSONException;

        void onGetOpenMyInvitationsCountError();

        void onGetOpenMyInvitationsCountNoResults();
    }

    public GetOpenMyInvitationsCount(GetOpenMyInvitationsCountListener getOpenMyInvitationsCountListener, Context context, String str, boolean z) {
        this.mListener = getOpenMyInvitationsCountListener;
        this.mContext = context;
        this.mTestMode = z;
        this.mOpenInvitationServiceEndpoint = str;
    }

    /* access modifiers changed from: protected */
    public JSONObject doInBackground(String... strArr) {
        JSONParser jSONParser = new JSONParser();
        JSONObject jSONObject = new JSONObject();
        Util.TIMEOUT = Indexable.MAX_STRING_LENGTH;
        return (this.mTestMode || isCancelled()) ? jSONObject : jSONParser.getJSONFromUrl(this.mOpenInvitationServiceEndpoint, false, this.mContext);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(JSONObject jSONObject) {
        Util.TIMEOUT = 60000;
        if (isCancelled() || jSONObject == null) {
            GetOpenMyInvitationsCountListener getOpenMyInvitationsCountListener = this.mListener;
            if (getOpenMyInvitationsCountListener != null) {
                getOpenMyInvitationsCountListener.onGetOpenMyInvitationsCountNoResults();
                return;
            }
            return;
        }
        try {
            if (this.mListener == null) {
                return;
            }
            if (!this.mTestMode) {
                this.mListener.onGetOpenMyInvitationsCountComplete(jSONObject);
            } else {
                this.mListener.onGetOpenMyInvitationsCountComplete(new JSONObject(this.testResult));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            GetOpenMyInvitationsCountListener getOpenMyInvitationsCountListener2 = this.mListener;
            if (getOpenMyInvitationsCountListener2 != null) {
                getOpenMyInvitationsCountListener2.onGetOpenMyInvitationsCountError();
            }
        }
    }
}
