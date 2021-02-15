package com.medscape.android.activity.calc;

import com.wbmd.qxcalculator.FilesProvider;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/activity/calc/MedscapeFilesProvider;", "Lcom/wbmd/qxcalculator/FilesProvider;", "()V", "contentJSONFileName", "", "databaseName", "resourcesZipFileName", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeFilesProvider.kt */
public final class MedscapeFilesProvider implements FilesProvider {
    public String contentJSONFileName() {
        return "content";
    }

    public String databaseName() {
        return "user";
    }

    public String resourcesZipFileName() {
        return "resources";
    }
}
