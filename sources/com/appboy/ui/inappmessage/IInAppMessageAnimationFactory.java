package com.appboy.ui.inappmessage;

import android.view.animation.Animation;
import com.appboy.models.IInAppMessage;

public interface IInAppMessageAnimationFactory {
    Animation getClosingAnimation(IInAppMessage iInAppMessage);

    Animation getOpeningAnimation(IInAppMessage iInAppMessage);
}
