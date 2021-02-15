package com.medscape.android;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.medscape.android.databinding.AdItemLayoutBindingImpl;
import com.medscape.android.databinding.ClrefTocFragmentBindingImpl;
import com.medscape.android.databinding.HomeCardInvitationBindingImpl;
import com.medscape.android.databinding.HomeNativeAdLayoutBindingImpl;
import com.medscape.android.databinding.ItemConsultPostUpdateBindingImpl;
import com.medscape.android.databinding.MyInvitationsCardBindingImpl;
import com.medscape.android.databinding.NativeAdBindingLayoutBindingImpl;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ADITEMLAYOUT = 1;
    private static final int LAYOUT_CLREFTOCFRAGMENT = 2;
    private static final int LAYOUT_HOMECARDINVITATION = 3;
    private static final int LAYOUT_HOMENATIVEADLAYOUT = 4;
    private static final int LAYOUT_ITEMCONSULTPOSTUPDATE = 5;
    private static final int LAYOUT_MYINVITATIONSCARD = 6;
    private static final int LAYOUT_NATIVEADBINDINGLAYOUT = 7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(7);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.ad_item_layout, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.clref_toc_fragment, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.home_card_invitation, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.home_native_ad_layout, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_consult_post_update, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.my_invitations_card, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.native_ad_binding_layout, 7);
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
                    if ("layout/ad_item_layout_0".equals(tag)) {
                        return new AdItemLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ad_item_layout is invalid. Received: " + tag);
                case 2:
                    if ("layout/clref_toc_fragment_0".equals(tag)) {
                        return new ClrefTocFragmentBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for clref_toc_fragment is invalid. Received: " + tag);
                case 3:
                    if ("layout/home_card_invitation_0".equals(tag)) {
                        return new HomeCardInvitationBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for home_card_invitation is invalid. Received: " + tag);
                case 4:
                    if ("layout/home_native_ad_layout_0".equals(tag)) {
                        return new HomeNativeAdLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for home_native_ad_layout is invalid. Received: " + tag);
                case 5:
                    if ("layout/item_consult_post_update_0".equals(tag)) {
                        return new ItemConsultPostUpdateBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_consult_post_update is invalid. Received: " + tag);
                case 6:
                    if ("layout/my_invitations_card_0".equals(tag)) {
                        return new MyInvitationsCardBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for my_invitations_card is invalid. Received: " + tag);
                case 7:
                    if ("layout/native_ad_binding_layout_0".equals(tag)) {
                        return new NativeAdBindingLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for native_ad_binding_layout is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(7);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.wbmd.ads.DataBinderMapperImpl());
        arrayList.add(new com.wbmd.decisionpoint.DataBinderMapperImpl());
        arrayList.add(new com.wbmd.environment.DataBinderMapperImpl());
        arrayList.add(new com.webmd.medscape.live.explorelivevents.DataBinderMapperImpl());
        arrayList.add(new com.webmd.wbmdlanguages.DataBinderMapperImpl());
        arrayList.add(new com.webmd.webmdrx.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(23);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "activityAdModel");
            sKeys.put(2, "admodel");
            sKeys.put(3, "bodyupdate");
            sKeys.put(4, "button");
            sKeys.put(5, "contributor");
            sKeys.put(6, "dfpad");
            sKeys.put(7, OmnitureConstants.OMNITURE_MODULE_FILTER);
            sKeys.put(8, "filterName");
            sKeys.put(9, FeedConstants.INVITATION_AD);
            sKeys.put(10, ContentParser.ITEM);
            sKeys.put(11, "language_model");
            sKeys.put(12, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            sKeys.put(13, "liveEvent");
            sKeys.put(14, "location");
            sKeys.put(15, "module");
            sKeys.put(16, "nativeViewModel");
            sKeys.put(17, "nativead");
            sKeys.put(18, "searchFilter");
            sKeys.put(19, "styleManager");
            sKeys.put(20, "title");
            sKeys.put(21, "viewModel");
            sKeys.put(22, "viewmodel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(7);
            sKeys = hashMap;
            hashMap.put("layout/ad_item_layout_0", Integer.valueOf(R.layout.ad_item_layout));
            sKeys.put("layout/clref_toc_fragment_0", Integer.valueOf(R.layout.clref_toc_fragment));
            sKeys.put("layout/home_card_invitation_0", Integer.valueOf(R.layout.home_card_invitation));
            sKeys.put("layout/home_native_ad_layout_0", Integer.valueOf(R.layout.home_native_ad_layout));
            sKeys.put("layout/item_consult_post_update_0", Integer.valueOf(R.layout.item_consult_post_update));
            sKeys.put("layout/my_invitations_card_0", Integer.valueOf(R.layout.my_invitations_card));
            sKeys.put("layout/native_ad_binding_layout_0", Integer.valueOf(R.layout.native_ad_binding_layout));
        }
    }
}
