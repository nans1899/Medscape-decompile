package io.grpc;

import io.grpc.Metadata;

public final class InternalStatus {
    public static final Metadata.Key<Status> CODE_KEY = Status.CODE_KEY;
    public static final Metadata.Key<String> MESSAGE_KEY = Status.MESSAGE_KEY;

    private InternalStatus() {
    }
}
