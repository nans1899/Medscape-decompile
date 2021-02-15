package com.medscape.android.pillid;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.task.SearchPills;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.medscape.android.view.CacheImageView;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PillSearchResultsPreviewFragment extends Fragment implements SearchPills.SearchPillsListener {
    static final String IMAGE_URL = "https://img.medscape.com/pi/features/drugdirectory/octupdate/%s.jpg";
    static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    ArrayList<PillSearchResultItem> gridArray = new ArrayList<>();
    GridView gridView;
    /* access modifiers changed from: private */
    public boolean isLoading = true;
    /* access modifiers changed from: private */
    public int mBackStackEntryIndex;
    private MedscapeException mException;
    private ArrayAdapter mPillIdSearchResultsAdapter;
    private View mRootView;
    HashMap<FilterType, String> mSelectedFilters;
    /* access modifiers changed from: private */
    public int mTotalSearchResults;

    public void onPillsSearchError(HttpResponseObject httpResponseObject) {
    }

    public void onPillsSearchNoResults() {
    }

    public static PillSearchResultsPreviewFragment newInstance(JSONArray jSONArray, HashMap<FilterType, String> hashMap, int i) {
        PillSearchResultsPreviewFragment pillSearchResultsPreviewFragment = new PillSearchResultsPreviewFragment();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                pillSearchResultsPreviewFragment.getClass();
                pillSearchResultsPreviewFragment.gridArray.add(new PillSearchResultItem(optJSONObject));
            }
        }
        pillSearchResultsPreviewFragment.mSelectedFilters = hashMap;
        pillSearchResultsPreviewFragment.mTotalSearchResults = i;
        return pillSearchResultsPreviewFragment;
    }

    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_pill_identifier_results, viewGroup, false);
        this.mRootView = inflate;
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar supportActionBar = ((PillIdentifierActivity) getActivity()).getSupportActionBar();
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.pill_identifier_results_title) + "</font>"));
        this.gridView = (GridView) getView().findViewById(R.id.gridView1);
        AnonymousClass1 r4 = new ArrayAdapter<PillSearchResultItem>(getActivity(), R.layout.pill_preview_item, this.gridArray) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                RecordHolder recordHolder;
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(Build.VERSION.SDK_INT < 14 ? R.layout.pill_preview_item_sdk10 : R.layout.pill_preview_item, viewGroup, false);
                    recordHolder = new RecordHolder();
                    recordHolder.mImage = (CacheImageView) view.findViewById(R.id.image);
                    recordHolder.mDrugName = (TextView) view.findViewById(R.id.drugName);
                    recordHolder.mGenericName = (TextView) view.findViewById(R.id.genericName);
                    recordHolder.mFormName = (TextView) view.findViewById(R.id.formName);
                    recordHolder.mStrength = (TextView) view.findViewById(R.id.strength);
                    view.setTag(recordHolder);
                } else {
                    recordHolder = (RecordHolder) view.getTag();
                }
                PillSearchResultItem pillSearchResultItem = PillSearchResultsPreviewFragment.this.gridArray.get(i);
                if (pillSearchResultItem.mImageUrl != null) {
                    recordHolder.mImage.configure(pillSearchResultItem.mImageUrl, 0);
                } else {
                    recordHolder.mImage.configure((String) null, R.drawable.drug_image_unavailable);
                }
                recordHolder.mDrugName.setText(pillSearchResultItem.mDrugName);
                recordHolder.mGenericName.setText(pillSearchResultItem.mGenericName);
                recordHolder.mFormName.setText(pillSearchResultItem.mFormNames);
                recordHolder.mStrength.setText(pillSearchResultItem.mStrength);
                recordHolder.item = pillSearchResultItem;
                return view;
            }
        };
        this.mPillIdSearchResultsAdapter = r4;
        this.gridView.setAdapter(r4);
        this.gridView.setRecyclerListener(new AbsListView.RecyclerListener() {
            public void onMovedToScrapHeap(View view) {
                ((CacheImageView) view.findViewById(R.id.image)).reset();
            }
        });
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PillDetailFragment newInstance = PillDetailFragment.newInstance(((RecordHolder) view.getTag()).item);
                PillSearchResultsPreviewFragment pillSearchResultsPreviewFragment = PillSearchResultsPreviewFragment.this;
                int unused = pillSearchResultsPreviewFragment.mBackStackEntryIndex = pillSearchResultsPreviewFragment.getFragmentManager().getBackStackEntryCount();
                FragmentTransaction beginTransaction = PillSearchResultsPreviewFragment.this.getFragmentManager().beginTransaction();
                beginTransaction.add((int) R.id.container, (Fragment) newInstance, "result");
                beginTransaction.setTransition(4096);
                beginTransaction.addToBackStack((String) null);
                beginTransaction.commit();
                PillSearchResultsPreviewFragment.this.dissmissSnackbar();
                boolean unused2 = PillSearchResultsPreviewFragment.this.isLoading = false;
                PillSearchResultsPreviewFragment.this.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (PillSearchResultsPreviewFragment.this.getFragmentManager() != null && PillSearchResultsPreviewFragment.this.getFragmentManager().getBackStackEntryCount() == PillSearchResultsPreviewFragment.this.mBackStackEntryIndex) {
                            PillSearchResultsPreviewFragment.this.setTitle();
                        }
                    }
                });
            }
        });
        this.gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int bufferItemCount = 10;
            private int currentPage = 0;
            private int itemCount = 0;

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i3 < this.itemCount) {
                    this.itemCount = i3;
                    if (i3 == 0) {
                        boolean unused = PillSearchResultsPreviewFragment.this.isLoading = true;
                    }
                }
                if (PillSearchResultsPreviewFragment.this.isLoading && i3 > this.itemCount) {
                    boolean unused2 = PillSearchResultsPreviewFragment.this.isLoading = false;
                    this.itemCount = i3;
                    this.currentPage++;
                }
                if (!PillSearchResultsPreviewFragment.this.isLoading && i3 < PillSearchResultsPreviewFragment.this.mTotalSearchResults && i3 - i2 <= i + this.bufferItemCount) {
                    PillSearchResultsPreviewFragment.this.loadMore(this.currentPage + 1, i3);
                    boolean unused3 = PillSearchResultsPreviewFragment.this.isLoading = true;
                }
            }
        });
    }

    public void sendOmniturePing() {
        Fragment findFragmentById = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        if (findFragmentById != null && (findFragmentById instanceof PillSearchResultsPreviewFragment) && !findFragmentById.isRemoving()) {
            ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", (String) null, OmnitureConstants.PAGE_NAME_RESULTS, (Map<String, Object>) null));
            OmnitureManager.get().trackModule(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "webview-launch", "ref", (Map<String, Object>) null);
        }
    }

    public void onResume() {
        super.onResume();
        sendOmniturePing();
    }

    public void onDestroy() {
        dissmissSnackbar();
        super.onDestroy();
    }

    public void setTitle() {
        FragmentActivity activity = getActivity();
        activity.setTitle(Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.pill_identifier_results_title) + "</font>"));
    }

    public class RecordHolder {
        PillSearchResultItem item;
        TextView mDrugName;
        TextView mFormName;
        TextView mGenericName;
        CacheImageView mImage;
        TextView mStrength;

        public RecordHolder() {
        }
    }

    public class PillSearchResultItem {
        String mColorNames;
        String mDrugName;
        String mFormNames;
        String mGenericName;
        int mId;
        String mImageUrl;
        String mImprint;
        String mManufacturer;
        int mMednameId;
        int mMedscapeDrugId;
        String mScoring;
        String mShapeNames;
        String mStrength;

        public PillSearchResultItem(JSONObject jSONObject) {
            this.mId = jSONObject.optInt("id");
            this.mImageUrl = jSONObject.optString(MessengerShareContentUtility.MEDIA_IMAGE).length() > 0 ? PillSearchResultsPreviewFragment.IMAGE_URL.replace("%s", jSONObject.optString(MessengerShareContentUtility.MEDIA_IMAGE)) : null;
            this.mDrugName = jSONObject.optString("drugName");
            this.mGenericName = jSONObject.optString("genericName");
            this.mStrength = jSONObject.optString("strength");
            this.mManufacturer = jSONObject.optString("manufacturer");
            this.mFormNames = getList(jSONObject.optJSONArray("forms"), "fdbFormName");
            this.mShapeNames = getList(jSONObject.optJSONArray("shapes"), "fdbShapeName");
            this.mColorNames = getList(jSONObject.optJSONArray("colors"), "fdbColorName");
            this.mScoring = jSONObject.optJSONObject("scoring") != null ? jSONObject.optJSONObject("scoring").optString("fdbScoringId") : "";
            this.mImprint = jSONObject.optString("imprint");
            this.mMednameId = jSONObject.optInt("medNameId");
            this.mMedscapeDrugId = jSONObject.optInt("medscapeDrugId");
        }

        private String getList(JSONArray jSONArray, String str) {
            StringBuffer stringBuffer = new StringBuffer();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    stringBuffer.append(jSONArray.optJSONObject(i).optString(str));
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
            }
            return stringBuffer.toString();
        }
    }

    public void loadMore(final int i, final int i2) {
        if (!Util.isOnline(MedscapeApplication.get())) {
            MedscapeException medscapeException = new MedscapeException(MedscapeApplication.get().getResources().getString(R.string.internet_required));
            this.mException = medscapeException;
            medscapeException.showSnackBar(this.mRootView, -2, MedscapeApplication.get().getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    PillSearchResultsPreviewFragment.this.loadMore(i, i2);
                }
            });
            return;
        }
        SearchPills searchPills = new SearchPills(this, getActivity(), false);
        if (!this.mSelectedFilters.isEmpty()) {
            AsyncTaskHelper.execute(threadPoolExecutor, searchPills, SearchPills.createQueryString(this.mSelectedFilters, i));
        }
    }

    public void onPillsSearchComplete(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("drugs");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                this.gridArray.add(new PillSearchResultItem(optJSONObject));
            }
        }
        this.mPillIdSearchResultsAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void dissmissSnackbar() {
        MedscapeException medscapeException = this.mException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
    }
}
