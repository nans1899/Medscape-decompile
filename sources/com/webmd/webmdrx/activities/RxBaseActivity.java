package com.webmd.webmdrx.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.share.ShareSavingsActivity;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.models.MarkerWindow;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.omnitureextensions.RxOmnitureSender;
import com.webmd.webmdrx.util.Constants;

public class RxBaseActivity extends AppCompatActivity {
    protected String mIcdDrugId;
    protected String mIcdDrugName;
    public RxOmnitureSender mRxOmnitureSender = new RxOmnitureSender();

    /* access modifiers changed from: protected */
    public void showSavingsCard(Context context, MarkerWindow markerWindow, String str, String str2, double d, String str3, double d2, String str4) {
        Intent intent = new Intent(context, CouponActivity.class);
        intent.putExtra(Constants.EXTRA_FORM, str);
        intent.putExtra(Constants.EXTRA_DOSAGE, str2);
        intent.putExtra(Constants.EXTRA_QUANTITY, d);
        intent.putExtra("extra_drug_name", str3);
        intent.putExtra(Constants.EXTRA_PHARMACY_NAME, markerWindow.getPharmacyName());
        intent.putExtra(Constants.EXTRA_DRUG_PRICE, markerWindow.getDrugPrice());
        intent.putExtra(Constants.EXTRA_PACKAGE_SIZE, d2);
        intent.putExtra(Constants.EXTRA_ICD, str4);
        intent.putExtra(Constants.EXTRA_ICD_DRUG_NAME, this.mIcdDrugName);
        intent.putExtra(Constants.EXTRA_ICD_DRUG_ID, this.mIcdDrugId);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ApiManager.APP_ID = getString(R.string.rx_app_id);
        RxOmnitureSender.Companion.setProfessional(getResources().getBoolean(R.bool.rx_is_professional));
    }

    public void showSavingsCard(Context context, Price price, String str, String str2, double d, String str3, double d2, String str4) {
        Intent intent = new Intent(context, CouponActivity.class);
        intent.putExtra(Constants.EXTRA_FORM, str);
        intent.putExtra(Constants.EXTRA_DOSAGE, str2);
        intent.putExtra(Constants.EXTRA_QUANTITY, d);
        intent.putExtra("extra_drug_name", str3);
        intent.putExtra(Constants.EXTRA_PHARMACY_NAME, price.getPharmacy().getName());
        intent.putExtra(Constants.EXTRA_DRUG_PRICE, price.getDrugPriceInfo().getDiscountPricing());
        intent.putExtra(Constants.EXTRA_PACKAGE_SIZE, d2);
        intent.putExtra(Constants.EXTRA_ICD, str4);
        intent.putExtra(Constants.EXTRA_ICD_DRUG_NAME, this.mIcdDrugName);
        intent.putExtra(Constants.EXTRA_ICD_DRUG_ID, this.mIcdDrugId);
        startActivity(intent);
    }

    public boolean checkPermission(int i) {
        if (Build.VERSION.SDK_INT < 23 || ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, i);
        return false;
    }

    /* access modifiers changed from: protected */
    public void goToSavings(Context context, Price price, String str, String str2, double d, String str3, double d2, String str4, boolean z) {
        if (z) {
            Intent intent = new Intent(context, ShareSavingsActivity.class);
            intent.putExtra(Constants.EXTRA_FORM, str);
            intent.putExtra(Constants.EXTRA_DOSAGE, str2);
            intent.putExtra(Constants.EXTRA_QUANTITY, d);
            intent.putExtra("extra_drug_name", str3);
            intent.putExtra(Constants.EXTRA_PRICES, price);
            intent.putExtra(Constants.EXTRA_PACKAGE_SIZE, d2);
            intent.putExtra(Constants.EXTRA_ICD, str4);
            intent.putExtra(Constants.EXTRA_ICD_DRUG_NAME, this.mIcdDrugName);
            intent.putExtra(Constants.EXTRA_ICD_DRUG_ID, this.mIcdDrugId);
            startActivity(intent);
            return;
        }
        showSavingsCard(context, price, str, str2, d, str3, d2, str4);
    }
}
