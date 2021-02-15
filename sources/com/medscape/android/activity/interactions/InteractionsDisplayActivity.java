package com.medscape.android.activity.interactions;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.db.model.Interaction;
import de.halfbit.pinnedsection.PinnedSectionListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InteractionsDisplayActivity extends AbstractBreadcrumbNavigableActivity {
    private ArrayList<Interaction> interactions;
    private int[] sectionCount;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.interactions_list);
        ArrayList arrayList = new ArrayList();
        ArrayList<Interaction> arrayList2 = (ArrayList) getIntent().getExtras().get("interactions");
        this.interactions = arrayList2;
        if (arrayList2 != null && arrayList2.size() > 0) {
            if (this.interactions.size() == 1) {
                setTitle(this.interactions.size() + " Interaction Found");
            } else {
                setTitle(this.interactions.size() + " Interactions Found");
            }
        }
        this.sectionCount = new int[]{0, 0, 0, 0};
        ArrayList<Interaction> arrayList3 = this.interactions;
        if (arrayList3 != null) {
            Iterator<Interaction> it = arrayList3.iterator();
            while (it.hasNext()) {
                Interaction next = it.next();
                next.setModifiedStrengthId(updateSectionCount(next.getStrengthId()));
            }
        }
        for (int length = this.sectionCount.length - 1; length >= 0; length--) {
            if (this.sectionCount[length] > 0) {
                arrayList.add(interactionName(length));
                arrayList.addAll(getListByIndex(length));
            }
        }
        ((PinnedSectionListView) findViewById(16908298)).setAdapter((ListAdapter) new InteractionsDisplayListAdapter(getApplicationContext(), 17367043, arrayList));
    }

    public String interactionName(int i) {
        if (i == 0) {
            return "Minor";
        }
        if (i == 1) {
            return "Monitor Closely";
        }
        if (i != 2) {
            return i != 3 ? "" : "Contraindicated";
        }
        return getResources().getString(R.string.serious_use_alternative);
    }

    public List<Interaction> getListByIndex(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<Interaction> it = this.interactions.iterator();
        while (it.hasNext()) {
            Interaction next = it.next();
            if (next.getModifiedStrengthId() == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005b, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int updateSectionCount(int r6) {
        /*
            r5 = this;
            r0 = 33
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            if (r6 == r0) goto L_0x007e
            switch(r6) {
                case 0: goto L_0x0076;
                case 1: goto L_0x006e;
                case 2: goto L_0x0065;
                case 3: goto L_0x005d;
                case 4: goto L_0x0054;
                case 5: goto L_0x004b;
                case 6: goto L_0x0043;
                default: goto L_0x000b;
            }
        L_0x000b:
            switch(r6) {
                case 10: goto L_0x003b;
                case 11: goto L_0x0033;
                case 12: goto L_0x002b;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r6) {
                case 20: goto L_0x0023;
                case 21: goto L_0x001b;
                case 22: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0086
        L_0x0013:
            int[] r6 = r5.sectionCount
            r0 = r6[r3]
            int r0 = r0 + r4
            r6[r3] = r0
            goto L_0x005b
        L_0x001b:
            int[] r6 = r5.sectionCount
            r0 = r6[r2]
            int r0 = r0 + r4
            r6[r2] = r0
            goto L_0x0052
        L_0x0023:
            int[] r6 = r5.sectionCount
            r0 = r6[r3]
            int r0 = r0 + r4
            r6[r3] = r0
            goto L_0x005b
        L_0x002b:
            int[] r6 = r5.sectionCount
            r0 = r6[r3]
            int r0 = r0 + r4
            r6[r3] = r0
            goto L_0x005b
        L_0x0033:
            int[] r6 = r5.sectionCount
            r0 = r6[r4]
            int r0 = r0 + r4
            r6[r4] = r0
            goto L_0x006c
        L_0x003b:
            int[] r6 = r5.sectionCount
            r0 = r6[r4]
            int r0 = r0 + r4
            r6[r4] = r0
            goto L_0x006c
        L_0x0043:
            int[] r6 = r5.sectionCount
            r0 = r6[r2]
            int r0 = r0 + r4
            r6[r2] = r0
            goto L_0x0052
        L_0x004b:
            int[] r6 = r5.sectionCount
            r0 = r6[r2]
            int r0 = r0 + r4
            r6[r2] = r0
        L_0x0052:
            r1 = 3
            goto L_0x0086
        L_0x0054:
            int[] r6 = r5.sectionCount
            r0 = r6[r3]
            int r0 = r0 + r4
            r6[r3] = r0
        L_0x005b:
            r1 = 2
            goto L_0x0086
        L_0x005d:
            int[] r6 = r5.sectionCount
            r0 = r6[r4]
            int r0 = r0 + r4
            r6[r4] = r0
            goto L_0x006c
        L_0x0065:
            int[] r6 = r5.sectionCount
            r0 = r6[r4]
            int r0 = r0 + r4
            r6[r4] = r0
        L_0x006c:
            r1 = 1
            goto L_0x0086
        L_0x006e:
            int[] r6 = r5.sectionCount
            r0 = r6[r1]
            int r0 = r0 + r4
            r6[r1] = r0
            goto L_0x0086
        L_0x0076:
            int[] r6 = r5.sectionCount
            r0 = r6[r1]
            int r0 = r0 + r4
            r6[r1] = r0
            goto L_0x0086
        L_0x007e:
            int[] r6 = r5.sectionCount
            r0 = r6[r2]
            int r0 = r0 + r4
            r6[r2] = r0
            goto L_0x0052
        L_0x0086:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.interactions.InteractionsDisplayActivity.updateSectionCount(int):int");
    }
}
