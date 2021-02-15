package com.wbmd.wbmdcommons.utils;

import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"com/wbmd/wbmdcommons/utils/Util$RX_SESSIONS$1", "Ljava/util/ArrayList;", "", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Util.kt */
public final class Util$RX_SESSIONS$1 extends ArrayList<Integer> {
    Util$RX_SESSIONS$1(int i) {
        super(i);
        add(1);
        add(5);
        add(10);
        add(15);
    }

    public /* bridge */ boolean contains(Integer num) {
        return super.contains(num);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return contains((Integer) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Integer num) {
        return super.indexOf(num);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Integer) {
            return indexOf((Integer) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(Integer num) {
        return super.lastIndexOf(num);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            return lastIndexOf((Integer) obj);
        }
        return -1;
    }

    public final /* bridge */ Integer remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(Integer num) {
        return super.remove(num);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Integer) {
            return remove((Integer) obj);
        }
        return false;
    }

    public /* bridge */ Integer removeAt(int i) {
        return (Integer) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }
}
