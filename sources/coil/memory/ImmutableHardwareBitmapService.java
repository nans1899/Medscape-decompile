package coil.memory;

import coil.size.Size;
import coil.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcoil/memory/ImmutableHardwareBitmapService;", "Lcoil/memory/HardwareBitmapService;", "allowHardware", "", "(Z)V", "size", "Lcoil/size/Size;", "logger", "Lcoil/util/Logger;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HardwareBitmapService.kt */
final class ImmutableHardwareBitmapService extends HardwareBitmapService {
    private final boolean allowHardware;

    public ImmutableHardwareBitmapService(boolean z) {
        super((DefaultConstructorMarker) null);
        this.allowHardware = z;
    }

    public boolean allowHardware(Size size, Logger logger) {
        Intrinsics.checkParameterIsNotNull(size, "size");
        return this.allowHardware;
    }
}
