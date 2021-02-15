package mnetinternal;

import android.app.Activity;
import java.lang.ref.WeakReference;

public final class bh {
    private WeakReference<Activity> a;

    public bh(Activity activity) {
        this.a = new WeakReference<>(activity);
    }

    public Activity a() {
        return (Activity) this.a.get();
    }
}
