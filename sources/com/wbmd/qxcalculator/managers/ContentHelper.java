package com.wbmd.qxcalculator.managers;

import android.content.Context;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.contentItems.common.ResourceFile;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBResourceFile;
import com.wbmd.qxcalculator.model.db.DaoSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContentHelper {
    private static final String TAG = APIParser.class.getSimpleName();
    private static ContentHelper mInstance;
    private Context context;

    public static ContentHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ContentHelper();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    private ContentHelper() {
    }

    public void checkForUpdatesToContentItems(List<DBContentItem> list, List<ContentItem> list2, List<String> list3, List<DBContentItem> list4, List<DBContentItem> list5, DaoSession daoSession) {
        boolean z;
        List<DBContentItem> list6 = list4;
        List<DBContentItem> list7 = list;
        ArrayList arrayList = new ArrayList(list);
        for (ContentItem next : list2) {
            DBContentItem dBContentItem = null;
            if (next.platforms == null || next.platforms.isEmpty()) {
                List<String> list8 = list3;
            } else {
                Iterator<Platform> it = next.platforms.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().os.equalsIgnoreCase(AppConfiguration.getInstance().getPlatformOsForContentItem())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBContentItem dBContentItem2 = (DBContentItem) it2.next();
                        if (dBContentItem2.getIdentifier().equals(next.identifier)) {
                            dBContentItem = dBContentItem2;
                            break;
                        }
                    }
                    if (dBContentItem != null) {
                        arrayList.remove(dBContentItem);
                        if (!next.lastModifiedDate.equals(dBContentItem.getLastModifiedDate())) {
                            dBContentItem.setRequiresUpdate(true);
                            long j = 0;
                            if (dBContentItem.getResourceFiles() != null) {
                                long j2 = 0;
                                for (DBResourceFile next2 : dBContentItem.getResourceFiles()) {
                                    j2 = Math.max(j2, next2.getLastModifiedDate() == null ? 0 : next2.getLastModifiedDate().longValue());
                                }
                                j = j2;
                            }
                            if (next.resourceFileMostRecentLastModifiedDate != null && next.resourceFileMostRecentLastModifiedDate.longValue() > j) {
                                dBContentItem.setResourcesRequireUpdate(true);
                            }
                            list6.add(dBContentItem);
                        } else if (dBContentItem.getRequiresUpdate() != null && dBContentItem.getRequiresUpdate().booleanValue()) {
                            dBContentItem.setRequiresUpdate(false);
                            list6.add(dBContentItem);
                        }
                        List<String> list9 = list3;
                    } else {
                        list3.add(next.identifier);
                    }
                    for (DBContentItem next3 : list) {
                        if (next3.getIdentifier().equals(next.identifier)) {
                            next3.setAllSpecialty(next.allSpecialty);
                            next3.setLeadConcept(next.leadConcept);
                            next3.setLeadSpecialty(next.leadSpecialty);
                        }
                    }
                }
            }
        }
        if (!list4.isEmpty()) {
            daoSession.getDBContentItemDao().updateInTx(list6);
        }
        if (!arrayList.isEmpty()) {
            list5.addAll(arrayList);
        }
    }

    public List<DBResourceFile> findResourceFilesToDelete(Map<ContentItem, DBContentItem> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null) {
            return arrayList;
        }
        for (Map.Entry next : map.entrySet()) {
            ContentItem contentItem = (ContentItem) next.getKey();
            DBContentItem dBContentItem = (DBContentItem) next.getValue();
            ArrayList arrayList2 = new ArrayList();
            if (dBContentItem.getResourceFiles() != null) {
                arrayList2.addAll(dBContentItem.getResourceFiles());
            }
            if (contentItem.resourceFiles != null && !contentItem.resourceFiles.isEmpty()) {
                for (ResourceFile next2 : contentItem.resourceFiles) {
                    DBResourceFile dBResourceFile = null;
                    Iterator it = arrayList2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBResourceFile dBResourceFile2 = (DBResourceFile) it.next();
                        if (dBResourceFile2.getIdentifier().equals(next2.identifier)) {
                            dBResourceFile = dBResourceFile2;
                            break;
                        }
                    }
                    if (dBResourceFile != null) {
                        arrayList2.remove(dBResourceFile);
                    }
                }
            }
            arrayList.addAll(arrayList2);
        }
        return arrayList;
    }
}
