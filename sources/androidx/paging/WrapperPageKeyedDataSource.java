package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import java.util.List;

class WrapperPageKeyedDataSource<K, A, B> extends PageKeyedDataSource<K, B> {
    final Function<List<A>, List<B>> mListFunction;
    private final PageKeyedDataSource<K, A> mSource;

    WrapperPageKeyedDataSource(PageKeyedDataSource<K, A> pageKeyedDataSource, Function<List<A>, List<B>> function) {
        this.mSource = pageKeyedDataSource;
        this.mListFunction = function;
    }

    public void addInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
        this.mSource.addInvalidatedCallback(invalidatedCallback);
    }

    public void removeInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
        this.mSource.removeInvalidatedCallback(invalidatedCallback);
    }

    public void invalidate() {
        this.mSource.invalidate();
    }

    public boolean isInvalid() {
        return this.mSource.isInvalid();
    }

    public void loadInitial(PageKeyedDataSource.LoadInitialParams<K> loadInitialParams, final PageKeyedDataSource.LoadInitialCallback<K, B> loadInitialCallback) {
        this.mSource.loadInitial(loadInitialParams, new PageKeyedDataSource.LoadInitialCallback<K, A>() {
            public void onResult(List<A> list, int i, int i2, K k, K k2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), i, i2, k, k2);
            }

            public void onResult(List<A> list, K k, K k2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k, k2);
            }
        });
    }

    public void loadBefore(PageKeyedDataSource.LoadParams<K> loadParams, final PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        this.mSource.loadBefore(loadParams, new PageKeyedDataSource.LoadCallback<K, A>() {
            public void onResult(List<A> list, K k) {
                loadCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k);
            }
        });
    }

    public void loadAfter(PageKeyedDataSource.LoadParams<K> loadParams, final PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        this.mSource.loadAfter(loadParams, new PageKeyedDataSource.LoadCallback<K, A>() {
            public void onResult(List<A> list, K k) {
                loadCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k);
            }
        });
    }
}
