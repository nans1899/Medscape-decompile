package com.medscape.android.activity.help.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.R;
import com.medscape.android.activity.help.models.HelpMainItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\u0010\u0007J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/medscape/android/activity/help/adapters/HelpMainAdapter;", "Landroid/widget/ArrayAdapter;", "Lcom/medscape/android/activity/help/models/HelpMainItem;", "mContext", "Landroid/content/Context;", "mItems", "", "(Landroid/content/Context;Ljava/util/List;)V", "getMItems", "()Ljava/util/List;", "setMItems", "(Ljava/util/List;)V", "getView", "Landroid/view/View;", "position", "", "convertView", "parent", "Landroid/view/ViewGroup;", "setItems", "", "items", "ViewHolder", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpMainAdapter.kt */
public final class HelpMainAdapter extends ArrayAdapter<HelpMainItem> {
    private List<HelpMainItem> mItems;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HelpMainAdapter(Context context, List<HelpMainItem> list) {
        super(context, 0, list);
        Intrinsics.checkNotNullParameter(context, "mContext");
        Intrinsics.checkNotNullParameter(list, "mItems");
        this.mItems = list;
    }

    public final List<HelpMainItem> getMItems() {
        return this.mItems;
    }

    public final void setMItems(List<HelpMainItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mItems = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.single_textview_row_no_arrow, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(view, "LayoutInflater.from(cont…_no_arrow, parent, false)");
            viewHolder = new ViewHolder();
            View findViewById = view.findViewById(R.id.rowTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.rowTitle)");
            viewHolder.setTextTitle((TextView) findViewById);
            view.setTag(viewHolder);
        } else {
            Object tag = view.getTag();
            if (tag != null) {
                viewHolder = (ViewHolder) tag;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.help.adapters.HelpMainAdapter.ViewHolder");
            }
        }
        HelpMainItem helpMainItem = (HelpMainItem) getItem(i);
        viewHolder.getTextTitle().setText(helpMainItem != null ? helpMainItem.getTitle() : null);
        return view;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/medscape/android/activity/help/adapters/HelpMainAdapter$ViewHolder;", "", "(Lcom/medscape/android/activity/help/adapters/HelpMainAdapter;)V", "textTitle", "Landroid/widget/TextView;", "getTextTitle", "()Landroid/widget/TextView;", "setTextTitle", "(Landroid/widget/TextView;)V", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HelpMainAdapter.kt */
    public final class ViewHolder {
        public TextView textTitle;

        public ViewHolder() {
        }

        public final TextView getTextTitle() {
            TextView textView = this.textTitle;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textTitle");
            }
            return textView;
        }

        public final void setTextTitle(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.textTitle = textView;
        }
    }

    public final void setItems(List<HelpMainItem> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        this.mItems = list;
        notifyDataSetChanged();
    }
}
