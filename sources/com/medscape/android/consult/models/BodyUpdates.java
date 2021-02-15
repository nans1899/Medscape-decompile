package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0007\b\u0016¢\u0006\u0002\u0010\u0005J\b\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020!H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006'"}, d2 = {"Lcom/medscape/android/consult/models/BodyUpdates;", "Landroid/os/Parcelable;", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "Body", "", "getBody", "()Ljava/lang/String;", "setBody", "(Ljava/lang/String;)V", "CreateDate", "getCreateDate", "setCreateDate", "DisplayDate", "getDisplayDate", "setDisplayDate", "Id", "getId", "setId", "Title", "getTitle", "setTitle", "UpdateDate", "getUpdateDate", "setUpdateDate", "isCurrentUser", "", "()Z", "setCurrentUser", "(Z)V", "describeContents", "", "writeToParcel", "", "parcel", "i", "CREATOR", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BodyUpdates.kt */
public final class BodyUpdates implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
    private String Body;
    private String CreateDate;
    private String DisplayDate;
    private String Id;
    private String Title;
    private String UpdateDate;
    private boolean isCurrentUser;

    public int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.Id;
    }

    public final void setId(String str) {
        this.Id = str;
    }

    public final String getCreateDate() {
        return this.CreateDate;
    }

    public final void setCreateDate(String str) {
        this.CreateDate = str;
    }

    public final String getUpdateDate() {
        return this.UpdateDate;
    }

    public final void setUpdateDate(String str) {
        this.UpdateDate = str;
    }

    public final String getDisplayDate() {
        return this.DisplayDate;
    }

    public final void setDisplayDate(String str) {
        this.DisplayDate = str;
    }

    public final String getTitle() {
        return this.Title;
    }

    public final void setTitle(String str) {
        this.Title = str;
    }

    public final String getBody() {
        return this.Body;
    }

    public final void setBody(String str) {
        this.Body = str;
    }

    public final boolean isCurrentUser() {
        return this.isCurrentUser;
    }

    public final void setCurrentUser(boolean z) {
        this.isCurrentUser = z;
    }

    public BodyUpdates(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        this.Id = parcel.readString();
        this.CreateDate = parcel.readString();
        this.UpdateDate = parcel.readString();
        this.DisplayDate = parcel.readString();
        this.Title = parcel.readString();
        this.Body = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.Id);
        parcel.writeString(this.CreateDate);
        parcel.writeString(this.UpdateDate);
        parcel.writeString(this.DisplayDate);
        parcel.writeString(this.Title);
        parcel.writeString(this.Body);
    }

    public BodyUpdates() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/medscape/android/consult/models/BodyUpdates$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/medscape/android/consult/models/BodyUpdates;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/medscape/android/consult/models/BodyUpdates;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: BodyUpdates.kt */
    public static final class CREATOR implements Parcelable.Creator<BodyUpdates> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public BodyUpdates createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new BodyUpdates(parcel);
        }

        public BodyUpdates[] newArray(int i) {
            return new BodyUpdates[i];
        }
    }
}
