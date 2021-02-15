package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class HandlerExecutor implements Executor {
    private final Handler mHandler;

    public HandlerExecutor(Handler handler) {
        this.mHandler = (Handler) Preconditions.checkNotNull(handler);
    }

    public void execute(Runnable runnable) {
        if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(runnable))) {
            throw new RejectedExecutionException(this.mHandler + " is shutting down");
        }
    }
}
