package net.bytebuddy.jar.asm;

final class ModuleWriter extends ModuleVisitor {
    private final ByteVector exports = new ByteVector();
    private int exportsCount;
    private int mainClassIndex;
    private final int moduleFlags;
    private final int moduleNameIndex;
    private final int moduleVersionIndex;
    private final ByteVector opens = new ByteVector();
    private int opensCount;
    private int packageCount;
    private final ByteVector packageIndex = new ByteVector();
    private final ByteVector provides = new ByteVector();
    private int providesCount;
    private final ByteVector requires = new ByteVector();
    private int requiresCount;
    private final SymbolTable symbolTable;
    private int usesCount;
    private final ByteVector usesIndex = new ByteVector();

    public void visitEnd() {
    }

    ModuleWriter(SymbolTable symbolTable2, int i, int i2, int i3) {
        super(Opcodes.ASM7);
        this.symbolTable = symbolTable2;
        this.moduleNameIndex = i;
        this.moduleFlags = i2;
        this.moduleVersionIndex = i3;
    }

    public void visitMainClass(String str) {
        this.mainClassIndex = this.symbolTable.addConstantClass(str).index;
    }

    public void visitPackage(String str) {
        this.packageIndex.putShort(this.symbolTable.addConstantPackage(str).index);
        this.packageCount++;
    }

    public void visitRequire(String str, int i, String str2) {
        int i2;
        ByteVector putShort = this.requires.putShort(this.symbolTable.addConstantModule(str).index).putShort(i);
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = this.symbolTable.addConstantUtf8(str2);
        }
        putShort.putShort(i2);
        this.requiresCount++;
    }

    public void visitExport(String str, int i, String... strArr) {
        this.exports.putShort(this.symbolTable.addConstantPackage(str).index).putShort(i);
        if (strArr == null) {
            this.exports.putShort(0);
        } else {
            this.exports.putShort(strArr.length);
            for (String addConstantModule : strArr) {
                this.exports.putShort(this.symbolTable.addConstantModule(addConstantModule).index);
            }
        }
        this.exportsCount++;
    }

    public void visitOpen(String str, int i, String... strArr) {
        this.opens.putShort(this.symbolTable.addConstantPackage(str).index).putShort(i);
        if (strArr == null) {
            this.opens.putShort(0);
        } else {
            this.opens.putShort(strArr.length);
            for (String addConstantModule : strArr) {
                this.opens.putShort(this.symbolTable.addConstantModule(addConstantModule).index);
            }
        }
        this.opensCount++;
    }

    public void visitUse(String str) {
        this.usesIndex.putShort(this.symbolTable.addConstantClass(str).index);
        this.usesCount++;
    }

    public void visitProvide(String str, String... strArr) {
        this.provides.putShort(this.symbolTable.addConstantClass(str).index);
        this.provides.putShort(strArr.length);
        for (String addConstantClass : strArr) {
            this.provides.putShort(this.symbolTable.addConstantClass(addConstantClass).index);
        }
        this.providesCount++;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeCount() {
        int i = 0;
        int i2 = (this.packageCount > 0 ? 1 : 0) + 1;
        if (this.mainClassIndex > 0) {
            i = 1;
        }
        return i2 + i;
    }

    /* access modifiers changed from: package-private */
    public int computeAttributesSize() {
        this.symbolTable.addConstantUtf8("Module");
        int i = this.requires.length + 22 + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length;
        if (this.packageCount > 0) {
            this.symbolTable.addConstantUtf8("ModulePackages");
            i += this.packageIndex.length + 8;
        }
        if (this.mainClassIndex <= 0) {
            return i;
        }
        this.symbolTable.addConstantUtf8("ModuleMainClass");
        return i + 8;
    }

    /* access modifiers changed from: package-private */
    public void putAttributes(ByteVector byteVector) {
        byteVector.putShort(this.symbolTable.addConstantUtf8("Module")).putInt(this.requires.length + 16 + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length).putShort(this.moduleNameIndex).putShort(this.moduleFlags).putShort(this.moduleVersionIndex).putShort(this.requiresCount).putByteArray(this.requires.data, 0, this.requires.length).putShort(this.exportsCount).putByteArray(this.exports.data, 0, this.exports.length).putShort(this.opensCount).putByteArray(this.opens.data, 0, this.opens.length).putShort(this.usesCount).putByteArray(this.usesIndex.data, 0, this.usesIndex.length).putShort(this.providesCount).putByteArray(this.provides.data, 0, this.provides.length);
        if (this.packageCount > 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("ModulePackages")).putInt(this.packageIndex.length + 2).putShort(this.packageCount).putByteArray(this.packageIndex.data, 0, this.packageIndex.length);
        }
        if (this.mainClassIndex > 0) {
            byteVector.putShort(this.symbolTable.addConstantUtf8("ModuleMainClass")).putInt(2).putShort(this.mainClassIndex);
        }
    }
}
