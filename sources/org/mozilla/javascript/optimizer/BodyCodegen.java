package org.mozilla.javascript.optimizer;

import androidx.core.app.NotificationCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.drugs.helper.SearchHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

/* compiled from: Codegen */
class BodyCodegen {
    private static final int ECMAERROR_EXCEPTION = 2;
    private static final int EVALUATOR_EXCEPTION = 1;
    private static final int EXCEPTION_MAX = 5;
    private static final int FINALLY_EXCEPTION = 4;
    static final int GENERATOR_START = 0;
    static final int GENERATOR_TERMINATE = -1;
    static final int GENERATOR_YIELD_START = 1;
    private static final int JAVASCRIPT_EXCEPTION = 0;
    private static final int MAX_LOCALS = 1024;
    private static final int THROWABLE_EXCEPTION = 3;
    private short argsLocal;
    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    private short contextLocal;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private ExceptionManager exceptionManager = new ExceptionManager();
    private Map<Node, FinallyReturnPoint> finallys;
    private short firstFreeLocal;
    private OptFunctionNode fnCurrent;
    private short funObjLocal;
    private short generatorStateLocal;
    private int generatorSwitch;
    private boolean hasVarsInRegs;
    private boolean inDirectCallFunction;
    private boolean inLocalBlock;
    private boolean isGenerator;
    private boolean itsForcedObjectParameters;
    private int itsLineNumber;
    private short itsOneArgArray;
    private short itsZeroArgArray;
    private List<Node> literals;
    private int[] locals;
    private short localsMax;
    private int maxLocals = 0;
    private int maxStack = 0;
    private short operationLocal;
    private short popvLocal;
    private int savedCodeOffset;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private short thisObjLocal;
    private short[] varRegisters;
    private short variableObjectLocal;

    BodyCodegen() {
    }

