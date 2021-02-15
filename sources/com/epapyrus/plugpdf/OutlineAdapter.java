package com.epapyrus.plugpdf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.epapyrus.plugpdf.core.OutlineItem;
import java.util.List;

public class OutlineAdapter extends BaseAdapter {
    private final int MAX_DEPS = 8;
    private boolean enableAddBtn;
    private boolean enableRemoveBtn;
    private final LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public final List<OutlineItem> mItems;
    /* access modifiers changed from: private */
    public final OutlintEditListener mListener;

    interface OutlintEditListener {
        void onClickToAdd(List<OutlineItem> list, int i, String str, boolean z);

        void onClickToRemove(List<OutlineItem> list, int i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public OutlineAdapter(LayoutInflater layoutInflater, List<OutlineItem> list, OutlintEditListener outlintEditListener) {
        this.mInflater = layoutInflater;
        this.mItems = list;
        this.mListener = outlintEditListener;
    }

    public int getCount() {
        return this.mItems.size();
    }

    public Object getItem(int i) {
        return this.mItems.get(i);
    }

    public void setEnableAdd(boolean z) {
        this.enableAddBtn = z;
    }

    public void setEnableRemove(boolean z) {
        this.enableRemoveBtn = z;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.panel_outline_item, (ViewGroup) null);
        }
        final Context context = view.getContext();
        int deps = this.mItems.get(i).getDeps();
        if (deps > 8) {
            deps = 8;
        }
        String str = "";
        for (int i2 = 0; i2 < deps; i2++) {
            str = str + "   ";
        }
        String valueOf = String.valueOf(this.mItems.get(i).getPageIdx() + 1);
        ((TextView) view.findViewById(R.id.title)).setText(str + this.mItems.get(i).getTitle());
        ((TextView) view.findViewById(R.id.page)).setText(valueOf);
        if (this.enableAddBtn) {
            ((Button) view.findViewById(R.id.add_btn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.outline_dialog, (ViewGroup) null);
                    final EditText editText = (EditText) inflate.findViewById(R.id.outline_dialog_text_field);
                    final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.outline_dialog_child_check_box);
                    final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
                    inputMethodManager.showSoftInput(editText, 2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(inflate);
                    builder.setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            OutlineAdapter.this.mListener.onClickToAdd(OutlineAdapter.this.mItems, i + 1, editText.getText().toString(), checkBox.isChecked());
                            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }
                    });
                    builder.create().show();
                }
            });
        } else {
            ((Button) view.findViewById(R.id.add_btn)).setVisibility(8);
        }
        if (this.enableRemoveBtn) {
            ((Button) view.findViewById(R.id.remove_btn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OutlineAdapter.this.mListener.onClickToRemove(OutlineAdapter.this.mItems, i);
                }
            });
        } else {
            ((Button) view.findViewById(R.id.remove_btn)).setVisibility(8);
        }
        return view;
    }
}
