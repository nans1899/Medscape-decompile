package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.play.core.tasks.Task;
import java.util.List;

interface w {
    Task<List<String>> a();

    Task<AssetPackStates> a(List<String> list, az azVar);

    Task<AssetPackStates> a(List<String> list, List<String> list2);

    void a(int i);

    void a(int i, String str);

    void a(int i, String str, String str2, int i2);

    void a(String str);

    void a(List<String> list);

    Task<ParcelFileDescriptor> b(int i, String str, String str2, int i2);

    void b();
}
