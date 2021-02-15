package mnetinternal;

import java.lang.ref.WeakReference;

public class cw<T> extends WeakReference<T> {
    public cw(T t) {
        super(t);
    }

    public void a(cz<T> czVar) {
        Object obj = get();
        if (obj == null) {
            czVar.a();
        } else {
            czVar.a(obj);
        }
    }
}
