package org.mozilla.javascript;

import java.io.IOException;
import java.io.Reader;
import org.mozilla.javascript.Token;

class TokenStream {
    private static final char BYTE_ORDER_MARK = '﻿';
    private static final int EOF_CHAR = -1;
    private ObjToIntMap allStrings = new ObjToIntMap(50);
    private int commentCursor = -1;
    private String commentPrefix = "";
    Token.CommentType commentType;
    int cursor;
    private boolean dirtyLine;
    private boolean hitEOF = false;
    private boolean isOctal;
    private int lineEndChar = -1;
    private int lineStart = 0;
    int lineno;
    private double number;
    private Parser parser;
    private int quoteChar;
    String regExpFlags;
    private char[] sourceBuffer;
    int sourceCursor;
    private int sourceEnd;
    private Reader sourceReader;
    private String sourceString;
    private String string = "";
    private char[] stringBuffer = new char[128];
    private int stringBufferTop;
    int tokenBeg;
    int tokenEnd;
    private final int[] ungetBuffer = new int[3];
    private int ungetCursor;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;

    private static boolean isAlpha(int i) {
        return i <= 90 ? 65 <= i : 97 <= i && i <= 122;
    }

    static boolean isDigit(int i) {
        return 48 <= i && i <= 57;
    }

    /* access modifiers changed from: package-private */
    public String tokenToString(int i) {
        return "";
    }

