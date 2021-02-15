package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import java.util.LinkedList;
import java.util.List;

class AdapterWrapper extends BaseAdapter implements StickyListHeadersAdapter {
    private final Context mContext;
    private DataSetObserver mDataSetObserver;
    StickyListHeadersAdapter mDelegate;
    private Drawable mDivider;
    private int mDividerHeight;
    /* access modifiers changed from: private */
    public final List<View> mHeaderCache = new LinkedList();
    /* access modifiers changed from: private */
    public OnHeaderClickListener mOnHeaderClickListener;

    interface OnHeaderClickListener {
        void onHeaderClick(View view, int i, long j);
    }

    AdapterWrapper(Context context, StickyListHeadersAdapter stickyListHeadersAdapter) {
        AnonymousClass1 r0 = new DataSetObserver() {
            public void onInvalidated() {
                AdapterWrapper.this.mHeaderCache.clear();
                AdapterWrapper.super.notifyDataSetInvalidated();
            }

            public void onChanged() {
                AdapterWrapper.super.notifyDataSetChanged();
            }
        };
        this.mDataSetObserver = r0;
        this.mContext = context;
        this.mDelegate = stickyListHeadersAdapter;
        stickyListHeadersAdapter.registerDataSetObserver(r0);
    }

    /* access modifiers changed from: package-private */
    public void setDivider(Drawable drawable, int i) {
        this.mDivider = drawable;
        this.mDividerHeight = i;
        notifyDataSetChanged();
    }

    public boolean areAllItemsEnabled() {
        return this.mDelegate.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.mDelegate.isEnabled(i);
    }

    public int getCount() {
        return this.mDelegate.getCount();
    }

    public Object getItem(int i) {
        return this.mDelegate.getItem(i);
    }

    public long getItemId(int i) {
        return this.mDelegate.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }

    public int getItemViewType(int i) {
        return this.mDelegate.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mDelegate.isEmpty();
    }

    private void recycleHeaderIfExists(WrapperView wrapperView) {
        View view = wrapperView.mHeader;
        if (view != null) {
            view.setVisibility(0);
            this.mHeaderCache.add(view);
        }
    }

    private View configureHeader(WrapperView wrapperView, final int i) {
        View headerView = this.mDelegate.getHeaderView(i, wrapperView.mHeader == null ? popHeader() : wrapperView.mHeader, wrapperView);
        if (headerView != null) {
            headerView.setClickable(true);
            headerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AdapterWrapper.this.mOnHeaderClickListener != null) {
                        AdapterWrapper.this.mOnHeaderClickListener.onHeaderClick(view, i, AdapterWrapper.this.mDelegate.getHeaderId(i));
                    }
                }
            });
            return headerView;
        }
        throw new NullPointerException("Header view must not be null.");
    }

    private View popHeader() {
        if (this.mHeaderCache.size() > 0) {
            return this.mHeaderCache.remove(0);
        }
        return null;
    }

    private boolean previousPositionHasSameHeader(int i) {
        if (i == 0 || this.mDelegate.getHeaderId(i) != this.mDelegate.getHeaderId(i - 1)) {
            return false;
        }
        return true;
    }

    public WrapperView getView(int i, View view, ViewGroup viewGroup) {
        WrapperView wrapperView = view == null ? new WrapperView(this.mContext) : (WrapperView) view;
        View view2 = this.mDelegate.getView(i, wrapperView.mItem, viewGroup);
        View view3 = null;
        if (previousPositionHasSameHeader(i)) {
            recycleHeaderIfExists(wrapperView);
        } else {
            view3 = configureHeader(wrapperView, i);
        }
        boolean z = view2 instanceof Checkable;
        if (z && !(wrapperView instanceof CheckableWrapperView)) {
            wrapperView = new CheckableWrapperView(this.mContext);
        } else if (!z && (wrapperView instanceof CheckableWrapperView)) {
            wrapperView = new WrapperView(this.mContext);
        }
        wrapperView.update(view2, view3, this.mDivider, this.mDividerHeight);
        return wrapperView;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mOnHeaderClickListener = onHeaderClickListener;
    }

    public boolean equals(Object obj) {
        return this.mDelegate.equals(obj);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return ((BaseAdapter) this.mDelegate).getDropDownView(i, view, viewGroup);
    }

    public int hashCode() {
        return this.mDelegate.hashCode();
    }

    public void notifyDataSetChanged() {
        ((BaseAdapter) this.mDelegate).notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated() {
        ((BaseAdapter) this.mDelegate).notifyDataSetInvalidated();
    }

    public String toString() {
        return this.mDelegate.toString();
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        return this.mDelegate.getHeaderView(i, view, viewGroup);
    }

    public long getHeaderId(int i) {
        return this.mDelegate.getHeaderId(i);
    }
}
