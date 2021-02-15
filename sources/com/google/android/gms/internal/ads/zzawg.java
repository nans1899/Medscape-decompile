package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzawg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzawg> CREATOR = new zzawj();
    public final String zzdxc;
    public final String zzdxd;
    public final boolean zzdxe;
    public final boolean zzdxf;
    public final List<String> zzdxg;
    public final boolean zzdxh;
    public final boolean zzdxi;
    public final List<String> zzdxj;

    public zzawg(String str, String str2, boolean z, boolean z2, List<String> list, boolean z3, boolean z4, List<String> list2) {
        this.zzdxc = str;
        this.zzdxd = str2;
        this.zzdxe = z;
        this.zzdxf = z2;
        this.zzdxg = list;
        this.zzdxh = z3;
        this.zzdxi = z4;
        this.zzdxj = list2 == null ? new ArrayList<>() : list2;
    }

    public static zzawg zzg(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new zzawg(jSONObject.optString("click_string", ""), jSONObject.optString("report_url", ""), jSONObject.optBoolean("rendered_ad_enabled", false), jSONObject.optBoolean("non_malicious_reporting_enabled", false), zzbao.zza(jSONObject.optJSONArray("allowed_headers"), (List<String>) null), jSONObject.optBoolean("protection_enabled", false), jSONObject.optBoolean("malicious_reporting_enabled", false), zzbao.zza(jSONObject.optJSONArray("webview_permissions"), (List<String>) null));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdxc, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdxd, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzdxe);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdxf);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzdxg, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzdxh);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdxi);
        SafeParcelWriter.writeStringList(parcel, 9, this.zzdxj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
