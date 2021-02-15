package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.appboy.Appboy;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView;

public class AppboySlideupViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageSlideupView createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        InAppMessageSlideup inAppMessageSlideup = (InAppMessageSlideup) iInAppMessage;
        AppboyInAppMessageSlideupView appboyInAppMessageSlideupView = (AppboyInAppMessageSlideupView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_slideup, (ViewGroup) null);
        appboyInAppMessageSlideupView.inflateStubViews(iInAppMessage);
        String appropriateImageUrl = appboyInAppMessageSlideupView.getAppropriateImageUrl(iInAppMessage);
        if (!StringUtils.isNullOrEmpty(appropriateImageUrl)) {
            Appboy.getInstance(applicationContext).getAppboyImageLoader().renderUrlIntoView(applicationContext, appropriateImageUrl, appboyInAppMessageSlideupView.getMessageImageView(), AppboyViewBounds.IN_APP_MESSAGE_SLIDEUP);
        }
        appboyInAppMessageSlideupView.setMessageBackgroundColor(inAppMessageSlideup.getBackgroundColor());
        appboyInAppMessageSlideupView.setMessage(inAppMessageSlideup.getMessage());
        appboyInAppMessageSlideupView.setMessageTextColor(inAppMessageSlideup.getMessageTextColor());
        appboyInAppMessageSlideupView.setMessageTextAlign(inAppMessageSlideup.getMessageTextAlign());
        appboyInAppMessageSlideupView.setMessageIcon(inAppMessageSlideup.getIcon(), inAppMessageSlideup.getIconColor(), inAppMessageSlideup.getIconBackgroundColor());
        appboyInAppMessageSlideupView.setMessageChevron(inAppMessageSlideup.getChevronColor(), inAppMessageSlideup.getClickAction());
        appboyInAppMessageSlideupView.resetMessageMargins(iInAppMessage.getImageDownloadSuccessful());
        return appboyInAppMessageSlideupView;
    }
}
