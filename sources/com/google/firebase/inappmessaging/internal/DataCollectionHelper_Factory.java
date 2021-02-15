package com.google.firebase.inappmessaging.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class DataCollectionHelper_Factory implements Factory<DataCollectionHelper> {
    private final Provider<FirebaseApp> firebaseAppProvider;
    private final Provider<Subscriber> firebaseEventsSubscriberProvider;
    private final Provider<FirebaseInstanceId> firebaseInstanceIdProvider;
    private final Provider<SharedPreferencesUtils> sharedPreferencesUtilsProvider;

    public DataCollectionHelper_Factory(Provider<FirebaseApp> provider, Provider<SharedPreferencesUtils> provider2, Provider<FirebaseInstanceId> provider3, Provider<Subscriber> provider4) {
        this.firebaseAppProvider = provider;
        this.sharedPreferencesUtilsProvider = provider2;
        this.firebaseInstanceIdProvider = provider3;
        this.firebaseEventsSubscriberProvider = provider4;
    }

    public DataCollectionHelper get() {
        return new DataCollectionHelper(this.firebaseAppProvider.get(), this.sharedPreferencesUtilsProvider.get(), this.firebaseInstanceIdProvider.get(), this.firebaseEventsSubscriberProvider.get());
    }

    public static DataCollectionHelper_Factory create(Provider<FirebaseApp> provider, Provider<SharedPreferencesUtils> provider2, Provider<FirebaseInstanceId> provider3, Provider<Subscriber> provider4) {
        return new DataCollectionHelper_Factory(provider, provider2, provider3, provider4);
    }

    public static DataCollectionHelper newInstance(FirebaseApp firebaseApp, SharedPreferencesUtils sharedPreferencesUtils, FirebaseInstanceId firebaseInstanceId, Subscriber subscriber) {
        return new DataCollectionHelper(firebaseApp, sharedPreferencesUtils, firebaseInstanceId, subscriber);
    }
}
