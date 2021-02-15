package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.firebase.messaging.Constants;
import io.grpc.LoadBalancer;
import io.grpc.NameResolver;
import java.util.Map;

public abstract class LoadBalancerProvider extends LoadBalancer.Factory {
    private static final NameResolver.ConfigOrError UNKNOWN_CONFIG = NameResolver.ConfigOrError.fromConfig(new UnknownConfig());

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public abstract String getPolicyName();

    public abstract int getPriority();

    public abstract boolean isAvailable();

    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        return UNKNOWN_CONFIG;
    }

    public final String toString() {
        return MoreObjects.toStringHelper((Object) this).add("policy", (Object) getPolicyName()).add(Constants.FirelogAnalytics.PARAM_PRIORITY, getPriority()).add("available", isAvailable()).toString();
    }

    public final int hashCode() {
        return super.hashCode();
    }

    private static final class UnknownConfig {
        public String toString() {
            return "service config is unused";
        }

        UnknownConfig() {
        }
    }
}
