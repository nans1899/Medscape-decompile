package com.webmd.wbmdcmepulse.exceptions;

public class IncompatibleArticleException extends Exception {
    public static final String ADVANCED_PLAYER_MSG = "Title equals advanced player.";
    public static final int ADVANCE_PLAYER = 4;
    public static final int ARTICLE_PARSING_ERROR = 7;
    public static final String ARTICLE_PARSING_ERROR_MSG = "Article Parser Failed";
    public static final String CLASS_NAME = IncompatibleArticleException.class.getSimpleName();
    public static final int CME_TV = 3;
    public static final String CME_TV_MSG = "HTML input tag with name cme-tv found.";
    public static final int MULTIPLE_EMBEDDED_VIDEOS = 6;
    public static final String MULTIPLE_EMBEDDED_VIDEOS_MSG = "Article contains multiple embedded videos";
    public static final int PRG_TEASER = 1;
    public static final String PRG_TEASER_MSG = "HTML div tag with id prgteaser found.";
    public static final int SCRIPT_TAG_IN_BODY = 0;
    public static final String SCRIPT_TAG_IN_BODY_MSG = "Script Tag found in xml body.";
    public static final int SIM_LANDING = 2;
    public static final String SIM_LANDING_MSG = "HTML Div tag with simLanding found.";
    public static final int SINGLE_PAGE_NO_CONTENT = 5;
    public static final String SINGLE_PAGE_NO_CONTENT_MSG = "Article has only one page and the page had no content.";

    public static String getErrorMessage(int i) {
        switch (i) {
            case 0:
                return SCRIPT_TAG_IN_BODY_MSG;
            case 1:
                return PRG_TEASER_MSG;
            case 2:
                return SIM_LANDING_MSG;
            case 3:
                return CME_TV_MSG;
            case 4:
                return ADVANCED_PLAYER_MSG;
            case 5:
                return SINGLE_PAGE_NO_CONTENT_MSG;
            case 6:
                return MULTIPLE_EMBEDDED_VIDEOS_MSG;
            case 7:
                return ARTICLE_PARSING_ERROR_MSG;
            default:
                return "";
        }
    }

    public IncompatibleArticleException(String str) {
        super("Incompatible Article. " + str);
    }

    public IncompatibleArticleException(String str, Throwable th) {
        super(str, th);
    }
}
