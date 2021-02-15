package org.mockito.internal.verification.checkers;

import org.mockito.internal.reporting.Discrepancy;

public class AtLeastDiscrepancy extends Discrepancy {
    public AtLeastDiscrepancy(int i, int i2) {
        super(i, i2);
    }

    public String getPluralizedWantedCount() {
        return "*at least* " + super.getPluralizedWantedCount();
    }
}
