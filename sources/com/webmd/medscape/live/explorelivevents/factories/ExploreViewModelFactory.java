package com.webmd.medscape.live.explorelivevents.factories;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/factories/ExploreViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "args", "Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "(Landroid/content/Context;Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreViewModelFactory.kt */
public final class ExploreViewModelFactory implements ViewModelProvider.Factory {
    private SharedPreferencesManager args;
    private final Context context;

    public ExploreViewModelFactory(Context context2, SharedPreferencesManager sharedPreferencesManager) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(sharedPreferencesManager, "args");
        this.context = context2;
        this.args = sharedPreferencesManager;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        return (ViewModel) cls.getConstructor(new Class[]{Context.class, SharedPreferencesManager.class}).newInstance(new Object[]{this.context, this.args});
    }
}
