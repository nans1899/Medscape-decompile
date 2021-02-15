package io.grpc;

import io.grpc.ManagedChannelProvider;
import io.grpc.ServiceProviders;
import java.util.Collections;

public abstract class ServerProvider {
    private static final ServerProvider provider;

    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> builderForPort(int i);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    static {
        Class cls = ServerProvider.class;
        provider = (ServerProvider) ServiceProviders.load(cls, Collections.emptyList(), cls.getClassLoader(), new ServiceProviders.PriorityAccessor<ServerProvider>() {
            public boolean isAvailable(ServerProvider serverProvider) {
                return serverProvider.isAvailable();
            }

            public int getPriority(ServerProvider serverProvider) {
                return serverProvider.priority();
            }
        });
    }

    public static ServerProvider provider() {
        ServerProvider serverProvider = provider;
        if (serverProvider != null) {
            return serverProvider;
        }
        throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }
}
