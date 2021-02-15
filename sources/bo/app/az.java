package bo.app;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class az extends ThreadPoolExecutor {
    private static final TimeUnit a = TimeUnit.MILLISECONDS;

    public az(ThreadFactory threadFactory) {
        super(1, 1, 0, a, ei.d(), threadFactory);
        setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
