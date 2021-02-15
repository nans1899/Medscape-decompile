package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

public class DeletingPathVisitor extends CountingPathVisitor {
    private final boolean overrideReadOnly;
    private final String[] skip;

    public static DeletingPathVisitor withBigIntegerCounters() {
        return new DeletingPathVisitor(Counters.bigIntegerPathCounters(), new String[0]);
    }

    public static DeletingPathVisitor withLongCounters() {
        return new DeletingPathVisitor(Counters.longPathCounters(), new String[0]);
    }

    public DeletingPathVisitor(Counters.PathCounters pathCounters, DeleteOption[] deleteOptionArr, String... strArr) {
        super(pathCounters);
        String[] strArr2 = strArr != null ? (String[]) strArr.clone() : EMPTY_STRING_ARRAY;
        Arrays.sort(strArr2);
        this.skip = strArr2;
        this.overrideReadOnly = StandardDeleteOption.overrideReadOnly(deleteOptionArr);
    }

    public DeletingPathVisitor(Counters.PathCounters pathCounters, String... strArr) {
        this(pathCounters, PathUtils.EMPTY_DELETE_OPTION_ARRAY, strArr);
    }

    private boolean accept(Path path) {
        return Arrays.binarySearch(this.skip, Objects.toString(path.getFileName(), (String) null)) < 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        DeletingPathVisitor deletingPathVisitor = (DeletingPathVisitor) obj;
        if (this.overrideReadOnly != deletingPathVisitor.overrideReadOnly || !Arrays.equals(this.skip, deletingPathVisitor.skip)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + Arrays.hashCode(this.skip)) * 31) + Objects.hash(new Object[]{Boolean.valueOf(this.overrideReadOnly)});
    }

    public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
        if (PathUtils.isEmptyDirectory(path)) {
            Files.deleteIfExists(path);
        }
        return super.postVisitDirectory(path, iOException);
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        super.preVisitDirectory(path, basicFileAttributes);
        return accept(path) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (accept(path)) {
            if (Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
                if (this.overrideReadOnly) {
                    PathUtils.setReadOnly(path, false, LinkOption.NOFOLLOW_LINKS);
                }
                Files.deleteIfExists(path);
            }
        }
        updateFileCounters(path, basicFileAttributes);
        return FileVisitResult.CONTINUE;
    }
}
