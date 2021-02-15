package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvp> CREATOR = new zzvo();
    public final int type;
    public final int zzadc;
    public final String zzadd;
    public final long zzade;

    public zzvp(int i, int i2, String str, long j) {
        this.type = i;
        this.zzadc = i2;
        this.zzadd = str;
        this.zzade = j;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.type);
        SafeParcelWriter.writeInt(parcel, 2, this.zzadc);
        SafeParcelWriter.writeString(parcel, 3, this.zzadd, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzade);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzvp zza(JSONObject jSONObject) throws JSONException {
        return new zzvp(jSONObject.getInt("type_num"), jSONObject.getInt("precision_num"), jSONObject.getString(FirebaseAnalytics.Param.CURRENCY), jSONObject.getLong("value"));
    }
}
