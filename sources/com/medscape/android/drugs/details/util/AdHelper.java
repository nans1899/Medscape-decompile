package com.medscape.android.drugs.details.util;

import android.content.Context;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\f\u001a\u0004\u0018\u00010\rJ$\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\rR:\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/medscape/android/drugs/details/util/AdHelper;", "", "()V", "mOmnitureContentData", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getMOmnitureContentData", "()Ljava/util/HashMap;", "setMOmnitureContentData", "(Ljava/util/HashMap;)V", "getConsultScreenMap", "context", "Landroid/content/Context;", "getDrugSectionScreenMap", "contentId", "", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdHelper.kt */
public final class AdHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap<String, Object> mOmnitureContentData;

    public final HashMap<String, Object> getMOmnitureContentData() {
        return this.mOmnitureContentData;
    }

    public final void setMOmnitureContentData(HashMap<String, Object> hashMap) {
        this.mOmnitureContentData = hashMap;
    }

    public final HashMap<String, String> getDrugSectionScreenMap(int i, Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        Map map = hashMap;
        map.put("pos", String.valueOf(context != null ? context.getString(R.string.banner_ad_pos) : null));
        map.put("pc", "content");
        HashMap<String, String> queryDatabase = AdsSegvar.getInstance().queryDatabase(context, i, 1);
        this.mOmnitureContentData = OmnitureManager.get().getContentBasedOmnitureData(queryDatabase, -1);
        hashMap.putAll(queryDatabase);
        map.put("art", "" + i);
        return hashMap;
    }

    public final HashMap<String, String> getConsultScreenMap(Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        Map map = hashMap;
        map.put("pos", String.valueOf(context != null ? context.getString(R.string.sponsored_consult_ad_pos) : null));
        map.put("pc", "content");
        return hashMap;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JN\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u00062\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/medscape/android/drugs/details/util/AdHelper$Companion;", "", "()V", "getUpdatedADParams", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "screenMap", "model", "Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HashMap<String, String> getUpdatedADParams(HashMap<String, String> hashMap, DrugSectionViewModel drugSectionViewModel) {
            Intrinsics.checkNotNullParameter(hashMap, "screenMap");
            Intrinsics.checkNotNullParameter(drugSectionViewModel, "model");
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.putAll(hashMap);
            switch (drugSectionViewModel.getTabNameID()) {
                case R.string.drug_monograph_sections_dosage_tab_adult:
                    hashMap2.put("medsec", "adult");
                    break;
                case R.string.drug_monograph_sections_dosage_tab_geriatric:
                    hashMap2.put("medsec", "ger");
                    break;
                case R.string.drug_monograph_sections_dosage_tab_pediatric:
                    hashMap2.put("medsec", "ped");
                    break;
            }
            return hashMap2;
        }
    }
}
