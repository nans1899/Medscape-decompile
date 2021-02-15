package coil.memory;

import android.os.Build;
import coil.size.Size;
import coil.util.Logger;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcoil/memory/HardwareBitmapService;", "", "()V", "allowHardware", "", "size", "Lcoil/size/Size;", "logger", "Lcoil/util/Logger;", "Companion", "Lcoil/memory/ImmutableHardwareBitmapService;", "Lcoil/memory/LimitedFileDescriptorHardwareBitmapService;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HardwareBitmapService.kt */
public abstract class HardwareBitmapService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public abstract boolean allowHardware(Size size, Logger logger);

    private HardwareBitmapService() {
    }

    public /* synthetic */ HardwareBitmapService(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"Lcoil/memory/HardwareBitmapService$Companion;", "", "()V", "invoke", "Lcoil/memory/HardwareBitmapService;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HardwareBitmapService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HardwareBitmapService invoke() {
            if (Build.VERSION.SDK_INT < 26 || HardwareBitmapBlacklist.IS_BLACKLISTED) {
                return new ImmutableHardwareBitmapService(false);
            }
            if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
                return LimitedFileDescriptorHardwareBitmapService.INSTANCE;
            }
            return new ImmutableHardwareBitmapService(true);
        }
    }
}
