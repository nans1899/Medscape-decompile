package com.wbmd.wbmddrugscommons.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.example.wbmddrugscommons.R;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.wbmd.wbmddrugscommons.model.DrugIdType;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import com.wbmd.wbmddrugscommons.parsers.DrugMonographParser;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import net.bytebuddy.description.type.TypeDescription;
import webmd.com.environmentswitcher.EnvironmentManager;

public class GetDrugMonographTask extends AsyncTask<DrugMonograph, Void, DrugMonograph> {
    static final String TAG = GetDrugMonographTask.class.getSimpleName();
    public static final int TIMEOUT = 60000;
    ICallbackEvent<DrugMonograph, String> mCallback;
    private final Context mContext;

    public GetDrugMonographTask(Context context, ICallbackEvent<DrugMonograph, String> iCallbackEvent) {
        this.mContext = context;
        this.mCallback = iCallbackEvent;
    }

    /* access modifiers changed from: protected */
    public DrugMonograph doInBackground(DrugMonograph... drugMonographArr) {
        String str;
        if (this.mContext == null || drugMonographArr.length == 0) {
            return null;
        }
        DrugMonograph drugMonograph = drugMonographArr[0];
        if (drugMonograph.idType == DrugIdType.NDC) {
            str = "ndc=" + drugMonograph.ndc;
        } else if (drugMonograph.idType == DrugIdType.DRUG_DB_ID) {
            str = "drugid=" + drugMonograph.FDB_id;
        } else if (drugMonograph.idType == null) {
            String[] split = drugMonograph.FDB_id.split("-");
            if (split.length > 1 && split[0] != null && split[0].equalsIgnoreCase("drug")) {
                if (split[1] != null) {
                    drugMonograph.FDB_id = "FDB_" + split[1];
                }
                if (split.length > 2 && split[2] != null) {
                    drugMonograph.monographId = split[2];
                }
            }
            str = "drugid=" + drugMonograph.FDB_id;
        } else {
            str = "";
        }
        if (!StringExtensions.isNullOrEmpty(drugMonograph.monographId) && drugMonograph.monographId.trim().length() > 0) {
            str = str.concat(String.format("&monoid=%s", new Object[]{drugMonograph.monographId}));
        }
        String concat = str.concat(String.format("&lang=%s", new Object[]{Constants.LANGUAGE_LOCALE}));
        EnvironmentManager instance = EnvironmentManager.getInstance(this.mContext);
        String str2 = instance.getMonographLink() + TypeDescription.Generic.OfWildcardType.SYMBOL + concat;
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        HashMap hashMap = new HashMap();
        hashMap.put("enc_data", clientSecretHashForTimestamp);
        hashMap.put("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        hashMap.put("client_id", instance.getClientId());
        try {
            return new DrugMonographParser(drugMonograph, VolleyRestClient.getInstance(this.mContext).get(str2, hashMap)).parse();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(DrugMonograph drugMonograph) {
        if (drugMonograph != null) {
            ICallbackEvent<DrugMonograph, String> iCallbackEvent = this.mCallback;
            if (iCallbackEvent != null) {
                iCallbackEvent.onCompleted(drugMonograph);
                return;
            }
            return;
        }
        ICallbackEvent<DrugMonograph, String> iCallbackEvent2 = this.mCallback;
        if (iCallbackEvent2 != null) {
            iCallbackEvent2.onError(this.mContext.getString(R.string.drug_error_fetching));
        }
    }
}
