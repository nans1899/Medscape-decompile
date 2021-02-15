package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.ProcessingInstruction;

public class ProcessingInstructionEvent extends BaseEvent implements ProcessingInstruction {
    String content;
    String name;

    public ProcessingInstructionEvent() {
        init();
    }

    public ProcessingInstructionEvent(String str, String str2) {
        init();
        this.name = str;
        this.content = str2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(3);
    }

    public String getTarget() {
        return this.name;
    }

    public void setTarget(String str) {
        this.name = str;
    }

    public void setData(String str) {
        this.content = str;
    }

    public String getData() {
        return this.content;
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<?");
        String str = this.name;
        if (str != null) {
            writer.write(str);
        }
        if (this.content != null) {
            writer.write(32);
            writer.write(this.content);
        }
        writer.write("?>");
    }
}
