package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.models.IInAppMessage;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageView;
import com.appboy.ui.support.ViewUtils;

public abstract class AppboyInAppMessageBaseView extends RelativeLayout implements IInAppMessageView {
    public abstract Object getMessageBackgroundObject();

    public View getMessageClickableView() {
        return this;
    }

    public abstract TextView getMessageIconView();

    public abstract ImageView getMessageImageView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayerType(1, (Paint) null);
    }

    public void setMessageBackgroundColor(int i) {
        InAppMessageViewUtils.setViewBackgroundColor((View) getMessageBackgroundObject(), i);
    }

    public void setMessageTextColor(int i) {
        InAppMessageViewUtils.setTextViewColor(getMessageTextView(), i);
    }

    public void setMessageTextAlign(TextAlign textAlign) {
        InAppMessageViewUtils.setTextAlignment(getMessageTextView(), textAlign);
    }

    public void setMessage(String str) {
        getMessageTextView().setText(str);
    }

    public void setMessageImageView(Bitmap bitmap) {
        InAppMessageViewUtils.setImage(bitmap, getMessageImageView());
    }

    public String getAppropriateImageUrl(IInAppMessage iInAppMessage) {
        if (!StringUtils.isNullOrBlank(iInAppMessage.getLocalImageUrl())) {
            return iInAppMessage.getLocalImageUrl();
        }
        return iInAppMessage.getRemoteImageUrl();
    }

    public void setMessageIcon(String str, int i, int i2) {
        if (getMessageIconView() != null) {
            InAppMessageViewUtils.setIcon(getContext(), str, i, i2, getMessageIconView());
        }
    }

    public void resetMessageMargins(boolean z) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.com_appboy_stubbed_inappmessage_image_view_parent);
        ImageView messageImageView = getMessageImageView();
        if (messageImageView != null) {
            if (!z) {
                ViewUtils.removeViewFromParent(messageImageView);
                if (relativeLayout != null) {
                    ViewUtils.removeViewFromParent(relativeLayout);
                }
            } else {
                ViewUtils.removeViewFromParent(getMessageIconView());
            }
        }
        if (getMessageIconView() != null && StringUtils.isNullOrBlank((String) getMessageIconView().getText())) {
            ViewUtils.removeViewFromParent(getMessageIconView());
        }
    }

    /* access modifiers changed from: package-private */
    public View getProperViewFromInflatedStub(int i) {
        ((ViewStub) findViewById(i)).inflate();
        return findViewById(R.id.com_appboy_stubbed_inappmessage_image_view);
    }
}
