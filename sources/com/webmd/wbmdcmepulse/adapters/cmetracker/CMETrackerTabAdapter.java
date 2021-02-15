package com.webmd.wbmdcmepulse.adapters.cmetracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerActivityFragment;
import com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.Calendar;

public class CMETrackerTabAdapter extends FragmentStatePagerAdapter {
    ICallbackEvent<Boolean, String> mCallbck;
    private int mNumberOfTabs;
    private CMETrackerResponse mResponse;
    private UserProfile mUserProfile;

    public CMETrackerTabAdapter(FragmentManager fragmentManager, int i, CMETrackerResponse cMETrackerResponse, ICallbackEvent<Boolean, String> iCallbackEvent, UserProfile userProfile) {
        super(fragmentManager);
        this.mNumberOfTabs = i;
        this.mResponse = cMETrackerResponse;
        this.mCallbck = iCallbackEvent;
        this.mUserProfile = userProfile;
    }

    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        Calendar instance = Calendar.getInstance();
        instance.add(1, i * -1);
        bundle.putString("year", String.valueOf(instance.get(1)));
        CMETrackerActivityFragment newInstance = CMETrackerActivityFragment.newInstance(Integer.toString(instance.get(1)), this.mResponse, this.mCallbck, this.mUserProfile);
        newInstance.setArguments(bundle);
        return newInstance;
    }

    public int getCount() {
        return this.mNumberOfTabs;
    }
}
