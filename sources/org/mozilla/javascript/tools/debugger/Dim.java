package org.mozilla.javascript.tools.debugger;

import com.google.android.gms.ads.AdError;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeCall;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.debug.DebugFrame;
import org.mozilla.javascript.debug.DebuggableObject;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;

public class Dim {
    public static final int BREAK = 4;
    public static final int EXIT = 5;
    public static final int GO = 3;
    private static final int IPROXY_COMPILE_SCRIPT = 2;
    private static final int IPROXY_DEBUG = 0;
    private static final int IPROXY_EVAL_SCRIPT = 3;
    private static final int IPROXY_LISTEN = 1;
    private static final int IPROXY_OBJECT_IDS = 7;
    private static final int IPROXY_OBJECT_PROPERTY = 6;
    private static final int IPROXY_OBJECT_TO_STRING = 5;
    private static final int IPROXY_STRING_IS_COMPILABLE = 4;
    public static final int STEP_INTO = 1;
    public static final int STEP_OUT = 2;
    public static final int STEP_OVER = 0;
    /* access modifiers changed from: private */
    public boolean breakFlag;
    /* access modifiers changed from: private */
    public boolean breakOnEnter;
    private boolean breakOnExceptions;
    /* access modifiers changed from: private */
    public boolean breakOnReturn;
    private GuiCallback callback;
    /* access modifiers changed from: private */
    public ContextFactory contextFactory;
    private StackFrame evalFrame;
    private String evalRequest;
    private String evalResult;
    private Object eventThreadMonitor = new Object();
    private int frameIndex = -1;
    private final Map<String, FunctionSource> functionNames = Collections.synchronizedMap(new HashMap());
    private final Map<DebuggableScript, FunctionSource> functionToSource = Collections.synchronizedMap(new HashMap());
    private boolean insideInterruptLoop;
    private volatile ContextData interruptedContextData;
    private DimIProxy listener;
    private Object monitor = new Object();
    private volatile int returnValue = -1;
    /* access modifiers changed from: private */
    public ScopeProvider scopeProvider;
    private SourceProvider sourceProvider;
    private final Map<String, SourceInfo> urlToSourceInfo = Collections.synchronizedMap(new HashMap());

    public void setGuiCallback(GuiCallback guiCallback) {
        this.callback = guiCallback;
    }

    public void setBreak() {
        this.breakFlag = true;
    }

    public void setScopeProvider(ScopeProvider scopeProvider2) {
        this.scopeProvider = scopeProvider2;
    }

    public void setSourceProvider(SourceProvider sourceProvider2) {
        this.sourceProvider = sourceProvider2;
    }

    public void contextSwitch(int i) {
        this.frameIndex = i;
    }

    public void setBreakOnExceptions(boolean z) {
        this.breakOnExceptions = z;
    }

    public void setBreakOnEnter(boolean z) {
        this.breakOnEnter = z;
    }

    public void setBreakOnReturn(boolean z) {
        this.breakOnReturn = z;
    }

    public void attachTo(ContextFactory contextFactory2) {
        detach();
        this.contextFactory = contextFactory2;
        DimIProxy dimIProxy = new DimIProxy(1);
        this.listener = dimIProxy;
        contextFactory2.addListener(dimIProxy);
    }

    public void detach() {
        DimIProxy dimIProxy = this.listener;
        if (dimIProxy != null) {
            this.contextFactory.removeListener(dimIProxy);
            this.contextFactory = null;
            this.listener = null;
        }
    }

    public void dispose() {
        detach();
    }

    /* access modifiers changed from: private */
    public FunctionSource getFunctionSource(DebuggableScript debuggableScript) {
        String loadSource;
        FunctionSource functionSource = functionSource(debuggableScript);
        if (functionSource != null) {
            return functionSource;
        }
        String normalizedUrl = getNormalizedUrl(debuggableScript);
        if (sourceInfo(normalizedUrl) != null || debuggableScript.isGeneratedScript() || (loadSource = loadSource(normalizedUrl)) == null) {
            return functionSource;
        }
        DebuggableScript debuggableScript2 = debuggableScript;
        while (true) {
            DebuggableScript parent = debuggableScript2.getParent();
            if (parent == null) {
                registerTopScript(debuggableScript2, loadSource);
                return functionSource(debuggableScript);
            }
            debuggableScript2 = parent;
        }
    }

