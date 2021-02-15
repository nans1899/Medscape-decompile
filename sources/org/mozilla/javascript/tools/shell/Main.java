package org.mozilla.javascript.tools.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.commonjs.module.ModuleScope;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class Main {
    private static final int EXITCODE_FILE_NOT_FOUND = 4;
    private static final int EXITCODE_RUNTIME_ERROR = 3;
    protected static ToolErrorReporter errorReporter;
    protected static int exitCode = 0;
    static List<String> fileList = new ArrayList();
    public static Global global = new Global();
    static String mainModule;
    static List<String> modulePath;
    static boolean processStdin = true;
    static Require require;
    static boolean sandboxed = false;
    private static final ScriptCache scriptCache = new ScriptCache(32);
    private static SecurityProxy securityImpl;
    public static ShellContextFactory shellContextFactory = new ShellContextFactory();
    static boolean useRequire = false;

    static {
        global.initQuitAction(new IProxy(3));
    }

    private static class IProxy implements ContextAction, QuitAction {
        private static final int EVAL_INLINE_SCRIPT = 2;
        private static final int PROCESS_FILES = 1;
        private static final int SYSTEM_EXIT = 3;
        String[] args;
        String scriptText;
        private int type;

        IProxy(int i) {
            this.type = i;
        }

        public Object run(Context context) {
            if (Main.useRequire) {
                Main.require = Main.global.installRequire(context, Main.modulePath, Main.sandboxed);
            }
            int i = this.type;
            if (i == 1) {
                Main.processFiles(context, this.args);
                return null;
            } else if (i == 2) {
                Main.evalInlineScript(context, this.scriptText);
                return null;
            } else {
                throw Kit.codeBug();
            }
        }

        public void quit(Context context, int i) {
            if (this.type == 3) {
                System.exit(i);
                return;
            }
            throw Kit.codeBug();
        }
    }

    public static void main(String[] strArr) {
        try {
            if (Boolean.getBoolean("rhino.use_java_policy_security")) {
                initJavaPolicySecuritySupport();
            }
        } catch (SecurityException e) {
            e.printStackTrace(System.err);
        }
        int exec = exec(strArr);
        if (exec != 0) {
            System.exit(exec);
        }
    }

    public static int exec(String[] strArr) {
        ToolErrorReporter toolErrorReporter = new ToolErrorReporter(false, global.getErr());
        errorReporter = toolErrorReporter;
        shellContextFactory.setErrorReporter(toolErrorReporter);
        String[] processOptions = processOptions(strArr);
        if (processStdin) {
            fileList.add((Object) null);
        }
        if (!global.initialized) {
            global.init((ContextFactory) shellContextFactory);
        }
        IProxy iProxy = new IProxy(1);
        iProxy.args = processOptions;
        shellContextFactory.call(iProxy);
        return exitCode;
    }

    static void processFiles(Context context, String[] strArr) {
        Object[] objArr = new Object[strArr.length];
        System.arraycopy(strArr, 0, objArr, 0, strArr.length);
        global.defineProperty("arguments", (Object) context.newArray((Scriptable) global, objArr), 2);
        for (String next : fileList) {
            try {
                processSource(context, next);
            } catch (IOException e) {
                Context.reportError(ToolErrorReporter.getMessage("msg.couldnt.read.source", next, e.getMessage()));
                exitCode = 4;
            } catch (RhinoException e2) {
                ToolErrorReporter.reportException(context.getErrorReporter(), e2);
                exitCode = 3;
            } catch (VirtualMachineError e3) {
                e3.printStackTrace();
                Context.reportError(ToolErrorReporter.getMessage("msg.uncaughtJSException", e3.toString()));
                exitCode = 3;
            }
        }
    }

    static void evalInlineScript(Context context, String str) {
        try {
            Script compileString = context.compileString(str, "<command>", 1, (Object) null);
            if (compileString != null) {
                compileString.exec(context, getShellScope());
            }
        } catch (RhinoException e) {
            ToolErrorReporter.reportException(context.getErrorReporter(), e);
            exitCode = 3;
        } catch (VirtualMachineError e2) {
            e2.printStackTrace();
            Context.reportError(ToolErrorReporter.getMessage("msg.uncaughtJSException", e2.toString()));
            exitCode = 3;
        }
    }

    public static Global getGlobal() {
        return global;
    }

    static Scriptable getShellScope() {
        return getScope((String) null);
    }

    static Scriptable getScope(String str) {
        URI uri;
        if (!useRequire) {
            return global;
        }
        if (str == null) {
            uri = new File(System.getProperty("user.dir")).toURI();
        } else if (SourceReader.toUrl(str) != null) {
            try {
                uri = new URI(str);
            } catch (URISyntaxException unused) {
                uri = new File(str).toURI();
            }
        } else {
            uri = new File(str).toURI();
        }
        return new ModuleScope(global, uri, (URI) null);
    }

    public static String[] processOptions(String[] strArr) {
        Class<Main> cls = Main.class;
        int i = 0;
        while (i != strArr.length) {
            String str = strArr[i];
            if (!str.startsWith("-")) {
                processStdin = false;
                fileList.add(str);
                mainModule = str;
                String[] strArr2 = new String[((strArr.length - i) - 1)];
                System.arraycopy(strArr, i + 1, strArr2, 0, (strArr.length - i) - 1);
                return strArr2;
            }
            if (str.equals("-version")) {
                i++;
                if (i != strArr.length) {
                    try {
                        int parseInt = Integer.parseInt(strArr[i]);
                        if (!Context.isValidLanguageVersion(parseInt)) {
                            str = strArr[i];
                        } else {
                            shellContextFactory.setLanguageVersion(parseInt);
                        }
                    } catch (NumberFormatException unused) {
                        str = strArr[i];
                    }
                }
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                System.exit(1);
                return null;
            } else if (str.equals("-opt") || str.equals("-O")) {
                i++;
                if (i != strArr.length) {
                    try {
                        int parseInt2 = Integer.parseInt(strArr[i]);
                        if (parseInt2 == -2) {
                            parseInt2 = -1;
                        } else if (!Context.isValidOptimizationLevel(parseInt2)) {
                            str = strArr[i];
                        }
                        shellContextFactory.setOptimizationLevel(parseInt2);
                    } catch (NumberFormatException unused2) {
                        str = strArr[i];
                    }
                }
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                System.exit(1);
                return null;
            } else if (str.equals("-encoding")) {
                i++;
                if (i == strArr.length) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                    System.exit(1);
                    return null;
                }
                shellContextFactory.setCharacterEncoding(strArr[i]);
            } else if (str.equals("-strict")) {
                shellContextFactory.setStrictMode(true);
                shellContextFactory.setAllowReservedKeywords(false);
                errorReporter.setIsReportingWarnings(true);
            } else if (str.equals("-fatal-warnings")) {
                shellContextFactory.setWarningAsError(true);
            } else if (str.equals("-e")) {
                processStdin = false;
                i++;
                if (i == strArr.length) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                    System.exit(1);
                    return null;
                }
                if (!global.initialized) {
                    global.init((ContextFactory) shellContextFactory);
                }
                IProxy iProxy = new IProxy(2);
                iProxy.scriptText = strArr[i];
                shellContextFactory.call(iProxy);
            } else if (str.equals("-require")) {
                useRequire = true;
            } else if (str.equals("-sandbox")) {
                sandboxed = true;
                useRequire = true;
            } else if (str.equals("-modules")) {
                i++;
                if (i == strArr.length) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                    System.exit(1);
                    return null;
                }
                if (modulePath == null) {
                    modulePath = new ArrayList();
                }
                modulePath.add(strArr[i]);
                useRequire = true;
            } else if (str.equals("-w")) {
                errorReporter.setIsReportingWarnings(true);
            } else if (str.equals("-f")) {
                processStdin = false;
                i++;
                if (i == strArr.length) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                    System.exit(1);
                    return null;
                } else if (strArr[i].equals("-")) {
                    fileList.add((Object) null);
                } else {
                    fileList.add(strArr[i]);
                    mainModule = strArr[i];
                }
            } else if (str.equals("-sealedlib")) {
                global.setSealedStdLib(true);
            } else if (str.equals("-debug")) {
                shellContextFactory.setGeneratingDebug(true);
            } else {
                if (str.equals("-?") || str.equals("-help")) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                    System.exit(1);
                }
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", str));
                global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", cls.getName()));
                System.exit(1);
                return null;
            }
            i++;
        }
        return new String[0];
    }

    private static void initJavaPolicySecuritySupport() {
        try {
            SecurityProxy securityProxy = (SecurityProxy) Class.forName("org.mozilla.javascript.tools.shell.JavaPolicySecurity").newInstance();
            securityImpl = securityProxy;
            SecurityController.initGlobal(securityProxy);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | LinkageError e) {
            throw Kit.initCause(new IllegalStateException("Can not load security support: " + e), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cf, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        org.mozilla.javascript.tools.ToolErrorReporter.reportException(r12.getErrorReporter(), r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e5, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e6, code lost:
        r8.printStackTrace();
        org.mozilla.javascript.Context.reportError(org.mozilla.javascript.tools.ToolErrorReporter.getMessage("msg.uncaughtJSException", r8.toString()));
        exitCode = 3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e5 A[ExcHandler: VirtualMachineError (r8v4 'e' java.lang.VirtualMachineError A[CUSTOM_DECLARE]), Splitter:B:33:0x00a7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void processSource(org.mozilla.javascript.Context r12, java.lang.String r13) throws java.io.IOException {
        /*
            if (r13 == 0) goto L_0x0027
            java.lang.String r0 = "-"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x000b
            goto L_0x0027
        L_0x000b:
            boolean r0 = useRequire
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = mainModule
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x001e
            org.mozilla.javascript.commonjs.module.Require r0 = require
            r0.requireMain(r12, r13)
            goto L_0x0109
        L_0x001e:
            org.mozilla.javascript.Scriptable r0 = getScope(r13)
            processFile(r12, r0, r13)
            goto L_0x0109
        L_0x0027:
            org.mozilla.javascript.Scriptable r0 = getShellScope()
            org.mozilla.javascript.tools.shell.Global r1 = global
            java.io.PrintStream r1 = r1.getErr()
            if (r13 != 0) goto L_0x003a
            java.lang.String r2 = r12.getImplementationVersion()
            r1.println(r2)
        L_0x003a:
            org.mozilla.javascript.tools.shell.ShellContextFactory r2 = shellContextFactory
            java.lang.String r2 = r2.getCharacterEncoding()
            if (r2 != 0) goto L_0x0048
            java.lang.String r2 = "file.encoding"
            java.lang.String r2 = java.lang.System.getProperty(r2)
        L_0x0048:
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ UnsupportedEncodingException -> 0x010a }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x010a }
            org.mozilla.javascript.tools.shell.Global r5 = global     // Catch:{ UnsupportedEncodingException -> 0x010a }
            java.io.InputStream r5 = r5.getIn()     // Catch:{ UnsupportedEncodingException -> 0x010a }
            r4.<init>(r5, r2)     // Catch:{ UnsupportedEncodingException -> 0x010a }
            r3.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x010a }
            r2 = 0
            r4 = 1
            r5 = 0
            r6 = 1
        L_0x005c:
            if (r5 != 0) goto L_0x0106
            org.mozilla.javascript.tools.shell.Global r7 = global
            java.lang.String[] r7 = r7.getPrompts(r12)
            if (r13 != 0) goto L_0x006b
            r8 = r7[r2]
            r1.print(r8)
        L_0x006b:
            r1.flush()
            java.lang.String r8 = ""
        L_0x0070:
            java.lang.String r9 = r3.readLine()     // Catch:{ IOException -> 0x009b }
            if (r9 != 0) goto L_0x0078
            r5 = 1
            goto L_0x00a3
        L_0x0078:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            r10.append(r9)
            java.lang.String r8 = "\n"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            int r6 = r6 + 1
            boolean r9 = r12.stringIsCompilableUnit(r8)
            if (r9 == 0) goto L_0x0095
            goto L_0x00a3
        L_0x0095:
            r9 = r7[r4]
            r1.print(r9)
            goto L_0x0070
        L_0x009b:
            r7 = move-exception
            java.lang.String r7 = r7.toString()
            r1.println(r7)
        L_0x00a3:
            r7 = 3
            java.lang.String r9 = "<stdin>"
            r10 = 0
            org.mozilla.javascript.Script r9 = r12.compileString(r8, r9, r6, r10)     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            if (r9 == 0) goto L_0x005c
            java.lang.Object r9 = r9.exec(r12, r0)     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            java.lang.Object r10 = org.mozilla.javascript.Context.getUndefinedValue()     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            if (r9 == r10) goto L_0x00d7
            boolean r10 = r9 instanceof org.mozilla.javascript.Function     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            if (r10 == 0) goto L_0x00c7
            java.lang.String r10 = r8.trim()     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            java.lang.String r11 = "function"
            boolean r10 = r10.startsWith(r11)     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            if (r10 != 0) goto L_0x00d7
        L_0x00c7:
            java.lang.String r9 = org.mozilla.javascript.Context.toString(r9)     // Catch:{ RhinoException -> 0x00cf, VirtualMachineError -> 0x00e5 }
            r1.println(r9)     // Catch:{ RhinoException -> 0x00cf, VirtualMachineError -> 0x00e5 }
            goto L_0x00d7
        L_0x00cf:
            r9 = move-exception
            org.mozilla.javascript.ErrorReporter r10 = r12.getErrorReporter()     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            org.mozilla.javascript.tools.ToolErrorReporter.reportException(r10, r9)     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
        L_0x00d7:
            org.mozilla.javascript.tools.shell.Global r9 = global     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            org.mozilla.javascript.NativeArray r9 = r9.history     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            long r10 = r9.getLength()     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            int r11 = (int) r10     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            r9.put((int) r11, (org.mozilla.javascript.Scriptable) r9, (java.lang.Object) r8)     // Catch:{ RhinoException -> 0x00fa, VirtualMachineError -> 0x00e5 }
            goto L_0x005c
        L_0x00e5:
            r8 = move-exception
            r8.printStackTrace()
            java.lang.String r8 = r8.toString()
            java.lang.String r9 = "msg.uncaughtJSException"
            java.lang.String r8 = org.mozilla.javascript.tools.ToolErrorReporter.getMessage((java.lang.String) r9, (java.lang.String) r8)
            org.mozilla.javascript.Context.reportError(r8)
            exitCode = r7
            goto L_0x005c
        L_0x00fa:
            r8 = move-exception
            org.mozilla.javascript.ErrorReporter r9 = r12.getErrorReporter()
            org.mozilla.javascript.tools.ToolErrorReporter.reportException(r9, r8)
            exitCode = r7
            goto L_0x005c
        L_0x0106:
            r1.println()
        L_0x0109:
            return
        L_0x010a:
            r12 = move-exception
            java.lang.reflect.UndeclaredThrowableException r13 = new java.lang.reflect.UndeclaredThrowableException
            r13.<init>(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.shell.Main.processSource(org.mozilla.javascript.Context, java.lang.String):void");
    }

    public static void processFileNoThrow(Context context, Scriptable scriptable, String str) {
        try {
            processFile(context, scriptable, str);
        } catch (IOException e) {
            Context.reportError(ToolErrorReporter.getMessage("msg.couldnt.read.source", str, e.getMessage()));
            exitCode = 4;
        } catch (RhinoException e2) {
            ToolErrorReporter.reportException(context.getErrorReporter(), e2);
            exitCode = 3;
        } catch (VirtualMachineError e3) {
            e3.printStackTrace();
            Context.reportError(ToolErrorReporter.getMessage("msg.uncaughtJSException", e3.toString()));
            exitCode = 3;
        }
    }

    public static void processFile(Context context, Scriptable scriptable, String str) throws IOException {
        SecurityProxy securityProxy = securityImpl;
        if (securityProxy == null) {
            processFileSecure(context, scriptable, str, (Object) null);
        } else {
            securityProxy.callProcessFileSecure(context, scriptable, str);
        }
    }

    static void processFileSecure(Context context, Scriptable scriptable, String str, Object obj) throws IOException {
        Script compileString;
        boolean endsWith = str.endsWith(".class");
        Object readFileOrUrl = readFileOrUrl(str, !endsWith);
        byte[] digest = getDigest(readFileOrUrl);
        String str2 = str + "_" + context.getOptimizationLevel();
        ScriptReference scriptReference = scriptCache.get(str2, digest);
        Script script = scriptReference != null ? (Script) scriptReference.get() : null;
        if (script == null) {
            if (endsWith) {
                compileString = loadCompiledScript(context, str, (byte[]) readFileOrUrl, obj);
            } else {
                String str3 = (String) readFileOrUrl;
                if (str3.length() > 0 && str3.charAt(0) == '#') {
                    int i = 1;
                    while (true) {
                        if (i == str3.length()) {
                            break;
                        }
                        char charAt = str3.charAt(i);
                        if (charAt == 10 || charAt == 13) {
                            str3 = str3.substring(i);
                        } else {
                            i++;
                        }
                    }
                }
                compileString = context.compileString(str3, str, 1, obj);
            }
            script = compileString;
            scriptCache.put(str2, digest, script);
        }
        if (script != null) {
            script.exec(context, scriptable);
        }
    }

    private static byte[] getDigest(Object obj) {
        byte[] bArr;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            try {
                bArr = ((String) obj).getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bArr = ((String) obj).getBytes();
            }
        } else {
            bArr = (byte[]) obj;
        }
        try {
            return MessageDigest.getInstance("MD5").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static Script loadCompiledScript(Context context, String str, byte[] bArr, Object obj) throws FileNotFoundException {
        if (bArr != null) {
            int lastIndexOf = str.lastIndexOf(47);
            int i = lastIndexOf < 0 ? 0 : lastIndexOf + 1;
            int lastIndexOf2 = str.lastIndexOf(46);
            if (lastIndexOf2 < i) {
                lastIndexOf2 = str.length();
            }
            String substring = str.substring(i, lastIndexOf2);
            try {
                GeneratedClassLoader createLoader = SecurityController.createLoader(context.getApplicationClassLoader(), obj);
                Class<?> defineClass = createLoader.defineClass(substring, bArr);
                createLoader.linkClass(defineClass);
                if (Script.class.isAssignableFrom(defineClass)) {
                    return (Script) defineClass.newInstance();
                }
                throw Context.reportRuntimeError("msg.must.implement.Script");
            } catch (IllegalAccessException e) {
                Context.reportError(e.toString());
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                Context.reportError(e2.toString());
                throw new RuntimeException(e2);
            }
        } else {
            throw new FileNotFoundException(str);
        }
    }

    public static InputStream getIn() {
        return getGlobal().getIn();
    }

    public static void setIn(InputStream inputStream) {
        getGlobal().setIn(inputStream);
    }

    public static PrintStream getOut() {
        return getGlobal().getOut();
    }

    public static void setOut(PrintStream printStream) {
        getGlobal().setOut(printStream);
    }

    public static PrintStream getErr() {
        return getGlobal().getErr();
    }

    public static void setErr(PrintStream printStream) {
        getGlobal().setErr(printStream);
    }

    private static Object readFileOrUrl(String str, boolean z) throws IOException {
        return SourceReader.readFileOrUrl(str, z, shellContextFactory.getCharacterEncoding());
    }

    static class ScriptReference extends SoftReference<Script> {
        byte[] digest;
        String path;

        ScriptReference(String str, byte[] bArr, Script script, ReferenceQueue<Script> referenceQueue) {
            super(script, referenceQueue);
            this.path = str;
            this.digest = bArr;
        }
    }

    static class ScriptCache extends LinkedHashMap<String, ScriptReference> {
        int capacity;
        ReferenceQueue<Script> queue = new ReferenceQueue<>();

        ScriptCache(int i) {
            super(i + 1, 2.0f, true);
            this.capacity = i;
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry<String, ScriptReference> entry) {
            return size() > this.capacity;
        }

        /* access modifiers changed from: package-private */
        public ScriptReference get(String str, byte[] bArr) {
            while (true) {
                ScriptReference scriptReference = (ScriptReference) this.queue.poll();
                if (scriptReference == null) {
                    break;
                }
                remove(scriptReference.path);
            }
            ScriptReference scriptReference2 = (ScriptReference) get(str);
            if (scriptReference2 == null || Arrays.equals(bArr, scriptReference2.digest)) {
                return scriptReference2;
            }
            remove(scriptReference2.path);
            return null;
        }

        /* access modifiers changed from: package-private */
        public void put(String str, byte[] bArr, Script script) {
            put(str, new ScriptReference(str, bArr, script, this.queue));
        }
    }
}
