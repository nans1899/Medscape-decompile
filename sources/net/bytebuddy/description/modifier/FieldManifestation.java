package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum FieldManifestation implements ModifierContributor.ForField {
    PLAIN(0),
    FINAL(16),
    VOLATILE(64);
    
    private final int mask;

    public int getRange() {
        return 80;
    }

    private FieldManifestation(int i) {
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

    public boolean isVolatile() {
        return (this.mask & 64) != 0;
    }

    public boolean isPlain() {
        return !isFinal() && !isVolatile();
    }
}
