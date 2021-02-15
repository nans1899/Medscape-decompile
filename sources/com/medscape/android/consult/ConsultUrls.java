package com.medscape.android.consult;

import android.content.Context;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import com.webmd.wbmdproffesionalauthentication.utilities.EnvironmentUtil;

public class ConsultUrls {
    private static String getCommunityRootUrl(Context context) {
        String urlScheme = getUrlScheme(context);
        String urlPrefix = getUrlPrefix(context);
        return urlScheme + "://community" + urlPrefix + ".medscape.com/api/Community/";
    }

    private static String getUrlScheme(Context context) {
        return new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE).equals(EnvironmentConstants.PRODUCTION) ? RequestBuilders.DEFAULT_SCHEME : "http";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getUrlPrefix(android.content.Context r6) {
        /*
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            java.lang.String r1 = "module_service"
            java.lang.String r6 = r0.getEnvironmentWithDefault(r6, r1)
            int r0 = r6.hashCode()
            r1 = 446124970(0x1a9753aa, float:6.25873E-23)
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r0 == r1) goto L_0x0049
            r1 = 568937877(0x21e94d95, float:1.580923E-18)
            if (r0 == r1) goto L_0x003f
            switch(r0) {
                case 568961724: goto L_0x0035;
                case 568961725: goto L_0x002b;
                case 568961726: goto L_0x0021;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0053
        L_0x0021:
            java.lang.String r0 = "environment_qa02"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 2
            goto L_0x0054
        L_0x002b:
            java.lang.String r0 = "environment_qa01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 1
            goto L_0x0054
        L_0x0035:
            java.lang.String r0 = "environment_qa00"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 0
            goto L_0x0054
        L_0x003f:
            java.lang.String r0 = "environment_perf"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 4
            goto L_0x0054
        L_0x0049:
            java.lang.String r0 = "environment_dev01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 3
            goto L_0x0054
        L_0x0053:
            r6 = -1
        L_0x0054:
            if (r6 == 0) goto L_0x006d
            if (r6 == r5) goto L_0x006a
            if (r6 == r4) goto L_0x0067
            if (r6 == r3) goto L_0x0064
            if (r6 == r2) goto L_0x0061
            java.lang.String r6 = ""
            goto L_0x006f
        L_0x0061:
            java.lang.String r6 = ".perf"
            goto L_0x006f
        L_0x0064:
            java.lang.String r6 = ".dev01"
            goto L_0x006f
        L_0x0067:
            java.lang.String r6 = ".qa02"
            goto L_0x006f
        L_0x006a:
            java.lang.String r6 = ".qa01"
            goto L_0x006f
        L_0x006d:
            java.lang.String r6 = ".qa00"
        L_0x006f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.ConsultUrls.getUrlPrefix(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getConsultConfigRootUrl(android.content.Context r6) {
        /*
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            java.lang.String r1 = "module_feed"
            java.lang.String r6 = r0.getEnvironmentWithDefault(r6, r1)
            int r0 = r6.hashCode()
            r1 = 68759055(0x4192e0f, float:1.8006213E-36)
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r0 == r1) goto L_0x0049
            r1 = 446124970(0x1a9753aa, float:6.25873E-23)
            if (r0 == r1) goto L_0x003f
            switch(r0) {
                case 568961724: goto L_0x0035;
                case 568961725: goto L_0x002b;
                case 568961726: goto L_0x0021;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0053
        L_0x0021:
            java.lang.String r0 = "environment_qa02"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 2
            goto L_0x0054
        L_0x002b:
            java.lang.String r0 = "environment_qa01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 1
            goto L_0x0054
        L_0x0035:
            java.lang.String r0 = "environment_qa00"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 3
            goto L_0x0054
        L_0x003f:
            java.lang.String r0 = "environment_dev01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 4
            goto L_0x0054
        L_0x0049:
            java.lang.String r0 = "environment_staging"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0053
            r6 = 0
            goto L_0x0054
        L_0x0053:
            r6 = -1
        L_0x0054:
            if (r6 == 0) goto L_0x006d
            if (r6 == r5) goto L_0x006a
            if (r6 == r4) goto L_0x0067
            if (r6 == r3) goto L_0x0064
            if (r6 == r2) goto L_0x0061
            java.lang.String r6 = "https://www.medscape.com/noscan/mobileapp/public/native-ipad/config/"
            goto L_0x006f
        L_0x0061:
            java.lang.String r6 = "http://www.dev01.medscape.com/noscan/mobileapp/public/native-ipad/config/"
            goto L_0x006f
        L_0x0064:
            java.lang.String r6 = "http://www.qa00.medscape.com/noscan/mobileapp/public/native-ipad/config/"
            goto L_0x006f
        L_0x0067:
            java.lang.String r6 = "http://www.qa02.medscape.com/noscan/mobileapp/public/native-ipad/config/"
            goto L_0x006f
        L_0x006a:
            java.lang.String r6 = "http://www.qa01.medscape.com/noscan/mobileapp/public/native-ipad/config/"
            goto L_0x006f
        L_0x006d:
            java.lang.String r6 = "http://www.staging.medscape.com/noscan/mobileapp/public/native-ipad/config/"
        L_0x006f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.ConsultUrls.getConsultConfigRootUrl(android.content.Context):java.lang.String");
    }

    public static String getTopFeedsUrl(Context context) {
        return getCommunityRootUrl(context) + "Discussion/GetTopFeed";
    }

    public static String getFollowedPostsUrl(Context context) {
        return getCommunityRootUrl(context) + "discussion/GetSubscribedThreads";
    }

    public static String getMyNetworkFeedUrl(Context context) {
        return String.format("%sDiscussion/GetNetworkFeed", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getMyPostsFeedUrl(Context context) {
        return String.format("%sDiscussion/getpostsbyuser", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getZimbraConfigUrl(Context context) {
        return (getUrlScheme(context) + "://community" + getUrlPrefix(context) + ".medscape.com/api/community/config/") + "MedscapeConsultConfig.json";
    }

    public static String getCurrentProfileUrl(Context context) {
        String communityRootUrl = getCommunityRootUrl(context);
        return communityRootUrl + "profile/MyProfile";
    }

    public static String getProfileUrl(Context context) {
        String communityRootUrl = getCommunityRootUrl(context);
        return communityRootUrl + "profile/GetProfile";
    }

    public static String getJoinGroupUrl(Context context) {
        String communityRootUrl = getCommunityRootUrl(context);
        return communityRootUrl + "Group/JoinGroup";
    }

    public static String getRefreshAuthUrl(Context context) {
        return String.format("%soauth/refresh?src=medscapeapp-android", new Object[]{String.format("%s://api%s.medscape.com/authenticate/", new Object[]{getUrlScheme(context), getUrlPrefix(context)})});
    }

    public static String getUploadProfileImageUrl(Context context) {
        return String.format("%sprofile/UpdateProfileImage", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUploadProfileUrl(Context context) {
        return String.format("%sprofile/Update", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getPostsByUserUrl(Context context) {
        return String.format("%sDiscussion/getpostsbyuser", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUpVoteUrl(Context context) {
        return String.format("%sRating/UpVote", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getDeleteUpVoteUrl(Context context) {
        return String.format("%sRating/DeleteUpVote", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getDownVoteUrl(Context context) {
        return String.format("%s/Rating/DownVote", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getDeleteDownVoteUrl(Context context) {
        return String.format("%sRating/DeleteDownVote", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getReportAbuseUrl(Context context) {
        return String.format("%sabuseReport/Create", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getDeleteThreadUrl(Context context) {
        return String.format("%sDiscussion/DeleteForumThread", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getPostThreadUrl(Context context) {
        return String.format("%sDiscussion/CreateForumThread", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUpdateThreadUrl(Context context) {
        return String.format("%sDiscussion/UpdateForumThread", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUploadImageUrl(Context context) {
        return String.format("%sMedia/UploadImage", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getSubscribeUrl(Context context) {
        return String.format("%sDiscussion/Subscribe", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getFollowUrl(Context context) {
        return String.format("%sFollowing/Follow", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUnFollowUrl(Context context) {
        return String.format("%sFollowing/UnFollow", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getMentionablesUrl(Context context) {
        return String.format("%smentionables/query", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getMentionablesByIdUrl(Context context) {
        return String.format("%smentionables/UserQuery", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getFollowingUrl(Context context) {
        return String.format("%sFollowing/ListFollowings", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getFollowersUrl(Context context) {
        return String.format("%sFollowing/ListFollowers", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getSearchContentUrl(Context context) {
        return String.format("%sSearch/Content", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getSearchUserUrl(Context context) {
        return String.format("%sSearch/User", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getResponsesUrl(Context context) {
        return String.format("%sDiscussion/GetPostRepliesByUser", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getRepliesUrl(Context context) {
        return String.format("%sDiscussion/GetReplies", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getPostUrl(Context context) {
        return String.format("%sDiscussion/GetThread", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getPostReplyUrl(Context context) {
        return String.format("%sDiscussion/CreateForumReply", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getTagsListUrl(Context context) {
        return String.format("%sTagList.json", new Object[]{getConsultConfigRootUrl(context)});
    }

    public static String getAdditionalConfigUrl(Context context) {
        return String.format("%sAdditionalConsultConfigv2.json", new Object[]{getConsultConfigRootUrl(context)});
    }

    public static String getNotificationPreferencesUrl(Context context) {
        return String.format("%sPreferences/GetNotificationPreferences", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUpdateNotificationPreferencesUrl(Context context) {
        return String.format("%sPreferences/UpdateNotificationPreferences", new Object[]{getCommunityRootUrl(context)});
    }

    public static String getUpdateUserAttributesUrl(Context context) {
        return String.format("https://api%s.medscape.com/regservice/registration/pub/update/userAttributes/", new Object[]{context != null ? EnvironmentUtil.Companion.getUrlPrefixForServiceEnv(context) : ""});
    }
}
