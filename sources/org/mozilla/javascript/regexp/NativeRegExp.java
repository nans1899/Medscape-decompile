package org.mozilla.javascript.regexp;

import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.slideshow.SlideshowPageFragment;
import net.bytebuddy.asm.Advice;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

public class NativeRegExp extends IdScriptableObject implements Function {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_compile = 1;
    private static final int Id_exec = 4;
    private static final int Id_global = 3;
    private static final int Id_ignoreCase = 4;
    private static final int Id_lastIndex = 1;
    private static final int Id_multiline = 5;
    private static final int Id_prefix = 6;
    private static final int Id_source = 2;
    private static final int Id_test = 5;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_MULTILINE = 4;
    public static final int MATCH = 1;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    public static final int PREFIX = 2;
    private static final Object REGEXP_TAG = new Object();
    private static final byte REOP_ALNUM = 9;
    private static final byte REOP_ALT = 31;
    private static final byte REOP_ALTPREREQ = 53;
    private static final byte REOP_ALTPREREQ2 = 55;
    private static final byte REOP_ALTPREREQi = 54;
    private static final byte REOP_ASSERT = 41;
    private static final byte REOP_ASSERTNOTTEST = 44;
    private static final byte REOP_ASSERTTEST = 43;
    private static final byte REOP_ASSERT_NOT = 42;
    private static final byte REOP_BACKREF = 13;
    private static final byte REOP_BOL = 2;
    private static final byte REOP_CLASS = 22;
    private static final byte REOP_DIGIT = 7;
    private static final byte REOP_DOT = 6;
    private static final byte REOP_EMPTY = 1;
    private static final byte REOP_END = 57;
    private static final byte REOP_ENDCHILD = 49;
    private static final byte REOP_EOL = 3;
    private static final byte REOP_FLAT = 14;
    private static final byte REOP_FLAT1 = 15;
    private static final byte REOP_FLAT1i = 17;
    private static final byte REOP_FLATi = 16;
    private static final byte REOP_JUMP = 32;
    private static final byte REOP_LPAREN = 29;
    private static final byte REOP_MINIMALOPT = 47;
    private static final byte REOP_MINIMALPLUS = 46;
    private static final byte REOP_MINIMALQUANT = 48;
    private static final byte REOP_MINIMALREPEAT = 52;
    private static final byte REOP_MINIMALSTAR = 45;
    private static final byte REOP_NCLASS = 23;
    private static final byte REOP_NONALNUM = 10;
    private static final byte REOP_NONDIGIT = 8;
    private static final byte REOP_NONSPACE = 12;
    private static final byte REOP_OPT = 28;
    private static final byte REOP_PLUS = 27;
    private static final byte REOP_QUANT = 25;
    private static final byte REOP_REPEAT = 51;
    private static final byte REOP_RPAREN = 30;
    private static final byte REOP_SIMPLE_END = 23;
    private static final byte REOP_SIMPLE_START = 1;
    private static final byte REOP_SPACE = 11;
    private static final byte REOP_STAR = 26;
    private static final byte REOP_UCFLAT1 = 18;
    private static final byte REOP_UCFLAT1i = 19;
    private static final byte REOP_WBDRY = 4;
    private static final byte REOP_WNONBDRY = 5;
    public static final int TEST = 0;
    private static final boolean debug = false;
    static final long serialVersionUID = 4965263491464903264L;
    double lastIndex;
    private RECompiled re;

    private static boolean isControlLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean reopIsSimple(int i) {
        return i >= 1 && i <= 23;
    }

    private static int toASCIIHexDigit(int i) {
        if (i < 48) {
            return -1;
        }
        if (i <= 57) {
            return i - 48;
        }
        int i2 = i | 32;
        if (97 > i2 || i2 > 102) {
            return -1;
        }
        return (i2 - 97) + 10;
    }

    public String getClassName() {
        return "RegExp";
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return 5;
    }

    public String getTypeOf() {
        return SlideshowPageFragment.ARG_OBJECT;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeRegExp nativeRegExp = new NativeRegExp();
        nativeRegExp.re = compileRE(context, "", (String) null, false);
        nativeRegExp.activatePrototypeMap(6);
        nativeRegExp.setParentScope(scriptable);
        nativeRegExp.setPrototype(getObjectPrototype(scriptable));
        NativeRegExpCtor nativeRegExpCtor = new NativeRegExpCtor();
        nativeRegExp.defineProperty("constructor", (Object) nativeRegExpCtor, 2);
        ScriptRuntime.setFunctionProtoAndParent(nativeRegExpCtor, scriptable);
        nativeRegExpCtor.setImmunePrototypeProperty(nativeRegExp);
        if (z) {
            nativeRegExp.sealObject();
            nativeRegExpCtor.sealObject();
        }
        defineProperty(scriptable, "RegExp", nativeRegExpCtor, 2);
    }

    NativeRegExp(Scriptable scriptable, RECompiled rECompiled) {
        this.re = rECompiled;
        this.lastIndex = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        ScriptRuntime.setBuiltinProtoAndParent(this, scriptable, TopLevel.Builtins.RegExp);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return execSub(context, scriptable, objArr, 1);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        return (Scriptable) execSub(context, scriptable, objArr, 1);
    }

