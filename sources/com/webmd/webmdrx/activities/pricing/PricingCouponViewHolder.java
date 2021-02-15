package com.webmd.webmdrx.activities.pricing;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.webmdrx.R;

public class PricingCouponViewHolder extends RecyclerView.ViewHolder {
    public TextView couponMessage;
    public LinearLayout couponRootLayout;
    public TextView couponTitle;
    public Context mContext;
    public View mRootView;
    public TextView savingValue;

    public PricingCouponViewHolder(View view, Context context) {
        super(view);
        this.mRootView = view;
        this.mContext = context;
        this.savingValue = (TextView) view.findViewById(R.id.coupon_saving_value);
        this.couponRootLayout = (LinearLayout) view.findViewById(R.id.coupon_layout);
        this.couponTitle = (TextView) view.findViewById(R.id.li_coupon_title);
        this.couponMessage = (TextView) view.findViewById(R.id.li_coupon_message);
    }
}
