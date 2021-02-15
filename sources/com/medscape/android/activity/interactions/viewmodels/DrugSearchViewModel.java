package com.medscape.android.activity.interactions.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.medscape.android.activity.interactions.repositories.DrugSearchRepository;
import com.medscape.android.db.model.Drug;
import java.util.List;

public class DrugSearchViewModel extends AndroidViewModel {
    private DrugSearchRepository drugSearchRepository;
    private Context mContext;
    private MutableLiveData<Boolean> mIsSearchListEmpty = new MutableLiveData<>();
    private MutableLiveData<Boolean> mShowSearchUi = new MutableLiveData<>();
    public LiveData<List<Drug>> searchResult;
    private MutableLiveData<String> searchTerm;

    public LiveData<List<Drug>> getSearchResult() {
        return this.searchResult;
    }

    public /* synthetic */ LiveData lambda$new$0$DrugSearchViewModel(String str) {
        LiveData<List<Drug>> searchResults = this.drugSearchRepository.getSearchResults(this.mContext, str);
        this.mIsSearchListEmpty.setValue(Boolean.valueOf(searchResults.getValue().isEmpty()));
        return searchResults;
    }

    public DrugSearchViewModel(Application application) {
        super(application);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        this.searchTerm = mutableLiveData;
        this.searchResult = Transformations.switchMap(mutableLiveData, new Function() {
            public final Object apply(Object obj) {
                return DrugSearchViewModel.this.lambda$new$0$DrugSearchViewModel((String) obj);
            }
        });
        this.mContext = application.getApplicationContext();
        this.drugSearchRepository = new DrugSearchRepository();
    }

    public void setSearchTerm(String str) {
        if (str == null || str.length() <= 0) {
            this.mShowSearchUi.setValue(false);
            return;
        }
        this.searchTerm.setValue(str);
        this.mShowSearchUi.setValue(true);
    }

    public LiveData<Boolean> isShowSearchUi() {
        return this.mShowSearchUi;
    }

    public LiveData<Boolean> isSearchListEmpty() {
        return this.mIsSearchListEmpty;
    }

    public void setDrugSearchRepository(DrugSearchRepository drugSearchRepository2) {
        this.drugSearchRepository = drugSearchRepository2;
    }

    public MutableLiveData<Boolean> getShowSearchUi() {
        return this.mShowSearchUi;
    }

    public MutableLiveData<Boolean> getIsSearchListEmpty() {
        return this.mIsSearchListEmpty;
    }

    public MutableLiveData<String> getSearchTerm() {
        return this.searchTerm;
    }
}
