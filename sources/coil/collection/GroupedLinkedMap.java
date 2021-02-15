package coil.collection;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H\u0002J\u001c\u0010\u0010\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H\u0002J(\u0010\u0011\u001a\u00020\u000e\"\u0004\b\u0002\u0010\u0001\"\u0004\b\u0003\u0010\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0006H\u0002J\r\u0010\u0012\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\u000e\"\u0004\b\u0002\u0010\u0001\"\u0004\b\u0003\u0010\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0006H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0004¢\u0006\u0002\n\u0000RB\u0010\u0007\u001a6\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\bj\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006`\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcoil/collection/GroupedLinkedMap;", "K", "V", "", "()V", "head", "Lcoil/collection/GroupedLinkedMap$LinkedEntry;", "keyToEntry", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "makeHead", "", "entry", "makeTail", "removeEntry", "removeLast", "()Ljava/lang/Object;", "set", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "", "updateEntry", "LinkedEntry", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: GroupedLinkedMap.kt */
public final class GroupedLinkedMap<K, V> {
    private final LinkedEntry<K, V> head = new LinkedEntry<>((Object) null, 1, (DefaultConstructorMarker) null);
    private final HashMap<K, LinkedEntry<K, V>> keyToEntry = new HashMap<>();

    public final void set(K k, V v) {
        Map map = this.keyToEntry;
        Object obj = map.get(k);
        LinkedEntry linkedEntry = obj;
        if (obj == null) {
            LinkedEntry linkedEntry2 = new LinkedEntry(k);
            makeTail(linkedEntry2);
            map.put(k, linkedEntry2);
            linkedEntry = linkedEntry2;
        }
        ((LinkedEntry) linkedEntry).add(v);
    }

    public final V get(K k) {
        Map map = this.keyToEntry;
        Object obj = map.get(k);
        if (obj == null) {
            obj = new LinkedEntry(k);
            map.put(k, obj);
        }
        LinkedEntry linkedEntry = (LinkedEntry) obj;
        makeHead(linkedEntry);
        return linkedEntry.removeLast();
    }

    public final V removeLast() {
        LinkedEntry<K, V> prev = this.head.getPrev();
        while (!Intrinsics.areEqual((Object) prev, (Object) this.head)) {
            V removeLast = prev.removeLast();
            if (removeLast != null) {
                return removeLast;
            }
            removeEntry(prev);
            Map map = this.keyToEntry;
            K key = prev.getKey();
            if (map != null) {
                TypeIntrinsics.asMutableMap(map).remove(key);
                prev = prev.getPrev();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GroupedLinkedMap( ");
        LinkedEntry<K, V> next = this.head.getNext();
        boolean z = false;
        while (!Intrinsics.areEqual((Object) next, (Object) this.head)) {
            sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
            sb.append(next.getKey());
            sb.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
            sb.append(next.size());
            sb.append("}, ");
            next = next.getNext();
            z = true;
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void makeHead(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        linkedEntry.setPrev(this.head);
        linkedEntry.setNext(this.head.getNext());
        updateEntry(linkedEntry);
    }

    private final void makeTail(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        linkedEntry.setPrev(this.head.getPrev());
        linkedEntry.setNext(this.head);
        updateEntry(linkedEntry);
    }

    private final <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.getNext().setPrev(linkedEntry);
        linkedEntry.getPrev().setNext(linkedEntry);
    }

    private final <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.getPrev().setNext(linkedEntry.getNext());
        linkedEntry.getNext().setPrev(linkedEntry.getPrev());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u0002¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0016\u001a\u0004\u0018\u00018\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0017\u001a\u00020\u0018R\u0015\u0010\u0004\u001a\u0004\u0018\u00018\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR&\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcoil/collection/GroupedLinkedMap$LinkedEntry;", "K", "V", "", "key", "(Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "next", "getNext", "()Lcoil/collection/GroupedLinkedMap$LinkedEntry;", "setNext", "(Lcoil/collection/GroupedLinkedMap$LinkedEntry;)V", "prev", "getPrev", "setPrev", "values", "", "add", "", "value", "removeLast", "size", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: GroupedLinkedMap.kt */
    private static final class LinkedEntry<K, V> {
        private final K key;
        private LinkedEntry<K, V> next;
        private LinkedEntry<K, V> prev;
        private List<V> values;

        public LinkedEntry() {
            this((Object) null, 1, (DefaultConstructorMarker) null);
        }

        public LinkedEntry(K k) {
            this.key = k;
            LinkedEntry<K, V> linkedEntry = this;
            this.prev = linkedEntry;
            this.next = linkedEntry;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LinkedEntry(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : obj);
        }

        public final K getKey() {
            return this.key;
        }

        public final LinkedEntry<K, V> getPrev() {
            return this.prev;
        }

        public final void setPrev(LinkedEntry<K, V> linkedEntry) {
            Intrinsics.checkParameterIsNotNull(linkedEntry, "<set-?>");
            this.prev = linkedEntry;
        }

        public final LinkedEntry<K, V> getNext() {
            return this.next;
        }

        public final void setNext(LinkedEntry<K, V> linkedEntry) {
            Intrinsics.checkParameterIsNotNull(linkedEntry, "<set-?>");
            this.next = linkedEntry;
        }

        public final V removeLast() {
            List<V> list = this.values;
            if (list == null || !(!list.isEmpty())) {
                return null;
            }
            return list.remove(CollectionsKt.getLastIndex(list));
        }

        public final int size() {
            List<V> list = this.values;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public final void add(V v) {
            List<V> list = this.values;
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(v);
            this.values = list;
        }
    }
}
