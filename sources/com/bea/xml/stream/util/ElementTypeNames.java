package com.bea.xml.stream.util;

public class ElementTypeNames {
    public static final String getEventTypeString(int i) {
        switch (i) {
            case 1:
                return "START_ELEMENT";
            case 2:
                return "END_ELEMENT";
            case 3:
                return "PROCESSING_INSTRUCTION";
            case 4:
                return "CHARACTERS";
            case 5:
                return "COMMENT";
            case 6:
                return "SPACE";
            case 7:
                return "START_DOCUMENT";
            case 8:
                return "END_DOCUMENT";
            case 9:
                return "ENTITY_REFERENCE";
            case 10:
                return "ATTRIBUTE";
            case 11:
                return "DTD";
            case 12:
                return "CDATA";
            case 13:
                return "NAMESPACE";
            default:
                return "UNKNOWN_EVENT_TYPE";
        }
    }

    public static int getEventType(String str) {
        if (str.equals("START_ELEMENT")) {
            return 1;
        }
        if (str.equals("SPACE")) {
            return 6;
        }
        if (str.equals("END_ELEMENT")) {
            return 2;
        }
        if (str.equals("PROCESSING_INSTRUCTION")) {
            return 3;
        }
        if (str.equals("CHARACTERS")) {
            return 4;
        }
        if (str.equals("COMMENT")) {
            return 5;
        }
        if (str.equals("START_DOCUMENT")) {
            return 7;
        }
        if (str.equals("END_DOCUMENT")) {
            return 8;
        }
        if (str.equals("ATTRIBUTE")) {
            return 10;
        }
        if (str.equals("DTD")) {
            return 11;
        }
        if (str.equals("CDATA")) {
            return 12;
        }
        return str.equals("NAMESPACE") ? 13 : -1;
    }
}
