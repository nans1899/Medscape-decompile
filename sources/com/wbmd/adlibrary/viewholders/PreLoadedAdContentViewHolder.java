package com.wbmd.adlibrary.viewholders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.adlibrary.R;

public class PreLoadedAdContentViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = PreLoadedAdContentViewHolder.class.getSimpleName();
    private View mRootView;
    private LinearLayout wrapper;

    public PreLoadedAdContentViewHolder(View view) {
        super(view);
        this.mRootView = view.findViewById(R.id.root_view);
        this.wrapper = (LinearLayout) view.findViewById(R.id.publisherAdViewWrapper);
    }

    public void bind(PublisherAdView publisherAdView) {
        if (publisherAdView != null) {
            try {
                if (this.wrapper.getChildCount() == 0 && publisherAdView.getParent() == null) {
                    this.wrapper.addView(publisherAdView);
                } else if (publisherAdView.getParent() == null) {
                    for (int i = 0; i < this.wrapper.getChildCount(); i++) {
                        this.wrapper.removeView(this.wrapper.getChildAt(i));
                    }
                    this.wrapper.addView(publisherAdView);
                } else if (((ViewGroup) publisherAdView.getParent()).getChildCount() > 0) {
                    for (int i2 = 0; i2 <= ((ViewGroup) publisherAdView.getParent()).getChildCount(); i2++) {
                        ((ViewGroup) publisherAdView.getParent()).removeView(((ViewGroup) publisherAdView.getParent()).getChildAt(i2));
                    }
                    this.wrapper.addView(publisherAdView);
                }
                this.mRootView.setVisibility(0);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }
}
