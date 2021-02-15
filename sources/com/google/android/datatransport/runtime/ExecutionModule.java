package com.google.android.datatransport.runtime;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

@Module
abstract class ExecutionModule {
    ExecutionModule() {
    }

    @Singleton
    @Provides
    static Executor executor() {
        return new SafeLoggingExecutor(Executors.newSingleThreadExecutor());
    }
}
