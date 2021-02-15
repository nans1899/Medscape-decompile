package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class DirectoryFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> DIRECTORY_COMPARATOR;
    public static final Comparator<File> DIRECTORY_REVERSE;
    private static final int TYPE_DIRECTORY = 1;
    private static final int TYPE_FILE = 2;
    private static final long serialVersionUID = 296132640160964395L;

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
        DirectoryFileComparator directoryFileComparator = new DirectoryFileComparator();
        DIRECTORY_COMPARATOR = directoryFileComparator;
        DIRECTORY_REVERSE = new ReverseFileComparator(directoryFileComparator);
    }

    public int compare(File file, File file2) {
        return getType(file) - getType(file2);
    }

    private int getType(File file) {
        return file.isDirectory() ? 1 : 2;
    }
}
