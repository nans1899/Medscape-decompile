package com.webmd.wbmdcmepulse.models.utils;

import android.content.Context;
import android.os.Build;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;

public class Constants {
    public static final String ABOUT_VERSION = "about";
    public static final String ACTIVITY_COMPLETE_BUTTON_LABEL = "You have already completed this activity";
    public static final String ACTIVITY_EXPIRED_BUTTON_LABEL = "This Activity has Expired";
    public static final String ADD_TOPICS_CUSTOM_DEEPLINK = "cmepulse://addtopics/custom";
    public static final String ADD_TOPICS_DEEPLINK = "cmepulse://addtopics";
    public static final String APP_BOY_SOURCE_KEY = "source";
    public static final String APP_DIRECTORY_PATH = "app_directory_path";
    public static final String ARTICLE_DEEPLINK = "cmepulse://viewarticle";
    public static final int AUTHENTICATION_STATUS_ACCEPTED = 3009;
    public static final int AUTHENTICATION_STATUS_INVALID = 3008;
    public static final int AUTHENTICATION_STATUS_OFFLINE = 3007;
    public static final String BACKGROUND_FLAG = "background_flag";
    public static final String BROADCAST_CONTENTUPDATEACTION = "com.medscape.cmepulse.contentupdate.BROADCAST";
    public static final String BROADCAST_UPDATEACTION = "com.medscape.cmepulse.appupdate.BROADCAST";
    public static final String BUNDLE_DEST_URL = "desturl";
    public static final String BUNDLE_EXTRA_APPBOY_DEEPLINK = "uri";
    public static final String BUNDLE_FEEDS = "FEEDS_BUNDLE";
    public static final String BUNDLE_HIDE_HEADER = "hide_header";
    public static final String BUNDLE_KEY_ARTICLE = "bundle_key_article";
    public static final String BUNDLE_KEY_ARTICLE_ID = "article_id";
    public static final String BUNDLE_KEY_ARTICLE_PAGE_NUMBER = "bundle_key_articlePage_number";
    public static final String BUNDLE_KEY_ARTICLE_PASS_SCORE = "bundle_article_pass_score";
    public static final String BUNDLE_KEY_ARTICLE_QNA = "bundle_key_article_qna";
    public static final String BUNDLE_KEY_ARTICLE_REFERENCES = "bundle_key_article_references";
    public static final String BUNDLE_KEY_ARTICLE_THUMB_URL = "bundle_key_article_thumb_url";
    public static final String BUNDLE_KEY_ARTICLE_TITLE = "article_title";
    public static final String BUNDLE_KEY_ASSET_ID = "asset_id";
    public static final String BUNDLE_KEY_CAME_FROM_TRACKER = "bundle_key_came_from_tracker";
    public static final String BUNDLE_KEY_DISCLOSURES = "bundle_key_disclosures";
    public static final String BUNDLE_KEY_DOWNLOAD_URL = "bundle_key_download_url";
    public static final String BUNDLE_KEY_EVAL_IS_TAKEN = "bundle_key_eval_is_taken";
    public static final String BUNDLE_KEY_EVAL_REQUIRED = "bundle_key_eval_required";
    public static final String BUNDLE_KEY_EVAL_STAND_ALONE = "bundle_key_eval_stand_alone";
    public static final String BUNDLE_KEY_FEED_URL = "bundle_key_feed_url";
    public static final String BUNDLE_KEY_FULL_SCREEN_ORNTN = "full_screen_orntn";
    public static final String BUNDLE_KEY_GOTO_EVAL = "bundle_key_goto_eval";
    public static final String BUNDLE_KEY_GO_BACK_HOME = "bundle_key_go_back_home";
    public static final String BUNDLE_KEY_IMAGE_FULL_SCREEN = "bundle_key_image_full_screen";
    public static final String BUNDLE_KEY_IMAGE_HEIGHT = "bundle_key_image_height";
    public static final String BUNDLE_KEY_IMAGE_SLIDE = "bundle_key_image_slide";
    public static final String BUNDLE_KEY_IMAGE_SLIDES = "bundle_key_image_slides";
    public static final String BUNDLE_KEY_IMAGE_SLIDE_POSITION = "bundle_key_image_slide_position";
    public static final String BUNDLE_KEY_IMAGE_WIDTH = "bundle_key_image_with";
    public static final String BUNDLE_KEY_IS_DIAlOG = "bundle_key_is_dialog";
    public static final String BUNDLE_KEY_IS_EIC = "bundle_key_is_eic";
    public static final String BUNDLE_KEY_IS_EVAL_REQ_DIALOG_VISIBLE = "is_eval_req_dialog_visible";
    public static final String BUNDLE_KEY_IS_MOC_ELIGIBLE = "bundle_key_is_moc_elgible";
    public static final String BUNDLE_KEY_LIVE_EVENT_REGISTRATION_URL = "bundle_key_live_event_registration_url";
    public static final String BUNDLE_KEY_MAX_CREDITS = "bundle_key_max_credits";
    public static final String BUNDLE_KEY_QNA = "bundle_key_qna";
    public static final String BUNDLE_KEY_QNA_ID = "bundle_key_qna_id";
    public static final String BUNDLE_KEY_REFERRING_LINK = "bundle_key_feed_position";
    public static final String BUNDLE_KEY_REFERRING_MODULE = "bundle_referring_module_key";
    public static final String BUNDLE_KEY_REFERRING_PAGE = "bundle_topic_key_id";
    public static final String BUNDLE_KEY_REFERRING_QUERY = "bundle_key_referring_query";
    public static final String BUNDLE_KEY_SAVED_ARTICLE = "bundle_key_saved_article";
    public static final String BUNDLE_KEY_SEARCH_QUERY = "search_query";
    public static final String BUNDLE_KEY_TRACKER_ITEMS = "bundle_key_tracker_items";
    public static final String BUNDLE_KEY_TRACKER_MAP = "bundle_key_tracker_map";
    public static final String BUNDLE_KEY_TRACKER_YEAR = "bundle_key_tracker_year";
    public static final String BUNDLE_KEY_TYPE_AHEAD_QUERY = "type_ahead_query";
    public static final String BUNDLE_KEY_USER_ID = "bundle_key_user_id";
    public static final String BUNDLE_KEY_USER_PROFILE = "bundle_key_user_profile";
    public static final String BUNDLE_KEY_VIDEO_IS_PLAYING = "video_is_playing";
    public static final String BUNDLE_KEY_VIDEO_LOCATION = "video_uri";
    public static final String BUNDLE_KEY_VIDEO_POSITION = "video_current_position";
    public static final String BUNDLE_OMNITURE_PAGENAME = "omniture";
    public static final String BUNDLE_PAGE_NAME = "page_name";
    public static final String BUNDLE_PAGE_TITLE = "pagetitle";
    public static final String BUNDLE_PAGE_URL = "pageurl";
    public static final String BUNDLE_SELECTED_SUBSCRIPTION = "selected_subscription";
    public static final String BUNDLE_SELECT_HOME_PAGE_ID = "select_home_page_id";
    public static final String BUNDLE_SET_ACTIONBAR_TITLE = "set_actionbar_title";
    public static final String BUNDLE_SHOW_UP = "show_up";
    public static final String BUNDLE_SUBSCRIPTION_ID = "subscriptionid";
    public static final String BUNDLE_SUBSCRIPTION_TITLE = "subscriptiontitle";
    public static final String BUNDLE_SUBSCRIPTION_TYPE = "subscriptiontype";
    public static final String BUNDLE_TOPIC_CREDIT_AMOUNT = "bundle_custom_topic_credit_amount";
    public static final String BUNDLE_TOPIC_CREDIT_TYPE = "bundle_custom_topic_credit_type";
    public static final int CMEACTIVITY_REQUEST_CODE = 8000;
    public static final int CMEACTIVITY_UPDATE_CODE = 9;
    public static final String CMEPULSE_KEYCHAIN_PREF_NAME = "cmepulse.keychain";
    public static final String CME_TRACKER_ACTIVITY_NAME = "CMETrackerActivity";
    public static final String CME_TRACKER_DEEPLINK = "cmepulse://cmetracker";
    public static final int COLOR_MEDSCAPE_RED = 11548981;
    public static final String CONTENT_CHECK_SAVE = "com.webmd.wbmdcmepulse.undo.save.check";
    public static final int CONTENT_FEED = 0;
    public static final String CONTENT_IS_SAVED = "com.webmd.wbmdcmepulse.save.content.is.saved";
    public static final String CONTENT_RESPONSE_SAVE = "com.webmd.wbmdcmepulse.undo.save.response";
    public static final String CONTENT_SAVE_ARTICLE = "com.webmd.wbmdcmepulse.save.content.article";
    public static final String CONTENT_SAVE_ARTICLEID = "com.webmd.wbmdcmepulse.save.content.articleid";
    public static final String CONTENT_SAVE_TITLE = "com.webmd.wbmdcmepulse.save.content.title";
    public static final String CONTENT_SAVE_UNSAVE_ACTION = "com.webmd.wbmdcmepulse.save.unsave.content";
    public static final String CONTENT_SAVE_URL = "com.webmd.wbmdcmepulse.save.content.url";
    public static final String COOKIE_KEY = "Cookie";
    public static final String COUNTRY_CODE_DE = "de_de";
    public static final int COUNTRY_CODE_DE_INT = 1004;
    public static final String COUNTRY_CODE_FR = "fr_fr";
    public static final int COUNTRY_CODE_FR_INT = 1005;
    public static final String COUNTRY_CODE_NON_US = "non_us";
    public static final int COUNTRY_CODE_NON_US_INT = 1006;
    public static final String COUNTRY_CODE_US = "en_us";
    public static final int COUNTRY_CODE_US_INT = 1003;
    public static final int CREDIT_TYPE_LOC = 6;
    public static final int CREDIT_TYPE_MEDICAL_LABORATORY = 5;
    public static final int CREDIT_TYPE_MOC = 9;
    public static final int CREDIT_TYPE_NURSE = 2;
    public static final int CREDIT_TYPE_NURSE_PRACTITIONER = 7;
    public static final int CREDIT_TYPE_PHARMACIST = 3;
    public static final int CREDIT_TYPE_PHYSICIAN = 1;
    public static final int CREDIT_TYPE_PHYSICIANS_ASSISTANT = 8;
    public static final int CREDIT_TYPE_PSYCHOLOGIST = 4;
    public static final String CUSTOM_TOPIC_CREDIT_AMOUNT_KEY = "custom_topic_amount";
    public static final String CUSTOM_TOPIC_CREDIT_TYPE_KEY = "custom_topic_type";
    public static final String CUSTOM_TOPIC_ID_KEY = "custom_topic_id";
    public static final String CUSTOM_WEB_DEEPLINK = "cmepulse://customweb";
    public static final String CUSTOM_WEB_PARAM_CONTNET_URL = "contenturl";
    public static final String CUSTOM_WEB_PARAM_TITLE = "title";
    public static final String CUTOM_TOPIC_ADD_IS_EDITING_KEY = "is_editing_custom_topic";
    public static final String DEBUG_ARTICLE = "DEBUGConfig_Article";
    public static final String DEBUG_CONFIG = "DEBUGConfig_Config";
    public static final String DEBUG_FEED = "DEBUGConfig_Feed";
    public static final String DEBUG_SERVICE = "DEBUGConfig_Service";
    public static final String DO_NOT_MAKE_OMNITURE_CALL = "do_not_make_omniture_call";
    public static final String EXTENDED_DATA_MESSAGE = "com.medscape.cmepulse.appupdate.custommessage";
    public static final String EXTENDED_DATA_TITLE = "com.medscape.cmepulse.appupdate.customtitle";
    public static final String EXTRA_CONTENTUPDATETYPE = "com.medscape.cmepulse.contentupdatetype";
    public static final String EXTRA_GO_TO_LOGIN = "com.medscape.cme.android.EXTRA_GO_TO_LOGIN";
    public static final String EXTRA_TAG_ID = "tag_id";
    public static final String FAQ_VERSION = "faq";
    public static final String FEEDBACK_VERSION = "feedback";
    public static final String FEED_DEEPLINK = "cmepulse://feed";
    public static final String FEED_PARAM_SUBSCRIPTION_ID_KEY = "subscription_id";
    public static final String FEED_PARAM_SUBSCRIPTION_ID_VALUE_FEATURED = "featured";
    public static final String FEED_PARAM_SUBSCRIPTION_ID_VALUE_FEATURED_CME = "Featured CME";
    public static final String FEED_PARAM_SUBSCRIPTION_TITLE = "subscription_title";
    public static final int FEED_SPAN_COUNT = 3;
    public static final String FRAGMENT_TAG_ADD_TOPIC = "add_topic";
    public static final String FRAGMENT_TAG_ARTICLE = "fragment_article";
    public static final String FRAGMENT_TAG_ARTICLE_ABBREVIATIONS = "fragment_article_abbreviations";
    public static final String FRAGMENT_TAG_ARTICLE_DISCLOSURES = "cme_fragment_article_disclosures";
    public static final String FRAGMENT_TAG_ARTICLE_REFERENCES = "cme_fragment_article_references";
    public static final String FRAGMENT_TAG_FEED = "fragment_feed";
    public static final String FRAGMENT_TAG_HOME = "fragment_home";
    public static final String FRAGMENT_TAG_SEARCH = "fragment_search";
    public static final String FRAGMENT_TAG_TOPIC = "fragment_topics";
    public static final String HAS_SEEN_ARTICLE_SWIPE_HINT = "has_seen_swipe_hint";
    public static final String HIDE_PRIVACY_HEADER_FLAG = "hide_privacy_header_flag";
    public static final String HIDE_TERMS_HEADER_FLAG = "hide_terms_header_flag";
    public static final String HOME_ACTIVITY_NAME = "HomeActivity";
    public static final String HTML_TYPE_LIST_ITEM = "li";
    public static final String HTML_TYPE_ORDERED_LIST = "ol";
    public static final String HTML_TYPE_PARAGRAPH = "p";
    public static final String HTML_TYPE_TABLE = "table";
    public static final String HTML_TYPE_UNORDERED_LIST = "ul";
    public static final double IMAGESCALE_FEED_FRAG_HEIGHT = 0.3d;
    public static final double IMAGESCALE_FEED_FRAG_WIDTH = 1.0d;
    public static final double IMAGESCALE_NINE_TABLET_HOME_ITEM_HEIGHT = 0.22d;
    public static final double IMAGESCALE_NINE_TABLET_HOME_ITEM_WIDTH = 0.33d;
    public static final double IMAGESCALE_NINE_TABLET_LANDSCAPE_HOME_ITEM_HEIGHT = 0.3d;
    public static final double IMAGESCALE_NINE_TABLET_LANDSCAPE_HOME_ITEM_WIDTH = 0.25d;
    public static final double IMAGESCALE_NINE_TABLET_LANDSCAPE_TOPICS_ITEM_HEIGHT = 0.35d;
    public static final double IMAGESCALE_NINE_TABLET_LANDSCAPE_TOPICS_ITEM_WIDTH = 0.29d;
    public static final double IMAGESCALE_NINE_TABLET_TOPICS_ITEM_HEIGHT = 0.24d;
    public static final double IMAGESCALE_NINE_TABLET_TOPICS_ITEM_WIDTH = 0.36d;
    public static final double IMAGESCALE_PHONE_HOME_FEATURED_HEIGHT = 0.45d;
    public static final double IMAGESCALE_PHONE_HOME_FEATURED_WIDTH = 1.0d;
    public static final double IMAGESCALE_PHONE_HOME_ITEM_HEIGHT = 0.25d;
    public static final double IMAGESCALE_PHONE_HOME_ITEM_WIDTH = 0.5d;
    public static final double IMAGESCALE_SEVEN_TABLET_HOME_ITEM_HEIGHT = 0.2d;
    public static final double IMAGESCALE_SEVEN_TABLET_HOME_ITEM_WIDTH = 0.33d;
    public static final double IMAGESCALE_SEVEN_TABLET_LANDSCAPE_HOME_ITEM_HEIGHT = 0.35d;
    public static final double IMAGESCALE_SEVEN_TABLET_LANDSCAPE_HOME_ITEM_WIDTH = 0.25d;
    public static final double IMAGESCALE_SEVEN_TABLET_LANDSCAPE_TOPICS_ITEM_HEIGHT = 0.35d;
    public static final double IMAGESCALE_SEVEN_TABLET_LANDSCAPE_TOPICS_ITEM_WIDTH = 0.29d;
    public static final double IMAGESCALE_SEVEN_TABLET_TOPICS_ITEM_HEIGHT = 0.24d;
    public static final double IMAGESCALE_SEVEN_TABLET_TOPICS_ITEM_WIDTH = 0.47d;
    public static final String INLINE_ACTIVITY_TEST = "inline_activity_test";
    public static final String KEY_CREDITS_EARNED = "key_credits_earned";
    public static final String LAST_LOGIN = "lastlogin";
    public static final String LAUNCH_COUNT = "launch_count";
    public static final int LIST_TYPE_BIRTH_YEAR = 1015;
    public static final int LIST_TYPE_COUNTRY = 1007;
    public static final int LIST_TYPE_GRADUATION_YEAR = 1016;
    public static final int LIST_TYPE_OCCUPATION = 1011;
    public static final int LIST_TYPE_PROFESSION = 1008;
    public static final int LIST_TYPE_SCHOOL = 1014;
    public static final int LIST_TYPE_SCHOOL_COUNTRY = 1013;
    public static final int LIST_TYPE_SCHOOL_STATE = 1012;
    public static final int LIST_TYPE_SPECIALTY = 1009;
    public static final int LIST_TYPE_SUBSPECIALTY = 1010;
    public static final String LIVE_EVENTS_CONFIG = "LiveEventsConfig";
    public static final String LIVE_EVENTS_DATA = "com.webmd.wbmdcmepulse.live.events.data";
    public static final String LIVE_EVENT_REGISTER_ACTION = "com.webmd.wbmdcmepulse.live.event.register";
    public static final String LIVE_EVENT_REGISTER_DEFAULT_URL = "com.webmd.wbmdcmepulse.live.event.register.url";
    public static final String LIVE_EVENT_REGISTER_LINK = "com.webmd.wbmdcmepulse.live.event.register.link";
    public static final int LOGGED_IN = 1000;
    public static final int LOGGED_OUT = 1002;
    public static final String LOGIN_PREF_ADSEGVARSKEY = "adSegvars";
    public static final String LOGIN_PREF_FIRSTNAMEKEY = "firstName";
    public static final String LOGIN_PREF_GUIDKEY = "userGuid";
    public static final String LOGIN_PREF_HOMEPAGEID = "homepageId";
    public static final String LOGIN_PREF_LASTNAMEKEY = "lastName";
    public static final String LOGIN_PREF_MOCSTRINGKEY = "userMocString";
    public static final String LOGIN_PREF_OCCUPATIONKEY = "workOccupation";
    public static final String LOGIN_PREF_OCCUPATIONSTRINGKEY = "userOccupationString";
    public static final String LOGIN_PREF_PREVIOUS_HPID = "prev_hpid";
    public static final String LOGIN_PREF_PROFESSIONKEY = "workProfession";
    public static final String LOGIN_PREF_PROFESSIONSTRINGKEY = "userProfessionString";
    public static final String LOGIN_PREF_SPECIALTYKEY = "userSpecialty";
    public static final String LOGIN_PREF_SPECIALTYSTRINGKEY = "userSpecialtyString";
    public static final String LOGIN_PREF_TARGETLISTDICTKEY = "targetLists";
    public static final String LOGIN_PREF_TOKENKEY = "token";
    public static final String LOGIN_PREF_WORKCOUNTRYKEY = "workCountr";
    public static final String LOGIN_PREF_WORKSTATEKEY = "workState";
    public static final double L_TABLET_SEARCH_IMAGE_HEIGHT = 0.4d;
    public static final double L_TABLET_SEARCH_IMAGE_WIDTH = 0.6d;
    public static final String MAKE_OMNITURE_CALL = "make_omniture_call";
    public static final String MEDPULSE_KEYCHAIN_PREF_NAME = "medpulse.keychain";
    public static final String MEDPULSE_PACKAGE_NAME = "com.medscape.medpulse";
    public static final String MEDSCAPE_KEYCHAIN_PREF_NAME = "medscape.keychain";
    public static final String MEDSCAPE_PACKAGE_NAME = "com.medscape.android";
    public static final int MEDSCAPE_USER = 1001;
    public static final String PREF_APP_BACKGROUND = "appBackground";
    public static final String PREF_COOKIE = "pref_cookie";
    public static final String PREF_COOKIE_STRING = "pref_cookie_stringtxt";
    public static final String PREF_KEY_APP_PACKAGE_NAME = "application.package.name";
    public static final String PREF_KEY_CMETRACKERACTIVITY_NAME = "cmetracker.name";
    public static final String PREF_KEY_HOMEACTIVITY_NAME = "homeactivity.name";
    public static final String PREF_KEY_LANDINGACTIVITY_NAME = "landingactivity.name";
    public static final String PREF_KEY_PASSWORD = "pref_password";
    public static final String PREF_KEY_SAVED_ARTICLE_CODE = "saved_article_code";
    public static final String PREF_KEY_USERNAME = "pref_username";
    public static final String PREF_KEY_USER_PROFILE = "pre_key_user_profile";
    public static final String PREF_UPDATEREQUIRED = "MandatoryUpdateRequired";
    public static final String PREF_UPDATE_REQUIRED_MESSAGE = "mandatoryUpdateRequiredMessage";
    public static final String PREF_UPDATE_REQUIRED_TITLE = "mandatoryUpdateRequiredTitle";
    public static final String PRIVACY_FILE_SUFFIX = "privacy.html";
    public static final String PRIVACY_POLICY_PAGE_NAME = "Privacy";
    public static final String PRIVACY_POLICY_PATH = "privacy_path";
    public static final String PRIVACY_POLICY_VERSION = "privacy";
    public static final String PROFESSION_ID = "wbmd_professionId";
    public static final String PUSH_ACTION_KEY = "action";
    public static final String PUSH_ACTION_VALUE_DEFAULT = "None";
    public static final String PUSH_ACTION_VALUE_OPEN_ACTIVITY = "openActivity";
    public static final String PUSH_TARGET_KEY = "target";
    public static final String PUSH_TARGET_VALUE_CUSTOM_WEB = "customweb";
    public static final String PUSH_TARGET_VALUE_DEFAULT = "None";
    public static final String PUSH_TARGET_VALUE_FEED = "feed";
    public static final double P_TABLET_FEED_IMAGE_HEIGHT = 0.18d;
    public static final double P_TABLET_FEED_IMAGE_WIDTH = 0.33d;
    public static final double P_TABLET_SEARCH_IMAGE_HEIGHT = 0.3d;
    public static final double P_TABLET_SEARCH_IMAGE_WIDTH = 1.0d;
    public static final int REQUEST_CODE_EVALUATION = 7000;
    public static final int REQUEST_CODE_FULL_SCREEN = 5000;
    public static final int REQUEST_CODE_IMAGE_VIEW_FULL_SCREEN = 5002;
    public static final int REQUEST_CODE_MOC_ID = 6000;
    public static final int REQUEST_CODE_SUBSCRIPTIONS_CHANGED = 3000;
    public static final int RESULT_CODE_CUSTOM_TOPICS_HAVE_CHANGED = 4000;
    public static final int RESULT_CODE_EXIT_FULL_SCREEN = 5001;
    public static final int RESULT_CODE_SUBSCRIPTIONS_AND_CUSTOM_HAVE_CHANGED = 4002;
    public static final int RESULT_CODE_SUBSCRIPTIONS_NO_CHANGE = 4003;
    public static final int RESULT_CODE_SUBSCRIPTION_TOPICS_HAVE_CHANGED = 4001;
    public static final String RETURN_ACTIVITY = "returnActivity";
    public static final String SAVED_ARTICLES = "user_saved_articles";
    public static final int SAVED_ARTICLE_GONE_CODE = 0;
    public static final int SAVED_ARTICLE_SELECTION_CODE = 2;
    public static final int SAVED_ARTICLE_SHOW_CODE = 1;
    public static final String SAVED_CUSTOM_TOPIC_IDS = "cutom_topics_pref";
    public static final String SAVED_CUSTOM_TOPIC_OBJECTS = "saved_custom_topic_objects";
    public static final String SAVED_ORDERD_TOPIC_KEYS = "ordered_all_topic_ids_pref";
    public static final String SAVED_SPECIALITY_TOPIC_IDS = "user_saved_subscription";
    public static final int SEARCH_PAGE_COUNT = 25;
    public static final String SECTION_TYPE_SLIDE = "slide";
    public static final String SELECTED_TILE_SUBSCRIPTION_CMD = "selected_topic_subscription_cmd";
    public static final String SELECTED_TILE_SUBSCRIPTION_ID = "selected_topic_subscription_id";
    public static final String SET_COOKIE_KEY = "Set-Cookie";
    public static final String SHARED_PREF_FILE_NAME = "persistent";
    public static final String SHOULD_CALL_LAUNCH_OPEN_METRIC = "shouldCallLaunchOpenMetric";
    public static final String SHOULD_CALL_UPDATE_SERVICE = "shouldCallUpdateService";
    public static final String SHOULD_CALL_UPDATE_SUBSCRIPTIONS = "shouldCallUpdateSubscriptions";
    public static final String SHOULD_INCREMENT_LAUNCH_COUNT = "should_increment";
    public static final String SHOULD_MAKE_OMNITURE_OPEN_CALL = "should_make_omniture_call";
    public static final String SHOULD_SEND_LAUNCH_OPEN = "should_send_launch_open";
    public static final String SPECIALTY_ID = "wbmd.medscape.specialty.id";
    public static final String STATE_REQUIREMENTS_DEEPLINK = "cmepulse://staterequirements";
    public static final String STRING_EMPTY = "";
    public static final int SUBSCRIPITION_ADDED = 0;
    public static final int SUBSCRIPITION_FAILED = -1;
    public static final int SUBSCRIPITION_REMOVED = 1;
    public static final String SUBSCRIPTION_PREF_PUSHKEY = "subscriptionids";
    public static final String SUBSCRIPTION_PREF_SPECIALITYKEY = "subscriptionids";
    public static final String SUBSCRIPTION_TYPE_CUSTOM = "custom";
    public static final String SUBSCRIPTION_TYPE_FEED = "feed";
    public static final int SUBSCRIPTION_TYPE_FEED_INT = 3000;
    public static final String SUBSCRIPTION_TYPE_SAVED = "saved";
    public static final int SUBSCRIPTION_TYPE_SAVED_INT = 3002;
    public static final String SUBSCRIPTION_TYPE_TRACKER = "tracker";
    public static final String TAG = "CMEPulse";
    public static final int TAG_ADD_TOPIC = 2012;
    public static final int TAG_CME_TRACKER = 2011;
    public static final int TAG_CONTINUE_FRAGMENT = 2005;
    public static final int TAG_DEBUG = 2007;
    public static final int TAG_FEED_LIST = 2009;
    public static final int TAG_HOME_REFRESH = 2008;
    public static final int TAG_LANDING_FRAGMENT = 2004;
    public static final int TAG_SIGNIN_FRAGMENT = 2000;
    public static final int TAG_SIGNUP_EDUCATION_FRAGMENT = 2003;
    public static final int TAG_SIGNUP_LIST_FRAGMENT = 2006;
    public static final int TAG_SIGNUP_PROFESSION_FRAGMENT = 2001;
    public static final int TAG_SIGNUP_PROFILE_FRAGMENT = 2002;
    public static final int TAG_USER_SUBSCRIPTION = 2010;
    public static final String TERMSOFUSE_PATH = "terms_path";
    public static final String TERMSOFUSE_VERSION = "terms";
    public static final String TERMS_FILE_SUFFIX = "termsofuse";
    public static final String TOPIC_ACTION_BAR_TITLE = "Topics";
    public static final Integer TOPIC_CUSTOM_TAB_POSITION = 1;
    public static final String TOPIC_CUSTOM_TAB_TITLE = "CUSTOM";
    public static final String TOPIC_SPECIALTIES_TAB_TITLE = "SPECIALTIES";
    public static final String TOPIC_START_TAB = "startTab";
    public static final String TRACKER_LABEL_CE = "CE Tracker";
    public static final String TRACKER_LABEL_CME = "CME Tracker";
    public static final String TRACKER_LABEL_EDUCATION = "Education Tracker";
    public static final String TYPE_AHEAD_URL = Config.getTypeAheadUrl();
    public static final String USER_TAG_CREDITS_EARNED = "creditsEarned";
    public static final String USER_TAG_KEY_COUNTRY = "ct";
    public static final String USER_TAG_KEY_FULL_NAME = "fullName";
    public static final String USER_TAG_KEY_PREFERENCES = "pref";
    public static final String USER_TAG_KEY_PROFILE_ID = "profid";
    public static final String USER_TAG_KEY_SPECIALTY_ID = "spid";
    public static final String USER_TAG_KEY_STATE = "st";
    public static final String USER_TAG_KEY_TOKEN = "token";
    public static final String USER_TAG_KEY_USER_ID = "userId";
    public static final String WEB_VIEW_TITLE_KEY = "TITLE_KEY";
    public static final String WEB_VIEW_URL_KEY = "URL_KEY";
    public static final String XML_LIST = "list";
    public static final String XML_LIST_ITEM = "list-item";
    public static final String XML_LIST_TYPE_KEY = "list-type";
    public static final String XML_LIST_TYPE_VALUE_BULLET = "bullet";
    public static final String XML_LIST_TYPE_VALUE_ORDER = "order";
    public static final String XML_ORDERED_LIST = "list list-type=\"order\"";
    public static final String XML_UNORDERED_LIST = "list list-type='bullet'";

