package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class CanExecuteFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter CANNOT_EXECUTE = new NotFileFilter(CAN_EXECUTE);
    public static final IOFileFilter CAN_EXECUTE = new CanExecuteFileFilter();
    private static final long serialVersionUID = 3179904805251622989L;

    protected CanExecuteFileFilter() {
    }

    public boolean accept(File file) {
        return file.canExecute();
    }
}
