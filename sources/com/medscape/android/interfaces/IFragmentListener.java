package com.medscape.android.interfaces;

import android.os.Bundle;
import java.util.Map;

public interface IFragmentListener {
    void attachFragmentWithTag(int i, Bundle bundle);

    <K, V> void attachListFragmentWithData(int i, int i2, Map<K, V> map, Bundle bundle);

    void onListFragmentSelectedListener(int i, Object obj, int i2);
}
