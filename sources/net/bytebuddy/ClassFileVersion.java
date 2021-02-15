package net.bytebuddy;

import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.utility.OpenedClassReader;

@HashCodeAndEqualsPlugin.Enhance
public class ClassFileVersion implements Comparable<ClassFileVersion> {
    protected static final int BASE_VERSION = 44;
    public static final ClassFileVersion JAVA_V1 = new ClassFileVersion(Opcodes.V1_1);
    public static final ClassFileVersion JAVA_V10 = new ClassFileVersion(54);
    public static final ClassFileVersion JAVA_V11 = new ClassFileVersion(55);
    public static final ClassFileVersion JAVA_V12 = new ClassFileVersion(56);
    public static final ClassFileVersion JAVA_V13 = new ClassFileVersion(57);
    public static final ClassFileVersion JAVA_V2 = new ClassFileVersion(46);
    public static final ClassFileVersion JAVA_V3 = new ClassFileVersion(47);
    public static final ClassFileVersion JAVA_V4 = new ClassFileVersion(48);
    public static final ClassFileVersion JAVA_V5 = new ClassFileVersion(49);
    public static final ClassFileVersion JAVA_V6 = new ClassFileVersion(50);
    public static final ClassFileVersion JAVA_V7 = new ClassFileVersion(51);
    public static final ClassFileVersion JAVA_V8 = new ClassFileVersion(52);
    public static final ClassFileVersion JAVA_V9 = new ClassFileVersion(53);
    private static final VersionLocator VERSION_LOCATOR = ((VersionLocator) AccessController.doPrivileged(VersionLocator.CreationAction.INSTANCE));
    private static transient /* synthetic */ ClassFileVersion ofThisVm_xBRx5PeX;
    private final int versionNumber;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.versionNumber == ((ClassFileVersion) obj).versionNumber;
    }

    public int hashCode() {
        return 527 + this.versionNumber;
    }

    protected ClassFileVersion(int i) {
        this.versionNumber = i;
    }

    public static ClassFileVersion ofMinorMajor(int i) {
        ClassFileVersion classFileVersion = new ClassFileVersion(i);
        if (classFileVersion.getMajorVersion() > 44) {
            return classFileVersion;
        }
        throw new IllegalArgumentException("Class version " + i + " is not valid");
    }

    public static ClassFileVersion ofJavaVersionString(String str) {
        if (str.equals("1.1")) {
            return JAVA_V1;
        }
        if (str.equals("1.2")) {
            return JAVA_V2;
        }
        if (str.equals("1.3")) {
            return JAVA_V3;
        }
        if (str.equals("1.4")) {
            return JAVA_V4;
        }
        if (str.equals("1.5") || str.equals(UserProfile.NURSE_PRACTITIONER_ID)) {
            return JAVA_V5;
        }
        if (str.equals("1.6") || str.equals("6")) {
            return JAVA_V6;
        }
        if (str.equals("1.7") || str.equals("7")) {
            return JAVA_V7;
        }
        if (str.equals("1.8") || str.equals(UserProfile.PHARMACIST_ID)) {
            return JAVA_V8;
        }
        if (str.equals("1.9") || str.equals("9")) {
            return JAVA_V9;
        }
        if (str.equals("1.10") || str.equals(UserProfile.PHYSICIAN_ID)) {
            return JAVA_V10;
        }
        if (str.equals("1.11") || str.equals(UserProfile.PHYS_ASST_ID)) {
            return JAVA_V11;
        }
        if (str.equals("1.12") || str.equals(UserProfile.NURSE_ID)) {
            return JAVA_V12;
        }
        if (str.equals("1.13") || str.equals("13")) {
            return JAVA_V13;
        }
        if (OpenedClassReader.EXPERIMENTAL) {
            try {
                int parseInt = Integer.parseInt(str.startsWith("1.") ? str.substring(2) : str);
                if (parseInt > 0) {
                    return new ClassFileVersion(parseInt + 44);
                }
            } catch (NumberFormatException unused) {
            }
        }
        throw new IllegalArgumentException("Unknown Java version string: " + str);
    }

    public static ClassFileVersion ofJavaVersion(int i) {
        switch (i) {
            case 1:
                return JAVA_V1;
            case 2:
                return JAVA_V2;
            case 3:
                return JAVA_V3;
            case 4:
                return JAVA_V4;
            case 5:
                return JAVA_V5;
            case 6:
                return JAVA_V6;
            case 7:
                return JAVA_V7;
            case 8:
                return JAVA_V8;
            case 9:
                return JAVA_V9;
            case 10:
                return JAVA_V10;
            case 11:
                return JAVA_V11;
            case 12:
                return JAVA_V12;
            case 13:
                return JAVA_V13;
            default:
                if (OpenedClassReader.EXPERIMENTAL && i > 0) {
                    return new ClassFileVersion(i + 44);
                }
                throw new IllegalArgumentException("Unknown Java version: " + i);
        }
    }

    @CachedReturnPlugin.Enhance
    public static ClassFileVersion ofThisVm() {
        ClassFileVersion locate = ofThisVm_xBRx5PeX != null ? null : VERSION_LOCATOR.locate();
        if (locate == null) {
            return ofThisVm_xBRx5PeX;
        }
        ofThisVm_xBRx5PeX = locate;
        return locate;
    }

    public static ClassFileVersion ofThisVm(ClassFileVersion classFileVersion) {
        try {
            return ofThisVm();
        } catch (Exception unused) {
            return classFileVersion;
        }
    }

    public static ClassFileVersion of(Class<?> cls) throws IOException {
        return of(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static ClassFileVersion of(Class<?> cls, ClassFileLocator classFileLocator) throws IOException {
        return of(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public static ClassFileVersion of(TypeDescription typeDescription, ClassFileLocator classFileLocator) throws IOException {
        ClassReader of = OpenedClassReader.of(classFileLocator.locate(typeDescription.getName()).resolve());
        VersionExtractor versionExtractor = new VersionExtractor();
        of.accept(versionExtractor, 1);
        return ofMinorMajor(versionExtractor.getClassFileVersionNumber());
    }

    public int getMinorMajorVersion() {
        return this.versionNumber;
    }

    public int getMajorVersion() {
        return this.versionNumber & 255;
    }

    public int getMinorVersion() {
        return this.versionNumber >> 16;
    }

    public int getJavaVersion() {
        return getMajorVersion() - 44;
    }

    public boolean isAtLeast(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) > -1;
    }

    public boolean isGreaterThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) > 0;
    }

    public boolean isAtMost(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) < 1;
    }

    public boolean isLessThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) < 0;
    }

    public ClassFileVersion asPreviewVersion() {
        return new ClassFileVersion(this.versionNumber | -65536);
    }

    public boolean isPreviewVersion() {
        return (this.versionNumber & -65536) == -65536;
    }

    public int compareTo(ClassFileVersion classFileVersion) {
        int i;
        int i2;
        if (getMajorVersion() == classFileVersion.getMajorVersion()) {
            i2 = getMinorVersion();
            i = classFileVersion.getMinorVersion();
        } else {
            i2 = getMajorVersion();
            i = classFileVersion.getMajorVersion();
        }
        return Integer.signum(i2 - i);
    }

    public String toString() {
        return "Java " + getJavaVersion();
    }

    protected interface VersionLocator {
        ClassFileVersion locate();

        public enum CreationAction implements PrivilegedAction<VersionLocator> {
            INSTANCE;

            public VersionLocator run() {
                try {
                    return new ForJava9CapableVm(Runtime.class.getMethod("version", new Class[0]), Class.forName("java.lang.Runtime$Version").getMethod("major", new Class[0]));
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForJava9CapableVm implements VersionLocator {
            private static final Object STATIC_METHOD = null;
            private final Method current;
            private final Method major;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                return this.current.equals(forJava9CapableVm.current) && this.major.equals(forJava9CapableVm.major);
            }

            public int hashCode() {
                return ((527 + this.current.hashCode()) * 31) + this.major.hashCode();
            }

            protected ForJava9CapableVm(Method method, Method method2) {
                this.current = method;
                this.major = method2;
            }

            public ClassFileVersion locate() {
                try {
                    return ClassFileVersion.ofJavaVersion(((Integer) this.major.invoke(this.current.invoke(STATIC_METHOD, new Object[0]), new Object[0])).intValue());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Could not access VM version lookup", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Could not look up VM version", e2.getCause());
                }
            }
        }

        public enum ForLegacyVm implements VersionLocator, PrivilegedAction<String> {
            INSTANCE;
            
            private static final String JAVA_VERSION_PROPERTY = "java.version";

            public ClassFileVersion locate() {
                String str = (String) AccessController.doPrivileged(this);
                int[] iArr = {-1, 0, 0};
                int i = 1;
                while (i < 3) {
                    iArr[i] = str.indexOf(46, iArr[i - 1] + 1);
                    if (iArr[i] != -1) {
                        i++;
                    } else {
                        throw new IllegalStateException("This JVM's version string does not seem to be valid: " + str);
                    }
                }
                return ClassFileVersion.ofJavaVersion(Integer.parseInt(str.substring(iArr[1] + 1, iArr[2])));
            }

            public String run() {
                return System.getProperty(JAVA_VERSION_PROPERTY);
            }
        }
    }

    protected static class VersionExtractor extends ClassVisitor {
        private int classFileVersionNumber;

        protected VersionExtractor() {
            super(OpenedClassReader.ASM_API);
        }

        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            this.classFileVersionNumber = i;
        }

        /* access modifiers changed from: protected */
        public int getClassFileVersionNumber() {
            return this.classFileVersionNumber;
        }
    }
}
