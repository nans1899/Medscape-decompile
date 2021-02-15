package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum SynchronizationState implements ModifierContributor.ForMethod {
    PLAIN(0),
    SYNCHRONIZED(32);
    
    private final int mask;

    public int getRange() {
        return 32;
    }

    private SynchronizationState(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isDefault() {
        return this == PLAIN;
    }

    public boolean isSynchronized() {
        return this == SYNCHRONIZED;
    }
}
