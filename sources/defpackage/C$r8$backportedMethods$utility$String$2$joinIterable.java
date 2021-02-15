package defpackage;

import java.util.Iterator;

/* renamed from: $r8$backportedMethods$utility$String$2$joinIterable  reason: invalid class name and default package */
public /* synthetic */ class C$r8$backportedMethods$utility$String$2$joinIterable {
    public static /* synthetic */ String join(CharSequence charSequence, Iterable iterable) {
        if (charSequence != null) {
            StringBuilder sb = new StringBuilder();
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                while (true) {
                    sb.append((CharSequence) it.next());
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(charSequence);
                }
            }
            return sb.toString();
        }
        throw new NullPointerException("delimiter");
    }
}
