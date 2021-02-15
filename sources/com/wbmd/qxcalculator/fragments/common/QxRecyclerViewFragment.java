package com.wbmd.qxcalculator.fragments.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.fragments.common.QxMDFragment;
import com.wbmd.qxcalculator.util.Log;

public abstract class QxRecyclerViewFragment extends DataObserverFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, QxRecyclerViewAdapter.OnRecyclerViewRowItemAccessoryViewClickedListener, ActionMode.Callback, SwipeRefreshLayout.OnRefreshListener {
    private static final String KEY_EMPTY_TEXT_VIEW_TEXT = "KEY_EMPTY_TEXT_VIEW_TEXT";
    private static final String KEY_SCROLL_POSITION = "KEY_SCROLL_POSITION";
    protected QxRecyclerViewAdapter adapter;
    private TextView emptyTextView;
    private String emptyTextViewText;
    protected FloatingActionButton fabButton;
    /* access modifiers changed from: private */
    public int firstVisibleItemPosition;
    protected boolean isSwipeToRefreshing;
    protected LinearLayoutManager layoutManager;
    protected QxRecyclerView listView;
    protected ActionMode mActionMode;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ScrollPosition scrollPosition;
    /* access modifiers changed from: private */
    public int totalItemCount;
    /* access modifiers changed from: private */
    public int visibleItemCount;

    protected enum ScrollPosition {
        NOT_SET,
        BEGINNING,
        END
    }

    /* access modifiers changed from: protected */
    public void didScrollAwayFromEnd() {
    }

    /* access modifiers changed from: protected */
    public void didScrollNearEnd() {
    }

    /* access modifiers changed from: protected */
    public Drawable getDrawableForEmptyView() {
        return null;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onRecyclerViewRowItemAccessoryViewClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
    }

    /* access modifiers changed from: protected */
    public abstract void rebuildRowItems();

