package mnetinternal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class bz {
    private CountDownLatch a = new CountDownLatch(1);

    public void a() {
        bi.a("##Locker", "unLock");
        this.a.countDown();
    }

    public void b() {
        try {
            bi.a("##Locker", "lock");
            this.a.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            bi.a("##Locker", (Throwable) e);
        }
    }
}
