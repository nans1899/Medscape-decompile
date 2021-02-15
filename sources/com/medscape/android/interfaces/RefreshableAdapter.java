package com.medscape.android.interfaces;

import android.widget.ListAdapter;
import com.medscape.android.ads.NativeDFPAD;
import java.util.List;

public interface RefreshableAdapter<T> extends ListAdapter {
    void addToList(T t);

    void refreshList(List<T> list);

    void removeInlineAD();

    void setInlineAD(NativeDFPAD nativeDFPAD);
}
