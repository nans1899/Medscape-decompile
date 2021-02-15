package net.bytebuddy.jar.asm;

final class SymbolTable {
    private int bootstrapMethodCount;
    private ByteVector bootstrapMethods;
    private String className;
    final ClassWriter classWriter;
    private ByteVector constantPool;
    private int constantPoolCount;
    private Entry[] entries;
    private int entryCount;
    private int majorVersion;
    private final ClassReader sourceClassReader;
    private int typeCount;
    private Entry[] typeTable;

    private static int hash(int i, int i2) {
        return (i + i2) & Integer.MAX_VALUE;
    }

    private static int hash(int i, long j) {
        return (i + ((int) j) + ((int) (j >>> 32))) & Integer.MAX_VALUE;
    }

    SymbolTable(ClassWriter classWriter2) {
        this.classWriter = classWriter2;
        this.sourceClassReader = null;
        this.entries = new Entry[256];
        this.constantPoolCount = 1;
        this.constantPool = new ByteVector();
    }

    SymbolTable(ClassWriter classWriter2, ClassReader classReader) {
        this.classWriter = classWriter2;
        this.sourceClassReader = classReader;
        byte[] bArr = classReader.b;
        int item = classReader.getItem(1) - 1;
        int i = classReader.header - item;
        this.constantPoolCount = classReader.getItemCount();
        ByteVector byteVector = new ByteVector(i);
        this.constantPool = byteVector;
        byteVector.putByteArray(bArr, item, i);
        this.entries = new Entry[(this.constantPoolCount * 2)];
        char[] cArr = new char[classReader.getMaxStringLength()];
        boolean z = false;
        int i2 = 1;
        while (i2 < this.constantPoolCount) {
            int item2 = classReader.getItem(i2);
            byte b = bArr[item2 - 1];
            switch (b) {
                case 1:
                    addConstantUtf8(i2, classReader.readUtf(i2, cArr));
                    break;
                case 3:
                case 4:
                    addConstantIntegerOrFloat(i2, b, classReader.readInt(item2));
                    break;
                case 5:
                case 6:
                    addConstantLongOrDouble(i2, b, classReader.readLong(item2));
                    break;
                case 7:
                case 8:
                case 16:
                case 19:
                case 20:
                    addConstantUtf8Reference(i2, b, classReader.readUTF8(item2, cArr));
                    break;
                case 9:
                case 10:
                case 11:
                    int item3 = classReader.getItem(classReader.readUnsignedShort(item2 + 2));
                    addConstantMemberReference(i2, b, classReader.readClass(item2, cArr), classReader.readUTF8(item3, cArr), classReader.readUTF8(item3 + 2, cArr));
                    break;
                case 12:
                    addConstantNameAndType(i2, classReader.readUTF8(item2, cArr), classReader.readUTF8(item2 + 2, cArr));
                    break;
                case 15:
                    int item4 = classReader.getItem(classReader.readUnsignedShort(item2 + 1));
                    int item5 = classReader.getItem(classReader.readUnsignedShort(item4 + 2));
                    addConstantMethodHandle(i2, classReader.readByte(item2), classReader.readClass(item4, cArr), classReader.readUTF8(item5, cArr), classReader.readUTF8(item5 + 2, cArr));
                    break;
                case 17:
                case 18:
                    int item6 = classReader.getItem(classReader.readUnsignedShort(item2 + 2));
                    addConstantDynamicOrInvokeDynamicReference(b, i2, classReader.readUTF8(item6, cArr), classReader.readUTF8(item6 + 2, cArr), classReader.readUnsignedShort(item2));
                    z = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            i2 += (b == 5 || b == 6) ? 2 : 1;
        }
        if (z) {
            copyBootstrapMethods(classReader, cArr);
        }
    }

    private void copyBootstrapMethods(ClassReader classReader, char[] cArr) {
        byte[] bArr = classReader.b;
        int firstAttributeOffset = classReader.getFirstAttributeOffset();
        int readUnsignedShort = classReader.readUnsignedShort(firstAttributeOffset - 2);
        while (true) {
            if (readUnsignedShort <= 0) {
                break;
            } else if ("BootstrapMethods".equals(classReader.readUTF8(firstAttributeOffset, cArr))) {
                this.bootstrapMethodCount = classReader.readUnsignedShort(firstAttributeOffset + 6);
                break;
            } else {
                firstAttributeOffset += classReader.readInt(firstAttributeOffset + 2) + 6;
                readUnsignedShort--;
            }
        }
        if (this.bootstrapMethodCount > 0) {
            int i = firstAttributeOffset + 8;
            int readInt = classReader.readInt(firstAttributeOffset + 2) - 2;
            ByteVector byteVector = new ByteVector(readInt);
            this.bootstrapMethods = byteVector;
            byteVector.putByteArray(bArr, i, readInt);
            int i2 = i;
            for (int i3 = 0; i3 < this.bootstrapMethodCount; i3++) {
                int i4 = i2 - i;
                int readUnsignedShort2 = classReader.readUnsignedShort(i2);
                int i5 = i2 + 2;
                int readUnsignedShort3 = classReader.readUnsignedShort(i5);
                i2 = i5 + 2;
                int hashCode = classReader.readConst(readUnsignedShort2, cArr).hashCode();
                while (true) {
                    int i6 = readUnsignedShort3 - 1;
                    if (readUnsignedShort3 <= 0) {
                        break;
                    }
                    int readUnsignedShort4 = classReader.readUnsignedShort(i2);
                    i2 += 2;
                    hashCode ^= classReader.readConst(readUnsignedShort4, cArr).hashCode();
                    readUnsignedShort3 = i6;
                }
                add(new Entry(i3, 64, (long) i4, hashCode & Integer.MAX_VALUE));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ClassReader getSource() {
        return this.sourceClassReader;
    }

    /* access modifiers changed from: package-private */
    public int getMajorVersion() {
        return this.majorVersion;
    }

    /* access modifiers changed from: package-private */
    public String getClassName() {
        return this.className;
    }

    /* access modifiers changed from: package-private */
    public int setMajorVersionAndClassName(int i, String str) {
        this.majorVersion = i;
        this.className = str;
        return addConstantClass(str).index;
    }

    /* access modifiers changed from: package-private */
    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }

    /* access modifiers changed from: package-private */
    public int getConstantPoolLength() {
        return this.constantPool.length;
    }

    /* access modifiers changed from: package-private */
    public void putConstantPool(ByteVector byteVector) {
        byteVector.putShort(this.constantPoolCount).putByteArray(this.constantPool.data, 0, this.constantPool.length);
    }

    /* access modifiers changed from: package-private */
    public int computeBootstrapMethodsSize() {
        if (this.bootstrapMethods == null) {
            return 0;
        }
        addConstantUtf8("BootstrapMethods");
        return this.bootstrapMethods.length + 8;
    }

    /* access modifiers changed from: package-private */
    public void putBootstrapMethods(ByteVector byteVector) {
        if (this.bootstrapMethods != null) {
            byteVector.putShort(addConstantUtf8("BootstrapMethods")).putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodCount).putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
        }
    }

    private Entry get(int i) {
        Entry[] entryArr = this.entries;
        return entryArr[i % entryArr.length];
    }

    private Entry put(Entry entry) {
        int i = this.entryCount;
        Entry[] entryArr = this.entries;
        if (i > (entryArr.length * 3) / 4) {
            int length = entryArr.length;
            int i2 = (length * 2) + 1;
            Entry[] entryArr2 = new Entry[i2];
            for (int i3 = length - 1; i3 >= 0; i3--) {
                Entry entry2 = this.entries[i3];
                while (entry2 != null) {
                    int i4 = entry2.hashCode % i2;
                    Entry entry3 = entry2.next;
                    entry2.next = entryArr2[i4];
                    entryArr2[i4] = entry2;
                    entry2 = entry3;
                }
            }
            this.entries = entryArr2;
        }
        this.entryCount++;
        int i5 = entry.hashCode;
        Entry[] entryArr3 = this.entries;
        int length2 = i5 % entryArr3.length;
        entry.next = entryArr3[length2];
        this.entries[length2] = entry;
        return entry;
    }

    private void add(Entry entry) {
        this.entryCount++;
        int i = entry.hashCode;
        Entry[] entryArr = this.entries;
        int length = i % entryArr.length;
        entry.next = entryArr[length];
        this.entries[length] = entry;
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstant(Object obj) {
        if (obj instanceof Integer) {
            return addConstantInteger(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return addConstantInteger(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return addConstantInteger(((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return addConstantInteger(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return addConstantInteger(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return addConstantFloat(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return addConstantLong(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return addConstantDouble(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return addConstantString((String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int sort = type.getSort();
            if (sort == 10) {
                return addConstantClass(type.getInternalName());
            }
            if (sort == 11) {
                return addConstantMethodType(type.getDescriptor());
            }
            return addConstantClass(type.getDescriptor());
        } else if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            return addConstantMethodHandle(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface());
        } else if (obj instanceof ConstantDynamic) {
            ConstantDynamic constantDynamic = (ConstantDynamic) obj;
            return addConstantDynamic(constantDynamic.getName(), constantDynamic.getDescriptor(), constantDynamic.getBootstrapMethod(), constantDynamic.getBootstrapMethodArgumentsUnsafe());
        } else {
            throw new IllegalArgumentException("value " + obj);
        }
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantClass(String str) {
        return addConstantUtf8Reference(7, str);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantFieldref(String str, String str2, String str3) {
        return addConstantMemberReference(9, str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantMethodref(String str, String str2, String str3, boolean z) {
        return addConstantMemberReference(z ? 11 : 10, str, str2, str3);
    }

    private Entry addConstantMemberReference(int i, String str, String str2, String str3) {
        int hash = hash(i, str, str2, str3);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == i && entry.hashCode == hash && entry.owner.equals(str) && entry.name.equals(str2) && entry.value.equals(str3)) {
                return entry;
            }
        }
        this.constantPool.put122(i, addConstantClass(str).index, addConstantNameAndType(str2, str3));
        int i2 = this.constantPoolCount;
        this.constantPoolCount = i2 + 1;
        return put(new Entry(i2, i, str, str2, str3, 0, hash));
    }

    private void addConstantMemberReference(int i, int i2, String str, String str2, String str3) {
        add(new Entry(i, i2, str, str2, str3, 0, hash(i2, str, str2, str3)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantString(String str) {
        return addConstantUtf8Reference(8, str);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantInteger(int i) {
        return addConstantIntegerOrFloat(3, i);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantFloat(float f) {
        return addConstantIntegerOrFloat(4, Float.floatToRawIntBits(f));
    }

    private Symbol addConstantIntegerOrFloat(int i, int i2) {
        int hash = hash(i, i2);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == i && entry.hashCode == hash && entry.data == ((long) i2)) {
                return entry;
            }
        }
        this.constantPool.putByte(i).putInt(i2);
        int i3 = this.constantPoolCount;
        this.constantPoolCount = i3 + 1;
        return put(new Entry(i3, i, (long) i2, hash));
    }

    private void addConstantIntegerOrFloat(int i, int i2, int i3) {
        add(new Entry(i, i2, (long) i3, hash(i2, i3)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantLong(long j) {
        return addConstantLongOrDouble(5, j);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantDouble(double d) {
        return addConstantLongOrDouble(6, Double.doubleToRawLongBits(d));
    }

    private Symbol addConstantLongOrDouble(int i, long j) {
        int hash = hash(i, j);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == i && entry.hashCode == hash && entry.data == j) {
                return entry;
            }
        }
        int i2 = this.constantPoolCount;
        this.constantPool.putByte(i).putLong(j);
        this.constantPoolCount += 2;
        return put(new Entry(i2, i, j, hash));
    }

    private void addConstantLongOrDouble(int i, int i2, long j) {
        add(new Entry(i, i2, j, hash(i2, j)));
    }

    /* access modifiers changed from: package-private */
    public int addConstantNameAndType(String str, String str2) {
        int hash = hash(12, str, str2);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 12 && entry.hashCode == hash && entry.name.equals(str) && entry.value.equals(str2)) {
                return entry.index;
            }
        }
        this.constantPool.put122(12, addConstantUtf8(str), addConstantUtf8(str2));
        int i = this.constantPoolCount;
        this.constantPoolCount = i + 1;
        return put(new Entry(i, 12, str, str2, hash)).index;
    }

    private void addConstantNameAndType(int i, String str, String str2) {
        add(new Entry(i, 12, str, str2, hash(12, str, str2)));
    }

    /* access modifiers changed from: package-private */
    public int addConstantUtf8(String str) {
        int hash = hash(1, str);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 1 && entry.hashCode == hash && entry.value.equals(str)) {
                return entry.index;
            }
        }
        this.constantPool.putByte(1).putUTF8(str);
        int i = this.constantPoolCount;
        this.constantPoolCount = i + 1;
        return put(new Entry(i, 1, str, hash)).index;
    }

    private void addConstantUtf8(int i, String str) {
        add(new Entry(i, 1, str, hash(1, str)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantMethodHandle(int i, String str, String str2, String str3, boolean z) {
        int hash = hash(15, str, str2, str3, i);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 15 && entry.hashCode == hash && entry.data == ((long) i) && entry.owner.equals(str) && entry.name.equals(str2) && entry.value.equals(str3)) {
                return entry;
            }
        }
        if (i <= 4) {
            this.constantPool.put112(15, i, addConstantFieldref(str, str2, str3).index);
        } else {
            this.constantPool.put112(15, i, addConstantMethodref(str, str2, str3, z).index);
        }
        int i2 = this.constantPoolCount;
        this.constantPoolCount = i2 + 1;
        return put(new Entry(i2, 15, str, str2, str3, (long) i, hash));
    }

    private void addConstantMethodHandle(int i, int i2, String str, String str2, String str3) {
        int i3 = i;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        add(new Entry(i3, 15, str4, str5, str6, (long) i2, hash(15, str, str2, str3, i2)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantMethodType(String str) {
        return addConstantUtf8Reference(16, str);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantDynamic(String str, String str2, Handle handle, Object... objArr) {
        return addConstantDynamicOrInvokeDynamicReference(17, str, str2, addBootstrapMethod(handle, objArr).index);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return addConstantDynamicOrInvokeDynamicReference(18, str, str2, addBootstrapMethod(handle, objArr).index);
    }

    private Symbol addConstantDynamicOrInvokeDynamicReference(int i, String str, String str2, int i2) {
        int hash = hash(i, str, str2, i2);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == i && entry.hashCode == hash && entry.data == ((long) i2) && entry.name.equals(str) && entry.value.equals(str2)) {
                return entry;
            }
        }
        this.constantPool.put122(i, i2, addConstantNameAndType(str, str2));
        int i3 = this.constantPoolCount;
        this.constantPoolCount = i3 + 1;
        return put(new Entry(i3, i, (String) null, str, str2, (long) i2, hash));
    }

    private void addConstantDynamicOrInvokeDynamicReference(int i, int i2, String str, String str2, int i3) {
        int i4 = i2;
        int i5 = i;
        String str3 = str;
        String str4 = str2;
        add(new Entry(i4, i5, (String) null, str3, str4, (long) i3, hash(i, str, str2, i3)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantModule(String str) {
        return addConstantUtf8Reference(19, str);
    }

    /* access modifiers changed from: package-private */
    public Symbol addConstantPackage(String str) {
        return addConstantUtf8Reference(20, str);
    }

    private Symbol addConstantUtf8Reference(int i, String str) {
        int hash = hash(i, str);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == i && entry.hashCode == hash && entry.value.equals(str)) {
                return entry;
            }
        }
        this.constantPool.put12(i, addConstantUtf8(str));
        int i2 = this.constantPoolCount;
        this.constantPoolCount = i2 + 1;
        return put(new Entry(i2, i, str, hash));
    }

    private void addConstantUtf8Reference(int i, int i2, String str) {
        add(new Entry(i, i2, str, hash(i2, str)));
    }

    /* access modifiers changed from: package-private */
    public Symbol addBootstrapMethod(Handle handle, Object... objArr) {
        ByteVector byteVector = this.bootstrapMethods;
        if (byteVector == null) {
            byteVector = new ByteVector();
            this.bootstrapMethods = byteVector;
        }
        for (Object addConstant : objArr) {
            addConstant(addConstant);
        }
        int i = byteVector.length;
        byteVector.putShort(addConstantMethodHandle(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface()).index);
        byteVector.putShort(objArr.length);
        for (Object addConstant2 : objArr) {
            byteVector.putShort(addConstant(addConstant2).index);
        }
        int i2 = byteVector.length - i;
        int hashCode = handle.hashCode();
        for (Object hashCode2 : objArr) {
            hashCode ^= hashCode2.hashCode();
        }
        return addBootstrapMethod(i, i2, hashCode & Integer.MAX_VALUE);
    }

    private Symbol addBootstrapMethod(int i, int i2, int i3) {
        byte[] bArr = this.bootstrapMethods.data;
        for (Entry entry = get(i3); entry != null; entry = entry.next) {
            if (entry.tag == 64 && entry.hashCode == i3) {
                int i4 = (int) entry.data;
                boolean z = false;
                int i5 = 0;
                while (true) {
                    if (i5 >= i2) {
                        z = true;
                        break;
                    } else if (bArr[i + i5] != bArr[i4 + i5]) {
                        break;
                    } else {
                        i5++;
                    }
                }
                if (z) {
                    this.bootstrapMethods.length = i;
                    return entry;
                }
            }
        }
        int i6 = this.bootstrapMethodCount;
        this.bootstrapMethodCount = i6 + 1;
        return put(new Entry(i6, 64, (long) i, i3));
    }

    /* access modifiers changed from: package-private */
    public Symbol getType(int i) {
        return this.typeTable[i];
    }

    /* access modifiers changed from: package-private */
    public int addType(String str) {
        int hash = hash(128, str);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 128 && entry.hashCode == hash && entry.value.equals(str)) {
                return entry.index;
            }
        }
        return addTypeInternal(new Entry(this.typeCount, 128, str, hash));
    }

    /* access modifiers changed from: package-private */
    public int addUninitializedType(String str, int i) {
        int hash = hash(129, str, i);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 129 && entry.hashCode == hash && entry.data == ((long) i) && entry.value.equals(str)) {
                return entry.index;
            }
        }
        return addTypeInternal(new Entry(this.typeCount, 129, str, (long) i, hash));
    }

    /* access modifiers changed from: package-private */
    public int addMergedType(int i, int i2) {
        long j = ((long) i) | (((long) i2) << 32);
        int hash = hash(130, i + i2);
        for (Entry entry = get(hash); entry != null; entry = entry.next) {
            if (entry.tag == 130 && entry.hashCode == hash && entry.data == j) {
                return entry.info;
            }
        }
        int addType = addType(this.classWriter.getCommonSuperClass(this.typeTable[i].value, this.typeTable[i2].value));
        put(new Entry(this.typeCount, 130, j, hash)).info = addType;
        return addType;
    }

    private int addTypeInternal(Entry entry) {
        if (this.typeTable == null) {
            this.typeTable = new Entry[16];
        }
        int i = this.typeCount;
        Entry[] entryArr = this.typeTable;
        if (i == entryArr.length) {
            Entry[] entryArr2 = new Entry[(entryArr.length * 2)];
            System.arraycopy(entryArr, 0, entryArr2, 0, entryArr.length);
            this.typeTable = entryArr2;
        }
        Entry[] entryArr3 = this.typeTable;
        int i2 = this.typeCount;
        this.typeCount = i2 + 1;
        entryArr3[i2] = entry;
        return put(entry).index;
    }

    private static int hash(int i, String str) {
        return (i + str.hashCode()) & Integer.MAX_VALUE;
    }

    private static int hash(int i, String str, int i2) {
        return (i + str.hashCode() + i2) & Integer.MAX_VALUE;
    }

    private static int hash(int i, String str, String str2) {
        return (i + (str.hashCode() * str2.hashCode())) & Integer.MAX_VALUE;
    }

    private static int hash(int i, String str, String str2, int i2) {
        return (i + (str.hashCode() * str2.hashCode() * (i2 + 1))) & Integer.MAX_VALUE;
    }

    private static int hash(int i, String str, String str2, String str3) {
        return (i + (str.hashCode() * str2.hashCode() * str3.hashCode())) & Integer.MAX_VALUE;
    }

    private static int hash(int i, String str, String str2, String str3, int i2) {
        return (i + (str.hashCode() * str2.hashCode() * str3.hashCode() * i2)) & Integer.MAX_VALUE;
    }

    private static class Entry extends Symbol {
        final int hashCode;
        Entry next;

        Entry(int i, int i2, String str, String str2, String str3, long j, int i3) {
            super(i, i2, str, str2, str3, j);
            this.hashCode = i3;
        }

        Entry(int i, int i2, String str, int i3) {
            super(i, i2, (String) null, (String) null, str, 0);
            this.hashCode = i3;
        }

        Entry(int i, int i2, String str, long j, int i3) {
            super(i, i2, (String) null, (String) null, str, j);
            this.hashCode = i3;
        }

        Entry(int i, int i2, String str, String str2, int i3) {
            super(i, i2, (String) null, str, str2, 0);
            this.hashCode = i3;
        }

        Entry(int i, int i2, long j, int i3) {
            super(i, i2, (String) null, (String) null, (String) null, j);
            this.hashCode = i3;
        }
    }
}
