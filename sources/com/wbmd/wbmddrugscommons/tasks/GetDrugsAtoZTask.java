package com.wbmd.wbmddrugscommons.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.example.wbmddrugscommons.R;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.model.Drug;
import com.wbmd.wbmddrugscommons.parsers.DrugsResponseParser;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import webmd.com.environmentswitcher.EnvironmentManager;

public class GetDrugsAtoZTask extends AsyncTask<Void, Void, List<Drug>> {
    static final String TAG = GetDrugsAtoZTask.class.getSimpleName();
    ICallbackEvent<List<Drug>, String> mCallback;
    private final Context mContext;

    private String azFieldList() {
        return "drug_id_s,title,status_code_s,istop_s,mono_id_s,url_s";
    }

    public GetDrugsAtoZTask(Context context, ICallbackEvent<List<Drug>, String> iCallbackEvent) {
        this.mContext = context;
        this.mCallback = iCallbackEvent;
    }

    /* access modifiers changed from: protected */
    public List<Drug> doInBackground(Void... voidArr) {
        if (this.mContext == null) {
            return null;
        }
        String format = String.format("?q=asset_type:AZFDBDRUGS&fl=%s&rows=99999&cache_2=true", new Object[]{azFieldList()});
        EnvironmentManager instance = EnvironmentManager.getInstance(this.mContext);
        String str = instance.getDrugListLink() + format;
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        HashMap hashMap = new HashMap();
        hashMap.put("enc_data", clientSecretHashForTimestamp);
        hashMap.put("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        hashMap.put("client_id", instance.getClientId());
        try {
            return new DrugsResponseParser(VolleyRestClient.getInstance(this.mContext).get(str, hashMap)).parse();
        } catch (InterruptedException e) {
            Trace.e(TAG, e.getMessage());
            return null;
        } catch (ExecutionException e2) {
            Trace.e(TAG, e2.getMessage());
            return null;
        } catch (TimeoutException e3) {
            Trace.e(TAG, e3.getMessage());
            return null;
        } catch (JSONException e4) {
            Trace.e(TAG, e4.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<Drug> list) {
        if (list != null) {
            this.mCallback.onCompleted(list);
        } else {
            this.mCallback.onError(this.mContext.getString(R.string.general_error_no_drugs));
        }
    }
}
