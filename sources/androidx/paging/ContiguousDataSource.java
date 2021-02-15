package androidx.paging;

import androidx.paging.PageResult;
import java.util.concurrent.Executor;

abstract class ContiguousDataSource<Key, Value> extends DataSource<Key, Value> {
    /* access modifiers changed from: package-private */
    public abstract void dispatchLoadAfter(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver);

    /* access modifiers changed from: package-private */
    public abstract void dispatchLoadBefore(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver);

    /* access modifiers changed from: package-private */
    public abstract void dispatchLoadInitial(Key key, int i, int i2, boolean z, Executor executor, PageResult.Receiver<Value> receiver);

    /* access modifiers changed from: package-private */
    public abstract Key getKey(int i, Value value);

    /* access modifiers changed from: package-private */
    public boolean isContiguous() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean supportsPageDropping() {
        return true;
    }

    ContiguousDataSource() {
    }
}
