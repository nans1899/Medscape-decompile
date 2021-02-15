package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class ModifierMatcher<T extends ModifierReviewable> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Mode mode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mode.equals(((ModifierMatcher) obj).mode);
    }

    public int hashCode() {
        return 527 + this.mode.hashCode();
    }

    public ModifierMatcher(Mode mode2) {
        this.mode = mode2;
    }

    public boolean matches(T t) {
        return (t.getModifiers() & this.mode.getModifiers()) != 0;
    }

    public String toString() {
        return this.mode.getDescription();
    }

    public enum Mode {
        PUBLIC(1, "isPublic()"),
        PROTECTED(4, "isProtected()"),
        PRIVATE(2, "isPrivate()"),
        FINAL(16, "isFinal()"),
        STATIC(8, "isStatic()"),
        SYNCHRONIZED(32, "isSynchronized()"),
        NATIVE(256, "isNative()"),
        STRICT(2048, "isStrict()"),
        VAR_ARGS(128, "isVarArgs()"),
        SYNTHETIC(4096, "isSynthetic()"),
        BRIDGE(64, "isBridge()"),
        ABSTRACT(1024, "isAbstract()"),
        INTERFACE(512, "isInterface()"),
        ANNOTATION(8192, "isAnnotation()"),
        VOLATILE(64, "isVolatile()"),
        TRANSIENT(128, "isTransient()"),
        MANDATED(32768, "isMandated()"),
        ENUMERATION(16384, "isEnum()");
        
        private final String description;
        private final int modifiers;

        private Mode(int i, String str) {
            this.modifiers = i;
            this.description = str;
        }

        /* access modifiers changed from: protected */
        public String getDescription() {
            return this.description;
        }

        /* access modifiers changed from: protected */
        public int getModifiers() {
            return this.modifiers;
        }
    }
}
