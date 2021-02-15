package bo.app;

import com.appboy.support.AppboyLogger;

public class cy implements cx {
    private static final String a = AppboyLogger.getAppboyLogTag(cy.class);
    private final ad b;

    public cy(ad adVar) {
        this.b = adVar;
    }

    public void a(cw cwVar) {
        c(cwVar);
    }

    public void b(cw cwVar) {
        c(cwVar);
    }

    private void c(cw cwVar) {
        AppboyLogger.i(a, "Short circuiting execution of network request and immediately marking it as succeeded.", false);
        cwVar.a(this.b, (cl) null);
        cwVar.a(this.b);
        this.b.a(new af((cv) cwVar), af.class);
    }
}
