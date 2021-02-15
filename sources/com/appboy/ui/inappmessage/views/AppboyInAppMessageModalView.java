package com.appboy.ui.inappmessage.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class AppboyInAppMessageModalView extends AppboyInAppMessageImmersiveBaseView {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageModalView.class);
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;

    public AppboyInAppMessageModalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews(Activity activity, IInAppMessageImmersive iInAppMessageImmersive) {
        AppboyInAppMessageImageView appboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_modal_imageview_stub);
        this.mAppboyInAppMessageImageView = appboyInAppMessageImageView;
        setInAppMessageImageViewAttributes(activity, iInAppMessageImmersive, appboyInAppMessageImageView);
        if (iInAppMessageImmersive.getImageStyle().equals(ImageStyle.GRAPHIC) && iInAppMessageImmersive.getBitmap() != null) {
            resizeGraphicFrameIfAppropriate(activity, iInAppMessageImmersive, ((double) iInAppMessageImmersive.getBitmap().getWidth()) / ((double) iInAppMessageImmersive.getBitmap().getHeight()));
        }
    }

    public View getFrameView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_frame);
    }

    public void resetMessageMargins(boolean z) {
        super.resetMessageMargins(z);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.com_appboy_inappmessage_modal_image_layout);
        if ((z || getMessageIconView() != null) && relativeLayout != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            relativeLayout.setLayoutParams(layoutParams);
        }
        findViewById(R.id.com_appboy_inappmessage_modal_text_layout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppboyLogger.d(AppboyInAppMessageModalView.TAG, "Passing scrollView click event to message clickable view.");
                AppboyInAppMessageModalView.this.getMessageClickableView().performClick();
            }
        });
    }

    public void setMessageBackgroundColor(int i) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(R.id.com_appboy_inappmessage_modal), i, getContext().getResources().getColor(R.color.com_appboy_inappmessage_background_light));
    }

    public List<View> getMessageButtonViews() {
        ArrayList arrayList = new ArrayList();
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_one) != null) {
            arrayList.add(findViewById(R.id.com_appboy_inappmessage_modal_button_one));
        }
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_two) != null) {
            arrayList.add(findViewById(R.id.com_appboy_inappmessage_modal_button_two));
        }
        return arrayList;
    }

    public View getMessageButtonsView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_header_text);
    }

    public View getMessageClickableView() {
        return findViewById(R.id.com_appboy_inappmessage_modal);
    }

    public View getMessageCloseButtonView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_close_button);
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_icon);
    }

    public Drawable getMessageBackgroundObject() {
        return getMessageClickableView().getBackground();
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    private void setInAppMessageImageViewAttributes(Activity activity, IInAppMessageImmersive iInAppMessageImmersive, IInAppMessageImageView iInAppMessageImageView) {
        float convertDpToPixels = (float) ViewUtils.convertDpToPixels(activity, AppboyInAppMessageParams.getModalizedImageRadiusDp());
        if (iInAppMessageImmersive.getImageStyle().equals(ImageStyle.GRAPHIC)) {
            iInAppMessageImageView.setCornersRadiusPx(convertDpToPixels);
        } else {
            iInAppMessageImageView.setCornersRadiiPx(convertDpToPixels, convertDpToPixels, 0.0f, 0.0f);
        }
        iInAppMessageImageView.setInAppMessageImageCropType(iInAppMessageImmersive.getCropType());
    }

    private void resizeGraphicFrameIfAppropriate(Activity activity, IInAppMessageImmersive iInAppMessageImmersive, double d) {
        if (iInAppMessageImmersive.getImageStyle().equals(ImageStyle.GRAPHIC)) {
            double graphicModalMaxWidthDp = AppboyInAppMessageParams.getGraphicModalMaxWidthDp();
            double graphicModalMaxHeightDp = AppboyInAppMessageParams.getGraphicModalMaxHeightDp();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).getLayoutParams();
            if (d >= graphicModalMaxWidthDp / graphicModalMaxHeightDp) {
                layoutParams.width = (int) ViewUtils.convertDpToPixels(activity, graphicModalMaxWidthDp);
                layoutParams.height = (int) (ViewUtils.convertDpToPixels(activity, graphicModalMaxWidthDp) / d);
            } else {
                layoutParams.width = (int) (ViewUtils.convertDpToPixels(activity, graphicModalMaxHeightDp) * d);
                layoutParams.height = (int) ViewUtils.convertDpToPixels(activity, graphicModalMaxHeightDp);
            }
            findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).setLayoutParams(layoutParams);
        }
    }
}
