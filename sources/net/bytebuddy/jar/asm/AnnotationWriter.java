package net.bytebuddy.jar.asm;

final class AnnotationWriter extends AnnotationVisitor {
    private final ByteVector annotation;
    private AnnotationWriter nextAnnotation;
    private int numElementValuePairs;
    private final int numElementValuePairsOffset;
    private final AnnotationWriter previousAnnotation;
    private final SymbolTable symbolTable;
    private final boolean useNamedValues;

    AnnotationWriter(SymbolTable symbolTable2, boolean z, ByteVector byteVector, AnnotationWriter annotationWriter) {
        super(Opcodes.ASM7);
        this.symbolTable = symbolTable2;
        this.useNamedValues = z;
        this.annotation = byteVector;
        this.numElementValuePairsOffset = byteVector.length == 0 ? -1 : byteVector.length - 2;
        this.previousAnnotation = annotationWriter;
        if (annotationWriter != null) {
            annotationWriter.nextAnnotation = this;
        }
    }

    AnnotationWriter(SymbolTable symbolTable2, ByteVector byteVector, AnnotationWriter annotationWriter) {
        this(symbolTable2, true, byteVector, annotationWriter);
    }

    public void visit(String str, Object obj) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(str));
        }
        if (obj instanceof String) {
            this.annotation.put12(115, this.symbolTable.addConstantUtf8((String) obj));
        } else if (obj instanceof Byte) {
            this.annotation.put12(66, this.symbolTable.addConstantInteger(((Byte) obj).byteValue()).index);
        } else if (obj instanceof Boolean) {
            this.annotation.put12(90, this.symbolTable.addConstantInteger(((Boolean) obj).booleanValue() ? 1 : 0).index);
        } else if (obj instanceof Character) {
            this.annotation.put12(67, this.symbolTable.addConstantInteger(((Character) obj).charValue()).index);
        } else if (obj instanceof Short) {
            this.annotation.put12(83, this.symbolTable.addConstantInteger(((Short) obj).shortValue()).index);
        } else if (obj instanceof Type) {
            this.annotation.put12(99, this.symbolTable.addConstantUtf8(((Type) obj).getDescriptor()));
        } else {
            int i = 0;
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                this.annotation.put12(91, bArr.length);
                int length = bArr.length;
                while (i < length) {
                    this.annotation.put12(66, this.symbolTable.addConstantInteger(bArr[i]).index);
                    i++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                this.annotation.put12(91, zArr.length);
                int length2 = zArr.length;
                while (i < length2) {
                    this.annotation.put12(90, this.symbolTable.addConstantInteger(zArr[i] ? 1 : 0).index);
                    i++;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                this.annotation.put12(91, sArr.length);
                int length3 = sArr.length;
                while (i < length3) {
                    this.annotation.put12(83, this.symbolTable.addConstantInteger(sArr[i]).index);
                    i++;
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                this.annotation.put12(91, cArr.length);
                int length4 = cArr.length;
                while (i < length4) {
                    this.annotation.put12(67, this.symbolTable.addConstantInteger(cArr[i]).index);
                    i++;
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                this.annotation.put12(91, iArr.length);
                int length5 = iArr.length;
                while (i < length5) {
                    this.annotation.put12(73, this.symbolTable.addConstantInteger(iArr[i]).index);
                    i++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                this.annotation.put12(91, jArr.length);
                int length6 = jArr.length;
                while (i < length6) {
                    this.annotation.put12(74, this.symbolTable.addConstantLong(jArr[i]).index);
                    i++;
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                this.annotation.put12(91, fArr.length);
                int length7 = fArr.length;
                while (i < length7) {
                    this.annotation.put12(70, this.symbolTable.addConstantFloat(fArr[i]).index);
                    i++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                this.annotation.put12(91, dArr.length);
                int length8 = dArr.length;
                while (i < length8) {
                    this.annotation.put12(68, this.symbolTable.addConstantDouble(dArr[i]).index);
                    i++;
                }
            } else {
                Symbol addConstant = this.symbolTable.addConstant(obj);
                this.annotation.put12(".s.IFJDCS".charAt(addConstant.tag), addConstant.index);
            }
        }
    }

    public void visitEnum(String str, String str2, String str3) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(str));
        }
        this.annotation.put12(101, this.symbolTable.addConstantUtf8(str2)).putShort(this.symbolTable.addConstantUtf8(str3));
    }

    public AnnotationVisitor visitAnnotation(String str, String str2) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(str));
        }
        this.annotation.put12(64, this.symbolTable.addConstantUtf8(str2)).putShort(0);
        return new AnnotationWriter(this.symbolTable, this.annotation, (AnnotationWriter) null);
    }

    public AnnotationVisitor visitArray(String str) {
        this.numElementValuePairs++;
        if (this.useNamedValues) {
            this.annotation.putShort(this.symbolTable.addConstantUtf8(str));
        }
        this.annotation.put12(91, 0);
        return new AnnotationWriter(this.symbolTable, false, this.annotation, (AnnotationWriter) null);
    }

    public void visitEnd() {
        if (this.numElementValuePairsOffset != -1) {
            byte[] bArr = this.annotation.data;
            int i = this.numElementValuePairsOffset;
            int i2 = this.numElementValuePairs;
            bArr[i] = (byte) (i2 >>> 8);
            bArr[i + 1] = (byte) i2;
        }
    }

    /* access modifiers changed from: package-private */
    public int computeAnnotationsSize(String str) {
        if (str != null) {
            this.symbolTable.addConstantUtf8(str);
        }
        int i = 8;
        for (AnnotationWriter annotationWriter = this; annotationWriter != null; annotationWriter = annotationWriter.previousAnnotation) {
            i += annotationWriter.annotation.length;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void putAnnotations(int i, ByteVector byteVector) {
        int i2 = 2;
        int i3 = 0;
        AnnotationWriter annotationWriter = null;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.previousAnnotation) {
            annotationWriter2.visitEnd();
            i2 += annotationWriter2.annotation.length;
            i3++;
            annotationWriter = annotationWriter2;
        }
        byteVector.putShort(i);
        byteVector.putInt(i2);
        byteVector.putShort(i3);
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.annotation.data, 0, annotationWriter.annotation.length);
            annotationWriter = annotationWriter.nextAnnotation;
        }
    }

    static int computeParameterAnnotationsSize(String str, AnnotationWriter[] annotationWriterArr, int i) {
        int i2;
        int i3 = (i * 2) + 7;
        for (int i4 = 0; i4 < i; i4++) {
            AnnotationWriter annotationWriter = annotationWriterArr[i4];
            if (annotationWriter == null) {
                i2 = 0;
            } else {
                i2 = annotationWriter.computeAnnotationsSize(str) - 8;
            }
            i3 += i2;
        }
        return i3;
    }

    static void putParameterAnnotations(int i, AnnotationWriter[] annotationWriterArr, int i2, ByteVector byteVector) {
        int i3;
        int i4 = (i2 * 2) + 1;
        for (int i5 = 0; i5 < i2; i5++) {
            AnnotationWriter annotationWriter = annotationWriterArr[i5];
            if (annotationWriter == null) {
                i3 = 0;
            } else {
                i3 = annotationWriter.computeAnnotationsSize((String) null) - 8;
            }
            i4 += i3;
        }
        byteVector.putShort(i);
        byteVector.putInt(i4);
        byteVector.putByte(i2);
        for (int i6 = 0; i6 < i2; i6++) {
            AnnotationWriter annotationWriter2 = null;
            int i7 = 0;
            for (AnnotationWriter annotationWriter3 = annotationWriterArr[i6]; annotationWriter3 != null; annotationWriter3 = annotationWriter3.previousAnnotation) {
                annotationWriter3.visitEnd();
                i7++;
                annotationWriter2 = annotationWriter3;
            }
            byteVector.putShort(i7);
            while (annotationWriter2 != null) {
                byteVector.putByteArray(annotationWriter2.annotation.data, 0, annotationWriter2.annotation.length);
                annotationWriter2 = annotationWriter2.nextAnnotation;
            }
        }
    }
}
