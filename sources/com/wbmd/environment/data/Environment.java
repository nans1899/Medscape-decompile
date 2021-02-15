package com.wbmd.environment.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012(\b\u0002\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J)\u0010\u001f\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\nHÆ\u0003J]\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072(\b\u0002\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\nHÆ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J\u0013\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\"HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001J\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\"HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR:\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006-"}, d2 = {"Lcom/wbmd/environment/data/Environment;", "Landroid/os/Parcelable;", "id", "", "name", "baseUrl", "isActive", "", "parameters", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/HashMap;)V", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "getId", "setId", "()Z", "setActive", "(Z)V", "getName", "setName", "getParameters", "()Ljava/util/HashMap;", "setParameters", "(Ljava/util/HashMap;)V", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Environment.kt */
public final class Environment implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String baseUrl;
    private String id;
    private boolean isActive;
    private String name;
    private HashMap<String, String> parameters;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            HashMap hashMap;
            Intrinsics.checkNotNullParameter(parcel, "in");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            boolean z = parcel.readInt() != 0;
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                hashMap = new HashMap(readInt);
                while (readInt != 0) {
                    hashMap.put(parcel.readString(), parcel.readString());
                    readInt--;
                }
            } else {
                hashMap = null;
            }
            return new Environment(readString, readString2, readString3, z, hashMap);
        }

        public final Object[] newArray(int i) {
            return new Environment[i];
        }
    }

    public static /* synthetic */ Environment copy$default(Environment environment, String str, String str2, String str3, boolean z, HashMap<String, String> hashMap, int i, Object obj) {
        if ((i & 1) != 0) {
            str = environment.id;
        }
        if ((i & 2) != 0) {
            str2 = environment.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = environment.baseUrl;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            z = environment.isActive;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            hashMap = environment.parameters;
        }
        return environment.copy(str, str4, str5, z2, hashMap);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.baseUrl;
    }

    public final boolean component4() {
        return this.isActive;
    }

    public final HashMap<String, String> component5() {
        return this.parameters;
    }

    public final Environment copy(String str, String str2, String str3, boolean z, HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "name");
        return new Environment(str, str2, str3, z, hashMap);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Environment)) {
            return false;
        }
        Environment environment = (Environment) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) environment.id) && Intrinsics.areEqual((Object) this.name, (Object) environment.name) && Intrinsics.areEqual((Object) this.baseUrl, (Object) environment.baseUrl) && this.isActive == environment.isActive && Intrinsics.areEqual((Object) this.parameters, (Object) environment.parameters);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.baseUrl;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.isActive;
        if (z) {
            z = true;
        }
        int i2 = (hashCode3 + (z ? 1 : 0)) * 31;
        HashMap<String, String> hashMap = this.parameters;
        if (hashMap != null) {
            i = hashMap.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        return "Environment(id=" + this.id + ", name=" + this.name + ", baseUrl=" + this.baseUrl + ", isActive=" + this.isActive + ", parameters=" + this.parameters + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.baseUrl);
        parcel.writeInt(this.isActive ? 1 : 0);
        HashMap<String, String> hashMap = this.parameters;
        if (hashMap != null) {
            parcel.writeInt(1);
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, String> next : hashMap.entrySet()) {
                parcel.writeString(next.getKey());
                parcel.writeString(next.getValue());
            }
            return;
        }
        parcel.writeInt(0);
    }

    public Environment(String str, String str2, String str3, boolean z, HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "name");
        this.id = str;
        this.name = str2;
        this.baseUrl = str3;
        this.isActive = z;
        this.parameters = hashMap;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Environment(String str, String str2, String str3, boolean z, HashMap hashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : hashMap);
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public final HashMap<String, String> getParameters() {
        return this.parameters;
    }

    public final void setParameters(HashMap<String, String> hashMap) {
        this.parameters = hashMap;
    }
}
