package com.google.firebase.inappmessaging.internal;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class TestDeviceHelper_Factory implements Factory<TestDeviceHelper> {
    private final Provider<SharedPreferencesUtils> sharedPreferencesUtilsProvider;

    public TestDeviceHelper_Factory(Provider<SharedPreferencesUtils> provider) {
        this.sharedPreferencesUtilsProvider = provider;
    }

    public TestDeviceHelper get() {
        return new TestDeviceHelper(this.sharedPreferencesUtilsProvider.get());
    }

    public static TestDeviceHelper_Factory create(Provider<SharedPreferencesUtils> provider) {
        return new TestDeviceHelper_Factory(provider);
    }

    public static TestDeviceHelper newInstance(SharedPreferencesUtils sharedPreferencesUtils) {
        return new TestDeviceHelper(sharedPreferencesUtils);
    }
}
