package net.bytebuddy.jar.asm;

import androidx.core.view.InputDeviceCompat;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.bytebuddy.description.method.MethodDescription;
import okio.Utf8;

public class ClassReader {
    static final int EXPAND_ASM_INSNS = 256;
    public static final int EXPAND_FRAMES = 8;
    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public final byte[] b;
    private final int[] bootstrapMethodOffsets;
    private final ConstantDynamic[] constantDynamicValues;
    private final String[] constantUtf8Values;
    private final int[] cpInfoOffsets;
    public final int header;
    private final int maxStringLength;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this(bArr, i, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0067, code lost:
        r5 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        r11 = r11 + r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ClassReader(byte[] r10, int r11, boolean r12) {
        /*
            r9 = this;
            r9.<init>()
            r9.b = r10
            if (r12 == 0) goto L_0x002d
            int r12 = r11 + 6
            short r0 = r9.readShort(r12)
            r1 = 56
            if (r0 > r1) goto L_0x0012
            goto L_0x002d
        L_0x0012:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Unsupported class file major version "
            r11.append(r0)
            short r12 = r9.readShort(r12)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x002d:
            int r12 = r11 + 8
            int r12 = r9.readUnsignedShort(r12)
            int[] r0 = new int[r12]
            r9.cpInfoOffsets = r0
            java.lang.String[] r0 = new java.lang.String[r12]
            r9.constantUtf8Values = r0
            int r11 = r11 + 10
            r0 = 0
            r1 = 1
            r2 = 0
            r3 = 0
            r4 = 1
        L_0x0042:
            if (r4 >= r12) goto L_0x0074
            int[] r5 = r9.cpInfoOffsets
            int r6 = r4 + 1
            int r7 = r11 + 1
            r5[r4] = r7
            byte r4 = r10[r11]
            r5 = 3
            r8 = 5
            switch(r4) {
                case 1: goto L_0x0069;
                case 2: goto L_0x0053;
                case 3: goto L_0x0066;
                case 4: goto L_0x0066;
                case 5: goto L_0x0061;
                case 6: goto L_0x0061;
                case 7: goto L_0x0071;
                case 8: goto L_0x0071;
                case 9: goto L_0x0066;
                case 10: goto L_0x0066;
                case 11: goto L_0x0066;
                case 12: goto L_0x0066;
                case 13: goto L_0x0053;
                case 14: goto L_0x0053;
                case 15: goto L_0x005f;
                case 16: goto L_0x0071;
                case 17: goto L_0x005c;
                case 18: goto L_0x0059;
                case 19: goto L_0x0071;
                case 20: goto L_0x0071;
                default: goto L_0x0053;
            }
        L_0x0053:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>()
            throw r10
        L_0x0059:
            r4 = r6
            r3 = 1
            goto L_0x0067
        L_0x005c:
            r4 = r6
            r2 = 1
            goto L_0x0067
        L_0x005f:
            r5 = 4
            goto L_0x0071
        L_0x0061:
            r5 = 9
            int r6 = r6 + 1
            goto L_0x0071
        L_0x0066:
            r4 = r6
        L_0x0067:
            r5 = 5
            goto L_0x0072
        L_0x0069:
            int r4 = r9.readUnsignedShort(r7)
            int r5 = r5 + r4
            if (r5 <= r0) goto L_0x0071
            r0 = r5
        L_0x0071:
            r4 = r6
        L_0x0072:
            int r11 = r11 + r5
            goto L_0x0042
        L_0x0074:
            r9.maxStringLength = r0
            r9.header = r11
            r10 = 0
            if (r2 == 0) goto L_0x007e
            net.bytebuddy.jar.asm.ConstantDynamic[] r11 = new net.bytebuddy.jar.asm.ConstantDynamic[r12]
            goto L_0x007f
        L_0x007e:
            r11 = r10
        L_0x007f:
            r9.constantDynamicValues = r11
            r11 = r2 | r3
            if (r11 == 0) goto L_0x0089
            int[] r10 = r9.readBootstrapMethodsAttribute(r0)
        L_0x0089:
            r9.bootstrapMethodOffsets = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.<init>(byte[], int, boolean):void");
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(readStream(inputStream, false));
    }

    public ClassReader(String str) throws IOException {
        this(readStream(ClassLoader.getSystemResourceAsStream(str.replace('.', '/') + ".class"), true));
    }

    private static byte[] readStream(InputStream inputStream, boolean z) throws IOException {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            } finally {
                if (z) {
                    inputStream.close();
                }
            }
        } else {
            throw new IOException("Class not found");
        }
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public String getSuperName() {
        return readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = new String[readUnsignedShort];
        if (readUnsignedShort > 0) {
            char[] cArr = new char[this.maxStringLength];
            for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                i += 2;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        String str;
        String str2;
        int i2;
        int i3;
        String[] strArr;
        int i4;
        ClassVisitor classVisitor2 = classVisitor;
        int i5 = i;
        Context context = new Context();
        context.attributePrototypes = attributeArr;
        context.parsingOptions = i5;
        context.charBuffer = new char[this.maxStringLength];
        char[] cArr = context.charBuffer;
        int i6 = this.header;
        int readUnsignedShort = readUnsignedShort(i6);
        String readClass = readClass(i6 + 2, cArr);
        String readClass2 = readClass(i6 + 4, cArr);
        int readUnsignedShort2 = readUnsignedShort(i6 + 6);
        String[] strArr2 = new String[readUnsignedShort2];
        int i7 = i6 + 8;
        for (int i8 = 0; i8 < readUnsignedShort2; i8++) {
            strArr2[i8] = readClass(i7, cArr);
            i7 += 2;
        }
        int firstAttributeOffset = getFirstAttributeOffset();
        int i9 = readUnsignedShort;
        int readUnsignedShort3 = readUnsignedShort(firstAttributeOffset - 2);
        String str3 = null;
        String str4 = null;
        int i10 = 0;
        String str5 = null;
        int i11 = 0;
        String str6 = null;
        String str7 = null;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        Attribute attribute = null;
        int i18 = 0;
        while (readUnsignedShort3 > 0) {
            String readUTF8 = readUTF8(firstAttributeOffset, cArr);
            int readInt = readInt(firstAttributeOffset + 2);
            int i19 = firstAttributeOffset + 6;
            String str8 = str3;
            if ("SourceFile".equals(readUTF8)) {
                i3 = i19;
                str4 = readUTF8(i19, cArr);
            } else if ("InnerClasses".equals(readUTF8)) {
                i18 = i19;
                i3 = i18;
            } else if ("EnclosingMethod".equals(readUTF8)) {
                i12 = i19;
                i3 = i12;
            } else if ("NestHost".equals(readUTF8)) {
                i3 = i19;
                str7 = readClass(i19, cArr);
            } else if ("NestMembers".equals(readUTF8)) {
                i17 = i19;
                i3 = i17;
            } else {
                if ("Signature".equals(readUTF8)) {
                    str5 = readUTF8(i19, cArr);
                } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                    i13 = i19;
                    i3 = i13;
                } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                    i15 = i19;
                    i3 = i15;
                } else if ("Deprecated".equals(readUTF8)) {
                    i9 |= 131072;
                } else if ("Synthetic".equals(readUTF8)) {
                    i9 |= 4096;
                } else if ("SourceDebugExtension".equals(readUTF8)) {
                    str3 = readUtf(i19, readInt, new char[readInt]);
                    i3 = i19;
                    i2 = i7;
                    i4 = readInt;
                    strArr = strArr2;
                    firstAttributeOffset = i3 + i4;
                    readUnsignedShort3--;
                    Attribute[] attributeArr2 = attributeArr;
                    strArr2 = strArr;
                    i7 = i2;
                } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                    i14 = i19;
                    i3 = i14;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                    i16 = i19;
                    i3 = i16;
                } else if ("Module".equals(readUTF8)) {
                    i10 = i19;
                    i3 = i10;
                } else if ("ModuleMainClass".equals(readUTF8)) {
                    str6 = readClass(i19, cArr);
                } else if ("ModulePackages".equals(readUTF8)) {
                    i11 = i19;
                    i3 = i11;
                } else {
                    if (!"BootstrapMethods".equals(readUTF8)) {
                        i3 = i19;
                        i2 = i7;
                        i4 = readInt;
                        strArr = strArr2;
                        Attribute readAttribute = readAttribute(attributeArr, readUTF8, i3, readInt, cArr, -1, (Label[]) null);
                        readAttribute.nextAttribute = attribute;
                        attribute = readAttribute;
                        i9 = i9;
                        str3 = str8;
                        str4 = str4;
                    } else {
                        i3 = i19;
                        String str9 = str4;
                        i2 = i7;
                        i4 = readInt;
                        strArr = strArr2;
                        Attribute attribute2 = attribute;
                        String str10 = str8;
                        int i20 = i9;
                        str3 = str10;
                    }
                    firstAttributeOffset = i3 + i4;
                    readUnsignedShort3--;
                    Attribute[] attributeArr22 = attributeArr;
                    strArr2 = strArr;
                    i7 = i2;
                }
                i3 = i19;
            }
            i2 = i7;
            i4 = readInt;
            strArr = strArr2;
            str3 = str8;
            firstAttributeOffset = i3 + i4;
            readUnsignedShort3--;
            Attribute[] attributeArr222 = attributeArr;
            strArr2 = strArr;
            i7 = i2;
        }
        String str11 = str3;
        String str12 = str4;
        int i21 = i7;
        String[] strArr3 = strArr2;
        Attribute attribute3 = attribute;
        classVisitor.visit(readInt(this.cpInfoOffsets[1] - 7), i9, readClass, str5, readClass2, strArr3);
        if ((i5 & 2) == 0) {
            String str13 = str12;
            String str14 = str11;
            if (!(str13 == null && str14 == null)) {
                classVisitor2.visitSource(str13, str14);
            }
        }
        if (i10 != 0) {
            readModuleAttributes(classVisitor, context, i10, i11, str6);
        }
        String str15 = str7;
        if (str15 != null) {
            classVisitor2.visitNestHost(str15);
        }
        int i22 = i12;
        if (i22 != 0) {
            String readClass3 = readClass(i22, cArr);
            int readUnsignedShort4 = readUnsignedShort(i22 + 2);
            if (readUnsignedShort4 == 0) {
                str = null;
            } else {
                str = readUTF8(this.cpInfoOffsets[readUnsignedShort4], cArr);
            }
            if (readUnsignedShort4 == 0) {
                str2 = null;
            } else {
                str2 = readUTF8(this.cpInfoOffsets[readUnsignedShort4] + 2, cArr);
            }
            classVisitor2.visitOuterClass(readClass3, str, str2);
        }
        int i23 = i13;
        if (i23 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i23);
            int i24 = i23 + 2;
            while (true) {
                int i25 = readUnsignedShort5 - 1;
                if (readUnsignedShort5 <= 0) {
                    break;
                }
                i24 = readElementValues(classVisitor2.visitAnnotation(readUTF8(i24, cArr), true), i24 + 2, true, cArr);
                readUnsignedShort5 = i25;
            }
        }
        int i26 = i14;
        if (i26 != 0) {
            int readUnsignedShort6 = readUnsignedShort(i26);
            int i27 = i26 + 2;
            while (true) {
                int i28 = readUnsignedShort6 - 1;
                if (readUnsignedShort6 <= 0) {
                    break;
                }
                i27 = readElementValues(classVisitor2.visitAnnotation(readUTF8(i27, cArr), false), i27 + 2, true, cArr);
                readUnsignedShort6 = i28;
            }
        }
        int i29 = i15;
        if (i29 != 0) {
            int readUnsignedShort7 = readUnsignedShort(i29);
            int i30 = i29 + 2;
            while (true) {
                int i31 = readUnsignedShort7 - 1;
                if (readUnsignedShort7 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context, i30);
                i30 = readElementValues(classVisitor2.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
                readUnsignedShort7 = i31;
            }
        }
        int i32 = i16;
        if (i32 != 0) {
            int readUnsignedShort8 = readUnsignedShort(i32);
            int i33 = i32 + 2;
            while (true) {
                int i34 = readUnsignedShort8 - 1;
                if (readUnsignedShort8 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, i33);
                i33 = readElementValues(classVisitor2.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
                readUnsignedShort8 = i34;
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.nextAttribute;
            attribute3.nextAttribute = null;
            classVisitor2.visitAttribute(attribute3);
            attribute3 = attribute4;
        }
        int i35 = i17;
        if (i35 != 0) {
            int readUnsignedShort9 = readUnsignedShort(i35);
            int i36 = i35 + 2;
            while (true) {
                int i37 = readUnsignedShort9 - 1;
                if (readUnsignedShort9 <= 0) {
                    break;
                }
                classVisitor2.visitNestMember(readClass(i36, cArr));
                i36 += 2;
                readUnsignedShort9 = i37;
            }
        }
        int i38 = i18;
        if (i38 != 0) {
            int readUnsignedShort10 = readUnsignedShort(i38);
            int i39 = i38 + 2;
            while (true) {
                int i40 = readUnsignedShort10 - 1;
                if (readUnsignedShort10 <= 0) {
                    break;
                }
                classVisitor2.visitInnerClass(readClass(i39, cArr), readClass(i39 + 2, cArr), readUTF8(i39 + 4, cArr), readUnsignedShort(i39 + 6));
                i39 += 8;
                readUnsignedShort10 = i40;
            }
        }
        int i41 = i21;
        int readUnsignedShort11 = readUnsignedShort(i41);
        int i42 = i41 + 2;
        while (true) {
            int i43 = readUnsignedShort11 - 1;
            if (readUnsignedShort11 <= 0) {
                break;
            }
            i42 = readField(classVisitor2, context, i42);
            readUnsignedShort11 = i43;
        }
        int readUnsignedShort12 = readUnsignedShort(i42);
        int i44 = i42 + 2;
        while (true) {
            int i45 = readUnsignedShort12 - 1;
            if (readUnsignedShort12 > 0) {
                i44 = readMethod(classVisitor2, context, i44);
                readUnsignedShort12 = i45;
            } else {
                classVisitor.visitEnd();
                return;
            }
        }
    }

    private void readModuleAttributes(ClassVisitor classVisitor, Context context, int i, int i2, String str) {
        String[] strArr;
        char[] cArr = context.charBuffer;
        int i3 = i + 6;
        ModuleVisitor visitModule = classVisitor.visitModule(readModule(i, cArr), readUnsignedShort(i + 2), readUTF8(i + 4, cArr));
        if (visitModule != null) {
            if (str != null) {
                visitModule.visitMainClass(str);
            }
            if (i2 != 0) {
                int readUnsignedShort = readUnsignedShort(i2);
                int i4 = i2 + 2;
                while (true) {
                    int i5 = readUnsignedShort - 1;
                    if (readUnsignedShort <= 0) {
                        break;
                    }
                    visitModule.visitPackage(readPackage(i4, cArr));
                    i4 += 2;
                    readUnsignedShort = i5;
                }
            }
            int readUnsignedShort2 = readUnsignedShort(i3);
            int i6 = i3 + 2;
            while (true) {
                int i7 = readUnsignedShort2 - 1;
                if (readUnsignedShort2 <= 0) {
                    break;
                }
                i6 += 6;
                visitModule.visitRequire(readModule(i6, cArr), readUnsignedShort(i6 + 2), readUTF8(i6 + 4, cArr));
                readUnsignedShort2 = i7;
            }
            int readUnsignedShort3 = readUnsignedShort(i6);
            int i8 = i6 + 2;
            while (true) {
                int i9 = readUnsignedShort3 - 1;
                String[] strArr2 = null;
                if (readUnsignedShort3 <= 0) {
                    break;
                }
                String readPackage = readPackage(i8, cArr);
                int readUnsignedShort4 = readUnsignedShort(i8 + 2);
                int readUnsignedShort5 = readUnsignedShort(i8 + 4);
                i8 += 6;
                if (readUnsignedShort5 != 0) {
                    strArr2 = new String[readUnsignedShort5];
                    for (int i10 = 0; i10 < readUnsignedShort5; i10++) {
                        strArr2[i10] = readModule(i8, cArr);
                        i8 += 2;
                    }
                }
                visitModule.visitExport(readPackage, readUnsignedShort4, strArr2);
                readUnsignedShort3 = i9;
            }
            int readUnsignedShort6 = readUnsignedShort(i8);
            int i11 = i8 + 2;
            while (true) {
                int i12 = readUnsignedShort6 - 1;
                if (readUnsignedShort6 <= 0) {
                    break;
                }
                String readPackage2 = readPackage(i11, cArr);
                int readUnsignedShort7 = readUnsignedShort(i11 + 2);
                int readUnsignedShort8 = readUnsignedShort(i11 + 4);
                i11 += 6;
                if (readUnsignedShort8 != 0) {
                    strArr = new String[readUnsignedShort8];
                    for (int i13 = 0; i13 < readUnsignedShort8; i13++) {
                        strArr[i13] = readModule(i11, cArr);
                        i11 += 2;
                    }
                } else {
                    strArr = null;
                }
                visitModule.visitOpen(readPackage2, readUnsignedShort7, strArr);
                readUnsignedShort6 = i12;
            }
            int readUnsignedShort9 = readUnsignedShort(i11);
            int i14 = i11 + 2;
            while (true) {
                int i15 = readUnsignedShort9 - 1;
                if (readUnsignedShort9 <= 0) {
                    break;
                }
                visitModule.visitUse(readClass(i14, cArr));
                i14 += 2;
                readUnsignedShort9 = i15;
            }
            int readUnsignedShort10 = readUnsignedShort(i14);
            int i16 = i14 + 2;
            while (true) {
                int i17 = readUnsignedShort10 - 1;
                if (readUnsignedShort10 > 0) {
                    String readClass = readClass(i16, cArr);
                    int readUnsignedShort11 = readUnsignedShort(i16 + 2);
                    i16 += 4;
                    String[] strArr3 = new String[readUnsignedShort11];
                    for (int i18 = 0; i18 < readUnsignedShort11; i18++) {
                        strArr3[i18] = readClass(i16, cArr);
                        i16 += 2;
                    }
                    visitModule.visitProvide(readClass, strArr3);
                    readUnsignedShort10 = i17;
                } else {
                    visitModule.visitEnd();
                    return;
                }
            }
        }
    }

    private int readField(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        int i3;
        Context context2 = context;
        int i4 = i;
        char[] cArr = context2.charBuffer;
        int readUnsignedShort = readUnsignedShort(i4);
        String readUTF8 = readUTF8(i4 + 2, cArr);
        String readUTF82 = readUTF8(i4 + 4, cArr);
        int i5 = i4 + 6;
        int readUnsignedShort2 = readUnsignedShort(i5);
        int i6 = i5 + 2;
        int i7 = readUnsignedShort;
        Attribute attribute = null;
        String str = null;
        Object obj = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = readUnsignedShort2 - 1;
            if (readUnsignedShort2 <= 0) {
                break;
            }
            String readUTF83 = readUTF8(i6, cArr);
            int readInt = readInt(i6 + 2);
            int i13 = i6 + 6;
            if ("ConstantValue".equals(readUTF83)) {
                int readUnsignedShort3 = readUnsignedShort(i13);
                if (readUnsignedShort3 == 0) {
                    obj = null;
                } else {
                    obj = readConst(readUnsignedShort3, cArr);
                }
            } else if ("Signature".equals(readUTF83)) {
                str = readUTF8(i13, cArr);
            } else {
                if ("Deprecated".equals(readUTF83)) {
                    i3 = 131072 | i7;
                } else if ("Synthetic".equals(readUTF83)) {
                    i3 = i7 | 4096;
                } else {
                    if ("RuntimeVisibleAnnotations".equals(readUTF83)) {
                        i11 = i13;
                        i2 = i11;
                    } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF83)) {
                        i9 = i13;
                        i2 = i9;
                    } else if ("RuntimeInvisibleAnnotations".equals(readUTF83)) {
                        i10 = i13;
                        i2 = i10;
                    } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF83)) {
                        i8 = i13;
                        i2 = i8;
                    } else {
                        i2 = i13;
                        Attribute attribute2 = attribute;
                        attribute = readAttribute(context2.attributePrototypes, readUTF83, i2, readInt, cArr, -1, (Label[]) null);
                        attribute.nextAttribute = attribute2;
                        i10 = i10;
                        i11 = i11;
                        i8 = i8;
                        i9 = i9;
                    }
                    i6 = i2 + readInt;
                    context2 = context;
                    readUnsignedShort2 = i12;
                }
                i7 = i3;
            }
            i2 = i13;
            i6 = i2 + readInt;
            context2 = context;
            readUnsignedShort2 = i12;
        }
        Attribute attribute3 = attribute;
        int i14 = i8;
        int i15 = i9;
        int i16 = i10;
        int i17 = i11;
        FieldVisitor visitField = classVisitor.visitField(i7, readUTF8, readUTF82, str, obj);
        if (visitField == null) {
            return i6;
        }
        if (i17 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i17);
            int i18 = i17 + 2;
            while (true) {
                int i19 = readUnsignedShort4 - 1;
                if (readUnsignedShort4 <= 0) {
                    break;
                }
                i18 = readElementValues(visitField.visitAnnotation(readUTF8(i18, cArr), true), i18 + 2, true, cArr);
                readUnsignedShort4 = i19;
            }
        }
        if (i16 != 0) {
            int i20 = i16;
            int readUnsignedShort5 = readUnsignedShort(i20);
            int i21 = i20 + 2;
            while (true) {
                int i22 = readUnsignedShort5 - 1;
                if (readUnsignedShort5 <= 0) {
                    break;
                }
                i21 = readElementValues(visitField.visitAnnotation(readUTF8(i21, cArr), false), i21 + 2, true, cArr);
                readUnsignedShort5 = i22;
            }
        }
        int i23 = i15;
        if (i23 != 0) {
            int readUnsignedShort6 = readUnsignedShort(i23);
            int i24 = i23 + 2;
            while (true) {
                int i25 = readUnsignedShort6 - 1;
                if (readUnsignedShort6 <= 0) {
                    break;
                }
                Context context3 = context;
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context3, i24);
                i24 = readElementValues(visitField.visitTypeAnnotation(context3.currentTypeAnnotationTarget, context3.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
                readUnsignedShort6 = i25;
            }
        }
        Context context4 = context;
        int i26 = i14;
        if (i26 != 0) {
            int readUnsignedShort7 = readUnsignedShort(i26);
            int i27 = i26 + 2;
            while (true) {
                int i28 = readUnsignedShort7 - 1;
                if (readUnsignedShort7 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context4, i27);
                i27 = readElementValues(visitField.visitTypeAnnotation(context4.currentTypeAnnotationTarget, context4.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
                readUnsignedShort7 = i28;
            }
        }
        while (true) {
            Attribute attribute4 = attribute3;
            if (attribute4 != null) {
                attribute3 = attribute4.nextAttribute;
                attribute4.nextAttribute = null;
                visitField.visitAttribute(attribute4);
            } else {
                visitField.visitEnd();
                return i6;
            }
        }
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int i) {
        String str;
        boolean z;
        int i2;
        int i3;
        int i4;
        int i5;
        Context context2 = context;
        int i6 = i;
        char[] cArr = context2.charBuffer;
        context2.currentMethodAccessFlags = readUnsignedShort(i6);
        context2.currentMethodName = readUTF8(i6 + 2, cArr);
        int i7 = i6 + 4;
        context2.currentMethodDescriptor = readUTF8(i7, cArr);
        int i8 = i6 + 6;
        int readUnsignedShort = readUnsignedShort(i8);
        int i9 = i8 + 2;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        Attribute attribute = null;
        boolean z2 = false;
        int i17 = 0;
        String[] strArr = null;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (true) {
            int i21 = readUnsignedShort - 1;
            if (readUnsignedShort <= 0) {
                break;
            }
            String readUTF8 = readUTF8(i9, cArr);
            int readInt = readInt(i9 + 2);
            int i22 = i9 + 6;
            int i23 = i10;
            if (!"Code".equals(readUTF8)) {
                if ("Exceptions".equals(readUTF8)) {
                    int readUnsignedShort2 = readUnsignedShort(i22);
                    String[] strArr2 = new String[readUnsignedShort2];
                    i5 = i11;
                    i4 = i12;
                    int i24 = i22 + 2;
                    for (int i25 = 0; i25 < readUnsignedShort2; i25++) {
                        strArr2[i25] = readClass(i24, cArr);
                        i24 += 2;
                    }
                    strArr = strArr2;
                    i3 = i7;
                    i17 = i22;
                } else {
                    i5 = i11;
                    i4 = i12;
                    if ("Signature".equals(readUTF8)) {
                        i16 = readUnsignedShort(i22);
                    } else if ("Deprecated".equals(readUTF8)) {
                        context2.currentMethodAccessFlags |= 131072;
                    } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                        i3 = i7;
                        i13 = i22;
                    } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                        i3 = i7;
                        i11 = i22;
                        i10 = i23;
                        i12 = i4;
                        i9 = i22 + readInt;
                        readUnsignedShort = i21;
                        i7 = i3;
                    } else if ("AnnotationDefault".equals(readUTF8)) {
                        i3 = i7;
                        i15 = i22;
                    } else {
                        if ("Synthetic".equals(readUTF8)) {
                            context2.currentMethodAccessFlags |= 4096;
                            i3 = i7;
                            i10 = i23;
                            i11 = i5;
                            i12 = i4;
                            z2 = true;
                        } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                            i3 = i7;
                            i12 = i22;
                            i10 = i23;
                            i11 = i5;
                        } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                            i3 = i7;
                            i10 = i22;
                            i11 = i5;
                            i12 = i4;
                        } else if ("RuntimeVisibleParameterAnnotations".equals(readUTF8)) {
                            i3 = i7;
                            i18 = i22;
                        } else if ("RuntimeInvisibleParameterAnnotations".equals(readUTF8)) {
                            i3 = i7;
                            i19 = i22;
                        } else if ("MethodParameters".equals(readUTF8)) {
                            i3 = i7;
                            i14 = i22;
                        } else {
                            int i26 = i23;
                            i3 = i7;
                            Attribute readAttribute = readAttribute(context2.attributePrototypes, readUTF8, i22, readInt, cArr, -1, (Label[]) null);
                            readAttribute.nextAttribute = attribute;
                            attribute = readAttribute;
                            i16 = i16;
                            i11 = i5;
                            i12 = i4;
                            i10 = i26;
                            i13 = i13;
                            i14 = i14;
                            i15 = i15;
                        }
                        i9 = i22 + readInt;
                        readUnsignedShort = i21;
                        i7 = i3;
                    }
                }
                i10 = i23;
                i11 = i5;
                i12 = i4;
                i9 = i22 + readInt;
                readUnsignedShort = i21;
                i7 = i3;
            } else if ((context2.parsingOptions & 1) == 0) {
                i3 = i7;
                i20 = i22;
                i10 = i23;
                i9 = i22 + readInt;
                readUnsignedShort = i21;
                i7 = i3;
            } else {
                i5 = i11;
                i4 = i12;
            }
            i3 = i7;
            i10 = i23;
            i11 = i5;
            i12 = i4;
            i9 = i22 + readInt;
            readUnsignedShort = i21;
            i7 = i3;
        }
        int i27 = i10;
        int i28 = i11;
        int i29 = i12;
        int i30 = i13;
        int i31 = i14;
        int i32 = i15;
        int i33 = i7;
        int i34 = i16;
        int i35 = context2.currentMethodAccessFlags;
        String str2 = context2.currentMethodName;
        String str3 = context2.currentMethodDescriptor;
        if (i34 == 0) {
            str = null;
        } else {
            str = readUtf(i34, cArr);
        }
        MethodVisitor visitMethod = classVisitor.visitMethod(i35, str2, str3, str, strArr);
        if (visitMethod == null) {
            return i9;
        }
        if (visitMethod instanceof MethodWriter) {
            MethodWriter methodWriter = (MethodWriter) visitMethod;
            int i36 = i9 - i6;
            if ((context2.currentMethodAccessFlags & 131072) != 0) {
                i2 = i33;
                z = true;
            } else {
                i2 = i33;
                z = false;
            }
            if (methodWriter.canCopyMethodAttributes(this, i, i36, z2, z, readUnsignedShort(i2), i34, i17)) {
                return i9;
            }
        }
        int i37 = i31;
        if (i37 != 0) {
            int readByte = readByte(i37);
            int i38 = i37 + 1;
            while (true) {
                int i39 = readByte - 1;
                if (readByte <= 0) {
                    break;
                }
                visitMethod.visitParameter(readUTF8(i38, cArr), readUnsignedShort(i38 + 2));
                i38 += 4;
                readByte = i39;
            }
        }
        int i40 = i32;
        if (i40 != 0) {
            AnnotationVisitor visitAnnotationDefault = visitMethod.visitAnnotationDefault();
            readElementValue(visitAnnotationDefault, i40, (String) null, cArr);
            if (visitAnnotationDefault != null) {
                visitAnnotationDefault.visitEnd();
            }
        }
        int i41 = i30;
        if (i41 != 0) {
            int readUnsignedShort3 = readUnsignedShort(i41);
            int i42 = i41 + 2;
            while (true) {
                int i43 = readUnsignedShort3 - 1;
                if (readUnsignedShort3 <= 0) {
                    break;
                }
                i42 = readElementValues(visitMethod.visitAnnotation(readUTF8(i42, cArr), true), i42 + 2, true, cArr);
                readUnsignedShort3 = i43;
            }
        }
        int i44 = i29;
        if (i44 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i44);
            int i45 = i44 + 2;
            while (true) {
                int i46 = readUnsignedShort4 - 1;
                if (readUnsignedShort4 <= 0) {
                    break;
                }
                i45 = readElementValues(visitMethod.visitAnnotation(readUTF8(i45, cArr), false), i45 + 2, true, cArr);
                readUnsignedShort4 = i46;
            }
        }
        int i47 = i28;
        if (i47 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i47);
            int i48 = i47 + 2;
            while (true) {
                int i49 = readUnsignedShort5 - 1;
                if (readUnsignedShort5 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context2, i48);
                i48 = readElementValues(visitMethod.visitTypeAnnotation(context2.currentTypeAnnotationTarget, context2.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
                readUnsignedShort5 = i49;
            }
        }
        int i50 = i27;
        if (i50 != 0) {
            int readUnsignedShort6 = readUnsignedShort(i50);
            int i51 = i50 + 2;
            while (true) {
                int i52 = readUnsignedShort6 - 1;
                if (readUnsignedShort6 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context2, i51);
                i51 = readElementValues(visitMethod.visitTypeAnnotation(context2.currentTypeAnnotationTarget, context2.currentTypeAnnotationTargetPath, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
                readUnsignedShort6 = i52;
            }
        }
        int i53 = i18;
        if (i53 != 0) {
            readParameterAnnotations(visitMethod, context2, i53, true);
        }
        int i54 = i19;
        if (i54 != 0) {
            readParameterAnnotations(visitMethod, context2, i54, false);
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.nextAttribute;
            attribute.nextAttribute = null;
            visitMethod.visitAttribute(attribute);
            attribute = attribute2;
        }
        int i55 = i20;
        if (i55 != 0) {
            visitMethod.visitCode();
            readCode(visitMethod, context2, i55);
        }
        visitMethod.visitEnd();
        return i9;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x04c5, code lost:
        r13 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x04c7, code lost:
        r34 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0526, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0527, code lost:
        r42 = r6;
        r34 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x052b, code lost:
        r36 = r13;
        r0 = r19;
        r1 = r22;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0072, code lost:
        r0 = r0 + 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x075c, code lost:
        r3 = r15 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0775, code lost:
        r3 = r15 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x078a, code lost:
        r3 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x078c, code lost:
        r0 = r19;
        r1 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0790, code lost:
        r4 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0792, code lost:
        r9 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0794, code lost:
        if (r35 == null) goto L_0x07be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0797, code lost:
        if (r1 >= r9.length) goto L_0x07be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0799, code lost:
        if (r0 > r11) goto L_0x07be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x079b, code lost:
        if (r0 != r11) goto L_0x07b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x079d, code lost:
        r0 = readTypeAnnotationTarget(r10, r9[r1]);
        readElementValues(r14.visitInsnAnnotation(r10.currentTypeAnnotationTarget, r10.currentTypeAnnotationTargetPath, readUTF8(r0, r13), true), r0 + 2, true, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x07b5, code lost:
        r1 = r1 + 1;
        r0 = getTypeAnnotationBytecodeOffset(r9, r1);
        r35 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x07be, code lost:
        r2 = r20;
        r5 = r27;
        r15 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x07c4, code lost:
        if (r15 == null) goto L_0x0801;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x07c7, code lost:
        if (r5 >= r15.length) goto L_0x0801;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x07c9, code lost:
        if (r2 > r11) goto L_0x0801;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x07cb, code lost:
        if (r2 != r11) goto L_0x07ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x07cd, code lost:
        r2 = readTypeAnnotationTarget(r10, r15[r5]);
        r19 = r0;
        r20 = r1;
        r22 = r11;
        readElementValues(r14.visitInsnAnnotation(r10.currentTypeAnnotationTarget, r10.currentTypeAnnotationTargetPath, readUTF8(r2, r13), false), r2 + 2, true, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x07ed, code lost:
        r19 = r0;
        r20 = r1;
        r22 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x07f4, code lost:
        r5 = r5 + 1;
        r2 = getTypeAnnotationBytecodeOffset(r15, r5);
        r0 = r19;
        r1 = r20;
        r11 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d3, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readCode(net.bytebuddy.jar.asm.MethodVisitor r40, net.bytebuddy.jar.asm.Context r41, int r42) {
        /*
            r39 = this;
            r8 = r39
            r9 = r40
            r10 = r41
            r11 = r42
            byte[] r12 = r8.b
            char[] r13 = r10.charBuffer
            int r14 = r8.readUnsignedShort(r11)
            int r0 = r11 + 2
            int r15 = r8.readUnsignedShort(r0)
            int r0 = r11 + 4
            int r7 = r8.readInt(r0)
            int r16 = r11 + 8
            int r6 = r16 + r7
            int r0 = r7 + 1
            net.bytebuddy.jar.asm.Label[] r5 = new net.bytebuddy.jar.asm.Label[r0]
            r10.currentMethodLabels = r5
            r0 = r16
        L_0x0028:
            r4 = 132(0x84, float:1.85E-43)
            r3 = 8
            if (r0 >= r6) goto L_0x00df
            int r1 = r0 - r16
            byte r2 = r12[r0]
            r2 = r2 & 255(0xff, float:3.57E-43)
            switch(r2) {
                case 0: goto L_0x00db;
                case 1: goto L_0x00db;
                case 2: goto L_0x00db;
                case 3: goto L_0x00db;
                case 4: goto L_0x00db;
                case 5: goto L_0x00db;
                case 6: goto L_0x00db;
                case 7: goto L_0x00db;
                case 8: goto L_0x00db;
                case 9: goto L_0x00db;
                case 10: goto L_0x00db;
                case 11: goto L_0x00db;
                case 12: goto L_0x00db;
                case 13: goto L_0x00db;
                case 14: goto L_0x00db;
                case 15: goto L_0x00db;
                case 16: goto L_0x00d7;
                case 17: goto L_0x00d3;
                case 18: goto L_0x00d7;
                case 19: goto L_0x00d3;
                case 20: goto L_0x00d3;
                case 21: goto L_0x00d7;
                case 22: goto L_0x00d7;
                case 23: goto L_0x00d7;
                case 24: goto L_0x00d7;
                case 25: goto L_0x00d7;
                case 26: goto L_0x00db;
                case 27: goto L_0x00db;
                case 28: goto L_0x00db;
                case 29: goto L_0x00db;
                case 30: goto L_0x00db;
                case 31: goto L_0x00db;
                case 32: goto L_0x00db;
                case 33: goto L_0x00db;
                case 34: goto L_0x00db;
                case 35: goto L_0x00db;
                case 36: goto L_0x00db;
                case 37: goto L_0x00db;
                case 38: goto L_0x00db;
                case 39: goto L_0x00db;
                case 40: goto L_0x00db;
                case 41: goto L_0x00db;
                case 42: goto L_0x00db;
                case 43: goto L_0x00db;
                case 44: goto L_0x00db;
                case 45: goto L_0x00db;
                case 46: goto L_0x00db;
                case 47: goto L_0x00db;
                case 48: goto L_0x00db;
                case 49: goto L_0x00db;
                case 50: goto L_0x00db;
                case 51: goto L_0x00db;
                case 52: goto L_0x00db;
                case 53: goto L_0x00db;
                case 54: goto L_0x00d7;
                case 55: goto L_0x00d7;
                case 56: goto L_0x00d7;
                case 57: goto L_0x00d7;
                case 58: goto L_0x00d7;
                case 59: goto L_0x00db;
                case 60: goto L_0x00db;
                case 61: goto L_0x00db;
                case 62: goto L_0x00db;
                case 63: goto L_0x00db;
                case 64: goto L_0x00db;
                case 65: goto L_0x00db;
                case 66: goto L_0x00db;
                case 67: goto L_0x00db;
                case 68: goto L_0x00db;
                case 69: goto L_0x00db;
                case 70: goto L_0x00db;
                case 71: goto L_0x00db;
                case 72: goto L_0x00db;
                case 73: goto L_0x00db;
                case 74: goto L_0x00db;
                case 75: goto L_0x00db;
                case 76: goto L_0x00db;
                case 77: goto L_0x00db;
                case 78: goto L_0x00db;
                case 79: goto L_0x00db;
                case 80: goto L_0x00db;
                case 81: goto L_0x00db;
                case 82: goto L_0x00db;
                case 83: goto L_0x00db;
                case 84: goto L_0x00db;
                case 85: goto L_0x00db;
                case 86: goto L_0x00db;
                case 87: goto L_0x00db;
                case 88: goto L_0x00db;
                case 89: goto L_0x00db;
                case 90: goto L_0x00db;
                case 91: goto L_0x00db;
                case 92: goto L_0x00db;
                case 93: goto L_0x00db;
                case 94: goto L_0x00db;
                case 95: goto L_0x00db;
                case 96: goto L_0x00db;
                case 97: goto L_0x00db;
                case 98: goto L_0x00db;
                case 99: goto L_0x00db;
                case 100: goto L_0x00db;
                case 101: goto L_0x00db;
                case 102: goto L_0x00db;
                case 103: goto L_0x00db;
                case 104: goto L_0x00db;
                case 105: goto L_0x00db;
                case 106: goto L_0x00db;
                case 107: goto L_0x00db;
                case 108: goto L_0x00db;
                case 109: goto L_0x00db;
                case 110: goto L_0x00db;
                case 111: goto L_0x00db;
                case 112: goto L_0x00db;
                case 113: goto L_0x00db;
                case 114: goto L_0x00db;
                case 115: goto L_0x00db;
                case 116: goto L_0x00db;
                case 117: goto L_0x00db;
                case 118: goto L_0x00db;
                case 119: goto L_0x00db;
                case 120: goto L_0x00db;
                case 121: goto L_0x00db;
                case 122: goto L_0x00db;
                case 123: goto L_0x00db;
                case 124: goto L_0x00db;
                case 125: goto L_0x00db;
                case 126: goto L_0x00db;
                case 127: goto L_0x00db;
                case 128: goto L_0x00db;
                case 129: goto L_0x00db;
                case 130: goto L_0x00db;
                case 131: goto L_0x00db;
                case 132: goto L_0x00d3;
                case 133: goto L_0x00db;
                case 134: goto L_0x00db;
                case 135: goto L_0x00db;
                case 136: goto L_0x00db;
                case 137: goto L_0x00db;
                case 138: goto L_0x00db;
                case 139: goto L_0x00db;
                case 140: goto L_0x00db;
                case 141: goto L_0x00db;
                case 142: goto L_0x00db;
                case 143: goto L_0x00db;
                case 144: goto L_0x00db;
                case 145: goto L_0x00db;
                case 146: goto L_0x00db;
                case 147: goto L_0x00db;
                case 148: goto L_0x00db;
                case 149: goto L_0x00db;
                case 150: goto L_0x00db;
                case 151: goto L_0x00db;
                case 152: goto L_0x00db;
                case 153: goto L_0x00c9;
                case 154: goto L_0x00c9;
                case 155: goto L_0x00c9;
                case 156: goto L_0x00c9;
                case 157: goto L_0x00c9;
                case 158: goto L_0x00c9;
                case 159: goto L_0x00c9;
                case 160: goto L_0x00c9;
                case 161: goto L_0x00c9;
                case 162: goto L_0x00c9;
                case 163: goto L_0x00c9;
                case 164: goto L_0x00c9;
                case 165: goto L_0x00c9;
                case 166: goto L_0x00c9;
                case 167: goto L_0x00c9;
                case 168: goto L_0x00c9;
                case 169: goto L_0x00d7;
                case 170: goto L_0x009b;
                case 171: goto L_0x0075;
                case 172: goto L_0x00db;
                case 173: goto L_0x00db;
                case 174: goto L_0x00db;
                case 175: goto L_0x00db;
                case 176: goto L_0x00db;
                case 177: goto L_0x00db;
                case 178: goto L_0x00d3;
                case 179: goto L_0x00d3;
                case 180: goto L_0x00d3;
                case 181: goto L_0x00d3;
                case 182: goto L_0x00d3;
                case 183: goto L_0x00d3;
                case 184: goto L_0x00d3;
                case 185: goto L_0x0072;
                case 186: goto L_0x0072;
                case 187: goto L_0x00d3;
                case 188: goto L_0x00d7;
                case 189: goto L_0x00d3;
                case 190: goto L_0x00db;
                case 191: goto L_0x00db;
                case 192: goto L_0x00d3;
                case 193: goto L_0x00d3;
                case 194: goto L_0x00db;
                case 195: goto L_0x00db;
                case 196: goto L_0x0057;
                case 197: goto L_0x0054;
                case 198: goto L_0x00c9;
                case 199: goto L_0x00c9;
                case 200: goto L_0x0049;
                case 201: goto L_0x0049;
                case 202: goto L_0x003d;
                case 203: goto L_0x003d;
                case 204: goto L_0x003d;
                case 205: goto L_0x003d;
                case 206: goto L_0x003d;
                case 207: goto L_0x003d;
                case 208: goto L_0x003d;
                case 209: goto L_0x003d;
                case 210: goto L_0x003d;
                case 211: goto L_0x003d;
                case 212: goto L_0x003d;
                case 213: goto L_0x003d;
                case 214: goto L_0x003d;
                case 215: goto L_0x003d;
                case 216: goto L_0x003d;
                case 217: goto L_0x003d;
                case 218: goto L_0x003d;
                case 219: goto L_0x003d;
                case 220: goto L_0x0049;
                default: goto L_0x0037;
            }
        L_0x0037:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x003d:
            int r2 = r0 + 1
            int r2 = r8.readUnsignedShort(r2)
            int r1 = r1 + r2
            r8.createLabel(r1, r5)
            goto L_0x00d3
        L_0x0049:
            int r2 = r0 + 1
            int r2 = r8.readInt(r2)
            int r1 = r1 + r2
            r8.createLabel(r1, r5)
            goto L_0x0072
        L_0x0054:
            int r0 = r0 + 4
            goto L_0x0028
        L_0x0057:
            int r1 = r0 + 1
            byte r1 = r12[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            if (r1 == r4) goto L_0x006f
            r2 = 169(0xa9, float:2.37E-43)
            if (r1 == r2) goto L_0x0054
            switch(r1) {
                case 21: goto L_0x0054;
                case 22: goto L_0x0054;
                case 23: goto L_0x0054;
                case 24: goto L_0x0054;
                case 25: goto L_0x0054;
                default: goto L_0x0066;
            }
        L_0x0066:
            switch(r1) {
                case 54: goto L_0x0054;
                case 55: goto L_0x0054;
                case 56: goto L_0x0054;
                case 57: goto L_0x0054;
                case 58: goto L_0x0054;
                default: goto L_0x0069;
            }
        L_0x0069:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x006f:
            int r0 = r0 + 6
            goto L_0x0028
        L_0x0072:
            int r0 = r0 + 5
            goto L_0x0028
        L_0x0075:
            r2 = r1 & 3
            int r2 = 4 - r2
            int r0 = r0 + r2
            int r2 = r8.readInt(r0)
            int r2 = r2 + r1
            r8.createLabel(r2, r5)
            int r2 = r0 + 4
            int r2 = r8.readInt(r2)
            int r0 = r0 + r3
        L_0x0089:
            int r3 = r2 + -1
            if (r2 <= 0) goto L_0x0028
            int r2 = r0 + 4
            int r2 = r8.readInt(r2)
            int r2 = r2 + r1
            r8.createLabel(r2, r5)
            int r0 = r0 + 8
            r2 = r3
            goto L_0x0089
        L_0x009b:
            r2 = r1 & 3
            int r2 = 4 - r2
            int r0 = r0 + r2
            int r2 = r8.readInt(r0)
            int r2 = r2 + r1
            r8.createLabel(r2, r5)
            int r2 = r0 + 8
            int r2 = r8.readInt(r2)
            int r3 = r0 + 4
            int r3 = r8.readInt(r3)
            int r2 = r2 - r3
            r3 = 1
            int r2 = r2 + r3
            int r0 = r0 + 12
        L_0x00b9:
            int r3 = r2 + -1
            if (r2 <= 0) goto L_0x0028
            int r2 = r8.readInt(r0)
            int r2 = r2 + r1
            r8.createLabel(r2, r5)
            int r0 = r0 + 4
            r2 = r3
            goto L_0x00b9
        L_0x00c9:
            int r2 = r0 + 1
            short r2 = r8.readShort(r2)
            int r1 = r1 + r2
            r8.createLabel(r1, r5)
        L_0x00d3:
            int r0 = r0 + 3
            goto L_0x0028
        L_0x00d7:
            int r0 = r0 + 2
            goto L_0x0028
        L_0x00db:
            int r0 = r0 + 1
            goto L_0x0028
        L_0x00df:
            int r1 = r8.readUnsignedShort(r0)
            int r0 = r0 + 2
        L_0x00e5:
            int r2 = r1 + -1
            if (r1 <= 0) goto L_0x0125
            int r1 = r8.readUnsignedShort(r0)
            net.bytebuddy.jar.asm.Label r1 = r8.createLabel(r1, r5)
            int r3 = r0 + 2
            int r3 = r8.readUnsignedShort(r3)
            net.bytebuddy.jar.asm.Label r3 = r8.createLabel(r3, r5)
            int r4 = r0 + 4
            int r4 = r8.readUnsignedShort(r4)
            net.bytebuddy.jar.asm.Label r4 = r8.createLabel(r4, r5)
            r20 = r2
            int[] r2 = r8.cpInfoOffsets
            r21 = r6
            int r6 = r0 + 6
            int r6 = r8.readUnsignedShort(r6)
            r2 = r2[r6]
            java.lang.String r2 = r8.readUTF8(r2, r13)
            int r0 = r0 + 8
            r9.visitTryCatchBlock(r1, r3, r4, r2)
            r1 = r20
            r6 = r21
            r3 = 8
            r4 = 132(0x84, float:1.85E-43)
            goto L_0x00e5
        L_0x0125:
            r21 = r6
            int r1 = r8.readUnsignedShort(r0)
            int r0 = r0 + 2
            r2 = 0
            r3 = 1
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
        L_0x013b:
            int r27 = r1 + -1
            if (r1 <= 0) goto L_0x0278
            java.lang.String r1 = r8.readUTF8(r0, r13)
            int r6 = r0 + 2
            int r29 = r8.readInt(r6)
            int r6 = r0 + 6
            java.lang.String r0 = "LocalVariableTable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0186
            int r0 = r10.parsingOptions
            r0 = r0 & 2
            if (r0 != 0) goto L_0x0180
            int r0 = r8.readUnsignedShort(r6)
            int r1 = r6 + 2
        L_0x015f:
            int r25 = r0 + -1
            if (r0 <= 0) goto L_0x0179
            int r0 = r8.readUnsignedShort(r1)
            r8.createDebugLabel(r0, r5)
            int r4 = r1 + 2
            int r4 = r8.readUnsignedShort(r4)
            int r0 = r0 + r4
            r8.createDebugLabel(r0, r5)
            int r1 = r1 + 10
            r0 = r25
            goto L_0x015f
        L_0x0179:
            r18 = r5
            r19 = r6
            r25 = r19
            goto L_0x0194
        L_0x0180:
            r31 = r2
            r2 = 1
            r4 = 0
            goto L_0x022c
        L_0x0186:
            java.lang.String r0 = "LocalVariableTypeTable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x019a
            r18 = r5
            r19 = r6
            r26 = r19
        L_0x0194:
            r35 = r7
            r34 = r21
            goto L_0x0236
        L_0x019a:
            java.lang.String r0 = "LineNumberTable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01cc
            int r0 = r10.parsingOptions
            r0 = r0 & 2
            if (r0 != 0) goto L_0x0180
            int r0 = r8.readUnsignedShort(r6)
            int r1 = r6 + 2
        L_0x01ae:
            int r4 = r0 + -1
            if (r0 <= 0) goto L_0x0180
            int r0 = r8.readUnsignedShort(r1)
            r31 = r2
            int r2 = r1 + 2
            int r2 = r8.readUnsignedShort(r2)
            int r1 = r1 + 4
            r8.createDebugLabel(r0, r5)
            r0 = r5[r0]
            r0.addLineNumber(r2)
            r0 = r4
            r2 = r31
            goto L_0x01ae
        L_0x01cc:
            r31 = r2
            java.lang.String r0 = "RuntimeVisibleTypeAnnotations"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e1
            r2 = 1
            int[] r0 = r8.readTypeAnnotations(r9, r10, r6, r2)
            r2 = r0
            r18 = r5
            r19 = r6
            goto L_0x0194
        L_0x01e1:
            r2 = 1
            java.lang.String r0 = "RuntimeInvisibleTypeAnnotations"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01f2
            r4 = 0
            int[] r0 = r8.readTypeAnnotations(r9, r10, r6, r4)
            r22 = r0
            goto L_0x022c
        L_0x01f2:
            r4 = 0
            java.lang.String r0 = "StackMapTable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x020a
            int r0 = r10.parsingOptions
            r0 = r0 & 4
            if (r0 != 0) goto L_0x022c
            int r0 = r6 + 2
            int r1 = r6 + r29
            r20 = r0
            r23 = r1
            goto L_0x022c
        L_0x020a:
            java.lang.String r0 = "StackMap"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0239
            int r0 = r10.parsingOptions
            r0 = r0 & 4
            if (r0 != 0) goto L_0x022c
            int r0 = r6 + 2
            int r1 = r6 + r29
            r20 = r0
            r23 = r1
            r18 = r5
            r19 = r6
            r35 = r7
            r34 = r21
            r2 = r31
            r3 = 0
            goto L_0x0236
        L_0x022c:
            r18 = r5
            r19 = r6
            r35 = r7
            r34 = r21
            r2 = r31
        L_0x0236:
            r9 = 8
            goto L_0x0268
        L_0x0239:
            net.bytebuddy.jar.asm.Attribute[] r0 = r10.attributePrototypes
            r17 = r0
            r0 = r39
            r30 = r1
            r1 = r17
            r11 = r31
            r2 = r30
            r32 = r3
            r9 = 8
            r3 = r6
            r4 = r29
            r18 = r5
            r5 = r13
            r19 = r6
            r34 = r21
            r6 = r42
            r35 = r7
            r7 = r18
            net.bytebuddy.jar.asm.Attribute r0 = r0.readAttribute(r1, r2, r3, r4, r5, r6, r7)
            r7 = r24
            r0.nextAttribute = r7
            r24 = r0
            r2 = r11
            r3 = r32
        L_0x0268:
            int r0 = r19 + r29
            r9 = r40
            r11 = r42
            r5 = r18
            r1 = r27
            r21 = r34
            r7 = r35
            goto L_0x013b
        L_0x0278:
            r11 = r2
            r32 = r3
            r18 = r5
            r35 = r7
            r34 = r21
            r7 = r24
            r9 = 8
            int r0 = r10.parsingOptions
            r0 = r0 & r9
            if (r0 == 0) goto L_0x028c
            r6 = 1
            goto L_0x028d
        L_0x028c:
            r6 = 0
        L_0x028d:
            r5 = -1
            if (r20 == 0) goto L_0x02e5
            r10.currentFrameOffset = r5
            r4 = 0
            r10.currentFrameType = r4
            r10.currentFrameLocalCount = r4
            r10.currentFrameLocalCountDelta = r4
            java.lang.Object[] r0 = new java.lang.Object[r15]
            r10.currentFrameLocalTypes = r0
            r10.currentFrameStackCount = r4
            java.lang.Object[] r0 = new java.lang.Object[r14]
            r10.currentFrameStackTypes = r0
            if (r6 == 0) goto L_0x02a8
            r8.computeImplicitFrame(r10)
        L_0x02a8:
            r0 = r20
        L_0x02aa:
            r3 = r23
            int r1 = r3 + -2
            if (r0 >= r1) goto L_0x02e2
            byte r1 = r12[r0]
            if (r1 != r9) goto L_0x02d3
            int r1 = r0 + 1
            int r1 = r8.readUnsignedShort(r1)
            if (r1 < 0) goto L_0x02d3
            r2 = r35
            if (r1 >= r2) goto L_0x02d0
            int r19 = r16 + r1
            byte r4 = r12[r19]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r5 = 187(0xbb, float:2.62E-43)
            if (r4 != r5) goto L_0x02d0
            r5 = r18
            r8.createLabel(r1, r5)
            goto L_0x02d7
        L_0x02d0:
            r5 = r18
            goto L_0x02d7
        L_0x02d3:
            r5 = r18
            r2 = r35
        L_0x02d7:
            int r0 = r0 + 1
            r35 = r2
            r23 = r3
            r18 = r5
            r4 = 0
            r5 = -1
            goto L_0x02aa
        L_0x02e2:
            r5 = r18
            goto L_0x02e9
        L_0x02e5:
            r5 = r18
            r3 = r23
        L_0x02e9:
            r2 = r35
            if (r6 == 0) goto L_0x030d
            int r0 = r10.parsingOptions
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x030d
            r1 = -1
            r4 = 0
            r18 = 0
            r19 = 0
            r0 = r40
            r21 = r2
            r2 = r15
            r9 = r3
            r3 = r4
            r24 = r7
            r7 = 0
            r4 = r18
            r36 = r5
            r5 = r19
            r0.visitFrame(r1, r2, r3, r4, r5)
            goto L_0x0315
        L_0x030d:
            r21 = r2
            r9 = r3
            r36 = r5
            r24 = r7
            r7 = 0
        L_0x0315:
            int r0 = r8.getTypeAnnotationBytecodeOffset(r11, r7)
            r5 = r22
            int r1 = r8.getTypeAnnotationBytecodeOffset(r5, r7)
            int r2 = r10.parsingOptions
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 != 0) goto L_0x032a
            r4 = 33
            r18 = 33
            goto L_0x032c
        L_0x032a:
            r18 = 0
        L_0x032c:
            r19 = r0
            r3 = r16
            r0 = r20
            r4 = 0
            r22 = 0
            r27 = 0
            r20 = r1
        L_0x0339:
            r2 = r34
            if (r3 >= r2) goto L_0x0821
            int r1 = r3 - r16
            r42 = r0
            r7 = r36
            r0 = r7[r1]
            r34 = r2
            if (r0 == 0) goto L_0x035c
            int r2 = r10.parsingOptions
            r2 = r2 & 2
            r23 = r14
            if (r2 != 0) goto L_0x0353
            r2 = 1
            goto L_0x0354
        L_0x0353:
            r2 = 0
        L_0x0354:
            r28 = 8
            r14 = r40
            r0.accept(r14, r2)
            goto L_0x0362
        L_0x035c:
            r23 = r14
            r28 = 8
            r14 = r40
        L_0x0362:
            r0 = r4
            r4 = r42
        L_0x0365:
            if (r4 == 0) goto L_0x0404
            int r2 = r10.currentFrameOffset
            if (r2 == r1) goto L_0x037c
            int r2 = r10.currentFrameOffset
            r29 = r15
            r15 = -1
            if (r2 != r15) goto L_0x0373
            goto L_0x037f
        L_0x0373:
            r15 = r3
            r14 = r4
            r37 = r5
            r35 = r11
            r11 = r1
            goto L_0x040d
        L_0x037c:
            r29 = r15
            r15 = -1
        L_0x037f:
            int r2 = r10.currentFrameOffset
            if (r2 == r15) goto L_0x03cf
            r2 = r32
            if (r2 == 0) goto L_0x03b3
            if (r6 == 0) goto L_0x038a
            goto L_0x03b3
        L_0x038a:
            int r0 = r10.currentFrameType
            int r15 = r10.currentFrameLocalCountDelta
            r30 = r3
            java.lang.Object[] r3 = r10.currentFrameLocalTypes
            r42 = r4
            int r4 = r10.currentFrameStackCount
            r31 = r5
            java.lang.Object[] r5 = r10.currentFrameStackTypes
            r32 = r0
            r0 = r40
            r35 = r11
            r11 = r1
            r1 = r32
            r32 = r34
            r34 = r13
            r13 = r2
            r2 = r15
            r15 = r30
            r14 = r42
            r37 = r31
            r0.visitFrame(r1, r2, r3, r4, r5)
            goto L_0x03cd
        L_0x03b3:
            r15 = r3
            r14 = r4
            r37 = r5
            r35 = r11
            r32 = r34
            r11 = r1
            r34 = r13
            r13 = r2
            r1 = -1
            int r2 = r10.currentFrameLocalCount
            java.lang.Object[] r3 = r10.currentFrameLocalTypes
            int r4 = r10.currentFrameStackCount
            java.lang.Object[] r5 = r10.currentFrameStackTypes
            r0 = r40
            r0.visitFrame(r1, r2, r3, r4, r5)
        L_0x03cd:
            r0 = 0
            goto L_0x03de
        L_0x03cf:
            r15 = r3
            r14 = r4
            r37 = r5
            r35 = r11
            r11 = r1
            r38 = r34
            r34 = r13
            r13 = r32
            r32 = r38
        L_0x03de:
            if (r14 >= r9) goto L_0x03ef
            int r4 = r8.readStackMapFrame(r14, r13, r6, r10)
            r14 = r40
            r1 = r11
            r3 = r15
            r15 = r29
            r11 = r35
            r5 = r37
            goto L_0x03fa
        L_0x03ef:
            r14 = r40
            r1 = r11
            r3 = r15
            r15 = r29
            r11 = r35
            r5 = r37
            r4 = 0
        L_0x03fa:
            r38 = r32
            r32 = r13
            r13 = r34
            r34 = r38
            goto L_0x0365
        L_0x0404:
            r14 = r4
            r37 = r5
            r35 = r11
            r29 = r15
            r11 = r1
            r15 = r3
        L_0x040d:
            r38 = r34
            r34 = r13
            r13 = r32
            r32 = r38
            if (r0 == 0) goto L_0x042b
            int r0 = r10.parsingOptions
            r0 = r0 & 8
            if (r0 == 0) goto L_0x0428
            r1 = 256(0x100, float:3.59E-43)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r0 = r40
            r0.visitFrame(r1, r2, r3, r4, r5)
        L_0x0428:
            r30 = 0
            goto L_0x042d
        L_0x042b:
            r30 = r0
        L_0x042d:
            byte r0 = r12[r15]
            r5 = r0 & 255(0xff, float:3.57E-43)
            r0 = 200(0xc8, float:2.8E-43)
            switch(r5) {
                case 0: goto L_0x0778;
                case 1: goto L_0x0778;
                case 2: goto L_0x0778;
                case 3: goto L_0x0778;
                case 4: goto L_0x0778;
                case 5: goto L_0x0778;
                case 6: goto L_0x0778;
                case 7: goto L_0x0778;
                case 8: goto L_0x0778;
                case 9: goto L_0x0778;
                case 10: goto L_0x0778;
                case 11: goto L_0x0778;
                case 12: goto L_0x0778;
                case 13: goto L_0x0778;
                case 14: goto L_0x0778;
                case 15: goto L_0x0778;
                case 16: goto L_0x075f;
                case 17: goto L_0x0744;
                case 18: goto L_0x0728;
                case 19: goto L_0x070c;
                case 20: goto L_0x070c;
                case 21: goto L_0x06f2;
                case 22: goto L_0x06f2;
                case 23: goto L_0x06f2;
                case 24: goto L_0x06f2;
                case 25: goto L_0x06f2;
                case 26: goto L_0x06d6;
                case 27: goto L_0x06d6;
                case 28: goto L_0x06d6;
                case 29: goto L_0x06d6;
                case 30: goto L_0x06d6;
                case 31: goto L_0x06d6;
                case 32: goto L_0x06d6;
                case 33: goto L_0x06d6;
                case 34: goto L_0x06d6;
                case 35: goto L_0x06d6;
                case 36: goto L_0x06d6;
                case 37: goto L_0x06d6;
                case 38: goto L_0x06d6;
                case 39: goto L_0x06d6;
                case 40: goto L_0x06d6;
                case 41: goto L_0x06d6;
                case 42: goto L_0x06d6;
                case 43: goto L_0x06d6;
                case 44: goto L_0x06d6;
                case 45: goto L_0x06d6;
                case 46: goto L_0x0778;
                case 47: goto L_0x0778;
                case 48: goto L_0x0778;
                case 49: goto L_0x0778;
                case 50: goto L_0x0778;
                case 51: goto L_0x0778;
                case 52: goto L_0x0778;
                case 53: goto L_0x0778;
                case 54: goto L_0x06f2;
                case 55: goto L_0x06f2;
                case 56: goto L_0x06f2;
                case 57: goto L_0x06f2;
                case 58: goto L_0x06f2;
                case 59: goto L_0x06ba;
                case 60: goto L_0x06ba;
                case 61: goto L_0x06ba;
                case 62: goto L_0x06ba;
                case 63: goto L_0x06ba;
                case 64: goto L_0x06ba;
                case 65: goto L_0x06ba;
                case 66: goto L_0x06ba;
                case 67: goto L_0x06ba;
                case 68: goto L_0x06ba;
                case 69: goto L_0x06ba;
                case 70: goto L_0x06ba;
                case 71: goto L_0x06ba;
                case 72: goto L_0x06ba;
                case 73: goto L_0x06ba;
                case 74: goto L_0x06ba;
                case 75: goto L_0x06ba;
                case 76: goto L_0x06ba;
                case 77: goto L_0x06ba;
                case 78: goto L_0x06ba;
                case 79: goto L_0x0778;
                case 80: goto L_0x0778;
                case 81: goto L_0x0778;
                case 82: goto L_0x0778;
                case 83: goto L_0x0778;
                case 84: goto L_0x0778;
                case 85: goto L_0x0778;
                case 86: goto L_0x0778;
                case 87: goto L_0x0778;
                case 88: goto L_0x0778;
                case 89: goto L_0x0778;
                case 90: goto L_0x0778;
                case 91: goto L_0x0778;
                case 92: goto L_0x0778;
                case 93: goto L_0x0778;
                case 94: goto L_0x0778;
                case 95: goto L_0x0778;
                case 96: goto L_0x0778;
                case 97: goto L_0x0778;
                case 98: goto L_0x0778;
                case 99: goto L_0x0778;
                case 100: goto L_0x0778;
                case 101: goto L_0x0778;
                case 102: goto L_0x0778;
                case 103: goto L_0x0778;
                case 104: goto L_0x0778;
                case 105: goto L_0x0778;
                case 106: goto L_0x0778;
                case 107: goto L_0x0778;
                case 108: goto L_0x0778;
                case 109: goto L_0x0778;
                case 110: goto L_0x0778;
                case 111: goto L_0x0778;
                case 112: goto L_0x0778;
                case 113: goto L_0x0778;
                case 114: goto L_0x0778;
                case 115: goto L_0x0778;
                case 116: goto L_0x0778;
                case 117: goto L_0x0778;
                case 118: goto L_0x0778;
                case 119: goto L_0x0778;
                case 120: goto L_0x0778;
                case 121: goto L_0x0778;
                case 122: goto L_0x0778;
                case 123: goto L_0x0778;
                case 124: goto L_0x0778;
                case 125: goto L_0x0778;
                case 126: goto L_0x0778;
                case 127: goto L_0x0778;
                case 128: goto L_0x0778;
                case 129: goto L_0x0778;
                case 130: goto L_0x0778;
                case 131: goto L_0x0778;
                case 132: goto L_0x069d;
                case 133: goto L_0x0778;
                case 134: goto L_0x0778;
                case 135: goto L_0x0778;
                case 136: goto L_0x0778;
                case 137: goto L_0x0778;
                case 138: goto L_0x0778;
                case 139: goto L_0x0778;
                case 140: goto L_0x0778;
                case 141: goto L_0x0778;
                case 142: goto L_0x0778;
                case 143: goto L_0x0778;
                case 144: goto L_0x0778;
                case 145: goto L_0x0778;
                case 146: goto L_0x0778;
                case 147: goto L_0x0778;
                case 148: goto L_0x0778;
                case 149: goto L_0x0778;
                case 150: goto L_0x0778;
                case 151: goto L_0x0778;
                case 152: goto L_0x0778;
                case 153: goto L_0x067f;
                case 154: goto L_0x067f;
                case 155: goto L_0x067f;
                case 156: goto L_0x067f;
                case 157: goto L_0x067f;
                case 158: goto L_0x067f;
                case 159: goto L_0x067f;
                case 160: goto L_0x067f;
                case 161: goto L_0x067f;
                case 162: goto L_0x067f;
                case 163: goto L_0x067f;
                case 164: goto L_0x067f;
                case 165: goto L_0x067f;
                case 166: goto L_0x067f;
                case 167: goto L_0x067f;
                case 168: goto L_0x067f;
                case 169: goto L_0x06f2;
                case 170: goto L_0x0639;
                case 171: goto L_0x05f3;
                case 172: goto L_0x0778;
                case 173: goto L_0x0778;
                case 174: goto L_0x0778;
                case 175: goto L_0x0778;
                case 176: goto L_0x0778;
                case 177: goto L_0x0778;
                case 178: goto L_0x0598;
                case 179: goto L_0x0598;
                case 180: goto L_0x0598;
                case 181: goto L_0x0598;
                case 182: goto L_0x0598;
                case 183: goto L_0x0598;
                case 184: goto L_0x0598;
                case 185: goto L_0x0598;
                case 186: goto L_0x0536;
                case 187: goto L_0x0513;
                case 188: goto L_0x075f;
                case 189: goto L_0x0513;
                case 190: goto L_0x0778;
                case 191: goto L_0x0778;
                case 192: goto L_0x0513;
                case 193: goto L_0x0513;
                case 194: goto L_0x0778;
                case 195: goto L_0x0778;
                case 196: goto L_0x04e5;
                case 197: goto L_0x04cd;
                case 198: goto L_0x067f;
                case 199: goto L_0x067f;
                case 200: goto L_0x04a6;
                case 201: goto L_0x04a6;
                case 202: goto L_0x045b;
                case 203: goto L_0x045b;
                case 204: goto L_0x045b;
                case 205: goto L_0x045b;
                case 206: goto L_0x045b;
                case 207: goto L_0x045b;
                case 208: goto L_0x045b;
                case 209: goto L_0x045b;
                case 210: goto L_0x045b;
                case 211: goto L_0x045b;
                case 212: goto L_0x045b;
                case 213: goto L_0x045b;
                case 214: goto L_0x045b;
                case 215: goto L_0x045b;
                case 216: goto L_0x045b;
                case 217: goto L_0x045b;
                case 218: goto L_0x045b;
                case 219: goto L_0x045b;
                case 220: goto L_0x043c;
                default: goto L_0x0436;
            }
        L_0x0436:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x043c:
            int r3 = r15 + 1
            int r1 = r8.readInt(r3)
            int r1 = r1 + r11
            r1 = r7[r1]
            r31 = r14
            r14 = r40
            r14.visitJumpInsn(r0, r1)
            int r3 = r15 + 5
            r42 = r6
            r36 = r13
            r0 = r19
            r1 = r22
            r13 = r34
            r4 = 1
            goto L_0x04c7
        L_0x045b:
            r31 = r14
            r14 = r40
            r1 = 218(0xda, float:3.05E-43)
            if (r5 >= r1) goto L_0x0466
            int r5 = r5 + -49
            goto L_0x0468
        L_0x0466:
            int r5 = r5 + -20
        L_0x0468:
            int r3 = r15 + 1
            int r1 = r8.readUnsignedShort(r3)
            int r1 = r1 + r11
            r1 = r7[r1]
            r2 = 167(0xa7, float:2.34E-43)
            if (r5 == r2) goto L_0x0493
            r3 = 168(0xa8, float:2.35E-43)
            if (r5 != r3) goto L_0x047a
            goto L_0x0493
        L_0x047a:
            if (r5 >= r2) goto L_0x0483
            int r5 = r5 + 1
            r2 = 1
            r3 = r5 ^ 1
            int r3 = r3 - r2
            goto L_0x0485
        L_0x0483:
            r3 = r5 ^ 1
        L_0x0485:
            int r2 = r11 + 3
            net.bytebuddy.jar.asm.Label r2 = r8.createLabel(r2, r7)
            r14.visitJumpInsn(r3, r2)
            r14.visitJumpInsn(r0, r1)
            r2 = 1
            goto L_0x049a
        L_0x0493:
            int r5 = r5 + 33
            r14.visitJumpInsn(r5, r1)
            r2 = r30
        L_0x049a:
            int r3 = r15 + 3
            r4 = r2
            r42 = r6
            r36 = r13
            r0 = r19
            r1 = r22
            goto L_0x04c5
        L_0x04a6:
            r31 = r14
            r14 = r40
            int r5 = r5 - r18
            int r3 = r15 + 1
            int r0 = r8.readInt(r3)
            int r1 = r11 + r0
            r0 = r7[r1]
            r14.visitJumpInsn(r5, r0)
            int r3 = r15 + 5
            r42 = r6
            r36 = r13
            r0 = r19
            r1 = r22
            r4 = r30
        L_0x04c5:
            r13 = r34
        L_0x04c7:
            r33 = 132(0x84, float:1.85E-43)
            r34 = r9
            goto L_0x0792
        L_0x04cd:
            r31 = r14
            r14 = r40
            int r3 = r15 + 1
            r4 = r34
            java.lang.String r0 = r8.readClass(r3, r4)
            int r3 = r15 + 3
            byte r1 = r12[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r14.visitMultiANewArrayInsn(r0, r1)
            int r3 = r15 + 4
            goto L_0x0527
        L_0x04e5:
            r31 = r14
            r4 = r34
            r14 = r40
            int r3 = r15 + 1
            byte r0 = r12[r3]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = 132(0x84, float:1.85E-43)
            if (r0 != r3) goto L_0x0507
            int r0 = r15 + 2
            int r0 = r8.readUnsignedShort(r0)
            int r1 = r15 + 4
            short r1 = r8.readShort(r1)
            r14.visitIincInsn(r0, r1)
            int r0 = r15 + 6
            goto L_0x0526
        L_0x0507:
            int r1 = r15 + 2
            int r1 = r8.readUnsignedShort(r1)
            r14.visitVarInsn(r0, r1)
            int r0 = r15 + 4
            goto L_0x0526
        L_0x0513:
            r31 = r14
            r4 = r34
            r3 = 132(0x84, float:1.85E-43)
            r14 = r40
            int r0 = r15 + 1
            java.lang.String r0 = r8.readClass(r0, r4)
            r14.visitTypeInsn(r5, r0)
            int r0 = r15 + 3
        L_0x0526:
            r3 = r0
        L_0x0527:
            r42 = r6
            r34 = r9
        L_0x052b:
            r36 = r13
            r0 = r19
            r1 = r22
            r33 = 132(0x84, float:1.85E-43)
            r13 = r4
            goto L_0x0790
        L_0x0536:
            r31 = r14
            r4 = r34
            r3 = 132(0x84, float:1.85E-43)
            r14 = r40
            int[] r0 = r8.cpInfoOffsets
            int r1 = r15 + 1
            int r1 = r8.readUnsignedShort(r1)
            r0 = r0[r1]
            int[] r1 = r8.cpInfoOffsets
            int r2 = r0 + 2
            int r2 = r8.readUnsignedShort(r2)
            r1 = r1[r2]
            java.lang.String r2 = r8.readUTF8(r1, r4)
            int r1 = r1 + 2
            java.lang.String r1 = r8.readUTF8(r1, r4)
            int[] r5 = r8.bootstrapMethodOffsets
            int r0 = r8.readUnsignedShort(r0)
            r0 = r5[r0]
            int r5 = r8.readUnsignedShort(r0)
            java.lang.Object r5 = r8.readConst(r5, r4)
            net.bytebuddy.jar.asm.Handle r5 = (net.bytebuddy.jar.asm.Handle) r5
            int r3 = r0 + 2
            int r3 = r8.readUnsignedShort(r3)
            r42 = r6
            java.lang.Object[] r6 = new java.lang.Object[r3]
            int r0 = r0 + 4
            r34 = r9
            r9 = 0
        L_0x057d:
            if (r9 >= r3) goto L_0x0592
            r36 = r3
            int r3 = r8.readUnsignedShort(r0)
            java.lang.Object r3 = r8.readConst(r3, r4)
            r6[r9] = r3
            int r0 = r0 + 2
            int r9 = r9 + 1
            r3 = r36
            goto L_0x057d
        L_0x0592:
            r14.visitInvokeDynamicInsn(r2, r1, r5, r6)
            int r3 = r15 + 5
            goto L_0x052b
        L_0x0598:
            r42 = r6
            r31 = r14
            r4 = r34
            r14 = r40
            r34 = r9
            int[] r0 = r8.cpInfoOffsets
            int r3 = r15 + 1
            int r1 = r8.readUnsignedShort(r3)
            r0 = r0[r1]
            int[] r1 = r8.cpInfoOffsets
            int r2 = r0 + 2
            int r2 = r8.readUnsignedShort(r2)
            r1 = r1[r2]
            java.lang.String r2 = r8.readClass(r0, r4)
            java.lang.String r3 = r8.readUTF8(r1, r4)
            int r1 = r1 + 2
            java.lang.String r6 = r8.readUTF8(r1, r4)
            r1 = 182(0xb6, float:2.55E-43)
            if (r5 >= r1) goto L_0x05d2
            r14.visitFieldInsn(r5, r2, r3, r6)
            r6 = r5
            r36 = r13
            r33 = 132(0x84, float:1.85E-43)
            r13 = r4
            goto L_0x05eb
        L_0x05d2:
            int r0 = r0 + -1
            byte r0 = r12[r0]
            r1 = 11
            if (r0 != r1) goto L_0x05dc
            r9 = 1
            goto L_0x05dd
        L_0x05dc:
            r9 = 0
        L_0x05dd:
            r0 = r40
            r1 = r5
            r33 = 132(0x84, float:1.85E-43)
            r36 = r13
            r13 = r4
            r4 = r6
            r6 = r5
            r5 = r9
            r0.visitMethodInsn(r1, r2, r3, r4, r5)
        L_0x05eb:
            r0 = 185(0xb9, float:2.59E-43)
            if (r6 != r0) goto L_0x075c
            int r3 = r15 + 5
            goto L_0x078c
        L_0x05f3:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r34 = r9
            r0 = r11 & 3
            int r0 = 4 - r0
            int r3 = r15 + r0
            int r0 = r8.readInt(r3)
            int r1 = r11 + r0
            r0 = r7[r1]
            int r1 = r3 + 4
            int r1 = r8.readInt(r1)
            int r3 = r3 + 8
            int[] r2 = new int[r1]
            net.bytebuddy.jar.asm.Label[] r4 = new net.bytebuddy.jar.asm.Label[r1]
            r5 = 0
        L_0x061c:
            if (r5 >= r1) goto L_0x0634
            int r6 = r8.readInt(r3)
            r2[r5] = r6
            int r6 = r3 + 4
            int r6 = r8.readInt(r6)
            int r6 = r6 + r11
            r6 = r7[r6]
            r4[r5] = r6
            int r3 = r3 + 8
            int r5 = r5 + 1
            goto L_0x061c
        L_0x0634:
            r14.visitLookupSwitchInsn(r0, r2, r4)
            goto L_0x078c
        L_0x0639:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r34 = r9
            r0 = r11 & 3
            int r0 = 4 - r0
            int r3 = r15 + r0
            int r0 = r8.readInt(r3)
            int r1 = r11 + r0
            r0 = r7[r1]
            int r1 = r3 + 4
            int r1 = r8.readInt(r1)
            int r2 = r3 + 8
            int r2 = r8.readInt(r2)
            int r3 = r3 + 12
            int r4 = r2 - r1
            r5 = 1
            int r4 = r4 + r5
            net.bytebuddy.jar.asm.Label[] r5 = new net.bytebuddy.jar.asm.Label[r4]
            r6 = 0
        L_0x066a:
            if (r6 >= r4) goto L_0x067a
            int r9 = r8.readInt(r3)
            int r9 = r9 + r11
            r9 = r7[r9]
            r5[r6] = r9
            int r3 = r3 + 4
            int r6 = r6 + 1
            goto L_0x066a
        L_0x067a:
            r14.visitTableSwitchInsn(r1, r2, r0, r5)
            goto L_0x078c
        L_0x067f:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r3 = r15 + 1
            short r0 = r8.readShort(r3)
            int r1 = r11 + r0
            r0 = r7[r1]
            r14.visitJumpInsn(r6, r0)
            goto L_0x075c
        L_0x069d:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r34 = r9
            int r3 = r15 + 1
            byte r0 = r12[r3]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r3 = r15 + 2
            byte r1 = r12[r3]
            r14.visitIincInsn(r0, r1)
            goto L_0x075c
        L_0x06ba:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r5 = r6 + -59
            int r0 = r5 >> 2
            int r0 = r0 + 54
            r1 = r5 & 3
            r14.visitVarInsn(r0, r1)
            goto L_0x078a
        L_0x06d6:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r5 = r6 + -26
            int r0 = r5 >> 2
            int r0 = r0 + 21
            r1 = r5 & 3
            r14.visitVarInsn(r0, r1)
            goto L_0x078a
        L_0x06f2:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r3 = r15 + 1
            byte r0 = r12[r3]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r14.visitVarInsn(r6, r0)
            goto L_0x0775
        L_0x070c:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r34 = r9
            int r3 = r15 + 1
            int r0 = r8.readUnsignedShort(r3)
            java.lang.Object r0 = r8.readConst(r0, r13)
            r14.visitLdcInsn(r0)
            goto L_0x075c
        L_0x0728:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r34 = r9
            int r3 = r15 + 1
            byte r0 = r12[r3]
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.Object r0 = r8.readConst(r0, r13)
            r14.visitLdcInsn(r0)
            goto L_0x0775
        L_0x0744:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r3 = r15 + 1
            short r0 = r8.readShort(r3)
            r14.visitIntInsn(r6, r0)
        L_0x075c:
            int r3 = r15 + 3
            goto L_0x078c
        L_0x075f:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            int r3 = r15 + 1
            byte r0 = r12[r3]
            r14.visitIntInsn(r6, r0)
        L_0x0775:
            int r3 = r15 + 2
            goto L_0x078c
        L_0x0778:
            r42 = r6
            r36 = r13
            r31 = r14
            r13 = r34
            r33 = 132(0x84, float:1.85E-43)
            r14 = r40
            r6 = r5
            r34 = r9
            r14.visitInsn(r6)
        L_0x078a:
            int r3 = r15 + 1
        L_0x078c:
            r0 = r19
            r1 = r22
        L_0x0790:
            r4 = r30
        L_0x0792:
            r9 = r35
            if (r35 == 0) goto L_0x07be
            int r2 = r9.length
            if (r1 >= r2) goto L_0x07be
            if (r0 > r11) goto L_0x07be
            if (r0 != r11) goto L_0x07b5
            r0 = r9[r1]
            int r0 = r8.readTypeAnnotationTarget(r10, r0)
            java.lang.String r2 = r8.readUTF8(r0, r13)
            int r0 = r0 + 2
            int r5 = r10.currentTypeAnnotationTarget
            net.bytebuddy.jar.asm.TypePath r6 = r10.currentTypeAnnotationTargetPath
            r15 = 1
            net.bytebuddy.jar.asm.AnnotationVisitor r2 = r14.visitInsnAnnotation(r5, r6, r2, r15)
            r8.readElementValues(r2, r0, r15, r13)
        L_0x07b5:
            int r1 = r1 + 1
            int r0 = r8.getTypeAnnotationBytecodeOffset(r9, r1)
            r35 = r9
            goto L_0x0792
        L_0x07be:
            r2 = r20
            r5 = r27
            r15 = r37
        L_0x07c4:
            if (r15 == 0) goto L_0x0801
            int r6 = r15.length
            if (r5 >= r6) goto L_0x0801
            if (r2 > r11) goto L_0x0801
            if (r2 != r11) goto L_0x07ed
            r2 = r15[r5]
            int r2 = r8.readTypeAnnotationTarget(r10, r2)
            java.lang.String r6 = r8.readUTF8(r2, r13)
            int r2 = r2 + 2
            r19 = r0
            int r0 = r10.currentTypeAnnotationTarget
            r20 = r1
            net.bytebuddy.jar.asm.TypePath r1 = r10.currentTypeAnnotationTargetPath
            r22 = r11
            r11 = 0
            net.bytebuddy.jar.asm.AnnotationVisitor r0 = r14.visitInsnAnnotation(r0, r1, r6, r11)
            r1 = 1
            r8.readElementValues(r0, r2, r1, r13)
            goto L_0x07f4
        L_0x07ed:
            r19 = r0
            r20 = r1
            r22 = r11
            r11 = 0
        L_0x07f4:
            int r5 = r5 + 1
            int r2 = r8.getTypeAnnotationBytecodeOffset(r15, r5)
            r0 = r19
            r1 = r20
            r11 = r22
            goto L_0x07c4
        L_0x0801:
            r19 = r0
            r20 = r1
            r11 = 0
            r6 = r42
            r27 = r5
            r11 = r9
            r5 = r15
            r22 = r20
            r14 = r23
            r15 = r29
            r0 = r31
            r9 = r34
            r20 = r2
            r34 = r32
            r32 = r36
            r36 = r7
            r7 = 0
            goto L_0x0339
        L_0x0821:
            r9 = r11
            r23 = r14
            r29 = r15
            r7 = r36
            r11 = 0
            r14 = r40
            r15 = r5
            r0 = r7[r21]
            if (r0 == 0) goto L_0x0835
            r0 = r7[r21]
            r14.visitLabel(r0)
        L_0x0835:
            r4 = r25
            if (r4 == 0) goto L_0x08cc
            int r0 = r10.parsingOptions
            r0 = r0 & 2
            if (r0 != 0) goto L_0x08cc
            r0 = r26
            if (r0 == 0) goto L_0x086d
            int r1 = r8.readUnsignedShort(r0)
            int r1 = r1 * 3
            int[] r6 = new int[r1]
            int r26 = r0 + 2
            r0 = r26
        L_0x084f:
            if (r1 <= 0) goto L_0x086b
            int r1 = r1 + -1
            int r2 = r0 + 6
            r6[r1] = r2
            r2 = -1
            int r1 = r1 + r2
            int r3 = r0 + 8
            int r3 = r8.readUnsignedShort(r3)
            r6[r1] = r3
            int r1 = r1 + r2
            int r3 = r8.readUnsignedShort(r0)
            r6[r1] = r3
            int r0 = r0 + 10
            goto L_0x084f
        L_0x086b:
            r12 = r6
            goto L_0x086e
        L_0x086d:
            r12 = 0
        L_0x086e:
            int r0 = r8.readUnsignedShort(r4)
            int r25 = r4 + 2
            r1 = r25
        L_0x0876:
            int r16 = r0 + -1
            if (r0 <= 0) goto L_0x08cc
            int r0 = r8.readUnsignedShort(r1)
            int r2 = r1 + 2
            int r2 = r8.readUnsignedShort(r2)
            int r3 = r1 + 4
            java.lang.String r3 = r8.readUTF8(r3, r13)
            int r4 = r1 + 6
            java.lang.String r4 = r8.readUTF8(r4, r13)
            int r5 = r1 + 8
            int r6 = r8.readUnsignedShort(r5)
            int r18 = r1 + 10
            if (r12 == 0) goto L_0x08b5
            r1 = 0
        L_0x089b:
            int r5 = r12.length
            if (r1 >= r5) goto L_0x08b5
            r5 = r12[r1]
            if (r5 != r0) goto L_0x08b2
            int r5 = r1 + 1
            r5 = r12[r5]
            if (r5 != r6) goto L_0x08b2
            int r1 = r1 + 2
            r1 = r12[r1]
            java.lang.String r1 = r8.readUTF8(r1, r13)
            r5 = r1
            goto L_0x08b6
        L_0x08b2:
            int r1 = r1 + 3
            goto L_0x089b
        L_0x08b5:
            r5 = 0
        L_0x08b6:
            r19 = r7[r0]
            int r0 = r0 + r2
            r20 = r7[r0]
            r0 = r40
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r19
            r5 = r20
            r0.visitLocalVariable(r1, r2, r3, r4, r5, r6)
            r0 = r16
            r1 = r18
            goto L_0x0876
        L_0x08cc:
            r12 = 65
            r7 = 64
            if (r9 == 0) goto L_0x0926
            int r6 = r9.length
            r5 = 0
        L_0x08d4:
            if (r5 >= r6) goto L_0x0926
            r0 = r9[r5]
            int r1 = r8.readByte(r0)
            if (r1 == r7) goto L_0x08ea
            if (r1 != r12) goto L_0x08e1
            goto L_0x08ea
        L_0x08e1:
            r19 = r5
            r20 = r6
            r12 = 64
            r16 = 0
            goto L_0x091c
        L_0x08ea:
            int r0 = r8.readTypeAnnotationTarget(r10, r0)
            java.lang.String r16 = r8.readUTF8(r0, r13)
            int r4 = r0 + 2
            int r1 = r10.currentTypeAnnotationTarget
            net.bytebuddy.jar.asm.TypePath r2 = r10.currentTypeAnnotationTargetPath
            net.bytebuddy.jar.asm.Label[] r3 = r10.currentLocalVariableAnnotationRangeStarts
            net.bytebuddy.jar.asm.Label[] r0 = r10.currentLocalVariableAnnotationRangeEnds
            int[] r7 = r10.currentLocalVariableAnnotationRangeIndices
            r18 = 1
            r19 = r0
            r0 = r40
            r11 = r4
            r4 = r19
            r19 = r5
            r5 = r7
            r20 = r6
            r6 = r16
            r12 = 64
            r16 = 0
            r7 = r18
            net.bytebuddy.jar.asm.AnnotationVisitor r0 = r0.visitLocalVariableAnnotation(r1, r2, r3, r4, r5, r6, r7)
            r1 = 1
            r8.readElementValues(r0, r11, r1, r13)
        L_0x091c:
            int r5 = r19 + 1
            r6 = r20
            r7 = 64
            r11 = 0
            r12 = 65
            goto L_0x08d4
        L_0x0926:
            r12 = 64
            r16 = 0
            if (r15 == 0) goto L_0x096f
            int r9 = r15.length
            r11 = 0
        L_0x092e:
            if (r11 >= r9) goto L_0x096f
            r0 = r15[r11]
            int r1 = r8.readByte(r0)
            r7 = 65
            if (r1 == r12) goto L_0x0941
            if (r1 != r7) goto L_0x093d
            goto L_0x0941
        L_0x093d:
            r1 = 1
            r18 = 65
            goto L_0x096a
        L_0x0941:
            int r0 = r8.readTypeAnnotationTarget(r10, r0)
            java.lang.String r6 = r8.readUTF8(r0, r13)
            int r5 = r0 + 2
            int r1 = r10.currentTypeAnnotationTarget
            net.bytebuddy.jar.asm.TypePath r2 = r10.currentTypeAnnotationTargetPath
            net.bytebuddy.jar.asm.Label[] r3 = r10.currentLocalVariableAnnotationRangeStarts
            net.bytebuddy.jar.asm.Label[] r4 = r10.currentLocalVariableAnnotationRangeEnds
            int[] r0 = r10.currentLocalVariableAnnotationRangeIndices
            r16 = 0
            r18 = r0
            r0 = r40
            r12 = r5
            r5 = r18
            r18 = 65
            r7 = r16
            net.bytebuddy.jar.asm.AnnotationVisitor r0 = r0.visitLocalVariableAnnotation(r1, r2, r3, r4, r5, r6, r7)
            r1 = 1
            r8.readElementValues(r0, r12, r1, r13)
        L_0x096a:
            int r11 = r11 + 1
            r12 = 64
            goto L_0x092e
        L_0x096f:
            r0 = r24
        L_0x0971:
            if (r0 == 0) goto L_0x097d
            net.bytebuddy.jar.asm.Attribute r1 = r0.nextAttribute
            r2 = 0
            r0.nextAttribute = r2
            r14.visitAttribute(r0)
            r0 = r1
            goto L_0x0971
        L_0x097d:
            r0 = r23
            r1 = r29
            r14.visitMaxs(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readCode(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.jar.asm.Context, int):void");
    }

    /* access modifiers changed from: protected */
    public Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    private Label createLabel(int i, Label[] labelArr) {
        Label readLabel = readLabel(i, labelArr);
        readLabel.flags = (short) (readLabel.flags & -2);
        return readLabel;
    }

    private void createDebugLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            Label readLabel = readLabel(i, labelArr);
            readLabel.flags = (short) (readLabel.flags | 1);
        }
    }

