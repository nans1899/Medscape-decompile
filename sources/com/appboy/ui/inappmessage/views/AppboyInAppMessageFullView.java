package com.appboy.ui.inappmessage.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageImageView;
import com.appboy.ui.inappmessage.IInAppMessageImageView;
import com.appboy.ui.inappmessage.config.AppboyInAppMessageParams;
import com.appboy.ui.support.ViewUtils;
import java.util.ArrayList;
import java.util.List;

public class AppboyInAppMessageFullView extends AppboyInAppMessageImmersiveBaseView {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageFullView.class);
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;

    public TextView getMessageIconView() {
        return null;
    }

    public AppboyInAppMessageFullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews(Activity activity, IInAppMessageImmersive iInAppMessageImmersive) {
        AppboyInAppMessageImageView appboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_full_imageview_stub);
        this.mAppboyInAppMessageImageView = appboyInAppMessageImageView;
        setInAppMessageImageViewAttributes(activity, iInAppMessageImmersive, appboyInAppMessageImageView);
    }

    public void setMessageBackgroundColor(int i) {
        if (getMessageBackgroundObject().getBackground() instanceof GradientDrawable) {
            InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(R.id.com_appboy_inappmessage_full), i, getContext().getResources().getColor(R.color.com_appboy_inappmessage_background_light));
        } else {
            super.setMessageBackgroundColor(i);
        }
    }

    public List<View> getMessageButtonViews() {
        ArrayList arrayList = new ArrayList();
        if (findViewById(R.id.com_appboy_inappmessage_full_button_one) != null) {
            arrayList.add(findViewById(R.id.com_appboy_inappmessage_full_button_one));
        }
        if (findViewById(R.id.com_appboy_inappmessage_full_button_two) != null) {
            arrayList.add(findViewById(R.id.com_appboy_inappmessage_full_button_two));
        }
        return arrayList;
    }

    public View getMessageButtonsView() {
        return findViewById(R.id.com_appboy_inappmessage_full_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_full_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_full_header_text);
    }

    public View getFrameView() {
        return findViewById(R.id.com_appboy_inappmessage_full_frame);
    }

    public View getMessageCloseButtonView() {
        return findViewById(R.id.com_appboy_inappmessage_full_close_button);
    }

    public View getMessageClickableView() {
        return findViewById(R.id.com_appboy_inappmessage_full);
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public View getMessageBackgroundObject() {
        return findViewById(R.id.com_appboy_inappmessage_full);
    }

    public void resetMessageMargins(boolean z) {
        super.resetMessageMargins(z);
        findViewById(R.id.com_appboy_inappmessage_full_text_layout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppboyLogger.d(AppboyInAppMessageFullView.TAG, "Passing scrollView click event to message clickable view.");
                AppboyInAppMessageFullView.this.getMessageClickableView().performClick();
            }
        });
    }

    public int getLongEdge() {
        return findViewById(R.id.com_appboy_inappmessage_full).getLayoutParams().height;
    }

    public int getShortEdge() {
        return findViewById(R.id.com_appboy_inappmessage_full).getLayoutParams().width;
    }

    private void setInAppMessageImageViewAttributes(Activity activity, IInAppMessageImmersive iInAppMessageImmersive, IInAppMessageImageView iInAppMessageImageView) {
        iInAppMessageImageView.setInAppMessageImageCropType(iInAppMessageImmersive.getCropType());
        if (ViewUtils.isRunningOnTablet(activity)) {
            float convertDpToPixels = (float) ViewUtils.convertDpToPixels(activity, AppboyInAppMessageParams.getModalizedImageRadiusDp());
            if (iInAppMessageImmersive.getImageStyle().equals(ImageStyle.GRAPHIC)) {
                iInAppMessageImageView.setCornersRadiusPx(convertDpToPixels);
            } else {
                iInAppMessageImageView.setCornersRadiiPx(convertDpToPixels, convertDpToPixels, 0.0f, 0.0f);
            }
        } else {
            iInAppMessageImageView.setCornersRadiusPx(0.0f);
        }
    }
}
