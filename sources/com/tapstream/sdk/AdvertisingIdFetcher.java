package com.tapstream.sdk;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.Callable;

public class AdvertisingIdFetcher implements Callable<AdvertisingID> {
    private final Application app;

    public AdvertisingIdFetcher(Application application) {
        this.app = application;
    }

    public AdvertisingID call() throws Exception {
        Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
        Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
        Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(cls, new Object[]{this.app});
        return new AdvertisingID((String) cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), ((Boolean) cls2.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue());
    }
}
