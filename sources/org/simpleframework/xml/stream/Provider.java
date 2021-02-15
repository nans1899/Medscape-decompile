package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;

interface Provider {
    EventReader provide(InputStream inputStream) throws Exception;

    EventReader provide(Reader reader) throws Exception;
}
