package org.objenesis.instantiator.basic;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

@Instantiator(Typology.STANDARD)
public class ProxyingInstantiator<T> implements ObjectInstantiator<T> {
    private static final byte[] CODE;
    private static final int CODE_ATTRIBUTE_LENGTH;
    private static int CONSTANT_POOL_COUNT = 9;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_UTF8_CLASS = 7;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final String SUFFIX = "$$$Objenesis";
    private final Class<?> newType;

    static {
        byte[] bArr = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_return};
        CODE = bArr;
        CODE_ATTRIBUTE_LENGTH = bArr.length + 12;
    }

    public ProxyingInstantiator(Class<T> cls) {
        byte[] writeExtendingClass = writeExtendingClass(cls, SUFFIX);
        try {
            this.newType = ClassDefinitionUtils.defineClass(cls.getName() + SUFFIX, writeExtendingClass, cls.getClassLoader());
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }

    public T newInstance() {
        try {
            return this.newType.newInstance();
        } catch (InstantiationException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException((Throwable) e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ea A[SYNTHETIC, Splitter:B:22:0x00ea] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] writeExtendingClass(java.lang.Class<?> r5, java.lang.String r6) {
        /*
            java.lang.String r5 = r5.getName()
            java.lang.String r5 = org.objenesis.instantiator.util.ClassDefinitionUtils.classNameToInternalClassName(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.<init>(r1)
            r1 = 0
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00e1 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x00e1 }
            byte[] r1 = org.objenesis.instantiator.util.ClassDefinitionUtils.MAGIC     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.write(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            byte[] r1 = org.objenesis.instantiator.util.ClassDefinitionUtils.VERSION     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.write(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            int r1 = CONSTANT_POOL_COUNT     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r1 = 7
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r1 = 8
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r1 = 1
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r3 = "<init>"
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r3 = "()V"
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r3 = "Code"
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r3.<init>()     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r4 = "L"
            r3.append(r4)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r3.append(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r4 = ";"
            r3.append(r4)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeUTF(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeByte(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeUTF(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r5 = 33
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r5 = 2
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r5 = 0
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r6 = 3
            r2.writeShort(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r6 = 4
            r2.writeShort(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r6 = 5
            r2.writeShort(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            int r6 = CODE_ATTRIBUTE_LENGTH     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeInt(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r1)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            byte[] r6 = CODE     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            int r6 = r6.length     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeInt(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            byte[] r6 = CODE     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.write(r6)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.writeShort(r5)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r2.close()     // Catch:{ IOException -> 0x00d2 }
            byte[] r5 = r0.toByteArray()
            return r5
        L_0x00d2:
            r5 = move-exception
            org.objenesis.ObjenesisException r6 = new org.objenesis.ObjenesisException
            r6.<init>((java.lang.Throwable) r5)
            throw r6
        L_0x00d9:
            r5 = move-exception
            r1 = r2
            goto L_0x00e8
        L_0x00dc:
            r5 = move-exception
            r1 = r2
            goto L_0x00e2
        L_0x00df:
            r5 = move-exception
            goto L_0x00e8
        L_0x00e1:
            r5 = move-exception
        L_0x00e2:
            org.objenesis.ObjenesisException r6 = new org.objenesis.ObjenesisException     // Catch:{ all -> 0x00df }
            r6.<init>((java.lang.Throwable) r5)     // Catch:{ all -> 0x00df }
            throw r6     // Catch:{ all -> 0x00df }
        L_0x00e8:
            if (r1 == 0) goto L_0x00f5
            r1.close()     // Catch:{ IOException -> 0x00ee }
            goto L_0x00f5
        L_0x00ee:
            r5 = move-exception
            org.objenesis.ObjenesisException r6 = new org.objenesis.ObjenesisException
            r6.<init>((java.lang.Throwable) r5)
            throw r6
        L_0x00f5:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objenesis.instantiator.basic.ProxyingInstantiator.writeExtendingClass(java.lang.Class, java.lang.String):byte[]");
    }
}
