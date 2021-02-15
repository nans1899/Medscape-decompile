package com.webmd.webmdrx.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import com.webmd.webmdrx.R;

public class QuantityAdapter extends ArrayAdapter<String> {
    /* access modifiers changed from: private */
    public CustomQuantityListener mQuantityListener;

    public interface CustomQuantityListener {
        void onCustomQuantity();
    }

    public QuantityAdapter(Context context, String[] strArr, CustomQuantityListener customQuantityListener) {
        super(context, 0, strArr);
        this.mQuantityListener = customQuantityListener;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog_row_view, viewGroup, false);
        CheckedTextView checkedTextView = (CheckedTextView) inflate.findViewById(R.id.c_dialog_text_view);
        checkedTextView.setText((String) getItem(i));
        checkedTextView.setChecked(false);
        if (i == getCount() - 1) {
            checkedTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    QuantityAdapter.this.mQuantityListener.onCustomQuantity();
                }
            });
        }
        return inflate;
    }
}
