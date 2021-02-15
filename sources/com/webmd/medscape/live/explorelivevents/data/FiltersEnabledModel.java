package com.webmd.medscape.live.explorelivevents.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012$\b\u0002\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006¢\u0006\u0002\u0010\tJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J%\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006HÆ\u0003JG\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032$\b\u0002\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0019HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR6\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006%"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/FiltersEnabledModel;", "Landroid/os/Parcelable;", "location", "", "specialty", "specifiedDateMap", "", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getLocation", "()Ljava/lang/String;", "setLocation", "(Ljava/lang/String;)V", "getSpecialty", "setSpecialty", "getSpecifiedDateMap", "()Ljava/util/Map;", "setSpecifiedDateMap", "(Ljava/util/Map;)V", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FiltersEnabledModel.kt */
public final class FiltersEnabledModel implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String location;
    private String specialty;
    private Map<String, Pair<ZonedDateTime, ZonedDateTime>> specifiedDateMap;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            LinkedHashMap linkedHashMap = new LinkedHashMap(readInt);
            while (readInt != 0) {
                linkedHashMap.put(parcel.readString(), (Pair) parcel.readSerializable());
                readInt--;
            }
            return new FiltersEnabledModel(readString, readString2, linkedHashMap);
        }

        public final Object[] newArray(int i) {
            return new FiltersEnabledModel[i];
        }
    }

    public FiltersEnabledModel() {
        this((String) null, (String) null, (Map) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FiltersEnabledModel copy$default(FiltersEnabledModel filtersEnabledModel, String str, String str2, Map<String, Pair<ZonedDateTime, ZonedDateTime>> map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = filtersEnabledModel.location;
        }
        if ((i & 2) != 0) {
            str2 = filtersEnabledModel.specialty;
        }
        if ((i & 4) != 0) {
            map = filtersEnabledModel.specifiedDateMap;
        }
        return filtersEnabledModel.copy(str, str2, map);
    }

    public final String component1() {
        return this.location;
    }

    public final String component2() {
        return this.specialty;
    }

    public final Map<String, Pair<ZonedDateTime, ZonedDateTime>> component3() {
        return this.specifiedDateMap;
    }

    public final FiltersEnabledModel copy(String str, String str2, Map<String, Pair<ZonedDateTime, ZonedDateTime>> map) {
        Intrinsics.checkNotNullParameter(map, "specifiedDateMap");
        return new FiltersEnabledModel(str, str2, map);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FiltersEnabledModel)) {
            return false;
        }
        FiltersEnabledModel filtersEnabledModel = (FiltersEnabledModel) obj;
        return Intrinsics.areEqual((Object) this.location, (Object) filtersEnabledModel.location) && Intrinsics.areEqual((Object) this.specialty, (Object) filtersEnabledModel.specialty) && Intrinsics.areEqual((Object) this.specifiedDateMap, (Object) filtersEnabledModel.specifiedDateMap);
    }

    public int hashCode() {
        String str = this.location;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.specialty;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Map<String, Pair<ZonedDateTime, ZonedDateTime>> map = this.specifiedDateMap;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "FiltersEnabledModel(location=" + this.location + ", specialty=" + this.specialty + ", specifiedDateMap=" + this.specifiedDateMap + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.location);
        parcel.writeString(this.specialty);
        Map<String, Pair<ZonedDateTime, ZonedDateTime>> map = this.specifiedDateMap;
        parcel.writeInt(map.size());
        for (Map.Entry<String, Pair<ZonedDateTime, ZonedDateTime>> next : map.entrySet()) {
            parcel.writeString(next.getKey());
            parcel.writeSerializable(next.getValue());
        }
    }

    public FiltersEnabledModel(String str, String str2, Map<String, Pair<ZonedDateTime, ZonedDateTime>> map) {
        Intrinsics.checkNotNullParameter(map, "specifiedDateMap");
        this.location = str;
        this.specialty = str2;
        this.specifiedDateMap = map;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FiltersEnabledModel(String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? MapsKt.hashMapOf(TuplesKt.to("", new Pair(null, null))) : map);
    }

    public final String getLocation() {
        return this.location;
    }

    public final void setLocation(String str) {
        this.location = str;
    }

    public final String getSpecialty() {
        return this.specialty;
    }

    public final void setSpecialty(String str) {
        this.specialty = str;
    }

    public final Map<String, Pair<ZonedDateTime, ZonedDateTime>> getSpecifiedDateMap() {
        return this.specifiedDateMap;
    }

    public final void setSpecifiedDateMap(Map<String, Pair<ZonedDateTime, ZonedDateTime>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.specifiedDateMap = map;
    }
}