    private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2;
        char[] cArr = context.charBuffer;
        int readUnsignedShort = readUnsignedShort(i);
        int[] iArr = new int[readUnsignedShort];
        int i3 = i + 2;
        for (int i4 = 0; i4 < readUnsignedShort; i4++) {
            iArr[i4] = i3;
            int readInt = readInt(i3);
            int i5 = readInt >>> 24;
            if (i5 != 23) {
                switch (i5) {
                    case 16:
                    case 17:
                    case 18:
                        break;
                    default:
                        switch (i5) {
                            case 64:
                            case 65:
                                int readUnsignedShort2 = readUnsignedShort(i3 + 1);
                                i2 = i3 + 3;
                                while (true) {
                                    int i6 = readUnsignedShort2 - 1;
                                    if (readUnsignedShort2 <= 0) {
                                        break;
                                    } else {
                                        int readUnsignedShort3 = readUnsignedShort(i2);
                                        int readUnsignedShort4 = readUnsignedShort(i2 + 2);
                                        i2 += 6;
                                        createLabel(readUnsignedShort3, context.currentMethodLabels);
                                        createLabel(readUnsignedShort3 + readUnsignedShort4, context.currentMethodLabels);
                                        readUnsignedShort2 = i6;
                                    }
                                }
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                                break;
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                                i2 = i3 + 4;
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                        break;
                }
            }
            i2 = i3 + 3;
            int readByte = readByte(i2);
            TypePath typePath = null;
            if (i5 == 66) {
                if (readByte != 0) {
                    typePath = new TypePath(this.b, i2);
                }
                int i7 = i2 + (readByte * 2) + 1;
                i3 = readElementValues(methodVisitor.visitTryCatchAnnotation(readInt & InputDeviceCompat.SOURCE_ANY, typePath, readUTF8(i7, cArr), z), i7 + 2, true, cArr);
            } else {
                i3 = readElementValues((AnnotationVisitor) null, i2 + (readByte * 2) + 3, true, cArr);
            }
        }
        return iArr;
    }

