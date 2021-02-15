package org.mozilla.javascript;

import com.medscape.android.settings.Settings;

public class NativeJavaTopPackage extends NativeJavaPackage implements Function, IdFunctionCall {
    private static final Object FTAG = "JavaTopPackage";
    private static final int Id_getClass = 1;
    private static final String[][] commonPackages = {new String[]{"java", "lang", "reflect"}, new String[]{"java", "io"}, new String[]{"java", "math"}, new String[]{"java", "net"}, new String[]{"java", "util", Settings.ZIP}, new String[]{"java", "text", "resources"}, new String[]{"java", "applet"}, new String[]{"javax", "swing"}};
    static final long serialVersionUID = -1455787259477709999L;

    NativeJavaTopPackage(ClassLoader classLoader) {
        super(true, "", classLoader);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return construct(context, scriptable, objArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.Scriptable construct(org.mozilla.javascript.Context r3, org.mozilla.javascript.Scriptable r4, java.lang.Object[] r5) {
        /*
            r2 = this;
            int r3 = r5.length
            r0 = 0
            if (r3 == 0) goto L_0x0018
            r3 = 0
            r3 = r5[r3]
            boolean r5 = r3 instanceof org.mozilla.javascript.Wrapper
            if (r5 == 0) goto L_0x0011
            org.mozilla.javascript.Wrapper r3 = (org.mozilla.javascript.Wrapper) r3
            java.lang.Object r3 = r3.unwrap()
        L_0x0011:
            boolean r5 = r3 instanceof java.lang.ClassLoader
            if (r5 == 0) goto L_0x0018
            java.lang.ClassLoader r3 = (java.lang.ClassLoader) r3
            goto L_0x0019
        L_0x0018:
            r3 = r0
        L_0x0019:
            if (r3 != 0) goto L_0x0021
            java.lang.String r3 = "msg.not.classloader"
            org.mozilla.javascript.Context.reportRuntimeError0(r3)
            return r0
        L_0x0021:
            org.mozilla.javascript.NativeJavaPackage r5 = new org.mozilla.javascript.NativeJavaPackage
            r0 = 1
            java.lang.String r1 = ""
            r5.<init>(r0, r1, r3)
            org.mozilla.javascript.ScriptRuntime.setObjectProtoAndParent(r5, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaTopPackage.construct(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.Object[]):org.mozilla.javascript.Scriptable");
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeJavaTopPackage nativeJavaTopPackage = new NativeJavaTopPackage(context.getApplicationClassLoader());
        nativeJavaTopPackage.setPrototype(getObjectPrototype(scriptable));
        nativeJavaTopPackage.setParentScope(scriptable);
        for (int i = 0; i != commonPackages.length; i++) {
            NativeJavaPackage nativeJavaPackage = nativeJavaTopPackage;
            int i2 = 0;
            while (true) {
                String[][] strArr = commonPackages;
                if (i2 == strArr[i].length) {
                    break;
                }
                nativeJavaPackage = nativeJavaPackage.forcePackage(strArr[i][i2], scriptable);
                i2++;
            }
        }
        IdFunctionObject idFunctionObject = new IdFunctionObject(nativeJavaTopPackage, FTAG, 1, "getClass", 1, scriptable);
        String[] topPackageNames = ScriptRuntime.getTopPackageNames();
        NativeJavaPackage[] nativeJavaPackageArr = new NativeJavaPackage[topPackageNames.length];
        for (int i3 = 0; i3 < topPackageNames.length; i3++) {
            nativeJavaPackageArr[i3] = (NativeJavaPackage) nativeJavaTopPackage.get(topPackageNames[i3], (Scriptable) nativeJavaTopPackage);
        }
        ScriptableObject scriptableObject = (ScriptableObject) scriptable;
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
        scriptableObject.defineProperty("Packages", (Object) nativeJavaTopPackage, 2);
        for (int i4 = 0; i4 < topPackageNames.length; i4++) {
            scriptableObject.defineProperty(topPackageNames[i4], (Object) nativeJavaPackageArr[i4], 2);
        }
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_getClass(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.Scriptable js_getClass(org.mozilla.javascript.Context r3, org.mozilla.javascript.Scriptable r4, java.lang.Object[] r5) {
        /*
            r2 = this;
            int r3 = r5.length
            if (r3 <= 0) goto L_0x003e
            r3 = 0
            r4 = r5[r3]
            boolean r4 = r4 instanceof org.mozilla.javascript.Wrapper
            if (r4 == 0) goto L_0x003e
            r4 = r5[r3]
            org.mozilla.javascript.Wrapper r4 = (org.mozilla.javascript.Wrapper) r4
            java.lang.Object r4 = r4.unwrap()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            r5 = r2
        L_0x001b:
            r0 = 46
            int r0 = r4.indexOf(r0, r3)
            r1 = -1
            if (r0 != r1) goto L_0x0029
            java.lang.String r3 = r4.substring(r3)
            goto L_0x002d
        L_0x0029:
            java.lang.String r3 = r4.substring(r3, r0)
        L_0x002d:
            java.lang.Object r3 = r5.get((java.lang.String) r3, (org.mozilla.javascript.Scriptable) r5)
            boolean r5 = r3 instanceof org.mozilla.javascript.Scriptable
            if (r5 == 0) goto L_0x003e
            r5 = r3
            org.mozilla.javascript.Scriptable r5 = (org.mozilla.javascript.Scriptable) r5
            if (r0 != r1) goto L_0x003b
            return r5
        L_0x003b:
            int r3 = r0 + 1
            goto L_0x001b
        L_0x003e:
            java.lang.String r3 = "msg.not.java.obj"
            org.mozilla.javascript.EvaluatorException r3 = org.mozilla.javascript.Context.reportRuntimeError0(r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaTopPackage.js_getClass(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.Object[]):org.mozilla.javascript.Scriptable");
    }
}
