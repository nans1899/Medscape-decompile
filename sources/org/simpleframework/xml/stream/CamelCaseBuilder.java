package org.simpleframework.xml.stream;

class CamelCaseBuilder implements Style {
    protected final boolean attribute;
    protected final boolean element;

    public CamelCaseBuilder(boolean z, boolean z2) {
        this.attribute = z2;
        this.element = z;
    }

    public String getAttribute(String str) {
        if (str != null) {
            return new Attribute(str).process();
        }
        return null;
    }

    public String getElement(String str) {
        if (str != null) {
            return new Element(str).process();
        }
        return null;
    }

    private class Attribute extends Splitter {
        private boolean capital;

        private Attribute(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void parse(char[] cArr, int i, int i2) {
            if (CamelCaseBuilder.this.attribute || this.capital) {
                cArr[i] = toUpper(cArr[i]);
            }
            this.capital = true;
        }

        /* access modifiers changed from: protected */
        public void commit(char[] cArr, int i, int i2) {
            this.builder.append(cArr, i, i2);
        }
    }

    private class Element extends Attribute {
        private boolean capital;

        private Element(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void parse(char[] cArr, int i, int i2) {
            if (CamelCaseBuilder.this.element || this.capital) {
                cArr[i] = toUpper(cArr[i]);
            }
            this.capital = true;
        }
    }
}
