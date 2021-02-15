package se.emilsjolander.stickylistheaders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DistinctMultiHashMap<TKey, TItemValue> {
    private IDMapper<TKey, TItemValue> mIDMapper;
    LinkedHashMap<Object, List<TItemValue>> mKeyToValuesMap;
    LinkedHashMap<Object, TKey> mValueToKeyIndexer;

    interface IDMapper<TKey, TItemValue> {
        TKey keyIdToKey(Object obj);

        Object keyToKeyId(TKey tkey);

        TItemValue valueIdToValue(Object obj);

        Object valueToValueId(TItemValue titemvalue);
    }

    DistinctMultiHashMap() {
        this(new IDMapper<TKey, TItemValue>() {
            public TKey keyIdToKey(Object obj) {
                return obj;
            }

            public Object keyToKeyId(TKey tkey) {
                return tkey;
            }

            public TItemValue valueIdToValue(Object obj) {
                return obj;
            }

            public Object valueToValueId(TItemValue titemvalue) {
                return titemvalue;
            }
        });
    }

    DistinctMultiHashMap(IDMapper<TKey, TItemValue> iDMapper) {
        this.mKeyToValuesMap = new LinkedHashMap<>();
        this.mValueToKeyIndexer = new LinkedHashMap<>();
        this.mIDMapper = iDMapper;
    }

    public List<TItemValue> get(TKey tkey) {
        return this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(tkey));
    }

    public TKey getKey(TItemValue titemvalue) {
        return this.mValueToKeyIndexer.get(this.mIDMapper.valueToValueId(titemvalue));
    }

    public void add(TKey tkey, TItemValue titemvalue) {
        Object keyToKeyId = this.mIDMapper.keyToKeyId(tkey);
        if (this.mKeyToValuesMap.get(keyToKeyId) == null) {
            this.mKeyToValuesMap.put(keyToKeyId, new ArrayList());
        }
        Object key = getKey(titemvalue);
        if (key != null) {
            this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(key)).remove(titemvalue);
        }
        this.mValueToKeyIndexer.put(this.mIDMapper.valueToValueId(titemvalue), tkey);
        if (!containsValue(this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(tkey)), titemvalue)) {
            this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(tkey)).add(titemvalue);
        }
    }

    public void removeKey(TKey tkey) {
        if (this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(tkey)) != null) {
            for (TItemValue valueToValueId : this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(tkey))) {
                this.mValueToKeyIndexer.remove(this.mIDMapper.valueToValueId(valueToValueId));
            }
            this.mKeyToValuesMap.remove(this.mIDMapper.keyToKeyId(tkey));
        }
    }

    public void removeValue(TItemValue titemvalue) {
        List list;
        if (!(getKey(titemvalue) == null || (list = this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(getKey(titemvalue)))) == null)) {
            list.remove(titemvalue);
        }
        this.mValueToKeyIndexer.remove(this.mIDMapper.valueToValueId(titemvalue));
    }

    public void clear() {
        this.mValueToKeyIndexer.clear();
        this.mKeyToValuesMap.clear();
    }

    public void clearValues() {
        for (Map.Entry entry : entrySet()) {
            if (entry.getValue() != null) {
                ((List) entry.getValue()).clear();
            }
        }
        this.mValueToKeyIndexer.clear();
    }

    public Set<Map.Entry<Object, List<TItemValue>>> entrySet() {
        return this.mKeyToValuesMap.entrySet();
    }

    public Set<Map.Entry<Object, TKey>> reverseEntrySet() {
        return this.mValueToKeyIndexer.entrySet();
    }

    public int size() {
        return this.mKeyToValuesMap.size();
    }

    public int valuesSize() {
        return this.mValueToKeyIndexer.size();
    }

    /* access modifiers changed from: protected */
    public boolean containsValue(List<TItemValue> list, TItemValue titemvalue) {
        for (TItemValue valueToValueId : list) {
            if (this.mIDMapper.valueToValueId(valueToValueId).equals(this.mIDMapper.valueToValueId(titemvalue))) {
                return true;
            }
        }
        return false;
    }

    public TItemValue getValueByPosition(int i) {
        Object[] array = this.mValueToKeyIndexer.keySet().toArray();
        if (i <= array.length) {
            return this.mIDMapper.valueIdToValue(array[i]);
        }
        throw new IndexOutOfBoundsException();
    }
}
