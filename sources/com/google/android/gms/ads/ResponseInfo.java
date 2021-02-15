package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzvr;
import com.google.android.gms.internal.ads.zzwm;
import com.google.android.gms.internal.ads.zzyn;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class ResponseInfo {
    private final zzyn zzadt;
    private final List<AdapterResponseInfo> zzadu = new ArrayList();

    private ResponseInfo(zzyn zzyn) {
        this.zzadt = zzyn;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            try {
                List<zzvr> adapterResponses = this.zzadt.getAdapterResponses();
                if (adapterResponses != null) {
                    for (zzvr zza : adapterResponses) {
                        this.zzadu.add(AdapterResponseInfo.zza(zza));
                    }
                }
            } catch (RemoteException e) {
                zzbbq.zzc("Could not forward getAdapterResponseInfo to ResponseInfo.", e);
            }
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzadt.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbq.zzc("Could not forward getMediationAdapterClassName to ResponseInfo.", e);
            return null;
        }
    }

    public final String getResponseId() {
        try {
            return this.zzadt.getResponseId();
        } catch (RemoteException e) {
            zzbbq.zzc("Could not forward getResponseId to ResponseInfo.", e);
            return null;
        }
    }

    public final List<AdapterResponseInfo> getAdapterResponses() {
        return this.zzadu;
    }

    public final JSONObject zzdp() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String responseId = getResponseId();
        if (responseId == null) {
            jSONObject.put("Response ID", "null");
        } else {
            jSONObject.put("Response ID", responseId);
        }
        String mediationAdapterClassName = getMediationAdapterClassName();
        if (mediationAdapterClassName == null) {
            jSONObject.put("Mediation Adapter Class Name", "null");
        } else {
            jSONObject.put("Mediation Adapter Class Name", mediationAdapterClassName);
        }
        JSONArray jSONArray = new JSONArray();
        for (AdapterResponseInfo zzdp : this.zzadu) {
            jSONArray.put(zzdp.zzdp());
        }
        jSONObject.put("Adapter Responses", jSONArray);
        return jSONObject;
    }

    public final String toString() {
        try {
            return zzdp().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }

    public static ResponseInfo zza(zzyn zzyn) {
        if (zzyn != null) {
            return new ResponseInfo(zzyn);
        }
        return null;
    }
}
