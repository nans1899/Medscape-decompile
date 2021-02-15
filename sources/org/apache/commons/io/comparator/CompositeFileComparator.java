package org.apache.commons.io.comparator;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompositeFileComparator extends AbstractFileComparator implements Serializable {
    private static final Comparator<?>[] EMPTY_COMPARATOR_ARRAY = new Comparator[0];
    private static final Comparator<?>[] NO_COMPARATORS = new Comparator[0];
    private static final long serialVersionUID = -2224170307287243428L;
    private final Comparator<File>[] delegates;

    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort((List<File>) list);
    }

    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    public CompositeFileComparator(Comparator<File>... comparatorArr) {
        if (comparatorArr == null) {
            this.delegates = (Comparator[]) NO_COMPARATORS;
            return;
        }
        Comparator<File>[] comparatorArr2 = (Comparator[]) new Comparator[comparatorArr.length];
        this.delegates = comparatorArr2;
        System.arraycopy(comparatorArr, 0, comparatorArr2, 0, comparatorArr.length);
    }

    public CompositeFileComparator(Iterable<Comparator<File>> iterable) {
        if (iterable == null) {
            this.delegates = (Comparator[]) NO_COMPARATORS;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Comparator<File> add : iterable) {
            arrayList.add(add);
        }
        this.delegates = (Comparator[]) arrayList.toArray(EMPTY_COMPARATOR_ARRAY);
    }

    public int compare(File file, File file2) {
        int i = 0;
        for (Comparator<File> compare : this.delegates) {
            i = compare.compare(file, file2);
            if (i != 0) {
                break;
            }
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
        for (int i = 0; i < this.delegates.length; i++) {
            if (i > 0) {
                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            sb.append(this.delegates[i]);
        }
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        return sb.toString();
    }
}
