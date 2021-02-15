package coil.size;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcoil/size/PixelSize;", "Lcoil/size/Size;", "width", "", "height", "(II)V", "getHeight", "()I", "getWidth", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Size.kt */
public final class PixelSize extends Size {
    private final int height;
    private final int width;

    public static /* synthetic */ PixelSize copy$default(PixelSize pixelSize, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = pixelSize.width;
        }
        if ((i3 & 2) != 0) {
            i2 = pixelSize.height;
        }
        return pixelSize.copy(i, i2);
    }

    public final int component1() {
        return this.width;
    }

    public final int component2() {
        return this.height;
    }

    public final PixelSize copy(int i, int i2) {
        return new PixelSize(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PixelSize)) {
            return false;
        }
        PixelSize pixelSize = (PixelSize) obj;
        return this.width == pixelSize.width && this.height == pixelSize.height;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }

    public String toString() {
        return "PixelSize(width=" + this.width + ", height=" + this.height + ")";
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public PixelSize(int i, int i2) {
        super((DefaultConstructorMarker) null);
        this.width = i;
        this.height = i2;
        if (!(i > 0 && i2 > 0)) {
            throw new IllegalArgumentException("Width and height must be > 0.".toString());
        }
    }
}
