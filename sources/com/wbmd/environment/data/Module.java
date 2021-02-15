package com.wbmd.environment.data;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bHÆ\u0003J7\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/wbmd/environment/data/Module;", "", "id", "", "name", "environments", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Environment;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)V", "getEnvironments", "()Ljava/util/ArrayList;", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getName", "setName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Module.kt */
public final class Module {
    private final ArrayList<Environment> environments;
    private String id;
    private String name;

    public static /* synthetic */ Module copy$default(Module module, String str, String str2, ArrayList<Environment> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = module.id;
        }
        if ((i & 2) != 0) {
            str2 = module.name;
        }
        if ((i & 4) != 0) {
            arrayList = module.environments;
        }
        return module.copy(str, str2, arrayList);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final ArrayList<Environment> component3() {
        return this.environments;
    }

    public final Module copy(String str, String str2, ArrayList<Environment> arrayList) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(arrayList, "environments");
        return new Module(str, str2, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Module)) {
            return false;
        }
        Module module = (Module) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) module.id) && Intrinsics.areEqual((Object) this.name, (Object) module.name) && Intrinsics.areEqual((Object) this.environments, (Object) module.environments);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        ArrayList<Environment> arrayList = this.environments;
        if (arrayList != null) {
            i = arrayList.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "Module(id=" + this.id + ", name=" + this.name + ", environments=" + this.environments + ")";
    }

    public Module(String str, String str2, ArrayList<Environment> arrayList) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(arrayList, "environments");
        this.id = str;
        this.name = str2;
        this.environments = arrayList;
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

    public final ArrayList<Environment> getEnvironments() {
        return this.environments;
    }
}
