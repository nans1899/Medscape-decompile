package mnetinternal;

import java.util.ArrayList;
import java.util.List;

public final class bp {
    private static bp b = new bp();
    private List<bo> a;

    private bp() {
        b();
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        arrayList.add(new bn());
    }

    public static List<bo> a() {
        return b.a;
    }
}
