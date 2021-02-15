package net.bytebuddy.dynamic.scaffold;

public enum TypeValidation {
    ENABLED(true),
    DISABLED(false);
    
    private final boolean enabled;

    private TypeValidation(boolean z) {
        this.enabled = z;
    }

    public static TypeValidation of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
