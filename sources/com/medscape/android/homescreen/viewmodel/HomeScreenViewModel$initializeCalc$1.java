package com.medscape.android.homescreen.viewmodel;

import com.medscape.android.Constants;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenViewModel.kt */
final class HomeScreenViewModel$initializeCalc$1 extends Lambda implements Function1<AnkoAsyncContext<HomeScreenViewModel>, Unit> {
    final /* synthetic */ HomeScreenViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HomeScreenViewModel$initializeCalc$1(HomeScreenViewModel homeScreenViewModel) {
        super(1);
        this.this$0 = homeScreenViewModel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AnkoAsyncContext<HomeScreenViewModel>) (AnkoAsyncContext) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(AnkoAsyncContext<HomeScreenViewModel> ankoAsyncContext) {
        Intrinsics.checkNotNullParameter(ankoAsyncContext, "$receiver");
        this.this$0.removeDBForHardCodedUser();
        User user = new User();
        user.identifier = Long.valueOf(this.this$0.wbmdUser);
        user.pnEnabled = false;
        DataManager.getInstance().initializeUserAccount(user);
        SharedPreferenceProvider.get().save(Constants.PREF_CALC_DB_VERSION, this.this$0.currentCalcDBVersion);
        this.this$0.updateOldCalculators();
    }
}
