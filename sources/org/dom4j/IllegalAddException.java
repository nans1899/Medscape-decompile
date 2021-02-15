package org.dom4j;

public class IllegalAddException extends IllegalArgumentException {
    public IllegalAddException(String str) {
        super(str);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IllegalAddException(org.dom4j.Element r3, org.dom4j.Node r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            java.lang.String r1 = "The node \""
            r0.<init>(r1)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r4 = "\" could not be added to the element \""
            r0.append(r4)
            java.lang.String r3 = r3.getQualifiedName()
            r0.append(r3)
            java.lang.String r3 = "\" because: "
            r0.append(r3)
            r0.append(r5)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.IllegalAddException.<init>(org.dom4j.Element, org.dom4j.Node, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IllegalAddException(org.dom4j.Branch r3, org.dom4j.Node r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            java.lang.String r1 = "The node \""
            r0.<init>(r1)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r4 = "\" could not be added to the branch \""
            r0.append(r4)
            java.lang.String r3 = r3.getName()
            r0.append(r3)
            java.lang.String r3 = "\" because: "
            r0.append(r3)
            r0.append(r5)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.IllegalAddException.<init>(org.dom4j.Branch, org.dom4j.Node, java.lang.String):void");
    }
}
