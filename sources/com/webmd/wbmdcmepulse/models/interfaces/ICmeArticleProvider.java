package com.webmd.wbmdcmepulse.models.interfaces;

import android.content.Context;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.QuestionRequest;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;

public interface ICmeArticleProvider {
    void getArticle(String str, ICallbackEvent<Article, CMEPulseException> iCallbackEvent);

    void getQna(String str, ICallbackEvent<Questionnaire, CMEPulseException> iCallbackEvent, Context context);

    void submitQuestionResponseList(QuestionRequest questionRequest, ICallbackEvent<String, CMEPulseException> iCallbackEvent, Context context);
}
