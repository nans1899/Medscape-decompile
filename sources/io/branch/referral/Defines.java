package io.branch.referral;

import com.facebook.appevents.UserDataStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.places.model.PlaceFields;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import io.branch.indexing.ContentDiscoveryManifest;
import net.media.android.bidder.base.models.internal.Logger;

public class Defines {

    public enum Jsonkey {
        IdentityID("identity_id"),
        Identity("identity"),
        DeviceFingerprintID("device_fingerprint_id"),
        SessionID("session_id"),
        LinkClickID("link_click_id"),
        GoogleSearchInstallReferrer("google_search_install_referrer"),
        GooglePlayInstallReferrer("install_referrer_extras"),
        ClickedReferrerTimeStamp("clicked_referrer_ts"),
        InstallBeginTimeStamp("install_begin_ts"),
        FaceBookAppLinkChecked("facebook_app_link_checked"),
        BranchLinkUsed("branch_used"),
        ReferringBranchIdentity("referring_branch_identity"),
        BranchIdentity("branch_identity"),
        BranchKey("branch_key"),
        BranchData("branch_data"),
        Bucket("bucket"),
        DefaultBucket("default"),
        Amount("amount"),
        CalculationType("calculation_type"),
        Location("location"),
        Type("type"),
        CreationSource("creation_source"),
        Prefix("prefix"),
        Expiration("expiration"),
        Event("event"),
        Metadata("metadata"),
        CommerceData("commerce_data"),
        ReferralCode(Branch.REFERRAL_CODE),
        Total("total"),
        Unique("unique"),
        Length(Name.LENGTH),
        Direction("direction"),
        BeginAfterID("begin_after_id"),
        Link("link"),
        ReferringData("referring_data"),
        ReferringLink("referring_link"),
        IsFullAppConv("is_full_app_conversion"),
        Data("data"),
        OS(AdParameterKeys.OS),
        HardwareID("hardware_id"),
        AndroidID("android_id"),
        UnidentifiedDevice("unidentified_device"),
        HardwareIDType("hardware_id_type"),
        HardwareIDTypeVendor("vendor_id"),
        HardwareIDTypeRandom("random"),
        IsHardwareIDReal("is_hardware_id_real"),
        AppVersion("app_version"),
        OSVersion("os_version"),
        Country(UserDataStore.COUNTRY),
        Language("language"),
        IsReferrable("is_referrable"),
        Update("update"),
        OriginalInstallTime("first_install_time"),
        FirstInstallTime("latest_install_time"),
        LastUpdateTime("latest_update_time"),
        PreviousUpdateTime("previous_update_time"),
        URIScheme("uri_scheme"),
        AppLinks(PlaceFields.APP_LINKS),
        AppIdentifier("app_identifier"),
        LinkIdentifier("link_identifier"),
        GoogleAdvertisingID("google_advertising_id"),
        AAID("aaid"),
        LATVal("lat_val"),
        LimitedAdTracking("limit_ad_tracking"),
        limitFacebookTracking("limit_facebook_tracking"),
        Debug("debug"),
        Brand("brand"),
        Model("model"),
        ScreenDpi("screen_dpi"),
        ScreenHeight("screen_height"),
        ScreenWidth("screen_width"),
        WiFi("wifi"),
        LocalIP("local_ip"),
        UserData("user_data"),
        DeveloperIdentity("developer_identity"),
        UserAgent(Logger.USER_AGENT),
        SDK(ServerProtocol.DIALOG_PARAM_SDK_VERSION),
        SdkVersion("sdk_version"),
        UIMode("ui_mode"),
        InstallMetadata("install_metadata"),
        Clicked_Branch_Link("+clicked_branch_link"),
        IsFirstSession("+is_first_session"),
        AndroidDeepLinkPath("$android_deeplink_path"),
        DeepLinkPath(Branch.DEEPLINK_PATH),
        AndroidAppLinkURL("android_app_link_url"),
        AndroidPushNotificationKey("branch"),
        AndroidPushIdentifier("push_identifier"),
        ForceNewBranchSession("branch_force_new_session"),
        CanonicalIdentifier("$canonical_identifier"),
        ContentTitle(Branch.OG_TITLE),
        ContentDesc(Branch.OG_DESC),
        ContentImgUrl(Branch.OG_IMAGE_URL),
        CanonicalUrl("$canonical_url"),
        ContentType("$content_type"),
        PublicallyIndexable("$publicly_indexable"),
        LocallyIndexable("$locally_indexable"),
        ContentKeyWords("$keywords"),
        ContentExpiryTime("$exp_date"),
        Params(NativeProtocol.WEB_DIALOG_PARAMS),
        SharedLink("$shared_link"),
        ShareError("$share_error"),
        External_Intent_URI("external_intent_uri"),
        External_Intent_Extra("external_intent_extra"),
        Last_Round_Trip_Time("lrtt"),
        Branch_Round_Trip_Time("brtt"),
        Branch_Instrumentation("instrumentation"),
        Queue_Wait_Time("qwt"),
        InstantDeepLinkSession("instant_dl_session"),
        BranchViewData("branch_view_data"),
        BranchViewID("id"),
        BranchViewAction("action"),
        BranchViewNumOfUse("number_of_use"),
        BranchViewUrl("url"),
        BranchViewHtml("html"),
        Path("path"),
        ViewList("view_list"),
        ContentActionView("view"),
        ContentPath("content_path"),
        ContentNavPath("content_nav_path"),
        ReferralLink("referral_link"),
        ContentData("content_data"),
        ContentEvents(OmnitureConstants.CHANNEL),
        ContentAnalyticsMode("content_analytics_mode"),
        ContentDiscovery(ContentDiscoveryManifest.CONTENT_DISCOVER_KEY),
        Environment("environment"),
        InstantApp("INSTANT_APP"),
        NativeApp("FULL_APP"),
        TransactionID(FirebaseAnalytics.Param.TRANSACTION_ID),
        Currency(FirebaseAnalytics.Param.CURRENCY),
        Revenue("revenue"),
        Shipping(FirebaseAnalytics.Param.SHIPPING),
        Tax(FirebaseAnalytics.Param.TAX),
        Coupon(FirebaseAnalytics.Param.COUPON),
        Affiliation(FirebaseAnalytics.Param.AFFILIATION),
        Description("description"),
        SearchQuery(Constants.BUNDLE_KEY_SEARCH_QUERY),
        Name("name"),
        CustomData("custom_data"),
        EventData("event_data"),
        ContentItems("content_items"),
        ContentSchema("$content_schema"),
        Price("$price"),
        PriceCurrency("$currency"),
        Quantity("$quantity"),
        SKU("$sku"),
        ProductName("$product_name"),
        ProductBrand("$product_brand"),
        ProductCategory("$product_category"),
        ProductVariant("$product_variant"),
        Rating("$rating"),
        RatingAverage("$rating_average"),
        RatingCount("$rating_count"),
        RatingMax("$rating_max"),
        AddressStreet("$address_street"),
        AddressCity("$address_city"),
        AddressRegion("$address_region"),
        AddressCountry("$address_country"),
        AddressPostalCode("$address_postal_code"),
        Latitude("$latitude"),
        Longitude("$longitude"),
        ImageCaptions("$image_captions"),
        Condition("$condition"),
        CreationTimestamp("$creation_timestamp"),
        TrackingDisabled("tracking_disabled"),
        Instant("instant");
        
