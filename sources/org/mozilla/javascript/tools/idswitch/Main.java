package org.mozilla.javascript.tools.idswitch;

import com.facebook.appevents.AppEventsConstants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class Main {
    private static final int GENERATED_TAG = 2;
    private static final String GENERATED_TAG_STR = "generated";
    private static final int NORMAL_LINE = 0;
    private static final int STRING_TAG = 3;
    private static final String STRING_TAG_STR = "string";
    private static final int SWITCH_TAG = 1;
    private static final String SWITCH_TAG_STR = "string_id_map";
    private CodePrinter P;
    private ToolErrorReporter R;
    private final List<IdValuePair> all_pairs = new ArrayList();
    private FileBody body;
    private String source_file;
    private int tag_definition_end;
    private int tag_value_end;
    private int tag_value_start;

    private static boolean is_value_type(int i) {
        return i == 3;
    }

    private static boolean is_white_space(int i) {
        return i == 32 || i == 9;
    }

    private static String tag_name(int i) {
        return i != -2 ? i != -1 ? i != 1 ? i != 2 ? "" : GENERATED_TAG_STR : SWITCH_TAG_STR : "/string_id_map" : "/generated";
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void process_file(String str) throws IOException {
        InputStream inputStream;
        OutputStream outputStream;
        this.source_file = str;
        this.body = new FileBody();
        if (str.equals("-")) {
            inputStream = System.in;
        } else {
            inputStream = new FileInputStream(str);
        }
        try {
            this.body.readData(new InputStreamReader(inputStream, "ASCII"));
            inputStream.close();
            process_file();
            if (this.body.wasModified()) {
                if (str.equals("-")) {
                    outputStream = System.out;
                } else {
                    outputStream = new FileOutputStream(str);
                }
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    this.body.writeData(outputStreamWriter);
                    outputStreamWriter.flush();
                } finally {
                    outputStream.close();
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0094 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void process_file() {
        /*
            r14 = this;
            org.mozilla.javascript.tools.idswitch.FileBody r0 = r14.body
            char[] r0 = r0.getBuffer()
            org.mozilla.javascript.tools.idswitch.FileBody r1 = r14.body
            r1.startLineLoop()
            r1 = 0
            r2 = -1
            r3 = 0
            r4 = -1
            r5 = -1
            r6 = -1
            r7 = -1
        L_0x0012:
            org.mozilla.javascript.tools.idswitch.FileBody r8 = r14.body
            boolean r8 = r8.nextLine()
            if (r8 == 0) goto L_0x00af
            org.mozilla.javascript.tools.idswitch.FileBody r8 = r14.body
            int r8 = r8.getLineBegin()
            org.mozilla.javascript.tools.idswitch.FileBody r9 = r14.body
            int r9 = r9.getLineEnd()
            int r10 = r14.extract_line_tag_id(r0, r8, r9)
            r11 = 2
            r12 = 1
            if (r3 == 0) goto L_0x0082
            if (r3 == r12) goto L_0x0043
            if (r3 == r11) goto L_0x0034
            goto L_0x008f
        L_0x0034:
            if (r10 != 0) goto L_0x003a
            if (r4 >= 0) goto L_0x008f
            r4 = r8
            goto L_0x008f
        L_0x003a:
            r9 = -2
            if (r10 != r9) goto L_0x0090
            if (r4 >= 0) goto L_0x0040
            r4 = r8
        L_0x0040:
            r5 = r8
            r3 = 1
            goto L_0x008f
        L_0x0043:
            if (r10 != 0) goto L_0x0049
            r14.look_for_id_definitions(r0, r8, r9, r1)
            goto L_0x008f
        L_0x0049:
            r13 = 3
            if (r10 != r13) goto L_0x0050
            r14.look_for_id_definitions(r0, r8, r9, r12)
            goto L_0x008f
        L_0x0050:
            if (r10 != r11) goto L_0x005a
            if (r4 < 0) goto L_0x0055
            goto L_0x0090
        L_0x0055:
            int r6 = r14.tag_definition_end
            r7 = r9
            r3 = 2
            goto L_0x008f
        L_0x005a:
            if (r10 != r2) goto L_0x0090
            if (r4 < 0) goto L_0x0080
            java.util.List<org.mozilla.javascript.tools.idswitch.IdValuePair> r3 = r14.all_pairs
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0080
            r14.generate_java_code()
            org.mozilla.javascript.tools.idswitch.CodePrinter r3 = r14.P
            java.lang.String r3 = r3.toString()
            org.mozilla.javascript.tools.idswitch.FileBody r8 = r14.body
            boolean r3 = r8.setReplacement(r4, r5, r3)
            if (r3 == 0) goto L_0x0080
            java.lang.String r3 = r14.get_time_stamp()
            org.mozilla.javascript.tools.idswitch.FileBody r8 = r14.body
            r8.setReplacement(r6, r7, r3)
        L_0x0080:
            r3 = 0
            goto L_0x008f
        L_0x0082:
            if (r10 != r12) goto L_0x008c
            java.util.List<org.mozilla.javascript.tools.idswitch.IdValuePair> r3 = r14.all_pairs
            r3.clear()
            r3 = 1
            r4 = -1
            goto L_0x008f
        L_0x008c:
            if (r10 != r2) goto L_0x008f
            goto L_0x0090
        L_0x008f:
            r12 = 0
        L_0x0090:
            if (r12 != 0) goto L_0x0094
            goto L_0x0012
        L_0x0094:
            java.lang.String r0 = tag_name(r10)
            java.lang.String r1 = "msg.idswitch.bad_tag_order"
            java.lang.String r3 = org.mozilla.javascript.tools.ToolErrorReporter.getMessage((java.lang.String) r1, (java.lang.String) r0)
            org.mozilla.javascript.tools.ToolErrorReporter r2 = r14.R
            java.lang.String r4 = r14.source_file
            org.mozilla.javascript.tools.idswitch.FileBody r0 = r14.body
            int r5 = r0.getLineNumber()
            r6 = 0
            r7 = 0
            org.mozilla.javascript.EvaluatorException r0 = r2.runtimeError(r3, r4, r5, r6, r7)
            throw r0
        L_0x00af:
            if (r3 != 0) goto L_0x00b2
            return
        L_0x00b2:
            java.lang.String r0 = tag_name(r3)
            java.lang.String r1 = "msg.idswitch.file_end_in_switch"
            java.lang.String r3 = org.mozilla.javascript.tools.ToolErrorReporter.getMessage((java.lang.String) r1, (java.lang.String) r0)
            org.mozilla.javascript.tools.ToolErrorReporter r2 = r14.R
            java.lang.String r4 = r14.source_file
            org.mozilla.javascript.tools.idswitch.FileBody r0 = r14.body
            int r5 = r0.getLineNumber()
            r6 = 0
            r7 = 0
            org.mozilla.javascript.EvaluatorException r0 = r2.runtimeError(r3, r4, r5, r6, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.idswitch.Main.process_file():void");
    }

    private String get_time_stamp() {
        return new SimpleDateFormat(" 'Last update:' yyyy-MM-dd HH:mm:ss z").format(new Date());
    }

    private void generate_java_code() {
        this.P.clear();
        IdValuePair[] idValuePairArr = new IdValuePair[this.all_pairs.size()];
        this.all_pairs.toArray(idValuePairArr);
        SwitchGenerator switchGenerator = new SwitchGenerator();
        switchGenerator.char_tail_test_threshold = 2;
        switchGenerator.setReporter(this.R);
        switchGenerator.setCodePrinter(this.P);
        switchGenerator.generateSwitch(idValuePairArr, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    private int extract_line_tag_id(char[] cArr, int i, int i2) {
        boolean z;
        int skip_white_space;
        char c;
        int skip_white_space2 = skip_white_space(cArr, i, i2);
        int look_for_slash_slash = look_for_slash_slash(cArr, skip_white_space2, i2);
        int i3 = 0;
        if (look_for_slash_slash != i2) {
            boolean z2 = skip_white_space2 + 2 == look_for_slash_slash;
            int skip_white_space3 = skip_white_space(cArr, look_for_slash_slash, i2);
            if (skip_white_space3 != i2 && cArr[skip_white_space3] == '#') {
                int i4 = skip_white_space3 + 1;
                if (i4 == i2 || cArr[i4] != '/') {
                    z = false;
                } else {
                    i4++;
                    z = true;
                }
                int i5 = i4;
                while (i5 != i2 && (r7 = cArr[i5]) != '#' && r7 != '=' && !is_white_space(r7)) {
                    i5++;
                }
                if (!(i5 == i2 || (skip_white_space = skip_white_space(cArr, i5, i2)) == i2 || (((c = cArr[skip_white_space]) != '=' && c != '#') || (i3 = get_tag_id(cArr, i4, i5, z2)) == 0))) {
                    String str = null;
                    if (c == '#') {
                        if (z) {
                            i3 = -i3;
                            if (is_value_type(i3)) {
                                str = "msg.idswitch.no_end_usage";
                            }
                        }
                        this.tag_definition_end = skip_white_space + 1;
                    } else {
                        if (z) {
                            str = "msg.idswitch.no_end_with_value";
                        } else if (!is_value_type(i3)) {
                            str = "msg.idswitch.no_value_allowed";
                        }
                        i3 = extract_tag_value(cArr, skip_white_space + 1, i2, i3);
                    }
                    if (str != null) {
                        throw this.R.runtimeError(ToolErrorReporter.getMessage(str, tag_name(i3)), this.source_file, this.body.getLineNumber(), (String) null, 0);
                    }
                }
            }
        }
        return i3;
    }

    private int look_for_slash_slash(char[] cArr, int i, int i2) {
        while (i + 2 <= i2) {
            int i3 = i + 1;
            if (cArr[i] == '/') {
                i = i3 + 1;
                if (cArr[i3] == '/') {
                    return i;
                }
            } else {
                i = i3;
            }
        }
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int extract_tag_value(char[] r7, int r8, int r9, int r10) {
        /*
            r6 = this;
            int r8 = skip_white_space(r7, r8, r9)
            r0 = 0
            r1 = 1
            if (r8 == r9) goto L_0x003a
            r2 = r8
        L_0x0009:
            if (r2 == r9) goto L_0x002f
            char r3 = r7[r2]
            boolean r4 = is_white_space(r3)
            r5 = 35
            if (r4 == 0) goto L_0x0028
            int r3 = r2 + 1
            int r3 = skip_white_space(r7, r3, r9)
            if (r3 == r9) goto L_0x0024
            char r4 = r7[r3]
            if (r4 != r5) goto L_0x0024
            r7 = r2
            r2 = r3
            goto L_0x0030
        L_0x0024:
            int r3 = r3 + 1
            r2 = r3
            goto L_0x0009
        L_0x0028:
            if (r3 != r5) goto L_0x002c
            r7 = r2
            goto L_0x0030
        L_0x002c:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x002f:
            r7 = r8
        L_0x0030:
            if (r2 == r9) goto L_0x003a
            r6.tag_value_start = r8
            r6.tag_value_end = r7
            int r2 = r2 + r1
            r6.tag_definition_end = r2
            goto L_0x003b
        L_0x003a:
            r1 = 0
        L_0x003b:
            if (r1 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r10 = 0
        L_0x003f:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.idswitch.Main.extract_tag_value(char[], int, int, int):int");
    }

    private int get_tag_id(char[] cArr, int i, int i2, boolean z) {
        if (z) {
            if (equals(SWITCH_TAG_STR, cArr, i, i2)) {
                return 1;
            }
            if (equals(GENERATED_TAG_STR, cArr, i, i2)) {
                return 2;
            }
        }
        return equals(STRING_TAG_STR, cArr, i, i2) ? 3 : 0;
    }

    private void look_for_id_definitions(char[] cArr, int i, int i2, boolean z) {
        int skip_name_char;
        int skip_white_space;
        int i3;
        int i4;
        int skip_white_space2 = skip_white_space(cArr, i, i2);
        int skip_matched_prefix = skip_matched_prefix("Id_", cArr, skip_white_space2, i2);
        if (skip_matched_prefix >= 0 && skip_matched_prefix != (skip_name_char = skip_name_char(cArr, skip_matched_prefix, i2)) && (skip_white_space = skip_white_space(cArr, skip_name_char, i2)) != i2 && cArr[skip_white_space] == '=') {
            if (z) {
                i4 = this.tag_value_start;
                i3 = this.tag_value_end;
            } else {
                i4 = skip_matched_prefix;
                i3 = skip_name_char;
            }
            add_id(cArr, skip_white_space2, skip_name_char, i4, i3);
        }
    }

    private void add_id(char[] cArr, int i, int i2, int i3, int i4) {
        IdValuePair idValuePair = new IdValuePair(new String(cArr, i3, i4 - i3), new String(cArr, i, i2 - i));
        idValuePair.setLineNumber(this.body.getLineNumber());
        this.all_pairs.add(idValuePair);
    }

    private static int skip_white_space(char[] cArr, int i, int i2) {
        while (i != i2 && is_white_space(cArr[i])) {
            i++;
        }
        return i;
    }

    private static int skip_matched_prefix(String str, char[] cArr, int i, int i2) {
        int length = str.length();
        if (length > i2 - i) {
            return -1;
        }
        int i3 = 0;
        while (i3 != length) {
            if (str.charAt(i3) != cArr[i]) {
                return -1;
            }
            i3++;
            i++;
        }
        return i;
    }

    private static boolean equals(String str, char[] cArr, int i, int i2) {
        if (str.length() != i2 - i) {
            return false;
        }
        int i3 = 0;
        while (i != i2) {
            if (cArr[i] != str.charAt(i3)) {
                return false;
            }
            i++;
            i3++;
        }
        return true;
    }

    private static int skip_name_char(char[] cArr, int i, int i2) {
        while (i != i2) {
            char c = cArr[i];
            if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && (('0' > c || c > '9') && c != '_'))) {
                break;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] strArr) {
        System.exit(new Main().exec(strArr));
    }

    private int exec(String[] strArr) {
        this.R = new ToolErrorReporter(true, System.err);
        int process_options = process_options(strArr);
        if (process_options == 0) {
            option_error(ToolErrorReporter.getMessage("msg.idswitch.no_file_argument"));
            return -1;
        } else if (process_options > 1) {
            option_error(ToolErrorReporter.getMessage("msg.idswitch.too_many_arguments"));
            return -1;
        } else {
            CodePrinter codePrinter = new CodePrinter();
            this.P = codePrinter;
            codePrinter.setIndentStep(4);
            this.P.setIndentTabSize(0);
            try {
                process_file(strArr[0]);
                return 0;
            } catch (IOException e) {
                print_error(ToolErrorReporter.getMessage("msg.idswitch.io_error", e.toString()));
                return -1;
            } catch (EvaluatorException unused) {
                return -1;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int process_options(java.lang.String[] r14) {
        /*
            r13 = this;
            int r0 = r14.length
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0005:
            r5 = -1
            r6 = 1
            if (r2 == r0) goto L_0x0066
            r7 = r14[r2]
            int r8 = r7.length()
            r9 = 2
            if (r8 < r9) goto L_0x0063
            char r10 = r7.charAt(r1)
            r11 = 45
            if (r10 != r11) goto L_0x0063
            char r10 = r7.charAt(r6)
            r12 = 0
            if (r10 != r11) goto L_0x0044
            if (r8 != r9) goto L_0x0026
            r14[r2] = r12
            goto L_0x0066
        L_0x0026:
            java.lang.String r8 = "--help"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x0030
            r3 = 1
            goto L_0x0061
        L_0x0030:
            java.lang.String r8 = "--version"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x003a
            r4 = 1
            goto L_0x0061
        L_0x003a:
            java.lang.String r0 = "msg.idswitch.bad_option"
            java.lang.String r0 = org.mozilla.javascript.tools.ToolErrorReporter.getMessage((java.lang.String) r0, (java.lang.String) r7)
            r13.option_error(r0)
            goto L_0x0067
        L_0x0044:
            r9 = 1
        L_0x0045:
            if (r9 == r8) goto L_0x0061
            char r10 = r7.charAt(r9)
            r11 = 104(0x68, float:1.46E-43)
            if (r10 == r11) goto L_0x005d
            java.lang.String r0 = java.lang.String.valueOf(r10)
            java.lang.String r2 = "msg.idswitch.bad_option_char"
            java.lang.String r0 = org.mozilla.javascript.tools.ToolErrorReporter.getMessage((java.lang.String) r2, (java.lang.String) r0)
            r13.option_error(r0)
            goto L_0x0067
        L_0x005d:
            int r9 = r9 + 1
            r3 = 1
            goto L_0x0045
        L_0x0061:
            r14[r2] = r12
        L_0x0063:
            int r2 = r2 + 1
            goto L_0x0005
        L_0x0066:
            r5 = 1
        L_0x0067:
            if (r5 != r6) goto L_0x0075
            if (r3 == 0) goto L_0x006f
            r13.show_usage()
            r5 = 0
        L_0x006f:
            if (r4 == 0) goto L_0x0075
            r13.show_version()
            goto L_0x0076
        L_0x0075:
            r1 = r5
        L_0x0076:
            if (r1 == r6) goto L_0x007b
            java.lang.System.exit(r1)
        L_0x007b:
            int r14 = r13.remove_nulls(r14)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.idswitch.Main.process_options(java.lang.String[]):int");
    }

    private void show_usage() {
        System.out.println(ToolErrorReporter.getMessage("msg.idswitch.usage"));
        System.out.println();
    }

    private void show_version() {
        System.out.println(ToolErrorReporter.getMessage("msg.idswitch.version"));
    }

    private void option_error(String str) {
        print_error(ToolErrorReporter.getMessage("msg.idswitch.bad_invocation", str));
    }

    private void print_error(String str) {
        System.err.println(str);
    }

    private int remove_nulls(String[] strArr) {
        int length = strArr.length;
        int i = 0;
        while (i != length && strArr[i] != null) {
            i++;
        }
        if (i != length) {
            for (int i2 = i + 1; i2 != length; i2++) {
                String str = strArr[i2];
                if (str != null) {
                    strArr[i] = str;
                    i++;
                }
            }
        }
        return i;
    }
}
