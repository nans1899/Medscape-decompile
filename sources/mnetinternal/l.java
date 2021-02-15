package mnetinternal;

import java.io.File;
import java.io.IOException;

public class l extends RuntimeException {
    private final File a;

    public l(String str, IOException iOException, File file) {
        super(str, iOException);
        this.a = file;
    }
}
