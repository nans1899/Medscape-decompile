package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.util.JsonReader;
import android.util.Log;
import com.facebook.appevents.UserDataStore;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.media.android.bidder.base.models.internal.Logger;

public class ChartImage {
    public String footer;
    public String imageName;
    public List<ChartLabel> labels;
    public Double maxLocationX;
    public Double maxLocationY;
    public Double maxValueX;
    public Double maxValueY;
    public Double originLocationX;
    public Double originLocationY;
    public Double originValueX;
    public Double originValueY;
    public Double preferredDipWidth;
    public String scaleAxisX;
    public String scaleAxisY;
    public String title;

    public enum AxisScaleType {
        LINEAR,
        LOG,
        LN
    }

    public AxisScaleType getAxisXScaleType() {
        if (this.scaleAxisX.equalsIgnoreCase(Logger.LOG)) {
            return AxisScaleType.LOG;
        }
        if (this.scaleAxisX.equalsIgnoreCase(UserDataStore.LAST_NAME)) {
            return AxisScaleType.LN;
        }
        return AxisScaleType.LINEAR;
    }

    public AxisScaleType getAxisYScaleType() {
        if (this.scaleAxisY.equalsIgnoreCase(Logger.LOG)) {
            return AxisScaleType.LOG;
        }
        if (this.scaleAxisY.equalsIgnoreCase(UserDataStore.LAST_NAME)) {
            return AxisScaleType.LN;
        }
        return AxisScaleType.LINEAR;
    }

    public static List<ChartImage> convertJsonToChartImages(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToChartImage(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ChartImage convertJsonToChartImage(JsonReader jsonReader) throws IOException, ParseException {
        ChartImage chartImage = new ChartImage();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Log.d("API", "name2 is " + nextName);
            if (nextName.equals("chart_image_name")) {
                chartImage.imageName = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                chartImage.title = APIParser.readString(jsonReader);
            } else if (nextName.equals("footer")) {
                chartImage.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("origin_location_x")) {
                chartImage.originLocationX = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("origin_location_y")) {
                chartImage.originLocationY = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_location_x")) {
                chartImage.maxLocationX = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_location_y")) {
                chartImage.maxLocationY = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("origin_value_x")) {
                chartImage.originValueX = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("origin_value_y")) {
                chartImage.originValueY = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_value_x")) {
                chartImage.maxValueX = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_value_y")) {
                chartImage.maxValueY = APIParser.readDouble(jsonReader);
            } else if (nextName.equals("scale_axis_x")) {
                chartImage.scaleAxisX = APIParser.readString(jsonReader);
            } else if (nextName.equals("scale_axis_y")) {
                chartImage.scaleAxisY = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("preferred_dip_width")) {
                chartImage.preferredDipWidth = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("labels")) {
                chartImage.labels = ChartLabel.convertJsonToChartLabels(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return chartImage;
    }
}
