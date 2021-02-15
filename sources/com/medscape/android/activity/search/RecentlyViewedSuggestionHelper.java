package com.medscape.android.activity.search;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

public class RecentlyViewedSuggestionHelper {
    public static final long HEADER_ROW_ID = -1;
    public static final String META_ARTICLE = "article";
    public static final String META_CALC_ID = "calcId";
    public static final String META_CLINICAL_REF_ARTICLE = "clinicalRefArticle";
    public static final String META_DRUG_CONTENT_ID = "contentId";
    public static final String META_DRUG_GENERIC_ID = "genericId";
    public static final String META_TYPE = "type";
    public static final String META_URL = "url";
    public static final String META_USER_GUID = "userGUID";
    private static final Map<SearchMode, SearchModeToTypeComparator> SEARCH_MODE_TO_TYPE;
    public static final String TYPE_EDUCATION = "education";
    public static final String TYPE_MEDLINE = "medline";
    public static final String TYPE_NEWS = "news";
    public static final Uri URI_RECENTLY_VIEWED = Uri.parse("content://com.medscape.android.activity.search.RecentlyViewedItemsSuggestionsProvider/search_suggest_query");
    private static RecentlyViewedSearchSuggestions recentlyViewedSuggestions;

    private interface SearchModeToTypeComparator {
        boolean isOfSearchMode(Bundle bundle);
    }

