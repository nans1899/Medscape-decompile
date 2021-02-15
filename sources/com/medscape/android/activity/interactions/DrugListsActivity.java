package com.medscape.android.activity.interactions;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.IListSelectionListener;
import com.medscape.android.activity.interactions.interfaces.IRemoveIconListener;
import com.medscape.android.activity.interactions.models.DrugList;
import com.medscape.android.activity.interactions.recycler_views.DrugListAdapter;
import com.medscape.android.activity.interactions.viewmodels.InteractionsViewModel;
import com.medscape.android.db.model.Drug;
import java.util.List;

public class DrugListsActivity extends AppCompatActivity implements IListSelectionListener, IRemoveIconListener {
    public ViewModelProvider.Factory factory;
    InteractionsViewModel interactionsViewModel;
    RecyclerView listRecyclerView;
    DrugListAdapter mAdapter;

    public void onRemoveDrugIconClicked(Drug drug, int i) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_drug_lists);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        long j = -1;
        if (intent != null) {
            j = intent.getLongExtra(Constants.EXTRA_VIEWED_LIST, -1);
        }
        setUpRecyclerViews();
        InteractionsViewModel interactionsViewModel2 = (InteractionsViewModel) ViewModelProviders.of((FragmentActivity) this, this.factory).get(InteractionsViewModel.class);
        this.interactionsViewModel = interactionsViewModel2;
        interactionsViewModel2.setUp();
        this.interactionsViewModel.getUserLists().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DrugListsActivity.this.lambda$onCreate$1$DrugListsActivity((List) obj);
            }
        });
        this.interactionsViewModel.loadUserLists(j);
    }

    public /* synthetic */ void lambda$null$0$DrugListsActivity(List list) {
        this.mAdapter.setData(list);
    }

    public /* synthetic */ void lambda$onCreate$1$DrugListsActivity(List list) {
        runOnUiThread(new Runnable(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DrugListsActivity.this.lambda$null$0$DrugListsActivity(this.f$1);
            }
        });
    }

    private void setUpRecyclerViews() {
        this.listRecyclerView = (RecyclerView) findViewById(R.id.drugs_lists_recycler_view);
        this.listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DrugListAdapter drugListAdapter = new DrugListAdapter(this, this);
        this.mAdapter = drugListAdapter;
        this.listRecyclerView.setAdapter(drugListAdapter);
    }

    public void onListSelected(DrugList drugList) {
        if (drugList != null) {
            Intent intent = new Intent();
            intent.putExtra(Constants.EXTRA_LIST_SELECTION, drugList);
            setResult(-1, intent);
            finish();
        }
    }

    public void onRemoveListIconClicked(int i, int i2) {
        showDeleteAlert(i);
    }

    private void showDeleteAlert(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((int) R.string.drug_interaction_delete_item_title);
        builder.setMessage((int) R.string.drug_interaction_delete_item_message);
        builder.setPositiveButton((CharSequence) getString(R.string.drug_interaction_delete_item_title), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DrugListsActivity.this.interactionsViewModel.removeList((long) i);
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void setInteractionsViewModel(InteractionsViewModel interactionsViewModel2) {
        this.interactionsViewModel = interactionsViewModel2;
    }
}
