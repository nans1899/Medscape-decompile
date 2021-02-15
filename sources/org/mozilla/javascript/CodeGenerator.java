package org.mozilla.javascript;

import androidx.core.view.InputDeviceCompat;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

class CodeGenerator extends Icode {
    private static final int ECF_TAIL = 1;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private CompilerEnvirons compilerEnv;
    private int doubleTableTop;
    private int exceptionTableTop;
    private long[] fixupTable;
    private int fixupTableTop;
    private int iCodeTop;
    private InterpreterData itsData;
    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;
    private int[] labelTable;
    private int labelTableTop;
    private int lineNumber;
    private ObjArray literalIds = new ObjArray();
    private int localTop;
    private ScriptNode scriptOrFn;
    private int stackDepth;
    private ObjToIntMap strings = new ObjToIntMap(20);

    CodeGenerator() {
    }

    public InterpreterData compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        this.compilerEnv = compilerEnvirons;
        new NodeTransformer().transform(scriptNode);
        if (z) {
            this.scriptOrFn = scriptNode.getFunctionNode(0);
        } else {
            this.scriptOrFn = scriptNode;
        }
        InterpreterData interpreterData = new InterpreterData(compilerEnvirons.getLanguageVersion(), this.scriptOrFn.getSourceName(), str, ((AstRoot) scriptNode).isInStrictMode());
        this.itsData = interpreterData;
        interpreterData.topLevel = true;
        if (z) {
            generateFunctionICode();
        } else {
            generateICodeFromTree(this.scriptOrFn);
        }
        return this.itsData;
    }

    private void generateFunctionICode() {
        this.itsInFunctionFlag = true;
        FunctionNode functionNode = (FunctionNode) this.scriptOrFn;
        this.itsData.itsFunctionType = functionNode.getFunctionType();
        this.itsData.itsNeedsActivation = functionNode.requiresActivation();
        if (functionNode.getFunctionName() != null) {
            this.itsData.itsName = functionNode.getName();
        }
        if (functionNode.isGenerator()) {
            addIcode(-62);
            addUint16(functionNode.getBaseLineno() & 65535);
        }
        generateICodeFromTree(functionNode.getLastChild());
    }

    private void generateICodeFromTree(Node node) {
        int i;
        generateNestedFunctions();
        generateRegExpLiterals();
        visitStatement(node, 0);
        fixLabelGotos();
        if (this.itsData.itsFunctionType == 0) {
            addToken(64);
        }
        int length = this.itsData.itsICode.length;
        int i2 = this.iCodeTop;
        if (length != i2) {
            byte[] bArr = new byte[i2];
            System.arraycopy(this.itsData.itsICode, 0, bArr, 0, this.iCodeTop);
            this.itsData.itsICode = bArr;
        }
        if (this.strings.size() == 0) {
            this.itsData.itsStringTable = null;
        } else {
            this.itsData.itsStringTable = new String[this.strings.size()];
            ObjToIntMap.Iterator newIterator = this.strings.newIterator();
            newIterator.start();
            while (!newIterator.done()) {
                String str = (String) newIterator.getKey();
                int value = newIterator.getValue();
                if (this.itsData.itsStringTable[value] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsStringTable[value] = str;
                newIterator.next();
            }
        }
        if (this.doubleTableTop == 0) {
            this.itsData.itsDoubleTable = null;
        } else {
            int length2 = this.itsData.itsDoubleTable.length;
            int i3 = this.doubleTableTop;
            if (length2 != i3) {
                double[] dArr = new double[i3];
                System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, this.doubleTableTop);
                this.itsData.itsDoubleTable = dArr;
            }
        }
        if (!(this.exceptionTableTop == 0 || this.itsData.itsExceptionTable.length == (i = this.exceptionTableTop))) {
            int[] iArr = new int[i];
            System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, this.exceptionTableTop);
            this.itsData.itsExceptionTable = iArr;
        }
        this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
        InterpreterData interpreterData = this.itsData;
        interpreterData.itsMaxFrameArray = interpreterData.itsMaxVars + this.itsData.itsMaxLocals + this.itsData.itsMaxStack;
        this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
        this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
        this.itsData.argCount = this.scriptOrFn.getParamCount();
        this.itsData.encodedSourceStart = this.scriptOrFn.getEncodedSourceStart();
        this.itsData.encodedSourceEnd = this.scriptOrFn.getEncodedSourceEnd();
        if (this.literalIds.size() != 0) {
            this.itsData.literalIds = this.literalIds.toArray();
        }
    }

    private void generateNestedFunctions() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        if (functionCount != 0) {
            InterpreterData[] interpreterDataArr = new InterpreterData[functionCount];
            for (int i = 0; i != functionCount; i++) {
                FunctionNode functionNode = this.scriptOrFn.getFunctionNode(i);
                CodeGenerator codeGenerator = new CodeGenerator();
                codeGenerator.compilerEnv = this.compilerEnv;
                codeGenerator.scriptOrFn = functionNode;
                codeGenerator.itsData = new InterpreterData(this.itsData);
                codeGenerator.generateFunctionICode();
                interpreterDataArr[i] = codeGenerator.itsData;
            }
            this.itsData.itsNestedFunctions = interpreterDataArr;
        }
    }

    private void generateRegExpLiterals() {
        int regexpCount = this.scriptOrFn.getRegexpCount();
        if (regexpCount != 0) {
            Context context = Context.getContext();
            RegExpProxy checkRegExpProxy = ScriptRuntime.checkRegExpProxy(context);
            Object[] objArr = new Object[regexpCount];
            for (int i = 0; i != regexpCount; i++) {
                objArr[i] = checkRegExpProxy.compileRegExp(context, this.scriptOrFn.getRegexpString(i), this.scriptOrFn.getRegexpFlags(i));
            }
            this.itsData.itsRegExpLiterals = objArr;
        }
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        if (lineno != this.lineNumber && lineno >= 0) {
            if (this.itsData.firstLinePC < 0) {
                this.itsData.firstLinePC = lineno;
            }
            this.lineNumber = lineno;
            addIcode(-26);
            addUint16(lineno & 65535);
        }
    }

    private RuntimeException badTree(Node node) {
        throw new RuntimeException(node.toString());
    }

    private void visitStatement(Node node, int i) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type != -62) {
            if (type != 64) {
                int i2 = 1;
                if (type != 81) {
                    int i3 = -5;
                    if (type == 109) {
                        int existingIntProp = node.getExistingIntProp(1);
                        int functionType = this.scriptOrFn.getFunctionNode(existingIntProp).getFunctionType();
                        if (functionType == 3) {
                            addIndexOp(-20, existingIntProp);
                        } else if (functionType != 1) {
                            throw Kit.codeBug();
                        }
                        if (!this.itsInFunctionFlag) {
                            addIndexOp(-19, existingIntProp);
                            stackChange(1);
                            addIcode(-5);
                            stackChange(-1);
                        }
                    } else if (type != 114) {
                        if (type != 123) {
                            if (type == 125) {
                                stackChange(1);
                                int localBlockRef = getLocalBlockRef(node);
                                addIndexOp(-24, localBlockRef);
                                stackChange(-1);
                                while (firstChild != null) {
                                    visitStatement(firstChild, i);
                                    firstChild = firstChild.getNext();
                                }
                                addIndexOp(-25, localBlockRef);
                            } else if (type == 141) {
                                int allocLocal = allocLocal();
                                node.putIntProp(2, allocLocal);
                                updateLineNumber(node);
                                while (firstChild != null) {
                                    visitStatement(firstChild, i);
                                    firstChild = firstChild.getNext();
                                }
                                addIndexOp(-56, allocLocal);
                                releaseLocal(allocLocal);
                            } else if (type == 160) {
                                addIcode(-64);
                            } else if (type == 50) {
                                updateLineNumber(node);
                                visitExpression(firstChild, 0);
                                addToken(50);
                                addUint16(this.lineNumber & 65535);
                                stackChange(-1);
                            } else if (type != 51) {
                                switch (type) {
                                    case 2:
                                        visitExpression(firstChild, 0);
                                        addToken(2);
                                        stackChange(-1);
                                        break;
                                    case 3:
                                        addToken(3);
                                        break;
                                    case 4:
                                        updateLineNumber(node);
                                        if (node.getIntProp(20, 0) == 0) {
                                            if (firstChild == null) {
                                                addIcode(-22);
                                                break;
                                            } else {
                                                visitExpression(firstChild, 1);
                                                addToken(4);
                                                stackChange(-1);
                                                break;
                                            }
                                        } else {
                                            addIcode(-63);
                                            addUint16(this.lineNumber & 65535);
                                            break;
                                        }
                                    case 5:
                                        addGoto(((Jump) node).target, type);
                                        break;
                                    case 6:
                                    case 7:
                                        Node node2 = ((Jump) node).target;
                                        visitExpression(firstChild, 0);
                                        addGoto(node2, type);
                                        stackChange(-1);
                                        break;
                                    default:
                                        switch (type) {
                                            case 57:
                                                int localBlockRef2 = getLocalBlockRef(node);
                                                int existingIntProp2 = node.getExistingIntProp(14);
                                                String string = firstChild.getString();
                                                visitExpression(firstChild.getNext(), 0);
                                                addStringPrefix(string);
                                                addIndexPrefix(localBlockRef2);
                                                addToken(57);
                                                if (existingIntProp2 == 0) {
                                                    i2 = 0;
                                                }
                                                addUint8(i2);
                                                stackChange(-1);
                                                break;
                                            case 58:
                                            case 59:
                                            case 60:
                                                visitExpression(firstChild, 0);
                                                addIndexOp(type, getLocalBlockRef(node));
                                                stackChange(-1);
                                                break;
                                            default:
                                                switch (type) {
                                                    case 128:
                                                    case 129:
                                                    case 130:
                                                    case 132:
                                                        break;
                                                    case 131:
                                                        markTargetLabel(node);
                                                        break;
                                                    case 133:
                                                    case 134:
                                                        updateLineNumber(node);
                                                        visitExpression(firstChild, 0);
                                                        if (type == 133) {
                                                            i3 = -4;
                                                        }
                                                        addIcode(i3);
                                                        stackChange(-1);
                                                        break;
                                                    case 135:
                                                        addGoto(((Jump) node).target, -23);
                                                        break;
                                                    case 136:
                                                        break;
                                                    default:
                                                        throw badTree(node);
                                                }
                                        }
                                }
                            } else {
                                updateLineNumber(node);
                                addIndexOp(51, getLocalBlockRef(node));
                            }
                        }
                        updateLineNumber(node);
                        while (firstChild != null) {
                            visitStatement(firstChild, i);
                            firstChild = firstChild.getNext();
                        }
                    } else {
                        updateLineNumber(node);
                        visitExpression(firstChild, 0);
                        Jump jump = (Jump) firstChild.getNext();
                        while (jump != null) {
                            if (jump.getType() == 115) {
                                Node firstChild2 = jump.getFirstChild();
                                addIcode(-1);
                                stackChange(1);
                                visitExpression(firstChild2, 0);
                                addToken(46);
                                stackChange(-1);
                                addGoto(jump.target, -6);
                                stackChange(-1);
                                jump = (Jump) jump.getNext();
                            } else {
                                throw badTree(jump);
                            }
                        }
                        addIcode(-4);
                        stackChange(-1);
                    }
                } else {
                    Jump jump2 = (Jump) node;
                    int localBlockRef3 = getLocalBlockRef(jump2);
                    int allocLocal2 = allocLocal();
                    addIndexOp(-13, allocLocal2);
                    int i4 = this.iCodeTop;
                    boolean z = this.itsInTryFlag;
                    this.itsInTryFlag = true;
                    while (firstChild != null) {
                        visitStatement(firstChild, i);
                        firstChild = firstChild.getNext();
                    }
                    this.itsInTryFlag = z;
                    Node node3 = jump2.target;
                    if (node3 != null) {
                        int i5 = this.labelTable[getTargetLabel(node3)];
                        addExceptionHandler(i4, i5, i5, false, localBlockRef3, allocLocal2);
                    }
                    Node node4 = jump2.getFinally();
                    if (node4 != null) {
                        int i6 = this.labelTable[getTargetLabel(node4)];
                        addExceptionHandler(i4, i6, i6, true, localBlockRef3, allocLocal2);
                    }
                    addIndexOp(-56, allocLocal2);
                    releaseLocal(allocLocal2);
                }
            } else {
                updateLineNumber(node);
                addToken(64);
            }
        }
        if (this.stackDepth != i) {
            throw Kit.codeBug();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x029d, code lost:
        if (r0 != 30) goto L_0x02a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x029f, code lost:
        visitExpression(r1, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02a3, code lost:
        generateCallFunAndThis(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02a6, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02a7, code lost:
        r1 = r1.getNext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02ab, code lost:
        if (r1 == null) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ad, code lost:
        visitExpression(r1, 0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02b3, code lost:
        r14 = r14.getIntProp(10, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02b9, code lost:
        if (r14 == 0) goto L_0x02d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02bb, code lost:
        addIndexOp(-21, r4);
        addUint8(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02c3, code lost:
        if (r0 != 30) goto L_0x02c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02c5, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02c6, code lost:
        addUint8(r7);
        addUint16(r13.lineNumber & 65535);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02d2, code lost:
        if (r0 != 38) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02d6, code lost:
        if ((r15 & 1) == 0) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02de, code lost:
        if (r13.compilerEnv.isGenerateDebugInfo() != false) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02e2, code lost:
        if (r13.itsInTryFlag != false) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02e4, code lost:
        r0 = -55;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02e8, code lost:
        addIndexOp(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02eb, code lost:
        if (r0 != 30) goto L_0x02f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02ed, code lost:
        stackChange(-r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02f2, code lost:
        stackChange(-1 - r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02fb, code lost:
        if (r4 <= r13.itsData.itsMaxCalleeArgs) goto L_0x03ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02fd, code lost:
        r13.itsData.itsMaxCalleeArgs = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0303, code lost:
        visitExpression(r1, 0);
        visitExpression(r1.getNext(), 0);
        addToken(r0);
        stackChange(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0315, code lost:
        r14 = r1.getString();
        visitExpression(r1, 0);
        visitExpression(r1.getNext(), 0);
        addStringOp(r0, r14);
        stackChange(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01a6, code lost:
        addToken(r0);
        stackChange(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0207, code lost:
        visitExpression(r1, 0);
        r14 = r1.getNext();
        visitExpression(r14, 0);
        r14 = r14.getNext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0218, code lost:
        if (r0 != 140) goto L_0x022b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x021a, code lost:
        addIcode(-2);
        stackChange(2);
        addToken(36);
        stackChange(-1);
        stackChange(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x022b, code lost:
        visitExpression(r14, 0);
        addToken(37);
        stackChange(-2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0238, code lost:
        visitExpression(r1, 0);
        r14 = r1.getNext();
        r15 = r14.getString();
        r14 = r14.getNext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0249, code lost:
        if (r0 != 139) goto L_0x0259;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x024b, code lost:
        addIcode(-1);
        stackChange(1);
        addStringOp(33, r15);
        stackChange(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0259, code lost:
        visitExpression(r14, 0);
        addStringOp(35, r15);
        stackChange(-1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void visitExpression(org.mozilla.javascript.Node r14, int r15) {
        /*
            r13 = this;
            int r0 = r14.getType()
            org.mozilla.javascript.Node r1 = r14.getFirstChild()
            int r2 = r13.stackDepth
            r3 = 89
            r4 = -4
            r5 = -1
            r6 = 1
            r7 = 0
            if (r0 == r3) goto L_0x03e6
            r3 = 102(0x66, float:1.43E-43)
            r8 = 7
            if (r0 == r3) goto L_0x03bd
            r3 = 109(0x6d, float:1.53E-43)
            r9 = 2
            if (r0 == r3) goto L_0x039f
            r3 = -50
            r10 = 126(0x7e, float:1.77E-43)
            if (r0 == r10) goto L_0x038f
            r11 = 142(0x8e, float:1.99E-43)
            if (r0 == r11) goto L_0x036b
            r12 = 146(0x92, float:2.05E-43)
            if (r0 == r12) goto L_0x034d
            r12 = 159(0x9f, float:2.23E-43)
            if (r0 == r12) goto L_0x032b
            r12 = 65535(0xffff, float:9.1834E-41)
            switch(r0) {
                case 8: goto L_0x0315;
                case 9: goto L_0x0303;
                case 10: goto L_0x0303;
                case 11: goto L_0x0303;
                case 12: goto L_0x0303;
                case 13: goto L_0x0303;
                case 14: goto L_0x0303;
                case 15: goto L_0x0303;
                case 16: goto L_0x0303;
                case 17: goto L_0x0303;
                case 18: goto L_0x0303;
                case 19: goto L_0x0303;
                case 20: goto L_0x0303;
                case 21: goto L_0x0303;
                case 22: goto L_0x0303;
                case 23: goto L_0x0303;
                case 24: goto L_0x0303;
                case 25: goto L_0x0303;
                case 26: goto L_0x038f;
                case 27: goto L_0x038f;
                case 28: goto L_0x038f;
                case 29: goto L_0x038f;
                case 30: goto L_0x029b;
                case 31: goto L_0x0276;
                case 32: goto L_0x038f;
                case 33: goto L_0x0266;
                case 34: goto L_0x0266;
                case 35: goto L_0x0238;
                case 36: goto L_0x0303;
                case 37: goto L_0x0207;
                case 38: goto L_0x029b;
                case 39: goto L_0x01fb;
                case 40: goto L_0x01ae;
                case 41: goto L_0x01fb;
                case 42: goto L_0x01a6;
                case 43: goto L_0x01a6;
                case 44: goto L_0x01a6;
                case 45: goto L_0x01a6;
                case 46: goto L_0x0303;
                case 47: goto L_0x0303;
                case 48: goto L_0x0197;
                case 49: goto L_0x01fb;
                default: goto L_0x0034;
            }
        L_0x0034:
            r10 = 55
            switch(r0) {
                case 52: goto L_0x0303;
                case 53: goto L_0x0303;
                case 54: goto L_0x0189;
                case 55: goto L_0x0172;
                case 56: goto L_0x0155;
                default: goto L_0x0039;
            }
        L_0x0039:
            switch(r0) {
                case 61: goto L_0x0149;
                case 62: goto L_0x0149;
                case 63: goto L_0x01a6;
                default: goto L_0x003c;
            }
        L_0x003c:
            switch(r0) {
                case 65: goto L_0x0144;
                case 66: goto L_0x0144;
                case 67: goto L_0x013c;
                case 68: goto L_0x036b;
                case 69: goto L_0x013c;
                case 70: goto L_0x029b;
                case 71: goto L_0x012c;
                case 72: goto L_0x0111;
                case 73: goto L_0x0315;
                case 74: goto L_0x0109;
                case 75: goto L_0x0109;
                case 76: goto L_0x0109;
                case 77: goto L_0x00ee;
                case 78: goto L_0x00ee;
                case 79: goto L_0x00ee;
                case 80: goto L_0x00ee;
                default: goto L_0x003f;
            }
        L_0x003f:
            switch(r0) {
                case 104: goto L_0x00c4;
                case 105: goto L_0x00c4;
                case 106: goto L_0x00bf;
                case 107: goto L_0x00bf;
                default: goto L_0x0042;
            }
        L_0x0042:
            switch(r0) {
                case 137: goto L_0x0090;
                case 138: goto L_0x008b;
                case 139: goto L_0x0238;
                case 140: goto L_0x0207;
                default: goto L_0x0045;
            }
        L_0x0045:
            switch(r0) {
                case 155: goto L_0x0073;
                case 156: goto L_0x0056;
                case 157: goto L_0x004d;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.RuntimeException r14 = r13.badTree(r14)
            throw r14
        L_0x004d:
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitArrayComprehension(r14, r1, r15)
            goto L_0x03ff
        L_0x0056:
            org.mozilla.javascript.InterpreterData r14 = r13.itsData
            boolean r14 = r14.itsNeedsActivation
            if (r14 == 0) goto L_0x005f
            org.mozilla.javascript.Kit.codeBug()
        L_0x005f:
            org.mozilla.javascript.ast.ScriptNode r14 = r13.scriptOrFn
            int r14 = r14.getIndexForNameNode(r1)
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            r15 = 156(0x9c, float:2.19E-43)
            r13.addVarOp(r15, r14)
            goto L_0x03ff
        L_0x0073:
            java.lang.String r14 = r1.getString()
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            r15 = -59
            r13.addStringOp(r15, r14)
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x008b:
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x0090:
            boolean r15 = r13.itsInFunctionFlag
            if (r15 == 0) goto L_0x00a1
            org.mozilla.javascript.InterpreterData r15 = r13.itsData
            boolean r15 = r15.itsNeedsActivation
            if (r15 != 0) goto L_0x00a1
            org.mozilla.javascript.ast.ScriptNode r15 = r13.scriptOrFn
            int r15 = r15.getIndexForNameNode(r14)
            goto L_0x00a2
        L_0x00a1:
            r15 = -1
        L_0x00a2:
            if (r15 != r5) goto L_0x00b2
            r15 = -14
            java.lang.String r14 = r14.getString()
            r13.addStringOp(r15, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x00b2:
            r13.addVarOp(r10, r15)
            r13.stackChange(r6)
            r14 = 32
            r13.addToken(r14)
            goto L_0x03ff
        L_0x00bf:
            r13.visitIncDec(r14, r1)
            goto L_0x03ff
        L_0x00c4:
            r13.visitExpression(r1, r7)
            r13.addIcode(r5)
            r13.stackChange(r6)
            int r14 = r13.iCodeTop
            r3 = 105(0x69, float:1.47E-43)
            if (r0 != r3) goto L_0x00d4
            goto L_0x00d5
        L_0x00d4:
            r8 = 6
        L_0x00d5:
            r13.addGotoOp(r8)
            r13.stackChange(r5)
            r13.addIcode(r4)
            r13.stackChange(r5)
            org.mozilla.javascript.Node r0 = r1.getNext()
            r15 = r15 & r6
            r13.visitExpression(r0, r15)
            r13.resolveForwardGoto(r14)
            goto L_0x03ff
        L_0x00ee:
            r15 = 16
            int r14 = r14.getIntProp(r15, r7)
            r15 = 0
        L_0x00f5:
            r13.visitExpression(r1, r7)
            int r15 = r15 + r6
            org.mozilla.javascript.Node r1 = r1.getNext()
            if (r1 != 0) goto L_0x00f5
            r13.addIndexOp(r0, r14)
            int r14 = 1 - r15
            r13.stackChange(r14)
            goto L_0x03ff
        L_0x0109:
            r13.visitExpression(r1, r7)
            r13.addToken(r0)
            goto L_0x03ff
        L_0x0111:
            if (r1 == 0) goto L_0x0117
            r13.visitExpression(r1, r7)
            goto L_0x011d
        L_0x0117:
            r13.addIcode(r3)
            r13.stackChange(r6)
        L_0x011d:
            r15 = 72
            r13.addToken(r15)
            int r14 = r14.getLineno()
            r14 = r14 & r12
            r13.addUint16(r14)
            goto L_0x03ff
        L_0x012c:
            r13.visitExpression(r1, r7)
            r15 = 17
            java.lang.Object r14 = r14.getProp(r15)
            java.lang.String r14 = (java.lang.String) r14
            r13.addStringOp(r0, r14)
            goto L_0x03ff
        L_0x013c:
            r13.visitExpression(r1, r7)
            r13.addToken(r0)
            goto L_0x03ff
        L_0x0144:
            r13.visitLiteral(r14, r1)
            goto L_0x03ff
        L_0x0149:
            int r14 = r13.getLocalBlockRef(r14)
            r13.addIndexOp(r0, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x0155:
            org.mozilla.javascript.InterpreterData r14 = r13.itsData
            boolean r14 = r14.itsNeedsActivation
            if (r14 == 0) goto L_0x015e
            org.mozilla.javascript.Kit.codeBug()
        L_0x015e:
            org.mozilla.javascript.ast.ScriptNode r14 = r13.scriptOrFn
            int r14 = r14.getIndexForNameNode(r1)
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            r15 = 56
            r13.addVarOp(r15, r14)
            goto L_0x03ff
        L_0x0172:
            org.mozilla.javascript.InterpreterData r15 = r13.itsData
            boolean r15 = r15.itsNeedsActivation
            if (r15 == 0) goto L_0x017b
            org.mozilla.javascript.Kit.codeBug()
        L_0x017b:
            org.mozilla.javascript.ast.ScriptNode r15 = r13.scriptOrFn
            int r14 = r15.getIndexForNameNode(r14)
            r13.addVarOp(r10, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x0189:
            int r14 = r13.getLocalBlockRef(r14)
            r15 = 54
            r13.addIndexOp(r15, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x0197:
            r15 = 4
            int r14 = r14.getExistingIntProp(r15)
            r15 = 48
            r13.addIndexOp(r15, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x01a6:
            r13.addToken(r0)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x01ae:
            double r14 = r14.getDouble()
            int r0 = (int) r14
            double r3 = (double) r0
            int r1 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r1 != 0) goto L_0x01ed
            if (r0 != 0) goto L_0x01ce
            r0 = -51
            r13.addIcode(r0)
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r0 = r0 / r14
            r14 = 0
            int r3 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r3 >= 0) goto L_0x01f6
            r14 = 29
            r13.addToken(r14)
            goto L_0x01f6
        L_0x01ce:
            if (r0 != r6) goto L_0x01d6
            r14 = -52
            r13.addIcode(r14)
            goto L_0x01f6
        L_0x01d6:
            short r14 = (short) r0
            if (r14 != r0) goto L_0x01e4
            r14 = -27
            r13.addIcode(r14)
            r14 = r0 & r12
            r13.addUint16(r14)
            goto L_0x01f6
        L_0x01e4:
            r14 = -28
            r13.addIcode(r14)
            r13.addInt(r0)
            goto L_0x01f6
        L_0x01ed:
            int r14 = r13.getDoubleIndex(r14)
            r15 = 40
            r13.addIndexOp(r15, r14)
        L_0x01f6:
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x01fb:
            java.lang.String r14 = r14.getString()
            r13.addStringOp(r0, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x0207:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r14 = r1.getNext()
            r13.visitExpression(r14, r7)
            org.mozilla.javascript.Node r14 = r14.getNext()
            r15 = 140(0x8c, float:1.96E-43)
            r1 = -2
            if (r0 != r15) goto L_0x022b
            r13.addIcode(r1)
            r13.stackChange(r9)
            r15 = 36
            r13.addToken(r15)
            r13.stackChange(r5)
            r13.stackChange(r5)
        L_0x022b:
            r13.visitExpression(r14, r7)
            r14 = 37
            r13.addToken(r14)
            r13.stackChange(r1)
            goto L_0x03ff
        L_0x0238:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r14 = r1.getNext()
            java.lang.String r15 = r14.getString()
            org.mozilla.javascript.Node r14 = r14.getNext()
            r1 = 139(0x8b, float:1.95E-43)
            if (r0 != r1) goto L_0x0259
            r13.addIcode(r5)
            r13.stackChange(r6)
            r0 = 33
            r13.addStringOp(r0, r15)
            r13.stackChange(r5)
        L_0x0259:
            r13.visitExpression(r14, r7)
            r14 = 35
            r13.addStringOp(r14, r15)
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x0266:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r14 = r1.getNext()
            java.lang.String r14 = r14.getString()
            r13.addStringOp(r0, r14)
            goto L_0x03ff
        L_0x0276:
            int r14 = r1.getType()
            r15 = 49
            if (r14 != r15) goto L_0x0280
            r14 = 1
            goto L_0x0281
        L_0x0280:
            r14 = 0
        L_0x0281:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            if (r14 == 0) goto L_0x0291
            r13.addIcode(r7)
            goto L_0x0296
        L_0x0291:
            r14 = 31
            r13.addToken(r14)
        L_0x0296:
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x029b:
            r3 = 30
            if (r0 != r3) goto L_0x02a3
            r13.visitExpression(r1, r7)
            goto L_0x02a6
        L_0x02a3:
            r13.generateCallFunAndThis(r1)
        L_0x02a6:
            r4 = 0
        L_0x02a7:
            org.mozilla.javascript.Node r1 = r1.getNext()
            if (r1 == 0) goto L_0x02b3
            r13.visitExpression(r1, r7)
            int r4 = r4 + 1
            goto L_0x02a7
        L_0x02b3:
            r1 = 10
            int r14 = r14.getIntProp(r1, r7)
            if (r14 == 0) goto L_0x02d0
            r15 = -21
            r13.addIndexOp(r15, r4)
            r13.addUint8(r14)
            if (r0 != r3) goto L_0x02c6
            r7 = 1
        L_0x02c6:
            r13.addUint8(r7)
            int r14 = r13.lineNumber
            r14 = r14 & r12
            r13.addUint16(r14)
            goto L_0x02eb
        L_0x02d0:
            r14 = 38
            if (r0 != r14) goto L_0x02e8
            r14 = r15 & 1
            if (r14 == 0) goto L_0x02e8
            org.mozilla.javascript.CompilerEnvirons r14 = r13.compilerEnv
            boolean r14 = r14.isGenerateDebugInfo()
            if (r14 != 0) goto L_0x02e8
            boolean r14 = r13.itsInTryFlag
            if (r14 != 0) goto L_0x02e8
            r14 = -55
            r0 = -55
        L_0x02e8:
            r13.addIndexOp(r0, r4)
        L_0x02eb:
            if (r0 != r3) goto L_0x02f2
            int r14 = -r4
            r13.stackChange(r14)
            goto L_0x02f7
        L_0x02f2:
            int r14 = -1 - r4
            r13.stackChange(r14)
        L_0x02f7:
            org.mozilla.javascript.InterpreterData r14 = r13.itsData
            int r14 = r14.itsMaxCalleeArgs
            if (r4 <= r14) goto L_0x03ff
            org.mozilla.javascript.InterpreterData r14 = r13.itsData
            r14.itsMaxCalleeArgs = r4
            goto L_0x03ff
        L_0x0303:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r14 = r1.getNext()
            r13.visitExpression(r14, r7)
            r13.addToken(r0)
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x0315:
            java.lang.String r14 = r1.getString()
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            r13.addStringOp(r0, r14)
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x032b:
            org.mozilla.javascript.Node r14 = r14.getFirstChild()
            org.mozilla.javascript.Node r15 = r14.getNext()
            org.mozilla.javascript.Node r14 = r14.getFirstChild()
            r13.visitExpression(r14, r7)
            r13.addToken(r9)
            r13.stackChange(r5)
            org.mozilla.javascript.Node r14 = r15.getFirstChild()
            r13.visitExpression(r14, r7)
            r14 = 3
            r13.addToken(r14)
            goto L_0x03ff
        L_0x034d:
            r13.updateLineNumber(r14)
            r13.visitExpression(r1, r7)
            r14 = -53
            r13.addIcode(r14)
            r13.stackChange(r5)
            int r14 = r13.iCodeTop
            org.mozilla.javascript.Node r15 = r1.getNext()
            r13.visitExpression(r15, r7)
            r15 = -54
            r13.addBackwardGoto(r15, r14)
            goto L_0x03ff
        L_0x036b:
            r13.visitExpression(r1, r7)
            org.mozilla.javascript.Node r14 = r1.getNext()
            if (r0 != r11) goto L_0x0382
            r13.addIcode(r5)
            r13.stackChange(r6)
            r15 = 67
            r13.addToken(r15)
            r13.stackChange(r5)
        L_0x0382:
            r13.visitExpression(r14, r7)
            r14 = 68
            r13.addToken(r14)
            r13.stackChange(r5)
            goto L_0x03ff
        L_0x038f:
            r13.visitExpression(r1, r7)
            if (r0 != r10) goto L_0x039b
            r13.addIcode(r4)
            r13.addIcode(r3)
            goto L_0x03ff
        L_0x039b:
            r13.addToken(r0)
            goto L_0x03ff
        L_0x039f:
            int r14 = r14.getExistingIntProp(r6)
            org.mozilla.javascript.ast.ScriptNode r15 = r13.scriptOrFn
            org.mozilla.javascript.ast.FunctionNode r15 = r15.getFunctionNode(r14)
            int r15 = r15.getFunctionType()
            if (r15 != r9) goto L_0x03b8
            r15 = -19
            r13.addIndexOp(r15, r14)
            r13.stackChange(r6)
            goto L_0x03ff
        L_0x03b8:
            java.lang.RuntimeException r14 = org.mozilla.javascript.Kit.codeBug()
            throw r14
        L_0x03bd:
            org.mozilla.javascript.Node r14 = r1.getNext()
            org.mozilla.javascript.Node r0 = r14.getNext()
            r13.visitExpression(r1, r7)
            int r1 = r13.iCodeTop
            r13.addGotoOp(r8)
            r13.stackChange(r5)
            r15 = r15 & r6
            r13.visitExpression(r14, r15)
            int r14 = r13.iCodeTop
            r3 = 5
            r13.addGotoOp(r3)
            r13.resolveForwardGoto(r1)
            r13.stackDepth = r2
            r13.visitExpression(r0, r15)
            r13.resolveForwardGoto(r14)
            goto L_0x03ff
        L_0x03e6:
            org.mozilla.javascript.Node r14 = r14.getLastChild()
        L_0x03ea:
            if (r1 == r14) goto L_0x03fa
            r13.visitExpression(r1, r7)
            r13.addIcode(r4)
            r13.stackChange(r5)
            org.mozilla.javascript.Node r1 = r1.getNext()
            goto L_0x03ea
        L_0x03fa:
            r14 = r15 & 1
            r13.visitExpression(r1, r14)     // Catch:{ all -> 0x0408 }
        L_0x03ff:
            int r2 = r2 + r6
            int r14 = r13.stackDepth
            if (r2 == r14) goto L_0x0407
            org.mozilla.javascript.Kit.codeBug()
        L_0x0407:
            return
        L_0x0408:
            r14 = move-exception
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.CodeGenerator.visitExpression(org.mozilla.javascript.Node, int):void");
    }

    private void generateCallFunAndThis(Node node) {
        int type = node.getType();
        if (type == 33 || type == 36) {
            Node firstChild = node.getFirstChild();
            visitExpression(firstChild, 0);
            Node next = firstChild.getNext();
            if (type == 33) {
                addStringOp(-16, next.getString());
                stackChange(1);
                return;
            }
            visitExpression(next, 0);
            addIcode(-17);
        } else if (type != 39) {
            visitExpression(node, 0);
            addIcode(-18);
            stackChange(1);
        } else {
            addStringOp(-15, node.getString());
            stackChange(2);
        }
    }

    private void visitIncDec(Node node, Node node2) {
        int existingIntProp = node.getExistingIntProp(13);
        int type = node2.getType();
        if (type == 33) {
            Node firstChild = node2.getFirstChild();
            visitExpression(firstChild, 0);
            addStringOp(-9, firstChild.getNext().getString());
            addUint8(existingIntProp);
        } else if (type == 36) {
            Node firstChild2 = node2.getFirstChild();
            visitExpression(firstChild2, 0);
            visitExpression(firstChild2.getNext(), 0);
            addIcode(-10);
            addUint8(existingIntProp);
            stackChange(-1);
        } else if (type == 39) {
            addStringOp(-8, node2.getString());
            addUint8(existingIntProp);
            stackChange(1);
        } else if (type == 55) {
            if (this.itsData.itsNeedsActivation) {
                Kit.codeBug();
            }
            addVarOp(-7, this.scriptOrFn.getIndexForNameNode(node2));
            addUint8(existingIntProp);
            stackChange(1);
        } else if (type == 67) {
            visitExpression(node2.getFirstChild(), 0);
            addIcode(-11);
            addUint8(existingIntProp);
        } else {
            throw badTree(node);
        }
    }

    private void visitLiteral(Node node, Node node2) {
        int i;
        Object[] objArr;
        int type = node.getType();
        if (type == 65) {
            i = 0;
            for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
                i++;
            }
            objArr = null;
        } else if (type == 66) {
            objArr = (Object[]) node.getProp(12);
            i = objArr.length;
        } else {
            throw badTree(node);
        }
        addIndexOp(-29, i);
        stackChange(2);
        while (node2 != null) {
            int type2 = node2.getType();
            if (type2 == 151) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-57);
            } else if (type2 == 152) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-58);
            } else {
                visitExpression(node2, 0);
                addIcode(-30);
            }
            stackChange(-1);
            node2 = node2.getNext();
        }
        if (type == 65) {
            int[] iArr = (int[]) node.getProp(11);
            if (iArr == null) {
                addToken(65);
            } else {
                int size = this.literalIds.size();
                this.literalIds.add(iArr);
                addIndexOp(-31, size);
            }
        } else {
            int size2 = this.literalIds.size();
            this.literalIds.add(objArr);
            addIndexOp(66, size2);
        }
        stackChange(-1);
    }

    private void visitArrayComprehension(Node node, Node node2, Node node3) {
        visitStatement(node2, this.stackDepth);
        visitExpression(node3, 0);
    }

    private int getLocalBlockRef(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId != -1) {
            return labelId;
        }
        int i = this.labelTableTop;
        int[] iArr = this.labelTable;
        if (iArr == null || i == iArr.length) {
            int[] iArr2 = this.labelTable;
            if (iArr2 == null) {
                this.labelTable = new int[32];
            } else {
                int[] iArr3 = new int[(iArr2.length * 2)];
                System.arraycopy(iArr2, 0, iArr3, 0, i);
                this.labelTable = iArr3;
            }
        }
        this.labelTableTop = i + 1;
        this.labelTable[i] = -1;
        node.labelId(i);
        return i;
    }

    private void markTargetLabel(Node node) {
        int targetLabel = getTargetLabel(node);
        if (this.labelTable[targetLabel] != -1) {
            Kit.codeBug();
        }
        this.labelTable[targetLabel] = this.iCodeTop;
    }

    private void addGoto(Node node, int i) {
        int targetLabel = getTargetLabel(node);
        if (targetLabel >= this.labelTableTop) {
            Kit.codeBug();
        }
        int i2 = this.labelTable[targetLabel];
        if (i2 != -1) {
            addBackwardGoto(i, i2);
            return;
        }
        int i3 = this.iCodeTop;
        addGotoOp(i);
        int i4 = this.fixupTableTop;
        long[] jArr = this.fixupTable;
        if (jArr == null || i4 == jArr.length) {
            long[] jArr2 = this.fixupTable;
            if (jArr2 == null) {
                this.fixupTable = new long[40];
            } else {
                long[] jArr3 = new long[(jArr2.length * 2)];
                System.arraycopy(jArr2, 0, jArr3, 0, i4);
                this.fixupTable = jArr3;
            }
        }
        this.fixupTableTop = i4 + 1;
        this.fixupTable[i4] = (((long) targetLabel) << 32) | ((long) i3);
    }

    private void fixLabelGotos() {
        int i = 0;
        while (i < this.fixupTableTop) {
            long j = this.fixupTable[i];
            int i2 = (int) j;
            int i3 = this.labelTable[(int) (j >> 32)];
            if (i3 != -1) {
                resolveGoto(i2, i3);
                i++;
            } else {
                throw Kit.codeBug();
            }
        }
        this.fixupTableTop = 0;
    }

    private void addBackwardGoto(int i, int i2) {
        int i3 = this.iCodeTop;
        if (i3 > i2) {
            addGotoOp(i);
            resolveGoto(i3, i2);
            return;
        }
        throw Kit.codeBug();
    }

    private void resolveForwardGoto(int i) {
        int i2 = this.iCodeTop;
        if (i2 >= i + 3) {
            resolveGoto(i, i2);
            return;
        }
        throw Kit.codeBug();
    }

    private void resolveGoto(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0 || i3 > 2) {
            int i4 = i + 1;
            if (i3 != ((short) i3)) {
                if (this.itsData.longJumps == null) {
                    this.itsData.longJumps = new UintMap();
                }
                this.itsData.longJumps.put(i4, i2);
                i3 = 0;
            }
            byte[] bArr = this.itsData.itsICode;
            bArr[i4] = (byte) (i3 >> 8);
            bArr[i4 + 1] = (byte) i3;
            return;
        }
        throw Kit.codeBug();
    }

    private void addToken(int i) {
        if (Icode.validTokenCode(i)) {
            addUint8(i);
            return;
        }
        throw Kit.codeBug();
    }

    private void addIcode(int i) {
        if (Icode.validIcode(i)) {
            addUint8(i & 255);
            return;
        }
        throw Kit.codeBug();
    }

    private void addUint8(int i) {
        if ((i & InputDeviceCompat.SOURCE_ANY) == 0) {
            byte[] bArr = this.itsData.itsICode;
            int i2 = this.iCodeTop;
            if (i2 == bArr.length) {
                bArr = increaseICodeCapacity(1);
            }
            bArr[i2] = (byte) i;
            this.iCodeTop = i2 + 1;
            return;
        }
        throw Kit.codeBug();
    }

    private void addUint16(int i) {
        if ((-65536 & i) == 0) {
            byte[] bArr = this.itsData.itsICode;
            int i2 = this.iCodeTop;
            int i3 = i2 + 2;
            if (i3 > bArr.length) {
                bArr = increaseICodeCapacity(2);
            }
            bArr[i2] = (byte) (i >>> 8);
            bArr[i2 + 1] = (byte) i;
            this.iCodeTop = i3;
            return;
        }
        throw Kit.codeBug();
    }

    private void addInt(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        int i3 = i2 + 4;
        if (i3 > bArr.length) {
            bArr = increaseICodeCapacity(4);
        }
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        this.iCodeTop = i3;
    }

    private int getDoubleIndex(double d) {
        int i = this.doubleTableTop;
        if (i == 0) {
            this.itsData.itsDoubleTable = new double[64];
        } else if (this.itsData.itsDoubleTable.length == i) {
            double[] dArr = new double[(i * 2)];
            System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, i);
            this.itsData.itsDoubleTable = dArr;
        }
        this.itsData.itsDoubleTable[i] = d;
        this.doubleTableTop = i + 1;
        return i;
    }

    private void addGotoOp(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 3 > bArr.length) {
            bArr = increaseICodeCapacity(3);
        }
        bArr[i2] = (byte) i;
        this.iCodeTop = i2 + 1 + 2;
    }

    private void addVarOp(int i, int i2) {
        if (i != -7) {
            if (i != 156) {
                if (i != 55 && i != 56) {
                    throw Kit.codeBug();
                } else if (i2 < 128) {
                    addIcode(i == 55 ? -48 : -49);
                    addUint8(i2);
                    return;
                }
            } else if (i2 < 128) {
                addIcode(-61);
                addUint8(i2);
                return;
            } else {
                addIndexOp(-60, i2);
                return;
            }
        }
        addIndexOp(i, i2);
    }

    private void addStringOp(int i, String str) {
        addStringPrefix(str);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addIndexOp(int i, int i2) {
        addIndexPrefix(i2);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addStringPrefix(String str) {
        int i = this.strings.get(str, -1);
        if (i == -1) {
            i = this.strings.size();
            this.strings.put(str, i);
        }
        if (i < 4) {
            addIcode(-41 - i);
        } else if (i <= 255) {
            addIcode(-45);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-46);
            addUint16(i);
        } else {
            addIcode(-47);
            addInt(i);
        }
    }

    private void addIndexPrefix(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        if (i < 6) {
            addIcode(-32 - i);
        } else if (i <= 255) {
            addIcode(-38);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-39);
            addUint16(i);
        } else {
            addIcode(-40);
            addInt(i);
        }
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [boolean] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addExceptionHandler(int r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            r4 = this;
            int r0 = r4.exceptionTableTop
            org.mozilla.javascript.InterpreterData r1 = r4.itsData
            int[] r1 = r1.itsExceptionTable
            if (r1 != 0) goto L_0x0016
            if (r0 == 0) goto L_0x000d
            org.mozilla.javascript.Kit.codeBug()
        L_0x000d:
            r1 = 12
            int[] r1 = new int[r1]
            org.mozilla.javascript.InterpreterData r2 = r4.itsData
            r2.itsExceptionTable = r1
            goto L_0x002a
        L_0x0016:
            int r2 = r1.length
            if (r2 != r0) goto L_0x002a
            int r1 = r1.length
            int r1 = r1 * 2
            int[] r1 = new int[r1]
            org.mozilla.javascript.InterpreterData r2 = r4.itsData
            int[] r2 = r2.itsExceptionTable
            r3 = 0
            java.lang.System.arraycopy(r2, r3, r1, r3, r0)
            org.mozilla.javascript.InterpreterData r2 = r4.itsData
            r2.itsExceptionTable = r1
        L_0x002a:
            int r2 = r0 + 0
            r1[r2] = r5
            int r5 = r0 + 1
            r1[r5] = r6
            int r5 = r0 + 2
            r1[r5] = r7
            int r5 = r0 + 3
            r1[r5] = r8
            int r5 = r0 + 4
            r1[r5] = r9
            int r5 = r0 + 5
            r1[r5] = r10
            int r0 = r0 + 6
            r4.exceptionTableTop = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.CodeGenerator.addExceptionHandler(int, int, int, boolean, int, int):void");
    }

    private byte[] increaseICodeCapacity(int i) {
        int length = this.itsData.itsICode.length;
        int i2 = this.iCodeTop;
        int i3 = i + i2;
        if (i3 > length) {
            int i4 = length * 2;
            if (i3 <= i4) {
                i3 = i4;
            }
            byte[] bArr = new byte[i3];
            System.arraycopy(this.itsData.itsICode, 0, bArr, 0, i2);
            this.itsData.itsICode = bArr;
            return bArr;
        }
        throw Kit.codeBug();
    }

    private void stackChange(int i) {
        if (i <= 0) {
            this.stackDepth += i;
            return;
        }
        int i2 = this.stackDepth + i;
        if (i2 > this.itsData.itsMaxStack) {
            this.itsData.itsMaxStack = i2;
        }
        this.stackDepth = i2;
    }

    private int allocLocal() {
        int i = this.localTop;
        int i2 = i + 1;
        this.localTop = i2;
        if (i2 > this.itsData.itsMaxLocals) {
            this.itsData.itsMaxLocals = this.localTop;
        }
        return i;
    }

    private void releaseLocal(int i) {
        int i2 = this.localTop - 1;
        this.localTop = i2;
        if (i != i2) {
            Kit.codeBug();
        }
    }
}
