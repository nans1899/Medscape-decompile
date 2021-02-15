package com.medscape.android.activity.search;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cursoradapter.widget.CursorAdapter;
import com.medscape.android.R;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.ViewHelper;

public class RecentlyViewedItemsAdapter extends CursorAdapter {
    private static final int RESET_MODE_TIMEOUT = 5000;
    private static final int VIEW_TYPES_COUNT = 2;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_REGULAR = 1;
    private final Context ctx;
    private final LayoutInflater inflater;
    /* access modifiers changed from: private */
    public boolean isClearRecentlyShown = false;
    /* access modifiers changed from: private */
    public RecentlyViewedClearClickListener mRecentlyViewedClearClickListener = null;
    private final Handler messageQueue = new Handler();
    private final Runnable resetRecentlyViewedClearModeRunnable = new Runnable() {
        public void run() {
            RecentlyViewedItemsAdapter.this.notifyDataSetChanged();
        }
    };

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public RecentlyViewedItemsAdapter(Context context, Cursor cursor, RecentlyViewedClearClickListener recentlyViewedClearClickListener) {
        super(context, cursor, 0);
        this.ctx = context;
        this.mRecentlyViewedClearClickListener = recentlyViewedClearClickListener;
        this.inflater = LayoutInflater.from(context);
        this.isClearRecentlyShown = false;
    }

    public RecentlyViewedItemsAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.ctx = context;
        this.inflater = LayoutInflater.from(context);
        this.isClearRecentlyShown = false;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(cursor.getPosition());
        if (itemViewType == 0) {
            return this.inflater.inflate(R.layout.clear_recently_viewed, viewGroup, false);
        }
        if (itemViewType == 1) {
            return this.inflater.inflate(R.layout.auto_complete_item, viewGroup, false);
        }
        throw new IllegalArgumentException("unknown view type: " + itemViewType);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        int itemViewType = getItemViewType(cursor.getPosition());
        if (itemViewType == 0) {
            bindHeaderItem(view);
        } else if (itemViewType == 1) {
            bindRegularItem(view, cursor);
        } else {
            throw new IllegalArgumentException("unknown view type: " + itemViewType);
        }
    }

    public void bindHeaderItem(View view) {
        final View findById = ViewHelper.findById(view, (int) R.id.cancelRecentlyViewed);
        final View findById2 = ViewHelper.findById(view, (int) R.id.clearRecentlyViewed);
        if (this.isClearRecentlyShown) {
            findById2.setVisibility(0);
            findById.setVisibility(8);
        } else {
            findById2.setVisibility(8);
            findById.setVisibility(0);
        }
        findById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                findById2.setVisibility(0);
                findById.setVisibility(8);
                boolean unused = RecentlyViewedItemsAdapter.this.isClearRecentlyShown = true;
            }
        });
        findById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                findById2.setVisibility(8);
                RecentlyViewedItemsAdapter.this.clearRecentlyViewedItems();
                RecentlyViewedItemsAdapter.this.notifyDataSetChanged();
                boolean unused = RecentlyViewedItemsAdapter.this.isClearRecentlyShown = false;
                if (RecentlyViewedItemsAdapter.this.mRecentlyViewedClearClickListener != null) {
                    RecentlyViewedItemsAdapter.this.mRecentlyViewedClearClickListener.onRecentlyViewedClearClick();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void clearRecentlyViewedItems() {
        RecentlyViewedSuggestionHelper.clearHistoryForSearchMode(this.ctx, getSearchMode());
        changeCursor((Cursor) null);
        this.messageQueue.removeCallbacks(this.resetRecentlyViewedClearModeRunnable);
    }

    private SearchMode getSearchMode() {
        return ((SearchModeProvider) this.ctx).getCurrentSearchMode();
    }

    public void bindRegularItem(View view, Cursor cursor) {
        ImageView imageView = (ImageView) ViewHelper.findById(view, (int) R.id.icon);
        Bundle decodeMeta = RecentlyViewedSuggestionHelper.decodeMeta(this.ctx, cursor.getString(cursor.getColumnIndex("suggest_text_2")));
        String string = decodeMeta.getString("type");
        ((TextView) ViewHelper.findById(view, (int) R.id.list_item)).setText(cursor.getString(cursor.getColumnIndex("suggest_text_1")));
        int resourceId = getResourceId(string, decodeMeta);
        if (resourceId > 0 && imageView != null) {
            imageView.setVisibility(0);
            imageView.setImageResource(resourceId);
        } else if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    private int getResourceId(String str, Bundle bundle) {
        if (SearchHelper.TYPE_DRUG.equals(str)) {
            return R.drawable.drugs3x;
        }
        if (SearchHelper.TYPE_CALCULATOR.equals(str)) {
            return R.drawable.calculator;
        }
        if ("T".equals(str)) {
            return ((ClinicalReferenceArticle) bundle.getSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE)).getType() == 0 ? R.drawable.conditions3x : R.drawable.scissors_icon;
        }
        boolean equals = "NR".equals(str);
        return 0;
    }

    public int getItemViewType(int i) {
        return getItemViewType((Cursor) getItem(i), i);
    }

    public boolean isEnabled(int i) {
        return getItemId(i) != -1;
    }

    private int getItemViewType(Cursor cursor, int i) {
        return (!"HEADER".equalsIgnoreCase(cursor.getString(cursor.getColumnIndex("suggest_text_1"))) || getItemId(i) != -1) ? 1 : 0;
    }
}
