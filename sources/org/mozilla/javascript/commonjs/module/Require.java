package org.mozilla.javascript.commonjs.module;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Require extends BaseFunction {
    private static final ThreadLocal<Map<String, Scriptable>> loadingModuleInterfaces = new ThreadLocal<>();
    private static final long serialVersionUID = 1;
    private final Map<String, Scriptable> exportedModuleInterfaces = new ConcurrentHashMap();
    private final Object loadLock = new Object();
    private Scriptable mainExports;
    private String mainModuleId = null;
    private final ModuleScriptProvider moduleScriptProvider;
    private final Scriptable nativeScope;
    private final Scriptable paths;
    private final Script postExec;
    private final Script preExec;
    private final boolean sandboxed;

    public int getArity() {
        return 1;
    }

    public String getFunctionName() {
        return "require";
    }

    public int getLength() {
        return 1;
    }

    public Require(Context context, Scriptable scriptable, ModuleScriptProvider moduleScriptProvider2, Script script, Script script2, boolean z) {
        this.moduleScriptProvider = moduleScriptProvider2;
        this.nativeScope = scriptable;
        this.sandboxed = z;
        this.preExec = script;
        this.postExec = script2;
        setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
        if (!z) {
            Scriptable newArray = context.newArray(scriptable, 0);
            this.paths = newArray;
            defineReadOnlyProperty(this, "paths", newArray);
            return;
        }
        this.paths = null;
    }

    public Scriptable requireMain(Context context, String str) {
        String str2 = this.mainModuleId;
        if (str2 == null) {
            try {
                if (this.moduleScriptProvider.getModuleScript(context, str, (URI) null, (URI) null, this.paths) != null) {
                    this.mainExports = getExportedModuleInterface(context, str, (URI) null, (URI) null, true);
                } else if (!this.sandboxed) {
                    URI uri = null;
                    try {
                        uri = new URI(str);
                    } catch (URISyntaxException unused) {
                    }
                    if (uri == null || !uri.isAbsolute()) {
                        File file = new File(str);
                        if (file.isFile()) {
                            uri = file.toURI();
                        } else {
                            Scriptable scriptable = this.nativeScope;
                            throw ScriptRuntime.throwError(context, scriptable, "Module \"" + str + "\" not found.");
                        }
                    }
                    URI uri2 = uri;
                    this.mainExports = getExportedModuleInterface(context, uri2.toString(), uri2, (URI) null, true);
                }
                this.mainModuleId = str;
                return this.mainExports;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } else if (str2.equals(str)) {
            return this.mainExports;
        } else {
            throw new IllegalStateException("Main module already set to " + this.mainModuleId);
        }
    }

    public void install(Scriptable scriptable) {
        ScriptableObject.putProperty(scriptable, "require", (Object) this);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        URI uri;
        URI uri2;
        if (objArr == null || objArr.length < 1) {
            throw ScriptRuntime.throwError(context, scriptable, "require() needs one argument");
        }
        String str = (String) Context.jsToJava(objArr[0], String.class);
        if (!str.startsWith("./") && !str.startsWith("../")) {
            uri = null;
            uri2 = null;
        } else if (scriptable2 instanceof ModuleScope) {
            ModuleScope moduleScope = (ModuleScope) scriptable2;
            URI base = moduleScope.getBase();
            URI uri3 = moduleScope.getUri();
            URI resolve = uri3.resolve(str);
            if (base == null) {
                str = resolve.toString();
            } else {
                str = base.relativize(uri3).resolve(str).toString();
                if (str.charAt(0) == '.') {
                    if (!this.sandboxed) {
                        str = resolve.toString();
                    } else {
                        throw ScriptRuntime.throwError(context, scriptable, "Module \"" + str + "\" is not contained in sandbox.");
                    }
                }
            }
            uri = resolve;
            uri2 = base;
        } else {
            throw ScriptRuntime.throwError(context, scriptable, "Can't resolve relative module ID \"" + str + "\" when require() is used outside of a module");
        }
        return getExportedModuleInterface(context, str, uri, uri2, false);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.throwError(context, scriptable, "require() can not be invoked as a constructor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009a, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.Scriptable getExportedModuleInterface(org.mozilla.javascript.Context r10, java.lang.String r11, java.net.URI r12, java.net.URI r13, boolean r14) {
        /*
            r9 = this;
            java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable> r0 = r9.exportedModuleInterfaces
            java.lang.Object r0 = r0.get(r11)
            org.mozilla.javascript.Scriptable r0 = (org.mozilla.javascript.Scriptable) r0
            if (r0 == 0) goto L_0x0015
            if (r14 != 0) goto L_0x000d
            return r0
        L_0x000d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Attempt to set main module after it was loaded"
            r10.<init>(r11)
            throw r10
        L_0x0015:
            java.lang.ThreadLocal<java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable>> r0 = loadingModuleInterfaces
            java.lang.Object r0 = r0.get()
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x0028
            java.lang.Object r1 = r0.get(r11)
            org.mozilla.javascript.Scriptable r1 = (org.mozilla.javascript.Scriptable) r1
            if (r1 == 0) goto L_0x0028
            return r1
        L_0x0028:
            java.lang.Object r1 = r9.loadLock
            monitor-enter(r1)
            java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable> r2 = r9.exportedModuleInterfaces     // Catch:{ all -> 0x00af }
            java.lang.Object r2 = r2.get(r11)     // Catch:{ all -> 0x00af }
            org.mozilla.javascript.Scriptable r2 = (org.mozilla.javascript.Scriptable) r2     // Catch:{ all -> 0x00af }
            if (r2 == 0) goto L_0x0037
            monitor-exit(r1)     // Catch:{ all -> 0x00af }
            return r2
        L_0x0037:
            org.mozilla.javascript.commonjs.module.ModuleScript r6 = r9.getModule(r10, r11, r12, r13)     // Catch:{ all -> 0x00af }
            boolean r12 = r9.sandboxed     // Catch:{ all -> 0x00af }
            if (r12 == 0) goto L_0x0063
            boolean r12 = r6.isSandboxed()     // Catch:{ all -> 0x00af }
            if (r12 == 0) goto L_0x0046
            goto L_0x0063
        L_0x0046:
            org.mozilla.javascript.Scriptable r12 = r9.nativeScope     // Catch:{ all -> 0x00af }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00af }
            r13.<init>()     // Catch:{ all -> 0x00af }
            java.lang.String r14 = "Module \""
            r13.append(r14)     // Catch:{ all -> 0x00af }
            r13.append(r11)     // Catch:{ all -> 0x00af }
            java.lang.String r11 = "\" is not contained in sandbox."
            r13.append(r11)     // Catch:{ all -> 0x00af }
            java.lang.String r11 = r13.toString()     // Catch:{ all -> 0x00af }
            org.mozilla.javascript.JavaScriptException r10 = org.mozilla.javascript.ScriptRuntime.throwError(r10, r12, r11)     // Catch:{ all -> 0x00af }
            throw r10     // Catch:{ all -> 0x00af }
        L_0x0063:
            org.mozilla.javascript.Scriptable r12 = r9.nativeScope     // Catch:{ all -> 0x00af }
            org.mozilla.javascript.Scriptable r12 = r10.newObject(r12)     // Catch:{ all -> 0x00af }
            if (r0 != 0) goto L_0x006d
            r13 = 1
            goto L_0x006e
        L_0x006d:
            r13 = 0
        L_0x006e:
            if (r13 == 0) goto L_0x007a
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x00af }
            r0.<init>()     // Catch:{ all -> 0x00af }
            java.lang.ThreadLocal<java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable>> r2 = loadingModuleInterfaces     // Catch:{ all -> 0x00af }
            r2.set(r0)     // Catch:{ all -> 0x00af }
        L_0x007a:
            r0.put(r11, r12)     // Catch:{ all -> 0x00af }
            r8 = 0
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r7 = r14
            org.mozilla.javascript.Scriptable r10 = r2.executeModuleScript(r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x009d }
            if (r12 == r10) goto L_0x008d
            r0.put(r11, r10)     // Catch:{ RuntimeException -> 0x009d }
            r12 = r10
        L_0x008d:
            if (r13 == 0) goto L_0x0099
            java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable> r10 = r9.exportedModuleInterfaces     // Catch:{ all -> 0x00af }
            r10.putAll(r0)     // Catch:{ all -> 0x00af }
            java.lang.ThreadLocal<java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable>> r10 = loadingModuleInterfaces     // Catch:{ all -> 0x00af }
            r10.set(r8)     // Catch:{ all -> 0x00af }
        L_0x0099:
            monitor-exit(r1)     // Catch:{ all -> 0x00af }
            return r12
        L_0x009b:
            r10 = move-exception
            goto L_0x00a2
        L_0x009d:
            r10 = move-exception
            r0.remove(r11)     // Catch:{ all -> 0x009b }
            throw r10     // Catch:{ all -> 0x009b }
        L_0x00a2:
            if (r13 == 0) goto L_0x00ae
            java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable> r11 = r9.exportedModuleInterfaces     // Catch:{ all -> 0x00af }
            r11.putAll(r0)     // Catch:{ all -> 0x00af }
            java.lang.ThreadLocal<java.util.Map<java.lang.String, org.mozilla.javascript.Scriptable>> r11 = loadingModuleInterfaces     // Catch:{ all -> 0x00af }
            r11.set(r8)     // Catch:{ all -> 0x00af }
        L_0x00ae:
            throw r10     // Catch:{ all -> 0x00af }
        L_0x00af:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00af }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.commonjs.module.Require.getExportedModuleInterface(org.mozilla.javascript.Context, java.lang.String, java.net.URI, java.net.URI, boolean):org.mozilla.javascript.Scriptable");
    }

    private Scriptable executeModuleScript(Context context, String str, Scriptable scriptable, ModuleScript moduleScript, boolean z) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(this.nativeScope);
        URI uri = moduleScript.getUri();
        URI base = moduleScript.getBase();
        defineReadOnlyProperty(scriptableObject, "id", str);
        if (!this.sandboxed) {
            defineReadOnlyProperty(scriptableObject, "uri", uri.toString());
        }
        ModuleScope moduleScope = new ModuleScope(this.nativeScope, uri, base);
        moduleScope.put("exports", (Scriptable) moduleScope, (Object) scriptable);
        moduleScope.put("module", (Scriptable) moduleScope, (Object) scriptableObject);
        scriptableObject.put("exports", (Scriptable) scriptableObject, (Object) scriptable);
        install(moduleScope);
        if (z) {
            defineReadOnlyProperty(this, "main", scriptableObject);
        }
        executeOptionalScript(this.preExec, context, moduleScope);
        moduleScript.getScript().exec(context, moduleScope);
        executeOptionalScript(this.postExec, context, moduleScope);
        return ScriptRuntime.toObject(this.nativeScope, ScriptableObject.getProperty((Scriptable) scriptableObject, "exports"));
    }

    private static void executeOptionalScript(Script script, Context context, Scriptable scriptable) {
        if (script != null) {
            script.exec(context, scriptable);
        }
    }

    private static void defineReadOnlyProperty(ScriptableObject scriptableObject, String str, Object obj) {
        ScriptableObject.putProperty((Scriptable) scriptableObject, str, obj);
        scriptableObject.setAttributes(str, 5);
    }

    private ModuleScript getModule(Context context, String str, URI uri, URI uri2) {
        try {
            ModuleScript moduleScript = this.moduleScriptProvider.getModuleScript(context, str, uri, uri2, this.paths);
            if (moduleScript != null) {
                return moduleScript;
            }
            Scriptable scriptable = this.nativeScope;
            throw ScriptRuntime.throwError(context, scriptable, "Module \"" + str + "\" not found.");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
    }
}
