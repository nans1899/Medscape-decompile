package com.medscape.android.reference.model;

import com.medscape.android.reference.exception.ContentNotFoundException;
import com.medscape.android.util.NumberUtil;
import com.medscape.android.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ClinicalReferenceContent implements Serializable {
    public HashMap<String, String> adSupportMap;
    public ArrayList<Contributor> contributors;
    private HashMap<String, String> gpsSectionIdMedsSectionIdMap = new HashMap<>();
    private boolean hasImages;
    public String lastUpdate;
    public LinkedHashMap<String, Figure> mediaGalleryMap;
    public LinkedHashMap<String, ReferenceItem> references;
    private HashMap<String, Integer> sectionIdIndexMap = new HashMap<>();
    public ArrayList<Sect1> sections;
    public LinkedHashMap<String, Table> tablesMap;
    public String title;

    public String toString() {
        return "ClinicalReferenceArticle" + "\nContent:" + "\ntitle:" + this.title + "\nsections:" + this.sections + "\nmedia:" + this.mediaGalleryMap + "\nreferences:" + this.references + "\ncontributors:" + this.contributors + "\ntables:" + this.tablesMap;
    }

    public Para getPara(int i, int i2, int i3) throws ContentNotFoundException {
        ArrayList<Sect1> arrayList = this.sections;
        if (arrayList != null && arrayList.get(i) != null && this.sections.get(i).subsections != null && this.sections.get(i).subsections.get(i2) != null && this.sections.get(i).subsections.get(i2).paras != null && this.sections.get(i).subsections.get(i2).paras.get(i3) != null) {
            return this.sections.get(i).subsections.get(i2).paras.get(i3);
        }
        throw new ContentNotFoundException("section:" + i + ", subsection:" + i2 + ", para:" + i3 + " not found");
    }

    public Sect2 getSubsection(int i, int i2) throws ContentNotFoundException {
        ArrayList<Sect1> arrayList = this.sections;
        if (arrayList != null && arrayList.get(i) != null && this.sections.get(i).subsections != null && this.sections.get(i).subsections.get(i2) != null) {
            return this.sections.get(i).subsections.get(i2);
        }
        throw new ContentNotFoundException("section:" + i + ", subsection:" + i2 + " not found");
    }

    public Sect1 getSection(int i) throws ContentNotFoundException {
        ArrayList<Sect1> arrayList = this.sections;
        if (arrayList != null && arrayList.get(i) != null) {
            return this.sections.get(i);
        }
        throw new ContentNotFoundException("section:" + i + " not found");
    }

    public String getSectionTitle(int i) throws ContentNotFoundException {
        return StringUtil.optString(getSection(i).title);
    }

    public String getSubsectionTitle(int i, int i2) throws ContentNotFoundException {
        String str = getSubsection(i, i2).title;
        if (StringUtil.isNotEmpty(str)) {
            return str;
        }
        return StringUtil.optString(getSection(i).title);
    }

    public ClinicalReferenceContent addSection(Sect1 sect1) {
        if (this.sections == null) {
            this.sections = new ArrayList<>();
        }
        if (!"media".equals(sect1.type)) {
            this.sections.add(sect1);
            this.sectionIdIndexMap.put(sect1.id, Integer.valueOf(this.sections.size() - 1));
        }
        return this;
    }

    public int getSectionIndexById(String str) {
        int i;
        String str2;
        if (this.sections == null || str == null) {
            i = 0;
        } else {
            i = NumberUtil.intValue(this.sectionIdIndexMap.get(str));
            if (i < 0 && (str2 = this.gpsSectionIdMedsSectionIdMap.get(str)) != null) {
                i = NumberUtil.intValue(this.sectionIdIndexMap.get(str2));
            }
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public void addTable(Table table) {
        if (this.tablesMap == null) {
            this.tablesMap = new LinkedHashMap<>();
        }
        this.tablesMap.put(table.id, table);
    }

    public boolean hasTables() {
        LinkedHashMap<String, Table> linkedHashMap = this.tablesMap;
        return linkedHashMap != null && !linkedHashMap.isEmpty();
    }

    public void addFigure(Figure figure) {
        if (this.mediaGalleryMap == null) {
            this.mediaGalleryMap = new LinkedHashMap<>();
        }
        this.mediaGalleryMap.put(figure.id, figure);
        if (figure.isImage()) {
            this.hasImages = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasMedia(boolean r3) {
        /*
            r2 = this;
            r0 = 1
            r1 = 0
            if (r3 == 0) goto L_0x0013
            java.util.LinkedHashMap<java.lang.String, com.medscape.android.reference.model.Figure> r3 = r2.mediaGalleryMap
            if (r3 == 0) goto L_0x001e
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x001e
            boolean r3 = r2.hasImages
            if (r3 == 0) goto L_0x001e
            goto L_0x001f
        L_0x0013:
            java.util.LinkedHashMap<java.lang.String, com.medscape.android.reference.model.Figure> r3 = r2.mediaGalleryMap
            if (r3 == 0) goto L_0x001e
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.model.ClinicalReferenceContent.hasMedia(boolean):boolean");
    }

    public HashMap<String, Integer> getSectionIdIndexMap() {
        return this.sectionIdIndexMap;
    }
}
