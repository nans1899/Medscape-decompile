package net.media.android.bidder.base.common;

import android.app.Application;
import mnetinternal.bg;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.HostAppContext;

public final class ViewContextProvider {
    private static ViewContextProvider sInstance;
    private boolean mInit = true;

    private ViewContextProvider(Application application) {
        bg.a(application);
        bg.a(false);
    }

    public static void init(Application application) {
        if (sInstance == null) {
            sInstance = new ViewContextProvider(application);
        }
    }

    private HostAppContext viewContext() {
        checkInit();
        return new HostAppContext(bg.c(), bg.b());
    }

    private String viewContent() {
        checkInit();
        return bg.a();
    }

    public static String getViewContent() {
        return sInstance.viewContent();
    }

    public static HostAppContext getViewContext() {
        return sInstance.viewContext();
    }

    private void checkInit() {
        if (!this.mInit) {
            Logger.debug("##ViewContextProvider##", "view context provider not init");
        }
    }
}
