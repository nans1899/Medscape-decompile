package com.medscape.android.drugs.details.datamodels;

import com.medscape.android.contentviewer.CrossLink;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000¢\u0006\u0002\u0010\u0003B\u0019\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/medscape/android/drugs/details/datamodels/InteractionListItem;", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "item", "(Lcom/medscape/android/drugs/details/datamodels/InteractionListItem;)V", "details", "", "strength", "", "(Ljava/lang/CharSequence;I)V", "getDetails", "()Ljava/lang/CharSequence;", "setDetails", "(Ljava/lang/CharSequence;)V", "isDetailVisible", "", "()Z", "setDetailVisible", "(Z)V", "getStrength", "()I", "setStrength", "(I)V", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: InteractionListItem.kt */
public final class InteractionListItem extends LineItem {
    private CharSequence details;
    private boolean isDetailVisible;
    private int strength;

    public InteractionListItem() {
        this((CharSequence) null, 0, 3, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InteractionListItem(CharSequence charSequence, int i) {
        super((CrossLink) null, (CharSequence) null, 0, false, false, false, 62, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(charSequence, "details");
        this.details = charSequence;
        this.strength = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InteractionListItem(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? -1 : i);
    }

    public final CharSequence getDetails() {
        return this.details;
    }

    public final int getStrength() {
        return this.strength;
    }

    public final void setDetails(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.details = charSequence;
    }

    public final void setStrength(int i) {
        this.strength = i;
    }

    public final boolean isDetailVisible() {
        return this.isDetailVisible;
    }

    public final void setDetailVisible(boolean z) {
        this.isDetailVisible = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InteractionListItem(InteractionListItem interactionListItem) {
        this((CharSequence) null, 0, 3, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(interactionListItem, ContentParser.ITEM);
        setCrossLink(interactionListItem.getCrossLink());
        setText(interactionListItem.getText());
        setSectionHeaderPosition(interactionListItem.getSectionHeaderPosition());
        setHeader(interactionListItem.isHeader());
        setSubheader(interactionListItem.isSubheader());
        this.details = interactionListItem.details;
        this.strength = interactionListItem.strength;
        this.isDetailVisible = interactionListItem.isDetailVisible;
        setExpandable(interactionListItem.isExpandable());
        setNextSection(interactionListItem.isNextSection());
        setErrorMessage(interactionListItem.isErrorMessage());
    }
}
