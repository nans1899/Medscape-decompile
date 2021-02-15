package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.cf;
import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;
import com.google.android.play.core.splitinstall.z;
import java.util.concurrent.Executor;

public final class bu implements a {
    private final m a;
    private ci<Context> b;
    private ci<dl> c;
    private ci<bb> d;
    private ci<cb> e;
    private ci<ar> f;
    private ci<String> g = cg.a(new s(this.b));
    private ci<w> h = new cf();
    private ci<Executor> i;
    private ci<cr> j;
    private ci<aw> k;
    private ci<bw> l;
    private ci<dv> m;
    private ci<df> n;
    private ci<dj> o;
    private ci<Cdo> p;
    private ci<bn> q;
    private ci<cu> r;
    private ci<bz> s;
    private ci<bq> t;
    private ci<Executor> u;
    private ci<db> v;
    private ci<z> w;
    private ci<i> x;
    private ci<AssetPackManager> y;

    /* synthetic */ bu(m mVar) {
        this.a = mVar;
        r rVar = new r(mVar);
        this.b = rVar;
        ci<dl> a2 = cg.a(new dm(rVar));
        this.c = a2;
        this.d = cg.a(new bd(this.b, a2));
        ci<cb> a3 = cg.a(cc.a);
        this.e = a3;
        this.f = cg.a(new as(this.b, a3));
        ci<Executor> a4 = cg.a(n.a);
        this.i = a4;
        this.j = cg.a(new cs(this.d, this.h, this.e, a4));
        cf cfVar = new cf();
        this.k = cfVar;
        this.l = cg.a(new bx(this.d, this.h, cfVar, this.e));
        this.m = cg.a(new dw(this.d));
        this.n = cg.a(new dg(this.d));
        this.o = cg.a(new dk(this.d, this.h, this.j, this.i, this.e));
        this.p = cg.a(new dp(this.d, this.h));
        ci<bn> a5 = cg.a(new bo(this.h));
        this.q = a5;
        ci<cu> a6 = cg.a(new cv(this.j, this.d, a5));
        this.r = a6;
        this.s = cg.a(new ca(this.j, this.h, this.l, this.m, this.n, this.o, this.p, a6));
        this.t = cg.a(br.a);
        ci<Executor> a7 = cg.a(u.a);
        this.u = a7;
        cf.a(this.k, cg.a(new ax(this.b, this.j, this.s, this.h, this.e, this.t, this.i, a7)));
        ci<db> a8 = cg.a(new dc(this.g, this.k, this.e, this.b, this.c, this.i));
        this.v = a8;
        cf.a(this.h, cg.a(new q(this.b, this.f, a8)));
        ci<z> a9 = cg.a(new t(this.b));
        this.w = a9;
        ci<i> a10 = cg.a(new j(this.d, this.h, this.k, a9, this.j, this.e, this.t, this.i));
        this.x = a10;
        this.y = cg.a(new p(a10, this.b));
    }

    public final AssetPackManager a() {
        return this.y.a();
    }

    public final void a(AssetPackExtractionService assetPackExtractionService) {
        assetPackExtractionService.a = r.a(this.a);
        assetPackExtractionService.b = this.x.a();
        assetPackExtractionService.c = this.d.a();
    }

    public final bb b() {
        return this.d.a();
    }
}