    private int getTypeAnnotationBytecodeOffset(int[] iArr, int i) {
        if (iArr == null || i >= iArr.length || readByte(iArr[i]) < 67) {
            return -1;
        }
        return readUnsignedShort(iArr[i] + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006b, code lost:
        r0 = r0 & androidx.core.view.InputDeviceCompat.SOURCE_ANY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006d, code lost:
        r11 = r11 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int readTypeAnnotationTarget(net.bytebuddy.jar.asm.Context r10, int r11) {
        /*
            r9 = this;
            int r0 = r9.readInt(r11)
            int r1 = r0 >>> 24
            r2 = 1
            if (r1 == 0) goto L_0x0070
            if (r1 == r2) goto L_0x0070
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            switch(r1) {
                case 16: goto L_0x006b;
                case 17: goto L_0x006b;
                case 18: goto L_0x006b;
                case 19: goto L_0x0068;
                case 20: goto L_0x0068;
                case 21: goto L_0x0068;
                case 22: goto L_0x0070;
                case 23: goto L_0x006b;
                default: goto L_0x0010;
            }
        L_0x0010:
            switch(r1) {
                case 64: goto L_0x0022;
                case 65: goto L_0x0022;
                case 66: goto L_0x006b;
                case 67: goto L_0x0020;
                case 68: goto L_0x0020;
                case 69: goto L_0x0020;
                case 70: goto L_0x0020;
                case 71: goto L_0x0019;
                case 72: goto L_0x0019;
                case 73: goto L_0x0019;
                case 74: goto L_0x0019;
                case 75: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>()
            throw r10
        L_0x0019:
            r1 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
            r0 = r0 & r1
            int r11 = r11 + 4
            goto L_0x0075
        L_0x0020:
            r0 = r0 & r3
            goto L_0x006d
        L_0x0022:
            r0 = r0 & r3
            int r1 = r11 + 1
            int r1 = r9.readUnsignedShort(r1)
            int r11 = r11 + 3
            net.bytebuddy.jar.asm.Label[] r3 = new net.bytebuddy.jar.asm.Label[r1]
            r10.currentLocalVariableAnnotationRangeStarts = r3
            net.bytebuddy.jar.asm.Label[] r3 = new net.bytebuddy.jar.asm.Label[r1]
            r10.currentLocalVariableAnnotationRangeEnds = r3
            int[] r3 = new int[r1]
            r10.currentLocalVariableAnnotationRangeIndices = r3
            r3 = 0
        L_0x0038:
            if (r3 >= r1) goto L_0x0075
            int r4 = r9.readUnsignedShort(r11)
            int r5 = r11 + 2
            int r5 = r9.readUnsignedShort(r5)
            int r6 = r11 + 4
            int r6 = r9.readUnsignedShort(r6)
            int r11 = r11 + 6
            net.bytebuddy.jar.asm.Label[] r7 = r10.currentLocalVariableAnnotationRangeStarts
            net.bytebuddy.jar.asm.Label[] r8 = r10.currentMethodLabels
            net.bytebuddy.jar.asm.Label r8 = r9.createLabel(r4, r8)
            r7[r3] = r8
            net.bytebuddy.jar.asm.Label[] r7 = r10.currentLocalVariableAnnotationRangeEnds
            int r4 = r4 + r5
            net.bytebuddy.jar.asm.Label[] r5 = r10.currentMethodLabels
            net.bytebuddy.jar.asm.Label r4 = r9.createLabel(r4, r5)
            r7[r3] = r4
            int[] r4 = r10.currentLocalVariableAnnotationRangeIndices
            r4[r3] = r6
            int r3 = r3 + 1
            goto L_0x0038
        L_0x0068:
            r0 = r0 & r3
            int r11 = r11 + r2
            goto L_0x0075
        L_0x006b:
            r0 = r0 & -256(0xffffffffffffff00, float:NaN)
        L_0x006d:
            int r11 = r11 + 3
            goto L_0x0075
        L_0x0070:
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r0 = r0 & r1
            int r11 = r11 + 2
        L_0x0075:
            r10.currentTypeAnnotationTarget = r0
            int r0 = r9.readByte(r11)
            if (r0 != 0) goto L_0x007f
            r1 = 0
            goto L_0x0086
        L_0x007f:
            net.bytebuddy.jar.asm.TypePath r1 = new net.bytebuddy.jar.asm.TypePath
            byte[] r3 = r9.b
            r1.<init>(r3, r11)
        L_0x0086:
            r10.currentTypeAnnotationTargetPath = r1
            int r11 = r11 + r2
            int r0 = r0 * 2
            int r11 = r11 + r0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readTypeAnnotationTarget(net.bytebuddy.jar.asm.Context, int):int");
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2 = i + 1;
        byte b2 = this.b[i] & 255;
        methodVisitor.visitAnnotableParameterCount(b2, z);
        char[] cArr = context.charBuffer;
        for (int i3 = 0; i3 < b2; i3++) {
            int readUnsignedShort = readUnsignedShort(i2);
            i2 += 2;
            while (true) {
                int i4 = readUnsignedShort - 1;
                if (readUnsignedShort <= 0) {
                    break;
                }
                i2 = readElementValues(methodVisitor.visitParameterAnnotation(i3, readUTF8(i2, cArr), z), i2 + 2, true, cArr);
                readUnsignedShort = i4;
            }
        }
    }

    private int readElementValues(AnnotationVisitor annotationVisitor, int i, boolean z, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + 2;
        if (!z) {
            while (true) {
                int i3 = readUnsignedShort - 1;
                if (readUnsignedShort <= 0) {
                    break;
                }
                i2 = readElementValue(annotationVisitor, i2, (String) null, cArr);
                readUnsignedShort = i3;
            }
        } else {
            while (true) {
                int i4 = readUnsignedShort - 1;
                if (readUnsignedShort <= 0) {
                    break;
                }
                i2 = readElementValue(annotationVisitor, i2 + 2, readUTF8(i2, cArr), cArr);
                readUnsignedShort = i4;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return i2;
    }

    private int readElementValue(AnnotationVisitor annotationVisitor, int i, String str, char[] cArr) {
        int i2 = 0;
        if (annotationVisitor == null) {
            byte b2 = this.b[i] & 255;
            if (b2 == 64) {
                return readElementValues((AnnotationVisitor) null, i + 3, true, cArr);
            }
            if (b2 != 91) {
                return b2 != 101 ? i + 3 : i + 5;
            }
            return readElementValues((AnnotationVisitor) null, i + 1, false, cArr);
        }
        int i3 = i + 1;
        byte b3 = this.b[i] & 255;
        if (b3 == 64) {
            return readElementValues(annotationVisitor.visitAnnotation(str, readUTF8(i3, cArr)), i3 + 2, true, cArr);
        }
        if (b3 != 70) {
            if (b3 == 83) {
                annotationVisitor.visit(str, Short.valueOf((short) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
            } else if (b3 == 99) {
                annotationVisitor.visit(str, Type.getType(readUTF8(i3, cArr)));
            } else if (b3 == 101) {
                annotationVisitor.visitEnum(str, readUTF8(i3, cArr), readUTF8(i3 + 2, cArr));
                return i3 + 4;
            } else if (b3 == 115) {
                annotationVisitor.visit(str, readUTF8(i3, cArr));
            } else if (!(b3 == 73 || b3 == 74)) {
                if (b3 == 90) {
                    annotationVisitor.visit(str, readInt(this.cpInfoOffsets[readUnsignedShort(i3)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                } else if (b3 != 91) {
                    switch (b3) {
                        case 66:
                            annotationVisitor.visit(str, Byte.valueOf((byte) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
                            break;
                        case 67:
                            annotationVisitor.visit(str, Character.valueOf((char) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
                            break;
                        case 68:
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                } else {
                    int readUnsignedShort = readUnsignedShort(i3);
                    int i4 = i3 + 2;
                    if (readUnsignedShort == 0) {
                        return readElementValues(annotationVisitor.visitArray(str), i4 - 2, false, cArr);
                    }
                    byte b4 = this.b[i4] & 255;
                    if (b4 == 70) {
                        float[] fArr = new float[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            fArr[i2] = Float.intBitsToFloat(readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]));
                            i4 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, fArr);
                        return i4;
                    } else if (b4 == 83) {
                        short[] sArr = new short[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            sArr[i2] = (short) readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]);
                            i4 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, sArr);
                        return i4;
                    } else if (b4 == 90) {
                        boolean[] zArr = new boolean[readUnsignedShort];
                        for (int i5 = 0; i5 < readUnsignedShort; i5++) {
                            zArr[i5] = readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]) != 0;
                            i4 += 3;
                        }
                        annotationVisitor.visit(str, zArr);
                        return i4;
                    } else if (b4 == 73) {
                        int[] iArr = new int[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            iArr[i2] = readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]);
                            i4 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, iArr);
                        return i4;
                    } else if (b4 != 74) {
                        switch (b4) {
                            case 66:
                                byte[] bArr = new byte[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    bArr[i2] = (byte) readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]);
                                    i4 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, bArr);
                                return i4;
                            case 67:
                                char[] cArr2 = new char[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    cArr2[i2] = (char) readInt(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]);
                                    i4 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, cArr2);
                                return i4;
                            case 68:
                                double[] dArr = new double[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    dArr[i2] = Double.longBitsToDouble(readLong(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]));
                                    i4 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, dArr);
                                return i4;
                            default:
                                return readElementValues(annotationVisitor.visitArray(str), i4 - 2, false, cArr);
                        }
                    } else {
                        long[] jArr = new long[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            jArr[i2] = readLong(this.cpInfoOffsets[readUnsignedShort(i4 + 1)]);
                            i4 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, jArr);
                        return i4;
                    }
                }
            }
            return i3 + 2;
        }
        annotationVisitor.visit(str, readConst(readUnsignedShort(i3), cArr));
        return i3 + 2;
    }

    private void computeImplicitFrame(Context context) {
        int i;
        int i2;
        String str = context.currentMethodDescriptor;
        Object[] objArr = context.currentFrameLocalTypes;
        int i3 = 0;
        if ((context.currentMethodAccessFlags & 8) == 0) {
            if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(context.currentMethodName)) {
                objArr[0] = Opcodes.UNINITIALIZED_THIS;
            } else {
                objArr[0] = readClass(this.header + 2, context.charBuffer);
            }
            i3 = 1;
        }
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            char charAt = str.charAt(i4);
            if (charAt == 'F') {
                i2 = i + 1;
                objArr[i] = Opcodes.FLOAT;
            } else if (charAt != 'L') {
                if (!(charAt == 'S' || charAt == 'I')) {
                    if (charAt == 'J') {
                        i2 = i + 1;
                        objArr[i] = Opcodes.LONG;
                    } else if (charAt != 'Z') {
                        if (charAt != '[') {
                            switch (charAt) {
                                case 'B':
                                case 'C':
                                    break;
                                case 'D':
                                    i2 = i + 1;
                                    objArr[i] = Opcodes.DOUBLE;
                                    break;
                                default:
                                    context.currentFrameLocalCount = i;
                                    return;
                            }
                        } else {
                            while (str.charAt(i5) == '[') {
                                i5++;
                            }
                            if (str.charAt(i5) == 'L') {
                                do {
                                    i5++;
                                } while (str.charAt(i5) != ';');
                            }
                            int i6 = i5 + 1;
                            objArr[i] = str.substring(i4, i6);
                            i4 = i6;
                            i++;
                        }
                    }
                }
                i2 = i + 1;
                objArr[i] = Opcodes.INTEGER;
            } else {
                int i7 = i5;
                while (str.charAt(i7) != ';') {
                    i7++;
                }
                objArr[i] = str.substring(i5, i7);
                i++;
                i4 = i7 + 1;
            }
            i = i2;
            i4 = i5;
        }
    }

    private int readStackMapFrame(int i, boolean z, boolean z2, Context context) {
        int i2;
        int i3;
        char[] cArr = context.charBuffer;
        Label[] labelArr = context.currentMethodLabels;
        if (z) {
            i3 = i + 1;
            i2 = this.b[i] & 255;
        } else {
            context.currentFrameOffset = -1;
            i3 = i;
            i2 = 255;
        }
        context.currentFrameLocalCountDelta = 0;
        if (i2 < 64) {
            context.currentFrameType = 3;
            context.currentFrameStackCount = 0;
        } else if (i2 < 128) {
            i2 -= 64;
            i3 = readVerificationTypeInfo(i3, context.currentFrameStackTypes, 0, cArr, labelArr);
            context.currentFrameType = 4;
            context.currentFrameStackCount = 1;
        } else if (i2 >= 247) {
            int readUnsignedShort = readUnsignedShort(i3);
            int i4 = i3 + 2;
            if (i2 == 247) {
                i4 = readVerificationTypeInfo(i4, context.currentFrameStackTypes, 0, cArr, labelArr);
                context.currentFrameType = 4;
                context.currentFrameStackCount = 1;
            } else if (i2 >= 248 && i2 < 251) {
                context.currentFrameType = 2;
                context.currentFrameLocalCountDelta = 251 - i2;
                context.currentFrameLocalCount -= context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            } else if (i2 == 251) {
                context.currentFrameType = 3;
                context.currentFrameStackCount = 0;
            } else if (i2 < 255) {
                int i5 = i2 - 251;
                int i6 = z2 ? context.currentFrameLocalCount : 0;
                int i7 = i5;
                while (i7 > 0) {
                    i4 = readVerificationTypeInfo(i4, context.currentFrameLocalTypes, i6, cArr, labelArr);
                    i7--;
                    i6++;
                }
                context.currentFrameType = 1;
                context.currentFrameLocalCountDelta = i5;
                context.currentFrameLocalCount += context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            } else {
                int readUnsignedShort2 = readUnsignedShort(i4);
                int i8 = i4 + 2;
                context.currentFrameType = 0;
                context.currentFrameLocalCountDelta = readUnsignedShort2;
                context.currentFrameLocalCount = readUnsignedShort2;
                for (int i9 = 0; i9 < readUnsignedShort2; i9++) {
                    i8 = readVerificationTypeInfo(i8, context.currentFrameLocalTypes, i9, cArr, labelArr);
                }
                int readUnsignedShort3 = readUnsignedShort(i8);
                i4 = i8 + 2;
                context.currentFrameStackCount = readUnsignedShort3;
                for (int i10 = 0; i10 < readUnsignedShort3; i10++) {
                    i4 = readVerificationTypeInfo(i4, context.currentFrameStackTypes, i10, cArr, labelArr);
                }
            }
            i2 = readUnsignedShort;
        } else {
            throw new IllegalArgumentException();
        }
        context.currentFrameOffset += i2 + 1;
        createLabel(context.currentFrameOffset, labelArr);
        return i3;
    }

    private int readVerificationTypeInfo(int i, Object[] objArr, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i + 1;
        switch (this.b[i] & 255) {
            case 0:
                objArr[i2] = Opcodes.TOP;
                return i3;
            case 1:
                objArr[i2] = Opcodes.INTEGER;
                return i3;
            case 2:
                objArr[i2] = Opcodes.FLOAT;
                return i3;
            case 3:
                objArr[i2] = Opcodes.DOUBLE;
                return i3;
            case 4:
                objArr[i2] = Opcodes.LONG;
                return i3;
            case 5:
                objArr[i2] = Opcodes.NULL;
                return i3;
            case 6:
                objArr[i2] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case 7:
                objArr[i2] = readClass(i3, cArr);
                break;
            case 8:
                objArr[i2] = createLabel(readUnsignedShort(i3), labelArr);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return i3 + 2;
    }

    /* access modifiers changed from: package-private */
    public final int getFirstAttributeOffset() {
        int i = this.header;
        int readUnsignedShort = i + 8 + (readUnsignedShort(i + 6) * 2);
        int readUnsignedShort2 = readUnsignedShort(readUnsignedShort);
        int i2 = readUnsignedShort + 2;
        while (true) {
            int i3 = readUnsignedShort2 - 1;
            if (readUnsignedShort2 <= 0) {
                break;
            }
            int readUnsignedShort3 = readUnsignedShort(i2 + 6);
            i2 += 8;
            while (true) {
                int i4 = readUnsignedShort3 - 1;
                if (readUnsignedShort3 <= 0) {
                    break;
                }
                i2 += readInt(i2 + 2) + 6;
                readUnsignedShort3 = i4;
            }
            readUnsignedShort2 = i3;
        }
        int readUnsignedShort4 = readUnsignedShort(i2);
        int i5 = i2 + 2;
        while (true) {
            int i6 = readUnsignedShort4 - 1;
            if (readUnsignedShort4 <= 0) {
                return i5 + 2;
            }
            int readUnsignedShort5 = readUnsignedShort(i5 + 6);
            i5 += 8;
            while (true) {
                int i7 = readUnsignedShort5 - 1;
                if (readUnsignedShort5 <= 0) {
                    break;
                }
                i5 += readInt(i5 + 2) + 6;
                readUnsignedShort5 = i7;
            }
            readUnsignedShort4 = i6;
        }
    }

    private int[] readBootstrapMethodsAttribute(int i) {
        char[] cArr = new char[i];
        int firstAttributeOffset = getFirstAttributeOffset();
        for (int readUnsignedShort = readUnsignedShort(firstAttributeOffset - 2); readUnsignedShort > 0; readUnsignedShort--) {
            String readUTF8 = readUTF8(firstAttributeOffset, cArr);
            int readInt = readInt(firstAttributeOffset + 2);
            int i2 = firstAttributeOffset + 6;
            if ("BootstrapMethods".equals(readUTF8)) {
                int readUnsignedShort2 = readUnsignedShort(i2);
                int[] iArr = new int[readUnsignedShort2];
                int i3 = i2 + 2;
                for (int i4 = 0; i4 < readUnsignedShort2; i4++) {
                    iArr[i4] = i3;
                    i3 += (readUnsignedShort(i3 + 2) * 2) + 4;
                }
                return iArr;
            }
            firstAttributeOffset = i2 + readInt;
        }
        return null;
    }

    private Attribute readAttribute(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        String str2 = str;
        for (Attribute attribute : attributeArr) {
            if (attribute.type.equals(str)) {
                return attribute.read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, (char[]) null, -1, (Label[]) null);
    }

    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }

    public int getItem(int i) {
        return this.cpInfoOffsets[i];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int i) {
        return this.b[i] & 255;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public short readShort(int i) {
        byte[] bArr = this.b;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int readInt(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }

    public long readLong(int i) {
        return (((long) readInt(i)) << 32) | (((long) readInt(i + 4)) & 4294967295L);
    }

    public String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        if (i == 0 || readUnsignedShort == 0) {
            return null;
        }
        return readUtf(readUnsignedShort, cArr);
    }

    /* access modifiers changed from: package-private */
    public final String readUtf(int i, char[] cArr) {
        String[] strArr = this.constantUtf8Values;
        String str = strArr[i];
        if (str != null) {
            return str;
        }
        int i2 = this.cpInfoOffsets[i];
        String readUtf = readUtf(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[i] = readUtf;
        return readUtf;
    }

    private String readUtf(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.b;
        int i5 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            if ((b2 & 128) == 0) {
                i3 = i5 + 1;
                cArr[i5] = (char) (b2 & Byte.MAX_VALUE);
            } else if ((b2 & 224) == 192) {
                cArr[i5] = (char) (((b2 & Ascii.US) << 6) + (bArr[i6] & Utf8.REPLACEMENT_BYTE));
                i5++;
                i = i6 + 1;
            } else {
                i3 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = ((b2 & Ascii.SI) << Ascii.FF) + ((bArr[i6] & Utf8.REPLACEMENT_BYTE) << 6);
                i6 = i7 + 1;
                cArr[i5] = (char) (i8 + (bArr[i7] & Utf8.REPLACEMENT_BYTE));
            }
            i = i6;
            i5 = i3;
        }
        return new String(cArr, 0, i5);
    }

    private String readStringish(int i, char[] cArr) {
        return readUTF8(this.cpInfoOffsets[readUnsignedShort(i)], cArr);
    }

    public String readClass(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readModule(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readPackage(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    private ConstantDynamic readConstantDynamic(int i, char[] cArr) {
        ConstantDynamic constantDynamic = this.constantDynamicValues[i];
        if (constantDynamic != null) {
            return constantDynamic;
        }
        int[] iArr = this.cpInfoOffsets;
        int i2 = iArr[i];
        int i3 = iArr[readUnsignedShort(i2 + 2)];
        String readUTF8 = readUTF8(i3, cArr);
        String readUTF82 = readUTF8(i3 + 2, cArr);
        int i4 = this.bootstrapMethodOffsets[readUnsignedShort(i2)];
        Handle handle = (Handle) readConst(readUnsignedShort(i4), cArr);
        int readUnsignedShort = readUnsignedShort(i4 + 2);
        Object[] objArr = new Object[readUnsignedShort];
        int i5 = i4 + 4;
        for (int i6 = 0; i6 < readUnsignedShort; i6++) {
            objArr[i6] = readConst(readUnsignedShort(i5), cArr);
            i5 += 2;
        }
        ConstantDynamic[] constantDynamicArr = this.constantDynamicValues;
        ConstantDynamic constantDynamic2 = new ConstantDynamic(readUTF8, readUTF82, handle, objArr);
        constantDynamicArr[i] = constantDynamic2;
        return constantDynamic2;
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.cpInfoOffsets[i];
        byte b2 = this.b[i2 - 1];
        switch (b2) {
            case 3:
                return Integer.valueOf(readInt(i2));
            case 4:
                return Float.valueOf(Float.intBitsToFloat(readInt(i2)));
            case 5:
                return Long.valueOf(readLong(i2));
            case 6:
                return Double.valueOf(Double.longBitsToDouble(readLong(i2)));
            case 7:
                return Type.getObjectType(readUTF8(i2, cArr));
            case 8:
                return readUTF8(i2, cArr);
            default:
                switch (b2) {
                    case 15:
                        int readByte = readByte(i2);
                        int i3 = this.cpInfoOffsets[readUnsignedShort(i2 + 1)];
                        int i4 = this.cpInfoOffsets[readUnsignedShort(i3 + 2)];
                        return new Handle(readByte, readClass(i3, cArr), readUTF8(i4, cArr), readUTF8(i4 + 2, cArr), this.b[i3 - 1] == 11);
                    case 16:
                        return Type.getMethodType(readUTF8(i2, cArr));
                    case 17:
                        return readConstantDynamic(i, cArr);
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }
}
