package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.BitSet;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzj();
    private final Account account;
    private final String zzj;
    private final boolean zzk;
    private final zzk[] zzl;

    zzh(zzk[] zzkArr, String str, boolean z, Account account2) {
        this.zzl = zzkArr;
        this.zzj = str;
        this.zzk = z;
        this.account = account2;
    }

    zzh(String str, boolean z, Account account2, zzk... zzkArr) {
        this(zzkArr, str, z, account2);
        if (zzkArr != null) {
            BitSet bitSet = new BitSet(zzq.zzy.length);
            for (zzk zzk2 : zzkArr) {
                int i = zzk2.zzs;
                if (i != -1) {
                    if (bitSet.get(i)) {
                        String valueOf = String.valueOf(zzq.zza(i));
                        throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate global search section type ".concat(valueOf) : new String("Duplicate global search section type "));
                    }
                    bitSet.set(i);
                }
            }
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 1, this.zzl, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 4, this.account, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzj, Boolean.valueOf(this.zzk), this.account, Integer.valueOf(Arrays.hashCode(this.zzl)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzh) {
            zzh zzh = (zzh) obj;
            if (!Objects.equal(this.zzj, zzh.zzj) || !Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(zzh.zzk)) || !Objects.equal(this.account, zzh.account) || !Arrays.equals(this.zzl, zzh.zzl)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
