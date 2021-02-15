package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class LinkedCalculatorRowItem extends QxRecyclerViewRowItem {
    public Spanned titleSpanned;

    public LinkedCalculatorRowItem(String str) {
        this.titleSpanned = Html.fromHtml(str);
    }

    public int getResourceId() {
        return R.layout.row_item_linked_calculator;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return LinkedCalculatorChoiceViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        LinkedCalculatorChoiceViewHolder linkedCalculatorChoiceViewHolder = (LinkedCalculatorChoiceViewHolder) viewHolder;
        linkedCalculatorChoiceViewHolder.textView.setText(this.titleSpanned);
        int i2 = AnonymousClass1.$SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition[rowPosition.ordinal()];
        if (i2 == 1) {
            linkedCalculatorChoiceViewHolder.separatorView.setVisibility(4);
        } else if (i2 != 2) {
            linkedCalculatorChoiceViewHolder.separatorView.setVisibility(0);
        } else {
            linkedCalculatorChoiceViewHolder.separatorView.setVisibility(4);
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.rowItems.calculator.LinkedCalculatorRowItem$1  reason: invalid class name */
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
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition r1 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.TOP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.calculator.LinkedCalculatorRowItem.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class LinkedCalculatorChoiceViewHolder extends QxRecyclerRowItemViewHolder {
        View separatorView;
        TextView textView;

        public LinkedCalculatorChoiceViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title_text_view);
            this.separatorView = view.findViewById(R.id.separator_view);
        }
    }
}
