package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.List;
import net.bytebuddy.implementation.MethodDelegation;

public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        delegate().handleResolvedAddressGroups(list, attributes);
    }

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    public void handleNameResolutionError(Status status) {
        delegate().handleNameResolutionError(status);
    }

    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        delegate().handleSubchannelState(subchannel, connectivityStateInfo);
    }

    public void shutdown() {
        delegate().shutdown();
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add(MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX, (Object) delegate()).toString();
    }
}
