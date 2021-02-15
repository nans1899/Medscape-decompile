package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final Counters.PathCounters pathCounters;

    public static CountingPathVisitor withBigIntegerCounters() {
        return new CountingPathVisitor(Counters.bigIntegerPathCounters());
    }

    public static CountingPathVisitor withLongCounters() {
        return new CountingPathVisitor(Counters.longPathCounters());
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters2) {
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters2, "pathCounter");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CountingPathVisitor)) {
            return false;
        }
        return Objects.equals(this.pathCounters, ((CountingPathVisitor) obj).pathCounters);
    }

    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pathCounters});
    }

    public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
        this.pathCounters.getDirectoryCounter().increment();
        return FileVisitResult.CONTINUE;
    }

    public String toString() {
        return this.pathCounters.toString();
    }

    /* access modifiers changed from: protected */
    public void updateFileCounters(Path path, BasicFileAttributes basicFileAttributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(basicFileAttributes.size());
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (Files.exists(path, new LinkOption[0])) {
            updateFileCounters(path, basicFileAttributes);
        }
        return FileVisitResult.CONTINUE;
    }
}
