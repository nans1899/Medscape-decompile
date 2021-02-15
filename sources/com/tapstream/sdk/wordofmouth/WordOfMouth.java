package com.tapstream.sdk.wordofmouth;

import android.app.Activity;
import android.view.View;

public interface WordOfMouth {
    void consumeReward(Reward reward);

    boolean isConsumed(Reward reward);

    void showOffer(Activity activity, View view, Offer offer);
}
