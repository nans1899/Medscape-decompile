package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class DTDChoice extends DTDContainer {
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("(");
        Enumeration elements = getItemsVec().elements();
        boolean z = true;
        while (elements.hasMoreElements()) {
            if (!z) {
                printWriter.print(" | ");
            }
            z = false;
            ((DTDItem) elements.nextElement()).write(printWriter);
        }
        printWriter.print(")");
        this.cardinal.write(printWriter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDChoice)) {
            return false;
        }
        return super.equals(obj);
    }
}
