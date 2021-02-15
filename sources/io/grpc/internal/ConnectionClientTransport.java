package io.grpc.internal;

import io.grpc.Attributes;

public interface ConnectionClientTransport extends ManagedClientTransport {
    Attributes getAttributes();
}
