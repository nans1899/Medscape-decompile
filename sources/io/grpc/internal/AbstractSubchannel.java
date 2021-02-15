package io.grpc.internal;

import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.LoadBalancer;
import javax.annotation.Nullable;

abstract class AbstractSubchannel extends LoadBalancer.Subchannel {
    /* access modifiers changed from: package-private */
    public abstract InternalInstrumented<InternalChannelz.ChannelStats> getInternalSubchannel();

    /* access modifiers changed from: package-private */
    @Nullable
    public abstract ClientTransport obtainActiveTransport();

    AbstractSubchannel() {
    }
}
