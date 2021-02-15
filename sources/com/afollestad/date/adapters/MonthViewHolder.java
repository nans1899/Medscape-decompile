package com.afollestad.date.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.date.util.DebouncerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/afollestad/date/adapters/MonthViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "adapter", "Lcom/afollestad/date/adapters/MonthAdapter;", "(Landroid/view/View;Lcom/afollestad/date/adapters/MonthAdapter;)V", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthAdapter.kt */
public final class MonthViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final MonthAdapter adapter;
    private final TextView textView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MonthViewHolder(View view, MonthAdapter monthAdapter) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(monthAdapter, "adapter");
        this.adapter = monthAdapter;
        this.textView = (TextView) view;
        DebouncerKt.onClickDebounced(view, new Function1<View, Unit>(this) {
            final /* synthetic */ MonthViewHolder this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((View) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(View view) {
                Intrinsics.checkParameterIsNotNull(view, "it");
                this.this$0.adapter.onRowClicked$com_afollestad_date_picker(this.this$0.getAdapterPosition());
            }
        });
    }

    public final TextView getTextView() {
        return this.textView;
    }
}
