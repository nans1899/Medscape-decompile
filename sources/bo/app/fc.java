package bo.app;

import java.util.List;

public final class fc extends fb implements eq {
    public fc(List<eq> list) {
        super(list);
    }

    public boolean a(fk fkVar) {
        for (eq a : this.a) {
            if (a.a(fkVar)) {
                return true;
            }
        }
        return false;
    }
}
