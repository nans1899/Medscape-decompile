package com.wbmd.decisionpoint.viewmodels;

import android.view.View;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/android/volley/VolleyError;", "kotlin.jvm.PlatformType", "onErrorResponse"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubViewModel.kt */
final class HubViewModel$getDecisionPointsFromServer$request$2 implements Response.ErrorListener {
    final /* synthetic */ boolean $isAvailable;
    final /* synthetic */ View $layout;
    final /* synthetic */ HubViewModel this$0;

    HubViewModel$getDecisionPointsFromServer$request$2(HubViewModel hubViewModel, boolean z, View view) {
        this.this$0 = hubViewModel;
        this.$isAvailable = z;
        this.$layout = view;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        if (!this.$isAvailable) {
            String string = this.$layout.getResources().getString(R.string.connection_error_message);
            Intrinsics.checkNotNullExpressionValue(string, "layout.resources.getStri…connection_error_message)");
            String string2 = this.$layout.getResources().getString(R.string.retry);
            Intrinsics.checkNotNullExpressionValue(string2, "layout.resources.getString(R.string.retry)");
            Utils.Companion.showSnackBar(this.$layout, -2, string, string2, new View.OnClickListener(this) {
                final /* synthetic */ HubViewModel$getDecisionPointsFromServer$request$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(View view) {
                    this.this$0.this$0.getDecisionPoints(this.this$0.$layout);
                }
            });
        }
        this.this$0.getLoadingData().postValue(false);
    }
}
