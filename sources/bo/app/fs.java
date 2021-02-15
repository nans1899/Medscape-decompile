package bo.app;

import com.appboy.models.outgoing.AppboyProperties;

public abstract class fs extends fr implements fl {
    private AppboyProperties a;

    protected fs(AppboyProperties appboyProperties, bz bzVar) {
        super(bzVar);
        this.a = appboyProperties;
    }

    public AppboyProperties f() {
        return this.a;
    }
}