    public static String getAbimVerficationUrl(Context context) {
        Config.getServiceEnvironment();
        return Utilities.generateEnvironment(context, "https://www%s.medscape.org/sites/abim-register");
    }

    public static class Config {
        private static String mArticleEnvironment = "";
        private static String mConfigEnvironment = "";
        private static String mFeedEnvironment = "";
        private static String mServiceEnvironment = "";

        public static String getTypeAheadUrl() {
            return "https://www.webmd.com/api/qrl/LookupService.ashx?q=%s&sz=10&s=10001&namespace=medscape";
        }

        public static void InitConfig(Context context) {
            setmFeedEnvironment(SharedPreferenceProvider.get().getString("pref_env_feed", "", context));
        }

        public static void setmFeedEnvironment(String str) {
            if (!Extensions.isStringNullOrEmpty(str) && !str.startsWith(".")) {
                str = "." + str.toLowerCase();
            }
            mFeedEnvironment = str;
        }

        public static String getFeedEnvironment() {
            return mFeedEnvironment;
        }

        public static void setmServiceEnvironment(String str) {
            if (!Extensions.isStringNullOrEmpty(str) && !str.startsWith(".")) {
                str = "." + str.toLowerCase();
            }
            mServiceEnvironment = str;
        }

        public static String getServiceEnvironment() {
            return mServiceEnvironment;
        }

