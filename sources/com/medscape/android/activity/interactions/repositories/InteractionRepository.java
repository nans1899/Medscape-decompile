package com.medscape.android.activity.interactions.repositories;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.medscape.android.activity.interactions.InteractionsDataManager;
import com.medscape.android.db.model.Drug;
import com.medscape.android.db.model.Interaction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class InteractionRepository {
    Executor executor = $$Lambda$InteractionRepository$jA63celV_Bj1k18Z86DvK4UOMM.INSTANCE;
    private Context mContext;

    public InteractionRepository(Context context) {
        this.mContext = context;
    }

    public LiveData<List<Interaction>> getInteractions(List<Drug> list) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        this.executor.execute(new Runnable(list, mutableLiveData) {
            public final /* synthetic */ List f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                InteractionRepository.this.lambda$getInteractions$1$InteractionRepository(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$getInteractions$1$InteractionRepository(List list, MutableLiveData mutableLiveData) {
        mutableLiveData.postValue(InteractionsDataManager.interactionsWithDrugs((ArrayList) list, this.mContext));
    }
}
