package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzarx extends zzaxj {
    private final /* synthetic */ QueryInfoGenerationCallback zzdqe;

    zzarx(zzaru zzaru, QueryInfoGenerationCallback queryInfoGenerationCallback) {
        this.zzdqe = queryInfoGenerationCallback;
    }

    public final void zzk(String str, String str2) {
        QueryInfo queryInfo = new QueryInfo(new zzzj(str, (Bundle) null));
        zzwm.zzqb().put(queryInfo, str2);
        this.zzdqe.onSuccess(queryInfo);
    }

    public final void onError(String str) {
        this.zzdqe.onFailure(str);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        QueryInfo queryInfo = new QueryInfo(new zzzj(str, bundle));
        zzwm.zzqb().put(queryInfo, str2);
        this.zzdqe.onSuccess(queryInfo);
    }
}
