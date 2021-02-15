package com.webmd.medscape.live.explorelivevents.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/SearchFilter;", "Landroid/os/Parcelable;", "locations", "", "", "specialtyDisplay", "specialtyKey", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getLocations", "()Ljava/util/List;", "getSpecialtyDisplay", "()Ljava/lang/String;", "getSpecialtyKey", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SearchFilter.kt */
public final class SearchFilter implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Expose
    private final List<String> locations;
    @Expose
    private final String specialtyDisplay;
    @Expose
    private final String specialtyKey;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new SearchFilter(parcel.createStringArrayList(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new SearchFilter[i];
        }
    }

    public static /* synthetic */ SearchFilter copy$default(SearchFilter searchFilter, List<String> list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = searchFilter.locations;
        }
        if ((i & 2) != 0) {
            str = searchFilter.specialtyDisplay;
        }
        if ((i & 4) != 0) {
            str2 = searchFilter.specialtyKey;
        }
        return searchFilter.copy(list, str, str2);
    }

    public final List<String> component1() {
        return this.locations;
    }

    public final String component2() {
        return this.specialtyDisplay;
    }

    public final String component3() {
        return this.specialtyKey;
    }

    public final SearchFilter copy(List<String> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "locations");
        Intrinsics.checkNotNullParameter(str, "specialtyDisplay");
        Intrinsics.checkNotNullParameter(str2, "specialtyKey");
        return new SearchFilter(list, str, str2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchFilter)) {
            return false;
        }
        SearchFilter searchFilter = (SearchFilter) obj;
        return Intrinsics.areEqual((Object) this.locations, (Object) searchFilter.locations) && Intrinsics.areEqual((Object) this.specialtyDisplay, (Object) searchFilter.specialtyDisplay) && Intrinsics.areEqual((Object) this.specialtyKey, (Object) searchFilter.specialtyKey);
    }

    public int hashCode() {
        List<String> list = this.locations;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.specialtyDisplay;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.specialtyKey;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "SearchFilter(locations=" + this.locations + ", specialtyDisplay=" + this.specialtyDisplay + ", specialtyKey=" + this.specialtyKey + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeStringList(this.locations);
        parcel.writeString(this.specialtyDisplay);
        parcel.writeString(this.specialtyKey);
    }

    public SearchFilter(List<String> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "locations");
        Intrinsics.checkNotNullParameter(str, "specialtyDisplay");
        Intrinsics.checkNotNullParameter(str2, "specialtyKey");
        this.locations = list;
        this.specialtyDisplay = str;
        this.specialtyKey = str2;
    }

    public final List<String> getLocations() {
        return this.locations;
    }

    public final String getSpecialtyDisplay() {
        return this.specialtyDisplay;
    }

    public final String getSpecialtyKey() {
        return this.specialtyKey;
    }
}
