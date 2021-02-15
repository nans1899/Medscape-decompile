package org.simpleframework.xml.stream;

class HyphenBuilder implements Style {
    HyphenBuilder() {
    }

    public String getAttribute(String str) {
        if (str != null) {
            return new Parser(str).process();
        }
        return null;
    }

    public String getElement(String str) {
        if (str != null) {
            return new Parser(str).process();
        }
        return null;
    }

    private class Parser extends Splitter {
        private Parser(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void parse(char[] cArr, int i, int i2) {
            cArr[i] = toLower(cArr[i]);
        }

        /* access modifiers changed from: protected */
        public void commit(char[] cArr, int i, int i2) {
            this.builder.append(cArr, i, i2);
            if (i + i2 < this.count) {
                this.builder.append('-');
            }
        }
    }
}
