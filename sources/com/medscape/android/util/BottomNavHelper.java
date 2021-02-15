package com.medscape.android.util;

import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.reflect.Field;

public class BottomNavHelper {
    public static void removeShiftMode(BottomNavigationView bottomNavigationView) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field declaredField = bottomNavigationMenuView.getClass().getDeclaredField("mShiftingMode");
            declaredField.setAccessible(true);
            declaredField.setBoolean(bottomNavigationMenuView, false);
            declaredField.setAccessible(false);
            for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i);
                bottomNavigationItemView.setShifting(false);
                bottomNavigationItemView.setChecked(bottomNavigationItemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException unused) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException unused2) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
}
