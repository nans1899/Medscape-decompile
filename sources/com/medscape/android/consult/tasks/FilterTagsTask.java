package com.medscape.android.consult.tasks;

import android.os.AsyncTask;
import com.medscape.android.consult.interfaces.ITagsForSearchReceivedListener;
import java.util.ArrayList;
import java.util.List;

public class FilterTagsTask extends AsyncTask<String, Void, List<String>> {
    private static final String TAG = GetTagsTask.class.getSimpleName();
    private String mQuery;
    private List<String> mTagList;
    private ITagsForSearchReceivedListener mTagsReceivedListener;

    public FilterTagsTask(List<String> list, ITagsForSearchReceivedListener iTagsForSearchReceivedListener) {
        this.mTagList = list;
        this.mTagsReceivedListener = iTagsForSearchReceivedListener;
    }

    /* access modifiers changed from: protected */
    public List<String> doInBackground(String... strArr) {
        ArrayList arrayList = new ArrayList();
        List<String> list = this.mTagList;
        if (list == null || strArr == null || strArr.length != 1 || strArr[0] == null) {
            return this.mTagList;
        }
        this.mQuery = strArr[0];
        for (String next : list) {
            if (next.toLowerCase().startsWith(this.mQuery)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<String> list) {
        super.onPostExecute(list);
        ITagsForSearchReceivedListener iTagsForSearchReceivedListener = this.mTagsReceivedListener;
        if (iTagsForSearchReceivedListener != null) {
            iTagsForSearchReceivedListener.onTagsReceived(list, this.mQuery);
        }
    }
}
