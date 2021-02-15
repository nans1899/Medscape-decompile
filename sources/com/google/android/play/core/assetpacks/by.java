package com.google.android.play.core.assetpacks;

final class by extends RuntimeException {
    final int a;

    by(String str) {
        super(str);
        this.a = -1;
    }

    by(String str, int i) {
        super(str);
        this.a = i;
    }

    by(String str, Exception exc) {
        super(str, exc);
        this.a = -1;
    }

    by(String str, Exception exc, int i) {
        super(str, exc);
        this.a = i;
    }
}
