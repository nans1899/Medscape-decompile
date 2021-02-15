package com.bea.xml.stream;

import javax.xml.stream.XMLStreamException;

public class XMLEventPlayer extends XMLEventReaderBase {
    private XMLStreamPlayer player;

    public XMLEventPlayer(XMLStreamPlayer xMLStreamPlayer) throws XMLStreamException {
        super(xMLStreamPlayer);
        this.player = xMLStreamPlayer;
    }

    /* access modifiers changed from: protected */
    public boolean parseSome() throws XMLStreamException {
        this.allocator.allocate(this.reader, this);
        if (this.reader.hasNext()) {
            this.reader.next();
        }
        if (isOpen() && this.reader.getEventType() == 8) {
            if (this.player.endDocumentIsPresent()) {
                this.allocator.allocate(this.reader, this);
            }
            internal_close();
        }
        return !needsMore();
    }
}
