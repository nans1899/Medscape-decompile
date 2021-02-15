package com.medscape.android.consult.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BottomNavBaseActivity;
import com.medscape.android.consult.adapters.ConsultTimelineFilterAdapter;
import com.medscape.android.consult.fragments.ConsultTimelineFragment;
import com.medscape.android.consult.interfaces.IMedStudentNotificationListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.managers.ConsultFilterManager;
import com.medscape.android.consult.managers.ConsultMedStudentFlagsApiManager;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultTimeLineFilterItem;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.DebugKt;
import org.json.JSONObject;

public class ConsultTimelineActivity extends BottomNavBaseActivity implements OnAdListener, IMedStudentNotificationListener {
    private static final String CONSULT_FEATURE_TAG = "Featured";
    /* access modifiers changed from: private */
    public String displayFilter = ConsultConstants.PHYSICIAN_FEED_FILTER;
    /* access modifiers changed from: private */
    public String globalTag = null;
    /* access modifiers changed from: private */
    public SwitchCompat globalToggle;
    public String groupTitle;
    /* access modifiers changed from: private */
    public boolean isGlobalFilterChanged = false;
    /* access modifiers changed from: private */
    public boolean isMedStudentFilterChanged = false;
    private AdditionalConfigReceiver mAdditionalConfigReceiver;
    private CurrentUserReceiver mCurrentUserReceiver;
    /* access modifiers changed from: private */
    public int mCurrentlyExpandedGroup = -1;
    private int mCurrentlySelectedConsultFeedType = Constants.CONSULT_FEEDTYPE_ALL;
    private String mCurrentlySelectedTag;
    private LinkedList<ConsultTimeLineFilterItem> mFilters = new LinkedList<>();
    /* access modifiers changed from: private */
    public LinkedList<ConsultTimeLineFilterItem> mHardcodedFilters = new LinkedList<>();
    private boolean mHasAdCallFailed = false;
    private boolean mHasDeeplink;
    private View mHeaderLayout;
    /* access modifiers changed from: private */
    public MenuItem mProfileMenuItem;
    private View mRootView;
    private int mScreenWidth;
    /* access modifiers changed from: private */
    public ConsultTimelineFilterAdapter mTimeLineFilterAdapter;
    /* access modifiers changed from: private */
    public PopupWindow mTimelineNavMenu;
    /* access modifiers changed from: private */
    public SwitchCompat medStudentPostSwitch;
    private String pclass = "consult";
    private HashMap<String, String> screenSpecificMap = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean shouldReloadFeed = false;

    public void isAdExpandedByUser(boolean z) {
    }

