package com.webmd.webmdrx.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.pricing.PricingCouponViewHolder;
import com.webmd.webmdrx.intf.IPharmacyStarListener;
import com.webmd.webmdrx.models.CouponLocal;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.Price;
import java.util.List;
import java.util.Locale;

public class PharmacyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_COUPON = 1;
    private static final int VIEW_TYPE_PRICING_RESULT = 0;
    Context mContext;
    public List<Price> mData;
    /* access modifiers changed from: private */
    public final onPharmacyClickListener mListener;
    private IPharmacyStarListener mStarListener;
    private int maxWidth;

    public interface onPharmacyClickListener {
        void onPharmacyClick(Price price);

        void onPharmacyCouponClick(Price price);
    }

    public PharmacyListAdapter(List<Price> list, onPharmacyClickListener onpharmacyclicklistener, IPharmacyStarListener iPharmacyStarListener, Context context) {
        this.mData = list;
        this.mListener = onpharmacyclicklistener;
        this.mStarListener = iPharmacyStarListener;
        this.mContext = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new PricingCouponViewHolder(from.inflate(R.layout.pricing_coupon_item, viewGroup, false), this.mContext);
        }
        return new PharamcyViewHolder(from.inflate(R.layout.pricing_list_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PharamcyViewHolder pharamcyViewHolder;
        if (getItemViewType(i) != 1) {
            final Price price = this.mData.get(i);
            Pharmacy pharmacy = price.getPharmacy();
            if (price != null && pharmacy != null && (pharamcyViewHolder = (PharamcyViewHolder) viewHolder) != null) {
                LinearLayout access$000 = pharamcyViewHolder.pricingLayout;
                ViewGroup.LayoutParams layoutParams = access$000.getLayoutParams();
                layoutParams.width = this.maxWidth;
                access$000.setLayoutParams(layoutParams);
                pharamcyViewHolder.mPharmacyName.setText(pharmacy.getName());
                if (price.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                    pharamcyViewHolder.mPharmacyName.setText(R.string.local_pharmacies);
                    double pharmacyGroupMinPrice = price.getPharmacy().getPharmacyGroupMinPrice();
                    double pharmacyGroupMaxPrice = price.getPharmacy().getPharmacyGroupMaxPrice();
                    if (pharmacyGroupMinPrice == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || pharmacyGroupMaxPrice == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || pharmacyGroupMinPrice == pharmacyGroupMaxPrice) {
                        double discountPricing = price.getDrugPriceInfo().getDiscountPricing();
                        pharamcyViewHolder.mPharmacyDiscountPrice.setText("$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(discountPricing)}));
                    } else {
                        String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(pharmacyGroupMaxPrice)});
                        pharamcyViewHolder.mPharmacyDiscountPrice.setText("$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(pharmacyGroupMinPrice)}) + " -");
                    }
                } else {
                    pharamcyViewHolder.mPharmacyName.setText(pharmacy.getName());
                    double discountPricing2 = price.getDrugPriceInfo().getDiscountPricing();
                    pharamcyViewHolder.mPharmacyDiscountPrice.setText("$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(discountPricing2)}));
                }
                pharamcyViewHolder.mDistanceToPharmacy.setText(String.format("%.2f miles", new Object[]{Double.valueOf(pharmacy.getDistance())}));
                pharamcyViewHolder.mRootLayout.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PharmacyListAdapter.this.mListener.onPharmacyClick(price);
                    }
                });
                pharamcyViewHolder.showCoupon.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PharmacyListAdapter.this.mListener.onPharmacyCouponClick(price);
                    }
                });
                pharamcyViewHolder.mInfoWrapper.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PharmacyListAdapter.this.mListener.onPharmacyClick(price);
                    }
                });
                return;
            }
            return;
        }
        PricingCouponViewHolder pricingCouponViewHolder = (PricingCouponViewHolder) viewHolder;
        CouponLocal couponLocal = this.mData.get(i).getCouponLocal();
        if (couponLocal != null) {
            pricingCouponViewHolder.savingValue.setText(couponLocal.getSavingValue());
            final String url = couponLocal.getUrl();
            pricingCouponViewHolder.couponRootLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PharmacyListAdapter.this.mContext.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(url)));
                }
            });
            pricingCouponViewHolder.couponTitle.setText(couponLocal.getShortText());
        }
    }

    public int getItemViewType(int i) {
        return this.mData.get(i).getCouponLocal() != null ? 1 : 0;
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setMaxPriceWidth(int i) {
        this.maxWidth = i;
    }

    class PharamcyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final TextView mDistanceToPharmacy;
        /* access modifiers changed from: private */
        public final View mInfoWrapper;
        /* access modifiers changed from: private */
        public final TextView mPharmacyDiscountPrice;
        /* access modifiers changed from: private */
        public final TextView mPharmacyName;
        /* access modifiers changed from: private */
        public final View mRootLayout;
        /* access modifiers changed from: private */
        public final LinearLayout pricingLayout;
        /* access modifiers changed from: private */
        public final Button showCoupon;

        PharamcyViewHolder(View view) {
            super(view);
            this.mRootLayout = view.findViewById(R.id.li_pricing_layout_root);
            this.mPharmacyName = (TextView) view.findViewById(R.id.li_pricing_text_view_pharmacy_name);
            this.mDistanceToPharmacy = (TextView) view.findViewById(R.id.li_pricing_text_view_pharmacy_distance);
            this.mPharmacyDiscountPrice = (TextView) view.findViewById(R.id.li_pricing_text_view_discount_price);
            this.pricingLayout = (LinearLayout) view.findViewById(R.id.li_pricing_layout_price);
            this.showCoupon = (Button) view.findViewById(R.id.show_coupon);
            this.mInfoWrapper = view.findViewById(R.id.info_wrapper);
        }
    }
}
