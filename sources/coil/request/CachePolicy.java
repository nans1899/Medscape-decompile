package coil.request;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcoil/request/CachePolicy;", "", "readEnabled", "", "writeEnabled", "(Ljava/lang/String;IZZ)V", "getReadEnabled", "()Z", "getWriteEnabled", "ENABLED", "READ_ONLY", "WRITE_ONLY", "DISABLED", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CachePolicy.kt */
public enum CachePolicy {
    ENABLED(true, true),
    READ_ONLY(true, false),
    WRITE_ONLY(false, true),
    DISABLED(false, false);
    
    private final boolean readEnabled;
    private final boolean writeEnabled;

    private CachePolicy(boolean z, boolean z2) {
        this.readEnabled = z;
        this.writeEnabled = z2;
    }

    public final boolean getReadEnabled() {
        return this.readEnabled;
    }

    public final boolean getWriteEnabled() {
        return this.writeEnabled;
    }
}
