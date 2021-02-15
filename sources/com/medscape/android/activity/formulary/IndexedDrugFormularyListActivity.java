package com.medscape.android.activity.formulary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.formulary.FormularyFinder;
import com.medscape.android.activity.search.RecentlyViewedDrugClickListener;
import com.medscape.android.drugs.AbstractIndexedDrugListActivity;
import com.medscape.android.drugs.parser.DrugMonographParser;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import java.util.Map;

public class IndexedDrugFormularyListActivity extends AbstractIndexedDrugListActivity implements RecentlyViewedDrugClickListener {
    private static final int SHOW_FORMULARY_NETWORK_ERROR_DIALOG = 109;
    private static final String TAG = IndexedDrugFormularyListActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public MedscapeException ex;
    FormularyFinder mFormularyFinder;

    /* access modifiers changed from: protected */
    public int getHintHeaderText() {
        return R.string.select_drug_to_view_formulary;
    }

    /* access modifiers changed from: protected */
    public boolean includeDrugClasses() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDrugClick(String str, String str2, String str3, String str4, int i, AbstractIndexedDrugListActivity.ClickSource clickSource, int i2, String str5) {
        int parseInt = Integer.parseInt(str2);
        this.mFormularyFinder = new FormularyFinder(this);
        openFormulary(parseInt);
    }

    public void onRecentlyViewedDrugClick(int i, String str) {
        openFormulary(i);
    }

    /* access modifiers changed from: private */
    public void openFormulary(final int i) {
        if (!Util.isOnline(this)) {
            Util.hideKeyboard(this);
            this.ex.showSnackBar(this.mDrugsListView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    IndexedDrugFormularyListActivity.this.openFormulary(i);
                    IndexedDrugFormularyListActivity.this.ex.dismissSnackBar();
                }
            });
            return;
        }
        this.mFormularyFinder.setCallBack(new FormularyFinder.Callbacks() {
            public void onFormularyDownloaded(boolean z) {
                if (!z || IndexedDrugFormularyListActivity.this.mFormularyFinder.getBrandModelList() == null || IndexedDrugFormularyListActivity.this.mFormularyFinder.getBrandModelList().size() <= 0) {
                    IndexedDrugFormularyListActivity indexedDrugFormularyListActivity = IndexedDrugFormularyListActivity.this;
                    Toast.makeText(indexedDrugFormularyListActivity, indexedDrugFormularyListActivity.getString(R.string.no_formulary_available), 0).show();
                    IndexedDrugFormularyListActivity.this.ex.dismissSnackBar();
                    return;
                }
                IndexedDrugFormularyListActivity.this.showFormulary(i);
            }
        });
        this.mFormularyFinder.checkForFormularies(i);
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

    /* access modifiers changed from: private */
    public void showFormulary(int i) {
        BrandModels brandModels = new BrandModels();
        brandModels.setBrandModels(this.mFormularyFinder.getBrandModelList());
        Bundle bundle = new Bundle();
        bundle.putInt(FormularyMyPlanPage.CONTENT_ID, i);
        bundle.putString("TITLE", getHeaderName(i));
        bundle.putSerializable("BRAND_MODELS", brandModels);
        Intent intent = new Intent(this, SelectBrandActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
    }

    private String getHeaderName(int i) {
        try {
            return DrugMonographParser.parse(i).getHeader().getGc();
        } catch (Exception e) {
            Log.w(TAG, "getHeaderName: failed to get header", e);
            return null;
        }
    }

    private void sendBIPing(int i) {
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + i, "start", (Map<String, Object>) null);
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 109) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error").setMessage("You must be connected to the internet in order to setup Formulary").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}
