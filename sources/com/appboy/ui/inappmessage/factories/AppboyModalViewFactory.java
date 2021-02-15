package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.appboy.Appboy;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageModal;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageModalView;

public class AppboyModalViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageModalView createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        InAppMessageModal inAppMessageModal = (InAppMessageModal) iInAppMessage;
        boolean equals = inAppMessageModal.getImageStyle().equals(ImageStyle.GRAPHIC);
        AppboyInAppMessageModalView appropriateModalView = getAppropriateModalView(activity, equals);
        appropriateModalView.inflateStubViews(activity, inAppMessageModal);
        String appropriateImageUrl = appropriateModalView.getAppropriateImageUrl(iInAppMessage);
        if (!StringUtils.isNullOrEmpty(appropriateImageUrl)) {
            Appboy.getInstance(applicationContext).getAppboyImageLoader().renderUrlIntoView(applicationContext, appropriateImageUrl, appropriateModalView.getMessageImageView(), AppboyViewBounds.IN_APP_MESSAGE_MODAL);
        }
        appropriateModalView.getFrameView().setOnClickListener((View.OnClickListener) null);
        appropriateModalView.setMessageBackgroundColor(iInAppMessage.getBackgroundColor());
        appropriateModalView.setFrameColor(inAppMessageModal.getFrameColor());
        appropriateModalView.setMessageButtons(inAppMessageModal.getMessageButtons());
        appropriateModalView.setMessageCloseButtonColor(inAppMessageModal.getCloseButtonColor());
        if (!equals) {
            appropriateModalView.setMessage(iInAppMessage.getMessage());
            appropriateModalView.setMessageTextColor(iInAppMessage.getMessageTextColor());
            appropriateModalView.setMessageHeaderText(inAppMessageModal.getHeader());
            appropriateModalView.setMessageHeaderTextColor(inAppMessageModal.getHeaderTextColor());
            appropriateModalView.setMessageIcon(iInAppMessage.getIcon(), iInAppMessage.getIconColor(), iInAppMessage.getIconBackgroundColor());
            appropriateModalView.setMessageHeaderTextAlignment(inAppMessageModal.getHeaderTextAlign());
            appropriateModalView.setMessageTextAlign(inAppMessageModal.getMessageTextAlign());
            appropriateModalView.resetMessageMargins(iInAppMessage.getImageDownloadSuccessful());
        }
        return appropriateModalView;
    }

    /* access modifiers changed from: package-private */
    public AppboyInAppMessageModalView getAppropriateModalView(Activity activity, boolean z) {
        if (z) {
            return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal_graphic, (ViewGroup) null);
        }
        return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal, (ViewGroup) null);
    }
}
