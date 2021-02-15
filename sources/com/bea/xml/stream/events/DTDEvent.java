package com.bea.xml.stream.events;

import com.wutka.dtd.DTDEntity;
import com.wutka.dtd.DTDExternalID;
import com.wutka.dtd.DTDNotation;
import com.wutka.dtd.DTDPublic;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.NotationDeclaration;

public class DTDEvent extends BaseEvent implements DTD {
    private String dtd;
    private List entities;
    private List notations;

    public Object getProcessedDTD() {
        return null;
    }

    public DTDEvent() {
        init();
    }

    public DTDEvent(String str) {
        init();
        setDTD(str);
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(11);
    }

    public static EntityDeclaration createEntityDeclaration(DTDEntity dTDEntity) {
        return new EntityDeclarationEvent(dTDEntity.getName(), dTDEntity.getValue());
    }

    public static NotationDeclaration createNotationDeclaration(DTDNotation dTDNotation) {
        DTDExternalID externalID = dTDNotation.getExternalID();
        return new NotationDeclarationEvent(dTDNotation.getName(), externalID instanceof DTDPublic ? ((DTDPublic) externalID).getPub() : null, externalID.getSystem());
    }

    public void setDTD(String str) {
        this.dtd = str;
    }

    public void setNotations(List list) {
        this.notations = list;
    }

    public void setEntities(List list) {
        this.entities = list;
    }

    public String getDocumentTypeDeclaration() {
        return this.dtd;
    }

    public List getEntities() {
        return this.entities;
    }

    public List getNotations() {
        return this.notations;
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!DOCTYPE ");
        String str = this.dtd;
        if (str != null && str.length() > 0) {
            writer.write(91);
            writer.write(this.dtd);
            writer.write(93);
        }
        writer.write(62);
    }
}
