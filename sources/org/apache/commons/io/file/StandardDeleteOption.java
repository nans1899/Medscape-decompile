package org.apache.commons.io.file;

public enum StandardDeleteOption implements DeleteOption {
    OVERRIDE_READ_ONLY;

    public static boolean overrideReadOnly(DeleteOption[] deleteOptionArr) {
        if (!(deleteOptionArr == null || deleteOptionArr.length == 0)) {
            for (StandardDeleteOption standardDeleteOption : deleteOptionArr) {
                if (standardDeleteOption == OVERRIDE_READ_ONLY) {
                    return true;
                }
            }
        }
        return false;
    }
}
