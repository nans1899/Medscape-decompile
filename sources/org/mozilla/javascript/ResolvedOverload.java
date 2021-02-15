package org.mozilla.javascript;

import java.util.Arrays;

/* compiled from: NativeJavaMethod */
class ResolvedOverload {
    final int index;
    final Class<?>[] types;

    ResolvedOverload(Object[] objArr, int i) {
        this.index = i;
        this.types = new Class[objArr.length];
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr[i2];
            obj = obj instanceof Wrapper ? ((Wrapper) obj).unwrap() : obj;
            this.types[i2] = obj == null ? null : obj.getClass();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean matches(Object[] objArr) {
        if (objArr.length != this.types.length) {
            return false;
        }
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            if (obj instanceof Wrapper) {
                obj = ((Wrapper) obj).unwrap();
            }
            if (obj == null) {
                if (this.types[i] != null) {
                    return false;
                }
            } else if (obj.getClass() != this.types[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ResolvedOverload)) {
            return false;
        }
        ResolvedOverload resolvedOverload = (ResolvedOverload) obj;
        if (!Arrays.equals(this.types, resolvedOverload.types) || this.index != resolvedOverload.index) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.types);
    }
}
