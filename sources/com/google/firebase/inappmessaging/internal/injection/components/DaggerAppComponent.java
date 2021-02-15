package com.google.firebase.inappmessaging.internal.injection.components;

import android.app.Application;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging_Factory;
import com.google.firebase.inappmessaging.internal.AbtIntegrationHelper;
import com.google.firebase.inappmessaging.internal.AnalyticsEventsManager;
import com.google.firebase.inappmessaging.internal.ApiClient;
import com.google.firebase.inappmessaging.internal.CampaignCacheClient;
import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.DisplayCallbacksFactory;
import com.google.firebase.inappmessaging.internal.DisplayCallbacksFactory_Factory;
import com.google.firebase.inappmessaging.internal.GrpcClient;
import com.google.firebase.inappmessaging.internal.GrpcClient_Factory;
import com.google.firebase.inappmessaging.internal.ImpressionStorageClient;
import com.google.firebase.inappmessaging.internal.InAppMessageStreamManager;
import com.google.firebase.inappmessaging.internal.InAppMessageStreamManager_Factory;
import com.google.firebase.inappmessaging.internal.MetricsLoggerClient;
import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import com.google.firebase.inappmessaging.internal.ProviderInstaller;
import com.google.firebase.inappmessaging.internal.RateLimiterClient;
import com.google.firebase.inappmessaging.internal.Schedulers;
import com.google.firebase.inappmessaging.internal.SharedPreferencesUtils;
import com.google.firebase.inappmessaging.internal.TestDeviceHelper;
import com.google.firebase.inappmessaging.internal.injection.components.AppComponent;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesApiClientFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesDataCollectionHelperFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesFirebaseAppFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesFirebaseInstanceIdFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesSharedPreferencesUtilsFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule_ProvidesTestDeviceHelperFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcClientModule_ProvidesApiKeyHeadersFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.TransportClientModule_ProvidesApiClientFactory;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.RateLimit;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.InAppMessagingSdkServingGrpc;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import io.grpc.Channel;
import io.grpc.Metadata;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class DaggerAppComponent implements AppComponent {
    private Provider<AbtIntegrationHelper> abtIntegrationHelperProvider;
    private Provider<AnalyticsConnector> analyticsConnectorProvider;
    private Provider<AnalyticsEventsManager> analyticsEventsManagerProvider;
    private final ApiClientModule apiClientModule;
    private Provider<ConnectableFlowable<String>> appForegroundEventFlowableProvider;
    private Provider<RateLimit> appForegroundRateLimitProvider;
    private Provider<Application> applicationProvider;
    private Provider<CampaignCacheClient> campaignCacheClientProvider;
    private Provider<Clock> clockProvider;
    private Provider<DeveloperListenerManager> developerListenerManagerProvider;
    private Provider<DisplayCallbacksFactory> displayCallbacksFactoryProvider;
    private Provider<Subscriber> firebaseEventsSubscriberProvider;
    private Provider<FirebaseInAppMessaging> firebaseInAppMessagingProvider;
    private Provider<Channel> gRPCChannelProvider;
    private Provider<GrpcClient> grpcClientProvider;
    private Provider<ImpressionStorageClient> impressionStorageClientProvider;
    private Provider<InAppMessageStreamManager> inAppMessageStreamManagerProvider;
    private Provider<ProviderInstaller> probiderInstallerProvider;
    private Provider<ConnectableFlowable<String>> programmaticContextualTriggerFlowableProvider;
    private Provider<ProgramaticContextualTriggers> programmaticContextualTriggersProvider;
    private Provider<ApiClient> providesApiClientProvider;
    private Provider<MetricsLoggerClient> providesApiClientProvider2;
    private Provider<Metadata> providesApiKeyHeadersProvider;
    private Provider<DataCollectionHelper> providesDataCollectionHelperProvider;
    private Provider<FirebaseApp> providesFirebaseAppProvider;
    private Provider<FirebaseInstanceId> providesFirebaseInstanceIdProvider;
    private Provider<InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub> providesInAppMessagingSdkServingStubProvider;
    private Provider<SharedPreferencesUtils> providesSharedPreferencesUtilsProvider;
    private Provider<TestDeviceHelper> providesTestDeviceHelperProvider;
    private Provider<RateLimiterClient> rateLimiterClientProvider;
    private Provider<Schedulers> schedulersProvider;
    private Provider<TransportFactory> transportFactoryProvider;
    private final UniversalComponent universalComponent;

    private DaggerAppComponent(ApiClientModule apiClientModule2, GrpcClientModule grpcClientModule, UniversalComponent universalComponent2, AbtIntegrationHelper abtIntegrationHelper, TransportFactory transportFactory) {
        this.universalComponent = universalComponent2;
        this.apiClientModule = apiClientModule2;
        initialize(apiClientModule2, grpcClientModule, universalComponent2, abtIntegrationHelper, transportFactory);
    }

    public static AppComponent.Builder builder() {
        return new Builder();
    }

    private DataCollectionHelper getDataCollectionHelper() {
        ApiClientModule apiClientModule2 = this.apiClientModule;
        return ApiClientModule_ProvidesDataCollectionHelperFactory.providesDataCollectionHelper(apiClientModule2, ApiClientModule_ProvidesSharedPreferencesUtilsFactory.providesSharedPreferencesUtils(apiClientModule2), (Subscriber) Preconditions.checkNotNull(this.universalComponent.firebaseEventsSubscriber(), "Cannot return null from a non-@Nullable component method"));
    }

    private void initialize(ApiClientModule apiClientModule2, GrpcClientModule grpcClientModule, UniversalComponent universalComponent2, AbtIntegrationHelper abtIntegrationHelper, TransportFactory transportFactory) {
        ApiClientModule apiClientModule3 = apiClientModule2;
        UniversalComponent universalComponent3 = universalComponent2;
        this.appForegroundEventFlowableProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundEventFlowable(universalComponent3);
        this.programmaticContextualTriggerFlowableProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggerFlowable(universalComponent3);
        this.campaignCacheClientProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_campaignCacheClient(universalComponent3);
        this.clockProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_clock(universalComponent3);
        this.gRPCChannelProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_gRPCChannel(universalComponent3);
        GrpcClientModule_ProvidesApiKeyHeadersFactory create = GrpcClientModule_ProvidesApiKeyHeadersFactory.create(grpcClientModule);
        this.providesApiKeyHeadersProvider = create;
        Provider<InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub> provider = DoubleCheck.provider(GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory.create(grpcClientModule, this.gRPCChannelProvider, create));
        this.providesInAppMessagingSdkServingStubProvider = provider;
        this.grpcClientProvider = DoubleCheck.provider(GrpcClient_Factory.create(provider));
        this.applicationProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_application(universalComponent3);
        this.providesSharedPreferencesUtilsProvider = ApiClientModule_ProvidesSharedPreferencesUtilsFactory.create(apiClientModule2);
        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_firebaseEventsSubscriber com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_firebaseeventssubscriber = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_firebaseEventsSubscriber(universalComponent3);
        this.firebaseEventsSubscriberProvider = com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_firebaseeventssubscriber;
        this.providesDataCollectionHelperProvider = ApiClientModule_ProvidesDataCollectionHelperFactory.create(apiClientModule3, this.providesSharedPreferencesUtilsProvider, com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_firebaseeventssubscriber);
        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_probiderInstaller com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_probiderinstaller = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_probiderInstaller(universalComponent3);
        this.probiderInstallerProvider = com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_probiderinstaller;
        this.providesApiClientProvider = DoubleCheck.provider(ApiClientModule_ProvidesApiClientFactory.create(apiClientModule3, this.grpcClientProvider, this.applicationProvider, this.providesDataCollectionHelperProvider, com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_probiderinstaller));
        this.analyticsEventsManagerProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsEventsManager(universalComponent3);
        this.schedulersProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_schedulers(universalComponent3);
        this.impressionStorageClientProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_impressionStorageClient(universalComponent3);
        this.rateLimiterClientProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_rateLimiterClient(universalComponent3);
        this.appForegroundRateLimitProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundRateLimit(universalComponent3);
        this.providesTestDeviceHelperProvider = ApiClientModule_ProvidesTestDeviceHelperFactory.create(apiClientModule3, this.providesSharedPreferencesUtilsProvider);
        Factory create2 = InstanceFactory.create(abtIntegrationHelper);
        this.abtIntegrationHelperProvider = create2;
        this.inAppMessageStreamManagerProvider = DoubleCheck.provider(InAppMessageStreamManager_Factory.create(this.appForegroundEventFlowableProvider, this.programmaticContextualTriggerFlowableProvider, this.campaignCacheClientProvider, this.clockProvider, this.providesApiClientProvider, this.analyticsEventsManagerProvider, this.schedulersProvider, this.impressionStorageClientProvider, this.rateLimiterClientProvider, this.appForegroundRateLimitProvider, this.providesTestDeviceHelperProvider, create2));
        this.programmaticContextualTriggersProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggers(universalComponent3);
        this.providesFirebaseAppProvider = ApiClientModule_ProvidesFirebaseAppFactory.create(apiClientModule2);
        this.transportFactoryProvider = InstanceFactory.create(transportFactory);
        this.analyticsConnectorProvider = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsConnector(universalComponent3);
        this.providesFirebaseInstanceIdProvider = ApiClientModule_ProvidesFirebaseInstanceIdFactory.create(apiClientModule2);
        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_developerListenerManager com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_developerlistenermanager = new com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_developerListenerManager(universalComponent3);
        this.developerListenerManagerProvider = com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_developerlistenermanager;
        Provider<MetricsLoggerClient> provider2 = DoubleCheck.provider(TransportClientModule_ProvidesApiClientFactory.create(this.providesFirebaseAppProvider, this.transportFactoryProvider, this.analyticsConnectorProvider, this.providesFirebaseInstanceIdProvider, this.clockProvider, com_google_firebase_inappmessaging_internal_injection_components_universalcomponent_developerlistenermanager));
        this.providesApiClientProvider2 = provider2;
        DisplayCallbacksFactory_Factory create3 = DisplayCallbacksFactory_Factory.create(this.impressionStorageClientProvider, this.clockProvider, this.schedulersProvider, this.rateLimiterClientProvider, this.campaignCacheClientProvider, this.appForegroundRateLimitProvider, provider2, this.providesDataCollectionHelperProvider);
        this.displayCallbacksFactoryProvider = create3;
        this.firebaseInAppMessagingProvider = DoubleCheck.provider(FirebaseInAppMessaging_Factory.create(this.inAppMessageStreamManagerProvider, this.programmaticContextualTriggersProvider, this.providesDataCollectionHelperProvider, create3, this.developerListenerManagerProvider));
    }

    public FirebaseInAppMessaging providesFirebaseInAppMessaging() {
        return this.firebaseInAppMessagingProvider.get();
    }

    public DisplayCallbacksFactory displayCallbacksFactory() {
        return new DisplayCallbacksFactory((ImpressionStorageClient) Preconditions.checkNotNull(this.universalComponent.impressionStorageClient(), "Cannot return null from a non-@Nullable component method"), (Clock) Preconditions.checkNotNull(this.universalComponent.clock(), "Cannot return null from a non-@Nullable component method"), (Schedulers) Preconditions.checkNotNull(this.universalComponent.schedulers(), "Cannot return null from a non-@Nullable component method"), (RateLimiterClient) Preconditions.checkNotNull(this.universalComponent.rateLimiterClient(), "Cannot return null from a non-@Nullable component method"), (CampaignCacheClient) Preconditions.checkNotNull(this.universalComponent.campaignCacheClient(), "Cannot return null from a non-@Nullable component method"), (RateLimit) Preconditions.checkNotNull(this.universalComponent.appForegroundRateLimit(), "Cannot return null from a non-@Nullable component method"), this.providesApiClientProvider2.get(), getDataCollectionHelper());
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static final class Builder implements AppComponent.Builder {
        private AbtIntegrationHelper abtIntegrationHelper;
        private ApiClientModule apiClientModule;
        private GrpcClientModule grpcClientModule;
        private TransportFactory transportFactory;
        private UniversalComponent universalComponent;

        private Builder() {
        }

        public Builder abtIntegrationHelper(AbtIntegrationHelper abtIntegrationHelper2) {
            this.abtIntegrationHelper = (AbtIntegrationHelper) Preconditions.checkNotNull(abtIntegrationHelper2);
            return this;
        }

        public Builder apiClientModule(ApiClientModule apiClientModule2) {
            this.apiClientModule = (ApiClientModule) Preconditions.checkNotNull(apiClientModule2);
            return this;
        }

        public Builder grpcClientModule(GrpcClientModule grpcClientModule2) {
            this.grpcClientModule = (GrpcClientModule) Preconditions.checkNotNull(grpcClientModule2);
            return this;
        }

        public Builder universalComponent(UniversalComponent universalComponent2) {
            this.universalComponent = (UniversalComponent) Preconditions.checkNotNull(universalComponent2);
            return this;
        }

        public Builder transportFactory(TransportFactory transportFactory2) {
            this.transportFactory = (TransportFactory) Preconditions.checkNotNull(transportFactory2);
            return this;
        }

        public AppComponent build() {
            Preconditions.checkBuilderRequirement(this.abtIntegrationHelper, AbtIntegrationHelper.class);
            Preconditions.checkBuilderRequirement(this.apiClientModule, ApiClientModule.class);
            Preconditions.checkBuilderRequirement(this.grpcClientModule, GrpcClientModule.class);
            Preconditions.checkBuilderRequirement(this.universalComponent, UniversalComponent.class);
            Preconditions.checkBuilderRequirement(this.transportFactory, TransportFactory.class);
            return new DaggerAppComponent(this.apiClientModule, this.grpcClientModule, this.universalComponent, this.abtIntegrationHelper, this.transportFactory);
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundEventFlowable implements Provider<ConnectableFlowable<String>> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundEventFlowable(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public ConnectableFlowable<String> get() {
            return (ConnectableFlowable) Preconditions.checkNotNull(this.universalComponent.appForegroundEventFlowable(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggerFlowable implements Provider<ConnectableFlowable<String>> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggerFlowable(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public ConnectableFlowable<String> get() {
            return (ConnectableFlowable) Preconditions.checkNotNull(this.universalComponent.programmaticContextualTriggerFlowable(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_campaignCacheClient implements Provider<CampaignCacheClient> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_campaignCacheClient(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public CampaignCacheClient get() {
            return (CampaignCacheClient) Preconditions.checkNotNull(this.universalComponent.campaignCacheClient(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_clock implements Provider<Clock> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_clock(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public Clock get() {
            return (Clock) Preconditions.checkNotNull(this.universalComponent.clock(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_gRPCChannel implements Provider<Channel> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_gRPCChannel(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public Channel get() {
            return (Channel) Preconditions.checkNotNull(this.universalComponent.gRPCChannel(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_application implements Provider<Application> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_application(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public Application get() {
            return (Application) Preconditions.checkNotNull(this.universalComponent.application(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_firebaseEventsSubscriber implements Provider<Subscriber> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_firebaseEventsSubscriber(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public Subscriber get() {
            return (Subscriber) Preconditions.checkNotNull(this.universalComponent.firebaseEventsSubscriber(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_probiderInstaller implements Provider<ProviderInstaller> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_probiderInstaller(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public ProviderInstaller get() {
            return (ProviderInstaller) Preconditions.checkNotNull(this.universalComponent.probiderInstaller(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsEventsManager implements Provider<AnalyticsEventsManager> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsEventsManager(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public AnalyticsEventsManager get() {
            return (AnalyticsEventsManager) Preconditions.checkNotNull(this.universalComponent.analyticsEventsManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_schedulers implements Provider<Schedulers> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_schedulers(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public Schedulers get() {
            return (Schedulers) Preconditions.checkNotNull(this.universalComponent.schedulers(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_impressionStorageClient implements Provider<ImpressionStorageClient> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_impressionStorageClient(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public ImpressionStorageClient get() {
            return (ImpressionStorageClient) Preconditions.checkNotNull(this.universalComponent.impressionStorageClient(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_rateLimiterClient implements Provider<RateLimiterClient> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_rateLimiterClient(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public RateLimiterClient get() {
            return (RateLimiterClient) Preconditions.checkNotNull(this.universalComponent.rateLimiterClient(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundRateLimit implements Provider<RateLimit> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_appForegroundRateLimit(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public RateLimit get() {
            return (RateLimit) Preconditions.checkNotNull(this.universalComponent.appForegroundRateLimit(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggers implements Provider<ProgramaticContextualTriggers> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_programmaticContextualTriggers(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public ProgramaticContextualTriggers get() {
            return (ProgramaticContextualTriggers) Preconditions.checkNotNull(this.universalComponent.programmaticContextualTriggers(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsConnector implements Provider<AnalyticsConnector> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_analyticsConnector(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public AnalyticsConnector get() {
            return (AnalyticsConnector) Preconditions.checkNotNull(this.universalComponent.analyticsConnector(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_developerListenerManager implements Provider<DeveloperListenerManager> {
        private final UniversalComponent universalComponent;

        com_google_firebase_inappmessaging_internal_injection_components_UniversalComponent_developerListenerManager(UniversalComponent universalComponent2) {
            this.universalComponent = universalComponent2;
        }

        public DeveloperListenerManager get() {
            return (DeveloperListenerManager) Preconditions.checkNotNull(this.universalComponent.developerListenerManager(), "Cannot return null from a non-@Nullable component method");
        }
    }
}
