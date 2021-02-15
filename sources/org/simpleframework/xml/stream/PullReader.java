package org.simpleframework.xml.stream;

import org.xmlpull.v1.XmlPullParser;

class PullReader implements EventReader {
    private XmlPullParser parser;
    private EventNode peek;

    public PullReader(XmlPullParser xmlPullParser) {
        this.parser = xmlPullParser;
    }

    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        int next = this.parser.next();
        if (next == 1) {
            return null;
        }
        if (next == 2) {
            return start();
        }
        if (next == 4) {
            return text();
        }
        if (next == 3) {
            return end();
        }
        return read();
    }

    private Text text() throws Exception {
        return new Text(this.parser);
    }

    private Start start() throws Exception {
        Start start = new Start(this.parser);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) throws Exception {
        int attributeCount = this.parser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            Entry attribute = attribute(i);
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(int i) throws Exception {
        return new Entry(this.parser, i);
    }

    private End end() throws Exception {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;
        private final String value;

        public boolean isReserved() {
            return false;
        }

        public Entry(XmlPullParser xmlPullParser, int i) {
            this.reference = xmlPullParser.getAttributeNamespace(i);
            this.prefix = xmlPullParser.getAttributePrefix(i);
            this.value = xmlPullParser.getAttributeValue(i);
            this.name = xmlPullParser.getAttributeName(i);
            this.source = xmlPullParser;
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public String getReference() {
            return this.reference;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public Object getSource() {
            return this.source;
        }
    }

    private static class Start extends EventElement {
        private final int line;
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;

        public Start(XmlPullParser xmlPullParser) {
            this.reference = xmlPullParser.getNamespace();
            this.line = xmlPullParser.getLineNumber();
            this.prefix = xmlPullParser.getPrefix();
            this.name = xmlPullParser.getName();
            this.source = xmlPullParser;
        }

        public int getLine() {
            return this.line;
        }

        public String getName() {
            return this.name;
        }

        public String getReference() {
            return this.reference;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public Object getSource() {
            return this.source;
        }
    }

    private static class Text extends EventToken {
        private final XmlPullParser source;
        private final String text;

        public boolean isText() {
            return true;
        }

        public Text(XmlPullParser xmlPullParser) {
            this.text = xmlPullParser.getText();
            this.source = xmlPullParser;
        }

        public String getValue() {
            return this.text;
        }

        public Object getSource() {
            return this.source;
        }
    }

    private static class End extends EventToken {
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
