package com.wbmd.wbmddrugscommons.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.data.DrugTTSData;
import com.wbmd.wbmddrugscommons.exceptions.ContentParsingException;
import com.wbmd.wbmddrugscommons.model.Tug;
import com.wbmd.wbmddrugscommons.parsers.DrugsTTSParser;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import java.util.HashMap;
import webmd.com.environmentswitcher.EnvironmentManager;

public class GetDrugTTSDataTask extends AsyncTask<Void, Void, Void> {
    static final String TAG = GetDrugTTSDataTask.class.getSimpleName();
    /* access modifiers changed from: private */
    public ICallbackEvent<HashMap<String, Tug>, String> mCallback;
    private final Context mContext;

    public GetDrugTTSDataTask(Context context, ICallbackEvent<HashMap<String, Tug>, String> iCallbackEvent) {
        this.mContext = context;
        this.mCallback = iCallbackEvent;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        Context context = this.mContext;
        if (context == null) {
            ICallbackEvent<HashMap<String, Tug>, String> iCallbackEvent = this.mCallback;
            if (iCallbackEvent != null) {
                iCallbackEvent.onError("Context is null");
            }
            return null;
        }
        VolleyRestClient.getInstance(this.mContext).get(EnvironmentManager.getInstance(context).getTTSDrugsLink(), 0, new ICallbackEvent<String, Exception>() {
            public void onError(Exception exc) {
                GetDrugTTSDataTask.this.mCallback.onError(exc.getMessage());
                Trace.e(GetDrugTTSDataTask.TAG, exc.getMessage());
            }

            public void onCompleted(String str) {
                try {
                    HashMap<String, Tug> parse = new DrugsTTSParser(str).parse();
                    DrugTTSData.get().setTTSData(parse);
                    GetDrugTTSDataTask.this.mCallback.onCompleted(parse);
                } catch (ContentParsingException e) {
                    Trace.e(GetDrugTTSDataTask.TAG, e.getMessage());
                    GetDrugTTSDataTask.this.mCallback.onError(e.getMessage());
                }
            }
        });
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
    }
}
