package com.medscape.android.search;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedItemClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemsAdapter;
import com.medscape.android.activity.search.ReferenceItemClickListener;
import com.medscape.android.activity.search.SearchResultsListAdapter;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.consult.viewholders.ConsultDummyViewHolder;

public class SearchResultsDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_DEFAULT = 4;
    private static final int VIEW_TYPE_EXTERNAL_DRIVER = 2;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_REGULAR = 1;
    private Context mContext;
    private int mCurrentTab = 0;
    RecentlyViewedItemClickListener mRecentlyViewedItemClickListener;
    /* access modifiers changed from: private */
    public RecentlyViewedItemsAdapter mRecentlyViewedItemsAdapter;
    ReferenceItemClickListener mReferenceItemClickListener;
    /* access modifiers changed from: private */
    public String mSearchQuery;
    /* access modifiers changed from: private */
    public SearchResultsListAdapter mSearchResultsListAdapter;

    public SearchResultsDataAdapter(Context context, RecentlyViewedItemClickListener recentlyViewedItemClickListener, ReferenceItemClickListener referenceItemClickListener) {
        this.mContext = context;
        this.mRecentlyViewedItemClickListener = recentlyViewedItemClickListener;
        this.mReferenceItemClickListener = referenceItemClickListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new RecentlyViewedListHeaderViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.clear_recently_viewed, viewGroup, false));
        }
        if (i == 1) {
            return new SearchListItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.auto_complete_item, viewGroup, false));
        }
        return new ConsultDummyViewHolder(i == 2 ? LayoutInflater.from(this.mContext).inflate(R.layout.search_external_driver_item, viewGroup, false) : null);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        try {
            if (this.mRecentlyViewedItemsAdapter != null) {
                this.mRecentlyViewedItemsAdapter.getCursor().moveToPosition(i);
                if (viewHolder instanceof RecentlyViewedListHeaderViewHolder) {
                    this.mRecentlyViewedItemsAdapter.bindHeaderItem(viewHolder.itemView);
                } else if (viewHolder instanceof SearchListItemViewHolder) {
                    this.mRecentlyViewedItemsAdapter.bindRegularItem(viewHolder.itemView, this.mRecentlyViewedItemsAdapter.getCursor());
                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            view.postOnAnimationDelayed(new Runnable() {
                                public void run() {
                                    SearchResultsDataAdapter.this.mRecentlyViewedItemClickListener.onItemClicked(SearchResultsDataAdapter.this.mRecentlyViewedItemsAdapter, view, i);
                                }
                            }, 400);
                        } else {
                            SearchResultsDataAdapter.this.mRecentlyViewedItemClickListener.onItemClicked(SearchResultsDataAdapter.this.mRecentlyViewedItemsAdapter, view, i);
                        }
                    }
                });
            } else if (this.mSearchResultsListAdapter != null) {
                if (((CRData) this.mSearchResultsListAdapter.getItem(i)).isExternalSearchDriver()) {
                    TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.external_driver_text);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mContext.getString(R.string.see_all_results));
                    spannableStringBuilder.append(" \"" + this.mSearchQuery + "\" ");
                    Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.ic_action_next_item_blue);
                    drawable.setBounds(0, 0, textView.getLineHeight(), textView.getLineHeight());
                    spannableStringBuilder.setSpan(new ImageSpan(drawable), spannableStringBuilder.length() + -1, spannableStringBuilder.length(), 33);
                    textView.setText(spannableStringBuilder);
                } else {
                    this.mSearchResultsListAdapter.getView(i, viewHolder.itemView, (ViewGroup) null);
                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            view.postOnAnimationDelayed(new Runnable() {
                                public void run() {
                                    SearchResultsDataAdapter.this.mReferenceItemClickListener.onItemClicked(SearchResultsDataAdapter.this.mSearchResultsListAdapter, view, i, SearchResultsDataAdapter.this.mSearchQuery);
                                }
                            }, 400);
                        } else {
                            SearchResultsDataAdapter.this.mReferenceItemClickListener.onItemClicked(SearchResultsDataAdapter.this.mSearchResultsListAdapter, view, i, SearchResultsDataAdapter.this.mSearchQuery);
                        }
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    public void refreshCurrentTab(int i) {
        this.mCurrentTab = i;
    }

    public int getItemCount() {
        RecentlyViewedItemsAdapter recentlyViewedItemsAdapter = this.mRecentlyViewedItemsAdapter;
        if (recentlyViewedItemsAdapter != null) {
            return recentlyViewedItemsAdapter.getCount();
        }
        SearchResultsListAdapter searchResultsListAdapter = this.mSearchResultsListAdapter;
        if (searchResultsListAdapter != null) {
            return searchResultsListAdapter.getCount();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        RecentlyViewedItemsAdapter recentlyViewedItemsAdapter = this.mRecentlyViewedItemsAdapter;
        int itemViewType = recentlyViewedItemsAdapter != null ? recentlyViewedItemsAdapter.getItemViewType(i) : 4;
        SearchResultsListAdapter searchResultsListAdapter = this.mSearchResultsListAdapter;
        if (searchResultsListAdapter != null) {
            return searchResultsListAdapter.getCount() == i + 1 ? 2 : 1;
        }
        return itemViewType;
    }

    public void setRecentlyViewedItemsAdapter(RecentlyViewedItemsAdapter recentlyViewedItemsAdapter) {
        this.mSearchResultsListAdapter = null;
        this.mRecentlyViewedItemsAdapter = recentlyViewedItemsAdapter;
        recentlyViewedItemsAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                SearchResultsDataAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void setSearchResultsListAdapter(SearchResultsListAdapter searchResultsListAdapter) {
        this.mRecentlyViewedItemsAdapter = null;
        this.mSearchResultsListAdapter = searchResultsListAdapter;
    }

    public SearchResultsListAdapter getSearchResultsListAdapter() {
        return this.mSearchResultsListAdapter;
    }

    public void setmSearchQuery(String str) {
        this.mSearchQuery = str;
    }
}
