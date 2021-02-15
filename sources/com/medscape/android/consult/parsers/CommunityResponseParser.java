package com.medscape.android.consult.parsers;

import android.text.Html;
import android.webkit.URLUtil;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.Constants;
import com.medscape.android.consult.models.CMEInfo;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.util.ConsultTimelineHtmlConverter;
import com.medscape.android.util.StringUtil;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommunityResponseParser {
    static String TAG = CommunityResponseParser.class.getSimpleName();

    public static ConsultPost parseCommunityResponse(JSONObject jSONObject, String str) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        ConsultPost consultPost = new ConsultPost();
        consultPost.setPostId(jSONObject.optString("Id"));
        String optString = jSONObject.optString(Constants.ZIMBRA_FORUM_ID);
        if (StringUtil.isNotEmpty(optString) && StringUtil.isNotEmpty(str) && optString.equalsIgnoreCase(str)) {
            consultPost.setIsHidden(true);
        }
        consultPost.setContentId(jSONObject.optString("ContentId"));
        consultPost.setContentTypeId(jSONObject.optString("ContentTypeId"));
        consultPost.setPoster(CommunityUserParser.parseCommunityUserResponseFromJson(jSONObject.optJSONObject("Author")));
        consultPost.setReviewer(CommunityUserParser.parseCommunityUserResponseFromJson(jSONObject.optJSONObject("Reviewer")));
        setPostBody(jSONObject, consultPost);
        setPostSubject(jSONObject, consultPost);
        JSONObject optJSONObject = jSONObject.optJSONObject("CME");
        if (optJSONObject != null) {
            String optString2 = optJSONObject.optString("Id");
            double optDouble = optJSONObject.optDouble("Credit");
            if (StringUtil.isNotEmpty(optString2) && optDouble > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                CMEInfo cMEInfo = new CMEInfo();
                cMEInfo.setCMEArticleId(optString2);
                cMEInfo.setCMECreditCount(optDouble);
                consultPost.setCMEInfo(cMEInfo);
            }
        }
        consultPost.setUpVoteCount(jSONObject.optInt("UpVoteCount"));
        consultPost.setCommentCount(jSONObject.optInt("ReplyCount"));
        if (jSONObject.optBoolean("UpVoted")) {
            consultPost.setIsUpVoted(true);
        }
        consultPost.setDownVoteCount(jSONObject.optInt("DownVoteCount"));
        if (jSONObject.optBoolean("DownVoted")) {
            consultPost.setIsDownVoted(true);
        }
        if (jSONObject.optBoolean("IsLowQuality")) {
            consultPost.setIsLowQualityPost(true);
        }
        List<ConsultAsset> imageAssetsFromResponse = getImageAssetsFromResponse(jSONObject);
        if (imageAssetsFromResponse != null && imageAssetsFromResponse.size() > 0) {
            consultPost.setConsultAssets(imageAssetsFromResponse);
        }
        List<ConsultFeedItem> consultCommentsFromResponse = getConsultCommentsFromResponse(jSONObject, str, Constants.CONSULT_COMMENT_TYPE_POST);
        if (consultCommentsFromResponse != null && consultCommentsFromResponse.size() > 0) {
            consultPost.setApprovedAnswers(consultCommentsFromResponse);
        }
        List<String> tagsFromResponse = getTagsFromResponse(jSONObject);
        if (tagsFromResponse != null && tagsFromResponse.size() > 0) {
            consultPost.setTags(tagsFromResponse);
        }
        consultPost.setTimestamp(dateFromZimbraTimestamp(jSONObject.optString(HttpHeaders.DATE)));
        if (jSONObject.optBoolean("SubscribeToThread")) {
            consultPost.setFollowState(Constants.FOLLOWING_STATE_FOLLOWING);
        } else {
            consultPost.setFollowState(Constants.FOLLOWING_STATE_NOT_FOLLOWING);
        }
        consultPost.setReplies(getConsultCommentsForJsonArray(jSONObject.optJSONArray("Replies"), str, Constants.CONSULT_COMMENT_TYPE_REPLY, false));
        consultPost.setCaseResolved(jSONObject.optBoolean("CaseIsResolved"));
        consultPost.setBodyUpdates(BodyUpdatesParser.INSTANCE.parseConsultBodyUpdate(jSONObject.optJSONArray("BodyUpdates")));
        return consultPost;
    }

    private static void setPostSubject(JSONObject jSONObject, ConsultPost consultPost) {
        String optString = jSONObject.optString("Title");
        if (StringUtil.isNotEmpty(optString)) {
            try {
                consultPost.setSubject(Html.fromHtml(new ConsultTimelineHtmlConverter(optString).removeTextCopyHtmlMarkup(optString)).toString());
            } catch (Exception unused) {
                consultPost.setSubject(Html.fromHtml(optString).toString());
            }
        }
    }

    private static void setPostBody(JSONObject jSONObject, ConsultPost consultPost) {
        String optString = jSONObject.optString("Body");
        if (StringUtil.isNotEmpty(optString)) {
            try {
                consultPost.setPostBody(getResultWithMentions(new ConsultTimelineHtmlConverter(optString).removeTextCopyHtmlMarkup(optString)));
            } catch (Exception unused) {
                consultPost.setPostBody(Html.fromHtml(optString).toString());
            }
        }
    }

    private static String getResultWithMentions(String str) {
        int i;
        String str2;
        if (StringUtil.isNotEmpty(str) && str.contains("a href") && str.contains("members/")) {
            int indexOf = str.indexOf("a href");
            int i2 = 0;
            while (true) {
                int i3 = -1;
                if (indexOf <= -1) {
                    break;
                }
                int i4 = indexOf + 8 + i2;
                if (str.length() > i4) {
                    int indexOf2 = str.substring(i4).indexOf("\"");
                    if (indexOf2 > -1 && str.length() > (i = indexOf2 + i4)) {
                        String substring = str.substring(i4, i);
                        if (!StringUtil.isNotEmpty(substring) || !substring.contains("members/") || !str.substring(i).contains("internal-link view-user-profile")) {
                            str2 = str;
                        } else {
                            i = str.substring(i).indexOf("internal-link view-user-profile") + 31 + indexOf2 + i4 + 2;
                            str2 = str.substring(0, i) + "@" + str.substring(i, str.length());
                            i2++;
                        }
                        int indexOf3 = i + str.substring(i).indexOf("</a>");
                        int indexOf4 = str.substring(indexOf3).indexOf("a href");
                        if (indexOf4 > -1) {
                            indexOf4 += indexOf3;
                        }
                        i3 = indexOf4;
                        str = str2;
                    }
                    indexOf = i3;
                } else {
                    indexOf = -1;
                }
            }
        }
        return str;
    }

    private static Calendar dateFromZimbraTimestamp(String str) {
        try {
            if (!StringUtil.isNotEmpty(str)) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parse = simpleDateFormat.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            return instance;
        } catch (Exception unused) {
            return null;
        }
    }

    private static List<String> getTagsFromResponse(JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("Tags");
            if (StringUtil.isNotEmpty(optString)) {
                String[] split = optString.split(",");
                ArrayList arrayList = new ArrayList();
                for (String str : split) {
                    if (StringUtil.isNotEmpty(str) && !str.equalsIgnoreCase("Featured")) {
                        try {
                            arrayList.add(URLDecoder.decode(str, "UTF-8"));
                        } catch (Exception unused) {
                        }
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    private static List<ConsultAsset> getImageAssetsFromResponse(JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (jSONObject == null || !jSONObject.has("ImageUrls") || (optJSONArray = jSONObject.optJSONArray("ImageUrls")) == null || optJSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                String string = optJSONArray.getString(i);
                if (URLUtil.isValidUrl(string)) {
                    ConsultAsset consultAsset = new ConsultAsset();
                    consultAsset.setAssetUrl(string);
                    arrayList.add(consultAsset);
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private static List<ConsultFeedItem> getConsultCommentsFromResponse(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null || !jSONObject.has("Answers")) {
            return null;
        }
        return getConsultCommentsForJsonArray(jSONObject.optJSONArray("Answers"), str, i, false);
    }

    public static List<ConsultFeedItem> getConsultCommentsForJsonArray(JSONArray jSONArray, String str, int i, boolean z) {
        ConsultComment consultCommentFromCommunityJson;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    Object obj = jSONArray.get(i2);
                    if (!(obj == null || !(obj instanceof JSONObject) || (consultCommentFromCommunityJson = getConsultCommentFromCommunityJson((JSONObject) obj, str)) == null)) {
                        consultCommentFromCommunityJson.setCommentType(i);
                        if (!z || !consultCommentFromCommunityJson.isMedscapeApprovedAnswer() || i != 3030) {
                            arrayList.add(consultCommentFromCommunityJson);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (arrayList2.size() > 0) {
            arrayList.addAll(0, arrayList2);
        }
        return arrayList;
    }

    public static ConsultComment getConsultCommentFromCommunityJson(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        List<ConsultFeedItem> list = null;
        if (jSONObject == null) {
            return null;
        }
        ConsultComment consultComment = new ConsultComment();
        consultComment.setCommentId(jSONObject.optString("Id"));
        consultComment.setContentId(jSONObject.optString("ContentId"));
        consultComment.setContentTypeId(jSONObject.optString("ContentTypeId"));
        consultComment.setParentThreadId(jSONObject.optString("ThreadId"));
        consultComment.setParentCommentId(jSONObject.isNull("ParentReplyId") ? "" : jSONObject.optString("ParentReplyId"));
        String optString = jSONObject.optString(Constants.ZIMBRA_FORUM_ID);
        if (StringUtil.isNotEmpty(optString) && StringUtil.isNotEmpty(str) && optString.equalsIgnoreCase(str)) {
            consultComment.setIsCommentOnHiddenPost(true);
        }
        consultComment.setPoster(CommunityUserParser.parseCommunityUserResponseFromJson(jSONObject.optJSONObject("Author")));
        consultComment.setReviewer(CommunityUserParser.parseCommunityUserResponseFromJson(jSONObject.optJSONObject("Reviewer")));
        String optString2 = jSONObject.optString("Body");
        if (StringUtil.isNotEmpty(optString2)) {
            try {
                consultComment.setCommentBody(getResultWithMentions(new ConsultTimelineHtmlConverter(optString2).removeTextCopyHtmlMarkup(optString2)));
            } catch (Exception unused) {
                consultComment.setCommentBody(Html.fromHtml(optString2).toString());
            }
        }
        consultComment.setUpVoteCount(jSONObject.optInt("UpVoteCount"));
        consultComment.setReplyCount(jSONObject.optInt("ReplyCount"));
        consultComment.setTimestamp(dateFromZimbraTimestamp(jSONObject.optString(HttpHeaders.DATE)));
        consultComment.setIsMedscapeApprovedAnswer(jSONObject.optBoolean("IsAnswer"));
        if (jSONObject.optBoolean("UpVoted")) {
            consultComment.setIsUpVoted(true);
        }
        consultComment.setDownVoteCount(jSONObject.optInt("DownVoteCount"));
        if (jSONObject.optBoolean("DownVoted")) {
            consultComment.setIsDownVoted(true);
        }
        if (jSONObject.optBoolean("IsLowQuality")) {
            consultComment.setIsLowQualityPost(true);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("Replies");
        if (!(optJSONObject == null || (optJSONArray = optJSONObject.optJSONArray("Items")) == null)) {
            list = getConsultCommentsForJsonArray(optJSONArray, str, Constants.CONSULT_COMMENT_TYPE_REPLY, false);
        }
        if (list == null || list.size() <= 0) {
            ConsultFeed consultFeed = new ConsultFeed();
            consultFeed.setTotalItems(consultComment.getReplyCount());
            consultFeed.setCurrentPageIndex(-1);
            consultComment.setRepliesFeed(consultFeed);
        } else {
            ConsultFeed consultFeed2 = new ConsultFeed();
            consultFeed2.addItemsToEnd(list);
            consultFeed2.setTotalItems(optJSONObject.optInt("TotalCount"));
            consultFeed2.setCurrentPageIndex(-1);
            consultComment.setRepliesFeed(consultFeed2);
        }
        return consultComment;
    }
}
