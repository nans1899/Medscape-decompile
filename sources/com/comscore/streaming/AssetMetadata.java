package com.comscore.streaming;

import com.comscore.util.cpp.CppJavaBinder;

public abstract class AssetMetadata extends CppJavaBinder {
    private long b;

    AssetMetadata(long j) {
        this.b = j;
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return this.b;
    }
}
