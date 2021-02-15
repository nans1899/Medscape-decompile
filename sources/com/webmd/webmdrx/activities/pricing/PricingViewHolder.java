package com.webmd.webmdrx.activities.pricing;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.DrugPriceInfo;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.util.StringUtil;

public class PricingViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private TextView mDistanceToPharmacy;
    private TextView mPharmacyDiscountPrice;
    private TextView mPharmacyName;
    /* access modifiers changed from: private */
    public View mRootView;
    private double mScreenDensity = 1.0d;

    public PricingViewHolder(View view, Context context) {
        super(view);
        this.mRootView = view;
        this.mContext = context;
        this.mPharmacyName = (TextView) view.findViewById(R.id.li_pricing_text_view_pharmacy_name);
        this.mPharmacyDiscountPrice = (TextView) view.findViewById(R.id.li_pricing_text_view_discount_price);
        this.mDistanceToPharmacy = (TextView) view.findViewById(R.id.li_pricing_text_view_pharmacy_distance);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        this.mScreenDensity = (double) displayMetrics.density;
    }

    public void bindDrugPrice(Price price) {
        if (price != null) {
            setPharmacyInfo(price.getPharmacy());
            setDrugPriceInfo(price.getDrugPriceInfo());
        }
    }

    private void setPharmacyInfo(Pharmacy pharmacy) {
        if (pharmacy != null) {
            this.mPharmacyName.setText(pharmacy.getName());
            this.mDistanceToPharmacy.setText(String.format("%s miles", new Object[]{Double.valueOf(pharmacy.getDistance())}));
            if (StringUtil.isNotEmpty(pharmacy.getImage())) {
                double d = this.mScreenDensity;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * 40.0d), (int) (d * 40.0d));
                layoutParams.addRule(15);
                layoutParams.addRule(9);
                layoutParams.setMargins(10, 10, 10, 10);
                this.mRootView.post(new Runnable() {
                    public void run() {
                        PricingViewHolder.this.mRootView.requestLayout();
                    }
                });
            }
        }
    }

    private void setDrugPriceInfo(DrugPriceInfo drugPriceInfo) {
        if (drugPriceInfo != null) {
            this.mPharmacyDiscountPrice.setText(String.format("$%s", new Object[]{Double.valueOf(drugPriceInfo.getDiscountPricing())}));
        }
    }

    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }
}
