package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.appindexing.Action;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zza extends AbstractSafeParcelable implements Action {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private final String zzar;
    private final Bundle zzay;
    private final String zzeg;
    private final String zzeh;
    private final String zzei;
    private final zzc zzej;
    private final String zzek;

    public zza(String str, String str2, String str3, String str4, zzc zzc, String str5, Bundle bundle) {
        this.zzar = str;
        this.zzeg = str2;
        this.zzeh = str3;
        this.zzei = str4;
        this.zzej = zzc;
        this.zzek = str5;
        if (bundle != null) {
            this.zzay = bundle;
        } else {
            this.zzay = Bundle.EMPTY;
        }
        this.zzay.setClassLoader(getClass().getClassLoader());
    }

    public final zzc zzab() {
        return this.zzej;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzar, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzeg, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzeh, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzei, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzej, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzek, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzay, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionImpl { ");
        sb.append("{ actionType: '");
        sb.append(this.zzar);
        sb.append("' } ");
        sb.append("{ objectName: '");
        sb.append(this.zzeg);
        sb.append("' } ");
        sb.append("{ objectUrl: '");
        sb.append(this.zzeh);
        sb.append("' } ");
        if (this.zzei != null) {
            sb.append("{ objectSameAs: '");
            sb.append(this.zzei);
            sb.append("' } ");
        }
        if (this.zzej != null) {
            sb.append("{ metadata: '");
            sb.append(this.zzej.toString());
            sb.append("' } ");
        }
        if (this.zzek != null) {
            sb.append("{ actionStatus: '");
            sb.append(this.zzek);
            sb.append("' } ");
        }
        if (!this.zzay.isEmpty()) {
            sb.append("{ ");
            sb.append(this.zzay);
            sb.append(" } ");
        }
        sb.append("}");
        return sb.toString();
    }
}
