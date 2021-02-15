package com.medscape.android.drugs.details.datamodels;

import com.medscape.android.contentviewer.CrossLink;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001a\u0010\u001c\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u0018R\u001a\u0010\n\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/medscape/android/drugs/details/datamodels/LineItem;", "", "crossLink", "Lcom/medscape/android/contentviewer/CrossLink;", "text", "", "sectionHeaderPosition", "", "isHeader", "", "isSubheader", "isErrorMessage", "(Lcom/medscape/android/contentviewer/CrossLink;Ljava/lang/CharSequence;IZZZ)V", "getCrossLink", "()Lcom/medscape/android/contentviewer/CrossLink;", "setCrossLink", "(Lcom/medscape/android/contentviewer/CrossLink;)V", "indentation", "getIndentation", "()I", "setIndentation", "(I)V", "()Z", "setErrorMessage", "(Z)V", "isExpandable", "setExpandable", "setHeader", "isNextSection", "setNextSection", "setSubheader", "getSectionHeaderPosition", "setSectionHeaderPosition", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "setCustomIndentation", "", "i", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LineItem.kt */
public class LineItem {
    private CrossLink crossLink;
    private int indentation;
    private boolean isErrorMessage;
    private boolean isExpandable;
    private boolean isHeader;
    private boolean isNextSection;
    private boolean isSubheader;
    private int sectionHeaderPosition;
    private CharSequence text;

    public LineItem(CrossLink crossLink2, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(charSequence, "text");
        this.crossLink = crossLink2;
        this.text = charSequence;
        this.sectionHeaderPosition = i;
        this.isHeader = z;
        this.isSubheader = z2;
        this.isErrorMessage = z3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LineItem(CrossLink crossLink2, String str, int i, boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(crossLink2, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? false : z3);
    }

    public final CrossLink getCrossLink() {
        return this.crossLink;
    }

    public final int getSectionHeaderPosition() {
        return this.sectionHeaderPosition;
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final void setCrossLink(CrossLink crossLink2) {
        this.crossLink = crossLink2;
    }

    public final void setSectionHeaderPosition(int i) {
        this.sectionHeaderPosition = i;
    }

    public final void setText(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.text = charSequence;
    }

    public final boolean isErrorMessage() {
        return this.isErrorMessage;
    }

    public final boolean isHeader() {
        return this.isHeader;
    }

    public final boolean isSubheader() {
        return this.isSubheader;
    }

    public final void setErrorMessage(boolean z) {
        this.isErrorMessage = z;
    }

    public final void setHeader(boolean z) {
        this.isHeader = z;
    }

    public final void setSubheader(boolean z) {
        this.isSubheader = z;
    }

    public final boolean isExpandable() {
        return this.isExpandable;
    }

    public final void setExpandable(boolean z) {
        this.isExpandable = z;
    }

    public final boolean isNextSection() {
        return this.isNextSection;
    }

    public final void setNextSection(boolean z) {
        this.isNextSection = z;
    }

    public final int getIndentation() {
        return this.indentation;
    }

    public final void setIndentation(int i) {
        this.indentation = i;
    }

    public final void setCustomIndentation(int i) {
        if (!this.isHeader) {
            this.indentation = this.isSubheader ? i - 1 : i + 1;
        }
    }
}
