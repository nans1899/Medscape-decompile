package com.webmd.wbmdcmepulse.models.interfaces;

import com.webmd.wbmdcmepulse.models.articles.QuestionResponse;

public interface ITestScoreListener {
    void onAnswersSubmitted(boolean z, double d, QuestionResponse[] questionResponseArr, boolean z2);

    void onTestAnswerChanged(int i, boolean z);
}
