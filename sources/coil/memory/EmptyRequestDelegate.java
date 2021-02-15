package coil.memory;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcoil/memory/EmptyRequestDelegate;", "Lcoil/memory/RequestDelegate;", "()V", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDelegate.kt */
public final class EmptyRequestDelegate extends RequestDelegate {
    public static final EmptyRequestDelegate INSTANCE = new EmptyRequestDelegate();

    private EmptyRequestDelegate() {
        super((DefaultConstructorMarker) null);
    }
}
