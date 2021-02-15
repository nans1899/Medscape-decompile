package org.apache.commons.io.input;

import java.io.Reader;

public class CharacterFilterReader extends AbstractCharacterFilterReader {
    private final int skip;

    public CharacterFilterReader(Reader reader, int i) {
        super(reader);
        this.skip = i;
    }

    /* access modifiers changed from: protected */
    public boolean filter(int i) {
        return i == this.skip;
    }
}
