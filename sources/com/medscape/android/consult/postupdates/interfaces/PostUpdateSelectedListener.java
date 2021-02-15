package com.medscape.android.consult.postupdates.interfaces;

import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/medscape/android/consult/postupdates/interfaces/PostUpdateSelectedListener;", "", "onUpdateSelected", "", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PostUpdateSelectedListener.kt */
public interface PostUpdateSelectedListener {
    void onUpdateSelected(ConsultPost consultPost, BodyUpdates bodyUpdates);
}
