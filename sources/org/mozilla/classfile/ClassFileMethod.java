package org.mozilla.classfile;

/* compiled from: ClassFileWriter */
final class ClassFileMethod {
    private byte[] itsCodeAttribute;
    private short itsFlags;
    private String itsName;
    private short itsNameIndex;
    private String itsType;
    private short itsTypeIndex;

    ClassFileMethod(String str, short s, String str2, short s2, short s3) {
        this.itsName = str;
        this.itsNameIndex = s;
        this.itsType = str2;
        this.itsTypeIndex = s2;
        this.itsFlags = s3;
    }

    /* access modifiers changed from: package-private */
    public void setCodeAttribute(byte[] bArr) {
        this.itsCodeAttribute = bArr;
    }

    /* access modifiers changed from: package-private */
    public int write(byte[] bArr, int i) {
        int putInt16 = ClassFileWriter.putInt16(1, bArr, ClassFileWriter.putInt16(this.itsTypeIndex, bArr, ClassFileWriter.putInt16(this.itsNameIndex, bArr, ClassFileWriter.putInt16(this.itsFlags, bArr, i))));
        byte[] bArr2 = this.itsCodeAttribute;
        System.arraycopy(bArr2, 0, bArr, putInt16, bArr2.length);
        return putInt16 + this.itsCodeAttribute.length;
    }

    /* access modifiers changed from: package-private */
    public int getWriteSize() {
        return this.itsCodeAttribute.length + 8;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.itsName;
    }

    /* access modifiers changed from: package-private */
    public String getType() {
        return this.itsType;
    }

    /* access modifiers changed from: package-private */
    public short getFlags() {
        return this.itsFlags;
    }
}
