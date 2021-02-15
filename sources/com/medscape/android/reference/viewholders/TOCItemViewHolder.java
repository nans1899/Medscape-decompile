package com.medscape.android.reference.viewholders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.model.Sect1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0014H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0014H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/medscape/android/reference/viewholders/TOCItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "root", "Landroid/widget/LinearLayout;", "getRoot", "()Landroid/widget/LinearLayout;", "setRoot", "(Landroid/widget/LinearLayout;)V", "bindData", "", "section", "Lcom/medscape/android/reference/model/Sect1;", "clickListener", "Lcom/medscape/android/reference/interfaces/ISectionItemClickListener;", "textSize", "", "isNightMode", "", "position", "sectionsSize", "getBackgroundColor", "nightModeOn", "getTextColor", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: TOCItemViewHolder.kt */
public class TOCItemViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout root;

    private final int getBackgroundColor(boolean z) {
        if (z) {
            return ViewCompat.MEASURED_STATE_MASK;
        }
        return -1;
    }

    private final int getTextColor(boolean z) {
        if (z) {
            return -1;
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TOCItemViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
    }

    public final LinearLayout getRoot() {
        return this.root;
    }

    public final void setRoot(LinearLayout linearLayout) {
        this.root = linearLayout;
    }

    public final void bindData(Sect1 sect1, ISectionItemClickListener iSectionItemClickListener, int i, boolean z, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sect1, "section");
        Intrinsics.checkNotNullParameter(iSectionItemClickListener, "clickListener");
        TextView textView = (TextView) this.itemView.findViewById(R.id.section_title);
        View findViewById = this.itemView.findViewById(R.id.list_divider);
        this.root = (LinearLayout) this.itemView.findViewById(R.id.root_toc_item);
        if (i2 == i3) {
            Intrinsics.checkNotNullExpressionValue(findViewById, "divider");
            findViewById.setVisibility(8);
        } else {
            Intrinsics.checkNotNullExpressionValue(findViewById, "divider");
            findViewById.setVisibility(0);
        }
        Intrinsics.checkNotNullExpressionValue(textView, "sectionTitle");
        textView.setText(sect1.title);
        LinearLayout linearLayout = this.root;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(getBackgroundColor(z));
        }
        textView.setTextColor(getTextColor(z));
        textView.setTextSize((float) ContentUtils.getTextFontSize(i));
        this.itemView.setOnClickListener(new TOCItemViewHolder$bindData$1(iSectionItemClickListener, i2));
    }
}
