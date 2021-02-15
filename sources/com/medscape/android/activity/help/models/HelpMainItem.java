package com.medscape.android.activity.help.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/medscape/android/activity/help/models/HelpMainItem;", "", "itemId", "", "title", "", "(ILjava/lang/String;)V", "getItemId", "()I", "setItemId", "(I)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpMainItem.kt */
public final class HelpMainItem {
    private int itemId;
    private String title;

    public static /* synthetic */ HelpMainItem copy$default(HelpMainItem helpMainItem, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = helpMainItem.itemId;
        }
        if ((i2 & 2) != 0) {
            str = helpMainItem.title;
        }
        return helpMainItem.copy(i, str);
    }

    public final int component1() {
        return this.itemId;
    }

    public final String component2() {
        return this.title;
    }

    public final HelpMainItem copy(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        return new HelpMainItem(i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HelpMainItem)) {
            return false;
        }
        HelpMainItem helpMainItem = (HelpMainItem) obj;
        return this.itemId == helpMainItem.itemId && Intrinsics.areEqual((Object) this.title, (Object) helpMainItem.title);
    }

    public int hashCode() {
        int i = this.itemId * 31;
        String str = this.title;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "HelpMainItem(itemId=" + this.itemId + ", title=" + this.title + ")";
    }

    public HelpMainItem(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        this.itemId = i;
        this.title = str;
    }

    public final int getItemId() {
        return this.itemId;
    }

    public final void setItemId(int i) {
        this.itemId = i;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
