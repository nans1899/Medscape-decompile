package com.wbmd.qxcalculator.model.rowItems;

import android.view.View;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class LabelValueDeletableRowItem extends QxRecyclerViewRowItem {
    public static final String kAccessoryTagDeleteButton = "kAccessoryTagDeleteButton";
    private QxRecyclerViewAdapter adapter;
    public Object payload;
    public boolean showDeleteButton;
    public String title;
    public String value;
    private int valueGravity;

    public LabelValueDeletableRowItem(String str, String str2, boolean z, Object obj) {
        this(str, str2, z, GravityCompat.START, obj);
    }

    public LabelValueDeletableRowItem(String str, String str2, boolean z, int i, Object obj) {
        this.title = str;
        this.value = str2;
        this.showDeleteButton = z;
        this.valueGravity = i;
        this.payload = obj;
        if (obj instanceof String) {
            this.tag = (String) obj;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public int getResourceId() {
        return R.layout.row_item_label_value_deletable;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return LabelValueDeletableViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        LabelValueDeletableViewHolder labelValueDeletableViewHolder = (LabelValueDeletableViewHolder) viewHolder;
        this.adapter = qxRecyclerViewAdapter;
        labelValueDeletableViewHolder.rowItem = this;
        labelValueDeletableViewHolder.labelTextView.setText(this.title);
        labelValueDeletableViewHolder.valueTextView.setText(this.value);
        labelValueDeletableViewHolder.valueTextView.setGravity(this.valueGravity);
        labelValueDeletableViewHolder.deleteButton.setVisibility(this.showDeleteButton ? 0 : 8);
        int i2 = AnonymousClass1.$SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition[rowPosition.ordinal()];
        if (i2 == 1) {
            labelValueDeletableViewHolder.separatorView.setVisibility(4);
        } else if (i2 != 2) {
            labelValueDeletableViewHolder.separatorView.setVisibility(0);
        } else {
            labelValueDeletableViewHolder.separatorView.setVisibility(4);
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.rowItems.LabelValueDeletableRowItem$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.LabelValueDeletableRowItem.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void onDeleteButtonPressed(View view) {
        QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        qxRecyclerViewAdapter.onAccessoryViewClicked(view, qxRecyclerViewAdapter.getPositionForRowItem(this));
    }

    public static final class LabelValueDeletableViewHolder extends QxRecyclerRowItemViewHolder {
        View contentContainer;
        View deleteButton;
        TextView labelTextView;
        int measuredWidth;
        LabelValueDeletableRowItem rowItem;
        View separatorView;
        TextView valueTextView;

        public LabelValueDeletableViewHolder(View view) {
            super(view);
            this.labelTextView = (TextView) view.findViewById(R.id.label_text_view);
            this.valueTextView = (TextView) view.findViewById(R.id.value_text_view);
            this.contentContainer = view.findViewById(R.id.content_container);
            View findViewById = view.findViewById(R.id.delete_button);
            this.deleteButton = findViewById;
            findViewById.setTag(LabelValueDeletableRowItem.kAccessoryTagDeleteButton);
            this.deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (LabelValueDeletableViewHolder.this.rowItem != null) {
                        LabelValueDeletableViewHolder.this.rowItem.onDeleteButtonPressed(view);
                    }
                }
            });
            this.separatorView = view.findViewById(R.id.separator_view);
        }
    }
}
