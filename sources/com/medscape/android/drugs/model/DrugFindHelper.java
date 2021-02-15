package com.medscape.android.drugs.model;

import android.content.Context;
import android.text.style.BackgroundColorSpan;
import androidx.core.content.ContextCompat;
import com.medscape.android.R;
import com.medscape.android.contentviewer.model.ReferenceFindPosition;
import com.medscape.android.reference.model.Para;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashSet;

public class DrugFindHelper {
    private Context mContext;
    private ReferenceFindPosition mCurrentFindItem;
    private int mCurrentFindPos;
    public ArrayList<ReferenceFindPosition> mFindPositions;
    public String mFindQuery = "";
    private HashSet<Integer> mFindRowsInsideDetail;

    public interface DrugFindCancelListener {
        void cancelFind();
    }

    public interface DrugFindMenuClickListener {
        void onFindMenuItemClicked();
    }

    public DrugFindHelper(Context context) {
        this.mContext = context;
        resetFind();
    }

    public void resetFind() {
        ArrayList<ReferenceFindPosition> arrayList = new ArrayList<>();
        this.mFindPositions = arrayList;
        arrayList.clear();
        HashSet<Integer> hashSet = new HashSet<>();
        this.mFindRowsInsideDetail = hashSet;
        hashSet.clear();
        this.mCurrentFindPos = 0;
        this.mFindQuery = "";
        this.mCurrentFindItem = null;
    }

    public CharSequence addFindPositionMap(CharSequence charSequence, int i, int i2) {
        return addFindPositionMap(charSequence, i, i2, false);
    }

    public CharSequence addFindPositionMap(CharSequence charSequence, int i, int i2, boolean z) {
        int indexOf;
        if (charSequence == null || StringUtil.isNullOrEmpty(charSequence.toString()) || (indexOf = charSequence.toString().toLowerCase().indexOf(this.mFindQuery.toLowerCase(), i2)) < 0) {
            return charSequence;
        }
        Para para = new Para();
        para.append(charSequence);
        Para applyTint = applyTint(para, indexOf);
        ReferenceFindPosition referenceFindPosition = new ReferenceFindPosition();
        referenceFindPosition.contentRow = i;
        referenceFindPosition.contentIndex = indexOf;
        referenceFindPosition.isDetailText = z;
        if (z) {
            this.mFindRowsInsideDetail.add(Integer.valueOf(i));
        }
        addFindPosition(referenceFindPosition);
        return addFindPositionMap(applyTint, i, indexOf + 1, z);
    }

    private void addFindPosition(ReferenceFindPosition referenceFindPosition) {
        ArrayList<ReferenceFindPosition> arrayList = this.mFindPositions;
        if (arrayList != null) {
            if (arrayList.size() == 0) {
                this.mCurrentFindItem = referenceFindPosition;
            }
            this.mFindPositions.add(referenceFindPosition);
        }
    }

    private Para applyTint(Para para, int i) {
        ArrayList<ReferenceFindPosition> arrayList = this.mFindPositions;
        if (!(arrayList == null || this.mContext == null)) {
            para.setSpan(new BackgroundColorSpan(ContextCompat.getColor(this.mContext, arrayList.size() == 0 ? R.color.orange_tint : R.color.yellow_tint)), i, this.mFindQuery.length() + i, 512);
        }
        return para;
    }

    public boolean isInFindMode() {
        return StringUtil.isNotEmpty(this.mFindQuery);
    }

    public boolean canScrolltoFind() {
        ReferenceFindPosition referenceFindPosition = this.mCurrentFindItem;
        return referenceFindPosition != null && referenceFindPosition.contentRow > -1;
    }

    public ReferenceFindPosition getCurrentFindItem() {
        return this.mCurrentFindItem;
    }

    public void setCurrentFindItem(ReferenceFindPosition referenceFindPosition) {
        this.mCurrentFindItem = referenceFindPosition;
    }

    public String getFindQuery() {
        return this.mFindQuery;
    }

    public void setFindQuery(String str) {
        this.mFindQuery = str;
    }

    public ArrayList<ReferenceFindPosition> getFindPositions() {
        return this.mFindPositions;
    }

    public void setFindPositions(ArrayList<ReferenceFindPosition> arrayList) {
        this.mFindPositions = arrayList;
    }

    public int getCurrentFindPos() {
        return this.mCurrentFindPos;
    }

    public void setCurrentFindPos(int i) {
        this.mCurrentFindPos = i;
    }

    public HashSet<Integer> getFindRowsInsideDetail() {
        return this.mFindRowsInsideDetail;
    }

    public void setFindRowsInsideDetail(HashSet<Integer> hashSet) {
        this.mFindRowsInsideDetail = hashSet;
    }
}
