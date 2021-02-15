package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDAny extends DTDItem {
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("ANY");
        this.cardinal.write(printWriter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDAny)) {
            return false;
        }
        return super.equals(obj);
    }
}
