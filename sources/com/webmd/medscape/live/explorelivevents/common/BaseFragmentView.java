package com.webmd.medscape.live.explorelivevents.common;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavArgs;
import androidx.navigation.ui.AppBarConfiguration;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J \u0010\u000b\u001a\u00020\u0003\"\b\b\u0000\u0010\f*\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u001a\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\b\b\u0001\u0010\"\u001a\u00020\u001dH\u0016J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0013H\u0016J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010+\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001dH\u0016J2\u00100\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u001d2\b\b\u0002\u0010&\u001a\u00020\u0013H\u0016¨\u00064"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/BaseFragmentView;", "", "clearFilterSelections", "", "extractArguments", "args", "Landroidx/navigation/NavArgs;", "handleErrorResponse", "message", "", "handleLoadingResponse", "handleSuccessResponse", "T", "resource", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "hideView", "view", "Landroid/view/View;", "hide", "", "initBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initDependencies", "initUI", "navigateToFilterSelection", "viewType", "", "observeViewModel", "renderMenuItem", "menuItem", "Landroid/view/MenuItem;", "titleRes", "renderToolbar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "isFirstScreen", "sendOmnitureActionCallToApp", "omnitureInfoParams", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "sendOmniturePageViewCallToApp", "setToolbar", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "setupStatusBar", "statusBarColor", "setupToolbar", "backgroundColor", "titleColor", "navIconRes", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseFragmentView.kt */
public interface BaseFragmentView {
    void clearFilterSelections();

    void extractArguments(NavArgs navArgs);

    void handleErrorResponse(String str);

    void handleLoadingResponse(String str);

    <T> void handleSuccessResponse(Resource<T> resource);

    void hideView(View view, boolean z);

    void initBinding(LayoutInflater layoutInflater, ViewGroup viewGroup);

    void initDependencies();

    void initUI();

    void navigateToFilterSelection(int i);

    void observeViewModel();

    void renderMenuItem(MenuItem menuItem, int i);

    void renderToolbar(Toolbar toolbar, boolean z);

    void sendOmnitureActionCallToApp(OmnitureInfo omnitureInfo);

    void sendOmniturePageViewCallToApp(OmnitureInfo omnitureInfo);

    void setToolbar(Toolbar toolbar, AppBarConfiguration appBarConfiguration);

    void setupStatusBar(int i);

    void setupToolbar(Toolbar toolbar, int i, int i2, int i3, boolean z);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    /* compiled from: BaseFragmentView.kt */
    public static final class DefaultImpls {
        public static void clearFilterSelections(BaseFragmentView baseFragmentView) {
        }

        public static void extractArguments(BaseFragmentView baseFragmentView, NavArgs navArgs) {
        }

        public static void handleErrorResponse(BaseFragmentView baseFragmentView, String str) {
        }

        public static void handleLoadingResponse(BaseFragmentView baseFragmentView, String str) {
        }

        public static <T> void handleSuccessResponse(BaseFragmentView baseFragmentView, Resource<T> resource) {
            Intrinsics.checkNotNullParameter(resource, ContentParser.RESOURCE);
        }

        public static void hideView(BaseFragmentView baseFragmentView, View view, boolean z) {
            Intrinsics.checkNotNullParameter(view, "view");
        }

        public static void initDependencies(BaseFragmentView baseFragmentView) {
        }

        public static void initUI(BaseFragmentView baseFragmentView) {
        }

        public static void navigateToFilterSelection(BaseFragmentView baseFragmentView, int i) {
        }

        public static void observeViewModel(BaseFragmentView baseFragmentView) {
        }

        public static void renderMenuItem(BaseFragmentView baseFragmentView, MenuItem menuItem, int i) {
            Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        }

        public static void renderToolbar(BaseFragmentView baseFragmentView, Toolbar toolbar, boolean z) {
            Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        }

        public static void sendOmnitureActionCallToApp(BaseFragmentView baseFragmentView, OmnitureInfo omnitureInfo) {
            Intrinsics.checkNotNullParameter(omnitureInfo, "omnitureInfoParams");
        }

        public static void sendOmniturePageViewCallToApp(BaseFragmentView baseFragmentView, OmnitureInfo omnitureInfo) {
            Intrinsics.checkNotNullParameter(omnitureInfo, "omnitureInfoParams");
        }

        public static void setToolbar(BaseFragmentView baseFragmentView, Toolbar toolbar, AppBarConfiguration appBarConfiguration) {
            Intrinsics.checkNotNullParameter(toolbar, "toolbar");
            Intrinsics.checkNotNullParameter(appBarConfiguration, "appBarConfiguration");
        }

        public static void setupStatusBar(BaseFragmentView baseFragmentView, int i) {
        }

        public static void setupToolbar(BaseFragmentView baseFragmentView, Toolbar toolbar, int i, int i2, int i3, boolean z) {
            Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        }

        public static /* synthetic */ void setupToolbar$default(BaseFragmentView baseFragmentView, Toolbar toolbar, int i, int i2, int i3, boolean z, int i4, Object obj) {
            if (obj == null) {
                baseFragmentView.setupToolbar(toolbar, i, i2, i3, (i4 & 16) != 0 ? false : z);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setupToolbar");
        }
    }
}
