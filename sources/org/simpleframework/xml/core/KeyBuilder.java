package org.simpleframework.xml.core;

import java.util.Arrays;

class KeyBuilder {
    private final Label label;

    private enum KeyType {
        TEXT,
        ATTRIBUTE,
        ELEMENT
    }

    public KeyBuilder(Label label2) {
        this.label = label2;
    }

    public Object getKey() throws Exception {
        if (this.label.isAttribute()) {
            return getKey(KeyType.ATTRIBUTE);
        }
        return getKey(KeyType.ELEMENT);
    }

    private Object getKey(KeyType keyType) throws Exception {
        String key = getKey(this.label.getPaths());
        if (keyType == null) {
            return key;
        }
        return new Key(keyType, key);
    }

    private String getKey(String[] strArr) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (strArr.length > 0) {
            Arrays.sort(strArr);
            for (String append : strArr) {
                sb.append(append);
                sb.append('>');
            }
        }
        return sb.toString();
    }

    private static class Key {
        private final KeyType type;
        private final String value;

        public Key(KeyType keyType, String str) throws Exception {
            this.value = str;
            this.type = keyType;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                return equals((Key) obj);
            }
            return false;
        }

        public boolean equals(Key key) {
            if (this.type == key.type) {
                return key.value.equals(this.value);
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return this.value;
        }
    }
}