    static {
        HashMap hashMap = new HashMap();
        SEARCH_MODE_TO_TYPE = hashMap;
        hashMap.put(SearchMode.SEARCH_NEWS, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return "news".equals(bundle.getString("type"));
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_REFERENCE, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                String string = bundle.getString("type");
                return SearchHelper.TYPE_DRUG.equals(string) || "T".equals(string) || SearchHelper.TYPE_CALCULATOR.equals(string);
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_EDUCATION, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return "education".equals(bundle.getString("type"));
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_MEDLINE, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return RecentlyViewedSuggestionHelper.TYPE_MEDLINE.equals(bundle.getString("type"));
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_DRUGS, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return SearchHelper.TYPE_DRUG.equals(bundle.getString("type"));
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_CONDITIONS, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                if ("T".equals(bundle.getString("type")) && ((ClinicalReferenceArticle) bundle.getSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE)).getType() == 0) {
                    return true;
                }
                return false;
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_PROCEDURES, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                if ("T".equals(bundle.getString("type")) && ((ClinicalReferenceArticle) bundle.getSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE)).getType() == 1) {
                    return true;
                }
                return false;
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_CALCULATORS, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return SearchHelper.TYPE_CALCULATOR.equals(bundle.getString("type"));
            }
        });
        SEARCH_MODE_TO_TYPE.put(SearchMode.SEARCH_PRICING, new SearchModeToTypeComparator() {
            public boolean isOfSearchMode(Bundle bundle) {
                return SearchHelper.TYPE_PRICING.equalsIgnoreCase(bundle.getString("type"));
            }
        });
    }

    public static void addToRecentlyViewed(Context context, String str, Bundle bundle) {
        if (recentlyViewedSuggestions == null) {
            recentlyViewedSuggestions = new RecentlyViewedSearchSuggestions(context.getApplicationContext());
        }
        bundle.putString("userGUID", AuthenticationManager.getInstance(context).getMaskedGuid());
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        String encodeToString = Base64.encodeToString(obtain.marshall(), 0);
        obtain.recycle();
        recentlyViewedSuggestions.saveRecentQuery(str, encodeToString);
    }

    public static Bundle decodeMeta(Context context, String str) {
        byte[] decode = Base64.decode(str, 0);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(decode, 0, decode.length);
        obtain.setDataPosition(0);
        Bundle readBundle = obtain.readBundle(context.getClassLoader());
        obtain.recycle();
        return readBundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        if (r12.isClosed() == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        android.util.Log.i("RecentlyViewed", "closing cursor - filterRecentlyViewedBySearchMode");
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        if (r12.isClosed() == false) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.database.Cursor filterRecentlyViewedBySearchMode(android.content.Context r11, android.database.Cursor r12, com.medscape.android.activity.search.SearchMode r13, int r14) {
        /*
            java.lang.String r0 = "closing cursor - filterRecentlyViewedBySearchMode"
            java.lang.String r1 = "RecentlyViewed"
            r2 = 0
            if (r12 == 0) goto L_0x0089
            int r3 = r12.getCount()
            if (r3 != 0) goto L_0x000f
            goto L_0x0089
        L_0x000f:
            android.database.MatrixCursor r3 = new android.database.MatrixCursor
            java.lang.String r4 = "_id"
            java.lang.String r5 = "suggest_text_1"
            java.lang.String r6 = "suggest_text_2"
            java.lang.String[] r7 = new java.lang.String[]{r4, r5, r6}
            r3.<init>(r7)
            includeHeaderRow(r3)
            int r4 = r12.getColumnIndex(r4)
            int r5 = r12.getColumnIndex(r5)
            int r6 = r12.getColumnIndex(r6)
        L_0x002d:
            r7 = 1
            boolean r8 = r12.moveToNext()     // Catch:{ all -> 0x0063 }
            if (r8 == 0) goto L_0x0054
            int r8 = r3.getCount()     // Catch:{ all -> 0x0063 }
            int r9 = r14 + 1
            if (r8 >= r9) goto L_0x0054
            java.lang.String r8 = r12.getString(r6)     // Catch:{ all -> 0x0063 }
            android.os.Bundle r9 = decodeMeta(r11, r8)     // Catch:{ all -> 0x0063 }
            boolean r10 = isTypeOfSearchMode(r9, r13)     // Catch:{ all -> 0x0063 }
            if (r10 == 0) goto L_0x002d
            boolean r9 = isForCurrentUser(r11, r9)     // Catch:{ all -> 0x0063 }
            if (r9 == 0) goto L_0x002d
            includeRow(r12, r3, r4, r5, r8)     // Catch:{ all -> 0x0063 }
            goto L_0x002d
        L_0x0054:
            if (r12 == 0) goto L_0x0071
            boolean r11 = r12.isClosed()
            if (r11 != 0) goto L_0x0071
        L_0x005c:
            android.util.Log.i(r1, r0)
            r12.close()
            goto L_0x0071
        L_0x0063:
            java.lang.String r11 = "Ignoring recentlyviewed error"
            android.util.Log.w(r1, r11)     // Catch:{ all -> 0x0079 }
            if (r12 == 0) goto L_0x0071
            boolean r11 = r12.isClosed()
            if (r11 != 0) goto L_0x0071
            goto L_0x005c
        L_0x0071:
            int r11 = r3.getCount()
            if (r11 != r7) goto L_0x0078
            return r2
        L_0x0078:
            return r3
        L_0x0079:
            r11 = move-exception
            if (r12 == 0) goto L_0x0088
            boolean r13 = r12.isClosed()
            if (r13 != 0) goto L_0x0088
            android.util.Log.i(r1, r0)
            r12.close()
        L_0x0088:
            throw r11
        L_0x0089:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.RecentlyViewedSuggestionHelper.filterRecentlyViewedBySearchMode(android.content.Context, android.database.Cursor, com.medscape.android.activity.search.SearchMode, int):android.database.Cursor");
    }

    private static void includeHeaderRow(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add(-1L);
        newRow.add("HEADER");
        newRow.add("HEADER");
    }

    private static void includeRow(Cursor cursor, MatrixCursor matrixCursor, int i, int i2, String str) {
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add(Long.valueOf(cursor.getLong(i)));
        newRow.add(cursor.getString(i2));
        newRow.add(str);
    }

    public static void clearHistoryForSearchMode(Context context, SearchMode searchMode) {
        if (recentlyViewedSuggestions == null) {
            recentlyViewedSuggestions = new RecentlyViewedSearchSuggestions(context.getApplicationContext());
        }
        recentlyViewedSuggestions.clearHistoryForSearchMode(searchMode);
    }

    static boolean isTypeOfSearchMode(Bundle bundle, SearchMode searchMode) {
        return SEARCH_MODE_TO_TYPE.get(searchMode).isOfSearchMode(bundle);
    }

    static boolean isForCurrentUser(Context context, Bundle bundle) {
        String maskedGuid = AuthenticationManager.getInstance(context).getMaskedGuid();
        return StringUtil.isNotEmpty(maskedGuid) && maskedGuid.equalsIgnoreCase(bundle.getString("userGUID"));
    }
}
