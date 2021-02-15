package net.bytebuddy.jar.asm;

import com.dd.plist.ASCIIPropertyListParser;

public final class Handle {
    private final String descriptor;
    private final boolean isInterface;
    private final String name;
    private final String owner;
    private final int tag;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Handle(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, i == 9);
    }

    public Handle(int i, String str, String str2, String str3, boolean z) {
        this.tag = i;
        this.owner = str;
        this.name = str2;
        this.descriptor = str3;
        this.isInterface = z;
    }

    public int getTag() {
        return this.tag;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.descriptor;
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle) obj;
        if (this.tag != handle.tag || this.isInterface != handle.isInterface || !this.owner.equals(handle.owner) || !this.name.equals(handle.name) || !this.descriptor.equals(handle.descriptor)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.tag + (this.isInterface ? 64 : 0) + (this.owner.hashCode() * this.name.hashCode() * this.descriptor.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.owner);
        sb.append('.');
        sb.append(this.name);
        sb.append(this.descriptor);
        sb.append(" (");
        sb.append(this.tag);
        sb.append(this.isInterface ? " itf" : "");
        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        return sb.toString();
    }
}
