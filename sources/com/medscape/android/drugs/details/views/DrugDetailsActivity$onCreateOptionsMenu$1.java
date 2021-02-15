package com.medscape.android.drugs.details.views;

import com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
final /* synthetic */ class DrugDetailsActivity$onCreateOptionsMenu$1 extends MutablePropertyReference0Impl {
    DrugDetailsActivity$onCreateOptionsMenu$1(DrugDetailsActivity drugDetailsActivity) {
        super(drugDetailsActivity, DrugDetailsActivity.class, "toolbarViewModel", "getToolbarViewModel()Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;", 0);
    }

    public Object get() {
        return DrugDetailsActivity.access$getToolbarViewModel$p((DrugDetailsActivity) this.receiver);
    }

    public void set(Object obj) {
        ((DrugDetailsActivity) this.receiver).toolbarViewModel = (DrugDetailsActivityViewModel) obj;
    }
}
