package com.medscape.android.activity.interactions.repositories;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.db.model.Drug;
import com.medscape.android.drugs.helper.SearchHelper;
import java.util.ArrayList;
import java.util.List;

public class DrugSearchRepository {
    private SearchHelper mSearchHelper;

    public DrugSearchRepository() {
        this.mSearchHelper = new SearchHelper();
    }

    public DrugSearchRepository(SearchHelper searchHelper) {
        this.mSearchHelper = searchHelper;
    }

    public LiveData<List<Drug>> getSearchResults(Context context, String str) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        ArrayList arrayList = new ArrayList();
        if (!(context == null || str == null)) {
            for (CRData next : this.mSearchHelper.wordDrugInteractionMatching(str, getSearchCount(str), 0, context)) {
                Drug drug = new Drug();
                drug.setContentId(next.getCid());
                drug.setDrugName(next.getTitle());
                drug.setComboId(next.getComboId());
                drug.setDrugId(next.getDrugId());
                drug.setGenericId(next.getGenericId());
                arrayList.add(drug);
            }
        }
        mutableLiveData.setValue(arrayList);
        return mutableLiveData;
    }

    public int getSearchCount(String str) {
        return (str == null || str.length() < 2) ? 25 : 0;
    }

    public void setSearchHelper(SearchHelper searchHelper) {
        this.mSearchHelper = searchHelper;
    }
}
