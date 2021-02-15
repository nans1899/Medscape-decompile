package bo.app;

import java.util.Set;

public class bx implements cb {
    private final Set<bz> a;

    public bx(Set<bz> set) {
        this.a = set;
    }

    public Set<bz> a() {
        return this.a;
    }

    public boolean b() {
        Set<bz> set = this.a;
        return set != null && set.isEmpty();
    }
}