    private String loadSource(String str) {
        InputStream inputStream;
        String systemProperty;
        int indexOf = str.indexOf(35);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        String str2 = null;
        try {
            if (str.indexOf(58) < 0) {
                try {
                    if (str.startsWith("~/") && (systemProperty = SecurityUtilities.getSystemProperty("user.home")) != null) {
                        File file = new File(new File(systemProperty), str.substring(2));
                        if (file.exists()) {
                            inputStream = new FileInputStream(file);
                            str2 = Kit.readReader(new InputStreamReader(inputStream));
                            inputStream.close();
                            return str2;
                        }
                    }
                    File file2 = new File(str);
                    if (file2.exists()) {
                        inputStream = new FileInputStream(file2);
                        str2 = Kit.readReader(new InputStreamReader(inputStream));
                        inputStream.close();
                        return str2;
                    }
                } catch (SecurityException unused) {
                }
                if (str.startsWith("//")) {
                    str = "http:" + str;
                } else if (str.startsWith("/")) {
                    str = "http://127.0.0.1" + str;
                } else {
                    str = "http://" + str;
                }
            }
            inputStream = new URL(str).openStream();
            str2 = Kit.readReader(new InputStreamReader(inputStream));
            inputStream.close();
        } catch (IOException e) {
            System.err.println("Failed to load source from " + str + ": " + e);
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
        return str2;
    }

    /* access modifiers changed from: private */
    public void registerTopScript(DebuggableScript debuggableScript, String str) {
        int i;
        String source;
        if (debuggableScript.isTopLevel()) {
            String normalizedUrl = getNormalizedUrl(debuggableScript);
            DebuggableScript[] allFunctions = getAllFunctions(debuggableScript);
            SourceProvider sourceProvider2 = this.sourceProvider;
            if (!(sourceProvider2 == null || (source = sourceProvider2.getSource(debuggableScript)) == null)) {
                str = source;
            }
            SourceInfo sourceInfo = new SourceInfo(str, allFunctions, normalizedUrl);
            synchronized (this.urlToSourceInfo) {
                SourceInfo sourceInfo2 = this.urlToSourceInfo.get(normalizedUrl);
                if (sourceInfo2 != null) {
                    sourceInfo.copyBreakpointsFrom(sourceInfo2);
                }
                this.urlToSourceInfo.put(normalizedUrl, sourceInfo);
                for (int i2 = 0; i2 != sourceInfo.functionSourcesTop(); i2++) {
                    FunctionSource functionSource = sourceInfo.functionSource(i2);
                    String name = functionSource.name();
                    if (name.length() != 0) {
                        this.functionNames.put(name, functionSource);
                    }
                }
            }
            synchronized (this.functionToSource) {
                for (i = 0; i != allFunctions.length; i++) {
                    this.functionToSource.put(allFunctions[i], sourceInfo.functionSource(i));
                }
            }
            this.callback.updateSourceText(sourceInfo);
            return;
        }
        throw new IllegalArgumentException();
    }

    private FunctionSource functionSource(DebuggableScript debuggableScript) {
        return this.functionToSource.get(debuggableScript);
    }

    public String[] functionNames() {
        String[] strArr;
        synchronized (this.urlToSourceInfo) {
            strArr = (String[]) this.functionNames.keySet().toArray(new String[this.functionNames.size()]);
        }
        return strArr;
    }

    public FunctionSource functionSourceByName(String str) {
        return this.functionNames.get(str);
    }

    public SourceInfo sourceInfo(String str) {
        return this.urlToSourceInfo.get(str);
    }

    private String getNormalizedUrl(DebuggableScript debuggableScript) {
        String sourceName = debuggableScript.getSourceName();
        if (sourceName == null) {
            return "<stdin>";
        }
        int length = sourceName.length();
        StringBuffer stringBuffer = null;
        int i = 0;
        while (true) {
            int indexOf = sourceName.indexOf(35, i);
            if (indexOf < 0) {
                break;
            }
            int i2 = indexOf + 1;
            int i3 = i2;
            while (i3 != length) {
                char charAt = sourceName.charAt(i3);
                if ('0' > charAt || charAt > '9') {
                    break;
                }
                i3++;
            }
            String str = "(eval)";
            if (i3 == i2 || !str.regionMatches(0, sourceName, i3, 6)) {
                str = null;
            } else {
                i = i3 + 6;
            }
            if (str == null) {
                break;
            }
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
                stringBuffer.append(sourceName.substring(0, indexOf));
            }
            stringBuffer.append(str);
        }
        if (stringBuffer == null) {
            return sourceName;
        }
        if (i != length) {
            stringBuffer.append(sourceName.substring(i));
        }
        return stringBuffer.toString();
    }

    private static DebuggableScript[] getAllFunctions(DebuggableScript debuggableScript) {
        ObjArray objArray = new ObjArray();
        collectFunctions_r(debuggableScript, objArray);
        DebuggableScript[] debuggableScriptArr = new DebuggableScript[objArray.size()];
        objArray.toArray(debuggableScriptArr);
        return debuggableScriptArr;
    }

    private static void collectFunctions_r(DebuggableScript debuggableScript, ObjArray objArray) {
        objArray.add(debuggableScript);
        for (int i = 0; i != debuggableScript.getFunctionCount(); i++) {
            collectFunctions_r(debuggableScript.getFunction(i), objArray);
        }
    }

    public void clearAllBreakpoints() {
        for (SourceInfo removeAllBreakpoints : this.urlToSourceInfo.values()) {
            removeAllBreakpoints.removeAllBreakpoints();
        }
    }

