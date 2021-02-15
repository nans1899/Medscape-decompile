package com.medscape.android.activity.search;

import android.content.Context;
import android.net.Uri;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.actions.SearchIntents;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.activity.search.model.SearchSuggestion;
import com.medscape.android.activity.search.model.SearchSuggestionMsg;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.utils.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchParser {
    public static ArrayList<Article> parseArticles(JSONObject jSONObject, Context context) throws JSONException {
        JSONObject jSONObject2;
        ArrayList<Article> arrayList = new ArrayList<>();
        if (jSONObject != null && jSONObject.has("data")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
            if (jSONObject3.has("response") && (jSONObject2 = jSONObject3.getJSONObject("response")) != null && jSONObject2.has("docs")) {
                JSONArray jSONArray = jSONObject2.getJSONArray("docs");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(createArticeFromJSON(jSONArray.getJSONObject(i), context));
                }
            }
        }
        return arrayList;
    }

    private static Article createArticeFromJSON(JSONObject jSONObject, Context context) throws JSONException {
        String str;
        Article article = new Article();
        article.mIsInlineAD = false;
        if (jSONObject != null) {
            if (jSONObject.has("title")) {
                article.mTitle = jSONObject.getString("title").trim();
            }
            if (jSONObject.has("clientUrl")) {
                String string = jSONObject.getString("clientUrl");
                if (!string.contains("www.medscape.com")) {
                    String str2 = ((!StringUtil.isNotEmpty(string) || string.startsWith("/")) ? "https://www.medscape.com" : "https://www.medscape.com/") + string;
                    article.mArticleId = Uri.parse(string).getLastPathSegment();
                    string = str2;
                }
                String str3 = string + "?src=android";
                try {
                    str = ((str3 + "&devicetype=android&") + "osversion=" + Util.getPhoneOSVersion() + "&") + "appversion=" + com.medscape.android.util.Util.getApplicationVersion(context);
                } catch (Throwable unused) {
                    str = str3;
                }
                article.mLink = str;
                article.mHasLinkUrl = true;
            }
            StringBuilder sb = new StringBuilder();
            if (!jSONObject.isNull("origContentType")) {
                sb.append(jSONObject.getString("origContentType"));
            }
            if (!jSONObject.isNull("pubDisplay")) {
                if (StringUtil.isNotEmpty(sb.toString())) {
                    sb.append(", ");
                }
                sb.append(jSONObject.getString("pubDisplay"));
            }
            if (!jSONObject.isNull("publicationDate")) {
                try {
                    Calendar instance = Calendar.getInstance();
                    Long valueOf = Long.valueOf(jSONObject.getLong("publicationDate"));
                    instance.setTimeInMillis(jSONObject.getLong("publicationDate"));
                    if (valueOf.longValue() > 0) {
                        if (StringUtil.isNotEmpty(sb.toString())) {
                            sb.append(", ");
                        }
                        sb.append(instance.getDisplayName(2, 2, Locale.US));
                        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        sb.append(instance.get(1));
                    }
                } catch (Throwable unused2) {
                }
            }
            article.mContentTypeDate = sb.toString();
            article.mSearchSuggestionMsg = null;
        }
        return article;
    }

    public static ArrayList<CRData> parseReferencefromJSON(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        ArrayList<CRData> arrayList = new ArrayList<>();
        if (jSONObject != null && jSONObject.has("data")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
            if (jSONObject3.has("response") && (jSONObject2 = jSONObject3.getJSONObject("response")) != null && jSONObject2.has("docs")) {
                JSONArray jSONArray = jSONObject2.getJSONArray("docs");
                for (int i = 0; i < jSONArray.length(); i++) {
                    CRData createCRDataFromJSON = createCRDataFromJSON(jSONArray.getJSONObject(i));
                    if (createCRDataFromJSON != null) {
                        arrayList.add(createCRDataFromJSON);
                        createCRDataFromJSON.setSearchSuggestionMsg((SearchSuggestionMsg) null);
                    }
                }
            }
        }
        return arrayList;
    }

    public static int parseMaxResults(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (jSONObject != null && jSONObject.has("data")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("data");
            if (jSONObject3.has("response") && (jSONObject2 = jSONObject3.getJSONObject("response")) != null && jSONObject2.has("numFound")) {
                return jSONObject2.getInt("numFound");
            }
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.medscape.android.activity.search.model.CRData createCRDataFromJSON(org.json.JSONObject r7) throws org.json.JSONException {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x009e
            com.medscape.android.activity.search.model.CRData r1 = new com.medscape.android.activity.search.model.CRData
            r1.<init>()
            r2 = 0
            r1.setInlineAD(r2)
            java.lang.String r3 = "id"
            boolean r4 = r7.has(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0032
            java.lang.String r3 = r7.getString(r3)
            java.lang.String r4 = "_"
            java.lang.String[] r3 = r3.split(r4)
            r4 = -1
            int r6 = r3.length
            if (r6 <= r5) goto L_0x002b
            int r4 = r3.length
            int r4 = r4 - r5
            r3 = r3[r4]
            int r4 = com.medscape.android.util.Util.convertStringToInt(r3)
        L_0x002b:
            if (r4 <= 0) goto L_0x0032
            r1.setCid(r4)
            r3 = 1
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            java.lang.String r4 = "title"
            boolean r6 = r7.has(r4)
            if (r6 == 0) goto L_0x0046
            java.lang.String r4 = r7.getString(r4)
            java.lang.String r4 = r4.trim()
            r1.setTitle(r4)
        L_0x0046:
            java.lang.String r4 = "contentGroup"
            boolean r6 = r7.has(r4)
            if (r6 == 0) goto L_0x009a
            if (r3 == 0) goto L_0x009a
            java.lang.String r7 = r7.getString(r4)
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r7)
            if (r3 == 0) goto L_0x009a
            java.lang.String r3 = "Drugs"
            boolean r3 = r7.contains(r3)
            if (r3 == 0) goto L_0x0069
            java.lang.String r7 = "D"
            r1.setType(r7)
        L_0x0067:
            r2 = 1
            goto L_0x009a
        L_0x0069:
            java.lang.String r3 = "Procedures"
            boolean r3 = r7.contains(r3)
            java.lang.String r4 = "T"
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = "Laboratory Medicine"
            boolean r3 = r7.contains(r3)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = "Tables & Protocols"
            boolean r3 = r7.contains(r3)
            if (r3 == 0) goto L_0x0084
            goto L_0x0093
        L_0x0084:
            java.lang.String r3 = "Conditions"
            boolean r7 = r7.contains(r3)
            if (r7 == 0) goto L_0x009a
            r1.setType(r4)
            r1.setCrType(r2)
            goto L_0x0067
        L_0x0093:
            r1.setType(r4)
            r1.setCrType(r5)
            goto L_0x0067
        L_0x009a:
            if (r2 != 0) goto L_0x009d
            return r0
        L_0x009d:
            return r1
        L_0x009e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.SearchParser.createCRDataFromJSON(org.json.JSONObject):com.medscape.android.activity.search.model.CRData");
    }

    public static ArrayList<SearchSuggestion> parseSearchSuggestions(JSONObject jSONObject) throws JSONException {
        ArrayList<SearchSuggestion> arrayList = new ArrayList<>();
        if (jSONObject != null && jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has("spellcheck")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("spellcheck");
                if (jSONObject3.has(ShareConstants.WEB_DIALOG_PARAM_SUGGESTIONS)) {
                    JSONArray jSONArray = jSONObject3.getJSONArray(ShareConstants.WEB_DIALOG_PARAM_SUGGESTIONS);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        SearchSuggestion searchSuggestion = new SearchSuggestion();
                        JSONObject jSONObject4 = jSONArray.getJSONObject(i).getJSONObject("suggestion");
                        if (jSONObject4.has(SearchIntents.EXTRA_QUERY)) {
                            searchSuggestion.setQuery(jSONObject4.getString(SearchIntents.EXTRA_QUERY));
                        }
                        if (jSONObject4.has("hits")) {
                            searchSuggestion.setHits(jSONObject4.getInt("hits"));
                        }
                        arrayList.add(searchSuggestion);
                    }
                }
            }
        }
        return arrayList;
    }
}
