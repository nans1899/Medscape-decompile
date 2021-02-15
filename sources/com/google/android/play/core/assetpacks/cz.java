package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

final /* synthetic */ class cz implements FilenameFilter {
    private final String a;

    cz(String str) {
        this.a = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(String.valueOf(this.a).concat("-")) && str.endsWith(".apk");
    }
}
