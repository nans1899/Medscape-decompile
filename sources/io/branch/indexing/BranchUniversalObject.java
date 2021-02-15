package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.BranchShortLinkBuilder;
import io.branch.referral.Defines;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ShareSheetStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchUniversalObject implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BranchUniversalObject createFromParcel(Parcel parcel) {
            return new BranchUniversalObject(parcel);
        }

        public BranchUniversalObject[] newArray(int i) {
            return new BranchUniversalObject[i];
        }
    };
    private String canonicalIdentifier_;
    private String canonicalUrl_;
    private long creationTimeStamp_;
    private String description_;
    private long expirationInMilliSec_;
    private String imageUrl_;
    private CONTENT_INDEX_MODE indexMode_;
    private final ArrayList<String> keywords_;
    private CONTENT_INDEX_MODE localIndexMode_;
    private ContentMetadata metadata_;
    private String title_;

    public enum CONTENT_INDEX_MODE {
        PUBLIC,
        PRIVATE
    }

    public interface RegisterViewStatusListener {
        void onRegisterViewFinished(boolean z, BranchError branchError);
    }

    public int describeContents() {
        return 0;
    }

    public String getCurrencyType() {
        return null;
    }

    public double getPrice() {
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public String getType() {
        return null;
    }

    public BranchUniversalObject setContentType(String str) {
        return this;
    }

    public BranchUniversalObject setPrice(double d, CurrencyType currencyType) {
        return this;
    }

    public BranchUniversalObject() {
        this.metadata_ = new ContentMetadata();
        this.keywords_ = new ArrayList<>();
        this.canonicalIdentifier_ = "";
        this.canonicalUrl_ = "";
        this.title_ = "";
        this.description_ = "";
        this.indexMode_ = CONTENT_INDEX_MODE.PUBLIC;
        this.localIndexMode_ = CONTENT_INDEX_MODE.PUBLIC;
        this.expirationInMilliSec_ = 0;
        this.creationTimeStamp_ = System.currentTimeMillis();
    }

    public BranchUniversalObject setCanonicalIdentifier(String str) {
        this.canonicalIdentifier_ = str;
        return this;
    }

    public BranchUniversalObject setCanonicalUrl(String str) {
        this.canonicalUrl_ = str;
        return this;
    }

    public BranchUniversalObject setTitle(String str) {
        this.title_ = str;
        return this;
    }

    public BranchUniversalObject setContentDescription(String str) {
        this.description_ = str;
        return this;
    }

    public BranchUniversalObject setContentImageUrl(String str) {
        this.imageUrl_ = str;
        return this;
    }

    public BranchUniversalObject addContentMetadata(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            for (String next : hashMap.keySet()) {
                this.metadata_.addCustomMetadata(next, hashMap.get(next));
            }
        }
        return this;
    }

    public BranchUniversalObject addContentMetadata(String str, String str2) {
        this.metadata_.addCustomMetadata(str, str2);
        return this;
    }

    public BranchUniversalObject setContentMetadata(ContentMetadata contentMetadata) {
        this.metadata_ = contentMetadata;
        return this;
    }

    public BranchUniversalObject setContentIndexingMode(CONTENT_INDEX_MODE content_index_mode) {
        this.indexMode_ = content_index_mode;
        return this;
    }

    public BranchUniversalObject setLocalIndexMode(CONTENT_INDEX_MODE content_index_mode) {
        this.localIndexMode_ = content_index_mode;
        return this;
    }

    public BranchUniversalObject addKeyWords(ArrayList<String> arrayList) {
        this.keywords_.addAll(arrayList);
        return this;
    }

    public BranchUniversalObject addKeyWord(String str) {
        this.keywords_.add(str);
        return this;
    }

    public BranchUniversalObject setContentExpiration(Date date) {
        this.expirationInMilliSec_ = date.getTime();
        return this;
    }

    public void listOnGoogleSearch(Context context) {
        AppIndexingHelper.addToAppIndex(context, this, (LinkProperties) null);
    }

    public void listOnGoogleSearch(Context context, LinkProperties linkProperties) {
        AppIndexingHelper.addToAppIndex(context, this, linkProperties);
    }

    public void removeFromLocalIndexing(Context context) {
        AppIndexingHelper.removeFromFirebaseLocalIndex(context, this, (LinkProperties) null);
    }

    public void removeFromLocalIndexing(Context context, LinkProperties linkProperties) {
        AppIndexingHelper.removeFromFirebaseLocalIndex(context, this, linkProperties);
    }

    public void userCompletedAction(String str) {
        userCompletedAction(str, (HashMap<String, String>) null);
    }

    public void userCompletedAction(BRANCH_STANDARD_EVENT branch_standard_event) {
        userCompletedAction(branch_standard_event.getName(), (HashMap<String, String>) null);
    }

    public void userCompletedAction(String str, HashMap<String, String> hashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            new JSONArray().put(this.canonicalIdentifier_);
            jSONObject.put(this.canonicalIdentifier_, convertToJson());
            if (hashMap != null) {
                for (String next : hashMap.keySet()) {
                    jSONObject.put(next, hashMap.get(next));
                }
            }
            if (Branch.getInstance() != null) {
                Branch.getInstance().userCompletedAction(str, jSONObject);
            }
        } catch (JSONException unused) {
        }
    }

    public void userCompletedAction(BRANCH_STANDARD_EVENT branch_standard_event, HashMap<String, String> hashMap) {
        userCompletedAction(branch_standard_event.getName(), hashMap);
    }

    public boolean isPublicallyIndexable() {
        return this.indexMode_ == CONTENT_INDEX_MODE.PUBLIC;
    }

    public boolean isLocallyIndexable() {
        return this.localIndexMode_ == CONTENT_INDEX_MODE.PUBLIC;
    }

    public HashMap<String, String> getMetadata() {
        return this.metadata_.getCustomMetadata();
    }

    public ContentMetadata getContentMetadata() {
        return this.metadata_;
    }

    public long getExpirationTime() {
        return this.expirationInMilliSec_;
    }

    public String getCanonicalIdentifier() {
        return this.canonicalIdentifier_;
    }

    public String getCanonicalUrl() {
        return this.canonicalUrl_;
    }

    public String getDescription() {
        return this.description_;
    }

    public String getImageUrl() {
        return this.imageUrl_;
    }

    public String getTitle() {
        return this.title_;
    }

    public JSONArray getKeywordsJsonArray() {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.keywords_.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords_;
    }

    public void registerView() {
        registerView((RegisterViewStatusListener) null);
    }

    public void registerView(RegisterViewStatusListener registerViewStatusListener) {
        if (Branch.getInstance() != null) {
            Branch.getInstance().registerView(this, registerViewStatusListener);
        } else if (registerViewStatusListener != null) {
            registerViewStatusListener.onRegisterViewFinished(false, new BranchError("Register view error", BranchError.ERR_BRANCH_NOT_INSTANTIATED));
        }
    }

    public String getShortUrl(Context context, LinkProperties linkProperties) {
        return getLinkBuilder(context, linkProperties).getShortUrl();
    }

    public String getShortUrl(Context context, LinkProperties linkProperties, boolean z) {
        return ((BranchShortLinkBuilder) getLinkBuilder(context, linkProperties).setDefaultToLongUrl(z)).getShortUrl();
    }

    public void generateShortUrl(Context context, LinkProperties linkProperties, Branch.BranchLinkCreateListener branchLinkCreateListener) {
        getLinkBuilder(context, linkProperties).generateShortUrl(branchLinkCreateListener);
    }

    public void generateShortUrl(Context context, LinkProperties linkProperties, Branch.BranchLinkCreateListener branchLinkCreateListener, boolean z) {
        ((BranchShortLinkBuilder) getLinkBuilder(context, linkProperties).setDefaultToLongUrl(z)).generateShortUrl(branchLinkCreateListener);
    }

    public void showShareSheet(Activity activity, LinkProperties linkProperties, ShareSheetStyle shareSheetStyle, Branch.BranchLinkShareListener branchLinkShareListener) {
        showShareSheet(activity, linkProperties, shareSheetStyle, branchLinkShareListener, (Branch.IChannelProperties) null);
    }

    public void showShareSheet(Activity activity, LinkProperties linkProperties, ShareSheetStyle shareSheetStyle, Branch.BranchLinkShareListener branchLinkShareListener, Branch.IChannelProperties iChannelProperties) {
        if (Branch.getInstance() != null) {
            Branch.ShareLinkBuilder shareLinkBuilder = new Branch.ShareLinkBuilder(activity, getLinkBuilder((Context) activity, linkProperties));
            shareLinkBuilder.setCallback(new LinkShareListenerWrapper(branchLinkShareListener, shareLinkBuilder, linkProperties)).setChannelProperties(iChannelProperties).setSubject(shareSheetStyle.getMessageTitle()).setMessage(shareSheetStyle.getMessageBody());
            if (shareSheetStyle.getCopyUrlIcon() != null) {
                shareLinkBuilder.setCopyUrlStyle(shareSheetStyle.getCopyUrlIcon(), shareSheetStyle.getCopyURlText(), shareSheetStyle.getUrlCopiedMessage());
            }
            if (shareSheetStyle.getMoreOptionIcon() != null) {
                shareLinkBuilder.setMoreOptionStyle(shareSheetStyle.getMoreOptionIcon(), shareSheetStyle.getMoreOptionText());
            }
            if (shareSheetStyle.getDefaultURL() != null) {
                shareLinkBuilder.setDefaultURL(shareSheetStyle.getDefaultURL());
            }
            if (shareSheetStyle.getPreferredOptions().size() > 0) {
                shareLinkBuilder.addPreferredSharingOptions(shareSheetStyle.getPreferredOptions());
            }
            if (shareSheetStyle.getStyleResourceID() > 0) {
                shareLinkBuilder.setStyleResourceID(shareSheetStyle.getStyleResourceID());
            }
            shareLinkBuilder.setDividerHeight(shareSheetStyle.getDividerHeight());
            shareLinkBuilder.setAsFullWidthStyle(shareSheetStyle.getIsFullWidthStyle());
            shareLinkBuilder.setDialogThemeResourceID(shareSheetStyle.getDialogThemeResourceID());
            shareLinkBuilder.setSharingTitle(shareSheetStyle.getSharingTitle());
            shareLinkBuilder.setSharingTitle(shareSheetStyle.getSharingTitleView());
            shareLinkBuilder.setIconSize(shareSheetStyle.getIconSize());
            if (shareSheetStyle.getIncludedInShareSheet() != null && shareSheetStyle.getIncludedInShareSheet().size() > 0) {
                shareLinkBuilder.includeInShareSheet(shareSheetStyle.getIncludedInShareSheet());
            }
            if (shareSheetStyle.getExcludedFromShareSheet() != null && shareSheetStyle.getExcludedFromShareSheet().size() > 0) {
                shareLinkBuilder.excludeFromShareSheet(shareSheetStyle.getExcludedFromShareSheet());
            }
            shareLinkBuilder.shareLink();
        } else if (branchLinkShareListener != null) {
            branchLinkShareListener.onLinkShareResponse((String) null, (String) null, new BranchError("Trouble sharing link. ", BranchError.ERR_BRANCH_NOT_INSTANTIATED));
        } else {
            Log.e("BranchSDK", "Sharing error. Branch instance is not created yet. Make sure you have initialised Branch.");
        }
    }

    private BranchShortLinkBuilder getLinkBuilder(Context context, LinkProperties linkProperties) {
        return getLinkBuilder(new BranchShortLinkBuilder(context), linkProperties);
    }

    /* access modifiers changed from: private */
    public BranchShortLinkBuilder getLinkBuilder(BranchShortLinkBuilder branchShortLinkBuilder, LinkProperties linkProperties) {
        if (linkProperties.getTags() != null) {
            branchShortLinkBuilder.addTags(linkProperties.getTags());
        }
        if (linkProperties.getFeature() != null) {
            branchShortLinkBuilder.setFeature(linkProperties.getFeature());
        }
        if (linkProperties.getAlias() != null) {
            branchShortLinkBuilder.setAlias(linkProperties.getAlias());
        }
        if (linkProperties.getChannel() != null) {
            branchShortLinkBuilder.setChannel(linkProperties.getChannel());
        }
        if (linkProperties.getStage() != null) {
            branchShortLinkBuilder.setStage(linkProperties.getStage());
        }
        if (linkProperties.getCampaign() != null) {
            branchShortLinkBuilder.setCampaign(linkProperties.getCampaign());
        }
        if (linkProperties.getMatchDuration() > 0) {
            branchShortLinkBuilder.setDuration(linkProperties.getMatchDuration());
        }
        if (!TextUtils.isEmpty(this.title_)) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.ContentTitle.getKey(), this.title_);
        }
        if (!TextUtils.isEmpty(this.canonicalIdentifier_)) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.CanonicalIdentifier.getKey(), this.canonicalIdentifier_);
        }
        if (!TextUtils.isEmpty(this.canonicalUrl_)) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.CanonicalUrl.getKey(), this.canonicalUrl_);
        }
        JSONArray keywordsJsonArray = getKeywordsJsonArray();
        if (keywordsJsonArray.length() > 0) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.ContentKeyWords.getKey(), keywordsJsonArray);
        }
        if (!TextUtils.isEmpty(this.description_)) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.ContentDesc.getKey(), this.description_);
        }
        if (!TextUtils.isEmpty(this.imageUrl_)) {
            branchShortLinkBuilder.addParameters(Defines.Jsonkey.ContentImgUrl.getKey(), this.imageUrl_);
        }
        if (this.expirationInMilliSec_ > 0) {
            String key = Defines.Jsonkey.ContentExpiryTime.getKey();
            branchShortLinkBuilder.addParameters(key, "" + this.expirationInMilliSec_);
        }
        String key2 = Defines.Jsonkey.PublicallyIndexable.getKey();
        branchShortLinkBuilder.addParameters(key2, "" + isPublicallyIndexable());
        JSONObject convertToJson = this.metadata_.convertToJson();
        try {
            Iterator<String> keys = convertToJson.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                branchShortLinkBuilder.addParameters(next, convertToJson.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashMap<String, String> controlParams = linkProperties.getControlParams();
        for (String next2 : controlParams.keySet()) {
            branchShortLinkBuilder.addParameters(next2, controlParams.get(next2));
        }
        return branchShortLinkBuilder;
    }

    public static BranchUniversalObject getReferredBranchUniversalObject() {
        BranchUniversalObject createInstance;
        Branch instance = Branch.getInstance();
        if (instance == null) {
            return null;
        }
        try {
            if (instance.getLatestReferringParams() == null) {
                return null;
            }
            if (instance.getLatestReferringParams().has("+clicked_branch_link") && instance.getLatestReferringParams().getBoolean("+clicked_branch_link")) {
                createInstance = createInstance(instance.getLatestReferringParams());
            } else if (instance.getDeeplinkDebugParams() == null || instance.getDeeplinkDebugParams().length() <= 0) {
                return null;
            } else {
                createInstance = createInstance(instance.getLatestReferringParams());
            }
            return createInstance;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: io.branch.indexing.BranchUniversalObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: io.branch.indexing.BranchUniversalObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: org.json.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static io.branch.indexing.BranchUniversalObject createInstance(org.json.JSONObject r5) {
        /*
            r0 = 0
            io.branch.indexing.BranchUniversalObject r1 = new io.branch.indexing.BranchUniversalObject     // Catch:{ Exception -> 0x00fd }
            r1.<init>()     // Catch:{ Exception -> 0x00fd }
            io.branch.referral.BranchUtil$JsonReader r2 = new io.branch.referral.BranchUtil$JsonReader     // Catch:{ Exception -> 0x00fc }
            r2.<init>(r5)     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ContentTitle     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r2.readOutString(r5)     // Catch:{ Exception -> 0x00fc }
            r1.title_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.CanonicalIdentifier     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r2.readOutString(r5)     // Catch:{ Exception -> 0x00fc }
            r1.canonicalIdentifier_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.CanonicalUrl     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r2.readOutString(r5)     // Catch:{ Exception -> 0x00fc }
            r1.canonicalUrl_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ContentDesc     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r2.readOutString(r5)     // Catch:{ Exception -> 0x00fc }
            r1.description_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ContentImgUrl     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r2.readOutString(r5)     // Catch:{ Exception -> 0x00fc }
            r1.imageUrl_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ContentExpiryTime     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            long r3 = r2.readOutLong(r5)     // Catch:{ Exception -> 0x00fc }
            r1.expirationInMilliSec_ = r3     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ContentKeyWords     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.Object r5 = r2.readOut(r5)     // Catch:{ Exception -> 0x00fc }
            boolean r3 = r5 instanceof org.json.JSONArray     // Catch:{ Exception -> 0x00fc }
            if (r3 == 0) goto L_0x0065
            r0 = r5
            org.json.JSONArray r0 = (org.json.JSONArray) r0     // Catch:{ Exception -> 0x00fc }
            goto L_0x0070
        L_0x0065:
            boolean r3 = r5 instanceof java.lang.String     // Catch:{ Exception -> 0x00fc }
            if (r3 == 0) goto L_0x0070
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x00fc }
            r0.<init>(r5)     // Catch:{ Exception -> 0x00fc }
        L_0x0070:
            if (r0 == 0) goto L_0x0087
            r5 = 0
        L_0x0073:
            int r3 = r0.length()     // Catch:{ Exception -> 0x00fc }
            if (r5 >= r3) goto L_0x0087
            java.util.ArrayList<java.lang.String> r3 = r1.keywords_     // Catch:{ Exception -> 0x00fc }
            java.lang.Object r4 = r0.get(r5)     // Catch:{ Exception -> 0x00fc }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x00fc }
            r3.add(r4)     // Catch:{ Exception -> 0x00fc }
            int r5 = r5 + 1
            goto L_0x0073
        L_0x0087:
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.PublicallyIndexable     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            java.lang.Object r5 = r2.readOut(r5)     // Catch:{ Exception -> 0x00fc }
            boolean r0 = r5 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x00fc }
            if (r0 == 0) goto L_0x00a5
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ Exception -> 0x00fc }
            boolean r5 = r5.booleanValue()     // Catch:{ Exception -> 0x00fc }
            if (r5 == 0) goto L_0x00a0
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC     // Catch:{ Exception -> 0x00fc }
            goto L_0x00a2
        L_0x00a0:
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE     // Catch:{ Exception -> 0x00fc }
        L_0x00a2:
            r1.indexMode_ = r5     // Catch:{ Exception -> 0x00fc }
            goto L_0x00b9
        L_0x00a5:
            boolean r0 = r5 instanceof java.lang.Integer     // Catch:{ Exception -> 0x00fc }
            if (r0 == 0) goto L_0x00b9
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x00fc }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x00fc }
            r0 = 1
            if (r5 != r0) goto L_0x00b5
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC     // Catch:{ Exception -> 0x00fc }
            goto L_0x00b7
        L_0x00b5:
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE     // Catch:{ Exception -> 0x00fc }
        L_0x00b7:
            r1.indexMode_ = r5     // Catch:{ Exception -> 0x00fc }
        L_0x00b9:
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.LocallyIndexable     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            boolean r5 = r2.readOutBoolean(r5)     // Catch:{ Exception -> 0x00fc }
            if (r5 == 0) goto L_0x00c8
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC     // Catch:{ Exception -> 0x00fc }
            goto L_0x00ca
        L_0x00c8:
            io.branch.indexing.BranchUniversalObject$CONTENT_INDEX_MODE r5 = io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE     // Catch:{ Exception -> 0x00fc }
        L_0x00ca:
            r1.localIndexMode_ = r5     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.CreationTimestamp     // Catch:{ Exception -> 0x00fc }
            java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x00fc }
            long r3 = r2.readOutLong(r5)     // Catch:{ Exception -> 0x00fc }
            r1.creationTimeStamp_ = r3     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.util.ContentMetadata r5 = io.branch.referral.util.ContentMetadata.createFromJson(r2)     // Catch:{ Exception -> 0x00fc }
            r1.metadata_ = r5     // Catch:{ Exception -> 0x00fc }
            org.json.JSONObject r5 = r2.getJsonObject()     // Catch:{ Exception -> 0x00fc }
            java.util.Iterator r0 = r5.keys()     // Catch:{ Exception -> 0x00fc }
        L_0x00e6:
            boolean r2 = r0.hasNext()     // Catch:{ Exception -> 0x00fc }
            if (r2 == 0) goto L_0x00fe
            java.lang.Object r2 = r0.next()     // Catch:{ Exception -> 0x00fc }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00fc }
            io.branch.referral.util.ContentMetadata r3 = r1.metadata_     // Catch:{ Exception -> 0x00fc }
            java.lang.String r4 = r5.optString(r2)     // Catch:{ Exception -> 0x00fc }
            r3.addCustomMetadata(r2, r4)     // Catch:{ Exception -> 0x00fc }
            goto L_0x00e6
        L_0x00fc:
            r0 = r1
        L_0x00fd:
            r1 = r0
        L_0x00fe:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.indexing.BranchUniversalObject.createInstance(org.json.JSONObject):io.branch.indexing.BranchUniversalObject");
    }

    public JSONObject convertToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject convertToJson = this.metadata_.convertToJson();
            Iterator<String> keys = convertToJson.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, convertToJson.get(next));
            }
            if (!TextUtils.isEmpty(this.title_)) {
                jSONObject.put(Defines.Jsonkey.ContentTitle.getKey(), this.title_);
            }
            if (!TextUtils.isEmpty(this.canonicalIdentifier_)) {
                jSONObject.put(Defines.Jsonkey.CanonicalIdentifier.getKey(), this.canonicalIdentifier_);
            }
            if (!TextUtils.isEmpty(this.canonicalUrl_)) {
                jSONObject.put(Defines.Jsonkey.CanonicalUrl.getKey(), this.canonicalUrl_);
            }
            if (this.keywords_.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = this.keywords_.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put(Defines.Jsonkey.ContentKeyWords.getKey(), jSONArray);
            }
            if (!TextUtils.isEmpty(this.description_)) {
                jSONObject.put(Defines.Jsonkey.ContentDesc.getKey(), this.description_);
            }
            if (!TextUtils.isEmpty(this.imageUrl_)) {
                jSONObject.put(Defines.Jsonkey.ContentImgUrl.getKey(), this.imageUrl_);
            }
            if (this.expirationInMilliSec_ > 0) {
                jSONObject.put(Defines.Jsonkey.ContentExpiryTime.getKey(), this.expirationInMilliSec_);
            }
            jSONObject.put(Defines.Jsonkey.PublicallyIndexable.getKey(), isPublicallyIndexable());
            jSONObject.put(Defines.Jsonkey.LocallyIndexable.getKey(), isLocallyIndexable());
            jSONObject.put(Defines.Jsonkey.CreationTimestamp.getKey(), this.creationTimeStamp_);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.creationTimeStamp_);
        parcel.writeString(this.canonicalIdentifier_);
        parcel.writeString(this.canonicalUrl_);
        parcel.writeString(this.title_);
        parcel.writeString(this.description_);
        parcel.writeString(this.imageUrl_);
        parcel.writeLong(this.expirationInMilliSec_);
        parcel.writeInt(this.indexMode_.ordinal());
        parcel.writeSerializable(this.keywords_);
        parcel.writeParcelable(this.metadata_, i);
        parcel.writeInt(this.localIndexMode_.ordinal());
    }

    private BranchUniversalObject(Parcel parcel) {
        this();
        this.creationTimeStamp_ = parcel.readLong();
        this.canonicalIdentifier_ = parcel.readString();
        this.canonicalUrl_ = parcel.readString();
        this.title_ = parcel.readString();
        this.description_ = parcel.readString();
        this.imageUrl_ = parcel.readString();
        this.expirationInMilliSec_ = parcel.readLong();
        this.indexMode_ = CONTENT_INDEX_MODE.values()[parcel.readInt()];
        ArrayList arrayList = (ArrayList) parcel.readSerializable();
        if (arrayList != null) {
            this.keywords_.addAll(arrayList);
        }
        this.metadata_ = (ContentMetadata) parcel.readParcelable(ContentMetadata.class.getClassLoader());
        this.localIndexMode_ = CONTENT_INDEX_MODE.values()[parcel.readInt()];
    }

    private class LinkShareListenerWrapper implements Branch.BranchLinkShareListener {
        private final LinkProperties linkProperties_;
        private final Branch.BranchLinkShareListener originalCallback_;
        private final Branch.ShareLinkBuilder shareLinkBuilder_;

        LinkShareListenerWrapper(Branch.BranchLinkShareListener branchLinkShareListener, Branch.ShareLinkBuilder shareLinkBuilder, LinkProperties linkProperties) {
            this.originalCallback_ = branchLinkShareListener;
            this.shareLinkBuilder_ = shareLinkBuilder;
            this.linkProperties_ = linkProperties;
        }

        public void onShareLinkDialogLaunched() {
            Branch.BranchLinkShareListener branchLinkShareListener = this.originalCallback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onShareLinkDialogLaunched();
            }
        }

        public void onShareLinkDialogDismissed() {
            Branch.BranchLinkShareListener branchLinkShareListener = this.originalCallback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onShareLinkDialogDismissed();
            }
        }

        public void onLinkShareResponse(String str, String str2, BranchError branchError) {
            HashMap hashMap = new HashMap();
            if (branchError == null) {
                hashMap.put(Defines.Jsonkey.SharedLink.getKey(), str);
            } else {
                hashMap.put(Defines.Jsonkey.ShareError.getKey(), branchError.getMessage());
            }
            BranchUniversalObject.this.userCompletedAction(BRANCH_STANDARD_EVENT.SHARE.getName(), (HashMap<String, String>) hashMap);
            Branch.BranchLinkShareListener branchLinkShareListener = this.originalCallback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onLinkShareResponse(str, str2, branchError);
            }
        }

        public void onChannelSelected(String str) {
            Branch.BranchLinkShareListener branchLinkShareListener = this.originalCallback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onChannelSelected(str);
            }
            Branch.BranchLinkShareListener branchLinkShareListener2 = this.originalCallback_;
            if ((branchLinkShareListener2 instanceof Branch.ExtendedBranchLinkShareListener) && ((Branch.ExtendedBranchLinkShareListener) branchLinkShareListener2).onChannelSelected(str, BranchUniversalObject.this, this.linkProperties_)) {
                Branch.ShareLinkBuilder shareLinkBuilder = this.shareLinkBuilder_;
                shareLinkBuilder.setShortLinkBuilderInternal(BranchUniversalObject.this.getLinkBuilder(shareLinkBuilder.getShortLinkBuilder(), this.linkProperties_));
            }
        }
    }
}
