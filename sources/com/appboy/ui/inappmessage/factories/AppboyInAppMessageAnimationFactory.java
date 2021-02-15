package com.appboy.ui.inappmessage.factories;

import android.content.res.Resources;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.ui.inappmessage.IInAppMessageAnimationFactory;
import com.appboy.ui.support.AnimationUtils;

public class AppboyInAppMessageAnimationFactory implements IInAppMessageAnimationFactory {
    private final int mShortAnimationDurationMillis = Resources.getSystem().getInteger(17694720);

    public Animation getOpeningAnimation(IInAppMessage iInAppMessage) {
        if (!(iInAppMessage instanceof InAppMessageSlideup)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(0.0f, 1.0f), (long) this.mShortAnimationDurationMillis, true);
        }
        if (((InAppMessageSlideup) iInAppMessage).getSlideFrom() == SlideFrom.TOP) {
            return AnimationUtils.createVerticalAnimation(-1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
    }

    public Animation getClosingAnimation(IInAppMessage iInAppMessage) {
        if (!(iInAppMessage instanceof InAppMessageSlideup)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(1.0f, 0.0f), (long) this.mShortAnimationDurationMillis, false);
        }
        if (((InAppMessageSlideup) iInAppMessage).getSlideFrom() == SlideFrom.TOP) {
            return AnimationUtils.createVerticalAnimation(0.0f, -1.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(0.0f, 1.0f, (long) this.mShortAnimationDurationMillis, false);
    }
}
