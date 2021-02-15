package bo.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ei {
    private static final int a;
    private static final int b;
    private static final int c = ((a * 2) + 1);

    public static long c() {
        return 1;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        a = availableProcessors;
        b = Math.max(2, Math.min(availableProcessors - 1, 4));
    }

    public static int a() {
        return b;
    }

    public static int b() {
        return c;
    }

    public static BlockingQueue<Runnable> d() {
        return new LinkedBlockingQueue(128);
    }
}
