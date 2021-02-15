package com.medscape.android.activity.help;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\b\"\u0004\b\u000b\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/help/ProfileMenuItems;", "", "text", "", "isLogOut", "", "isInvites", "(Ljava/lang/String;ZZ)V", "()Z", "setInvites", "(Z)V", "setLogOut", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ProfileMenuItems.kt */
public final class ProfileMenuItems {
    private boolean isInvites;
    private boolean isLogOut;
    private String text;

    public ProfileMenuItems() {
        this((String) null, false, false, 7, (DefaultConstructorMarker) null);
    }

    public ProfileMenuItems(String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.text = str;
        this.isLogOut = z;
        this.isInvites = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ProfileMenuItems(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    public final String getText() {
        return this.text;
    }

    public final boolean isInvites() {
        return this.isInvites;
    }

    public final boolean isLogOut() {
        return this.isLogOut;
    }

    public final void setInvites(boolean z) {
        this.isInvites = z;
    }

    public final void setLogOut(boolean z) {
        this.isLogOut = z;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }
}
