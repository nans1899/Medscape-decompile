package com.google.android.datatransport.runtime.backends;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BackendRegistryModule {
    /* access modifiers changed from: package-private */
    @Binds
    public abstract BackendRegistry backendRegistry(MetadataBackendRegistry metadataBackendRegistry);
}
