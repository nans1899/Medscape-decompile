package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDComment implements DTDOutput {
    public String text;

    public DTDComment() {
    }

    public DTDComment(String str) {
        this.text = str;
    }

    public String toString() {
        return this.text;
    }

    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("<!--");
        printWriter.print(this.text);
        printWriter.println("-->");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDComment)) {
            return false;
        }
        DTDComment dTDComment = (DTDComment) obj;
        if (this.text == null && dTDComment.text != null) {
            return false;
        }
        String str = this.text;
        return str == null || str.equals(dTDComment.text);
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
}