    /* access modifiers changed from: package-private */
    public Scriptable compile(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length <= 0 || !(objArr[0] instanceof NativeRegExp)) {
            this.re = compileRE(context, objArr.length == 0 ? "" : escapeRegExp(objArr[0]), (objArr.length <= 1 || objArr[1] == Undefined.instance) ? null : ScriptRuntime.toString(objArr[1]), false);
            this.lastIndex = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        } else if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            NativeRegExp nativeRegExp = objArr[0];
            this.re = nativeRegExp.re;
            this.lastIndex = nativeRegExp.lastIndex;
            return this;
        } else {
            throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        if (this.re.source.length != 0) {
            sb.append(this.re.source);
        } else {
            sb.append("(?:)");
        }
        sb.append('/');
        if ((this.re.flags & 1) != 0) {
            sb.append('g');
        }
        if ((this.re.flags & 2) != 0) {
            sb.append('i');
        }
        if ((this.re.flags & 4) != 0) {
            sb.append(Advice.OffsetMapping.ForOrigin.Renderer.ForMethodName.SYMBOL);
        }
        return sb.toString();
    }

    NativeRegExp() {
    }

    private static RegExpImpl getImpl(Context context) {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(context);
    }

    private static String escapeRegExp(Object obj) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        StringBuilder sb = null;
        int i = 0;
        for (int indexOf = scriptRuntime.indexOf(47); indexOf > -1; indexOf = scriptRuntime.indexOf(47, indexOf + 1)) {
            if (indexOf == i || scriptRuntime.charAt(indexOf - 1) != '\\') {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(scriptRuntime, i, indexOf);
                sb.append("\\/");
                i = indexOf + 1;
            }
        }
        if (sb == null) {
            return scriptRuntime;
        }
        sb.append(scriptRuntime, i, scriptRuntime.length());
        return sb.toString();
    }

    private Object execSub(Context context, Scriptable scriptable, Object[] objArr, int i) {
        String str;
        RegExpImpl impl = getImpl(context);
        if (objArr.length == 0) {
            str = impl.input;
            if (str == null) {
                reportError("msg.no.re.input.for", toString());
            }
        } else {
            str = ScriptRuntime.toString(objArr[0]);
        }
        String str2 = str;
        int i2 = this.re.flags & 1;
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        double d2 = i2 != 0 ? this.lastIndex : 0.0d;
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || ((double) str2.length()) < d2) {
            this.lastIndex = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return null;
        }
        int[] iArr = {(int) d2};
        Object executeRegExp = executeRegExp(context, scriptable, impl, str2, iArr, i);
        if ((this.re.flags & 1) == 0) {
            return executeRegExp;
        }
        if (!(executeRegExp == null || executeRegExp == Undefined.instance)) {
            d = (double) iArr[0];
        }
        this.lastIndex = d;
        return executeRegExp;
    }

    static RECompiled compileRE(Context context, String str, String str2, boolean z) {
        int i;
        RECompiled rECompiled = new RECompiled(str);
        int length = str.length();
        if (str2 != null) {
            i = 0;
            for (int i2 = 0; i2 < str2.length(); i2++) {
                char charAt = str2.charAt(i2);
                if (charAt == 'g') {
                    i |= 1;
                } else if (charAt == 'i') {
                    i |= 2;
                } else if (charAt == 'm') {
                    i |= 4;
                } else {
                    reportError("msg.invalid.re.flag", String.valueOf(charAt));
                }
            }
        } else {
            i = 0;
        }
        rECompiled.flags = i;
        CompilerState compilerState = new CompilerState(context, rECompiled.source, length, i);
        if (z && length > 0) {
            compilerState.result = new RENode((byte) 14);
            compilerState.result.chr = compilerState.cpbegin[0];
            compilerState.result.length = length;
            compilerState.result.flatIndex = 0;
            compilerState.progLength += 5;
        } else if (!parseDisjunction(compilerState)) {
            return null;
        }
        rECompiled.program = new byte[(compilerState.progLength + 1)];
        if (compilerState.classCount != 0) {
            rECompiled.classList = new RECharSet[compilerState.classCount];
            rECompiled.classCount = compilerState.classCount;
        }
        rECompiled.program[emitREBytecode(compilerState, rECompiled, 0, compilerState.result)] = REOP_END;
        rECompiled.parenCount = compilerState.parenCount;
        byte b = rECompiled.program[0];
        if (b == 2) {
            rECompiled.anchorCh = -2;
        } else if (b != 31) {
            switch (b) {
                case 14:
                case 16:
                    rECompiled.anchorCh = rECompiled.source[getIndex(rECompiled.program, 1)];
                    break;
                case 15:
                case 17:
                    rECompiled.anchorCh = (char) (rECompiled.program[1] & 255);
                    break;
                case 18:
                case 19:
                    rECompiled.anchorCh = (char) getIndex(rECompiled.program, 1);
                    break;
            }
        } else {
            RENode rENode = compilerState.result;
            if (rENode.kid.op == 2 && rENode.kid2.op == 2) {
                rECompiled.anchorCh = -2;
            }
        }
        return rECompiled;
    }

    private static boolean isWord(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || isDigit(c) || c == '_';
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int i) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(i);
    }

    private static char upcase(char c) {
        if (c < 128) {
            return ('a' > c || c > 'z') ? c : (char) (c - ' ');
        }
        char upperCase = Character.toUpperCase(c);
        return upperCase < 128 ? c : upperCase;
    }

    private static char downcase(char c) {
        if (c < 128) {
            return ('A' > c || c > 'Z') ? c : (char) (c + ' ');
        }
        char lowerCase = Character.toLowerCase(c);
        return lowerCase < 128 ? c : lowerCase;
    }

    private static boolean parseDisjunction(CompilerState compilerState) {
        if (!parseAlternative(compilerState)) {
            return false;
        }
        char[] cArr = compilerState.cpbegin;
        int i = compilerState.cp;
        if (i != cArr.length && cArr[i] == '|') {
            compilerState.cp++;
            RENode rENode = new RENode((byte) 31);
            rENode.kid = compilerState.result;
            if (!parseDisjunction(compilerState)) {
                return false;
            }
            rENode.kid2 = compilerState.result;
            compilerState.result = rENode;
            if (rENode.kid.op == 14 && rENode.kid2.op == 14) {
                rENode.op = (compilerState.flags & 2) == 0 ? REOP_ALTPREREQ : REOP_ALTPREREQi;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.chr;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == 22 && rENode.kid.index < 256 && rENode.kid2.op == 14 && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid2.chr;
                rENode.index = rENode.kid.index;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == 14 && rENode.kid2.op == 22 && rENode.kid2.index < 256 && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.index;
                compilerState.progLength += 13;
            } else {
                compilerState.progLength += 9;
            }
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState compilerState) {
        char[] cArr = compilerState.cpbegin;
        RENode rENode = null;
        RENode rENode2 = null;
        while (compilerState.cp != compilerState.cpend && cArr[compilerState.cp] != '|' && (compilerState.parenNesting == 0 || cArr[compilerState.cp] != ')')) {
            if (!parseTerm(compilerState)) {
                return false;
            }
            if (rENode == null) {
                rENode = compilerState.result;
                rENode2 = rENode;
            } else {
                rENode2.next = compilerState.result;
            }
            while (rENode2.next != null) {
                rENode2 = rENode2.next;
            }
        }
        if (rENode == null) {
            compilerState.result = new RENode((byte) 1);
        } else {
            compilerState.result = rENode;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r18v0, types: [char[]] */
    /* JADX WARNING: type inference failed for: r5v0, types: [char] */
    /* JADX WARNING: type inference failed for: r8v0, types: [char] */
    /* JADX WARNING: type inference failed for: r8v3, types: [char] */
    /* JADX WARNING: type inference failed for: r1v7, types: [int, char] */
    /* JADX WARNING: type inference failed for: r12v7, types: [int, char] */
    /* JADX WARNING: type inference failed for: r12v9, types: [int, char] */
    /* JADX WARNING: type inference failed for: r1v14, types: [char] */
    /* JADX WARNING: type inference failed for: r8v9, types: [char] */
    /* JADX WARNING: type inference failed for: r8v11, types: [int, char] */
    /* JADX WARNING: type inference failed for: r12v21, types: [char] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        r12 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        r13 = 0;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005d, code lost:
        if (r13 >= r1) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005f, code lost:
        if (r8 >= r2) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
        r15 = r8 + 1;
        r14 = org.mozilla.javascript.Kit.xDigitToInt(r18[r8], r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0069, code lost:
        if (r14 >= 0) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006b, code lost:
        r8 = r15 - (r13 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0070, code lost:
        r13 = r13 + 1;
        r8 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0074, code lost:
        r12 = r14;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r1v7, types: [int, char] */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState r16, org.mozilla.javascript.regexp.RENode r17, char[] r18, int r19, int r20) {
        /*
            r0 = r17
            r1 = r19
            r2 = r20
            r3 = 0
            r0.bmsize = r3
            r4 = 1
            r0.sense = r4
            if (r1 != r2) goto L_0x000f
            return r4
        L_0x000f:
            char r5 = r18[r1]
            r6 = 94
            if (r5 != r6) goto L_0x0019
            int r1 = r1 + 1
            r0.sense = r3
        L_0x0019:
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x001c:
            if (r1 == r2) goto L_0x010b
            char r8 = r18[r1]
            r9 = 2
            java.lang.String r10 = ""
            java.lang.String r11 = "msg.bad.range"
            r12 = 92
            if (r8 == r12) goto L_0x0030
            int r8 = r1 + 1
            char r12 = r18[r1]
        L_0x002d:
            r1 = r8
            goto L_0x00cc
        L_0x0030:
            int r1 = r1 + 1
            int r8 = r1 + 1
            char r1 = r18[r1]
            r13 = 68
            if (r1 == r13) goto L_0x0100
            r13 = 83
            if (r1 == r13) goto L_0x0100
            r13 = 87
            if (r1 == r13) goto L_0x0100
            r13 = 102(0x66, float:1.43E-43)
            if (r1 == r13) goto L_0x00c8
            r13 = 110(0x6e, float:1.54E-43)
            if (r1 == r13) goto L_0x00c4
            switch(r1) {
                case 48: goto L_0x009a;
                case 49: goto L_0x009a;
                case 50: goto L_0x009a;
                case 51: goto L_0x009a;
                case 52: goto L_0x009a;
                case 53: goto L_0x009a;
                case 54: goto L_0x009a;
                case 55: goto L_0x009a;
                default: goto L_0x004d;
            }
        L_0x004d:
            switch(r1) {
                case 98: goto L_0x0097;
                case 99: goto L_0x0085;
                case 100: goto L_0x007c;
                default: goto L_0x0050;
            }
        L_0x0050:
            switch(r1) {
                case 114: goto L_0x0079;
                case 115: goto L_0x0100;
                case 116: goto L_0x0076;
                case 117: goto L_0x005a;
                case 118: goto L_0x0057;
                case 119: goto L_0x0100;
                case 120: goto L_0x0055;
                default: goto L_0x0053;
            }
        L_0x0053:
            r12 = r1
            goto L_0x002d
        L_0x0055:
            r1 = 2
            goto L_0x005b
        L_0x0057:
            r12 = 11
            goto L_0x002d
        L_0x005a:
            r1 = 4
        L_0x005b:
            r13 = 0
            r14 = 0
        L_0x005d:
            if (r13 >= r1) goto L_0x0074
            if (r8 >= r2) goto L_0x0074
            int r15 = r8 + 1
            char r8 = r18[r8]
            int r14 = org.mozilla.javascript.Kit.xDigitToInt(r8, r14)
            if (r14 >= 0) goto L_0x0070
            int r13 = r13 + 1
            int r8 = r15 - r13
            goto L_0x002d
        L_0x0070:
            int r13 = r13 + 1
            r8 = r15
            goto L_0x005d
        L_0x0074:
            r12 = r14
            goto L_0x002d
        L_0x0076:
            r12 = 9
            goto L_0x002d
        L_0x0079:
            r12 = 13
            goto L_0x002d
        L_0x007c:
            if (r6 == 0) goto L_0x0082
            reportError(r11, r10)
            return r3
        L_0x0082:
            r12 = 57
            goto L_0x002d
        L_0x0085:
            if (r8 >= r2) goto L_0x0094
            char r1 = r18[r8]
            boolean r1 = isControlLetter(r1)
            if (r1 == 0) goto L_0x0094
            int r1 = r8 + 1
            char r8 = r18[r8]
            goto L_0x00cc
        L_0x0094:
            int r1 = r8 + -1
            goto L_0x00cc
        L_0x0097:
            r12 = 8
            goto L_0x002d
        L_0x009a:
            int r1 = r1 + -48
            char r12 = r18[r8]
            r13 = 48
            if (r13 > r12) goto L_0x0053
            r14 = 55
            if (r12 > r14) goto L_0x0053
            int r8 = r8 + 1
            int r1 = r1 * 8
            int r12 = r12 + -48
            int r1 = r1 + r12
            char r12 = r18[r8]
            if (r13 > r12) goto L_0x0053
            if (r12 > r14) goto L_0x0053
            int r8 = r8 + 1
            int r13 = r1 * 8
            int r12 = r12 + -48
            int r13 = r13 + r12
            r12 = 255(0xff, float:3.57E-43)
            if (r13 > r12) goto L_0x00c1
            r12 = r13
            goto L_0x002d
        L_0x00c1:
            int r8 = r8 + -1
            goto L_0x0053
        L_0x00c4:
            r12 = 10
            goto L_0x002d
        L_0x00c8:
            r12 = 12
            goto L_0x002d
        L_0x00cc:
            if (r6 == 0) goto L_0x00d6
            if (r7 <= r12) goto L_0x00d4
            reportError(r11, r10)
            return r3
        L_0x00d4:
            r6 = 0
            goto L_0x00e6
        L_0x00d6:
            int r8 = r2 + -1
            if (r1 >= r8) goto L_0x00e6
            char r8 = r18[r1]
            r10 = 45
            if (r8 != r10) goto L_0x00e6
            int r1 = r1 + 1
            char r7 = (char) r12
            r6 = 1
            goto L_0x001c
        L_0x00e6:
            r8 = r16
            int r10 = r8.flags
            r9 = r9 & r10
            if (r9 == 0) goto L_0x00fb
            char r9 = (char) r12
            char r10 = upcase(r9)
            char r9 = downcase(r9)
            if (r10 < r9) goto L_0x00fa
            r12 = r10
            goto L_0x00fb
        L_0x00fa:
            r12 = r9
        L_0x00fb:
            if (r12 <= r5) goto L_0x001c
            r5 = r12
            goto L_0x001c
        L_0x0100:
            if (r6 == 0) goto L_0x0106
            reportError(r11, r10)
            return r3
        L_0x0106:
            r1 = 65536(0x10000, float:9.18355E-41)
            r0.bmsize = r1
            return r4
        L_0x010b:
            int r5 = r5 + r4
            r0.bmsize = r5
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState, org.mozilla.javascript.regexp.RENode, char[], int, int):boolean");
    }

    private static void doFlat(CompilerState compilerState, char c) {
        compilerState.result = new RENode((byte) 14);
        compilerState.result.chr = c;
        compilerState.result.length = 1;
        compilerState.result.flatIndex = -1;
        compilerState.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState compilerState, int i, String str) {
        int i2 = compilerState.cp;
        char[] cArr = compilerState.cpbegin;
        int i3 = c - '0';
        boolean z = false;
        while (compilerState.cp != compilerState.cpend) {
            char c2 = cArr[compilerState.cp];
            if (!isDigit(c2)) {
                break;
            }
            if (!z) {
                int i4 = c2 - '0';
                if (i3 < (i - i4) / 10) {
                    i3 = (i3 * 10) + i4;
                } else {
                    i3 = i;
                    z = true;
                }
            }
            compilerState.cp++;
        }
        if (z) {
            reportError(str, String.valueOf(cArr, i2, compilerState.cp - i2));
        }
        return i3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v66, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v67, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v108, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v109, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v110, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v111, resolved type: char} */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x017f, code lost:
        r4 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03d0  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x041a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x041b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean parseTerm(org.mozilla.javascript.regexp.CompilerState r16) {
        /*
            r0 = r16
            char[] r1 = r0.cpbegin
            int r2 = r0.cp
            int r3 = r2 + 1
            r0.cp = r3
            char r2 = r1[r2]
            int r3 = r0.parenCount
            r4 = 36
            r5 = 3
            r6 = 1
            if (r2 == r4) goto L_0x044c
            r4 = 46
            r7 = 42
            r8 = 6
            r10 = 65535(0xffff, float:9.1834E-41)
            r11 = 63
            r13 = 0
            if (r2 == r4) goto L_0x032e
            if (r2 == r11) goto L_0x031f
            r4 = 94
            r14 = 2
            if (r2 == r4) goto L_0x0312
            r4 = 91
            r15 = 92
            java.lang.String r12 = ""
            if (r2 == r4) goto L_0x02b5
            r4 = 14
            r9 = 4
            if (r2 == r15) goto L_0x00e8
            switch(r2) {
                case 40: goto L_0x005b;
                case 41: goto L_0x0055;
                case 42: goto L_0x031f;
                case 43: goto L_0x031f;
                default: goto L_0x0038;
            }
        L_0x0038:
            org.mozilla.javascript.regexp.RENode r8 = new org.mozilla.javascript.regexp.RENode
            r8.<init>(r4)
            r0.result = r8
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.chr = r2
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            r2.length = r6
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            int r4 = r0.cp
            int r4 = r4 - r6
            r2.flatIndex = r4
            int r2 = r0.progLength
            int r2 = r2 + r5
            r0.progLength = r2
            goto L_0x033a
        L_0x0055:
            java.lang.String r0 = "msg.re.unmatched.right.paren"
            reportError(r0, r12)
            return r13
        L_0x005b:
            r2 = 0
            int r4 = r0.cp
            int r4 = r0.cp
            int r4 = r4 + r6
            int r5 = r0.cpend
            if (r4 >= r5) goto L_0x009f
            int r4 = r0.cp
            char r4 = r1[r4]
            if (r4 != r11) goto L_0x009f
            int r4 = r0.cp
            int r4 = r4 + r6
            char r4 = r1[r4]
            r5 = 61
            if (r4 == r5) goto L_0x007c
            r15 = 33
            if (r4 == r15) goto L_0x007c
            r15 = 58
            if (r4 != r15) goto L_0x009f
        L_0x007c:
            int r8 = r0.cp
            int r8 = r8 + r14
            r0.cp = r8
            if (r4 != r5) goto L_0x0090
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 41
            r2.<init>(r4)
            int r4 = r0.progLength
            int r4 = r4 + r9
            r0.progLength = r4
            goto L_0x00b3
        L_0x0090:
            r5 = 33
            if (r4 != r5) goto L_0x00b3
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r2.<init>(r7)
            int r4 = r0.progLength
            int r4 = r4 + r9
            r0.progLength = r4
            goto L_0x00b3
        L_0x009f:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 29
            r2.<init>(r4)
            int r4 = r0.progLength
            int r4 = r4 + r8
            r0.progLength = r4
            int r4 = r0.parenCount
            int r5 = r4 + 1
            r0.parenCount = r5
            r2.parenIndex = r4
        L_0x00b3:
            int r4 = r0.parenNesting
            int r4 = r4 + r6
            r0.parenNesting = r4
            boolean r4 = parseDisjunction(r16)
            if (r4 != 0) goto L_0x00bf
            return r13
        L_0x00bf:
            int r4 = r0.cp
            int r5 = r0.cpend
            if (r4 == r5) goto L_0x00e2
            int r4 = r0.cp
            char r4 = r1[r4]
            r5 = 41
            if (r4 == r5) goto L_0x00ce
            goto L_0x00e2
        L_0x00ce:
            int r4 = r0.cp
            int r4 = r4 + r6
            r0.cp = r4
            int r4 = r0.parenNesting
            int r4 = r4 - r6
            r0.parenNesting = r4
            if (r2 == 0) goto L_0x033a
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r2.kid = r4
            r0.result = r2
            goto L_0x033a
        L_0x00e2:
            java.lang.String r0 = "msg.unterm.paren"
            reportError(r0, r12)
            return r13
        L_0x00e8:
            int r2 = r0.cp
            int r8 = r0.cpend
            if (r2 >= r8) goto L_0x02af
            int r2 = r0.cp
            int r8 = r2 + 1
            r0.cp = r8
            char r2 = r1[r2]
            r8 = 66
            if (r2 == r8) goto L_0x02a1
            r8 = 68
            if (r2 == r8) goto L_0x0291
            r8 = 83
            if (r2 == r8) goto L_0x0281
            r8 = 87
            r11 = 10
            if (r2 == r8) goto L_0x0271
            r8 = 102(0x66, float:1.43E-43)
            if (r2 == r8) goto L_0x026a
            r8 = 110(0x6e, float:1.54E-43)
            if (r2 == r8) goto L_0x0265
            r8 = 13
            java.lang.String r11 = "msg.bad.backref"
            r7 = 9
            r15 = 48
            switch(r2) {
                case 48: goto L_0x0238;
                case 49: goto L_0x01e3;
                case 50: goto L_0x01e3;
                case 51: goto L_0x01e3;
                case 52: goto L_0x01e3;
                case 53: goto L_0x01e3;
                case 54: goto L_0x01e3;
                case 55: goto L_0x01e3;
                case 56: goto L_0x01e3;
                case 57: goto L_0x01e3;
                default: goto L_0x011b;
            }
        L_0x011b:
            switch(r2) {
                case 98: goto L_0x01d6;
                case 99: goto L_0x01ae;
                case 100: goto L_0x019f;
                default: goto L_0x011e;
            }
        L_0x011e:
            switch(r2) {
                case 114: goto L_0x019a;
                case 115: goto L_0x018a;
                case 116: goto L_0x0185;
                case 117: goto L_0x0155;
                case 118: goto L_0x014e;
                case 119: goto L_0x0140;
                case 120: goto L_0x013e;
                default: goto L_0x0121;
            }
        L_0x0121:
            org.mozilla.javascript.regexp.RENode r7 = new org.mozilla.javascript.regexp.RENode
            r7.<init>(r4)
            r0.result = r7
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.chr = r2
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            r2.length = r6
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            int r4 = r0.cp
            int r4 = r4 - r6
            r2.flatIndex = r4
            int r2 = r0.progLength
            int r2 = r2 + r5
            r0.progLength = r2
            goto L_0x033a
        L_0x013e:
            r9 = 2
            goto L_0x0155
        L_0x0140:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r2.<init>(r7)
            r0.result = r2
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
            goto L_0x033a
        L_0x014e:
            r2 = 11
            doFlat(r0, r2)
            goto L_0x033a
        L_0x0155:
            r2 = 0
            r4 = 0
        L_0x0157:
            if (r2 >= r9) goto L_0x017f
            int r5 = r0.cp
            int r7 = r0.cpend
            if (r5 >= r7) goto L_0x017f
            int r5 = r0.cp
            int r7 = r5 + 1
            r0.cp = r7
            char r5 = r1[r5]
            int r4 = org.mozilla.javascript.Kit.xDigitToInt(r5, r4)
            if (r4 >= 0) goto L_0x017c
            int r4 = r0.cp
            int r2 = r2 + r14
            int r4 = r4 - r2
            r0.cp = r4
            int r2 = r0.cp
            int r4 = r2 + 1
            r0.cp = r4
            char r4 = r1[r2]
            goto L_0x017f
        L_0x017c:
            int r2 = r2 + 1
            goto L_0x0157
        L_0x017f:
            char r2 = (char) r4
            doFlat(r0, r2)
            goto L_0x033a
        L_0x0185:
            doFlat(r0, r7)
            goto L_0x033a
        L_0x018a:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 11
            r2.<init>(r4)
            r0.result = r2
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
            goto L_0x033a
        L_0x019a:
            doFlat(r0, r8)
            goto L_0x033a
        L_0x019f:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 7
            r2.<init>(r4)
            r0.result = r2
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
            goto L_0x033a
        L_0x01ae:
            int r2 = r0.cp
            int r4 = r0.cpend
            if (r2 >= r4) goto L_0x01ca
            int r2 = r0.cp
            char r2 = r1[r2]
            boolean r2 = isControlLetter(r2)
            if (r2 == 0) goto L_0x01ca
            int r2 = r0.cp
            int r4 = r2 + 1
            r0.cp = r4
            char r2 = r1[r2]
            r2 = r2 & 31
            char r15 = (char) r2
            goto L_0x01d1
        L_0x01ca:
            int r2 = r0.cp
            int r2 = r2 - r6
            r0.cp = r2
            r15 = 92
        L_0x01d1:
            doFlat(r0, r15)
            goto L_0x033a
        L_0x01d6:
            org.mozilla.javascript.regexp.RENode r1 = new org.mozilla.javascript.regexp.RENode
            r1.<init>(r9)
            r0.result = r1
            int r1 = r0.progLength
            int r1 = r1 + r6
            r0.progLength = r1
            return r6
        L_0x01e3:
            int r4 = r0.cp
            int r4 = r4 - r6
            java.lang.String r9 = "msg.overlarge.backref"
            int r2 = getDecimalValue(r2, r0, r10, r9)
            int r9 = r0.parenCount
            if (r2 <= r9) goto L_0x01f5
            org.mozilla.javascript.Context r9 = r0.cx
            reportWarning(r9, r11, r12)
        L_0x01f5:
            if (r2 <= r7) goto L_0x0225
            int r7 = r0.parenCount
            if (r2 <= r7) goto L_0x0225
            r0.cp = r4
            r2 = 0
        L_0x01fe:
            int r4 = r0.cp
            int r5 = r0.cpend
            if (r4 >= r5) goto L_0x021f
            int r4 = r0.cp
            char r4 = r1[r4]
            if (r4 < r15) goto L_0x021f
            r5 = 55
            if (r4 > r5) goto L_0x021f
            int r5 = r0.cp
            int r5 = r5 + r6
            r0.cp = r5
            int r5 = r2 * 8
            int r4 = r4 + -48
            int r5 = r5 + r4
            r4 = 255(0xff, float:3.57E-43)
            if (r5 <= r4) goto L_0x021d
            goto L_0x021f
        L_0x021d:
            r2 = r5
            goto L_0x01fe
        L_0x021f:
            char r2 = (char) r2
            doFlat(r0, r2)
            goto L_0x033a
        L_0x0225:
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r8)
            r0.result = r4
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            int r2 = r2 - r6
            r4.parenIndex = r2
            int r2 = r0.progLength
            int r2 = r2 + r5
            r0.progLength = r2
            goto L_0x033a
        L_0x0238:
            org.mozilla.javascript.Context r2 = r0.cx
            reportWarning(r2, r11, r12)
            r2 = 0
        L_0x023e:
            int r4 = r0.cp
            int r5 = r0.cpend
            if (r4 >= r5) goto L_0x025f
            int r4 = r0.cp
            char r4 = r1[r4]
            if (r4 < r15) goto L_0x025f
            r5 = 55
            if (r4 > r5) goto L_0x025f
            int r5 = r0.cp
            int r5 = r5 + r6
            r0.cp = r5
            int r5 = r2 * 8
            int r4 = r4 + -48
            int r5 = r5 + r4
            r4 = 255(0xff, float:3.57E-43)
            if (r5 <= r4) goto L_0x025d
            goto L_0x025f
        L_0x025d:
            r2 = r5
            goto L_0x023e
        L_0x025f:
            char r2 = (char) r2
            doFlat(r0, r2)
            goto L_0x033a
        L_0x0265:
            doFlat(r0, r11)
            goto L_0x033a
        L_0x026a:
            r2 = 12
            doFlat(r0, r2)
            goto L_0x033a
        L_0x0271:
            r2 = 12
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r11)
            r0.result = r4
            int r4 = r0.progLength
            int r4 = r4 + r6
            r0.progLength = r4
            goto L_0x033a
        L_0x0281:
            r2 = 12
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r2)
            r0.result = r4
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
            goto L_0x033a
        L_0x0291:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 8
            r2.<init>(r4)
            r0.result = r2
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
            goto L_0x033a
        L_0x02a1:
            org.mozilla.javascript.regexp.RENode r1 = new org.mozilla.javascript.regexp.RENode
            r2 = 5
            r1.<init>(r2)
            r0.result = r1
            int r1 = r0.progLength
            int r1 = r1 + r6
            r0.progLength = r1
            return r6
        L_0x02af:
            java.lang.String r0 = "msg.trail.backslash"
            reportError(r0, r12)
            return r13
        L_0x02b5:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r4 = 22
            r2.<init>(r4)
            r0.result = r2
            int r2 = r0.cp
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.startIndex = r2
        L_0x02c4:
            int r4 = r0.cp
            int r7 = r0.cpend
            if (r4 != r7) goto L_0x02d0
            java.lang.String r0 = "msg.unterm.class"
            reportError(r0, r12)
            return r13
        L_0x02d0:
            int r4 = r0.cp
            char r4 = r1[r4]
            r7 = 92
            if (r4 != r7) goto L_0x02de
            int r4 = r0.cp
            int r4 = r4 + r6
            r0.cp = r4
            goto L_0x030c
        L_0x02de:
            int r4 = r0.cp
            char r4 = r1[r4]
            r8 = 93
            if (r4 != r8) goto L_0x030c
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            int r7 = r0.cp
            int r7 = r7 - r2
            r4.kidlen = r7
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            int r7 = r0.classCount
            int r8 = r7 + 1
            r0.classCount = r8
            r4.index = r7
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            int r7 = r0.cp
            int r8 = r7 + 1
            r0.cp = r8
            boolean r2 = calculateBitmapSize(r0, r4, r1, r2, r7)
            if (r2 != 0) goto L_0x0306
            return r13
        L_0x0306:
            int r2 = r0.progLength
            int r2 = r2 + r5
            r0.progLength = r2
            goto L_0x033a
        L_0x030c:
            int r4 = r0.cp
            int r4 = r4 + r6
            r0.cp = r4
            goto L_0x02c4
        L_0x0312:
            org.mozilla.javascript.regexp.RENode r1 = new org.mozilla.javascript.regexp.RENode
            r1.<init>(r14)
            r0.result = r1
            int r1 = r0.progLength
            int r1 = r1 + r6
            r0.progLength = r1
            return r6
        L_0x031f:
            int r0 = r0.cp
            int r0 = r0 - r6
            char r0 = r1[r0]
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "msg.bad.quant"
            reportError(r1, r0)
            return r13
        L_0x032e:
            org.mozilla.javascript.regexp.RENode r2 = new org.mozilla.javascript.regexp.RENode
            r2.<init>(r8)
            r0.result = r2
            int r2 = r0.progLength
            int r2 = r2 + r6
            r0.progLength = r2
        L_0x033a:
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            int r4 = r0.cp
            int r5 = r0.cpend
            if (r4 != r5) goto L_0x0343
            return r6
        L_0x0343:
            int r4 = r0.cp
            char r4 = r1[r4]
            r5 = -1
            r7 = 25
            r8 = 42
            if (r4 == r8) goto L_0x0401
            r8 = 43
            if (r4 == r8) goto L_0x03ea
            r8 = 63
            if (r4 == r8) goto L_0x03d3
            r8 = 123(0x7b, float:1.72E-43)
            if (r4 == r8) goto L_0x035d
            r5 = 0
            goto L_0x0418
        L_0x035d:
            int r4 = r0.cp
            int r8 = r0.cp
            int r8 = r8 + r6
            r0.cp = r8
            int r9 = r1.length
            if (r8 >= r9) goto L_0x03cd
            int r8 = r0.cp
            char r8 = r1[r8]
            boolean r9 = isDigit(r8)
            if (r9 == 0) goto L_0x03cd
            int r9 = r0.cp
            int r9 = r9 + r6
            r0.cp = r9
            java.lang.String r9 = "msg.overlarge.min"
            int r8 = getDecimalValue(r8, r0, r10, r9)
            int r9 = r0.cp
            char r9 = r1[r9]
            r11 = 44
            if (r9 != r11) goto L_0x03b0
            int r9 = r0.cp
            int r9 = r9 + r6
            r0.cp = r9
            char r9 = r1[r9]
            boolean r11 = isDigit(r9)
            if (r11 == 0) goto L_0x03b1
            int r5 = r0.cp
            int r5 = r5 + r6
            r0.cp = r5
            java.lang.String r5 = "msg.overlarge.max"
            int r5 = getDecimalValue(r9, r0, r10, r5)
            int r9 = r0.cp
            char r9 = r1[r9]
            if (r8 <= r5) goto L_0x03b1
            int r0 = r0.cp
            char r0 = r1[r0]
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "msg.max.lt.min"
            reportError(r1, r0)
            return r13
        L_0x03b0:
            r5 = r8
        L_0x03b1:
            r10 = 125(0x7d, float:1.75E-43)
            if (r9 != r10) goto L_0x03cd
            org.mozilla.javascript.regexp.RENode r9 = new org.mozilla.javascript.regexp.RENode
            r9.<init>(r7)
            r0.result = r9
            org.mozilla.javascript.regexp.RENode r7 = r0.result
            r7.min = r8
            org.mozilla.javascript.regexp.RENode r7 = r0.result
            r7.max = r5
            int r5 = r0.progLength
            r7 = 12
            int r5 = r5 + r7
            r0.progLength = r5
            r5 = 1
            goto L_0x03ce
        L_0x03cd:
            r5 = 0
        L_0x03ce:
            if (r5 != 0) goto L_0x0418
            r0.cp = r4
            goto L_0x0418
        L_0x03d3:
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r7)
            r0.result = r4
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.min = r13
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.max = r6
            int r4 = r0.progLength
            r8 = 8
            int r4 = r4 + r8
            r0.progLength = r4
            goto L_0x0417
        L_0x03ea:
            r8 = 8
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r7)
            r0.result = r4
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.min = r6
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.max = r5
            int r4 = r0.progLength
            int r4 = r4 + r8
            r0.progLength = r4
            goto L_0x0417
        L_0x0401:
            r8 = 8
            org.mozilla.javascript.regexp.RENode r4 = new org.mozilla.javascript.regexp.RENode
            r4.<init>(r7)
            r0.result = r4
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.min = r13
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.max = r5
            int r4 = r0.progLength
            int r4 = r4 + r8
            r0.progLength = r4
        L_0x0417:
            r5 = 1
        L_0x0418:
            if (r5 != 0) goto L_0x041b
            return r6
        L_0x041b:
            int r4 = r0.cp
            int r4 = r4 + r6
            r0.cp = r4
            org.mozilla.javascript.regexp.RENode r4 = r0.result
            r4.kid = r2
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            r2.parenIndex = r3
            org.mozilla.javascript.regexp.RENode r2 = r0.result
            int r4 = r0.parenCount
            int r4 = r4 - r3
            r2.parenCount = r4
            int r2 = r0.cp
            int r3 = r0.cpend
            if (r2 >= r3) goto L_0x0447
            int r2 = r0.cp
            char r1 = r1[r2]
            r2 = 63
            if (r1 != r2) goto L_0x0447
            int r1 = r0.cp
            int r1 = r1 + r6
            r0.cp = r1
            org.mozilla.javascript.regexp.RENode r0 = r0.result
            r0.greedy = r13
            goto L_0x044b
        L_0x0447:
            org.mozilla.javascript.regexp.RENode r0 = r0.result
            r0.greedy = r6
        L_0x044b:
            return r6
        L_0x044c:
            org.mozilla.javascript.regexp.RENode r1 = new org.mozilla.javascript.regexp.RENode
            r1.<init>(r5)
            r0.result = r1
            int r1 = r0.progLength
            int r1 = r1 + r6
            r0.progLength = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.parseTerm(org.mozilla.javascript.regexp.CompilerState):boolean");
    }

    private static void resolveForwardJump(byte[] bArr, int i, int i2) {
        if (i <= i2) {
            addIndex(bArr, i, i2 - i);
            return;
        }
        throw Kit.codeBug();
    }

    private static int getOffset(byte[] bArr, int i) {
        return getIndex(bArr, i);
    }

    private static int addIndex(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw Kit.codeBug();
        } else if (i2 <= 65535) {
            bArr[i] = (byte) (i2 >> 8);
            bArr[i + 1] = (byte) i2;
            return i + 2;
        } else {
            throw Context.reportRuntimeError("Too complex regexp");
        }
    }

    private static int getIndex(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private static int emitREBytecode(CompilerState compilerState, RECompiled rECompiled, int i, RENode rENode) {
        int i2;
        byte[] bArr = rECompiled.program;
        while (rENode != null) {
            int i3 = i + 1;
            bArr[i] = rENode.op;
            byte b = rENode.op;
            boolean z = true;
            if (b != 1) {
                if (b != 22) {
                    if (b == 25) {
                        if (rENode.min == 0 && rENode.max == -1) {
                            bArr[i3 - 1] = rENode.greedy ? 26 : REOP_MINIMALSTAR;
                        } else if (rENode.min == 0 && rENode.max == 1) {
                            bArr[i3 - 1] = rENode.greedy ? 28 : REOP_MINIMALOPT;
                        } else if (rENode.min == 1 && rENode.max == -1) {
                            bArr[i3 - 1] = rENode.greedy ? 27 : REOP_MINIMALPLUS;
                        } else {
                            if (!rENode.greedy) {
                                bArr[i3 - 1] = REOP_MINIMALQUANT;
                            }
                            i3 = addIndex(bArr, addIndex(bArr, i3, rENode.min), rENode.max + 1);
                        }
                        int addIndex = addIndex(bArr, addIndex(bArr, i3, rENode.parenCount), rENode.parenIndex);
                        int emitREBytecode = emitREBytecode(compilerState, rECompiled, addIndex + 2, rENode.kid);
                        i2 = emitREBytecode + 1;
                        bArr[emitREBytecode] = REOP_ENDCHILD;
                        resolveForwardJump(bArr, addIndex, i2);
                    } else if (b != 29) {
                        if (b != 31) {
                            if (b == 13) {
                                i = addIndex(bArr, i3, rENode.parenIndex);
                            } else if (b == 14) {
                                if (rENode.flatIndex != -1) {
                                    while (rENode.next != null && rENode.next.op == 14 && rENode.flatIndex + rENode.length == rENode.next.flatIndex) {
                                        rENode.length += rENode.next.length;
                                        rENode.next = rENode.next.next;
                                    }
                                }
                                if (rENode.flatIndex != -1 && rENode.length > 1) {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 16;
                                    } else {
                                        bArr[i3 - 1] = 14;
                                    }
                                    i = addIndex(bArr, addIndex(bArr, i3, rENode.flatIndex), rENode.length);
                                } else if (rENode.chr < 256) {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 17;
                                    } else {
                                        bArr[i3 - 1] = 15;
                                    }
                                    i = i3 + 1;
                                    bArr[i3] = (byte) rENode.chr;
                                } else {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 19;
                                    } else {
                                        bArr[i3 - 1] = 18;
                                    }
                                    i = addIndex(bArr, i3, rENode.chr);
                                }
                            } else if (b == 41) {
                                int emitREBytecode2 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                                i2 = emitREBytecode2 + 1;
                                bArr[emitREBytecode2] = REOP_ASSERTTEST;
                                resolveForwardJump(bArr, i3, i2);
                            } else if (b != 42) {
                                switch (b) {
                                    case 53:
                                    case 54:
                                    case 55:
                                        if (rENode.op != 54) {
                                            z = false;
                                        }
                                        char c = rENode.chr;
                                        if (z) {
                                            c = upcase(c);
                                        }
                                        addIndex(bArr, i3, c);
                                        int i4 = i3 + 2;
                                        int i5 = rENode.index;
                                        if (z) {
                                            i5 = upcase((char) i5);
                                        }
                                        addIndex(bArr, i4, i5);
                                        i3 = i4 + 2;
                                        break;
                                }
                            } else {
                                int emitREBytecode3 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                                i2 = emitREBytecode3 + 1;
                                bArr[emitREBytecode3] = REOP_ASSERTNOTTEST;
                                resolveForwardJump(bArr, i3, i2);
                            }
                        }
                        RENode rENode2 = rENode.kid2;
                        int emitREBytecode4 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                        int i6 = emitREBytecode4 + 1;
                        bArr[emitREBytecode4] = 32;
                        int i7 = i6 + 2;
                        resolveForwardJump(bArr, i3, i7);
                        int emitREBytecode5 = emitREBytecode(compilerState, rECompiled, i7, rENode2);
                        int i8 = emitREBytecode5 + 1;
                        bArr[emitREBytecode5] = 32;
                        i = i8 + 2;
                        resolveForwardJump(bArr, i6, i);
                        resolveForwardJump(bArr, i8, i);
                    } else {
                        int emitREBytecode6 = emitREBytecode(compilerState, rECompiled, addIndex(bArr, i3, rENode.parenIndex), rENode.kid);
                        bArr[emitREBytecode6] = 30;
                        i = addIndex(bArr, emitREBytecode6 + 1, rENode.parenIndex);
                    }
                    i = i2;
                } else {
                    if (!rENode.sense) {
                        bArr[i3 - 1] = Ascii.ETB;
                    }
                    i = addIndex(bArr, i3, rENode.index);
                    rECompiled.classList[rENode.index] = new RECharSet(rENode.bmsize, rENode.startIndex, rENode.kidlen, rENode.sense);
                }
                rENode = rENode.next;
            } else {
                i3--;
            }
            i = i3;
            rENode = rENode.next;
        }
        return i;
    }

    private static void pushProgState(REGlobalData rEGlobalData, int i, int i2, int i3, REBackTrackData rEBackTrackData, int i4, int i5) {
        rEGlobalData.stateStackTop = new REProgState(rEGlobalData.stateStackTop, i, i2, i3, rEBackTrackData, i4, i5);
    }

    private static REProgState popProgState(REGlobalData rEGlobalData) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.stateStackTop = rEProgState.previous;
        return rEProgState;
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, rEGlobalData.cp, rEProgState.continuationOp, rEProgState.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i, int i2, int i3, int i4) {
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, i2, i3, i4);
    }

    private static boolean flatNMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (rEGlobalData.regexp.source[i + i4] != str.charAt(rEGlobalData.cp + i4)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        char[] cArr = rEGlobalData.regexp.source;
        for (int i4 = 0; i4 < i2; i4++) {
            char c = cArr[i + i4];
            char charAt = str.charAt(rEGlobalData.cp + i4);
            if (c != charAt && upcase(c) != upcase(charAt)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData rEGlobalData, int i, String str, int i2) {
        if (rEGlobalData.parens == null || i >= rEGlobalData.parens.length) {
            return false;
        }
        int parensIndex = rEGlobalData.parensIndex(i);
        if (parensIndex == -1) {
            return true;
        }
        int parensLength = rEGlobalData.parensLength(i);
        if (rEGlobalData.cp + parensLength > i2) {
            return false;
        }
        if ((rEGlobalData.regexp.flags & 2) != 0) {
            for (int i3 = 0; i3 < parensLength; i3++) {
                char charAt = str.charAt(parensIndex + i3);
                char charAt2 = str.charAt(rEGlobalData.cp + i3);
                if (charAt != charAt2 && upcase(charAt) != upcase(charAt2)) {
                    return false;
                }
            }
        } else if (!str.regionMatches(parensIndex, str, rEGlobalData.cp, parensLength)) {
            return false;
        }
        rEGlobalData.cp += parensLength;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet rECharSet, char c) {
        int i = c / 8;
        if (c < rECharSet.length) {
            byte[] bArr = rECharSet.bits;
            bArr[i] = (byte) ((1 << (c & 7)) | bArr[i]);
            return;
        }
        throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
    }

    private static void addCharacterRangeToCharSet(RECharSet rECharSet, char c, char c2) {
        int i = c / 8;
        int i2 = c2 / 8;
        if (c2 >= rECharSet.length || c > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        char c3 = (char) (c & 7);
        char c4 = (char) (c2 & 7);
        if (i == i2) {
            byte[] bArr = rECharSet.bits;
            bArr[i] = (byte) (((255 >> (7 - (c4 - c3))) << c3) | bArr[i]);
            return;
        }
        byte[] bArr2 = rECharSet.bits;
        bArr2[i] = (byte) ((255 << c3) | bArr2[i]);
        while (true) {
            i++;
            if (i < i2) {
                rECharSet.bits[i] = -1;
            } else {
                byte[] bArr3 = rECharSet.bits;
                bArr3[i2] = (byte) (bArr3[i2] | (255 >> (7 - c4)));
                return;
            }
        }
    }

    private static void processCharSet(REGlobalData rEGlobalData, RECharSet rECharSet) {
        synchronized (rECharSet) {
            if (!rECharSet.converted) {
                processCharSetImpl(rEGlobalData, rECharSet);
                rECharSet.converted = true;
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0088, code lost:
        r9 = 0;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        if (r9 >= r2) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008c, code lost:
        if (r12 >= r3) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008e, code lost:
        r15 = r12 + 1;
        r12 = toASCIIHexDigit(r0.regexp.source[r12]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009a, code lost:
        if (r12 >= 0) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009c, code lost:
        r12 = r15 - (r9 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a1, code lost:
        r13 = (r13 << 4) | r12;
        r9 = r9 + 1;
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a8, code lost:
        r10 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
        r10 = (char) r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData r17, org.mozilla.javascript.regexp.RECharSet r18) {
        /*
            r0 = r17
            r1 = r18
            int r2 = r1.startIndex
            int r3 = r1.strlength
            int r3 = r3 + r2
            int r4 = r1.length
            int r4 = r4 + 7
            r5 = 8
            int r4 = r4 / r5
            byte[] r4 = new byte[r4]
            r1.bits = r4
            if (r2 != r3) goto L_0x0017
            return
        L_0x0017:
            org.mozilla.javascript.regexp.RECompiled r4 = r0.regexp
            char[] r4 = r4.source
            char r4 = r4[r2]
            r6 = 94
            r7 = 0
            r8 = 1
            if (r4 != r6) goto L_0x0025
            int r2 = r2 + 1
        L_0x0025:
            r4 = 0
            r6 = 0
        L_0x0027:
            if (r2 == r3) goto L_0x01b2
            org.mozilla.javascript.regexp.RECompiled r9 = r0.regexp
            char[] r9 = r9.source
            char r9 = r9[r2]
            r10 = 92
            r11 = 2
            if (r9 == r10) goto L_0x0043
            org.mozilla.javascript.regexp.RECompiled r9 = r0.regexp
            char[] r9 = r9.source
            int r10 = r2 + 1
            char r2 = r9[r2]
            r16 = r10
            r10 = r2
            r2 = r16
            goto L_0x0125
        L_0x0043:
            int r2 = r2 + 1
            org.mozilla.javascript.regexp.RECompiled r9 = r0.regexp
            char[] r9 = r9.source
            int r12 = r2 + 1
            char r2 = r9[r2]
            r9 = 68
            if (r2 == r9) goto L_0x01a1
            r9 = 83
            if (r2 == r9) goto L_0x018f
            r9 = 87
            if (r2 == r9) goto L_0x017d
            r9 = 102(0x66, float:1.43E-43)
            if (r2 == r9) goto L_0x0121
            r9 = 110(0x6e, float:1.54E-43)
            if (r2 == r9) goto L_0x011d
            r9 = 48
            switch(r2) {
                case 48: goto L_0x00ec;
                case 49: goto L_0x00ec;
                case 50: goto L_0x00ec;
                case 51: goto L_0x00ec;
                case 52: goto L_0x00ec;
                case 53: goto L_0x00ec;
                case 54: goto L_0x00ec;
                case 55: goto L_0x00ec;
                default: goto L_0x0066;
            }
        L_0x0066:
            switch(r2) {
                case 98: goto L_0x00e8;
                case 99: goto L_0x00ca;
                case 100: goto L_0x00c3;
                default: goto L_0x0069;
            }
        L_0x0069:
            switch(r2) {
                case 114: goto L_0x00c0;
                case 115: goto L_0x00ae;
                case 116: goto L_0x00ab;
                case 117: goto L_0x0087;
                case 118: goto L_0x0084;
                case 119: goto L_0x0072;
                case 120: goto L_0x0070;
                default: goto L_0x006c;
            }
        L_0x006c:
            r10 = r2
        L_0x006d:
            r2 = r12
            goto L_0x0125
        L_0x0070:
            r2 = 2
            goto L_0x0088
        L_0x0072:
            int r2 = r1.length
            int r2 = r2 - r8
        L_0x0075:
            if (r2 < 0) goto L_0x01af
            char r9 = (char) r2
            boolean r10 = isWord(r9)
            if (r10 == 0) goto L_0x0081
            addCharacterToCharSet(r1, r9)
        L_0x0081:
            int r2 = r2 + -1
            goto L_0x0075
        L_0x0084:
            r10 = 11
            goto L_0x006d
        L_0x0087:
            r2 = 4
        L_0x0088:
            r9 = 0
            r13 = 0
        L_0x008a:
            if (r9 >= r2) goto L_0x00a8
            if (r12 >= r3) goto L_0x00a8
            org.mozilla.javascript.regexp.RECompiled r14 = r0.regexp
            char[] r14 = r14.source
            int r15 = r12 + 1
            char r12 = r14[r12]
            int r12 = toASCIIHexDigit(r12)
            if (r12 >= 0) goto L_0x00a1
            int r9 = r9 + 1
            int r12 = r15 - r9
            goto L_0x00a9
        L_0x00a1:
            int r13 = r13 << 4
            r13 = r13 | r12
            int r9 = r9 + 1
            r12 = r15
            goto L_0x008a
        L_0x00a8:
            r10 = r13
        L_0x00a9:
            char r10 = (char) r10
            goto L_0x006d
        L_0x00ab:
            r10 = 9
            goto L_0x006d
        L_0x00ae:
            int r2 = r1.length
            int r2 = r2 - r8
        L_0x00b1:
            if (r2 < 0) goto L_0x01af
            boolean r9 = isREWhiteSpace(r2)
            if (r9 == 0) goto L_0x00bd
            char r9 = (char) r2
            addCharacterToCharSet(r1, r9)
        L_0x00bd:
            int r2 = r2 + -1
            goto L_0x00b1
        L_0x00c0:
            r10 = 13
            goto L_0x006d
        L_0x00c3:
            r2 = 57
            addCharacterRangeToCharSet(r1, r9, r2)
            goto L_0x01af
        L_0x00ca:
            if (r12 >= r3) goto L_0x00e5
            org.mozilla.javascript.regexp.RECompiled r2 = r0.regexp
            char[] r2 = r2.source
            char r2 = r2[r12]
            boolean r2 = isControlLetter(r2)
            if (r2 == 0) goto L_0x00e5
            org.mozilla.javascript.regexp.RECompiled r2 = r0.regexp
            char[] r2 = r2.source
            int r9 = r12 + 1
            char r2 = r2[r12]
            r2 = r2 & 31
            char r10 = (char) r2
            r2 = r9
            goto L_0x0125
        L_0x00e5:
            int r12 = r12 + -1
            goto L_0x006d
        L_0x00e8:
            r2 = r12
            r10 = 8
            goto L_0x0125
        L_0x00ec:
            int r2 = r2 + -48
            org.mozilla.javascript.regexp.RECompiled r10 = r0.regexp
            char[] r10 = r10.source
            char r10 = r10[r12]
            if (r9 > r10) goto L_0x011a
            r13 = 55
            if (r10 > r13) goto L_0x011a
            int r12 = r12 + 1
            int r2 = r2 * 8
            int r10 = r10 + -48
            int r2 = r2 + r10
            org.mozilla.javascript.regexp.RECompiled r10 = r0.regexp
            char[] r10 = r10.source
            char r10 = r10[r12]
            if (r9 > r10) goto L_0x011a
            if (r10 > r13) goto L_0x011a
            int r12 = r12 + 1
            int r9 = r2 * 8
            int r10 = r10 + -48
            int r9 = r9 + r10
            r10 = 255(0xff, float:3.57E-43)
            if (r9 > r10) goto L_0x0118
            r2 = r9
            goto L_0x011a
        L_0x0118:
            int r12 = r12 + -1
        L_0x011a:
            char r10 = (char) r2
            goto L_0x006d
        L_0x011d:
            r10 = 10
            goto L_0x006d
        L_0x0121:
            r10 = 12
            goto L_0x006d
        L_0x0125:
            if (r4 == 0) goto L_0x0150
            org.mozilla.javascript.regexp.RECompiled r4 = r0.regexp
            int r4 = r4.flags
            r4 = r4 & r11
            if (r4 == 0) goto L_0x014a
            r4 = r6
        L_0x012f:
            if (r4 > r10) goto L_0x014d
            addCharacterToCharSet(r1, r4)
            char r9 = upcase(r4)
            char r11 = downcase(r4)
            if (r4 == r9) goto L_0x0141
            addCharacterToCharSet(r1, r9)
        L_0x0141:
            if (r4 == r11) goto L_0x0146
            addCharacterToCharSet(r1, r11)
        L_0x0146:
            int r4 = r4 + 1
            char r4 = (char) r4
            goto L_0x012f
        L_0x014a:
            addCharacterRangeToCharSet(r1, r6, r10)
        L_0x014d:
            r4 = 0
            goto L_0x0027
        L_0x0150:
            org.mozilla.javascript.regexp.RECompiled r9 = r0.regexp
            int r9 = r9.flags
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0166
            char r9 = upcase(r10)
            addCharacterToCharSet(r1, r9)
            char r9 = downcase(r10)
            addCharacterToCharSet(r1, r9)
            goto L_0x0169
        L_0x0166:
            addCharacterToCharSet(r1, r10)
        L_0x0169:
            int r9 = r3 + -1
            if (r2 >= r9) goto L_0x0027
            org.mozilla.javascript.regexp.RECompiled r9 = r0.regexp
            char[] r9 = r9.source
            char r9 = r9[r2]
            r11 = 45
            if (r9 != r11) goto L_0x0027
            int r2 = r2 + 1
            r6 = r10
            r4 = 1
            goto L_0x0027
        L_0x017d:
            int r2 = r1.length
            int r2 = r2 - r8
        L_0x0180:
            if (r2 < 0) goto L_0x01af
            char r9 = (char) r2
            boolean r10 = isWord(r9)
            if (r10 != 0) goto L_0x018c
            addCharacterToCharSet(r1, r9)
        L_0x018c:
            int r2 = r2 + -1
            goto L_0x0180
        L_0x018f:
            int r2 = r1.length
            int r2 = r2 - r8
        L_0x0192:
            if (r2 < 0) goto L_0x01af
            boolean r9 = isREWhiteSpace(r2)
            if (r9 != 0) goto L_0x019e
            char r9 = (char) r2
            addCharacterToCharSet(r1, r9)
        L_0x019e:
            int r2 = r2 + -1
            goto L_0x0192
        L_0x01a1:
            r2 = 47
            addCharacterRangeToCharSet(r1, r7, r2)
            r2 = 58
            int r9 = r1.length
            int r9 = r9 - r8
            char r9 = (char) r9
            addCharacterRangeToCharSet(r1, r2, r9)
        L_0x01af:
            r2 = r12
            goto L_0x0027
        L_0x01b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData, org.mozilla.javascript.regexp.RECharSet):void");
    }

    private static boolean classMatcher(REGlobalData rEGlobalData, RECharSet rECharSet, char c) {
        if (!rECharSet.converted) {
            processCharSet(rEGlobalData, rECharSet);
        }
        int i = c >> 3;
        boolean z = true;
        if (!(rECharSet.length == 0 || c >= rECharSet.length || (rECharSet.bits[i] & (1 << (c & 7))) == 0)) {
            z = false;
        }
        return rECharSet.sense ^ z;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01f6, code lost:
        if (isLineTerm(r4.charAt(r3.cp - 1)) == false) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01fa, code lost:
        if (r1 == false) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01fc, code lost:
        if (r9 != false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01fe, code lost:
        r3.cp = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0200, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0201, code lost:
        r3.cp = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0204, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a3, code lost:
        if (isWord(r4.charAt(r3.cp)) != false) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c9, code lost:
        if (isWord(r4.charAt(r3.cp)) != false) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01cb, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01cc, code lost:
        r1 = r1 ^ r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01e0, code lost:
        if (isLineTerm(r4.charAt(r3.cp)) == false) goto L_0x01fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int simpleMatch(org.mozilla.javascript.regexp.REGlobalData r3, java.lang.String r4, int r5, byte[] r6, int r7, int r8, boolean r9) {
        /*
            int r0 = r3.cp
            r1 = 0
            r2 = 1
            switch(r5) {
                case 1: goto L_0x01f9;
                case 2: goto L_0x01e3;
                case 3: goto L_0x01ce;
                case 4: goto L_0x01a6;
                case 5: goto L_0x0180;
                case 6: goto L_0x0169;
                case 7: goto L_0x0152;
                case 8: goto L_0x013b;
                case 9: goto L_0x0124;
                case 10: goto L_0x010d;
                case 11: goto L_0x00f6;
                case 12: goto L_0x00df;
                case 13: goto L_0x00d3;
                case 14: goto L_0x00c1;
                case 15: goto L_0x00a3;
                case 16: goto L_0x0091;
                case 17: goto L_0x006d;
                case 18: goto L_0x0053;
                case 19: goto L_0x002f;
                case 20: goto L_0x0007;
                case 21: goto L_0x0007;
                case 22: goto L_0x000c;
                case 23: goto L_0x000c;
                default: goto L_0x0007;
            }
        L_0x0007:
            java.lang.RuntimeException r3 = org.mozilla.javascript.Kit.codeBug()
            throw r3
        L_0x000c:
            int r5 = getIndex(r6, r7)
            int r7 = r7 + 2
            int r6 = r3.cp
            if (r6 == r8) goto L_0x01fa
            org.mozilla.javascript.regexp.RECompiled r6 = r3.regexp
            org.mozilla.javascript.regexp.RECharSet[] r6 = r6.classList
            r5 = r6[r5]
            int r6 = r3.cp
            char r4 = r4.charAt(r6)
            boolean r4 = classMatcher(r3, r5, r4)
            if (r4 == 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x002f:
            int r5 = getIndex(r6, r7)
            char r5 = (char) r5
            int r7 = r7 + 2
            int r6 = r3.cp
            if (r6 == r8) goto L_0x01fa
            int r6 = r3.cp
            char r4 = r4.charAt(r6)
            if (r5 == r4) goto L_0x004c
            char r5 = upcase(r5)
            char r4 = upcase(r4)
            if (r5 != r4) goto L_0x01fa
        L_0x004c:
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x0053:
            int r5 = getIndex(r6, r7)
            char r5 = (char) r5
            int r7 = r7 + 2
            int r6 = r3.cp
            if (r6 == r8) goto L_0x01fa
            int r6 = r3.cp
            char r4 = r4.charAt(r6)
            if (r4 != r5) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x006d:
            int r5 = r7 + 1
            byte r6 = r6[r7]
            r6 = r6 & 255(0xff, float:3.57E-43)
            char r6 = (char) r6
            int r7 = r3.cp
            if (r7 == r8) goto L_0x00be
            int r7 = r3.cp
            char r4 = r4.charAt(r7)
            if (r6 == r4) goto L_0x008a
            char r6 = upcase(r6)
            char r4 = upcase(r4)
            if (r6 != r4) goto L_0x00be
        L_0x008a:
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            r1 = 1
            goto L_0x00be
        L_0x0091:
            int r5 = getIndex(r6, r7)
            int r7 = r7 + 2
            int r6 = getIndex(r6, r7)
            int r7 = r7 + 2
            boolean r1 = flatNIMatcher(r3, r5, r6, r4, r8)
            goto L_0x01fa
        L_0x00a3:
            int r5 = r7 + 1
            byte r6 = r6[r7]
            r6 = r6 & 255(0xff, float:3.57E-43)
            char r6 = (char) r6
            int r7 = r3.cp
            if (r7 == r8) goto L_0x00be
            int r7 = r3.cp
            char r4 = r4.charAt(r7)
            if (r4 != r6) goto L_0x00be
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            r7 = r5
            goto L_0x01f9
        L_0x00be:
            r7 = r5
            goto L_0x01fa
        L_0x00c1:
            int r5 = getIndex(r6, r7)
            int r7 = r7 + 2
            int r6 = getIndex(r6, r7)
            int r7 = r7 + 2
            boolean r1 = flatNMatcher(r3, r5, r6, r4, r8)
            goto L_0x01fa
        L_0x00d3:
            int r5 = getIndex(r6, r7)
            int r7 = r7 + 2
            boolean r1 = backrefMatcher(r3, r5, r4, r8)
            goto L_0x01fa
        L_0x00df:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isREWhiteSpace(r4)
            if (r4 != 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x00f6:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isREWhiteSpace(r4)
            if (r4 == 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x010d:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isWord(r4)
            if (r4 != 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x0124:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isWord(r4)
            if (r4 == 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x013b:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isDigit(r4)
            if (r4 != 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x0152:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isDigit(r4)
            if (r4 == 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x0169:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isLineTerm(r4)
            if (r4 != 0) goto L_0x01fa
            int r4 = r3.cp
            int r4 = r4 + r2
            r3.cp = r4
            goto L_0x01f9
        L_0x0180:
            int r5 = r3.cp
            if (r5 == 0) goto L_0x0194
            int r5 = r3.cp
            int r5 = r5 - r2
            char r5 = r4.charAt(r5)
            boolean r5 = isWord(r5)
            if (r5 != 0) goto L_0x0192
            goto L_0x0194
        L_0x0192:
            r5 = 0
            goto L_0x0195
        L_0x0194:
            r5 = 1
        L_0x0195:
            int r6 = r3.cp
            if (r6 >= r8) goto L_0x01cc
            int r6 = r3.cp
            char r4 = r4.charAt(r6)
            boolean r4 = isWord(r4)
            if (r4 == 0) goto L_0x01cc
            goto L_0x01cb
        L_0x01a6:
            int r5 = r3.cp
            if (r5 == 0) goto L_0x01ba
            int r5 = r3.cp
            int r5 = r5 - r2
            char r5 = r4.charAt(r5)
            boolean r5 = isWord(r5)
            if (r5 != 0) goto L_0x01b8
            goto L_0x01ba
        L_0x01b8:
            r5 = 0
            goto L_0x01bb
        L_0x01ba:
            r5 = 1
        L_0x01bb:
            int r6 = r3.cp
            if (r6 >= r8) goto L_0x01cb
            int r6 = r3.cp
            char r4 = r4.charAt(r6)
            boolean r4 = isWord(r4)
            if (r4 != 0) goto L_0x01cc
        L_0x01cb:
            r1 = 1
        L_0x01cc:
            r1 = r1 ^ r5
            goto L_0x01fa
        L_0x01ce:
            int r5 = r3.cp
            if (r5 == r8) goto L_0x01f9
            boolean r5 = r3.multiline
            if (r5 == 0) goto L_0x01fa
            int r5 = r3.cp
            char r4 = r4.charAt(r5)
            boolean r4 = isLineTerm(r4)
            if (r4 != 0) goto L_0x01f9
            goto L_0x01fa
        L_0x01e3:
            int r5 = r3.cp
            if (r5 == 0) goto L_0x01f9
            boolean r5 = r3.multiline
            if (r5 == 0) goto L_0x01fa
            int r5 = r3.cp
            int r5 = r5 - r2
            char r4 = r4.charAt(r5)
            boolean r4 = isLineTerm(r4)
            if (r4 != 0) goto L_0x01f9
            goto L_0x01fa
        L_0x01f9:
            r1 = 1
        L_0x01fa:
            if (r1 == 0) goto L_0x0201
            if (r9 != 0) goto L_0x0200
            r3.cp = r0
        L_0x0200:
            return r7
        L_0x0201:
            r3.cp = r0
            r3 = -1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.simpleMatch(org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int, byte[], int, int, boolean):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v55, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v60, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02ca, code lost:
        if (r9[r0] == 44) goto L_0x0305;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0305, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x034c, code lost:
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0359, code lost:
        r12 = r11 + getOffset(r9, r11);
        r11 = r11 + 2;
        r4 = r11 + 1;
        r2 = r9[r11];
        r11 = r7.cp;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x036b, code lost:
        if (reopIsSimple(r2) == false) goto L_0x0389;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x036d, code lost:
        r0 = simpleMatch(r23, r24, r2, r9, r4, r25, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0379, code lost:
        if (r0 >= 0) goto L_0x0380;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x037b, code lost:
        r11 = r12 + 1;
        r2 = r9[r12];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0380, code lost:
        r6 = r9[r0];
        r13 = r0 + 1;
        r18 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0389, code lost:
        r6 = r2;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x038b, code lost:
        pushBackTrackState(r23, r9[r12], r12 + 1, r11, r17, r16);
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x039a, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03c7, code lost:
        switch(r2) {
            case 25: goto L_0x03e9;
            case 26: goto L_0x03e3;
            case 27: goto L_0x03de;
            case 28: goto L_0x03da;
            default: goto L_0x03ca;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03ca, code lost:
        switch(r2) {
            case 45: goto L_0x03d8;
            case 46: goto L_0x03d6;
            case 47: goto L_0x03d4;
            case 48: goto L_0x03d2;
            default: goto L_0x03cd;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03d1, code lost:
        throw org.mozilla.javascript.Kit.codeBug();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03d2, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03d4, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03d6, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03d8, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03da, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03db, code lost:
        r13 = r11;
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03de, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03df, code lost:
        r13 = r11;
        r2 = -1;
        r12 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03e3, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03e4, code lost:
        r13 = r11;
        r2 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03e6, code lost:
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03e7, code lost:
        r11 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03e9, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03ea, code lost:
        r1 = getOffset(r9, r11);
        r11 = r11 + 2;
        r2 = getOffset(r9, r11) - 1;
        r12 = r1;
        r13 = r11 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03fa, code lost:
        pushProgState(r23, r12, r2, r7.cp, (org.mozilla.javascript.regexp.REBackTrackData) null, r17, r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0407, code lost:
        if (r11 == false) goto L_0x041d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0409, code lost:
        pushBackTrackState(r7, REOP_REPEAT, r13);
        r1 = r13 + 6;
        r2 = r1 + 1;
        r1 = r9[r1];
        r11 = r2;
        r16 = r13;
        r17 = 51;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0419, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x041f, code lost:
        if (r12 == 0) goto L_0x042d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0421, code lost:
        r1 = r13 + 6;
        r2 = r1 + 1;
        r1 = r9[r1];
        r11 = r2;
        r16 = r13;
        r17 = 52;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x042d, code lost:
        pushBackTrackState(r7, REOP_MINIMALREPEAT, r13);
        popProgState(r23);
        r13 = r13 + 4;
        r13 = r13 + getOffset(r9, r13);
        r11 = r13 + 1;
        r2 = r9[r13];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009a, code lost:
        r18 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        if (classMatcher(r7, r7.regexp.classList[r1], r3) == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c3, code lost:
        if (r3 != r1) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0118, code lost:
        r22 = r16;
        r16 = r11;
        r11 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01aa, code lost:
        r16 = r0;
        r17 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c5, code lost:
        r16 = r0;
        r17 = r1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean executeREBytecode(org.mozilla.javascript.regexp.REGlobalData r23, java.lang.String r24, int r25) {
        /*
            r7 = r23
            r8 = r25
            org.mozilla.javascript.regexp.RECompiled r0 = r7.regexp
            byte[] r9 = r0.program
            r10 = 0
            byte r11 = r9[r10]
            org.mozilla.javascript.regexp.RECompiled r0 = r7.regexp
            int r0 = r0.anchorCh
            r12 = 57
            r14 = 52
            r15 = 1
            r16 = 1
            if (r0 >= 0) goto L_0x004c
            boolean r0 = reopIsSimple(r11)
            if (r0 == 0) goto L_0x004c
        L_0x001e:
            int r0 = r7.cp
            if (r0 > r8) goto L_0x0044
            r6 = 1
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r9
            r4 = r16
            r5 = r25
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 < 0) goto L_0x0039
            int r16 = r0 + 1
            byte r11 = r9[r0]
            r0 = 1
            goto L_0x0045
        L_0x0039:
            int r0 = r7.skipped
            int r0 = r0 + r15
            r7.skipped = r0
            int r0 = r7.cp
            int r0 = r0 + r15
            r7.cp = r0
            goto L_0x001e
        L_0x0044:
            r0 = 0
        L_0x0045:
            if (r0 != 0) goto L_0x0048
            return r10
        L_0x0048:
            r2 = r11
            r11 = r16
            goto L_0x004e
        L_0x004c:
            r2 = r11
            r11 = 1
        L_0x004e:
            r16 = 0
            r17 = 57
            r18 = 0
        L_0x0054:
            boolean r0 = reopIsSimple(r2)
            if (r0 == 0) goto L_0x0073
            r6 = 1
            r0 = r23
            r1 = r24
            r3 = r9
            r4 = r11
            r5 = r25
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 < 0) goto L_0x006b
            r1 = 1
            goto L_0x006c
        L_0x006b:
            r1 = 0
        L_0x006c:
            if (r1 == 0) goto L_0x006f
            r11 = r0
        L_0x006f:
            r18 = r1
            goto L_0x0308
        L_0x0073:
            if (r2 == r12) goto L_0x0444
            r6 = -1
            switch(r2) {
                case 25: goto L_0x0287;
                case 26: goto L_0x0287;
                case 27: goto L_0x0287;
                case 28: goto L_0x0287;
                case 29: goto L_0x03b1;
                case 30: goto L_0x039c;
                case 31: goto L_0x0359;
                case 32: goto L_0x034e;
                default: goto L_0x0079;
            }
        L_0x0079:
            r5 = 44
            switch(r2) {
                case 41: goto L_0x02e3;
                case 42: goto L_0x02a4;
                case 43: goto L_0x028a;
                case 44: goto L_0x028a;
                case 45: goto L_0x0287;
                case 46: goto L_0x0287;
                case 47: goto L_0x0287;
                case 48: goto L_0x0287;
                case 49: goto L_0x027f;
                default: goto L_0x007e;
            }
        L_0x007e:
            switch(r2) {
                case 51: goto L_0x0192;
                case 52: goto L_0x00c6;
                case 53: goto L_0x0088;
                case 54: goto L_0x0088;
                case 55: goto L_0x0088;
                default: goto L_0x0081;
            }
        L_0x0081:
            java.lang.String r0 = "invalid bytecode"
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug(r0)
            throw r0
        L_0x0088:
            int r0 = getIndex(r9, r11)
            char r0 = (char) r0
            int r11 = r11 + 2
            int r1 = getIndex(r9, r11)
            char r1 = (char) r1
            int r11 = r11 + 2
            int r3 = r7.cp
            if (r3 != r8) goto L_0x009e
        L_0x009a:
            r18 = 0
            goto L_0x0308
        L_0x009e:
            int r3 = r7.cp
            r5 = r24
            char r3 = r5.charAt(r3)
            r4 = 55
            if (r2 != r4) goto L_0x00b9
            if (r3 == r0) goto L_0x0359
            org.mozilla.javascript.regexp.RECompiled r0 = r7.regexp
            org.mozilla.javascript.regexp.RECharSet[] r0 = r0.classList
            r0 = r0[r1]
            boolean r0 = classMatcher(r7, r0, r3)
            if (r0 != 0) goto L_0x0359
            goto L_0x009a
        L_0x00b9:
            r4 = 54
            if (r2 != r4) goto L_0x00c1
            char r3 = upcase(r3)
        L_0x00c1:
            if (r3 == r0) goto L_0x0359
            if (r3 == r1) goto L_0x0359
            goto L_0x009a
        L_0x00c6:
            r5 = r24
            org.mozilla.javascript.regexp.REProgState r4 = popProgState(r23)
            if (r18 != 0) goto L_0x0120
            int r0 = r4.max
            if (r0 == r6) goto L_0x00dd
            int r0 = r4.max
            if (r0 <= 0) goto L_0x00d7
            goto L_0x00dd
        L_0x00d7:
            int r0 = r4.continuationPc
            int r1 = r4.continuationOp
            goto L_0x01aa
        L_0x00dd:
            int r1 = r4.min
            int r2 = r4.max
            int r3 = r7.cp
            r16 = 0
            int r0 = r4.continuationOp
            int r4 = r4.continuationPc
            r17 = r0
            r0 = r23
            r19 = r4
            r4 = r16
            r5 = r17
            r12 = -1
            r6 = r19
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            int r0 = getIndex(r9, r11)
            int r1 = r11 + 2
            int r2 = getIndex(r9, r1)
            int r1 = r1 + 4
            r3 = 0
        L_0x0106:
            if (r3 >= r0) goto L_0x0110
            int r4 = r2 + r3
            r7.setParens(r4, r12, r10)
            int r3 = r3 + 1
            goto L_0x0106
        L_0x0110:
            int r16 = r1 + 1
            byte r2 = r9[r1]
        L_0x0114:
            r12 = 57
            r17 = 52
        L_0x0118:
            r22 = r16
            r16 = r11
            r11 = r22
            goto L_0x0054
        L_0x0120:
            r12 = -1
            int r0 = r4.min
            if (r0 != 0) goto L_0x0131
            int r0 = r7.cp
            int r1 = r4.index
            if (r0 != r1) goto L_0x0131
            int r0 = r4.continuationPc
            int r1 = r4.continuationOp
            goto L_0x01c5
        L_0x0131:
            int r0 = r4.min
            int r1 = r4.max
            if (r0 == 0) goto L_0x0139
            int r0 = r0 + -1
        L_0x0139:
            r16 = r0
            if (r1 == r12) goto L_0x013f
            int r1 = r1 + -1
        L_0x013f:
            r2 = r1
            int r3 = r7.cp
            r5 = 0
            int r6 = r4.continuationOp
            int r1 = r4.continuationPc
            r0 = r23
            r17 = r1
            r1 = r16
            r13 = r4
            r4 = r5
            r5 = r6
            r6 = r17
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            if (r16 == 0) goto L_0x0173
            int r0 = getIndex(r9, r11)
            int r1 = r11 + 2
            int r2 = getIndex(r9, r1)
            int r1 = r1 + 4
            r3 = 0
        L_0x0164:
            if (r3 >= r0) goto L_0x016e
            int r4 = r2 + r3
            r7.setParens(r4, r12, r10)
            int r3 = r3 + 1
            goto L_0x0164
        L_0x016e:
            int r16 = r1 + 1
            byte r2 = r9[r1]
            goto L_0x0114
        L_0x0173:
            int r0 = r13.continuationPc
            int r1 = r13.continuationOp
            pushBackTrackState(r7, r14, r11)
            popProgState(r23)
            int r11 = r11 + 4
            int r2 = getOffset(r9, r11)
            int r11 = r11 + r2
            int r2 = r11 + 1
            byte r3 = r9[r11]
            r16 = r0
            r17 = r1
            r11 = r2
            r2 = r3
            r12 = 57
            goto L_0x0054
        L_0x0192:
            r12 = -1
        L_0x0193:
            org.mozilla.javascript.regexp.REProgState r13 = popProgState(r23)
            if (r18 != 0) goto L_0x01b0
            int r0 = r13.min
            if (r0 != 0) goto L_0x019f
            r18 = 1
        L_0x019f:
            int r0 = r13.continuationPc
            int r1 = r13.continuationOp
            int r11 = r11 + 4
            int r2 = getOffset(r9, r11)
            int r11 = r11 + r2
        L_0x01aa:
            r16 = r0
            r17 = r1
            goto L_0x0308
        L_0x01b0:
            int r0 = r13.min
            if (r0 != 0) goto L_0x01cb
            int r0 = r7.cp
            int r1 = r13.index
            if (r0 != r1) goto L_0x01cb
            int r0 = r13.continuationPc
            int r1 = r13.continuationOp
            int r11 = r11 + 4
            int r2 = getOffset(r9, r11)
            int r11 = r11 + r2
        L_0x01c5:
            r16 = r0
            r17 = r1
            goto L_0x009a
        L_0x01cb:
            int r0 = r13.min
            int r1 = r13.max
            if (r0 == 0) goto L_0x01d3
            int r0 = r0 + -1
        L_0x01d3:
            r16 = r0
            if (r1 == r12) goto L_0x01d9
            int r1 = r1 + -1
        L_0x01d9:
            r17 = r1
            if (r17 != 0) goto L_0x01f0
            int r0 = r13.continuationPc
            int r1 = r13.continuationOp
            int r11 = r11 + 4
            int r2 = getOffset(r9, r11)
            int r11 = r11 + r2
            r16 = r0
            r17 = r1
            r18 = 1
            goto L_0x0308
        L_0x01f0:
            int r0 = r11 + 6
            byte r2 = r9[r0]
            int r6 = r7.cp
            boolean r1 = reopIsSimple(r2)
            if (r1 == 0) goto L_0x022e
            int r4 = r0 + 1
            r18 = 1
            r0 = r23
            r1 = r24
            r3 = r9
            r5 = r25
            r20 = r6
            r6 = r18
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 >= 0) goto L_0x0229
            if (r16 != 0) goto L_0x0215
            r0 = 1
            goto L_0x0216
        L_0x0215:
            r0 = 0
        L_0x0216:
            int r1 = r13.continuationPc
            int r2 = r13.continuationOp
            int r11 = r11 + 4
            int r3 = getOffset(r9, r11)
            int r11 = r11 + r3
            r18 = r0
            r16 = r1
            r17 = r2
            goto L_0x0308
        L_0x0229:
            r18 = r0
            r21 = 1
            goto L_0x0234
        L_0x022e:
            r20 = r6
            r21 = r18
            r18 = r0
        L_0x0234:
            r4 = 0
            int r5 = r13.continuationOp
            int r6 = r13.continuationPc
            r0 = r23
            r1 = r16
            r2 = r17
            r3 = r20
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            if (r16 != 0) goto L_0x0269
            r1 = 51
            int r4 = r13.continuationOp
            int r5 = r13.continuationPc
            r0 = r23
            r2 = r11
            r3 = r20
            pushBackTrackState(r0, r1, r2, r3, r4, r5)
            int r0 = getIndex(r9, r11)
            int r1 = r11 + 2
            int r1 = getIndex(r9, r1)
            r2 = 0
        L_0x025f:
            if (r2 >= r0) goto L_0x0269
            int r3 = r1 + r2
            r7.setParens(r3, r12, r10)
            int r2 = r2 + 1
            goto L_0x025f
        L_0x0269:
            byte r0 = r9[r18]
            r1 = 49
            if (r0 == r1) goto L_0x027b
            int r16 = r18 + 1
            byte r2 = r9[r18]
            r18 = r21
            r12 = 57
            r17 = 51
            goto L_0x0118
        L_0x027b:
            r18 = r21
            goto L_0x0193
        L_0x027f:
            r11 = r16
            r2 = r17
            r18 = 1
            goto L_0x0054
        L_0x0287:
            r12 = -1
            goto L_0x03c7
        L_0x028a:
            org.mozilla.javascript.regexp.REProgState r0 = popProgState(r23)
            int r1 = r0.index
            r7.cp = r1
            org.mozilla.javascript.regexp.REBackTrackData r1 = r0.backTrack
            r7.backTrackStackTop = r1
            int r1 = r0.continuationPc
            int r0 = r0.continuationOp
            if (r2 != r5) goto L_0x029e
            r18 = r18 ^ 1
        L_0x029e:
            r17 = r0
            r16 = r1
            goto L_0x0308
        L_0x02a4:
            int r0 = getIndex(r9, r11)
            int r12 = r11 + r0
            int r11 = r11 + 2
            int r13 = r11 + 1
            byte r11 = r9[r11]
            boolean r0 = reopIsSimple(r11)
            if (r0 == 0) goto L_0x02cd
            r6 = 0
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r9
            r4 = r13
            r14 = 44
            r5 = r25
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 < 0) goto L_0x02cf
            byte r0 = r9[r0]
            if (r0 != r14) goto L_0x02cf
            goto L_0x0305
        L_0x02cd:
            r14 = 44
        L_0x02cf:
            r1 = 0
            r2 = 0
            int r3 = r7.cp
            org.mozilla.javascript.regexp.REBackTrackData r4 = r7.backTrackStackTop
            r0 = r23
            r5 = r17
            r6 = r16
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            pushBackTrackState(r7, r14, r12)
            goto L_0x034c
        L_0x02e3:
            int r0 = getIndex(r9, r11)
            int r12 = r11 + r0
            int r11 = r11 + 2
            int r13 = r11 + 1
            byte r11 = r9[r11]
            boolean r0 = reopIsSimple(r11)
            if (r0 == 0) goto L_0x0338
            r6 = 0
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r9
            r4 = r13
            r5 = r25
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 >= 0) goto L_0x0338
        L_0x0305:
            r11 = r13
            goto L_0x009a
        L_0x0308:
            if (r18 != 0) goto L_0x0332
            org.mozilla.javascript.regexp.REBackTrackData r0 = r7.backTrackStackTop
            if (r0 == 0) goto L_0x0331
            org.mozilla.javascript.regexp.REBackTrackData r1 = r0.previous
            r7.backTrackStackTop = r1
            long[] r1 = r0.parens
            r7.parens = r1
            int r1 = r0.cp
            r7.cp = r1
            org.mozilla.javascript.regexp.REProgState r1 = r0.stateStackTop
            r7.stateStackTop = r1
            int r1 = r0.continuationOp
            int r2 = r0.continuationPc
            int r11 = r0.pc
            int r0 = r0.op
            r17 = r1
            r16 = r2
            r12 = 57
            r14 = 52
            r2 = r0
            goto L_0x0054
        L_0x0331:
            return r10
        L_0x0332:
            int r0 = r11 + 1
            byte r2 = r9[r11]
            goto L_0x03c0
        L_0x0338:
            r1 = 0
            r2 = 0
            int r3 = r7.cp
            org.mozilla.javascript.regexp.REBackTrackData r4 = r7.backTrackStackTop
            r0 = r23
            r5 = r17
            r6 = r16
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            r0 = 43
            pushBackTrackState(r7, r0, r12)
        L_0x034c:
            r2 = r11
            goto L_0x039a
        L_0x034e:
            int r0 = getOffset(r9, r11)
            int r11 = r11 + r0
            int r0 = r11 + 1
            byte r2 = r9[r11]
            goto L_0x03c0
        L_0x0359:
            int r0 = getOffset(r9, r11)
            int r12 = r11 + r0
            int r11 = r11 + 2
            int r4 = r11 + 1
            byte r2 = r9[r11]
            int r11 = r7.cp
            boolean r0 = reopIsSimple(r2)
            if (r0 == 0) goto L_0x0389
            r6 = 1
            r0 = r23
            r1 = r24
            r3 = r9
            r5 = r25
            int r0 = simpleMatch(r0, r1, r2, r3, r4, r5, r6)
            if (r0 >= 0) goto L_0x0380
            int r11 = r12 + 1
            byte r2 = r9[r12]
            goto L_0x03c1
        L_0x0380:
            int r1 = r0 + 1
            byte r0 = r9[r0]
            r6 = r0
            r13 = r1
            r18 = 1
            goto L_0x038b
        L_0x0389:
            r6 = r2
            r13 = r4
        L_0x038b:
            int r2 = r12 + 1
            byte r1 = r9[r12]
            r0 = r23
            r3 = r11
            r4 = r17
            r5 = r16
            pushBackTrackState(r0, r1, r2, r3, r4, r5)
            r2 = r6
        L_0x039a:
            r11 = r13
            goto L_0x03c1
        L_0x039c:
            int r0 = getIndex(r9, r11)
            int r11 = r11 + 2
            int r1 = r7.parensIndex(r0)
            int r2 = r7.cp
            int r2 = r2 - r1
            r7.setParens(r0, r1, r2)
            int r0 = r11 + 1
            byte r2 = r9[r11]
            goto L_0x03c0
        L_0x03b1:
            int r0 = getIndex(r9, r11)
            int r11 = r11 + 2
            int r1 = r7.cp
            r7.setParens(r0, r1, r10)
            int r0 = r11 + 1
            byte r2 = r9[r11]
        L_0x03c0:
            r11 = r0
        L_0x03c1:
            r12 = 57
            r14 = 52
            goto L_0x0054
        L_0x03c7:
            switch(r2) {
                case 25: goto L_0x03e9;
                case 26: goto L_0x03e3;
                case 27: goto L_0x03de;
                case 28: goto L_0x03da;
                default: goto L_0x03ca;
            }
        L_0x03ca:
            switch(r2) {
                case 45: goto L_0x03d8;
                case 46: goto L_0x03d6;
                case 47: goto L_0x03d4;
                case 48: goto L_0x03d2;
                default: goto L_0x03cd;
            }
        L_0x03cd:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x03d2:
            r0 = 0
            goto L_0x03ea
        L_0x03d4:
            r0 = 0
            goto L_0x03db
        L_0x03d6:
            r0 = 0
            goto L_0x03df
        L_0x03d8:
            r0 = 0
            goto L_0x03e4
        L_0x03da:
            r0 = 1
        L_0x03db:
            r13 = r11
            r2 = 1
            goto L_0x03e6
        L_0x03de:
            r0 = 1
        L_0x03df:
            r13 = r11
            r2 = -1
            r12 = 1
            goto L_0x03e7
        L_0x03e3:
            r0 = 1
        L_0x03e4:
            r13 = r11
            r2 = -1
        L_0x03e6:
            r12 = 0
        L_0x03e7:
            r11 = r0
            goto L_0x03fa
        L_0x03e9:
            r0 = 1
        L_0x03ea:
            int r1 = getOffset(r9, r11)
            int r11 = r11 + 2
            int r2 = getOffset(r9, r11)
            int r2 = r2 - r15
            int r11 = r11 + 2
            r12 = r1
            r13 = r11
            goto L_0x03e7
        L_0x03fa:
            int r3 = r7.cp
            r4 = 0
            r0 = r23
            r1 = r12
            r5 = r17
            r6 = r16
            pushProgState(r0, r1, r2, r3, r4, r5, r6)
            if (r11 == 0) goto L_0x041d
            r0 = 51
            pushBackTrackState(r7, r0, r13)
            int r1 = r13 + 6
            int r2 = r1 + 1
            byte r1 = r9[r1]
            r11 = r2
            r16 = r13
            r17 = 51
        L_0x0419:
            r2 = r1
            r1 = 52
            goto L_0x03c1
        L_0x041d:
            r0 = 51
            if (r12 == 0) goto L_0x042d
            int r1 = r13 + 6
            int r2 = r1 + 1
            byte r1 = r9[r1]
            r11 = r2
            r16 = r13
            r17 = 52
            goto L_0x0419
        L_0x042d:
            r1 = 52
            pushBackTrackState(r7, r1, r13)
            popProgState(r23)
            int r13 = r13 + 4
            int r2 = getOffset(r9, r13)
            int r13 = r13 + r2
            int r2 = r13 + 1
            byte r3 = r9[r13]
            r11 = r2
            r2 = r3
            goto L_0x03c1
        L_0x0444:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.executeREBytecode(org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int):boolean");
    }

    private static boolean matchRegExp(REGlobalData rEGlobalData, RECompiled rECompiled, String str, int i, int i2, boolean z) {
        if (rECompiled.parenCount != 0) {
            rEGlobalData.parens = new long[rECompiled.parenCount];
        } else {
            rEGlobalData.parens = null;
        }
        rEGlobalData.backTrackStackTop = null;
        rEGlobalData.stateStackTop = null;
        rEGlobalData.multiline = z || (rECompiled.flags & 4) != 0;
        rEGlobalData.regexp = rECompiled;
        int i3 = rEGlobalData.regexp.anchorCh;
        int i4 = i;
        while (i4 <= i2) {
            if (i3 >= 0) {
                while (i4 != i2) {
                    char charAt = str.charAt(i4);
                    if (charAt != i3 && ((rEGlobalData.regexp.flags & 2) == 0 || upcase(charAt) != upcase((char) i3))) {
                        i4++;
                    }
                }
                return false;
            }
            rEGlobalData.cp = i4;
            rEGlobalData.skipped = i4 - i;
            for (int i5 = 0; i5 < rECompiled.parenCount; i5++) {
                rEGlobalData.parens[i5] = -1;
            }
            boolean executeREBytecode = executeREBytecode(rEGlobalData, str, i2);
            rEGlobalData.backTrackStackTop = null;
            rEGlobalData.stateStackTop = null;
            if (executeREBytecode) {
                return true;
            }
            if (i3 != -2 || rEGlobalData.multiline) {
                i4 = rEGlobalData.skipped + i + 1;
            } else {
                rEGlobalData.skipped = i2;
                return false;
            }
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [java.lang.Boolean] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object executeRegExp(org.mozilla.javascript.Context r17, org.mozilla.javascript.Scriptable r18, org.mozilla.javascript.regexp.RegExpImpl r19, java.lang.String r20, int[] r21, int r22) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            r8 = r20
            r9 = r22
            org.mozilla.javascript.regexp.REGlobalData r10 = new org.mozilla.javascript.regexp.REGlobalData
            r10.<init>()
            r11 = 0
            r2 = r21[r11]
            int r12 = r20.length()
            if (r2 <= r12) goto L_0x0018
            r13 = r12
            goto L_0x0019
        L_0x0018:
            r13 = r2
        L_0x0019:
            org.mozilla.javascript.regexp.RECompiled r3 = r0.re
            boolean r7 = r1.multiline
            r2 = r10
            r4 = r20
            r5 = r13
            r6 = r12
            boolean r2 = matchRegExp(r2, r3, r4, r5, r6, r7)
            r3 = 0
            if (r2 != 0) goto L_0x0030
            r1 = 2
            if (r9 == r1) goto L_0x002d
            return r3
        L_0x002d:
            java.lang.Object r1 = org.mozilla.javascript.Undefined.instance
            return r1
        L_0x0030:
            int r2 = r10.cp
            r21[r11] = r2
            int r4 = r10.skipped
            int r4 = r4 + r13
            int r4 = r2 - r4
            int r5 = r2 - r4
            if (r9 != 0) goto L_0x0044
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r14 = r3
            r7 = r6
            r6 = r17
            goto L_0x0058
        L_0x0044:
            r6 = r17
            r7 = r18
            org.mozilla.javascript.Scriptable r7 = r6.newArray((org.mozilla.javascript.Scriptable) r7, (int) r11)
            r14 = r7
            org.mozilla.javascript.Scriptable r14 = (org.mozilla.javascript.Scriptable) r14
            int r15 = r5 + r4
            java.lang.String r15 = r8.substring(r5, r15)
            r14.put((int) r11, (org.mozilla.javascript.Scriptable) r14, (java.lang.Object) r15)
        L_0x0058:
            org.mozilla.javascript.regexp.RECompiled r15 = r0.re
            int r15 = r15.parenCount
            if (r15 != 0) goto L_0x0065
            r1.parens = r3
            org.mozilla.javascript.regexp.SubString r3 = org.mozilla.javascript.regexp.SubString.emptySubString
            r1.lastParen = r3
            goto L_0x00a4
        L_0x0065:
            org.mozilla.javascript.regexp.RECompiled r15 = r0.re
            int r15 = r15.parenCount
            org.mozilla.javascript.regexp.SubString[] r15 = new org.mozilla.javascript.regexp.SubString[r15]
            r1.parens = r15
            r15 = 0
        L_0x006e:
            org.mozilla.javascript.regexp.RECompiled r11 = r0.re
            int r11 = r11.parenCount
            if (r15 >= r11) goto L_0x00a2
            int r11 = r10.parensIndex(r15)
            r0 = -1
            if (r11 == r0) goto L_0x0094
            int r0 = r10.parensLength(r15)
            org.mozilla.javascript.regexp.SubString r3 = new org.mozilla.javascript.regexp.SubString
            r3.<init>(r8, r11, r0)
            org.mozilla.javascript.regexp.SubString[] r0 = r1.parens
            r0[r15] = r3
            if (r9 == 0) goto L_0x009d
            int r0 = r15 + 1
            java.lang.String r11 = r3.toString()
            r14.put((int) r0, (org.mozilla.javascript.Scriptable) r14, (java.lang.Object) r11)
            goto L_0x009d
        L_0x0094:
            if (r9 == 0) goto L_0x009d
            int r0 = r15 + 1
            java.lang.Object r11 = org.mozilla.javascript.Undefined.instance
            r14.put((int) r0, (org.mozilla.javascript.Scriptable) r14, (java.lang.Object) r11)
        L_0x009d:
            int r15 = r15 + 1
            r0 = r16
            goto L_0x006e
        L_0x00a2:
            r1.lastParen = r3
        L_0x00a4:
            if (r9 == 0) goto L_0x00b7
            int r0 = r10.skipped
            int r0 = r0 + r13
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r3 = "index"
            r14.put((java.lang.String) r3, (org.mozilla.javascript.Scriptable) r14, (java.lang.Object) r0)
            java.lang.String r0 = "input"
            r14.put((java.lang.String) r0, (org.mozilla.javascript.Scriptable) r14, (java.lang.Object) r8)
        L_0x00b7:
            org.mozilla.javascript.regexp.SubString r0 = r1.lastMatch
            if (r0 != 0) goto L_0x00d0
            org.mozilla.javascript.regexp.SubString r0 = new org.mozilla.javascript.regexp.SubString
            r0.<init>()
            r1.lastMatch = r0
            org.mozilla.javascript.regexp.SubString r0 = new org.mozilla.javascript.regexp.SubString
            r0.<init>()
            r1.leftContext = r0
            org.mozilla.javascript.regexp.SubString r0 = new org.mozilla.javascript.regexp.SubString
            r0.<init>()
            r1.rightContext = r0
        L_0x00d0:
            org.mozilla.javascript.regexp.SubString r0 = r1.lastMatch
            r0.str = r8
            org.mozilla.javascript.regexp.SubString r0 = r1.lastMatch
            r0.index = r5
            org.mozilla.javascript.regexp.SubString r0 = r1.lastMatch
            r0.length = r4
            org.mozilla.javascript.regexp.SubString r0 = r1.leftContext
            r0.str = r8
            int r0 = r17.getLanguageVersion()
            r3 = 120(0x78, float:1.68E-43)
            if (r0 != r3) goto L_0x00f3
            org.mozilla.javascript.regexp.SubString r0 = r1.leftContext
            r0.index = r13
            org.mozilla.javascript.regexp.SubString r0 = r1.leftContext
            int r3 = r10.skipped
            r0.length = r3
            goto L_0x00ff
        L_0x00f3:
            org.mozilla.javascript.regexp.SubString r0 = r1.leftContext
            r3 = 0
            r0.index = r3
            org.mozilla.javascript.regexp.SubString r0 = r1.leftContext
            int r3 = r10.skipped
            int r13 = r13 + r3
            r0.length = r13
        L_0x00ff:
            org.mozilla.javascript.regexp.SubString r0 = r1.rightContext
            r0.str = r8
            org.mozilla.javascript.regexp.SubString r0 = r1.rightContext
            r0.index = r2
            org.mozilla.javascript.regexp.SubString r0 = r1.rightContext
            int r12 = r12 - r2
            r0.length = r12
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.executeRegExp(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.regexp.RegExpImpl, java.lang.String, int[], int):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public int getFlags() {
        return this.re.flags;
    }

    private static void reportWarning(Context context, String str, String str2) {
        if (context.hasFeature(11)) {
            Context.reportWarning(ScriptRuntime.getMessage1(str, str2));
        }
    }

    private static void reportError(String str, String str2) {
        throw ScriptRuntime.constructError("SyntaxError", ScriptRuntime.getMessage1(str, str2));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findInstanceIdInfo(java.lang.String r10) {
        /*
            r9 = this;
            int r0 = r10.length()
            r1 = 5
            r2 = 2
            r3 = 1
            r4 = 4
            r5 = 3
            r6 = 6
            r7 = 0
            if (r0 != r6) goto L_0x0021
            char r0 = r10.charAt(r7)
            r8 = 103(0x67, float:1.44E-43)
            if (r0 != r8) goto L_0x0019
            java.lang.String r0 = "global"
            r8 = 3
            goto L_0x0043
        L_0x0019:
            r8 = 115(0x73, float:1.61E-43)
            if (r0 != r8) goto L_0x0041
            java.lang.String r0 = "source"
            r8 = 2
            goto L_0x0043
        L_0x0021:
            r8 = 9
            if (r0 != r8) goto L_0x0039
            char r0 = r10.charAt(r7)
            r8 = 108(0x6c, float:1.51E-43)
            if (r0 != r8) goto L_0x0031
            java.lang.String r0 = "lastIndex"
            r8 = 1
            goto L_0x0043
        L_0x0031:
            r8 = 109(0x6d, float:1.53E-43)
            if (r0 != r8) goto L_0x0041
            java.lang.String r0 = "multiline"
            r8 = 5
            goto L_0x0043
        L_0x0039:
            r8 = 10
            if (r0 != r8) goto L_0x0041
            java.lang.String r0 = "ignoreCase"
            r8 = 4
            goto L_0x0043
        L_0x0041:
            r0 = 0
            r8 = 0
        L_0x0043:
            if (r0 == 0) goto L_0x004e
            if (r0 == r10) goto L_0x004e
            boolean r0 = r0.equals(r10)
            if (r0 != 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r7 = r8
        L_0x004f:
            if (r7 != 0) goto L_0x0056
            int r10 = super.findInstanceIdInfo(r10)
            return r10
        L_0x0056:
            if (r7 == r3) goto L_0x0068
            if (r7 == r2) goto L_0x0067
            if (r7 == r5) goto L_0x0067
            if (r7 == r4) goto L_0x0067
            if (r7 != r1) goto L_0x0061
            goto L_0x0067
        L_0x0061:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L_0x0067:
            r6 = 7
        L_0x0068:
            int r10 = instanceIdInfo(r6, r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.findInstanceIdInfo(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        if (i == 1) {
            return "lastIndex";
        }
        if (i == 2) {
            return "source";
        }
        if (i == 3) {
            return "global";
        }
        if (i != 4) {
            return i != 5 ? super.getInstanceIdName(i) : "multiline";
        }
        return "ignoreCase";
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        boolean z = true;
        if (i == 1) {
            return ScriptRuntime.wrapNumber(this.lastIndex);
        }
        if (i == 2) {
            return new String(this.re.source);
        }
        if (i == 3) {
            if ((this.re.flags & 1) == 0) {
                z = false;
            }
            return ScriptRuntime.wrapBoolean(z);
        } else if (i == 4) {
            if ((this.re.flags & 2) == 0) {
                z = false;
            }
            return ScriptRuntime.wrapBoolean(z);
        } else if (i != 5) {
            return super.getInstanceIdValue(i);
        } else {
            if ((this.re.flags & 4) == 0) {
                z = false;
            }
            return ScriptRuntime.wrapBoolean(z);
        }
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            this.lastIndex = ScriptRuntime.toNumber(obj);
        } else if (i != 2 && i != 3 && i != 4 && i != 5) {
            super.setInstanceIdValue(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "compile";
                break;
            case 2:
                str2 = "toString";
                break;
            case 3:
                str2 = "toSource";
                break;
            case 4:
                str = "exec";
                break;
            case 5:
                str = "test";
                break;
            case 6:
                str = "prefix";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        str = str2;
        i2 = 0;
        initPrototypeMethod(REGEXP_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(REGEXP_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return realThis(scriptable2, idFunctionObject).compile(context, scriptable, objArr);
            case 2:
            case 3:
                return realThis(scriptable2, idFunctionObject).toString();
            case 4:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 1);
            case 5:
                return Boolean.TRUE.equals(realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 0)) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static NativeRegExp realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeRegExp) {
            return (NativeRegExp) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = r7.length()
            r1 = 116(0x74, float:1.63E-43)
            r2 = 3
            r3 = 6
            r4 = 4
            r5 = 0
            if (r0 == r4) goto L_0x002f
            if (r0 == r3) goto L_0x002b
            r3 = 7
            if (r0 == r3) goto L_0x0027
            r3 = 8
            if (r0 == r3) goto L_0x0016
            goto L_0x0041
        L_0x0016:
            char r0 = r7.charAt(r2)
            r3 = 111(0x6f, float:1.56E-43)
            if (r0 != r3) goto L_0x0021
            java.lang.String r0 = "toSource"
            goto L_0x0043
        L_0x0021:
            if (r0 != r1) goto L_0x0041
            r2 = 2
            java.lang.String r0 = "toString"
            goto L_0x0043
        L_0x0027:
            r2 = 1
            java.lang.String r0 = "compile"
            goto L_0x0043
        L_0x002b:
            java.lang.String r0 = "prefix"
            r2 = 6
            goto L_0x0043
        L_0x002f:
            char r0 = r7.charAt(r5)
            r2 = 101(0x65, float:1.42E-43)
            if (r0 != r2) goto L_0x003b
            java.lang.String r0 = "exec"
            r2 = 4
            goto L_0x0043
        L_0x003b:
            if (r0 != r1) goto L_0x0041
            r2 = 5
            java.lang.String r0 = "test"
            goto L_0x0043
        L_0x0041:
            r0 = 0
            r2 = 0
        L_0x0043:
            if (r0 == 0) goto L_0x004e
            if (r0 == r7) goto L_0x004e
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r5 = r2
        L_0x004f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.findPrototypeId(java.lang.String):int");
    }
}
