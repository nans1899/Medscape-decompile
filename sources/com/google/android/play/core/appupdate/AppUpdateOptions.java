package com.google.android.play.core.appupdate;

public abstract class AppUpdateOptions {

    public static abstract class Builder {
        public abstract void a();

        public abstract AppUpdateOptions build();

        public abstract Builder setAppUpdateType(int i);
    }

    public static AppUpdateOptions defaultOptions(int i) {
        return newBuilder(i).build();
    }

    public static Builder newBuilder(int i) {
        m mVar = new m();
        mVar.setAppUpdateType(i);
        mVar.a();
        return mVar;
    }

    public abstract boolean a();

    public abstract int appUpdateType();
}
