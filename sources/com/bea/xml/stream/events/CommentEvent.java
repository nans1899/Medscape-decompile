package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.Comment;

public class CommentEvent extends CharactersEvent implements Comment {
    public CommentEvent() {
        init();
    }

    public CommentEvent(String str) {
        init();
        setData(str);
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(5);
    }

    public String getText() {
        return getData();
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!--");
        String text = getText();
        if (text.length() > 0) {
            writer.write(text);
        }
        writer.write("-->");
    }
}
