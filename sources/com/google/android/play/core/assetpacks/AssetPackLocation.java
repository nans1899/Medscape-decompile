package com.google.android.play.core.assetpacks;

import com.google.android.play.core.splitcompat.d;

public abstract class AssetPackLocation {
    private static final AssetPackLocation a = new bh(1, (String) null, (String) null);

    static AssetPackLocation a() {
        return a;
    }

    static AssetPackLocation a(String str, String str2) {
        d.a(str, (Object) "STORAGE_FILES location path must be non-null");
        d.a(str, (Object) "STORAGE_FILES assetsPath must be non-null");
        return new bh(0, str, str2);
    }

    public abstract String assetsPath();

    public abstract int packStorageMethod();

    public abstract String path();
}
