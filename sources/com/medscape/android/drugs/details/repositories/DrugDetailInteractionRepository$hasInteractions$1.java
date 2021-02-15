package com.medscape.android.drugs.details.repositories;

import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.details.util.DatabaseRequests;
import com.medscape.android.interfaces.ICallbackEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailInteractionRepository.kt */
final class DrugDetailInteractionRepository$hasInteractions$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ICallbackEvent $callback;
    final /* synthetic */ DrugDetailInteractionRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DrugDetailInteractionRepository$hasInteractions$1(DrugDetailInteractionRepository drugDetailInteractionRepository, ICallbackEvent iCallbackEvent) {
        super(0);
        this.this$0 = drugDetailInteractionRepository;
        this.$callback = iCallbackEvent;
    }

    public final void invoke() {
        List list;
        List<InteractionListItem> interactionDrugList;
        try {
            this.this$0.setRequests(new DatabaseRequests(this.this$0.getMContext()));
            DrugDetailInteractionRepository drugDetailInteractionRepository = this.this$0;
            DatabaseRequests requests = this.this$0.getRequests();
            if (requests == null || (interactionDrugList = requests.getInteractionDrugList(String.valueOf(this.this$0.getContentId()))) == null || (list = CollectionsKt.toMutableList(interactionDrugList)) == null) {
                list = new ArrayList();
            }
            drugDetailInteractionRepository.setOriginalList(list);
            this.$callback.onCompleted(Boolean.valueOf(this.this$0.getOriginalList().size() > 0));
        } catch (Exception e) {
            this.$callback.onError(e);
        }
    }
}
