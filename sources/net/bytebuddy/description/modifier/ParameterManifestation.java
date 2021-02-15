package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum ParameterManifestation implements ModifierContributor.ForParameter {
    PLAIN(0),
    FINAL(16);
    
    private final int mask;

    public int getRange() {
        return 16;
    }

    private ParameterManifestation(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isDefault() {
        return this == PLAIN;
    }

    public boolean isFinal() {
        return this == FINAL;
    }
}
