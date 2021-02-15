package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.StartDocument;

public class StartDocumentEvent extends BaseEvent implements StartDocument {
    protected String encodingScheme = "UTF-8";
    private boolean encodingSchemeSet = false;
    protected String publicId = "";
    protected boolean standalone = false;
    private boolean standaloneSet = false;
    protected String systemId = "";
    protected String version = "1.0";

    public StartDocumentEvent() {
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(7);
    }

    public String getSystemId() {
        return this.systemId;
    }

    public String getCharacterEncodingScheme() {
        return this.encodingScheme;
    }

    public boolean isStandalone() {
        return this.standalone;
    }

    public String getVersion() {
        return this.version;
    }

    public void setStandalone(boolean z) {
        this.standaloneSet = true;
        this.standalone = z;
    }

    public void setStandalone(String str) {
        this.standaloneSet = true;
        if (str == null) {
            this.standalone = true;
        } else if (str.equals("yes")) {
            this.standalone = true;
        } else {
            this.standalone = false;
        }
    }

    public boolean encodingSet() {
        return this.encodingSchemeSet;
    }

    public boolean standaloneSet() {
        return this.standaloneSet;
    }

    public void setEncoding(String str) {
        this.encodingScheme = str;
        this.encodingSchemeSet = true;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void clear() {
        this.encodingScheme = "UTF-8";
        this.standalone = true;
        this.version = "1.0";
        this.encodingSchemeSet = false;
        this.standaloneSet = false;
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<?xml version=\"");
        writer.write(this.version);
        writer.write("\" encoding='");
        writer.write(this.encodingScheme);
        writer.write(39);
        if (this.standaloneSet) {
            writer.write(" standalone='");
            writer.write(this.standalone ? "yes'" : "no'");
        }
        writer.write("?>");
    }
}