    /* access modifiers changed from: protected */
    public boolean shouldAutoBuildRowItems() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowFab() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean swipeToRefreshEnabled() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int thresholdToCallDidScrollNearEnd() {
        return 5;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.adapter == null) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
            this.adapter.setOnAccessoryViewClickListener(this);
        }
        if (bundle != null) {
            this.scrollPosition = ScrollPosition.values()[bundle.getInt(KEY_SCROLL_POSITION)];
            this.emptyTextViewText = bundle.getString(KEY_EMPTY_TEXT_VIEW_TEXT);
            return;
        }
        this.scrollPosition = ScrollPosition.BEGINNING;
        this.emptyTextViewText = getTextForEmptyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_SCROLL_POSITION, this.scrollPosition.ordinal());
        bundle.putString(KEY_EMPTY_TEXT_VIEW_TEXT, this.emptyTextViewText);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.fragment_qx_recycler_view;
    }

    /* access modifiers changed from: protected */
    public LinearLayoutManager getLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        return linearLayoutManager;
    }

    /* access modifiers changed from: protected */
    public String getTextForEmptyView() {
        return getString(R.string.empty_no_items);
    }

    public void updateEmptyTextViewText(String str) {
        this.emptyTextViewText = str;
        TextView textView = this.emptyTextView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutResourceId(), viewGroup, false);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefreshLayout);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
            this.mSwipeRefreshLayout.setEnabled(swipeToRefreshEnabled());
        }
        this.listView = (QxRecyclerView) inflate.findViewById(R.id.recycler_view);
        View findViewById = inflate.findViewById(R.id.empty_view);
        if (findViewById != null) {
            this.listView.setEmptyView(findViewById);
            View findViewById2 = findViewById.findViewById(R.id.empty_list_text_view);
            if (findViewById2 != null && (findViewById2 instanceof TextView)) {
                TextView textView = (TextView) findViewById2;
                this.emptyTextView = textView;
                textView.setText(this.emptyTextViewText);
            }
            View findViewById3 = findViewById.findViewById(R.id.empty_list_image_view);
            if (findViewById3 != null && (findViewById3 instanceof ImageView)) {
                Drawable drawableForEmptyView = getDrawableForEmptyView();
                if (drawableForEmptyView == null) {
                    findViewById3.setVisibility(8);
                } else {
                    findViewById3.setVisibility(0);
                    ((ImageView) findViewById3).setImageDrawable(drawableForEmptyView);
                }
            }
        }
        setContentView(this.listView);
        this.listView.setAdapter(this.adapter);
        LinearLayoutManager layoutManager2 = getLayoutManager();
        this.layoutManager = layoutManager2;
        this.listView.setLayoutManager(layoutManager2);
        this.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                if (i == 0 || i == 2) {
                    QxRecyclerViewFragment qxRecyclerViewFragment = QxRecyclerViewFragment.this;
                    int unused = qxRecyclerViewFragment.totalItemCount = qxRecyclerViewFragment.layoutManager.getItemCount();
                    if (QxRecyclerViewFragment.this.totalItemCount > 0) {
                        QxRecyclerViewFragment qxRecyclerViewFragment2 = QxRecyclerViewFragment.this;
                        int unused2 = qxRecyclerViewFragment2.visibleItemCount = qxRecyclerViewFragment2.listView.getChildCount();
                        QxRecyclerViewFragment qxRecyclerViewFragment3 = QxRecyclerViewFragment.this;
                        int unused3 = qxRecyclerViewFragment3.firstVisibleItemPosition = qxRecyclerViewFragment3.layoutManager.findFirstVisibleItemPosition();
                        if (QxRecyclerViewFragment.this.firstVisibleItemPosition + QxRecyclerViewFragment.this.visibleItemCount > QxRecyclerViewFragment.this.totalItemCount - QxRecyclerViewFragment.this.thresholdToCallDidScrollNearEnd()) {
                            if (QxRecyclerViewFragment.this.scrollPosition != ScrollPosition.END) {
                                QxRecyclerViewFragment.this.didScrollIntoEndZone();
                            }
                            QxRecyclerViewFragment.this.scrollPosition = ScrollPosition.END;
                            QxRecyclerViewFragment.this.didScrollNearEnd();
                        } else {
                            QxRecyclerViewFragment.this.scrollPosition = ScrollPosition.BEGINNING;
                            QxRecyclerViewFragment.this.didScrollAwayFromEnd();
                        }
                        if (i == 0 && QxRecyclerViewFragment.this.firstVisibleItemPosition + QxRecyclerViewFragment.this.visibleItemCount == QxRecyclerViewFragment.this.totalItemCount) {
                            QxRecyclerViewFragment.this.didReachEndOfScrollView();
                        }
                    }
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
        View findViewById4 = inflate.findViewById(R.id.error_view);
        if (findViewById4 != null) {
            setErrorView(findViewById4);
        }
        setLoadingView(inflate.findViewById(R.id.refreshing_view));
        setSavingOverlayView(inflate.findViewById(R.id.saving_overlay));
        this.fabButton = (FloatingActionButton) inflate.findViewById(R.id.fab_button);
        if (shouldShowFab()) {
            if (Build.VERSION.SDK_INT >= 17) {
                QxRecyclerView qxRecyclerView = this.listView;
                qxRecyclerView.setPadding(qxRecyclerView.getPaddingStart(), this.listView.getPaddingTop(), this.listView.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.recycler_view_padding_bottom_for_fab));
            } else {
                QxRecyclerView qxRecyclerView2 = this.listView;
                qxRecyclerView2.setPadding(qxRecyclerView2.getPaddingLeft(), this.listView.getPaddingTop(), this.listView.getPaddingRight(), getResources().getDimensionPixelSize(R.dimen.recycler_view_padding_bottom_for_fab));
            }
        }
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (!this.adapter.getHasBeenInitialized() && shouldAutoBuildRowItems()) {
            rebuildRowItems();
        }
    }

    public void onDetach() {
        super.onDetach();
    }

    /* access modifiers changed from: protected */
    public void setViewMode(QxMDFragment.ViewMode viewMode, boolean z, String str) {
        if (this.mSwipeRefreshLayout != null) {
            if (viewMode == QxMDFragment.ViewMode.LOADING) {
                this.mSwipeRefreshLayout.setEnabled(false);
            } else {
                this.mSwipeRefreshLayout.setEnabled(swipeToRefreshEnabled());
            }
        }
        super.setViewMode(viewMode, z, str);
        updateFabVisibility();
    }

    public void updateFabVisibility() {
        if (this.fabButton == null) {
            return;
        }
        if (this.viewMode != QxMDFragment.ViewMode.IDLE || !shouldShowFab()) {
            this.fabButton.setVisibility(8);
        } else {
            this.fabButton.setVisibility(0);
        }
    }

    public void onRefresh() {
        onSwipeToRefreshTriggered();
    }

    /* access modifiers changed from: protected */
    public void onSwipeToRefreshTriggered() {
        Log.d((Object) this, "onSwipeToRefreshTiggered");
    }

    /* access modifiers changed from: protected */
    public void updateActionMode() {
        ActionMode actionMode;
        boolean z = this.adapter.getEditModeSelectionCount() > 0;
        if (z && this.mActionMode == null) {
            this.adapter.setIsEditing(true);
            this.mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(this);
            if (Build.VERSION.SDK_INT >= 21) {
                getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color_editing));
            }
        } else if (!z && (actionMode = this.mActionMode) != null) {
            actionMode.finish();
        }
        ActionMode actionMode2 = this.mActionMode;
        if (actionMode2 != null) {
            actionMode2.setTitle((CharSequence) String.valueOf(this.adapter.getEditModeSelectionCount()) + " selected");
        }
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.adapter.setIsEditing(false);
        this.mActionMode = null;
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(((QxMDActivity) getActivity()).getStatusBarColor());
        }
    }

    /* access modifiers changed from: protected */
    public void showCannotEditItemToast() {
        Toast.makeText(getActivity(), R.string.cannot_edit_item_toast, 0).show();
    }

    /* access modifiers changed from: protected */
    public void didScrollIntoEndZone() {
        Log.d((Object) this, "didScrollIntoEndZone");
    }

    /* access modifiers changed from: protected */
    public void didReachEndOfScrollView() {
        Log.d((Object) this, "didReachEndOfScrollView");
    }

    public void resetRecyclerViewAdapter() {
        this.adapter.reset();
    }
}
