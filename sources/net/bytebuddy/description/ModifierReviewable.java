package net.bytebuddy.description;

import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.FieldPersistence;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.MethodStrictness;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.ParameterManifestation;
import net.bytebuddy.description.modifier.ProvisioningState;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;

public interface ModifierReviewable {
    public static final int EMPTY_MASK = 0;

    public interface ForFieldDescription extends OfEnumeration {
        FieldManifestation getFieldManifestation();

        FieldPersistence getFieldPersistence();

        boolean isTransient();

        boolean isVolatile();
    }

    public interface ForMethodDescription extends OfAbstraction {
        MethodManifestation getMethodManifestation();

        MethodStrictness getMethodStrictness();

        SynchronizationState getSynchronizationState();

        boolean isBridge();

        boolean isNative();

        boolean isStrict();

        boolean isSynchronized();

        boolean isVarArgs();
    }

    public interface ForParameterDescription extends ModifierReviewable {
        ParameterManifestation getParameterManifestation();

        ProvisioningState getProvisioningState();

        boolean isMandated();
    }

    public interface ForTypeDefinition extends OfAbstraction, OfEnumeration {
        TypeManifestation getTypeManifestation();

        boolean isAnnotation();

        boolean isInterface();
    }

    public interface OfAbstraction extends OfByteCodeElement {
        boolean isAbstract();
    }

    public interface OfByteCodeElement extends ModifierReviewable {
        Ownership getOwnership();

        Visibility getVisibility();

        boolean isDeprecated();

        boolean isPackagePrivate();

        boolean isPrivate();

        boolean isProtected();

        boolean isPublic();

        boolean isStatic();
    }

    public interface OfEnumeration extends OfByteCodeElement {
        EnumerationState getEnumerationState();

        boolean isEnum();
    }

    int getModifiers();

    SyntheticState getSyntheticState();

    boolean isFinal();

    boolean isSynthetic();

    public static abstract class AbstractBase implements ForTypeDefinition, ForFieldDescription, ForMethodDescription, ForParameterDescription {
        public boolean isAbstract() {
            return matchesMask(1024);
        }

        public boolean isFinal() {
            return matchesMask(16);
        }

        public boolean isStatic() {
            return matchesMask(8);
        }

        public boolean isPublic() {
            return matchesMask(1);
        }

        public boolean isProtected() {
            return matchesMask(4);
        }

        public boolean isPackagePrivate() {
            return !isPublic() && !isProtected() && !isPrivate();
        }

        public boolean isPrivate() {
            return matchesMask(2);
        }

        public boolean isNative() {
            return matchesMask(256);
        }

        public boolean isSynchronized() {
            return matchesMask(32);
        }

        public boolean isStrict() {
            return matchesMask(2048);
        }

        public boolean isMandated() {
            return matchesMask(32768);
        }

        public boolean isSynthetic() {
            return matchesMask(4096);
        }

        public boolean isBridge() {
            return matchesMask(64);
        }

        public boolean isDeprecated() {
            return matchesMask(131072);
        }

        public boolean isAnnotation() {
            return matchesMask(8192);
        }

        public boolean isEnum() {
            return matchesMask(16384);
        }

        public boolean isInterface() {
            return matchesMask(512);
        }

        public boolean isTransient() {
            return matchesMask(128);
        }

        public boolean isVolatile() {
            return matchesMask(64);
        }

        public boolean isVarArgs() {
            return matchesMask(128);
        }

        public SyntheticState getSyntheticState() {
            return isSynthetic() ? SyntheticState.SYNTHETIC : SyntheticState.PLAIN;
        }

        public Visibility getVisibility() {
            int modifiers = getModifiers();
            int i = modifiers & 7;
            if (i == 0) {
                return Visibility.PACKAGE_PRIVATE;
            }
            if (i == 1) {
                return Visibility.PUBLIC;
            }
            if (i == 2) {
                return Visibility.PRIVATE;
            }
            if (i == 4) {
                return Visibility.PROTECTED;
            }
            throw new IllegalStateException("Unexpected modifiers: " + modifiers);
        }

        public Ownership getOwnership() {
            return isStatic() ? Ownership.STATIC : Ownership.MEMBER;
        }

        public EnumerationState getEnumerationState() {
            return isEnum() ? EnumerationState.ENUMERATION : EnumerationState.PLAIN;
        }

        public TypeManifestation getTypeManifestation() {
            int modifiers = getModifiers();
            int i = modifiers & 9744;
            if (i == 0) {
                return TypeManifestation.PLAIN;
            }
            if (i == 16) {
                return TypeManifestation.FINAL;
            }
            if (i == 1024) {
                return TypeManifestation.ABSTRACT;
            }
            if (i == 1536) {
                return TypeManifestation.INTERFACE;
            }
            if (i == 9728) {
                return TypeManifestation.ANNOTATION;
            }
            throw new IllegalStateException("Unexpected modifiers: " + modifiers);
        }

        public FieldManifestation getFieldManifestation() {
            int modifiers = getModifiers();
            int i = modifiers & 80;
            if (i == 0) {
                return FieldManifestation.PLAIN;
            }
            if (i == 16) {
                return FieldManifestation.FINAL;
            }
            if (i == 64) {
                return FieldManifestation.VOLATILE;
            }
            throw new IllegalStateException("Unexpected modifiers: " + modifiers);
        }

        public FieldPersistence getFieldPersistence() {
            int modifiers = getModifiers();
            int i = modifiers & 128;
            if (i == 0) {
                return FieldPersistence.PLAIN;
            }
            if (i == 128) {
                return FieldPersistence.TRANSIENT;
            }
            throw new IllegalStateException("Unexpected modifiers: " + modifiers);
        }

        public SynchronizationState getSynchronizationState() {
            return isSynchronized() ? SynchronizationState.SYNCHRONIZED : SynchronizationState.PLAIN;
        }

        public MethodManifestation getMethodManifestation() {
            int modifiers = getModifiers();
            int i = modifiers & 1360;
            if (i == 0) {
                return MethodManifestation.PLAIN;
            }
            if (i == 16) {
                return MethodManifestation.FINAL;
            }
            if (i == 64) {
                return MethodManifestation.BRIDGE;
            }
            if (i == 80) {
                return MethodManifestation.FINAL_BRIDGE;
            }
            if (i == 256) {
                return MethodManifestation.NATIVE;
            }
            if (i == 272) {
                return MethodManifestation.FINAL_NATIVE;
            }
            if (i == 1024) {
                return MethodManifestation.ABSTRACT;
            }
            throw new IllegalStateException("Unexpected modifiers: " + modifiers);
        }

        public MethodStrictness getMethodStrictness() {
            return isStrict() ? MethodStrictness.STRICT : MethodStrictness.PLAIN;
        }

        public ParameterManifestation getParameterManifestation() {
            return isFinal() ? ParameterManifestation.FINAL : ParameterManifestation.PLAIN;
        }

        public ProvisioningState getProvisioningState() {
            return isMandated() ? ProvisioningState.MANDATED : ProvisioningState.PLAIN;
        }

        private boolean matchesMask(int i) {
            return (getModifiers() & i) == i;
        }
    }
}
