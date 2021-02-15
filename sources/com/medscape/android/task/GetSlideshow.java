package com.medscape.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.parser.model.Slideshow;
import com.medscape.android.util.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSlideshow extends AsyncTask<String, Void, JSONObject> {
    Context mContext;
    private boolean mDemoMode = false;
    GetSlideShowListener mListener;
    String slideShowUrl;

    public interface GetSlideShowListener {
        void onSlideshowDownloadError();

        void onSlideshowDownloaded(Slideshow slideshow);
    }

    public GetSlideshow(GetSlideShowListener getSlideShowListener, Context context, boolean z) {
        this.mListener = getSlideShowListener;
        this.mContext = context;
        this.mDemoMode = z;
    }

    /* access modifiers changed from: protected */
    public JSONObject doInBackground(String... strArr) {
        this.slideShowUrl = strArr[0];
        JSONParser jSONParser = new JSONParser();
        if (this.mDemoMode) {
            return jSONParser.getJSONFromLocalUrl(this.slideShowUrl);
        }
        return jSONParser.getJSONFromUrl(strArr[0], false, this.mContext);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.mListener.onSlideshowDownloaded(Slideshow.createFromJSON(jSONObject, this.slideShowUrl));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            this.mListener.onSlideshowDownloadError();
        }
    }
}
