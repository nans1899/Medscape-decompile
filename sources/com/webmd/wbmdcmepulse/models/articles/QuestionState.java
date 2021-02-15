package com.webmd.wbmdcmepulse.models.articles;

public class QuestionState {
    public String formId;
    public String formatType;
    public boolean isPoll;
    public boolean isPreTest;
    public boolean isResponded;
    public boolean isScorable;
    public boolean showAnswerTable;
    public String tag;

    public QuestionState() {
    }

    public QuestionState(ActivityTest activityTest) {
        this.formatType = activityTest.formatType;
        this.isScorable = activityTest.isScorable;
        this.formId = activityTest.id;
    }
}
