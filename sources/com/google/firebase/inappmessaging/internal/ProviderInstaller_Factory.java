package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProviderInstaller_Factory implements Factory<ProviderInstaller> {
    private final Provider<Application> applicationProvider;

    public ProviderInstaller_Factory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public ProviderInstaller get() {
        return new ProviderInstaller(this.applicationProvider.get());
    }

    public static ProviderInstaller_Factory create(Provider<Application> provider) {
        return new ProviderInstaller_Factory(provider);
    }

    public static ProviderInstaller newInstance(Application application) {
        return new ProviderInstaller(application);
    }
}
