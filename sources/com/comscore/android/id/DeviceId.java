package com.comscore.android.id;

public class DeviceId {
    public final int _source;
    private String a;
    private String b;
    private int c;
    private int d;

    public interface Source {
        public static final int ANDROIDBUILDSERIAL = 1;
        public static final int ANDROIDID = 2;
        public static final int GUID = 0;
    }

    DeviceId(String str, String str2, int i, int i2, int i3) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
        this._source = i3;
    }

    public int getCommonness() {
        return this.c;
    }

    public String getId() {
        return this.b;
    }

    public String getName() {
        return this.a;
    }

    public int getPersistency() {
        return this.d;
    }

    public int getSource() {
        return this._source;
    }

    public String getSuffix() {
        return getCommonness() + "" + getPersistency();
    }
}
