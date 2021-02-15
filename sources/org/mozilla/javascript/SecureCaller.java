package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class SecureCaller {
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
    /* access modifiers changed from: private */
    public static final byte[] secureCallerImplBytecode = loadBytecode();

    public abstract Object call(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: org.mozilla.javascript.SecureCaller} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object callSecurely(final java.security.CodeSource r9, org.mozilla.javascript.Callable r10, org.mozilla.javascript.Context r11, org.mozilla.javascript.Scriptable r12, org.mozilla.javascript.Scriptable r13, java.lang.Object[] r14) {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            org.mozilla.javascript.SecureCaller$1 r1 = new org.mozilla.javascript.SecureCaller$1
            r1.<init>(r0)
            java.lang.Object r0 = java.security.AccessController.doPrivileged(r1)
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.SecureCaller>>> r1 = callers
            monitor-enter(r1)
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.SecureCaller>>> r2 = callers     // Catch:{ all -> 0x0069 }
            java.lang.Object r2 = r2.get(r9)     // Catch:{ all -> 0x0069 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0026
            java.util.WeakHashMap r2 = new java.util.WeakHashMap     // Catch:{ all -> 0x0069 }
            r2.<init>()     // Catch:{ all -> 0x0069 }
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.SecureCaller>>> r3 = callers     // Catch:{ all -> 0x0069 }
            r3.put(r9, r2)     // Catch:{ all -> 0x0069 }
        L_0x0026:
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            monitor-enter(r2)
            java.lang.Object r1 = r2.get(r0)     // Catch:{ all -> 0x0066 }
            java.lang.ref.SoftReference r1 = (java.lang.ref.SoftReference) r1     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0037
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0066 }
            org.mozilla.javascript.SecureCaller r1 = (org.mozilla.javascript.SecureCaller) r1     // Catch:{ all -> 0x0066 }
            goto L_0x0038
        L_0x0037:
            r1 = 0
        L_0x0038:
            if (r1 != 0) goto L_0x005a
            org.mozilla.javascript.SecureCaller$2 r1 = new org.mozilla.javascript.SecureCaller$2     // Catch:{ PrivilegedActionException -> 0x004f }
            r1.<init>(r0, r9)     // Catch:{ PrivilegedActionException -> 0x004f }
            java.lang.Object r9 = java.security.AccessController.doPrivileged(r1)     // Catch:{ PrivilegedActionException -> 0x004f }
            r1 = r9
            org.mozilla.javascript.SecureCaller r1 = (org.mozilla.javascript.SecureCaller) r1     // Catch:{ PrivilegedActionException -> 0x004f }
            java.lang.ref.SoftReference r9 = new java.lang.ref.SoftReference     // Catch:{ PrivilegedActionException -> 0x004f }
            r9.<init>(r1)     // Catch:{ PrivilegedActionException -> 0x004f }
            r2.put(r0, r9)     // Catch:{ PrivilegedActionException -> 0x004f }
            goto L_0x005a
        L_0x004f:
            r9 = move-exception
            java.lang.reflect.UndeclaredThrowableException r10 = new java.lang.reflect.UndeclaredThrowableException     // Catch:{ all -> 0x0066 }
            java.lang.Throwable r9 = r9.getCause()     // Catch:{ all -> 0x0066 }
            r10.<init>(r9)     // Catch:{ all -> 0x0066 }
            throw r10     // Catch:{ all -> 0x0066 }
        L_0x005a:
            r3 = r1
            monitor-exit(r2)     // Catch:{ all -> 0x0066 }
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r8 = r14
            java.lang.Object r9 = r3.call(r4, r5, r6, r7, r8)
            return r9
        L_0x0066:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0066 }
            throw r9
        L_0x0069:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.SecureCaller.callSecurely(java.security.CodeSource, org.mozilla.javascript.Callable, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static class SecureClassLoaderImpl extends SecureClassLoader {
        SecureClassLoaderImpl(ClassLoader classLoader) {
            super(classLoader);
        }

        /* access modifiers changed from: package-private */
        public Class<?> defineAndLinkClass(String str, byte[] bArr, CodeSource codeSource) {
            Class<?> defineClass = defineClass(str, bArr, 0, bArr.length, codeSource);
            resolveClass(defineClass);
            return defineClass;
        }
    }

    private static byte[] loadBytecode() {
        return (byte[]) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return SecureCaller.loadBytecodePrivileged();
            }
        });
    }

    /* access modifiers changed from: private */
    public static byte[] loadBytecodePrivileged() {
        InputStream openStream;
        try {
            openStream = SecureCaller.class.getResource("SecureCallerImpl.clazz").openStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = openStream.read();
                if (read == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    openStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.write(read);
            }
        } catch (IOException e) {
            throw new UndeclaredThrowableException(e);
        } catch (Throwable th) {
            openStream.close();
            throw th;
        }
    }
}
