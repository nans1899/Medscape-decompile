package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.text.Html;
import com.facebook.appevents.AppEventsConstants;
import com.qxmd.eventssdkandroid.managers.QXEEventsManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAd;
import com.wbmd.qxcalculator.model.db.DBLocation;
import com.wbmd.qxcalculator.model.db.DBProfession;
import com.wbmd.qxcalculator.model.db.DBPromotion;
import com.wbmd.qxcalculator.model.db.DBSpecialty;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.parsedObjects.Promotion;
import com.wbmd.qxcalculator.util.legacy.ContentParser;

public class EventsManager {
    private static EventsManager ourInstance;

    public static EventsManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new EventsManager();
        }
        return ourInstance;
    }

    private EventsManager() {
    }

    public static void initializeApp(Context context) {
        QXEEventsManager.initializeApp(context);
    }

    public void setProfessionId(Long l) {
        QXEEventsManager.getInstance().setProfessionId(l);
    }

    public void setSpecialtyId(Long l) {
        QXEEventsManager.getInstance().setSpecialtyId(l);
    }

    public void setLocationId(Long l) {
        QXEEventsManager.getInstance().setLocationId(l);
    }

    public void setUserEmail(String str) {
        QXEEventsManager.getInstance().setUserEmailAddress(str);
    }

    public void updateUserId(Long l) {
        QXEEventsManager.getInstance().setUserId(l);
    }

    public void startSessionWithQxEvents() {
        String str;
        Long l;
        Long l2;
        Long l3;
        String str2;
        Long l4;
        Long l5;
        Long l6;
        Long l7;
        if (UserManager.getInstance().getActiveUserAccountType() == UserManager.AccountType.REGULAR) {
            DBUser dbUser = UserManager.getInstance().getDbUser();
            long j = null;
            if (dbUser != null) {
                DBProfession profession = dbUser.getProfession();
                DBSpecialty specialty = dbUser.getSpecialty();
                DBLocation location = dbUser.getLocation();
                if (profession == null) {
                    l5 = null;
                } else {
                    l5 = profession.getIdentifier();
                }
                if (specialty == null) {
                    l6 = null;
                } else {
                    l6 = specialty.getIdentifier();
                }
                if (location == null) {
                    l7 = null;
                } else {
                    l7 = location.getIdentifier();
                }
                String userEmail = UserManager.getInstance().getUserEmail();
                if (userEmail == null || !userEmail.contains("@qxmd.com")) {
                    QXEEventsManager.getInstance().setDebugMode(false);
                } else {
                    QXEEventsManager.getInstance().setDebugMode(true);
                }
                if (!UserManager.getInstance().needsUpgradeToUniversalAccounts()) {
                    String identifier = dbUser.getIdentifier();
                    try {
                        j = Long.valueOf(Long.parseLong(dbUser.getIdentifier()));
                    } catch (NumberFormatException unused) {
                    }
                    l3 = l5;
                    l2 = l6;
                    l = l7;
                    str = userEmail;
                    str2 = identifier;
                } else if (dbUser.getIdentifierForLegacyUser() != null) {
                    str2 = Long.toString(dbUser.getIdentifierForLegacyUser().longValue());
                    l3 = l5;
                    l2 = l6;
                    l = l7;
                    str = userEmail;
                    l4 = dbUser.getIdentifierForLegacyUser();
                } else {
                    j = 0L;
                    str2 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                    l3 = l5;
                    l2 = l6;
                    l = l7;
                    str = userEmail;
                }
                l4 = j;
            } else {
                l4 = null;
                str2 = null;
                l3 = null;
                l2 = null;
                l = null;
                str = null;
            }
            QXEEventsManager.getInstance().startCalculateSession(str2, l3, l2, l, str);
            QXEEventsManager.getInstance().setAppCalculate();
            QXEEventsManager.getInstance().trackEvent(1000L, "accounts", "start", (String) null, l4, (Long) null, (Long) null);
        }
    }

    public void stopSession() {
        QXEEventsManager.getInstance().stopCalculateSession();
        DBUser dbUser = UserManager.getInstance().getDbUser();
        long j = null;
        if (dbUser != null) {
            if (UserManager.getInstance().needsUpgradeToUniversalAccounts()) {
                j = dbUser.getIdentifierForLegacyUser() != null ? dbUser.getIdentifierForLegacyUser() : 0L;
            } else {
                try {
                    j = Long.valueOf(Long.parseLong(dbUser.getIdentifier()));
                } catch (NumberFormatException unused) {
                }
            }
        }
        QXEEventsManager.getInstance().trackEvent(1000L, "accounts", "stop", (String) null, j, (Long) null, (Long) null);
    }

    public void trackFeaturedContentView(DBFeaturedContentAd dBFeaturedContentAd, DBPromotion dBPromotion) {
        Long l;
        Long l2 = null;
        try {
            l = Long.valueOf(Long.parseLong(dBFeaturedContentAd.getIdentifier()));
        } catch (Exception unused) {
            l = null;
        }
        QXEEventsManager instance = QXEEventsManager.getInstance();
        if (dBPromotion != null) {
            l2 = dBPromotion.getCampaignAdId();
        }
        instance.trackEvent(1500L, "impression", "calculator_list", (String) null, l, l2, (Long) null);
    }

    public void trackFeaturedContentClick(DBFeaturedContentAd dBFeaturedContentAd, DBPromotion dBPromotion) {
        Long l;
        Long l2 = null;
        try {
            l = Long.valueOf(Long.parseLong(dBFeaturedContentAd.getIdentifier()));
        } catch (Exception unused) {
            l = null;
        }
        QXEEventsManager instance = QXEEventsManager.getInstance();
        if (dBPromotion != null) {
            l2 = dBPromotion.getCampaignAdId();
        }
        instance.trackEvent(1500L, "click", "calculator_list", (String) null, l, l2, (Long) null);
    }

    public void trackContentItemUsed(String str, Promotion promotion) {
        Promotion promotion2 = promotion;
        QXEEventsManager.getInstance().trackEvent(eventTypeIdForContentItemUse(str), (String) null, (String) null, (String) null, objectIdForContentItem(str), (Long) null, (Long) null);
        QXEEventsManager.getInstance().trackEvent(1200L, contentItemTypeCategory(str), (String) null, (String) null, objectIdForContentItem(str), promotion2 == null ? null : promotion2.campaignAdId, (Long) null);
    }

    public void trackContentItemFinishedUse(ContentItem contentItem, Promotion promotion, long j) {
        QXEEventsManager.getInstance().trackEvent(1201L, contentItemTypeCategory(contentItem.identifier), (String) null, (String) null, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, Long.valueOf(j));
    }

    public void trackExtraSectionSectionExpanded(ContentItem contentItem, ExtraSectionItem extraSectionItem, Promotion promotion) {
        QXEEventsManager.getInstance().trackEvent(1300L, "result_section_expanded", Html.fromHtml(extraSectionItem.title).toString(), (String) null, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, (Long) null);
    }

    public void trackExtraSectionConversion(ContentItem contentItem, ExtraSectionItem extraSectionItem, Promotion promotion) {
        QXEEventsManager.getInstance().trackEvent(1300L, "result_section_click", Html.fromHtml(extraSectionItem.title).toString(), (String) null, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, (Long) null);
    }

    public void trackMoreInfoPageView(ContentItem contentItem, Promotion promotion) {
        QXEEventsManager.getInstance().trackEvent(1100L, contentItemTypeCategory(contentItem.identifier), "more_info", (String) null, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, (Long) null);
    }

    public void trackLinkClicked(ContentItem contentItem, String str, Promotion promotion) {
        QXEEventsManager.getInstance().trackEvent(1400L, contentItemTypeCategory(contentItem.identifier), (String) null, str, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, (Long) null);
    }

    public void trackReferenceBookSectionViewed(ContentItem contentItem, ReferenceBookSectionItem referenceBookSectionItem, Promotion promotion) {
        QXEEventsManager.getInstance().trackEvent(1100L, "reference_book_page", Html.fromHtml(referenceBookSectionItem.title).toString(), (String) null, objectIdForContentItem(contentItem.identifier), promotion == null ? null : promotion.campaignAdId, (Long) null);
    }

    public void trackSplashpageView(String str, Long l) {
        QXEEventsManager.getInstance().trackEvent(1100L, "splash_page", (String) null, (String) null, objectIdForContentItem(str), l, (Long) null);
    }

    private Long objectIdForContentItem(String str) {
        String str2;
        Long l;
        if (str.contains("calculator_")) {
            str2 = str.substring(11);
        } else if (str.contains("file_source_")) {
            str2 = str.substring(12);
        } else if (str.contains("definition_")) {
            str2 = str.substring(11);
        } else if (str.contains("reference_book_")) {
            str2 = str.substring(15);
        } else {
            str2 = str.contains("splash_page_") ? str.substring(12) : null;
        }
        if (str2 != null) {
            try {
                l = Long.valueOf(Long.parseLong(str2));
            } catch (Exception unused) {
                l = null;
            }
            if (l != null) {
                return l;
            }
        }
        return null;
    }

    private Long eventTypeIdForContentItemUse(String str) {
        if (str.contains("calculator_")) {
            return 101L;
        }
        if (str.contains("file_source_")) {
            return 103L;
        }
        if (str.contains("definition_")) {
            return 102L;
        }
        if (str.contains("reference_book_")) {
            return 104L;
        }
        return 0L;
    }

    private String contentItemTypeCategory(String str) {
        if (str.contains("calculator_")) {
            return ContentParser.CALCULATOR;
        }
        if (str.contains("file_source_")) {
            return "file_source";
        }
        if (str.contains("definition_")) {
            return "definition";
        }
        if (str.contains("reference_book_")) {
            return "reference_book";
        }
        return null;
    }
}
