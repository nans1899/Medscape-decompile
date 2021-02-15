package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.SharedPreferencesUtils;
import com.google.firebase.inappmessaging.internal.TestDeviceHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApiClientModule_ProvidesTestDeviceHelperFactory implements Factory<TestDeviceHelper> {
    private final ApiClientModule module;
    private final Provider<SharedPreferencesUtils> sharedPreferencesUtilsProvider;

    public ApiClientModule_ProvidesTestDeviceHelperFactory(ApiClientModule apiClientModule, Provider<SharedPreferencesUtils> provider) {
        this.module = apiClientModule;
        this.sharedPreferencesUtilsProvider = provider;
    }

    public TestDeviceHelper get() {
        return providesTestDeviceHelper(this.module, this.sharedPreferencesUtilsProvider.get());
    }

    public static ApiClientModule_ProvidesTestDeviceHelperFactory create(ApiClientModule apiClientModule, Provider<SharedPreferencesUtils> provider) {
        return new ApiClientModule_ProvidesTestDeviceHelperFactory(apiClientModule, provider);
    }

    public static TestDeviceHelper providesTestDeviceHelper(ApiClientModule apiClientModule, SharedPreferencesUtils sharedPreferencesUtils) {
        return (TestDeviceHelper) Preconditions.checkNotNull(apiClientModule.providesTestDeviceHelper(sharedPreferencesUtils), "Cannot return null from a non-@Nullable @Provides method");
    }
}
