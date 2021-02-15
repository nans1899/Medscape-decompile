package com.google.android.gms.internal.ads;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdxk implements FilenameFilter {
    private final Pattern zzhfo;

    public zzdxk(Pattern pattern) {
        this.zzhfo = (Pattern) zzdwd.checkNotNull(pattern);
    }

    public final boolean accept(@NullableDecl File file, String str) {
        return this.zzhfo.matcher(str).matches();
    }
}
