package androidx.paging;

import androidx.paging.AsyncPagedListDiffer;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public abstract class PagedListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    final AsyncPagedListDiffer<T> mDiffer;
    private final AsyncPagedListDiffer.PagedListListener<T> mListener = new AsyncPagedListDiffer.PagedListListener<T>() {
        public void onCurrentListChanged(PagedList<T> pagedList, PagedList<T> pagedList2) {
            PagedListAdapter.this.onCurrentListChanged(pagedList2);
            PagedListAdapter.this.onCurrentListChanged(pagedList, pagedList2);
        }
    };

    @Deprecated
    public void onCurrentListChanged(PagedList<T> pagedList) {
    }

    public void onCurrentListChanged(PagedList<T> pagedList, PagedList<T> pagedList2) {
    }

    protected PagedListAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>((RecyclerView.Adapter) this, itemCallback);
        this.mDiffer = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener(this.mListener);
    }

    protected PagedListAdapter(AsyncDifferConfig<T> asyncDifferConfig) {
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.mDiffer = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener(this.mListener);
    }

    public void submitList(PagedList<T> pagedList) {
        this.mDiffer.submitList(pagedList);
    }

    public void submitList(PagedList<T> pagedList, Runnable runnable) {
        this.mDiffer.submitList(pagedList, runnable);
    }

    /* access modifiers changed from: protected */
    public T getItem(int i) {
        return this.mDiffer.getItem(i);
    }

    public int getItemCount() {
        return this.mDiffer.getItemCount();
    }

    public PagedList<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }
}
