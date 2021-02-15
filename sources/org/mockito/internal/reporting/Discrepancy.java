package org.mockito.internal.reporting;

public class Discrepancy {
    private final int actualCount;
    private final int wantedCount;

    public Discrepancy(int i, int i2) {
        this.wantedCount = i;
        this.actualCount = i2;
    }

    public int getWantedCount() {
        return this.wantedCount;
    }

    public String getPluralizedWantedCount() {
        return Pluralizer.pluralize(this.wantedCount);
    }

    public int getActualCount() {
        return this.actualCount;
    }

    public String getPluralizedActualCount() {
        return Pluralizer.pluralize(this.actualCount);
    }
}
