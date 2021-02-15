package com.medscape.android.activity.interactions;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.INewListCreationListener;
import com.medscape.android.activity.interactions.interfaces.IRemoveIconListener;
import com.medscape.android.activity.interactions.interfaces.ISearchItemClickListener;
import com.medscape.android.activity.interactions.models.DrugList;
import com.medscape.android.activity.interactions.recycler_views.InteractionDrugAdapter;
import com.medscape.android.activity.interactions.recycler_views.InteractionDrugSearchAdapter;
import com.medscape.android.activity.interactions.viewmodels.DrugSearchViewModel;
import com.medscape.android.activity.interactions.viewmodels.InteractionsViewModel;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.db.model.Drug;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrugInteractionActivity extends BaseActivity implements IRemoveIconListener, ISearchItemClickListener {
    private final int DELETE_ALL = 19;
    private final int PICK_LIST_REQUEST = 3456;
    private TextView clearAll;
    private RecyclerView drugRecyclerView;
    public ViewModelProvider.Factory drugsFactory;
    private TextView interactionMessage;
    InteractionsViewModel interactionsViewModel;
    private TextView listName;
    private InteractionDrugAdapter mDrugListAdapter;
    private InteractionDrugSearchAdapter mSearchAdapter;
    private TextView noResultTv;
    private ProgressBar progressBar;
    private EditText searchBar;
    public ViewModelProvider.Factory searchFactory;
    private RelativeLayout searchResultLayout;
    DrugSearchViewModel searchViewModel;
    private TextView useSearchFieldTv;
    private Button viewInteractionsBtn;
    public String voiceQuery;

    public void onRemoveListIconClicked(int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_drug_interaction);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.medscape_blue));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            getSupportActionBar().setHomeActionContentDescription((int) R.string.drug_interaction_home_content_desc);
        }
        setUpViews();
        setUpRecyclerViews();
        setUpListeners();
        this.searchViewModel = (DrugSearchViewModel) ViewModelProviders.of((FragmentActivity) this, this.searchFactory).get(DrugSearchViewModel.class);
        this.interactionsViewModel = (InteractionsViewModel) ViewModelProviders.of((FragmentActivity) this, this.drugsFactory).get(InteractionsViewModel.class);
        subscribeToSearchViewModel();
        subscribeToInteractionsViewModel();
        this.interactionsViewModel.setUp();
        checkForPassedDrug();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("voice_query")) {
            this.voiceQuery = intent.getStringExtra("voice_query");
        }
    }

    private void subscribeToSearchViewModel() {
        this.searchViewModel.getSearchResult().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToSearchViewModel$1$DrugInteractionActivity((List) obj);
            }
        });
        this.searchViewModel.isShowSearchUi().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToSearchViewModel$3$DrugInteractionActivity((Boolean) obj);
            }
        });
        this.searchViewModel.isSearchListEmpty().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToSearchViewModel$5$DrugInteractionActivity((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$subscribeToSearchViewModel$1$DrugInteractionActivity(List list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$null$0$DrugInteractionActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$DrugInteractionActivity(List list) {
        this.mSearchAdapter.setData(list);
        this.mSearchAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$subscribeToSearchViewModel$3$DrugInteractionActivity(Boolean bool) {
        runOnUiThread(new Runnable(bool) {
            public final /* synthetic */ Boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$null$2$DrugInteractionActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$2$DrugInteractionActivity(Boolean bool) {
        if (bool.booleanValue()) {
            this.searchResultLayout.setVisibility(0);
        } else {
            this.searchResultLayout.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$subscribeToSearchViewModel$5$DrugInteractionActivity(Boolean bool) {
        runOnUiThread(new Runnable(bool) {
            public final /* synthetic */ Boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$null$4$DrugInteractionActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$4$DrugInteractionActivity(Boolean bool) {
        if (bool.booleanValue()) {
            this.noResultTv.setVisibility(0);
        } else {
            this.noResultTv.setVisibility(8);
        }
    }

    private void subscribeToInteractionsViewModel() {
        this.interactionsViewModel.getDrugList().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToInteractionsViewModel$7$DrugInteractionActivity((List) obj);
            }
        });
        this.interactionsViewModel.getInteractions().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToInteractionsViewModel$8$DrugInteractionActivity((List) obj);
            }
        });
        this.interactionsViewModel.getListName().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugInteractionActivity.this.lambda$subscribeToInteractionsViewModel$10$DrugInteractionActivity((String) obj);
            }
        });
    }

    public /* synthetic */ void lambda$subscribeToInteractionsViewModel$7$DrugInteractionActivity(List list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$null$6$DrugInteractionActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$6$DrugInteractionActivity(List list) {
        this.progressBar.setVisibility(0);
        this.interactionMessage.setVisibility(8);
        this.mDrugListAdapter.setData(list);
        this.mDrugListAdapter.notifyDataSetChanged();
        updateUi(list.size());
    }

    public /* synthetic */ void lambda$subscribeToInteractionsViewModel$8$DrugInteractionActivity(List list) {
        updateInteractionUi(list.size());
    }

    public /* synthetic */ void lambda$subscribeToInteractionsViewModel$10$DrugInteractionActivity(String str) {
        runOnUiThread(new Runnable(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$null$9$DrugInteractionActivity(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$null$9$DrugInteractionActivity(String str) {
        if (str != null) {
            this.listName.setVisibility(0);
            this.listName.setText(str);
            return;
        }
        this.listName.setVisibility(8);
    }

    private void setUpViews() {
        EditText editText = (EditText) findViewById(R.id.drug_interaction_search);
        this.searchBar = editText;
        editText.requestFocus();
        this.clearAll = (TextView) findViewById(R.id.drug_interaction_clear_all);
        this.listName = (TextView) findViewById(R.id.drug_interaction_list_name);
        this.useSearchFieldTv = (TextView) findViewById(R.id.drug_interaction_use_search_field);
        this.interactionMessage = (TextView) findViewById(R.id.drug_interaction_interaction_message);
        this.viewInteractionsBtn = (Button) findViewById(R.id.drug_interaction_view_interactions_button);
        this.noResultTv = (TextView) findViewById(R.id.drug_interaction_no_result);
        this.searchResultLayout = (RelativeLayout) findViewById(R.id.drug_interaction_search_layout);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void setUpRecyclerViews() {
        this.drugRecyclerView = (RecyclerView) findViewById(R.id.interaction_main_drug_list);
        this.drugRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        InteractionDrugAdapter interactionDrugAdapter = new InteractionDrugAdapter(this, this);
        this.mDrugListAdapter = interactionDrugAdapter;
        this.drugRecyclerView.setAdapter(interactionDrugAdapter);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drug_interaction_search_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        InteractionDrugSearchAdapter interactionDrugSearchAdapter = new InteractionDrugSearchAdapter(this);
        this.mSearchAdapter = interactionDrugSearchAdapter;
        recyclerView.setAdapter(interactionDrugSearchAdapter);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_INTERACTIONS_VIEWED, this);
        OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "checker", "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.voiceQuery != null) {
            finish();
        }
    }

    private void setUpListeners() {
        this.searchBar.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                DrugInteractionActivity.this.searchViewModel.setSearchTerm(charSequence.toString());
                DrugInteractionActivity.this.getWindow().setSoftInputMode(16);
            }
        });
        this.viewInteractionsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                List value = DrugInteractionActivity.this.interactionsViewModel.getInteractions().getValue();
                if (value != null && value.size() > 0) {
                    OmnitureManager.get().trackModule(DrugInteractionActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "interaction", "view", (Map<String, Object>) null);
                    Intent intent = new Intent(DrugInteractionActivity.this, InteractionsDisplayActivity.class);
                    intent.putExtra("interactions", (ArrayList) value);
                    DrugInteractionActivity.this.startActivity(intent);
                }
            }
        });
        this.clearAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrugInteractionActivity.this.showDeleteAlert(19, (Drug) null);
            }
        });
    }

    private void checkForPassedDrug() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("drug") != null) {
            this.interactionsViewModel.addPassedDrug((Drug) intent.getExtras().get("drug"));
        }
    }

    public void onRemoveDrugIconClicked(Drug drug, int i) {
        if (drug != null) {
            showDeleteAlert(20, drug);
        }
    }

    public void onSearchItemClicked(Drug drug) {
        if (drug != null) {
            this.interactionsViewModel.addDrug(drug);
            AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_INTERACTION_ADD, this);
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "interaction", "add", (Map<String, Object>) null);
            resetSearchUi();
        }
    }

    private void resetSearchUi() {
        this.searchResultLayout.setVisibility(8);
        this.searchBar.setText("");
        Util.hideKeyboard(this);
    }

    private void updateUi(int i) {
        invalidateOptionsMenu();
        if (i == 0) {
            this.clearAll.setVisibility(8);
            this.useSearchFieldTv.setVisibility(0);
            this.interactionMessage.setVisibility(8);
            setButtonBackground(R.drawable.round_cornered_rectangle_grey);
        } else if (i == 1) {
            this.clearAll.setVisibility(0);
            this.useSearchFieldTv.setVisibility(0);
            this.interactionMessage.setVisibility(8);
            setButtonBackground(R.drawable.round_cornered_rectangle_grey);
        } else if (i > 1) {
            this.clearAll.setVisibility(0);
            this.useSearchFieldTv.setVisibility(8);
            this.interactionMessage.setVisibility(0);
            setButtonBackground(R.drawable.round_cornered_rectangle_grey);
        }
    }

    private void updateInteractionUi(int i) {
        if (i == 0) {
            setInteractionUiData(R.color.interaction_green, R.drawable.round_cornered_rectangle_grey, getString(R.string.drug_interaction_no_interactions_found));
        } else if (i == 1) {
            setInteractionUiData(R.color.interaction_red, R.drawable.round_cornered_rectangle_blue, getString(R.string.drug_interaction_interaction_found, new Object[]{Integer.valueOf(i)}));
        } else {
            setInteractionUiData(R.color.interaction_red, R.drawable.round_cornered_rectangle_blue, getString(R.string.drug_interaction_interactions_found, new Object[]{Integer.valueOf(i)}));
        }
    }

    private void setInteractionUiData(int i, int i2, String str) {
        runOnUiThread(new Runnable(str, i, i2) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                DrugInteractionActivity.this.lambda$setInteractionUiData$11$DrugInteractionActivity(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public /* synthetic */ void lambda$setInteractionUiData$11$DrugInteractionActivity(String str, int i, int i2) {
        this.progressBar.setVisibility(8);
        this.interactionMessage.setVisibility(0);
        this.interactionMessage.setText(str);
        this.interactionMessage.setTextColor(getResources().getColor(i));
        setButtonBackground(i2);
    }

    private void setButtonBackground(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.viewInteractionsBtn.setBackground(getResources().getDrawable(i));
        } else {
            this.viewInteractionsBtn.setBackgroundDrawable(getResources().getDrawable(i));
        }
    }

    /* access modifiers changed from: private */
    public void showDeleteAlert(final int i, final Drug drug) {
        String str;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i == 19) {
            builder.setTitle((int) R.string.drug_interaction_delete_all_title);
            builder.setMessage((int) R.string.drug_interaction_delete_all_message);
            str = getString(R.string.drug_interaction_delete_all_title);
        } else {
            builder.setTitle((int) R.string.drug_interaction_delete_item_title);
            builder.setMessage((int) R.string.drug_interaction_delete_item_message);
            str = getString(R.string.drug_interaction_delete_item_title);
        }
        builder.setPositiveButton((CharSequence) str, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 19) {
                    DrugInteractionActivity.this.interactionsViewModel.clearAll();
                } else if (drug != null) {
                    DrugInteractionActivity.this.interactionsViewModel.removeDrug(drug);
                }
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("options", "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.drug_interaction_menu, menu);
        setOptionMenu(menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        setOptionMenu(menu);
        return true;
    }

    private void setOptionMenu(Menu menu) {
        this.interactionsViewModel.refreshOptionMenu();
        MenuItem findItem = menu.findItem(R.id.drug_interaction_close_list);
        MenuItem findItem2 = menu.findItem(R.id.drug_interaction_saved_list);
        MenuItem findItem3 = menu.findItem(R.id.drug_interaction_open_saved);
        findItem.setVisible(this.interactionsViewModel.getMenuOptions().isCloseVisible());
        findItem2.setVisible(this.interactionsViewModel.getMenuOptions().isSaveVisible());
        if (this.interactionsViewModel.getMenuOptions().isSaveBlack()) {
            setBlackMenuOptionText(findItem2);
        } else {
            grayOutMenuOption(findItem2);
        }
        findItem3.setVisible(this.interactionsViewModel.getMenuOptions().isOpenVisible());
        findItem3.setTitle(this.interactionsViewModel.getMenuOptions().getOpenMenuTitle());
        if (this.interactionsViewModel.getMenuOptions().isOpenMenuBlack()) {
            setBlackMenuOptionText(findItem3);
        } else {
            grayOutMenuOption(findItem3);
        }
    }

    private void grayOutMenuOption(MenuItem menuItem) {
        SpannableString spannableString = new SpannableString(menuItem.getTitle().toString());
        spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, spannableString.length(), 0);
        menuItem.setTitle(spannableString);
    }

    private void setBlackMenuOptionText(MenuItem menuItem) {
        SpannableString spannableString = new SpannableString(menuItem.getTitle().toString());
        spannableString.setSpan(new ForegroundColorSpan(ViewCompat.MEASURED_STATE_MASK), 0, spannableString.length(), 0);
        menuItem.setTitle(spannableString);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case R.id.drug_interaction_close_list:
                this.interactionsViewModel.closeList();
                invalidateOptionsMenu();
                return true;
            case R.id.drug_interaction_open_saved:
                Long currentListId = this.interactionsViewModel.getCurrentListId();
                if (this.interactionsViewModel.haveSavedList()) {
                    Intent intent = new Intent(this, DrugListsActivity.class);
                    if (!currentListId.equals(-1L)) {
                        intent.putExtra(Constants.EXTRA_VIEWED_LIST, currentListId);
                    }
                    startActivityForResult(intent, 3456);
                }
                return true;
            case R.id.drug_interaction_saved_list:
                if (this.interactionsViewModel.getDrugListSize() > 0) {
                    showListCreationDialog();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3456 && i2 == -1 && intent != null) {
            this.interactionsViewModel.addListOfDrugs((DrugList) intent.getParcelableExtra(Constants.EXTRA_LIST_SELECTION));
        }
    }

    private void showListCreationDialog() {
        SaveListDialogFragment saveListDialogFragment = new SaveListDialogFragment();
        saveListDialogFragment.setListener(new INewListCreationListener() {
            public void onNewListCreation(String str) {
                OmnitureManager.get().trackModule(DrugInteractionActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "interaction", "save", (Map<String, Object>) null);
                DrugInteractionActivity.this.interactionsViewModel.createList(str);
                DrugInteractionActivity.this.invalidateOptionsMenu();
            }
        });
        saveListDialogFragment.show(getSupportFragmentManager(), (String) null);
    }
}
