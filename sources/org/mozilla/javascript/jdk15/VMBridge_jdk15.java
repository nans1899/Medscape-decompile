package org.mozilla.javascript.jdk15;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.mozilla.javascript.jdk13.VMBridge_jdk13;

public class VMBridge_jdk15 extends VMBridge_jdk13 {
    public VMBridge_jdk15() throws SecurityException, InstantiationException {
        try {
            Method.class.getMethod("isVarArgs", (Class[]) null);
        } catch (NoSuchMethodException e) {
            throw new InstantiationException(e.getMessage());
        }
    }

    public boolean isVarArgs(Member member) {
        if (member instanceof Method) {
            return ((Method) member).isVarArgs();
        }
        if (member instanceof Constructor) {
            return ((Constructor) member).isVarArgs();
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.util.Iterator<?>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Iterator<?> getJavaIterator(org.mozilla.javascript.Context r1, org.mozilla.javascript.Scriptable r2, java.lang.Object r3) {
        /*
            r0 = this;
            boolean r1 = r3 instanceof org.mozilla.javascript.Wrapper
            r2 = 0
            if (r1 == 0) goto L_0x001c
            org.mozilla.javascript.Wrapper r3 = (org.mozilla.javascript.Wrapper) r3
            java.lang.Object r1 = r3.unwrap()
            boolean r3 = r1 instanceof java.util.Iterator
            if (r3 == 0) goto L_0x0012
            r2 = r1
            java.util.Iterator r2 = (java.util.Iterator) r2
        L_0x0012:
            boolean r3 = r1 instanceof java.lang.Iterable
            if (r3 == 0) goto L_0x001c
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r2 = r1.iterator()
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.jdk15.VMBridge_jdk15.getJavaIterator(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.Object):java.util.Iterator");
    }
}
