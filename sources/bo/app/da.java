package bo.app;

import java.util.concurrent.Executor;

public class da implements cx {
    private final d a;
    private final g b;
    private final ad c;
    private final ad d;
    private final Executor e;
    private final dk f;
    private final dq g;
    private final dg h;
    private bq i;

    public da(d dVar, g gVar, ad adVar, ad adVar2, Executor executor, dk dkVar, dq dqVar, dg dgVar) {
        this.a = dVar;
        this.b = gVar;
        this.c = adVar;
        this.d = adVar2;
        this.e = executor;
        this.f = dkVar;
        this.g = dqVar;
        this.h = dgVar;
    }

    public void a(cw cwVar) {
        this.e.execute(a((cv) cwVar));
    }

    public void b(cw cwVar) {
        a((cv) cwVar).run();
    }

    public void a(bq bqVar) {
        this.i = bqVar;
    }

    private cp a(cv cvVar) {
        return new cp(cvVar, this.a, this.b, this.c, this.d, this.f, this.i, this.g, this.h);
    }
}
