package com.wutka.dtd;

import java.io.PrintWriter;

public class DTDSystem extends DTDExternalID {
    public void write(PrintWriter printWriter) {
        if (this.system != null) {
            printWriter.print("SYSTEM \"");
            printWriter.print(this.system);
            printWriter.print("\"");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDSystem)) {
            return false;
        }
        return super.equals(obj);
    }
}
