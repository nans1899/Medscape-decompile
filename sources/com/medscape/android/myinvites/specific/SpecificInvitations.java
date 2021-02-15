package com.medscape.android.myinvites.specific;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R.\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/myinvites/specific/SpecificInvitations;", "", "invitations", "Ljava/util/ArrayList;", "Lcom/medscape/android/myinvites/specific/Invitation;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getInvitations", "()Ljava/util/ArrayList;", "setInvitations", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SpecificInvitations.kt */
public final class SpecificInvitations {
    @SerializedName("campaigns")
    private ArrayList<Invitation> invitations;

    public static /* synthetic */ SpecificInvitations copy$default(SpecificInvitations specificInvitations, ArrayList<Invitation> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = specificInvitations.invitations;
        }
        return specificInvitations.copy(arrayList);
    }

    public final ArrayList<Invitation> component1() {
        return this.invitations;
    }

    public final SpecificInvitations copy(ArrayList<Invitation> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "invitations");
        return new SpecificInvitations(arrayList);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof SpecificInvitations) && Intrinsics.areEqual((Object) this.invitations, (Object) ((SpecificInvitations) obj).invitations);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<Invitation> arrayList = this.invitations;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "SpecificInvitations(invitations=" + this.invitations + ")";
    }

    public SpecificInvitations(ArrayList<Invitation> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "invitations");
        this.invitations = arrayList;
    }

    public final ArrayList<Invitation> getInvitations() {
        return this.invitations;
    }

    public final void setInvitations(ArrayList<Invitation> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.invitations = arrayList;
    }
}
