package org.joda.time.convert;

import net.bytebuddy.pool.TypePool;

class ConverterSet {
    private final Converter[] iConverters;
    private Entry[] iSelectEntries = new Entry[16];

    ConverterSet(Converter[] converterArr) {
        this.iConverters = converterArr;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005e A[EDGE_INSN: B:39:0x005e->B:29:0x005e ?: BREAK  , SYNTHETIC] */
    org.joda.time.convert.Converter select(java.lang.Class<?> r10) throws java.lang.IllegalStateException {
        /*
            r9 = this;
            org.joda.time.convert.ConverterSet$Entry[] r0 = r9.iSelectEntries
            int r1 = r0.length
            r2 = 0
            if (r10 != 0) goto L_0x0007
            goto L_0x001d
        L_0x0007:
            int r3 = r10.hashCode()
            int r4 = r1 + -1
            r3 = r3 & r4
        L_0x000e:
            r4 = r0[r3]
            if (r4 == 0) goto L_0x001f
            java.lang.Class<?> r5 = r4.iType
            if (r5 != r10) goto L_0x0019
            org.joda.time.convert.Converter r10 = r4.iConverter
            return r10
        L_0x0019:
            int r3 = r3 + 1
            if (r3 < r1) goto L_0x000e
        L_0x001d:
            r3 = 0
            goto L_0x000e
        L_0x001f:
            org.joda.time.convert.Converter r4 = selectSlow(r9, r10)
            org.joda.time.convert.ConverterSet$Entry r5 = new org.joda.time.convert.ConverterSet$Entry
            r5.<init>(r10, r4)
            java.lang.Object r10 = r0.clone()
            org.joda.time.convert.ConverterSet$Entry[] r10 = (org.joda.time.convert.ConverterSet.Entry[]) r10
            org.joda.time.convert.ConverterSet$Entry[] r10 = (org.joda.time.convert.ConverterSet.Entry[]) r10
            r10[r3] = r5
            r0 = 0
        L_0x0033:
            if (r0 >= r1) goto L_0x003f
            r3 = r10[r0]
            if (r3 != 0) goto L_0x003c
            r9.iSelectEntries = r10
            return r4
        L_0x003c:
            int r0 = r0 + 1
            goto L_0x0033
        L_0x003f:
            int r0 = r1 << 1
            org.joda.time.convert.ConverterSet$Entry[] r3 = new org.joda.time.convert.ConverterSet.Entry[r0]
            r5 = 0
        L_0x0044:
            if (r5 >= r1) goto L_0x0063
            r6 = r10[r5]
            java.lang.Class<?> r7 = r6.iType
            if (r7 != 0) goto L_0x004d
            goto L_0x005c
        L_0x004d:
            int r7 = r7.hashCode()
            int r8 = r0 + -1
            r7 = r7 & r8
        L_0x0054:
            r8 = r3[r7]
            if (r8 == 0) goto L_0x005e
            int r7 = r7 + 1
            if (r7 < r0) goto L_0x0054
        L_0x005c:
            r7 = 0
            goto L_0x0054
        L_0x005e:
            r3[r7] = r6
            int r5 = r5 + 1
            goto L_0x0044
        L_0x0063:
            r9.iSelectEntries = r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.convert.ConverterSet.select(java.lang.Class):org.joda.time.convert.Converter");
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.iConverters.length;
    }

    /* access modifiers changed from: package-private */
    public void copyInto(Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        System.arraycopy(converterArr2, 0, converterArr, 0, converterArr2.length);
    }

    /* access modifiers changed from: package-private */
    public ConverterSet add(Converter converter, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        int i = 0;
        while (i < length) {
            Converter converter2 = converterArr2[i];
            if (converter.equals(converter2)) {
                if (converterArr != null) {
                    converterArr[0] = null;
                }
                return this;
            } else if (converter.getSupportedType() == converter2.getSupportedType()) {
                Converter[] converterArr3 = new Converter[length];
                for (int i2 = 0; i2 < length; i2++) {
                    if (i2 != i) {
                        converterArr3[i2] = converterArr2[i2];
                    } else {
                        converterArr3[i2] = converter;
                    }
                }
                if (converterArr != null) {
                    converterArr[0] = converter2;
                }
                return new ConverterSet(converterArr3);
            } else {
                i++;
            }
        }
        Converter[] converterArr4 = new Converter[(length + 1)];
        System.arraycopy(converterArr2, 0, converterArr4, 0, length);
        converterArr4[length] = converter;
        if (converterArr != null) {
            converterArr[0] = null;
        }
        return new ConverterSet(converterArr4);
    }

    /* access modifiers changed from: package-private */
    public ConverterSet remove(Converter converter, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        for (int i = 0; i < length; i++) {
            if (converter.equals(converterArr2[i])) {
                return remove(i, converterArr);
            }
        }
        if (converterArr != null) {
            converterArr[0] = null;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public ConverterSet remove(int i, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        if (i < length) {
            if (converterArr != null) {
                converterArr[0] = converterArr2[i];
            }
            Converter[] converterArr3 = new Converter[(length - 1)];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 != i) {
                    converterArr3[i2] = converterArr2[i3];
                    i2++;
                }
            }
            return new ConverterSet(converterArr3);
        }
        throw new IndexOutOfBoundsException();
    }

    private static Converter selectSlow(ConverterSet converterSet, Class<?> cls) {
        String str;
        Converter[] converterArr = converterSet.iConverters;
        int length = converterArr.length;
        int i = length;
        while (true) {
            length--;
            if (length >= 0) {
                Converter converter = converterArr[length];
                Class<?> supportedType = converter.getSupportedType();
                if (supportedType == cls) {
                    return converter;
                }
                if (supportedType == null || (cls != null && !supportedType.isAssignableFrom(cls))) {
                    converterSet = converterSet.remove(length, (Converter[]) null);
                    converterArr = converterSet.iConverters;
                    i = converterArr.length;
                }
            } else if (cls == null || i == 0) {
                return null;
            } else {
                if (i == 1) {
                    return converterArr[0];
                }
                int i2 = i;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    Class<?> supportedType2 = converterArr[i].getSupportedType();
                    int i3 = i2;
                    while (true) {
                        i2--;
                        if (i2 < 0) {
                            break;
                        } else if (i2 != i && converterArr[i2].getSupportedType().isAssignableFrom(supportedType2)) {
                            converterSet = converterSet.remove(i2, (Converter[]) null);
                            converterArr = converterSet.iConverters;
                            i3 = converterArr.length;
                            i = i3 - 1;
                        }
                    }
                    i2 = i3;
                }
                if (i2 == 1) {
                    return converterArr[0];
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to find best converter for type \"");
                sb.append(cls.getName());
                sb.append("\" from remaining set: ");
                for (int i4 = 0; i4 < i2; i4++) {
                    Converter converter2 = converterArr[i4];
                    Class<?> supportedType3 = converter2.getSupportedType();
                    sb.append(converter2.getClass().getName());
                    sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                    if (supportedType3 == null) {
                        str = null;
                    } else {
                        str = supportedType3.getName();
                    }
                    sb.append(str);
                    sb.append("], ");
                }
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    static class Entry {
        final Converter iConverter;
        final Class<?> iType;

        Entry(Class<?> cls, Converter converter) {
            this.iType = cls;
            this.iConverter = converter;
        }
    }
}
