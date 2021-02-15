package com.google.android.gms.ads.query;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.internal.ads.zzaru;
import com.google.android.gms.internal.ads.zzyw;
import com.google.android.gms.internal.ads.zzzj;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class QueryInfo {
    private final zzzj zzhls;

    public QueryInfo(zzzj zzzj) {
        this.zzhls = zzzj;
    }

    public String getQuery() {
        return this.zzhls.getQuery();
    }

    public Bundle getQueryBundle() {
        return this.zzhls.getQueryBundle();
    }

    public String getRequestId() {
        return zzzj.zza(this);
    }

    public static void generate(Context context, AdFormat adFormat, AdRequest adRequest, QueryInfoGenerationCallback queryInfoGenerationCallback) {
        zzyw zzyw;
        if (adRequest == null) {
            zzyw = null;
        } else {
            zzyw = adRequest.zzdr();
        }
        new zzaru(context, adFormat, zzyw).zza(queryInfoGenerationCallback);
    }
}
