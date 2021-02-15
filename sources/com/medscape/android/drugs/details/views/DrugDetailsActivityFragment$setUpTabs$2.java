package com.medscape.android.drugs.details.views;

import androidx.fragment.app.FragmentActivity;
import com.google.android.material.tabs.TabLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.drugs.OmnitureValues;
import com.medscape.android.util.Util;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/medscape/android/drugs/details/views/DrugDetailsActivityFragment$setUpTabs$2", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
public final class DrugDetailsActivityFragment$setUpTabs$2 implements TabLayout.OnTabSelectedListener {
    final /* synthetic */ DrugDetailsActivityFragment this$0;

    public void onTabUnselected(TabLayout.Tab tab) {
    }

    DrugDetailsActivityFragment$setUpTabs$2(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        this.this$0 = drugDetailsActivityFragment;
    }

    public void onTabReselected(TabLayout.Tab tab) {
        this.this$0.scrollToTop();
    }

    public void onTabSelected(TabLayout.Tab tab) {
        HashMap hashMap;
        Util.hideKeyboard(this.this$0.getActivity());
        boolean z = false;
        this.this$0.getDrugSectionViewModel().setTab(tab != null ? tab.getPosition() : 0);
        this.this$0.getActivityViewModel().setSearchMode(false);
        this.this$0.scrollToTop();
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            ((DrugDetailsActivity) activity).getAd();
            String biSectionNameFromTitle = OmnitureValues.getBiSectionNameFromTitle(String.valueOf(tab != null ? tab.getText() : null));
            if (biSectionNameFromTitle != null) {
                String generateChronicleId = OmnitureValues.generateChronicleId(this.this$0.getDrugSectionViewModel(), this.this$0.assetId, biSectionNameFromTitle);
                CharSequence charSequence = generateChronicleId;
                if (charSequence == null || charSequence.length() == 0) {
                    z = true;
                }
                if (!z) {
                    hashMap = new HashMap();
                    hashMap.put("wapp.asset", generateChronicleId);
                } else {
                    hashMap = null;
                }
                OmnitureManager.get().trackPageView(this.this$0.getContext(), Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", String.valueOf(this.this$0.getDrugSectionViewModel().getDrugSectionContentRepo().getContentId()), biSectionNameFromTitle, hashMap);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
    }
}
