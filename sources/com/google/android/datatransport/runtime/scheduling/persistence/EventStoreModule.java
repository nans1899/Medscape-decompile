package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public abstract class EventStoreModule {
    @Provides
    @Named("SQLITE_DB_NAME")
    static String dbName() {
        return "com.google.android.datatransport.events";
    }

    /* access modifiers changed from: package-private */
    @Binds
    public abstract EventStore eventStore(SQLiteEventStore sQLiteEventStore);

    /* access modifiers changed from: package-private */
    @Binds
    public abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore sQLiteEventStore);

    @Provides
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    @Provides
    @Named("SCHEMA_VERSION")
    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }
}
