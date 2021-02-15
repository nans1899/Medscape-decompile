package io.grpc;

import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ManagedChannelProvider {
    static final Iterable<Class<?>> HARDCODED_CLASSES;
    private static final ManagedChannelProvider provider;

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForTarget(String str);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    static {
        Class cls = ManagedChannelProvider.class;
        HardcodedClasses hardcodedClasses = new HardcodedClasses();
        HARDCODED_CLASSES = hardcodedClasses;
        provider = (ManagedChannelProvider) ServiceProviders.load(cls, hardcodedClasses, cls.getClassLoader(), new ServiceProviders.PriorityAccessor<ManagedChannelProvider>() {
            public boolean isAvailable(ManagedChannelProvider managedChannelProvider) {
                return managedChannelProvider.isAvailable();
            }

            public int getPriority(ManagedChannelProvider managedChannelProvider) {
                return managedChannelProvider.priority();
            }
        });
    }

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider managedChannelProvider = provider;
        if (managedChannelProvider != null) {
            return managedChannelProvider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }

    private static final class HardcodedClasses implements Iterable<Class<?>> {
        private HardcodedClasses() {
        }

        public Iterator<Class<?>> iterator() {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
            } catch (ClassNotFoundException unused) {
            }
            try {
                arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
            } catch (ClassNotFoundException unused2) {
            }
            return arrayList.iterator();
        }
    }
}
