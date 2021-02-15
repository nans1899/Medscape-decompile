package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDProcessingInstruction implements DTDOutput {
    public String text;

    public DTDProcessingInstruction() {
    }

    public DTDProcessingInstruction(String str) {
        this.text = str;
    }

    public String toString() {
        return this.text;
    }

    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("<?");
        printWriter.print(this.text);
        printWriter.println("?>");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDProcessingInstruction)) {
            return false;
        }
        DTDProcessingInstruction dTDProcessingInstruction = (DTDProcessingInstruction) obj;
        String str = this.text;
        if (str == null) {
            if (dTDProcessingInstruction.text != null) {
                return false;
            }
        } else if (!str.equals(dTDProcessingInstruction.text)) {
            return false;
        }
        return true;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
}
