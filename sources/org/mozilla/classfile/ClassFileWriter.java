package org.mozilla.classfile;

import androidx.core.view.ViewCompat;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import net.bytebuddy.description.method.MethodDescription;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.UintMap;

public class ClassFileWriter {
    public static final short ACC_ABSTRACT = 1024;
    public static final short ACC_FINAL = 16;
    public static final short ACC_NATIVE = 256;
    public static final short ACC_PRIVATE = 2;
    public static final short ACC_PROTECTED = 4;
    public static final short ACC_PUBLIC = 1;
    public static final short ACC_STATIC = 8;
    public static final short ACC_SUPER = 32;
    public static final short ACC_SYNCHRONIZED = 32;
    public static final short ACC_TRANSIENT = 128;
    public static final short ACC_VOLATILE = 64;
    private static final boolean DEBUGCODE = false;
    private static final boolean DEBUGLABELS = false;
    private static final boolean DEBUGSTACK = false;
    private static final int ExceptionTableSize = 4;
    private static final int FileHeaderConstant = -889275714;
    private static final boolean GenerateStackMap;
    private static final int LineNumberTableSize = 16;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private static final int MajorVersion;
    private static final int MinorVersion;
    private static final int SuperBlockStartsSize = 4;
    private String generatedClassName;
    /* access modifiers changed from: private */
    public byte[] itsCodeBuffer = new byte[256];
    /* access modifiers changed from: private */
    public int itsCodeBufferTop;
    /* access modifiers changed from: private */
    public ConstantPool itsConstantPool;
    private ClassFileMethod itsCurrentMethod;
    /* access modifiers changed from: private */
    public ExceptionTableEntry[] itsExceptionTable;
    /* access modifiers changed from: private */
    public int itsExceptionTableTop;
    private ObjArray itsFields = new ObjArray();
    private long[] itsFixupTable;
    private int itsFixupTableTop;
    private short itsFlags;
    private ObjArray itsInterfaces = new ObjArray();
    /* access modifiers changed from: private */
    public UintMap itsJumpFroms = null;
    private int[] itsLabelTable;
    private int itsLabelTableTop;
    private int[] itsLineNumberTable;
    private int itsLineNumberTableTop;
    /* access modifiers changed from: private */
    public short itsMaxLocals;
    /* access modifiers changed from: private */
    public short itsMaxStack;
    private ObjArray itsMethods = new ObjArray();
    private short itsSourceFileNameIndex;
    private short itsStackTop;
    /* access modifiers changed from: private */
    public int[] itsSuperBlockStarts = null;
    /* access modifiers changed from: private */
    public int itsSuperBlockStartsTop = 0;
    private short itsSuperClassIndex;
    /* access modifiers changed from: private */
    public short itsThisClassIndex;
    private ObjArray itsVarDescriptors;
    private char[] tmpCharBuffer = new char[64];

    private static String bytecodeStr(int i) {
        return "";
    }

    static /* synthetic */ int access$410(ClassFileWriter classFileWriter) {
        int i = classFileWriter.itsExceptionTableTop;
        classFileWriter.itsExceptionTableTop = i - 1;
        return i;
    }

    public static class ClassFileFormatException extends RuntimeException {
        private static final long serialVersionUID = 1263998431033790599L;

        ClassFileFormatException(String str) {
            super(str);
        }
    }

    public ClassFileWriter(String str, String str2, String str3) {
        this.generatedClassName = str;
        ConstantPool constantPool = new ConstantPool(this);
        this.itsConstantPool = constantPool;
        this.itsThisClassIndex = constantPool.addClass(str);
        this.itsSuperClassIndex = this.itsConstantPool.addClass(str2);
        if (str3 != null) {
            this.itsSourceFileNameIndex = this.itsConstantPool.addUtf8(str3);
        }
        this.itsFlags = 33;
    }

    public final String getClassName() {
        return this.generatedClassName;
    }

    public void addInterface(String str) {
        this.itsInterfaces.add(Short.valueOf(this.itsConstantPool.addClass(str)));
    }

    public void setFlags(short s) {
        this.itsFlags = s;
    }

    static String getSlashedForm(String str) {
        return str.replace('.', '/');
    }

    public static String classNameToSignature(String str) {
        int length = str.length();
        int i = length + 1;
        int i2 = i + 1;
        char[] cArr = new char[i2];
        cArr[0] = 'L';
        cArr[i] = ';';
        str.getChars(0, length, cArr, 1);
        for (int i3 = 1; i3 != i; i3++) {
            if (cArr[i3] == '.') {
                cArr[i3] = '/';
            }
        }
        return new String(cArr, 0, i2);
    }

