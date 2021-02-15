package com.google.android.gms.internal.icing;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzg {
    private Account account;
    private List<zzk> zzi;
    private String zzj;
    private boolean zzk;

    public final zzg zza(zzk zzk2) {
        if (this.zzi == null && zzk2 != null) {
            this.zzi = new ArrayList();
        }
        if (zzk2 != null) {
            this.zzi.add(zzk2);
        }
        return this;
    }

    public final zzg zza(String str) {
        this.zzj = str;
        return this;
    }

    public final zzg zza(boolean z) {
        this.zzk = true;
        return this;
    }

    public final zzg zza(Account account2) {
        this.account = account2;
        return this;
    }

    public final zzh zzb() {
        String str = this.zzj;
        boolean z = this.zzk;
        Account account2 = this.account;
        List<zzk> list = this.zzi;
        return new zzh(str, z, account2, list != null ? (zzk[]) list.toArray(new zzk[list.size()]) : null);
    }
}
