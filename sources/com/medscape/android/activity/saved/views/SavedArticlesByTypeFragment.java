package com.medscape.android.activity.saved.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.saved.adapters.SavedItemsAdapter;
import com.medscape.android.activity.saved.viewmodel.SavedFeedViewModel;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager;
import com.medscape.android.cache.Cache;
import com.medscape.android.cache.DrugCache;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.parser.model.Article;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceArticleLandingActivity;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 /2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001/B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010#H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H\u0016J\u0018\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010.\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/medscape/android/activity/saved/views/SavedArticlesByTypeFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;", "Lcom/wbmd/qxcalculator/LaunchQxCallback;", "()V", "calcPvid", "", "mContext", "Landroidx/fragment/app/FragmentActivity;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "saveViewModel", "Lcom/medscape/android/activity/saved/viewmodel/SavedFeedViewModel;", "savedArticlesList", "Landroidx/recyclerview/widget/RecyclerView;", "savedList", "", "", "tabName", "markOmnitureData", "", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onQxItemClicked", "contentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "bundle", "onResume", "onSavedItemClicked", "position", "", "onSavedItemDelete", "view", "onSavedListItemClicked", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SavedArticlesByTypeFragment.kt */
public final class SavedArticlesByTypeFragment extends Fragment implements SavedItemsAdapter.OnSavedItemClicked, SavedItemsAdapter.OnSavedItemDelete, LaunchQxCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private String calcPvid = "";
    private FragmentActivity mContext;
    public View rootView;
    private SavedFeedViewModel saveViewModel;
    private RecyclerView savedArticlesList;
    private List<Object> savedList = new ArrayList();
    private String tabName;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onSavedItemDelete(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "view");
        SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            savedFeedViewModel.deleteSavedItem(view, (AppCompatActivity) activity);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onSavedItemClicked(int i) {
        onSavedListItemClicked(i);
    }

    public final View getRootView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.all_saved_articles_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…agment, container, false)");
        this.rootView = inflate;
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.mContext = activity;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
        }
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.saved_items_list);
        if (findViewById != null) {
            this.savedArticlesList = (RecyclerView) findViewById;
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            }
            SavedFeedViewModel savedFeedViewModel = (SavedFeedViewModel) ViewModelProviders.of(fragmentActivity).get(SavedFeedViewModel.class);
            Intrinsics.checkNotNullExpressionValue(savedFeedViewModel, "mContext.let { ViewModel…dViewModel::class.java) }");
            this.saveViewModel = savedFeedViewModel;
            if (savedFeedViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            ArrayList<RecyclerView> listRecyclerViews = savedFeedViewModel.getListRecyclerViews();
            RecyclerView recyclerView = this.savedArticlesList;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("savedArticlesList");
            }
            listRecyclerViews.add(recyclerView);
            View view2 = this.rootView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            return view2;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
    }

    public void onAttach(Context context) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null && (string = arguments.getString("tabName")) != null) {
            Intrinsics.checkNotNullExpressionValue(string, "it");
            this.tabName = string;
        }
    }

    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
            if (savedFeedViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            Intrinsics.checkNotNullExpressionValue(activity, "it");
            savedFeedViewModel.getAllSavedArticles(activity);
        }
        String str = this.tabName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabName");
        }
        SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
        if (savedFeedViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel2.getALL())) {
            SavedFeedViewModel savedFeedViewModel3 = this.saveViewModel;
            if (savedFeedViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            SavedFeedViewModel savedFeedViewModel4 = this.saveViewModel;
            if (savedFeedViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            savedFeedViewModel3.setActiveFragment(savedFeedViewModel4.getALL());
            this.savedList = new ArrayList();
            SavedFeedViewModel savedFeedViewModel5 = this.saveViewModel;
            if (savedFeedViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            List<Object> savedList2 = savedFeedViewModel5.getSavedList();
            List<Object> list = this.savedList;
            if (list != null) {
                ((ArrayList) list).addAll(savedList2);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
            }
        } else {
            SavedFeedViewModel savedFeedViewModel6 = this.saveViewModel;
            if (savedFeedViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel6.getDRUGS())) {
                SavedFeedViewModel savedFeedViewModel7 = this.saveViewModel;
                if (savedFeedViewModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                }
                SavedFeedViewModel savedFeedViewModel8 = this.saveViewModel;
                if (savedFeedViewModel8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                }
                savedFeedViewModel7.setActiveFragment(savedFeedViewModel8.getDRUGS());
                this.savedList = new ArrayList();
                SavedFeedViewModel savedFeedViewModel9 = this.saveViewModel;
                if (savedFeedViewModel9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                }
                List<DrugCache> savedDrugList = savedFeedViewModel9.getSavedDrugList();
                List<Object> list2 = this.savedList;
                if (list2 != null) {
                    ((ArrayList) list2).addAll(savedDrugList);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            } else {
                SavedFeedViewModel savedFeedViewModel10 = this.saveViewModel;
                if (savedFeedViewModel10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                }
                if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel10.getREF())) {
                    SavedFeedViewModel savedFeedViewModel11 = this.saveViewModel;
                    if (savedFeedViewModel11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                    }
                    SavedFeedViewModel savedFeedViewModel12 = this.saveViewModel;
                    if (savedFeedViewModel12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                    }
                    savedFeedViewModel11.setActiveFragment(savedFeedViewModel12.getREF());
                    this.savedList = new ArrayList();
                    SavedFeedViewModel savedFeedViewModel13 = this.saveViewModel;
                    if (savedFeedViewModel13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                    }
                    List<ClinicalReferenceArticle> crRefList = savedFeedViewModel13.getCrRefList();
                    List<Object> list3 = this.savedList;
                    if (list3 != null) {
                        ((ArrayList) list3).addAll(crRefList);
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                } else {
                    SavedFeedViewModel savedFeedViewModel14 = this.saveViewModel;
                    if (savedFeedViewModel14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                    }
                    if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel14.getCALC())) {
                        SavedFeedViewModel savedFeedViewModel15 = this.saveViewModel;
                        if (savedFeedViewModel15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                        }
                        SavedFeedViewModel savedFeedViewModel16 = this.saveViewModel;
                        if (savedFeedViewModel16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                        }
                        savedFeedViewModel15.setActiveFragment(savedFeedViewModel16.getCALC());
                        this.savedList = new ArrayList();
                        SavedFeedViewModel savedFeedViewModel17 = this.saveViewModel;
                        if (savedFeedViewModel17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                        }
                        List<CalcArticle> savedCalcList = savedFeedViewModel17.getSavedCalcList();
                        List<Object> list4 = this.savedList;
                        if (list4 != null) {
                            ((ArrayList) list4).addAll(savedCalcList);
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                        }
                    } else {
                        SavedFeedViewModel savedFeedViewModel18 = this.saveViewModel;
                        if (savedFeedViewModel18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                        }
                        if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel18.getNEWS())) {
                            SavedFeedViewModel savedFeedViewModel19 = this.saveViewModel;
                            if (savedFeedViewModel19 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                            }
                            SavedFeedViewModel savedFeedViewModel20 = this.saveViewModel;
                            if (savedFeedViewModel20 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                            }
                            savedFeedViewModel19.setActiveFragment(savedFeedViewModel20.getNEWS());
                            this.savedList = new ArrayList();
                            SavedFeedViewModel savedFeedViewModel21 = this.saveViewModel;
                            if (savedFeedViewModel21 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                            }
                            List<Cache> savedNewsList = savedFeedViewModel21.getSavedNewsList();
                            List<Object> list5 = this.savedList;
                            if (list5 != null) {
                                ((ArrayList) list5).addAll(savedNewsList);
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                            }
                        } else {
                            SavedFeedViewModel savedFeedViewModel22 = this.saveViewModel;
                            if (savedFeedViewModel22 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                            }
                            if (Intrinsics.areEqual((Object) str, (Object) savedFeedViewModel22.getEDU())) {
                                SavedFeedViewModel savedFeedViewModel23 = this.saveViewModel;
                                if (savedFeedViewModel23 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                                }
                                SavedFeedViewModel savedFeedViewModel24 = this.saveViewModel;
                                if (savedFeedViewModel24 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                                }
                                savedFeedViewModel23.setActiveFragment(savedFeedViewModel24.getEDU());
                                this.savedList = new ArrayList();
                                SavedFeedViewModel savedFeedViewModel25 = this.saveViewModel;
                                if (savedFeedViewModel25 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
                                }
                                List<Cache> savedCMEList = savedFeedViewModel25.getSavedCMEList();
                                List<Object> list6 = this.savedList;
                                if (list6 != null) {
                                    ((ArrayList) list6).addAll(savedCMEList);
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.savedList.size() > 0) {
            SavedFeedViewModel savedFeedViewModel26 = this.saveViewModel;
            if (savedFeedViewModel26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            }
            savedFeedViewModel26.setSavedItemsAdapter(new SavedItemsAdapter(fragmentActivity, this.savedList, this, this));
            RecyclerView recyclerView = this.savedArticlesList;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("savedArticlesList");
            }
            FragmentActivity fragmentActivity2 = this.mContext;
            if (fragmentActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity2));
            RecyclerView recyclerView2 = this.savedArticlesList;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("savedArticlesList");
            }
            SavedFeedViewModel savedFeedViewModel27 = this.saveViewModel;
            if (savedFeedViewModel27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            recyclerView2.setAdapter(savedFeedViewModel27.getSavedItemsAdapter());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/activity/saved/views/SavedArticlesByTypeFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/activity/saved/views/SavedArticlesByTypeFragment;", "tab", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedArticlesByTypeFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SavedArticlesByTypeFragment newInstance(String str) {
            Intrinsics.checkNotNullParameter(str, "tab");
            SavedArticlesByTypeFragment savedArticlesByTypeFragment = new SavedArticlesByTypeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("tabName", str);
            savedArticlesByTypeFragment.setArguments(bundle);
            return savedArticlesByTypeFragment;
        }
    }

    private final void onSavedListItemClicked(int i) {
        Intent intent;
        Object obj = this.savedList.get(i);
        if (obj instanceof DrugCache) {
            markOmnitureData();
            Intent intent2 = new Intent(getActivity(), DrugMonographMainActivity.class);
            DrugCache drugCache = (DrugCache) obj;
            intent2.putExtra(Constants.EXTRA_CONTENT_ID, drugCache.getContentId());
            intent2.putExtra("drugName", drugCache.getDrugName());
            startActivity(intent2);
        } else if (obj instanceof Cache) {
            Cache cache = (Cache) obj;
            String url = cache.getUrl();
            Util.getApplicationVersion(getContext());
            String str = url + com.wbmd.wbmdcommons.utils.Util.attachAndrodSrcTagToUrl(url);
            String str2 = str + com.wbmd.wbmdcommons.utils.Util.addBasicQueryParams(str);
            if (cache.getType() == Cache.NEWS) {
                Context context = getContext();
                if (context != null) {
                    WebviewUtil.Companion companion = WebviewUtil.Companion;
                    Intrinsics.checkNotNullExpressionValue(context, "it");
                    Intrinsics.checkNotNullExpressionValue(str2, "url");
                    companion.launchNews(context, str2, cache.getTitle(), "wv-launch-save", "news", Constants.OMNITURE_CHANNEL_NEWS, cache.getTime(), cache.getImageUrl(), cache.getByline());
                    return;
                }
                return;
            }
            Article article = new Article();
            article.setDate(cache.getTime());
            article.mTitle = cache.getTitle();
            String url2 = cache.getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "cache.url");
            if (url2 != null) {
                article.mLink = StringsKt.trim((CharSequence) url2).toString();
                Context context2 = getContext();
                if (context2 != null) {
                    Intrinsics.checkNotNullExpressionValue(context2, "it");
                    CMEHelper.launchCMEArticle$default(context2, article, false, true, 4, (Object) null);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        } else if (obj instanceof ClinicalReferenceArticle) {
            markOmnitureData();
            if (new ReferenceArticleTOCEnabledManager().getRefTOCData()) {
                intent = new Intent(getActivity(), ClinicalReferenceArticleLandingActivity.class);
            } else {
                intent = new Intent(getActivity(), ClinicalReferenceArticleActivity.class);
            }
            intent.putExtra("article", (Serializable) obj);
            startActivity(intent);
        } else if (obj instanceof CalcArticle) {
            CalcArticle calcArticle = (CalcArticle) obj;
            String calcId = calcArticle.getCalcId();
            Intrinsics.checkNotNullExpressionValue(calcId, "cache.calcId");
            if (StringsKt.startsWith$default(calcId, ContentParser.CALCULATOR, false, 2, (Object) null)) {
                String calcPageNameForOmniture = Util.getCalcPageNameForOmniture(getActivity(), obj);
                Intrinsics.checkNotNullExpressionValue(calcPageNameForOmniture, "Util.getCalcPageNameForOmniture(activity, cache)");
                this.calcPvid = CalcOmnitureHelper.INSTANCE.sendOmnitureCall(getActivity(), "save", Constants.OMNITURE_MLINK_OPEN, calcPageNameForOmniture);
                if (!Util.findMatchingQxCalcForMedscapeCalc(getActivity(), obj, this)) {
                    Activity activity = getActivity();
                    View view = this.rootView;
                    if (view == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    }
                    Util.showNoCalculatorAvailable(activity, view);
                    return;
                }
                return;
            }
            DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(calcArticle.getCalcId());
            if (contentItemForIdentifier != null) {
                ContentItemLaunchManager.getInstance().launchContentItem(contentItemForIdentifier, getActivity(), (LaunchQxCallback) this, (Intent) null);
                return;
            }
            Activity activity2 = getActivity();
            View view2 = this.rootView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            Util.showNoCalculatorAvailable(activity2, view2);
        }
    }

    private final void markOmnitureData() {
        OmnitureManager.get().markModule("save", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        if (bundle != null) {
            bundle.putString("pvid", this.calcPvid);
        }
        Util.openQxItem(getActivity(), dBContentItem, bundle);
    }
}
