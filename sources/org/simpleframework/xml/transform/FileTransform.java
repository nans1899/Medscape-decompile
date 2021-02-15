package org.simpleframework.xml.transform;

import java.io.File;

class FileTransform implements Transform<File> {
    FileTransform() {
    }

    public File read(String str) {
        return new File(str);
    }

    public String write(File file) {
        return file.getPath();
    }
}
