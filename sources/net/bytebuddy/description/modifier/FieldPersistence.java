package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum FieldPersistence implements ModifierContributor.ForField {
    PLAIN(0),
    TRANSIENT(128);
    
    private final int mask;

    public int getRange() {
        return 128;
    }

    private FieldPersistence(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isDefault() {
        return this == PLAIN;
    }

    public boolean isTransient() {
        return (this.mask & 128) != 0;
    }
}
