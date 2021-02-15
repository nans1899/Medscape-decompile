package org.objenesis.instantiator.sun;

import com.google.common.base.Ascii;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

@Instantiator(Typology.STANDARD)
public class MagicInstantiator<T> implements ObjectInstantiator<T> {
    private static int CONSTANT_POOL_COUNT = 19;
    private static final byte[] CONSTRUCTOR_CODE;
    private static final int CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_INTERFACE = 9;
    private static final int INDEX_CLASS_OBJECT = 14;
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_CLASS_TYPE = 17;
    private static final int INDEX_METHODREF_OBJECT_CONSTRUCTOR = 13;
    private static final int INDEX_NAMEANDTYPE_DEFAULT_CONSTRUCTOR = 16;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_INSTANTIATOR_CLASS = 7;
    private static final int INDEX_UTF8_INTERFACE = 10;
    private static final int INDEX_UTF8_NEWINSTANCE_DESC = 12;
    private static final int INDEX_UTF8_NEWINSTANCE_NAME = 11;
    private static final int INDEX_UTF8_OBJECT = 15;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final int INDEX_UTF8_TYPE = 18;
    private static final String MAGIC_ACCESSOR = getMagicClass();
    private static final byte[] NEWINSTANCE_CODE;
    private static final int NEWINSTANCE_CODE_ATTRIBUTE_LENGTH;
    private ObjectInstantiator<T> instantiator;

    static {
        byte[] bArr = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_invokespecial, 0, Ascii.CR, ClassDefinitionUtils.OPS_return};
        CONSTRUCTOR_CODE = bArr;
        CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH = bArr.length + 12;
        byte[] bArr2 = {ClassDefinitionUtils.OPS_new, 0, 17, ClassDefinitionUtils.OPS_dup, ClassDefinitionUtils.OPS_invokespecial, 0, Ascii.CR, ClassDefinitionUtils.OPS_areturn};
        NEWINSTANCE_CODE = bArr2;
        NEWINSTANCE_CODE_ATTRIBUTE_LENGTH = bArr2.length + 12;
    }

    public MagicInstantiator(Class<T> cls) {
        this.instantiator = newInstantiatorOf(cls);
    }

    public ObjectInstantiator<T> getInstantiator() {
        return this.instantiator;
    }

    private <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        String str = getClass().getName() + "$$$" + cls.getSimpleName();
        Class existingClass = ClassDefinitionUtils.getExistingClass(getClass().getClassLoader(), str);
        if (existingClass == null) {
            try {
                existingClass = ClassDefinitionUtils.defineClass(str, writeExtendingClass(cls, str), getClass().getClassLoader());
            } catch (Exception e) {
                throw new ObjenesisException((Throwable) e);
            }
        }
        try {
            return (ObjectInstantiator) existingClass.newInstance();
        } catch (InstantiationException e2) {
            throw new ObjenesisException((Throwable) e2);
        } catch (IllegalAccessException e3) {
            throw new ObjenesisException((Throwable) e3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0175 A[SYNTHETIC, Splitter:B:22:0x0175] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] writeExtendingClass(java.lang.Class<?> r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r9 = org.objenesis.instantiator.util.ClassDefinitionUtils.classNameToInternalClassName(r9)
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.<init>(r1)
            r1 = 0
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x016c }
            r2.<init>(r0)     // Catch:{ IOException -> 0x016c }
            byte[] r1 = org.objenesis.instantiator.util.ClassDefinitionUtils.MAGIC     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.write(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            byte[] r1 = org.objenesis.instantiator.util.ClassDefinitionUtils.VERSION     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.write(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            int r1 = CONSTANT_POOL_COUNT     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r1 = 7
            r2.writeByte(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r3 = 8
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r3 = 1
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = "<init>"
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = "()V"
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = "Code"
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r4.<init>()     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r5 = "L"
            r4.append(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r4.append(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r5 = ";"
            r4.append(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeUTF(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r9 = MAGIC_ACCESSOR     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeUTF(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r9 = 10
            r2.writeShort(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.Class<org.objenesis.instantiator.ObjectInstantiator> r4 = org.objenesis.instantiator.ObjectInstantiator.class
            java.lang.String r4 = r4.getName()     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r5 = 46
            r6 = 47
            java.lang.String r4 = r4.replace(r5, r6)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = "newInstance"
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r4 = "()Ljava/lang/Object;"
            r2.writeUTF(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r9 = 14
            r2.writeShort(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r9 = 16
            r2.writeShort(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r9 = 15
            r2.writeShort(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r9 = "java/lang/Object"
            r2.writeUTF(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r9 = 12
            r2.writeByte(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r4 = 3
            r2.writeShort(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r5 = 4
            r2.writeShort(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r1 = 18
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeByte(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r8 = r8.getName()     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            java.lang.String r8 = org.objenesis.instantiator.util.ClassDefinitionUtils.classNameToInternalClassName(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeUTF(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r8 = 49
            r2.writeShort(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r8 = 2
            r2.writeShort(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r1 = 9
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r1 = 0
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r4 = 5
            r2.writeShort(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            int r5 = CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeInt(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            byte[] r5 = CONSTRUCTOR_CODE     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            int r5 = r5.length     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeInt(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            byte[] r5 = CONSTRUCTOR_CODE     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.write(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r5 = 11
            r2.writeShort(r5)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r4)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            int r9 = NEWINSTANCE_CODE_ATTRIBUTE_LENGTH     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeInt(r9)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r3)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            byte[] r8 = NEWINSTANCE_CODE     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            int r8 = r8.length     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeInt(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            byte[] r8 = NEWINSTANCE_CODE     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.write(r8)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x0167, all -> 0x0164 }
            r2.close()     // Catch:{ IOException -> 0x015d }
            byte[] r8 = r0.toByteArray()
            return r8
        L_0x015d:
            r8 = move-exception
            org.objenesis.ObjenesisException r9 = new org.objenesis.ObjenesisException
            r9.<init>((java.lang.Throwable) r8)
            throw r9
        L_0x0164:
            r8 = move-exception
            r1 = r2
            goto L_0x0173
        L_0x0167:
            r8 = move-exception
            r1 = r2
            goto L_0x016d
        L_0x016a:
            r8 = move-exception
            goto L_0x0173
        L_0x016c:
            r8 = move-exception
        L_0x016d:
            org.objenesis.ObjenesisException r9 = new org.objenesis.ObjenesisException     // Catch:{ all -> 0x016a }
            r9.<init>((java.lang.Throwable) r8)     // Catch:{ all -> 0x016a }
            throw r9     // Catch:{ all -> 0x016a }
        L_0x0173:
            if (r1 == 0) goto L_0x0180
            r1.close()     // Catch:{ IOException -> 0x0179 }
            goto L_0x0180
        L_0x0179:
            r8 = move-exception
            org.objenesis.ObjenesisException r9 = new org.objenesis.ObjenesisException
            r9.<init>((java.lang.Throwable) r8)
            throw r9
        L_0x0180:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objenesis.instantiator.sun.MagicInstantiator.writeExtendingClass(java.lang.Class, java.lang.String):byte[]");
    }

    public T newInstance() {
        return this.instantiator.newInstance();
    }

    private static String getMagicClass() {
        try {
            Class.forName("sun.reflect.MagicAccessorImpl", false, MagicInstantiator.class.getClassLoader());
            return "sun/reflect/MagicAccessorImpl";
        } catch (ClassNotFoundException unused) {
            return "jdk/internal/reflect/MagicAccessorImpl";
        }
    }
}
