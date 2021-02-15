package mnetinternal;

import net.media.android.bidder.base.logging.Logger;

public abstract class ac implements Runnable {
    public abstract void a();

    public void run() {
        try {
            a();
        } catch (Exception e) {
            Logger.notify("##MNetRunnable", e.getMessage(), e);
        }
    }
}
