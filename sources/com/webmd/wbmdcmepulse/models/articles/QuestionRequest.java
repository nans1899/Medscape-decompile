package com.webmd.wbmdcmepulse.models.articles;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

public class QuestionRequest {
    public static final String QUESTION_TYPE_INTERNAL = "INTERNAL";
    public static final String QUESTION_TYPE_POST = "POST";
    public String formId;
    public String guid;
    public QuestionResponse[] questionResponseList;
    public String questionType;
    public String questionnaireId;
    public String siteid = NativeAppInstallAd.ASSET_HEADLINE;
}
