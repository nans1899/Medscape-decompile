package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum TypeManifestation implements ModifierContributor.ForType {
    PLAIN(0),
    FINAL(16),
    ABSTRACT(1024),
    INTERFACE(1536),
    ANNOTATION(9728);
    
    private final int mask;

    public int getRange() {
        return 9744;
    }

    private TypeManifestation(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isDefault() {
        return this == PLAIN;
    }

    public boolean isFinal() {
        return (this.mask & 16) != 0;
    }

    public boolean isAbstract() {
        return (this.mask & 1024) != 0 && !isInterface();
    }

    public boolean isInterface() {
        return (this.mask & 512) != 0;
    }

    public boolean isAnnotation() {
        return (this.mask & 8192) != 0;
    }
}
