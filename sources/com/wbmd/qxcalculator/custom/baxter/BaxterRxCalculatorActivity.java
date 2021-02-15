package com.wbmd.qxcalculator.custom.baxter;

import android.util.JsonReader;
import android.util.Log;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Calculator;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultDefaultRowItem;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class BaxterRxCalculatorActivity extends CalculatorActivity {
    private List<List<BaxterDose>> parseResultsIntoBaxterDoses() {
        Log.d("API", "BaxterRxCalculatorActivity.parseResultsIntoBaxterDoses");
        Calculator calculator = this.contentItem.calculator;
        ArrayList arrayList = new ArrayList(calculator.results.size());
        for (Result next : calculator.results) {
            StringReader stringReader = new StringReader(next.answerResult);
            Log.d("API", "parse: " + next.answerResult);
            JsonReader jsonReader = new JsonReader(stringReader);
            jsonReader.setLenient(true);
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName = jsonReader.nextName();
                    Log.d("API", "name is " + nextName);
                    if (nextName.equals("doses")) {
                        ArrayList arrayList2 = new ArrayList();
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            BaxterDose baxterDose = new BaxterDose();
                            jsonReader.beginObject();
                            while (jsonReader.hasNext()) {
                                String nextName2 = jsonReader.nextName();
                                Log.d("API", "name2 is " + nextName2);
                                if (nextName2.equals("day_title")) {
                                    baxterDose.dayTitle = APIParser.readString(jsonReader);
                                } else if (nextName2.equalsIgnoreCase("day_type")) {
                                    baxterDose.dayType = APIParser.readString(jsonReader);
                                } else if (nextName2.equals("night_title")) {
                                    baxterDose.nightTitle = APIParser.readString(jsonReader);
                                } else if (nextName2.equalsIgnoreCase("night_type")) {
                                    baxterDose.nightType = APIParser.readString(jsonReader);
                                } else {
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                            arrayList2.add(baxterDose);
                        }
                        jsonReader.endArray();
                        arrayList.add(arrayList2);
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void buildResultRowItems(List<QxRecyclerViewRowItem> list) {
        Calculator calculator = this.contentItem.calculator;
        int i = 0;
        for (List next : parseResultsIntoBaxterDoses()) {
            list.add(new BaxterDoseResultHeaderRowItem(calculator.results.get(i)));
            for (int i2 = 0; i2 < next.size(); i2++) {
                BaxterDose baxterDose = (BaxterDose) next.get(i2);
                boolean z = true;
                if (i2 != next.size() - 1) {
                    z = false;
                }
                list.add(new BaxterDoseResultDoseRowItem(baxterDose, i2, z, this.contentItem));
            }
            i++;
        }
    }

    /* renamed from: com.wbmd.qxcalculator.custom.baxter.BaxterRxCalculatorActivity$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType;

        static {
            int[] iArr = new int[Result.ResultType.values().length];
            $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType = iArr;
            try {
                iArr[Result.ResultType.BAXTER_RX_DOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public QxRecyclerViewRowItem getRowItemForResult(Result result, Result.ResultType resultType) {
        if (AnonymousClass1.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType[result.getResultType().ordinal()] != 1) {
            return super.getRowItemForResult(result, resultType);
        }
        return new ResultDefaultRowItem(result);
    }
}
