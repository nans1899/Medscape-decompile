package com.medscape.android.drugs.parser;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.drugs.model.DrugMonographSection;
import com.medscape.android.helper.FileHelper;
import java.io.File;
import java.util.ArrayList;

public class PregnancyCategoriesParser {
    public DrugMonographSection parse() throws Exception {
        DrugMonographSection drugMonographSection = new DrugMonographSection();
        NSDictionary nSDictionary = (NSDictionary) PropertyListParser.parse(new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/PregnancyCategories.plist"));
        NSObject nSObject = nSDictionary.get((Object) "sectionTitle");
        NSObject[] array = ((NSArray) nSDictionary.objectForKey("categories")).getArray();
        ArrayList arrayList = new ArrayList();
        for (NSObject obj : array) {
            DrugMonographSection.subSection subsection = new DrugMonographSection.subSection();
            subsection.item = obj.toString();
            arrayList.add(subsection);
        }
        drugMonographSection.setListItems2(arrayList);
        drugMonographSection.setTitle(nSObject.toString());
        return drugMonographSection;
    }
}
