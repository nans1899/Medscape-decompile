package com.webmd.wbmdcmepulse.omniture;

import android.content.Context;
import com.webmd.wbmdomnituremanager.WBMDOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import java.util.HashMap;
import java.util.Map;

public class OmnitureData extends WBMDOmnitureData {
    public static final String KEY_QUERYTEXT = "wapp.querytext";
    public static final String LINK_NAME_ARTICLE_SAVED = "save";
    public static final String LINK_NAME_MOC_LINK_RESUBMIT = "resubmit";
    public static final String LINK_NAME_MOC_RESUBMIT_LINK = "resubmit";
    public static final String LINK_NAME_MOC_SUBMIT_LINK = "submit";
    public static final String LINK_NAME_QNA_SERVICE_ERROR = "error";
    public static final String LINK_NAME_QNA_SUCCESS = "success";
    public static final String LINK_NAME_QNA_VALIDATION = "validation";
    public static final String LINK_NAME_TAB_SLIDES = "slides";
    public static final String LINK_NAME_TAB_VIDEO = "video";
    public static final String LINK_NAME_TRACKER_COMPLETE = "completed";
    public static final String LINK_NAME_TRACKER_IN_PROGRESS = "inprogress";
    public static final String LINK_NAME_VIDEO_PAUSE = "pause";
    public static final String LINK_NAME_VIDEO_PLAY = "play";
    public static final String MODULE_NAME_CME_TRACKER = "edu-tracker";
    public static final String MODULE_NAME_MOC_SUBMIT_BUTTON = "moc-submit-btn";
    public static final String MODULE_NAME_QNA = "edu-%s-%s";
    public static final String MODULE_NAME_QNA_FIRST_SUBMISSION = "cmetst-taker-fin";
    public static final String MODULE_NAME_QNA_SCORABLE = "cmetst-taker-qual";
    public static final String MODULE_NAME_SLIDES_SWIPE = "edu-slide-swp";
    public static final String MODULE_NAME_VIDEO = "player";
    public static final String MODULE_NAME_VIDEO_SLIDES_TAB = "edu-toggle";
    public static final String PAGE_NAME_ARTICLE_FULL_SCREEN_IMAGE = "viewarticle/%s-slide-%s";
    public static final String PAGE_NAME_ARTICLE_INFO_LAYER = "activity-info/view/%s";
    public static final String PAGE_NAME_ARTICLE_VIEW = "viewarticle/%s";
    public static final String PAGE_NAME_ARTICLE_VIEW_PAGE = "viewarticle/%s-%s";
    public static final String PAGE_NAME_CERTIFICATE_VIEW = "certificate/view/";
    public static final String PAGE_NAME_CME_TRACKER = "activitytracker/view/%s";
    public static final String PAGE_NAME_CME_TRACKER_ACTION = "activitytracker/view";
    public static final String PAGE_NAME_CONGRATULATIONS_VIEW = "complete/view/%s";
    public static final String PAGE_NAME_CONTINUE_VIEW = "congratulations/view/%s";
    public static final String PAGE_NAME_EVAL_WEB = "evaluation/view/%s";
    public static final String PAGE_NAME_FEED = "section-name/view/%s";
    public static final String PAGE_NAME_PAGE_NAME_ABIM_ID = "mocsubmission/view";
    public static final String PAGE_NAME_POST_TEST = "cmetest/view/%s";
    public static final String PAGE_NAME_SAVED_ARTICLES = "saved/view";
    public static String mAppName = "app-edu";
    public static Map<String, String> mOmnitureDefaultData = new HashMap();
    private final Context mContext;

    public OmnitureData(Context context) {
        this.mContext = context;
        WBMDOmnitureManager.mPageNamePrefix = "medscape.com/";
    }

    public Map<String, String> addPageNames() {
        return new HashMap();
    }

    public Map<String, String> addPageNameExceptions() {
        return new HashMap();
    }

    public Map<String, String> addModuleNames() {
        return new HashMap();
    }

    public Map<String, String> addModuleNameExceptions() {
        return new HashMap();
    }

    public Map<String, String> addDefaultData() {
        return mOmnitureDefaultData;
    }

    public String addAppName() {
        return mAppName;
    }
}
