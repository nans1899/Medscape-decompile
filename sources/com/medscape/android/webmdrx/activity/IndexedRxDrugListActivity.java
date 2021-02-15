package com.medscape.android.webmdrx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.medscape.android.R;
import com.medscape.android.drugs.AbstractIndexedDrugListActivity;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.webmdrx.RxLauncher;

public class IndexedRxDrugListActivity extends AbstractIndexedDrugListActivity {
    private static final String TAG = IndexedRxDrugListActivity.class.getSimpleName();
    private MedscapeException ex;

    /* access modifiers changed from: protected */
    public int getHintHeaderText() {
        return R.string.select_rx_drug_to_view_pricing;
    }

    /* access modifiers changed from: protected */
    public boolean includeDrugClasses() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDrugClick(String str, String str2, String str3, String str4, int i, AbstractIndexedDrugListActivity.ClickSource clickSource, int i2, String str5) {
        ViewHelper.findById((Activity) this, 16908301).setVisibility(0);
        RxLauncher.Companion.launchRxDrug(str3, str5, str2, this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ex = new MedscapeException(getResources().getString(R.string.internet_required));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.ex.dismissSnackBar();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        return super.onOptionsItemSelected(menuItem);
    }
}
