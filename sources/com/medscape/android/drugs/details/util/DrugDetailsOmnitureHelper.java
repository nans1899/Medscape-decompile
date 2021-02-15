package com.medscape.android.drugs.details.util;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.drugs.OmnitureValues;
import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005R:\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/medscape/android/drugs/details/util/DrugDetailsOmnitureHelper;", "", "()V", "mOmnitureContentData", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getMOmnitureContentData", "()Ljava/util/HashMap;", "setMOmnitureContentData", "(Ljava/util/HashMap;)V", "sendPageChangeOmniture", "", "sectionViewModel", "Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "context", "Landroid/app/Activity;", "assetId", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsOmnitureHelper.kt */
public final class DrugDetailsOmnitureHelper {
    private HashMap<String, Object> mOmnitureContentData;

    public final HashMap<String, Object> getMOmnitureContentData() {
        return this.mOmnitureContentData;
    }

    public final void setMOmnitureContentData(HashMap<String, Object> hashMap) {
        this.mOmnitureContentData = hashMap;
    }

    public final void sendPageChangeOmniture(DrugSectionViewModel drugSectionViewModel, Activity activity, String str) {
        String str2;
        Integer num;
        Intrinsics.checkNotNullParameter(drugSectionViewModel, "sectionViewModel");
        Intrinsics.checkNotNullParameter(activity, "context");
        String str3 = null;
        if (drugSectionViewModel.getNextSectionTrigger()) {
            drugSectionViewModel.setNextSectionTrigger(false);
            OmnitureManager.get().markModule(NotificationCompat.CATEGORY_NAVIGATION, "pull", (Map<String, Object>) null);
        } else {
            OmnitureManager.get().markModule(NotificationCompat.CATEGORY_NAVIGATION, Category.K_MENU_CATEGORY, (Map<String, Object>) null);
        }
        String valueOf = String.valueOf(drugSectionViewModel.getDrugSectionContentRepo().getContentId());
        ArrayList<Integer> indexes = drugSectionViewModel.getIndexes();
        if (!(indexes == null || (num = indexes.get(0)) == null)) {
            Intrinsics.checkNotNullExpressionValue(num, "it");
            str3 = OmnitureValues.getBiSectionNameFromPageNumber(num.intValue());
        }
        if (str3 == null) {
            str2 = "";
        } else {
            str2 = '-' + str3;
        }
        String str4 = str2;
        HashMap<String, Object> hashMap = this.mOmnitureContentData;
        if (hashMap != null) {
            hashMap.put("wapp.asset", String.valueOf(new ChronicleIDUtil().generateAssetId(valueOf, str, "/drug/view/" + valueOf + str4)));
        }
        ((BaseActivity) activity).setCurrentPvid(OmnitureManager.get().trackPageView(activity, Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", valueOf, str4, this.mOmnitureContentData));
    }
}
