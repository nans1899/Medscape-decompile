package com.google.android.play.core.internal;

import com.google.android.play.core.splitinstall.v;
import com.medscape.android.settings.Settings;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class ba implements ap {
    ba() {
    }

    static au a() {
        return new ax();
    }

    public static void a(ClassLoader classLoader, Set<File> set, az azVar) {
        if (!set.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (File parentFile : set) {
                hashSet.add(parentFile.getParentFile());
            }
            Object a = av.a(classLoader);
            List a2 = bl.a(a, "nativeLibraryDirectories", List.class).a();
            hashSet.removeAll(a2);
            a2.addAll(hashSet);
            ArrayList arrayList = new ArrayList();
            Object[] a3 = azVar.a(a, new ArrayList(hashSet), arrayList);
            if (!arrayList.isEmpty()) {
                bj bjVar = new bj("Error in makePathElements");
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    cd.a(bjVar, (IOException) arrayList.get(i));
                }
                throw bjVar;
            }
            synchronized (v.class) {
                bl.b(a, "nativeLibraryPathElements", Object.class).b(Arrays.asList(a3));
            }
        }
    }

    public static boolean a(ClassLoader classLoader, File file, File file2, boolean z, String str) {
        return av.a(classLoader, file, file2, z, a(), str, av.b());
    }

    static az b() {
        return new ay();
    }

    public final void a(ClassLoader classLoader, Set<File> set) {
        a(classLoader, set, b());
    }

    public final boolean a(ClassLoader classLoader, File file, File file2, boolean z) {
        return a(classLoader, file, file2, z, Settings.ZIP);
    }
}
