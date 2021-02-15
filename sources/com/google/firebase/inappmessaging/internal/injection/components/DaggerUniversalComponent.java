package com.google.firebase.inappmessaging.internal.injection.components;

import android.app.Application;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inappmessaging.internal.AnalyticsEventsManager;
import com.google.firebase.inappmessaging.internal.CampaignCacheClient;
import com.google.firebase.inappmessaging.internal.CampaignCacheClient_Factory;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.ImpressionStorageClient;
import com.google.firebase.inappmessaging.internal.ImpressionStorageClient_Factory;
import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import com.google.firebase.inappmessaging.internal.ProtoStorageClient;
import com.google.firebase.inappmessaging.internal.ProviderInstaller;
import com.google.firebase.inappmessaging.internal.ProviderInstaller_Factory;
import com.google.firebase.inappmessaging.internal.RateLimiterClient;
import com.google.firebase.inappmessaging.internal.RateLimiterClient_Factory;
import com.google.firebase.inappmessaging.internal.Schedulers;
import com.google.firebase.inappmessaging.internal.Schedulers_Factory;
import com.google.firebase.inappmessaging.internal.injection.modules.AnalyticsEventsModule;
import com.google.firebase.inappmessaging.internal.injection.modules.AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.AppMeasurementModule;
import com.google.firebase.inappmessaging.internal.injection.modules.AppMeasurementModule_ProvidesAnalyticsConnectorFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.AppMeasurementModule_ProvidesSubsriberFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApplicationModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ApplicationModule_DeveloperListenerManagerFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApplicationModule_ProvidesApplicationFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ForegroundFlowableModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcChannelModule;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcChannelModule_ProvidesGrpcChannelFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcChannelModule_ProvidesServiceHostFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ProgrammaticContextualTriggerFlowableModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ProtoStorageClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ProtoStorageClientModule_ProvidesProtoStorageClientForCampaignFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.RateLimitModule;
import com.google.firebase.inappmessaging.internal.injection.modules.RateLimitModule_ProvidesAppForegroundRateLimitFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.SchedulerModule;
import com.google.firebase.inappmessaging.internal.injection.modules.SchedulerModule_ProvidesComputeSchedulerFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.SchedulerModule_ProvidesIOSchedulerFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.SchedulerModule_ProvidesMainThreadSchedulerFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.SystemClockModule;
import com.google.firebase.inappmessaging.internal.injection.modules.SystemClockModule_ProvidesSystemClockModuleFactory;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.ProtoMarshallerClient;
import com.google.firebase.inappmessaging.model.ProtoMarshallerClient_Factory;
import com.google.firebase.inappmessaging.model.RateLimit;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.grpc.Channel;
import io.reactivex.Scheduler;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class DaggerUniversalComponent implements UniversalComponent {
    private Provider<CampaignCacheClient> campaignCacheClientProvider;
    private Provider<DeveloperListenerManager> developerListenerManagerProvider;
    private Provider<ImpressionStorageClient> impressionStorageClientProvider;
    private Provider<ProtoMarshallerClient> protoMarshallerClientProvider;
    private Provider<ProviderInstaller> providerInstallerProvider;
    private Provider<ConnectableFlowable<String>> providesAnalyticsConnectorEventsProvider;
    private Provider<AnalyticsConnector> providesAnalyticsConnectorProvider;
    private Provider<AnalyticsEventsManager> providesAnalyticsEventsManagerProvider;
    private Provider<ConnectableFlowable<String>> providesAppForegroundEventStreamProvider;
    private Provider<Application> providesApplicationProvider;
    private Provider<Scheduler> providesComputeSchedulerProvider;
    private Provider<Channel> providesGrpcChannelProvider;
    private Provider<Scheduler> providesIOSchedulerProvider;
    private Provider<Scheduler> providesMainThreadSchedulerProvider;
    private Provider<ConnectableFlowable<String>> providesProgramaticContextualTriggerStreamProvider;
    private Provider<ProgramaticContextualTriggers> providesProgramaticContextualTriggersProvider;
    private Provider<ProtoStorageClient> providesProtoStorageClientForCampaignProvider;
    private Provider<ProtoStorageClient> providesProtoStorageClientForImpressionStoreProvider;
    private Provider<ProtoStorageClient> providesProtoStorageClientForLimiterStoreProvider;
    private Provider<String> providesServiceHostProvider;
    private Provider<Subscriber> providesSubsriberProvider;
    private Provider<Clock> providesSystemClockModuleProvider;
    private final RateLimitModule rateLimitModule;
    private Provider<RateLimiterClient> rateLimiterClientProvider;
    private Provider<Schedulers> schedulersProvider;
    private final SystemClockModule systemClockModule;

    private DaggerUniversalComponent(GrpcChannelModule grpcChannelModule, SchedulerModule schedulerModule, ApplicationModule applicationModule, ForegroundFlowableModule foregroundFlowableModule, ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule, AnalyticsEventsModule analyticsEventsModule, ProtoStorageClientModule protoStorageClientModule, SystemClockModule systemClockModule2, RateLimitModule rateLimitModule2, AppMeasurementModule appMeasurementModule) {
        this.systemClockModule = systemClockModule2;
        this.rateLimitModule = rateLimitModule2;
        initialize(grpcChannelModule, schedulerModule, applicationModule, foregroundFlowableModule, programmaticContextualTriggerFlowableModule, analyticsEventsModule, protoStorageClientModule, systemClockModule2, rateLimitModule2, appMeasurementModule);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(GrpcChannelModule grpcChannelModule, SchedulerModule schedulerModule, ApplicationModule applicationModule, ForegroundFlowableModule foregroundFlowableModule, ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule, AnalyticsEventsModule analyticsEventsModule, ProtoStorageClientModule protoStorageClientModule, SystemClockModule systemClockModule2, RateLimitModule rateLimitModule2, AppMeasurementModule appMeasurementModule) {
        Provider<Application> provider = DoubleCheck.provider(ApplicationModule_ProvidesApplicationFactory.create(applicationModule));
        this.providesApplicationProvider = provider;
        this.providerInstallerProvider = DoubleCheck.provider(ProviderInstaller_Factory.create(provider));
        Provider<String> provider2 = DoubleCheck.provider(GrpcChannelModule_ProvidesServiceHostFactory.create(grpcChannelModule));
        this.providesServiceHostProvider = provider2;
        this.providesGrpcChannelProvider = DoubleCheck.provider(GrpcChannelModule_ProvidesGrpcChannelFactory.create(grpcChannelModule, provider2));
        this.providesIOSchedulerProvider = DoubleCheck.provider(SchedulerModule_ProvidesIOSchedulerFactory.create(schedulerModule));
        this.providesComputeSchedulerProvider = DoubleCheck.provider(SchedulerModule_ProvidesComputeSchedulerFactory.create(schedulerModule));
        Provider<Scheduler> provider3 = DoubleCheck.provider(SchedulerModule_ProvidesMainThreadSchedulerFactory.create(schedulerModule));
        this.providesMainThreadSchedulerProvider = provider3;
        this.schedulersProvider = DoubleCheck.provider(Schedulers_Factory.create(this.providesIOSchedulerProvider, this.providesComputeSchedulerProvider, provider3));
        this.providesAppForegroundEventStreamProvider = DoubleCheck.provider(ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory.create(foregroundFlowableModule, this.providesApplicationProvider));
        this.providesProgramaticContextualTriggerStreamProvider = DoubleCheck.provider(ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggerStreamFactory.create(programmaticContextualTriggerFlowableModule));
        this.providesProgramaticContextualTriggersProvider = DoubleCheck.provider(ProgrammaticContextualTriggerFlowableModule_ProvidesProgramaticContextualTriggersFactory.create(programmaticContextualTriggerFlowableModule));
        Provider<AnalyticsConnector> provider4 = DoubleCheck.provider(AppMeasurementModule_ProvidesAnalyticsConnectorFactory.create(appMeasurementModule));
        this.providesAnalyticsConnectorProvider = provider4;
        Provider<AnalyticsEventsManager> provider5 = DoubleCheck.provider(AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory.create(analyticsEventsModule, provider4));
        this.providesAnalyticsEventsManagerProvider = provider5;
        this.providesAnalyticsConnectorEventsProvider = DoubleCheck.provider(AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory.create(analyticsEventsModule, provider5));
        this.providesSubsriberProvider = DoubleCheck.provider(AppMeasurementModule_ProvidesSubsriberFactory.create(appMeasurementModule));
        this.providesProtoStorageClientForCampaignProvider = DoubleCheck.provider(ProtoStorageClientModule_ProvidesProtoStorageClientForCampaignFactory.create(protoStorageClientModule, this.providesApplicationProvider));
        SystemClockModule_ProvidesSystemClockModuleFactory create = SystemClockModule_ProvidesSystemClockModuleFactory.create(systemClockModule2);
        this.providesSystemClockModuleProvider = create;
        this.campaignCacheClientProvider = DoubleCheck.provider(CampaignCacheClient_Factory.create(this.providesProtoStorageClientForCampaignProvider, this.providesApplicationProvider, create));
        Provider<ProtoStorageClient> provider6 = DoubleCheck.provider(ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory.create(protoStorageClientModule, this.providesApplicationProvider));
        this.providesProtoStorageClientForImpressionStoreProvider = provider6;
        this.impressionStorageClientProvider = DoubleCheck.provider(ImpressionStorageClient_Factory.create(provider6));
        this.protoMarshallerClientProvider = DoubleCheck.provider(ProtoMarshallerClient_Factory.create());
        Provider<ProtoStorageClient> provider7 = DoubleCheck.provider(ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory.create(protoStorageClientModule, this.providesApplicationProvider));
        this.providesProtoStorageClientForLimiterStoreProvider = provider7;
        this.rateLimiterClientProvider = DoubleCheck.provider(RateLimiterClient_Factory.create(provider7, this.providesSystemClockModuleProvider));
        this.developerListenerManagerProvider = DoubleCheck.provider(ApplicationModule_DeveloperListenerManagerFactory.create(applicationModule));
    }

    public ProviderInstaller probiderInstaller() {
        return this.providerInstallerProvider.get();
    }

    public Channel gRPCChannel() {
        return this.providesGrpcChannelProvider.get();
    }

    public Schedulers schedulers() {
        return this.schedulersProvider.get();
    }

    public ConnectableFlowable<String> appForegroundEventFlowable() {
        return this.providesAppForegroundEventStreamProvider.get();
    }

    public ConnectableFlowable<String> programmaticContextualTriggerFlowable() {
        return this.providesProgramaticContextualTriggerStreamProvider.get();
    }

    public ProgramaticContextualTriggers programmaticContextualTriggers() {
        return this.providesProgramaticContextualTriggersProvider.get();
    }

    public ConnectableFlowable<String> analyticsEventsFlowable() {
        return this.providesAnalyticsConnectorEventsProvider.get();
    }

    public AnalyticsEventsManager analyticsEventsManager() {
        return this.providesAnalyticsEventsManagerProvider.get();
    }

    public AnalyticsConnector analyticsConnector() {
        return this.providesAnalyticsConnectorProvider.get();
    }

    public Subscriber firebaseEventsSubscriber() {
        return this.providesSubsriberProvider.get();
    }

    public CampaignCacheClient campaignCacheClient() {
        return this.campaignCacheClientProvider.get();
    }

    public ImpressionStorageClient impressionStorageClient() {
        return this.impressionStorageClientProvider.get();
    }

    public Clock clock() {
        return SystemClockModule_ProvidesSystemClockModuleFactory.providesSystemClockModule(this.systemClockModule);
    }

    public ProtoMarshallerClient protoMarshallerClient() {
        return this.protoMarshallerClientProvider.get();
    }

    public RateLimiterClient rateLimiterClient() {
        return this.rateLimiterClientProvider.get();
    }

    public Application application() {
        return this.providesApplicationProvider.get();
    }

    public RateLimit appForegroundRateLimit() {
        return RateLimitModule_ProvidesAppForegroundRateLimitFactory.providesAppForegroundRateLimit(this.rateLimitModule);
    }

    public DeveloperListenerManager developerListenerManager() {
        return this.developerListenerManagerProvider.get();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Builder {
        private AnalyticsEventsModule analyticsEventsModule;
        private AppMeasurementModule appMeasurementModule;
        private ApplicationModule applicationModule;
        private ForegroundFlowableModule foregroundFlowableModule;
        private GrpcChannelModule grpcChannelModule;
        private ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule;
        private ProtoStorageClientModule protoStorageClientModule;
        private RateLimitModule rateLimitModule;
        private SchedulerModule schedulerModule;
        private SystemClockModule systemClockModule;

        private Builder() {
        }

        public Builder grpcChannelModule(GrpcChannelModule grpcChannelModule2) {
            this.grpcChannelModule = (GrpcChannelModule) Preconditions.checkNotNull(grpcChannelModule2);
            return this;
        }

        public Builder schedulerModule(SchedulerModule schedulerModule2) {
            this.schedulerModule = (SchedulerModule) Preconditions.checkNotNull(schedulerModule2);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule2) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule2);
            return this;
        }

        public Builder foregroundFlowableModule(ForegroundFlowableModule foregroundFlowableModule2) {
            this.foregroundFlowableModule = (ForegroundFlowableModule) Preconditions.checkNotNull(foregroundFlowableModule2);
            return this;
        }

        public Builder programmaticContextualTriggerFlowableModule(ProgrammaticContextualTriggerFlowableModule programmaticContextualTriggerFlowableModule2) {
            this.programmaticContextualTriggerFlowableModule = (ProgrammaticContextualTriggerFlowableModule) Preconditions.checkNotNull(programmaticContextualTriggerFlowableModule2);
            return this;
        }

        public Builder analyticsEventsModule(AnalyticsEventsModule analyticsEventsModule2) {
            this.analyticsEventsModule = (AnalyticsEventsModule) Preconditions.checkNotNull(analyticsEventsModule2);
            return this;
        }

        public Builder protoStorageClientModule(ProtoStorageClientModule protoStorageClientModule2) {
            this.protoStorageClientModule = (ProtoStorageClientModule) Preconditions.checkNotNull(protoStorageClientModule2);
            return this;
        }

        public Builder systemClockModule(SystemClockModule systemClockModule2) {
            this.systemClockModule = (SystemClockModule) Preconditions.checkNotNull(systemClockModule2);
            return this;
        }

        public Builder rateLimitModule(RateLimitModule rateLimitModule2) {
            this.rateLimitModule = (RateLimitModule) Preconditions.checkNotNull(rateLimitModule2);
            return this;
        }

        public Builder appMeasurementModule(AppMeasurementModule appMeasurementModule2) {
            this.appMeasurementModule = (AppMeasurementModule) Preconditions.checkNotNull(appMeasurementModule2);
            return this;
        }

        public UniversalComponent build() {
            if (this.grpcChannelModule == null) {
                this.grpcChannelModule = new GrpcChannelModule();
            }
            if (this.schedulerModule == null) {
                this.schedulerModule = new SchedulerModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.foregroundFlowableModule == null) {
                this.foregroundFlowableModule = new ForegroundFlowableModule();
            }
            Preconditions.checkBuilderRequirement(this.programmaticContextualTriggerFlowableModule, ProgrammaticContextualTriggerFlowableModule.class);
            if (this.analyticsEventsModule == null) {
                this.analyticsEventsModule = new AnalyticsEventsModule();
            }
            if (this.protoStorageClientModule == null) {
                this.protoStorageClientModule = new ProtoStorageClientModule();
            }
            if (this.systemClockModule == null) {
                this.systemClockModule = new SystemClockModule();
            }
            if (this.rateLimitModule == null) {
                this.rateLimitModule = new RateLimitModule();
            }
            Preconditions.checkBuilderRequirement(this.appMeasurementModule, AppMeasurementModule.class);
            return new DaggerUniversalComponent(this.grpcChannelModule, this.schedulerModule, this.applicationModule, this.foregroundFlowableModule, this.programmaticContextualTriggerFlowableModule, this.analyticsEventsModule, this.protoStorageClientModule, this.systemClockModule, this.rateLimitModule, this.appMeasurementModule);
        }
    }
}
