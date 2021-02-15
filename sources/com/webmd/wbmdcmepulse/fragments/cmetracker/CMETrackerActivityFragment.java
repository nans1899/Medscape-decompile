package com.webmd.wbmdcmepulse.fragments.cmetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CMETrackerActivity;
import com.webmd.wbmdcmepulse.activities.CmeEvaluationActivity;
import com.webmd.wbmdcmepulse.activities.CmePostTestActivity;
import com.webmd.wbmdcmepulse.activities.CmeWebActivity;
import com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter;
import com.webmd.wbmdcmepulse.controllers.ArticleController;
import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.fragments.BaseFragment;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.Eligibility;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.cmetracker.CMEItem;
import com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse;
import com.webmd.wbmdcmepulse.models.interfaces.ICMETrackerButtonHandler;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.QnaScoreCalculator;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider;
import com.webmd.wbmdcmepulse.providers.CmeArticleProvider;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class CMETrackerActivityFragment extends BaseFragment implements ICMETrackerButtonHandler {
    /* access modifiers changed from: private */
    public static final String TAG = CMETrackerActivityFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public ICallbackEvent<Boolean, String> mCallbck;
    private TextView mEmplyListTextView;
    private ArrayList<CMEItem> mEntireList;
    private CMETrackerResponse mResponse;
    /* access modifiers changed from: private */
    public View mRootView;
    /* access modifiers changed from: private */
    public StickyListHeadersListView mStickyHeadersListView;
    /* access modifiers changed from: private */
    public String mYear;

    public static CMETrackerActivityFragment newInstance(String str, CMETrackerResponse cMETrackerResponse, ICallbackEvent<Boolean, String> iCallbackEvent, UserProfile userProfile) {
        CMETrackerActivityFragment cMETrackerActivityFragment = new CMETrackerActivityFragment();
        cMETrackerActivityFragment.mYear = str;
        cMETrackerActivityFragment.mResponse = cMETrackerResponse;
        cMETrackerActivityFragment.mCallbck = iCallbackEvent;
        cMETrackerActivityFragment.mUserProfile = userProfile;
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEY_USER_PROFILE, userProfile);
        cMETrackerActivityFragment.setArguments(bundle);
        return cMETrackerActivityFragment;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(Constants.BUNDLE_KEY_TRACKER_MAP, this.mResponse);
        bundle.putString(Constants.BUNDLE_KEY_TRACKER_YEAR, this.mYear);
        super.onSaveInstanceState(bundle);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_CME_TRACKER, new Object[]{this.mYear}), (Map<String, String>) null, (WBMDOmnitureModule) null);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle != null) {
            this.mResponse = (CMETrackerResponse) bundle.getParcelable(Constants.BUNDLE_KEY_TRACKER_MAP);
            this.mYear = bundle.getString(Constants.BUNDLE_KEY_TRACKER_YEAR);
        }
        View inflate = layoutInflater.inflate(R.layout.cme_fragment_cmetracker, viewGroup, false);
        this.mRootView = inflate;
        this.mStickyHeadersListView = (StickyListHeadersListView) inflate.findViewById(R.id.cme_tracker_list_view);
        this.mEmplyListTextView = (TextView) this.mRootView.findViewById(R.id.emptyListTextView);
        setUpStickyHeaders();
        loadTrackerData(this.mEmplyListTextView);
        return this.mRootView;
    }

    private void setUpStickyHeaders() {
        this.mStickyHeadersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.webmd.wbmdcmepulse.models.cmetracker.CMEItem r1 = (com.webmd.wbmdcmepulse.models.cmetracker.CMEItem) r1
                    boolean r4 = r1.isSection()
                    if (r4 != 0) goto L_0x0055
                    java.lang.String r1 = r1.getType()
                    java.lang.String r4 = "IN_PROGRESS"
                    boolean r1 = r1.equals(r4)
                    if (r1 != 0) goto L_0x0055
                    int r1 = com.webmd.wbmdcmepulse.R.id.completed_options
                    android.view.View r1 = r2.findViewById(r1)
                    int r4 = r1.getVisibility()
                    r5 = 8
                    if (r4 != r5) goto L_0x003c
                    r4 = 0
                    r1.setVisibility(r4)
                    int r1 = com.webmd.wbmdcmepulse.R.id.arrow
                    android.view.View r1 = r2.findViewById(r1)
                    if (r1 == 0) goto L_0x004c
                    com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment r2 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment.this
                    r2.rotateUp(r1)
                    goto L_0x004c
                L_0x003c:
                    r1.setVisibility(r5)
                    int r1 = com.webmd.wbmdcmepulse.R.id.arrow
                    android.view.View r1 = r2.findViewById(r1)
                    if (r1 == 0) goto L_0x004c
                    com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment r2 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment.this
                    r2.rotateDown(r1)
                L_0x004c:
                    com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment r1 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment.this
                    se.emilsjolander.stickylistheaders.StickyListHeadersListView r1 = r1.mStickyHeadersListView
                    r1.setSelection(r3)
                L_0x0055:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment.AnonymousClass1.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
    }

    private void loadTrackerData(TextView textView) {
        Bundle bundle;
        if (getActivity() != null && !getActivity().isFinishing() && this.mResponse != null) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            this.mEntireList = new ArrayList<>();
            CMETrackerHeaderFragment newInstance = CMETrackerHeaderFragment.newInstance(new ICallbackEvent<Boolean, String>() {
                public void onError(String str) {
                    CMETrackerActivityFragment.this.mCallbck.onError(str);
                }

                public void onCompleted(Boolean bool) {
                    if (bool.booleanValue()) {
                        CMETrackerActivityFragment.this.setHeaderHeight();
                        CMETrackerActivityFragment.this.mCallbck.onCompleted(bool);
                    }
                }
            }, this.mUserProfile);
            Bundle arguments = newInstance.getArguments();
            if (arguments != null) {
                bundle = new Bundle(arguments);
            } else {
                bundle = new Bundle();
            }
            if (this.mResponse.creditMap != null) {
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                HashMap hashMap = this.mResponse.creditMap;
                if (hashMap.containsKey(CMETrackerDataProvider.CME_CREDITS_KEY + "-" + this.mYear)) {
                    String str = CMETrackerDataProvider.CME_CREDITS_KEY;
                    HashMap hashMap2 = this.mResponse.creditMap;
                    bundle.putString(str, decimalFormat.format(hashMap2.get(CMETrackerDataProvider.CME_CREDITS_KEY + "-" + this.mYear)));
                } else {
                    bundle.putString(CMETrackerDataProvider.CME_CREDITS_KEY, "0.00");
                }
                HashMap hashMap3 = this.mResponse.creditMap;
                if (hashMap3.containsKey(CMETrackerDataProvider.CE_CREDITS_KEY + "-" + this.mYear)) {
                    HashMap hashMap4 = this.mResponse.creditMap;
                    StringBuilder sb = new StringBuilder(decimalFormat.format(hashMap4.get(CMETrackerDataProvider.CE_CREDITS_KEY + "-" + this.mYear)));
                    HashMap hashMap5 = this.mResponse.creditMap;
                    if (hashMap5.containsKey(CMETrackerDataProvider.RX_CREDITS_KEY + "-" + this.mYear)) {
                        sb.append(" / ");
                        HashMap hashMap6 = this.mResponse.creditMap;
                        sb.append(decimalFormat.format(hashMap6.get(CMETrackerDataProvider.RX_CREDITS_KEY + "-" + this.mYear)));
                    } else {
                        sb.append(" / ");
                        sb.append("0.00");
                    }
                    bundle.putString(CMETrackerDataProvider.CE_CREDITS_KEY, sb.toString());
                } else {
                    bundle.putString(CMETrackerDataProvider.CE_CREDITS_KEY, "0.00");
                }
                HashMap hashMap7 = this.mResponse.creditMap;
                if (hashMap7.containsKey(CMETrackerDataProvider.MOC_CREDITS_KEY + "-" + this.mYear)) {
                    String str2 = CMETrackerDataProvider.MOC_CREDITS_KEY;
                    HashMap hashMap8 = this.mResponse.creditMap;
                    bundle.putString(str2, decimalFormat.format(hashMap8.get(CMETrackerDataProvider.MOC_CREDITS_KEY + "-" + this.mYear)));
                } else {
                    bundle.putString(CMETrackerDataProvider.MOC_CREDITS_KEY, "0.00");
                }
                HashMap hashMap9 = this.mResponse.creditMap;
                if (hashMap9.containsKey(CMETrackerDataProvider.LOC_CREDITS_KEY + "-" + this.mYear)) {
                    String str3 = CMETrackerDataProvider.LOC_CREDITS_KEY;
                    HashMap hashMap10 = this.mResponse.creditMap;
                    bundle.putString(str3, decimalFormat.format(hashMap10.get(CMETrackerDataProvider.LOC_CREDITS_KEY + "-" + this.mYear)));
                } else {
                    bundle.putString(CMETrackerDataProvider.LOC_CREDITS_KEY, "0.00");
                }
            }
            bundle.putBoolean("resubmitABIM", isResubmitABIMNeeded(this.mResponse));
            newInstance.setArguments(bundle);
            beginTransaction.add(R.id.cme_tracker_header_layout, (Fragment) newInstance);
            beginTransaction.commit();
            boolean equals = this.mYear.equals(Integer.toString(Calendar.getInstance().get(1)));
            if (this.mResponse.inProgressCmeItems.size() > 0 && equals) {
                CMEItem cMEItem = new CMEItem();
                cMEItem.setIsSection(true);
                cMEItem.setTitle(getResources().getString(R.string.cme_tracker_in_progress_header_label));
                this.mEntireList.addAll(this.mResponse.inProgressCmeItems);
            }
            if (this.mResponse.completedCmeItems.size() > 0) {
                CMEItem cMEItem2 = new CMEItem();
                cMEItem2.setIsSection(true);
                cMEItem2.setTitle(getResources().getString(R.string.cme_tracker_completed_header_label));
                Iterator<CMEItem> it = this.mResponse.completedCmeItems.iterator();
                while (it.hasNext()) {
                    CMEItem next = it.next();
                    if (new SimpleDateFormat("yyyy").format(next.getParticipationDate()).equals(this.mYear)) {
                        this.mEntireList.add(next);
                    }
                }
            }
            if (this.mResponse.mocCmeItmes.size() > 0) {
                CMEItem cMEItem3 = new CMEItem();
                cMEItem3.setIsSection(true);
                cMEItem3.setTitle(getResources().getString(R.string.cme_tracker_completed_moc_header_label));
                Iterator<CMEItem> it2 = this.mResponse.mocCmeItmes.iterator();
                while (it2.hasNext()) {
                    CMEItem next2 = it2.next();
                    if (new SimpleDateFormat("yyyy").format(next2.getParticipationDate()).equals(this.mYear)) {
                        this.mEntireList.add(next2);
                    }
                }
            }
            if (this.mResponse.locCmeItmes.size() > 0) {
                CMEItem cMEItem4 = new CMEItem();
                cMEItem4.setIsSection(true);
                cMEItem4.setTitle(getResources().getString(R.string.cme_tracker_completed_loc_header_label));
                Iterator<CMEItem> it3 = this.mResponse.locCmeItmes.iterator();
                while (it3.hasNext()) {
                    CMEItem next3 = it3.next();
                    if (new SimpleDateFormat("yyyy").format(next3.getParticipationDate()).equals(this.mYear)) {
                        this.mEntireList.add(next3);
                    }
                }
            }
            setUpAdapter(this.mEntireList);
            ArrayList<CMEItem> arrayList = this.mEntireList;
            if (arrayList == null || arrayList.size() == 0) {
                textView.setText("No Credits found for " + this.mYear);
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            setHeaderHeight();
        }
    }

    private void setUpAdapter(ArrayList<CMEItem> arrayList) {
        CMETrackerListAdapter cMETrackerListAdapter = new CMETrackerListAdapter(getContext(), arrayList, this.mUserProfile);
        cMETrackerListAdapter.buttonHandler = this;
        this.mStickyHeadersListView.setAdapter(cMETrackerListAdapter);
        cMETrackerListAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void setHeaderHeight() {
        this.mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                DisplayMetrics displayMetrics;
                View findViewById = CMETrackerActivityFragment.this.mRootView.findViewById(R.id.cme_tracker_header_layout);
                if (!(findViewById == null || CMETrackerActivityFragment.this.getActivity() == null || CMETrackerActivityFragment.this.getActivity().getResources() == null || (displayMetrics = CMETrackerActivityFragment.this.getActivity().getResources().getDisplayMetrics()) == null)) {
                    View findViewById2 = findViewById.findViewById(R.id.credit_total_layout);
                    View findViewById3 = findViewById.findViewById(R.id.abim_id_button);
                    View findViewById4 = findViewById.findViewById(R.id.cmeTrackerStateRequirementsButton);
                    if (findViewById4.getVisibility() == 0) {
                        findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, ((int) findViewById4.getY()) + findViewById4.getHeight() + ((int) (displayMetrics.density * 30.0f))));
                    } else if (findViewById3.getVisibility() == 0) {
                        findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, ((int) findViewById3.getY()) + findViewById3.getHeight() + ((int) (displayMetrics.density * 30.0f))));
                    } else {
                        findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, ((int) findViewById2.getY()) + findViewById2.getHeight() + ((int) (displayMetrics.density * 30.0f))));
                    }
                }
                try {
                    if (Build.VERSION.SDK_INT < 16) {
                        CMETrackerActivityFragment.this.mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        CMETrackerActivityFragment.this.mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                } catch (Exception unused) {
                    Trace.w(CMETrackerActivityFragment.TAG, "Failed to remove global layout listener");
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0075 A[EDGE_INSN: B:54:0x0075->B:30:0x0075 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isResubmitABIMNeeded(com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse r7) {
        /*
            r6 = this;
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r0 = r7.inProgressCmeItems
            r1 = 1
            java.lang.String r2 = "Error"
            if (r0 == 0) goto L_0x003b
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r0 = r7.inProgressCmeItems
            java.util.Iterator r0 = r0.iterator()
        L_0x000d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x003b
            java.lang.Object r3 = r0.next()
            com.webmd.wbmdcmepulse.models.cmetracker.CMEItem r3 = (com.webmd.wbmdcmepulse.models.cmetracker.CMEItem) r3
            if (r3 == 0) goto L_0x000d
            java.util.ArrayList r4 = r3.getErrorCodes()
            boolean r4 = r6.errorCodesNeedResubmission(r4)
            if (r4 == 0) goto L_0x000d
            java.lang.String r4 = r3.getMOCStatus()
            boolean r4 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r4)
            if (r4 != 0) goto L_0x000d
            java.lang.String r3 = r3.getMOCStatus()
            boolean r3 = r3.equalsIgnoreCase(r2)
            if (r3 == 0) goto L_0x000d
            r0 = 1
            goto L_0x003c
        L_0x003b:
            r0 = 0
        L_0x003c:
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r3 = r7.completedCmeItems
            if (r3 == 0) goto L_0x0075
            if (r0 != 0) goto L_0x0075
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r3 = r7.completedCmeItems
            java.util.Iterator r3 = r3.iterator()
        L_0x0048:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0075
            java.lang.Object r4 = r3.next()
            com.webmd.wbmdcmepulse.models.cmetracker.CMEItem r4 = (com.webmd.wbmdcmepulse.models.cmetracker.CMEItem) r4
            if (r4 == 0) goto L_0x0048
            java.util.ArrayList r5 = r4.getErrorCodes()
            boolean r5 = r6.errorCodesNeedResubmission(r5)
            if (r5 == 0) goto L_0x0048
            java.lang.String r5 = r4.getMOCStatus()
            boolean r5 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r5)
            if (r5 != 0) goto L_0x0048
            java.lang.String r4 = r4.getMOCStatus()
            boolean r4 = r4.equalsIgnoreCase(r2)
            if (r4 == 0) goto L_0x0048
            r0 = 1
        L_0x0075:
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r3 = r7.mocCmeItmes
            if (r3 == 0) goto L_0x00ae
            if (r0 != 0) goto L_0x00ae
            java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r7 = r7.mocCmeItmes
            java.util.Iterator r7 = r7.iterator()
        L_0x0081:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x00ae
            java.lang.Object r3 = r7.next()
            com.webmd.wbmdcmepulse.models.cmetracker.CMEItem r3 = (com.webmd.wbmdcmepulse.models.cmetracker.CMEItem) r3
            if (r3 == 0) goto L_0x0081
            java.util.ArrayList r4 = r3.getErrorCodes()
            boolean r4 = r6.errorCodesNeedResubmission(r4)
            if (r4 == 0) goto L_0x0081
            java.lang.String r4 = r3.getMOCStatus()
            boolean r4 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r4)
            if (r4 != 0) goto L_0x0081
            java.lang.String r3 = r3.getMOCStatus()
            boolean r3 = r3.equalsIgnoreCase(r2)
            if (r3 == 0) goto L_0x0081
            goto L_0x00af
        L_0x00ae:
            r1 = r0
        L_0x00af:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment.isResubmitABIMNeeded(com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse):boolean");
    }

    private boolean errorCodesNeedResubmission(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return false;
        }
        List asList = Arrays.asList(new String[]{"621", "622", "623", "624", "661", "664", "665"});
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            if (asList.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void rotateUp(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    /* access modifiers changed from: private */
    public void rotateDown(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    public void viewActivityButtonPressed(View view, CMEItem cMEItem) {
        final String replace = cMEItem.getReferrerUri().replace("/viewarticle/", "");
        if (Extensions.isStringNullOrEmpty(replace)) {
            Toast.makeText(getContext(), "Activity Not Found", 0).show();
        } else if (cMEItem.getType().equals(CMEItem.IN_PROGRESS)) {
            final CmeArticleProvider cmeArticleProvider = new CmeArticleProvider(getContext());
            cmeArticleProvider.getArticle(replace, new ICallbackEvent<Article, CMEPulseException>() {
                public void onError(CMEPulseException cMEPulseException) {
                    if (!cMEPulseException.getMessage().equals(IncompatibleArticleException.CLASS_NAME)) {
                        Toast.makeText(CMETrackerActivityFragment.this.getContext(), cMEPulseException.getMessage(), 0).show();
                    } else if (CMETrackerActivityFragment.this.getActivity() instanceof CMETrackerActivity) {
                        Intent cMEWebViewIntent = ((CMETrackerActivity) CMETrackerActivityFragment.this.getActivity()).getCMEWebViewIntent();
                        cMEWebViewIntent.putExtra(Constants.WEB_VIEW_URL_KEY, Utilities.getCMEUrl(CMETrackerActivityFragment.this.getContext(), replace));
                        cMEWebViewIntent.putExtra(Constants.WEB_VIEW_TITLE_KEY, "CME Activity");
                        cMEWebViewIntent.putExtra(Constants.RETURN_ACTIVITY, Constants.CME_TRACKER_ACTIVITY_NAME);
                        CMETrackerActivityFragment.this.startActivity(cMEWebViewIntent);
                        CMETrackerActivityFragment.this.getActivity().finish();
                    }
                }

                public void onCompleted(final Article article) {
                    cmeArticleProvider.getQna(article.qnaId, new ICallbackEvent<Questionnaire, CMEPulseException>() {
                        public void onError(CMEPulseException cMEPulseException) {
                            Toast.makeText(CMETrackerActivityFragment.this.getContext(), cMEPulseException.getMessage(), 0).show();
                        }

                        public void onCompleted(Questionnaire questionnaire) {
                            Questionnaire questionnaire2 = questionnaire;
                            try {
                                UserProfile userProfile = new ArticleController(CMETrackerActivityFragment.this.getContext(), CMETrackerActivityFragment.this.mUserProfile).getUserProfile();
                                UserProfession professionProfile = userProfile.getProfessionProfile();
                                boolean z = false;
                                boolean z2 = false;
                                boolean z3 = false;
                                for (int i = 0; i < article.eligibilities.size(); i++) {
                                    Eligibility eligibility = article.eligibilities.get(i);
                                    if (eligibility.type == 6) {
                                        z2 = true;
                                    } else if (Utilities.getCreditTypeFromProfessionId(professionProfile.getProfessionId(), professionProfile.getOccupationId()) == eligibility.type) {
                                        z3 = eligibility.isEvalRequired;
                                        z = true;
                                    }
                                }
                                if (z) {
                                    z2 = z3;
                                }
                                boolean z4 = true;
                                boolean z5 = false;
                                for (ActivityTest next : questionnaire2.tests) {
                                    if (!z4) {
                                        break;
                                    } else if (next.isScorable) {
                                        for (Question next2 : next.questions) {
                                            int i2 = !next2.isPassed ? 1 : 0;
                                            if (!z5 && (next2.formatType.equals("POST") || next2.formatType.equals("INTERNAL"))) {
                                                z5 = true;
                                            }
                                            if (QnaScoreCalculator.getCurrentScore(i2, next.questions.size()) < QnaScoreCalculator.getPassingScore(article.eligibilities, CMETrackerActivityFragment.this.mUserProfile)) {
                                                z4 = false;
                                            }
                                        }
                                    }
                                }
                                if (CMETrackerActivityFragment.this.getActivity() instanceof CMETrackerActivity) {
                                    ((CMETrackerActivity) CMETrackerActivityFragment.this.getActivity()).sendArticleViewedEvent(article);
                                }
                                if (!z4 || !z2) {
                                    String str = Constants.BUNDLE_KEY_USER_PROFILE;
                                    if (z5) {
                                        String str2 = OmnitureData.LINK_NAME_TRACKER_IN_PROGRESS;
                                        Context context = CMETrackerActivityFragment.this.getContext();
                                        String str3 = Constants.BUNDLE_KEY_REFERRING_LINK;
                                        Intent intent = new Intent(context, CmePostTestActivity.class);
                                        intent.putExtra(Constants.BUNDLE_KEY_QNA, questionnaire2);
                                        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE, article);
                                        List<Eligibility> list = article.eligibilities;
                                        UserProfile userProfile2 = CMETrackerActivityFragment.this.mUserProfile;
                                        String str4 = OmnitureData.MODULE_NAME_CME_TRACKER;
                                        String str5 = Constants.BUNDLE_KEY_REFERRING_MODULE;
                                        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, QnaScoreCalculator.getPassingScore(list, userProfile2));
                                        intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, z2);
                                        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, article.title);
                                        intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, article.isMocEligible);
                                        intent.putExtra(Constants.BUNDLE_KEY_USER_ID, userProfile.getBasicProfile().getUserId());
                                        intent.putExtra(Constants.BUNDLE_KEY_CAME_FROM_TRACKER, true);
                                        intent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, String.format(OmnitureData.PAGE_NAME_CME_TRACKER, new Object[]{CMETrackerActivityFragment.this.mYear}));
                                        intent.putExtra(str5, str4);
                                        intent.putExtra(str3, str2);
                                        intent.putExtra(str, CMETrackerActivityFragment.this.mUserProfile);
                                        CMETrackerActivityFragment.this.startActivity(intent);
                                        return;
                                    }
                                    String str6 = OmnitureData.MODULE_NAME_CME_TRACKER;
                                    String str7 = OmnitureData.LINK_NAME_TRACKER_IN_PROGRESS;
                                    String str8 = Constants.BUNDLE_KEY_REFERRING_MODULE;
                                    String str9 = Constants.BUNDLE_KEY_REFERRING_LINK;
                                    String str10 = str6;
                                    if (CMETrackerActivityFragment.this.getActivity() instanceof CMETrackerActivity) {
                                        Intent cMEArticleActivityIntent = ((CMETrackerActivity) CMETrackerActivityFragment.this.getActivity()).getCMEArticleActivityIntent();
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, article.id);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE, article);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_QNA, questionnaire2);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, z2);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, z2);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, article.title);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, article.isMocEligible);
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_USER_ID, userProfile.getBasicProfile().getUserId());
                                        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, String.format(OmnitureData.PAGE_NAME_CME_TRACKER, new Object[]{CMETrackerActivityFragment.this.mYear}));
                                        cMEArticleActivityIntent.putExtra(str8, str10);
                                        cMEArticleActivityIntent.putExtra(str9, str7);
                                        cMEArticleActivityIntent.putExtra(str, CMETrackerActivityFragment.this.mUserProfile);
                                        CMETrackerActivityFragment.this.startActivity(cMEArticleActivityIntent);
                                        return;
                                    }
                                    return;
                                }
                                Intent intent2 = new Intent(CMETrackerActivityFragment.this.getContext(), CmeEvaluationActivity.class);
                                intent2.putExtra(Constants.BUNDLE_KEY_QNA_ID, questionnaire2.id);
                                intent2.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, z2);
                                intent2.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, CMETrackerActivityFragment.this.mUserProfile);
                                CMETrackerActivityFragment.this.startActivity(intent2);
                            } catch (Exception e) {
                                Trace.e(CMETrackerActivityFragment.TAG, e.getMessage());
                            }
                        }
                    }, CMETrackerActivityFragment.this.getContext());
                }
            });
        } else {
            FragmentActivity activity = getActivity();
            if (activity instanceof CMETrackerActivity) {
                Intent cMELaunchIntent = ((CMETrackerActivity) activity).getCMELaunchIntent();
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, replace);
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_FEED_URL, Utilities.getCMEUrl(getContext(), replace));
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, cMEItem.getTitle());
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, String.format(OmnitureData.PAGE_NAME_CME_TRACKER, new Object[]{this.mYear}));
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, OmnitureData.MODULE_NAME_CME_TRACKER);
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, OmnitureData.LINK_NAME_TRACKER_COMPLETE);
                cMELaunchIntent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
                startActivity(cMELaunchIntent);
            }
        }
    }

    public void viewCertificateButtonPressed(View view, CMEItem cMEItem) {
        Intent intent = new Intent(getContext(), CmeWebActivity.class);
        String viewCertificateUri = cMEItem.getViewCertificateUri();
        intent.putExtra(Constants.WEB_VIEW_URL_KEY, Utilities.generateEnvironment(getContext(), "https://www%s.medscape.org") + viewCertificateUri);
        intent.putExtra(Constants.WEB_VIEW_TITLE_KEY, "Certificate");
        startActivity(intent);
    }
}
