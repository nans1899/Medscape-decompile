package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;

class WrapperPositionalDataSource<A, B> extends PositionalDataSource<B> {
    final Function<List<A>, List<B>> mListFunction;
    private final PositionalDataSource<A> mSource;

    WrapperPositionalDataSource(PositionalDataSource<A> positionalDataSource, Function<List<A>, List<B>> function) {
        this.mSource = positionalDataSource;
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

    public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, final PositionalDataSource.LoadInitialCallback<B> loadInitialCallback) {
        this.mSource.loadInitial(loadInitialParams, new PositionalDataSource.LoadInitialCallback<A>() {
            public void onResult(List<A> list, int i, int i2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list), i, i2);
            }

            public void onResult(List<A> list, int i) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list), i);
            }
        });
    }

    public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, final PositionalDataSource.LoadRangeCallback<B> loadRangeCallback) {
        this.mSource.loadRange(loadRangeParams, new PositionalDataSource.LoadRangeCallback<A>() {
            public void onResult(List<A> list) {
                loadRangeCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list));
            }
        });
    }
}
