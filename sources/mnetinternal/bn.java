package mnetinternal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
import net.media.android.bidder.base.R;
import org.json.JSONArray;
import org.json.JSONObject;

class bn implements bo {
    private bh a;
    private boolean b;
    private List<bm> c = new ArrayList();
    private List<bk> d = new ArrayList();
    private JSONObject e;
    private int f = 0;

    bn() {
    }

    private bm a(bm bmVar, bl blVar) {
        View c2 = blVar.a().c();
        if (c2.getParent() == null || c2.getParent().getClass().getName().equals("android.view.ViewRootImpl")) {
            return bmVar;
        }
        if (bmVar == null || bmVar.c() == null || (!bv.a(bmVar.c()) && !(bmVar.c() instanceof AdapterView) && !bt.a(bmVar.c()))) {
            return blVar.a();
        }
        if (bv.a(blVar.a().c()) || (blVar.a().c() instanceof AdapterView) || bt.a(blVar.a().c())) {
            return blVar.a();
        }
        return bmVar;
    }

    private void a(bl blVar) {
        if (blVar.a() != null && ca.a(blVar)) {
            this.c.add(blVar.a());
        }
    }

    private void b(bl blVar) {
        if (blVar.a() != null && ca.a(blVar) && c(blVar)) {
            this.d.add(new bk(blVar.a()));
        }
    }

    private void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.a.a().getWindow().getDecorView();
        bm a2 = bl.a(this.a, (View) viewGroup).a();
        int i = this.f;
        this.f = i + 1;
        a2.a(i);
        if (z) {
            this.e = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            this.e.put("parent", a2.f());
            a(viewGroup, a2, jSONArray);
            this.e.put("child", jSONArray);
            return;
        }
        a(viewGroup);
    }

    private void a(ViewGroup viewGroup, bm bmVar, JSONArray jSONArray) {
        int i;
        int i2;
        if (viewGroup != null) {
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                View childAt = viewGroup.getChildAt(i3);
                if (ca.d(childAt)) {
                    this.b = true;
                } else {
                    bl a2 = bl.a(this.a, childAt);
                    a2.a().a(bmVar);
                    bm a3 = a2.a();
                    if (childAt.getId() == -1) {
                        i = this.f;
                        this.f = i + 1;
                    } else {
                        i = childAt.getId();
                    }
                    a3.a(i);
                    a2.a().c().setTag(R.string._mnet_view_id_tag, Integer.valueOf(a2.a().a()));
                    a(a2);
                    b(a2);
                    if (childAt instanceof ViewGroup) {
                        JSONObject f2 = a2.a().f();
                        JSONArray jSONArray2 = new JSONArray();
                        List<View> a4 = ca.a((ViewGroup) childAt);
                        if (a4 != null) {
                            for (View next : a4) {
                                if (ca.d(childAt)) {
                                    this.b = true;
                                } else if (bt.a(next) || (next instanceof AdapterView) || !(next instanceof ViewGroup)) {
                                    bl a5 = bl.a(this.a, next);
                                    a5.a().a(a(bmVar, a2));
                                    bm a6 = a5.a();
                                    if (next.getId() == -1) {
                                        i2 = this.f;
                                        this.f = i2 + 1;
                                    } else {
                                        i2 = next.getId();
                                    }
                                    a6.a(i2);
                                    a5.a().c().setTag(R.string._mnet_view_id_tag, Integer.valueOf(a5.a().a()));
                                    jSONArray2.put(a5.a().f());
                                    a(a5);
                                    b(a2);
                                } else {
                                    a((ViewGroup) next, a(bmVar, a2), jSONArray2);
                                }
                            }
                        }
                        f2.put("child", jSONArray2);
                        jSONArray.put(f2);
                    } else {
                        jSONArray.put(a2.a().f());
                    }
                }
            }
        }
    }

    private void a(ViewGroup viewGroup) {
        int i;
        List<View> a2;
        int i2;
        if (viewGroup != null) {
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                View childAt = viewGroup.getChildAt(i3);
                bl a3 = bl.a(this.a, childAt);
                bm a4 = a3.a();
                if (childAt.getId() == -1) {
                    i = this.f;
                    this.f = i + 1;
                } else {
                    i = childAt.getId();
                }
                a4.a(i);
                a3.a().c().setTag(R.string._mnet_view_id_tag, Integer.valueOf(a3.a().a()));
                if (c(a3)) {
                    this.d.add(new bk(a3.a()));
                }
                if ((childAt instanceof ViewGroup) && (a2 = ca.a((ViewGroup) childAt)) != null) {
                    for (View next : a2) {
                        if (bt.a(next) || (next instanceof AdapterView) || !(next instanceof ViewGroup)) {
                            bl a5 = bl.a(this.a, next);
                            bm a6 = a5.a();
                            if (next.getId() == -1) {
                                i2 = this.f;
                                this.f = i2 + 1;
                            } else {
                                i2 = next.getId();
                            }
                            a6.a(i2);
                            a5.a().c().setTag(R.string._mnet_view_id_tag, Integer.valueOf(a5.a().a()));
                            if (c(a5)) {
                                this.d.add(new bk(a5.a()));
                            }
                        } else {
                            a((ViewGroup) next);
                        }
                    }
                }
            }
        }
    }

    private boolean c(bl blVar) {
        View c2 = blVar.a().c();
        return bt.a(c2) || (c2 instanceof AdapterView) || bv.a(c2);
    }

    public bd a(Activity activity, boolean z) {
        a();
        try {
            this.a = new bh(activity);
            a(z);
            return new bd(this.a, true, z, a(activity), b(activity), this.d, this.c, this.b, this.e);
        } catch (Exception e2) {
            bi.a("##DefaultParser##", (Throwable) e2);
            return new bd(this.a, false);
        }
    }

    private void a() {
        this.f = 0;
        this.b = false;
        this.e = null;
        this.a = null;
        this.d.clear();
        this.c.clear();
    }

    public String a(Activity activity) {
        return bf.a(activity);
    }

    public String b(Activity activity) {
        return bf.a(activity, this.d);
    }
}
