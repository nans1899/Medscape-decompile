package com.medscape.android.custom;

import android.app.Activity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.medscape.android.R;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomMenu {
    private static volatile boolean mIsShowing = false;
    private static volatile CustomMenu mMenu;
    private static volatile PopupWindow mPopupWindow;
    private Activity mContext = null;
    /* access modifiers changed from: private */
    public OnMenuItemSelectedListener mListener = null;
    private ArrayList<CustomMenuItem> mMenuItems;

    public interface OnMenuItemSelectedListener {
        void MenuItemSelectedEvent(Integer num);
    }

    public static boolean isShowing() {
        return mIsShowing;
    }

    public CustomMenu(Activity activity, OnMenuItemSelectedListener onMenuItemSelectedListener, HashMap<Integer, HashMap<Integer, String>> hashMap) {
        this.mListener = onMenuItemSelectedListener;
        this.mMenuItems = new ArrayList<>();
        this.mContext = activity;
        for (Integer next : hashMap.keySet()) {
            int i = 0;
            HashMap hashMap2 = hashMap.get(next);
            String str = "";
            for (Integer num : hashMap2.keySet()) {
                str = (String) hashMap2.get(num);
                i = num.intValue();
            }
            CustomMenuItem customMenuItem = new CustomMenuItem();
            customMenuItem.setCaption(str);
            customMenuItem.setId(next.intValue());
            customMenuItem.setMenuItem(i);
            this.mMenuItems.add(customMenuItem);
        }
    }

    public void show(View view) {
        mIsShowing = true;
        int size = this.mMenuItems.size();
        if (size >= 1 && mPopupWindow == null) {
            Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
            View inflate = this.mContext.getLayoutInflater().inflate(R.layout.custom_menu, (ViewGroup) null);
            mPopupWindow = new PopupWindow(inflate, -1, -2, false);
            mPopupWindow.setAnimationStyle(R.style.Animations_MenuAnimation);
            mPopupWindow.setWidth(defaultDisplay.getWidth());
            mPopupWindow.showAtLocation(view, 80, 0, 0);
            TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.custom_menu_table);
            tableLayout.removeAllViews();
            for (int i = 0; i < size; i++) {
                TableRow tableRow = new TableRow(this.mContext);
                tableRow.setLayoutParams(new WindowManager.LayoutParams(-1, -2));
                final CustomMenuItem customMenuItem = this.mMenuItems.get(i);
                View inflate2 = this.mContext.getLayoutInflater().inflate(R.layout.custom_menu_item, (ViewGroup) null);
                Button button = (Button) inflate2.findViewById(R.id.custom_menu_item_caption);
                button.setText(customMenuItem.getCaption());
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CustomMenu.this.mListener.MenuItemSelectedEvent(Integer.valueOf(customMenuItem.getMenuItem()));
                        CustomMenu.hide();
                    }
                });
                tableRow.addView(inflate2);
                tableLayout.addView(tableRow);
            }
        }
    }

    public static void hide() {
        mIsShowing = false;
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            mMenu = null;
        }
    }

    public static int getWidth() {
        if (mPopupWindow != null) {
            return mPopupWindow.getContentView().getWidth();
        }
        return 0;
    }

    public static int getHeight() {
        if (mPopupWindow != null) {
            return mPopupWindow.getContentView().getHeight();
        }
        return 0;
    }

    public static synchronized void show(Activity activity, HashMap<Integer, HashMap<Integer, String>> hashMap, OnMenuItemSelectedListener onMenuItemSelectedListener) {
        synchronized (CustomMenu.class) {
            if (mPopupWindow != null) {
                if (mPopupWindow.isShowing()) {
                    mIsShowing = true;
                } else {
                    mIsShowing = false;
                    mPopupWindow = null;
                }
            }
            if (mMenu == null || !isShowing()) {
                CustomMenu customMenu = new CustomMenu(activity, onMenuItemSelectedListener, hashMap);
                mMenu = customMenu;
                customMenu.show(activity.findViewById(16908290).getRootView());
                return;
            }
            hide();
        }
    }

    private class CustomMenuItem {
        private String mCaption;
        private int mId;
        private int menuItem;

        private CustomMenuItem() {
            this.mCaption = null;
            this.mId = -1;
            this.menuItem = -1;
        }

        public void setCaption(String str) {
            this.mCaption = str;
        }

        public String getCaption() {
            return this.mCaption;
        }

        public void setId(int i) {
            this.mId = i;
        }

        public int getId() {
            return this.mId;
        }

        public int getMenuItem() {
            return this.menuItem;
        }

        public void setMenuItem(int i) {
            this.menuItem = i;
        }
    }

    public static int[] getPos() {
        int[] iArr = new int[2];
        if (mPopupWindow != null) {
            mPopupWindow.getContentView().getLocationOnScreen(iArr);
        }
        return iArr;
    }
}
