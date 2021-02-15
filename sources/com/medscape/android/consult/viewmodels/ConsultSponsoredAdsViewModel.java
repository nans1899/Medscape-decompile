package com.medscape.android.consult.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/consult/viewmodels/ConsultSponsoredAdsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "postID", "Landroidx/lifecycle/MutableLiveData;", "", "getPostID", "setPostID", "", "setValueForSponsoredPostID", "id", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultSponsoredAdsViewModel.kt */
public final class ConsultSponsoredAdsViewModel extends ViewModel {
    private MutableLiveData<String> postID = new MutableLiveData<>();

    public final void setValueForSponsoredPostID(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.postID.setValue(str);
    }

    public final MutableLiveData<String> getPostID() {
        return this.postID;
    }

    public final void setPostID(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "postID");
        this.postID = mutableLiveData;
    }
}
