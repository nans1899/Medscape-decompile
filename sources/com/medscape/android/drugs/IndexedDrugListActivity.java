package com.medscape.android.drugs;

import android.content.Intent;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.drugs.AbstractIndexedDrugListActivity;
import com.medscape.android.drugs.helper.SearchHelper;
import java.util.Map;

public class IndexedDrugListActivity extends AbstractIndexedDrugListActivity {
    private void sendOmniturePing(String str, AbstractIndexedDrugListActivity.ClickSource clickSource) {
    }

    /* access modifiers changed from: protected */
    public int getHintHeaderText() {
        return R.string.select_drug_to_view_monograph;
    }

    /* access modifiers changed from: protected */
    public boolean includeDrugClasses() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDrugClick(String str, String str2, String str3, String str4, int i, AbstractIndexedDrugListActivity.ClickSource clickSource, int i2, String str5) {
        sendOmniturePing(str, clickSource);
        OmnitureManager omnitureManager = OmnitureManager.get();
        omnitureManager.markModule(true, "brwslst", "" + (i2 + 1), (Map<String, Object>) null);
        launchDrugDetailsScreen(str2, str3, str4, i);
    }

    private void launchDrugDetailsScreen(String str, String str2, String str3, int i) {
        Intent intent = new Intent();
        Integer valueOf = Integer.valueOf(str);
        if (SearchHelper.TYPE_DRUG.equals(str3)) {
            intent.setClass(this, DrugMonographMainActivity.class);
            intent.putExtra(Constants.EXTRA_CONTENT_ID, valueOf);
            if (this.searchView != null) {
                intent.putExtra(Constants.EXTRA_QUERY, this.searchView.getQuery().toString());
            }
            intent.putExtra("drugName", str2);
        } else if (i == 4) {
            intent.setClass(this, DrugListActivity.class);
            intent.putExtra("classId", valueOf);
            intent.putExtra("className", str2);
        } else {
            intent.setClass(this, BrowseByChildClassActivity.class);
            intent.putExtra("parentId", valueOf);
            intent.putExtra("className", str2);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }
}
