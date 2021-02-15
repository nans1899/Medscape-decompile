package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

public class NativeJavaPackage extends ScriptableObject {
    static final long serialVersionUID = 7445054382212031523L;
    private transient ClassLoader classLoader;
    private Set<String> negativeCache;
    private String packageName;

    public String getClassName() {
        return "JavaPackage";
    }

    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    public boolean has(String str, Scriptable scriptable) {
        return true;
    }

    public void put(String str, Scriptable scriptable, Object obj) {
    }

    NativeJavaPackage(boolean z, String str, ClassLoader classLoader2) {
        this.negativeCache = null;
        this.packageName = str;
        this.classLoader = classLoader2;
    }

    public NativeJavaPackage(String str, ClassLoader classLoader2) {
        this(false, str, classLoader2);
    }

    public NativeJavaPackage(String str) {
        this(false, str, Context.getCurrentContext().getApplicationClassLoader());
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw Context.reportRuntimeError0("msg.pkg.int");
    }

    public Object get(String str, Scriptable scriptable) {
        return getPkgProperty(str, scriptable, true);
    }

    public Object get(int i, Scriptable scriptable) {
        return NOT_FOUND;
    }

    /* access modifiers changed from: package-private */
    public NativeJavaPackage forcePackage(String str, Scriptable scriptable) {
        String str2;
        Object obj = super.get(str, (Scriptable) this);
        if (obj != null && (obj instanceof NativeJavaPackage)) {
            return (NativeJavaPackage) obj;
        }
        if (this.packageName.length() == 0) {
            str2 = str;
        } else {
            str2 = this.packageName + "." + str;
        }
        NativeJavaPackage nativeJavaPackage = new NativeJavaPackage(true, str2, this.classLoader);
        ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage, scriptable);
        super.put(str, (Scriptable) this, (Object) nativeJavaPackage);
        return nativeJavaPackage;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0098, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object getPkgProperty(java.lang.String r6, org.mozilla.javascript.Scriptable r7, boolean r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.Object r0 = super.get((java.lang.String) r6, (org.mozilla.javascript.Scriptable) r7)     // Catch:{ all -> 0x0099 }
            java.lang.Object r1 = NOT_FOUND     // Catch:{ all -> 0x0099 }
            if (r0 == r1) goto L_0x000b
            monitor-exit(r5)
            return r0
        L_0x000b:
            java.util.Set<java.lang.String> r0 = r5.negativeCache     // Catch:{ all -> 0x0099 }
            r1 = 0
            if (r0 == 0) goto L_0x001a
            java.util.Set<java.lang.String> r0 = r5.negativeCache     // Catch:{ all -> 0x0099 }
            boolean r0 = r0.contains(r6)     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x001a
            monitor-exit(r5)
            return r1
        L_0x001a:
            java.lang.String r0 = r5.packageName     // Catch:{ all -> 0x0099 }
            int r0 = r0.length()     // Catch:{ all -> 0x0099 }
            if (r0 != 0) goto L_0x0024
            r0 = r6
            goto L_0x003a
        L_0x0024:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r0.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = r5.packageName     // Catch:{ all -> 0x0099 }
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            r2 = 46
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            r0.append(r6)     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0099 }
        L_0x003a:
            org.mozilla.javascript.Context r2 = org.mozilla.javascript.Context.getContext()     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.ClassShutter r3 = r2.getClassShutter()     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x004a
            boolean r3 = r3.visibleToScripts(r0)     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x006e
        L_0x004a:
            java.lang.ClassLoader r3 = r5.classLoader     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x0055
            java.lang.ClassLoader r3 = r5.classLoader     // Catch:{ all -> 0x0099 }
            java.lang.Class r3 = org.mozilla.javascript.Kit.classOrNull(r3, r0)     // Catch:{ all -> 0x0099 }
            goto L_0x0059
        L_0x0055:
            java.lang.Class r3 = org.mozilla.javascript.Kit.classOrNull(r0)     // Catch:{ all -> 0x0099 }
        L_0x0059:
            if (r3 == 0) goto L_0x006e
            org.mozilla.javascript.WrapFactory r1 = r2.getWrapFactory()     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.Scriptable r4 = getTopLevelScope(r5)     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.Scriptable r1 = r1.wrapJavaClass(r2, r4, r3)     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.Scriptable r2 = r5.getPrototype()     // Catch:{ all -> 0x0099 }
            r1.setPrototype(r2)     // Catch:{ all -> 0x0099 }
        L_0x006e:
            if (r1 != 0) goto L_0x0092
            if (r8 == 0) goto L_0x0082
            org.mozilla.javascript.NativeJavaPackage r1 = new org.mozilla.javascript.NativeJavaPackage     // Catch:{ all -> 0x0099 }
            r8 = 1
            java.lang.ClassLoader r2 = r5.classLoader     // Catch:{ all -> 0x0099 }
            r1.<init>(r8, r0, r2)     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.Scriptable r8 = r5.getParentScope()     // Catch:{ all -> 0x0099 }
            org.mozilla.javascript.ScriptRuntime.setObjectProtoAndParent(r1, r8)     // Catch:{ all -> 0x0099 }
            goto L_0x0092
        L_0x0082:
            java.util.Set<java.lang.String> r8 = r5.negativeCache     // Catch:{ all -> 0x0099 }
            if (r8 != 0) goto L_0x008d
            java.util.HashSet r8 = new java.util.HashSet     // Catch:{ all -> 0x0099 }
            r8.<init>()     // Catch:{ all -> 0x0099 }
            r5.negativeCache = r8     // Catch:{ all -> 0x0099 }
        L_0x008d:
            java.util.Set<java.lang.String> r8 = r5.negativeCache     // Catch:{ all -> 0x0099 }
            r8.add(r6)     // Catch:{ all -> 0x0099 }
        L_0x0092:
            if (r1 == 0) goto L_0x0097
            super.put((java.lang.String) r6, (org.mozilla.javascript.Scriptable) r7, (java.lang.Object) r1)     // Catch:{ all -> 0x0099 }
        L_0x0097:
            monitor-exit(r5)
            return r1
        L_0x0099:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaPackage.getPkgProperty(java.lang.String, org.mozilla.javascript.Scriptable, boolean):java.lang.Object");
    }

    public Object getDefaultValue(Class<?> cls) {
        return toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.classLoader = Context.getCurrentContext().getApplicationClassLoader();
    }

    public String toString() {
        return "[JavaPackage " + this.packageName + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeJavaPackage)) {
            return false;
        }
        NativeJavaPackage nativeJavaPackage = (NativeJavaPackage) obj;
        if (!this.packageName.equals(nativeJavaPackage.packageName) || this.classLoader != nativeJavaPackage.classLoader) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.packageName.hashCode();
        ClassLoader classLoader2 = this.classLoader;
        return hashCode ^ (classLoader2 == null ? 0 : classLoader2.hashCode());
    }
}
