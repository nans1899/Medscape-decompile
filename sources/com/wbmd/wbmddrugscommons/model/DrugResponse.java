package com.wbmd.wbmddrugscommons.model;

public class DrugResponse {
    private int code;
    private Data data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public class Data {
        private Drug[] docs;

        public Data() {
        }

        public Drug[] getDocs() {
            return this.docs;
        }

        public void setDocs(Drug[] drugArr) {
            this.docs = drugArr;
        }
    }
}
