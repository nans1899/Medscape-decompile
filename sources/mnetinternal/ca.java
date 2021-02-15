package mnetinternal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ca {
    public static String a(View view) {
        try {
            if (view.getId() != -1) {
                return view.getContext().getResources().getResourceName(view.getId());
            }
        } catch (Exception unused) {
        }
        return view.getClass().getName();
    }

    public static String a(View view, int i) {
        try {
            if (view.getId() != -1) {
                return view.getContext().getResources().getResourceName(view.getId());
            }
            if (i == -1) {
                return view.getClass().getName();
            }
            return view.getClass().getName() + ":id/" + i;
        } catch (Exception unused) {
            return view.getClass().getName();
        }
    }

    public static String b(View view) {
        if (view == null) {
            return String.format(Locale.ENGLISH, "x:%d,y:%d,w:%d,h:%d", new Object[]{0, 0, 0, 0});
        }
        DisplayMetrics a = a(view.getContext());
        int i = a.widthPixels;
        int i2 = a.heightPixels;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return String.format(Locale.ENGLISH, "x:%d,y:%d,w:%d,h:%d", new Object[]{Integer.valueOf(a(iArr[0], i)), Integer.valueOf(a(iArr[1], i2)), Integer.valueOf(a(view.getWidth(), i)), Integer.valueOf(a(view.getHeight(), i2))});
    }

    private static int a(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        return Double.valueOf(((((double) i) * 1.0d) / ((double) i2)) * 100.0d).intValue();
    }

    public static DisplayMetrics a(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context == null || (windowManager = (WindowManager) context.getSystemService("window")) == null) {
            return displayMetrics;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static View b(Activity activity, View view) {
        ViewParent parent;
        if (view == null || (parent = view.getParent()) == null) {
            return null;
        }
        if ((parent instanceof AdapterView) || bt.a(parent)) {
            return view;
        }
        if (parent.equals(activity.getWindow().getDecorView()) || parent.getClass().getName().equals("android.view.ViewRootImpl") || parent.getClass().getName().equals("android.view.ViewRoot")) {
            return null;
        }
        return b(activity, (View) parent);
    }

    private static String a(Class cls) {
        if (cls == null) {
            return null;
        }
        String canonicalName = cls.getCanonicalName();
        if (canonicalName.equals("androidx.viewpager.widget.ViewPager")) {
            return "androidx.viewpager.widget.ViewPager";
        }
        if (canonicalName.equals(AdapterView.class.getCanonicalName())) {
            return AdapterView.class.getCanonicalName();
        }
        if (canonicalName.equals("androidx.recyclerview.widget.RecyclerView")) {
            return "androidx.recyclerview.widget.RecyclerView";
        }
        if (canonicalName.equals("androidx.swiperefreshlayout.widget.SwipeRefreshLayout")) {
            return "androidx.swiperefreshlayout.widget.SwipeRefreshLayout";
        }
        if (canonicalName.equals(View.class.getCanonicalName())) {
            return View.class.getCanonicalName();
        }
        if (canonicalName.equals(ViewGroup.class.getCanonicalName())) {
            return ViewGroup.class.getCanonicalName();
        }
        if (canonicalName.equals("androidx.appcompat.widget.ActionMenuView")) {
            return "androidx.appcompat.widget.ActionMenuView";
        }
        if (Build.VERSION.SDK_INT < 21 || !canonicalName.equals(ActionMenuView.class.getCanonicalName())) {
            return a(cls.getSuperclass());
        }
        return ActionMenuView.class.getCanonicalName();
    }

    public static String c(View view) {
        return a((Class) view.getClass());
    }

    public static boolean a(Activity activity, View view) {
        return b(activity, view) != null;
    }

    public static List<View> a(ViewGroup viewGroup) {
        if (bs.a(viewGroup)) {
            bi.a("##ViewUtils##", "Skipping Drawer");
            return null;
        } else if (bv.a(viewGroup)) {
            int childCount = viewGroup.getChildCount();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                int[] iArr = new int[2];
                childAt.getLocationOnScreen(iArr);
                if (iArr[0] >= 0 && iArr[0] < viewGroup.getWidth()) {
                    arrayList.add(childAt);
                }
            }
            bi.a("##ViewUtils##", "view pager childs: " + arrayList.size());
            return arrayList;
        } else if (viewGroup instanceof AdapterView) {
            return null;
        } else {
            if (!bt.a(viewGroup) || !bt.b(viewGroup)) {
                return Collections.singletonList(viewGroup);
            }
            return null;
        }
    }

    public static boolean d(View view) {
        String canonicalName = view.getClass().getCanonicalName();
        if (!canonicalName.contains("MNetView") && !canonicalName.contains("PublisherAdView") && !canonicalName.contains("MoPubView")) {
            return false;
        }
        bi.a("##ViewUtils##", "skipping ad view: " + a(view));
        return true;
    }

    public static boolean a(bl blVar) {
        View c = blVar.a().c();
        if (br.a(c) || bt.a(c) || (c instanceof AdapterView) || bv.a(c)) {
            return true;
        }
        if ((c instanceof EditText) || blVar.b()) {
            return false;
        }
        return c.isClickable();
    }
}
