package com.wbmd.qxcalculator.fragments.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public abstract class RefreshingFragment extends Fragment {
    public static final String KEY_BROADCAST_NOTIFY_DATA_CHANGED = "KEY_BROADCAST_NOTIFY_DATA_CHANGED";
    public static final String KEY_BROADCAST_REBUILD_CALC_LISTS = "KEY_BROADCAST_REBUILD_CALC_LISTS";
    public static final String KEY_BROADCAST_REFRESH_COMPLETE = "KEY_BROADCAST_REFRESH_COMPLETE";
    public static final String KEY_BROADCAST_REFRESH_SUCCESS_STATUS = "HomeActivity.KEY_BROADCAST_REFRESH_SUCCESS_STATUS";
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("BROADCAST", "receive " + intent.getAction());
            if (intent.getAction().equals(RefreshingFragment.KEY_BROADCAST_REFRESH_COMPLETE)) {
                RefreshingFragment.this.appRefreshComplete(intent.getBooleanExtra(RefreshingFragment.KEY_BROADCAST_REFRESH_SUCCESS_STATUS, false));
            } else if (intent.getAction().equals(RefreshingFragment.KEY_BROADCAST_NOTIFY_DATA_CHANGED)) {
                RefreshingFragment.this.listDataChanged();
            } else if (intent.getAction().equals(RefreshingFragment.KEY_BROADCAST_REBUILD_CALC_LISTS)) {
                RefreshingFragment.this.rebuildCalculatorList();
            }
        }
    };
    protected ContentRefreshListener contentRefreshListener;

    public interface ContentRefreshListener {
        boolean isRefreshing();
    }

    public abstract void appRefreshComplete(boolean z);

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return null;
    }

    public abstract void listDataChanged();

    public abstract void rebuildCalculatorList();

    public IntentFilter getBroadcastIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(KEY_BROADCAST_REFRESH_COMPLETE);
        intentFilter.addAction(KEY_BROADCAST_REBUILD_CALC_LISTS);
        intentFilter.addAction(KEY_BROADCAST_NOTIFY_DATA_CHANGED);
        return intentFilter;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.contentRefreshListener = (ContentRefreshListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement ContentRefreshListener");
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", getClass().getCanonicalName() + " onCreate");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, getBroadcastIntentFilter());
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("API", getClass().getCanonicalName() + " onDestroy");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
    }

    public void tabBecameVisible() {
        if (getAnalyticsScreenName() != null) {
            Log.d("HomeTab", "tab became visible: " + getAnalyticsScreenName());
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            tabBecameVisible();
        }
    }

    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            tabBecameVisible();
        }
    }
}
