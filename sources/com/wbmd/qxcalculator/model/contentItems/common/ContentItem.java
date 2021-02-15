package com.wbmd.qxcalculator.model.contentItems.common;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Calculator;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection;
import com.wbmd.qxcalculator.model.contentItems.commonfile.CommonFile;
import com.wbmd.qxcalculator.model.contentItems.definition.Definition;
import com.wbmd.qxcalculator.model.contentItems.filesource.FileSource;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook;
import com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.parsedObjects.Promotion;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ContentItem implements Parcelable {
    public static final String CONTENT_ITEM_TYPE_CALCULATOR = "ContentItem.CALCULATOR";
    public static final String CONTENT_ITEM_TYPE_COMMON_FILE = "ContentItem.COMMON_FILE";
    public static final String CONTENT_ITEM_TYPE_DEFINITION = "ContentItem.DEFINITION";
    public static final String CONTENT_ITEM_TYPE_FILE_SOURCE = "ContentItem.FILE_SOURCE";
    public static final String CONTENT_ITEM_TYPE_REFERENCE_BOOK = "ContentItem.REFERENCE_BOOK";
    public static final String CONTENT_ITEM_TYPE_SPLASH_PAGE = "ContentItem.SPLASH_PAGE";
    public static final Parcelable.Creator<ContentItem> CREATOR = new Parcelable.Creator<ContentItem>() {
        public ContentItem createFromParcel(Parcel parcel) {
            ContentItem contentItem = new ContentItem();
            contentItem.identifier = parcel.readString();
            contentItem.name = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.overrideName = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.description = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.footer = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.startDate = (Long) parcel.readValue(Long.class.getClassLoader());
            contentItem.endDate = (Long) parcel.readValue(Long.class.getClassLoader());
            contentItem.trackerId = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.lastModifiedDate = (Long) parcel.readValue(Long.class.getClassLoader());
            contentItem.resourceFileMostRecentLastModifiedDate = (Long) parcel.readValue(Long.class.getClassLoader());
            contentItem.debugType = (Integer) parcel.readValue(Integer.class.getClassLoader());
            contentItem.moreInformation = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.moreInfoSource = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.showAds = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            contentItem.lastUsedDate = (Long) parcel.readValue(Long.class.getClassLoader());
            contentItem.isFavorite = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            contentItem.copiedFromContentItemId = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.leadSpecialty = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.leadConcept = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.allSpecialty = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.parentCategory = (String) parcel.readValue(String.class.getClassLoader());
            contentItem.tags = parcel.createTypedArrayList(Tag.CREATOR);
            contentItem.categories = parcel.createTypedArrayList(Category.CREATOR);
            contentItem.resourceFiles = parcel.createTypedArrayList(ResourceFile.CREATOR);
            contentItem.platforms = parcel.createTypedArrayList(Platform.CREATOR);
            contentItem.filters = parcel.createTypedArrayList(Filter.CREATOR);
            contentItem.featuredContentAds = parcel.createTypedArrayList(FeaturedContentAd.CREATOR);
            contentItem.restrictions = parcel.createTypedArrayList(Restriction.CREATOR);
            contentItem.promotions = parcel.createTypedArrayList(Promotion.CREATOR);
            contentItem.promotionToUse = (Promotion) parcel.readValue(Promotion.class.getClassLoader());
            contentItem.itemFileZip = (ItemFileZip) parcel.readParcelable(ItemFileZip.class.getClassLoader());
            contentItem.moreInfoSections = parcel.createTypedArrayList(MoreInfoSection.CREATOR);
            contentItem.calculator = (Calculator) parcel.readParcelable(Calculator.class.getClassLoader());
            contentItem.definition = (Definition) parcel.readParcelable(Definition.class.getClassLoader());
            contentItem.fileSource = (FileSource) parcel.readParcelable(FileSource.class.getClassLoader());
            contentItem.referenceBook = (ReferenceBook) parcel.readParcelable(ReferenceBook.class.getClassLoader());
            contentItem.commonFile = (CommonFile) parcel.readParcelable(CommonFile.class.getClassLoader());
            contentItem.splashPage = (SplashPage) parcel.readParcelable(SplashPage.class.getClassLoader());
            return contentItem;
        }

        public ContentItem[] newArray(int i) {
            return new ContentItem[i];
        }
    };
    public String allSpecialty;
    public Calculator calculator;
    public List<Category> categories;
    public CommonFile commonFile;
    public String copiedFromContentItemId;
    public Integer debugType;
    public Definition definition;
    public String description;
    public Long endDate;
    public List<FeaturedContentAd> featuredContentAds;
    public FileSource fileSource;
    public List<Filter> filters;
    public String footer;
    public String identifier;
    public Boolean isFavorite;
    public ItemFileZip itemFileZip;
    public Long lastModifiedDate;
    public Long lastUsedDate;
    public String leadConcept;
    public String leadSpecialty;
    public List<MoreInfoSection> moreInfoSections;
    public String moreInfoSource;
    public String moreInformation;
    public String name;
    public String overrideName;
    public String parentCategory;
    public List<Platform> platforms;
    public Promotion promotionToUse;
    public List<Promotion> promotions;
    public ReferenceBook referenceBook;
    public Long resourceFileMostRecentLastModifiedDate;
    public List<ResourceFile> resourceFiles;
    public List<Restriction> restrictions;
    public Boolean showAds;
    public SplashPage splashPage;
    public Long startDate;
    public List<Tag> tags;
    public String trackerId;
    public String type;

    public enum ContentItemListType {
        DEFAULT,
        MENU,
        MENU_TERMS_OF_USE,
        MENU_PRIVACY_POLICY,
        HIDDEN,
        SPLASH_PAGE
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ContentItem> contentItems(List<DBContentItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ContentItem> arrayList = new ArrayList<>(list.size());
        for (DBContentItem contentItem : list) {
            arrayList.add(new ContentItem(contentItem));
        }
        return arrayList;
    }

    public ContentItem(DBContentItem dBContentItem) {
        if (dBContentItem != null) {
            this.type = dBContentItem.getType();
            this.identifier = dBContentItem.getIdentifier();
            this.name = dBContentItem.getName();
            this.overrideName = dBContentItem.overrideName;
            this.description = dBContentItem.getDescription();
            this.footer = dBContentItem.getFooter();
            this.startDate = dBContentItem.getStartDate();
            this.endDate = dBContentItem.getEndDate();
            this.trackerId = dBContentItem.getTrackerId();
            this.lastModifiedDate = dBContentItem.getLastModifiedDate();
            this.debugType = dBContentItem.getDebugType();
            this.moreInformation = dBContentItem.getMoreInformation();
            this.moreInfoSource = dBContentItem.getMoreInformationSource();
            this.showAds = dBContentItem.getShowAds();
            this.lastUsedDate = dBContentItem.getLastUsedDate();
            this.isFavorite = dBContentItem.getIsFavorite();
            this.copiedFromContentItemId = dBContentItem.getCopiedFromContentItemId();
            this.leadSpecialty = dBContentItem.getLeadSpecialty();
            this.leadConcept = dBContentItem.getLeadConcept();
            this.allSpecialty = dBContentItem.getAllSpecialty();
            this.parentCategory = dBContentItem.getParentCalcName();
            this.tags = Tag.tags(dBContentItem.getTags());
            this.categories = Category.categories(dBContentItem.getCategories());
            this.platforms = Platform.platforms(dBContentItem.getPlatforms());
            this.filters = Filter.filters(dBContentItem.getFilters());
            this.featuredContentAds = FeaturedContentAd.featuredContentAds(dBContentItem.getFeaturedContentAds());
            this.restrictions = Restriction.restrictions(dBContentItem.getRestrictions());
            this.resourceFiles = ResourceFile.resourceFiles(dBContentItem.getResourceFiles());
            this.itemFileZip = new ItemFileZip(dBContentItem.getItemFileZip());
            this.promotions = Promotion.promotions(dBContentItem.getPromotions());
            if (dBContentItem.promotionToUse != null) {
                this.promotionToUse = new Promotion(dBContentItem.promotionToUse);
            }
            this.moreInfoSections = MoreInfoSection.moreInfoSections(dBContentItem.getMoreInfoSections());
            if (dBContentItem.getCalculator() != null) {
                this.calculator = new Calculator(dBContentItem.getCalculator());
            } else if (dBContentItem.getDefinition() != null) {
                this.definition = new Definition(dBContentItem.getDefinition());
            } else if (dBContentItem.getFileSource() != null) {
                this.fileSource = new FileSource(dBContentItem.getFileSource());
            } else if (dBContentItem.getReferenceBook() != null) {
                this.referenceBook = new ReferenceBook(dBContentItem.getReferenceBook());
            } else if (dBContentItem.getCommonFile() != null) {
                this.commonFile = new CommonFile(dBContentItem.getCommonFile());
            } else if (dBContentItem.getSplashPage() != null) {
                this.splashPage = new SplashPage(dBContentItem.getSplashPage());
            }
        }
    }

    public ContentItem() {
        this((DBContentItem) null);
    }

    public void initializeContentItem(DBUser dBUser, Context context) {
        if (this.type.equals(CONTENT_ITEM_TYPE_CALCULATOR)) {
            this.calculator.initializeCalculator(dBUser, context);
        } else if (this.type.equals(CONTENT_ITEM_TYPE_REFERENCE_BOOK)) {
            this.referenceBook.initializeReferenceBook(dBUser);
        }
        List<MoreInfoSection> list = this.moreInfoSections;
        if (list != null) {
            Collections.sort(list, new Comparator<MoreInfoSection>() {
                public int compare(MoreInfoSection moreInfoSection, MoreInfoSection moreInfoSection2) {
                    return moreInfoSection.position.compareTo(moreInfoSection2.position);
                }
            });
            for (MoreInfoSection initializeMoreInfoSection : this.moreInfoSections) {
                initializeMoreInfoSection.initializeMoreInfoSection(dBUser);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.name);
        parcel.writeValue(this.overrideName);
        parcel.writeValue(this.description);
        parcel.writeValue(this.footer);
        parcel.writeValue(this.startDate);
        parcel.writeValue(this.endDate);
        parcel.writeValue(this.trackerId);
        parcel.writeValue(this.lastModifiedDate);
        parcel.writeValue(this.resourceFileMostRecentLastModifiedDate);
        parcel.writeValue(this.debugType);
        parcel.writeValue(this.moreInformation);
        parcel.writeValue(this.moreInfoSource);
        parcel.writeValue(this.showAds);
        parcel.writeValue(this.lastUsedDate);
        parcel.writeValue(this.isFavorite);
        parcel.writeValue(this.copiedFromContentItemId);
        parcel.writeValue(this.leadSpecialty);
        parcel.writeValue(this.leadConcept);
        parcel.writeValue(this.allSpecialty);
        parcel.writeValue(this.parentCategory);
        parcel.writeTypedList(this.tags);
        parcel.writeTypedList(this.categories);
        parcel.writeTypedList(this.resourceFiles);
        parcel.writeTypedList(this.platforms);
        parcel.writeTypedList(this.filters);
        parcel.writeTypedList(this.featuredContentAds);
        parcel.writeTypedList(this.restrictions);
        parcel.writeTypedList(this.promotions);
        parcel.writeValue(this.promotionToUse);
        parcel.writeParcelable(this.itemFileZip, i);
        parcel.writeTypedList(this.moreInfoSections);
        parcel.writeParcelable(this.calculator, i);
        parcel.writeParcelable(this.definition, i);
        parcel.writeParcelable(this.fileSource, i);
        parcel.writeParcelable(this.referenceBook, i);
        parcel.writeParcelable(this.commonFile, i);
        parcel.writeParcelable(this.splashPage, i);
    }

    public static List<ContentItem> convertJsonToContentItems(JsonReader jsonReader, String str) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(200);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToContentItem(jsonReader, str));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ContentItem convertJsonToContentItem(JsonReader jsonReader, String str) throws IOException, ParseException {
        ContentItem contentItem = new ContentItem();
        contentItem.type = str;
        if (str.equals(CONTENT_ITEM_TYPE_CALCULATOR)) {
            contentItem.calculator = new Calculator();
        } else if (str.equals(CONTENT_ITEM_TYPE_DEFINITION)) {
            contentItem.definition = new Definition();
        } else if (str.equals(CONTENT_ITEM_TYPE_FILE_SOURCE)) {
            contentItem.fileSource = new FileSource();
        } else if (str.equals(CONTENT_ITEM_TYPE_REFERENCE_BOOK)) {
            contentItem.referenceBook = new ReferenceBook();
        } else if (str.equals(CONTENT_ITEM_TYPE_COMMON_FILE)) {
            contentItem.commonFile = new CommonFile();
        } else if (str.equals(CONTENT_ITEM_TYPE_SPLASH_PAGE)) {
            contentItem.splashPage = new SplashPage();
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("friendly_id")) {
                contentItem.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                contentItem.name = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("description")) {
                contentItem.description = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                contentItem.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(FirebaseAnalytics.Param.START_DATE)) {
                contentItem.startDate = APIParser.readUnixTimestampAndConvertToMs(jsonReader);
            } else if (nextName.equalsIgnoreCase(FirebaseAnalytics.Param.END_DATE)) {
                contentItem.endDate = APIParser.readUnixTimestampAndConvertToMs(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_id")) {
                contentItem.trackerId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("last_modified")) {
                Long readUnixTimestampAndConvertToMs = APIParser.readUnixTimestampAndConvertToMs(jsonReader);
                contentItem.lastModifiedDate = readUnixTimestampAndConvertToMs;
                if (readUnixTimestampAndConvertToMs == null) {
                    contentItem.lastModifiedDate = 0L;
                }
            } else if (nextName.equalsIgnoreCase("resource_file_most_recently_modified_at")) {
                Long readUnixTimestampAndConvertToMs2 = APIParser.readUnixTimestampAndConvertToMs(jsonReader);
                contentItem.resourceFileMostRecentLastModifiedDate = readUnixTimestampAndConvertToMs2;
                if (readUnixTimestampAndConvertToMs2 == null) {
                    contentItem.resourceFileMostRecentLastModifiedDate = 0L;
                }
            } else if (nextName.equalsIgnoreCase("debug_type")) {
                contentItem.debugType = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_information")) {
                contentItem.moreInformation = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_information_src")) {
                contentItem.moreInfoSource = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("show_ads")) {
                contentItem.showAds = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("copied_from_content_item_id")) {
                contentItem.copiedFromContentItemId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(ContentParser.TAGS)) {
                contentItem.tags = Tag.convertJsonToTags(jsonReader);
            } else if (nextName.equalsIgnoreCase("categories")) {
                contentItem.categories = Category.convertJsonToCategories(jsonReader);
            } else if (nextName.equalsIgnoreCase("platforms")) {
                contentItem.platforms = Platform.convertJsonToPlatforms(jsonReader);
            } else if (nextName.equalsIgnoreCase(ShareConstants.WEB_DIALOG_PARAM_FILTERS)) {
                contentItem.filters = Filter.convertJsonToFilters(jsonReader);
            } else if (nextName.equalsIgnoreCase("featured_content_ads")) {
                contentItem.featuredContentAds = FeaturedContentAd.convertJsonToFeaturedContentAds(jsonReader);
            } else if (nextName.equalsIgnoreCase("restrictions")) {
                contentItem.restrictions = Restriction.convertJsonToFeaturedRestrictions(jsonReader);
            } else if (nextName.equalsIgnoreCase("promotions")) {
                contentItem.promotions = Promotion.convertJsonToPromotions(jsonReader);
            } else if (nextName.equalsIgnoreCase("files")) {
                contentItem.resourceFiles = ResourceFile.convertJsonToResourceFiles(jsonReader);
            } else if (nextName.equalsIgnoreCase("item_files_zip")) {
                contentItem.itemFileZip = ItemFileZip.convertJsonToItemFileZip(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_info_sections")) {
                contentItem.moreInfoSections = MoreInfoSection.convertJsonToMoreInfoSections(jsonReader);
            } else if (nextName.equalsIgnoreCase("medscape")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    contentItem.leadSpecialty = null;
                    contentItem.leadConcept = null;
                    contentItem.allSpecialty = null;
                    jsonReader.nextNull();
                } else {
                    ContentTags convertJsonToContentTag = ContentTags.convertJsonToContentTag(jsonReader);
                    if (convertJsonToContentTag != null) {
                        contentItem.leadSpecialty = convertJsonToContentTag.leadSpecialty;
                        contentItem.leadConcept = convertJsonToContentTag.leadConcept;
                        contentItem.allSpecialty = convertJsonToContentTag.allSpecialty;
                    }
                }
            } else if ((!str.equals(CONTENT_ITEM_TYPE_CALCULATOR) || !contentItem.calculator.readJsonTag(jsonReader, nextName)) && ((!str.equals(CONTENT_ITEM_TYPE_DEFINITION) || !contentItem.definition.readJsonTag(jsonReader, nextName)) && ((!str.equals(CONTENT_ITEM_TYPE_FILE_SOURCE) || !contentItem.fileSource.readJsonTag(jsonReader, nextName)) && ((!str.equals(CONTENT_ITEM_TYPE_REFERENCE_BOOK) || !contentItem.referenceBook.readJsonTag(jsonReader, nextName)) && (!str.equals(CONTENT_ITEM_TYPE_SPLASH_PAGE) || !contentItem.splashPage.readJsonTag(jsonReader, nextName)))))) {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (str.equals(CONTENT_ITEM_TYPE_CALCULATOR)) {
            contentItem.calculator.identifier = contentItem.identifier;
        } else if (str.equals(CONTENT_ITEM_TYPE_DEFINITION)) {
            contentItem.definition.identifier = contentItem.identifier;
        } else if (str.equals(CONTENT_ITEM_TYPE_FILE_SOURCE)) {
            contentItem.fileSource.identifier = contentItem.identifier;
        } else if (str.equals(CONTENT_ITEM_TYPE_REFERENCE_BOOK)) {
            contentItem.referenceBook.identifier = contentItem.identifier;
        } else if (str.equals(CONTENT_ITEM_TYPE_COMMON_FILE)) {
            contentItem.commonFile.identifier = contentItem.identifier;
        } else if (str.equals(CONTENT_ITEM_TYPE_SPLASH_PAGE)) {
            contentItem.splashPage.identifier = contentItem.identifier;
        }
        List<ResourceFile> list = contentItem.resourceFiles;
        if (list != null && !list.isEmpty()) {
            for (ResourceFile resourceFile : contentItem.resourceFiles) {
                resourceFile.contentItemIdentifier = contentItem.identifier;
            }
        }
        setCategoryIdentifiers(contentItem.identifier, 0, contentItem.categories);
        return contentItem;
    }

    private static void setCategoryIdentifiers(String str, int i, List<Category> list) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            for (Category next : list) {
                next.contentSpecificIdentifier = i + "_i" + i2 + "_" + str + "_" + next.identifier;
                setCategoryIdentifiers(str, i + 1, next.subCategories);
                i2++;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContentItem)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((ContentItem) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public ContentItemListType getListType() {
        List<Category> list = this.categories;
        if (list == null || list.isEmpty()) {
            if (this.splashPage != null) {
                return ContentItemListType.SPLASH_PAGE;
            }
            return ContentItemListType.HIDDEN;
        } else if (this.identifier.equalsIgnoreCase(DataManager.K_PRIVACY_POLICY_IDENTIFIER)) {
            return ContentItemListType.MENU_PRIVACY_POLICY;
        } else {
            if (this.identifier.equalsIgnoreCase(DataManager.K_TERMS_OF_USE_IDENTIFIER)) {
                return ContentItemListType.MENU_TERMS_OF_USE;
            }
            boolean z = false;
            Iterator<Category> it = this.categories.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().isMenuItem()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                return ContentItemListType.MENU;
            }
            return ContentItemListType.DEFAULT;
        }
    }

    public boolean isMenuContentItem() {
        List<Category> list = this.categories;
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (Category isMenuItem : this.categories) {
            if (isMenuItem.isMenuItem()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "ContentItem [contentSpecificIdentifier=" + this.identifier + ", name=" + this.name + ", description=" + this.description + ", footer=" + this.footer + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", trackerId=" + this.trackerId + ", lastModifiedDate=" + this.lastModifiedDate + ", debugType=" + this.debugType + ", moreInformation=" + this.moreInformation + ", moreInfoSource=" + this.moreInfoSource + ", tags=" + this.tags + ", categories=" + this.categories + "]";
    }
}
