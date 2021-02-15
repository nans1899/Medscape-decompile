package mnetinternal;

import android.view.View;

public class bs {
    private static Class a = bu.a("com.google.android.material.navigation.NavigationView");

    public static boolean a(View view) {
        Class cls = a;
        return cls != null && cls.isInstance(view);
    }
}
