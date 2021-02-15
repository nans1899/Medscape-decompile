package org.mozilla.javascript.tools.idswitch;

import com.appboy.Constants;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class SwitchGenerator {
    private CodePrinter P;
    private ToolErrorReporter R;
    private boolean c_was_defined;
    int char_tail_test_threshold = 2;
    private int[] columns;
    private String default_value;
    private IdValuePair[] pairs;
    private String source_file;
    int use_if_threshold = 3;
    String v_c = "c";
    String v_guess = "X";
    String v_id = "id";
    String v_label = "L";
    String v_length_suffix = "_length";
    String v_s = Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY;
    String v_switch_label = "L0";

    public CodePrinter getCodePrinter() {
        return this.P;
    }

    public void setCodePrinter(CodePrinter codePrinter) {
        this.P = codePrinter;
    }

    public ToolErrorReporter getReporter() {
        return this.R;
    }

    public void setReporter(ToolErrorReporter toolErrorReporter) {
        this.R = toolErrorReporter;
    }

    public String getSourceFileName() {
        return this.source_file;
    }

    public void setSourceFileName(String str) {
        this.source_file = str;
    }

    public void generateSwitch(String[] strArr, String str) {
        int length = strArr.length / 2;
        IdValuePair[] idValuePairArr = new IdValuePair[length];
        for (int i = 0; i != length; i++) {
            int i2 = i * 2;
            idValuePairArr[i] = new IdValuePair(strArr[i2], strArr[i2 + 1]);
        }
        generateSwitch(idValuePairArr, str);
    }

    public void generateSwitch(IdValuePair[] idValuePairArr, String str) {
        int length = idValuePairArr.length;
        if (length != 0) {
            this.pairs = idValuePairArr;
            this.default_value = str;
            generate_body(0, length, 2);
        }
    }

    private void generate_body(int i, int i2, int i3) {
        this.P.indent(i3);
        this.P.p(this.v_switch_label);
        this.P.p(": { ");
        this.P.p(this.v_id);
        this.P.p(" = ");
        this.P.p(this.default_value);
        this.P.p("; String ");
        this.P.p(this.v_guess);
        this.P.p(" = null;");
        this.c_was_defined = false;
        int offset = this.P.getOffset();
        this.P.p(" int ");
        this.P.p(this.v_c);
        this.P.p(';');
        int offset2 = this.P.getOffset();
        this.P.nl();
        int i4 = i3 + 1;
        generate_length_switch(i, i2, i4);
        if (!this.c_was_defined) {
            this.P.erase(offset, offset2);
        }
        this.P.indent(i4);
        this.P.p("if (");
        this.P.p(this.v_guess);
        this.P.p("!=null && ");
        this.P.p(this.v_guess);
        this.P.p("!=");
        this.P.p(this.v_s);
        this.P.p(" && !");
        this.P.p(this.v_guess);
        this.P.p(".equals(");
        this.P.p(this.v_s);
        this.P.p(")) ");
        this.P.p(this.v_id);
        this.P.p(" = ");
        this.P.p(this.default_value);
        this.P.p(";");
        this.P.nl();
        this.P.indent(i4);
        this.P.p("break ");
        this.P.p(this.v_switch_label);
        this.P.p(";");
        this.P.nl();
        this.P.line(i3, "}");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generate_length_switch(int r12, int r13, int r14) {
        /*
            r11 = this;
            r0 = -1
            r11.sort_pairs(r12, r13, r0)
            r11.check_all_is_different(r12, r13)
            int r0 = r11.count_different_lengths(r12, r13)
            org.mozilla.javascript.tools.idswitch.IdValuePair[] r1 = r11.pairs
            int r2 = r13 + -1
            r1 = r1[r2]
            int r1 = r1.idLength
            int[] r1 = new int[r1]
            r11.columns = r1
            int r1 = r11.use_if_threshold
            r2 = 0
            r3 = 1
            if (r0 > r1) goto L_0x0055
            if (r0 == r3) goto L_0x0053
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            r1.indent(r14)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = "int "
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = r11.v_s
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = r11.v_length_suffix
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = " = "
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = r11.v_s
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = ".length();"
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            r1.nl()
        L_0x0053:
            r1 = 1
            goto L_0x007c
        L_0x0055:
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            r1.indent(r14)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = r11.v_label
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = ": switch ("
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = r11.v_s
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            java.lang.String r4 = ".length()) {"
            r1.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r1 = r11.P
            r1.nl()
            r1 = 0
        L_0x007c:
            org.mozilla.javascript.tools.idswitch.IdValuePair[] r4 = r11.pairs
            r4 = r4[r12]
            int r4 = r4.idLength
            r5 = r12
        L_0x0083:
            r6 = r5
        L_0x0084:
            int r10 = r6 + 1
            if (r10 == r13) goto L_0x0093
            org.mozilla.javascript.tools.idswitch.IdValuePair[] r2 = r11.pairs
            r2 = r2[r10]
            int r2 = r2.idLength
            if (r2 == r4) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r6 = r10
            goto L_0x0084
        L_0x0093:
            if (r1 == 0) goto L_0x00dd
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            r6.indent(r14)
            if (r5 == r12) goto L_0x00a3
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = "else "
            r6.p((java.lang.String) r7)
        L_0x00a3:
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = "if ("
            r6.p((java.lang.String) r7)
            if (r0 != r3) goto L_0x00bb
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = r11.v_s
            r6.p((java.lang.String) r7)
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = ".length()=="
            r6.p((java.lang.String) r7)
            goto L_0x00d0
        L_0x00bb:
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = r11.v_s
            r6.p((java.lang.String) r7)
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = r11.v_length_suffix
            r6.p((java.lang.String) r7)
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = "=="
            r6.p((java.lang.String) r7)
        L_0x00d0:
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            r6.p((int) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r4 = r11.P
            java.lang.String r6 = ") {"
            r4.p((java.lang.String) r6)
            goto L_0x00f5
        L_0x00dd:
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            r6.indent(r14)
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            java.lang.String r7 = "case "
            r6.p((java.lang.String) r7)
            org.mozilla.javascript.tools.idswitch.CodePrinter r6 = r11.P
            r6.p((int) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r4 = r11.P
            java.lang.String r6 = ":"
            r4.p((java.lang.String) r6)
        L_0x00f5:
            int r4 = r14 + 1
            r7 = r4
            r8 = r1 ^ 1
            r4 = r11
            r6 = r10
            r9 = r1
            r4.generate_letter_switch(r5, r6, r7, r8, r9)
            java.lang.String r4 = "}"
            if (r1 == 0) goto L_0x010f
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            r5.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            r5.nl()
            goto L_0x0129
        L_0x010f:
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            java.lang.String r6 = "break "
            r5.p((java.lang.String) r6)
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            java.lang.String r6 = r11.v_label
            r5.p((java.lang.String) r6)
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            java.lang.String r6 = ";"
            r5.p((java.lang.String) r6)
            org.mozilla.javascript.tools.idswitch.CodePrinter r5 = r11.P
            r5.nl()
        L_0x0129:
            if (r10 != r13) goto L_0x013d
            if (r1 != 0) goto L_0x013c
            org.mozilla.javascript.tools.idswitch.CodePrinter r12 = r11.P
            r12.indent(r14)
            org.mozilla.javascript.tools.idswitch.CodePrinter r12 = r11.P
            r12.p((java.lang.String) r4)
            org.mozilla.javascript.tools.idswitch.CodePrinter r12 = r11.P
            r12.nl()
        L_0x013c:
            return
        L_0x013d:
            r4 = r2
            r5 = r10
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.idswitch.SwitchGenerator.generate_length_switch(int, int, int):void");
    }

    private void generate_letter_switch(int i, int i2, int i3, boolean z, boolean z2) {
        int i4 = this.pairs[i].idLength;
        for (int i5 = 0; i5 != i4; i5++) {
            this.columns[i5] = i5;
        }
        generate_letter_switch_r(i, i2, i4, i3, z, z2);
    }

    private boolean generate_letter_switch_r(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        int i5;
        boolean z5;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        boolean z6 = false;
        if (i6 + 1 == i7) {
            this.P.p(' ');
            IdValuePair idValuePair = this.pairs[i6];
            if (i8 > this.char_tail_test_threshold) {
                this.P.p(this.v_guess);
                this.P.p("=");
                this.P.qstring(idValuePair.id);
                this.P.p(";");
                this.P.p(this.v_id);
                this.P.p("=");
                this.P.p(idValuePair.value);
                this.P.p(";");
            } else if (i8 == 0) {
                this.P.p(this.v_id);
                this.P.p("=");
                this.P.p(idValuePair.value);
                this.P.p("; break ");
                this.P.p(this.v_switch_label);
                this.P.p(";");
                z6 = true;
            } else {
                this.P.p("if (");
                int i10 = this.columns[0];
                this.P.p(this.v_s);
                this.P.p(".charAt(");
                this.P.p(i10);
                this.P.p(")==");
                this.P.qchar(idValuePair.id.charAt(i10));
                for (int i11 = 1; i11 != i8; i11++) {
                    this.P.p(" && ");
                    int i12 = this.columns[i11];
                    this.P.p(this.v_s);
                    this.P.p(".charAt(");
                    this.P.p(i12);
                    this.P.p(")==");
                    this.P.qchar(idValuePair.id.charAt(i12));
                }
                this.P.p(") {");
                this.P.p(this.v_id);
                this.P.p("=");
                this.P.p(idValuePair.value);
                this.P.p("; break ");
                this.P.p(this.v_switch_label);
                this.P.p(";}");
            }
            this.P.p(' ');
            return z6;
        }
        int find_max_different_column = find_max_different_column(i, i2, i3);
        int i13 = this.columns[find_max_different_column];
        int count_different_chars = count_different_chars(i6, i7, i13);
        int[] iArr = this.columns;
        int i14 = i8 - 1;
        iArr[find_max_different_column] = iArr[i14];
        if (z2) {
            this.P.nl();
            this.P.indent(i9);
        } else {
            this.P.p(' ');
        }
        if (count_different_chars <= this.use_if_threshold) {
            this.c_was_defined = true;
            this.P.p(this.v_c);
            this.P.p("=");
            this.P.p(this.v_s);
            this.P.p(".charAt(");
            this.P.p(i13);
            this.P.p(");");
            z4 = z;
            z3 = true;
        } else {
            if (!z) {
                this.P.p(this.v_label);
                this.P.p(": ");
                z5 = true;
            } else {
                z5 = z;
            }
            this.P.p("switch (");
            this.P.p(this.v_s);
            this.P.p(".charAt(");
            this.P.p(i13);
            this.P.p(")) {");
            z4 = z5;
            z3 = false;
        }
        char charAt = this.pairs[i6].id.charAt(i13);
        int i15 = i6;
        int i16 = i15;
        char c = 0;
        while (true) {
            int i17 = i16 + 1;
            if (i17 == i7 || (c = this.pairs[i17].id.charAt(i13)) != charAt) {
                char c2 = c;
                if (z3) {
                    this.P.nl();
                    this.P.indent(i9);
                    if (i15 != i6) {
                        this.P.p("else ");
                    }
                    this.P.p("if (");
                    this.P.p(this.v_c);
                    this.P.p("==");
                    this.P.qchar(charAt);
                    this.P.p(") {");
                } else {
                    this.P.nl();
                    this.P.indent(i9);
                    this.P.p("case ");
                    this.P.qchar(charAt);
                    this.P.p(":");
                }
                int i18 = i17;
                i5 = i13;
                boolean generate_letter_switch_r = generate_letter_switch_r(i15, i17, i14, i9 + 1, z4, z3);
                if (z3) {
                    this.P.p("}");
                } else if (!generate_letter_switch_r) {
                    this.P.p("break ");
                    this.P.p(this.v_label);
                    this.P.p(";");
                }
                if (i18 == i7) {
                    break;
                }
                i15 = i18;
                i16 = i15;
                charAt = c2;
                c = charAt;
                i13 = i5;
            } else {
                i16 = i17;
            }
        }
        if (z3) {
            this.P.nl();
            if (z2) {
                this.P.indent(i9 - 1);
            } else {
                this.P.indent(i9);
            }
        } else {
            this.P.nl();
            this.P.indent(i9);
            this.P.p("}");
            if (z2) {
                this.P.nl();
                this.P.indent(i9 - 1);
            } else {
                this.P.p(' ');
            }
        }
        this.columns[find_max_different_column] = i5;
        return false;
    }

    private int count_different_lengths(int i, int i2) {
        int i3 = 0;
        int i4 = -1;
        while (i != i2) {
            int i5 = this.pairs[i].idLength;
            if (i4 != i5) {
                i3++;
                i4 = i5;
            }
            i++;
        }
        return i3;
    }

    private int find_max_different_column(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 != i3; i6++) {
            int i7 = this.columns[i6];
            sort_pairs(i, i2, i7);
            int count_different_chars = count_different_chars(i, i2, i7);
            if (count_different_chars == i2 - i) {
                return i6;
            }
            if (i5 < count_different_chars) {
                i4 = i6;
                i5 = count_different_chars;
            }
        }
        if (i4 != i3 - 1) {
            sort_pairs(i, i2, this.columns[i4]);
        }
        return i4;
    }

    private int count_different_chars(int i, int i2, int i3) {
        int i4 = 0;
        char c = 65535;
        while (i != i2) {
            char charAt = this.pairs[i].id.charAt(i3);
            if (charAt != c) {
                i4++;
                c = charAt;
            }
            i++;
        }
        return i4;
    }

    private void check_all_is_different(int i, int i2) {
        if (i != i2) {
            IdValuePair idValuePair = this.pairs[i];
            while (true) {
                i++;
                if (i != i2) {
                    IdValuePair idValuePair2 = this.pairs[i];
                    if (!idValuePair.id.equals(idValuePair2.id)) {
                        idValuePair = idValuePair2;
                    } else {
                        throw on_same_pair_fail(idValuePair, idValuePair2);
                    }
                } else {
                    return;
                }
            }
        }
    }

    private EvaluatorException on_same_pair_fail(IdValuePair idValuePair, IdValuePair idValuePair2) {
        int i;
        int lineNumber = idValuePair.getLineNumber();
        int lineNumber2 = idValuePair2.getLineNumber();
        if (lineNumber2 > lineNumber) {
            i = lineNumber2;
        } else {
            i = lineNumber;
            lineNumber = lineNumber2;
        }
        return this.R.runtimeError(ToolErrorReporter.getMessage("msg.idswitch.same_string", idValuePair.id, new Integer(lineNumber)), this.source_file, i, (String) null, 0);
    }

    private void sort_pairs(int i, int i2, int i3) {
        heap4Sort(this.pairs, i, i2 - i, i3);
    }

    private static boolean bigger(IdValuePair idValuePair, IdValuePair idValuePair2, int i) {
        if (i < 0) {
            int i2 = idValuePair.idLength - idValuePair2.idLength;
            if (i2 != 0) {
                return i2 > 0;
            }
            if (idValuePair.id.compareTo(idValuePair2.id) > 0) {
                return true;
            }
            return false;
        } else if (idValuePair.id.charAt(i) > idValuePair2.id.charAt(i)) {
            return true;
        } else {
            return false;
        }
    }

    private static void heap4Sort(IdValuePair[] idValuePairArr, int i, int i2, int i3) {
        if (i2 > 1) {
            makeHeap4(idValuePairArr, i, i2, i3);
            while (i2 > 1) {
                i2--;
                int i4 = i + i2;
                IdValuePair idValuePair = idValuePairArr[i4];
                int i5 = i + 0;
                idValuePairArr[i4] = idValuePairArr[i5];
                idValuePairArr[i5] = idValuePair;
                heapify4(idValuePairArr, i, i2, 0, i3);
            }
        }
    }

    private static void makeHeap4(IdValuePair[] idValuePairArr, int i, int i2, int i3) {
        int i4 = (i2 + 2) >> 2;
        while (i4 != 0) {
            i4--;
            heapify4(idValuePairArr, i, i2, i4, i3);
        }
    }

    private static void heapify4(IdValuePair[] idValuePairArr, int i, int i2, int i3, int i4) {
        IdValuePair idValuePair = idValuePairArr[i + i3];
        while (true) {
            int i5 = i3 << 2;
            int i6 = i5 | 1;
            int i7 = i5 | 2;
            int i8 = i5 | 3;
            int i9 = i5 + 4;
            if (i9 < i2) {
                IdValuePair idValuePair2 = idValuePairArr[i + i6];
                IdValuePair idValuePair3 = idValuePairArr[i + i7];
                IdValuePair idValuePair4 = idValuePairArr[i + i8];
                IdValuePair idValuePair5 = idValuePairArr[i + i9];
                if (bigger(idValuePair3, idValuePair2, i4)) {
                    i6 = i7;
                    idValuePair2 = idValuePair3;
                }
                if (bigger(idValuePair5, idValuePair4, i4)) {
                    i8 = i9;
                    idValuePair4 = idValuePair5;
                }
                if (bigger(idValuePair4, idValuePair2, i4)) {
                    i6 = i8;
                    idValuePair2 = idValuePair4;
                }
                if (!bigger(idValuePair, idValuePair2, i4)) {
                    idValuePairArr[i3 + i] = idValuePair2;
                    idValuePairArr[i + i6] = idValuePair;
                    i3 = i6;
                } else {
                    return;
                }
            } else if (i6 < i2) {
                IdValuePair idValuePair6 = idValuePairArr[i + i6];
                if (i7 != i2) {
                    IdValuePair idValuePair7 = idValuePairArr[i + i7];
                    if (bigger(idValuePair7, idValuePair6, i4)) {
                        i6 = i7;
                        idValuePair6 = idValuePair7;
                    }
                    if (i8 != i2) {
                        IdValuePair idValuePair8 = idValuePairArr[i + i8];
                        if (bigger(idValuePair8, idValuePair6, i4)) {
                            idValuePair6 = idValuePair8;
                            i6 = i8;
                        }
                    }
                }
                if (bigger(idValuePair6, idValuePair, i4)) {
                    idValuePairArr[i3 + i] = idValuePair6;
                    idValuePairArr[i + i6] = idValuePair;
                    return;
                }
                return;
            } else {
                return;
            }
        }
    }
}
