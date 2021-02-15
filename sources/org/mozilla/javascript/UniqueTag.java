package org.mozilla.javascript;

import java.io.Serializable;

public final class UniqueTag implements Serializable {
    public static final UniqueTag DOUBLE_MARK = new UniqueTag(3);
    private static final int ID_DOUBLE_MARK = 3;
    private static final int ID_NOT_FOUND = 1;
    private static final int ID_NULL_VALUE = 2;
    public static final UniqueTag NOT_FOUND = new UniqueTag(1);
    public static final UniqueTag NULL_VALUE = new UniqueTag(2);
    static final long serialVersionUID = -4320556826714577259L;
    private final int tagId;

    private UniqueTag(int i) {
        this.tagId = i;
    }

    public Object readResolve() {
        int i = this.tagId;
        if (i == 1) {
            return NOT_FOUND;
        }
        if (i == 2) {
            return NULL_VALUE;
        }
        if (i == 3) {
            return DOUBLE_MARK;
        }
        throw new IllegalStateException(String.valueOf(this.tagId));
    }

    public String toString() {
        String str;
        int i = this.tagId;
        if (i == 1) {
            str = "NOT_FOUND";
        } else if (i == 2) {
            str = "NULL_VALUE";
        } else if (i == 3) {
            str = "DOUBLE_MARK";
        } else {
            throw Kit.codeBug();
        }
        return super.toString() + ": " + str;
    }
}
