package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Utils;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    PicassoExecutorService() {
        super(3, 3, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new Utils.PicassoThreadFactory());
    }

    /* access modifiers changed from: package-private */
    public void adjustThreadCount(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            setThreadCount(3);
            return;
        }
        int type = networkInfo.getType();
        if (type == 0) {
            int subtype = networkInfo.getSubtype();
            switch (subtype) {
                case 1:
                case 2:
                    setThreadCount(1);
                    return;
                case 3:
                case 4:
                case 5:
                case 6:
                    break;
                default:
                    switch (subtype) {
                        case 12:
                            break;
                        case 13:
                        case 14:
                        case 15:
                            setThreadCount(3);
                            return;
                        default:
                            setThreadCount(3);
                            return;
                    }
            }
            setThreadCount(2);
        } else if (type == 1 || type == 6 || type == 9) {
            setThreadCount(4);
        } else {
            setThreadCount(3);
        }
    }

    private void setThreadCount(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }

    public Future<?> submit(Runnable runnable) {
        PicassoFutureTask picassoFutureTask = new PicassoFutureTask((BitmapHunter) runnable);
        execute(picassoFutureTask);
        return picassoFutureTask;
    }

    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        PicassoFutureTask(BitmapHunter bitmapHunter) {
            super(bitmapHunter, (Object) null);
            this.hunter = bitmapHunter;
        }

        public int compareTo(PicassoFutureTask picassoFutureTask) {
            Picasso.Priority priority = this.hunter.getPriority();
            Picasso.Priority priority2 = picassoFutureTask.hunter.getPriority();
            return priority == priority2 ? this.hunter.sequence - picassoFutureTask.hunter.sequence : priority2.ordinal() - priority.ordinal();
        }
    }
}