    TokenStream(Parser parser2, Reader reader, String str, int i) {
        this.parser = parser2;
        this.lineno = i;
        if (reader != null) {
            if (str != null) {
                Kit.codeBug();
            }
            this.sourceReader = reader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (str == null) {
                Kit.codeBug();
            }
            this.sourceString = str;
            this.sourceEnd = str.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    static boolean isKeyword(String str) {
        return stringToKeyword(str) != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0205, code lost:
        if (r0.charAt(1) == 'n') goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0231, code lost:
        if (r0.charAt(1) == 'h') goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x029f, code lost:
        if (r0.charAt(1) == 'n') goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02a1, code lost:
        r5 = 127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x02db, code lost:
        r1 = null;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x02dd, code lost:
        if (r1 == null) goto L_0x02e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x02df, code lost:
        if (r1 == r0) goto L_0x02e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x02e5, code lost:
        if (r1.equals(r0) != false) goto L_0x02e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x02e7, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x02e9, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x02ea, code lost:
        if (r5 != 0) goto L_0x02ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x02ec, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x02ef, code lost:
        return r5 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0029, code lost:
        r2 = 127;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int stringToKeyword(java.lang.String r17) {
        /*
            r0 = r17
            int r1 = r17.length()
            r3 = 100
            r6 = 99
            r8 = 111(0x6f, float:1.56E-43)
            r9 = 109(0x6d, float:1.53E-43)
            r10 = 118(0x76, float:1.65E-43)
            r13 = 102(0x66, float:1.43E-43)
            r14 = 110(0x6e, float:1.54E-43)
            r15 = 97
            r5 = 105(0x69, float:1.47E-43)
            r2 = 116(0x74, float:1.63E-43)
            r12 = 114(0x72, float:1.6E-43)
            r11 = 101(0x65, float:1.42E-43)
            r7 = 0
            r4 = 1
            r16 = 127(0x7f, float:1.78E-43)
            switch(r1) {
                case 2: goto L_0x02b6;
                case 3: goto L_0x0239;
                case 4: goto L_0x016f;
                case 5: goto L_0x00fb;
                case 6: goto L_0x00a5;
                case 7: goto L_0x007b;
                case 8: goto L_0x0053;
                case 9: goto L_0x003e;
                case 10: goto L_0x002d;
                case 11: goto L_0x0025;
                case 12: goto L_0x0027;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x02db
        L_0x0027:
            java.lang.String r1 = "synchronized"
        L_0x0029:
            r2 = 127(0x7f, float:1.78E-43)
            goto L_0x02dd
        L_0x002d:
            char r1 = r0.charAt(r4)
            if (r1 != r9) goto L_0x0036
            java.lang.String r1 = "implements"
            goto L_0x0029
        L_0x0036:
            if (r1 != r14) goto L_0x02db
            r2 = 53
            java.lang.String r1 = "instanceof"
            goto L_0x02dd
        L_0x003e:
            char r1 = r0.charAt(r7)
            if (r1 != r5) goto L_0x0047
            java.lang.String r1 = "interface"
            goto L_0x0029
        L_0x0047:
            r3 = 112(0x70, float:1.57E-43)
            if (r1 != r3) goto L_0x004e
            java.lang.String r1 = "protected"
            goto L_0x0029
        L_0x004e:
            if (r1 != r2) goto L_0x02db
            java.lang.String r1 = "transient"
            goto L_0x0029
        L_0x0053:
            char r1 = r0.charAt(r7)
            if (r1 == r15) goto L_0x0078
            if (r1 == r13) goto L_0x0072
            if (r1 == r10) goto L_0x006f
            if (r1 == r6) goto L_0x0069
            if (r1 == r3) goto L_0x0063
            goto L_0x02db
        L_0x0063:
            r2 = 160(0xa0, float:2.24E-43)
            java.lang.String r1 = "debugger"
            goto L_0x02dd
        L_0x0069:
            java.lang.String r1 = "continue"
            r2 = 121(0x79, float:1.7E-43)
            goto L_0x02dd
        L_0x006f:
            java.lang.String r1 = "volatile"
            goto L_0x0029
        L_0x0072:
            java.lang.String r1 = "function"
            r2 = 109(0x6d, float:1.53E-43)
            goto L_0x02dd
        L_0x0078:
            java.lang.String r1 = "abstract"
            goto L_0x0029
        L_0x007b:
            char r1 = r0.charAt(r4)
            if (r1 == r15) goto L_0x00a2
            if (r1 == r11) goto L_0x009e
            if (r1 == r5) goto L_0x0098
            if (r1 == r8) goto L_0x0095
            if (r1 == r12) goto L_0x0092
            r2 = 120(0x78, float:1.68E-43)
            if (r1 == r2) goto L_0x008f
            goto L_0x02db
        L_0x008f:
            java.lang.String r1 = "extends"
            goto L_0x0029
        L_0x0092:
            java.lang.String r1 = "private"
            goto L_0x0029
        L_0x0095:
            java.lang.String r1 = "boolean"
            goto L_0x0029
        L_0x0098:
            r2 = 125(0x7d, float:1.75E-43)
            java.lang.String r1 = "finally"
            goto L_0x02dd
        L_0x009e:
            java.lang.String r1 = "default"
            goto L_0x02dd
        L_0x00a2:
            java.lang.String r1 = "package"
            goto L_0x0029
        L_0x00a5:
            char r1 = r0.charAt(r4)
            if (r1 == r15) goto L_0x00f7
            if (r1 == r11) goto L_0x00e4
            r4 = 104(0x68, float:1.46E-43)
            if (r1 == r4) goto L_0x00e0
            if (r1 == r9) goto L_0x00dc
            if (r1 == r8) goto L_0x00d8
            if (r1 == r2) goto L_0x00d4
            r2 = 117(0x75, float:1.64E-43)
            if (r1 == r2) goto L_0x00d0
            switch(r1) {
                case 119: goto L_0x00ca;
                case 120: goto L_0x00c6;
                case 121: goto L_0x00c0;
                default: goto L_0x00be;
            }
        L_0x00be:
            goto L_0x02db
        L_0x00c0:
            r2 = 32
            java.lang.String r1 = "typeof"
            goto L_0x02dd
        L_0x00c6:
            java.lang.String r1 = "export"
            goto L_0x0029
        L_0x00ca:
            java.lang.String r1 = "switch"
            r2 = 114(0x72, float:1.6E-43)
            goto L_0x02dd
        L_0x00d0:
            java.lang.String r1 = "public"
            goto L_0x0029
        L_0x00d4:
            java.lang.String r1 = "static"
            goto L_0x0029
        L_0x00d8:
            java.lang.String r1 = "double"
            goto L_0x0029
        L_0x00dc:
            java.lang.String r1 = "import"
            goto L_0x0029
        L_0x00e0:
            java.lang.String r1 = "throws"
            goto L_0x0029
        L_0x00e4:
            char r1 = r0.charAt(r7)
            if (r1 != r3) goto L_0x00f0
            r2 = 31
            java.lang.String r1 = "delete"
            goto L_0x02dd
        L_0x00f0:
            if (r1 != r12) goto L_0x02db
            r2 = 4
            java.lang.String r1 = "return"
            goto L_0x02dd
        L_0x00f7:
            java.lang.String r1 = "native"
            goto L_0x0029
        L_0x00fb:
            r1 = 2
            char r1 = r0.charAt(r1)
            if (r1 == r15) goto L_0x016b
            if (r1 == r11) goto L_0x0153
            if (r1 == r5) goto L_0x014d
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 == r3) goto L_0x0147
            if (r1 == r12) goto L_0x0141
            if (r1 == r2) goto L_0x013b
            switch(r1) {
                case 110: goto L_0x0129;
                case 111: goto L_0x0117;
                case 112: goto L_0x0113;
                default: goto L_0x0111;
            }
        L_0x0111:
            goto L_0x02db
        L_0x0113:
            java.lang.String r1 = "super"
            goto L_0x0029
        L_0x0117:
            char r1 = r0.charAt(r7)
            if (r1 != r13) goto L_0x0121
            java.lang.String r1 = "float"
            goto L_0x0029
        L_0x0121:
            r2 = 115(0x73, float:1.61E-43)
            if (r1 != r2) goto L_0x02db
            java.lang.String r1 = "short"
            goto L_0x0029
        L_0x0129:
            char r1 = r0.charAt(r7)
            if (r1 != r6) goto L_0x0135
            r2 = 154(0x9a, float:2.16E-43)
            java.lang.String r1 = "const"
            goto L_0x02dd
        L_0x0135:
            if (r1 != r13) goto L_0x02db
            java.lang.String r1 = "final"
            goto L_0x0029
        L_0x013b:
            r2 = 124(0x7c, float:1.74E-43)
            java.lang.String r1 = "catch"
            goto L_0x02dd
        L_0x0141:
            r2 = 50
            java.lang.String r1 = "throw"
            goto L_0x02dd
        L_0x0147:
            r2 = 44
            java.lang.String r1 = "false"
            goto L_0x02dd
        L_0x014d:
            java.lang.String r1 = "while"
            r2 = 117(0x75, float:1.64E-43)
            goto L_0x02dd
        L_0x0153:
            char r1 = r0.charAt(r7)
            r2 = 98
            if (r1 != r2) goto L_0x0161
            r2 = 120(0x78, float:1.68E-43)
            java.lang.String r1 = "break"
            goto L_0x02dd
        L_0x0161:
            r2 = 121(0x79, float:1.7E-43)
            if (r1 != r2) goto L_0x02db
            r2 = 72
            java.lang.String r1 = "yield"
            goto L_0x02dd
        L_0x016b:
            java.lang.String r1 = "class"
            goto L_0x0029
        L_0x016f:
            char r1 = r0.charAt(r7)
            r3 = 98
            if (r1 == r3) goto L_0x0235
            if (r1 == r6) goto L_0x0209
            if (r1 == r11) goto L_0x01da
            r3 = 103(0x67, float:1.44E-43)
            if (r1 == r3) goto L_0x01d6
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 == r3) goto L_0x01d2
            if (r1 == r14) goto L_0x01cc
            if (r1 == r2) goto L_0x019b
            if (r1 == r10) goto L_0x0195
            r2 = 119(0x77, float:1.67E-43)
            if (r1 == r2) goto L_0x018f
            goto L_0x02db
        L_0x018f:
            r2 = 123(0x7b, float:1.72E-43)
            java.lang.String r1 = "with"
            goto L_0x02dd
        L_0x0195:
            r2 = 126(0x7e, float:1.77E-43)
            java.lang.String r1 = "void"
            goto L_0x02dd
        L_0x019b:
            r1 = 3
            char r1 = r0.charAt(r1)
            if (r1 != r11) goto L_0x01b5
            r2 = 2
            char r1 = r0.charAt(r2)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r12) goto L_0x02db
            r5 = 45
            goto L_0x02ea
        L_0x01b5:
            r2 = 2
            r3 = 115(0x73, float:1.61E-43)
            if (r1 != r3) goto L_0x02db
            char r1 = r0.charAt(r2)
            if (r1 != r5) goto L_0x02db
            char r1 = r0.charAt(r4)
            r2 = 104(0x68, float:1.46E-43)
            if (r1 != r2) goto L_0x02db
            r5 = 43
            goto L_0x02ea
        L_0x01cc:
            r2 = 42
            java.lang.String r1 = "null"
            goto L_0x02dd
        L_0x01d2:
            java.lang.String r1 = "long"
            goto L_0x0029
        L_0x01d6:
            java.lang.String r1 = "goto"
            goto L_0x0029
        L_0x01da:
            r1 = 3
            char r1 = r0.charAt(r1)
            if (r1 != r11) goto L_0x01f6
            r2 = 2
            char r1 = r0.charAt(r2)
            r2 = 115(0x73, float:1.61E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x02db
            r5 = 113(0x71, float:1.58E-43)
            goto L_0x02ea
        L_0x01f6:
            r2 = 2
            if (r1 != r9) goto L_0x02db
            char r1 = r0.charAt(r2)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r14) goto L_0x02db
            goto L_0x02a1
        L_0x0209:
            r2 = 2
            r1 = 3
            char r1 = r0.charAt(r1)
            if (r1 != r11) goto L_0x0223
            char r1 = r0.charAt(r2)
            r2 = 115(0x73, float:1.61E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r15) goto L_0x02db
            r5 = 115(0x73, float:1.61E-43)
            goto L_0x02ea
        L_0x0223:
            if (r1 != r12) goto L_0x02db
            char r1 = r0.charAt(r2)
            if (r1 != r15) goto L_0x02db
            char r1 = r0.charAt(r4)
            r2 = 104(0x68, float:1.46E-43)
            if (r1 != r2) goto L_0x02db
            goto L_0x02a1
        L_0x0235:
            java.lang.String r1 = "byte"
            goto L_0x0029
        L_0x0239:
            char r1 = r0.charAt(r7)
            if (r1 == r13) goto L_0x02a4
            if (r1 == r5) goto L_0x0294
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 == r3) goto L_0x0284
            if (r1 == r14) goto L_0x0271
            if (r1 == r2) goto L_0x025e
            if (r1 == r10) goto L_0x024d
            goto L_0x02db
        L_0x024d:
            r1 = 2
            char r1 = r0.charAt(r1)
            if (r1 != r12) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r15) goto L_0x02db
            r5 = 122(0x7a, float:1.71E-43)
            goto L_0x02ea
        L_0x025e:
            r1 = 2
            char r1 = r0.charAt(r1)
            r2 = 121(0x79, float:1.7E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r12) goto L_0x02db
            r5 = 81
            goto L_0x02ea
        L_0x0271:
            r1 = 2
            char r1 = r0.charAt(r1)
            r2 = 119(0x77, float:1.67E-43)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r11) goto L_0x02db
            r5 = 30
            goto L_0x02ea
        L_0x0284:
            r1 = 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r11) goto L_0x02db
            r5 = 153(0x99, float:2.14E-43)
            goto L_0x02ea
        L_0x0294:
            r1 = 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r14) goto L_0x02db
        L_0x02a1:
            r5 = 127(0x7f, float:1.78E-43)
            goto L_0x02ea
        L_0x02a4:
            r1 = 2
            r2 = 119(0x77, float:1.67E-43)
            char r1 = r0.charAt(r1)
            if (r1 != r12) goto L_0x02db
            char r1 = r0.charAt(r4)
            if (r1 != r8) goto L_0x02db
            r5 = 119(0x77, float:1.67E-43)
            goto L_0x02ea
        L_0x02b6:
            char r1 = r0.charAt(r4)
            if (r1 != r13) goto L_0x02c5
            char r1 = r0.charAt(r7)
            if (r1 != r5) goto L_0x02db
            r5 = 112(0x70, float:1.57E-43)
            goto L_0x02ea
        L_0x02c5:
            if (r1 != r14) goto L_0x02d0
            char r1 = r0.charAt(r7)
            if (r1 != r5) goto L_0x02db
            r5 = 52
            goto L_0x02ea
        L_0x02d0:
            if (r1 != r8) goto L_0x02db
            char r1 = r0.charAt(r7)
            if (r1 != r3) goto L_0x02db
            r5 = 118(0x76, float:1.65E-43)
            goto L_0x02ea
        L_0x02db:
            r1 = 0
            r2 = 0
        L_0x02dd:
            if (r1 == 0) goto L_0x02e9
            if (r1 == r0) goto L_0x02e9
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x02e9
            r5 = 0
            goto L_0x02ea
        L_0x02e9:
            r5 = r2
        L_0x02ea:
            if (r5 != 0) goto L_0x02ed
            return r7
        L_0x02ed:
            r0 = r5 & 255(0xff, float:3.57E-43)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.stringToKeyword(java.lang.String):int");
    }

    /* access modifiers changed from: package-private */
    public final String getSourceString() {
        return this.sourceString;
    }

    /* access modifiers changed from: package-private */
    public final int getLineno() {
        return this.lineno;
    }

    /* access modifiers changed from: package-private */
    public final String getString() {
        return this.string;
    }

    /* access modifiers changed from: package-private */
    public final char getQuoteChar() {
        return (char) this.quoteChar;
    }

    /* access modifiers changed from: package-private */
    public final double getNumber() {
        return this.number;
    }

    /* access modifiers changed from: package-private */
    public final boolean isNumberOctal() {
        return this.isOctal;
    }

    /* access modifiers changed from: package-private */
    public final boolean eof() {
        return this.hitEOF;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x045b A[LOOP:6: B:321:0x045b->B:323:0x0461, LOOP_START, PHI: r1 
      PHI: (r1v25 int) = (r1v4 int), (r1v26 int) binds: [B:320:0x0459, B:323:0x0461] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0469 A[LOOP:7: B:324:0x0469->B:335:0x0487, LOOP_START, PHI: r1 r8 
      PHI: (r1v23 int) = (r1v4 int), (r1v24 int) binds: [B:320:0x0459, B:335:0x0487] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r8v4 int) = (r8v2 int), (r8v5 int) binds: [B:320:0x0459, B:335:0x0487] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x049b A[LOOP:8: B:342:0x049b->B:343:0x04a6, LOOP_START, PHI: r1 
      PHI: (r1v21 int) = (r1v5 int), (r1v22 int) binds: [B:341:0x0499, B:343:0x04a6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x04af A[PHI: r1 
      PHI: (r1v20 int) = (r1v17 int), (r1v13 int) binds: [B:406:0x04af, B:346:0x04ac] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x04b1  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0276 A[SYNTHETIC] */
    final int getToken() throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
        L_0x0002:
            int r1 = r16.getChar()
            r2 = -1
            r3 = 0
            if (r1 != r2) goto L_0x0013
            int r1 = r0.cursor
            int r2 = r1 + -1
            r0.tokenBeg = r2
            r0.tokenEnd = r1
            return r3
        L_0x0013:
            r4 = 10
            r5 = 1
            if (r1 != r4) goto L_0x0023
            r0.dirtyLine = r3
            int r1 = r0.cursor
            int r2 = r1 + -1
            r0.tokenBeg = r2
            r0.tokenEnd = r1
            return r5
        L_0x0023:
            boolean r6 = isJSSpace(r1)
            if (r6 != 0) goto L_0x0002
            r6 = 45
            if (r1 == r6) goto L_0x002f
            r0.dirtyLine = r5
        L_0x002f:
            int r7 = r0.cursor
            int r8 = r7 + -1
            r0.tokenBeg = r8
            r0.tokenEnd = r7
            r7 = 64
            if (r1 != r7) goto L_0x003e
            r1 = 147(0x93, float:2.06E-43)
            return r1
        L_0x003e:
            r7 = 117(0x75, float:1.64E-43)
            r8 = 92
            if (r1 != r8) goto L_0x0056
            int r1 = r16.getChar()
            if (r1 != r7) goto L_0x004f
            r0.stringBufferTop = r3
            r9 = 1
            r10 = 1
            goto L_0x0063
        L_0x004f:
            r0.ungetChar(r1)
            r1 = 92
            r9 = 0
            goto L_0x0062
        L_0x0056:
            char r9 = (char) r1
            boolean r9 = java.lang.Character.isJavaIdentifierStart(r9)
            if (r9 == 0) goto L_0x0062
            r0.stringBufferTop = r3
            r0.addToString(r1)
        L_0x0062:
            r10 = 0
        L_0x0063:
            r11 = 4
            r12 = 39
            if (r9 == 0) goto L_0x0115
            r1 = r10
        L_0x0069:
            if (r10 == 0) goto L_0x008c
            r4 = 0
            r6 = 0
        L_0x006d:
            if (r4 == r11) goto L_0x007d
            int r9 = r16.getChar()
            int r6 = org.mozilla.javascript.Kit.xDigitToInt(r9, r6)
            if (r6 >= 0) goto L_0x007a
            goto L_0x007d
        L_0x007a:
            int r4 = r4 + 1
            goto L_0x006d
        L_0x007d:
            if (r6 >= 0) goto L_0x0087
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.invalid.escape"
            r1.addError(r3)
            return r2
        L_0x0087:
            r0.addToString(r6)
            r10 = 0
            goto L_0x0069
        L_0x008c:
            int r4 = r16.getChar()
            if (r4 != r8) goto L_0x00a3
            int r1 = r16.getChar()
            if (r1 != r7) goto L_0x009b
            r1 = 1
            r10 = 1
            goto L_0x0069
        L_0x009b:
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.illegal.character"
            r1.addError(r3)
            return r2
        L_0x00a3:
            if (r4 == r2) goto L_0x00b6
            r6 = 65279(0xfeff, float:9.1475E-41)
            if (r4 == r6) goto L_0x00b6
            char r6 = (char) r4
            boolean r6 = java.lang.Character.isJavaIdentifierPart(r6)
            if (r6 != 0) goto L_0x00b2
            goto L_0x00b6
        L_0x00b2:
            r0.addToString(r4)
            goto L_0x0069
        L_0x00b6:
            r0.ungetChar(r4)
            java.lang.String r2 = r16.getStringFromBuffer()
            if (r1 != 0) goto L_0x0100
            int r1 = stringToKeyword(r2)
            if (r1 == 0) goto L_0x010a
            r3 = 153(0x99, float:2.14E-43)
            if (r1 == r3) goto L_0x00cd
            r3 = 72
            if (r1 != r3) goto L_0x00e6
        L_0x00cd:
            org.mozilla.javascript.Parser r3 = r0.parser
            org.mozilla.javascript.CompilerEnvirons r3 = r3.compilerEnv
            int r3 = r3.getLanguageVersion()
            r4 = 170(0xaa, float:2.38E-43)
            if (r3 >= r4) goto L_0x00e6
            r3 = 153(0x99, float:2.14E-43)
            if (r1 != r3) goto L_0x00e0
            java.lang.String r1 = "let"
            goto L_0x00e2
        L_0x00e0:
            java.lang.String r1 = "yield"
        L_0x00e2:
            r0.string = r1
            r1 = 39
        L_0x00e6:
            org.mozilla.javascript.ObjToIntMap r3 = r0.allStrings
            java.lang.Object r3 = r3.intern(r2)
            java.lang.String r3 = (java.lang.String) r3
            r0.string = r3
            r3 = 127(0x7f, float:1.78E-43)
            if (r1 == r3) goto L_0x00f5
            return r1
        L_0x00f5:
            org.mozilla.javascript.Parser r3 = r0.parser
            org.mozilla.javascript.CompilerEnvirons r3 = r3.compilerEnv
            boolean r3 = r3.isReservedKeywordAsIdentifier()
            if (r3 != 0) goto L_0x010a
            return r1
        L_0x0100:
            boolean r1 = isKeyword(r2)
            if (r1 == 0) goto L_0x010a
            java.lang.String r2 = r0.convertLastCharToHex(r2)
        L_0x010a:
            org.mozilla.javascript.ObjToIntMap r1 = r0.allStrings
            java.lang.Object r1 = r1.intern(r2)
            java.lang.String r1 = (java.lang.String) r1
            r0.string = r1
            return r12
        L_0x0115:
            boolean r9 = isDigit(r1)
            r10 = 101(0x65, float:1.42E-43)
            r13 = 16
            r14 = 120(0x78, float:1.68E-43)
            r7 = 46
            r15 = 48
            if (r9 != 0) goto L_0x0430
            if (r1 != r7) goto L_0x0133
            int r9 = r16.peekChar()
            boolean r9 = isDigit(r9)
            if (r9 == 0) goto L_0x0133
            goto L_0x0430
        L_0x0133:
            r9 = 34
            if (r1 == r9) goto L_0x033f
            if (r1 != r12) goto L_0x013b
            goto L_0x033f
        L_0x013b:
            r9 = 47
            r12 = 33
            r14 = 61
            if (r1 == r12) goto L_0x032c
            r15 = 91
            if (r1 == r15) goto L_0x0329
            r15 = 37
            if (r1 == r15) goto L_0x031f
            r10 = 38
            if (r1 == r10) goto L_0x0308
            r10 = 93
            if (r1 == r10) goto L_0x0305
            r10 = 94
            if (r1 == r10) goto L_0x02fd
            r4 = 62
            r8 = 161(0xa1, float:2.26E-43)
            switch(r1) {
                case 40: goto L_0x02fa;
                case 41: goto L_0x02f7;
                case 42: goto L_0x02eb;
                case 43: goto L_0x02d4;
                case 44: goto L_0x02d1;
                case 45: goto L_0x02a3;
                case 46: goto L_0x028c;
                case 47: goto L_0x0226;
                default: goto L_0x015e;
            }
        L_0x015e:
            switch(r1) {
                case 58: goto L_0x0218;
                case 59: goto L_0x0215;
                case 60: goto L_0x01d0;
                case 61: goto L_0x01bd;
                case 62: goto L_0x018f;
                case 63: goto L_0x018c;
                default: goto L_0x0161;
            }
        L_0x0161:
            switch(r1) {
                case 123: goto L_0x0189;
                case 124: goto L_0x0172;
                case 125: goto L_0x016f;
                case 126: goto L_0x016c;
                default: goto L_0x0164;
            }
        L_0x0164:
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.illegal.character"
            r1.addError(r3)
            return r2
        L_0x016c:
            r1 = 27
            return r1
        L_0x016f:
            r1 = 86
            return r1
        L_0x0172:
            r1 = 124(0x7c, float:1.74E-43)
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x017d
            r1 = 104(0x68, float:1.46E-43)
            return r1
        L_0x017d:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0186
            r1 = 91
            return r1
        L_0x0186:
            r1 = 9
            return r1
        L_0x0189:
            r1 = 85
            return r1
        L_0x018c:
            r1 = 102(0x66, float:1.43E-43)
            return r1
        L_0x018f:
            boolean r1 = r0.matchChar(r4)
            if (r1 == 0) goto L_0x01b3
            boolean r1 = r0.matchChar(r4)
            if (r1 == 0) goto L_0x01a7
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x01a4
            r1 = 96
            return r1
        L_0x01a4:
            r1 = 20
            return r1
        L_0x01a7:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x01b0
            r1 = 95
            return r1
        L_0x01b0:
            r1 = 19
            return r1
        L_0x01b3:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x01bc
            r1 = 17
            return r1
        L_0x01bc:
            return r13
        L_0x01bd:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x01cd
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x01ca
            return r7
        L_0x01ca:
            r1 = 12
            return r1
        L_0x01cd:
            r1 = 90
            return r1
        L_0x01d0:
            boolean r1 = r0.matchChar(r12)
            if (r1 == 0) goto L_0x01f5
            boolean r1 = r0.matchChar(r6)
            if (r1 == 0) goto L_0x01f2
            boolean r1 = r0.matchChar(r6)
            if (r1 == 0) goto L_0x01ef
            int r1 = r0.cursor
            int r1 = r1 - r11
            r0.tokenBeg = r1
            r16.skipLine()
            org.mozilla.javascript.Token$CommentType r1 = org.mozilla.javascript.Token.CommentType.HTML
            r0.commentType = r1
            return r8
        L_0x01ef:
            r0.ungetCharIgnoreLineEnd(r6)
        L_0x01f2:
            r0.ungetCharIgnoreLineEnd(r12)
        L_0x01f5:
            r1 = 60
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x0209
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0206
            r1 = 94
            return r1
        L_0x0206:
            r1 = 18
            return r1
        L_0x0209:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0212
            r1 = 15
            return r1
        L_0x0212:
            r1 = 14
            return r1
        L_0x0215:
            r1 = 82
            return r1
        L_0x0218:
            r1 = 58
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x0223
            r1 = 144(0x90, float:2.02E-43)
            return r1
        L_0x0223:
            r1 = 103(0x67, float:1.44E-43)
            return r1
        L_0x0226:
            r16.markCommentStart()
            boolean r1 = r0.matchChar(r9)
            if (r1 == 0) goto L_0x023d
            int r1 = r0.cursor
            int r1 = r1 + -2
            r0.tokenBeg = r1
            r16.skipLine()
            org.mozilla.javascript.Token$CommentType r1 = org.mozilla.javascript.Token.CommentType.LINE
            r0.commentType = r1
            return r8
        L_0x023d:
            r1 = 42
            boolean r4 = r0.matchChar(r1)
            if (r4 == 0) goto L_0x0280
            int r4 = r0.cursor
            int r4 = r4 + -2
            r0.tokenBeg = r4
            boolean r4 = r0.matchChar(r1)
            if (r4 == 0) goto L_0x0257
            org.mozilla.javascript.Token$CommentType r4 = org.mozilla.javascript.Token.CommentType.JSDOC
            r0.commentType = r4
        L_0x0255:
            r4 = 1
            goto L_0x025c
        L_0x0257:
            org.mozilla.javascript.Token$CommentType r4 = org.mozilla.javascript.Token.CommentType.BLOCK_COMMENT
            r0.commentType = r4
        L_0x025b:
            r4 = 0
        L_0x025c:
            int r6 = r16.getChar()
            if (r6 != r2) goto L_0x026f
            int r1 = r0.cursor
            int r1 = r1 - r5
            r0.tokenEnd = r1
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r2 = "msg.unterminated.comment"
            r1.addError(r2)
            return r8
        L_0x026f:
            if (r6 != r1) goto L_0x0272
            goto L_0x0255
        L_0x0272:
            if (r6 != r9) goto L_0x027b
            if (r4 == 0) goto L_0x025c
            int r1 = r0.cursor
            r0.tokenEnd = r1
            return r8
        L_0x027b:
            int r4 = r0.cursor
            r0.tokenEnd = r4
            goto L_0x025b
        L_0x0280:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0289
            r1 = 100
            return r1
        L_0x0289:
            r1 = 24
            return r1
        L_0x028c:
            boolean r1 = r0.matchChar(r7)
            if (r1 == 0) goto L_0x0295
            r1 = 143(0x8f, float:2.0E-43)
            return r1
        L_0x0295:
            r1 = 40
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x02a0
            r1 = 146(0x92, float:2.05E-43)
            return r1
        L_0x02a0:
            r1 = 108(0x6c, float:1.51E-43)
            return r1
        L_0x02a3:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x02ac
            r1 = 98
            goto L_0x02ce
        L_0x02ac:
            boolean r1 = r0.matchChar(r6)
            if (r1 == 0) goto L_0x02cc
            boolean r1 = r0.dirtyLine
            if (r1 != 0) goto L_0x02c9
            boolean r1 = r0.matchChar(r4)
            if (r1 == 0) goto L_0x02c9
            java.lang.String r1 = "--"
            r0.markCommentStart(r1)
            r16.skipLine()
            org.mozilla.javascript.Token$CommentType r1 = org.mozilla.javascript.Token.CommentType.HTML
            r0.commentType = r1
            return r8
        L_0x02c9:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x02ce
        L_0x02cc:
            r1 = 22
        L_0x02ce:
            r0.dirtyLine = r5
            return r1
        L_0x02d1:
            r1 = 89
            return r1
        L_0x02d4:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x02dd
            r1 = 97
            return r1
        L_0x02dd:
            r1 = 43
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x02e8
            r1 = 106(0x6a, float:1.49E-43)
            return r1
        L_0x02e8:
            r1 = 21
            return r1
        L_0x02eb:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x02f4
            r1 = 99
            return r1
        L_0x02f4:
            r1 = 23
            return r1
        L_0x02f7:
            r1 = 88
            return r1
        L_0x02fa:
            r1 = 87
            return r1
        L_0x02fd:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0304
            return r8
        L_0x0304:
            return r4
        L_0x0305:
            r1 = 84
            return r1
        L_0x0308:
            r1 = 38
            boolean r1 = r0.matchChar(r1)
            if (r1 == 0) goto L_0x0313
            r1 = 105(0x69, float:1.47E-43)
            return r1
        L_0x0313:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x031c
            r1 = 93
            return r1
        L_0x031c:
            r1 = 11
            return r1
        L_0x031f:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0326
            return r10
        L_0x0326:
            r1 = 25
            return r1
        L_0x0329:
            r1 = 83
            return r1
        L_0x032c:
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x033c
            boolean r1 = r0.matchChar(r14)
            if (r1 == 0) goto L_0x0339
            return r9
        L_0x0339:
            r1 = 13
            return r1
        L_0x033c:
            r1 = 26
            return r1
        L_0x033f:
            r0.quoteChar = r1
            r0.stringBufferTop = r3
            int r1 = r0.getChar(r3)
        L_0x0347:
            int r5 = r0.quoteChar
            if (r1 == r5) goto L_0x041f
            if (r1 == r4) goto L_0x0410
            if (r1 != r2) goto L_0x0351
            goto L_0x0410
        L_0x0351:
            if (r1 != r8) goto L_0x0405
            int r1 = r16.getChar()
            if (r1 == r4) goto L_0x03fd
            r5 = 98
            if (r1 == r5) goto L_0x03f8
            r5 = 102(0x66, float:1.43E-43)
            if (r1 == r5) goto L_0x03f3
            r5 = 110(0x6e, float:1.54E-43)
            if (r1 == r5) goto L_0x03ee
            r5 = 114(0x72, float:1.6E-43)
            if (r1 == r5) goto L_0x03e9
            if (r1 == r14) goto L_0x03c5
            switch(r1) {
                case 116: goto L_0x03c0;
                case 117: goto L_0x039f;
                case 118: goto L_0x039b;
                default: goto L_0x036e;
            }
        L_0x036e:
            if (r15 > r1) goto L_0x0405
            r5 = 56
            if (r1 >= r5) goto L_0x0405
            int r1 = r1 + -48
            int r6 = r16.getChar()
            if (r15 > r6) goto L_0x0396
            if (r6 >= r5) goto L_0x0396
            int r1 = r1 * 8
            int r1 = r1 + r6
            int r1 = r1 - r15
            int r6 = r16.getChar()
            if (r15 > r6) goto L_0x0396
            if (r6 >= r5) goto L_0x0396
            r5 = 31
            if (r1 > r5) goto L_0x0396
            int r1 = r1 * 8
            int r1 = r1 + r6
            int r1 = r1 - r15
            int r6 = r16.getChar()
        L_0x0396:
            r0.ungetChar(r6)
            goto L_0x0405
        L_0x039b:
            r1 = 11
            goto L_0x0405
        L_0x039f:
            int r1 = r0.stringBufferTop
            r5 = 117(0x75, float:1.64E-43)
            r0.addToString(r5)
            r6 = 0
            r7 = 0
        L_0x03a8:
            if (r7 == r11) goto L_0x03bc
            int r9 = r16.getChar()
            int r6 = org.mozilla.javascript.Kit.xDigitToInt(r9, r6)
            if (r6 >= 0) goto L_0x03b6
            r1 = r9
            goto L_0x0347
        L_0x03b6:
            r0.addToString(r9)
            int r7 = r7 + 1
            goto L_0x03a8
        L_0x03bc:
            r0.stringBufferTop = r1
        L_0x03be:
            r1 = r6
            goto L_0x0407
        L_0x03c0:
            r5 = 117(0x75, float:1.64E-43)
            r1 = 9
            goto L_0x0407
        L_0x03c5:
            r5 = 117(0x75, float:1.64E-43)
            int r1 = r16.getChar()
            int r6 = org.mozilla.javascript.Kit.xDigitToInt(r1, r3)
            if (r6 >= 0) goto L_0x03d6
            r0.addToString(r14)
            goto L_0x0347
        L_0x03d6:
            int r7 = r16.getChar()
            int r6 = org.mozilla.javascript.Kit.xDigitToInt(r7, r6)
            if (r6 >= 0) goto L_0x03be
            r0.addToString(r14)
            r0.addToString(r1)
            r1 = r7
            goto L_0x0347
        L_0x03e9:
            r5 = 117(0x75, float:1.64E-43)
            r1 = 13
            goto L_0x0407
        L_0x03ee:
            r5 = 117(0x75, float:1.64E-43)
            r1 = 10
            goto L_0x0407
        L_0x03f3:
            r5 = 117(0x75, float:1.64E-43)
            r1 = 12
            goto L_0x0407
        L_0x03f8:
            r5 = 117(0x75, float:1.64E-43)
            r1 = 8
            goto L_0x0407
        L_0x03fd:
            r5 = 117(0x75, float:1.64E-43)
            int r1 = r16.getChar()
            goto L_0x0347
        L_0x0405:
            r5 = 117(0x75, float:1.64E-43)
        L_0x0407:
            r0.addToString(r1)
            int r1 = r0.getChar(r3)
            goto L_0x0347
        L_0x0410:
            r0.ungetChar(r1)
            int r1 = r0.cursor
            r0.tokenEnd = r1
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.unterminated.string.lit"
            r1.addError(r3)
            return r2
        L_0x041f:
            java.lang.String r1 = r16.getStringFromBuffer()
            org.mozilla.javascript.ObjToIntMap r2 = r0.allStrings
            java.lang.Object r1 = r2.intern(r1)
            java.lang.String r1 = (java.lang.String) r1
            r0.string = r1
            r1 = 41
            return r1
        L_0x0430:
            r0.isOctal = r3
            r0.stringBufferTop = r3
            if (r1 != r15) goto L_0x0457
            int r1 = r16.getChar()
            if (r1 == r14) goto L_0x0450
            r8 = 88
            if (r1 != r8) goto L_0x0441
            goto L_0x0450
        L_0x0441:
            boolean r8 = isDigit(r1)
            if (r8 == 0) goto L_0x044c
            r0.isOctal = r5
            r8 = 8
            goto L_0x0459
        L_0x044c:
            r0.addToString(r15)
            goto L_0x0457
        L_0x0450:
            int r1 = r16.getChar()
            r8 = 16
            goto L_0x0459
        L_0x0457:
            r8 = 10
        L_0x0459:
            if (r8 != r13) goto L_0x0469
        L_0x045b:
            int r9 = org.mozilla.javascript.Kit.xDigitToInt(r1, r3)
            if (r9 < 0) goto L_0x048f
            r0.addToString(r1)
            int r1 = r16.getChar()
            goto L_0x045b
        L_0x0469:
            if (r15 > r1) goto L_0x048f
            r9 = 57
            if (r1 > r9) goto L_0x048f
            r9 = 8
            r11 = 56
            if (r8 != r9) goto L_0x0487
            if (r1 < r11) goto L_0x0487
            org.mozilla.javascript.Parser r8 = r0.parser
            if (r1 != r11) goto L_0x047e
            java.lang.String r12 = "8"
            goto L_0x0480
        L_0x047e:
            java.lang.String r12 = "9"
        L_0x0480:
            java.lang.String r13 = "msg.bad.octal.literal"
            r8.addWarning(r13, r12)
            r8 = 10
        L_0x0487:
            r0.addToString(r1)
            int r1 = r16.getChar()
            goto L_0x0469
        L_0x048f:
            if (r8 != r4) goto L_0x04e1
            if (r1 == r7) goto L_0x0499
            if (r1 == r10) goto L_0x0499
            r9 = 69
            if (r1 != r9) goto L_0x04e1
        L_0x0499:
            if (r1 != r7) goto L_0x04a8
        L_0x049b:
            r0.addToString(r1)
            int r1 = r16.getChar()
            boolean r5 = isDigit(r1)
            if (r5 != 0) goto L_0x049b
        L_0x04a8:
            if (r1 == r10) goto L_0x04b1
            r5 = 69
            if (r1 != r5) goto L_0x04af
            goto L_0x04b1
        L_0x04af:
            r5 = 0
            goto L_0x04e1
        L_0x04b1:
            r0.addToString(r1)
            int r1 = r16.getChar()
            r5 = 43
            if (r1 == r5) goto L_0x04be
            if (r1 != r6) goto L_0x04c5
        L_0x04be:
            r0.addToString(r1)
            int r1 = r16.getChar()
        L_0x04c5:
            boolean r5 = isDigit(r1)
            if (r5 != 0) goto L_0x04d3
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.missing.exponent"
            r1.addError(r3)
            return r2
        L_0x04d3:
            r0.addToString(r1)
            int r1 = r16.getChar()
            boolean r5 = isDigit(r1)
            if (r5 != 0) goto L_0x04d3
            goto L_0x04af
        L_0x04e1:
            r0.ungetChar(r1)
            java.lang.String r1 = r16.getStringFromBuffer()
            r0.string = r1
            if (r8 != r4) goto L_0x04fb
            if (r5 != 0) goto L_0x04fb
            double r1 = java.lang.Double.parseDouble(r1)     // Catch:{ NumberFormatException -> 0x04f3 }
            goto L_0x04ff
        L_0x04f3:
            org.mozilla.javascript.Parser r1 = r0.parser
            java.lang.String r3 = "msg.caught.nfe"
            r1.addError(r3)
            return r2
        L_0x04fb:
            double r1 = org.mozilla.javascript.ScriptRuntime.stringToNumber(r1, r3, r8)
        L_0x04ff:
            r0.number = r1
            r1 = 40
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.getToken():int");
    }

    static boolean isJSSpace(int i) {
        return i <= 127 ? i == 32 || i == 9 || i == 12 || i == 11 : i == 160 || i == 65279 || Character.getType((char) i) == 12;
    }

    private static boolean isJSFormatChar(int i) {
        return i > 127 && Character.getType((char) i) == 16;
    }

    /* access modifiers changed from: package-private */
    public void readRegExp(int i) throws IOException {
        int i2;
        int i3 = this.tokenBeg;
        this.stringBufferTop = 0;
        if (i == 100) {
            addToString(61);
        } else if (i != 24) {
            Kit.codeBug();
        }
        boolean z = false;
        while (true) {
            i2 = getChar();
            if (i2 == 47 && !z) {
                int i4 = this.stringBufferTop;
                while (true) {
                    if (!matchChar(103)) {
                        if (!matchChar(105)) {
                            if (!matchChar(109)) {
                                if (!matchChar(121)) {
                                    break;
                                }
                                addToString(121);
                            } else {
                                addToString(109);
                            }
                        } else {
                            addToString(105);
                        }
                    } else {
                        addToString(103);
                    }
                }
                this.tokenEnd = i3 + this.stringBufferTop + 2;
                if (isAlpha(peekChar())) {
                    this.parser.reportError("msg.invalid.re.flag");
                }
                this.string = new String(this.stringBuffer, 0, i4);
                this.regExpFlags = new String(this.stringBuffer, i4, this.stringBufferTop - i4);
                return;
            } else if (i2 == 10 || i2 == -1) {
                ungetChar(i2);
                this.tokenEnd = this.cursor - 1;
                this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
                this.parser.reportError("msg.unterminated.re.lit");
            } else {
                if (i2 == 92) {
                    addToString(i2);
                    i2 = getChar();
                } else if (i2 == 91) {
                    z = true;
                } else if (i2 == 93) {
                    z = false;
                }
                addToString(i2);
            }
        }
        ungetChar(i2);
        this.tokenEnd = this.cursor - 1;
        this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
        this.parser.reportError("msg.unterminated.re.lit");
    }

    /* access modifiers changed from: package-private */
    public String readAndClearRegExpFlags() {
        String str = this.regExpFlags;
        this.regExpFlags = null;
        return str;
    }

    /* access modifiers changed from: package-private */
    public boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    /* access modifiers changed from: package-private */
    public int getFirstXMLToken() throws IOException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!canUngetChar()) {
            return -1;
        }
        ungetChar(60);
        return getNextXMLToken();
    }

    /* access modifiers changed from: package-private */
    public int getNextXMLToken() throws IOException {
        this.tokenBeg = this.cursor;
        this.stringBufferTop = 0;
        while (true) {
            int i = getChar();
            if (i == -1) {
                this.tokenEnd = this.cursor;
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return -1;
            } else if (this.xmlIsTagContent) {
                if (i == 9 || i == 10 || i == 13 || i == 32) {
                    addToString(i);
                } else if (i == 34 || i == 39) {
                    addToString(i);
                    if (!readQuotedString(i)) {
                        return -1;
                    }
                } else if (i == 47) {
                    addToString(i);
                    if (peekChar() == 62) {
                        addToString(getChar());
                        this.xmlIsTagContent = false;
                        this.xmlOpenTagsCount--;
                    }
                } else if (i == 123) {
                    ungetChar(i);
                    this.string = getStringFromBuffer();
                    return 145;
                } else if (i == 61) {
                    addToString(i);
                    this.xmlIsAttribute = true;
                } else if (i != 62) {
                    addToString(i);
                    this.xmlIsAttribute = false;
                } else {
                    addToString(i);
                    this.xmlIsTagContent = false;
                    this.xmlIsAttribute = false;
                }
                if (!this.xmlIsTagContent && this.xmlOpenTagsCount == 0) {
                    this.string = getStringFromBuffer();
                    return 148;
                }
            } else if (i == 60) {
                addToString(i);
                int peekChar = peekChar();
                if (peekChar == 33) {
                    addToString(getChar());
                    int peekChar2 = peekChar();
                    if (peekChar2 == 45) {
                        addToString(getChar());
                        int i2 = getChar();
                        if (i2 == 45) {
                            addToString(i2);
                            if (!readXmlComment()) {
                                return -1;
                            }
                        } else {
                            this.stringBufferTop = 0;
                            this.string = null;
                            this.parser.addError("msg.XML.bad.form");
                            return -1;
                        }
                    } else if (peekChar2 == 91) {
                        addToString(getChar());
                        if (getChar() == 67 && getChar() == 68 && getChar() == 65 && getChar() == 84 && getChar() == 65 && getChar() == 91) {
                            addToString(67);
                            addToString(68);
                            addToString(65);
                            addToString(84);
                            addToString(65);
                            addToString(91);
                            if (!readCDATA()) {
                                return -1;
                            }
                        } else {
                            this.stringBufferTop = 0;
                            this.string = null;
                            this.parser.addError("msg.XML.bad.form");
                        }
                    } else if (!readEntity()) {
                        return -1;
                    }
                } else if (peekChar == 47) {
                    addToString(getChar());
                    int i3 = this.xmlOpenTagsCount;
                    if (i3 == 0) {
                        this.stringBufferTop = 0;
                        this.string = null;
                        this.parser.addError("msg.XML.bad.form");
                        return -1;
                    }
                    this.xmlIsTagContent = true;
                    this.xmlOpenTagsCount = i3 - 1;
                } else if (peekChar != 63) {
                    this.xmlIsTagContent = true;
                    this.xmlOpenTagsCount++;
                } else {
                    addToString(getChar());
                    if (!readPI()) {
                        return -1;
                    }
                }
            } else if (i != 123) {
                addToString(i);
            } else {
                ungetChar(i);
                this.string = getStringFromBuffer();
                return 145;
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return -1;
    }

    private boolean readQuotedString(int i) throws IOException {
        int i2;
        do {
            i2 = getChar();
            if (i2 != -1) {
                addToString(i2);
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        } while (i2 != i);
        return true;
    }

    private boolean readXmlComment() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 45 && peekChar() == 45) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int i = getChar();
        while (i != -1) {
            addToString(i);
            if (i == 93 && peekChar() == 93) {
                i = getChar();
                addToString(i);
                if (peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                i = getChar();
            }
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int i = getChar();
        int i2 = 1;
        while (i != -1) {
            addToString(i);
            if (i == 60) {
                i2++;
            } else if (i == 62 && i2 - 1 == 0) {
                return true;
            }
            i = getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readPI() throws IOException {
        while (true) {
            int i = getChar();
            if (i != -1) {
                addToString(i);
                if (i == 63 && peekChar() == 62) {
                    addToString(getChar());
                    return true;
                }
            } else {
                this.stringBufferTop = 0;
                this.string = null;
                this.parser.addError("msg.XML.bad.form");
                return false;
            }
        }
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int i) {
        int i2 = this.stringBufferTop;
        char[] cArr = this.stringBuffer;
        if (i2 == cArr.length) {
            char[] cArr2 = new char[(cArr.length * 2)];
            System.arraycopy(cArr, 0, cArr2, 0, i2);
            this.stringBuffer = cArr2;
        }
        this.stringBuffer[i2] = (char) i;
        this.stringBufferTop = i2 + 1;
    }

    private boolean canUngetChar() {
        int i = this.ungetCursor;
        return i == 0 || this.ungetBuffer[i - 1] != 10;
    }

    private void ungetChar(int i) {
        int i2 = this.ungetCursor;
        if (i2 != 0 && this.ungetBuffer[i2 - 1] == 10) {
            Kit.codeBug();
        }
        int[] iArr = this.ungetBuffer;
        int i3 = this.ungetCursor;
        this.ungetCursor = i3 + 1;
        iArr[i3] = i;
        this.cursor--;
    }

    private boolean matchChar(int i) throws IOException {
        int charIgnoreLineEnd = getCharIgnoreLineEnd();
        if (charIgnoreLineEnd == i) {
            this.tokenEnd = this.cursor;
            return true;
        }
        ungetCharIgnoreLineEnd(charIgnoreLineEnd);
        return false;
    }

    private int peekChar() throws IOException {
        int i = getChar();
        ungetChar(i);
        return i;
    }

    private int getChar() throws IOException {
        return getChar(true);
    }

    private int getChar(boolean z) throws IOException {
        char c;
        int i = this.ungetCursor;
        if (i != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i2 = i - 1;
            this.ungetCursor = i2;
            return iArr[i2];
        }
        while (true) {
            String str = this.sourceString;
            if (str != null) {
                int i3 = this.sourceCursor;
                if (i3 == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                this.sourceCursor = i3 + 1;
                c = str.charAt(i3);
            } else if (this.sourceCursor != this.sourceEnd || fillSourceBuffer()) {
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i4 = this.sourceCursor;
                this.sourceCursor = i4 + 1;
                c = cArr[i4];
            } else {
                this.hitEOF = true;
                return -1;
            }
            int i5 = this.lineEndChar;
            if (i5 >= 0) {
                if (i5 == 13 && c == 10) {
                    this.lineEndChar = 10;
                } else {
                    this.lineEndChar = -1;
                    this.lineStart = this.sourceCursor - 1;
                    this.lineno++;
                }
            }
            if (c <= 127) {
                if (c != 10 && c != 13) {
                    return c;
                }
                this.lineEndChar = c;
            } else if (c == 65279) {
                return c;
            } else {
                if (!z || !isJSFormatChar(c)) {
                }
            }
        }
        if (!ScriptRuntime.isJSLineTerminator(c)) {
            return c;
        }
        this.lineEndChar = c;
        return 10;
    }

    private int getCharIgnoreLineEnd() throws IOException {
        char c;
        int i = this.ungetCursor;
        if (i != 0) {
            this.cursor++;
            int[] iArr = this.ungetBuffer;
            int i2 = i - 1;
            this.ungetCursor = i2;
            return iArr[i2];
        }
        while (true) {
            String str = this.sourceString;
            if (str != null) {
                int i3 = this.sourceCursor;
                if (i3 == this.sourceEnd) {
                    this.hitEOF = true;
                    return -1;
                }
                this.cursor++;
                this.sourceCursor = i3 + 1;
                c = str.charAt(i3);
            } else if (this.sourceCursor != this.sourceEnd || fillSourceBuffer()) {
                this.cursor++;
                char[] cArr = this.sourceBuffer;
                int i4 = this.sourceCursor;
                this.sourceCursor = i4 + 1;
                c = cArr[i4];
            } else {
                this.hitEOF = true;
                return -1;
            }
            if (c <= 127) {
                if (c != 10 && c != 13) {
                    return c;
                }
                this.lineEndChar = c;
            } else if (c == 65279) {
                return c;
            } else {
                if (!isJSFormatChar(c)) {
                    if (!ScriptRuntime.isJSLineTerminator(c)) {
                        return c;
                    }
                    this.lineEndChar = c;
                }
            }
        }
        return 10;
    }

    private void ungetCharIgnoreLineEnd(int i) {
        int[] iArr = this.ungetBuffer;
        int i2 = this.ungetCursor;
        this.ungetCursor = i2 + 1;
        iArr[i2] = i;
        this.cursor--;
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private void skipLine() throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            int r0 = r2.getChar()
            r1 = -1
            if (r0 == r1) goto L_0x000c
            r1 = 10
            if (r0 == r1) goto L_0x000c
            goto L_0x0000
        L_0x000c:
            r2.ungetChar(r0)
            int r0 = r2.cursor
            r2.tokenEnd = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.TokenStream.skipLine():void");
    }

    /* access modifiers changed from: package-private */
    public final int getOffset() {
        int i = this.sourceCursor - this.lineStart;
        return this.lineEndChar >= 0 ? i - 1 : i;
    }

    /* access modifiers changed from: package-private */
    public final String getLine() {
        if (this.sourceString != null) {
            int i = this.sourceCursor;
            if (this.lineEndChar >= 0) {
                i--;
            } else {
                while (i != this.sourceEnd && !ScriptRuntime.isJSLineTerminator(this.sourceString.charAt(i))) {
                    i++;
                }
            }
            return this.sourceString.substring(this.lineStart, i);
        }
        int i2 = this.sourceCursor - this.lineStart;
        if (this.lineEndChar >= 0) {
            i2--;
        } else {
            while (true) {
                int i3 = this.lineStart + i2;
                if (i3 == this.sourceEnd) {
                    try {
                        if (!fillSourceBuffer()) {
                            break;
                        }
                        i3 = this.lineStart + i2;
                    } catch (IOException unused) {
                    }
                }
                if (ScriptRuntime.isJSLineTerminator(this.sourceBuffer[i3])) {
                    break;
                }
                i2++;
            }
        }
        return new String(this.sourceBuffer, this.lineStart, i2);
    }

    private boolean fillSourceBuffer() throws IOException {
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart == 0 || isMarkingComment()) {
                char[] cArr = this.sourceBuffer;
                char[] cArr2 = new char[(cArr.length * 2)];
                System.arraycopy(cArr, 0, cArr2, 0, this.sourceEnd);
                this.sourceBuffer = cArr2;
            } else {
                char[] cArr3 = this.sourceBuffer;
                int i = this.lineStart;
                System.arraycopy(cArr3, i, cArr3, 0, this.sourceEnd - i);
                int i2 = this.sourceEnd;
                int i3 = this.lineStart;
                this.sourceEnd = i2 - i3;
                this.sourceCursor -= i3;
                this.lineStart = 0;
            }
        }
        Reader reader = this.sourceReader;
        char[] cArr4 = this.sourceBuffer;
        int i4 = this.sourceEnd;
        int read = reader.read(cArr4, i4, cArr4.length - i4);
        if (read < 0) {
            return false;
        }
        this.sourceEnd += read;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        markCommentStart("");
    }

    private void markCommentStart(String str) {
        if (this.parser.compilerEnv.isRecordingComments() && this.sourceReader != null) {
            this.commentPrefix = str;
            this.commentCursor = this.sourceCursor - 1;
        }
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    /* access modifiers changed from: package-private */
    public final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder sb = new StringBuilder(this.commentPrefix);
        sb.append(this.sourceBuffer, this.commentCursor, getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return sb.toString();
    }

    private String convertLastCharToHex(String str) {
        int length = str.length() - 1;
        StringBuffer stringBuffer2 = new StringBuffer(str.substring(0, length));
        stringBuffer2.append("\\u");
        String hexString = Integer.toHexString(str.charAt(length));
        for (int i = 0; i < 4 - hexString.length(); i++) {
            stringBuffer2.append('0');
        }
        stringBuffer2.append(hexString);
        return stringBuffer2.toString();
    }
}
