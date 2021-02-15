package com.medscape.android.myinvites;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.medscape.android.myinvites.specific.SpecificInvitations;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "", "kotlin.jvm.PlatformType", "onResponse"}, k = 3, mv = {1, 4, 0})
/* compiled from: MyInvitationsViewModel.kt */
final class MyInvitationsViewModel$load$serverRequest$1<T> implements Response.Listener<String> {
    final /* synthetic */ MyInvitationsViewModel this$0;

    MyInvitationsViewModel$load$serverRequest$1(MyInvitationsViewModel myInvitationsViewModel) {
        this.this$0 = myInvitationsViewModel;
    }

    public final void onResponse(String str) {
        this.this$0.getInvitationData().postValue(this.this$0.sortInvitations((SpecificInvitations) new Gson().fromJson(str, SpecificInvitations.class)));
        this.this$0.getLoadingData().postValue(false);
    }
}
