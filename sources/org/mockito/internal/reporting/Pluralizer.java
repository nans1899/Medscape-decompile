package org.mockito.internal.reporting;

public class Pluralizer {
    public static String pluralize(int i) {
        if (i == 1) {
            return "1 time";
        }
        return i + " times";
    }

    public static String were_exactly_x_interactions(int i) {
        if (i == 1) {
            return "was exactly 1 interaction";
        }
        return "were exactly " + i + " interactions";
    }
}
