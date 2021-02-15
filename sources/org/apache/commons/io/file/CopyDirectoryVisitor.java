package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

public class CopyDirectoryVisitor extends CountingPathVisitor {
    private static final CopyOption[] EMPTY_COPY_OPTIONS = new CopyOption[0];
    private final CopyOption[] copyOptions;
    private final Path sourceDirectory;
    private final Path targetDirectory;

    public CopyDirectoryVisitor(Counters.PathCounters pathCounters, Path path, Path path2, CopyOption... copyOptionArr) {
        super(pathCounters);
        this.sourceDirectory = path;
        this.targetDirectory = path2;
        this.copyOptions = copyOptionArr == null ? EMPTY_COPY_OPTIONS : (CopyOption[]) copyOptionArr.clone();
    }

    /* access modifiers changed from: protected */
    public void copy(Path path, Path path2) throws IOException {
        Files.copy(path, path2, this.copyOptions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        CopyDirectoryVisitor copyDirectoryVisitor = (CopyDirectoryVisitor) obj;
        if (!Arrays.equals(this.copyOptions, copyDirectoryVisitor.copyOptions) || !Objects.equals(this.sourceDirectory, copyDirectoryVisitor.sourceDirectory) || !Objects.equals(this.targetDirectory, copyDirectoryVisitor.targetDirectory)) {
            return false;
        }
        return true;
    }

    public CopyOption[] getCopyOptions() {
        return (CopyOption[]) this.copyOptions.clone();
    }

    public Path getSourceDirectory() {
        return this.sourceDirectory;
    }

    public Path getTargetDirectory() {
        return this.targetDirectory;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + Arrays.hashCode(this.copyOptions)) * 31) + Objects.hash(new Object[]{this.sourceDirectory, this.targetDirectory});
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        Path resolve = this.targetDirectory.resolve(this.sourceDirectory.relativize(path));
        if (Files.notExists(resolve, new LinkOption[0])) {
            Files.createDirectory(resolve, new FileAttribute[0]);
        }
        return super.preVisitDirectory(path, basicFileAttributes);
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        Path resolve = this.targetDirectory.resolve(this.sourceDirectory.relativize(path));
        copy(path, resolve);
        return super.visitFile(resolve, basicFileAttributes);
    }
}
