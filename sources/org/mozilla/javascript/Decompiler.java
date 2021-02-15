package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import okhttp3.internal.ws.WebSocketProtocol;

public class Decompiler {
    public static final int CASE_GAP_PROP = 3;
    private static final int FUNCTION_END = 164;
    public static final int INDENT_GAP_PROP = 2;
    public static final int INITIAL_INDENT_PROP = 1;
    public static final int ONLY_BODY_FLAG = 1;
    public static final int TO_SOURCE_FLAG = 2;
    private static final boolean printSource = false;
    private char[] sourceBuffer = new char[128];
    private int sourceTop;

    /* access modifiers changed from: package-private */
    public String getEncodedSource() {
        return sourceToString(0);
    }

    /* access modifiers changed from: package-private */
    public int getCurrentOffset() {
        return this.sourceTop;
    }

    /* access modifiers changed from: package-private */
    public int markFunctionStart(int i) {
        int currentOffset = getCurrentOffset();
        addToken(109);
        append((char) i);
        return currentOffset;
    }

    /* access modifiers changed from: package-private */
    public int markFunctionEnd(int i) {
        int currentOffset = getCurrentOffset();
        append(164);
        return currentOffset;
    }

    /* access modifiers changed from: package-private */
    public void addToken(int i) {
        if (i < 0 || i > 163) {
            throw new IllegalArgumentException();
        }
        append((char) i);
    }

    /* access modifiers changed from: package-private */
    public void addEOL(int i) {
        if (i < 0 || i > 163) {
            throw new IllegalArgumentException();
        }
        append((char) i);
        append(1);
    }

    /* access modifiers changed from: package-private */
    public void addName(String str) {
        addToken(39);
        appendString(str);
    }

    /* access modifiers changed from: package-private */
    public void addString(String str) {
        addToken(41);
        appendString(str);
    }

    /* access modifiers changed from: package-private */
    public void addRegexp(String str, String str2) {
        addToken(48);
        appendString('/' + str + '/' + str2);
    }

