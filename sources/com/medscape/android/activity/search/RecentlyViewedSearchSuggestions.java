package com.medscape.android.activity.search;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.SearchRecentSuggestions;
import android.text.TextUtils;
import java.util.List;

public class RecentlyViewedSearchSuggestions extends SearchRecentSuggestions {
    private final Context ctx;
    private final Uri suggestionsUri = Uri.parse("content://com.medscape.android.activity.search.RecentlyViewedItemsSuggestionsProvider/suggestions");

    public RecentlyViewedSearchSuggestions(Context context) {
        super(context, RecentlyViewedItemsSuggestionsProvider.AUTHORITY, 3);
        this.ctx = context;
    }

    public void clearHistoryForSearchMode(final SearchMode searchMode) {
        new Thread("clearHistoryForSearchMode") {
            public void run() {
                RecentlyViewedSearchSuggestions.this.clearHistoryForSearchModeBlocking(searchMode);
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void clearHistoryForSearchModeBlocking(SearchMode searchMode) {
        removeRecentlyViewedItems(selectEntriesForSearchMode(searchMode));
    }

    private void removeRecentlyViewedItems(List<Long> list) {
        ContentResolver contentResolver = this.ctx.getContentResolver();
        Uri uri = this.suggestionsUri;
        contentResolver.delete(uri, "_id IN (" + TextUtils.join(", ", list) + ")", (String[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        if (r3.isClosed() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        android.util.Log.i("RecentlyViewed", "closing cursor - filterRecentlyViewedBySearchMode");
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0074, code lost:
        if (r3.isClosed() == false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.Long> selectEntriesForSearchMode(com.medscape.android.activity.search.SearchMode r10) {
        /*
            r9 = this;
            java.lang.String r0 = "closing cursor - filterRecentlyViewedBySearchMode"
            java.lang.String r1 = "RecentlyViewed"
            android.content.Context r2 = r9.ctx
            android.content.ContentResolver r3 = r2.getContentResolver()
            android.net.Uri r4 = com.medscape.android.activity.search.RecentlyViewedSuggestionHelper.URI_RECENTLY_VIEWED
            r2 = 0
            java.lang.String[] r7 = new java.lang.String[]{r2}
            r5 = 0
            r6 = 0
            r8 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)
            int r4 = r3.getCount()
            if (r4 != 0) goto L_0x001f
            return r2
        L_0x001f:
            java.lang.String r2 = "_id"
            int r2 = r3.getColumnIndex(r2)
            java.lang.String r4 = "suggest_text_2"
            int r4 = r3.getColumnIndex(r4)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L_0x0030:
            boolean r6 = r3.moveToNext()     // Catch:{ all -> 0x0069 }
            if (r6 == 0) goto L_0x005a
            java.lang.String r6 = r3.getString(r4)     // Catch:{ all -> 0x0069 }
            android.content.Context r7 = r9.ctx     // Catch:{ all -> 0x0069 }
            android.os.Bundle r6 = com.medscape.android.activity.search.RecentlyViewedSuggestionHelper.decodeMeta(r7, r6)     // Catch:{ all -> 0x0069 }
            boolean r7 = com.medscape.android.activity.search.RecentlyViewedSuggestionHelper.isTypeOfSearchMode(r6, r10)     // Catch:{ all -> 0x0069 }
            if (r7 == 0) goto L_0x0030
            android.content.Context r7 = r9.ctx     // Catch:{ all -> 0x0069 }
            boolean r6 = com.medscape.android.activity.search.RecentlyViewedSuggestionHelper.isForCurrentUser(r7, r6)     // Catch:{ all -> 0x0069 }
            if (r6 == 0) goto L_0x0030
            long r6 = r3.getLong(r2)     // Catch:{ all -> 0x0069 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0069 }
            r5.add(r6)     // Catch:{ all -> 0x0069 }
            goto L_0x0030
        L_0x005a:
            if (r3 == 0) goto L_0x0077
            boolean r10 = r3.isClosed()
            if (r10 != 0) goto L_0x0077
        L_0x0062:
            android.util.Log.i(r1, r0)
            r3.close()
            goto L_0x0077
        L_0x0069:
            java.lang.String r10 = "Ignoring recentlyviewed error"
            android.util.Log.w(r1, r10)     // Catch:{ all -> 0x0078 }
            if (r3 == 0) goto L_0x0077
            boolean r10 = r3.isClosed()
            if (r10 != 0) goto L_0x0077
            goto L_0x0062
        L_0x0077:
            return r5
        L_0x0078:
            r10 = move-exception
            if (r3 == 0) goto L_0x0087
            boolean r2 = r3.isClosed()
            if (r2 != 0) goto L_0x0087
            android.util.Log.i(r1, r0)
            r3.close()
        L_0x0087:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.RecentlyViewedSearchSuggestions.selectEntriesForSearchMode(com.medscape.android.activity.search.SearchMode):java.util.List");
    }
}
