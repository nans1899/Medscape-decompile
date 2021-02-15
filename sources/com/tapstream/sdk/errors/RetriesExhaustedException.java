package com.tapstream.sdk.errors;

public class RetriesExhaustedException extends ApiException {
    public RetriesExhaustedException() {
    }

    public RetriesExhaustedException(String str) {
        super(str);
    }

    public RetriesExhaustedException(String str, Throwable th) {
        super(str, th);
    }

    public RetriesExhaustedException(Throwable th) {
        super(th);
    }

    public RetriesExhaustedException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
