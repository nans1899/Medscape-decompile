package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.IOCase;

public class NameFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 176844364689077340L;
    private final IOCase caseSensitivity;
    private final String[] names;

    public NameFileFilter(String str) {
        this(str, (IOCase) null);
    }

    public NameFileFilter(String str, IOCase iOCase) {
        if (str != null) {
            this.names = new String[]{str};
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The wildcard must not be null");
    }

    public NameFileFilter(String... strArr) {
        this(strArr, (IOCase) null);
    }

    public NameFileFilter(String[] strArr, IOCase iOCase) {
        if (strArr != null) {
            String[] strArr2 = new String[strArr.length];
            this.names = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The array of names must not be null");
    }

    public NameFileFilter(List<String> list) {
        this(list, (IOCase) null);
    }

    public NameFileFilter(List<String> list, IOCase iOCase) {
        if (list != null) {
            this.names = (String[]) list.toArray(EMPTY_STRING_ARRAY);
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The list of names must not be null");
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String checkEquals : this.names) {
            if (this.caseSensitivity.checkEquals(name, checkEquals)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (String checkEquals : this.names) {
            if (this.caseSensitivity.checkEquals(str, checkEquals)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.names != null) {
            for (int i = 0; i < this.names.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.names[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
