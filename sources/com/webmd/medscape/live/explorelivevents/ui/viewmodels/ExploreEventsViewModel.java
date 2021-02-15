package com.webmd.medscape.live.explorelivevents.ui.viewmodels;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.webmd.medscape.live.explorelivevents.common.MenuUiState;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.network.EventsRepository;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.medscape.live.explorelivevents.util.GsonUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J@\u0010\u001d\u001a\u00020\u001e2\u0010\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u0010\u0010 \u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010!\u001a\u0004\u0018\u00010\u00122\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0002J\u0006\u0010#\u001a\u00020\u001eJ\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010%\u001a\u00020\u0017J\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010&\u001a\u00020\u001e2\b\u0010'\u001a\u0004\u0018\u00010\u0012J>\u0010(\u001a\u00020\u001e2\u0010\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u0010\u0010 \u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010!\u001a\u0004\u0018\u00010\u00122\b\u0010\"\u001a\u0004\u0018\u00010\u0012J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bJ\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f0\bJ\u0018\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000f0\bJ\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00170\bJ\u000e\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u0017J\u000e\u00100\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f0\bX\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000f0\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000b¨\u00061"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/viewmodels/ExploreEventsViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "sharedPreferencesManager", "Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "(Landroid/content/Context;Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;)V", "errorObserver", "Landroidx/lifecycle/MutableLiveData;", "Lcom/webmd/medscape/live/explorelivevents/data/Error;", "getErrorObserver", "()Landroidx/lifecycle/MutableLiveData;", "eventsRepository", "Lcom/webmd/medscape/live/explorelivevents/network/EventsRepository;", "filtersObserver", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "liveEventUrlObserver", "", "liveEventsObserver", "", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "menuUiStateObserver", "Lcom/webmd/medscape/live/explorelivevents/common/MenuUiState;", "retrofit", "Lretrofit2/Retrofit;", "uiState", "Lcom/webmd/medscape/live/explorelivevents/data/UiState;", "getUiState", "fetchLiveEvents", "", "specialties", "locations", "startDate", "endDate", "getFilters", "getFiltersFromApi", "getMenuUiState", "loadEventUrl", "url", "loadEvents", "observeErrorObserver", "observeFilters", "observeLiveEventUrl", "observeLiveEvents", "observeMenuUiState", "setMenuUiState", "menuUiState", "setRetrofit", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreEventsViewModel.kt */
public final class ExploreEventsViewModel extends ViewModel {
    private final MutableLiveData<Error> errorObserver;
    private EventsRepository eventsRepository;
    private final MutableLiveData<Resource<FiltersResponse>> filtersObserver;
    private final MutableLiveData<Resource<String>> liveEventUrlObserver;
    /* access modifiers changed from: private */
    public final MutableLiveData<Resource<List<LiveEvent>>> liveEventsObserver;
    private final MutableLiveData<MenuUiState> menuUiStateObserver;
    private Retrofit retrofit;
    private SharedPreferencesManager sharedPreferencesManager;
    private final MutableLiveData<UiState> uiState = new MutableLiveData<>();

    public ExploreEventsViewModel(Context context, SharedPreferencesManager sharedPreferencesManager2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sharedPreferencesManager2, "sharedPreferencesManager");
        this.sharedPreferencesManager = sharedPreferencesManager2;
        this.eventsRepository = new EventsRepository(context, this.sharedPreferencesManager);
        this.liveEventsObserver = new MutableLiveData<>();
        this.filtersObserver = new MutableLiveData<>();
        this.liveEventUrlObserver = new MutableLiveData<>();
        this.menuUiStateObserver = new MutableLiveData<>();
        this.errorObserver = new MutableLiveData<>();
        this.uiState.setValue(new UiState(false, false, false, false, 15, (DefaultConstructorMarker) null));
        this.menuUiStateObserver.setValue(new MenuUiState(false, false, false, 7, (DefaultConstructorMarker) null));
        this.liveEventUrlObserver.setValue(Resource.Companion.loading("Loading please wait", null));
    }

    public final MutableLiveData<UiState> getUiState() {
        return this.uiState;
    }

    public final MutableLiveData<Error> getErrorObserver() {
        return this.errorObserver;
    }

    /* renamed from: getUiState  reason: collision with other method in class */
    public final UiState m1getUiState() {
        return this.uiState.getValue();
    }

    public final MenuUiState getMenuUiState() {
        MenuUiState value = this.menuUiStateObserver.getValue();
        if (value != null) {
            return value;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.common.MenuUiState");
    }

    public final void loadEvents(List<String> list, List<String> list2, String str, String str2) {
        this.liveEventsObserver.setValue(Resource.Companion.loading("Loading", null));
        fetchLiveEvents(list, list2, str, str2);
    }

    public final void getFiltersFromApi(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        EventsRepository eventsRepository2 = this.eventsRepository;
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        eventsRepository2.setRetrofit(retrofit3);
        this.eventsRepository.getFilters(context, ExtensionsKt.toServerDate(ZonedDateTime.now()));
    }

    public final void getFilters() {
        String string = this.sharedPreferencesManager.getString("filters_cache", (String) null);
        if (string != null) {
            Object deserialize = GsonUtils.INSTANCE.deserialize(FiltersResponse.class, string);
            if (deserialize != null) {
                this.filtersObserver.setValue(Resource.Companion.success("Success", (FiltersResponse) deserialize));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.data.FiltersResponse");
        }
    }

    private final void fetchLiveEvents(List<String> list, List<String> list2, String str, String str2) {
        EventsRepository eventsRepository2 = this.eventsRepository;
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        eventsRepository2.setRetrofit(retrofit3);
        this.eventsRepository.getLiveEvents(list, list2, str, str2, new ExploreEventsViewModel$fetchLiveEvents$1(this), new ExploreEventsViewModel$fetchLiveEvents$2(this));
    }

    public final void loadEventUrl(String str) {
        if (str != null) {
            this.liveEventUrlObserver.setValue(Resource.Companion.success("Success", str));
        } else {
            this.liveEventUrlObserver.setValue(Resource.Companion.error("Please provide a url", null));
        }
    }

    public final void setRetrofit(Retrofit retrofit3) {
        Intrinsics.checkNotNullParameter(retrofit3, "retrofit");
        this.retrofit = retrofit3;
    }

    public final void setMenuUiState(MenuUiState menuUiState) {
        Intrinsics.checkNotNullParameter(menuUiState, "menuUiState");
        this.menuUiStateObserver.setValue(menuUiState);
    }

    public final MutableLiveData<Resource<List<LiveEvent>>> observeLiveEvents() {
        return this.liveEventsObserver;
    }

    public final MutableLiveData<Resource<FiltersResponse>> observeFilters() {
        return this.filtersObserver;
    }

    public final MutableLiveData<Resource<String>> observeLiveEventUrl() {
        return this.liveEventUrlObserver;
    }

    public final MutableLiveData<MenuUiState> observeMenuUiState() {
        return this.menuUiStateObserver;
    }

    public final MutableLiveData<Error> observeErrorObserver() {
        return this.errorObserver;
    }
}
