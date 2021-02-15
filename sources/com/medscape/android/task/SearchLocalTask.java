package com.medscape.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.drugs.helper.SearchHelper;
import java.util.List;

public class SearchLocalTask extends AsyncTask<String, Void, List<CRData>> {
    private final Context mContext;
    private final SearchLocalCompleteListener mListener;
    private final int mOffset;
    private final int mResultCount;
    private final SearchMode mSearchMode;

    public interface SearchLocalCompleteListener {
        void onNoSearchResults();

        void onSearchComplete(List<CRData> list);
    }

    public SearchLocalTask(Context context, SearchMode searchMode, int i) {
        this(context, searchMode, i, 0);
    }

    public SearchLocalTask(Context context, SearchMode searchMode, int i, int i2) {
        this(context, searchMode, i, i2, (SearchLocalCompleteListener) null);
    }

    public SearchLocalTask(Context context, SearchMode searchMode, int i, int i2, SearchLocalCompleteListener searchLocalCompleteListener) {
        this.mContext = context;
        this.mSearchMode = searchMode;
        this.mResultCount = i;
        this.mOffset = i2;
        this.mListener = searchLocalCompleteListener;
    }

    /* access modifiers changed from: protected */
    public List<CRData> doInBackground(String... strArr) {
        String str = strArr[0];
        int i = AnonymousClass1.$SwitchMap$com$medscape$android$activity$search$SearchMode[this.mSearchMode.ordinal()];
        if (i == 1) {
            return new SearchHelper().wordAllMatching(str, this.mResultCount, this.mOffset, this.mContext);
        }
        if (i == 2) {
            return SearchHelper.wordDrugMatching(str, this.mResultCount, this.mOffset, this.mContext);
        }
        if (i == 3) {
            return SearchHelper.wordConditionMatching(str, this.mResultCount, this.mOffset, this.mContext);
        }
        if (i == 4) {
            return SearchHelper.wordProcedureMatching(str, this.mResultCount, this.mOffset, this.mContext);
        }
        if (i != 5) {
            return new SearchHelper().wordAllMatching(str, this.mResultCount, this.mOffset, this.mContext);
        }
        return SearchHelper.wordCalculatorMatching(str, this.mResultCount, this.mOffset, this.mContext);
    }

    /* renamed from: com.medscape.android.task.SearchLocalTask$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$medscape$android$activity$search$SearchMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.medscape.android.activity.search.SearchMode[] r0 = com.medscape.android.activity.search.SearchMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$medscape$android$activity$search$SearchMode = r0
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_REFERENCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_DRUGS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_CONDITIONS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_PROCEDURES     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$medscape$android$activity$search$SearchMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.medscape.android.activity.search.SearchMode r1 = com.medscape.android.activity.search.SearchMode.SEARCH_CALCULATORS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.task.SearchLocalTask.AnonymousClass1.<clinit>():void");
        }
    }

    public void onPostExecute(List<CRData> list) {
        if (this.mListener == null) {
            return;
        }
        if (list == null || list.isEmpty()) {
            this.mListener.onNoSearchResults();
        } else {
            this.mListener.onSearchComplete(list);
        }
    }
}