    public void addField(String str, String str2, short s) {
        this.itsFields.add(new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s));
    }

    public void addField(String str, String str2, short s, int i) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), 0, 0, this.itsConstantPool.addConstant(i));
        this.itsFields.add(classFileField);
    }

    public void addField(String str, String str2, short s, long j) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), 0, 2, this.itsConstantPool.addConstant(j));
        this.itsFields.add(classFileField);
    }

    public void addField(String str, String str2, short s, double d) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), 0, 2, this.itsConstantPool.addConstant(d));
        this.itsFields.add(classFileField);
    }

    public void addVariableDescriptor(String str, String str2, int i, int i2) {
        int[] iArr = {this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), i, i2};
        if (this.itsVarDescriptors == null) {
            this.itsVarDescriptors = new ObjArray();
        }
        this.itsVarDescriptors.add(iArr);
    }

    public void startMethod(String str, String str2, short s) {
        this.itsCurrentMethod = new ClassFileMethod(str, this.itsConstantPool.addUtf8(str), str2, this.itsConstantPool.addUtf8(str2), s);
        this.itsJumpFroms = new UintMap();
        this.itsMethods.add(this.itsCurrentMethod);
        addSuperBlockStart(0);
    }

    public void stopMethod(short s) {
        StackMapTable stackMapTable;
        int i;
        int computeWriteSize;
        if (this.itsCurrentMethod != null) {
            fixLabelGotos();
            this.itsMaxLocals = s;
            if (GenerateStackMap) {
                finalizeSuperBlockStarts();
                stackMapTable = new StackMapTable();
                stackMapTable.generate();
            } else {
                stackMapTable = null;
            }
            int i2 = this.itsLineNumberTable != null ? (this.itsLineNumberTableTop * 4) + 8 : 0;
            ObjArray objArray = this.itsVarDescriptors;
            int size = objArray != null ? (objArray.size() * 10) + 8 : 0;
            int i3 = (stackMapTable == null || (computeWriteSize = stackMapTable.computeWriteSize()) <= 0) ? 0 : computeWriteSize + 6;
            int i4 = this.itsCodeBufferTop + 14 + 2 + (this.itsExceptionTableTop * 8) + 2 + i2 + size + i3;
            if (i4 <= 65536) {
                byte[] bArr = new byte[i4];
                int putInt32 = putInt32(this.itsCodeBufferTop, bArr, putInt16(this.itsMaxLocals, bArr, putInt16(this.itsMaxStack, bArr, putInt32(i4 - 6, bArr, putInt16(this.itsConstantPool.addUtf8("Code"), bArr, 0)))));
                System.arraycopy(this.itsCodeBuffer, 0, bArr, putInt32, this.itsCodeBufferTop);
                int i5 = putInt32 + this.itsCodeBufferTop;
                int i6 = this.itsExceptionTableTop;
                if (i6 > 0) {
                    i = putInt16(i6, bArr, i5);
                    int i7 = 0;
                    while (i7 < this.itsExceptionTableTop) {
                        ExceptionTableEntry exceptionTableEntry = this.itsExceptionTable[i7];
                        short labelPC = (short) getLabelPC(exceptionTableEntry.itsStartLabel);
                        short labelPC2 = (short) getLabelPC(exceptionTableEntry.itsEndLabel);
                        short labelPC3 = (short) getLabelPC(exceptionTableEntry.itsHandlerLabel);
                        short s2 = exceptionTableEntry.itsCatchType;
                        if (labelPC == -1) {
                            throw new IllegalStateException("start label not defined");
                        } else if (labelPC2 == -1) {
                            throw new IllegalStateException("end label not defined");
                        } else if (labelPC3 != -1) {
                            i = putInt16(s2, bArr, putInt16(labelPC3, bArr, putInt16(labelPC2, bArr, putInt16(labelPC, bArr, i))));
                            i7++;
                        } else {
                            throw new IllegalStateException("handler label not defined");
                        }
                    }
                } else {
                    i = putInt16(0, bArr, i5);
                }
                int i8 = this.itsLineNumberTable != null ? 1 : 0;
                if (this.itsVarDescriptors != null) {
                    i8++;
                }
                if (i3 > 0) {
                    i8++;
                }
                int putInt16 = putInt16(i8, bArr, i);
                if (this.itsLineNumberTable != null) {
                    putInt16 = putInt16(this.itsLineNumberTableTop, bArr, putInt32((this.itsLineNumberTableTop * 4) + 2, bArr, putInt16(this.itsConstantPool.addUtf8("LineNumberTable"), bArr, putInt16)));
                    for (int i9 = 0; i9 < this.itsLineNumberTableTop; i9++) {
                        putInt16 = putInt32(this.itsLineNumberTable[i9], bArr, putInt16);
                    }
                }
                if (this.itsVarDescriptors != null) {
                    int putInt162 = putInt16(this.itsConstantPool.addUtf8("LocalVariableTable"), bArr, putInt16);
                    int size2 = this.itsVarDescriptors.size();
                    int putInt163 = putInt16(size2, bArr, putInt32((size2 * 10) + 2, bArr, putInt162));
                    for (int i10 = 0; i10 < size2; i10++) {
                        int[] iArr = (int[]) this.itsVarDescriptors.get(i10);
                        int i11 = iArr[0];
                        int i12 = iArr[1];
                        int i13 = iArr[2];
                        putInt163 = putInt16(iArr[3], bArr, putInt16(i12, bArr, putInt16(i11, bArr, putInt16(this.itsCodeBufferTop - i13, bArr, putInt16(i13, bArr, putInt16)))));
                    }
                }
                if (i3 > 0) {
                    stackMapTable.write(bArr, putInt16(this.itsConstantPool.addUtf8("StackMapTable"), bArr, putInt16));
                }
                this.itsCurrentMethod.setCodeAttribute(bArr);
                this.itsExceptionTable = null;
                this.itsExceptionTableTop = 0;
                this.itsLineNumberTableTop = 0;
                this.itsCodeBufferTop = 0;
                this.itsCurrentMethod = null;
                this.itsMaxStack = 0;
                this.itsStackTop = 0;
                this.itsLabelTableTop = 0;
                this.itsFixupTableTop = 0;
                this.itsVarDescriptors = null;
                this.itsSuperBlockStarts = null;
                this.itsSuperBlockStartsTop = 0;
                this.itsJumpFroms = null;
                return;
            }
            throw new ClassFileFormatException("generated bytecode for method exceeds 64K limit.");
        }
        throw new IllegalStateException("No method to stop");
    }

    public void add(int i) {
        if (opcodeCount(i) == 0) {
            int stackChange = this.itsStackTop + stackChange(i);
            if (stackChange < 0 || 32767 < stackChange) {
                badStack(stackChange);
            }
            addToCodeBuffer(i);
            short s = (short) stackChange;
            this.itsStackTop = s;
            if (stackChange > this.itsMaxStack) {
                this.itsMaxStack = s;
            }
            if (i == 191) {
                addSuperBlockStart(this.itsCodeBufferTop);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Unexpected operands");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        if (r7 < 0) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
        if (r7 >= 65536) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
        if (r7 < 256) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004a, code lost:
        addToCodeBuffer(org.mozilla.classfile.ByteCode.WIDE);
        addToCodeBuffer(r6);
        addToCodeInt16(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0057, code lost:
        addToCodeBuffer(r6);
        addToCodeBuffer(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        throw new org.mozilla.classfile.ClassFileWriter.ClassFileFormatException("out of range variable");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void add(int r6, int r7) {
        /*
            r5 = this;
            short r0 = r5.itsStackTop
            int r1 = stackChange(r6)
            int r0 = r0 + r1
            if (r0 < 0) goto L_0x000d
            r1 = 32767(0x7fff, float:4.5916E-41)
            if (r1 >= r0) goto L_0x0010
        L_0x000d:
            badStack(r0)
        L_0x0010:
            r1 = 180(0xb4, float:2.52E-43)
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r6 == r1) goto L_0x0114
            r1 = 181(0xb5, float:2.54E-43)
            if (r6 == r1) goto L_0x0114
            r1 = 188(0xbc, float:2.63E-43)
            java.lang.String r3 = "out of range index"
            r4 = 256(0x100, float:3.59E-43)
            if (r6 == r1) goto L_0x0103
            r1 = 198(0xc6, float:2.77E-43)
            if (r6 == r1) goto L_0x00ba
            r1 = 199(0xc7, float:2.79E-43)
            if (r6 == r1) goto L_0x00ba
            switch(r6) {
                case 16: goto L_0x00a7;
                case 17: goto L_0x0094;
                case 18: goto L_0x0067;
                case 19: goto L_0x0067;
                case 20: goto L_0x0067;
                case 21: goto L_0x0044;
                case 22: goto L_0x0044;
                case 23: goto L_0x0044;
                case 24: goto L_0x0044;
                case 25: goto L_0x0044;
                default: goto L_0x002d;
            }
        L_0x002d:
            switch(r6) {
                case 54: goto L_0x0044;
                case 55: goto L_0x0044;
                case 56: goto L_0x0044;
                case 57: goto L_0x0044;
                case 58: goto L_0x0044;
                default: goto L_0x0030;
            }
        L_0x0030:
            switch(r6) {
                case 153: goto L_0x00ba;
                case 154: goto L_0x00ba;
                case 155: goto L_0x00ba;
                case 156: goto L_0x00ba;
                case 157: goto L_0x00ba;
                case 158: goto L_0x00ba;
                case 159: goto L_0x00ba;
                case 160: goto L_0x00ba;
                case 161: goto L_0x00ba;
                case 162: goto L_0x00ba;
                case 163: goto L_0x00ba;
                case 164: goto L_0x00ba;
                case 165: goto L_0x00ba;
                case 166: goto L_0x00ba;
                case 167: goto L_0x003b;
                case 168: goto L_0x00ba;
                case 169: goto L_0x0044;
                default: goto L_0x0033;
            }
        L_0x0033:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Unexpected opcode for 1 operand"
            r6.<init>(r7)
            throw r6
        L_0x003b:
            int r1 = r5.itsCodeBufferTop
            int r1 = r1 + 3
            r5.addSuperBlockStart(r1)
            goto L_0x00ba
        L_0x0044:
            if (r7 < 0) goto L_0x005f
            if (r7 >= r2) goto L_0x005f
            if (r7 < r4) goto L_0x0057
            r1 = 196(0xc4, float:2.75E-43)
            r5.addToCodeBuffer(r1)
            r5.addToCodeBuffer(r6)
            r5.addToCodeInt16(r7)
            goto L_0x011e
        L_0x0057:
            r5.addToCodeBuffer(r6)
            r5.addToCodeBuffer(r7)
            goto L_0x011e
        L_0x005f:
            org.mozilla.classfile.ClassFileWriter$ClassFileFormatException r6 = new org.mozilla.classfile.ClassFileWriter$ClassFileFormatException
            java.lang.String r7 = "out of range variable"
            r6.<init>(r7)
            throw r6
        L_0x0067:
            if (r7 < 0) goto L_0x008e
            if (r7 >= r2) goto L_0x008e
            r1 = 19
            if (r7 >= r4) goto L_0x007e
            if (r6 == r1) goto L_0x007e
            r2 = 20
            if (r6 != r2) goto L_0x0076
            goto L_0x007e
        L_0x0076:
            r5.addToCodeBuffer(r6)
            r5.addToCodeBuffer(r7)
            goto L_0x011e
        L_0x007e:
            r2 = 18
            if (r6 != r2) goto L_0x0086
            r5.addToCodeBuffer(r1)
            goto L_0x0089
        L_0x0086:
            r5.addToCodeBuffer(r6)
        L_0x0089:
            r5.addToCodeInt16(r7)
            goto L_0x011e
        L_0x008e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r3)
            throw r6
        L_0x0094:
            short r1 = (short) r7
            if (r1 != r7) goto L_0x009f
            r5.addToCodeBuffer(r6)
            r5.addToCodeInt16(r7)
            goto L_0x011e
        L_0x009f:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "out of range short"
            r6.<init>(r7)
            throw r6
        L_0x00a7:
            byte r1 = (byte) r7
            if (r1 != r7) goto L_0x00b2
            r5.addToCodeBuffer(r6)
            r5.addToCodeBuffer(r1)
            goto L_0x011e
        L_0x00b2:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "out of range byte"
            r6.<init>(r7)
            throw r6
        L_0x00ba:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r7 & r1
            if (r2 == r1) goto L_0x00d0
            if (r7 < 0) goto L_0x00c8
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r7 > r3) goto L_0x00c8
            goto L_0x00d0
        L_0x00c8:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Bad label for branch"
            r6.<init>(r7)
            throw r6
        L_0x00d0:
            int r3 = r5.itsCodeBufferTop
            r5.addToCodeBuffer(r6)
            if (r2 == r1) goto L_0x00e4
            r5.addToCodeInt16(r7)
            int r7 = r7 + r3
            r5.addSuperBlockStart(r7)
            org.mozilla.javascript.UintMap r6 = r5.itsJumpFroms
            r6.put((int) r7, (int) r3)
            goto L_0x011e
        L_0x00e4:
            int r6 = r5.getLabelPC(r7)
            r1 = -1
            if (r6 == r1) goto L_0x00f9
            int r7 = r6 - r3
            r5.addToCodeInt16(r7)
            r5.addSuperBlockStart(r6)
            org.mozilla.javascript.UintMap r7 = r5.itsJumpFroms
            r7.put((int) r6, (int) r3)
            goto L_0x011e
        L_0x00f9:
            int r3 = r3 + 1
            r5.addLabelFixup(r7, r3)
            r6 = 0
            r5.addToCodeInt16(r6)
            goto L_0x011e
        L_0x0103:
            if (r7 < 0) goto L_0x010e
            if (r7 >= r4) goto L_0x010e
            r5.addToCodeBuffer(r6)
            r5.addToCodeBuffer(r7)
            goto L_0x011e
        L_0x010e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r3)
            throw r6
        L_0x0114:
            if (r7 < 0) goto L_0x0128
            if (r7 >= r2) goto L_0x0128
            r5.addToCodeBuffer(r6)
            r5.addToCodeInt16(r7)
        L_0x011e:
            short r6 = (short) r0
            r5.itsStackTop = r6
            short r7 = r5.itsMaxStack
            if (r0 <= r7) goto L_0x0127
            r5.itsMaxStack = r6
        L_0x0127:
            return
        L_0x0128:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "out of range field"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.add(int, int):void");
    }

    public void addLoadConstant(int i) {
        if (i == 0) {
            add(3);
        } else if (i == 1) {
            add(4);
        } else if (i == 2) {
            add(5);
        } else if (i == 3) {
            add(6);
        } else if (i == 4) {
            add(7);
        } else if (i != 5) {
            add(18, this.itsConstantPool.addConstant(i));
        } else {
            add(8);
        }
    }

    public void addLoadConstant(long j) {
        add(20, this.itsConstantPool.addConstant(j));
    }

    public void addLoadConstant(float f) {
        add(18, this.itsConstantPool.addConstant(f));
    }

    public void addLoadConstant(double d) {
        add(20, this.itsConstantPool.addConstant(d));
    }

    public void addLoadConstant(String str) {
        add(18, this.itsConstantPool.addConstant(str));
    }

    public void add(int i, int i2, int i3) {
        int stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < 0 || 32767 < stackChange) {
            badStack(stackChange);
        }
        if (i == 132) {
            if (i2 < 0 || i2 >= 65536) {
                throw new ClassFileFormatException("out of range variable");
            } else if (i3 < 0 || i3 >= 65536) {
                throw new ClassFileFormatException("out of range increment");
            } else if (i2 > 255 || i3 < -128 || i3 > 127) {
                addToCodeBuffer(ByteCode.WIDE);
                addToCodeBuffer(132);
                addToCodeInt16(i2);
                addToCodeInt16(i3);
            } else {
                addToCodeBuffer(132);
                addToCodeBuffer(i2);
                addToCodeBuffer(i3);
            }
        } else if (i != 197) {
            throw new IllegalArgumentException("Unexpected opcode for 2 operands");
        } else if (i2 < 0 || i2 >= 65536) {
            throw new IllegalArgumentException("out of range index");
        } else if (i3 < 0 || i3 >= 256) {
            throw new IllegalArgumentException("out of range dimensions");
        } else {
            addToCodeBuffer(197);
            addToCodeInt16(i2);
            addToCodeBuffer(i3);
        }
        short s = (short) stackChange;
        this.itsStackTop = s;
        if (stackChange > this.itsMaxStack) {
            this.itsMaxStack = s;
        }
    }

    public void add(int i, String str) {
        int stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < 0 || 32767 < stackChange) {
            badStack(stackChange);
        }
        if (i == 187 || i == 189 || i == 192 || i == 193) {
            short addClass = this.itsConstantPool.addClass(str);
            addToCodeBuffer(i);
            addToCodeInt16(addClass);
            short s = (short) stackChange;
            this.itsStackTop = s;
            if (stackChange > this.itsMaxStack) {
                this.itsMaxStack = s;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("bad opcode for class reference");
    }

    public void add(int i, String str, String str2, String str3) {
        int i2;
        int stackChange = this.itsStackTop + stackChange(i);
        char charAt = str3.charAt(0);
        int i3 = (charAt == 'J' || charAt == 'D') ? 2 : 1;
        switch (i) {
            case 178:
            case 180:
                i2 = stackChange + i3;
                break;
            case 179:
            case 181:
                i2 = stackChange - i3;
                break;
            default:
                throw new IllegalArgumentException("bad opcode for field reference");
        }
        if (i2 < 0 || 32767 < i2) {
            badStack(i2);
        }
        short addFieldRef = this.itsConstantPool.addFieldRef(str, str2, str3);
        addToCodeBuffer(i);
        addToCodeInt16(addFieldRef);
        short s = (short) i2;
        this.itsStackTop = s;
        if (i2 > this.itsMaxStack) {
            this.itsMaxStack = s;
        }
    }

    public void addInvoke(int i, String str, String str2, String str3) {
        int sizeOfParameters = sizeOfParameters(str3);
        int i2 = sizeOfParameters >>> 16;
        int stackChange = this.itsStackTop + ((short) sizeOfParameters) + stackChange(i);
        if (stackChange < 0 || 32767 < stackChange) {
            badStack(stackChange);
        }
        switch (i) {
            case 182:
            case 183:
            case 184:
            case 185:
                addToCodeBuffer(i);
                if (i == 185) {
                    addToCodeInt16(this.itsConstantPool.addInterfaceMethodRef(str, str2, str3));
                    addToCodeBuffer(i2 + 1);
                    addToCodeBuffer(0);
                } else {
                    addToCodeInt16(this.itsConstantPool.addMethodRef(str, str2, str3));
                }
                short s = (short) stackChange;
                this.itsStackTop = s;
                if (stackChange > this.itsMaxStack) {
                    this.itsMaxStack = s;
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("bad opcode for method reference");
        }
    }

    public void addPush(int i) {
        byte b = (byte) i;
        if (b != i) {
            short s = (short) i;
            if (s == i) {
                add(17, (int) s);
            } else {
                addLoadConstant(i);
            }
        } else if (i == -1) {
            add(2);
        } else if (i < 0 || i > 5) {
            add(16, (int) b);
        } else {
            add((byte) (i + 3));
        }
    }

    public void addPush(boolean z) {
        add(z ? 4 : 3);
    }

    public void addPush(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            addPush(i);
            add(133);
            return;
        }
        addLoadConstant(j);
    }

    public void addPush(double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            add(14);
            if (1.0d / d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                add(119);
            }
        } else if (d == 1.0d || d == -1.0d) {
            add(15);
            if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                add(119);
            }
        } else {
            addLoadConstant(d);
        }
    }

    public void addPush(String str) {
        int length = str.length();
        int i = 0;
        int utfEncodingLimit = this.itsConstantPool.getUtfEncodingLimit(str, 0, length);
        if (utfEncodingLimit == length) {
            addLoadConstant(str);
            return;
        }
        add(187, "java/lang/StringBuffer");
        add(89);
        addPush(length);
        addInvoke(183, "java/lang/StringBuffer", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(I)V");
        while (true) {
            add(89);
            addLoadConstant(str.substring(i, utfEncodingLimit));
            addInvoke(182, "java/lang/StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
            add(87);
            if (utfEncodingLimit == length) {
                addInvoke(182, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;");
                return;
            }
            i = utfEncodingLimit;
            utfEncodingLimit = this.itsConstantPool.getUtfEncodingLimit(str, utfEncodingLimit, length);
        }
    }

    public boolean isUnderStringSizeLimit(String str) {
        return this.itsConstantPool.isUnderUtfEncodingLimit(str);
    }

    public void addIStore(int i) {
        xop(59, 54, i);
    }

    public void addLStore(int i) {
        xop(63, 55, i);
    }

    public void addFStore(int i) {
        xop(67, 56, i);
    }

    public void addDStore(int i) {
        xop(71, 57, i);
    }

    public void addAStore(int i) {
        xop(75, 58, i);
    }

    public void addILoad(int i) {
        xop(26, 21, i);
    }

    public void addLLoad(int i) {
        xop(30, 22, i);
    }

    public void addFLoad(int i) {
        xop(34, 23, i);
    }

    public void addDLoad(int i) {
        xop(38, 24, i);
    }

    public void addALoad(int i) {
        xop(42, 25, i);
    }

    public void addLoadThis() {
        add(42);
    }

    private void xop(int i, int i2, int i3) {
        if (i3 == 0) {
            add(i);
        } else if (i3 == 1) {
            add(i + 1);
        } else if (i3 == 2) {
            add(i + 2);
        } else if (i3 != 3) {
            add(i2, i3);
        } else {
            add(i + 3);
        }
    }

    public int addTableSwitch(int i, int i2) {
        if (i <= i2) {
            int stackChange = this.itsStackTop + stackChange(170);
            if (stackChange < 0 || 32767 < stackChange) {
                badStack(stackChange);
            }
            int i3 = (~this.itsCodeBufferTop) & 3;
            int addReservedCodeSpace = addReservedCodeSpace(i3 + 1 + (((i2 - i) + 1 + 3) * 4));
            int i4 = addReservedCodeSpace + 1;
            this.itsCodeBuffer[addReservedCodeSpace] = -86;
            while (i3 != 0) {
                this.itsCodeBuffer[i4] = 0;
                i3--;
                i4++;
            }
            putInt32(i2, this.itsCodeBuffer, putInt32(i, this.itsCodeBuffer, i4 + 4));
            short s = (short) stackChange;
            this.itsStackTop = s;
            if (stackChange > this.itsMaxStack) {
                this.itsMaxStack = s;
            }
            return addReservedCodeSpace;
        }
        throw new ClassFileFormatException("Bad bounds: " + i + ' ' + i2);
    }

    public final void markTableSwitchDefault(int i) {
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, -1, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int i, int i2) {
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, i2, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int i, int i2, int i3) {
        if (i3 < 0 || i3 > this.itsMaxStack) {
            throw new IllegalArgumentException("Bad stack index: " + i3);
        }
        this.itsStackTop = (short) i3;
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, i2, this.itsCodeBufferTop);
    }

    public void setTableSwitchJump(int i, int i2, int i3) {
        if (i3 < 0 || i3 > this.itsCodeBufferTop) {
            throw new IllegalArgumentException("Bad jump target: " + i3);
        } else if (i2 >= -1) {
            int i4 = (~i) & 3;
            int i5 = i2 < 0 ? i + 1 + i4 : i + 1 + i4 + ((i2 + 3) * 4);
            if (i >= 0) {
                int i6 = this.itsCodeBufferTop;
                if (i <= ((i6 - 16) - i4) - 1) {
                    byte[] bArr = this.itsCodeBuffer;
                    if ((bArr[i] & 255) != 170) {
                        throw new IllegalArgumentException(i + " is not offset of tableswitch statement");
                    } else if (i5 < 0 || i5 + 4 > i6) {
                        throw new ClassFileFormatException("Too big case index: " + i2);
                    } else {
                        putInt32(i3 - i, bArr, i5);
                        return;
                    }
                }
            }
            throw new IllegalArgumentException(i + " is outside a possible range of tableswitch" + " in already generated code");
        } else {
            throw new IllegalArgumentException("Bad case index: " + i2);
        }
    }

    public int acquireLabel() {
        int i = this.itsLabelTableTop;
        int[] iArr = this.itsLabelTable;
        if (iArr == null || i == iArr.length) {
            int[] iArr2 = this.itsLabelTable;
            if (iArr2 == null) {
                this.itsLabelTable = new int[32];
            } else {
                int[] iArr3 = new int[(iArr2.length * 2)];
                System.arraycopy(iArr2, 0, iArr3, 0, i);
                this.itsLabelTable = iArr3;
            }
        }
        this.itsLabelTableTop = i + 1;
        this.itsLabelTable[i] = -1;
        return i | Integer.MIN_VALUE;
    }

    public void markLabel(int i) {
        if (i < 0) {
            int i2 = i & Integer.MAX_VALUE;
            if (i2 <= this.itsLabelTableTop) {
                int[] iArr = this.itsLabelTable;
                if (iArr[i2] == -1) {
                    iArr[i2] = this.itsCodeBufferTop;
                    return;
                }
                throw new IllegalStateException("Can only mark label once");
            }
            throw new IllegalArgumentException("Bad label");
        }
        throw new IllegalArgumentException("Bad label, no biscuit");
    }

    public void markLabel(int i, short s) {
        markLabel(i);
        this.itsStackTop = s;
    }

    public void markHandler(int i) {
        this.itsStackTop = 1;
        markLabel(i);
    }

    public int getLabelPC(int i) {
        if (i < 0) {
            int i2 = i & Integer.MAX_VALUE;
            if (i2 < this.itsLabelTableTop) {
                return this.itsLabelTable[i2];
            }
            throw new IllegalArgumentException("Bad label");
        }
        throw new IllegalArgumentException("Bad label, no biscuit");
    }

    private void addLabelFixup(int i, int i2) {
        if (i < 0) {
            int i3 = i & Integer.MAX_VALUE;
            if (i3 < this.itsLabelTableTop) {
                int i4 = this.itsFixupTableTop;
                long[] jArr = this.itsFixupTable;
                if (jArr == null || i4 == jArr.length) {
                    long[] jArr2 = this.itsFixupTable;
                    if (jArr2 == null) {
                        this.itsFixupTable = new long[40];
                    } else {
                        long[] jArr3 = new long[(jArr2.length * 2)];
                        System.arraycopy(jArr2, 0, jArr3, 0, i4);
                        this.itsFixupTable = jArr3;
                    }
                }
                this.itsFixupTableTop = i4 + 1;
                this.itsFixupTable[i4] = ((long) i2) | (((long) i3) << 32);
                return;
            }
            throw new IllegalArgumentException("Bad label");
        }
        throw new IllegalArgumentException("Bad label, no biscuit");
    }

    private void fixLabelGotos() {
        byte[] bArr = this.itsCodeBuffer;
        int i = 0;
        while (i < this.itsFixupTableTop) {
            long j = this.itsFixupTable[i];
            int i2 = (int) j;
            int i3 = this.itsLabelTable[(int) (j >> 32)];
            if (i3 != -1) {
                addSuperBlockStart(i3);
                int i4 = i2 - 1;
                this.itsJumpFroms.put(i3, i4);
                int i5 = i3 - i4;
                if (((short) i5) == i5) {
                    bArr[i2] = (byte) (i5 >> 8);
                    bArr[i2 + 1] = (byte) i5;
                    i++;
                } else {
                    throw new ClassFileFormatException("Program too complex: too big jump offset");
                }
            } else {
                throw new RuntimeException();
            }
        }
        this.itsFixupTableTop = 0;
    }

    public int getCurrentCodeOffset() {
        return this.itsCodeBufferTop;
    }

    public short getStackTop() {
        return this.itsStackTop;
    }

    public void setStackTop(short s) {
        this.itsStackTop = s;
    }

    public void adjustStackTop(int i) {
        int i2 = this.itsStackTop + i;
        if (i2 < 0 || 32767 < i2) {
            badStack(i2);
        }
        short s = (short) i2;
        this.itsStackTop = s;
        if (i2 > this.itsMaxStack) {
            this.itsMaxStack = s;
        }
    }

    private void addToCodeBuffer(int i) {
        this.itsCodeBuffer[addReservedCodeSpace(1)] = (byte) i;
    }

    private void addToCodeInt16(int i) {
        putInt16(i, this.itsCodeBuffer, addReservedCodeSpace(2));
    }

    private int addReservedCodeSpace(int i) {
        if (this.itsCurrentMethod != null) {
            int i2 = this.itsCodeBufferTop;
            int i3 = i + i2;
            byte[] bArr = this.itsCodeBuffer;
            if (i3 > bArr.length) {
                int length = bArr.length * 2;
                if (i3 > length) {
                    length = i3;
                }
                byte[] bArr2 = new byte[length];
                System.arraycopy(this.itsCodeBuffer, 0, bArr2, 0, i2);
                this.itsCodeBuffer = bArr2;
            }
            this.itsCodeBufferTop = i3;
            return i2;
        }
        throw new IllegalArgumentException("No method to add to");
    }

    public void addExceptionHandler(int i, int i2, int i3, String str) {
        if ((i & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad startLabel");
        } else if ((i2 & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad endLabel");
        } else if ((i3 & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            ExceptionTableEntry exceptionTableEntry = new ExceptionTableEntry(i, i2, i3, str == null ? 0 : this.itsConstantPool.addClass(str));
            int i4 = this.itsExceptionTableTop;
            if (i4 == 0) {
                this.itsExceptionTable = new ExceptionTableEntry[4];
            } else {
                ExceptionTableEntry[] exceptionTableEntryArr = this.itsExceptionTable;
                if (i4 == exceptionTableEntryArr.length) {
                    ExceptionTableEntry[] exceptionTableEntryArr2 = new ExceptionTableEntry[(i4 * 2)];
                    System.arraycopy(exceptionTableEntryArr, 0, exceptionTableEntryArr2, 0, i4);
                    this.itsExceptionTable = exceptionTableEntryArr2;
                }
            }
            this.itsExceptionTable[i4] = exceptionTableEntry;
            this.itsExceptionTableTop = i4 + 1;
        } else {
            throw new IllegalArgumentException("Bad handlerLabel");
        }
    }

    public void addLineNumberEntry(short s) {
        if (this.itsCurrentMethod != null) {
            int i = this.itsLineNumberTableTop;
            if (i == 0) {
                this.itsLineNumberTable = new int[16];
            } else {
                int[] iArr = this.itsLineNumberTable;
                if (i == iArr.length) {
                    int[] iArr2 = new int[(i * 2)];
                    System.arraycopy(iArr, 0, iArr2, 0, i);
                    this.itsLineNumberTable = iArr2;
                }
            }
            this.itsLineNumberTable[i] = (this.itsCodeBufferTop << 16) + s;
            this.itsLineNumberTableTop = i + 1;
            return;
        }
        throw new IllegalArgumentException("No method to stop");
    }

    final class StackMapTable {
        static final boolean DEBUGSTACKMAP = false;
        private int[] locals = null;
        private int localsTop = 0;
        private byte[] rawStackMap = null;
        private int rawStackMapTop = 0;
        private int[] stack = null;
        private int stackTop = 0;
        private SuperBlock[] superBlockDeps;
        private SuperBlock[] superBlocks = null;
        private boolean wide = false;
        private SuperBlock[] workList = null;
        private int workListTop = 0;

        private boolean isBranch(int i) {
            switch (i) {
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 167:
                    return true;
                default:
                    switch (i) {
                        case 198:
                        case 199:
                        case 200:
                            return true;
                        default:
                            return false;
                    }
            }
        }

        private boolean isSuperBlockEnd(int i) {
            if (i == 167 || i == 191 || i == 200 || i == 176 || i == 177) {
                return true;
            }
            switch (i) {
                case 170:
                case 171:
                case 172:
                case 173:
                case 174:
                    return true;
                default:
                    return false;
            }
        }

        StackMapTable() {
        }

        /* access modifiers changed from: package-private */
        public void generate() {
            int i;
            this.superBlocks = new SuperBlock[ClassFileWriter.this.itsSuperBlockStartsTop];
            int[] access$100 = ClassFileWriter.this.createInitialLocals();
            for (int i2 = 0; i2 < ClassFileWriter.this.itsSuperBlockStartsTop; i2++) {
                int i3 = ClassFileWriter.this.itsSuperBlockStarts[i2];
                if (i2 == ClassFileWriter.this.itsSuperBlockStartsTop - 1) {
                    i = ClassFileWriter.this.itsCodeBufferTop;
                } else {
                    i = ClassFileWriter.this.itsSuperBlockStarts[i2 + 1];
                }
                this.superBlocks[i2] = new SuperBlock(i2, i3, i, access$100);
            }
            this.superBlockDeps = getSuperBlockDependencies();
            verify();
        }

        private SuperBlock getSuperBlockFromOffset(int i) {
            SuperBlock superBlock;
            int i2 = 0;
            while (true) {
                SuperBlock[] superBlockArr = this.superBlocks;
                if (i2 >= superBlockArr.length || (superBlock = superBlockArr[i2]) == null) {
                } else if (i >= superBlock.getStart() && i < superBlock.getEnd()) {
                    return superBlock;
                } else {
                    i2++;
                }
            }
            throw new IllegalArgumentException("bad offset: " + i);
        }

        private SuperBlock[] getSuperBlockDependencies() {
            SuperBlock[] superBlockArr = new SuperBlock[this.superBlocks.length];
            for (int i = 0; i < ClassFileWriter.this.itsExceptionTableTop; i++) {
                ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i];
                superBlockArr[getSuperBlockFromOffset((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel)).getIndex()] = getSuperBlockFromOffset((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel));
            }
            int[] keys = ClassFileWriter.this.itsJumpFroms.getKeys();
            for (int i2 : keys) {
                superBlockArr[getSuperBlockFromOffset(i2).getIndex()] = getSuperBlockFromOffset(ClassFileWriter.this.itsJumpFroms.getInt(i2, -1));
            }
            return superBlockArr;
        }

        private SuperBlock getBranchTarget(int i) {
            int i2;
            if ((ClassFileWriter.this.itsCodeBuffer[i] & 255) == 200) {
                i2 = getOperand(i + 1, 4);
            } else {
                i2 = (short) getOperand(i + 1, 2);
            }
            return getSuperBlockFromOffset(i + i2);
        }

        private int getOperand(int i) {
            return getOperand(i, 1);
        }

        private int getOperand(int i, int i2) {
            if (i2 <= 4) {
                byte b = 0;
                for (int i3 = 0; i3 < i2; i3++) {
                    b = (b << 8) | (ClassFileWriter.this.itsCodeBuffer[i + i3] & 255);
                }
                return b;
            }
            throw new IllegalArgumentException("bad operand size");
        }

        private void verify() {
            int[] access$100 = ClassFileWriter.this.createInitialLocals();
            int i = 0;
            this.superBlocks[0].merge(access$100, access$100.length, new int[0], 0, ClassFileWriter.this.itsConstantPool);
            this.workList = new SuperBlock[]{this.superBlocks[0]};
            this.workListTop = 1;
            executeWorkList();
            while (true) {
                SuperBlock[] superBlockArr = this.superBlocks;
                if (i < superBlockArr.length) {
                    SuperBlock superBlock = superBlockArr[i];
                    if (!superBlock.isInitialized()) {
                        killSuperBlock(superBlock);
                    }
                    i++;
                } else {
                    executeWorkList();
                    return;
                }
            }
        }

        private void killSuperBlock(SuperBlock superBlock) {
            SuperBlock superBlockFromOffset;
            int[] iArr = new int[0];
            int[] iArr2 = {TypeInfo.OBJECT("java/lang/Throwable", ClassFileWriter.this.itsConstantPool)};
            int i = 0;
            while (true) {
                if (i >= ClassFileWriter.this.itsExceptionTableTop) {
                    break;
                }
                ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i];
                int labelPC = ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel);
                int labelPC2 = ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsEndLabel);
                superBlockFromOffset = getSuperBlockFromOffset(ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel));
                if ((superBlock.getStart() <= labelPC || superBlock.getStart() >= labelPC2) && (labelPC <= superBlock.getStart() || labelPC >= superBlock.getEnd() || !superBlockFromOffset.isInitialized())) {
                    i++;
                }
            }
            iArr = superBlockFromOffset.getLocals();
            int[] iArr3 = iArr;
            int i2 = 0;
            while (i2 < ClassFileWriter.this.itsExceptionTableTop) {
                if (ClassFileWriter.this.getLabelPC(ClassFileWriter.this.itsExceptionTable[i2].itsStartLabel) == superBlock.getStart()) {
                    for (int i3 = i2 + 1; i3 < ClassFileWriter.this.itsExceptionTableTop; i3++) {
                        ClassFileWriter.this.itsExceptionTable[i3 - 1] = ClassFileWriter.this.itsExceptionTable[i3];
                    }
                    ClassFileWriter.access$410(ClassFileWriter.this);
                    i2--;
                }
                i2++;
            }
            superBlock.merge(iArr3, iArr3.length, iArr2, 1, ClassFileWriter.this.itsConstantPool);
            int end = superBlock.getEnd() - 1;
            ClassFileWriter.this.itsCodeBuffer[end] = -65;
            for (int start = superBlock.getStart(); start < end; start++) {
                ClassFileWriter.this.itsCodeBuffer[start] = 0;
            }
        }

        private void executeWorkList() {
            while (true) {
                int i = this.workListTop;
                if (i > 0) {
                    SuperBlock[] superBlockArr = this.workList;
                    int i2 = i - 1;
                    this.workListTop = i2;
                    SuperBlock superBlock = superBlockArr[i2];
                    superBlock.setInQueue(false);
                    this.locals = superBlock.getLocals();
                    int[] stack2 = superBlock.getStack();
                    this.stack = stack2;
                    this.localsTop = this.locals.length;
                    this.stackTop = stack2.length;
                    executeBlock(superBlock);
                } else {
                    return;
                }
            }
        }

        private void executeBlock(SuperBlock superBlock) {
            int i;
            int start = superBlock.getStart();
            byte b = 0;
            while (start < superBlock.getEnd()) {
                b = ClassFileWriter.this.itsCodeBuffer[start] & 255;
                int execute = execute(start);
                if (isBranch(b)) {
                    flowInto(getBranchTarget(start));
                } else if (b == 170) {
                    int i2 = start + 1 + ((~start) & 3);
                    flowInto(getSuperBlockFromOffset(getOperand(i2, 4) + start));
                    int operand = (getOperand(i2 + 8, 4) - getOperand(i2 + 4, 4)) + 1;
                    int i3 = i2 + 12;
                    for (int i4 = 0; i4 < operand; i4++) {
                        flowInto(getSuperBlockFromOffset(getOperand((i4 * 4) + i3, 4) + start));
                    }
                }
                for (int i5 = 0; i5 < ClassFileWriter.this.itsExceptionTableTop; i5++) {
                    ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i5];
                    short labelPC = (short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel);
                    short labelPC2 = (short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsEndLabel);
                    if (start >= labelPC && start < labelPC2) {
                        SuperBlock superBlockFromOffset = getSuperBlockFromOffset((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel));
                        if (exceptionTableEntry.itsCatchType == 0) {
                            i = TypeInfo.OBJECT(ClassFileWriter.this.itsConstantPool.addClass("java/lang/Throwable"));
                        } else {
                            i = TypeInfo.OBJECT(exceptionTableEntry.itsCatchType);
                        }
                        SuperBlock superBlock2 = superBlockFromOffset;
                        superBlock2.merge(this.locals, this.localsTop, new int[]{i}, 1, ClassFileWriter.this.itsConstantPool);
                        addToWorkList(superBlockFromOffset);
                    }
                }
                start += execute;
            }
            if (!isSuperBlockEnd(b)) {
                int index = superBlock.getIndex() + 1;
                SuperBlock[] superBlockArr = this.superBlocks;
                if (index < superBlockArr.length) {
                    flowInto(superBlockArr[index]);
                }
            }
        }

        private void flowInto(SuperBlock superBlock) {
            if (superBlock.merge(this.locals, this.localsTop, this.stack, this.stackTop, ClassFileWriter.this.itsConstantPool)) {
                addToWorkList(superBlock);
            }
        }

        private void addToWorkList(SuperBlock superBlock) {
            if (!superBlock.isInQueue()) {
                superBlock.setInQueue(true);
                superBlock.setInitialized(true);
                int i = this.workListTop;
                SuperBlock[] superBlockArr = this.workList;
                if (i == superBlockArr.length) {
                    SuperBlock[] superBlockArr2 = new SuperBlock[(i * 2)];
                    System.arraycopy(superBlockArr, 0, superBlockArr2, 0, i);
                    this.workList = superBlockArr2;
                }
                SuperBlock[] superBlockArr3 = this.workList;
                int i2 = this.workListTop;
                this.workListTop = i2 + 1;
                superBlockArr3[i2] = superBlock;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:101:0x0357, code lost:
            push(3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x035b, code lost:
            push(2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x035f, code lost:
            push(4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x0363, code lost:
            push(1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x036a, code lost:
            r3 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x036b, code lost:
            if (r3 != 0) goto L_0x0373;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x036d, code lost:
            r3 = org.mozilla.classfile.ClassFileWriter.opcodeLength(r0, r10.wide);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x0375, code lost:
            if (r10.wide == false) goto L_0x037d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x0379, code lost:
            if (r0 == 196) goto L_0x037d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x037b, code lost:
            r10.wide = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x037d, code lost:
            return r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00f1, code lost:
            r3 = pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0148, code lost:
            push(org.mozilla.classfile.TypeInfo.fromType(org.mozilla.classfile.ClassFileWriter.access$1200(((org.mozilla.classfile.FieldOrMethodRef) org.mozilla.classfile.ClassFileWriter.access$800(r10.this$0).getConstantData(getOperand(r11 + 1, 2))).getType()), org.mozilla.classfile.ClassFileWriter.access$800(r10.this$0)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x020d, code lost:
            pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0210, code lost:
            pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x02c9, code lost:
            pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x02d1, code lost:
            pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x02d9, code lost:
            pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x02e1, code lost:
            pop();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int execute(int r11) {
            /*
                r10 = this;
                org.mozilla.classfile.ClassFileWriter r0 = org.mozilla.classfile.ClassFileWriter.this
                byte[] r0 = r0.itsCodeBuffer
                byte r0 = r0[r11]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r1 = 6
                r2 = 5
                r3 = 8
                r4 = 0
                r5 = 3
                r6 = 4
                r7 = 2
                r8 = 1
                switch(r0) {
                    case 0: goto L_0x036a;
                    case 1: goto L_0x0367;
                    case 2: goto L_0x0363;
                    case 3: goto L_0x0363;
                    case 4: goto L_0x0363;
                    case 5: goto L_0x0363;
                    case 6: goto L_0x0363;
                    case 7: goto L_0x0363;
                    case 8: goto L_0x0363;
                    case 9: goto L_0x035f;
                    case 10: goto L_0x035f;
                    case 11: goto L_0x035b;
                    case 12: goto L_0x035b;
                    case 13: goto L_0x035b;
                    case 14: goto L_0x0357;
                    case 15: goto L_0x0357;
                    case 16: goto L_0x0363;
                    case 17: goto L_0x0363;
                    case 18: goto L_0x02fd;
                    case 19: goto L_0x02fd;
                    case 20: goto L_0x02fd;
                    case 21: goto L_0x0363;
                    case 22: goto L_0x035f;
                    case 23: goto L_0x035b;
                    case 24: goto L_0x0357;
                    case 25: goto L_0x02ed;
                    case 26: goto L_0x0363;
                    case 27: goto L_0x0363;
                    case 28: goto L_0x0363;
                    case 29: goto L_0x0363;
                    case 30: goto L_0x035f;
                    case 31: goto L_0x035f;
                    case 32: goto L_0x035f;
                    case 33: goto L_0x035f;
                    case 34: goto L_0x035b;
                    case 35: goto L_0x035b;
                    case 36: goto L_0x035b;
                    case 37: goto L_0x035b;
                    case 38: goto L_0x0357;
                    case 39: goto L_0x0357;
                    case 40: goto L_0x0357;
                    case 41: goto L_0x0357;
                    case 42: goto L_0x02e6;
                    case 43: goto L_0x02e6;
                    case 44: goto L_0x02e6;
                    case 45: goto L_0x02e6;
                    case 46: goto L_0x02de;
                    case 47: goto L_0x02d6;
                    case 48: goto L_0x02ce;
                    case 49: goto L_0x02c6;
                    case 50: goto L_0x0287;
                    case 51: goto L_0x02de;
                    case 52: goto L_0x02de;
                    case 53: goto L_0x02de;
                    case 54: goto L_0x0277;
                    case 55: goto L_0x0267;
                    case 56: goto L_0x0258;
                    case 57: goto L_0x0248;
                    case 58: goto L_0x0238;
                    case 59: goto L_0x0231;
                    case 60: goto L_0x0231;
                    case 61: goto L_0x0231;
                    case 62: goto L_0x0231;
                    case 63: goto L_0x022a;
                    case 64: goto L_0x022a;
                    case 65: goto L_0x022a;
                    case 66: goto L_0x022a;
                    case 67: goto L_0x0223;
                    case 68: goto L_0x0223;
                    case 69: goto L_0x0223;
                    case 70: goto L_0x0223;
                    case 71: goto L_0x021c;
                    case 72: goto L_0x021c;
                    case 73: goto L_0x021c;
                    case 74: goto L_0x021c;
                    case 75: goto L_0x0215;
                    case 76: goto L_0x0215;
                    case 77: goto L_0x0215;
                    case 78: goto L_0x0215;
                    case 79: goto L_0x020a;
                    case 80: goto L_0x020a;
                    case 81: goto L_0x020a;
                    case 82: goto L_0x020a;
                    case 83: goto L_0x020a;
                    case 84: goto L_0x020a;
                    case 85: goto L_0x020a;
                    case 86: goto L_0x020a;
                    case 87: goto L_0x0210;
                    case 88: goto L_0x0205;
                    case 89: goto L_0x01f9;
                    case 90: goto L_0x01e6;
                    case 91: goto L_0x01d3;
                    case 92: goto L_0x01c7;
                    case 93: goto L_0x01b4;
                    case 94: goto L_0x01a1;
                    case 95: goto L_0x0191;
                    case 96: goto L_0x02de;
                    case 97: goto L_0x02d6;
                    case 98: goto L_0x02ce;
                    case 99: goto L_0x02c6;
                    case 100: goto L_0x02de;
                    case 101: goto L_0x02d6;
                    case 102: goto L_0x02ce;
                    case 103: goto L_0x02c6;
                    case 104: goto L_0x02de;
                    case 105: goto L_0x02d6;
                    case 106: goto L_0x02ce;
                    case 107: goto L_0x02c6;
                    case 108: goto L_0x02de;
                    case 109: goto L_0x02d6;
                    case 110: goto L_0x02ce;
                    case 111: goto L_0x02c6;
                    case 112: goto L_0x02de;
                    case 113: goto L_0x02d6;
                    case 114: goto L_0x02ce;
                    case 115: goto L_0x02c6;
                    case 116: goto L_0x02e1;
                    case 117: goto L_0x02d9;
                    case 118: goto L_0x02d1;
                    case 119: goto L_0x02c9;
                    case 120: goto L_0x02de;
                    case 121: goto L_0x02d6;
                    case 122: goto L_0x02de;
                    case 123: goto L_0x02d6;
                    case 124: goto L_0x02de;
                    case 125: goto L_0x02d6;
                    case 126: goto L_0x02de;
                    case 127: goto L_0x02d6;
                    case 128: goto L_0x02de;
                    case 129: goto L_0x02d6;
                    case 130: goto L_0x02de;
                    case 131: goto L_0x02d6;
                    case 132: goto L_0x036a;
                    case 133: goto L_0x02d9;
                    case 134: goto L_0x02d1;
                    case 135: goto L_0x02c9;
                    case 136: goto L_0x02e1;
                    case 137: goto L_0x02d1;
                    case 138: goto L_0x02c9;
                    case 139: goto L_0x02e1;
                    case 140: goto L_0x02d9;
                    case 141: goto L_0x02c9;
                    case 142: goto L_0x02e1;
                    case 143: goto L_0x02d9;
                    case 144: goto L_0x02d1;
                    case 145: goto L_0x02e1;
                    case 146: goto L_0x02e1;
                    case 147: goto L_0x02e1;
                    case 148: goto L_0x02de;
                    case 149: goto L_0x02de;
                    case 150: goto L_0x02de;
                    case 151: goto L_0x02de;
                    case 152: goto L_0x02de;
                    case 153: goto L_0x0210;
                    case 154: goto L_0x0210;
                    case 155: goto L_0x0210;
                    case 156: goto L_0x0210;
                    case 157: goto L_0x0210;
                    case 158: goto L_0x0210;
                    case 159: goto L_0x020d;
                    case 160: goto L_0x020d;
                    case 161: goto L_0x020d;
                    case 162: goto L_0x020d;
                    case 163: goto L_0x020d;
                    case 164: goto L_0x020d;
                    case 165: goto L_0x020d;
                    case 166: goto L_0x020d;
                    case 167: goto L_0x036a;
                    case 168: goto L_0x0016;
                    case 169: goto L_0x0016;
                    case 170: goto L_0x0175;
                    case 171: goto L_0x0016;
                    case 172: goto L_0x0170;
                    case 173: goto L_0x0170;
                    case 174: goto L_0x0170;
                    case 175: goto L_0x0170;
                    case 176: goto L_0x0170;
                    case 177: goto L_0x0170;
                    case 178: goto L_0x0148;
                    case 179: goto L_0x0210;
                    case 180: goto L_0x0145;
                    case 181: goto L_0x020d;
                    case 182: goto L_0x00c5;
                    case 183: goto L_0x00c5;
                    case 184: goto L_0x00c5;
                    case 185: goto L_0x00c5;
                    case 186: goto L_0x0016;
                    case 187: goto L_0x00bc;
                    case 188: goto L_0x0087;
                    case 189: goto L_0x004e;
                    case 190: goto L_0x02e1;
                    case 191: goto L_0x0042;
                    case 192: goto L_0x0031;
                    case 193: goto L_0x02e1;
                    case 194: goto L_0x0210;
                    case 195: goto L_0x0210;
                    case 196: goto L_0x002d;
                    case 197: goto L_0x0016;
                    case 198: goto L_0x0210;
                    case 199: goto L_0x0210;
                    case 200: goto L_0x036a;
                    default: goto L_0x0016;
                }
            L_0x0016:
                java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "bad opcode: "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                r11.<init>(r0)
                throw r11
            L_0x002d:
                r10.wide = r8
                goto L_0x036a
            L_0x0031:
                r10.pop()
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11, r7)
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r11)
                r10.push(r11)
                goto L_0x036a
            L_0x0042:
                int r11 = r10.pop()
                r10.clearStack()
                r10.push(r11)
                goto L_0x036a
            L_0x004e:
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11, r7)
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                java.lang.Object r11 = r1.getConstantData(r11)
                java.lang.String r11 = (java.lang.String) r11
                r10.pop()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "[L"
                r1.append(r2)
                r1.append(r11)
                r11 = 59
                r1.append(r11)
                java.lang.String r11 = r1.toString()
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r11, r1)
                r10.push(r11)
                goto L_0x036a
            L_0x0087:
                r10.pop()
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                byte[] r1 = r1.itsCodeBuffer
                int r11 = r11 + r8
                byte r11 = r1[r11]
                char r11 = org.mozilla.classfile.ClassFileWriter.arrayTypeToName(r11)
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "["
                r2.append(r3)
                r2.append(r11)
                java.lang.String r11 = r2.toString()
                short r11 = r1.addClass(r11)
                short r11 = (short) r11
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r11)
                r10.push(r11)
                goto L_0x036a
            L_0x00bc:
                int r11 = org.mozilla.classfile.TypeInfo.UNINITIALIZED_VARIABLE(r11)
                r10.push(r11)
                goto L_0x036a
            L_0x00c5:
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11, r7)
                org.mozilla.classfile.ClassFileWriter r2 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r2 = r2.itsConstantPool
                java.lang.Object r11 = r2.getConstantData(r11)
                org.mozilla.classfile.FieldOrMethodRef r11 = (org.mozilla.classfile.FieldOrMethodRef) r11
                java.lang.String r2 = r11.getType()
                java.lang.String r11 = r11.getName()
                int r3 = org.mozilla.classfile.ClassFileWriter.sizeOfParameters(r2)
                int r3 = r3 >>> 16
                r5 = 0
            L_0x00e5:
                if (r5 >= r3) goto L_0x00ed
                r10.pop()
                int r5 = r5 + 1
                goto L_0x00e5
            L_0x00ed:
                r3 = 184(0xb8, float:2.58E-43)
                if (r0 == r3) goto L_0x011f
                int r3 = r10.pop()
                int r5 = org.mozilla.classfile.TypeInfo.getTag(r3)
                int r6 = org.mozilla.classfile.TypeInfo.UNINITIALIZED_VARIABLE(r4)
                if (r5 == r6) goto L_0x0101
                if (r5 != r1) goto L_0x011f
            L_0x0101:
                java.lang.String r1 = "<init>"
                boolean r11 = r1.equals(r11)
                if (r11 == 0) goto L_0x0117
                org.mozilla.classfile.ClassFileWriter r11 = org.mozilla.classfile.ClassFileWriter.this
                short r11 = r11.itsThisClassIndex
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r11)
                r10.initializeTypeInfo(r3, r11)
                goto L_0x011f
            L_0x0117:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "bad instance"
                r11.<init>(r0)
                throw r11
            L_0x011f:
                r11 = 41
                int r11 = r2.indexOf(r11)
                int r11 = r11 + r8
                java.lang.String r11 = r2.substring(r11)
                java.lang.String r11 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r11)
                java.lang.String r1 = "V"
                boolean r1 = r11.equals(r1)
                if (r1 != 0) goto L_0x036a
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                int r11 = org.mozilla.classfile.TypeInfo.fromType(r11, r1)
                r10.push(r11)
                goto L_0x036a
            L_0x0145:
                r10.pop()
            L_0x0148:
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11, r7)
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                java.lang.Object r11 = r1.getConstantData(r11)
                org.mozilla.classfile.FieldOrMethodRef r11 = (org.mozilla.classfile.FieldOrMethodRef) r11
                java.lang.String r11 = r11.getType()
                java.lang.String r11 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r11)
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                int r11 = org.mozilla.classfile.TypeInfo.fromType(r11, r1)
                r10.push(r11)
                goto L_0x036a
            L_0x0170:
                r10.clearStack()
                goto L_0x036a
            L_0x0175:
                int r1 = r11 + 1
                int r2 = ~r11
                r2 = r2 & r5
                int r1 = r1 + r2
                int r2 = r1 + 4
                int r2 = r10.getOperand(r2, r6)
                int r3 = r1 + 8
                int r3 = r10.getOperand(r3, r6)
                int r3 = r3 - r2
                int r3 = r3 + r6
                int r3 = r3 * 4
                int r3 = r3 + r1
                int r3 = r3 - r11
                r10.pop()
                goto L_0x036b
            L_0x0191:
                int r11 = r10.pop()
                int r1 = r10.pop()
                r10.push(r11)
                r10.push(r1)
                goto L_0x036a
            L_0x01a1:
                long r1 = r10.pop2()
                long r5 = r10.pop2()
                r10.push2(r1)
                r10.push2(r5)
                r10.push2(r1)
                goto L_0x036a
            L_0x01b4:
                long r1 = r10.pop2()
                int r11 = r10.pop()
                r10.push2(r1)
                r10.push(r11)
                r10.push2(r1)
                goto L_0x036a
            L_0x01c7:
                long r1 = r10.pop2()
                r10.push2(r1)
                r10.push2(r1)
                goto L_0x036a
            L_0x01d3:
                int r11 = r10.pop()
                long r1 = r10.pop2()
                r10.push(r11)
                r10.push2(r1)
                r10.push(r11)
                goto L_0x036a
            L_0x01e6:
                int r11 = r10.pop()
                int r1 = r10.pop()
                r10.push(r11)
                r10.push(r1)
                r10.push(r11)
                goto L_0x036a
            L_0x01f9:
                int r11 = r10.pop()
                r10.push(r11)
                r10.push(r11)
                goto L_0x036a
            L_0x0205:
                r10.pop2()
                goto L_0x036a
            L_0x020a:
                r10.pop()
            L_0x020d:
                r10.pop()
            L_0x0210:
                r10.pop()
                goto L_0x036a
            L_0x0215:
                int r11 = r0 + -75
                r10.executeAStore(r11)
                goto L_0x036a
            L_0x021c:
                int r11 = r0 + -71
                r10.executeStore(r11, r5)
                goto L_0x036a
            L_0x0223:
                int r11 = r0 + -67
                r10.executeStore(r11, r7)
                goto L_0x036a
            L_0x022a:
                int r11 = r0 + -63
                r10.executeStore(r11, r6)
                goto L_0x036a
            L_0x0231:
                int r11 = r0 + -59
                r10.executeStore(r11, r8)
                goto L_0x036a
            L_0x0238:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x023e
                goto L_0x023f
            L_0x023e:
                r7 = 1
            L_0x023f:
                int r11 = r10.getOperand(r11, r7)
                r10.executeAStore(r11)
                goto L_0x036a
            L_0x0248:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x024e
                goto L_0x024f
            L_0x024e:
                r7 = 1
            L_0x024f:
                int r11 = r10.getOperand(r11, r7)
                r10.executeStore(r11, r5)
                goto L_0x036a
            L_0x0258:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x025e
                r8 = 2
            L_0x025e:
                int r11 = r10.getOperand(r11, r8)
                r10.executeStore(r11, r7)
                goto L_0x036a
            L_0x0267:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x026d
                goto L_0x026e
            L_0x026d:
                r7 = 1
            L_0x026e:
                int r11 = r10.getOperand(r11, r7)
                r10.executeStore(r11, r6)
                goto L_0x036a
            L_0x0277:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x027d
                goto L_0x027e
            L_0x027d:
                r7 = 1
            L_0x027e:
                int r11 = r10.getOperand(r11, r7)
                r10.executeStore(r11, r8)
                goto L_0x036a
            L_0x0287:
                r10.pop()
                int r11 = r10.pop()
                int r11 = r11 >>> r3
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                java.lang.Object r11 = r1.getConstantData(r11)
                java.lang.String r11 = (java.lang.String) r11
                char r1 = r11.charAt(r4)
                r2 = 91
                if (r1 != r2) goto L_0x02be
                java.lang.String r11 = r11.substring(r8)
                java.lang.String r11 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r11)
                org.mozilla.classfile.ClassFileWriter r1 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r1 = r1.itsConstantPool
                short r11 = r1.addClass(r11)
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r11)
                r10.push(r11)
                goto L_0x036a
            L_0x02be:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "bad array type"
                r11.<init>(r0)
                throw r11
            L_0x02c6:
                r10.pop()
            L_0x02c9:
                r10.pop()
                goto L_0x0357
            L_0x02ce:
                r10.pop()
            L_0x02d1:
                r10.pop()
                goto L_0x035b
            L_0x02d6:
                r10.pop()
            L_0x02d9:
                r10.pop()
                goto L_0x035f
            L_0x02de:
                r10.pop()
            L_0x02e1:
                r10.pop()
                goto L_0x0363
            L_0x02e6:
                int r11 = r0 + -42
                r10.executeALoad(r11)
                goto L_0x036a
            L_0x02ed:
                int r11 = r11 + r8
                boolean r1 = r10.wide
                if (r1 == 0) goto L_0x02f3
                goto L_0x02f4
            L_0x02f3:
                r7 = 1
            L_0x02f4:
                int r11 = r10.getOperand(r11, r7)
                r10.executeALoad(r11)
                goto L_0x036a
            L_0x02fd:
                r9 = 18
                if (r0 != r9) goto L_0x0307
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11)
                goto L_0x030c
            L_0x0307:
                int r11 = r11 + r8
                int r11 = r10.getOperand(r11, r7)
            L_0x030c:
                org.mozilla.classfile.ClassFileWriter r9 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r9 = r9.itsConstantPool
                byte r11 = r9.getConstantType(r11)
                if (r11 == r5) goto L_0x0353
                if (r11 == r6) goto L_0x034f
                if (r11 == r2) goto L_0x034b
                if (r11 == r1) goto L_0x0347
                if (r11 != r3) goto L_0x0330
                org.mozilla.classfile.ClassFileWriter r11 = org.mozilla.classfile.ClassFileWriter.this
                org.mozilla.classfile.ConstantPool r11 = r11.itsConstantPool
                java.lang.String r1 = "java/lang/String"
                int r11 = org.mozilla.classfile.TypeInfo.OBJECT(r1, r11)
                r10.push(r11)
                goto L_0x036a
            L_0x0330:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "bad const type "
                r1.append(r2)
                r1.append(r11)
                java.lang.String r11 = r1.toString()
                r0.<init>(r11)
                throw r0
            L_0x0347:
                r10.push(r5)
                goto L_0x036a
            L_0x034b:
                r10.push(r6)
                goto L_0x036a
            L_0x034f:
                r10.push(r7)
                goto L_0x036a
            L_0x0353:
                r10.push(r8)
                goto L_0x036a
            L_0x0357:
                r10.push(r5)
                goto L_0x036a
            L_0x035b:
                r10.push(r7)
                goto L_0x036a
            L_0x035f:
                r10.push(r6)
                goto L_0x036a
            L_0x0363:
                r10.push(r8)
                goto L_0x036a
            L_0x0367:
                r10.push(r2)
            L_0x036a:
                r3 = 0
            L_0x036b:
                if (r3 != 0) goto L_0x0373
                boolean r11 = r10.wide
                int r3 = org.mozilla.classfile.ClassFileWriter.opcodeLength(r0, r11)
            L_0x0373:
                boolean r11 = r10.wide
                if (r11 == 0) goto L_0x037d
                r11 = 196(0xc4, float:2.75E-43)
                if (r0 == r11) goto L_0x037d
                r10.wide = r4
            L_0x037d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.StackMapTable.execute(int):int");
        }

        private void executeALoad(int i) {
            int local = getLocal(i);
            int tag = TypeInfo.getTag(local);
            if (tag == 7 || tag == 6 || tag == 8 || tag == 5) {
                push(local);
                return;
            }
            throw new IllegalStateException("bad local variable type: " + local + " at index: " + i);
        }

        private void executeAStore(int i) {
            setLocal(i, pop());
        }

        private void executeStore(int i, int i2) {
            pop();
            setLocal(i, i2);
        }

        private void initializeTypeInfo(int i, int i2) {
            initializeTypeInfo(i, i2, this.locals, this.localsTop);
            initializeTypeInfo(i, i2, this.stack, this.stackTop);
        }

        private void initializeTypeInfo(int i, int i2, int[] iArr, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (iArr[i4] == i) {
                    iArr[i4] = i2;
                }
            }
        }

        private int getLocal(int i) {
            if (i < this.localsTop) {
                return this.locals[i];
            }
            return 0;
        }

        private void setLocal(int i, int i2) {
            int i3 = this.localsTop;
            if (i >= i3) {
                int i4 = i + 1;
                int[] iArr = new int[i4];
                System.arraycopy(this.locals, 0, iArr, 0, i3);
                this.locals = iArr;
                this.localsTop = i4;
            }
            this.locals[i] = i2;
        }

        private void push(int i) {
            int i2 = this.stackTop;
            if (i2 == this.stack.length) {
                int[] iArr = new int[Math.max(i2 * 2, 4)];
                System.arraycopy(this.stack, 0, iArr, 0, this.stackTop);
                this.stack = iArr;
            }
            int[] iArr2 = this.stack;
            int i3 = this.stackTop;
            this.stackTop = i3 + 1;
            iArr2[i3] = i;
        }

        private int pop() {
            int[] iArr = this.stack;
            int i = this.stackTop - 1;
            this.stackTop = i;
            return iArr[i];
        }

        private void push2(long j) {
            push((int) (j & 16777215));
            long j2 = j >>> 32;
            if (j2 != 0) {
                push((int) (j2 & 16777215));
            }
        }

        private long pop2() {
            long pop = (long) pop();
            if (TypeInfo.isTwoWords((int) pop)) {
                return pop;
            }
            return (pop << 32) | ((long) (pop() & ViewCompat.MEASURED_SIZE_MASK));
        }

        private void clearStack() {
            this.stackTop = 0;
        }

        /* access modifiers changed from: package-private */
        public int computeWriteSize() {
            this.rawStackMap = new byte[getWorstCaseWriteSize()];
            computeRawStackMap();
            return this.rawStackMapTop + 2;
        }

        /* access modifiers changed from: package-private */
        public int write(byte[] bArr, int i) {
            int putInt16 = ClassFileWriter.putInt16(this.superBlocks.length - 1, bArr, ClassFileWriter.putInt32(this.rawStackMapTop + 2, bArr, i));
            System.arraycopy(this.rawStackMap, 0, bArr, putInt16, this.rawStackMapTop);
            return putInt16 + this.rawStackMapTop;
        }

        private void computeRawStackMap() {
            int[] trimmedLocals = this.superBlocks[0].getTrimmedLocals();
            int i = -1;
            int i2 = 1;
            while (true) {
                SuperBlock[] superBlockArr = this.superBlocks;
                if (i2 < superBlockArr.length) {
                    SuperBlock superBlock = superBlockArr[i2];
                    int[] trimmedLocals2 = superBlock.getTrimmedLocals();
                    int[] stack2 = superBlock.getStack();
                    int start = (superBlock.getStart() - i) - 1;
                    if (stack2.length == 0) {
                        int length = trimmedLocals.length > trimmedLocals2.length ? trimmedLocals2.length : trimmedLocals.length;
                        int abs = Math.abs(trimmedLocals.length - trimmedLocals2.length);
                        int i3 = 0;
                        while (i3 < length && trimmedLocals[i3] == trimmedLocals2[i3]) {
                            i3++;
                        }
                        if (i3 == trimmedLocals2.length && abs == 0) {
                            writeSameFrame(trimmedLocals2, start);
                        } else if (i3 == trimmedLocals2.length && abs <= 3) {
                            writeChopFrame(abs, start);
                        } else if (i3 != trimmedLocals.length || abs > 3) {
                            writeFullFrame(trimmedLocals2, stack2, start);
                        } else {
                            writeAppendFrame(trimmedLocals2, abs, start);
                        }
                    } else if (stack2.length != 1) {
                        writeFullFrame(trimmedLocals2, stack2, start);
                    } else if (Arrays.equals(trimmedLocals, trimmedLocals2)) {
                        writeSameLocalsOneStackItemFrame(trimmedLocals2, stack2, start);
                    } else {
                        writeFullFrame(trimmedLocals2, stack2, start);
                    }
                    i = superBlock.getStart();
                    i2++;
                    trimmedLocals = trimmedLocals2;
                } else {
                    return;
                }
            }
        }

        private int getWorstCaseWriteSize() {
            return (this.superBlocks.length - 1) * ((ClassFileWriter.this.itsMaxLocals * 3) + 7 + (ClassFileWriter.this.itsMaxStack * 3));
        }

        private void writeSameFrame(int[] iArr, int i) {
            if (i <= 63) {
                byte[] bArr = this.rawStackMap;
                int i2 = this.rawStackMapTop;
                this.rawStackMapTop = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            byte[] bArr2 = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            int i4 = i3 + 1;
            this.rawStackMapTop = i4;
            bArr2[i3] = -5;
            this.rawStackMapTop = ClassFileWriter.putInt16(i, bArr2, i4);
        }

        private void writeSameLocalsOneStackItemFrame(int[] iArr, int[] iArr2, int i) {
            if (i <= 63) {
                byte[] bArr = this.rawStackMap;
                int i2 = this.rawStackMapTop;
                this.rawStackMapTop = i2 + 1;
                bArr[i2] = (byte) (i + 64);
            } else {
                byte[] bArr2 = this.rawStackMap;
                int i3 = this.rawStackMapTop;
                int i4 = i3 + 1;
                this.rawStackMapTop = i4;
                bArr2[i3] = -9;
                this.rawStackMapTop = ClassFileWriter.putInt16(i, bArr2, i4);
            }
            writeType(iArr2[0]);
        }

        private void writeFullFrame(int[] iArr, int[] iArr2, int i) {
            byte[] bArr = this.rawStackMap;
            int i2 = this.rawStackMapTop;
            int i3 = i2 + 1;
            this.rawStackMapTop = i3;
            bArr[i2] = -1;
            int putInt16 = ClassFileWriter.putInt16(i, bArr, i3);
            this.rawStackMapTop = putInt16;
            this.rawStackMapTop = ClassFileWriter.putInt16(iArr.length, this.rawStackMap, putInt16);
            int writeTypes = writeTypes(iArr);
            this.rawStackMapTop = writeTypes;
            this.rawStackMapTop = ClassFileWriter.putInt16(iArr2.length, this.rawStackMap, writeTypes);
            this.rawStackMapTop = writeTypes(iArr2);
        }

        private void writeAppendFrame(int[] iArr, int i, int i2) {
            int length = iArr.length - i;
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            int i4 = i3 + 1;
            this.rawStackMapTop = i4;
            bArr[i3] = (byte) (i + 251);
            this.rawStackMapTop = ClassFileWriter.putInt16(i2, bArr, i4);
            this.rawStackMapTop = writeTypes(iArr, length);
        }

        private void writeChopFrame(int i, int i2) {
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            int i4 = i3 + 1;
            this.rawStackMapTop = i4;
            bArr[i3] = (byte) (251 - i);
            this.rawStackMapTop = ClassFileWriter.putInt16(i2, bArr, i4);
        }

        private int writeTypes(int[] iArr) {
            return writeTypes(iArr, 0);
        }

        private int writeTypes(int[] iArr, int i) {
            while (i < iArr.length) {
                this.rawStackMapTop = writeType(iArr[i]);
                i++;
            }
            return this.rawStackMapTop;
        }

        private int writeType(int i) {
            int i2 = i & 255;
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            this.rawStackMapTop = i3 + 1;
            bArr[i3] = (byte) i2;
            if (i2 == 7 || i2 == 8) {
                this.rawStackMapTop = ClassFileWriter.putInt16(i >>> 8, this.rawStackMap, this.rawStackMapTop);
            }
            return this.rawStackMapTop;
        }
    }

    /* access modifiers changed from: private */
    public static char arrayTypeToName(int i) {
        switch (i) {
            case 4:
                return ASCIIPropertyListParser.DATE_APPLE_END_TOKEN;
            case 5:
                return 'C';
            case 6:
                return 'F';
            case 7:
                return ASCIIPropertyListParser.DATA_GSDATE_BEGIN_TOKEN;
            case 8:
                return ASCIIPropertyListParser.DATA_GSBOOL_BEGIN_TOKEN;
            case 9:
                return 'S';
            case 10:
                return ASCIIPropertyListParser.DATA_GSINT_BEGIN_TOKEN;
            case 11:
                return 'J';
            default:
                throw new IllegalArgumentException("bad operand");
        }
    }

    private static String classDescriptorToInternalName(String str) {
        return str.substring(1, str.length() - 1);
    }

    /* access modifiers changed from: private */
    public static String descriptorToInternalName(String str) {
        char charAt = str.charAt(0);
        if (charAt == 'F') {
            return str;
        }
        if (charAt == 'L') {
            return classDescriptorToInternalName(str);
        }
        if (charAt == 'S' || charAt == 'V' || charAt == 'I' || charAt == 'J' || charAt == 'Z' || charAt == '[') {
            return str;
        }
        switch (charAt) {
            case 'B':
            case 'C':
            case 'D':
                return str;
            default:
                throw new IllegalArgumentException("bad descriptor:" + str);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int[] createInitialLocals() {
        /*
            r10 = this;
            short r0 = r10.itsMaxLocals
            int[] r0 = new int[r0]
            org.mozilla.classfile.ClassFileMethod r1 = r10.itsCurrentMethod
            short r1 = r1.getFlags()
            r1 = r1 & 8
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x002c
            org.mozilla.classfile.ClassFileMethod r1 = r10.itsCurrentMethod
            java.lang.String r1 = r1.getName()
            java.lang.String r4 = "<init>"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0022
            r1 = 6
            r0[r2] = r1
            goto L_0x002a
        L_0x0022:
            short r1 = r10.itsThisClassIndex
            int r1 = org.mozilla.classfile.TypeInfo.OBJECT(r1)
            r0[r2] = r1
        L_0x002a:
            r1 = 1
            goto L_0x002d
        L_0x002c:
            r1 = 0
        L_0x002d:
            org.mozilla.classfile.ClassFileMethod r4 = r10.itsCurrentMethod
            java.lang.String r4 = r4.getType()
            r5 = 40
            int r5 = r4.indexOf(r5)
            r6 = 41
            int r6 = r4.indexOf(r6)
            if (r5 != 0) goto L_0x00ae
            if (r6 < 0) goto L_0x00ae
            int r5 = r5 + r3
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
        L_0x0049:
            if (r5 >= r6) goto L_0x00ad
            char r8 = r4.charAt(r5)
            r9 = 70
            if (r8 == r9) goto L_0x0085
            r9 = 76
            if (r8 == r9) goto L_0x0075
            r9 = 83
            if (r8 == r9) goto L_0x0085
            r9 = 73
            if (r8 == r9) goto L_0x0085
            r9 = 74
            if (r8 == r9) goto L_0x0085
            r9 = 90
            if (r8 == r9) goto L_0x0085
            r9 = 91
            if (r8 == r9) goto L_0x006f
            switch(r8) {
                case 66: goto L_0x0085;
                case 67: goto L_0x0085;
                case 68: goto L_0x0085;
                default: goto L_0x006e;
            }
        L_0x006e:
            goto L_0x008e
        L_0x006f:
            r7.append(r9)
            int r5 = r5 + 1
            goto L_0x0049
        L_0x0075:
            r8 = 59
            int r8 = r4.indexOf(r8, r5)
            int r8 = r8 + r3
            java.lang.String r5 = r4.substring(r5, r8)
            r7.append(r5)
            r5 = r8
            goto L_0x008e
        L_0x0085:
            char r8 = r4.charAt(r5)
            r7.append(r8)
            int r5 = r5 + 1
        L_0x008e:
            java.lang.String r8 = r7.toString()
            java.lang.String r8 = descriptorToInternalName(r8)
            org.mozilla.classfile.ConstantPool r9 = r10.itsConstantPool
            int r8 = org.mozilla.classfile.TypeInfo.fromType(r8, r9)
            int r9 = r1 + 1
            r0[r1] = r8
            boolean r1 = org.mozilla.classfile.TypeInfo.isTwoWords(r8)
            if (r1 == 0) goto L_0x00a8
            int r9 = r9 + 1
        L_0x00a8:
            r1 = r9
            r7.setLength(r2)
            goto L_0x0049
        L_0x00ad:
            return r0
        L_0x00ae:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "bad method type"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.createInitialLocals():int[]");
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    private int getWriteSize() {
        if (this.itsSourceFileNameIndex != 0) {
            this.itsConstantPool.addUtf8("SourceFile");
        }
        int writeSize = 8 + this.itsConstantPool.getWriteSize() + 2 + 2 + 2 + 2 + (this.itsInterfaces.size() * 2) + 2;
        for (int i = 0; i < this.itsFields.size(); i++) {
            writeSize += ((ClassFileField) this.itsFields.get(i)).getWriteSize();
        }
        int i2 = writeSize + 2;
        for (int i3 = 0; i3 < this.itsMethods.size(); i3++) {
            i2 += ((ClassFileMethod) this.itsMethods.get(i3)).getWriteSize();
        }
        if (this.itsSourceFileNameIndex != 0) {
            i2 = i2 + 2 + 2 + 4;
        }
        return i2 + 2;
    }

    public byte[] toByteArray() {
        int i;
        int writeSize = getWriteSize();
        byte[] bArr = new byte[writeSize];
        short addUtf8 = this.itsSourceFileNameIndex != 0 ? this.itsConstantPool.addUtf8("SourceFile") : 0;
        int putInt16 = putInt16(this.itsInterfaces.size(), bArr, putInt16(this.itsSuperClassIndex, bArr, putInt16(this.itsThisClassIndex, bArr, putInt16(this.itsFlags, bArr, this.itsConstantPool.write(bArr, putInt16(MajorVersion, bArr, putInt16(MinorVersion, bArr, putInt32(FileHeaderConstant, bArr, 0))))))));
        for (int i2 = 0; i2 < this.itsInterfaces.size(); i2++) {
            putInt16 = putInt16(((Short) this.itsInterfaces.get(i2)).shortValue(), bArr, putInt16);
        }
        int putInt162 = putInt16(this.itsFields.size(), bArr, putInt16);
        for (int i3 = 0; i3 < this.itsFields.size(); i3++) {
            putInt162 = ((ClassFileField) this.itsFields.get(i3)).write(bArr, putInt162);
        }
        int putInt163 = putInt16(this.itsMethods.size(), bArr, putInt162);
        for (int i4 = 0; i4 < this.itsMethods.size(); i4++) {
            putInt163 = ((ClassFileMethod) this.itsMethods.get(i4)).write(bArr, putInt163);
        }
        if (this.itsSourceFileNameIndex != 0) {
            i = putInt16(this.itsSourceFileNameIndex, bArr, putInt32(2, bArr, putInt16(addUtf8, bArr, putInt16(1, bArr, putInt163))));
        } else {
            i = putInt16(0, bArr, putInt163);
        }
        if (i == writeSize) {
            return bArr;
        }
        throw new RuntimeException();
    }

    static int putInt64(long j, byte[] bArr, int i) {
        return putInt32((int) j, bArr, putInt32((int) (j >>> 32), bArr, i));
    }

    private static void badStack(int i) {
        String str;
        if (i < 0) {
            str = "Stack underflow: " + i;
        } else {
            str = "Too big stack: " + i;
        }
        throw new IllegalStateException(str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int sizeOfParameters(java.lang.String r15) {
        /*
            int r0 = r15.length()
            r1 = 41
            int r1 = r15.lastIndexOf(r1)
            r2 = 3
            if (r2 > r0) goto L_0x00b2
            r2 = 0
            char r3 = r15.charAt(r2)
            r4 = 40
            if (r3 != r4) goto L_0x00b2
            r3 = 1
            if (r3 > r1) goto L_0x00b2
            int r4 = r1 + 1
            if (r4 >= r0) goto L_0x00b2
            r0 = 1
            r5 = 0
            r6 = 0
        L_0x0020:
            r7 = 91
            r8 = 90
            r9 = 74
            r10 = 73
            r11 = 83
            r12 = 76
            r13 = 70
            if (r0 == r1) goto L_0x0084
            char r14 = r15.charAt(r0)
            if (r14 == r13) goto L_0x007d
            if (r14 == r12) goto L_0x0069
            if (r14 == r11) goto L_0x007d
            if (r14 == r10) goto L_0x007d
            if (r14 == r9) goto L_0x0066
            if (r14 == r8) goto L_0x007d
            if (r14 == r7) goto L_0x0047
            switch(r14) {
                case 66: goto L_0x007d;
                case 67: goto L_0x007d;
                case 68: goto L_0x0066;
                default: goto L_0x0045;
            }
        L_0x0045:
            r0 = 0
            goto L_0x0085
        L_0x0047:
            int r0 = r0 + 1
            char r14 = r15.charAt(r0)
        L_0x004d:
            if (r14 != r7) goto L_0x0056
            int r0 = r0 + 1
            char r14 = r15.charAt(r0)
            goto L_0x004d
        L_0x0056:
            if (r14 == r13) goto L_0x007d
            if (r14 == r12) goto L_0x0069
            if (r14 == r11) goto L_0x007d
            if (r14 == r8) goto L_0x007d
            if (r14 == r10) goto L_0x007d
            if (r14 == r9) goto L_0x007d
            switch(r14) {
                case 66: goto L_0x007d;
                case 67: goto L_0x007d;
                case 68: goto L_0x007d;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x0045
        L_0x0066:
            int r5 = r5 + -1
            goto L_0x007d
        L_0x0069:
            int r5 = r5 + -1
            int r6 = r6 + 1
            int r0 = r0 + r3
            r14 = 59
            int r14 = r15.indexOf(r14, r0)
            int r0 = r0 + r3
            if (r0 > r14) goto L_0x0045
            if (r14 < r1) goto L_0x007a
            goto L_0x0045
        L_0x007a:
            int r0 = r14 + 1
            goto L_0x0020
        L_0x007d:
            int r5 = r5 + -1
            int r6 = r6 + 1
            int r0 = r0 + 1
            goto L_0x0020
        L_0x0084:
            r0 = 1
        L_0x0085:
            if (r0 == 0) goto L_0x00b2
            char r1 = r15.charAt(r4)
            if (r1 == r13) goto L_0x00a6
            if (r1 == r12) goto L_0x00a6
            if (r1 == r11) goto L_0x00a6
            r4 = 86
            if (r1 == r4) goto L_0x00a4
            if (r1 == r10) goto L_0x00a6
            if (r1 == r9) goto L_0x00a1
            if (r1 == r8) goto L_0x00a6
            if (r1 == r7) goto L_0x00a6
            switch(r1) {
                case 66: goto L_0x00a6;
                case 67: goto L_0x00a6;
                case 68: goto L_0x00a1;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            goto L_0x00a8
        L_0x00a1:
            int r5 = r5 + 1
            goto L_0x00a6
        L_0x00a4:
            r2 = r0
            goto L_0x00a8
        L_0x00a6:
            int r5 = r5 + r3
            goto L_0x00a4
        L_0x00a8:
            if (r2 == 0) goto L_0x00b2
            int r15 = r6 << 16
            r0 = 65535(0xffff, float:9.1834E-41)
            r0 = r0 & r5
            r15 = r15 | r0
            return r15
        L_0x00b2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Bad parameter signature: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.sizeOfParameters(java.lang.String):int");
    }

    static int putInt16(int i, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        return i2 + 2;
    }

    static int putInt32(int i, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        return i2 + 4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        return 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int opcodeLength(int r3, boolean r4) {
        /*
            r0 = 254(0xfe, float:3.56E-43)
            if (r3 == r0) goto L_0x0039
            r0 = 255(0xff, float:3.57E-43)
            if (r3 == r0) goto L_0x0039
            r0 = 5
            r1 = 2
            r2 = 3
            switch(r3) {
                case 0: goto L_0x0039;
                case 1: goto L_0x0039;
                case 2: goto L_0x0039;
                case 3: goto L_0x0039;
                case 4: goto L_0x0039;
                case 5: goto L_0x0039;
                case 6: goto L_0x0039;
                case 7: goto L_0x0039;
                case 8: goto L_0x0039;
                case 9: goto L_0x0039;
                case 10: goto L_0x0039;
                case 11: goto L_0x0039;
                case 12: goto L_0x0039;
                case 13: goto L_0x0039;
                case 14: goto L_0x0039;
                case 15: goto L_0x0039;
                case 16: goto L_0x0038;
                case 17: goto L_0x0037;
                case 18: goto L_0x0038;
                case 19: goto L_0x0037;
                case 20: goto L_0x0037;
                case 21: goto L_0x0033;
                case 22: goto L_0x0033;
                case 23: goto L_0x0033;
                case 24: goto L_0x0033;
                case 25: goto L_0x0033;
                case 26: goto L_0x0039;
                case 27: goto L_0x0039;
                case 28: goto L_0x0039;
                case 29: goto L_0x0039;
                case 30: goto L_0x0039;
                case 31: goto L_0x0039;
                case 32: goto L_0x0039;
                case 33: goto L_0x0039;
                case 34: goto L_0x0039;
                case 35: goto L_0x0039;
                case 36: goto L_0x0039;
                case 37: goto L_0x0039;
                case 38: goto L_0x0039;
                case 39: goto L_0x0039;
                case 40: goto L_0x0039;
                case 41: goto L_0x0039;
                case 42: goto L_0x0039;
                case 43: goto L_0x0039;
                case 44: goto L_0x0039;
                case 45: goto L_0x0039;
                case 46: goto L_0x0039;
                case 47: goto L_0x0039;
                case 48: goto L_0x0039;
                case 49: goto L_0x0039;
                case 50: goto L_0x0039;
                case 51: goto L_0x0039;
                case 52: goto L_0x0039;
                case 53: goto L_0x0039;
                case 54: goto L_0x0033;
                case 55: goto L_0x0033;
                case 56: goto L_0x0033;
                case 57: goto L_0x0033;
                case 58: goto L_0x0033;
                case 59: goto L_0x0039;
                case 60: goto L_0x0039;
                case 61: goto L_0x0039;
                case 62: goto L_0x0039;
                case 63: goto L_0x0039;
                case 64: goto L_0x0039;
                case 65: goto L_0x0039;
                case 66: goto L_0x0039;
                case 67: goto L_0x0039;
                case 68: goto L_0x0039;
                case 69: goto L_0x0039;
                case 70: goto L_0x0039;
                case 71: goto L_0x0039;
                case 72: goto L_0x0039;
                case 73: goto L_0x0039;
                case 74: goto L_0x0039;
                case 75: goto L_0x0039;
                case 76: goto L_0x0039;
                case 77: goto L_0x0039;
                case 78: goto L_0x0039;
                case 79: goto L_0x0039;
                case 80: goto L_0x0039;
                case 81: goto L_0x0039;
                case 82: goto L_0x0039;
                case 83: goto L_0x0039;
                case 84: goto L_0x0039;
                case 85: goto L_0x0039;
                case 86: goto L_0x0039;
                case 87: goto L_0x0039;
                case 88: goto L_0x0039;
                case 89: goto L_0x0039;
                case 90: goto L_0x0039;
                case 91: goto L_0x0039;
                case 92: goto L_0x0039;
                case 93: goto L_0x0039;
                case 94: goto L_0x0039;
                case 95: goto L_0x0039;
                case 96: goto L_0x0039;
                case 97: goto L_0x0039;
                case 98: goto L_0x0039;
                case 99: goto L_0x0039;
                case 100: goto L_0x0039;
                case 101: goto L_0x0039;
                case 102: goto L_0x0039;
                case 103: goto L_0x0039;
                case 104: goto L_0x0039;
                case 105: goto L_0x0039;
                case 106: goto L_0x0039;
                case 107: goto L_0x0039;
                case 108: goto L_0x0039;
                case 109: goto L_0x0039;
                case 110: goto L_0x0039;
                case 111: goto L_0x0039;
                case 112: goto L_0x0039;
                case 113: goto L_0x0039;
                case 114: goto L_0x0039;
                case 115: goto L_0x0039;
                case 116: goto L_0x0039;
                case 117: goto L_0x0039;
                case 118: goto L_0x0039;
                case 119: goto L_0x0039;
                case 120: goto L_0x0039;
                case 121: goto L_0x0039;
                case 122: goto L_0x0039;
                case 123: goto L_0x0039;
                case 124: goto L_0x0039;
                case 125: goto L_0x0039;
                case 126: goto L_0x0039;
                case 127: goto L_0x0039;
                case 128: goto L_0x0039;
                case 129: goto L_0x0039;
                case 130: goto L_0x0039;
                case 131: goto L_0x0039;
                case 132: goto L_0x002e;
                case 133: goto L_0x0039;
                case 134: goto L_0x0039;
                case 135: goto L_0x0039;
                case 136: goto L_0x0039;
                case 137: goto L_0x0039;
                case 138: goto L_0x0039;
                case 139: goto L_0x0039;
                case 140: goto L_0x0039;
                case 141: goto L_0x0039;
                case 142: goto L_0x0039;
                case 143: goto L_0x0039;
                case 144: goto L_0x0039;
                case 145: goto L_0x0039;
                case 146: goto L_0x0039;
                case 147: goto L_0x0039;
                case 148: goto L_0x0039;
                case 149: goto L_0x0039;
                case 150: goto L_0x0039;
                case 151: goto L_0x0039;
                case 152: goto L_0x0039;
                case 153: goto L_0x0037;
                case 154: goto L_0x0037;
                case 155: goto L_0x0037;
                case 156: goto L_0x0037;
                case 157: goto L_0x0037;
                case 158: goto L_0x0037;
                case 159: goto L_0x0037;
                case 160: goto L_0x0037;
                case 161: goto L_0x0037;
                case 162: goto L_0x0037;
                case 163: goto L_0x0037;
                case 164: goto L_0x0037;
                case 165: goto L_0x0037;
                case 166: goto L_0x0037;
                case 167: goto L_0x0037;
                case 168: goto L_0x0037;
                case 169: goto L_0x0033;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r3) {
                case 172: goto L_0x0039;
                case 173: goto L_0x0039;
                case 174: goto L_0x0039;
                case 175: goto L_0x0039;
                case 176: goto L_0x0039;
                case 177: goto L_0x0039;
                case 178: goto L_0x0037;
                case 179: goto L_0x0037;
                case 180: goto L_0x0037;
                case 181: goto L_0x0037;
                case 182: goto L_0x0037;
                case 183: goto L_0x0037;
                case 184: goto L_0x0037;
                case 185: goto L_0x002d;
                default: goto L_0x0011;
            }
        L_0x0011:
            switch(r3) {
                case 187: goto L_0x0037;
                case 188: goto L_0x0038;
                case 189: goto L_0x0037;
                case 190: goto L_0x0039;
                case 191: goto L_0x0039;
                case 192: goto L_0x0037;
                case 193: goto L_0x0037;
                case 194: goto L_0x0039;
                case 195: goto L_0x0039;
                case 196: goto L_0x0039;
                case 197: goto L_0x002b;
                case 198: goto L_0x0037;
                case 199: goto L_0x0037;
                case 200: goto L_0x002d;
                case 201: goto L_0x002d;
                case 202: goto L_0x0039;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Bad opcode: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            throw r4
        L_0x002b:
            r3 = 4
            return r3
        L_0x002d:
            return r0
        L_0x002e:
            if (r4 == 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r0 = 3
        L_0x0032:
            return r0
        L_0x0033:
            if (r4 == 0) goto L_0x0036
            r1 = 3
        L_0x0036:
            return r1
        L_0x0037:
            return r2
        L_0x0038:
            return r1
        L_0x0039:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.opcodeLength(int, boolean):int");
    }

    static int opcodeCount(int i) {
        if (i == 254 || i == 255) {
            return 0;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
                return 0;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
                return 1;
            case 132:
                return 2;
            case 170:
            case 171:
                return -1;
            default:
                switch (i) {
                    case 187:
                    case 188:
                    case 189:
                    case 192:
                    case 193:
                    case 198:
                    case 199:
                    case 200:
                    case 201:
                        return 1;
                    case 190:
                    case 191:
                    case 194:
                    case 195:
                    case ByteCode.WIDE:
                    case ByteCode.BREAKPOINT:
                        return 0;
                    case 197:
                        return 2;
                    default:
                        throw new IllegalArgumentException("Bad opcode: " + i);
                }
        }
    }

    static int stackChange(int i) {
        if (i == 254 || i == 255) {
            return 0;
        }
        switch (i) {
            case 0:
            case 47:
            case 49:
            case 95:
            case 116:
            case 117:
            case 118:
            case 119:
            case 132:
            case 134:
            case 138:
            case 139:
            case 143:
            case 145:
            case 146:
            case 147:
            case 167:
            case 169:
            case 177:
            case 178:
            case 179:
            case 184:
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 34:
            case 35:
            case 36:
            case 37:
            case 42:
            case 43:
            case 44:
            case 45:
            case 89:
            case 90:
            case 91:
            case 133:
            case 135:
            case 140:
            case 141:
            case 168:
                return 1;
            case 9:
            case 10:
            case 14:
            case 15:
            case 20:
            case 22:
            case 24:
            case 30:
            case 31:
            case 32:
            case 33:
            case 38:
            case 39:
            case 40:
            case 41:
            case 92:
            case 93:
            case 94:
                return 2;
            case 46:
            case 48:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 67:
            case 68:
            case 69:
            case 70:
            case 75:
            case 76:
            case 77:
            case 78:
            case 87:
            case 96:
            case 98:
            case 100:
            case 102:
            case 104:
            case 106:
            case 108:
            case 110:
            case 112:
            case 114:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 128:
            case 130:
            case 136:
            case 137:
            case 142:
            case 144:
            case 149:
            case 150:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 170:
            case 171:
            case 172:
            case 174:
            case 176:
            case 180:
            case 181:
            case 182:
            case 183:
            case 185:
                return -1;
            case 55:
            case 57:
            case 63:
            case 64:
            case 65:
            case 66:
            case 71:
            case 72:
            case 73:
            case 74:
            case 88:
            case 97:
            case 99:
            case 101:
            case 103:
            case 105:
            case 107:
            case 109:
            case 111:
            case 113:
            case 115:
            case 127:
            case 129:
            case 131:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 173:
            case 175:
                return -2;
            case 79:
            case 81:
            case 83:
            case 84:
            case 85:
            case 86:
            case 148:
            case 151:
            case 152:
                return -3;
            case 80:
            case 82:
                return -4;
            default:
                switch (i) {
                    case 187:
                    case 197:
                    case 201:
                        return 1;
                    case 188:
                    case 189:
                    case 190:
                    case 192:
                    case 193:
                    case ByteCode.WIDE:
                    case 200:
                    case ByteCode.BREAKPOINT:
                        return 0;
                    case 191:
                    case 194:
                    case 195:
                    case 198:
                    case 199:
                        return -1;
                    default:
                        throw new IllegalArgumentException("Bad opcode: " + i);
                }
        }
    }

    /* access modifiers changed from: package-private */
    public final char[] getCharBuffer(int i) {
        char[] cArr = this.tmpCharBuffer;
        if (i > cArr.length) {
            int length = cArr.length * 2;
            if (i <= length) {
                i = length;
            }
            this.tmpCharBuffer = new char[i];
        }
        return this.tmpCharBuffer;
    }

    private void addSuperBlockStart(int i) {
        if (GenerateStackMap) {
            int[] iArr = this.itsSuperBlockStarts;
            if (iArr == null) {
                this.itsSuperBlockStarts = new int[4];
            } else {
                int length = iArr.length;
                int i2 = this.itsSuperBlockStartsTop;
                if (length == i2) {
                    int[] iArr2 = new int[(i2 * 2)];
                    System.arraycopy(iArr, 0, iArr2, 0, i2);
                    this.itsSuperBlockStarts = iArr2;
                }
            }
            int[] iArr3 = this.itsSuperBlockStarts;
            int i3 = this.itsSuperBlockStartsTop;
            this.itsSuperBlockStartsTop = i3 + 1;
            iArr3[i3] = i;
        }
    }

    private void finalizeSuperBlockStarts() {
        if (GenerateStackMap) {
            for (int i = 0; i < this.itsExceptionTableTop; i++) {
                addSuperBlockStart((short) getLabelPC(this.itsExceptionTable[i].itsHandlerLabel));
            }
            Arrays.sort(this.itsSuperBlockStarts, 0, this.itsSuperBlockStartsTop);
            int i2 = this.itsSuperBlockStarts[0];
            int i3 = 1;
            for (int i4 = 1; i4 < this.itsSuperBlockStartsTop; i4++) {
                int[] iArr = this.itsSuperBlockStarts;
                int i5 = iArr[i4];
                if (i2 != i5) {
                    if (i3 != i4) {
                        iArr[i3] = i5;
                    }
                    i3++;
                    i2 = i5;
                }
            }
            this.itsSuperBlockStartsTop = i3;
            if (this.itsSuperBlockStarts[i3 - 1] == this.itsCodeBufferTop) {
                this.itsSuperBlockStartsTop = i3 - 1;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d A[SYNTHETIC, Splitter:B:28:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    static {
        /*
            r0 = 0
            r1 = 0
            r2 = 48
            java.lang.Class<org.mozilla.classfile.ClassFileWriter> r3 = org.mozilla.classfile.ClassFileWriter.class
            java.lang.String r4 = "ClassFileWriter.class"
            java.io.InputStream r1 = r3.getResourceAsStream(r4)     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            if (r1 != 0) goto L_0x0014
            java.lang.String r3 = "org/mozilla/classfile/ClassFileWriter.class"
            java.io.InputStream r1 = java.lang.ClassLoader.getSystemResourceAsStream(r3)     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
        L_0x0014:
            r3 = 8
            byte[] r4 = new byte[r3]     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            r5 = 0
        L_0x0019:
            if (r5 >= r3) goto L_0x002b
            int r6 = 8 - r5
            int r6 = r1.read(r4, r5, r6)     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            if (r6 < 0) goto L_0x0025
            int r5 = r5 + r6
            goto L_0x0019
        L_0x0025:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            r3.<init>()     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            throw r3     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
        L_0x002b:
            r5 = 4
            byte r5 = r4[r5]     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            int r5 = r5 << r3
            r6 = 5
            byte r6 = r4[r6]     // Catch:{ Exception -> 0x0061, all -> 0x0053 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5 = r5 | r6
            r6 = 6
            byte r6 = r4[r6]     // Catch:{ Exception -> 0x0062, all -> 0x0051 }
            int r3 = r6 << 8
            r6 = 7
            byte r2 = r4[r6]     // Catch:{ Exception -> 0x0062, all -> 0x0051 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r2 | r3
            MinorVersion = r5
            MajorVersion = r2
            r3 = 50
            if (r2 < r3) goto L_0x0049
            r0 = 1
        L_0x0049:
            GenerateStackMap = r0
            if (r1 == 0) goto L_0x006b
        L_0x004d:
            r1.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x006b
        L_0x0051:
            r3 = move-exception
            goto L_0x0055
        L_0x0053:
            r3 = move-exception
            r5 = 0
        L_0x0055:
            MinorVersion = r5
            MajorVersion = r2
            GenerateStackMap = r0
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            throw r3
        L_0x0061:
            r5 = 0
        L_0x0062:
            MinorVersion = r5
            MajorVersion = r2
            GenerateStackMap = r0
            if (r1 == 0) goto L_0x006b
            goto L_0x004d
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.<clinit>():void");
    }
}
