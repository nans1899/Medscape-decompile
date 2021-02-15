package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class DefaultFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> DEFAULT_COMPARATOR;
    public static final Comparator<File> DEFAULT_REVERSE;
    private static final long serialVersionUID = 3260141861365313518L;

    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort((List<File>) list);
    }

    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    static {
        DefaultFileComparator defaultFileComparator = new DefaultFileComparator();
        DEFAULT_COMPARATOR = defaultFileComparator;
        DEFAULT_REVERSE = new ReverseFileComparator(defaultFileComparator);
    }

    public int compare(File file, File file2) {
        return file.compareTo(file2);
    }
}
