package com.webmd.medscape.live.explorelivevents.common;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/ExploreFontsContainer;", "Lcom/webmd/medscape/live/explorelivevents/common/IExploreFonts;", "Landroid/os/Parcelable;", "regularFont", "", "italicFont", "mediumFont", "mediumItalicFont", "boldFont", "(IIIII)V", "getBoldFont", "()I", "setBoldFont", "(I)V", "getItalicFont", "setItalicFont", "getMediumFont", "setMediumFont", "getMediumItalicFont", "setMediumItalicFont", "getRegularFont", "setRegularFont", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreFontsContainer.kt */
public final class ExploreFontsContainer implements IExploreFonts, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private int boldFont;
    private int italicFont;
    private int mediumFont;
    private int mediumItalicFont;
    private int regularFont;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new ExploreFontsContainer(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new ExploreFontsContainer[i];
        }
    }

    public ExploreFontsContainer() {
        this(0, 0, 0, 0, 0, 31, (DefaultConstructorMarker) null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.regularFont);
        parcel.writeInt(this.italicFont);
        parcel.writeInt(this.mediumFont);
        parcel.writeInt(this.mediumItalicFont);
        parcel.writeInt(this.boldFont);
    }

    public ExploreFontsContainer(int i, int i2, int i3, int i4, int i5) {
        this.regularFont = i;
        this.italicFont = i2;
        this.mediumFont = i3;
        this.mediumItalicFont = i4;
        this.boldFont = i5;
    }

    public int getRegularFont() {
        return this.regularFont;
    }

    public void setRegularFont(int i) {
        this.regularFont = i;
    }

    public int getItalicFont() {
        return this.italicFont;
    }

    public void setItalicFont(int i) {
        this.italicFont = i;
    }

    public int getMediumFont() {
        return this.mediumFont;
    }

    public void setMediumFont(int i) {
        this.mediumFont = i;
    }

    public int getMediumItalicFont() {
        return this.mediumItalicFont;
    }

    public void setMediumItalicFont(int i) {
        this.mediumItalicFont = i;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ExploreFontsContainer(int r5, int r6, int r7, int r8, int r9, int r10, kotlin.jvm.internal.DefaultConstructorMarker r11) {
        /*
            r4 = this;
            r11 = r10 & 1
            r0 = 0
            if (r11 == 0) goto L_0x0007
            r11 = 0
            goto L_0x0008
        L_0x0007:
            r11 = r5
        L_0x0008:
            r5 = r10 & 2
            if (r5 == 0) goto L_0x000e
            r1 = 0
            goto L_0x000f
        L_0x000e:
            r1 = r6
        L_0x000f:
            r5 = r10 & 4
            if (r5 == 0) goto L_0x0015
            r2 = 0
            goto L_0x0016
        L_0x0015:
            r2 = r7
        L_0x0016:
            r5 = r10 & 8
            if (r5 == 0) goto L_0x001c
            r3 = 0
            goto L_0x001d
        L_0x001c:
            r3 = r8
        L_0x001d:
            r5 = r10 & 16
            if (r5 == 0) goto L_0x0023
            r10 = 0
            goto L_0x0024
        L_0x0023:
            r10 = r9
        L_0x0024:
            r5 = r4
            r6 = r11
            r7 = r1
            r8 = r2
            r9 = r3
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.common.ExploreFontsContainer.<init>(int, int, int, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public int getBoldFont() {
        return this.boldFont;
    }

    public void setBoldFont(int i) {
        this.boldFont = i;
    }
}
