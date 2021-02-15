package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;

public class FileFilterUtils {
    private static final IOFileFilter cvsFilter = notFileFilter(and(directoryFileFilter(), nameFileFilter("CVS")));
    private static final IOFileFilter svnFilter = notFileFilter(and(directoryFileFilter(), nameFileFilter(".svn")));

    public static File[] filter(IOFileFilter iOFileFilter, File... fileArr) {
        if (iOFileFilter == null) {
            throw new IllegalArgumentException("file filter is null");
        } else if (fileArr == null) {
            return FileUtils.EMPTY_FILE_ARRAY;
        } else {
            ArrayList arrayList = new ArrayList();
            int length = fileArr.length;
            int i = 0;
            while (i < length) {
                File file = fileArr[i];
                if (file != null) {
                    if (iOFileFilter.accept(file)) {
                        arrayList.add(file);
                    }
                    i++;
                } else {
                    throw new IllegalArgumentException("file array contains null");
                }
            }
            return (File[]) arrayList.toArray(FileUtils.EMPTY_FILE_ARRAY);
        }
    }

    public static File[] filter(IOFileFilter iOFileFilter, Iterable<File> iterable) {
        return (File[]) filterList(iOFileFilter, iterable).toArray(FileUtils.EMPTY_FILE_ARRAY);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Iterable<java.io.File>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.io.File> filterList(org.apache.commons.io.filefilter.IOFileFilter r1, java.lang.Iterable<java.io.File> r2) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r1 = filter(r1, r2, r0)
            java.util.List r1 = (java.util.List) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.filefilter.FileFilterUtils.filterList(org.apache.commons.io.filefilter.IOFileFilter, java.lang.Iterable):java.util.List");
    }

    public static List<File> filterList(IOFileFilter iOFileFilter, File... fileArr) {
        return Arrays.asList(filter(iOFileFilter, fileArr));
    }

    public static Set<File> filterSet(IOFileFilter iOFileFilter, File... fileArr) {
        return new HashSet(Arrays.asList(filter(iOFileFilter, fileArr)));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Iterable<java.io.File>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Set<java.io.File> filterSet(org.apache.commons.io.filefilter.IOFileFilter r1, java.lang.Iterable<java.io.File> r2) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Collection r1 = filter(r1, r2, r0)
            java.util.Set r1 = (java.util.Set) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.filefilter.FileFilterUtils.filterSet(org.apache.commons.io.filefilter.IOFileFilter, java.lang.Iterable):java.util.Set");
    }

    private static <T extends Collection<File>> T filter(IOFileFilter iOFileFilter, Iterable<File> iterable, T t) {
        if (iOFileFilter != null) {
            if (iterable != null) {
                for (File next : iterable) {
                    if (next == null) {
                        throw new IllegalArgumentException("file collection contains null");
                    } else if (iOFileFilter.accept(next)) {
                        t.add(next);
                    }
                }
            }
            return t;
        }
        throw new IllegalArgumentException("file filter is null");
    }

    public static IOFileFilter prefixFileFilter(String str) {
        return new PrefixFileFilter(str);
    }

    public static IOFileFilter prefixFileFilter(String str, IOCase iOCase) {
        return new PrefixFileFilter(str, iOCase);
    }

    public static IOFileFilter suffixFileFilter(String str) {
        return new SuffixFileFilter(str);
    }

    public static IOFileFilter suffixFileFilter(String str, IOCase iOCase) {
        return new SuffixFileFilter(str, iOCase);
    }

    public static IOFileFilter nameFileFilter(String str) {
        return new NameFileFilter(str);
    }

