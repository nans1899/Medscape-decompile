package com.github.jasminb.jsonapi.models.errors;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.List;

public class Errors {
    private List<Error> errors;

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> list) {
        this.errors = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Errors{errors=");
        Object obj = this.errors;
        if (obj == null) {
            obj = "Undefined";
        }
        sb.append(obj);
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        return sb.toString();
    }
}