        public static void setmConfigEnvironment(String str) {
            if (!Extensions.isStringNullOrEmpty(str) && !str.startsWith(".")) {
                str = "." + str.toLowerCase();
            }
            mConfigEnvironment = str;
        }

        public static String getConfigUrl(Context context) {
            String str;
            Context applicationContext = context.getApplicationContext();
            int i = Build.VERSION.SDK_INT;
            try {
                str = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                str = "1.0";
            }
            String lowerCase = mConfigEnvironment.toLowerCase();
            char c = 65535;
            int hashCode = lowerCase.hashCode();
            if (hashCode != 1469735) {
                if (hashCode == 962340941 && lowerCase.equals(".staging")) {
                    c = 0;
                }
            } else if (lowerCase.equals(".dev")) {
                c = 1;
            }
            String str2 = c != 0 ? c != 1 ? "https://www.medscape.com/noscan/mobileapp/public/native-ipad/cmepulseConfig.json" : "http://184.73.69.209/assets/cmepulseConfig.json" : "http://www.staging.medscape.com/noscan/mobileapp/public/native-ipad/cmepulseConfig.json";
            return String.format(str2 + "?osversion=" + i + "&devicetype=android&src=cmepulseapp-android&appversion=" + str, new Object[]{mConfigEnvironment});
        }

