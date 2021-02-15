package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.events.Subscriber;
import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.SharedPreferencesUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApiClientModule_ProvidesDataCollectionHelperFactory implements Factory<DataCollectionHelper> {
    private final Provider<Subscriber> firebaseEventSubscriberProvider;
    private final ApiClientModule module;
    private final Provider<SharedPreferencesUtils> sharedPreferencesUtilsProvider;

    public ApiClientModule_ProvidesDataCollectionHelperFactory(ApiClientModule apiClientModule, Provider<SharedPreferencesUtils> provider, Provider<Subscriber> provider2) {
        this.module = apiClientModule;
        this.sharedPreferencesUtilsProvider = provider;
        this.firebaseEventSubscriberProvider = provider2;
    }

    public DataCollectionHelper get() {
        return providesDataCollectionHelper(this.module, this.sharedPreferencesUtilsProvider.get(), this.firebaseEventSubscriberProvider.get());
    }

    public static ApiClientModule_ProvidesDataCollectionHelperFactory create(ApiClientModule apiClientModule, Provider<SharedPreferencesUtils> provider, Provider<Subscriber> provider2) {
        return new ApiClientModule_ProvidesDataCollectionHelperFactory(apiClientModule, provider, provider2);
    }

    public static DataCollectionHelper providesDataCollectionHelper(ApiClientModule apiClientModule, SharedPreferencesUtils sharedPreferencesUtils, Subscriber subscriber) {
        return (DataCollectionHelper) Preconditions.checkNotNull(apiClientModule.providesDataCollectionHelper(sharedPreferencesUtils, subscriber), "Cannot return null from a non-@Nullable @Provides method");
    }
}
