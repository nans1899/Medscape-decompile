package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.util.JsonReader;
import android.util.Log;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.medscape.android.Constants;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ChartLabel {
    public Double coordX;
    public Double coordY;
    public String imageName;
    public String title;
    public String titlePos;

    public enum LabelTitlePosition {
        NOT_SET,
        TOP,
        BOTTOM
    }

    public LabelTitlePosition getTitlePosition() {
        String str = this.titlePos;
        if (str == null) {
            return LabelTitlePosition.NOT_SET;
        }
        if (str.equalsIgnoreCase(Constants.CONSULT_DEEPLINK_TOP_POSTS)) {
            return LabelTitlePosition.TOP;
        }
        return LabelTitlePosition.BOTTOM;
    }

    public static List<ChartLabel> convertJsonToChartLabels(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToChartLabel(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ChartLabel convertJsonToChartLabel(JsonReader jsonReader) throws IOException, ParseException {
        ChartLabel chartLabel = new ChartLabel();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Log.d("API", "name2 is " + nextName);
            if (nextName.equals(MessengerShareContentUtility.MEDIA_IMAGE)) {
                chartLabel.imageName = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                chartLabel.title = APIParser.readString(jsonReader);
            } else if (nextName.equals("title_pos")) {
                chartLabel.titlePos = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("coord_x")) {
                chartLabel.coordX = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("coord_y")) {
                chartLabel.coordY = APIParser.readDouble(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return chartLabel;
    }
}
