package com.medscape.android.pillid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.task.ImprintSearchTask;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PillImprintSearchAdapter extends BaseAdapter implements Filterable {
    static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public MedscapeException mMedscapeException;
    /* access modifiers changed from: private */
    public List<String> mResultList = new ArrayList();
    /* access modifiers changed from: private */
    public AutoSearchRetryListener mRetryListener;
    /* access modifiers changed from: private */
    public View mRootView;

    public interface AutoSearchRetryListener {
        void onAutoSearchRetry();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public PillImprintSearchAdapter(Activity activity, View view, AutoSearchRetryListener autoSearchRetryListener) {
        this.mActivity = activity;
        this.mContext = activity;
        this.mRootView = view;
        this.mRetryListener = autoSearchRetryListener;
    }

    public int getCount() {
        return this.mResultList.size();
    }

    public String getItem(int i) {
        return this.mResultList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.single_textview_row_no_arrow_search_row, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.rowTitle)).setText(getItem(i));
        return view;
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                if (filterResults == null || filterResults.count <= 0) {
                    PillImprintSearchAdapter.this.notifyDataSetInvalidated();
                    return;
                }
                List unused = PillImprintSearchAdapter.this.mResultList = (List) filterResults.values;
                PillImprintSearchAdapter.this.notifyDataSetChanged();
            }

            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (charSequence != null) {
                    List<String> findImprints = findImprints(PillImprintSearchAdapter.this.mContext, charSequence.toString());
                    filterResults.values = findImprints;
                    filterResults.count = findImprints.size();
                }
                return filterResults;
            }

            /* access modifiers changed from: private */
            public List<String> findImprints(final Context context, final String str) {
                if (Util.isOnline(MedscapeApplication.get())) {
                    return new ImprintSearchTask(context, str, false).execute();
                }
                Util.hideKeyboard(PillImprintSearchAdapter.this.mActivity);
                MedscapeException unused = PillImprintSearchAdapter.this.mMedscapeException = new MedscapeException(MedscapeApplication.get().getResources().getString(R.string.internet_required));
                PillImprintSearchAdapter.this.mMedscapeException.showSnackBar(PillImprintSearchAdapter.this.mRootView, -2, MedscapeApplication.get().getResources().getString(R.string.retry), new View.OnClickListener() {
                    public void onClick(View view) {
                        List unused = AnonymousClass1.this.findImprints(context, str);
                        PillImprintSearchAdapter.this.mRetryListener.onAutoSearchRetry();
                    }
                });
                return new ArrayList();
            }
        };
    }

    public void dismissExceptionSnackbar() {
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
    }
}
