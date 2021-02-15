package javax.xml.stream;

public class XMLStreamException extends Exception {
    protected Location location;
    protected Throwable nested;

    public XMLStreamException() {
    }

    public XMLStreamException(String str) {
        super(str);
    }

    public XMLStreamException(Throwable th) {
        this.nested = th;
    }

    public XMLStreamException(String str, Throwable th) {
        super(str);
        this.nested = th;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMLStreamException(java.lang.String r3, javax.xml.stream.Location r4, java.lang.Throwable r5) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "ParseError at [row,col]:["
            r0.append(r1)
            int r1 = r4.getLineNumber()
            r0.append(r1)
            java.lang.String r1 = ","
            r0.append(r1)
            int r1 = r4.getColumnNumber()
            r0.append(r1)
            java.lang.String r1 = "]\n"
            r0.append(r1)
            java.lang.String r1 = "Message: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            r2.nested = r5
            r2.location = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.xml.stream.XMLStreamException.<init>(java.lang.String, javax.xml.stream.Location, java.lang.Throwable):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMLStreamException(java.lang.String r3, javax.xml.stream.Location r4) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "ParseError at [row,col]:["
            r0.append(r1)
            int r1 = r4.getLineNumber()
            r0.append(r1)
            java.lang.String r1 = ","
            r0.append(r1)
            int r1 = r4.getColumnNumber()
            r0.append(r1)
            java.lang.String r1 = "]\n"
            r0.append(r1)
            java.lang.String r1 = "Message: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            r2.location = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.xml.stream.XMLStreamException.<init>(java.lang.String, javax.xml.stream.Location):void");
    }

    public Throwable getNestedException() {
        return this.nested;
    }

    public Location getLocation() {
        return this.location;
    }
}
