package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.WebMDException;
import java.util.List;

public interface IDrugsReceivedListener {
    void onDrugsReceived(String str, List<DrugSearchResult> list);

    void onDrugsRequestFailed(WebMDException webMDException);
}
