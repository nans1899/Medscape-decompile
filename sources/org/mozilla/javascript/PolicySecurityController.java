package org.mozilla.javascript;

import androidx.core.app.NotificationCompat;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;
import net.bytebuddy.description.method.MethodDescription;
import org.mozilla.classfile.ClassFileWriter;

public class PolicySecurityController extends SecurityController {
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
    /* access modifiers changed from: private */
    public static final byte[] secureCallerImplBytecode = loadBytecode();

    public static abstract class SecureCaller {
        public abstract Object call(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);
    }

    public Object getDynamicSecurityDomain(Object obj) {
        return obj;
    }

    public Class<?> getStaticSecurityDomainClassInternal() {
        return CodeSource.class;
    }

    private static class Loader extends SecureClassLoader implements GeneratedClassLoader {
        private final CodeSource codeSource;

        Loader(ClassLoader classLoader, CodeSource codeSource2) {
            super(classLoader);
            this.codeSource = codeSource2;
        }

        public Class<?> defineClass(String str, byte[] bArr) {
            return defineClass(str, bArr, 0, bArr.length, this.codeSource);
        }

        public void linkClass(Class<?> cls) {
            resolveClass(cls);
        }
    }

    public GeneratedClassLoader createClassLoader(final ClassLoader classLoader, final Object obj) {
        return (Loader) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return new Loader(classLoader, (CodeSource) obj);
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: org.mozilla.javascript.PolicySecurityController$SecureCaller} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object callWithDomain(java.lang.Object r10, final org.mozilla.javascript.Context r11, org.mozilla.javascript.Callable r12, org.mozilla.javascript.Scriptable r13, org.mozilla.javascript.Scriptable r14, java.lang.Object[] r15) {
        /*
            r9 = this;
            org.mozilla.javascript.PolicySecurityController$2 r0 = new org.mozilla.javascript.PolicySecurityController$2
            r0.<init>(r11)
            java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0
            java.security.CodeSource r10 = (java.security.CodeSource) r10
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.PolicySecurityController$SecureCaller>>> r1 = callers
            monitor-enter(r1)
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.PolicySecurityController$SecureCaller>>> r2 = callers     // Catch:{ all -> 0x0067 }
            java.lang.Object r2 = r2.get(r10)     // Catch:{ all -> 0x0067 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x0067 }
            if (r2 != 0) goto L_0x0024
            java.util.WeakHashMap r2 = new java.util.WeakHashMap     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            java.util.Map<java.security.CodeSource, java.util.Map<java.lang.ClassLoader, java.lang.ref.SoftReference<org.mozilla.javascript.PolicySecurityController$SecureCaller>>> r3 = callers     // Catch:{ all -> 0x0067 }
            r3.put(r10, r2)     // Catch:{ all -> 0x0067 }
        L_0x0024:
            monitor-exit(r1)     // Catch:{ all -> 0x0067 }
            monitor-enter(r2)
            java.lang.Object r1 = r2.get(r0)     // Catch:{ all -> 0x0064 }
            java.lang.ref.SoftReference r1 = (java.lang.ref.SoftReference) r1     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0035
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0064 }
            org.mozilla.javascript.PolicySecurityController$SecureCaller r1 = (org.mozilla.javascript.PolicySecurityController.SecureCaller) r1     // Catch:{ all -> 0x0064 }
            goto L_0x0036
        L_0x0035:
            r1 = 0
        L_0x0036:
            if (r1 != 0) goto L_0x0058
            org.mozilla.javascript.PolicySecurityController$3 r1 = new org.mozilla.javascript.PolicySecurityController$3     // Catch:{ PrivilegedActionException -> 0x004d }
            r1.<init>(r0, r10)     // Catch:{ PrivilegedActionException -> 0x004d }
            java.lang.Object r10 = java.security.AccessController.doPrivileged(r1)     // Catch:{ PrivilegedActionException -> 0x004d }
            r1 = r10
            org.mozilla.javascript.PolicySecurityController$SecureCaller r1 = (org.mozilla.javascript.PolicySecurityController.SecureCaller) r1     // Catch:{ PrivilegedActionException -> 0x004d }
            java.lang.ref.SoftReference r10 = new java.lang.ref.SoftReference     // Catch:{ PrivilegedActionException -> 0x004d }
            r10.<init>(r1)     // Catch:{ PrivilegedActionException -> 0x004d }
            r2.put(r0, r10)     // Catch:{ PrivilegedActionException -> 0x004d }
            goto L_0x0058
        L_0x004d:
            r10 = move-exception
            java.lang.reflect.UndeclaredThrowableException r11 = new java.lang.reflect.UndeclaredThrowableException     // Catch:{ all -> 0x0064 }
            java.lang.Throwable r10 = r10.getCause()     // Catch:{ all -> 0x0064 }
            r11.<init>(r10)     // Catch:{ all -> 0x0064 }
            throw r11     // Catch:{ all -> 0x0064 }
        L_0x0058:
            r3 = r1
            monitor-exit(r2)     // Catch:{ all -> 0x0064 }
            r4 = r12
            r5 = r11
            r6 = r13
            r7 = r14
            r8 = r15
            java.lang.Object r10 = r3.call(r4, r5, r6, r7, r8)
            return r10
        L_0x0064:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0064 }
            throw r10
        L_0x0067:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0067 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.PolicySecurityController.callWithDomain(java.lang.Object, org.mozilla.javascript.Context, org.mozilla.javascript.Callable, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static byte[] loadBytecode() {
        String name = SecureCaller.class.getName();
        ClassFileWriter classFileWriter = new ClassFileWriter(name + "Impl", name, "<generated>");
        classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", 1);
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(183, name, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.add(177);
        classFileWriter.stopMethod(1);
        classFileWriter.startMethod(NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Callable;" + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", 17);
        for (int i = 1; i < 6; i++) {
            classFileWriter.addALoad(i);
        }
        classFileWriter.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(" + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter.add(176);
        classFileWriter.stopMethod(6);
        return classFileWriter.toByteArray();
    }
}
