package net.bytebuddy.utility;

import java.security.AccessController;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class OpenedClassReader {
    public static final int ASM_API = Opcodes.ASM7;
    public static final boolean EXPERIMENTAL;
    public static final String EXPERIMENTAL_PROPERTY = "net.bytebuddy.experimental";

    static {
        boolean z;
        try {
            z = Boolean.parseBoolean((String) AccessController.doPrivileged(new GetSystemPropertyAction(EXPERIMENTAL_PROPERTY)));
        } catch (Exception unused) {
            z = false;
        }
        EXPERIMENTAL = z;
    }

    private OpenedClassReader() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static ClassReader of(byte[] bArr) {
        if (!EXPERIMENTAL) {
            return new ClassReader(bArr);
        }
        byte[] bArr2 = {bArr[4], bArr[5], bArr[6], bArr[7]};
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 55;
        ClassReader classReader = new ClassReader(bArr);
        System.arraycopy(bArr2, 0, bArr, 4, 4);
        return classReader;
    }
}
