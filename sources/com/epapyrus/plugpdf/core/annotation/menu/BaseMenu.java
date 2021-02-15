package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.BaseMenuItem;
import java.util.ArrayList;

public abstract class BaseMenu {
    private Context mContext;
    private LayoutInflater mInflater;
    protected ArrayList<BaseMenuItem> mItemList = new ArrayList<>();
    /* access modifiers changed from: private */
    public MenuSelectListener mListener;
    private PopupWindow mPopupWindow;

    public interface MenuSelectListener {
        void onSelectItem(BaseMenuItem baseMenuItem);
    }

    public BaseMenu(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void setListener(MenuSelectListener menuSelectListener) {
        this.mListener = menuSelectListener;
    }

    public synchronized void show(View view, int i, int i2) {
        int i3;
        int i4;
        View view2 = view;
        synchronized (this) {
            if (this.mItemList.size() > 0) {
                int size = this.mItemList.size();
                View inflate = this.mInflater.inflate(ResourceManager.getLayoutId(this.mContext, "annot_menu"), (ViewGroup) null);
                int i5 = 0;
                if (view.getLeft() <= 0) {
                    int[] iArr = new int[2];
                    view2.getLocationOnScreen(iArr);
                    i3 = iArr[0] - view.getLeft();
                    i4 = iArr[1] - view.getTop();
                } else {
                    i4 = 0;
                    i3 = 0;
                }
                int i6 = -2;
                PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
                this.mPopupWindow = popupWindow;
                popupWindow.setOutsideTouchable(true);
                this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                this.mPopupWindow.showAtLocation(view2, 51, i3 + i, i4 + i2);
                TableLayout tableLayout = (TableLayout) inflate.findViewById(ResourceManager.getId(this.mContext, "annot_menu_table"));
                tableLayout.setBackgroundColor(-1);
                int convertDipToPx = (int) PlugPDFUtility.convertDipToPx(this.mContext, 4.0f);
                int i7 = 0;
                while (i7 < size) {
                    final BaseMenuItem baseMenuItem = this.mItemList.get(i7);
                    TableRow tableRow = new TableRow(this.mContext);
                    tableRow.setPadding(i5, -5, i5, -5);
                    tableRow.setLayoutParams(new ViewGroup.LayoutParams(i6, i6));
                    LinearLayout linearLayout = new LinearLayout(this.mContext);
                    linearLayout.setGravity(17);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i5, i6, 1.0f);
                    layoutParams.setMargins(i5, convertDipToPx, i5, convertDipToPx);
                    Button button = new Button(this.mContext);
                    button.setText(baseMenuItem.textResourceId());
                    button.setTextSize(15.0f);
                    button.setBackgroundColor(-12303292);
                    AnonymousClass1 r9 = r1;
                    final View view3 = view;
                    Button button2 = button;
                    final int i8 = i;
                    LinearLayout.LayoutParams layoutParams2 = layoutParams;
                    final int i9 = i2;
                    AnonymousClass1 r1 = new View.OnClickListener() {
                        public void onClick(View view) {
                            if (baseMenuItem.hasMenu()) {
                                baseMenuItem.setListener(BaseMenu.this.mListener);
                                baseMenuItem.showMenu(view3, i8, i9);
                            } else if (BaseMenu.this.mListener != null) {
                                BaseMenu.this.mListener.onSelectItem(baseMenuItem);
                            }
                            BaseMenu.this.close();
                        }
                    };
                    button2.setOnClickListener(r9);
                    linearLayout.addView(button2, layoutParams2);
                    tableRow.addView(linearLayout);
                    tableLayout.addView(tableRow);
                    i7++;
                    View view4 = view;
                    i5 = 0;
                    i6 = -2;
                }
            }
        }
    }

    public synchronized void close() {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
            this.mPopupWindow = null;
        }
    }
}