    public void onAdAvailable() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.skipForceUpAutoRegister = true;
        super.onCreate(bundle);
        setContentView(R.layout.activity_fragmentcontainer_no_actionbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setLogo((Drawable) null);
        }
        this.mRootView = findViewById(R.id.content_frame);
        this.mScreenWidth = ((WindowManager) getSystemService("window")).getDefaultDisplay().getWidth();
        generateHardCodedFilters();
        handleExtras();
        setUpConsultTimelineScreen();
    }

    private void handleExtras() {
        Bundle extras;
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            String string = extras.getString(Constants.EXTRA_CONSULT_CLICKED_TAG);
            if (StringUtil.isNotEmpty(string)) {
                this.mCurrentlySelectedTag = string;
                this.mCurrentlySelectedConsultFeedType = Constants.CONSULT_FEEDTYPE_TAG;
                return;
            }
            String stringExtra = intent.getStringExtra(Constants.EXTRA_CONSULT_DEEPLINK_URL);
            if (!StringUtil.isNotEmpty(stringExtra)) {
                stringExtra = intent.getDataString();
            }
            this.mHasDeeplink = handleDeeplink(stringExtra);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_consult_timeline, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        if (findItem != null) {
            findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (ConsultTimelineActivity.this.getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
                        ConsultTimelineActivity.this.toggleNavigationDrawer(true);
                        return false;
                    }
                    OmnitureManager.get().trackModule(ConsultTimelineActivity.this, "consult", "consult-search", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                    ConsultTimelineActivity.this.showSearchActionBar();
                    return false;
                }
            });
        }
        MenuItem findItem2 = menu.findItem(R.id.consult_profile);
        this.mProfileMenuItem = findItem2;
        if (findItem2 != null) {
            ConsultUser currentUser = ConsultDataManager.getInstance(this, this).getCurrentUser();
            if (this.mProfileMenuItem != null) {
                if (currentUser == null || !StringUtil.isNotEmpty(currentUser.getUserId())) {
                    this.mProfileMenuItem.setEnabled(false);
                } else {
                    this.mProfileMenuItem.setEnabled(true);
                }
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    private void setUpActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setUpHeader(toolbar);
            setupDropdown();
            setFilterItemsForDropDown();
            setSupportActionBar(toolbar);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setUpHeader(Toolbar toolbar) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.consult_timeline_filter, toolbar, false);
        this.mHeaderLayout = inflate;
        inflate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ConsultTimelineActivity.this.getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
                    ConsultTimelineActivity.this.toggleNavigationDrawer(true);
                } else {
                    ConsultTimelineActivity.this.toggleNavigationMenu(view);
                }
            }
        });
        if (StringUtil.isNotEmpty(this.mCurrentlySelectedTag)) {
            ((TextView) this.mHeaderLayout.findViewById(R.id.selectedSection)).setText(getStringForFeedType(this.mCurrentlySelectedConsultFeedType, this.mCurrentlySelectedTag));
        } else {
            ((TextView) this.mHeaderLayout.findViewById(R.id.selectedSection)).setText(getStringForFeedType(Constants.CONSULT_FEEDTYPE_ALL, ""));
        }
        setProfileIcon((ImageView) this.mHeaderLayout.findViewById(R.id.profile));
        if (getProfileIcon() != null) {
            getProfileIcon().setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ConsultTimelineActivity.this.lambda$setUpHeader$0$ConsultTimelineActivity(view);
                }
            });
        }
        toolbar.addView(this.mHeaderLayout, new ActionBar.LayoutParams(-1, -2));
    }

    public /* synthetic */ void lambda$setUpHeader$0$ConsultTimelineActivity(View view) {
        toggleNavigationDrawer(true);
    }

    public void toggleNavigationMenu(View view) {
        if (this.mTimelineNavMenu.isShowing()) {
            this.mTimelineNavMenu.dismiss();
            return;
        }
        OmnitureManager.get().trackModule(this, "consult", "consult-menu", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        this.mTimelineNavMenu.setWindowLayoutMode(100, -2);
        this.mTimelineNavMenu.setWidth(this.mScreenWidth);
        this.mTimelineNavMenu.setBackgroundDrawable(new ColorDrawable(0));
        this.mTimelineNavMenu.showAsDropDown(view, (int) Util.dpToPixel(this, 0), -25);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        PopupWindow popupWindow;
        if (motionEvent.getAction() == 0 && (popupWindow = this.mTimelineNavMenu) != null && popupWindow.isShowing()) {
            int[] iArr = new int[2];
            this.mTimelineNavMenu.getContentView().getLocationOnScreen(iArr);
            Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.mTimelineNavMenu.getContentView().getWidth(), iArr[1] + this.mTimelineNavMenu.getContentView().getHeight());
            this.mHeaderLayout.getLocationOnScreen(iArr);
            Rect rect2 = new Rect(iArr[0], iArr[1], iArr[0] + this.mHeaderLayout.getWidth(), iArr[1] + this.mHeaderLayout.getHeight());
            if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !rect2.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                this.mTimelineNavMenu.dismiss();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void setupDropdown() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundResource(R.drawable.shadow_consult_dropdown);
        linearLayout.setOrientation(1);
        View createMedicalStudentFilter = createMedicalStudentFilter();
        if (createMedicalStudentFilter != null) {
            linearLayout.addView(createMedicalStudentFilter);
        }
        View createGlobalToggle = createGlobalToggle();
        if (createGlobalToggle != null) {
            linearLayout.addView(createGlobalToggle);
        }
        final ExpandableListView expandableListView = new ExpandableListView(this);
        expandableListView.setGroupIndicator(getResources().getDrawable(17170445));
        ConsultTimelineFilterAdapter consultTimelineFilterAdapter = new ConsultTimelineFilterAdapter(this);
        this.mTimeLineFilterAdapter = consultTimelineFilterAdapter;
        expandableListView.setAdapter(consultTimelineFilterAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                if (ConsultTimelineActivity.this.mTimeLineFilterAdapter.getChildrenCount(i) <= 0) {
                    ConsultTimelineActivity.this.onSectionSelected(i, 0);
                    boolean unused = ConsultTimelineActivity.this.shouldReloadFeed = false;
                    if (!ConsultTimelineActivity.this.mTimelineNavMenu.isShowing()) {
                        return true;
                    }
                    ConsultTimelineActivity.this.mTimelineNavMenu.dismiss();
                    return true;
                } else if (expandableListView.isGroupExpanded(i)) {
                    expandableListView.collapseGroup(i);
                    int unused2 = ConsultTimelineActivity.this.mCurrentlyExpandedGroup = -1;
                    return true;
                } else {
                    if (ConsultTimelineActivity.this.mCurrentlyExpandedGroup >= 0) {
                        expandableListView.collapseGroup(ConsultTimelineActivity.this.mCurrentlyExpandedGroup);
                    }
                    expandableListView.expandGroup(i);
                    int unused3 = ConsultTimelineActivity.this.mCurrentlyExpandedGroup = i;
                    return true;
                }
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                expandableListView.collapseGroup(ConsultTimelineActivity.this.mHardcodedFilters.size() - 1);
                ConsultTimelineActivity.this.onSectionSelected(i, i2);
                boolean unused = ConsultTimelineActivity.this.shouldReloadFeed = false;
                if (ConsultTimelineActivity.this.mTimelineNavMenu.isShowing()) {
                    ConsultTimelineActivity.this.mTimelineNavMenu.dismiss();
                }
                return true;
            }
        });
        linearLayout.addView(expandableListView);
        PopupWindow popupWindow = new PopupWindow(linearLayout);
        this.mTimelineNavMenu = popupWindow;
        popupWindow.setFocusable(true);
        this.mTimelineNavMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                if (ConsultTimelineActivity.this.isMedStudentFilterChanged || ConsultTimelineActivity.this.isGlobalFilterChanged) {
                    if (ConsultTimelineActivity.this.shouldReloadFeed) {
                        boolean unused = ConsultTimelineActivity.this.shouldReloadFeed = false;
                        ConsultTimelineActivity.this.reloadCurrentFeed();
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (ConsultTimelineActivity.this.medStudentPostSwitch != null) {
                            if (ConsultTimelineActivity.this.isMedStudentFilterChanged) {
                                if (ConsultTimelineActivity.this.medStudentPostSwitch.isChecked()) {
                                    jSONObject.put(ConsultConstants.MED_STUDENTS_FILTER_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_YES);
                                } else {
                                    jSONObject.put(ConsultConstants.MED_STUDENTS_FILTER_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                }
                            }
                        }
                        if (ConsultTimelineActivity.this.globalToggle != null && ConsultTimelineActivity.this.isGlobalFilterChanged) {
                            if (ConsultTimelineActivity.this.globalToggle.isChecked()) {
                                jSONObject.put(ConsultConstants.GLOBAL_POSTS_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                            } else {
                                jSONObject.put(ConsultConstants.GLOBAL_POSTS_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_YES);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    boolean unused2 = ConsultTimelineActivity.this.isMedStudentFilterChanged = false;
                    boolean unused3 = ConsultTimelineActivity.this.isGlobalFilterChanged = false;
                    ConsultMedStudentFlagsApiManager.saveMedStudentFlag(ConsultTimelineActivity.this, jSONObject);
                }
            }
        });
    }

    private View createMedicalStudentFilter() {
        if (!Settings.singleton(this).getSetting("wbmd_professionId", "").equals(UserProfile.MEDICAL_LABORATORY_ID)) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.medical_students_filter_layout, (ViewGroup) null, false);
            if (inflate != null) {
                SwitchCompat switchCompat = (SwitchCompat) inflate.findViewById(R.id.consult_medical_students_filter);
                this.medStudentPostSwitch = switchCompat;
                switchCompat.setChecked(ConsultFilterManager.getShowStudentStatus(this));
                if (ConsultFilterManager.getShowStudentStatus(this)) {
                    this.displayFilter = null;
                }
                this.medStudentPostSwitch.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean unused = ConsultTimelineActivity.this.shouldReloadFeed = true;
                        ConsultTimelineActivity consultTimelineActivity = ConsultTimelineActivity.this;
                        boolean unused2 = consultTimelineActivity.isMedStudentFilterChanged = true ^ consultTimelineActivity.isMedStudentFilterChanged;
                        if (ConsultTimelineActivity.this.medStudentPostSwitch.isChecked()) {
                            String unused3 = ConsultTimelineActivity.this.displayFilter = null;
                            OmnitureManager.get().trackModule(ConsultTimelineActivity.this, "consult", "consult-ms-tggle", DebugKt.DEBUG_PROPERTY_VALUE_ON, (Map<String, Object>) null);
                            return;
                        }
                        String unused4 = ConsultTimelineActivity.this.displayFilter = ConsultConstants.PHYSICIAN_FEED_FILTER;
                        OmnitureManager.get().trackModule(ConsultTimelineActivity.this, "consult", "consult-ms-tggle", DebugKt.DEBUG_PROPERTY_VALUE_OFF, (Map<String, Object>) null);
                    }
                });
            }
            return inflate;
        }
        this.displayFilter = null;
        return null;
    }

    private View createGlobalToggle() {
        if (Settings.singleton(this).getSetting(Constants.USER_COUNTRY_CODE, "").equals("us")) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.consult_global_posts_toggle, (ViewGroup) null, false);
            if (inflate != null) {
                SwitchCompat switchCompat = (SwitchCompat) inflate.findViewById(R.id.consult_global_post_filter);
                this.globalToggle = switchCompat;
                switchCompat.setChecked(ConsultFilterManager.getGlobalToggleStatus(this));
                if (ConsultFilterManager.getGlobalToggleStatus(this)) {
                    this.globalTag = null;
                } else {
                    this.globalTag = ConsultConstants.GLOBAL_FEED_FILTER;
                }
                this.globalToggle.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean unused = ConsultTimelineActivity.this.shouldReloadFeed = true;
                        ConsultTimelineActivity consultTimelineActivity = ConsultTimelineActivity.this;
                        boolean unused2 = consultTimelineActivity.isGlobalFilterChanged = true ^ consultTimelineActivity.isGlobalFilterChanged;
                        if (ConsultTimelineActivity.this.globalToggle.isChecked()) {
                            String unused3 = ConsultTimelineActivity.this.globalTag = null;
                            OmnitureManager.get().trackModule(ConsultTimelineActivity.this, "consult", "consult-gp-tggle", DebugKt.DEBUG_PROPERTY_VALUE_ON, (Map<String, Object>) null);
                            return;
                        }
                        String unused4 = ConsultTimelineActivity.this.globalTag = ConsultConstants.GLOBAL_FEED_FILTER;
                        OmnitureManager.get().trackModule(ConsultTimelineActivity.this, "consult", "consult-gp-tggle", DebugKt.DEBUG_PROPERTY_VALUE_OFF, (Map<String, Object>) null);
                    }
                });
            }
            return inflate;
        }
        this.globalTag = null;
        return null;
    }

    /* access modifiers changed from: private */
    public void reloadCurrentFeed() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
            if (findFragmentByTag instanceof ConsultTimelineFragment) {
                ((ConsultTimelineFragment) findFragmentByTag).loadFeedForNewSection(ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
            }
        }
    }

    public void onSectionSelected(int i, int i2) {
        ConsultTimeLineFilterItem consultTimeLineFilterItem = this.mTimeLineFilterAdapter.getFilterItems().get(i);
        if (consultTimeLineFilterItem != null && consultTimeLineFilterItem.getChildFilters() != null && consultTimeLineFilterItem.getChildFilters().size() > 0 && consultTimeLineFilterItem.getChildFilters().get(i2) != null) {
            ((TextView) this.mHeaderLayout.findViewById(R.id.selectedSection)).setText(consultTimeLineFilterItem.getChildFilters().get(i2).getTitle());
        } else if (consultTimeLineFilterItem != null) {
            ((TextView) this.mHeaderLayout.findViewById(R.id.selectedSection)).setText(consultTimeLineFilterItem.getTitle());
        }
        this.mTimeLineFilterAdapter.notifyDataSetChanged();
        updateFragmentDataForType(i, i2);
    }

    private void updateFragmentDataForType(int i, int i2) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
            if (findFragmentByTag instanceof ConsultTimelineFragment) {
                ConsultTimelineFragment consultTimelineFragment = (ConsultTimelineFragment) findFragmentByTag;
                ConsultTimeLineFilterItem consultTimeLineFilterItem = this.mFilters.get(i);
                if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase(Constants.CONSULT_ALL)) {
                    consultTimelineFragment.loadFeedForNewSection(Constants.CONSULT_FEEDTYPE_ALL, (String) null, ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
                } else if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase("featured")) {
                    consultTimelineFragment.loadFeedForNewSection(3007, CONSULT_FEATURE_TAG, ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
                } else if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase(Constants.CONSULT_TOP_POSTS)) {
                    consultTimelineFragment.loadFeedForNewSection(Constants.CONSULT_FEEDTYPE_TOP_POSTS, (String) null, ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
                } else if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase(Constants.CONSULT_MY_NETWORK)) {
                    consultTimelineFragment.loadFeedForNewSection(3000, (String) null, (String) null);
                } else if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase(Constants.CONSULT_FOLLOWED_POSTS)) {
                    consultTimelineFragment.loadFeedForNewSection(Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS, (String) null, (String) null);
                } else if (consultTimeLineFilterItem.getFilterType().equalsIgnoreCase(Constants.CONSULT_MY_POSTS)) {
                    consultTimelineFragment.loadFeedForNewSection(3002, (String) null, (String) null);
                } else if (consultTimeLineFilterItem.getChildFilters() != null && consultTimeLineFilterItem.getChildFilters().size() > i2 && consultTimeLineFilterItem.getChildFilters().get(i2) != null) {
                    String tag = consultTimeLineFilterItem.getChildFilters().get(i2).getTag();
                    if (StringUtil.isNullOrEmpty(tag)) {
                        tag = consultTimeLineFilterItem.getChildFilters().get(i2).getTitle();
                    }
                    this.groupTitle = consultTimeLineFilterItem.getTitle();
                    consultTimelineFragment.loadFeedForNewSection(Constants.CONSULT_FEEDTYPE_TAG, tag, ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
                } else if (consultTimeLineFilterItem.getTag() != null) {
                    if (StringUtil.isNullOrEmpty(consultTimeLineFilterItem.getTag())) {
                        consultTimeLineFilterItem.setTag(consultTimeLineFilterItem.getTitle());
                    }
                    consultTimelineFragment.loadFeedForNewSection(Constants.CONSULT_FEEDTYPE_TAG, consultTimeLineFilterItem.getTag(), ConsultFilterManager.getFilters(this.displayFilter, this.globalTag));
                }
                consultTimelineFragment.setConsultTitle(consultTimeLineFilterItem.getTitle());
                consultTimelineFragment.requestAd();
            }
        }
    }

    /* access modifiers changed from: private */
    public void setFilterItemsForDropDown() {
        if (this.mTimeLineFilterAdapter != null) {
            this.mFilters = new LinkedList<>();
            List<ConsultTimeLineFilterItem> topLevelFeedsFilterList = ConsultDataManager.getInstance(this, this).getTopLevelFeedsFilterList();
            if (topLevelFeedsFilterList == null || topLevelFeedsFilterList.size() <= 0) {
                LinkedList<ConsultTimeLineFilterItem> linkedList = this.mHardcodedFilters;
                if (linkedList != null && linkedList.size() > 0) {
                    this.mFilters.addAll(this.mHardcodedFilters);
                }
            } else {
                this.mFilters.addAll(topLevelFeedsFilterList);
            }
            this.mTimeLineFilterAdapter.setData(this.mFilters);
        }
    }

    private void initializeFragment() {
        if (getSupportFragmentManager() != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            ConsultTimelineFragment newInstance = ConsultTimelineFragment.newInstance();
            newInstance.setMedStudentNotificationListener(this);
            Intent intent = getIntent();
            if (!(intent == null || intent.getExtras() == null)) {
                Bundle extras = intent.getExtras();
                if (this.mHasDeeplink) {
                    extras.putString(Constants.EXTRA_CONSULT_DEEPLINK_ACTION, this.mCurrentlySelectedTag);
                    extras.putInt(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE, this.mCurrentlySelectedConsultFeedType);
                }
                newInstance.setArguments(extras);
            }
            beginTransaction.add((int) R.id.content_frame, (Fragment) newInstance, Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mCurrentUserReceiver == null) {
            this.mCurrentUserReceiver = new CurrentUserReceiver();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mCurrentUserReceiver, new IntentFilter(Constants.CONSULT_CURRENT_USER_UPDATEACTION));
        if (this.mAdditionalConfigReceiver == null) {
            this.mAdditionalConfigReceiver = new AdditionalConfigReceiver();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mAdditionalConfigReceiver, new IntentFilter(Constants.CONSULT_ADDITIONAL_CONFIG_UPDATEACTION));
        invalidateOptionsMenu();
        AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_CONSULT_VIEWED, this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mHasAdCallFailed = false;
        if (this.mCurrentUserReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mCurrentUserReceiver);
        }
        if (this.mAdditionalConfigReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mAdditionalConfigReceiver);
        }
    }

    /* access modifiers changed from: private */
    public void showSearchActionBar() {
        Intent intent = new Intent(this, ConsultSearchActivity.class);
        intent.setAction("android.intent.action.SEARCH");
        startActivity(intent);
    }

    public void onShowInFeedClicked() {
        OmnitureManager.get().trackModule(this, "consult", "consult-ms-msg", "show", (Map<String, Object>) null);
        SwitchCompat switchCompat = this.medStudentPostSwitch;
        if (switchCompat != null && !switchCompat.isChecked()) {
            this.medStudentPostSwitch.setChecked(true);
            this.displayFilter = null;
            reloadCurrentFeed();
        }
    }

    public void onHideClicked() {
        OmnitureManager.get().trackModule(this, "consult", "consult-ms-msg", MessengerShareContentUtility.SHARE_BUTTON_HIDE, (Map<String, Object>) null);
        SwitchCompat switchCompat = this.medStudentPostSwitch;
        if (switchCompat != null && switchCompat.isChecked()) {
            this.medStudentPostSwitch.setChecked(false);
            this.displayFilter = ConsultConstants.PHYSICIAN_FEED_FILTER;
            reloadCurrentFeed();
        }
    }

    private class CurrentUserReceiver extends BroadcastReceiver {
        private CurrentUserReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (ConsultTimelineActivity.this.mProfileMenuItem != null) {
                ConsultTimelineActivity.this.mProfileMenuItem.setEnabled(true);
            }
        }
    }

    private class AdditionalConfigReceiver extends BroadcastReceiver {
        private AdditionalConfigReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            ConsultTimelineActivity.this.setFilterItemsForDropDown();
        }
    }

    public void setScreenSpecificMap() {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.consult_inline_ad_pos));
        this.screenSpecificMap.put("pc", this.pclass);
    }

    public void onAdNotAvilable() {
        this.mHasAdCallFailed = true;
    }

    /* access modifiers changed from: package-private */
    public String parseDeeplinkAction(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return null;
        }
        String authority = Uri.parse(str).getAuthority();
        if (!StringUtil.isNotEmpty(authority)) {
            return null;
        }
        if (authority.startsWith("createpost")) {
            return Constants.CONSULT_DEEPLINK_ACTION_CREATEPOST;
        }
        if (authority.startsWith("timeline")) {
            return Constants.CONSULT_DEEPLINK_ACTION_TIMELINE;
        }
        if (authority.startsWith("post")) {
            return Constants.CONSULT_DEEPLINK_ACTION_POST;
        }
        if (authority.startsWith("user")) {
            return Constants.CONSULT_DEEPLINK_ACTION_USER;
        }
        return null;
    }

    private boolean handleTimelineDeeplink(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return false;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (!StringUtil.isNotEmpty(lastPathSegment)) {
            return true;
        }
        this.mCurrentlySelectedTag = lastPathSegment;
        if (isSpecialtyTag(str, lastPathSegment)) {
            this.mCurrentlySelectedConsultFeedType = Constants.CONSULT_FEEDTYPE_TAG;
            return true;
        }
        this.mCurrentlySelectedConsultFeedType = getContentFeedTypeForTag(lastPathSegment);
        return true;
    }

    private boolean isSpecialtyTag(String str, String str2) {
        return str.contains("/specialty/") && StringUtil.isNotEmpty(str2);
    }

    private boolean handleUserDeeplink(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return true;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (!StringUtil.isNotEmpty(lastPathSegment)) {
            return true;
        }
        ConsultUser consultUser = new ConsultUser();
        consultUser.setUserId(lastPathSegment);
        Intent intent = new Intent(this, ConsultProfileActivity.class);
        intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
        startActivityForResult(intent, Constants.REQUEST_CODE_PROFILE_VIEW);
        finish();
        return true;
    }

    private boolean handleCreatePostDeeplink(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return true;
        }
        createNewPost();
        finish();
        return true;
    }

    private boolean handlePostDeeplink(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return true;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (!StringUtil.isNotEmpty(lastPathSegment)) {
            return true;
        }
        ConsultPost consultPost = new ConsultPost();
        consultPost.setPostId(lastPathSegment);
        Intent intent = new Intent(this, ConsultPostDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost);
        startActivityForResult(intent, Constants.REQUEST_CODE_POST_DETAIL_VIEW);
        finish();
        return true;
    }

    private boolean handleDeeplink(String str) {
        if (StringUtil.isNotEmpty(str)) {
            String parseDeeplinkAction = parseDeeplinkAction(str);
            char c = 65535;
            switch (parseDeeplinkAction.hashCode()) {
                case -1457726012:
                    if (parseDeeplinkAction.equals(Constants.CONSULT_DEEPLINK_ACTION_TIMELINE)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1236804701:
                    if (parseDeeplinkAction.equals(Constants.CONSULT_DEEPLINK_ACTION_POST)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1236652338:
                    if (parseDeeplinkAction.equals(Constants.CONSULT_DEEPLINK_ACTION_USER)) {
                        c = 3;
                        break;
                    }
                    break;
                case -844033953:
                    if (parseDeeplinkAction.equals(Constants.CONSULT_DEEPLINK_ACTION_CREATEPOST)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                return handleTimelineDeeplink(str);
            }
            if (c == 1) {
                return handleCreatePostDeeplink(str);
            }
            if (c == 2) {
                return handlePostDeeplink(str);
            }
            if (c == 3) {
                return handleUserDeeplink(str);
            }
        }
        return false;
    }

    private int getContentFeedTypeForTag(String str) {
        if ("featured".equalsIgnoreCase(str)) {
            return 3007;
        }
        if (Constants.CONSULT_MY_NETWORK.equalsIgnoreCase(str)) {
            return 3000;
        }
        if (Constants.CONSULT_FOLLOWED_POSTS.equalsIgnoreCase(str)) {
            return Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS;
        }
        if (Constants.CONSULT_DEEPLINK_TOP_POSTS.equalsIgnoreCase(str)) {
            return Constants.CONSULT_FEEDTYPE_TOP_POSTS;
        }
        if (Constants.CONSULT_MY_POSTS.equalsIgnoreCase(str)) {
            return 3002;
        }
        return Constants.CONSULT_FEEDTYPE_ALL;
    }

    private String getStringForFeedType(int i, String str) {
        switch (i) {
            case 3000:
                return getMatchedFilter(Constants.CONSULT_MY_NETWORK).getTitle();
            case Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS:
                return getMatchedFilter(Constants.CONSULT_FOLLOWED_POSTS).getTitle();
            case 3002:
                return getMatchedFilter(Constants.CONSULT_MY_POSTS).getTitle();
            case Constants.CONSULT_FEEDTYPE_TAG:
                return str.equalsIgnoreCase("featured") ? getMatchedFilter("featured").getTitle() : str;
            case Constants.CONSULT_FEEDTYPE_ALL:
                return getMatchedFilter(Constants.CONSULT_ALL).getTitle();
            case Constants.CONSULT_FEEDTYPE_TOP_POSTS:
                return getMatchedFilter(Constants.CONSULT_TOP_POSTS).getTitle();
            case 3007:
                return getMatchedFilter("featured").getTitle();
            default:
                return str;
        }
    }

    public void createNewPost() {
        Intent intent = new Intent(this, ConsultPostActivity.class);
        intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE, this.mCurrentlySelectedConsultFeedType);
        if (StringUtil.isNotEmpty(this.mCurrentlySelectedTag)) {
            intent.putExtra(Constants.EXTRA_CONSULT_TIMELINE_FEED_TAG, this.mCurrentlySelectedTag);
        }
        startActivityForResult(intent, Constants.REQUEST_CODE_UPLOAD_POST);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        FragmentManager supportFragmentManager;
        if (i == 4016) {
            if (i2 == -1 && (supportFragmentManager = getSupportFragmentManager()) != null) {
                Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
                if (findFragmentByTag instanceof ConsultTimelineFragment) {
                    ((ConsultTimelineFragment) findFragmentByTag).updateFeedWithNewPost(intent);
                }
            }
        } else if (i != 4013) {
        } else {
            if (i2 == -1) {
                FragmentManager supportFragmentManager2 = getSupportFragmentManager();
                if (supportFragmentManager2 != null) {
                    Fragment findFragmentByTag2 = supportFragmentManager2.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
                    if (findFragmentByTag2 instanceof ConsultTimelineFragment) {
                        ((ConsultTimelineFragment) findFragmentByTag2).loadFeedForNewSection(3000, (String) null, (String) null);
                        return;
                    }
                    return;
                }
                return;
            }
            finish();
        }
    }

    public void setUpConsultTimelineScreen() {
        setUpActionBar();
        setScreenSpecificMap();
        initializeFragment();
    }

    private void generateHardCodedFilters() {
        this.mHardcodedFilters = new LinkedList<>();
        ConsultTimeLineFilterItem consultTimeLineFilterItem = new ConsultTimeLineFilterItem();
        consultTimeLineFilterItem.setTitle("All Posts");
        consultTimeLineFilterItem.setFilterType(Constants.CONSULT_ALL);
        this.mHardcodedFilters.add(consultTimeLineFilterItem);
        ConsultTimeLineFilterItem consultTimeLineFilterItem2 = new ConsultTimeLineFilterItem();
        consultTimeLineFilterItem2.setTitle(CONSULT_FEATURE_TAG);
        consultTimeLineFilterItem2.setFilterType("featured");
        this.mHardcodedFilters.add(consultTimeLineFilterItem2);
        ConsultTimeLineFilterItem consultTimeLineFilterItem3 = new ConsultTimeLineFilterItem();
        consultTimeLineFilterItem3.setTitle("Top Posts");
        consultTimeLineFilterItem3.setFilterType(Constants.CONSULT_TOP_POSTS);
        this.mHardcodedFilters.add(consultTimeLineFilterItem3);
        ConsultTimeLineFilterItem consultTimeLineFilterItem4 = new ConsultTimeLineFilterItem();
        consultTimeLineFilterItem4.setTitle("Followed Posts");
        consultTimeLineFilterItem4.setFilterType(Constants.CONSULT_FOLLOWED_POSTS);
        this.mHardcodedFilters.add(consultTimeLineFilterItem4);
        ConsultTimeLineFilterItem consultTimeLineFilterItem5 = new ConsultTimeLineFilterItem();
        consultTimeLineFilterItem5.setTitle("My Posts");
        consultTimeLineFilterItem5.setFilterType(Constants.CONSULT_MY_POSTS);
        this.mHardcodedFilters.add(consultTimeLineFilterItem5);
    }

    private ConsultTimeLineFilterItem getMatchedFilter(String str) {
        ConsultTimeLineFilterItem consultTimeLineFilterItem = new ConsultTimeLineFilterItem();
        Iterator it = this.mHardcodedFilters.iterator();
        while (it.hasNext()) {
            ConsultTimeLineFilterItem consultTimeLineFilterItem2 = (ConsultTimeLineFilterItem) it.next();
            if (consultTimeLineFilterItem2.getFilterType().equalsIgnoreCase(str)) {
                return consultTimeLineFilterItem2;
            }
        }
        return consultTimeLineFilterItem;
    }

    public String getDisplayFilter() {
        return ConsultFilterManager.getFilters(this.displayFilter, this.globalTag);
    }

    public void scrollToTop() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_TIMELINE);
        if (findFragmentByTag instanceof ConsultTimelineFragment) {
            ((ConsultTimelineFragment) findFragmentByTag).mRecyclerView.scrollToPosition(0);
        }
    }

    public void onBackPressed() {
        finish();
    }
}
