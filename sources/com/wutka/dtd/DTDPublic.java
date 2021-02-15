package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDPublic extends DTDExternalID {
    public String pub;

    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("PUBLIC \"");
        printWriter.print(this.pub);
        printWriter.print("\"");
        if (this.system != null) {
            printWriter.print(" \"");
            printWriter.print(this.system);
            printWriter.print("\"");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDPublic) || !super.equals(obj)) {
            return false;
        }
        DTDPublic dTDPublic = (DTDPublic) obj;
        String str = this.pub;
        if (str == null) {
            if (dTDPublic.pub != null) {
                return false;
            }
        } else if (!str.equals(dTDPublic.pub)) {
            return false;
        }
        return true;
    }

    public void setPub(String str) {
        this.pub = str;
    }

    public String getPub() {
        return this.pub;
    }
}
