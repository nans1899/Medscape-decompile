package com.medscape.android.pillid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.pillid.PillImprintSearchAdapter;
import com.medscape.android.task.SearchPills;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PillIdentifierMainFragment extends Fragment implements SearchPills.SearchPillsListener, PillImprintSearchAdapter.AutoSearchRetryListener {
    static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    Button mButtonViewResults;
    MenuItem mClearMenuButton;
    FilterListAdapter mFilterAdapter;
    LinkedHashMap<FilterType, ListItemView> mFilterMap;
    AutoCompleteTextView mImprintQueryTextView;
    ArrayList<ListItemView> mList;
    ListView mListView;
    /* access modifiers changed from: private */
    public MedscapeException mMedscapeException;
    private JSONArray mPillIdSearchResultsArray;
    boolean mPillSearchComplete = false;
    ProgressBar mProgressBar;
    private View mRootView;
    SearchPills mSearchPillTask;
    HashMap<FilterType, String> mSelectedFilters = new HashMap<>();
    TextView mTextViewNumOfResults;
    private int mTotalSearchResults;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_pill_identifier, viewGroup, false);
        this.mRootView = inflate;
        return inflate;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initializeList();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.pill_identifier, menu);
        this.mClearMenuButton = menu.findItem(R.id.action_clear);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MedscapeException medscapeException;
        if (this.mProgressBar.getVisibility() == 0 || this.mTextViewNumOfResults.getVisibility() == 0 || ((medscapeException = this.mMedscapeException) != null && medscapeException.getMessage().equals(getResources().getString(R.string.error_connection_required)))) {
            this.mClearMenuButton.setEnabled(true);
        } else {
            this.mClearMenuButton.setEnabled(false);
        }
        super.onPrepareOptionsMenu(menu);
    }

    private void initializeList() {
        ArrayList<ListItemView> arrayList = this.mList;
        if (arrayList != null) {
            arrayList.clear();
            this.mFilterMap.clear();
        } else {
            this.mList = new ArrayList<>();
            this.mFilterMap = new LinkedHashMap<>();
        }
        this.mFilterMap.put(FilterType.IMPRINT, new ListItemView(FilterType.IMPRINT));
        this.mFilterMap.put(FilterType.SHAPE, new ListItemView(FilterType.SHAPE));
        this.mFilterMap.put(FilterType.COLOR, new ListItemView(FilterType.COLOR));
        this.mFilterMap.put(FilterType.FORM, new ListItemView(FilterType.FORM));
        this.mFilterMap.put(FilterType.SCORING, new ListItemView(FilterType.SCORING));
        this.mList.addAll(getList(this.mFilterMap));
    }

    private ArrayList<ListItemView> getList(LinkedHashMap<FilterType, ListItemView> linkedHashMap) {
        ArrayList<ListItemView> arrayList = new ArrayList<>();
        for (Map.Entry next : linkedHashMap.entrySet()) {
            if (next.getKey() != FilterType.IMPRINT || ((ListItemView) next.getValue()).selectedImprintText != null) {
                arrayList.add(next.getValue());
            }
        }
        return arrayList;
    }

    public void setTitle() {
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            activity.setTitle(Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.pill_identifier_filter_main_title) + "</font>"));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle();
        this.mListView = (ListView) getView().findViewById(R.id.list);
        FilterListAdapter filterListAdapter = new FilterListAdapter(getActivity(), R.layout.pillid_list_item, this.mList);
        this.mFilterAdapter = filterListAdapter;
        this.mListView.setAdapter(filterListAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Fragment fragment;
                if (PillIdentifierMainFragment.this.mMedscapeException != null) {
                    PillIdentifierMainFragment.this.mMedscapeException.dismissSnackBar();
                }
                FilterType filterType = ((ViewHolder) view.getTag()).filterType;
                if (FilterType.IMPRINT.equals(filterType)) {
                    PillIdentifierMainFragment.this.mImprintQueryTextView.requestFocus();
                    return;
                }
                if (FilterType.SHAPE.equals(filterType)) {
                    fragment = ShapeFilterFragment.newInstance();
                } else if (FilterType.COLOR.equals(filterType)) {
                    fragment = ColorFilterFragment.newInstance();
                } else if (FilterType.FORM.equals(filterType)) {
                    fragment = FormFilterFragment.newInstance();
                } else {
                    fragment = FilterType.SCORING.equals(filterType) ? ScoringFilterFragment.newInstance() : null;
                }
                AppboyEventsHandler.logDailyEvent(PillIdentifierMainFragment.this.getContext(), AppboyConstants.APPBOY_EVENT_PILL_ID_LOOKUP, PillIdentifierMainFragment.this.getActivity());
                PillIdentifierMainFragment.this.mImprintQueryTextView.clearFocus();
                FragmentTransaction beginTransaction = PillIdentifierMainFragment.this.getFragmentManager().beginTransaction();
                beginTransaction.add((int) R.id.container, fragment);
                beginTransaction.setTransition(4096);
                beginTransaction.addToBackStack((String) null);
                beginTransaction.commit();
            }
        });
        this.mTextViewNumOfResults = (TextView) getView().findViewById(R.id.numOfResults);
        this.mProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        Button button = (Button) getView().findViewById(R.id.viewResults);
        this.mButtonViewResults = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PillIdentifierMainFragment.this.onViewResultsClick(view);
            }
        });
        final PillImprintSearchAdapter pillImprintSearchAdapter = new PillImprintSearchAdapter(getActivity(), this.mListView, this);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) getView().findViewById(R.id.imprintQuery);
        this.mImprintQueryTextView = autoCompleteTextView;
        autoCompleteTextView.requestFocus();
        addMagnifyingGlasstoHint(this.mImprintQueryTextView);
        this.mImprintQueryTextView.setAdapter(pillImprintSearchAdapter);
        this.mImprintQueryTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (PillIdentifierMainFragment.this.mMedscapeException != null) {
                    PillIdentifierMainFragment.this.mMedscapeException.dismissSnackBar();
                }
                PillImprintSearchAdapter pillImprintSearchAdapter = pillImprintSearchAdapter;
                if (pillImprintSearchAdapter != null) {
                    pillImprintSearchAdapter.dismissExceptionSnackbar();
                }
                String str = (String) adapterView.getItemAtPosition(i);
                HashMap hashMap = new HashMap();
                hashMap.put("wapp.querytext", str);
                OmnitureManager omnitureManager = OmnitureManager.get();
                omnitureManager.markModule("impr-results", "" + (i + 1), hashMap);
                PillIdentifierMainFragment.this.updateSelectedFilter(FilterType.IMPRINT, str, str);
                PillIdentifierMainFragment.this.mImprintQueryTextView.setText("");
                PillIdentifierMainFragment.this.mImprintQueryTextView.clearFocus();
            }
        });
        this.mImprintQueryTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                InputMethodManager inputMethodManager;
                if (!z && (inputMethodManager = (InputMethodManager) PillIdentifierMainFragment.this.getActivity().getSystemService("input_method")) != null) {
                    inputMethodManager.hideSoftInputFromWindow(PillIdentifierMainFragment.this.mImprintQueryTextView.getWindowToken(), 0);
                }
            }
        });
        this.mImprintQueryTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((BaseActivity) PillIdentifierMainFragment.this.getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(PillIdentifierMainFragment.this.getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "search", (String) null, (String) null, (Map<String, Object>) null));
                AppboyEventsHandler.logDailyEvent(PillIdentifierMainFragment.this.getContext(), AppboyConstants.APPBOY_EVENT_PILL_ID_LOOKUP, PillIdentifierMainFragment.this.getActivity());
            }
        });
        getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int i;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                PillIdentifierMainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int ceil = (int) Math.ceil((double) (displayMetrics.density * 40.0f));
                if (((double) displayMetrics.density) > 1.5d) {
                    i = PillIdentifierMainFragment.this.mListView.getHeight() + ceil;
                } else {
                    i = PillIdentifierMainFragment.this.mListView.getHeight();
                }
                PillIdentifierMainFragment.this.mImprintQueryTextView.setDropDownHeight(i);
            }
        });
        setHasOptionsMenu(true);
    }

    private void addMagnifyingGlasstoHint(AutoCompleteTextView autoCompleteTextView) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.append(getString(R.string.pill_identifier_imprint_hint));
            Drawable drawable = getResources().getDrawable(R.drawable.action_search);
            int textSize = (int) autoCompleteTextView.getTextSize();
            drawable.setBounds(0, 0, textSize, textSize);
            spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
            autoCompleteTextView.setHint(spannableStringBuilder);
        } catch (Exception unused) {
            autoCompleteTextView.setHint(getString(R.string.pill_identifier_imprint_hint));
        }
    }

    public void sendOmnitureMetric() {
        Fragment findFragmentById;
        if (getActivity() != null && (findFragmentById = getActivity().getSupportFragmentManager().findFragmentById(R.id.container)) != null && (findFragmentById instanceof PillIdentifierMainFragment) && !findFragmentById.isRemoving()) {
            ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", (String) null, (String) null, (Map<String, Object>) null));
        }
    }

    public void onResume() {
        super.onResume();
        sendOmnitureMetric();
    }

    public void onAutoSearchRetry() {
        if (isAdded()) {
            performAutoSuggestions();
        }
    }

    public class ViewHolder {
        ImageView clearFilterButton;
        FilterType filterType;
        ImageView icon;
        TextView selectedImprintText;
        TextView text;
        TextView tvTitle;

        public ViewHolder() {
        }
    }

    public class ListItemView {
        FilterType mFilterType;
        String selectedColorHex;
        String selectedImprintText;
        int selectedResourceId;

        public ListItemView(FilterType filterType) {
            this.mFilterType = filterType;
        }
    }

    public static PillIdentifierMainFragment newInstance() {
        return new PillIdentifierMainFragment();
    }

    public void updateSelectedFilter(FilterType filterType, String str, String str2) {
        resetResultButtons();
        int i = 0;
        if (FilterType.SHAPE.equals(filterType)) {
            ListItemView listItemView = this.mFilterMap.get(filterType);
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            listItemView.selectedResourceId = i;
        } else if (FilterType.COLOR.equals(filterType)) {
            this.mFilterMap.get(filterType).selectedColorHex = str;
        } else if (FilterType.FORM.equals(filterType)) {
            ListItemView listItemView2 = this.mFilterMap.get(filterType);
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            listItemView2.selectedResourceId = i;
        } else if (FilterType.SCORING.equals(filterType)) {
            ListItemView listItemView3 = this.mFilterMap.get(filterType);
            if (str != null) {
                i = Integer.valueOf(str).intValue();
            }
            listItemView3.selectedResourceId = i;
        } else if (FilterType.IMPRINT.equals(filterType)) {
            this.mFilterMap.get(filterType).selectedImprintText = str2;
        }
        this.mList.clear();
        this.mList.addAll(getList(this.mFilterMap));
        if (str2 != null) {
            this.mSelectedFilters.put(filterType, str2);
        } else {
            this.mSelectedFilters.remove(filterType);
        }
        searchForPills();
        this.mFilterAdapter.notifyDataSetChanged();
        ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, "pill-identifier", "view", (String) null, (String) null, (Map<String, Object>) null));
    }

    /* access modifiers changed from: private */
    public void searchForPills() {
        SearchPills searchPills = new SearchPills(this, getActivity(), false);
        if (!this.mSelectedFilters.isEmpty()) {
            AsyncTaskHelper.executeReplacement(threadPoolExecutor, this.mSearchPillTask, searchPills, SearchPills.createQueryString(this.mSelectedFilters, 0));
            this.mSearchPillTask = searchPills;
            this.mProgressBar.setVisibility(0);
            this.mClearMenuButton.setEnabled(true);
            return;
        }
        AsyncTaskHelper.cancel(threadPoolExecutor, this.mSearchPillTask);
        this.mProgressBar.setVisibility(8);
        this.mClearMenuButton.setEnabled(false);
    }

    public void onClearClick(View view) {
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        AsyncTaskHelper.cancel(threadPoolExecutor, this.mSearchPillTask);
        initializeList();
        this.mSelectedFilters.clear();
        this.mFilterAdapter.notifyDataSetChanged();
        resetResultButtons();
        this.mProgressBar.setVisibility(8);
        this.mClearMenuButton.setEnabled(false);
    }

    public void onClearFilterClick(View view) {
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        AsyncTaskHelper.cancel(threadPoolExecutor, this.mSearchPillTask);
        updateSelectedFilter((FilterType) view.getTag(), (String) null, (String) null);
    }

    public void onViewResultsClick(View view) {
        this.mImprintQueryTextView.clearFocus();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add((int) R.id.container, (Fragment) PillSearchResultsPreviewFragment.newInstance(this.mPillIdSearchResultsArray, this.mSelectedFilters, this.mTotalSearchResults));
        beginTransaction.setTransition(4096);
        beginTransaction.addToBackStack((String) null);
        beginTransaction.commit();
    }

    public void resetResultButtons() {
        this.mPillSearchComplete = false;
        TextView textView = this.mTextViewNumOfResults;
        if (textView != null) {
            textView.setVisibility(8);
        }
        Button button = this.mButtonViewResults;
        if (button != null) {
            button.setVisibility(4);
        }
    }

    public class FilterListAdapter extends ArrayAdapter<ListItemView> {
        Context context;
        ArrayList<ListItemView> items;
        int layoutResourceId;

        public FilterListAdapter(Context context2, int i, ArrayList<ListItemView> arrayList) {
            super(context2, i, arrayList);
            this.layoutResourceId = i;
            this.context = context2;
            this.items = arrayList;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.pillid_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
                viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
                viewHolder.selectedImprintText = (TextView) view.findViewById(R.id.selection);
                viewHolder.text = (TextView) view.findViewById(R.id.clearText);
                viewHolder.clearFilterButton = (ImageView) view.findViewById(R.id.clearFilterButton);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ListItemView item = getItem(i);
            viewHolder.selectedImprintText.setVisibility(8);
            viewHolder.icon.setVisibility(8);
            viewHolder.text.setVisibility(8);
            viewHolder.clearFilterButton.setVisibility(4);
            viewHolder.clearFilterButton.setTag(item.mFilterType);
            viewHolder.clearFilterButton.setContentDescription(PillIdentifierMainFragment.this.getString(R.string.pill_identifier_clear_filter_content_desc, item.mFilterType.getName()));
            viewHolder.icon.clearColorFilter();
            if (item.mFilterType.equals(FilterType.SHAPE) || item.mFilterType.equals(FilterType.FORM) || item.mFilterType.equals(FilterType.SCORING)) {
                if (item.selectedResourceId != 0) {
                    viewHolder.icon.setImageResource(item.selectedResourceId);
                    viewHolder.icon.setVisibility(0);
                    viewHolder.clearFilterButton.setVisibility(0);
                }
            } else if (item.mFilterType.equals(FilterType.COLOR)) {
                if (item.selectedColorHex != null) {
                    if (item.selectedColorHex.equals("#ffffff")) {
                        viewHolder.icon.clearColorFilter();
                        viewHolder.icon.setImageResource(R.drawable.pill_color_selected_ring);
                    } else if (item.selectedColorHex.equals("#fbfbfb")) {
                        viewHolder.icon.clearColorFilter();
                        viewHolder.icon.setImageResource(R.drawable.pill_color_selected_ring);
                        viewHolder.text.setVisibility(0);
                    } else {
                        viewHolder.icon.clearColorFilter();
                        viewHolder.icon.setImageResource(R.drawable.pill_color_selected_shape);
                        viewHolder.icon.getDrawable().setColorFilter(Color.parseColor(item.selectedColorHex), PorterDuff.Mode.SRC_ATOP);
                    }
                    viewHolder.icon.setVisibility(0);
                    viewHolder.clearFilterButton.setVisibility(0);
                }
            } else if (item.mFilterType.equals(FilterType.IMPRINT) && item.selectedImprintText != null && !item.selectedImprintText.isEmpty()) {
                viewHolder.selectedImprintText.setText(item.selectedImprintText);
                viewHolder.selectedImprintText.setVisibility(0);
                viewHolder.clearFilterButton.setVisibility(0);
            }
            viewHolder.tvTitle.setText(item.mFilterType.getName());
            viewHolder.filterType = item.mFilterType;
            return view;
        }

        public int getCount() {
            return this.items.size();
        }

        public ListItemView getItem(int i) {
            return this.items.get(i);
        }
    }

    public void onPillsSearchComplete(JSONObject jSONObject) throws JSONException {
        this.mPillSearchComplete = true;
        this.mTotalSearchResults = jSONObject.getInt("totalCount");
        this.mPillIdSearchResultsArray = jSONObject.getJSONArray("drugs");
        if (this.mTotalSearchResults == 1) {
            TextView textView = this.mTextViewNumOfResults;
            textView.setText(this.mTotalSearchResults + " Result Found");
        } else {
            TextView textView2 = this.mTextViewNumOfResults;
            textView2.setText(this.mTotalSearchResults + " Results Found");
        }
        this.mTextViewNumOfResults.setVisibility(0);
        if (this.mTotalSearchResults > 0) {
            this.mButtonViewResults.setVisibility(0);
        } else {
            onPillsSearchNoResults();
        }
        this.mProgressBar.setVisibility(8);
    }

    public void onPillsSearchNoResults() {
        this.mTotalSearchResults = 0;
        this.mPillSearchComplete = true;
        this.mProgressBar.setVisibility(8);
        this.mButtonViewResults.setVisibility(4);
    }

    public void onPillsSearchError(HttpResponseObject httpResponseObject) {
        this.mPillSearchComplete = true;
        this.mProgressBar.setVisibility(8);
        this.mTextViewNumOfResults.setVisibility(8);
        this.mButtonViewResults.setVisibility(8);
        if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
            this.mMedscapeException = new MedscapeException(httpResponseObject.getResponseErrorMsg());
            if (isAdded() && getActivity() != null) {
                this.mMedscapeException.showSnackBar(this.mRootView, -2, getString(R.string.retry), new View.OnClickListener() {
                    public void onClick(View view) {
                        PillIdentifierMainFragment.this.searchForPills();
                    }
                });
            }
        }
    }

    public void performAutoSuggestions() {
        this.mImprintQueryTextView.postDelayed(new Runnable() {
            public void run() {
                if (PillIdentifierMainFragment.this.isAdded() && PillIdentifierMainFragment.this.getActivity() != null && Util.isOnline(PillIdentifierMainFragment.this.getActivity())) {
                    String obj = PillIdentifierMainFragment.this.mImprintQueryTextView.getText().toString();
                    if (!Extensions.IsNullOrEmpty(obj)) {
                        PillIdentifierMainFragment.this.mImprintQueryTextView.setText("");
                        PillIdentifierMainFragment.this.mImprintQueryTextView.setText(obj);
                        PillIdentifierMainFragment.this.mImprintQueryTextView.setSelection(obj.length());
                        PillIdentifierMainFragment.this.mImprintQueryTextView.requestFocusFromTouch();
                        ((InputMethodManager) PillIdentifierMainFragment.this.getActivity().getSystemService("input_method")).showSoftInput(PillIdentifierMainFragment.this.mImprintQueryTextView, 0);
                        PillIdentifierMainFragment.this.mImprintQueryTextView.showDropDown();
                    }
                }
            }
        }, 200);
    }
}
