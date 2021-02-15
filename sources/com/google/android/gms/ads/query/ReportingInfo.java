package com.google.android.gms.ads.query;

import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzasb;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class ReportingInfo {
    private final zzarw zzhlt;

    private ReportingInfo(Builder builder) {
        this.zzhlt = new zzarw(builder.zzhlu);
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public final zzasb zzhlu;

        public Builder(View view) {
            zzasb zzasb = new zzasb();
            this.zzhlu = zzasb;
            zzasb.zzk(view);
        }

        public final Builder setAssetViews(Map<String, View> map) {
            this.zzhlu.zzh(map);
            return this;
        }

        public final ReportingInfo build() {
            return new ReportingInfo(this);
        }
    }

    public final void updateImpressionUrls(List<Uri> list, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        this.zzhlt.updateImpressionUrls(list, updateImpressionUrlsCallback);
    }

    public final void updateClickUrl(Uri uri, UpdateClickUrlCallback updateClickUrlCallback) {
        this.zzhlt.updateClickUrl(uri, updateClickUrlCallback);
    }

    public final void reportTouchEvent(MotionEvent motionEvent) {
        this.zzhlt.reportTouchEvent(motionEvent);
    }
}
