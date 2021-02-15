package com.google.firebase.inappmessaging.display.internal.injection.components;

import android.view.LayoutInflater;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.BannerBindingWrapper;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.BannerBindingWrapper_Factory;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.CardBindingWrapper;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.CardBindingWrapper_Factory;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.ImageBindingWrapper;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.ImageBindingWrapper_Factory;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.ModalBindingWrapper;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.ModalBindingWrapper_Factory;
import com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterModule;
import com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterModule_InAppMessageLayoutConfigFactory;
import com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterModule_ProvidesBannerMessageFactory;
import com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterModule_ProvidesInflaterserviceFactory;
import com.google.firebase.inappmessaging.model.InAppMessage;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class DaggerInAppMessageComponent implements InAppMessageComponent {
    private Provider<BannerBindingWrapper> bannerBindingWrapperProvider;
    private Provider<CardBindingWrapper> cardBindingWrapperProvider;
    private Provider<ImageBindingWrapper> imageBindingWrapperProvider;
    private Provider<InAppMessageLayoutConfig> inAppMessageLayoutConfigProvider;
    private Provider<ModalBindingWrapper> modalBindingWrapperProvider;
    private Provider<InAppMessage> providesBannerMessageProvider;
    private Provider<LayoutInflater> providesInflaterserviceProvider;

    private DaggerInAppMessageComponent(InflaterModule inflaterModule) {
        initialize(inflaterModule);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(InflaterModule inflaterModule) {
        this.inAppMessageLayoutConfigProvider = DoubleCheck.provider(InflaterModule_InAppMessageLayoutConfigFactory.create(inflaterModule));
        this.providesInflaterserviceProvider = DoubleCheck.provider(InflaterModule_ProvidesInflaterserviceFactory.create(inflaterModule));
        InflaterModule_ProvidesBannerMessageFactory create = InflaterModule_ProvidesBannerMessageFactory.create(inflaterModule);
        this.providesBannerMessageProvider = create;
        this.imageBindingWrapperProvider = DoubleCheck.provider(ImageBindingWrapper_Factory.create(this.inAppMessageLayoutConfigProvider, this.providesInflaterserviceProvider, create));
        this.modalBindingWrapperProvider = DoubleCheck.provider(ModalBindingWrapper_Factory.create(this.inAppMessageLayoutConfigProvider, this.providesInflaterserviceProvider, this.providesBannerMessageProvider));
        this.bannerBindingWrapperProvider = DoubleCheck.provider(BannerBindingWrapper_Factory.create(this.inAppMessageLayoutConfigProvider, this.providesInflaterserviceProvider, this.providesBannerMessageProvider));
        this.cardBindingWrapperProvider = DoubleCheck.provider(CardBindingWrapper_Factory.create(this.inAppMessageLayoutConfigProvider, this.providesInflaterserviceProvider, this.providesBannerMessageProvider));
    }

    public ImageBindingWrapper imageBindingWrapper() {
        return this.imageBindingWrapperProvider.get();
    }

    public ModalBindingWrapper modalBindingWrapper() {
        return this.modalBindingWrapperProvider.get();
    }

    public BannerBindingWrapper bannerBindingWrapper() {
        return this.bannerBindingWrapperProvider.get();
    }

    public CardBindingWrapper cardBindingWrapper() {
        return this.cardBindingWrapperProvider.get();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public static final class Builder {
        private InflaterModule inflaterModule;

        private Builder() {
        }

        public Builder inflaterModule(InflaterModule inflaterModule2) {
            this.inflaterModule = (InflaterModule) Preconditions.checkNotNull(inflaterModule2);
            return this;
        }

        public InAppMessageComponent build() {
            Preconditions.checkBuilderRequirement(this.inflaterModule, InflaterModule.class);
            return new DaggerInAppMessageComponent(this.inflaterModule);
        }
    }
}
