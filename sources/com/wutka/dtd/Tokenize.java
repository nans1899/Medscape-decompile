package com.wutka.dtd;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.Enumeration;
import net.bytebuddy.description.type.TypeDescription;

class Tokenize {
    Tokenize() {
    }

    public static void main(String[] strArr) {
        DTDParser dTDParser;
        try {
            if (strArr[0].indexOf("://") > 0) {
                dTDParser = new DTDParser(new URL(strArr[0]), true);
            } else {
                dTDParser = new DTDParser(new File(strArr[0]), true);
            }
            DTD parse = dTDParser.parse(true);
            if (parse.rootElement != null) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Root element is probably: ");
                stringBuffer.append(parse.rootElement.name);
                printStream.println(stringBuffer.toString());
            }
            Enumeration elements = parse.elements.elements();
            while (elements.hasMoreElements()) {
                DTDElement dTDElement = (DTDElement) elements.nextElement();
                PrintStream printStream2 = System.out;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Element: ");
                stringBuffer2.append(dTDElement.name);
                printStream2.println(stringBuffer2.toString());
                System.out.print("   Content: ");
                dumpDTDItem(dTDElement.content);
                System.out.println();
                if (dTDElement.attributes.size() > 0) {
                    System.out.println("   Attributes: ");
                    Enumeration elements2 = dTDElement.attributes.elements();
                    while (elements2.hasMoreElements()) {
                        System.out.print("        ");
                        dumpAttribute((DTDAttribute) elements2.nextElement());
                    }
                    System.out.println();
                }
            }
            Enumeration elements3 = parse.entities.elements();
            while (elements3.hasMoreElements()) {
                DTDEntity dTDEntity = (DTDEntity) elements3.nextElement();
                if (dTDEntity.isParsed) {
                    System.out.print("Parsed ");
                }
                PrintStream printStream3 = System.out;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Entity: ");
                stringBuffer3.append(dTDEntity.name);
                printStream3.println(stringBuffer3.toString());
                if (dTDEntity.value != null) {
                    PrintStream printStream4 = System.out;
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("    Value: ");
                    stringBuffer4.append(dTDEntity.value);
                    printStream4.println(stringBuffer4.toString());
                }
                if (dTDEntity.externalID != null) {
                    if (dTDEntity.externalID instanceof DTDSystem) {
                        PrintStream printStream5 = System.out;
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("    System: ");
                        stringBuffer5.append(dTDEntity.externalID.system);
                        printStream5.println(stringBuffer5.toString());
                    } else {
                        DTDPublic dTDPublic = (DTDPublic) dTDEntity.externalID;
                        PrintStream printStream6 = System.out;
                        StringBuffer stringBuffer6 = new StringBuffer();
                        stringBuffer6.append("    Public: ");
                        stringBuffer6.append(dTDPublic.pub);
                        stringBuffer6.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        stringBuffer6.append(dTDPublic.system);
                        printStream6.println(stringBuffer6.toString());
                    }
                }
                if (dTDEntity.ndata != null) {
                    PrintStream printStream7 = System.out;
                    StringBuffer stringBuffer7 = new StringBuffer();
                    stringBuffer7.append("    NDATA ");
                    stringBuffer7.append(dTDEntity.ndata);
                    printStream7.println(stringBuffer7.toString());
                }
            }
            Enumeration elements4 = parse.notations.elements();
            while (elements4.hasMoreElements()) {
                DTDNotation dTDNotation = (DTDNotation) elements4.nextElement();
                PrintStream printStream8 = System.out;
                StringBuffer stringBuffer8 = new StringBuffer();
                stringBuffer8.append("Notation: ");
                stringBuffer8.append(dTDNotation.name);
                printStream8.println(stringBuffer8.toString());
                if (dTDNotation.externalID != null) {
                    if (dTDNotation.externalID instanceof DTDSystem) {
                        PrintStream printStream9 = System.out;
                        StringBuffer stringBuffer9 = new StringBuffer();
                        stringBuffer9.append("    System: ");
                        stringBuffer9.append(dTDNotation.externalID.system);
                        printStream9.println(stringBuffer9.toString());
                    } else {
                        DTDPublic dTDPublic2 = (DTDPublic) dTDNotation.externalID;
                        PrintStream printStream10 = System.out;
                        StringBuffer stringBuffer10 = new StringBuffer();
                        stringBuffer10.append("    Public: ");
                        stringBuffer10.append(dTDPublic2.pub);
                        stringBuffer10.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        printStream10.print(stringBuffer10.toString());
                        if (dTDPublic2.system != null) {
                            System.out.println(dTDPublic2.system);
                        } else {
                            System.out.println();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void dumpDTDItem(DTDItem dTDItem) {
        if (dTDItem != null) {
            if (dTDItem instanceof DTDAny) {
                System.out.print("Any");
            } else if (dTDItem instanceof DTDEmpty) {
                System.out.print("Empty");
            } else if (dTDItem instanceof DTDName) {
                System.out.print(((DTDName) dTDItem).value);
            } else {
                int i = 0;
                if (dTDItem instanceof DTDChoice) {
                    System.out.print("(");
                    DTDItem[] items = ((DTDChoice) dTDItem).getItems();
                    while (i < items.length) {
                        if (i > 0) {
                            System.out.print("|");
                        }
                        dumpDTDItem(items[i]);
                        i++;
                    }
                    System.out.print(")");
                } else if (dTDItem instanceof DTDSequence) {
                    System.out.print("(");
                    DTDItem[] items2 = ((DTDSequence) dTDItem).getItems();
                    while (i < items2.length) {
                        if (i > 0) {
                            System.out.print(",");
                        }
                        dumpDTDItem(items2[i]);
                        i++;
                    }
                    System.out.print(")");
                } else if (dTDItem instanceof DTDMixed) {
                    System.out.print("(");
                    DTDItem[] items3 = ((DTDMixed) dTDItem).getItems();
                    while (i < items3.length) {
                        if (i > 0) {
                            System.out.print(",");
                        }
                        dumpDTDItem(items3[i]);
                        i++;
                    }
                    System.out.print(")");
                } else if (dTDItem instanceof DTDPCData) {
                    System.out.print("#PCDATA");
                }
            }
            if (dTDItem.cardinal == DTDCardinal.OPTIONAL) {
                System.out.print(TypeDescription.Generic.OfWildcardType.SYMBOL);
            } else if (dTDItem.cardinal == DTDCardinal.ZEROMANY) {
                System.out.print("*");
            } else if (dTDItem.cardinal == DTDCardinal.ONEMANY) {
                System.out.print("+");
            }
        }
    }

    public static void dumpAttribute(DTDAttribute dTDAttribute) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dTDAttribute.name);
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        printStream.print(stringBuffer.toString());
        if (dTDAttribute.type instanceof String) {
            System.out.print(dTDAttribute.type);
        } else {
            int i = 0;
            if (dTDAttribute.type instanceof DTDEnumeration) {
                System.out.print("(");
                String[] items = ((DTDEnumeration) dTDAttribute.type).getItems();
                while (i < items.length) {
                    if (i > 0) {
                        System.out.print(",");
                    }
                    System.out.print(items[i]);
                    i++;
                }
                System.out.print(")");
            } else if (dTDAttribute.type instanceof DTDNotationList) {
                System.out.print("Notation (");
                String[] items2 = ((DTDNotationList) dTDAttribute.type).getItems();
                while (i < items2.length) {
                    if (i > 0) {
                        System.out.print(",");
                    }
                    System.out.print(items2[i]);
                    i++;
                }
                System.out.print(")");
            }
        }
        if (dTDAttribute.decl != null) {
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer2.append(dTDAttribute.decl.name);
            printStream2.print(stringBuffer2.toString());
        }
        if (dTDAttribute.defaultValue != null) {
            PrintStream printStream3 = System.out;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer3.append(dTDAttribute.defaultValue);
            printStream3.print(stringBuffer3.toString());
        }
        System.out.println();
    }
}
