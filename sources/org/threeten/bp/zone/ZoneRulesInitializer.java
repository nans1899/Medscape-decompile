package org.threeten.bp.zone;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ZoneRulesInitializer {
    public static final ZoneRulesInitializer DO_NOTHING = new DoNothingZoneRulesInitializer();
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);
    private static final AtomicReference<ZoneRulesInitializer> INITIALIZER = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public abstract void initializeProviders();

    public static void setInitializer(ZoneRulesInitializer zoneRulesInitializer) {
        if (INITIALIZED.get()) {
            throw new IllegalStateException("Already initialized");
        } else if (!INITIALIZER.compareAndSet((Object) null, zoneRulesInitializer)) {
            throw new IllegalStateException("Initializer was already set, possibly with a default during initialization");
        }
    }

    static void initialize() {
        if (!INITIALIZED.getAndSet(true)) {
            INITIALIZER.compareAndSet((Object) null, new ServiceLoaderZoneRulesInitializer());
            INITIALIZER.get().initializeProviders();
            return;
        }
        throw new IllegalStateException("Already initialized");
    }

    static class DoNothingZoneRulesInitializer extends ZoneRulesInitializer {
        /* access modifiers changed from: protected */
        public void initializeProviders() {
        }

        DoNothingZoneRulesInitializer() {
        }
    }

    static class ServiceLoaderZoneRulesInitializer extends ZoneRulesInitializer {
        ServiceLoaderZoneRulesInitializer() {
        }

        /* access modifiers changed from: protected */
        public void initializeProviders() {
            Iterator<S> it = ServiceLoader.load(ZoneRulesProvider.class, ZoneRulesProvider.class.getClassLoader()).iterator();
            while (it.hasNext()) {
                try {
                    ZoneRulesProvider.registerProvider((ZoneRulesProvider) it.next());
                } catch (ServiceConfigurationError e) {
                    if (!(e.getCause() instanceof SecurityException)) {
                        throw e;
                    }
                }
            }
        }
    }
}
