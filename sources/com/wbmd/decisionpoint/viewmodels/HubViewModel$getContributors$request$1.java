package com.wbmd.decisionpoint.viewmodels;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.wbmd.decisionpoint.domain.contributors.ContributorsData;
import com.wbmd.decisionpoint.ui.adapters.HubAdapter;
import kotlin.Metadata;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "onResponse"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubViewModel.kt */
final class HubViewModel$getContributors$request$1<T> implements Response.Listener<JSONObject> {
    final /* synthetic */ HubViewModel this$0;

    HubViewModel$getContributors$request$1(HubViewModel hubViewModel) {
        this.this$0 = hubViewModel;
    }

    public final void onResponse(JSONObject jSONObject) {
        HubAdapter.addItem$default(HubViewModel.access$getAdapter$p(this.this$0), new HubAdapter.ExpertsAnswersRow(), false, 2, (Object) null);
        HubViewModel.access$getAdapter$p(this.this$0).addItems(this.this$0.generateContributors((ContributorsData) new Gson().fromJson(jSONObject.toString(), ContributorsData.class)));
        this.this$0.getLoadingData().postValue(false);
    }
}
