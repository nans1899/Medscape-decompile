package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDDecl implements DTDOutput {
    public static final DTDDecl FIXED = new DTDDecl(0, "FIXED");
    public static final DTDDecl IMPLIED = new DTDDecl(2, "IMPLIED");
    public static final DTDDecl REQUIRED = new DTDDecl(1, "REQUIRED");
    public static final DTDDecl VALUE = new DTDDecl(3, "VALUE");
    public String name;
    public int type;

    public DTDDecl(int i, String str) {
        this.type = i;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DTDDecl) && ((DTDDecl) obj).type == this.type;
    }

    public void write(PrintWriter printWriter) throws IOException {
        if (this == FIXED) {
            printWriter.print(" #FIXED");
        } else if (this == REQUIRED) {
            printWriter.print(" #REQUIRED");
        } else if (this == IMPLIED) {
            printWriter.print(" #IMPLIED");
        }
    }
}