        private String key;

        private Jsonkey(String str) {
            this.key = "";
            this.key = str;
        }

        public String getKey() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }

    public enum RequestPath {
        RedeemRewards("v1/redeem"),
        GetURL("v1/url"),
        GetApp("v1/app-link-settings"),
        RegisterInstall("v1/install"),
        RegisterClose("v1/close"),
        RegisterOpen("v1/open"),
        RegisterView("v1/register-view"),
        GetCredits("v1/credits/"),
        GetCreditHistory("v1/credithistory"),
        CompletedAction("v1/event"),
        IdentifyUser("v1/profile"),
        Logout("v1/logout"),
        GetReferralCode("v1/referralcode"),
        ValidateReferralCode("v1/referralcode/"),
        ApplyReferralCode("v1/applycode/"),
        DebugConnect("v1/debug/connect"),
        ContentEvent("v1/content-events"),
        TrackStandardEvent("v2/event/standard"),
        TrackCustomEvent("v2/event/custom");
        
        private String key;

        private RequestPath(String str) {
            this.key = "";
            this.key = str;
        }

        public String getPath() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }

    public enum LinkParam {
        Tags(ContentParser.TAGS),
        Alias("alias"),
        Type("type"),
        Duration("duration"),
        Channel("channel"),
        Feature("feature"),
        Stage("stage"),
        Campaign("campaign"),
        Data("data"),
        URL("url");
        
        private String key;

        private LinkParam(String str) {
            this.key = "";
            this.key = str;
        }

        public String getKey() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }
}
