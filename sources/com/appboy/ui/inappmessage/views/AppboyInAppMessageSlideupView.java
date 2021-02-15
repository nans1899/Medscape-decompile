package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.models.IInAppMessage;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageImageView;

public class AppboyInAppMessageSlideupView extends AppboyInAppMessageBaseView {
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;

    public AppboyInAppMessageSlideupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews(IInAppMessage iInAppMessage) {
        AppboyInAppMessageImageView appboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_slideup_imageview_stub);
        this.mAppboyInAppMessageImageView = appboyInAppMessageImageView;
        appboyInAppMessageImageView.setInAppMessageImageCropType(iInAppMessage.getCropType());
    }

    /* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$appboy$enums$inappmessage$ClickAction;

        static {
            int[] iArr = new int[ClickAction.values().length];
            $SwitchMap$com$appboy$enums$inappmessage$ClickAction = iArr;
            try {
                iArr[ClickAction.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void setMessageChevron(int i, ClickAction clickAction) {
        if (AnonymousClass1.$SwitchMap$com$appboy$enums$inappmessage$ClickAction[clickAction.ordinal()] != 1) {
            InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageChevronView(), i, getContext().getResources().getColor(R.color.com_appboy_inappmessage_chevron));
        } else {
            getMessageChevronView().setVisibility(8);
        }
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_slideup_message);
    }

    public View getMessageBackgroundObject() {
        return findViewById(R.id.com_appboy_inappmessage_slideup);
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_slideup_icon);
    }

    public View getMessageChevronView() {
        return findViewById(R.id.com_appboy_inappmessage_slideup_chevron);
    }
}