    /* access modifiers changed from: private */
    public void handleBreakpointHit(StackFrame stackFrame, Context context) {
        this.breakFlag = false;
        interrupted(context, stackFrame, (Throwable) null);
    }

    /* access modifiers changed from: private */
    public void handleExceptionThrown(Context context, Throwable th, StackFrame stackFrame) {
        if (this.breakOnExceptions) {
            ContextData contextData = stackFrame.contextData();
            if (contextData.lastProcessedException != th) {
                interrupted(context, stackFrame, th);
                Throwable unused = contextData.lastProcessedException = th;
            }
        }
    }

    public ContextData currentContextData() {
        return this.interruptedContextData;
    }

    public void setReturnValue(int i) {
        synchronized (this.monitor) {
            this.returnValue = i;
            this.monitor.notify();
        }
    }

    public void go() {
        synchronized (this.monitor) {
            this.returnValue = 3;
            this.monitor.notifyAll();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String eval(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "undefined"
            if (r5 != 0) goto L_0x0005
            return r0
        L_0x0005:
            org.mozilla.javascript.tools.debugger.Dim$ContextData r1 = r4.currentContextData()
            if (r1 == 0) goto L_0x0052
            int r2 = r4.frameIndex
            int r3 = r1.frameCount()
            if (r2 < r3) goto L_0x0014
            goto L_0x0052
        L_0x0014:
            int r2 = r4.frameIndex
            org.mozilla.javascript.tools.debugger.Dim$StackFrame r2 = r1.getFrame(r2)
            boolean r1 = r1.eventThreadFlag
            if (r1 == 0) goto L_0x0029
            org.mozilla.javascript.Context r0 = org.mozilla.javascript.Context.getCurrentContext()
            java.lang.String r5 = do_eval(r0, r2, r5)
            goto L_0x004e
        L_0x0029:
            java.lang.Object r1 = r4.monitor
            monitor-enter(r1)
            boolean r3 = r4.insideInterruptLoop     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x004c
            r4.evalRequest = r5     // Catch:{ all -> 0x004f }
            r4.evalFrame = r2     // Catch:{ all -> 0x004f }
            java.lang.Object r5 = r4.monitor     // Catch:{ all -> 0x004f }
            r5.notify()     // Catch:{ all -> 0x004f }
        L_0x0039:
            java.lang.Object r5 = r4.monitor     // Catch:{ InterruptedException -> 0x0043 }
            r5.wait()     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.String r5 = r4.evalRequest     // Catch:{ all -> 0x004f }
            if (r5 != 0) goto L_0x0039
            goto L_0x004a
        L_0x0043:
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x004f }
            r5.interrupt()     // Catch:{ all -> 0x004f }
        L_0x004a:
            java.lang.String r0 = r4.evalResult     // Catch:{ all -> 0x004f }
        L_0x004c:
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            r5 = r0
        L_0x004e:
            return r5
        L_0x004f:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            throw r5
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.Dim.eval(java.lang.String):java.lang.String");
    }

    public void compileScript(String str, String str2) {
        DimIProxy dimIProxy = new DimIProxy(2);
        String unused = dimIProxy.url = str;
        String unused2 = dimIProxy.text = str2;
        dimIProxy.withContext();
    }

    public void evalScript(String str, String str2) {
        DimIProxy dimIProxy = new DimIProxy(3);
        String unused = dimIProxy.url = str;
        String unused2 = dimIProxy.text = str2;
        dimIProxy.withContext();
    }

    public String objectToString(Object obj) {
        DimIProxy dimIProxy = new DimIProxy(5);
        Object unused = dimIProxy.object = obj;
        dimIProxy.withContext();
        return dimIProxy.stringResult;
    }

    public boolean stringIsCompilableUnit(String str) {
        DimIProxy dimIProxy = new DimIProxy(4);
        String unused = dimIProxy.text = str;
        dimIProxy.withContext();
        return dimIProxy.booleanResult;
    }

    public Object getObjectProperty(Object obj, Object obj2) {
        DimIProxy dimIProxy = new DimIProxy(6);
        Object unused = dimIProxy.object = obj;
        Object unused2 = dimIProxy.id = obj2;
        dimIProxy.withContext();
        return dimIProxy.objectResult;
    }

    public Object[] getObjectIds(Object obj) {
        DimIProxy dimIProxy = new DimIProxy(7);
        Object unused = dimIProxy.object = obj;
        dimIProxy.withContext();
        return dimIProxy.objectArrayResult;
    }

    /* access modifiers changed from: private */
    public Object getObjectPropertyImpl(Context context, Object obj, Object obj2) {
        Scriptable scriptable = (Scriptable) obj;
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.equals("this")) {
                return scriptable;
            }
            if (str.equals("__proto__")) {
                return scriptable.getPrototype();
            }
            if (str.equals("__parent__")) {
                return scriptable.getParentScope();
            }
            Object property = ScriptableObject.getProperty(scriptable, str);
            if (property == ScriptableObject.NOT_FOUND) {
                return Undefined.instance;
            }
            return property;
        }
        Object property2 = ScriptableObject.getProperty(scriptable, ((Integer) obj2).intValue());
        return property2 == ScriptableObject.NOT_FOUND ? Undefined.instance : property2;
    }

    /* access modifiers changed from: private */
    public Object[] getObjectIdsImpl(Context context, Object obj) {
        Object[] objArr;
        if (!(obj instanceof Scriptable) || obj == Undefined.instance) {
            return Context.emptyArgs;
        }
        Scriptable scriptable = (Scriptable) obj;
        if (scriptable instanceof DebuggableObject) {
            objArr = ((DebuggableObject) scriptable).getAllIds();
        } else {
            objArr = scriptable.getIds();
        }
        Scriptable prototype = scriptable.getPrototype();
        Scriptable parentScope = scriptable.getParentScope();
        char c = 1;
        int i = prototype != null ? 1 : 0;
        if (parentScope != null) {
            i++;
        }
        if (i == 0) {
            return objArr;
        }
        Object[] objArr2 = new Object[(objArr.length + i)];
        System.arraycopy(objArr, 0, objArr2, i, objArr.length);
        if (prototype != null) {
            objArr2[0] = "__proto__";
        } else {
            c = 0;
        }
        if (parentScope != null) {
            objArr2[c] = "__parent__";
        }
        return objArr2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:11|12|13|14|15|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        r8.interruptedContextData = null;
        r8.eventThreadMonitor.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0103, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        if (r2 == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        if (r8.interruptedContextData != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        org.mozilla.javascript.Kit.codeBug();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r8.frameIndex = r0.frameCount() - 1;
        r5 = java.lang.Thread.currentThread().toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0045, code lost:
        if (r11 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0049, code lost:
        r11 = r11.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004e, code lost:
        if (r1 != false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0050, code lost:
        r1 = r8.monitor;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0052, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0055, code lost:
        if (r8.insideInterruptLoop == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0057, code lost:
        org.mozilla.javascript.Kit.codeBug();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005a, code lost:
        r8.insideInterruptLoop = true;
        r8.evalRequest = null;
        r8.returnValue = -1;
        r8.callback.enterInterrupt(r10, r5, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r8.monitor.wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006c, code lost:
        if (r8.evalRequest == null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006e, code lost:
        r8.evalResult = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r8.evalResult = do_eval(r9, r8.evalFrame, r8.evalRequest);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r8.evalRequest = null;
        r8.evalFrame = null;
        r8.monitor.notify();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0084, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0085, code lost:
        r8.evalRequest = null;
        r8.evalFrame = null;
        r8.monitor.notify();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008e, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0091, code lost:
        if (r8.returnValue == -1) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0093, code lost:
        r9 = r8.returnValue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0096, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0098, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x009f, code lost:
        r9 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a4, code lost:
        r8.insideInterruptLoop = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00a6, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00aa, code lost:
        r8.returnValue = -1;
        r8.callback.enterInterrupt(r10, r5, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00b3, code lost:
        if (r8.returnValue != -1) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r8.callback.dispatchNextGuiEvent();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r9 = r8.returnValue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x00f7, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x00fa, code lost:
        monitor-enter(r8.eventThreadMonitor);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void interrupted(org.mozilla.javascript.Context r9, org.mozilla.javascript.tools.debugger.Dim.StackFrame r10, java.lang.Throwable r11) {
        /*
            r8 = this;
            org.mozilla.javascript.tools.debugger.Dim$ContextData r0 = r10.contextData()
            org.mozilla.javascript.tools.debugger.GuiCallback r1 = r8.callback
            boolean r1 = r1.isGuiEventThread()
            boolean unused = r0.eventThreadFlag = r1
            java.lang.Object r2 = r8.eventThreadMonitor
            monitor-enter(r2)
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x001b
            org.mozilla.javascript.tools.debugger.Dim$ContextData r5 = r8.interruptedContextData     // Catch:{ all -> 0x0107 }
            if (r5 == 0) goto L_0x0027
            monitor-exit(r2)     // Catch:{ all -> 0x0107 }
            r2 = 1
            goto L_0x002b
        L_0x001b:
            org.mozilla.javascript.tools.debugger.Dim$ContextData r5 = r8.interruptedContextData     // Catch:{ all -> 0x0107 }
            if (r5 == 0) goto L_0x0027
            java.lang.Object r5 = r8.eventThreadMonitor     // Catch:{ InterruptedException -> 0x0025 }
            r5.wait()     // Catch:{ InterruptedException -> 0x0025 }
            goto L_0x001b
        L_0x0025:
            monitor-exit(r2)     // Catch:{ all -> 0x0107 }
            return
        L_0x0027:
            r8.interruptedContextData = r0     // Catch:{ all -> 0x0107 }
            monitor-exit(r2)     // Catch:{ all -> 0x0107 }
            r2 = 0
        L_0x002b:
            if (r2 == 0) goto L_0x002e
            return
        L_0x002e:
            org.mozilla.javascript.tools.debugger.Dim$ContextData r2 = r8.interruptedContextData
            if (r2 != 0) goto L_0x0035
            org.mozilla.javascript.Kit.codeBug()
        L_0x0035:
            r2 = 0
            int r5 = r0.frameCount()     // Catch:{ all -> 0x00f7 }
            int r5 = r5 - r4
            r8.frameIndex = r5     // Catch:{ all -> 0x00f7 }
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00f7 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00f7 }
            if (r11 != 0) goto L_0x0049
            r11 = r2
            goto L_0x004d
        L_0x0049:
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00f7 }
        L_0x004d:
            r6 = -1
            if (r1 != 0) goto L_0x00aa
            java.lang.Object r1 = r8.monitor     // Catch:{ all -> 0x00f7 }
            monitor-enter(r1)     // Catch:{ all -> 0x00f7 }
            boolean r7 = r8.insideInterruptLoop     // Catch:{ all -> 0x00a7 }
            if (r7 == 0) goto L_0x005a
            org.mozilla.javascript.Kit.codeBug()     // Catch:{ all -> 0x00a7 }
        L_0x005a:
            r8.insideInterruptLoop = r4     // Catch:{ all -> 0x00a7 }
            r8.evalRequest = r2     // Catch:{ all -> 0x00a7 }
            r8.returnValue = r6     // Catch:{ all -> 0x00a7 }
            org.mozilla.javascript.tools.debugger.GuiCallback r7 = r8.callback     // Catch:{ all -> 0x00a7 }
            r7.enterInterrupt(r10, r5, r11)     // Catch:{ all -> 0x00a7 }
        L_0x0065:
            java.lang.Object r10 = r8.monitor     // Catch:{ InterruptedException -> 0x0098 }
            r10.wait()     // Catch:{ InterruptedException -> 0x0098 }
            java.lang.String r10 = r8.evalRequest     // Catch:{ all -> 0x0096 }
            if (r10 == 0) goto L_0x008f
            r8.evalResult = r2     // Catch:{ all -> 0x0096 }
            org.mozilla.javascript.tools.debugger.Dim$StackFrame r10 = r8.evalFrame     // Catch:{ all -> 0x0084 }
            java.lang.String r11 = r8.evalRequest     // Catch:{ all -> 0x0084 }
            java.lang.String r10 = do_eval(r9, r10, r11)     // Catch:{ all -> 0x0084 }
            r8.evalResult = r10     // Catch:{ all -> 0x0084 }
            r8.evalRequest = r2     // Catch:{ all -> 0x0096 }
            r8.evalFrame = r2     // Catch:{ all -> 0x0096 }
            java.lang.Object r10 = r8.monitor     // Catch:{ all -> 0x0096 }
            r10.notify()     // Catch:{ all -> 0x0096 }
            goto L_0x0065
        L_0x0084:
            r9 = move-exception
            r8.evalRequest = r2     // Catch:{ all -> 0x0096 }
            r8.evalFrame = r2     // Catch:{ all -> 0x0096 }
            java.lang.Object r10 = r8.monitor     // Catch:{ all -> 0x0096 }
            r10.notify()     // Catch:{ all -> 0x0096 }
            throw r9     // Catch:{ all -> 0x0096 }
        L_0x008f:
            int r10 = r8.returnValue     // Catch:{ all -> 0x0096 }
            if (r10 == r6) goto L_0x0065
            int r9 = r8.returnValue     // Catch:{ all -> 0x0096 }
            goto L_0x00a0
        L_0x0096:
            r9 = move-exception
            goto L_0x00a4
        L_0x0098:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0096 }
            r9.interrupt()     // Catch:{ all -> 0x0096 }
            r9 = -1
        L_0x00a0:
            r8.insideInterruptLoop = r3     // Catch:{ all -> 0x00a7 }
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            goto L_0x00bd
        L_0x00a4:
            r8.insideInterruptLoop = r3     // Catch:{ all -> 0x00a7 }
            throw r9     // Catch:{ all -> 0x00a7 }
        L_0x00a7:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            throw r9     // Catch:{ all -> 0x00f7 }
        L_0x00aa:
            r8.returnValue = r6     // Catch:{ all -> 0x00f7 }
            org.mozilla.javascript.tools.debugger.GuiCallback r9 = r8.callback     // Catch:{ all -> 0x00f7 }
            r9.enterInterrupt(r10, r5, r11)     // Catch:{ all -> 0x00f7 }
        L_0x00b1:
            int r9 = r8.returnValue     // Catch:{ all -> 0x00f7 }
            if (r9 != r6) goto L_0x00bb
            org.mozilla.javascript.tools.debugger.GuiCallback r9 = r8.callback     // Catch:{ InterruptedException -> 0x00b1 }
            r9.dispatchNextGuiEvent()     // Catch:{ InterruptedException -> 0x00b1 }
            goto L_0x00b1
        L_0x00bb:
            int r9 = r8.returnValue     // Catch:{ all -> 0x00f7 }
        L_0x00bd:
            if (r9 == 0) goto L_0x00de
            if (r9 == r4) goto L_0x00d7
            r10 = 2
            if (r9 == r10) goto L_0x00c5
            goto L_0x00e8
        L_0x00c5:
            int r9 = r0.frameCount()     // Catch:{ all -> 0x00f7 }
            if (r9 <= r4) goto L_0x00e8
            boolean unused = r0.breakNextLine = r4     // Catch:{ all -> 0x00f7 }
            int r9 = r0.frameCount()     // Catch:{ all -> 0x00f7 }
            int r9 = r9 - r4
            int unused = r0.stopAtFrameDepth = r9     // Catch:{ all -> 0x00f7 }
            goto L_0x00e8
        L_0x00d7:
            boolean unused = r0.breakNextLine = r4     // Catch:{ all -> 0x00f7 }
            int unused = r0.stopAtFrameDepth = r6     // Catch:{ all -> 0x00f7 }
            goto L_0x00e8
        L_0x00de:
            boolean unused = r0.breakNextLine = r4     // Catch:{ all -> 0x00f7 }
            int r9 = r0.frameCount()     // Catch:{ all -> 0x00f7 }
            int unused = r0.stopAtFrameDepth = r9     // Catch:{ all -> 0x00f7 }
        L_0x00e8:
            java.lang.Object r9 = r8.eventThreadMonitor
            monitor-enter(r9)
            r8.interruptedContextData = r2     // Catch:{ all -> 0x00f4 }
            java.lang.Object r10 = r8.eventThreadMonitor     // Catch:{ all -> 0x00f4 }
            r10.notifyAll()     // Catch:{ all -> 0x00f4 }
            monitor-exit(r9)     // Catch:{ all -> 0x00f4 }
            return
        L_0x00f4:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x00f4 }
            throw r10
        L_0x00f7:
            r9 = move-exception
            java.lang.Object r10 = r8.eventThreadMonitor
            monitor-enter(r10)
            r8.interruptedContextData = r2     // Catch:{ all -> 0x0104 }
            java.lang.Object r11 = r8.eventThreadMonitor     // Catch:{ all -> 0x0104 }
            r11.notifyAll()     // Catch:{ all -> 0x0104 }
            monitor-exit(r10)     // Catch:{ all -> 0x0104 }
            throw r9
        L_0x0104:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0104 }
            throw r9
        L_0x0107:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0107 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.Dim.interrupted(org.mozilla.javascript.Context, org.mozilla.javascript.tools.debugger.Dim$StackFrame, java.lang.Throwable):void");
    }

    private static String do_eval(Context context, StackFrame stackFrame, String str) {
        String str2 = "";
        Debugger debugger = context.getDebugger();
        Object debuggerContextData = context.getDebuggerContextData();
        int optimizationLevel = context.getOptimizationLevel();
        context.setDebugger((Debugger) null, (Object) null);
        context.setOptimizationLevel(-1);
        context.setGeneratingDebug(false);
        try {
            Object call = ((Callable) context.compileString(str, str2, 0, (Object) null)).call(context, stackFrame.scope, stackFrame.thisObj, ScriptRuntime.emptyArgs);
            if (call != Undefined.instance) {
                str2 = ScriptRuntime.toString(call);
            }
        } catch (Exception e) {
            str2 = e.getMessage();
        } catch (Throwable th) {
            context.setGeneratingDebug(true);
            context.setOptimizationLevel(optimizationLevel);
            context.setDebugger(debugger, debuggerContextData);
            throw th;
        }
        context.setGeneratingDebug(true);
        context.setOptimizationLevel(optimizationLevel);
        context.setDebugger(debugger, debuggerContextData);
        return str2 == null ? "null" : str2;
    }

    private static class DimIProxy implements ContextAction, ContextFactory.Listener, Debugger {
        /* access modifiers changed from: private */
        public boolean booleanResult;
        private Dim dim;
        /* access modifiers changed from: private */
        public Object id;
        /* access modifiers changed from: private */
        public Object object;
        /* access modifiers changed from: private */
        public Object[] objectArrayResult;
        /* access modifiers changed from: private */
        public Object objectResult;
        /* access modifiers changed from: private */
        public String stringResult;
        /* access modifiers changed from: private */
        public String text;
        private int type;
        /* access modifiers changed from: private */
        public String url;

        private DimIProxy(Dim dim2, int i) {
            this.dim = dim2;
            this.type = i;
        }

        public Object run(Context context) {
            switch (this.type) {
                case 2:
                    context.compileString(this.text, this.url, 1, (Object) null);
                    break;
                case 3:
                    Scriptable scope = this.dim.scopeProvider != null ? this.dim.scopeProvider.getScope() : null;
                    if (scope == null) {
                        scope = new ImporterTopLevel(context);
                    }
                    context.evaluateString(scope, this.text, this.url, 1, (Object) null);
                    break;
                case 4:
                    this.booleanResult = context.stringIsCompilableUnit(this.text);
                    break;
                case 5:
                    if (this.object != Undefined.instance) {
                        Object obj = this.object;
                        if (obj != null) {
                            if (!(obj instanceof NativeCall)) {
                                this.stringResult = Context.toString(obj);
                                break;
                            } else {
                                this.stringResult = "[object Call]";
                                break;
                            }
                        } else {
                            this.stringResult = "null";
                            break;
                        }
                    } else {
                        this.stringResult = AdError.UNDEFINED_DOMAIN;
                        break;
                    }
                case 6:
                    this.objectResult = this.dim.getObjectPropertyImpl(context, this.object, this.id);
                    break;
                case 7:
                    this.objectArrayResult = this.dim.getObjectIdsImpl(context, this.object);
                    break;
                default:
                    throw Kit.codeBug();
            }
            return null;
        }

        /* access modifiers changed from: private */
        public void withContext() {
            this.dim.contextFactory.call(this);
        }

        public void contextCreated(Context context) {
            if (this.type != 1) {
                Kit.codeBug();
            }
            context.setDebugger(new DimIProxy(this.dim, 0), new ContextData());
            context.setGeneratingDebug(true);
            context.setOptimizationLevel(-1);
        }

        public void contextReleased(Context context) {
            if (this.type != 1) {
                Kit.codeBug();
            }
        }

        public DebugFrame getFrame(Context context, DebuggableScript debuggableScript) {
            if (this.type != 0) {
                Kit.codeBug();
            }
            FunctionSource access$2200 = this.dim.getFunctionSource(debuggableScript);
            if (access$2200 == null) {
                return null;
            }
            return new StackFrame(context, this.dim, access$2200);
        }

        public void handleCompilationDone(Context context, DebuggableScript debuggableScript, String str) {
            if (this.type != 0) {
                Kit.codeBug();
            }
            if (debuggableScript.isTopLevel()) {
                this.dim.registerTopScript(debuggableScript, str);
            }
        }
    }

    public static class ContextData {
        /* access modifiers changed from: private */
        public boolean breakNextLine;
        /* access modifiers changed from: private */
        public boolean eventThreadFlag;
        private ObjArray frameStack = new ObjArray();
        /* access modifiers changed from: private */
        public Throwable lastProcessedException;
        /* access modifiers changed from: private */
        public int stopAtFrameDepth = -1;

        public static ContextData get(Context context) {
            return (ContextData) context.getDebuggerContextData();
        }

        public int frameCount() {
            return this.frameStack.size();
        }

        public StackFrame getFrame(int i) {
            return (StackFrame) this.frameStack.get((this.frameStack.size() - i) - 1);
        }

        /* access modifiers changed from: private */
        public void pushFrame(StackFrame stackFrame) {
            this.frameStack.push(stackFrame);
        }

        /* access modifiers changed from: private */
        public void popFrame() {
            this.frameStack.pop();
        }
    }

    public static class StackFrame implements DebugFrame {
        private boolean[] breakpoints;
        private ContextData contextData;
        private Dim dim;
        private FunctionSource fsource;
        private int lineNumber;
        /* access modifiers changed from: private */
        public Scriptable scope;
        /* access modifiers changed from: private */
        public Scriptable thisObj;

        private StackFrame(Context context, Dim dim2, FunctionSource functionSource) {
            this.dim = dim2;
            this.contextData = ContextData.get(context);
            this.fsource = functionSource;
            this.breakpoints = functionSource.sourceInfo().breakpoints;
            this.lineNumber = functionSource.firstLine();
        }

        public void onEnter(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            this.contextData.pushFrame(this);
            this.scope = scriptable;
            this.thisObj = scriptable2;
            if (this.dim.breakOnEnter) {
                this.dim.handleBreakpointHit(this, context);
            }
        }

        public void onLineChange(Context context, int i) {
            this.lineNumber = i;
            if (!this.breakpoints[i] && !this.dim.breakFlag) {
                boolean access$1400 = this.contextData.breakNextLine;
                if (access$1400 && this.contextData.stopAtFrameDepth >= 0) {
                    access$1400 = this.contextData.frameCount() <= this.contextData.stopAtFrameDepth;
                }
                if (access$1400) {
                    int unused = this.contextData.stopAtFrameDepth = -1;
                    boolean unused2 = this.contextData.breakNextLine = false;
                } else {
                    return;
                }
            }
            this.dim.handleBreakpointHit(this, context);
        }

        public void onExceptionThrown(Context context, Throwable th) {
            this.dim.handleExceptionThrown(context, th, this);
        }

        public void onExit(Context context, boolean z, Object obj) {
            if (this.dim.breakOnReturn && !z) {
                this.dim.handleBreakpointHit(this, context);
            }
            this.contextData.popFrame();
        }

        public void onDebuggerStatement(Context context) {
            this.dim.handleBreakpointHit(this, context);
        }

        public SourceInfo sourceInfo() {
            return this.fsource.sourceInfo();
        }

        public ContextData contextData() {
            return this.contextData;
        }

        public Object scope() {
            return this.scope;
        }

        public Object thisObj() {
            return this.thisObj;
        }

        public String getUrl() {
            return this.fsource.sourceInfo().url();
        }

        public int getLineNumber() {
            return this.lineNumber;
        }

        public String getFunctionName() {
            return this.fsource.name();
        }
    }

    public static class FunctionSource {
        private int firstLine;
        private String name;
        private SourceInfo sourceInfo;

        private FunctionSource(SourceInfo sourceInfo2, int i, String str) {
            if (str != null) {
                this.sourceInfo = sourceInfo2;
                this.firstLine = i;
                this.name = str;
                return;
            }
            throw new IllegalArgumentException();
        }

        public SourceInfo sourceInfo() {
            return this.sourceInfo;
        }

        public int firstLine() {
            return this.firstLine;
        }

        public String name() {
            return this.name;
        }
    }

    public static class SourceInfo {
        private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
        private boolean[] breakableLines;
        /* access modifiers changed from: private */
        public boolean[] breakpoints;
        private FunctionSource[] functionSources;
        private String source;
        private String url;

        private SourceInfo(String str, DebuggableScript[] debuggableScriptArr, String str2) {
            this.source = str;
            this.url = str2;
            int length = debuggableScriptArr.length;
            int[][] iArr = new int[length][];
            for (int i = 0; i != length; i++) {
                iArr[i] = debuggableScriptArr[i].getLineNumbers();
            }
            int[] iArr2 = new int[length];
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            while (true) {
                if (i2 == length) {
                    break;
                }
                int[] iArr3 = iArr[i2];
                if (iArr3 == null || iArr3.length == 0) {
                    iArr2[i2] = -1;
                } else {
                    int i5 = iArr3[0];
                    int i6 = i5;
                    for (int i7 = 1; i7 != iArr3.length; i7++) {
                        int i8 = iArr3[i7];
                        if (i8 < i5) {
                            i5 = i8;
                        } else if (i8 > i6) {
                            i6 = i8;
                        }
                    }
                    iArr2[i2] = i5;
                    if (i3 > i4) {
                        i3 = i5;
                    } else {
                        i3 = i5 < i3 ? i5 : i3;
                        if (i6 <= i4) {
                        }
                    }
                    i4 = i6;
                }
                i2++;
            }
            if (i3 > i4) {
                boolean[] zArr = EMPTY_BOOLEAN_ARRAY;
                this.breakableLines = zArr;
                this.breakpoints = zArr;
            } else if (i3 >= 0) {
                int i9 = i4 + 1;
                this.breakableLines = new boolean[i9];
                this.breakpoints = new boolean[i9];
                for (int i10 = 0; i10 != length; i10++) {
                    int[] iArr4 = iArr[i10];
                    if (!(iArr4 == null || iArr4.length == 0)) {
                        for (int i11 = 0; i11 != iArr4.length; i11++) {
                            this.breakableLines[iArr4[i11]] = true;
                        }
                    }
                }
            } else {
                throw new IllegalStateException(String.valueOf(i3));
            }
            this.functionSources = new FunctionSource[length];
            for (int i12 = 0; i12 != length; i12++) {
                String functionName = debuggableScriptArr[i12].getFunctionName();
                if (functionName == null) {
                    functionName = "";
                }
                this.functionSources[i12] = new FunctionSource(this, iArr2[i12], functionName);
            }
        }

        public String source() {
            return this.source;
        }

        public String url() {
            return this.url;
        }

        public int functionSourcesTop() {
            return this.functionSources.length;
        }

        public FunctionSource functionSource(int i) {
            return this.functionSources[i];
        }

        /* access modifiers changed from: private */
        public void copyBreakpointsFrom(SourceInfo sourceInfo) {
            int length = sourceInfo.breakpoints.length;
            boolean[] zArr = this.breakpoints;
            if (length > zArr.length) {
                length = zArr.length;
            }
            for (int i = 0; i != length; i++) {
                if (sourceInfo.breakpoints[i]) {
                    this.breakpoints[i] = true;
                }
            }
        }

        public boolean breakableLine(int i) {
            boolean[] zArr = this.breakableLines;
            return i < zArr.length && zArr[i];
        }

        public boolean breakpoint(int i) {
            if (breakableLine(i)) {
                boolean[] zArr = this.breakpoints;
                return i < zArr.length && zArr[i];
            }
            throw new IllegalArgumentException(String.valueOf(i));
        }

        public boolean breakpoint(int i, boolean z) {
            boolean z2;
            if (breakableLine(i)) {
                synchronized (this.breakpoints) {
                    if (this.breakpoints[i] != z) {
                        this.breakpoints[i] = z;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                return z2;
            }
            throw new IllegalArgumentException(String.valueOf(i));
        }

        public void removeAllBreakpoints() {
            synchronized (this.breakpoints) {
                for (int i = 0; i != this.breakpoints.length; i++) {
                    this.breakpoints[i] = false;
                }
            }
        }
    }
}
