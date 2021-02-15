package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.internal.ApiClient;
import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.GrpcClient;
import com.google.firebase.inappmessaging.internal.ProviderInstaller;
import com.google.firebase.inappmessaging.internal.SharedPreferencesUtils;
import com.google.firebase.inappmessaging.internal.TestDeviceHelper;
import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ApiClientModule {
    private final Clock clock;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstanceId firebaseInstanceId;

    public ApiClientModule(FirebaseApp firebaseApp2, FirebaseInstanceId firebaseInstanceId2, Clock clock2) {
        this.firebaseApp = firebaseApp2;
        this.firebaseInstanceId = firebaseInstanceId2;
        this.clock = clock2;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public FirebaseInstanceId providesFirebaseInstanceId() {
        return this.firebaseInstanceId;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public FirebaseApp providesFirebaseApp() {
        return this.firebaseApp;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public SharedPreferencesUtils providesSharedPreferencesUtils() {
        return new SharedPreferencesUtils(this.firebaseApp);
    }

    /* access modifiers changed from: package-private */
    @Provides
    public DataCollectionHelper providesDataCollectionHelper(SharedPreferencesUtils sharedPreferencesUtils, Subscriber subscriber) {
        return new DataCollectionHelper(this.firebaseApp, sharedPreferencesUtils, this.firebaseInstanceId, subscriber);
    }

    /* access modifiers changed from: package-private */
    @Provides
    public TestDeviceHelper providesTestDeviceHelper(SharedPreferencesUtils sharedPreferencesUtils) {
        return new TestDeviceHelper(sharedPreferencesUtils);
    }

    /* access modifiers changed from: package-private */
    @Provides
    public ApiClient providesApiClient(Lazy<GrpcClient> lazy, Application application, DataCollectionHelper dataCollectionHelper, ProviderInstaller providerInstaller) {
        return new ApiClient(lazy, this.firebaseApp, application, this.firebaseInstanceId, dataCollectionHelper, this.clock, providerInstaller);
    }
}
