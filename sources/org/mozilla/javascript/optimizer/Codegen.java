package org.mozilla.javascript.optimizer;

import androidx.core.app.NotificationCompat;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.ScriptNode;

public class Codegen implements Evaluator {
    static final String DEFAULT_MAIN_METHOD_CLASS = "org.mozilla.javascript.optimizer.OptRuntime";
    static final String FUNCTION_CONSTRUCTOR_SIGNATURE = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V";
    static final String FUNCTION_INIT_SIGNATURE = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V";
    static final String ID_FIELD_NAME = "_id";
    static final String REGEXP_INIT_METHOD_NAME = "_reInit";
    static final String REGEXP_INIT_METHOD_SIGNATURE = "(Lorg/mozilla/javascript/Context;)V";
    private static final String SUPER_CLASS_NAME = "org.mozilla.javascript.NativeFunction";
    private static final Object globalLock = new Object();
    private static int globalSerialClassCounter;
    private CompilerEnvirons compilerEnv;
    private ObjArray directCallTargets;
    private double[] itsConstantList;
    private int itsConstantListSize;
    String mainClassName;
    String mainClassSignature;
    private String mainMethodClass = DEFAULT_MAIN_METHOD_CLASS;
    private ObjToIntMap scriptOrFnIndexes;
    ScriptNode[] scriptOrFnNodes;

    private static String getStaticConstantWrapperType(double d) {
        return ((double) ((int) d)) == d ? "Ljava/lang/Integer;" : "Ljava/lang/Double;";
    }

    public void captureStackInfo(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    public String getSourcePositionFromStack(Context context, int[] iArr) {
        throw new UnsupportedOperationException();
    }

    public String getPatchedStack(RhinoException rhinoException, String str) {
        throw new UnsupportedOperationException();
    }

    public List<String> getScriptStack(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    public void setEvalScriptFlag(Script script) {
        throw new UnsupportedOperationException();
    }

    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        int i;
        synchronized (globalLock) {
            i = globalSerialClassCounter + 1;
            globalSerialClassCounter = i;
        }
        String str2 = "c";
        if (scriptNode.getSourceName().length() > 0) {
            str2 = scriptNode.getSourceName().replaceAll("\\W", "_");
            if (!Character.isJavaIdentifierStart(str2.charAt(0))) {
                str2 = "_" + str2;
            }
        }
        Object obj = "org.mozilla.javascript.gen." + str2 + "_" + i;
        return new Object[]{obj, compileToClassFile(compilerEnvirons, obj, scriptNode, str, z)};
    }

    public Script createScriptObject(Object obj, Object obj2) {
        try {
            return (Script) defineClass(obj, obj2).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        try {
            return (NativeFunction) defineClass(obj, obj2).getConstructors()[0].newInstance(new Object[]{scriptable, context, 0});
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    private Class<?> defineClass(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        String str = (String) objArr[0];
        byte[] bArr = (byte[]) objArr[1];
        GeneratedClassLoader createLoader = SecurityController.createLoader(getClass().getClassLoader(), obj2);
        try {
            Class<?> defineClass = createLoader.defineClass(str, bArr);
            createLoader.linkClass(defineClass);
            return defineClass;
        } catch (IllegalArgumentException | SecurityException e) {
            throw new RuntimeException("Malformed optimizer package " + e);
        }
    }

    public byte[] compileToClassFile(CompilerEnvirons compilerEnvirons, String str, ScriptNode scriptNode, String str2, boolean z) {
        this.compilerEnv = compilerEnvirons;
        transform(scriptNode);
        if (z) {
            scriptNode = scriptNode.getFunctionNode(0);
        }
        initScriptNodesData(scriptNode);
        this.mainClassName = str;
        this.mainClassSignature = ClassFileWriter.classNameToSignature(str);
        try {
            return generateCode(str2);
        } catch (ClassFileWriter.ClassFileFormatException e) {
            throw reportClassFileFormatException(scriptNode, e.getMessage());
        }
    }

    private RuntimeException reportClassFileFormatException(ScriptNode scriptNode, String str) {
        return Context.reportRuntimeError(scriptNode instanceof FunctionNode ? ScriptRuntime.getMessage2("msg.while.compiling.fn", ((FunctionNode) scriptNode).getFunctionName(), str) : ScriptRuntime.getMessage1("msg.while.compiling.script", str), scriptNode.getSourceName(), scriptNode.getLineno(), (String) null, 0);
    }

    private void transform(ScriptNode scriptNode) {
        initOptFunctions_r(scriptNode);
        int optimizationLevel = this.compilerEnv.getOptimizationLevel();
        HashMap hashMap = null;
        if (optimizationLevel > 0 && scriptNode.getType() == 136) {
            int functionCount = scriptNode.getFunctionCount();
            for (int i = 0; i != functionCount; i++) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode, i);
                if (optFunctionNode.fnode.getFunctionType() == 1) {
                    String name = optFunctionNode.fnode.getName();
                    if (name.length() != 0) {
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        hashMap.put(name, optFunctionNode);
                    }
                }
            }
        }
        if (hashMap != null) {
            this.directCallTargets = new ObjArray();
        }
        new OptTransformer(hashMap, this.directCallTargets).transform(scriptNode);
        if (optimizationLevel > 0) {
            new Optimizer().optimize(scriptNode);
        }
    }

    private static void initOptFunctions_r(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            FunctionNode functionNode = scriptNode.getFunctionNode(i);
            new OptFunctionNode(functionNode);
            initOptFunctions_r(functionNode);
        }
    }

    private void initScriptNodesData(ScriptNode scriptNode) {
        ObjArray objArray = new ObjArray();
        collectScriptNodes_r(scriptNode, objArray);
        int size = objArray.size();
        ScriptNode[] scriptNodeArr = new ScriptNode[size];
        this.scriptOrFnNodes = scriptNodeArr;
        objArray.toArray(scriptNodeArr);
        this.scriptOrFnIndexes = new ObjToIntMap(size);
        for (int i = 0; i != size; i++) {
            this.scriptOrFnIndexes.put(this.scriptOrFnNodes[i], i);
        }
    }

    private static void collectScriptNodes_r(ScriptNode scriptNode, ObjArray objArray) {
        objArray.add(scriptNode);
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            collectScriptNodes_r(scriptNode.getFunctionNode(i), objArray);
        }
    }