    /* access modifiers changed from: package-private */
    public void generateBodyCode() {
        Node node;
        this.isGenerator = Codegen.isGenerator(this.scriptOrFn);
        initBodyGeneration();
        if (this.isGenerator) {
            ClassFileWriter classFileWriter = this.cfw;
            classFileWriter.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_gen", "(" + this.codegen.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR + "Ljava/lang/Object;I)Ljava/lang/Object;", 10);
        } else {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), 10);
        }
        generatePrologue();
        if (this.fnCurrent != null) {
            node = this.scriptOrFn.getLastChild();
        } else {
            node = this.scriptOrFn;
        }
        generateStatement(node);
        generateEpilogue();
        this.cfw.stopMethod((short) (this.localsMax + 1));
        if (this.isGenerator) {
            generateGenerator();
        }
        if (this.literals != null) {
            for (int i = 0; i < this.literals.size(); i++) {
                Node node2 = this.literals.get(i);
                int type = node2.getType();
                if (type == 65) {
                    generateArrayLiteralFactory(node2, i + 1);
                } else if (type != 66) {
                    Kit.codeBug(Token.typeToName(type));
                } else {
                    generateObjectLiteralFactory(node2, i + 1);
                }
            }
        }
    }

    private void generateGenerator() {
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), 10);
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.argsLocal);
        addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(this.scriptOrFnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        generateNestedFunctionInits();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addLoadConstant(this.maxLocals);
        this.cfw.addLoadConstant(this.maxStack);
        addOptRuntimeInvoke("createNativeGenerator", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;II)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateNestedFunctionInits() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, i);
            if (optFunctionNode.fnode.getFunctionType() == 1) {
                visitFunction(optFunctionNode, 1);
            }
        }
    }

    private void initBodyGeneration() {
        int paramAndVarCount;
        this.varRegisters = null;
        if (this.scriptOrFn.getType() == 109) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn);
            this.fnCurrent = optFunctionNode;
            boolean z = !optFunctionNode.fnode.requiresActivation();
            this.hasVarsInRegs = z;
            if (z && (paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount()) != 0) {
                this.varRegisters = new short[paramAndVarCount];
            }
            boolean isTargetOfDirectCall = this.fnCurrent.isTargetOfDirectCall();
            this.inDirectCallFunction = isTargetOfDirectCall;
            if (isTargetOfDirectCall && !this.hasVarsInRegs) {
                Codegen.badTree();
            }
        } else {
            this.fnCurrent = null;
            this.hasVarsInRegs = false;
            this.inDirectCallFunction = false;
        }
        this.locals = new int[1024];
        this.funObjLocal = 0;
        this.contextLocal = 1;
        this.variableObjectLocal = 2;
        this.thisObjLocal = 3;
        this.localsMax = 4;
        this.firstFreeLocal = 4;
        this.popvLocal = -1;
        this.argsLocal = -1;
        this.itsZeroArgArray = -1;
        this.itsOneArgArray = -1;
        this.epilogueLabel = -1;
        this.enterAreaStartLabel = -1;
        this.generatorStateLocal = -1;
    }

    private void generatePrologue() {
        String str;
        short s;
        if (this.inDirectCallFunction) {
            int paramCount = this.scriptOrFn.getParamCount();
            if (this.firstFreeLocal != 4) {
                Kit.codeBug();
            }
            for (int i = 0; i != paramCount; i++) {
                short[] sArr = this.varRegisters;
                short s2 = this.firstFreeLocal;
                sArr[i] = s2;
                this.firstFreeLocal = (short) (s2 + 3);
            }
            if (!this.fnCurrent.getParameterNumberContext()) {
                this.itsForcedObjectParameters = true;
                for (int i2 = 0; i2 != paramCount; i2++) {
                    short s3 = this.varRegisters[i2];
                    this.cfw.addALoad(s3);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, acquireLabel);
                    this.cfw.addDLoad(s3 + 1);
                    addDoubleWrap();
                    this.cfw.addAStore(s3);
                    this.cfw.markLabel(acquireLabel);
                }
            }
        }
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        short s4 = this.firstFreeLocal;
        short s5 = (short) (s4 + 1);
        this.firstFreeLocal = s5;
        this.argsLocal = s4;
        this.localsMax = s5;
        if (this.isGenerator) {
            short s6 = (short) (s5 + 1);
            this.firstFreeLocal = s6;
            this.operationLocal = s5;
            this.localsMax = s6;
            this.cfw.addALoad(this.thisObjLocal);
            short s7 = this.firstFreeLocal;
            short s8 = (short) (s7 + 1);
            this.firstFreeLocal = s8;
            this.generatorStateLocal = s7;
            this.localsMax = s8;
            this.cfw.add(192, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState");
            this.cfw.add(89);
            this.cfw.addAStore(this.generatorStateLocal);
            this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "thisObj", "Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.thisObjLocal);
            if (this.epilogueLabel == -1) {
                this.epilogueLabel = this.cfw.acquireLabel();
            }
            List<Node> resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
            if (resumptionPoints != null) {
                generateGetGeneratorResumptionPoint();
                this.generatorSwitch = this.cfw.addTableSwitch(0, resumptionPoints.size() + 0);
                generateCheckForThrowOrClose(-1, false, 0);
            }
        }
        if (this.fnCurrent == null && this.scriptOrFn.getRegexpCount() != 0) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addInvoke(184, this.codegen.mainClassName, "_reInit", "(Lorg/mozilla/javascript/Context;)V");
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            saveCurrentCodeOffset();
        }
        if (this.hasVarsInRegs) {
            int paramCount2 = this.scriptOrFn.getParamCount();
            if (paramCount2 > 0 && !this.inDirectCallFunction) {
                this.cfw.addALoad(this.argsLocal);
                this.cfw.add(190);
                this.cfw.addPush(paramCount2);
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(162, acquireLabel2);
                this.cfw.addALoad(this.argsLocal);
                this.cfw.addPush(paramCount2);
                addScriptRuntimeInvoke("padArguments", "([Ljava/lang/Object;I)[Ljava/lang/Object;");
                this.cfw.addAStore(this.argsLocal);
                this.cfw.markLabel(acquireLabel2);
            }
            int paramCount3 = this.fnCurrent.fnode.getParamCount();
            int paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount();
            boolean[] paramAndVarConst = this.fnCurrent.fnode.getParamAndVarConst();
            short s9 = -1;
            for (int i3 = 0; i3 != paramAndVarCount; i3++) {
                if (i3 < paramCount3) {
                    if (!this.inDirectCallFunction) {
                        s = getNewWordLocal();
                        this.cfw.addALoad(this.argsLocal);
                        this.cfw.addPush(i3);
                        this.cfw.add(50);
                        this.cfw.addAStore(s);
                    } else {
                        s = -1;
                    }
                } else if (this.fnCurrent.isNumberVar(i3)) {
                    s = getNewWordPairLocal(paramAndVarConst[i3]);
                    this.cfw.addPush((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    this.cfw.addDStore(s);
                } else {
                    s = getNewWordLocal(paramAndVarConst[i3]);
                    if (s9 == -1) {
                        Codegen.pushUndefined(this.cfw);
                        s9 = s;
                    } else {
                        this.cfw.addALoad(s9);
                    }
                    this.cfw.addAStore(s);
                }
                if (s >= 0) {
                    if (paramAndVarConst[i3]) {
                        this.cfw.addPush(0);
                        this.cfw.addIStore((this.fnCurrent.isNumberVar(i3) ? (short) 2 : 1) + s);
                    }
                    this.varRegisters[i3] = s;
                }
                if (this.compilerEnv.isGenerateDebugInfo()) {
                    String paramOrVarName = this.fnCurrent.fnode.getParamOrVarName(i3);
                    String str2 = this.fnCurrent.isNumberVar(i3) ? SearchHelper.TYPE_DRUG : TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR;
                    int currentCodeOffset = this.cfw.getCurrentCodeOffset();
                    if (s < 0) {
                        s = this.varRegisters[i3];
                    }
                    this.cfw.addVariableDescriptor(paramOrVarName, str2, currentCodeOffset, s);
                }
            }
        } else if (!this.isGenerator) {
            if (this.fnCurrent != null) {
                this.cfw.addALoad(this.funObjLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addALoad(this.argsLocal);
                addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("enterActivationFunction", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
                str = "activation";
            } else {
                this.cfw.addALoad(this.funObjLocal);
                this.cfw.addALoad(this.thisObjLocal);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(0);
                addScriptRuntimeInvoke("initScript", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Z)V");
                str = "global";
            }
            this.enterAreaStartLabel = this.cfw.acquireLabel();
            this.epilogueLabel = this.cfw.acquireLabel();
            this.cfw.markLabel(this.enterAreaStartLabel);
            generateNestedFunctionInits();
            if (this.compilerEnv.isGenerateDebugInfo()) {
                ClassFileWriter classFileWriter = this.cfw;
                classFileWriter.addVariableDescriptor(str, "Lorg/mozilla/javascript/Scriptable;", classFileWriter.getCurrentCodeOffset(), this.variableObjectLocal);
            }
            OptFunctionNode optFunctionNode = this.fnCurrent;
            if (optFunctionNode == null) {
                this.popvLocal = getNewWordLocal();
                Codegen.pushUndefined(this.cfw);
                this.cfw.addAStore(this.popvLocal);
                int endLineno = this.scriptOrFn.getEndLineno();
                if (endLineno != -1) {
                    this.cfw.addLineNumberEntry((short) endLineno);
                    return;
                }
                return;
            }
            if (optFunctionNode.itsContainsCalls0) {
                this.itsZeroArgArray = getNewWordLocal();
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
                this.cfw.addAStore(this.itsZeroArgArray);
            }
            if (this.fnCurrent.itsContainsCalls1) {
                this.itsOneArgArray = getNewWordLocal();
                this.cfw.addPush(1);
                this.cfw.add(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                this.cfw.addAStore(this.itsOneArgArray);
            }
        }
    }

    private void generateGetGeneratorResumptionPoint() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateSetGeneratorResumptionPoint(int i) {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.addLoadConstant(i);
        this.cfw.add(181, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateGetGeneratorStackState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorStackState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateEpilogue() {
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.isGenerator) {
            Map<Node, int[]> liveLocals = ((FunctionNode) this.scriptOrFn).getLiveLocals();
            if (liveLocals != null) {
                List<Node> resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
                for (int i = 0; i < resumptionPoints.size(); i++) {
                    Node node = resumptionPoints.get(i);
                    int[] iArr = liveLocals.get(node);
                    if (iArr != null) {
                        this.cfw.markTableSwitchCase(this.generatorSwitch, getNextGeneratorState(node));
                        generateGetGeneratorLocalsState();
                        for (int i2 = 0; i2 < iArr.length; i2++) {
                            this.cfw.add(89);
                            this.cfw.addLoadConstant(i2);
                            this.cfw.add(50);
                            this.cfw.addAStore(iArr[i2]);
                        }
                        this.cfw.add(87);
                        this.cfw.add(167, getTargetLabel(node));
                    }
                }
            }
            Map<Node, FinallyReturnPoint> map = this.finallys;
            if (map != null) {
                for (Node next : map.keySet()) {
                    if (next.getType() == 125) {
                        FinallyReturnPoint finallyReturnPoint = this.finallys.get(next);
                        this.cfw.markLabel(finallyReturnPoint.tableLabel, 1);
                        int addTableSwitch = this.cfw.addTableSwitch(0, finallyReturnPoint.jsrPoints.size() - 1);
                        this.cfw.markTableSwitchDefault(addTableSwitch);
                        int i3 = 0;
                        for (int i4 = 0; i4 < finallyReturnPoint.jsrPoints.size(); i4++) {
                            this.cfw.markTableSwitchCase(addTableSwitch, i3);
                            this.cfw.add(167, finallyReturnPoint.jsrPoints.get(i4).intValue());
                            i3++;
                        }
                    }
                }
            }
        }
        int i5 = this.epilogueLabel;
        if (i5 != -1) {
            this.cfw.markLabel(i5);
        }
        if (this.hasVarsInRegs) {
            this.cfw.add(176);
        } else if (this.isGenerator) {
            if (((FunctionNode) this.scriptOrFn).getResumptionPoints() != null) {
                this.cfw.markTableSwitchDefault(this.generatorSwitch);
            }
            generateSetGeneratorResumptionPoint(-1);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke("throwStopIteration", "(Ljava/lang/Object;)V");
            Codegen.pushUndefined(this.cfw);
            this.cfw.add(176);
        } else if (this.fnCurrent == null) {
            this.cfw.addALoad(this.popvLocal);
            this.cfw.add(176);
        } else {
            generateActivationExit();
            this.cfw.add(176);
            int acquireLabel = this.cfw.acquireLabel();
            this.cfw.markHandler(acquireLabel);
            short newWordLocal = getNewWordLocal();
            this.cfw.addAStore(newWordLocal);
            generateActivationExit();
            this.cfw.addALoad(newWordLocal);
            releaseWordLocal(newWordLocal);
            this.cfw.add(191);
            this.cfw.addExceptionHandler(this.enterAreaStartLabel, this.epilogueLabel, acquireLabel, (String) null);
        }
    }

    private void generateGetGeneratorLocalsState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorLocalsState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateActivationExit() {
        if (this.fnCurrent == null || this.hasVarsInRegs) {
            throw Kit.codeBug();
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("exitActivationFunction", "(Lorg/mozilla/javascript/Context;)V");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:146:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0145, code lost:
        if (r7.compilerEnv.isGenerateObserverCount() == false) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0147, code lost:
        addInstructionCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014a, code lost:
        visitGoto((org.mozilla.javascript.ast.Jump) r8, r0, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateStatement(org.mozilla.javascript.Node r8) {
        /*
            r7 = this;
            r7.updateLineNumber(r8)
            int r0 = r8.getType()
            org.mozilla.javascript.Node r1 = r8.getFirstChild()
            r2 = 50
            if (r0 == r2) goto L_0x02e7
            r2 = 51
            if (r0 == r2) goto L_0x02cb
            r2 = 64
            r3 = 167(0xa7, float:2.34E-43)
            r4 = -1
            if (r0 == r2) goto L_0x0280
            r2 = 81
            if (r0 == r2) goto L_0x0279
            r2 = 109(0x6d, float:1.53E-43)
            r5 = 1
            if (r0 == r2) goto L_0x0258
            r2 = 114(0x72, float:1.6E-43)
            if (r0 == r2) goto L_0x0246
            r2 = 123(0x7b, float:1.72E-43)
            if (r0 == r2) goto L_0x0231
            r2 = 125(0x7d, float:1.75E-43)
            if (r0 == r2) goto L_0x01c1
            r2 = 141(0x8d, float:1.98E-43)
            r6 = 2
            if (r0 == r2) goto L_0x0193
            r2 = 160(0xa0, float:2.24E-43)
            if (r0 == r2) goto L_0x02f8
            switch(r0) {
                case 2: goto L_0x016d;
                case 3: goto L_0x0151;
                case 4: goto L_0x0280;
                case 5: goto L_0x013f;
                case 6: goto L_0x013f;
                case 7: goto L_0x013f;
                default: goto L_0x003b;
            }
        L_0x003b:
            r2 = 0
            switch(r0) {
                case 57: goto L_0x00f7;
                case 58: goto L_0x00ca;
                case 59: goto L_0x00ca;
                case 60: goto L_0x00ca;
                default: goto L_0x003f;
            }
        L_0x003f:
            switch(r0) {
                case 128: goto L_0x0231;
                case 129: goto L_0x0231;
                case 130: goto L_0x0231;
                case 131: goto L_0x00a9;
                case 132: goto L_0x0231;
                case 133: goto L_0x005d;
                case 134: goto L_0x0047;
                case 135: goto L_0x013f;
                case 136: goto L_0x0231;
                default: goto L_0x0042;
            }
        L_0x0042:
            java.lang.RuntimeException r8 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r8
        L_0x0047:
            r7.generateExpression(r1, r8)
            short r8 = r7.popvLocal
            if (r8 >= 0) goto L_0x0054
            short r8 = r7.getNewWordLocal()
            r7.popvLocal = r8
        L_0x0054:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.popvLocal
            r8.addAStore(r0)
            goto L_0x02f8
        L_0x005d:
            int r0 = r1.getType()
            r3 = 56
            if (r0 != r3) goto L_0x006e
            org.mozilla.javascript.Node r8 = r1.getFirstChild()
            r7.visitSetVar(r1, r8, r2)
            goto L_0x02f8
        L_0x006e:
            int r0 = r1.getType()
            r3 = 156(0x9c, float:2.19E-43)
            if (r0 != r3) goto L_0x007f
            org.mozilla.javascript.Node r8 = r1.getFirstChild()
            r7.visitSetConstVar(r1, r8, r2)
            goto L_0x02f8
        L_0x007f:
            int r0 = r1.getType()
            r3 = 72
            if (r0 != r3) goto L_0x008c
            r7.generateYieldPoint(r1, r2)
            goto L_0x02f8
        L_0x008c:
            r7.generateExpression(r1, r8)
            r0 = 8
            int r8 = r8.getIntProp(r0, r4)
            if (r8 == r4) goto L_0x00a0
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r0 = 88
            r8.add(r0)
            goto L_0x02f8
        L_0x00a0:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r0 = 87
            r8.add(r0)
            goto L_0x02f8
        L_0x00a9:
            org.mozilla.javascript.CompilerEnvirons r0 = r7.compilerEnv
            boolean r0 = r0.isGenerateObserverCount()
            if (r0 == 0) goto L_0x00b4
            r7.addInstructionCount()
        L_0x00b4:
            int r8 = r7.getTargetLabel(r8)
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            r0.markLabel(r8)
            org.mozilla.javascript.CompilerEnvirons r8 = r7.compilerEnv
            boolean r8 = r8.isGenerateObserverCount()
            if (r8 == 0) goto L_0x02f8
            r7.saveCurrentCodeOffset()
            goto L_0x02f8
        L_0x00ca:
            r7.generateExpression(r1, r8)
            org.mozilla.classfile.ClassFileWriter r1 = r7.cfw
            short r3 = r7.contextLocal
            r1.addALoad(r3)
            r1 = 58
            if (r0 != r1) goto L_0x00da
            r5 = 0
            goto L_0x00e0
        L_0x00da:
            r1 = 59
            if (r0 != r1) goto L_0x00df
            goto L_0x00e0
        L_0x00df:
            r5 = 2
        L_0x00e0:
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            r0.addPush((int) r5)
            java.lang.String r0 = "enumInit"
            java.lang.String r1 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;"
            r7.addScriptRuntimeInvoke(r0, r1)
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            int r8 = r7.getLocalBlockRegister(r8)
            r0.addAStore(r8)
            goto L_0x02f8
        L_0x00f7:
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            r0.setStackTop(r2)
            int r0 = r7.getLocalBlockRegister(r8)
            r2 = 14
            int r2 = r8.getExistingIntProp(r2)
            java.lang.String r3 = r1.getString()
            org.mozilla.javascript.Node r1 = r1.getNext()
            r7.generateExpression(r1, r8)
            if (r2 != 0) goto L_0x0119
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r8.add(r5)
            goto L_0x011e
        L_0x0119:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r8.addALoad(r0)
        L_0x011e:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r8.addPush((java.lang.String) r3)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r1 = r7.contextLocal
            r8.addALoad(r1)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r1 = r7.variableObjectLocal
            r8.addALoad(r1)
            java.lang.String r8 = "newCatchScope"
            java.lang.String r1 = "(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;"
            r7.addScriptRuntimeInvoke(r8, r1)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r8.addAStore(r0)
            goto L_0x02f8
        L_0x013f:
            org.mozilla.javascript.CompilerEnvirons r2 = r7.compilerEnv
            boolean r2 = r2.isGenerateObserverCount()
            if (r2 == 0) goto L_0x014a
            r7.addInstructionCount()
        L_0x014a:
            org.mozilla.javascript.ast.Jump r8 = (org.mozilla.javascript.ast.Jump) r8
            r7.visitGoto(r8, r0, r1)
            goto L_0x02f8
        L_0x0151:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.variableObjectLocal
            r8.addALoad(r0)
            java.lang.String r8 = "leaveWith"
            java.lang.String r0 = "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;"
            r7.addScriptRuntimeInvoke(r8, r0)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.variableObjectLocal
            r8.addAStore(r0)
            short r8 = r7.variableObjectLocal
            r7.decReferenceWordLocal(r8)
            goto L_0x02f8
        L_0x016d:
            r7.generateExpression(r1, r8)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.contextLocal
            r8.addALoad(r0)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.variableObjectLocal
            r8.addALoad(r0)
            java.lang.String r8 = "enterWith"
            java.lang.String r0 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;"
            r7.addScriptRuntimeInvoke(r8, r0)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            short r0 = r7.variableObjectLocal
            r8.addAStore(r0)
            short r8 = r7.variableObjectLocal
            r7.incReferenceWordLocal(r8)
            goto L_0x02f8
        L_0x0193:
            boolean r0 = r7.inLocalBlock
            r7.inLocalBlock = r5
            short r2 = r7.getNewWordLocal()
            boolean r3 = r7.isGenerator
            if (r3 == 0) goto L_0x01a9
            org.mozilla.classfile.ClassFileWriter r3 = r7.cfw
            r3.add(r5)
            org.mozilla.classfile.ClassFileWriter r3 = r7.cfw
            r3.addAStore(r2)
        L_0x01a9:
            r8.putIntProp(r6, r2)
        L_0x01ac:
            if (r1 == 0) goto L_0x01b6
            r7.generateStatement(r1)
            org.mozilla.javascript.Node r1 = r1.getNext()
            goto L_0x01ac
        L_0x01b6:
            short r1 = (short) r2
            r7.releaseWordLocal(r1)
            r8.removeProp(r6)
            r7.inLocalBlock = r0
            goto L_0x02f8
        L_0x01c1:
            boolean r0 = r7.isGenerator
            if (r0 != 0) goto L_0x01c7
            goto L_0x02f8
        L_0x01c7:
            org.mozilla.javascript.CompilerEnvirons r0 = r7.compilerEnv
            boolean r0 = r0.isGenerateObserverCount()
            if (r0 == 0) goto L_0x01d2
            r7.saveCurrentCodeOffset()
        L_0x01d2:
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            r0.setStackTop(r5)
            short r0 = r7.getNewWordLocal()
            org.mozilla.classfile.ClassFileWriter r2 = r7.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r4 = r7.cfw
            int r4 = r4.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r5 = r7.cfw
            r5.markLabel(r2)
            r7.generateIntegerWrap()
            org.mozilla.classfile.ClassFileWriter r2 = r7.cfw
            r2.addAStore(r0)
        L_0x01f4:
            if (r1 == 0) goto L_0x01fe
            r7.generateStatement(r1)
            org.mozilla.javascript.Node r1 = r1.getNext()
            goto L_0x01f4
        L_0x01fe:
            org.mozilla.classfile.ClassFileWriter r1 = r7.cfw
            r1.addALoad(r0)
            org.mozilla.classfile.ClassFileWriter r1 = r7.cfw
            r2 = 192(0xc0, float:2.69E-43)
            java.lang.String r5 = "java/lang/Integer"
            r1.add((int) r2, (java.lang.String) r5)
            r7.generateIntegerUnwrap()
            java.util.Map<org.mozilla.javascript.Node, org.mozilla.javascript.optimizer.BodyCodegen$FinallyReturnPoint> r1 = r7.finallys
            java.lang.Object r8 = r1.get(r8)
            org.mozilla.javascript.optimizer.BodyCodegen$FinallyReturnPoint r8 = (org.mozilla.javascript.optimizer.BodyCodegen.FinallyReturnPoint) r8
            org.mozilla.classfile.ClassFileWriter r1 = r7.cfw
            int r1 = r1.acquireLabel()
            r8.tableLabel = r1
            org.mozilla.classfile.ClassFileWriter r1 = r7.cfw
            int r8 = r8.tableLabel
            r1.add((int) r3, (int) r8)
            short r8 = (short) r0
            r7.releaseWordLocal(r8)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r8.markLabel(r4)
            goto L_0x02f8
        L_0x0231:
            org.mozilla.javascript.CompilerEnvirons r8 = r7.compilerEnv
            boolean r8 = r8.isGenerateObserverCount()
            if (r8 == 0) goto L_0x023c
            r7.addInstructionCount(r5)
        L_0x023c:
            if (r1 == 0) goto L_0x02f8
            r7.generateStatement(r1)
            org.mozilla.javascript.Node r1 = r1.getNext()
            goto L_0x023c
        L_0x0246:
            org.mozilla.javascript.CompilerEnvirons r0 = r7.compilerEnv
            boolean r0 = r0.isGenerateObserverCount()
            if (r0 == 0) goto L_0x0251
            r7.addInstructionCount()
        L_0x0251:
            org.mozilla.javascript.ast.Jump r8 = (org.mozilla.javascript.ast.Jump) r8
            r7.visitSwitch(r8, r1)
            goto L_0x02f8
        L_0x0258:
            int r8 = r8.getExistingIntProp(r5)
            org.mozilla.javascript.ast.ScriptNode r0 = r7.scriptOrFn
            org.mozilla.javascript.optimizer.OptFunctionNode r8 = org.mozilla.javascript.optimizer.OptFunctionNode.get(r0, r8)
            org.mozilla.javascript.ast.FunctionNode r0 = r8.fnode
            int r0 = r0.getFunctionType()
            r1 = 3
            if (r0 != r1) goto L_0x0270
            r7.visitFunction(r8, r0)
            goto L_0x02f8
        L_0x0270:
            if (r0 != r5) goto L_0x0274
            goto L_0x02f8
        L_0x0274:
            java.lang.RuntimeException r8 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r8
        L_0x0279:
            org.mozilla.javascript.ast.Jump r8 = (org.mozilla.javascript.ast.Jump) r8
            r7.visitTryCatchFinally(r8, r1)
            goto L_0x02f8
        L_0x0280:
            boolean r2 = r7.isGenerator
            if (r2 != 0) goto L_0x02a2
            if (r1 == 0) goto L_0x028a
            r7.generateExpression(r1, r8)
            goto L_0x02a2
        L_0x028a:
            r8 = 4
            if (r0 != r8) goto L_0x0293
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            org.mozilla.javascript.optimizer.Codegen.pushUndefined(r8)
            goto L_0x02a2
        L_0x0293:
            short r8 = r7.popvLocal
            if (r8 < 0) goto L_0x029d
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            r0.addALoad(r8)
            goto L_0x02a2
        L_0x029d:
            java.lang.RuntimeException r8 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r8
        L_0x02a2:
            org.mozilla.javascript.CompilerEnvirons r8 = r7.compilerEnv
            boolean r8 = r8.isGenerateObserverCount()
            if (r8 == 0) goto L_0x02ad
            r7.addInstructionCount()
        L_0x02ad:
            int r8 = r7.epilogueLabel
            if (r8 != r4) goto L_0x02c3
            boolean r8 = r7.hasVarsInRegs
            if (r8 == 0) goto L_0x02be
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            int r8 = r8.acquireLabel()
            r7.epilogueLabel = r8
            goto L_0x02c3
        L_0x02be:
            java.lang.RuntimeException r8 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r8
        L_0x02c3:
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            int r0 = r7.epilogueLabel
            r8.add((int) r3, (int) r0)
            goto L_0x02f8
        L_0x02cb:
            org.mozilla.javascript.CompilerEnvirons r0 = r7.compilerEnv
            boolean r0 = r0.isGenerateObserverCount()
            if (r0 == 0) goto L_0x02d6
            r7.addInstructionCount()
        L_0x02d6:
            org.mozilla.classfile.ClassFileWriter r0 = r7.cfw
            int r8 = r7.getLocalBlockRegister(r8)
            r0.addALoad(r8)
            org.mozilla.classfile.ClassFileWriter r8 = r7.cfw
            r0 = 191(0xbf, float:2.68E-43)
            r8.add(r0)
            goto L_0x02f8
        L_0x02e7:
            r7.generateExpression(r1, r8)
            org.mozilla.javascript.CompilerEnvirons r8 = r7.compilerEnv
            boolean r8 = r8.isGenerateObserverCount()
            if (r8 == 0) goto L_0x02f5
            r7.addInstructionCount()
        L_0x02f5:
            r7.generateThrowJavaScriptException()
        L_0x02f8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.optimizer.BodyCodegen.generateStatement(org.mozilla.javascript.Node):void");
    }

    private void generateIntegerWrap() {
        this.cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }

    private void generateIntegerUnwrap() {
        this.cfw.addInvoke(182, "java/lang/Integer", "intValue", "()I");
    }

    private void generateThrowJavaScriptException() {
        this.cfw.add(187, "org/mozilla/javascript/JavaScriptException");
        this.cfw.add(90);
        this.cfw.add(95);
        this.cfw.addPush(this.scriptOrFn.getSourceName());
        this.cfw.addPush(this.itsLineNumber);
        this.cfw.addInvoke(183, "org/mozilla/javascript/JavaScriptException", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/Object;Ljava/lang/String;I)V");
        this.cfw.add(191);
    }

    private int getNextGeneratorState(Node node) {
        return ((FunctionNode) this.scriptOrFn).getResumptionPoints().indexOf(node) + 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:136:0x045a, code lost:
        r2 = r1.cfw.acquireLabel();
        r3 = r1.cfw.acquireLabel();
        visitIfJumpRelOp(r0, r4, r2, r3);
        addJumpedBooleanWrap(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01f9, code lost:
        if (r4 == null) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01fb, code lost:
        generateExpression(r4, r0);
        r4 = r4.getNext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0203, code lost:
        r1.cfw.addALoad(r1.contextLocal);
        r1.cfw.addALoad(r1.variableObjectLocal);
        r1.cfw.addPush(r18.getString());
        addScriptRuntimeInvoke("bind", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x02c4, code lost:
        visitSetElem(r3, r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02fc, code lost:
        visitSetProp(r3, r0, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateExpression(org.mozilla.javascript.Node r18, org.mozilla.javascript.Node r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            int r3 = r18.getType()
            org.mozilla.javascript.Node r4 = r18.getFirstChild()
            r6 = 89
            if (r3 == r6) goto L_0x057e
            r7 = 102(0x66, float:1.43E-43)
            java.lang.String r9 = "(Ljava/lang/Object;)Z"
            java.lang.String r10 = "toBoolean"
            if (r3 == r7) goto L_0x053f
            r7 = 109(0x6d, float:1.53E-43)
            r13 = 1
            if (r3 == r7) goto L_0x0517
            r7 = 126(0x7e, float:1.77E-43)
            if (r3 == r7) goto L_0x0506
            java.lang.String r7 = "refGet"
            r14 = 142(0x8e, float:1.99E-43)
            java.lang.String r15 = "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;"
            if (r3 == r14) goto L_0x04d9
            r14 = 146(0x92, float:2.05E-43)
            if (r3 == r14) goto L_0x04d4
            r14 = 159(0x9f, float:2.23E-43)
            if (r3 == r14) goto L_0x04bd
            r14 = 149(0x95, float:2.09E-43)
            if (r3 == r14) goto L_0x0494
            r14 = 150(0x96, float:2.1E-43)
            if (r3 == r14) goto L_0x048c
            java.lang.String r14 = "TRUE"
            java.lang.String r11 = "FALSE"
            java.lang.String r12 = "Ljava/lang/Boolean;"
            java.lang.String r5 = "java/lang/Boolean"
            r8 = 0
            switch(r3) {
                case 8: goto L_0x0487;
                case 9: goto L_0x0482;
                case 10: goto L_0x0482;
                case 11: goto L_0x0482;
                case 12: goto L_0x046e;
                case 13: goto L_0x046e;
                case 14: goto L_0x045a;
                case 15: goto L_0x045a;
                case 16: goto L_0x045a;
                case 17: goto L_0x045a;
                case 18: goto L_0x0482;
                case 19: goto L_0x0482;
                case 20: goto L_0x0482;
                case 21: goto L_0x03f9;
                case 22: goto L_0x03f2;
                case 23: goto L_0x03eb;
                case 24: goto L_0x03dd;
                case 25: goto L_0x03dd;
                case 26: goto L_0x039e;
                case 27: goto L_0x037b;
                case 28: goto L_0x0365;
                case 29: goto L_0x0365;
                case 30: goto L_0x033b;
                case 31: goto L_0x0312;
                case 32: goto L_0x0306;
                case 33: goto L_0x0301;
                case 34: goto L_0x0301;
                case 35: goto L_0x02fc;
                case 36: goto L_0x02c9;
                case 37: goto L_0x02c4;
                case 38: goto L_0x033b;
                case 39: goto L_0x02a4;
                case 40: goto L_0x0287;
                case 41: goto L_0x027c;
                case 42: goto L_0x0275;
                case 43: goto L_0x026c;
                case 44: goto L_0x0263;
                case 45: goto L_0x025a;
                case 46: goto L_0x046e;
                case 47: goto L_0x046e;
                case 48: goto L_0x0223;
                case 49: goto L_0x01f9;
                default: goto L_0x0047;
            }
        L_0x0047:
            switch(r3) {
                case 52: goto L_0x045a;
                case 53: goto L_0x045a;
                case 54: goto L_0x01ee;
                case 55: goto L_0x01e9;
                case 56: goto L_0x01e4;
                default: goto L_0x004a;
            }
        L_0x004a:
            java.lang.String r2 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;"
            switch(r3) {
                case 61: goto L_0x01c0;
                case 62: goto L_0x01c0;
                case 63: goto L_0x01b7;
                default: goto L_0x004f;
            }
        L_0x004f:
            java.lang.String r5 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;"
            switch(r3) {
                case 65: goto L_0x01b2;
                case 66: goto L_0x01ad;
                case 67: goto L_0x019e;
                case 68: goto L_0x04d9;
                case 69: goto L_0x018d;
                case 70: goto L_0x0173;
                case 71: goto L_0x0153;
                case 72: goto L_0x014e;
                case 73: goto L_0x0149;
                case 74: goto L_0x0138;
                case 75: goto L_0x0127;
                case 76: goto L_0x0116;
                case 77: goto L_0x00cd;
                case 78: goto L_0x00cd;
                case 79: goto L_0x00cd;
                case 80: goto L_0x00cd;
                default: goto L_0x0054;
            }
        L_0x0054:
            switch(r3) {
                case 104: goto L_0x0094;
                case 105: goto L_0x0094;
                case 106: goto L_0x008f;
                case 107: goto L_0x008f;
                default: goto L_0x0057;
            }
        L_0x0057:
            switch(r3) {
                case 137: goto L_0x008a;
                case 138: goto L_0x059b;
                case 139: goto L_0x02fc;
                case 140: goto L_0x02c4;
                default: goto L_0x005a;
            }
        L_0x005a:
            switch(r3) {
                case 155: goto L_0x0085;
                case 156: goto L_0x0080;
                case 157: goto L_0x0074;
                default: goto L_0x005d;
            }
        L_0x005d:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Unexpected node type "
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0074:
            org.mozilla.javascript.Node r2 = r4.getNext()
            r1.generateStatement(r4)
            r1.generateExpression(r2, r0)
            goto L_0x059b
        L_0x0080:
            r1.visitSetConstVar(r0, r4, r13)
            goto L_0x059b
        L_0x0085:
            r1.visitSetConst(r0, r4)
            goto L_0x059b
        L_0x008a:
            r17.visitTypeofname(r18)
            goto L_0x059b
        L_0x008f:
            r17.visitIncDec(r18)
            goto L_0x059b
        L_0x0094:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            r2.add(r6)
            r1.addScriptRuntimeInvoke(r10, r9)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r2 = r2.acquireLabel()
            r5 = 105(0x69, float:1.47E-43)
            if (r3 != r5) goto L_0x00b1
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            r5 = 153(0x99, float:2.14E-43)
            r3.add((int) r5, (int) r2)
            goto L_0x00b8
        L_0x00b1:
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            r5 = 154(0x9a, float:2.16E-43)
            r3.add((int) r5, (int) r2)
        L_0x00b8:
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            r5 = 87
            r3.add(r5)
            org.mozilla.javascript.Node r3 = r4.getNext()
            r1.generateExpression(r3, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.markLabel(r2)
            goto L_0x059b
        L_0x00cd:
            r2 = 16
            int r2 = r0.getIntProp(r2, r8)
        L_0x00d3:
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r4 = r4.getNext()
            if (r4 != 0) goto L_0x00d3
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r4 = r1.contextLocal
            r0.addALoad(r4)
            switch(r3) {
                case 77: goto L_0x0108;
                case 78: goto L_0x0103;
                case 79: goto L_0x00f7;
                case 80: goto L_0x00eb;
                default: goto L_0x00e6;
            }
        L_0x00e6:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x00eb:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r3 = r1.variableObjectLocal
            r0.addALoad(r3)
            java.lang.String r0 = "nameRef"
            java.lang.String r3 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;"
            goto L_0x010c
        L_0x00f7:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r3 = r1.variableObjectLocal
            r0.addALoad(r3)
            java.lang.String r0 = "nameRef"
            java.lang.String r3 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;"
            goto L_0x010c
        L_0x0103:
            java.lang.String r0 = "memberRef"
            java.lang.String r3 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;"
            goto L_0x010c
        L_0x0108:
            java.lang.String r0 = "memberRef"
            java.lang.String r3 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;"
        L_0x010c:
            org.mozilla.classfile.ClassFileWriter r4 = r1.cfw
            r4.addPush((int) r2)
            r1.addScriptRuntimeInvoke(r0, r3)
            goto L_0x059b
        L_0x0116:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "escapeTextValue"
            r1.addScriptRuntimeInvoke(r0, r5)
            goto L_0x059b
        L_0x0127:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "escapeAttributeValue"
            r1.addScriptRuntimeInvoke(r0, r5)
            goto L_0x059b
        L_0x0138:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r3 = r1.contextLocal
            r0.addALoad(r3)
            java.lang.String r0 = "setDefaultNamespace"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x0149:
            r1.visitStrictSetName(r0, r4)
            goto L_0x059b
        L_0x014e:
            r1.generateYieldPoint(r0, r13)
            goto L_0x059b
        L_0x0153:
            r2 = 17
            java.lang.Object r2 = r0.getProp(r2)
            java.lang.String r2 = (java.lang.String) r2
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.addPush((java.lang.String) r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "specialRef"
            java.lang.String r2 = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x0173:
            r1.generateFunctionAndThisObj(r4, r0)
            org.mozilla.javascript.Node r2 = r4.getNext()
            r1.generateCallArgArray(r0, r2, r8)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "callRef"
            java.lang.String r2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x018d:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "refDel"
            r1.addScriptRuntimeInvoke(r0, r15)
            goto L_0x059b
        L_0x019e:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            r1.addScriptRuntimeInvoke(r7, r15)
            goto L_0x059b
        L_0x01ad:
            r1.visitObjectLiteral(r0, r4, r8)
            goto L_0x059b
        L_0x01b2:
            r1.visitArrayLiteral(r0, r4, r8)
            goto L_0x059b
        L_0x01b7:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 42
            r0.add(r2)
            goto L_0x059b
        L_0x01c0:
            int r0 = r17.getLocalBlockRegister(r18)
            org.mozilla.classfile.ClassFileWriter r4 = r1.cfw
            r4.addALoad(r0)
            r0 = 61
            if (r3 != r0) goto L_0x01d6
            java.lang.String r0 = "enumNext"
            java.lang.String r2 = "(Ljava/lang/Object;)Ljava/lang/Boolean;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x01d6:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r3 = r1.contextLocal
            r0.addALoad(r3)
            java.lang.String r0 = "enumId"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x01e4:
            r1.visitSetVar(r0, r4, r13)
            goto L_0x059b
        L_0x01e9:
            r17.visitGetVar(r18)
            goto L_0x059b
        L_0x01ee:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r0 = r17.getLocalBlockRegister(r18)
            r2.addALoad(r0)
            goto L_0x059b
        L_0x01f9:
            if (r4 == 0) goto L_0x0203
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r4 = r4.getNext()
            goto L_0x01f9
        L_0x0203:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.contextLocal
            r2.addALoad(r3)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.variableObjectLocal
            r2.addALoad(r3)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            java.lang.String r0 = r18.getString()
            r2.addPush((java.lang.String) r0)
            java.lang.String r0 = "bind"
            java.lang.String r2 = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x0223:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.contextLocal
            r2.addALoad(r3)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.variableObjectLocal
            r2.addALoad(r3)
            r2 = 4
            int r0 = r0.getExistingIntProp(r2)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            org.mozilla.javascript.optimizer.Codegen r3 = r1.codegen
            java.lang.String r3 = r3.mainClassName
            org.mozilla.javascript.optimizer.Codegen r4 = r1.codegen
            org.mozilla.javascript.ast.ScriptNode r5 = r1.scriptOrFn
            java.lang.String r0 = r4.getCompiledRegexpName(r5, r0)
            java.lang.String r4 = "Ljava/lang/Object;"
            r6 = 178(0xb2, float:2.5E-43)
            r2.add(r6, r3, r0, r4)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 184(0xb8, float:2.58E-43)
            java.lang.String r3 = "org/mozilla/javascript/ScriptRuntime"
            java.lang.String r4 = "wrapRegExp"
            java.lang.String r5 = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;"
            r0.addInvoke(r2, r3, r4, r5)
            goto L_0x059b
        L_0x025a:
            r6 = 178(0xb2, float:2.5E-43)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.add(r6, r5, r14, r12)
            goto L_0x059b
        L_0x0263:
            r6 = 178(0xb2, float:2.5E-43)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.add(r6, r5, r11, r12)
            goto L_0x059b
        L_0x026c:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.thisObjLocal
            r0.addALoad(r2)
            goto L_0x059b
        L_0x0275:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.add(r13)
            goto L_0x059b
        L_0x027c:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            java.lang.String r0 = r18.getString()
            r2.addPush((java.lang.String) r0)
            goto L_0x059b
        L_0x0287:
            double r2 = r18.getDouble()
            r4 = 8
            r5 = -1
            int r0 = r0.getIntProp(r4, r5)
            if (r0 == r5) goto L_0x029b
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.addPush((double) r2)
            goto L_0x059b
        L_0x029b:
            org.mozilla.javascript.optimizer.Codegen r0 = r1.codegen
            org.mozilla.classfile.ClassFileWriter r4 = r1.cfw
            r0.pushNumberAsObject(r4, r2)
            goto L_0x059b
        L_0x02a4:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.contextLocal
            r2.addALoad(r3)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.variableObjectLocal
            r2.addALoad(r3)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            java.lang.String r0 = r18.getString()
            r2.addPush((java.lang.String) r0)
            java.lang.String r0 = "name"
            java.lang.String r2 = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x02c4:
            r1.visitSetElem(r3, r0, r4)
            goto L_0x059b
        L_0x02c9:
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r2 = r4.getNext()
            r1.generateExpression(r2, r0)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            short r3 = r1.contextLocal
            r2.addALoad(r3)
            r2 = 8
            r3 = -1
            int r0 = r0.getIntProp(r2, r3)
            if (r0 == r3) goto L_0x02ec
            java.lang.String r0 = "getObjectIndex"
            java.lang.String r2 = "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x02ec:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.variableObjectLocal
            r0.addALoad(r2)
            java.lang.String r0 = "getObjectElem"
            java.lang.String r2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x02fc:
            r1.visitSetProp(r3, r0, r4)
            goto L_0x059b
        L_0x0301:
            r1.visitGetProp(r0, r4)
            goto L_0x059b
        L_0x0306:
            r1.generateExpression(r4, r0)
            java.lang.String r0 = "typeof"
            java.lang.String r2 = "(Ljava/lang/Object;)Ljava/lang/String;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x0312:
            int r2 = r4.getType()
            r3 = 49
            if (r2 != r3) goto L_0x031b
            goto L_0x031c
        L_0x031b:
            r13 = 0
        L_0x031c:
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r2 = r4.getNext()
            r1.generateExpression(r2, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.addPush((boolean) r13)
            java.lang.String r0 = "delete"
            java.lang.String r2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Z)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x033b:
            r2 = 10
            int r2 = r0.getIntProp(r2, r8)
            if (r2 != 0) goto L_0x0360
            r2 = 9
            java.lang.Object r2 = r0.getProp(r2)
            org.mozilla.javascript.optimizer.OptFunctionNode r2 = (org.mozilla.javascript.optimizer.OptFunctionNode) r2
            if (r2 == 0) goto L_0x0352
            r1.visitOptimizedCall(r0, r2, r3, r4)
            goto L_0x059b
        L_0x0352:
            r2 = 38
            if (r3 != r2) goto L_0x035b
            r1.visitStandardCall(r0, r4)
            goto L_0x059b
        L_0x035b:
            r1.visitStandardNew(r0, r4)
            goto L_0x059b
        L_0x0360:
            r1.visitSpecialCall(r0, r3, r2, r4)
            goto L_0x059b
        L_0x0365:
            r1.generateExpression(r4, r0)
            r17.addObjectToDouble()
            r0 = 29
            if (r3 != r0) goto L_0x0376
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 119(0x77, float:1.67E-43)
            r0.add(r2)
        L_0x0376:
            r17.addDoubleWrap()
            goto L_0x059b
        L_0x037b:
            r1.generateExpression(r4, r0)
            java.lang.String r0 = "toInt32"
            java.lang.String r2 = "(Ljava/lang/Object;)I"
            r1.addScriptRuntimeInvoke(r0, r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = -1
            r0.addPush((int) r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 130(0x82, float:1.82E-43)
            r0.add(r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 135(0x87, float:1.89E-43)
            r0.add(r2)
            r17.addDoubleWrap()
            goto L_0x059b
        L_0x039e:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            int r3 = r3.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r6 = r1.cfw
            int r6 = r6.acquireLabel()
            r1.generateIfJump(r4, r0, r2, r3)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.markLabel(r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 178(0xb2, float:2.5E-43)
            r0.add(r2, r5, r11, r12)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r4 = 167(0xa7, float:2.34E-43)
            r0.add((int) r4, (int) r6)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.markLabel(r3)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.add(r2, r5, r14, r12)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.markLabel(r6)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = -1
            r0.adjustStackTop(r2)
            goto L_0x059b
        L_0x03dd:
            r5 = 24
            if (r3 != r5) goto L_0x03e4
            r3 = 111(0x6f, float:1.56E-43)
            goto L_0x03e6
        L_0x03e4:
            r3 = 115(0x73, float:1.61E-43)
        L_0x03e6:
            r1.visitArithmetic(r0, r3, r4, r2)
            goto L_0x059b
        L_0x03eb:
            r3 = 107(0x6b, float:1.5E-43)
            r1.visitArithmetic(r0, r3, r4, r2)
            goto L_0x059b
        L_0x03f2:
            r3 = 103(0x67, float:1.44E-43)
            r1.visitArithmetic(r0, r3, r4, r2)
            goto L_0x059b
        L_0x03f9:
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r2 = r4.getNext()
            r1.generateExpression(r2, r0)
            r2 = 8
            r3 = -1
            int r0 = r0.getIntProp(r2, r3)
            if (r0 == 0) goto L_0x0451
            java.lang.String r2 = "add"
            if (r0 == r13) goto L_0x044a
            r3 = 2
            if (r0 == r3) goto L_0x0443
            int r0 = r4.getType()
            r3 = 41
            if (r0 != r3) goto L_0x0422
            java.lang.String r0 = "(Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;"
            r1.addScriptRuntimeInvoke(r2, r0)
            goto L_0x059b
        L_0x0422:
            org.mozilla.javascript.Node r0 = r4.getNext()
            int r0 = r0.getType()
            r3 = 41
            if (r0 != r3) goto L_0x0435
            java.lang.String r0 = "(Ljava/lang/Object;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;"
            r1.addScriptRuntimeInvoke(r2, r0)
            goto L_0x059b
        L_0x0435:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r3 = r1.contextLocal
            r0.addALoad(r3)
            java.lang.String r0 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r2, r0)
            goto L_0x059b
        L_0x0443:
            java.lang.String r0 = "(Ljava/lang/Object;D)Ljava/lang/Object;"
            r1.addOptRuntimeInvoke(r2, r0)
            goto L_0x059b
        L_0x044a:
            java.lang.String r0 = "(DLjava/lang/Object;)Ljava/lang/Object;"
            r1.addOptRuntimeInvoke(r2, r0)
            goto L_0x059b
        L_0x0451:
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 99
            r0.add(r2)
            goto L_0x059b
        L_0x045a:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            int r3 = r3.acquireLabel()
            r1.visitIfJumpRelOp(r0, r4, r2, r3)
            r1.addJumpedBooleanWrap(r2, r3)
            goto L_0x059b
        L_0x046e:
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            int r3 = r3.acquireLabel()
            r1.visitIfJumpEqOp(r0, r4, r2, r3)
            r1.addJumpedBooleanWrap(r2, r3)
            goto L_0x059b
        L_0x0482:
            r1.visitBitOp(r0, r3, r4)
            goto L_0x059b
        L_0x0487:
            r1.visitSetName(r0, r4)
            goto L_0x059b
        L_0x048c:
            r1.generateExpression(r4, r0)
            r17.addObjectToDouble()
            goto L_0x059b
        L_0x0494:
            int r2 = r4.getType()
            r3 = 40
            if (r2 != r3) goto L_0x04a4
            r2 = 8
            r5 = -1
            int r3 = r4.getIntProp(r2, r5)
            goto L_0x04a8
        L_0x04a4:
            r2 = 8
            r5 = -1
            r3 = -1
        L_0x04a8:
            if (r3 == r5) goto L_0x04b5
            r4.removeProp(r2)
            r1.generateExpression(r4, r0)
            r4.putIntProp(r2, r3)
            goto L_0x059b
        L_0x04b5:
            r1.generateExpression(r4, r0)
            r17.addDoubleWrap()
            goto L_0x059b
        L_0x04bd:
            org.mozilla.javascript.Node r0 = r4.getNext()
            org.mozilla.javascript.Node r2 = r0.getNext()
            r1.generateStatement(r4)
            org.mozilla.javascript.Node r3 = r0.getFirstChild()
            r1.generateExpression(r3, r0)
            r1.generateStatement(r2)
            goto L_0x059b
        L_0x04d4:
            r1.visitDotQuery(r0, r4)
            goto L_0x059b
        L_0x04d9:
            r1.generateExpression(r4, r0)
            org.mozilla.javascript.Node r2 = r4.getNext()
            r4 = 142(0x8e, float:1.99E-43)
            if (r3 != r4) goto L_0x04f3
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            r3.add(r6)
            org.mozilla.classfile.ClassFileWriter r3 = r1.cfw
            short r4 = r1.contextLocal
            r3.addALoad(r4)
            r1.addScriptRuntimeInvoke(r7, r15)
        L_0x04f3:
            r1.generateExpression(r2, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            short r2 = r1.contextLocal
            r0.addALoad(r2)
            java.lang.String r0 = "refSet"
            java.lang.String r2 = "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;"
            r1.addScriptRuntimeInvoke(r0, r2)
            goto L_0x059b
        L_0x0506:
            r1.generateExpression(r4, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r2 = 87
            r0.add(r2)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            org.mozilla.javascript.optimizer.Codegen.pushUndefined(r0)
            goto L_0x059b
        L_0x0517:
            org.mozilla.javascript.optimizer.OptFunctionNode r3 = r1.fnCurrent
            if (r3 != 0) goto L_0x0523
            int r2 = r19.getType()
            r3 = 136(0x88, float:1.9E-43)
            if (r2 == r3) goto L_0x059b
        L_0x0523:
            int r0 = r0.getExistingIntProp(r13)
            org.mozilla.javascript.ast.ScriptNode r2 = r1.scriptOrFn
            org.mozilla.javascript.optimizer.OptFunctionNode r0 = org.mozilla.javascript.optimizer.OptFunctionNode.get(r2, r0)
            org.mozilla.javascript.ast.FunctionNode r2 = r0.fnode
            int r2 = r2.getFunctionType()
            r3 = 2
            if (r2 != r3) goto L_0x053a
            r1.visitFunction(r0, r2)
            goto L_0x059b
        L_0x053a:
            java.lang.RuntimeException r0 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r0
        L_0x053f:
            org.mozilla.javascript.Node r2 = r4.getNext()
            org.mozilla.javascript.Node r3 = r2.getNext()
            r1.generateExpression(r4, r0)
            r1.addScriptRuntimeInvoke(r10, r9)
            org.mozilla.classfile.ClassFileWriter r4 = r1.cfw
            int r4 = r4.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r5 = r1.cfw
            r6 = 153(0x99, float:2.14E-43)
            r5.add((int) r6, (int) r4)
            org.mozilla.classfile.ClassFileWriter r5 = r1.cfw
            short r5 = r5.getStackTop()
            r1.generateExpression(r2, r0)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r6 = r1.cfw
            r7 = 167(0xa7, float:2.34E-43)
            r6.add((int) r7, (int) r2)
            org.mozilla.classfile.ClassFileWriter r6 = r1.cfw
            r6.markLabel(r4, r5)
            r1.generateExpression(r3, r0)
            org.mozilla.classfile.ClassFileWriter r0 = r1.cfw
            r0.markLabel(r2)
            goto L_0x059b
        L_0x057e:
            org.mozilla.javascript.Node r2 = r4.getNext()
        L_0x0582:
            r16 = r4
            r4 = r2
            r2 = r16
            if (r4 == 0) goto L_0x0598
            r1.generateExpression(r2, r0)
            org.mozilla.classfile.ClassFileWriter r2 = r1.cfw
            r3 = 87
            r2.add(r3)
            org.mozilla.javascript.Node r2 = r4.getNext()
            goto L_0x0582
        L_0x0598:
            r1.generateExpression(r2, r0)     // Catch:{ all -> 0x059c }
        L_0x059b:
            return
        L_0x059c:
            r0 = move-exception
            r2 = r0
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.optimizer.BodyCodegen.generateExpression(org.mozilla.javascript.Node, org.mozilla.javascript.Node):void");
    }

    private void generateYieldPoint(Node node, boolean z) {
        short stackTop = this.cfw.getStackTop();
        int i = this.maxStack;
        if (i <= stackTop) {
            i = stackTop;
        }
        this.maxStack = i;
        if (this.cfw.getStackTop() != 0) {
            generateGetGeneratorStackState();
            for (int i2 = 0; i2 < stackTop; i2++) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addLoadConstant(i2);
                this.cfw.add(95);
                this.cfw.add(83);
            }
            this.cfw.add(87);
        }
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            generateExpression(firstChild, node);
        } else {
            Codegen.pushUndefined(this.cfw);
        }
        int nextGeneratorState = getNextGeneratorState(node);
        generateSetGeneratorResumptionPoint(nextGeneratorState);
        boolean generateSaveLocals = generateSaveLocals(node);
        this.cfw.add(176);
        generateCheckForThrowOrClose(getTargetLabel(node), generateSaveLocals, nextGeneratorState);
        if (stackTop != 0) {
            generateGetGeneratorStackState();
            for (int i3 = 0; i3 < stackTop; i3++) {
                this.cfw.add(89);
                this.cfw.addLoadConstant((stackTop - i3) - 1);
                this.cfw.add(50);
                this.cfw.add(95);
            }
            this.cfw.add(87);
        }
        if (z) {
            this.cfw.addALoad(this.argsLocal);
        }
    }

    private void generateCheckForThrowOrClose(int i, boolean z, int i2) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.addALoad(this.argsLocal);
        generateThrowJavaScriptException();
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.add(192, "java/lang/Throwable");
        this.cfw.add(191);
        if (i != -1) {
            this.cfw.markLabel(i);
        }
        if (!z) {
            this.cfw.markTableSwitchCase(this.generatorSwitch, i2);
        }
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(2);
        this.cfw.add(159, acquireLabel2);
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(1);
        this.cfw.add(159, acquireLabel);
    }

    private void generateIfJump(Node node, Node node2, int i, int i2) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type != 26) {
            if (!(type == 46 || type == 47)) {
                if (!(type == 52 || type == 53)) {
                    if (type == 104 || type == 105) {
                        int acquireLabel = this.cfw.acquireLabel();
                        if (type == 105) {
                            generateIfJump(firstChild, node, acquireLabel, i2);
                        } else {
                            generateIfJump(firstChild, node, i, acquireLabel);
                        }
                        this.cfw.markLabel(acquireLabel);
                        generateIfJump(firstChild.getNext(), node, i, i2);
                        return;
                    }
                    switch (type) {
                        case 12:
                        case 13:
                            break;
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                            break;
                        default:
                            generateExpression(node, node2);
                            addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                            this.cfw.add(154, i);
                            this.cfw.add(167, i2);
                            return;
                    }
                }
                visitIfJumpRelOp(node, firstChild, i, i2);
                return;
            }
            visitIfJumpEqOp(node, firstChild, i, i2);
            return;
        }
        generateIfJump(firstChild, node, i2, i);
    }

    private void visitFunction(OptFunctionNode optFunctionNode, int i) {
        int index = this.codegen.getIndex(optFunctionNode.fnode);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(index);
        this.cfw.addInvoke(183, this.codegen.mainClassName, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        if (i != 2) {
            this.cfw.addPush(i);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.contextLocal);
            addOptRuntimeInvoke("initFunction", "(Lorg/mozilla/javascript/NativeFunction;ILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)V");
        }
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId != -1) {
            return labelId;
        }
        int acquireLabel = this.cfw.acquireLabel();
        node.labelId(acquireLabel);
        return acquireLabel;
    }

    private void visitGoto(Jump jump, int i, Node node) {
        Node node2 = jump.target;
        if (i == 6 || i == 7) {
            if (node != null) {
                int targetLabel = getTargetLabel(node2);
                int acquireLabel = this.cfw.acquireLabel();
                if (i == 6) {
                    generateIfJump(node, jump, targetLabel, acquireLabel);
                } else {
                    generateIfJump(node, jump, acquireLabel, targetLabel);
                }
                this.cfw.markLabel(acquireLabel);
                return;
            }
            throw Codegen.badTree();
        } else if (i != 135) {
            addGoto(node2, 167);
        } else if (this.isGenerator) {
            addGotoWithReturn(node2);
        } else {
            inlineFinally(node2);
        }
    }

    private void addGotoWithReturn(Node node) {
        FinallyReturnPoint finallyReturnPoint = this.finallys.get(node);
        this.cfw.addLoadConstant(finallyReturnPoint.jsrPoints.size());
        addGoto(node, 167);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        finallyReturnPoint.jsrPoints.add(Integer.valueOf(acquireLabel));
    }

    private void generateArrayLiteralFactory(Node node, int i) {
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", 2);
        visitArrayLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateObjectLiteralFactory(Node node, int i) {
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", 2);
        visitObjectLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void visitArrayLiteral(Node node, Node node2, boolean z) {
        int i = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i++;
        }
        if (z || ((i <= 10 && this.cfw.getCurrentCodeOffset() <= 30000) || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            addNewObjectArray(i);
            for (int i2 = 0; i2 != i; i2++) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
                generateExpression(node2, node);
                this.cfw.add(83);
                node2 = node2.getNext();
            }
            int[] iArr = (int[]) node.getProp(11);
            if (iArr == null) {
                this.cfw.add(1);
                this.cfw.add(3);
            } else {
                this.cfw.addPush(OptRuntime.encodeIntArray(iArr));
                this.cfw.addPush(iArr.length);
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke("newArrayLiteral", "([Ljava/lang/Object;Ljava/lang/String;ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.literals == null) {
            this.literals = new LinkedList();
        }
        this.literals.add(node);
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addInvoke(182, this.codegen.mainClassName, this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size(), "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitObjectLiteral(Node node, Node node2, boolean z) {
        boolean z2;
        Object[] objArr = (Object[]) node.getProp(12);
        int length = objArr.length;
        if (z || ((length <= 10 && this.cfw.getCurrentCodeOffset() <= 30000) || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            addNewObjectArray(length);
            for (int i = 0; i != length; i++) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                Object obj = objArr[i];
                if (obj instanceof String) {
                    this.cfw.addPush((String) obj);
                } else {
                    this.cfw.addPush(((Integer) obj).intValue());
                    addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
                }
                this.cfw.add(83);
            }
            addNewObjectArray(length);
            Node node3 = node2;
            for (int i2 = 0; i2 != length; i2++) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
                int type = node3.getType();
                if (type == 151 || type == 152) {
                    generateExpression(node3.getFirstChild(), node);
                } else {
                    generateExpression(node3, node);
                }
                this.cfw.add(83);
                node3 = node3.getNext();
            }
            Node node4 = node2;
            int i3 = 0;
            while (true) {
                if (i3 == length) {
                    z2 = false;
                    break;
                }
                int type2 = node4.getType();
                if (type2 == 151 || type2 == 152) {
                    z2 = true;
                } else {
                    node4 = node4.getNext();
                    i3++;
                }
            }
            if (z2) {
                this.cfw.addPush(length);
                this.cfw.add(188, 10);
                for (int i4 = 0; i4 != length; i4++) {
                    this.cfw.add(89);
                    this.cfw.addPush(i4);
                    int type3 = node2.getType();
                    if (type3 == 151) {
                        this.cfw.add(2);
                    } else if (type3 == 152) {
                        this.cfw.add(4);
                    } else {
                        this.cfw.add(3);
                    }
                    this.cfw.add(79);
                    node2 = node2.getNext();
                }
            } else {
                this.cfw.add(1);
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("newObjectLiteral", "([Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.literals == null) {
            this.literals = new LinkedList();
        }
        this.literals.add(node);
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addInvoke(182, this.codegen.mainClassName, this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size(), "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitSpecialCall(Node node, int i, int i2, Node node2) {
        String str;
        String str2;
        this.cfw.addALoad(this.contextLocal);
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
        }
        generateCallArgArray(node, node2.getNext(), false);
        if (i == 30) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            str2 = "newObjectSpecial";
            str = "(Lorg/mozilla/javascript/Context;Ljava/lang/Object;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;";
        } else {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            String sourceName = this.scriptOrFn.getSourceName();
            ClassFileWriter classFileWriter = this.cfw;
            if (sourceName == null) {
                sourceName = "";
            }
            classFileWriter.addPush(sourceName);
            this.cfw.addPush(this.itsLineNumber);
            str2 = "callSpecial";
            str = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;ILjava/lang/String;I)Ljava/lang/Object;";
        }
        addOptRuntimeInvoke(str2, str);
    }

    private void visitStandardCall(Node node, Node node2) {
        String str;
        String str2;
        if (node.getType() == 38) {
            Node next = node2.getNext();
            int type = node2.getType();
            if (next == null) {
                if (type == 39) {
                    this.cfw.addPush(node2.getString());
                    str2 = "callName0";
                    str = "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                } else if (type == 33) {
                    Node firstChild = node2.getFirstChild();
                    generateExpression(firstChild, node);
                    this.cfw.addPush(firstChild.getNext().getString());
                    str2 = "callProp0";
                    str = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                } else if (type != 34) {
                    generateFunctionAndThisObj(node2, node);
                    str2 = "call0";
                    str = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                } else {
                    throw Kit.codeBug();
                }
            } else if (type == 39) {
                String string = node2.getString();
                generateCallArgArray(node, next, false);
                this.cfw.addPush(string);
                str2 = "callName";
                str = "([Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                int i = 0;
                for (Node node3 = next; node3 != null; node3 = node3.getNext()) {
                    i++;
                }
                generateFunctionAndThisObj(node2, node);
                if (i == 1) {
                    generateExpression(next, node);
                    str2 = "call1";
                    str = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                } else if (i == 2) {
                    generateExpression(next, node);
                    generateExpression(next.getNext(), node);
                    str2 = "call2";
                    str = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                } else {
                    generateCallArgArray(node, next, false);
                    str2 = "callN";
                    str = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
                }
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke(str2, str);
            return;
        }
        throw Codegen.badTree();
    }

    private void visitStandardNew(Node node, Node node2) {
        if (node.getType() == 30) {
            Node next = node2.getNext();
            generateExpression(node2, node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            generateCallArgArray(node, next, false);
            addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        throw Codegen.badTree();
    }

    private void visitOptimizedCall(Node node, OptFunctionNode optFunctionNode, int i, Node node2) {
        short s;
        Node next = node2.getNext();
        String str = this.codegen.mainClassName;
        if (i == 30) {
            generateExpression(node2, node);
            s = 0;
        } else {
            generateFunctionAndThisObj(node2, node);
            s = getNewWordLocal();
            this.cfw.addAStore(s);
        }
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(89);
        this.cfw.add(193, str);
        this.cfw.add(153, acquireLabel2);
        this.cfw.add(192, str);
        this.cfw.add(89);
        this.cfw.add(180, str, "_id", "I");
        this.cfw.addPush(this.codegen.getIndex(optFunctionNode.fnode));
        this.cfw.add(160, acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i == 30) {
            this.cfw.add(1);
        } else {
            this.cfw.addALoad(s);
        }
        for (Node node3 = next; node3 != null; node3 = node3.getNext()) {
            int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node3);
            if (nodeIsDirectCallParameter >= 0) {
                this.cfw.addALoad(nodeIsDirectCallParameter);
                this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            } else if (node3.getIntProp(8, -1) == 0) {
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                generateExpression(node3, node);
            } else {
                generateExpression(node3, node);
                this.cfw.addPush((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            }
        }
        this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        ClassFileWriter classFileWriter = this.cfw;
        String str2 = this.codegen.mainClassName;
        Codegen codegen2 = this.codegen;
        FunctionNode functionNode = optFunctionNode.fnode;
        classFileWriter.addInvoke(184, str2, i == 30 ? codegen2.getDirectCtorName(functionNode) : codegen2.getBodyMethodName(functionNode), this.codegen.getBodyMethodSignature(optFunctionNode.fnode));
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i != 30) {
            this.cfw.addALoad(s);
            releaseWordLocal(s);
        }
        generateCallArgArray(node, next, true);
        if (i == 30) {
            addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        } else {
            this.cfw.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void generateCallArgArray(Node node, Node node2, boolean z) {
        short s;
        int i = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i++;
        }
        if (i != 1 || (s = this.itsOneArgArray) < 0) {
            addNewObjectArray(i);
        } else {
            this.cfw.addALoad(s);
        }
        for (int i2 = 0; i2 != i; i2++) {
            if (!this.isGenerator) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
            }
            if (!z) {
                generateExpression(node2, node);
            } else {
                int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
                if (nodeIsDirectCallParameter >= 0) {
                    dcpLoadAsObject(nodeIsDirectCallParameter);
                } else {
                    generateExpression(node2, node);
                    if (node2.getIntProp(8, -1) == 0) {
                        addDoubleWrap();
                    }
                }
            }
            if (this.isGenerator) {
                short newWordLocal = getNewWordLocal();
                this.cfw.addAStore(newWordLocal);
                this.cfw.add(192, "[Ljava/lang/Object;");
                this.cfw.add(89);
                this.cfw.addPush(i2);
                this.cfw.addALoad(newWordLocal);
                releaseWordLocal(newWordLocal);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
        }
    }

    private void generateFunctionAndThisObj(Node node, Node node2) {
        int type = node.getType();
        int type2 = node.getType();
        if (type2 != 33) {
            if (type2 == 34) {
                throw Kit.codeBug();
            } else if (type2 != 36) {
                if (type2 != 39) {
                    generateExpression(node, node2);
                    this.cfw.addALoad(this.contextLocal);
                    addScriptRuntimeInvoke("getValueFunctionAndThis", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
                } else {
                    this.cfw.addPush(node.getString());
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    addScriptRuntimeInvoke("getNameFunctionAndThis", "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                }
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
            }
        }
        Node firstChild = node.getFirstChild();
        generateExpression(firstChild, node);
        Node next = firstChild.getNext();
        if (type == 33) {
            this.cfw.addPush(next.getString());
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getPropFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
        } else if (node.getIntProp(8, -1) == -1) {
            generateExpression(next, node);
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke("getElemFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
        } else {
            throw Codegen.badTree();
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        this.itsLineNumber = lineno;
        if (lineno != -1) {
            this.cfw.addLineNumberEntry((short) lineno);
        }
    }

    private void visitTryCatchFinally(Jump jump, Node node) {
        int i;
        int i2;
        Jump jump2 = jump;
        short newWordLocal = getNewWordLocal();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addAStore(newWordLocal);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel, 0);
        Node node2 = jump2.target;
        Node node3 = jump.getFinally();
        int[] iArr = new int[5];
        this.exceptionManager.pushExceptionInfo(jump2);
        if (node2 != null) {
            iArr[0] = this.cfw.acquireLabel();
            iArr[1] = this.cfw.acquireLabel();
            iArr[2] = this.cfw.acquireLabel();
            Context currentContext = Context.getCurrentContext();
            if (currentContext != null && currentContext.hasFeature(13)) {
                iArr[3] = this.cfw.acquireLabel();
            }
        }
        if (node3 != null) {
            iArr[4] = this.cfw.acquireLabel();
        }
        this.exceptionManager.setHandlers(iArr, acquireLabel);
        if (this.isGenerator && node3 != null) {
            FinallyReturnPoint finallyReturnPoint = new FinallyReturnPoint();
            if (this.finallys == null) {
                this.finallys = new HashMap();
            }
            this.finallys.put(node3, finallyReturnPoint);
            this.finallys.put(node3.getNext(), finallyReturnPoint);
        }
        for (Node node4 = node; node4 != null; node4 = node4.getNext()) {
            if (node4 == node2) {
                int targetLabel = getTargetLabel(node2);
                this.exceptionManager.removeHandler(0, targetLabel);
                this.exceptionManager.removeHandler(1, targetLabel);
                this.exceptionManager.removeHandler(2, targetLabel);
                this.exceptionManager.removeHandler(3, targetLabel);
            }
            generateStatement(node4);
        }
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        int localBlockRegister = getLocalBlockRegister(jump);
        if (node2 != null) {
            int labelId = node2.labelId();
            short s = newWordLocal;
            int i3 = labelId;
            i = localBlockRegister;
            i2 = acquireLabel2;
            generateCatchBlock(0, s, i3, localBlockRegister, iArr[0]);
            generateCatchBlock(1, s, i3, localBlockRegister, iArr[1]);
            generateCatchBlock(2, s, i3, localBlockRegister, iArr[2]);
            Context currentContext2 = Context.getCurrentContext();
            if (currentContext2 != null && currentContext2.hasFeature(13)) {
                generateCatchBlock(3, newWordLocal, labelId, i, iArr[3]);
            }
        } else {
            i = localBlockRegister;
            i2 = acquireLabel2;
        }
        if (node3 != null) {
            int acquireLabel3 = this.cfw.acquireLabel();
            int acquireLabel4 = this.cfw.acquireLabel();
            this.cfw.markHandler(acquireLabel3);
            if (!this.isGenerator) {
                this.cfw.markLabel(iArr[4]);
            }
            int i4 = i;
            this.cfw.addAStore(i4);
            this.cfw.addALoad(newWordLocal);
            this.cfw.addAStore(this.variableObjectLocal);
            int labelId2 = node3.labelId();
            if (this.isGenerator) {
                addGotoWithReturn(node3);
            } else {
                inlineFinally(node3, iArr[4], acquireLabel4);
            }
            this.cfw.addALoad(i4);
            if (this.isGenerator) {
                this.cfw.add(192, "java/lang/Throwable");
            }
            this.cfw.add(191);
            this.cfw.markLabel(acquireLabel4);
            if (this.isGenerator) {
                this.cfw.addExceptionHandler(acquireLabel, labelId2, acquireLabel3, (String) null);
            }
        }
        releaseWordLocal(newWordLocal);
        this.cfw.markLabel(i2);
        if (!this.isGenerator) {
            this.exceptionManager.popExceptionInfo();
        }
    }

    private void generateCatchBlock(int i, short s, int i2, int i3, int i4) {
        if (i4 == 0) {
            i4 = this.cfw.acquireLabel();
        }
        this.cfw.markHandler(i4);
        this.cfw.addAStore(i3);
        this.cfw.addALoad(s);
        this.cfw.addAStore(this.variableObjectLocal);
        exceptionTypeToName(i);
        this.cfw.add(167, i2);
    }

    /* access modifiers changed from: private */
    public String exceptionTypeToName(int i) {
        if (i == 0) {
            return "org/mozilla/javascript/JavaScriptException";
        }
        if (i == 1) {
            return "org/mozilla/javascript/EvaluatorException";
        }
        if (i == 2) {
            return "org/mozilla/javascript/EcmaError";
        }
        if (i == 3) {
            return "java/lang/Throwable";
        }
        if (i == 4) {
            return null;
        }
        throw Kit.codeBug();
    }

    /* compiled from: Codegen */
    private class ExceptionManager {
        private LinkedList<ExceptionInfo> exceptionInfo = new LinkedList<>();

        ExceptionManager() {
        }

        /* access modifiers changed from: package-private */
        public void pushExceptionInfo(Jump jump) {
            this.exceptionInfo.add(new ExceptionInfo(jump, BodyCodegen.this.getFinallyAtTarget(jump.getFinally())));
        }

        /* access modifiers changed from: package-private */
        public void addHandler(int i, int i2, int i3) {
            ExceptionInfo top = getTop();
            top.handlerLabels[i] = i2;
            top.exceptionStarts[i] = i3;
        }

        /* access modifiers changed from: package-private */
        public void setHandlers(int[] iArr, int i) {
            getTop();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    addHandler(i2, iArr[i2], i);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int removeHandler(int i, int i2) {
            ExceptionInfo top = getTop();
            if (top.handlerLabels[i] == 0) {
                return 0;
            }
            int i3 = top.handlerLabels[i];
            endCatch(top, i, i2);
            top.handlerLabels[i] = 0;
            return i3;
        }

        /* access modifiers changed from: package-private */
        public void popExceptionInfo() {
            this.exceptionInfo.removeLast();
        }

        /* access modifiers changed from: package-private */
        public void markInlineFinallyStart(Node node, int i) {
            LinkedList<ExceptionInfo> linkedList = this.exceptionInfo;
            ListIterator<ExceptionInfo> listIterator = linkedList.listIterator(linkedList.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo previous = listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (previous.handlerLabels[i2] != 0 && previous.currentFinally == null) {
                        endCatch(previous, i2, i);
                        previous.exceptionStarts[i2] = 0;
                        previous.currentFinally = node;
                    }
                }
                if (previous.finallyBlock == node) {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markInlineFinallyEnd(Node node, int i) {
            LinkedList<ExceptionInfo> linkedList = this.exceptionInfo;
            ListIterator<ExceptionInfo> listIterator = linkedList.listIterator(linkedList.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo previous = listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (previous.handlerLabels[i2] != 0 && previous.currentFinally == node) {
                        previous.exceptionStarts[i2] = i;
                        previous.currentFinally = null;
                    }
                }
                if (previous.finallyBlock == node) {
                    return;
                }
            }
        }

        private void endCatch(ExceptionInfo exceptionInfo2, int i, int i2) {
            if (exceptionInfo2.exceptionStarts[i] != 0) {
                if (BodyCodegen.this.cfw.getLabelPC(exceptionInfo2.exceptionStarts[i]) != BodyCodegen.this.cfw.getLabelPC(i2)) {
                    BodyCodegen.this.cfw.addExceptionHandler(exceptionInfo2.exceptionStarts[i], i2, exceptionInfo2.handlerLabels[i], BodyCodegen.this.exceptionTypeToName(i));
                    return;
                }
                return;
            }
            throw new IllegalStateException("bad exception start");
        }

        private ExceptionInfo getTop() {
            return this.exceptionInfo.getLast();
        }

        /* compiled from: Codegen */
        private class ExceptionInfo {
            Node currentFinally = null;
            int[] exceptionStarts = new int[5];
            Node finallyBlock;
            int[] handlerLabels = new int[5];
            Jump node;

            ExceptionInfo(Jump jump, Node node2) {
                this.node = jump;
                this.finallyBlock = node2;
            }
        }
    }

    private void inlineFinally(Node node, int i, int i2) {
        Node finallyAtTarget = getFinallyAtTarget(node);
        finallyAtTarget.resetTargets();
        this.exceptionManager.markInlineFinallyStart(finallyAtTarget, i);
        for (Node firstChild = finallyAtTarget.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            generateStatement(firstChild);
        }
        this.exceptionManager.markInlineFinallyEnd(finallyAtTarget, i2);
    }

    private void inlineFinally(Node node) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        inlineFinally(node, acquireLabel, acquireLabel2);
        this.cfw.markLabel(acquireLabel2);
    }

    /* access modifiers changed from: private */
    public Node getFinallyAtTarget(Node node) {
        Node next;
        if (node == null) {
            return null;
        }
        if (node.getType() == 125) {
            return node;
        }
        if (node != null && node.getType() == 131 && (next = node.getNext()) != null && next.getType() == 125) {
            return next;
        }
        throw Kit.codeBug("bad finally target");
    }

    private boolean generateSaveLocals(Node node) {
        int i = 0;
        for (int i2 = 0; i2 < this.firstFreeLocal; i2++) {
            if (this.locals[i2] != 0) {
                i++;
            }
        }
        if (i == 0) {
            ((FunctionNode) this.scriptOrFn).addLiveLocals(node, (int[]) null);
            return false;
        }
        int i3 = this.maxLocals;
        if (i3 <= i) {
            i3 = i;
        }
        this.maxLocals = i3;
        int[] iArr = new int[i];
        int i4 = 0;
        for (int i5 = 0; i5 < this.firstFreeLocal; i5++) {
            if (this.locals[i5] != 0) {
                iArr[i4] = i5;
                i4++;
            }
        }
        ((FunctionNode) this.scriptOrFn).addLiveLocals(node, iArr);
        generateGetGeneratorLocalsState();
        for (int i6 = 0; i6 < i; i6++) {
            this.cfw.add(89);
            this.cfw.addLoadConstant(i6);
            this.cfw.addALoad(iArr[i6]);
            this.cfw.add(83);
        }
        this.cfw.add(87);
        return true;
    }

    private void visitSwitch(Jump jump, Node node) {
        generateExpression(node, jump);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        Jump jump2 = (Jump) node.getNext();
        while (jump2 != null) {
            if (jump2.getType() == 115) {
                generateExpression(jump2.getFirstChild(), jump2);
                this.cfw.addALoad(newWordLocal);
                addScriptRuntimeInvoke("shallowEq", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
                addGoto(jump2.target, 154);
                jump2 = (Jump) jump2.getNext();
            } else {
                throw Codegen.badTree();
            }
        }
        releaseWordLocal(newWordLocal);
    }

    private void visitTypeofname(Node node) {
        int indexForNameNode;
        if (!this.hasVarsInRegs || (indexForNameNode = this.fnCurrent.fnode.getIndexForNameNode(node)) < 0) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(node.getString());
            addScriptRuntimeInvoke("typeofName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;");
        } else if (this.fnCurrent.isNumberVar(indexForNameNode)) {
            this.cfw.addPush("number");
        } else if (varIsDirectCallParameter(indexForNameNode)) {
            short s = this.varRegisters[indexForNameNode];
            this.cfw.addALoad(s);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            int acquireLabel = this.cfw.acquireLabel();
            this.cfw.add(165, acquireLabel);
            short stackTop = this.cfw.getStackTop();
            this.cfw.addALoad(s);
            addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
            int acquireLabel2 = this.cfw.acquireLabel();
            this.cfw.add(167, acquireLabel2);
            this.cfw.markLabel(acquireLabel, stackTop);
            this.cfw.addPush("number");
            this.cfw.markLabel(acquireLabel2);
        } else {
            this.cfw.addALoad(this.varRegisters[indexForNameNode]);
            addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
        }
    }

    private void saveCurrentCodeOffset() {
        this.savedCodeOffset = this.cfw.getCurrentCodeOffset();
    }

    private void addInstructionCount() {
        addInstructionCount(Math.max(this.cfw.getCurrentCodeOffset() - this.savedCodeOffset, 1));
    }

    private void addInstructionCount(int i) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(i);
        addScriptRuntimeInvoke("addInstructionCount", "(Lorg/mozilla/javascript/Context;I)V");
    }

    private void visitIncDec(Node node) {
        int existingIntProp = node.getExistingIntProp(13);
        Node firstChild = node.getFirstChild();
        int type = firstChild.getType();
        if (type == 33) {
            Node firstChild2 = firstChild.getFirstChild();
            generateExpression(firstChild2, node);
            generateExpression(firstChild2.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("propIncrDecr", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
        } else if (type == 34) {
            throw Kit.codeBug();
        } else if (type == 36) {
            Node firstChild3 = firstChild.getFirstChild();
            generateExpression(firstChild3, node);
            generateExpression(firstChild3.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            if (firstChild3.getNext().getIntProp(8, -1) != -1) {
                addOptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
            } else {
                addScriptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
            }
        } else if (type == 39) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(firstChild.getString());
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("nameIncrDecr", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
        } else if (type == 55) {
            if (!this.hasVarsInRegs) {
                Kit.codeBug();
            }
            boolean z = (existingIntProp & 2) != 0;
            int varIndex = this.fnCurrent.getVarIndex(firstChild);
            short s = this.varRegisters[varIndex];
            if (node.getIntProp(8, -1) != -1) {
                boolean varIsDirectCallParameter = varIsDirectCallParameter(varIndex);
                ClassFileWriter classFileWriter = this.cfw;
                int i = s + (varIsDirectCallParameter ? 1 : 0);
                classFileWriter.addDLoad(i);
                if (z) {
                    this.cfw.add(92);
                }
                this.cfw.addPush(1.0d);
                if ((existingIntProp & 1) == 0) {
                    this.cfw.add(99);
                } else {
                    this.cfw.add(103);
                }
                if (!z) {
                    this.cfw.add(92);
                }
                this.cfw.addDStore(i);
                return;
            }
            if (varIsDirectCallParameter(varIndex)) {
                dcpLoadAsObject(s);
            } else {
                this.cfw.addALoad(s);
            }
            if (z) {
                this.cfw.add(89);
            }
            addObjectToDouble();
            this.cfw.addPush(1.0d);
            if ((existingIntProp & 1) == 0) {
                this.cfw.add(99);
            } else {
                this.cfw.add(103);
            }
            addDoubleWrap();
            if (!z) {
                this.cfw.add(89);
            }
            this.cfw.addAStore(s);
        } else if (type != 67) {
            Codegen.badTree();
        } else {
            generateExpression(firstChild.getFirstChild(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("refIncrDecr", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
        }
    }

    private static boolean isArithmeticNode(Node node) {
        int type = node.getType();
        return type == 22 || type == 25 || type == 24 || type == 23;
    }

    private void visitArithmetic(Node node, int i, Node node2, Node node3) {
        if (node.getIntProp(8, -1) != -1) {
            generateExpression(node2, node);
            generateExpression(node2.getNext(), node);
            this.cfw.add(i);
            return;
        }
        boolean isArithmeticNode = isArithmeticNode(node3);
        generateExpression(node2, node);
        if (!isArithmeticNode(node2)) {
            addObjectToDouble();
        }
        generateExpression(node2.getNext(), node);
        if (!isArithmeticNode(node2.getNext())) {
            addObjectToDouble();
        }
        this.cfw.add(i);
        if (!isArithmeticNode) {
            addDoubleWrap();
        }
    }

    private void visitBitOp(Node node, int i, Node node2) {
        int intProp = node.getIntProp(8, -1);
        generateExpression(node2, node);
        if (i == 20) {
            addScriptRuntimeInvoke("toUint32", "(Ljava/lang/Object;)J");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            this.cfw.addPush(31);
            this.cfw.add(126);
            this.cfw.add(125);
            this.cfw.add(138);
            addDoubleWrap();
            return;
        }
        if (intProp == -1) {
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
        } else {
            addScriptRuntimeInvoke("toInt32", "(D)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(D)I");
        }
        if (i == 18) {
            this.cfw.add(120);
        } else if (i != 19) {
            switch (i) {
                case 9:
                    this.cfw.add(128);
                    break;
                case 10:
                    this.cfw.add(130);
                    break;
                case 11:
                    this.cfw.add(126);
                    break;
                default:
                    throw Codegen.badTree();
            }
        } else {
            this.cfw.add(122);
        }
        this.cfw.add(135);
        if (intProp == -1) {
            addDoubleWrap();
        }
    }

    private int nodeIsDirectCallParameter(Node node) {
        if (node.getType() != 55 || !this.inDirectCallFunction || this.itsForcedObjectParameters) {
            return -1;
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        if (this.fnCurrent.isParameter(varIndex)) {
            return this.varRegisters[varIndex];
        }
        return -1;
    }

    private boolean varIsDirectCallParameter(int i) {
        return this.fnCurrent.isParameter(i) && this.inDirectCallFunction && !this.itsForcedObjectParameters;
    }

    private void genSimpleCompare(int i, int i2, int i3) {
        if (i2 != -1) {
            switch (i) {
                case 14:
                    this.cfw.add(152);
                    this.cfw.add(155, i2);
                    break;
                case 15:
                    this.cfw.add(152);
                    this.cfw.add(158, i2);
                    break;
                case 16:
                    this.cfw.add(151);
                    this.cfw.add(157, i2);
                    break;
                case 17:
                    this.cfw.add(151);
                    this.cfw.add(156, i2);
                    break;
                default:
                    throw Codegen.badTree();
            }
            if (i3 != -1) {
                this.cfw.add(167, i3);
                return;
            }
            return;
        }
        throw Codegen.badTree();
    }

    private void visitIfJumpRelOp(Node node, Node node2, int i, int i2) {
        Node node3 = node;
        Node node4 = node2;
        int i3 = i;
        int i4 = i2;
        if (i3 == -1 || i4 == -1) {
            throw Codegen.badTree();
        }
        int type = node.getType();
        Node next = node2.getNext();
        if (type == 53 || type == 52) {
            generateExpression(node4, node3);
            generateExpression(next, node3);
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke(type == 53 ? "instanceOf" : "in", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Z");
            this.cfw.add(154, i3);
            this.cfw.add(167, i4);
            return;
        }
        int intProp = node3.getIntProp(8, -1);
        int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node4);
        int nodeIsDirectCallParameter2 = nodeIsDirectCallParameter(next);
        if (intProp != -1) {
            if (intProp != 2) {
                generateExpression(node4, node3);
            } else if (nodeIsDirectCallParameter != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter);
            } else {
                generateExpression(node4, node3);
                addObjectToDouble();
            }
            if (intProp != 1) {
                generateExpression(next, node3);
            } else if (nodeIsDirectCallParameter2 != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter2);
            } else {
                generateExpression(next, node3);
                addObjectToDouble();
            }
            genSimpleCompare(type, i3, i4);
            return;
        }
        if (nodeIsDirectCallParameter == -1 || nodeIsDirectCallParameter2 == -1) {
            generateExpression(node4, node3);
            generateExpression(next, node3);
        } else {
            short stackTop = this.cfw.getStackTop();
            int acquireLabel = this.cfw.acquireLabel();
            this.cfw.addALoad(nodeIsDirectCallParameter);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, acquireLabel);
            this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            dcpLoadAsNumber(nodeIsDirectCallParameter2);
            genSimpleCompare(type, i3, i4);
            if (stackTop == this.cfw.getStackTop()) {
                this.cfw.markLabel(acquireLabel);
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.addALoad(nodeIsDirectCallParameter2);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                this.cfw.add(166, acquireLabel2);
                this.cfw.addALoad(nodeIsDirectCallParameter);
                addObjectToDouble();
                this.cfw.addDLoad(nodeIsDirectCallParameter2 + 1);
                genSimpleCompare(type, i3, i4);
                if (stackTop == this.cfw.getStackTop()) {
                    this.cfw.markLabel(acquireLabel2);
                    this.cfw.addALoad(nodeIsDirectCallParameter);
                    this.cfw.addALoad(nodeIsDirectCallParameter2);
                } else {
                    throw Codegen.badTree();
                }
            } else {
                throw Codegen.badTree();
            }
        }
        if (type == 17 || type == 16) {
            this.cfw.add(95);
        }
        addScriptRuntimeInvoke((type == 14 || type == 16) ? "cmp_LT" : "cmp_LE", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
        this.cfw.add(154, i3);
        this.cfw.add(167, i4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void visitIfJumpEqOp(org.mozilla.javascript.Node r18, org.mozilla.javascript.Node r19, int r20, int r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = -1
            if (r3 == r5) goto L_0x015b
            if (r4 == r5) goto L_0x015b
            org.mozilla.classfile.ClassFileWriter r6 = r0.cfw
            short r6 = r6.getStackTop()
            int r7 = r18.getType()
            org.mozilla.javascript.Node r8 = r19.getNext()
            int r9 = r19.getType()
            r12 = 12
            r14 = 42
            if (r9 == r14) goto L_0x00dd
            int r9 = r8.getType()
            if (r9 != r14) goto L_0x002f
            goto L_0x00dd
        L_0x002f:
            int r9 = r0.nodeIsDirectCallParameter(r2)
            if (r9 == r5) goto L_0x00a1
            int r5 = r8.getType()
            r13 = 149(0x95, float:2.09E-43)
            if (r5 != r13) goto L_0x00a1
            org.mozilla.javascript.Node r5 = r8.getFirstChild()
            int r13 = r5.getType()
            r10 = 40
            if (r13 != r10) goto L_0x00a1
            org.mozilla.classfile.ClassFileWriter r10 = r0.cfw
            r10.addALoad(r9)
            org.mozilla.classfile.ClassFileWriter r10 = r0.cfw
            r13 = 178(0xb2, float:2.5E-43)
            java.lang.String r15 = "java/lang/Void"
            java.lang.String r11 = "TYPE"
            java.lang.String r14 = "Ljava/lang/Class;"
            r10.add(r13, r15, r11, r14)
            org.mozilla.classfile.ClassFileWriter r10 = r0.cfw
            int r10 = r10.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r11 = r0.cfw
            r13 = 166(0xa6, float:2.33E-43)
            r11.add((int) r13, (int) r10)
            org.mozilla.classfile.ClassFileWriter r11 = r0.cfw
            int r9 = r9 + 1
            r11.addDLoad(r9)
            org.mozilla.classfile.ClassFileWriter r9 = r0.cfw
            double r13 = r5.getDouble()
            r9.addPush((double) r13)
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r9 = 151(0x97, float:2.12E-43)
            r5.add(r9)
            if (r7 != r12) goto L_0x008b
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r9 = 153(0x99, float:2.14E-43)
            r5.add((int) r9, (int) r3)
            r11 = 154(0x9a, float:2.16E-43)
            goto L_0x0094
        L_0x008b:
            r9 = 153(0x99, float:2.14E-43)
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r11 = 154(0x9a, float:2.16E-43)
            r5.add((int) r11, (int) r3)
        L_0x0094:
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r13 = 167(0xa7, float:2.34E-43)
            r5.add((int) r13, (int) r4)
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r5.markLabel(r10)
            goto L_0x00a5
        L_0x00a1:
            r9 = 153(0x99, float:2.14E-43)
            r11 = 154(0x9a, float:2.16E-43)
        L_0x00a5:
            r0.generateExpression(r2, r1)
            r0.generateExpression(r8, r1)
            java.lang.String r1 = "shallowEq"
            java.lang.String r2 = "eq"
            if (r7 == r12) goto L_0x00c7
            r5 = 13
            if (r7 == r5) goto L_0x00c3
            r5 = 46
            if (r7 == r5) goto L_0x00c8
            r2 = 47
            if (r7 != r2) goto L_0x00be
            goto L_0x00c4
        L_0x00be:
            java.lang.RuntimeException r1 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r1
        L_0x00c3:
            r1 = r2
        L_0x00c4:
            r14 = 153(0x99, float:2.14E-43)
            goto L_0x00ca
        L_0x00c7:
            r1 = r2
        L_0x00c8:
            r14 = 154(0x9a, float:2.16E-43)
        L_0x00ca:
            java.lang.String r2 = "(Ljava/lang/Object;Ljava/lang/Object;)Z"
            r0.addScriptRuntimeInvoke(r1, r2)
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            r1.add((int) r14, (int) r3)
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            r2 = 167(0xa7, float:2.34E-43)
            r1.add((int) r2, (int) r4)
            goto L_0x014d
        L_0x00dd:
            int r5 = r19.getType()
            if (r5 != r14) goto L_0x00e4
            r2 = r8
        L_0x00e4:
            r0.generateExpression(r2, r1)
            r1 = 199(0xc7, float:2.79E-43)
            r2 = 46
            if (r7 == r2) goto L_0x013d
            r2 = 47
            if (r7 != r2) goto L_0x00f4
            r2 = 46
            goto L_0x013d
        L_0x00f4:
            if (r7 == r12) goto L_0x0105
            r2 = 13
            if (r7 != r2) goto L_0x0100
            r16 = r4
            r4 = r3
            r3 = r16
            goto L_0x0105
        L_0x0100:
            java.lang.RuntimeException r1 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r1
        L_0x0105:
            org.mozilla.classfile.ClassFileWriter r2 = r0.cfw
            r5 = 89
            r2.add(r5)
            org.mozilla.classfile.ClassFileWriter r2 = r0.cfw
            int r2 = r2.acquireLabel()
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r5.add((int) r1, (int) r2)
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            short r1 = r1.getStackTop()
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r7 = 87
            r5.add(r7)
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r7 = 167(0xa7, float:2.34E-43)
            r5.add((int) r7, (int) r3)
            org.mozilla.classfile.ClassFileWriter r5 = r0.cfw
            r5.markLabel(r2, r1)
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            org.mozilla.javascript.optimizer.Codegen.pushUndefined(r1)
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            r2 = 165(0xa5, float:2.31E-43)
            r1.add((int) r2, (int) r3)
            goto L_0x0146
        L_0x013d:
            if (r7 != r2) goto L_0x0141
            r1 = 198(0xc6, float:2.77E-43)
        L_0x0141:
            org.mozilla.classfile.ClassFileWriter r2 = r0.cfw
            r2.add((int) r1, (int) r3)
        L_0x0146:
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            r2 = 167(0xa7, float:2.34E-43)
            r1.add((int) r2, (int) r4)
        L_0x014d:
            org.mozilla.classfile.ClassFileWriter r1 = r0.cfw
            short r1 = r1.getStackTop()
            if (r6 != r1) goto L_0x0156
            return
        L_0x0156:
            java.lang.RuntimeException r1 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r1
        L_0x015b:
            java.lang.RuntimeException r1 = org.mozilla.javascript.optimizer.Codegen.badTree()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.optimizer.BodyCodegen.visitIfJumpEqOp(org.mozilla.javascript.Node, org.mozilla.javascript.Node, int, int):void");
    }

    private void visitSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitStrictSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("strictSetName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitSetConst(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setConst", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitGetVar(Node node) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        short s = this.varRegisters[varIndex];
        if (varIsDirectCallParameter(varIndex)) {
            if (node.getIntProp(8, -1) != -1) {
                dcpLoadAsNumber(s);
            } else {
                dcpLoadAsObject(s);
            }
        } else if (this.fnCurrent.isNumberVar(varIndex)) {
            this.cfw.addDLoad(s);
        } else {
            this.cfw.addALoad(s);
        }
    }

    private void visitSetVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        short s = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (z) {
                return;
            }
            if (z2) {
                this.cfw.add(88);
            } else {
                this.cfw.add(87);
            }
        } else if (!varIsDirectCallParameter(varIndex)) {
            boolean isNumberVar = this.fnCurrent.isNumberVar(varIndex);
            if (!z2) {
                if (isNumberVar) {
                    Kit.codeBug();
                }
                this.cfw.addAStore(s);
                if (z) {
                    this.cfw.addALoad(s);
                }
            } else if (isNumberVar) {
                this.cfw.addDStore(s);
                if (z) {
                    this.cfw.addDLoad(s);
                }
            } else {
                if (z) {
                    this.cfw.add(92);
                }
                addDoubleWrap();
                this.cfw.addAStore(s);
            }
        } else if (z2) {
            if (z) {
                this.cfw.add(92);
            }
            this.cfw.addALoad(s);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            int acquireLabel = this.cfw.acquireLabel();
            int acquireLabel2 = this.cfw.acquireLabel();
            this.cfw.add(165, acquireLabel);
            short stackTop = this.cfw.getStackTop();
            addDoubleWrap();
            this.cfw.addAStore(s);
            this.cfw.add(167, acquireLabel2);
            this.cfw.markLabel(acquireLabel, stackTop);
            this.cfw.addDStore(s + 1);
            this.cfw.markLabel(acquireLabel2);
        } else {
            if (z) {
                this.cfw.add(89);
            }
            this.cfw.addAStore(s);
        }
    }

    private void visitSetConstVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        short s = this.varRegisters[varIndex];
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        if (z2) {
            int i = s + 2;
            this.cfw.addILoad(i);
            this.cfw.add(154, acquireLabel2);
            short stackTop = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i);
            this.cfw.addDStore(s);
            if (z) {
                this.cfw.addDLoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop);
                this.cfw.add(88);
            }
        } else {
            int i2 = s + 1;
            this.cfw.addILoad(i2);
            this.cfw.add(154, acquireLabel2);
            short stackTop2 = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i2);
            this.cfw.addAStore(s);
            if (z) {
                this.cfw.addALoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop2);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop2);
                this.cfw.add(87);
            }
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void visitGetProp(Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        generateExpression(next, node);
        if (node.getType() == 34) {
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke("getObjectPropNoWarn", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else if (node2.getType() == 43 && next.getType() == 41) {
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitSetProp(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 139) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        if (i == 139) {
            this.cfw.add(90);
            if (node2.getType() == 43 && next.getType() == 41) {
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            } else {
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("setObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
    }

    private void visitSetElem(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 140) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        boolean z = node.getIntProp(8, -1) != -1;
        if (i == 140) {
            if (z) {
                this.cfw.add(93);
                this.cfw.addALoad(this.contextLocal);
                addOptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            } else {
                this.cfw.add(90);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        if (z) {
            addScriptRuntimeInvoke("setObjectIndex", "(Ljava/lang/Object;DLjava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else {
            addScriptRuntimeInvoke("setObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        }
    }

    private void visitDotQuery(Node node, Node node2) {
        updateLineNumber(node);
        generateExpression(node2, node);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("enterDotQuery", "(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(1);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.add(87);
        generateExpression(node2.getNext(), node);
        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("updateDotQuery", "(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        this.cfw.add(89);
        this.cfw.add(198, acquireLabel);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("leaveDotQuery", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
    }

    private int getLocalBlockRegister(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private void dcpLoadAsNumber(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        addObjectToDouble();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        this.cfw.markLabel(acquireLabel2);
    }

    private void dcpLoadAsObject(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        addDoubleWrap();
        this.cfw.markLabel(acquireLabel2);
    }

    private void addGoto(Node node, int i) {
        this.cfw.add(i, getTargetLabel(node));
    }

    private void addObjectToDouble() {
        addScriptRuntimeInvoke("toNumber", "(Ljava/lang/Object;)D");
    }

    private void addNewObjectArray(int i) {
        if (i == 0) {
            short s = this.itsZeroArgArray;
            if (s >= 0) {
                this.cfw.addALoad(s);
            } else {
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
            }
        } else {
            this.cfw.addPush(i);
            this.cfw.add(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
        }
    }

    private void addScriptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org.mozilla.javascript.ScriptRuntime", str, str2);
    }

    private void addOptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", str, str2);
    }

    private void addJumpedBooleanWrap(int i, int i2) {
        this.cfw.markLabel(i2);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(i);
        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        this.cfw.markLabel(acquireLabel);
        this.cfw.adjustStackTop(-1);
    }

    private void addDoubleWrap() {
        addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
    }

    private short getNewWordPairLocal(boolean z) {
        short consecutiveSlots = getConsecutiveSlots(2, z);
        if (consecutiveSlots < 1023) {
            int[] iArr = this.locals;
            iArr[consecutiveSlots] = 1;
            iArr[consecutiveSlots + 1] = 1;
            if (z) {
                iArr[consecutiveSlots + 2] = 1;
            }
            short s = this.firstFreeLocal;
            if (consecutiveSlots != s) {
                return consecutiveSlots;
            }
            for (int i = s + 2; i < 1024; i++) {
                if (this.locals[i] == 0) {
                    short s2 = (short) i;
                    this.firstFreeLocal = s2;
                    if (this.localsMax < s2) {
                        this.localsMax = s2;
                    }
                    return consecutiveSlots;
                }
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private short getNewWordLocal(boolean z) {
        short consecutiveSlots = getConsecutiveSlots(1, z);
        if (consecutiveSlots < 1023) {
            int[] iArr = this.locals;
            iArr[consecutiveSlots] = 1;
            if (z) {
                iArr[consecutiveSlots + 1] = 1;
            }
            short s = this.firstFreeLocal;
            if (consecutiveSlots != s) {
                return consecutiveSlots;
            }
            for (int i = s + 2; i < 1024; i++) {
                if (this.locals[i] == 0) {
                    short s2 = (short) i;
                    this.firstFreeLocal = s2;
                    if (this.localsMax < s2) {
                        this.localsMax = s2;
                    }
                    return consecutiveSlots;
                }
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private short getNewWordLocal() {
        short s = this.firstFreeLocal;
        this.locals[s] = 1;
        for (int i = s + 1; i < 1024; i++) {
            if (this.locals[i] == 0) {
                short s2 = (short) i;
                this.firstFreeLocal = s2;
                if (this.localsMax < s2) {
                    this.localsMax = s2;
                }
                return s;
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private short getConsecutiveSlots(int i, boolean z) {
        if (z) {
            i++;
        }
        short s = this.firstFreeLocal;
        while (s < 1023) {
            int i2 = 0;
            while (i2 < i && this.locals[s + i2] == 0) {
                i2++;
            }
            if (i2 >= i) {
                break;
            }
            s = (short) (s + 1);
        }
        return s;
    }

    private void incReferenceWordLocal(short s) {
        int[] iArr = this.locals;
        iArr[s] = iArr[s] + 1;
    }

    private void decReferenceWordLocal(short s) {
        int[] iArr = this.locals;
        iArr[s] = iArr[s] - 1;
    }

    private void releaseWordLocal(short s) {
        if (s < this.firstFreeLocal) {
            this.firstFreeLocal = s;
        }
        this.locals[s] = 0;
    }

    /* compiled from: Codegen */
    static class FinallyReturnPoint {
        public List<Integer> jsrPoints = new ArrayList();
        public int tableLabel = 0;

        FinallyReturnPoint() {
        }
    }
}
