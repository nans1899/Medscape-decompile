package com.comscore.android.id;

public class CrossPublisherId {
    public final String crossPublisherId;
    public final int source;

    public interface Source {
        public static final int ADID = 1;
        public static final int ANDROIDBUILDSERIAL = 2;
        public static final int ANDROIDID = 3;
        public static final int NotPresent = 0;
    }

    CrossPublisherId(String str, int i) {
        this.crossPublisherId = str;
        this.source = i;
    }
}
