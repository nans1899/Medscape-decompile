package org.mockito.internal.matchers.apachecommons;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EqualsBuilder {
    private boolean isEquals = true;

    public static boolean reflectionEquals(Object obj, Object obj2) {
        return reflectionEquals(obj, obj2, false, (Class<?>) null, (String[]) null);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, String[] strArr) {
        return reflectionEquals(obj, obj2, false, (Class<?>) null, strArr);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z) {
        return reflectionEquals(obj, obj2, z, (Class<?>) null, (String[]) null);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z, Class<?> cls) {
        return reflectionEquals(obj, obj2, z, cls, (String[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r1.isInstance(r12) == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r2.isInstance(r11) == false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean reflectionEquals(java.lang.Object r11, java.lang.Object r12, boolean r13, java.lang.Class<?> r14, java.lang.String[] r15) {
        /*
            if (r11 != r12) goto L_0x0004
            r11 = 1
            return r11
        L_0x0004:
            r0 = 0
            if (r11 == 0) goto L_0x0056
            if (r12 != 0) goto L_0x000a
            goto L_0x0056
        L_0x000a:
            java.lang.Class r1 = r11.getClass()
            java.lang.Class r2 = r12.getClass()
            boolean r3 = r1.isInstance(r12)
            if (r3 == 0) goto L_0x001f
            boolean r3 = r2.isInstance(r11)
            if (r3 != 0) goto L_0x002d
            goto L_0x002c
        L_0x001f:
            boolean r3 = r2.isInstance(r11)
            if (r3 == 0) goto L_0x0056
            boolean r3 = r1.isInstance(r12)
            if (r3 != 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r1 = r2
        L_0x002d:
            org.mockito.internal.matchers.apachecommons.EqualsBuilder r10 = new org.mockito.internal.matchers.apachecommons.EqualsBuilder
            r10.<init>()
            r4 = r11
            r5 = r12
            r6 = r1
            r7 = r10
            r8 = r13
            r9 = r15
            reflectionAppend(r4, r5, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x0056 }
        L_0x003b:
            java.lang.Class r2 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0056 }
            if (r2 == 0) goto L_0x0051
            if (r1 == r14) goto L_0x0051
            java.lang.Class r1 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0056 }
            r2 = r11
            r3 = r12
            r4 = r1
            r5 = r10
            r6 = r13
            r7 = r15
            reflectionAppend(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x0056 }
            goto L_0x003b
        L_0x0051:
            boolean r11 = r10.isEquals()
            return r11
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.matchers.apachecommons.EqualsBuilder.reflectionEquals(java.lang.Object, java.lang.Object, boolean, java.lang.Class, java.lang.String[]):boolean");
    }

    private static void reflectionAppend(Object obj, Object obj2, Class<?> cls, EqualsBuilder equalsBuilder, boolean z, String[] strArr) {
        Field[] declaredFields = cls.getDeclaredFields();
        List asList = strArr != null ? Arrays.asList(strArr) : Collections.emptyList();
        AccessibleObject.setAccessible(declaredFields, true);
        for (int i = 0; i < declaredFields.length && equalsBuilder.isEquals; i++) {
            Field field = declaredFields[i];
            if (!asList.contains(field.getName()) && field.getName().indexOf(36) == -1 && ((z || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                try {
                    equalsBuilder.append(field.get(obj), field.get(obj2));
                } catch (IllegalAccessException unused) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }

    public EqualsBuilder appendSuper(boolean z) {
        this.isEquals = z & this.isEquals;
        return this;
    }

    public EqualsBuilder append(Object obj, Object obj2) {
        if (!this.isEquals || obj == obj2) {
            return this;
        }
        boolean z = false;
        if (obj == null || obj2 == null) {
            setEquals(false);
            return this;
        }
        if (!obj.getClass().isArray()) {
            if (!(obj instanceof BigDecimal) || !(obj2 instanceof BigDecimal)) {
                this.isEquals = obj.equals(obj2);
            } else {
                if (((BigDecimal) obj).compareTo((BigDecimal) obj2) == 0) {
                    z = true;
                }
                this.isEquals = z;
            }
        } else if (obj.getClass() != obj2.getClass()) {
            setEquals(false);
        } else if (obj instanceof long[]) {
            append((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            append((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            append((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            append((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            append((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            append((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            append((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            append((boolean[]) obj, (boolean[]) obj2);
        } else {
            append((Object[]) obj, (Object[]) obj2);
        }
        return this;
    }

    public EqualsBuilder append(long j, long j2) {
        this.isEquals = (j == j2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(int i, int i2) {
        this.isEquals = (i == i2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(short s, short s2) {
        this.isEquals = (s == s2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(char c, char c2) {
        this.isEquals = (c == c2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(byte b, byte b2) {
        this.isEquals = (b == b2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(double d, double d2) {
        if (!this.isEquals) {
            return this;
        }
        return append(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    public EqualsBuilder append(float f, float f2) {
        if (!this.isEquals) {
            return this;
        }
        return append(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    public EqualsBuilder append(boolean z, boolean z2) {
        this.isEquals = (z == z2) & this.isEquals;
        return this;
    }

    public EqualsBuilder append(Object[] objArr, Object[] objArr2) {
        if (!this.isEquals || objArr == objArr2) {
            return this;
        }
        if (objArr == null || objArr2 == null) {
            setEquals(false);
            return this;
        } else if (objArr.length != objArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < objArr.length && this.isEquals; i++) {
                append(objArr[i], objArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(long[] jArr, long[] jArr2) {
        if (!this.isEquals || jArr == jArr2) {
            return this;
        }
        if (jArr == null || jArr2 == null) {
            setEquals(false);
            return this;
        } else if (jArr.length != jArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < jArr.length && this.isEquals; i++) {
                append(jArr[i], jArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(int[] iArr, int[] iArr2) {
        if (!this.isEquals || iArr == iArr2) {
            return this;
        }
        if (iArr == null || iArr2 == null) {
            setEquals(false);
            return this;
        } else if (iArr.length != iArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < iArr.length && this.isEquals; i++) {
                append(iArr[i], iArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(short[] sArr, short[] sArr2) {
        if (!this.isEquals || sArr == sArr2) {
            return this;
        }
        if (sArr == null || sArr2 == null) {
            setEquals(false);
            return this;
        } else if (sArr.length != sArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < sArr.length && this.isEquals; i++) {
                append(sArr[i], sArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(char[] cArr, char[] cArr2) {
        if (!this.isEquals || cArr == cArr2) {
            return this;
        }
        if (cArr == null || cArr2 == null) {
            setEquals(false);
            return this;
        } else if (cArr.length != cArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < cArr.length && this.isEquals; i++) {
                append(cArr[i], cArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(byte[] bArr, byte[] bArr2) {
        if (!this.isEquals || bArr == bArr2) {
            return this;
        }
        if (bArr == null || bArr2 == null) {
            setEquals(false);
            return this;
        } else if (bArr.length != bArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < bArr.length && this.isEquals; i++) {
                append(bArr[i], bArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(double[] dArr, double[] dArr2) {
        if (!this.isEquals || dArr == dArr2) {
            return this;
        }
        if (dArr == null || dArr2 == null) {
            setEquals(false);
            return this;
        } else if (dArr.length != dArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < dArr.length && this.isEquals; i++) {
                append(dArr[i], dArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(float[] fArr, float[] fArr2) {
        if (!this.isEquals || fArr == fArr2) {
            return this;
        }
        if (fArr == null || fArr2 == null) {
            setEquals(false);
            return this;
        } else if (fArr.length != fArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < fArr.length && this.isEquals; i++) {
                append(fArr[i], fArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (!this.isEquals || zArr == zArr2) {
            return this;
        }
        if (zArr == null || zArr2 == null) {
            setEquals(false);
            return this;
        } else if (zArr.length != zArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < zArr.length && this.isEquals; i++) {
                append(zArr[i], zArr2[i]);
            }
            return this;
        }
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    /* access modifiers changed from: protected */
    public void setEquals(boolean z) {
        this.isEquals = z;
    }
}
