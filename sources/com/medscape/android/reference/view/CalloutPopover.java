package com.medscape.android.reference.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.slideshow.ToolTipDrawable;
import com.medscape.android.util.Util;
import com.medscape.android.view.ViewCompat;

public class CalloutPopover {
    private static final int MARGIN = 20;
    private static final int PADDING = 20;
    private static final int POINTER_HEIGHT = 80;
    private static final int POINTER_WIDTH = 40;
    private static final int TOP_MARGIN = 250;
    private boolean isNightMode = false;
    FrameLayout mCalloutContainer;
    Context mContext;
    View mDimmer;
    View mParent;
    PopupWindow popupWindow;

    public CalloutPopover(Context context, View view, View view2) {
        this.mContext = context;
        this.mDimmer = view;
        this.mParent = view2;
        this.popupWindow = new PopupWindow();
        this.mCalloutContainer = new FrameLayout(this.mContext);
        this.mCalloutContainer.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 1));
        this.popupWindow.setContentView(this.mCalloutContainer);
    }

    public void show(TextView textView, View view) {
        if (textView != null) {
            int[] clickedLocation = ((LocatableLinkMovementMethod) textView.getMovementMethod()).getClickedLocation();
            show(clickedLocation[0], clickedLocation[1], view);
        }
    }

    public void show(int i, int i2, View view) {
        ToolTipDrawable toolTipDrawable;
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
            this.mDimmer.setVisibility(8);
        } else if (view != null) {
            this.mCalloutContainer.removeAllViews();
            this.mCalloutContainer.addView(view);
            if (this.isNightMode) {
                toolTipDrawable = new ToolTipDrawable(this.mContext, R.color.greyish_brown);
            } else {
                toolTipDrawable = new ToolTipDrawable(this.mContext);
            }
            toolTipDrawable.setCornerRadius(15.0f);
            toolTipDrawable.setPadding(20, 20, 20, 20);
            toolTipDrawable.setPaintBorderVisibility(false);
            toolTipDrawable.setArrowHeight(80);
            toolTipDrawable.setArrowWidth(40);
            toolTipDrawable.setArrowStartPosition(i);
            toolTipDrawable.setMarginRight(20);
            ViewCompat.setBackground(this.mCalloutContainer, toolTipDrawable);
            CalloutDimensionResolver calloutDimensionResolver = new CalloutDimensionResolver(i2);
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, calloutDimensionResolver.getHeight()));
            toolTipDrawable.setIsBottomBubble(calloutDimensionResolver.getIsBootomToolTip());
            this.popupWindow.setWidth(calloutDimensionResolver.getWidth());
            this.popupWindow.setHeight(-2);
            this.popupWindow.showAtLocation(this.mParent, 0, i, calloutDimensionResolver.getPopAnchorY());
            this.mDimmer.setVisibility(0);
        }
    }

    public void setNightMode(boolean z) {
        this.isNightMode = z;
    }

    public void handleTouchEvent(MotionEvent motionEvent) {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            int[] iArr = new int[2];
            popupWindow2.getContentView().getLocationOnScreen(iArr);
            if (!new Rect(iArr[0], iArr[1], iArr[0] + this.popupWindow.getContentView().getWidth(), iArr[1] + this.popupWindow.getContentView().getHeight()).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                this.popupWindow.dismiss();
                this.mDimmer.setVisibility(8);
            }
        }
    }

    public void dismiss() {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            popupWindow2.dismiss();
            this.mDimmer.setVisibility(8);
        }
    }

    private class CalloutDimensionResolver {
        boolean isBootomToolTip;
        int popupAnchorY;
        int viewHeight = -2;
        int viewWidth;

        public CalloutDimensionResolver(int i) {
            this.viewWidth = Util.getDisplayWidth(CalloutPopover.this.mContext) - 20;
            int displayHeight = Util.getDisplayHeight(CalloutPopover.this.mContext) - 20;
            CalloutPopover.this.mCalloutContainer.measure(View.MeasureSpec.makeMeasureSpec(this.viewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(displayHeight, Integer.MIN_VALUE));
            int measuredHeight = CalloutPopover.this.mCalloutContainer.getMeasuredHeight();
            int i2 = i - 250;
            int i3 = displayHeight - i;
            boolean z = i3 >= i2;
            this.isBootomToolTip = z;
            if (z) {
                if (measuredHeight > i3) {
                    this.viewHeight = (i3 - 20) - 80;
                }
                this.popupAnchorY = i + 20;
            } else if (measuredHeight > i2) {
                this.popupAnchorY = 250;
                this.viewHeight = i - 330;
            } else {
                this.popupAnchorY = i - (measuredHeight - 20);
            }
        }

        public int getPopAnchorY() {
            return this.popupAnchorY;
        }

        public boolean getIsBootomToolTip() {
            return this.isBootomToolTip;
        }

        public int getHeight() {
            return this.viewHeight;
        }

        public int getWidth() {
            return this.viewWidth;
        }
    }
}
