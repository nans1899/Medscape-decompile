package org.mockito.internal.configuration.plugins;

import java.io.InputStream;
import org.mockito.internal.util.io.IOUtil;

class PluginFileReader {
    PluginFileReader() {
    }

    /* access modifiers changed from: package-private */
    public String readPluginClass(InputStream inputStream) {
        for (String stripCommentAndWhitespace : IOUtil.readLines(inputStream)) {
            String stripCommentAndWhitespace2 = stripCommentAndWhitespace(stripCommentAndWhitespace);
            if (stripCommentAndWhitespace2.length() > 0) {
                return stripCommentAndWhitespace2;
            }
        }
        return null;
    }

    private static String stripCommentAndWhitespace(String str) {
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        return str.trim();
    }
}
