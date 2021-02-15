package com.ib.clickstream;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/ib/clickstream/Impression;", "", "contentId", "", "ttl", "position", "category", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "setCategory", "(Ljava/lang/String;)V", "getContentId", "setContentId", "getPosition", "setPosition", "getTtl", "setTtl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamConstants.kt */
public final class Impression {
    private String category;
    @SerializedName("content_id")
    private String contentId;
    private String position;
    private String ttl;

    public Impression() {
        this((String) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Impression copy$default(Impression impression, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = impression.contentId;
        }
        if ((i & 2) != 0) {
            str2 = impression.ttl;
        }
        if ((i & 4) != 0) {
            str3 = impression.position;
        }
        if ((i & 8) != 0) {
            str4 = impression.category;
        }
        return impression.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.contentId;
    }

    public final String component2() {
        return this.ttl;
    }

    public final String component3() {
        return this.position;
    }

    public final String component4() {
        return this.category;
    }

    public final Impression copy(String str, String str2, String str3, String str4) {
        return new Impression(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Impression)) {
            return false;
        }
        Impression impression = (Impression) obj;
        return Intrinsics.areEqual((Object) this.contentId, (Object) impression.contentId) && Intrinsics.areEqual((Object) this.ttl, (Object) impression.ttl) && Intrinsics.areEqual((Object) this.position, (Object) impression.position) && Intrinsics.areEqual((Object) this.category, (Object) impression.category);
    }

    public int hashCode() {
        String str = this.contentId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ttl;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.position;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.category;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "Impression(contentId=" + this.contentId + ", ttl=" + this.ttl + ", position=" + this.position + ", category=" + this.category + ")";
    }

    public Impression(String str, String str2, String str3, String str4) {
        this.contentId = str;
        this.ttl = str2;
        this.position = str3;
        this.category = str4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Impression(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4);
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final void setContentId(String str) {
        this.contentId = str;
    }

    public final String getTtl() {
        return this.ttl;
    }

    public final void setTtl(String str) {
        this.ttl = str;
    }

    public final String getPosition() {
        return this.position;
    }

    public final void setPosition(String str) {
        this.position = str;
    }

    public final String getCategory() {
        return this.category;
    }

    public final void setCategory(String str) {
        this.category = str;
    }
}
