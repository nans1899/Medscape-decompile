package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

public class AccumulatorPathVisitor extends CountingPathVisitor {
    private final List<Path> dirList = new ArrayList();
    private final List<Path> fileList = new ArrayList();

    public static AccumulatorPathVisitor withBigIntegerCounters() {
        return new AccumulatorPathVisitor(Counters.bigIntegerPathCounters());
    }

    public static AccumulatorPathVisitor withLongCounters() {
        return new AccumulatorPathVisitor(Counters.longPathCounters());
    }

    public AccumulatorPathVisitor(Counters.PathCounters pathCounters) {
        super(pathCounters);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof AccumulatorPathVisitor)) {
            return false;
        }
        AccumulatorPathVisitor accumulatorPathVisitor = (AccumulatorPathVisitor) obj;
        if (!Objects.equals(this.dirList, accumulatorPathVisitor.dirList) || !Objects.equals(this.fileList, accumulatorPathVisitor.fileList)) {
            return false;
        }
        return true;
    }

    public List<Path> getDirList() {
        return this.dirList;
    }

    public List<Path> getFileList() {
        return this.fileList;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + Objects.hash(new Object[]{this.dirList, this.fileList});
    }

    public List<Path> relativizeDirectories(Path path, boolean z, Comparator<? super Path> comparator) {
        return PathUtils.relativize(getDirList(), path, z, comparator);
    }

    public List<Path> relativizeFiles(Path path, boolean z, Comparator<? super Path> comparator) {
        return PathUtils.relativize(getFileList(), path, z, comparator);
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        (Files.isDirectory(path, new LinkOption[0]) ? this.dirList : this.fileList).add(path.normalize());
        return super.visitFile(path, basicFileAttributes);
    }
}
