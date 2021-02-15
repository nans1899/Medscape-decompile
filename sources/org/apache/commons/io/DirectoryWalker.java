package org.apache.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public abstract class DirectoryWalker<T> {
    private final int depthLimit;
    private final FileFilter filter;

    /* access modifiers changed from: protected */
    public File[] filterDirectoryContents(File file, int i, File... fileArr) throws IOException {
        return fileArr;
    }

    /* access modifiers changed from: protected */
    public boolean handleDirectory(File file, int i, Collection<T> collection) throws IOException {
        return true;
    }

    /* access modifiers changed from: protected */
    public void handleDirectoryEnd(File file, int i, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleDirectoryStart(File file, int i, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleEnd(Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleFile(File file, int i, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public boolean handleIsCancelled(File file, int i, Collection<T> collection) throws IOException {
        return false;
    }

    /* access modifiers changed from: protected */
    public void handleRestricted(File file, int i, Collection<T> collection) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleStart(File file, Collection<T> collection) throws IOException {
    }

    protected DirectoryWalker() {
        this((FileFilter) null, -1);
    }

    protected DirectoryWalker(FileFilter fileFilter, int i) {
        this.filter = fileFilter;
        this.depthLimit = i;
    }

    protected DirectoryWalker(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2, int i) {
        if (iOFileFilter == null && iOFileFilter2 == null) {
            this.filter = null;
        } else {
            this.filter = FileFilterUtils.or(FileFilterUtils.makeDirectoryOnly(iOFileFilter == null ? TrueFileFilter.TRUE : iOFileFilter), FileFilterUtils.makeFileOnly(iOFileFilter2 == null ? TrueFileFilter.TRUE : iOFileFilter2));
        }
        this.depthLimit = i;
    }

    /* access modifiers changed from: protected */
    public final void walk(File file, Collection<T> collection) throws IOException {
        Objects.requireNonNull(file, "startDirectory");
        try {
            handleStart(file, collection);
            walk(file, 0, collection);
            handleEnd(collection);
        } catch (CancelException e) {
            handleCancelled(file, collection, e);
        }
    }

    private void walk(File file, int i, Collection<T> collection) throws IOException {
        checkIfCancelled(file, i, collection);
        if (handleDirectory(file, i, collection)) {
            handleDirectoryStart(file, i, collection);
            int i2 = i + 1;
            int i3 = this.depthLimit;
            if (i3 < 0 || i2 <= i3) {
                checkIfCancelled(file, i, collection);
                FileFilter fileFilter = this.filter;
                File[] filterDirectoryContents = filterDirectoryContents(file, i, fileFilter == null ? file.listFiles() : file.listFiles(fileFilter));
                if (filterDirectoryContents == null) {
                    handleRestricted(file, i2, collection);
                } else {
                    for (File file2 : filterDirectoryContents) {
                        if (file2.isDirectory()) {
                            walk(file2, i2, collection);
                        } else {
                            checkIfCancelled(file2, i2, collection);
                            handleFile(file2, i2, collection);
                            checkIfCancelled(file2, i2, collection);
                        }
                    }
                }
            }
            handleDirectoryEnd(file, i, collection);
        }
        checkIfCancelled(file, i, collection);
    }

    /* access modifiers changed from: protected */
    public final void checkIfCancelled(File file, int i, Collection<T> collection) throws IOException {
        if (handleIsCancelled(file, i, collection)) {
            throw new CancelException(file, i);
        }
    }

    /* access modifiers changed from: protected */
    public void handleCancelled(File file, Collection<T> collection, CancelException cancelException) throws IOException {
        throw cancelException;
    }

    public static class CancelException extends IOException {
        private static final long serialVersionUID = 1347339620135041008L;
        private final int depth;
        private final File file;

        public CancelException(File file2, int i) {
            this("Operation Cancelled", file2, i);
        }

        public CancelException(String str, File file2, int i) {
            super(str);
            this.file = file2;
            this.depth = i;
        }

        public File getFile() {
            return this.file;
        }

        public int getDepth() {
            return this.depth;
        }
    }
}
