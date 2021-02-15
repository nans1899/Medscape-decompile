package com.medscape.android.homescreen.home_nav_tray.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "", "id", "", "text", "", "drawable", "folderId", "shouldDisplayRedDot", "", "type", "(ILjava/lang/String;IIZLjava/lang/String;)V", "getDrawable", "()I", "setDrawable", "(I)V", "getFolderId", "setFolderId", "getId", "setId", "getShouldDisplayRedDot", "()Z", "setShouldDisplayRedDot", "(Z)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getType", "setType", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NavItem.kt */
public final class NavItem {
    private int drawable;
    private int folderId;
    private int id;
    private boolean shouldDisplayRedDot;
    private String text;
    private String type;

    public NavItem() {
        this(0, (String) null, 0, 0, false, (String) null, 63, (DefaultConstructorMarker) null);
    }

    public NavItem(int i, String str, int i2, int i3, boolean z, String str2) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "type");
        this.id = i;
        this.text = str;
        this.drawable = i2;
        this.folderId = i3;
        this.shouldDisplayRedDot = z;
        this.type = str2;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final int getDrawable() {
        return this.drawable;
    }

    public final void setDrawable(int i) {
        this.drawable = i;
    }

    public final int getFolderId() {
        return this.folderId;
    }

    public final void setFolderId(int i) {
        this.folderId = i;
    }

    public final boolean getShouldDisplayRedDot() {
        return this.shouldDisplayRedDot;
    }

    public final void setShouldDisplayRedDot(boolean z) {
        this.shouldDisplayRedDot = z;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ NavItem(int r6, java.lang.String r7, int r8, int r9, boolean r10, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r5 = this;
            r13 = r12 & 1
            r0 = 0
            if (r13 == 0) goto L_0x0007
            r13 = 0
            goto L_0x0008
        L_0x0007:
            r13 = r6
        L_0x0008:
            r6 = r12 & 2
            java.lang.String r1 = ""
            if (r6 == 0) goto L_0x0010
            r2 = r1
            goto L_0x0011
        L_0x0010:
            r2 = r7
        L_0x0011:
            r6 = r12 & 4
            r7 = -1
            if (r6 == 0) goto L_0x0018
            r3 = -1
            goto L_0x0019
        L_0x0018:
            r3 = r8
        L_0x0019:
            r6 = r12 & 8
            if (r6 == 0) goto L_0x001f
            r4 = -1
            goto L_0x0020
        L_0x001f:
            r4 = r9
        L_0x0020:
            r6 = r12 & 16
            if (r6 == 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r0 = r10
        L_0x0026:
            r6 = r12 & 32
            if (r6 == 0) goto L_0x002c
            r12 = r1
            goto L_0x002d
        L_0x002c:
            r12 = r11
        L_0x002d:
            r6 = r5
            r7 = r13
            r8 = r2
            r9 = r3
            r10 = r4
            r11 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.homescreen.home_nav_tray.models.NavItem.<init>(int, java.lang.String, int, int, boolean, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }
}
