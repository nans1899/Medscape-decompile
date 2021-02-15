package org.apache.commons.io.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.file.Counters;

public final class PathUtils {
    public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
    public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
    public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
    public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];

    private static class RelativeSortedPaths {
        final boolean equals;
        final List<Path> relativeFileList1;
        final List<Path> relativeFileList2;

        private RelativeSortedPaths(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
            List<Path> list;
            List<Path> list2 = null;
            boolean z = true;
            if (path == null && path2 == null) {
                this.equals = true;
            } else {
                if ((path == null) ^ (path2 == null)) {
                    this.equals = false;
                } else {
                    boolean exists = Files.exists(path, linkOptionArr);
                    boolean exists2 = Files.exists(path2, linkOptionArr);
                    if (!exists || !exists2) {
                        this.equals = (exists || exists2) ? false : z;
                    } else {
                        AccumulatorPathVisitor access$000 = PathUtils.accumulate(path, i, fileVisitOptionArr);
                        AccumulatorPathVisitor access$0002 = PathUtils.accumulate(path2, i, fileVisitOptionArr);
                        if (access$000.getDirList().size() != access$0002.getDirList().size() || access$000.getFileList().size() != access$0002.getFileList().size()) {
                            this.equals = false;
                        } else if (!access$000.relativizeDirectories(path, true, (Comparator<? super Path>) null).equals(access$0002.relativizeDirectories(path2, true, (Comparator<? super Path>) null))) {
                            this.equals = false;
                        } else {
                            List<Path> relativizeFiles = access$000.relativizeFiles(path, true, (Comparator<? super Path>) null);
                            List<Path> relativizeFiles2 = access$0002.relativizeFiles(path2, true, (Comparator<? super Path>) null);
                            this.equals = relativizeFiles.equals(relativizeFiles2);
                            List<Path> list3 = relativizeFiles2;
                            list2 = relativizeFiles;
                            list = list3;
                            this.relativeFileList1 = list2;
                            this.relativeFileList2 = list;
                        }
                    }
                }
            }
            list = null;
            this.relativeFileList1 = list2;
            this.relativeFileList2 = list;
        }
    }

    /* access modifiers changed from: private */
    public static AccumulatorPathVisitor accumulate(Path path, int i, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return (AccumulatorPathVisitor) visitFileTree(AccumulatorPathVisitor.withLongCounters(), path, toFileVisitOptionSet(fileVisitOptionArr), i);
    }

    public static Counters.PathCounters cleanDirectory(Path path) throws IOException {
        return cleanDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters cleanDirectory(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return ((CleaningPathVisitor) visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters copyDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        return ((CopyDirectoryVisitor) visitFileTree(new CopyDirectoryVisitor(Counters.longPathCounters(), path, path2, copyOptionArr), path)).getPathCounters();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0017, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.file.Path copyFile(java.net.URL r0, java.nio.file.Path r1, java.nio.file.CopyOption... r2) throws java.io.IOException {
        /*
            java.io.InputStream r0 = r0.openStream()
            java.nio.file.Files.copy(r0, r1, r2)     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x000c
            r0.close()
        L_0x000c:
            return r1
        L_0x000d:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000f }
        L_0x000f:
            r2 = move-exception
            if (r0 == 0) goto L_0x001a
            r0.close()     // Catch:{ all -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.file.PathUtils.copyFile(java.net.URL, java.nio.file.Path, java.nio.file.CopyOption[]):java.nio.file.Path");
    }

    public static Path copyFileToDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        return Files.copy(path, path2.resolve(path.getFileName()), copyOptionArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r0 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.file.Path copyFileToDirectory(java.net.URL r1, java.nio.file.Path r2, java.nio.file.CopyOption... r3) throws java.io.IOException {
        /*
            java.io.InputStream r0 = r1.openStream()
            java.lang.String r1 = r1.getFile()     // Catch:{ all -> 0x0015 }
            java.nio.file.Path r1 = r2.resolve(r1)     // Catch:{ all -> 0x0015 }
            java.nio.file.Files.copy(r0, r1, r3)     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0014
            r0.close()
        L_0x0014:
            return r2
        L_0x0015:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r2 = move-exception
            if (r0 == 0) goto L_0x0022
            r0.close()     // Catch:{ all -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0022:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.file.PathUtils.copyFileToDirectory(java.net.URL, java.nio.file.Path, java.nio.file.CopyOption[]):java.nio.file.Path");
    }

    public static Counters.PathCounters countDirectory(Path path) throws IOException {
        return ((CountingPathVisitor) visitFileTree(new CountingPathVisitor(Counters.longPathCounters()), path)).getPathCounters();
    }

    public static Counters.PathCounters delete(Path path) throws IOException {
        return delete(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters delete(Path path, DeleteOption... deleteOptionArr) throws IOException {
        if (Files.isDirectory(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            return deleteDirectory(path, deleteOptionArr);
        }
        return deleteFile(path, deleteOptionArr);
    }

    public static Counters.PathCounters deleteDirectory(Path path) throws IOException {
        return deleteDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteDirectory(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return ((DeletingPathVisitor) visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters deleteFile(Path path) throws IOException {
        return deleteFile(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteFile(Path path, DeleteOption... deleteOptionArr) throws IOException {
        if (!Files.isDirectory(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            Counters.PathCounters longPathCounters = Counters.longPathCounters();
            boolean exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
            long size = exists ? Files.size(path) : 0;
            if (overrideReadOnly(deleteOptionArr) && exists) {
                setReadOnly(path, false, LinkOption.NOFOLLOW_LINKS);
            }
            if (Files.deleteIfExists(path)) {
                longPathCounters.getFileCounter().increment();
                longPathCounters.getByteCounter().add(size);
            }
            return longPathCounters;
        }
        throw new NoSuchFileException(path.toString());
    }

    private static boolean overrideReadOnly(DeleteOption[] deleteOptionArr) {
        if (deleteOptionArr == null) {
            return false;
        }
        for (StandardDeleteOption standardDeleteOption : deleteOptionArr) {
            if (standardDeleteOption == StandardDeleteOption.OVERRIDE_READ_ONLY) {
                return true;
            }
        }
        return false;
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2) throws IOException {
        return directoryAndFileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2, LinkOption[] linkOptionArr, OpenOption[] openOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        if (path == null && path2 == null) {
            return true;
        }
        if ((path == null) ^ (path2 == null)) {
            return false;
        }
        if (!Files.exists(path, new LinkOption[0]) && !Files.exists(path2, new LinkOption[0])) {
            return true;
        }
        RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path, path2, Integer.MAX_VALUE, linkOptionArr, fileVisitOptionArr);
        if (!relativeSortedPaths.equals) {
            return false;
        }
        List<Path> list = relativeSortedPaths.relativeFileList1;
        List<Path> list2 = relativeSortedPaths.relativeFileList2;
        for (Path next : list) {
            if (Collections.binarySearch(list2, next) <= -1) {
                throw new IllegalStateException("Unexpected mismatch.");
            } else if (!fileContentEquals(path.resolve(next), path2.resolve(next), linkOptionArr, openOptionArr)) {
                return false;
            }
        }
        return true;
    }

    public static boolean directoryContentEquals(Path path, Path path2) throws IOException {
        return directoryContentEquals(path, path2, Integer.MAX_VALUE, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryContentEquals(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return new RelativeSortedPaths(path, path2, i, linkOptionArr, fileVisitOptionArr).equals;
    }

    public static boolean fileContentEquals(Path path, Path path2) throws IOException {
        return fileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0066, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0067, code lost:
        if (r9 != null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x006d, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r10.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0071, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0074, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0075, code lost:
        if (r8 != null) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x007b, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x007c, code lost:
        r9.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x007f, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean fileContentEquals(java.nio.file.Path r8, java.nio.file.Path r9, java.nio.file.LinkOption[] r10, java.nio.file.OpenOption[] r11) throws java.io.IOException {
        /*
            r0 = 1
            if (r8 != 0) goto L_0x0006
            if (r9 != 0) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            if (r8 != 0) goto L_0x000b
            r2 = 1
            goto L_0x000c
        L_0x000b:
            r2 = 0
        L_0x000c:
            if (r9 != 0) goto L_0x0010
            r3 = 1
            goto L_0x0011
        L_0x0010:
            r3 = 0
        L_0x0011:
            r2 = r2 ^ r3
            if (r2 == 0) goto L_0x0015
            return r1
        L_0x0015:
            java.nio.file.Path r2 = r8.normalize()
            java.nio.file.Path r3 = r9.normalize()
            boolean r4 = java.nio.file.Files.exists(r2, r10)
            boolean r5 = java.nio.file.Files.exists(r3, r10)
            if (r4 == r5) goto L_0x0028
            return r1
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            return r0
        L_0x002b:
            boolean r4 = java.nio.file.Files.isDirectory(r2, r10)
            java.lang.String r5 = "Can't compare directories, only files: "
            if (r4 != 0) goto L_0x0095
            boolean r10 = java.nio.file.Files.isDirectory(r3, r10)
            if (r10 != 0) goto L_0x0080
            long r4 = java.nio.file.Files.size(r2)
            long r6 = java.nio.file.Files.size(r3)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 == 0) goto L_0x0046
            return r1
        L_0x0046:
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004d
            return r0
        L_0x004d:
            java.io.InputStream r8 = java.nio.file.Files.newInputStream(r2, r11)
            java.io.InputStream r9 = java.nio.file.Files.newInputStream(r3, r11)     // Catch:{ all -> 0x0072 }
            boolean r10 = org.apache.commons.io.IOUtils.contentEquals((java.io.InputStream) r8, (java.io.InputStream) r9)     // Catch:{ all -> 0x0064 }
            if (r9 == 0) goto L_0x005e
            r9.close()     // Catch:{ all -> 0x0072 }
        L_0x005e:
            if (r8 == 0) goto L_0x0063
            r8.close()
        L_0x0063:
            return r10
        L_0x0064:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r11 = move-exception
            if (r9 == 0) goto L_0x0071
            r9.close()     // Catch:{ all -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r9 = move-exception
            r10.addSuppressed(r9)     // Catch:{ all -> 0x0072 }
        L_0x0071:
            throw r11     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r10 = move-exception
            if (r8 == 0) goto L_0x007f
            r8.close()     // Catch:{ all -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r8 = move-exception
            r9.addSuppressed(r8)
        L_0x007f:
            throw r10
        L_0x0080:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r5)
            r9.append(r3)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0095:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r5)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.file.PathUtils.fileContentEquals(java.nio.file.Path, java.nio.file.Path, java.nio.file.LinkOption[], java.nio.file.OpenOption[]):boolean");
    }

    public static List<AclEntry> getAclEntryList(Path path) throws IOException {
        AclFileAttributeView aclFileAttributeView = (AclFileAttributeView) Files.getFileAttributeView(path, AclFileAttributeView.class, new LinkOption[0]);
        if (aclFileAttributeView == null) {
            return null;
        }
        return aclFileAttributeView.getAcl();
    }

    public static boolean isEmpty(Path path) throws IOException {
        return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r2 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0029, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isEmptyDirectory(java.nio.file.Path r2) throws java.io.IOException {
        /*
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r2)
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x001c }
            boolean r0 = r0.hasNext()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0015
            r0 = 0
            if (r2 == 0) goto L_0x0014
            r2.close()
        L_0x0014:
            return r0
        L_0x0015:
            if (r2 == 0) goto L_0x001a
            r2.close()
        L_0x001a:
            r2 = 1
            return r2
        L_0x001c:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            if (r2 == 0) goto L_0x0029
            r2.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0029:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.file.PathUtils.isEmptyDirectory(java.nio.file.Path):boolean");
    }

    public static boolean isEmptyFile(Path path) throws IOException {
        return Files.size(path) <= 0;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.util.Comparator<? super java.nio.file.Path>, java.util.Comparator] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<java.nio.file.Path> relativize(java.util.Collection<java.nio.file.Path> r1, java.nio.file.Path r2, boolean r3, java.util.Comparator<? super java.nio.file.Path> r4) {
        /*
            java.util.stream.Stream r1 = r1.stream()
            r2.getClass()
            org.apache.commons.io.file.-$$Lambda$F1-4FczAF_Jk8u2VkahBqQ90mmg r0 = new org.apache.commons.io.file.-$$Lambda$F1-4FczAF_Jk8u2VkahBqQ90mmg
            r0.<init>(r2)
            java.util.stream.Stream r1 = r1.map(r0)
            if (r3 == 0) goto L_0x001d
            if (r4 != 0) goto L_0x0019
            java.util.stream.Stream r1 = r1.sorted()
            goto L_0x001d
        L_0x0019:
            java.util.stream.Stream r1 = r1.sorted(r4)
        L_0x001d:
            java.util.stream.Collector r2 = java.util.stream.Collectors.toList()
            java.lang.Object r1 = r1.collect(r2)
            java.util.List r1 = (java.util.List) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.file.PathUtils.relativize(java.util.Collection, java.nio.file.Path, boolean, java.util.Comparator):java.util.List");
    }

    public static Path setReadOnly(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        DosFileAttributeView dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, linkOptionArr);
        if (dosFileAttributeView != null) {
            dosFileAttributeView.setReadOnly(z);
            return path;
        }
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, linkOptionArr);
        if (posixFileAttributeView != null) {
            Set<PosixFilePermission> permissions = posixFileAttributeView.readAttributes().permissions();
            permissions.remove(PosixFilePermission.OWNER_WRITE);
            permissions.remove(PosixFilePermission.GROUP_WRITE);
            permissions.remove(PosixFilePermission.OTHERS_WRITE);
            return Files.setPosixFilePermissions(path, permissions);
        }
        throw new IOException("No DosFileAttributeView or PosixFileAttributeView for " + path);
    }

    static Set<FileVisitOption> toFileVisitOptionSet(FileVisitOption... fileVisitOptionArr) {
        if (fileVisitOptionArr == null) {
            return EnumSet.noneOf(FileVisitOption.class);
        }
        return (Set) Arrays.stream(fileVisitOptionArr).collect(Collectors.toSet());
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path) throws IOException {
        Files.walkFileTree(path, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path, Set<FileVisitOption> set, int i) throws IOException {
        Files.walkFileTree(path, set, i, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, String str, String... strArr) throws IOException {
        return visitFileTree(t, Paths.get(str, strArr));
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, URI uri) throws IOException {
        return visitFileTree(t, Paths.get(uri));
    }

    private PathUtils() {
    }
}
