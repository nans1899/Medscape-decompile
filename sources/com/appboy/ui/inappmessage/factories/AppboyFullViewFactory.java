package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.appboy.Appboy;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageFullView;
import com.appboy.ui.support.ViewUtils;

public class AppboyFullViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageFullView createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
        Context applicationContext = activity.getApplicationContext();
        InAppMessageFull inAppMessageFull = (InAppMessageFull) iInAppMessage;
        boolean equals = inAppMessageFull.getImageStyle().equals(ImageStyle.GRAPHIC);
        AppboyInAppMessageFullView appropriateFullView = getAppropriateFullView(activity, equals);
        appropriateFullView.inflateStubViews(activity, inAppMessageFull);
        String appropriateImageUrl = appropriateFullView.getAppropriateImageUrl(iInAppMessage);
        if (!StringUtils.isNullOrEmpty(appropriateImageUrl)) {
            Appboy.getInstance(applicationContext).getAppboyImageLoader().renderUrlIntoView(applicationContext, appropriateImageUrl, appropriateFullView.getMessageImageView(), AppboyViewBounds.NO_BOUNDS);
        }
        appropriateFullView.getFrameView().setOnClickListener((View.OnClickListener) null);
        appropriateFullView.setMessageBackgroundColor(inAppMessageFull.getBackgroundColor());
        appropriateFullView.setFrameColor(inAppMessageFull.getFrameColor());
        appropriateFullView.setMessageButtons(inAppMessageFull.getMessageButtons());
        appropriateFullView.setMessageCloseButtonColor(inAppMessageFull.getCloseButtonColor());
        if (!equals) {
            appropriateFullView.setMessage(inAppMessageFull.getMessage());
            appropriateFullView.setMessageTextColor(inAppMessageFull.getMessageTextColor());
            appropriateFullView.setMessageHeaderText(inAppMessageFull.getHeader());
            appropriateFullView.setMessageHeaderTextColor(inAppMessageFull.getHeaderTextColor());
            appropriateFullView.setMessageHeaderTextAlignment(inAppMessageFull.getHeaderTextAlign());
            appropriateFullView.setMessageTextAlign(inAppMessageFull.getMessageTextAlign());
            appropriateFullView.resetMessageMargins(inAppMessageFull.getImageDownloadSuccessful());
        }
        resetLayoutParamsIfAppropriate(activity, inAppMessageFull, appropriateFullView);
        return appropriateFullView;
    }

    /* access modifiers changed from: package-private */
    public boolean resetLayoutParamsIfAppropriate(Activity activity, IInAppMessage iInAppMessage, AppboyInAppMessageFullView appboyInAppMessageFullView) {
        RelativeLayout.LayoutParams layoutParams;
        if (!(!ViewUtils.isRunningOnTablet(activity) || iInAppMessage.getOrientation() == null || iInAppMessage.getOrientation() == Orientation.ANY)) {
            int longEdge = appboyInAppMessageFullView.getLongEdge();
            int shortEdge = appboyInAppMessageFullView.getShortEdge();
            if (longEdge > 0 && shortEdge > 0) {
                if (iInAppMessage.getOrientation() == Orientation.LANDSCAPE) {
                    layoutParams = new RelativeLayout.LayoutParams(longEdge, shortEdge);
                } else {
                    layoutParams = new RelativeLayout.LayoutParams(shortEdge, longEdge);
                }
                layoutParams.addRule(13, -1);
                appboyInAppMessageFullView.getMessageBackgroundObject().setLayoutParams(layoutParams);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public AppboyInAppMessageFullView getAppropriateFullView(Activity activity, boolean z) {
        if (z) {
            return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full_graphic, (ViewGroup) null);
        }
        return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full, (ViewGroup) null);
    }
}
