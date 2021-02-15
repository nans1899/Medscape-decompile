package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.ApiClient;
import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.GrpcClient;
import com.google.firebase.inappmessaging.internal.ProviderInstaller;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ApiClientModule_ProvidesApiClientFactory implements Factory<ApiClient> {
    private final Provider<Application> applicationProvider;
    private final Provider<DataCollectionHelper> dataCollectionHelperProvider;
    private final Provider<GrpcClient> grpcClientProvider;
    private final ApiClientModule module;
    private final Provider<ProviderInstaller> providerInstallerProvider;

    public ApiClientModule_ProvidesApiClientFactory(ApiClientModule apiClientModule, Provider<GrpcClient> provider, Provider<Application> provider2, Provider<DataCollectionHelper> provider3, Provider<ProviderInstaller> provider4) {
        this.module = apiClientModule;
        this.grpcClientProvider = provider;
        this.applicationProvider = provider2;
        this.dataCollectionHelperProvider = provider3;
        this.providerInstallerProvider = provider4;
    }

    public ApiClient get() {
        return providesApiClient(this.module, DoubleCheck.lazy(this.grpcClientProvider), this.applicationProvider.get(), this.dataCollectionHelperProvider.get(), this.providerInstallerProvider.get());
    }

    public static ApiClientModule_ProvidesApiClientFactory create(ApiClientModule apiClientModule, Provider<GrpcClient> provider, Provider<Application> provider2, Provider<DataCollectionHelper> provider3, Provider<ProviderInstaller> provider4) {
        return new ApiClientModule_ProvidesApiClientFactory(apiClientModule, provider, provider2, provider3, provider4);
    }

    public static ApiClient providesApiClient(ApiClientModule apiClientModule, Lazy<GrpcClient> lazy, Application application, DataCollectionHelper dataCollectionHelper, ProviderInstaller providerInstaller) {
        return (ApiClient) Preconditions.checkNotNull(apiClientModule.providesApiClient(lazy, application, dataCollectionHelper, providerInstaller), "Cannot return null from a non-@Nullable @Provides method");
    }
}
