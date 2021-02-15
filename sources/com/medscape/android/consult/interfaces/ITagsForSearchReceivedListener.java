package com.medscape.android.consult.interfaces;

import java.util.List;

public interface ITagsForSearchReceivedListener {
    void onTagsReceived(List<String> list, String str);
}
