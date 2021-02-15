package com.medscape.android.activity;

import android.app.Activity;
import androidx.fragment.app.Fragment;

public abstract class AbstractIndexFragment extends Fragment {
    protected static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static Callbacks sDummyCallbacks = new Callbacks() {
        public void onItemSelected(int i) {
        }
    };
    protected int mActivatedPosition = -1;
    protected Callbacks mCallbacks = sDummyCallbacks;

    public interface Callbacks {
        void onItemSelected(int i);
    }

    public abstract void onListItemClick(int i);

    public void onDetach() {
        super.onDetach();
        this.mCallbacks = sDummyCallbacks;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Callbacks) {
            this.mCallbacks = (Callbacks) activity;
            return;
        }
        throw new IllegalStateException("Activity must implement fragment's callbacks.");
    }
}
