package coil.memory;

import coil.size.PixelSize;
import coil.size.Size;
import coil.util.Logger;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcoil/memory/LimitedFileDescriptorHardwareBitmapService;", "Lcoil/memory/HardwareBitmapService;", "()V", "FILE_DESCRIPTOR_CHECK_INTERVAL", "", "FILE_DESCRIPTOR_LIMIT", "MIN_SIZE_DIMENSION", "TAG", "", "decodesSinceLastFileDescriptorCheck", "fileDescriptorList", "Ljava/io/File;", "hasAvailableFileDescriptors", "", "allowHardware", "size", "Lcoil/size/Size;", "logger", "Lcoil/util/Logger;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HardwareBitmapService.kt */
final class LimitedFileDescriptorHardwareBitmapService extends HardwareBitmapService {
    private static final int FILE_DESCRIPTOR_CHECK_INTERVAL = 50;
    private static final int FILE_DESCRIPTOR_LIMIT = 750;
    public static final LimitedFileDescriptorHardwareBitmapService INSTANCE = new LimitedFileDescriptorHardwareBitmapService();
    private static final int MIN_SIZE_DIMENSION = 75;
    private static final String TAG = "LimitedFileDescriptorHardwareBitmapService";
    private static volatile int decodesSinceLastFileDescriptorCheck;
    private static final File fileDescriptorList = new File("/proc/self/fd");
    private static volatile boolean hasAvailableFileDescriptors = true;

    private LimitedFileDescriptorHardwareBitmapService() {
        super((DefaultConstructorMarker) null);
    }

    public boolean allowHardware(Size size, Logger logger) {
        Intrinsics.checkParameterIsNotNull(size, "size");
        if (size instanceof PixelSize) {
            PixelSize pixelSize = (PixelSize) size;
            if (pixelSize.getWidth() < 75 || pixelSize.getHeight() < 75) {
                return false;
            }
        }
        return hasAvailableFileDescriptors(logger);
    }

    private final synchronized boolean hasAvailableFileDescriptors(Logger logger) {
        int i = decodesSinceLastFileDescriptorCheck;
        decodesSinceLastFileDescriptorCheck = i + 1;
        if (i >= 50) {
            boolean z = false;
            decodesSinceLastFileDescriptorCheck = 0;
            String[] list = fileDescriptorList.list();
            if (list == null) {
                list = new String[0];
            }
            int length = list.length;
            if (length < 750) {
                z = true;
            }
            hasAvailableFileDescriptors = z;
            if (hasAvailableFileDescriptors && logger != null && logger.getLevel() <= 5) {
                logger.log(TAG, 5, "Unable to allocate more hardware bitmaps. Number of used file descriptors: " + length, (Throwable) null);
            }
        }
        return hasAvailableFileDescriptors;
    }
}
