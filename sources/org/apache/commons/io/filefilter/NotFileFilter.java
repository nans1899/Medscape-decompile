package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 6131563330944994230L;
    private final IOFileFilter filter;

    public NotFileFilter(IOFileFilter iOFileFilter) {
        if (iOFileFilter != null) {
            this.filter = iOFileFilter;
            return;
        }
        throw new IllegalArgumentException("The filter must not be null");
    }

    public boolean accept(File file) {
        return !this.filter.accept(file);
    }

    public boolean accept(File file, String str) {
        return !this.filter.accept(file, str);
    }

    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
