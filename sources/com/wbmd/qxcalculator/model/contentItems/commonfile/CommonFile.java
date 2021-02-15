package com.wbmd.qxcalculator.model.contentItems.commonfile;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.db.DBCommonFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CommonFile implements Parcelable {
    public static final Parcelable.Creator<CommonFile> CREATOR = new Parcelable.Creator<CommonFile>() {
        public CommonFile createFromParcel(Parcel parcel) {
            CommonFile commonFile = new CommonFile();
            commonFile.identifier = parcel.readString();
            return commonFile;
        }

        public CommonFile[] newArray(int i) {
            return new CommonFile[i];
        }
    };
    public String identifier;

    public int describeContents() {
        return 0;
    }

    public boolean readJsonTag(JsonReader jsonReader, String str) throws IOException, ParseException {
        return false;
    }

    public static ArrayList<CommonFile> commonFiles(List<DBCommonFile> list) {
        if (list == null) {
            return null;
        }
        ArrayList<CommonFile> arrayList = new ArrayList<>(list.size());
        for (DBCommonFile commonFile : list) {
            arrayList.add(new CommonFile(commonFile));
        }
        return arrayList;
    }

    public CommonFile(DBCommonFile dBCommonFile) {
        if (dBCommonFile != null) {
            this.identifier = dBCommonFile.getIdentifier();
        }
    }

    public CommonFile() {
        this((DBCommonFile) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
    }

    public String toString() {
        return "CommonFile [identifier=" + this.identifier + "]";
    }
}
