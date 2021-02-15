package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class ConsultFeed implements Parcelable {
    public static final Parcelable.Creator<ConsultFeed> CREATOR = new Parcelable.Creator<ConsultFeed>() {
        public ConsultFeed createFromParcel(Parcel parcel) {
            return new ConsultFeed(parcel);
        }

        public ConsultFeed[] newArray(int i) {
            return new ConsultFeed[i];
        }
    };
    private int mContentCount;
    private int mCurrentPageIndex;
    private int mCurrentPageIndexForDateRange;
    private List<ConsultFeedItem> mItems = new ArrayList();
    private int mPromoCount;
    private int mTotalItems;
    private int mTotalItemsForDateRange;
    private List<String> mUniqueIds = new ArrayList();

    private int addItemsToFront() {
        return -1;
    }

    private boolean removeConsultFeedItemAtPosition(int i) {
        return false;
    }

    private int removePromoItems() {
        return -1;
    }

    private void replaceConsultFeedItemAtPosition(int i, ConsultFeed consultFeed) {
    }

    public int describeContents() {
        return 0;
    }

    public int addItemsToEnd(List<ConsultFeedItem> list) {
        List<ConsultFeedItem> list2 = this.mItems;
        int i = 0;
        for (ConsultFeedItem next : list) {
            String uniqueIdentifier = next.getUniqueIdentifier();
            if (!StringUtil.isNotEmpty(uniqueIdentifier) || !this.mUniqueIds.contains(uniqueIdentifier)) {
                list2.add(next);
                if (StringUtil.isNotEmpty(uniqueIdentifier)) {
                    this.mUniqueIds.add(uniqueIdentifier);
                }
                if (next.isPromo()) {
                    this.mPromoCount++;
                } else {
                    this.mContentCount++;
                }
                i++;
            }
        }
        return i;
    }

    public boolean addConsultFeedItemToPosition(ConsultFeedItem consultFeedItem, int i) {
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        }
        this.mItems.add(i, consultFeedItem);
        this.mTotalItems++;
        return false;
    }

    public void setTotalItems(int i) {
        this.mTotalItems = i;
    }

    public int getTotalItems() {
        return this.mTotalItems;
    }

    public void setCurrentPageIndex(int i) {
        this.mCurrentPageIndex = i;
    }

    public int getCurrentPageIndex() {
        return this.mCurrentPageIndex;
    }

    public List<ConsultFeedItem> getFeedItems() {
        return this.mItems;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mItems);
        parcel.writeInt(this.mPromoCount);
        parcel.writeInt(this.mContentCount);
        parcel.writeInt(this.mTotalItems);
        parcel.writeInt(this.mCurrentPageIndex);
        parcel.writeStringList(this.mUniqueIds);
        parcel.writeInt(this.mTotalItemsForDateRange);
        parcel.writeInt(this.mCurrentPageIndexForDateRange);
    }

    public ConsultFeed() {
    }

    protected ConsultFeed(Parcel parcel) {
        this.mItems = parcel.createTypedArrayList(ConsultFeedItem.CREATOR);
        this.mPromoCount = parcel.readInt();
        this.mContentCount = parcel.readInt();
        this.mTotalItems = parcel.readInt();
        this.mCurrentPageIndex = parcel.readInt();
        this.mUniqueIds = parcel.createStringArrayList();
        this.mTotalItemsForDateRange = parcel.readInt();
        this.mCurrentPageIndexForDateRange = parcel.readInt();
    }
}
