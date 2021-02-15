package com.medscape.android.activity.interactions.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.models.DrugList;
import com.medscape.android.activity.interactions.models.InteractionsMenuOptions;
import com.medscape.android.activity.interactions.repositories.InteractionListRepository;
import com.medscape.android.activity.interactions.repositories.InteractionRepository;
import com.medscape.android.db.model.Drug;
import com.medscape.android.db.model.Interaction;
import java.util.ArrayList;
import java.util.List;

public class InteractionsViewModel extends AndroidViewModel {
    public MutableLiveData<List<Drug>> drugList;
    InteractionRepository interactionRepo;
    LiveData<List<Interaction>> interactions;
    MutableLiveData<String> listName = new MutableLiveData<>();
    InteractionListRepository listRepo;
    private Context mContext;
    InteractionsMenuOptions menuOptions;
    private long noShowListId = -1;
    MutableLiveData<List<DrugList>> userLists;

    public /* synthetic */ LiveData lambda$new$0$InteractionsViewModel(List list) {
        return this.interactionRepo.getInteractions(list);
    }

    public InteractionsViewModel(Application application) {
        super(application);
        MutableLiveData<List<Drug>> mutableLiveData = new MutableLiveData<>();
        this.drugList = mutableLiveData;
        this.interactions = Transformations.switchMap(mutableLiveData, new Function() {
            public final Object apply(Object obj) {
                return InteractionsViewModel.this.lambda$new$0$InteractionsViewModel((List) obj);
            }
        });
        this.userLists = new MutableLiveData<>();
        this.menuOptions = new InteractionsMenuOptions();
        this.mContext = application.getApplicationContext();
    }

    public void setUp() {
        this.listRepo = new InteractionListRepository(this.mContext);
        this.interactionRepo = new InteractionRepository(this.mContext);
        loadDrugs();
    }

    public void addDrug(Drug drug) {
        if (drug != null) {
            this.listRepo.addDrugToCurrentList(drug);
            this.drugList.setValue(this.listRepo.getDrugs().getValue());
        }
    }

    public void addPassedDrug(Drug drug) {
        if (drug != null) {
            this.listRepo.addPassedDrug(drug.getContentId(), drug.getDrugName());
            this.drugList.setValue(this.listRepo.getDrugs().getValue());
        }
    }

    public void removeDrug(Drug drug) {
        if (drug != null) {
            this.listRepo.removeDrug(drug);
            this.drugList.setValue(this.listRepo.getDrugs().getValue());
        }
    }

    public void clearAll() {
        for (Drug removeDrug : this.drugList.getValue()) {
            this.listRepo.removeDrug(removeDrug);
        }
        this.drugList.setValue(new ArrayList());
    }

    public void addListOfDrugs(DrugList drugList2) {
        if (this.drugList.getValue().size() > 0) {
            List<Drug> drugsForList = this.listRepo.getDrugsForList(drugList2.getId());
            if (drugsForList != null && !drugsForList.isEmpty()) {
                for (Drug addDrug : drugsForList) {
                    addDrug(addDrug);
                }
                return;
            }
            return;
        }
        this.listRepo.openList(drugList2.getId());
        this.listName.setValue(this.listRepo.getListName().getValue());
        this.drugList.setValue(this.listRepo.getDrugs().getValue());
    }

    public void loadDrugs() {
        this.drugList.setValue(this.listRepo.getDrugs().getValue());
        this.listName.setValue(this.listRepo.getListName().getValue());
    }

    public LiveData<String> getListName() {
        return this.listName;
    }

    public LiveData<List<Drug>> getDrugList() {
        return this.drugList;
    }

    public LiveData<List<Interaction>> getInteractions() {
        return this.interactions;
    }

    public int getDrugListSize() {
        return this.drugList.getValue().size();
    }

    public InteractionsMenuOptions getMenuOptions() {
        return this.menuOptions;
    }

    public void refreshOptionMenu() {
        if (this.listName.getValue() == null) {
            this.menuOptions.setCloseVisible(false);
            this.menuOptions.setSaveVisible(true);
            if (this.drugList.getValue().size() > 0) {
                this.menuOptions.setSaveBlack(true);
            } else {
                this.menuOptions.setSaveBlack(false);
            }
        } else {
            this.menuOptions.setCloseVisible(true);
            this.menuOptions.setSaveVisible(false);
        }
        List<DrugList> drugListsForUser = this.listRepo.getDrugListsForUser(-1);
        if (drugListsForUser.size() != 1) {
            this.menuOptions.setOpenVisible(true);
        } else if (this.listRepo.getLastViewedListId().equals(drugListsForUser.get(0).getId())) {
            this.menuOptions.setOpenVisible(false);
        } else {
            this.menuOptions.setOpenVisible(true);
        }
        if (this.drugList.getValue() == null || this.drugList.getValue().isEmpty() || drugListsForUser.size() <= 0) {
            this.menuOptions.setOpenMenuTitle(this.mContext.getString(R.string.drug_interaction_open_list));
        } else {
            this.menuOptions.setOpenMenuTitle(this.mContext.getString(R.string.drug_interaction_add_from_saved));
        }
        if (drugListsForUser.size() > 0) {
            this.menuOptions.setOpenMenuBlack(true);
        } else {
            this.menuOptions.setOpenMenuBlack(false);
        }
    }

    public void closeList() {
        this.listRepo.closeList();
        this.listName.setValue(this.listRepo.getListName().getValue());
        this.drugList.setValue(this.listRepo.getDrugs().getValue());
    }

    public void createList(String str) {
        if (str != null) {
            this.listRepo.createList(str);
            this.listName.setValue(this.listRepo.getListName().getValue());
        }
    }

    public Long getCurrentListId() {
        if (!this.listRepo.getLastViewedListId().equals(this.listRepo.getDefaultListId())) {
            return this.listRepo.getLastViewedListId();
        }
        return -1L;
    }

    public boolean haveSavedList() {
        List<DrugList> drugListsForUser = this.listRepo.getDrugListsForUser(-1);
        return drugListsForUser != null && !drugListsForUser.isEmpty();
    }

    public void loadUserLists(long j) {
        this.noShowListId = j;
        this.userLists.setValue(this.listRepo.getDrugListsForUser(j));
    }

    public MutableLiveData<List<DrugList>> getUserLists() {
        return this.userLists;
    }

    public void removeList(long j) {
        this.listRepo.removeList(j);
        this.userLists.setValue(this.listRepo.getDrugListsForUser(this.noShowListId));
    }

    public void setListRepo(InteractionListRepository interactionListRepository) {
        this.listRepo = interactionListRepository;
    }

    public void setInteractionRepo(InteractionRepository interactionRepository) {
        this.interactionRepo = interactionRepository;
    }

    public void setListName(MutableLiveData<String> mutableLiveData) {
        this.listName = mutableLiveData;
    }

    public void setNoShowListId(long j) {
        this.noShowListId = j;
    }

    public long getNoShowListId() {
        return this.noShowListId;
    }
}
