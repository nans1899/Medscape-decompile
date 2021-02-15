package org.mozilla.javascript.v8dtoa;

public class DoubleHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int kDenormalExponent = -1074;
    private static final int kExponentBias = 1075;
    static final long kExponentMask = 9218868437227405312L;
    static final long kHiddenBit = 4503599627370496L;
    static final long kSignMask = Long.MIN_VALUE;
    static final long kSignificandMask = 4503599627370495L;
    private static final int kSignificandSize = 52;

    static boolean isDenormal(long j) {
        return (j & kExponentMask) == 0;
    }

    static boolean isInfinite(long j) {
        return (j & kExponentMask) == kExponentMask && (j & kSignificandMask) == 0;
    }

    static boolean isNan(long j) {
        return (j & kExponentMask) == kExponentMask && (j & kSignificandMask) != 0;
    }

    static boolean isSpecial(long j) {
        return (j & kExponentMask) == kExponentMask;
    }

    static int sign(long j) {
        return (j & Long.MIN_VALUE) == 0 ? 1 : -1;
    }

    static DiyFp asDiyFp(long j) {
        return new DiyFp(significand(j), exponent(j));
    }

    static DiyFp asNormalizedDiyFp(long j) {
        long significand = significand(j);
        int exponent = exponent(j);
        while ((kHiddenBit & significand) == 0) {
            significand <<= 1;
            exponent--;
        }
        return new DiyFp(significand << 11, exponent - 11);
    }

    static int exponent(long j) {
        return isDenormal(j) ? kDenormalExponent : ((int) (((j & kExponentMask) >>> 52) & 4294967295L)) - 1075;
    }

    static long significand(long j) {
        long j2 = kSignificandMask & j;
        return !isDenormal(j) ? j2 + kHiddenBit : j2;
    }

    static void normalizedBoundaries(long j, DiyFp diyFp, DiyFp diyFp2) {
        DiyFp asDiyFp = asDiyFp(j);
        boolean z = asDiyFp.f() == kHiddenBit;
        diyFp2.setF((asDiyFp.f() << 1) + 1);
        diyFp2.setE(asDiyFp.e() - 1);
        diyFp2.normalize();
        if (!z || asDiyFp.e() == kDenormalExponent) {
            diyFp.setF((asDiyFp.f() << 1) - 1);
            diyFp.setE(asDiyFp.e() - 1);
        } else {
            diyFp.setF((asDiyFp.f() << 2) - 1);
            diyFp.setE(asDiyFp.e() - 2);
        }
        diyFp.setF(diyFp.f() << (diyFp.e() - diyFp2.e()));
        diyFp.setE(diyFp2.e());
    }
}
