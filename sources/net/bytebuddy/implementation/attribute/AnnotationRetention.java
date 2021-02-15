package net.bytebuddy.implementation.attribute;

public enum AnnotationRetention {
    ENABLED(true),
    DISABLED(false);
    
    private final boolean enabled;

    private AnnotationRetention(boolean z) {
        this.enabled = z;
    }

    public static AnnotationRetention of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
