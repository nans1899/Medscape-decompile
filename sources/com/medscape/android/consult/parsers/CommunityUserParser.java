package com.medscape.android.consult.parsers;

import android.text.Html;
import com.medscape.android.Constants;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultTimelineHtmlConverter;
import com.medscape.android.util.StringUtil;
import org.json.JSONObject;

public class CommunityUserParser {
    static String TAG = CommunityUserParser.class.getSimpleName();

    public static ConsultUser parseCommunityUserResponse(String str) {
        if (str != null && !str.trim().equalsIgnoreCase("")) {
            try {
                return parseCommunityUserResponseFromJson(new JSONObject(str).optJSONObject("data"));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static ConsultUser parseCommunityUserResponseFromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ConsultUser consultUser = new ConsultUser();
        consultUser.setDisplayName(jSONObject.optString(Constants.CONSULT_USER_DISPLAY_NAME));
        consultUser.setUserId(jSONObject.optString(Constants.CONSULT_USER_USERNAME));
        ConsultAsset consultAsset = new ConsultAsset();
        consultAsset.setAssetUrl(jSONObject.optString(Constants.CONSULT_USER_AVATAR_URL));
        consultUser.setProfileImageAsset(consultAsset);
        consultUser.setFollowingCount(jSONObject.optInt(Constants.CONSULT_USER_FOLLOWING_COUNT));
        consultUser.setFollowerCount(jSONObject.optInt(Constants.CONSULT_USER_FOLLOWERS_COUNT));
        consultUser.setPostCount(jSONObject.optInt(Constants.CONSULT_USER_POST_COUNT));
        consultUser.setResponsesCount(jSONObject.optInt(Constants.CONSULT_USER_REPLIES_COUNT));
        consultUser.setAllTimeRank(jSONObject.optInt(Constants.CONSULT_USER_ALLTIME_RANK));
        String optString = jSONObject.optString(Constants.CONSULT_USER_BIO);
        if (StringUtil.isNotEmpty(optString)) {
            try {
                consultUser.setBio(Html.fromHtml(new ConsultTimelineHtmlConverter(optString).removeTextCopyHtmlMarkupIncludingParagraph(optString)).toString());
            } catch (Exception unused) {
                consultUser.setBio(Html.fromHtml(optString).toString());
            }
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(Constants.CONSULT_USER_PROPERTIES);
        if (optJSONObject != null) {
            consultUser.setSpecialty(optJSONObject.optString(Constants.CONSULT_USER_SPECIALTY));
            consultUser.setEducation(optJSONObject.optString(Constants.CONSULT_USER_EDUCATION));
            consultUser.setInstitution(optJSONObject.optString(Constants.CONSULT_USER_INSTITUTION));
            ConsultAsset consultAsset2 = new ConsultAsset();
            consultAsset2.setAssetUrl(optJSONObject.optString(Constants.CONSULT_USER_INSTITUTION_LOGO));
            consultUser.setInstitutionLogo(consultAsset2);
            consultUser.setProfessionalTitle(optJSONObject.optString("Title"));
            consultUser.setProfessionalURL(optJSONObject.optString(Constants.CONSULT_USER_PROFESSIONAL_URL));
            consultUser.setProfessionString(optJSONObject.optString(Constants.CONSULT_USER_PROFESSION_STRING));
            consultUser.setProfessionID(optJSONObject.optString(Constants.CONSULT_USER_PROFESSION_ID));
            consultUser.setUserRole(optJSONObject.optString(Constants.CONSULT_USER_ROLE));
            consultUser.setUserType(optJSONObject.optString(Constants.CONSULT_USER_TYPE));
        }
        if (!jSONObject.has(Constants.CONSULT_USER_IS_FOLLOWING)) {
            consultUser.setFollowState(Constants.FOLLOWING_STATE_UNKNOWN);
            return consultUser;
        } else if (jSONObject.optBoolean(Constants.CONSULT_USER_IS_FOLLOWING, false)) {
            consultUser.setFollowState(Constants.FOLLOWING_STATE_FOLLOWING);
            return consultUser;
        } else {
            consultUser.setFollowState(Constants.FOLLOWING_STATE_NOT_FOLLOWING);
            return consultUser;
        }
    }
}
