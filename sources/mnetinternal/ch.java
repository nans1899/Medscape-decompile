package mnetinternal;

import android.content.Context;
import java.lang.ref.WeakReference;

abstract class ch {
    protected cw<Context> a;
    private boolean b;
    private int c;

    /* access modifiers changed from: package-private */
    public abstract void a();

    /* access modifiers changed from: package-private */
    public abstract void a(int i);

    protected ch(Context context, int i) {
        this.a = new cw<>(context);
        this.c = i;
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i) {
        return i == this.c || i == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return !da.a((WeakReference) this.a);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.b = z;
    }
}
