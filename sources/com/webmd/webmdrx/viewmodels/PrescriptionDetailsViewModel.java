package com.webmd.webmdrx.viewmodels;

import androidx.lifecycle.ViewModel;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.wbmd.wbmddrugscommons.model.Drug;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/webmd/webmdrx/viewmodels/PrescriptionDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "setDrugFromMonograph", "", "monograph", "Lcom/wbmd/wbmddrugscommons/model/DrugMonograph;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PrescriptionDetailsViewModel.kt */
public final class PrescriptionDetailsViewModel extends ViewModel {
    public final void setDrugFromMonograph(DrugMonograph drugMonograph) {
        Intrinsics.checkNotNullParameter(drugMonograph, Constants.WBMDDrugResponseKeyDrugMonograph);
        Drug drug = new Drug();
        drug.setId(drugMonograph.FDB_id);
        drug.setDrugName(drugMonograph.tDrugName);
        drug.setMonoId(drugMonograph.monographId);
    }
}
