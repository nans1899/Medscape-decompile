package com.medscape.android.activity.interactions.repositories;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.Constants;
import com.medscape.android.activity.interactions.models.DrugList;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.db.model.Drug;
import com.medscape.android.provider.SharedPreferenceProvider;
import java.util.List;

public class InteractionListRepository {
    private Long defaultListId;
    private Long lastViewedListId;
    private CacheManager mCacheManager;
    private Context mContext;
    private String userGuid;

    public InteractionListRepository(Context context) {
        this.mCacheManager = new CacheManager(context);
        this.mContext = context;
        String maskedGuid = AuthenticationManager.getInstance(context).getMaskedGuid();
        this.userGuid = maskedGuid;
        this.defaultListId = Long.valueOf(getDefaultListId(maskedGuid));
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        this.lastViewedListId = Long.valueOf(sharedPreferenceProvider.get(Constants.PREF_LAST_LIST_ID + this.userGuid, this.defaultListId.longValue()));
        if (this.mCacheManager.isUpgraded()) {
            Log.i("_COPY", "Copy process started");
            CacheManager cacheManager = this.mCacheManager;
            cacheManager.updateOldDrugs(cacheManager.getWritableDatabase());
            copyOldDrugs();
            this.mCacheManager.setIsUpgraded(false);
        }
    }

    public InteractionListRepository() {
    }

    public long getDefaultListId(String str) {
        long defaultListId2 = this.mCacheManager.getDefaultListId(str);
        if (defaultListId2 != -1) {
            return defaultListId2;
        }
        this.mCacheManager.addDefaultListRow();
        return this.mCacheManager.getDefaultListId(str);
    }

    public Long getDefaultListId() {
        return this.defaultListId;
    }

    public LiveData<String> getListName() {
        MutableLiveData mutableLiveData = new MutableLiveData();
        if (this.lastViewedListId.equals(this.defaultListId)) {
            mutableLiveData.setValue(null);
        } else {
            mutableLiveData.setValue(this.mCacheManager.getListNameById(this.lastViewedListId.longValue()));
        }
        return mutableLiveData;
    }

    public List<Drug> getDrugsForList(Long l) {
        return this.mCacheManager.getDrugsForList(this.mContext, l);
    }

    public MutableLiveData<List<Drug>> getDrugs() {
        MutableLiveData<List<Drug>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(this.mCacheManager.getDrugsForList(this.mContext, this.lastViewedListId));
        return mutableLiveData;
    }

    public void addDrugToCurrentList(Drug drug) {
        if (drug != null) {
            if (!drug.isGenericDrug()) {
                drug = this.mCacheManager.getGenericDrug(drug);
            }
            if (!isDrugAdded(drug)) {
                this.mCacheManager.addDrugToList(this.lastViewedListId.longValue(), drug);
            }
        }
    }

    public void addPassedDrug(int i, String str) {
        Drug drugIdFromContentId = this.mCacheManager.getDrugIdFromContentId(this.mContext, i, str);
        if (drugIdFromContentId != null) {
            addDrugToCurrentList(drugIdFromContentId);
        }
    }

    public void removeDrug(Drug drug) {
        if (drug != null) {
            this.mCacheManager.removeDrugFromList(this.lastViewedListId.longValue(), drug);
        }
    }

    public boolean isDrugAdded(Drug drug) {
        List<Drug> drugsForList = this.mCacheManager.getDrugsForList(this.mContext, this.lastViewedListId);
        if (drugsForList == null || drugsForList.size() <= 0) {
            return false;
        }
        for (Drug next : drugsForList) {
            if (next != null && next.sameAs(drug)) {
                next.mergeOtherNames(drug);
                this.mCacheManager.updateDrugInList(this.lastViewedListId.longValue(), next);
                return true;
            }
        }
        return false;
    }

    private void copyOldDrugs() {
        List<Drug> interactionDrugs = this.mCacheManager.getInteractionDrugs();
        if (interactionDrugs != null && interactionDrugs.size() > 0) {
            for (Drug addDrugToCurrentList : interactionDrugs) {
                addDrugToCurrentList(addDrugToCurrentList);
            }
        }
        this.mCacheManager.deleteAllIntetractionsDrugs();
    }

    public List<DrugList> getDrugListsForUser(long j) {
        return this.mCacheManager.getDrugListsForUser(j);
    }

    public Long getLastViewedListId() {
        return this.lastViewedListId;
    }

    public void createList(String str) {
        long createNewList = this.mCacheManager.createNewList(str);
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        sharedPreferenceProvider.save(Constants.PREF_LAST_LIST_ID + this.userGuid, Long.valueOf(createNewList));
        this.lastViewedListId = Long.valueOf(createNewList);
        this.mCacheManager.moveDrugsToNewList(createNewList, this.userGuid);
        this.mCacheManager.removeAllDrugsForList(this.defaultListId.longValue());
    }

    public void removeList(long j) {
        this.mCacheManager.removeAllDrugsForList(j);
        this.mCacheManager.removeList(j);
    }

    public void closeList() {
        this.lastViewedListId = this.defaultListId;
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        sharedPreferenceProvider.save(Constants.PREF_LAST_LIST_ID + this.userGuid, this.defaultListId);
    }

    public void openList(Long l) {
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        sharedPreferenceProvider.save(Constants.PREF_LAST_LIST_ID + this.userGuid, l);
        this.lastViewedListId = l;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.mCacheManager = cacheManager;
    }

    public void setUserGuid(String str) {
        this.userGuid = str;
    }

    public void setDefaultListId(Long l) {
        this.defaultListId = l;
    }

    public void setLastViewedListId(Long l) {
        this.lastViewedListId = l;
    }
}
