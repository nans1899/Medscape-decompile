package mnetinternal;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.media.android.bidder.base.R;

public final class bl {
    private HashMap<String, Object> a = new HashMap<>();
    private bm b;

    private bl() {
    }

    public bm a() {
        return this.b;
    }

    private Map<String, Object> c() {
        return this.a;
    }

    private void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    public void a(bm bmVar) {
        this.b = bmVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bl blVar = (bl) obj;
        if (!this.b.equals(blVar.a())) {
            return false;
        }
        HashMap<String, Object> hashMap = this.a;
        if (hashMap == null || !hashMap.containsKey("is_adapter_child") || !((Boolean) this.a.get("is_adapter_child")).booleanValue()) {
            HashMap<String, Object> hashMap2 = this.a;
            HashMap<String, Object> hashMap3 = blVar.a;
            if (hashMap2 != null) {
                return hashMap2.equals(hashMap3);
            }
            if (hashMap3 == null) {
                return true;
            }
            return false;
        }
        bi.a("###ViewClone##", "View is part of adapter");
        return true;
    }

    public static bl a(bh bhVar, View view) {
        if (view == null) {
            return null;
        }
        bl blVar = new bl();
        bm bmVar = new bm(view, bhVar);
        blVar.a(bmVar);
        if (view.getTag(R.string._mnet_view_id_tag) != null) {
            bmVar.a(((Integer) view.getTag(R.string._mnet_view_id_tag)).intValue());
        }
        if (view instanceof TextView) {
            blVar.a("text", (Object) ((TextView) view).getText());
        }
        if (bv.a(view)) {
            d(blVar);
        } else if ((view instanceof AdapterView) && ((AdapterView) view).getAdapter() != null) {
            c(blVar);
        } else if (bt.a(view)) {
            b(blVar);
        } else if (br.a(view)) {
            a(blVar);
        }
        blVar.a("is_adapter_child", (Object) Boolean.valueOf(ca.a(bhVar.a(), view)));
        bmVar.a(blVar.c());
        return blVar;
    }

    private static void a(bl blVar) {
        blVar.a().c(0);
        blVar.a().b(br.b(blVar.a().c()));
    }

    private static void b(bl blVar) {
        int i;
        bi.a("###ViewClone##", "recycler view clone");
        View c = blVar.a().c();
        int c2 = bt.c(c);
        if (c2 != 0) {
            if (c.getTag(R.string._mnet_page_count_tag) == null) {
                bi.a("###ViewClone##", "Setting page count tag : " + c2);
                c.setTag(R.string._mnet_page_count_tag, Integer.valueOf(c2));
            }
            i = ((Integer) c.getTag(R.string._mnet_page_count_tag)).intValue();
        } else {
            i = 0;
        }
        blVar.a().c(a(c2, i));
        blVar.a().b(i);
        bi.a("###ViewClone##", String.format(Locale.US, "RecyclerView counts : %d %d", new Object[]{Integer.valueOf(blVar.a().d()), Integer.valueOf(blVar.a().e())}));
    }

    private static void c(bl blVar) {
        bi.a("###ViewClone##", "adapter view clone");
        AdapterView adapterView = (AdapterView) blVar.a().c();
        int firstVisiblePosition = adapterView.getFirstVisiblePosition();
        int i = 0;
        int count = adapterView.getAdapter() != null ? adapterView.getAdapter().getCount() : 0;
        if (count != 0) {
            if (adapterView.getTag(R.string._mnet_page_count_tag) == null) {
                bi.a("###ViewClone##", "Setting page count tag : " + count);
                adapterView.setTag(R.string._mnet_page_count_tag, Integer.valueOf(count));
            }
            i = ((Integer) adapterView.getTag(R.string._mnet_page_count_tag)).intValue();
        }
        blVar.a().c(a(firstVisiblePosition, i));
        blVar.a().b(i);
    }

    private static void d(bl blVar) {
        bi.a("###ViewClone##", "view pager clone");
        View c = blVar.a().c();
        blVar.a().c(bv.c(c));
        blVar.a().b(bv.b(c));
        bi.a("###ViewClone##", "View pager count :" + blVar.a().d() + " , " + blVar.a().e());
    }

    private static int a(int i, int i2) {
        if (i2 == 0 || i == i2) {
            return 0;
        }
        return i / i2;
    }

    public boolean b() {
        if (this.a.containsKey("is_adapter_child")) {
            return ((Boolean) this.a.get("is_adapter_child")).booleanValue();
        }
        return false;
    }
}
