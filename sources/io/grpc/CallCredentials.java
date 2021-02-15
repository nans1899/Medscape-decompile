package io.grpc;

import java.util.concurrent.Executor;

public abstract class CallCredentials {

    public static abstract class MetadataApplier {
        public abstract void apply(Metadata metadata);

        public abstract void fail(Status status);
    }

    public static abstract class RequestInfo {
        public abstract String getAuthority();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();

        public abstract SecurityLevel getSecurityLevel();

        public abstract Attributes getTransportAttrs();
    }

    public abstract void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier);

    public abstract void thisUsesUnstableApi();
}
