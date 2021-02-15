package com.medscape.android.drugs.model;

import android.provider.BaseColumns;

public class DrugsContract {

    public static final class Drug implements BaseColumns {
        public static final String AVAILABILITY = "Availability";
        public static final String COMBO_ID = "ComboId";
        public static final String CONTENT_ID = "ContentID";
        public static final String DRUG_ID = "DrugID";
        public static final String DRUG_NAME = "DrugName";
        public static final String GENERIC = "Generic";
        public static final String GENERIC_ID = "GenericID";
        public static final String IS_NON_DRUG = "IsNonDrug";
        public static final String TABLE = "tblDrugs";
        public static final String UNIQUE_ID = "UniqueID";
    }
}
