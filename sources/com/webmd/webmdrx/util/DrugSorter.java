package com.webmd.webmdrx.util;

import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.Quantity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DrugSorter {
    public static ArrayList<Drug> sort(ArrayList<Drug> arrayList) {
        Collections.sort(arrayList, new Comparator<Drug>() {
            public int compare(Drug drug, Drug drug2) {
                Quantity lowestRankedQuantity = DrugSorter.getLowestRankedQuantity(drug);
                Quantity lowestRankedQuantity2 = DrugSorter.getLowestRankedQuantity(drug2);
                if (lowestRankedQuantity.getRank() == lowestRankedQuantity2.getRank() && lowestRankedQuantity.getQuantity() == lowestRankedQuantity2.getQuantity()) {
                    return drug.getStrength().compareTo(drug2.getStrength());
                }
                return lowestRankedQuantity.getRank() - lowestRankedQuantity2.getRank();
            }
        });
        return arrayList;
    }

    public static Quantity getLowestRankedQuantity(Drug drug) {
        List<Quantity> quantityList = drug.getQuantityList();
        Collections.sort(quantityList, new Comparator<Quantity>() {
            public int compare(Quantity quantity, Quantity quantity2) {
                return quantity.getRank() - quantity2.getRank();
            }
        });
        return quantityList.get(0);
    }
}
