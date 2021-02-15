package coil.memory;

import android.graphics.Bitmap;
import coil.decode.DecodeUtils;
import coil.memory.MemoryCache;
import coil.request.Request;
import coil.size.OriginalSize;
import coil.size.PixelSize;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.util.Bitmaps;
import coil.util.Logger;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J8\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J:\u0010\u0015\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcoil/memory/MemoryCacheService;", "", "requestService", "Lcoil/memory/RequestService;", "logger", "Lcoil/util/Logger;", "(Lcoil/memory/RequestService;Lcoil/util/Logger;)V", "isCachedValueValid", "", "cacheKey", "Lcoil/memory/MemoryCache$Key;", "cacheValue", "Lcoil/memory/MemoryCache$Value;", "request", "Lcoil/request/Request;", "sizeResolver", "Lcoil/size/SizeResolver;", "size", "Lcoil/size/Size;", "scale", "Lcoil/size/Scale;", "isSizeValid", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MemoryCacheService.kt */
public final class MemoryCacheService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "MemoryCacheService";
    private final Logger logger;
    private final RequestService requestService;

    public MemoryCacheService(RequestService requestService2, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(requestService2, "requestService");
        this.requestService = requestService2;
        this.logger = logger2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/memory/MemoryCacheService$Companion;", "", "()V", "TAG", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCacheService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final boolean isCachedValueValid(MemoryCache.Key key, MemoryCache.Value value, Request request, SizeResolver sizeResolver, Size size, Scale scale) {
        Intrinsics.checkParameterIsNotNull(value, "cacheValue");
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(sizeResolver, "sizeResolver");
        Intrinsics.checkParameterIsNotNull(size, "size");
        Intrinsics.checkParameterIsNotNull(scale, "scale");
        if (!isSizeValid(key, value, request, sizeResolver, size, scale)) {
            return false;
        }
        if (this.requestService.isConfigValidForHardware(request, Bitmaps.getSafeConfig(value.getBitmap()))) {
            return true;
        }
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 3) {
            logger2.log(TAG, 3, request.getData() + ": Cached bitmap is hardware-backed, which is incompatible with the request.", (Throwable) null);
        }
        return false;
    }

    private final boolean isSizeValid(MemoryCache.Key key, MemoryCache.Value value, Request request, SizeResolver sizeResolver, Size size, Scale scale) {
        int i;
        int i2;
        Size size2 = size;
        Scale scale2 = scale;
        if (size2 instanceof OriginalSize) {
            if (!value.isSampled()) {
                return true;
            }
            Logger logger2 = this.logger;
            if (logger2 != null && logger2.getLevel() <= 3) {
                logger2.log(TAG, 3, request.getData() + ": Requested original size, but cached image is sampled.", (Throwable) null);
            }
            return false;
        } else if (!(size2 instanceof PixelSize)) {
            return true;
        } else {
            Size size3 = key != null ? key.getSize() : null;
            if (size3 instanceof PixelSize) {
                PixelSize pixelSize = (PixelSize) size3;
                i = pixelSize.getWidth();
                i2 = pixelSize.getHeight();
            } else if (!Intrinsics.areEqual((Object) size3, (Object) OriginalSize.INSTANCE) && size3 != null) {
                throw new NoWhenBranchMatchedException();
            } else {
                Bitmap bitmap = value.getBitmap();
                i = bitmap.getWidth();
                i2 = bitmap.getHeight();
            }
            PixelSize pixelSize2 = (PixelSize) size2;
            if (Math.abs(i - pixelSize2.getWidth()) <= 1 && Math.abs(i2 - pixelSize2.getHeight()) <= 1) {
                return true;
            }
            double computeSizeMultiplier = DecodeUtils.computeSizeMultiplier(i, i2, pixelSize2.getWidth(), pixelSize2.getHeight(), scale2);
            if (computeSizeMultiplier == 1.0d) {
                Request request2 = request;
            } else if (!this.requestService.allowInexactSize(request, sizeResolver)) {
                Logger logger3 = this.logger;
                if (logger3 == null || logger3.getLevel() > 3) {
                    return false;
                }
                logger3.log(TAG, 3, request.getData() + ": Cached image's request size (" + i + ", " + i2 + ") " + "does not exactly match the requested size (" + pixelSize2.getWidth() + ", " + pixelSize2.getHeight() + ", " + scale2 + ").", (Throwable) null);
                return false;
            }
            if (computeSizeMultiplier <= 1.0d || !value.isSampled()) {
                return true;
            }
            Logger logger4 = this.logger;
            if (logger4 == null || logger4.getLevel() > 3) {
                return false;
            }
            logger4.log(TAG, 3, request.getData() + ": Cached image's request size (" + i + ", " + i2 + ") " + "is smaller than the requested size (" + pixelSize2.getWidth() + ", " + pixelSize2.getHeight() + ", " + scale2 + ").", (Throwable) null);
            return false;
        }
    }
}
