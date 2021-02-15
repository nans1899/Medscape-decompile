package androidx.paging;

import androidx.paging.PositionalDataSource;
import java.util.ArrayList;
import java.util.List;

class ListDataSource<T> extends PositionalDataSource<T> {
    private final List<T> mList;

    public ListDataSource(List<T> list) {
        this.mList = new ArrayList(list);
    }

    public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        int size = this.mList.size();
        int computeInitialLoadPosition = computeInitialLoadPosition(loadInitialParams, size);
        loadInitialCallback.onResult(this.mList.subList(computeInitialLoadPosition, computeInitialLoadSize(loadInitialParams, computeInitialLoadPosition, size) + computeInitialLoadPosition), computeInitialLoadPosition, size);
    }

    public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(this.mList.subList(loadRangeParams.startPosition, loadRangeParams.startPosition + loadRangeParams.loadSize));
    }
}
