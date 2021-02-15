package net.bytebuddy.dynamic.loading;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public enum NoOpClassFileTransformer implements ClassFileTransformer {
    INSTANCE;
    
    private static final byte[] NO_TRANSFORMATION = null;

    static {
        NO_TRANSFORMATION = null;
    }

    public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
        return NO_TRANSFORMATION;
    }
}
