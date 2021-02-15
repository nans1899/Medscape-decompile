package com.webmd.wbmdcmepulse.models;

import android.content.Context;
import android.os.Build;
import com.facebook.appevents.AppEventsConstants;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;

public class CPEvent {
    public static final String ACTIVITY_NAME_COMPLETION = "completion";
    public static final String ACTIVITY_NAME_PARTICIPATION = "participation";
    private static final String ACTIVITY_NAME_SEARCH = "education";
    private static final String ACTIVITY_NAME_VIEW = "view";
    private static final String APP_NAME_QNA = "cme";
    private static final String APP_NAME_SEARCH = "search";
    private static final String APP_NAME_VIEW_ARTICLE = "viewarticle";
    private static final String URL_SEARCH = "search/click";
    public String activityId;
    public String activityName;
    public String appname;
    public String[] blockCode;
    public String contentGroup;
    private Context context;
    public String date;
    public String leadConcept;
    public String leadSpec;
    private UserProfile mUserProfile = new UserProfile();
    public String pageN;
    public String search;
    public String uid;
    public String url;

    public CPEvent(Context context2) {
        this.context = context2;
    }

    public CPEvent buildViewArticleEvent(String str, String str2, String[] strArr, String str3, String str4) throws Exception {
        String str5;
        CPEvent cPEvent = new CPEvent(this.context);
        cPEvent.uid = this.mUserProfile.getBasicProfile().getUserId();
        cPEvent.activityId = str;
        cPEvent.appname = APP_NAME_VIEW_ARTICLE;
        cPEvent.activityName = ACTIVITY_NAME_VIEW;
        cPEvent.pageN = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        cPEvent.date = getFormattedDateTimeNowString();
        int i = Build.VERSION.SDK_INT;
        try {
            str5 = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str5 = "1.0";
        }
        cPEvent.url = Utilities.generateEnvironment(this.context, "https://api%s.medscape.com/contentservice/render/cme/article/") + str + "?devicetype=android&src=cmepulseapp-android&output_version=appxml&token=" + SharedPreferenceProvider.get().getSimpleCryptoDecryptedString("token", "", this.context) + "&osversion=" + i + "&fl=en_us&appversion=" + str5;
        cPEvent.contentGroup = str2;
        cPEvent.blockCode = strArr;
        cPEvent.leadConcept = str3;
        cPEvent.leadSpec = str4;
        return cPEvent;
    }

    public CPEvent buildSearchEvent(String str) throws Exception {
        CPEvent cPEvent = new CPEvent(this.context);
        cPEvent.uid = this.mUserProfile.getBasicProfile().getUserId();
        cPEvent.appname = "search";
        cPEvent.activityName = "education";
        cPEvent.date = getFormattedDateTimeNowString();
        cPEvent.url = URL_SEARCH;
        cPEvent.search = str;
        return cPEvent;
    }

    public CPEvent buildQnaParticipationEvent(String str, String str2, String[] strArr, String str3, String str4, String str5) throws Exception {
        CPEvent buildQnaEvent = buildQnaEvent(str);
        buildQnaEvent.activityName = ACTIVITY_NAME_PARTICIPATION;
        buildQnaEvent.contentGroup = str2;
        buildQnaEvent.blockCode = strArr;
        buildQnaEvent.leadConcept = str3;
        buildQnaEvent.leadSpec = str4;
        buildQnaEvent.activityId = str5;
        return buildQnaEvent;
    }

    public CPEvent buildQnaCompletionEvent(String str, String str2, String[] strArr, String str3, String str4, String str5) throws Exception {
        CPEvent buildQnaEvent = buildQnaEvent(str);
        buildQnaEvent.activityName = ACTIVITY_NAME_COMPLETION;
        buildQnaEvent.contentGroup = str2;
        buildQnaEvent.blockCode = strArr;
        buildQnaEvent.leadConcept = str3;
        buildQnaEvent.leadSpec = str4;
        buildQnaEvent.activityId = str5;
        return buildQnaEvent;
    }

    private CPEvent buildQnaEvent(String str) throws Exception {
        CPEvent cPEvent = new CPEvent(this.context);
        cPEvent.uid = this.mUserProfile.getBasicProfile().getUserId();
        cPEvent.appname = "cme";
        cPEvent.date = getFormattedDateTimeNowString();
        cPEvent.url = Utilities.generateEnvironment(this.context, "https://www%s.medscape.org/qna/processor/") + str;
        return cPEvent;
    }

    private String getFormattedDateTimeNowString() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
