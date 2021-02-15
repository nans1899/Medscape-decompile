package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.ModuleVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class ModuleRemapper extends ModuleVisitor {
    protected final Remapper remapper;

    public ModuleRemapper(ModuleVisitor moduleVisitor, Remapper remapper2) {
        this(Opcodes.ASM7, moduleVisitor, remapper2);
    }

    protected ModuleRemapper(int i, ModuleVisitor moduleVisitor, Remapper remapper2) {
        super(i, moduleVisitor);
        this.remapper = remapper2;
    }

    public void visitMainClass(String str) {
        super.visitMainClass(this.remapper.mapType(str));
    }

    public void visitPackage(String str) {
        super.visitPackage(this.remapper.mapPackageName(str));
    }

    public void visitRequire(String str, int i, String str2) {
        super.visitRequire(this.remapper.mapModuleName(str), i, str2);
    }

    public void visitExport(String str, int i, String... strArr) {
        String[] strArr2;
        if (strArr != null) {
            strArr2 = new String[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr2[i2] = this.remapper.mapModuleName(strArr[i2]);
            }
        } else {
            strArr2 = null;
        }
        super.visitExport(this.remapper.mapPackageName(str), i, strArr2);
    }

    public void visitOpen(String str, int i, String... strArr) {
        String[] strArr2;
        if (strArr != null) {
            strArr2 = new String[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr2[i2] = this.remapper.mapModuleName(strArr[i2]);
            }
        } else {
            strArr2 = null;
        }
        super.visitOpen(this.remapper.mapPackageName(str), i, strArr2);
    }

    public void visitUse(String str) {
        super.visitUse(this.remapper.mapType(str));
    }

    public void visitProvide(String str, String... strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = this.remapper.mapType(strArr[i]);
        }
        super.visitProvide(this.remapper.mapType(str), strArr2);
    }
}
