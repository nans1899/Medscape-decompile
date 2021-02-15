package com.medscape.android.activity.speciality;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.FeedDetail;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.db.model.FeedMasters;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.homescreen.viewmodel.HomeScreenViewModel;
import com.medscape.android.util.Util;
import java.util.List;
import java.util.Map;

public class SpecialityListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public int currentCheckPosition;
    /* access modifiers changed from: private */
    public String currentSpecialtyName = "";
    private String feedType;
    List<FeedMasters> mList;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_specialty);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("feedType")) {
            this.feedType = extras.getString("feedType");
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String[] strArr = new String[2];
        strArr[0] = Settings.singleton(this).getSetting(Util.getUserSpecialityIDKey(this), "");
        strArr[1] = this.feedType.equals("NEWS") ? ExifInterface.GPS_MEASUREMENT_2D : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        this.currentSpecialtyName = FeedMaster.getSpecialtyNameOrDefaultBySpecialtyId(databaseHelper, strArr);
        setupActionBar();
        loadFromFeedTable();
    }

    private void loadFromFeedTable() {
        try {
            if (this.feedType.equalsIgnoreCase("NEWS")) {
                this.mList = FeedMaster.getAllSpecialtyByNameforNEWS(new DatabaseHelper(this));
            } else {
                this.mList = FeedMaster.getAllSpecialtyByNameForCME(new DatabaseHelper(this));
            }
            if (this.mList != null) {
                ((ListView) findViewById(16908298)).setAdapter(new SpecialtyAdapter(this.mList));
                ((ListView) findViewById(16908298)).setOnItemClickListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (view != null) {
            View childAt = adapterView.getChildAt(this.currentCheckPosition);
            if (childAt != null) {
                childAt.findViewById(R.id.logo).setVisibility(8);
            }
            view.findViewById(R.id.logo).setVisibility(0);
        }
        OmnitureManager.get().trackModule(this, "other", "preference", "change", (Map<String, Object>) null);
        setSpecialtyID(i);
        Intent intent = new Intent();
        intent.putExtra("specialityName", this.mList.get(i).getSpecilatyName());
        intent.putExtra("feedurl", getFeedUrlFromID(this.mList.get(i).getSpecilatyId()));
        setResult(-1, intent);
        finish();
    }

    private String getFeedUrlFromID(int i) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String[] strArr = new String[2];
        strArr[0] = String.valueOf(i);
        strArr[1] = this.feedType.equalsIgnoreCase("NEWS") ? ExifInterface.GPS_MEASUREMENT_2D : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        FeedDetail.findOneBySpecialtyAndFeedType(databaseHelper, strArr);
        return "";
    }

    private void setSpecialtyID(int i) {
        Settings.singleton(this).saveSetting(UserProfileProvider.INSTANCE.getUserSpecialityIDKey(this), String.valueOf(this.mList.get(i).getSpecilatyId()));
        String str = this.feedType;
        if (str != null && str.equals("NEWS")) {
            Settings.singleton(this).saveSetting(HomeScreenViewModel.RANDOM_FEED_ID, "-1");
        }
    }

    public class SpecialtyAdapter extends BaseAdapter {
        List<FeedMasters> feedList;

        public long getItemId(int i) {
            return (long) i;
        }

        SpecialtyAdapter(List<FeedMasters> list) {
            this.feedList = list;
        }

        public int getCount() {
            return this.feedList.size();
        }

        public FeedMasters getItem(int i) {
            return this.feedList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = SpecialityListActivity.this.getLayoutInflater().inflate(R.layout.specialty_row, viewGroup, false);
            if (inflate != null) {
                ((TextView) inflate.findViewById(R.id.rowTitle)).setText(this.feedList.get(i).getSpecilatyName());
                if (SpecialityListActivity.this.currentSpecialtyName.equalsIgnoreCase(this.feedList.get(i).getSpecilatyName())) {
                    SpecialityListActivity.this.currentCheckPosition = i;
                    inflate.findViewById(R.id.logo).setVisibility(0);
                } else {
                    inflate.findViewById(R.id.logo).setVisibility(8);
                }
            }
            return inflate;
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.sp_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            getSupportActionBar().setTitle((CharSequence) getResources().getString(R.string.title_content_preference));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onBackPressed();
        return true;
    }
}
