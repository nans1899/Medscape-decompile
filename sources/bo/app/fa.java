package bo.app;

import java.util.List;

public final class fa extends fb implements eq {
    public fa(List<eq> list) {
        super(list);
    }

    public boolean a(fk fkVar) {
        boolean z = false;
        for (eq a : this.a) {
            if (!a.a(fkVar)) {
                return false;
            }
            z = true;
        }
        return z;
    }
}