    public static IOFileFilter nameFileFilter(String str, IOCase iOCase) {
        return new NameFileFilter(str, iOCase);
    }

    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.DIRECTORY;
    }

    public static IOFileFilter fileFileFilter() {
        return FileFileFilter.FILE;
    }

    @Deprecated
    public static IOFileFilter andFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return new AndFileFilter(iOFileFilter, iOFileFilter2);
    }

    @Deprecated
    public static IOFileFilter orFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return new OrFileFilter(iOFileFilter, iOFileFilter2);
    }

    public static IOFileFilter and(IOFileFilter... iOFileFilterArr) {
        return new AndFileFilter(toList(iOFileFilterArr));
    }

    public static IOFileFilter or(IOFileFilter... iOFileFilterArr) {
        return new OrFileFilter(toList(iOFileFilterArr));
    }

    public static List<IOFileFilter> toList(IOFileFilter... iOFileFilterArr) {
        if (iOFileFilterArr != null) {
            ArrayList arrayList = new ArrayList(iOFileFilterArr.length);
            int i = 0;
            while (i < iOFileFilterArr.length) {
                if (iOFileFilterArr[i] != null) {
                    arrayList.add(iOFileFilterArr[i]);
                    i++;
                } else {
                    throw new IllegalArgumentException("The filter[" + i + "] is null");
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("The filters must not be null");
    }

    public static IOFileFilter notFileFilter(IOFileFilter iOFileFilter) {
        return new NotFileFilter(iOFileFilter);
    }

    public static IOFileFilter trueFileFilter() {
        return TrueFileFilter.TRUE;
    }

    public static IOFileFilter falseFileFilter() {
        return FalseFileFilter.FALSE;
    }

    public static IOFileFilter asFileFilter(FileFilter fileFilter) {
        return new DelegateFileFilter(fileFilter);
    }

    public static IOFileFilter asFileFilter(FilenameFilter filenameFilter) {
        return new DelegateFileFilter(filenameFilter);
    }

    public static IOFileFilter ageFileFilter(long j) {
        return new AgeFileFilter(j);
    }

    public static IOFileFilter ageFileFilter(long j, boolean z) {
        return new AgeFileFilter(j, z);
    }

    public static IOFileFilter ageFileFilter(Date date) {
        return new AgeFileFilter(date);
    }

    public static IOFileFilter ageFileFilter(Date date, boolean z) {
        return new AgeFileFilter(date, z);
    }

    public static IOFileFilter ageFileFilter(File file) {
        return new AgeFileFilter(file);
    }

    public static IOFileFilter ageFileFilter(File file, boolean z) {
        return new AgeFileFilter(file, z);
    }

    public static IOFileFilter sizeFileFilter(long j) {
        return new SizeFileFilter(j);
    }

    public static IOFileFilter sizeFileFilter(long j, boolean z) {
        return new SizeFileFilter(j, z);
    }

    public static IOFileFilter sizeRangeFileFilter(long j, long j2) {
        return new AndFileFilter(new SizeFileFilter(j, true), new SizeFileFilter(j2 + 1, false));
    }

    public static IOFileFilter magicNumberFileFilter(String str) {
        return new MagicNumberFileFilter(str);
    }

    public static IOFileFilter magicNumberFileFilter(String str, long j) {
        return new MagicNumberFileFilter(str, j);
    }

    public static IOFileFilter magicNumberFileFilter(byte[] bArr) {
        return new MagicNumberFileFilter(bArr);
    }

    public static IOFileFilter magicNumberFileFilter(byte[] bArr, long j) {
        return new MagicNumberFileFilter(bArr, j);
    }

    public static IOFileFilter makeCVSAware(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            return cvsFilter;
        }
        return and(iOFileFilter, cvsFilter);
    }

    public static IOFileFilter makeSVNAware(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            return svnFilter;
        }
        return and(iOFileFilter, svnFilter);
    }

    public static IOFileFilter makeDirectoryOnly(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            return DirectoryFileFilter.DIRECTORY;
        }
        return new AndFileFilter(DirectoryFileFilter.DIRECTORY, iOFileFilter);
    }

    public static IOFileFilter makeFileOnly(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            return FileFileFilter.FILE;
        }
        return new AndFileFilter(FileFileFilter.FILE, iOFileFilter);
    }
}