        public static String getConfigEnvironment() {
            return mConfigEnvironment;
        }

        public static void setmArticleEnvironment(String str) {
            if (!Extensions.isStringNullOrEmpty(str) && !str.startsWith(".")) {
                str = "." + str.toLowerCase();
            }
            mArticleEnvironment = str;
        }

        public static String getArticleEnvironment() {
            return mArticleEnvironment;
        }

        public static String getForgotPasswordUrl() {
            return String.format("https://api%s.medscape.com/servicegateway/v1/regservice/forgotpassword", new Object[]{mConfigEnvironment});
        }

        public static String getDomain(Context context) {
            return Utilities.generateEnvironment(context, "www%s.medscape.com/");
        }

        public static String getProfRegData(Context context) {
            return Utilities.generateEnvironment(context, "https://api%s.medscape.com/servicegateway/v1/regservice/getProfRefData");
        }

        public static String getRegistrationUrl(Context context) {
            return Utilities.generateEnvironment(context, "https://api%s.medscape.com/servicegateway/v1/regservice/register");
        }

        public static String getValidateEmailUrl(Context context) {
            return Utilities.generateEnvironment(context, "https://profreg%s.medscape.com/px/validateEmail.do?emailAddress=") + "%s";
        }

        public static String getCustomTopicstUrl(Context context) {
            return Utilities.generateEnvironment(context, "https://api%s.medscape.com/") + "contentsearchservice/profeducation/search?page=1&size=100&out=multimedia,title,maxCredits,creditType,clientUrl,description,pubDisplay,publicationDate,activityExpirationDate&client=mscpsrch";
        }

