package org.mockito.internal.util.collections;

import com.dd.plist.ASCIIPropertyListParser;
import org.mockito.internal.util.MockUtil;

public class HashCodeAndEqualsMockWrapper {
    private final Object mockInstance;

    public HashCodeAndEqualsMockWrapper(Object obj) {
        this.mockInstance = obj;
    }

    public Object get() {
        return this.mockInstance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof HashCodeAndEqualsMockWrapper) && this.mockInstance == ((HashCodeAndEqualsMockWrapper) obj).mockInstance) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return System.identityHashCode(this.mockInstance);
    }

    public static HashCodeAndEqualsMockWrapper of(Object obj) {
        return new HashCodeAndEqualsMockWrapper(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashCodeAndEqualsMockWrapper{mockInstance=");
        sb.append(MockUtil.isMock(this.mockInstance) ? MockUtil.getMockName(this.mockInstance) : typeInstanceString());
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        return sb.toString();
    }

    private String typeInstanceString() {
        return this.mockInstance.getClass().getSimpleName() + "(" + System.identityHashCode(this.mockInstance) + ")";
    }
}
