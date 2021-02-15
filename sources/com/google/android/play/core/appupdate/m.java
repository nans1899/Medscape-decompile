package com.google.android.play.core.appupdate;

import com.google.android.play.core.appupdate.AppUpdateOptions;

final class m extends AppUpdateOptions.Builder {
    private Integer a;
    private Boolean b;

    m() {
    }

    public final void a() {
        this.b = false;
    }

    public final AppUpdateOptions build() {
        String str = "";
        if (this.a == null) {
            str = str.concat(" appUpdateType");
        }
        if (this.b == null) {
            str = String.valueOf(str).concat(" allowClearStorage");
        }
        if (str.isEmpty()) {
            return new n(this.a.intValue(), this.b.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    public final AppUpdateOptions.Builder setAppUpdateType(int i) {
        this.a = Integer.valueOf(i);
        return this;
    }
}
