package com.webmd.medscape.live.explorelivevents.ui.fragments;

import androidx.lifecycle.Observer;
import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.ui.fragments.FilterSelectionFragment;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragment.kt */
final class FilterSelectionFragment$observeViewModel$1<T> implements Observer<Resource<FiltersResponse>> {
    final /* synthetic */ FilterSelectionFragment this$0;

    FilterSelectionFragment$observeViewModel$1(FilterSelectionFragment filterSelectionFragment) {
        this.this$0 = filterSelectionFragment;
    }

    public final void onChanged(Resource<FiltersResponse> resource) {
        FiltersResponse data;
        if (FilterSelectionFragment.WhenMappings.$EnumSwitchMapping$0[resource.getStatus().ordinal()] == 1 && (data = resource.getData()) != null) {
            FilterSelectionFragment.access$getFiltersAdapter$p(this.this$0).setData(data);
        }
    }
}
