package com.webmd.wbmdcmepulse.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.snackbar.Snackbar;
import com.webmd.wbmdcmepulse.models.utils.Utilities;

public class NoNetworkViewPager extends ViewPager {
    /* access modifiers changed from: private */
    public Context mContext;
    private View mRootView;
    private Snackbar mSnackbar;

    public NoNetworkViewPager(Context context) {
        super(context);
        this.mContext = context;
    }

    public NoNetworkViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        View view;
        if (this.mContext == null && (view = this.mRootView) != null) {
            this.mContext = view.getContext();
        }
        if (Utilities.isNetworkAvailable(this.mContext)) {
            Snackbar snackbar = this.mSnackbar;
            if (snackbar != null) {
                snackbar.dismiss();
            }
            return super.onInterceptTouchEvent(motionEvent);
        }
        showNoNetworkSnackBar(this.mRootView);
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (Utilities.isNetworkAvailable(this.mContext)) {
            Snackbar snackbar = this.mSnackbar;
            if (snackbar != null) {
                snackbar.dismiss();
            }
            return super.onTouchEvent(motionEvent);
        }
        showNoNetworkSnackBar(this.mRootView);
        return false;
    }

    public void setRootView(View view) {
        this.mRootView = view;
    }

    /* access modifiers changed from: private */
    public void showNoNetworkSnackBar(View view) {
        if (view != null) {
            Snackbar buildSnackBar = Utilities.buildSnackBar(view, -2, "Internet Connection Required", "Retry", new View.OnClickListener() {
                public void onClick(View view) {
                    if (!Utilities.isNetworkAvailable(NoNetworkViewPager.this.mContext)) {
                        NoNetworkViewPager.this.showNoNetworkSnackBar(view);
                    }
                }
            });
            this.mSnackbar = buildSnackBar;
            buildSnackBar.show();
        }
    }
}
