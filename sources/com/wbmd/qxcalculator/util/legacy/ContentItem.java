package com.wbmd.qxcalculator.util.legacy;

import java.util.ArrayList;
import java.util.List;

public class ContentItem extends ContentNode implements Comparable<ContentItem> {
    private String mCampaign;
    private List<String> mParentGroups;
    private List<String> mRegions;
    private String mResource;
    private String mSponsor;
    private String mSubtitle;
    private List<String> mTags;
    private String mTitle;
    private String mTracker;
    private ItemType mType = ItemType.NONE;

    public enum ItemType {
        NONE,
        CALCULATOR,
        GUIDE,
        PDF,
        URL_INTERNAL,
        URL_EXTERNAL,
        CUSTOM
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public ItemType getType() {
        return this.mType;
    }

    public void setType(ItemType itemType) {
        this.mType = itemType;
    }

    public String getResource() {
        return this.mResource;
    }

    public void setResource(String str) {
        this.mResource = str;
    }

    public String getSponsor() {
        return this.mSponsor;
    }

    public void setSponsor(String str) {
        this.mSponsor = str;
    }

    public String getCampaign() {
        return this.mCampaign;
    }

    public void setCampaign(String str) {
        this.mCampaign = str;
    }

    public String getTracker() {
        return this.mTracker;
    }

    public void setTracker(String str) {
        this.mTracker = str;
    }

    public List<String> getTagList() {
        return this.mTags;
    }

    public String getTags() {
        List<String> list = this.mTags;
        if (list == null) {
            return null;
        }
        String str = "";
        for (String str2 : list) {
            str = str + str2 + ", ";
        }
        return str.substring(0, str.length() - 2);
    }

    public void setTags(String str) {
        if (str == null) {
            this.mTags = null;
            return;
        }
        this.mTags = new ArrayList();
        for (String trim : str.split(",")) {
            this.mTags.add(trim.trim());
        }
    }

    public List<String> getParentGroupList() {
        return this.mParentGroups;
    }

    public String getParentGroups() {
        if (this.mParentGroups == null) {
            return null;
        }
        String str = new String();
        for (String str2 : this.mParentGroups) {
            str = str + str2 + ", ";
        }
        return str.substring(0, str.length() - 2);
    }

    public void setParentGroups(String str) {
        if (str == null) {
            this.mParentGroups = null;
            return;
        }
        this.mParentGroups = new ArrayList();
        for (String trim : str.split(",")) {
            this.mParentGroups.add(trim.trim());
        }
    }

    public String getRandomParentGroup() {
        if (this.mParentGroups == null) {
            return null;
        }
        return this.mParentGroups.get(((int) (Math.random() * 1000000.0d)) % this.mParentGroups.size());
    }

    public List<String> getRegions() {
        return this.mRegions;
    }

    public void addRegion(String str) {
        if (this.mRegions == null) {
            this.mRegions = new ArrayList();
        }
        this.mRegions.add(str);
    }

    public boolean isValidRegion(String str) {
        List<String> list;
        if (str == null || (list = this.mRegions) == null) {
            return true;
        }
        for (String equals : list) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(ContentItem contentItem) {
        if (this.mTitle == null && contentItem.mTitle == null) {
            return 0;
        }
        String str = this.mTitle;
        if (str == null) {
            return -1;
        }
        String str2 = contentItem.mTitle;
        if (str2 == null) {
            return 1;
        }
        return str.compareToIgnoreCase(str2);
    }

    public int hashCode() {
        String str = this.mResource;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mSubtitle;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mTitle;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        ItemType itemType = this.mType;
        int hashCode4 = (hashCode3 + (itemType == null ? 0 : itemType.hashCode())) * 31;
        String str4 = this.mSponsor;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.mCampaign;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.mTracker;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<String> list = this.mTags;
        int hashCode8 = (hashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.mParentGroups;
        if (list2 != null) {
            i = list2.hashCode();
        }
        return hashCode8 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContentItem contentItem = (ContentItem) obj;
        String str = this.mResource;
        if (str == null) {
            if (contentItem.mResource != null) {
                return false;
            }
        } else if (!str.equals(contentItem.mResource)) {
            return false;
        }
        String str2 = this.mSubtitle;
        if (str2 == null) {
            if (contentItem.mSubtitle != null) {
                return false;
            }
        } else if (!str2.equals(contentItem.mSubtitle)) {
            return false;
        }
        String str3 = this.mTitle;
        if (str3 == null) {
            if (contentItem.mTitle != null) {
                return false;
            }
        } else if (!str3.equals(contentItem.mTitle)) {
            return false;
        }
        if (this.mType != contentItem.mType) {
            return false;
        }
        List<String> list = this.mTags;
        if (list == null) {
            if (contentItem.mTags != null) {
                return false;
            }
        } else if (!list.equals(contentItem.mTags)) {
            return false;
        }
        List<String> list2 = this.mParentGroups;
        if (list2 == null) {
            if (contentItem.mParentGroups != null) {
                return false;
            }
        } else if (!list2.equals(contentItem.mParentGroups)) {
            return false;
        }
        String str4 = this.mSponsor;
        if (str4 == null) {
            if (contentItem.mSponsor != null) {
                return false;
            }
        } else if (!str4.equals(contentItem.mSponsor)) {
            return false;
        }
        String str5 = this.mCampaign;
        if (str5 == null) {
            if (contentItem.mCampaign != null) {
                return false;
            }
        } else if (!str5.equals(contentItem.mCampaign)) {
            return false;
        }
        String str6 = this.mTracker;
        if (str6 == null) {
            if (contentItem.mTracker != null) {
                return false;
            }
        } else if (!str6.equals(contentItem.mTracker)) {
            return false;
        }
        return true;
    }
}
