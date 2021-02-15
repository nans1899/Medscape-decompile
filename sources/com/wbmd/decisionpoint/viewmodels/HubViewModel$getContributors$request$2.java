package com.wbmd.decisionpoint.viewmodels;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/android/volley/VolleyError;", "kotlin.jvm.PlatformType", "onErrorResponse"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubViewModel.kt */
final class HubViewModel$getContributors$request$2 implements Response.ErrorListener {
    final /* synthetic */ HubViewModel this$0;

    HubViewModel$getContributors$request$2(HubViewModel hubViewModel) {
        this.this$0 = hubViewModel;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        this.this$0.getLoadingData().postValue(false);
    }
}
