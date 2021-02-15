package com.wbmd.adlibrary.viewholders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.adlibrary.R;

public class PreLoadedAdViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = PreLoadedAdContentViewHolder.class.getSimpleName();
    private TextView advertisementLabelTextView;
    private View horizontalRule;
    private View mRootView;
    private LinearLayout wrapper;

    public PreLoadedAdViewHolder(View view) {
        super(view);
        this.mRootView = view.findViewById(R.id.root_view);
        this.wrapper = (LinearLayout) view.findViewById(R.id.publisherAdViewWrapper);
        this.horizontalRule = view.findViewById(R.id.bottomHorizontalRule);
        this.advertisementLabelTextView = (TextView) view.findViewById(R.id.adLabel);
    }

    public void displayBottomHorizontalRule(Boolean bool) {
        this.horizontalRule.setVisibility(bool.booleanValue() ? 0 : 8);
    }

    public void bind(PublisherAdView publisherAdView) {
        if (publisherAdView != null) {
            try {
                this.advertisementLabelTextView.setVisibility(0);
                if (this.wrapper.getChildCount() == 0) {
                    if (publisherAdView.getParent() != null) {
                        ((ViewGroup) publisherAdView.getParent()).removeView(publisherAdView);
                    }
                    this.wrapper.addView(publisherAdView);
                } else {
                    this.wrapper.removeAllViews();
                    this.wrapper.addView(publisherAdView);
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
                return;
            }
        }
        this.mRootView.setVisibility(0);
    }

    public void removeAdView() {
        LinearLayout linearLayout = this.wrapper;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            TextView textView = this.advertisementLabelTextView;
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }
}
