package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.Characters;

public class CharactersEvent extends BaseEvent implements Characters {
    private String data;
    private boolean isCData = false;
    private boolean isIgnorable = false;
    private boolean isSpace = false;

    public CharactersEvent() {
        init();
    }

    public CharactersEvent(String str) {
        init();
        setData(str);
    }

    public CharactersEvent(String str, boolean z) {
        init();
        setData(str);
        this.isCData = z;
    }

    public void setSpace(boolean z) {
        this.isSpace = z;
    }

    public boolean isWhiteSpace() {
        return this.isSpace;
    }

    public boolean isIgnorableWhiteSpace() {
        return this.isIgnorable;
    }

    public void setIgnorable(boolean z) {
        this.isIgnorable = z;
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(4);
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public boolean isCData() {
        return this.isCData;
    }

    public char[] getDataAsArray() {
        return this.data.toCharArray();
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        if (this.isCData) {
            writer.write("<![CDATA[");
            writer.write(getData());
            writer.write("]]>");
            return;
        }
        String data2 = getData();
        int length = data2.length();
        if (length > 0) {
            int i = 0;
            while (i < length && (r7 = data2.charAt(i)) != '&' && r7 != '<' && r7 != '>') {
                i++;
            }
            if (i == length) {
                writer.write(data2);
                return;
            }
            if (i > 0) {
                writer.write(data2, 0, i);
            }
            while (i < length) {
                char charAt = data2.charAt(i);
                if (charAt == '&') {
                    writer.write("&amp;");
                } else if (charAt == '<') {
                    writer.write("&lt;");
                } else if (charAt != '>') {
                    writer.write(charAt);
                } else {
                    writer.write("&gt;");
                }
                i++;
            }
        }
    }
}
