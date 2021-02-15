package com.ib.foreceup.model;

import java.util.ArrayList;
import java.util.HashMap;

public class UserConditions {
    private boolean inverse;
    private String kay;
    private ArrayList<String> value;

    public UserConditions(String str, ArrayList<String> arrayList, boolean z) {
        this.kay = str;
        this.value = arrayList;
        this.inverse = z;
    }

    public UserConditions() {
    }

    public String getKay() {
        return this.kay;
    }

    public void setKay(String str) {
        this.kay = str;
    }

    public ArrayList<String> getValue() {
        return this.value;
    }

    public void setValue(ArrayList<String> arrayList) {
        this.value = arrayList;
    }

    public boolean isInverse() {
        return this.inverse;
    }

    public void setInverse(boolean z) {
        this.inverse = z;
    }

    public class UserSentCriteria {
        private HashMap<String, String> criteria;
        private String key;

        public UserSentCriteria() {
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public HashMap<String, String> getCriteria() {
            return this.criteria;
        }

        public void setCriteria(HashMap<String, String> hashMap) {
            this.criteria = hashMap;
        }
    }
}
