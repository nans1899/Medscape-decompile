package com.webmd.wbmdcmepulse.controllers;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.webmd.wbmdcmepulse.models.articles.QuestionState;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public class ArticleController {
    private Context mContext;
    private boolean mIsEducationalImpactChallange;
    private UserProfile mUserProfile;

    public ArticleController(Context context, UserProfile userProfile) {
        this.mContext = context;
        this.mUserProfile = userProfile;
    }

    public UserProfile getUserProfile() {
        return this.mUserProfile;
    }

    public boolean shouldShowAnimatePoll(QuestionState questionState) {
        return questionState.showAnswerTable && questionState.isPoll;
    }

    public void goToQuestion(final View view) {
        new Handler().post(new Runnable() {
            public void run() {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                view.clearFocus();
            }
        });
    }

    public void setViewVisibile(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public boolean isPollableQuestion(QuestionState questionState) {
        return questionState != null && !questionState.isResponded && questionState.isPoll && questionState.showAnswerTable;
    }

    public void setIsEducationalImpactChallenge(boolean z) {
        this.mIsEducationalImpactChallange = z;
    }

    public boolean getIsEducationalImpactChallenge() {
        return this.mIsEducationalImpactChallange;
    }
}