    private byte[] generateCode(String str) {
        int i = 0;
        boolean z = true;
        boolean z2 = this.scriptOrFnNodes[0].getType() == 136;
        if (this.scriptOrFnNodes.length <= 1 && z2) {
            z = false;
        }
        String str2 = null;
        if (this.compilerEnv.isGenerateDebugInfo()) {
            str2 = this.scriptOrFnNodes[0].getSourceName();
        }
        ClassFileWriter classFileWriter = new ClassFileWriter(this.mainClassName, SUPER_CLASS_NAME, str2);
        classFileWriter.addField(ID_FIELD_NAME, "I", 2);
        if (z) {
            generateFunctionConstructor(classFileWriter);
        }
        if (z2) {
            classFileWriter.addInterface("org/mozilla/javascript/Script");
            generateScriptCtor(classFileWriter);
            generateMain(classFileWriter);
            generateExecute(classFileWriter);
        }
        generateCallMethod(classFileWriter);
        generateResumeGenerator(classFileWriter);
        generateNativeFunctionOverrides(classFileWriter, str);
        int length = this.scriptOrFnNodes.length;
        while (i != length) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i];
            BodyCodegen bodyCodegen = new BodyCodegen();
            bodyCodegen.cfw = classFileWriter;
            bodyCodegen.codegen = this;
            bodyCodegen.compilerEnv = this.compilerEnv;
            bodyCodegen.scriptOrFn = scriptNode;
            bodyCodegen.scriptOrFnIndex = i;
            try {
                bodyCodegen.generateBodyCode();
                if (scriptNode.getType() == 109) {
                    OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                    generateFunctionInit(classFileWriter, optFunctionNode);
                    if (optFunctionNode.isTargetOfDirectCall()) {
                        emitDirectConstructor(classFileWriter, optFunctionNode);
                    }
                }
                i++;
            } catch (ClassFileWriter.ClassFileFormatException e) {
                throw reportClassFileFormatException(scriptNode, e.getMessage());
            }
        }
        emitRegExpInit(classFileWriter);
        emitConstantDudeInitializers(classFileWriter);
        return classFileWriter.toByteArray();
    }

    private void emitDirectConstructor(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        classFileWriter.startMethod(getDirectCtorName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode), 10);
        int paramCount = optFunctionNode.fnode.getParamCount();
        int i = (paramCount * 3) + 4;
        int i2 = i + 1;
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/BaseFunction", "createObject", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addAStore(i2);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(i2);
        for (int i3 = 0; i3 < paramCount; i3++) {
            int i4 = i3 * 3;
            classFileWriter.addALoad(i4 + 4);
            classFileWriter.addDLoad(i4 + 5);
        }
        classFileWriter.addALoad(i);
        classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode));
        int acquireLabel = classFileWriter.acquireLabel();
        classFileWriter.add(89);
        classFileWriter.add(193, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(153, acquireLabel);
        classFileWriter.add(192, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(176);
        classFileWriter.markLabel(acquireLabel);
        classFileWriter.addALoad(i2);
        classFileWriter.add(176);
        classFileWriter.stopMethod((short) (i2 + 1));
    }

    static boolean isGenerator(ScriptNode scriptNode) {
        return scriptNode.getType() == 109 && ((FunctionNode) scriptNode).isGenerator();
    }

    private void generateResumeGenerator(ClassFileWriter classFileWriter) {
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (true) {
            ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
            if (i2 >= scriptNodeArr.length) {
                break;
            }
            if (isGenerator(scriptNodeArr[i2])) {
                z = true;
            }
            i2++;
        }
        if (z) {
            classFileWriter.startMethod("resumeGenerator", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 17);
            classFileWriter.addALoad(0);
            classFileWriter.addALoad(1);
            classFileWriter.addALoad(2);
            classFileWriter.addALoad(4);
            classFileWriter.addALoad(5);
            classFileWriter.addILoad(3);
            classFileWriter.addLoadThis();
            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
            int addTableSwitch = classFileWriter.addTableSwitch(0, this.scriptOrFnNodes.length - 1);
            classFileWriter.markTableSwitchDefault(addTableSwitch);
            int acquireLabel = classFileWriter.acquireLabel();
            while (true) {
                ScriptNode[] scriptNodeArr2 = this.scriptOrFnNodes;
                if (i < scriptNodeArr2.length) {
                    ScriptNode scriptNode = scriptNodeArr2[i];
                    classFileWriter.markTableSwitchCase(addTableSwitch, i, 6);
                    if (isGenerator(scriptNode)) {
                        String str = this.mainClassName;
                        classFileWriter.addInvoke(184, str, getBodyMethodName(scriptNode) + "_gen", "(" + this.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR + "Ljava/lang/Object;I)Ljava/lang/Object;");
                        classFileWriter.add(176);
                    } else {
                        classFileWriter.add(167, acquireLabel);
                    }
                    i++;
                } else {
                    classFileWriter.markLabel(acquireLabel);
                    pushUndefined(classFileWriter);
                    classFileWriter.add(176);
                    classFileWriter.stopMethod(6);
                    return;
                }
            }
        }
    }

    private void generateCallMethod(ClassFileWriter classFileWriter) {
        int i;
        int paramCount;
        ClassFileWriter classFileWriter2 = classFileWriter;
        classFileWriter2.startMethod(NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", 17);
        int acquireLabel = classFileWriter.acquireLabel();
        classFileWriter2.addALoad(1);
        classFileWriter2.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "hasTopCall", "(Lorg/mozilla/javascript/Context;)Z");
        classFileWriter2.add(154, acquireLabel);
        classFileWriter2.addALoad(0);
        classFileWriter2.addALoad(1);
        classFileWriter2.addALoad(2);
        classFileWriter2.addALoad(3);
        classFileWriter2.addALoad(4);
        classFileWriter2.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "doTopCall", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter2.add(176);
        classFileWriter2.markLabel(acquireLabel);
        classFileWriter2.addALoad(0);
        classFileWriter2.addALoad(1);
        classFileWriter2.addALoad(2);
        classFileWriter2.addALoad(3);
        classFileWriter2.addALoad(4);
        int length = this.scriptOrFnNodes.length;
        boolean z = 2 <= length;
        if (z) {
            classFileWriter.addLoadThis();
            classFileWriter2.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
            i = classFileWriter2.addTableSwitch(1, length - 1);
        } else {
            i = 0;
        }
        short s = 0;
        for (int i2 = 0; i2 != length; i2++) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i2];
            if (z) {
                if (i2 == 0) {
                    classFileWriter2.markTableSwitchDefault(i);
                    s = classFileWriter.getStackTop();
                } else {
                    classFileWriter2.markTableSwitchCase(i, i2 - 1, s);
                }
            }
            if (scriptNode.getType() == 109) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                if (optFunctionNode.isTargetOfDirectCall() && (paramCount = optFunctionNode.fnode.getParamCount()) != 0) {
                    for (int i3 = 0; i3 != paramCount; i3++) {
                        classFileWriter2.add(190);
                        classFileWriter2.addPush(i3);
                        int acquireLabel2 = classFileWriter.acquireLabel();
                        int acquireLabel3 = classFileWriter.acquireLabel();
                        classFileWriter2.add(164, acquireLabel2);
                        classFileWriter2.addALoad(4);
                        classFileWriter2.addPush(i3);
                        classFileWriter2.add(50);
                        classFileWriter2.add(167, acquireLabel3);
                        classFileWriter2.markLabel(acquireLabel2);
                        pushUndefined(classFileWriter);
                        classFileWriter2.markLabel(acquireLabel3);
                        classFileWriter2.adjustStackTop(-1);
                        classFileWriter2.addPush((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        classFileWriter2.addALoad(4);
                    }
                }
            }
            classFileWriter2.addInvoke(184, this.mainClassName, getBodyMethodName(scriptNode), getBodyMethodSignature(scriptNode));
            classFileWriter2.add(176);
        }
        classFileWriter2.stopMethod(5);
    }

    private void generateMain(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("main", "([Ljava/lang/String;)V", 9);
        classFileWriter.add(187, classFileWriter.getClassName());
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, classFileWriter.getClassName(), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, this.mainMethodClass, "main", "(Lorg/mozilla/javascript/Script;[Ljava/lang/String;)V");
        classFileWriter.add(177);
        classFileWriter.stopMethod(1);
    }

    private void generateExecute(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("exec", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;", 17);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.add(89);
        classFileWriter.add(1);
        classFileWriter.addInvoke(182, classFileWriter.getClassName(), NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter.add(176);
        classFileWriter.stopMethod(3);
    }

    private void generateScriptCtor(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", 1);
        classFileWriter.addLoadThis();
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.addLoadThis();
        classFileWriter.addPush(0);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.add(177);
        classFileWriter.stopMethod(1);
    }

    private void generateFunctionConstructor(ClassFileWriter classFileWriter) {
        int i;
        boolean z = true;
        classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, FUNCTION_CONSTRUCTOR_SIGNATURE, 1);
        short s = 0;
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.addLoadThis();
        classFileWriter.addILoad(3);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(1);
        int i2 = this.scriptOrFnNodes[0].getType() == 136 ? 1 : 0;
        int length = this.scriptOrFnNodes.length;
        if (i2 != length) {
            if (2 > length - i2) {
                z = false;
            }
            if (z) {
                classFileWriter.addILoad(3);
                i = classFileWriter.addTableSwitch(i2 + 1, length - 1);
            } else {
                i = 0;
            }
            for (int i3 = i2; i3 != length; i3++) {
                if (z) {
                    if (i3 == i2) {
                        classFileWriter.markTableSwitchDefault(i);
                        s = classFileWriter.getStackTop();
                    } else {
                        classFileWriter.markTableSwitchCase(i, (i3 - 1) - i2, s);
                    }
                }
                classFileWriter.addInvoke(183, this.mainClassName, getFunctionInitMethodName(OptFunctionNode.get(this.scriptOrFnNodes[i3])), FUNCTION_INIT_SIGNATURE);
                classFileWriter.add(177);
            }
            classFileWriter.stopMethod(4);
            return;
        }
        throw badTree();
    }

    private void generateFunctionInit(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        classFileWriter.startMethod(getFunctionInitMethodName(optFunctionNode), FUNCTION_INIT_SIGNATURE, 18);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/NativeFunction", "initScriptFunction", FUNCTION_INIT_SIGNATURE);
        if (optFunctionNode.fnode.getRegexpCount() != 0) {
            classFileWriter.addALoad(1);
            classFileWriter.addInvoke(184, this.mainClassName, REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE);
        }
        classFileWriter.add(177);
        classFileWriter.stopMethod(3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateNativeFunctionOverrides(org.mozilla.classfile.ClassFileWriter r19, java.lang.String r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "getLanguageVersion"
            java.lang.String r3 = "()I"
            r4 = 1
            r1.startMethod(r2, r3, r4)
            org.mozilla.javascript.CompilerEnvirons r2 = r0.compilerEnv
            int r2 = r2.getLanguageVersion()
            r1.addPush((int) r2)
            r2 = 172(0xac, float:2.41E-43)
            r1.add(r2)
            r1.stopMethod(r4)
            r6 = 0
        L_0x001e:
            r7 = 6
            if (r6 == r7) goto L_0x01cc
            r7 = 4
            if (r6 != r7) goto L_0x002b
            if (r20 != 0) goto L_0x002b
            r2 = 0
            r4 = 172(0xac, float:2.41E-43)
            goto L_0x01c5
        L_0x002b:
            java.lang.String r8 = "()Ljava/lang/String;"
            r9 = 5
            r10 = 2
            r11 = 3
            if (r6 == 0) goto L_0x0068
            if (r6 == r4) goto L_0x0062
            if (r6 == r10) goto L_0x005c
            if (r6 == r11) goto L_0x0053
            if (r6 == r7) goto L_0x004a
            if (r6 != r9) goto L_0x0045
            java.lang.String r8 = "getParamOrVarConst"
            java.lang.String r12 = "(I)Z"
            r1.startMethod(r8, r12, r4)
            r8 = 3
            goto L_0x006e
        L_0x0045:
            java.lang.RuntimeException r1 = org.mozilla.javascript.Kit.codeBug()
            throw r1
        L_0x004a:
            java.lang.String r12 = "getEncodedSource"
            r1.startMethod(r12, r8, r4)
            r19.addPush((java.lang.String) r20)
            goto L_0x006d
        L_0x0053:
            java.lang.String r8 = "getParamOrVarName"
            java.lang.String r12 = "(I)Ljava/lang/String;"
            r1.startMethod(r8, r12, r4)
            r8 = 2
            goto L_0x006e
        L_0x005c:
            java.lang.String r8 = "getParamAndVarCount"
            r1.startMethod(r8, r3, r4)
            goto L_0x006d
        L_0x0062:
            java.lang.String r8 = "getParamCount"
            r1.startMethod(r8, r3, r4)
            goto L_0x006d
        L_0x0068:
            java.lang.String r12 = "getFunctionName"
            r1.startMethod(r12, r8, r4)
        L_0x006d:
            r8 = 1
        L_0x006e:
            org.mozilla.javascript.ast.ScriptNode[] r12 = r0.scriptOrFnNodes
            int r12 = r12.length
            if (r12 <= r4) goto L_0x008a
            r19.addLoadThis()
            r13 = 180(0xb4, float:2.52E-43)
            java.lang.String r14 = r19.getClassName()
            java.lang.String r15 = "_id"
            java.lang.String r5 = "I"
            r1.add(r13, r14, r15, r5)
            int r5 = r12 + -1
            int r5 = r1.addTableSwitch(r4, r5)
            goto L_0x008b
        L_0x008a:
            r5 = 0
        L_0x008b:
            r13 = 0
            r14 = 0
        L_0x008d:
            if (r14 == r12) goto L_0x01bf
            org.mozilla.javascript.ast.ScriptNode[] r15 = r0.scriptOrFnNodes
            r15 = r15[r14]
            if (r14 != 0) goto L_0x009f
            if (r12 <= r4) goto L_0x00a4
            r1.markTableSwitchDefault(r5)
            short r13 = r19.getStackTop()
            goto L_0x00a4
        L_0x009f:
            int r2 = r14 + -1
            r1.markTableSwitchCase(r5, r2, r13)
        L_0x00a4:
            r2 = 176(0xb0, float:2.47E-43)
            if (r6 == 0) goto L_0x0195
            if (r6 == r4) goto L_0x0187
            if (r6 == r10) goto L_0x0179
            if (r6 == r11) goto L_0x012a
            if (r6 == r7) goto L_0x0109
            if (r6 != r9) goto L_0x0104
            int r2 = r15.getParamAndVarCount()
            boolean[] r15 = r15.getParamAndVarConst()
            if (r2 != 0) goto L_0x00c5
            r1.add(r11)
            r2 = 172(0xac, float:2.41E-43)
            r1.add(r2)
            goto L_0x0125
        L_0x00c5:
            r7 = 172(0xac, float:2.41E-43)
            if (r2 != r4) goto L_0x00d4
            r16 = 0
            boolean r2 = r15[r16]
            r1.addPush((boolean) r2)
            r1.add(r7)
            goto L_0x0125
        L_0x00d4:
            r1.addILoad(r4)
            int r7 = r2 + -1
            int r7 = r1.addTableSwitch(r4, r7)
            r9 = 0
        L_0x00de:
            if (r9 == r2) goto L_0x0125
            short r17 = r19.getStackTop()
            if (r17 == 0) goto L_0x00e9
            org.mozilla.javascript.Kit.codeBug()
        L_0x00e9:
            if (r9 != 0) goto L_0x00ef
            r1.markTableSwitchDefault(r7)
            goto L_0x00f5
        L_0x00ef:
            int r10 = r9 + -1
            r11 = 0
            r1.markTableSwitchCase(r7, r10, r11)
        L_0x00f5:
            boolean r10 = r15[r9]
            r1.addPush((boolean) r10)
            r10 = 172(0xac, float:2.41E-43)
            r1.add(r10)
            int r9 = r9 + 1
            r10 = 2
            r11 = 3
            goto L_0x00de
        L_0x0104:
            java.lang.RuntimeException r1 = org.mozilla.javascript.Kit.codeBug()
            throw r1
        L_0x0109:
            int r7 = r15.getEncodedSourceStart()
            r1.addPush((int) r7)
            int r7 = r15.getEncodedSourceEnd()
            r1.addPush((int) r7)
            r7 = 182(0xb6, float:2.55E-43)
            java.lang.String r9 = "java/lang/String"
            java.lang.String r10 = "substring"
            java.lang.String r11 = "(II)Ljava/lang/String;"
            r1.addInvoke(r7, r9, r10, r11)
            r1.add(r2)
        L_0x0125:
            r2 = 0
            r4 = 172(0xac, float:2.41E-43)
            goto L_0x01b4
        L_0x012a:
            int r7 = r15.getParamAndVarCount()
            if (r7 != 0) goto L_0x0137
            r1.add(r4)
            r1.add(r2)
            goto L_0x0125
        L_0x0137:
            if (r7 != r4) goto L_0x0145
            r9 = 0
            java.lang.String r7 = r15.getParamOrVarName(r9)
            r1.addPush((java.lang.String) r7)
            r1.add(r2)
            goto L_0x0125
        L_0x0145:
            r1.addILoad(r4)
            int r9 = r7 + -1
            int r9 = r1.addTableSwitch(r4, r9)
            r10 = 0
        L_0x014f:
            if (r10 == r7) goto L_0x0125
            short r11 = r19.getStackTop()
            if (r11 == 0) goto L_0x015a
            org.mozilla.javascript.Kit.codeBug()
        L_0x015a:
            java.lang.String r11 = r15.getParamOrVarName(r10)
            if (r10 != 0) goto L_0x0165
            r1.markTableSwitchDefault(r9)
            r2 = 0
            goto L_0x016b
        L_0x0165:
            int r4 = r10 + -1
            r2 = 0
            r1.markTableSwitchCase(r9, r4, r2)
        L_0x016b:
            r1.addPush((java.lang.String) r11)
            r4 = 176(0xb0, float:2.47E-43)
            r1.add(r4)
            int r10 = r10 + 1
            r2 = 176(0xb0, float:2.47E-43)
            r4 = 1
            goto L_0x014f
        L_0x0179:
            r2 = 0
            int r4 = r15.getParamAndVarCount()
            r1.addPush((int) r4)
            r4 = 172(0xac, float:2.41E-43)
            r1.add(r4)
            goto L_0x01b4
        L_0x0187:
            r2 = 0
            r4 = 172(0xac, float:2.41E-43)
            int r7 = r15.getParamCount()
            r1.addPush((int) r7)
            r1.add(r4)
            goto L_0x01b4
        L_0x0195:
            r2 = 0
            r4 = 172(0xac, float:2.41E-43)
            int r7 = r15.getType()
            r9 = 136(0x88, float:1.9E-43)
            if (r7 != r9) goto L_0x01a6
            java.lang.String r7 = ""
            r1.addPush((java.lang.String) r7)
            goto L_0x01af
        L_0x01a6:
            org.mozilla.javascript.ast.FunctionNode r15 = (org.mozilla.javascript.ast.FunctionNode) r15
            java.lang.String r7 = r15.getName()
            r1.addPush((java.lang.String) r7)
        L_0x01af:
            r7 = 176(0xb0, float:2.47E-43)
            r1.add(r7)
        L_0x01b4:
            int r14 = r14 + 1
            r2 = 172(0xac, float:2.41E-43)
            r4 = 1
            r7 = 4
            r9 = 5
            r10 = 2
            r11 = 3
            goto L_0x008d
        L_0x01bf:
            r2 = 0
            r4 = 172(0xac, float:2.41E-43)
            r1.stopMethod(r8)
        L_0x01c5:
            int r6 = r6 + 1
            r2 = 172(0xac, float:2.41E-43)
            r4 = 1
            goto L_0x001e
        L_0x01cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.optimizer.Codegen.generateNativeFunctionOverrides(org.mozilla.classfile.ClassFileWriter, java.lang.String):void");
    }

    private void emitRegExpInit(ClassFileWriter classFileWriter) {
        ClassFileWriter classFileWriter2 = classFileWriter;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            ScriptNode[] scriptNodeArr = this.scriptOrFnNodes;
            if (i2 == scriptNodeArr.length) {
                break;
            }
            i3 += scriptNodeArr[i2].getRegexpCount();
            i2++;
        }
        if (i3 != 0) {
            short s = 10;
            classFileWriter2.startMethod(REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE, 10);
            classFileWriter2.addField("_reInitDone", "Z", 74);
            classFileWriter2.add(178, this.mainClassName, "_reInitDone", "Z");
            int acquireLabel = classFileWriter.acquireLabel();
            classFileWriter2.add(153, acquireLabel);
            classFileWriter2.add(177);
            classFileWriter2.markLabel(acquireLabel);
            classFileWriter2.addALoad(0);
            classFileWriter2.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "checkRegExpProxy", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/RegExpProxy;");
            classFileWriter2.addAStore(1);
            int i4 = 0;
            while (true) {
                ScriptNode[] scriptNodeArr2 = this.scriptOrFnNodes;
                if (i4 != scriptNodeArr2.length) {
                    ScriptNode scriptNode = scriptNodeArr2[i4];
                    int regexpCount = scriptNode.getRegexpCount();
                    int i5 = 0;
                    while (i5 != regexpCount) {
                        String compiledRegexpName = getCompiledRegexpName(scriptNode, i5);
                        String regexpString = scriptNode.getRegexpString(i5);
                        String regexpFlags = scriptNode.getRegexpFlags(i5);
                        classFileWriter2.addField(compiledRegexpName, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR, s);
                        classFileWriter2.addALoad(1);
                        classFileWriter2.addALoad(i);
                        classFileWriter2.addPush(regexpString);
                        if (regexpFlags == null) {
                            classFileWriter2.add(1);
                        } else {
                            classFileWriter2.addPush(regexpFlags);
                        }
                        classFileWriter2.addInvoke(185, "org/mozilla/javascript/RegExpProxy", "compileRegExp", "(Lorg/mozilla/javascript/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
                        classFileWriter2.add(179, this.mainClassName, compiledRegexpName, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR);
                        i5++;
                        i = 0;
                        s = 10;
                    }
                    i4++;
                    i = 0;
                    s = 10;
                } else {
                    classFileWriter2.addPush(1);
                    classFileWriter2.add(179, this.mainClassName, "_reInitDone", "Z");
                    classFileWriter2.add(177);
                    classFileWriter2.stopMethod(2);
                    return;
                }
            }
        }
    }

    private void emitConstantDudeInitializers(ClassFileWriter classFileWriter) {
        int i = this.itsConstantListSize;
        if (i != 0) {
            classFileWriter.startMethod(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME, "()V", 24);
            double[] dArr = this.itsConstantList;
            for (int i2 = 0; i2 != i; i2++) {
                double d = dArr[i2];
                String str = "_k" + i2;
                String staticConstantWrapperType = getStaticConstantWrapperType(d);
                classFileWriter.addField(str, staticConstantWrapperType, 10);
                int i3 = (int) d;
                if (((double) i3) == d) {
                    classFileWriter.addPush(i3);
                    classFileWriter.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                } else {
                    classFileWriter.addPush(d);
                    addDoubleWrap(classFileWriter);
                }
                classFileWriter.add(179, this.mainClassName, str, staticConstantWrapperType);
            }
            classFileWriter.add(177);
            classFileWriter.stopMethod(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void pushNumberAsObject(ClassFileWriter classFileWriter, double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            if (1.0d / d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "zeroObj", "Ljava/lang/Double;");
                return;
            }
            classFileWriter.addPush(d);
            addDoubleWrap(classFileWriter);
        } else if (d == 1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "oneObj", "Ljava/lang/Double;");
        } else if (d == -1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "minusOneObj", "Ljava/lang/Double;");
        } else if (d != d) {
            classFileWriter.add(178, "org/mozilla/javascript/ScriptRuntime", "NaNobj", "Ljava/lang/Double;");
        } else {
            int i = this.itsConstantListSize;
            if (i >= 2000) {
                classFileWriter.addPush(d);
                addDoubleWrap(classFileWriter);
                return;
            }
            int i2 = 0;
            if (i == 0) {
                this.itsConstantList = new double[64];
            } else {
                double[] dArr = this.itsConstantList;
                int i3 = 0;
                while (i3 != i && dArr[i3] != d) {
                    i3++;
                }
                if (i == dArr.length) {
                    double[] dArr2 = new double[(i * 2)];
                    System.arraycopy(this.itsConstantList, 0, dArr2, 0, i);
                    this.itsConstantList = dArr2;
                }
                i2 = i3;
            }
            if (i2 == i) {
                this.itsConstantList[i] = d;
                this.itsConstantListSize = i + 1;
            }
            classFileWriter.add(178, this.mainClassName, "_k" + i2, getStaticConstantWrapperType(d));
        }
    }

    private static void addDoubleWrap(ClassFileWriter classFileWriter) {
        classFileWriter.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", "wrapDouble", "(D)Ljava/lang/Double;");
    }

    static void pushUndefined(ClassFileWriter classFileWriter) {
        classFileWriter.add(178, "org/mozilla/javascript/Undefined", "instance", TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR);
    }

    /* access modifiers changed from: package-private */
    public int getIndex(ScriptNode scriptNode) {
        return this.scriptOrFnIndexes.getExisting(scriptNode);
    }

    /* access modifiers changed from: package-private */
    public String getDirectCtorName(ScriptNode scriptNode) {
        return "_n" + getIndex(scriptNode);
    }

    /* access modifiers changed from: package-private */
    public String getBodyMethodName(ScriptNode scriptNode) {
        return "_c_" + cleanName(scriptNode) + "_" + getIndex(scriptNode);
    }

    /* access modifiers changed from: package-private */
    public String cleanName(ScriptNode scriptNode) {
        if (!(scriptNode instanceof FunctionNode)) {
            return "script";
        }
        Name functionName = ((FunctionNode) scriptNode).getFunctionName();
        if (functionName == null) {
            return "anonymous";
        }
        return functionName.getIdentifier();
    }

    /* access modifiers changed from: package-private */
    public String getBodyMethodSignature(ScriptNode scriptNode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        stringBuffer.append(this.mainClassSignature);
        stringBuffer.append("Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;");
        if (scriptNode.getType() == 109) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
            if (optFunctionNode.isTargetOfDirectCall()) {
                int paramCount = optFunctionNode.fnode.getParamCount();
                for (int i = 0; i != paramCount; i++) {
                    stringBuffer.append("Ljava/lang/Object;D");
                }
            }
        }
        stringBuffer.append("[Ljava/lang/Object;)Ljava/lang/Object;");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getFunctionInitMethodName(OptFunctionNode optFunctionNode) {
        return "_i" + getIndex(optFunctionNode.fnode);
    }

    /* access modifiers changed from: package-private */
    public String getCompiledRegexpName(ScriptNode scriptNode, int i) {
        return "_re" + getIndex(scriptNode) + "_" + i;
    }

    static RuntimeException badTree() {
        throw new RuntimeException("Bad tree in codegen");
    }

    public void setMainMethodClass(String str) {
        this.mainMethodClass = str;
    }
}
