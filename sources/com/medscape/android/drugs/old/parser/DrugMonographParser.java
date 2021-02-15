package com.medscape.android.drugs.old.parser;

import com.appboy.Constants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.db.model.DrugClass;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographHeader;
import com.medscape.android.drugs.model.DrugMonographSection;
import com.medscape.android.drugs.parser.PregnancyCategoriesParser;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DrugMonographParser {
    private static final int ADULT_INDEX = 0;
    private static final String GERIATRIC_DOSING_TITLE = "Geriatric Dosing";
    private static final int GERIATRIC_INDEX = 2;
    private static final int PREGNANCY_INDEX = 6;
    private static int index;

    public static DrugMonograph parse(int i) {
        DrugMonograph drugMonograph = new DrugMonograph();
        try {
            Document read = new SAXReader().read((InputStream) new ByteArrayInputStream(FileHelper.readFileAsString(new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/" + i + ".xml")).getBytes()));
            drugMonograph.setHeader(parseHeader(read));
            drugMonograph.setSections(parseSection(read));
        } catch (Exception e) {
            LogUtil.e("DrugMonographParser", "getMessage = %s", e.getMessage().toString());
        }
        return drugMonograph;
    }

    private static DrugMonographHeader parseHeader(Document document) {
        DrugMonographHeader drugMonographHeader = new DrugMonographHeader();
        drugMonographHeader.setGc(document.selectSingleNode("//dm/dc/h/gc").getText());
        drugMonographHeader.setAv(document.selectSingleNode("//dm/dc/h/av").getText());
        drugMonographHeader.setBr(document.selectSingleNode("//dm/dc/h/br").getText());
        ArrayList arrayList = new ArrayList();
        for (Element element : document.selectNodes("//dm/dc/h/cl/c")) {
            DrugClass drugClass = new DrugClass();
            drugClass.setClassId(Integer.valueOf(element.attribute("id").getText()).intValue());
            drugClass.setClassName(element.getTextTrim());
            arrayList.add(drugClass);
        }
        drugMonographHeader.setClasses(arrayList);
        return drugMonographHeader;
    }

    private static HashMap<Integer, List<DrugMonographSection>> parseSection(Document document) {
        List<DrugMonographSection> handleGeriatricDosing;
        HashMap<Integer, List<DrugMonographSection>> hashMap = new HashMap<>();
        for (Element element : document.selectNodes("//dm/dc/c")) {
            Integer valueOf = Integer.valueOf(element.attribute("i").getText());
            index = 0;
            List<DrugMonographSection> parseSubSections = parseSubSections(element);
            if (valueOf.intValue() == 0 && (handleGeriatricDosing = handleGeriatricDosing(parseSubSections)) != null && !handleGeriatricDosing.isEmpty()) {
                hashMap.put(2, handleGeriatricDosing);
            }
            if (valueOf.intValue() == 6) {
                try {
                    DrugMonographSection parse = new PregnancyCategoriesParser().parse();
                    if (!(parse == null || parseSubSections == null)) {
                        parseSubSections.add(parse);
                    }
                } catch (Exception unused) {
                }
            }
            hashMap.put(valueOf, parseSubSections);
        }
        return hashMap;
    }

    private static List<DrugMonographSection> handleGeriatricDosing(List<DrugMonographSection> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<DrugMonographSection> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DrugMonographSection next = it.next();
            if (GERIATRIC_DOSING_TITLE.equalsIgnoreCase(next.getTitle())) {
                if (next.getSubsections() != null) {
                    arrayList.addAll(next.getSubsections());
                    list.remove(next);
                } else if (next.getSubsections() == null && next.getListItems2() != null) {
                    DrugMonographSection drugMonographSection = new DrugMonographSection();
                    drugMonographSection.setListItems2(next.getListItems2());
                    arrayList.add(drugMonographSection);
                    list.remove(next);
                }
            }
        }
        return arrayList;
    }

    public static void go1(Element element, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print("   ");
        }
        System.out.print(element.getQualifiedName());
        PrintStream printStream = System.out;
        printStream.println(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + element.getTextTrim());
        Iterator elementIterator = element.elementIterator();
        while (elementIterator.hasNext()) {
            go1((Element) elementIterator.next(), i + 1);
        }
    }

    private static List<DrugMonographSection> parseSubSections(Element element) {
        ArrayList arrayList = new ArrayList();
        Iterator elementIterator = element.elementIterator();
        while (elementIterator.hasNext()) {
            Element element2 = (Element) elementIterator.next();
            if (element2.getQualifiedName().equalsIgnoreCase("i")) {
                DrugMonographSection drugMonographSection = new DrugMonographSection();
                drugMonographSection.setListItems2(new ArrayList());
                if (element2.getParent().getName().equalsIgnoreCase("c")) {
                    DrugMonographSection.subSection subsection = new DrugMonographSection.subSection();
                    subsection.item = element2.getTextTrim();
                    subsection.title = "";
                    drugMonographSection.getListItems2().add(subsection);
                }
                if (drugMonographSection.getListItems2().size() > 0) {
                    arrayList.add(drugMonographSection);
                }
            } else if (!element2.getQualifiedName().equalsIgnoreCase(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY)) {
                return null;
            } else {
                index = 0;
                DrugMonographSection createEmptySection = createEmptySection();
                addAllInnerSubSections(element2, createEmptySection);
                arrayList.add(createEmptySection);
            }
        }
        return arrayList;
    }

    private static DrugMonographSection addAllInnerSubSections(Element element, DrugMonographSection drugMonographSection) {
        try {
            Iterator elementIterator = element.elementIterator();
            if (index != 0) {
                DrugMonographSection createEmptySection = createEmptySection();
                createEmptySection.setTitle(element.attribute("h").getText());
                drugMonographSection.getSubsections().add(createEmptySection);
            } else {
                drugMonographSection.setTitle(element.attribute("h").getText());
            }
            while (elementIterator.hasNext()) {
                Element element2 = (Element) elementIterator.next();
                if (element2.getQualifiedName().equalsIgnoreCase("i")) {
                    DrugMonographSection.subSection subsection = new DrugMonographSection.subSection();
                    String stringValue = element2.getStringValue();
                    if (element2.elements().size() > 0) {
                        for (int i = 0; i < element2.elements().size(); i++) {
                            Element element3 = (Element) element2.elements().get(i);
                            if (element3.getQualifiedName().equals(Constants.APPBOY_PUSH_CONTENT_KEY)) {
                                String stringValue2 = element3.getStringValue();
                                stringValue = stringValue.replace(stringValue2, "<a href=\"" + element3.attribute("href").getValue() + "\">" + stringValue2 + " </a>");
                            }
                        }
                        subsection.item = stringValue;
                    } else {
                        subsection.item = element2.getTextTrim();
                    }
                    subsection.index = index;
                    subsection.title = element.attribute("h").getText();
                    if (!(element2.attribute(com.medscape.android.Constants.OMNITURE_MLINK_CALC) == null || element2.attribute(com.medscape.android.Constants.OMNITURE_MLINK_CALC).getText() == null || element2.attribute(com.medscape.android.Constants.OMNITURE_MLINK_CALC).getText().isEmpty())) {
                        subsection.crossLinkType = CrossLink.Type.CALC;
                        subsection.crossLinkId = element2.attribute(com.medscape.android.Constants.OMNITURE_MLINK_CALC).getText();
                    }
                    DrugMonographSection createEmptySection2 = createEmptySection();
                    createEmptySection2.getListItems2().add(subsection);
                    drugMonographSection.getSubsections().add(createEmptySection2);
                } else if (element2.getQualifiedName().equalsIgnoreCase(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY)) {
                    index++;
                    addAllInnerSubSections(element2, drugMonographSection);
                    index--;
                }
            }
            return drugMonographSection;
        } catch (Throwable th) {
            th.printStackTrace();
            return drugMonographSection;
        }
    }

    private static DrugMonographSection createEmptySection() {
        DrugMonographSection drugMonographSection = new DrugMonographSection();
        drugMonographSection.setListItems2(new ArrayList());
        drugMonographSection.setSubsections(new ArrayList());
        drugMonographSection.setIndex(index);
        return drugMonographSection;
    }
}