        public static String getContentSearchUrl(Context context) {
            return Utilities.generateEnvironment(context, "https://api%s.medscape.com/") + "contentsearchservice/profeducation/search?q=%s&page=%s&size=%s&out=multimedia,title,maxCredits,creditType,clientUrl,description,pubDisplay,publicationDate,activityExpirationDate&client=mscpsrch&guid=%s&facets=%s";
        }

        public static String getCMETrackerUrl(Context context) {
            return Utilities.generateEnvironment(context, "https://www%s.medscape.org/activitytracker/json");
        }

        public static String getArticleEvaluationUrl(Context context, String str) {
            return Utilities.generateEnvironment(context, "https://www%s.medscape.org/qna/form-evaluation/") + str;
        }

        public static String getStandAloneEvaluationUrl(Context context, String str) {
            return Utilities.generateEnvironment(context, "https://www%s.medscape.org/qna/processor/") + str + "?showStandAlone=true";
        }

        public static String getFeedUrl(Context context) {
            String str;
            int i = Build.VERSION.SDK_INT;
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                str = "1.0";
            }
            if (mFeedEnvironment.contains("dev")) {
                return String.format("http://www%s.medscape.com/noscan/mobileapp/public/native-ipad2/cmepulse.json?appversion=" + str + "&osversion=" + i + "&src=cmepulseapp-android&devicetype=android", new Object[]{".staging"});
            }
            return String.format("https://www%s.medscape.com/noscan/mobileapp/public/native-ipad/cmepulse.json?appversion=" + str + "&osversion=" + i + "&src=cmepulseapp-android&devicetype=android", new Object[]{mFeedEnvironment});
        }

        public static String getArticleUrl(String str, String str2, Context context) {
            String str3;
            int i = Build.VERSION.SDK_INT;
            try {
                str3 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                str3 = "1.0";
            }
            if (mFeedEnvironment.contains("dev")) {
                return String.format("https://api%s.medscape.com/contentservice/render/cme/article/%s?devicetype=android&src=cmepulseapp-iphone&output_version=appxml&token=%s&osversion=%d&fl=en_us&appversion=%s", new Object[]{".staging", str, str2, Integer.valueOf(i), str3});
            }
            return String.format("https://api%s.medscape.com/contentservice/render/cme/article/%s?devicetype=android&src=cmepulseapp-iphone&output_version=appxml&token=%s&osversion=%d&fl=en_us&appversion=%s", new Object[]{mFeedEnvironment, str, str2, Integer.valueOf(i), str3});
        }
    }
}
