package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

final /* synthetic */ class ds implements FilenameFilter {
    static final FilenameFilter a = new ds();

    private ds() {
    }

    public final boolean accept(File file, String str) {
        return dt.a.matcher(str).matches();
    }
}
