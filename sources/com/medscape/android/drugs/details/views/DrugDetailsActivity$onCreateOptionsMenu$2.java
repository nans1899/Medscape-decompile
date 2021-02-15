package com.medscape.android.drugs.details.views;

import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
final /* synthetic */ class DrugDetailsActivity$onCreateOptionsMenu$2 extends MutablePropertyReference0Impl {
    DrugDetailsActivity$onCreateOptionsMenu$2(DrugDetailsActivity drugDetailsActivity) {
        super(drugDetailsActivity, DrugDetailsActivity.class, "drugSectionViewModel", "getDrugSectionViewModel()Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", 0);
    }

    public Object get() {
        return ((DrugDetailsActivity) this.receiver).getDrugSectionViewModel();
    }

    public void set(Object obj) {
        ((DrugDetailsActivity) this.receiver).setDrugSectionViewModel((DrugSectionViewModel) obj);
    }
}