    /* access modifiers changed from: package-private */
    public void addNumber(double d) {
        addToken(40);
        long j = (long) d;
        if (((double) j) != d) {
            long doubleToLongBits = Double.doubleToLongBits(d);
            append(ASCIIPropertyListParser.DATA_GSDATE_BEGIN_TOKEN);
            append((char) ((int) (doubleToLongBits >> 48)));
            append((char) ((int) (doubleToLongBits >> 32)));
            append((char) ((int) (doubleToLongBits >> 16)));
            append((char) ((int) doubleToLongBits));
            return;
        }
        if (j < 0) {
            Kit.codeBug();
        }
        if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            append('S');
            append((char) ((int) j));
            return;
        }
        append('J');
        append((char) ((int) (j >> 48)));
        append((char) ((int) (j >> 32)));
        append((char) ((int) (j >> 16)));
        append((char) ((int) j));
    }

    private void appendString(String str) {
        int length = str.length();
        int i = this.sourceTop + (length >= 32768 ? 2 : 1) + length;
        if (i > this.sourceBuffer.length) {
            increaseSourceCapacity(i);
        }
        if (length >= 32768) {
            char[] cArr = this.sourceBuffer;
            int i2 = this.sourceTop;
            cArr[i2] = (char) (32768 | (length >>> 16));
            this.sourceTop = i2 + 1;
        }
        char[] cArr2 = this.sourceBuffer;
        int i3 = this.sourceTop;
        cArr2[i3] = (char) length;
        int i4 = i3 + 1;
        this.sourceTop = i4;
        str.getChars(0, length, cArr2, i4);
        this.sourceTop = i;
    }

    private void append(char c) {
        int i = this.sourceTop;
        if (i == this.sourceBuffer.length) {
            increaseSourceCapacity(i + 1);
        }
        char[] cArr = this.sourceBuffer;
        int i2 = this.sourceTop;
        cArr[i2] = c;
        this.sourceTop = i2 + 1;
    }

    private void increaseSourceCapacity(int i) {
        if (i <= this.sourceBuffer.length) {
            Kit.codeBug();
        }
        int length = this.sourceBuffer.length * 2;
        if (length >= i) {
            i = length;
        }
        char[] cArr = new char[i];
        System.arraycopy(this.sourceBuffer, 0, cArr, 0, this.sourceTop);
        this.sourceBuffer = cArr;
    }

    private String sourceToString(int i) {
        if (i < 0 || this.sourceTop < i) {
            Kit.codeBug();
        }
        return new String(this.sourceBuffer, i, this.sourceTop - i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0247, code lost:
        if (r4 != 164) goto L_0x042e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x017b, code lost:
        r14 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String decompile(java.lang.String r17, int r18, org.mozilla.javascript.UintMap r19) {
        /*
            r0 = r17
            r1 = r19
            int r2 = r17.length()
            if (r2 != 0) goto L_0x000d
            java.lang.String r0 = ""
            return r0
        L_0x000d:
            r3 = 1
            r4 = 0
            int r5 = r1.getInt(r3, r4)
            if (r5 < 0) goto L_0x04a9
            r6 = 2
            r7 = 4
            int r8 = r1.getInt(r6, r7)
            if (r8 < 0) goto L_0x04a3
            r9 = 3
            int r1 = r1.getInt(r9, r6)
            if (r1 < 0) goto L_0x049d
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            r10 = r18 & 1
            if (r10 == 0) goto L_0x002f
            r10 = 1
            goto L_0x0030
        L_0x002f:
            r10 = 0
        L_0x0030:
            r11 = r18 & 2
            if (r11 == 0) goto L_0x0036
            r11 = 1
            goto L_0x0037
        L_0x0036:
            r11 = 0
        L_0x0037:
            char r12 = r0.charAt(r4)
            r13 = 136(0x88, float:1.9E-43)
            if (r12 != r13) goto L_0x0042
            r12 = -1
            r13 = 1
            goto L_0x0047
        L_0x0042:
            char r12 = r0.charAt(r3)
            r13 = 0
        L_0x0047:
            r14 = 40
            r15 = 10
            r4 = 32
            if (r11 != 0) goto L_0x005b
            r9.append(r15)
            r15 = 0
        L_0x0053:
            if (r15 >= r5) goto L_0x0060
            r9.append(r4)
            int r15 = r15 + 1
            goto L_0x0053
        L_0x005b:
            if (r12 != r6) goto L_0x0060
            r9.append(r14)
        L_0x0060:
            r15 = 0
            r16 = 0
        L_0x0063:
            if (r13 >= r2) goto L_0x0486
            char r14 = r0.charAt(r13)
            r6 = 39
            if (r14 == r3) goto L_0x042a
            if (r14 == r7) goto L_0x0415
            r7 = 50
            if (r14 == r7) goto L_0x040d
            r7 = 66
            r4 = 58
            if (r14 == r7) goto L_0x0407
            r7 = 72
            if (r14 == r7) goto L_0x03ff
            r7 = 160(0xa0, float:2.24E-43)
            if (r14 == r7) goto L_0x03f7
            r7 = 164(0xa4, float:2.3E-43)
            if (r14 == r7) goto L_0x021d
            r7 = 52
            if (r14 == r7) goto L_0x03ef
            r7 = 53
            if (r14 == r7) goto L_0x03e7
            r7 = 143(0x8f, float:2.0E-43)
            if (r14 == r7) goto L_0x03df
            r7 = 144(0x90, float:2.02E-43)
            if (r14 == r7) goto L_0x03d7
            r7 = 146(0x92, float:2.05E-43)
            if (r14 == r7) goto L_0x03cf
            r7 = 147(0x93, float:2.06E-43)
            if (r14 == r7) goto L_0x03c7
            switch(r14) {
                case 9: goto L_0x03be;
                case 10: goto L_0x03b5;
                case 11: goto L_0x03ac;
                case 12: goto L_0x03a3;
                case 13: goto L_0x039a;
                case 14: goto L_0x0391;
                case 15: goto L_0x0388;
                case 16: goto L_0x037f;
                case 17: goto L_0x0376;
                case 18: goto L_0x036d;
                case 19: goto L_0x0364;
                case 20: goto L_0x035b;
                case 21: goto L_0x0352;
                case 22: goto L_0x0349;
                case 23: goto L_0x0340;
                case 24: goto L_0x0337;
                case 25: goto L_0x032e;
                case 26: goto L_0x0325;
                case 27: goto L_0x031c;
                case 28: goto L_0x0313;
                case 29: goto L_0x030a;
                case 30: goto L_0x0301;
                case 31: goto L_0x02f8;
                case 32: goto L_0x02ef;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            switch(r14) {
                case 39: goto L_0x02df;
                case 40: goto L_0x02d6;
                case 41: goto L_0x02cd;
                case 42: goto L_0x02c4;
                case 43: goto L_0x02bb;
                case 44: goto L_0x02b2;
                case 45: goto L_0x02a9;
                case 46: goto L_0x02a0;
                case 47: goto L_0x0297;
                case 48: goto L_0x02df;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            switch(r14) {
                case 81: goto L_0x028e;
                case 82: goto L_0x027a;
                case 83: goto L_0x0271;
                case 84: goto L_0x0268;
                case 85: goto L_0x0256;
                case 86: goto L_0x0228;
                case 87: goto L_0x0221;
                case 88: goto L_0x020b;
                case 89: goto L_0x0205;
                case 90: goto L_0x01ff;
                case 91: goto L_0x01f9;
                case 92: goto L_0x01f3;
                case 93: goto L_0x01ed;
                case 94: goto L_0x01e7;
                case 95: goto L_0x01e1;
                case 96: goto L_0x01db;
                case 97: goto L_0x01d5;
                case 98: goto L_0x01cf;
                case 99: goto L_0x01c9;
                case 100: goto L_0x01c3;
                case 101: goto L_0x01bd;
                case 102: goto L_0x01b7;
                case 103: goto L_0x01a5;
                case 104: goto L_0x019e;
                case 105: goto L_0x0197;
                case 106: goto L_0x0190;
                case 107: goto L_0x0189;
                case 108: goto L_0x0182;
                case 109: goto L_0x0174;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            switch(r14) {
                case 112: goto L_0x016d;
                case 113: goto L_0x0166;
                case 114: goto L_0x015f;
                case 115: goto L_0x0158;
                case 116: goto L_0x0151;
                case 117: goto L_0x014a;
                case 118: goto L_0x0143;
                case 119: goto L_0x013c;
                case 120: goto L_0x012a;
                case 121: goto L_0x0118;
                case 122: goto L_0x0111;
                case 123: goto L_0x010a;
                case 124: goto L_0x0103;
                case 125: goto L_0x00fc;
                case 126: goto L_0x00f5;
                default: goto L_0x00a9;
            }
        L_0x00a9:
            switch(r14) {
                case 151: goto L_0x00d9;
                case 152: goto L_0x00d9;
                case 153: goto L_0x00d2;
                case 154: goto L_0x00cb;
                default: goto L_0x00ac;
            }
        L_0x00ac:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Token: "
            r2.append(r3)
            char r0 = r0.charAt(r13)
            java.lang.String r0 = org.mozilla.javascript.Token.name(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00cb:
            java.lang.String r4 = "const "
            r9.append(r4)
            goto L_0x021d
        L_0x00d2:
            java.lang.String r4 = "let "
            r9.append(r4)
            goto L_0x021d
        L_0x00d9:
            char r4 = r0.charAt(r13)
            r6 = 151(0x97, float:2.12E-43)
            if (r4 != r6) goto L_0x00e4
            java.lang.String r4 = "get "
            goto L_0x00e6
        L_0x00e4:
            java.lang.String r4 = "set "
        L_0x00e6:
            r9.append(r4)
            int r13 = r13 + 1
            int r13 = r13 + r3
            r4 = 0
            int r6 = printSourceString(r0, r13, r4, r9)
            int r13 = r6 + 1
            goto L_0x017b
        L_0x00f5:
            java.lang.String r4 = "void "
            r9.append(r4)
            goto L_0x021d
        L_0x00fc:
            java.lang.String r4 = "finally "
            r9.append(r4)
            goto L_0x021d
        L_0x0103:
            java.lang.String r4 = "catch "
            r9.append(r4)
            goto L_0x021d
        L_0x010a:
            java.lang.String r4 = "with "
            r9.append(r4)
            goto L_0x021d
        L_0x0111:
            java.lang.String r4 = "var "
            r9.append(r4)
            goto L_0x021d
        L_0x0118:
            java.lang.String r4 = "continue"
            r9.append(r4)
            int r4 = getNext(r0, r2, r13)
            if (r6 != r4) goto L_0x021d
            r4 = 32
            r9.append(r4)
            goto L_0x021d
        L_0x012a:
            r4 = 32
            java.lang.String r7 = "break"
            r9.append(r7)
            int r7 = getNext(r0, r2, r13)
            if (r6 != r7) goto L_0x021d
            r9.append(r4)
            goto L_0x021d
        L_0x013c:
            java.lang.String r4 = "for "
            r9.append(r4)
            goto L_0x021d
        L_0x0143:
            java.lang.String r4 = "do "
            r9.append(r4)
            goto L_0x021d
        L_0x014a:
            java.lang.String r4 = "while "
            r9.append(r4)
            goto L_0x021d
        L_0x0151:
            java.lang.String r4 = "default"
            r9.append(r4)
            goto L_0x021d
        L_0x0158:
            java.lang.String r4 = "case "
            r9.append(r4)
            goto L_0x021d
        L_0x015f:
            java.lang.String r4 = "switch "
            r9.append(r4)
            goto L_0x021d
        L_0x0166:
            java.lang.String r4 = "else "
            r9.append(r4)
            goto L_0x021d
        L_0x016d:
            java.lang.String r4 = "if "
            r9.append(r4)
            goto L_0x021d
        L_0x0174:
            int r13 = r13 + 1
            java.lang.String r4 = "function "
            r9.append(r4)
        L_0x017b:
            r14 = r5
            r5 = 32
            r7 = 40
            goto L_0x0482
        L_0x0182:
            r4 = 46
            r9.append(r4)
            goto L_0x021d
        L_0x0189:
            java.lang.String r4 = "--"
            r9.append(r4)
            goto L_0x021d
        L_0x0190:
            java.lang.String r4 = "++"
            r9.append(r4)
            goto L_0x021d
        L_0x0197:
            java.lang.String r4 = " && "
            r9.append(r4)
            goto L_0x021d
        L_0x019e:
            java.lang.String r4 = " || "
            r9.append(r4)
            goto L_0x021d
        L_0x01a5:
            int r6 = getNext(r0, r2, r13)
            if (r3 != r6) goto L_0x01b0
            r9.append(r4)
            goto L_0x021d
        L_0x01b0:
            java.lang.String r4 = " : "
            r9.append(r4)
            goto L_0x021d
        L_0x01b7:
            java.lang.String r4 = " ? "
            r9.append(r4)
            goto L_0x021d
        L_0x01bd:
            java.lang.String r4 = " %= "
            r9.append(r4)
            goto L_0x021d
        L_0x01c3:
            java.lang.String r4 = " /= "
            r9.append(r4)
            goto L_0x021d
        L_0x01c9:
            java.lang.String r4 = " *= "
            r9.append(r4)
            goto L_0x021d
        L_0x01cf:
            java.lang.String r4 = " -= "
            r9.append(r4)
            goto L_0x021d
        L_0x01d5:
            java.lang.String r4 = " += "
            r9.append(r4)
            goto L_0x021d
        L_0x01db:
            java.lang.String r4 = " >>>= "
            r9.append(r4)
            goto L_0x021d
        L_0x01e1:
            java.lang.String r4 = " >>= "
            r9.append(r4)
            goto L_0x021d
        L_0x01e7:
            java.lang.String r4 = " <<= "
            r9.append(r4)
            goto L_0x021d
        L_0x01ed:
            java.lang.String r4 = " &= "
            r9.append(r4)
            goto L_0x021d
        L_0x01f3:
            java.lang.String r4 = " ^= "
            r9.append(r4)
            goto L_0x021d
        L_0x01f9:
            java.lang.String r4 = " |= "
            r9.append(r4)
            goto L_0x021d
        L_0x01ff:
            java.lang.String r4 = " = "
            r9.append(r4)
            goto L_0x021d
        L_0x0205:
            java.lang.String r4 = ", "
            r9.append(r4)
            goto L_0x021d
        L_0x020b:
            r4 = 41
            r9.append(r4)
            r4 = 85
            int r6 = getNext(r0, r2, r13)
            if (r4 != r6) goto L_0x021d
            r4 = 32
            r9.append(r4)
        L_0x021d:
            r7 = 40
            goto L_0x042e
        L_0x0221:
            r7 = 40
            r9.append(r7)
            goto L_0x042e
        L_0x0228:
            r7 = 40
            int r15 = r15 + -1
            if (r10 == 0) goto L_0x0232
            if (r15 != 0) goto L_0x0232
            goto L_0x042e
        L_0x0232:
            r4 = 125(0x7d, float:1.75E-43)
            r9.append(r4)
            int r4 = getNext(r0, r2, r13)
            if (r4 == r3) goto L_0x0253
            r6 = 113(0x71, float:1.58E-43)
            if (r4 == r6) goto L_0x024b
            r6 = 117(0x75, float:1.64E-43)
            if (r4 == r6) goto L_0x024b
            r6 = 164(0xa4, float:2.3E-43)
            if (r4 == r6) goto L_0x0253
            goto L_0x042e
        L_0x024b:
            int r5 = r5 - r8
            r4 = 32
            r9.append(r4)
            goto L_0x042e
        L_0x0253:
            int r5 = r5 - r8
            goto L_0x042e
        L_0x0256:
            r7 = 40
            int r15 = r15 + 1
            int r4 = getNext(r0, r2, r13)
            if (r3 != r4) goto L_0x0261
            int r5 = r5 + r8
        L_0x0261:
            r4 = 123(0x7b, float:1.72E-43)
            r9.append(r4)
            goto L_0x042e
        L_0x0268:
            r7 = 40
            r4 = 93
            r9.append(r4)
            goto L_0x042e
        L_0x0271:
            r7 = 40
            r4 = 91
            r9.append(r4)
            goto L_0x042e
        L_0x027a:
            r7 = 40
            r4 = 59
            r9.append(r4)
            int r4 = getNext(r0, r2, r13)
            if (r3 == r4) goto L_0x042e
            r4 = 32
            r9.append(r4)
            goto L_0x042e
        L_0x028e:
            r7 = 40
            java.lang.String r4 = "try "
            r9.append(r4)
            goto L_0x042e
        L_0x0297:
            r7 = 40
            java.lang.String r4 = " !== "
            r9.append(r4)
            goto L_0x042e
        L_0x02a0:
            r7 = 40
            java.lang.String r4 = " === "
            r9.append(r4)
            goto L_0x042e
        L_0x02a9:
            r7 = 40
            java.lang.String r4 = "true"
            r9.append(r4)
            goto L_0x042e
        L_0x02b2:
            r7 = 40
            java.lang.String r4 = "false"
            r9.append(r4)
            goto L_0x042e
        L_0x02bb:
            r7 = 40
            java.lang.String r4 = "this"
            r9.append(r4)
            goto L_0x042e
        L_0x02c4:
            r7 = 40
            java.lang.String r4 = "null"
            r9.append(r4)
            goto L_0x042e
        L_0x02cd:
            r7 = 40
            int r13 = r13 + 1
            int r13 = printSourceString(r0, r13, r3, r9)
            goto L_0x02e8
        L_0x02d6:
            r7 = 40
            int r13 = r13 + 1
            int r13 = printSourceNumber(r0, r13, r9)
            goto L_0x02e8
        L_0x02df:
            r7 = 40
            int r13 = r13 + 1
            r4 = 0
            int r13 = printSourceString(r0, r13, r4, r9)
        L_0x02e8:
            r4 = 32
            r7 = 4
            r14 = 40
            goto L_0x0063
        L_0x02ef:
            r7 = 40
            java.lang.String r4 = "typeof "
            r9.append(r4)
            goto L_0x042e
        L_0x02f8:
            r7 = 40
            java.lang.String r4 = "delete "
            r9.append(r4)
            goto L_0x042e
        L_0x0301:
            r7 = 40
            java.lang.String r4 = "new "
            r9.append(r4)
            goto L_0x042e
        L_0x030a:
            r7 = 40
            r4 = 45
            r9.append(r4)
            goto L_0x042e
        L_0x0313:
            r7 = 40
            r4 = 43
            r9.append(r4)
            goto L_0x042e
        L_0x031c:
            r7 = 40
            r4 = 126(0x7e, float:1.77E-43)
            r9.append(r4)
            goto L_0x042e
        L_0x0325:
            r7 = 40
            r4 = 33
            r9.append(r4)
            goto L_0x042e
        L_0x032e:
            r7 = 40
            java.lang.String r4 = " % "
            r9.append(r4)
            goto L_0x042e
        L_0x0337:
            r7 = 40
            java.lang.String r4 = " / "
            r9.append(r4)
            goto L_0x042e
        L_0x0340:
            r7 = 40
            java.lang.String r4 = " * "
            r9.append(r4)
            goto L_0x042e
        L_0x0349:
            r7 = 40
            java.lang.String r4 = " - "
            r9.append(r4)
            goto L_0x042e
        L_0x0352:
            r7 = 40
            java.lang.String r4 = " + "
            r9.append(r4)
            goto L_0x042e
        L_0x035b:
            r7 = 40
            java.lang.String r4 = " >>> "
            r9.append(r4)
            goto L_0x042e
        L_0x0364:
            r7 = 40
            java.lang.String r4 = " >> "
            r9.append(r4)
            goto L_0x042e
        L_0x036d:
            r7 = 40
            java.lang.String r4 = " << "
            r9.append(r4)
            goto L_0x042e
        L_0x0376:
            r7 = 40
            java.lang.String r4 = " >= "
            r9.append(r4)
            goto L_0x042e
        L_0x037f:
            r7 = 40
            java.lang.String r4 = " > "
            r9.append(r4)
            goto L_0x042e
        L_0x0388:
            r7 = 40
            java.lang.String r4 = " <= "
            r9.append(r4)
            goto L_0x042e
        L_0x0391:
            r7 = 40
            java.lang.String r4 = " < "
            r9.append(r4)
            goto L_0x042e
        L_0x039a:
            r7 = 40
            java.lang.String r4 = " != "
            r9.append(r4)
            goto L_0x042e
        L_0x03a3:
            r7 = 40
            java.lang.String r4 = " == "
            r9.append(r4)
            goto L_0x042e
        L_0x03ac:
            r7 = 40
            java.lang.String r4 = " & "
            r9.append(r4)
            goto L_0x042e
        L_0x03b5:
            r7 = 40
            java.lang.String r4 = " ^ "
            r9.append(r4)
            goto L_0x042e
        L_0x03be:
            r7 = 40
            java.lang.String r4 = " | "
            r9.append(r4)
            goto L_0x042e
        L_0x03c7:
            r7 = 40
            r4 = 64
            r9.append(r4)
            goto L_0x042e
        L_0x03cf:
            r7 = 40
            java.lang.String r4 = ".("
            r9.append(r4)
            goto L_0x042e
        L_0x03d7:
            r7 = 40
            java.lang.String r4 = "::"
            r9.append(r4)
            goto L_0x042e
        L_0x03df:
            r7 = 40
            java.lang.String r4 = ".."
            r9.append(r4)
            goto L_0x042e
        L_0x03e7:
            r7 = 40
            java.lang.String r4 = " instanceof "
            r9.append(r4)
            goto L_0x042e
        L_0x03ef:
            r7 = 40
            java.lang.String r4 = " in "
            r9.append(r4)
            goto L_0x042e
        L_0x03f7:
            r7 = 40
            java.lang.String r4 = "debugger;\n"
            r9.append(r4)
            goto L_0x042e
        L_0x03ff:
            r7 = 40
            java.lang.String r4 = "yield "
            r9.append(r4)
            goto L_0x042e
        L_0x0407:
            r7 = 40
            r9.append(r4)
            goto L_0x042e
        L_0x040d:
            r7 = 40
            java.lang.String r4 = "throw "
            r9.append(r4)
            goto L_0x042e
        L_0x0415:
            r7 = 40
            java.lang.String r4 = "return"
            r9.append(r4)
            r4 = 82
            int r6 = getNext(r0, r2, r13)
            if (r4 == r6) goto L_0x042e
            r4 = 32
            r9.append(r4)
            goto L_0x042e
        L_0x042a:
            r7 = 40
            if (r11 == 0) goto L_0x0432
        L_0x042e:
            r14 = r5
        L_0x042f:
            r5 = 32
            goto L_0x0482
        L_0x0432:
            r4 = 0
            if (r16 != 0) goto L_0x0443
            if (r10 == 0) goto L_0x043e
            r9.setLength(r4)
            int r5 = r5 - r8
            r14 = r5
            r5 = 0
            goto L_0x0440
        L_0x043e:
            r14 = r5
            r5 = 1
        L_0x0440:
            r16 = 1
            goto L_0x0445
        L_0x0443:
            r14 = r5
            r5 = 1
        L_0x0445:
            if (r5 == 0) goto L_0x044c
            r5 = 10
            r9.append(r5)
        L_0x044c:
            int r5 = r13 + 1
            if (r5 >= r2) goto L_0x042f
            char r5 = r0.charAt(r5)
            r4 = 115(0x73, float:1.61E-43)
            if (r5 == r4) goto L_0x0476
            r4 = 116(0x74, float:1.63E-43)
            if (r5 != r4) goto L_0x045d
            goto L_0x0476
        L_0x045d:
            r4 = 86
            if (r5 != r4) goto L_0x0462
            goto L_0x0472
        L_0x0462:
            if (r5 != r6) goto L_0x0474
            int r4 = r13 + 2
            int r4 = getSourceStringEnd(r0, r4)
            char r4 = r0.charAt(r4)
            r5 = 103(0x67, float:1.44E-43)
            if (r4 != r5) goto L_0x0474
        L_0x0472:
            r4 = r8
            goto L_0x0478
        L_0x0474:
            r4 = 0
            goto L_0x0478
        L_0x0476:
            int r4 = r8 - r1
        L_0x0478:
            if (r4 >= r14) goto L_0x042f
            r5 = 32
            r9.append(r5)
            int r4 = r4 + 1
            goto L_0x0478
        L_0x0482:
            int r13 = r13 + r3
            r5 = r14
            goto L_0x02e8
        L_0x0486:
            if (r11 != 0) goto L_0x0490
            if (r10 != 0) goto L_0x0498
            r0 = 10
            r9.append(r0)
            goto L_0x0498
        L_0x0490:
            r0 = 2
            if (r12 != r0) goto L_0x0498
            r0 = 41
            r9.append(r0)
        L_0x0498:
            java.lang.String r0 = r9.toString()
            return r0
        L_0x049d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x04a3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x04a9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Decompiler.decompile(java.lang.String, int, org.mozilla.javascript.UintMap):java.lang.String");
    }

    private static int getNext(String str, int i, int i2) {
        int i3 = i2 + 1;
        if (i3 < i) {
            return str.charAt(i3);
        }
        return 0;
    }

    private static int getSourceStringEnd(String str, int i) {
        return printSourceString(str, i, false, (StringBuffer) null);
    }

    private static int printSourceString(String str, int i, boolean z, StringBuffer stringBuffer) {
        char charAt = str.charAt(i);
        int i2 = i + 1;
        if ((32768 & charAt) != 0) {
            charAt = ((charAt & 32767) << 16) | str.charAt(i2);
            i2++;
        }
        if (stringBuffer != null) {
            String substring = str.substring(i2, i2 + charAt);
            if (!z) {
                stringBuffer.append(substring);
            } else {
                stringBuffer.append('\"');
                stringBuffer.append(ScriptRuntime.escapeString(substring));
                stringBuffer.append('\"');
            }
        }
        return i2 + charAt;
    }

    private static int printSourceNumber(String str, int i, StringBuffer stringBuffer) {
        int i2;
        char charAt = str.charAt(i);
        int i3 = i + 1;
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (charAt == 'S') {
            if (stringBuffer != null) {
                d = (double) str.charAt(i3);
            }
            i2 = i3 + 1;
        } else if (charAt == 'J' || charAt == 'D') {
            if (stringBuffer != null) {
                long charAt2 = (((long) str.charAt(i3)) << 48) | (((long) str.charAt(i3 + 1)) << 32) | (((long) str.charAt(i3 + 2)) << 16) | ((long) str.charAt(i3 + 3));
                if (charAt == 'J') {
                    d = (double) charAt2;
                } else {
                    d = Double.longBitsToDouble(charAt2);
                }
            }
            i2 = i3 + 4;
        } else {
            throw new RuntimeException();
        }
        if (stringBuffer != null) {
            stringBuffer.append(ScriptRuntime.numberToString(d, 10));
        }
        return i2;
    }
}
