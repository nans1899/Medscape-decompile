package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDName extends DTDItem {
    public String value;

    public DTDName() {
    }

    public DTDName(String str) {
        this.value = str;
    }

    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print(this.value);
        this.cardinal.write(printWriter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDName) || !super.equals(obj)) {
            return false;
        }
        DTDName dTDName = (DTDName) obj;
        String str = this.value;
        if (str == null) {
            if (dTDName.value != null) {
                return false;
            }
        } else if (!str.equals(dTDName.value)) {
            return false;
        }
        return true;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
