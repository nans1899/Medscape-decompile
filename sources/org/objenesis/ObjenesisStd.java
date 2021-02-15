package org.objenesis;

import org.objenesis.strategy.StdInstantiatorStrategy;

public class ObjenesisStd extends ObjenesisBase {
    public ObjenesisStd() {
        super(new StdInstantiatorStrategy());
    }

    public ObjenesisStd(boolean z) {
        super(new StdInstantiatorStrategy(), z);
    }
}
