package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import net.bytebuddy.description.type.TypeDescription;

public class DTDCardinal implements DTDOutput {
    public static final DTDCardinal NONE = new DTDCardinal(0, "NONE");
    public static final DTDCardinal ONEMANY = new DTDCardinal(3, "ONEMANY");
    public static final DTDCardinal OPTIONAL = new DTDCardinal(1, "OPTIONAL");
    public static final DTDCardinal ZEROMANY = new DTDCardinal(2, "ZEROMANY");
    public String name;
    public int type;

    public DTDCardinal(int i, String str) {
        this.type = i;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DTDCardinal) && ((DTDCardinal) obj).type == this.type;
    }

    public void write(PrintWriter printWriter) throws IOException {
        if (this != NONE) {
            if (this == OPTIONAL) {
                printWriter.print(TypeDescription.Generic.OfWildcardType.SYMBOL);
            } else if (this == ZEROMANY) {
                printWriter.print("*");
            } else if (this == ONEMANY) {
                printWriter.print("+");
            }
        }
    }
}
