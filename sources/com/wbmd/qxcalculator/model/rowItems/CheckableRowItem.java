package com.wbmd.qxcalculator.model.rowItems;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.rowItems.CheckableSingleRowItem;

public class CheckableRowItem extends QxRecyclerViewRowItem {
    public String title;

    public CheckableRowItem(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public int getResourceId() {
        return R.layout.row_item_checkable_single;
    }

    public int getResourceForRadioButton() {
        return R.id.radio_button;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CheckableSingleRowItem.CheckableSingleViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CheckableSingleRowItem.CheckableSingleViewHolder checkableSingleViewHolder = (CheckableSingleRowItem.CheckableSingleViewHolder) viewHolder;
        checkableSingleViewHolder.titleTextView.setText(this.title);
        checkableSingleViewHolder.radioButton.setChecked(this.isSelected);
        int i2 = AnonymousClass1.$SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition[rowPosition.ordinal()];
        if (i2 == 1) {
            checkableSingleViewHolder.separatorView.setVisibility(4);
        } else if (i2 != 2) {
            checkableSingleViewHolder.separatorView.setVisibility(0);
        } else {
            checkableSingleViewHolder.separatorView.setVisibility(4);
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.rowItems.CheckableRowItem$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition[] r0 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition = r0
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition r1 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition     // Catch:{ NoSuchFieldError -> 0x001d }
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition r1 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.BOTTOM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.CheckableRowItem.AnonymousClass1.<clinit>():void");
        }
    }

    public static int getRadioButtonResourceId() {
        return R.id.radio_button;
    }

    public static final class CheckableSingleViewHolder extends QxRecyclerRowItemViewHolder {
        RadioButton radioButton;
        View separatorView;
        TextView titleTextView;

        public CheckableSingleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.textview);
            this.radioButton = (RadioButton) view.findViewById(R.id.radio_button);
            this.separatorView = view.findViewById(R.id.separator_view);
        }
    }
}
