package se.emilsjolander.stickylistheaders;

import java.util.HashMap;

class DualHashMap<TKey, TValue> {
    HashMap<TKey, TValue> mKeyToValue = new HashMap<>();
    HashMap<TValue, TKey> mValueToKey = new HashMap<>();

    DualHashMap() {
    }

    public void put(TKey tkey, TValue tvalue) {
        remove(tkey);
        removeByValue(tvalue);
        this.mKeyToValue.put(tkey, tvalue);
        this.mValueToKey.put(tvalue, tkey);
    }

    public TKey getKey(TValue tvalue) {
        return this.mValueToKey.get(tvalue);
    }

    public TValue get(TKey tkey) {
        return this.mKeyToValue.get(tkey);
    }

    public void remove(TKey tkey) {
        if (get(tkey) != null) {
            this.mValueToKey.remove(get(tkey));
        }
        this.mKeyToValue.remove(tkey);
    }

    public void removeByValue(TValue tvalue) {
        if (getKey(tvalue) != null) {
            this.mKeyToValue.remove(getKey(tvalue));
        }
        this.mValueToKey.remove(tvalue);
    }
}
