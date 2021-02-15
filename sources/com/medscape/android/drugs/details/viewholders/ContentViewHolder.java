package com.medscape.android.drugs.details.viewholders;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0018R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/medscape/android/drugs/details/viewholders/ContentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "listener", "Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;", "(Landroid/view/View;Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;)V", "content", "Landroid/widget/TextView;", "getContent", "()Landroid/widget/TextView;", "setContent", "(Landroid/widget/TextView;)V", "crossLinkIcon", "Landroid/widget/ImageView;", "getCrossLinkIcon", "()Landroid/widget/ImageView;", "setCrossLinkIcon", "(Landroid/widget/ImageView;)V", "getListener", "()Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;", "getView", "()Landroid/view/View;", "bindContent", "", "item", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "enableTextSelection", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContentViewHolder.kt */
public final class ContentViewHolder extends RecyclerView.ViewHolder {
    private TextView content;
    private ImageView crossLinkIcon;
    private final DataViewHolder.DataListClickListener listener;
    private final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentViewHolder(View view2, DataViewHolder.DataListClickListener dataListClickListener) {
        super(view2);
        Intrinsics.checkNotNullParameter(view2, "view");
        this.view = view2;
        this.listener = dataListClickListener;
        View findViewById = view2.findViewById(R.id.text);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.text)");
        this.content = (TextView) findViewById;
        View findViewById2 = this.view.findViewById(R.id.crossLinkIcon);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.crossLinkIcon)");
        this.crossLinkIcon = (ImageView) findViewById2;
    }

    public final DataViewHolder.DataListClickListener getListener() {
        return this.listener;
    }

    public final View getView() {
        return this.view;
    }

    public final TextView getContent() {
        return this.content;
    }

    public final void setContent(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.content = textView;
    }

    public final ImageView getCrossLinkIcon() {
        return this.crossLinkIcon;
    }

    public final void setCrossLinkIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.crossLinkIcon = imageView;
    }

    public final void bindContent(LineItem lineItem) {
        CrossLink crossLink = null;
        this.content.setText(lineItem != null ? lineItem.getText() : null);
        this.content.setMovementMethod(LinkMovementMethod.getInstance());
        if (lineItem != null) {
            crossLink = lineItem.getCrossLink();
        }
        if (crossLink != null) {
            this.crossLinkIcon.setOnClickListener(new ContentViewHolder$bindContent$1(this));
            if (crossLink.type == CrossLink.Type.CALC) {
                this.crossLinkIcon.setVisibility(0);
                this.crossLinkIcon.setImageResource(R.drawable.calculator);
            } else {
                this.crossLinkIcon.setVisibility(0);
            }
        } else {
            this.crossLinkIcon.setVisibility(8);
        }
        if (lineItem != null) {
            View view2 = this.view;
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            view2.setPadding((int) Util.dpToPixel(view3.getContext(), lineItem.getIndentation() * 10), 0, 0, 0);
        }
    }

    public final void enableTextSelection() {
        this.content.setEnabled(false);
        this.content.setEnabled(true);
    }
}
