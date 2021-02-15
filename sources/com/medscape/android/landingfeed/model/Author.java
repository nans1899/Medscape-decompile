package com.medscape.android.landingfeed.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/medscape/android/landingfeed/model/Author;", "", "specialty", "", "userType", "avatarUrl", "displayName", "institutionLogo", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAvatarUrl", "()Ljava/lang/String;", "getDisplayName", "getInstitutionLogo", "getSpecialty", "getUserType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Author.kt */
public final class Author {
    @SerializedName("avatarUrl")
    private final String avatarUrl;
    @SerializedName("displayName")
    private final String displayName;
    @SerializedName("institutionLogo")
    private final String institutionLogo;
    private final String specialty;
    @SerializedName("userType")
    private final String userType;

    public Author() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Author copy$default(Author author, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = author.specialty;
        }
        if ((i & 2) != 0) {
            str2 = author.userType;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = author.avatarUrl;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = author.displayName;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = author.institutionLogo;
        }
        return author.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.specialty;
    }

    public final String component2() {
        return this.userType;
    }

    public final String component3() {
        return this.avatarUrl;
    }

    public final String component4() {
        return this.displayName;
    }

    public final String component5() {
        return this.institutionLogo;
    }

    public final Author copy(String str, String str2, String str3, String str4, String str5) {
        return new Author(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Author)) {
            return false;
        }
        Author author = (Author) obj;
        return Intrinsics.areEqual((Object) this.specialty, (Object) author.specialty) && Intrinsics.areEqual((Object) this.userType, (Object) author.userType) && Intrinsics.areEqual((Object) this.avatarUrl, (Object) author.avatarUrl) && Intrinsics.areEqual((Object) this.displayName, (Object) author.displayName) && Intrinsics.areEqual((Object) this.institutionLogo, (Object) author.institutionLogo);
    }

    public int hashCode() {
        String str = this.specialty;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.userType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.avatarUrl;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.displayName;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.institutionLogo;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "Author(specialty=" + this.specialty + ", userType=" + this.userType + ", avatarUrl=" + this.avatarUrl + ", displayName=" + this.displayName + ", institutionLogo=" + this.institutionLogo + ")";
    }

    public Author(String str, String str2, String str3, String str4, String str5) {
        this.specialty = str;
        this.userType = str2;
        this.avatarUrl = str3;
        this.displayName = str4;
        this.institutionLogo = str5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Author(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            r0 = 0
            if (r10 == 0) goto L_0x0008
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
        L_0x0008:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x000f
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
        L_0x000f:
            r10 = r5
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0017
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
        L_0x0017:
            r1 = r6
            r5 = r9 & 8
            if (r5 == 0) goto L_0x001f
            r7 = r0
            java.lang.String r7 = (java.lang.String) r7
        L_0x001f:
            r2 = r7
            r5 = r9 & 16
            if (r5 == 0) goto L_0x0027
            r8 = r0
            java.lang.String r8 = (java.lang.String) r8
        L_0x0027:
            r0 = r8
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r1
            r9 = r2
            r10 = r0
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.model.Author.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getSpecialty() {
        return this.specialty;
    }

    public final String getUserType() {
        return this.userType;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getInstitutionLogo() {
        return this.institutionLogo;
    }
}
