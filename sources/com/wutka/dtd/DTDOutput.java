package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public interface DTDOutput {
    void write(PrintWriter printWriter) throws IOException;
}
