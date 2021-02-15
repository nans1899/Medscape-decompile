package com.webmd.medscape.live.explorelivevents;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.databinding.ButtonItemBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.DropdownFilterItemBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.EventItemBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentEventsBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentExploreBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.LocationItemBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.QuickFilterItemView2BindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.QuickFiltersView2BindingImpl;
import com.webmd.medscape.live.explorelivevents.databinding.SpecialtyItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_BUTTONITEM = 1;
    private static final int LAYOUT_DROPDOWNFILTERITEM = 2;
    private static final int LAYOUT_EVENTITEM = 3;
    private static final int LAYOUT_FRAGMENTEVENTS = 4;
    private static final int LAYOUT_FRAGMENTEXPLORE = 5;
    private static final int LAYOUT_LOCATIONITEM = 6;
    private static final int LAYOUT_NOTFOUNDERRORVIEW = 7;
    private static final int LAYOUT_QUICKFILTERITEMVIEW2 = 8;
    private static final int LAYOUT_QUICKFILTERSVIEW2 = 9;
    private static final int LAYOUT_SPECIALTYITEM = 10;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(10);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.button_item, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dropdown_filter_item, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.event_item, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_events, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_explore, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.location_item, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.not_found_error_view, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.quick_filter_item_view2, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.quick_filters_view2, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.specialty_item, 10);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/button_item_0".equals(tag)) {
                        return new ButtonItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for button_item is invalid. Received: " + tag);
                case 2:
                    if ("layout/dropdown_filter_item_0".equals(tag)) {
                        return new DropdownFilterItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dropdown_filter_item is invalid. Received: " + tag);
                case 3:
                    if ("layout/event_item_0".equals(tag)) {
                        return new EventItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for event_item is invalid. Received: " + tag);
                case 4:
                    if ("layout/fragment_events_0".equals(tag)) {
                        return new FragmentEventsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_events is invalid. Received: " + tag);
                case 5:
                    if ("layout/fragment_explore_0".equals(tag)) {
                        return new FragmentExploreBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_explore is invalid. Received: " + tag);
                case 6:
                    if ("layout/location_item_0".equals(tag)) {
                        return new LocationItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for location_item is invalid. Received: " + tag);
                case 7:
                    if ("layout/not_found_error_view_0".equals(tag)) {
                        return new NotFoundErrorViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for not_found_error_view is invalid. Received: " + tag);
                case 8:
                    if ("layout/quick_filter_item_view2_0".equals(tag)) {
                        return new QuickFilterItemView2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for quick_filter_item_view2 is invalid. Received: " + tag);
                case 9:
                    if ("layout/quick_filters_view2_0".equals(tag)) {
                        return new QuickFiltersView2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for quick_filters_view2 is invalid. Received: " + tag);
                case 10:
                    if ("layout/specialty_item_0".equals(tag)) {
                        return new SpecialtyItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for specialty_item is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(9);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "button");
            sKeys.put(2, OmnitureConstants.OMNITURE_MODULE_FILTER);
            sKeys.put(3, "filterName");
            sKeys.put(4, "liveEvent");
            sKeys.put(5, "location");
            sKeys.put(6, "searchFilter");
            sKeys.put(7, "styleManager");
            sKeys.put(8, "viewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(10);
            sKeys = hashMap;
            hashMap.put("layout/button_item_0", Integer.valueOf(R.layout.button_item));
            sKeys.put("layout/dropdown_filter_item_0", Integer.valueOf(R.layout.dropdown_filter_item));
            sKeys.put("layout/event_item_0", Integer.valueOf(R.layout.event_item));
            sKeys.put("layout/fragment_events_0", Integer.valueOf(R.layout.fragment_events));
            sKeys.put("layout/fragment_explore_0", Integer.valueOf(R.layout.fragment_explore));
            sKeys.put("layout/location_item_0", Integer.valueOf(R.layout.location_item));
            sKeys.put("layout/not_found_error_view_0", Integer.valueOf(R.layout.not_found_error_view));
            sKeys.put("layout/quick_filter_item_view2_0", Integer.valueOf(R.layout.quick_filter_item_view2));
            sKeys.put("layout/quick_filters_view2_0", Integer.valueOf(R.layout.quick_filters_view2));
            sKeys.put("layout/specialty_item_0", Integer.valueOf(R.layout.specialty_item));
        }
    }
}
