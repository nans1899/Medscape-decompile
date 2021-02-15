package com.wbmd.adlibrary.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.callbacks.IAdStateListener;
import com.wbmd.adlibrary.model.AdRequest;

public class AdDialogFragment extends DialogFragment {
    private AdRequest mAdRequest;
    private IAdStateListener mCallback;
    private boolean mIsAdLoading;
    private PublisherAdRequest mPublisherAdRequest;
    private PublisherAdView mPublisherAdView;
    private View mRootView;
    private LinearLayout wrapper;

    public static AdDialogFragment newInstance(PublisherAdView publisherAdView) {
        AdDialogFragment adDialogFragment = new AdDialogFragment();
        adDialogFragment.mPublisherAdView = publisherAdView;
        return adDialogFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fullscreen_brandseeker_ad, viewGroup, false);
        this.mRootView = inflate;
        ((ImageView) inflate.findViewById(R.id.image_close_brandseeker)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AdDialogFragment.this.dismiss();
            }
        });
        LinearLayout linearLayout = (LinearLayout) this.mRootView.findViewById(R.id.adViewWrapper);
        this.wrapper = linearLayout;
        try {
            if (this.mPublisherAdView != null) {
                if (linearLayout.getChildCount() == 0 && this.mPublisherAdView.getParent() == null) {
                    this.wrapper.addView(this.mPublisherAdView);
                } else if (this.mPublisherAdView.getParent() == null) {
                    for (int i = 0; i < this.wrapper.getChildCount(); i++) {
                        this.wrapper.removeView(this.wrapper.getChildAt(i));
                    }
                    this.wrapper.addView(this.mPublisherAdView);
                } else if (((ViewGroup) this.mPublisherAdView.getParent()).getChildCount() > 0) {
                    for (int i2 = 0; i2 <= ((ViewGroup) this.mPublisherAdView.getParent()).getChildCount(); i2++) {
                        ((ViewGroup) this.mPublisherAdView.getParent()).removeView(((ViewGroup) this.mPublisherAdView.getParent()).getChildAt(i2));
                    }
                    this.wrapper.addView(this.mPublisherAdView);
                }
                this.mRootView.setVisibility(0);
            }
        } catch (Exception e) {
            Log.d("___ad___", e.getMessage());
        }
        return this.mRootView;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(relativeLayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setLayout(-1, -1);
        dialog.setCancelable(true);
        return dialog;
    }

    public void onResume() {
        super.onResume();
        PublisherAdView publisherAdView = this.mPublisherAdView;
        if (publisherAdView != null) {
            publisherAdView.resume();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                AdDialogFragment.this.dismiss();
            }
        }, 20000);
    }
}
