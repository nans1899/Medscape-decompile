package org.threeten.bp.format;

import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalField;

final class SimpleDateTimeTextProvider extends DateTimeTextProvider {
    /* access modifiers changed from: private */
    public static final Comparator<Map.Entry<String, Long>> COMPARATOR = new Comparator<Map.Entry<String, Long>>() {
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getKey().length() - entry.getKey().length();
        }
    };
    private final ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> cache = new ConcurrentHashMap(16, 0.75f, 2);

    SimpleDateTimeTextProvider() {
    }

    public String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
        Object findStore = findStore(temporalField, locale);
        if (findStore instanceof LocaleStore) {
            return ((LocaleStore) findStore).getText(j, textStyle);
        }
        return null;
    }

    public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale) {
        Object findStore = findStore(temporalField, locale);
        if (findStore instanceof LocaleStore) {
            return ((LocaleStore) findStore).getTextIterator(textStyle);
        }
        return null;
    }

    private Object findStore(TemporalField temporalField, Locale locale) {
        Map.Entry createEntry = createEntry(temporalField, locale);
        Object obj = this.cache.get(createEntry);
        if (obj != null) {
            return obj;
        }
        this.cache.putIfAbsent(createEntry, createStore(temporalField, locale));
        return this.cache.get(createEntry);
    }

    private Object createStore(TemporalField temporalField, Locale locale) {
        TemporalField temporalField2 = temporalField;
        if (temporalField2 == ChronoField.MONTH_OF_YEAR) {
            DateFormatSymbols instance = DateFormatSymbols.getInstance(locale);
            HashMap hashMap = new HashMap();
            String[] months = instance.getMonths();
            HashMap hashMap2 = new HashMap();
            DateFormatSymbols dateFormatSymbols = instance;
            hashMap2.put(1L, months[0]);
            hashMap2.put(2L, months[1]);
            hashMap2.put(3L, months[2]);
            hashMap2.put(4L, months[3]);
            hashMap2.put(5L, months[4]);
            hashMap2.put(6L, months[5]);
            hashMap2.put(7L, months[6]);
            hashMap2.put(8L, months[7]);
            hashMap2.put(9L, months[8]);
            hashMap2.put(10L, months[9]);
            hashMap2.put(11L, months[10]);
            hashMap2.put(12L, months[11]);
            hashMap.put(TextStyle.FULL, hashMap2);
            HashMap hashMap3 = new HashMap();
            hashMap3.put(1L, months[0].substring(0, 1));
            hashMap3.put(2L, months[1].substring(0, 1));
            hashMap3.put(3L, months[2].substring(0, 1));
            hashMap3.put(4L, months[3].substring(0, 1));
            hashMap3.put(5L, months[4].substring(0, 1));
            hashMap3.put(6L, months[5].substring(0, 1));
            hashMap3.put(7L, months[6].substring(0, 1));
            hashMap3.put(8L, months[7].substring(0, 1));
            hashMap3.put(9L, months[8].substring(0, 1));
            hashMap3.put(10L, months[9].substring(0, 1));
            hashMap3.put(11L, months[10].substring(0, 1));
            hashMap3.put(12L, months[11].substring(0, 1));
            HashMap hashMap4 = hashMap;
            hashMap4.put(TextStyle.NARROW, hashMap3);
            String[] shortMonths = dateFormatSymbols.getShortMonths();
            HashMap hashMap5 = new HashMap();
            hashMap5.put(1L, shortMonths[0]);
            hashMap5.put(2, shortMonths[1]);
            hashMap5.put(3L, shortMonths[2]);
            hashMap5.put(4L, shortMonths[3]);
            hashMap5.put(5L, shortMonths[4]);
            hashMap5.put(6L, shortMonths[5]);
            hashMap5.put(7L, shortMonths[6]);
            hashMap5.put(8L, shortMonths[7]);
            hashMap5.put(9L, shortMonths[8]);
            hashMap5.put(10L, shortMonths[9]);
            hashMap5.put(11L, shortMonths[10]);
            hashMap5.put(12L, shortMonths[11]);
            hashMap4.put(TextStyle.SHORT, hashMap5);
            return createLocaleStore(hashMap4);
        } else if (temporalField2 == ChronoField.DAY_OF_WEEK) {
            DateFormatSymbols instance2 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap6 = new HashMap();
            String[] weekdays = instance2.getWeekdays();
            HashMap hashMap7 = new HashMap();
            hashMap7.put(1L, weekdays[2]);
            hashMap7.put(2, weekdays[3]);
            hashMap7.put(3L, weekdays[4]);
            hashMap7.put(4L, weekdays[5]);
            hashMap7.put(5L, weekdays[6]);
            hashMap7.put(6L, weekdays[7]);
            hashMap7.put(7L, weekdays[1]);
            hashMap6.put(TextStyle.FULL, hashMap7);
            HashMap hashMap8 = new HashMap();
            hashMap8.put(1L, weekdays[2].substring(0, 1));
            hashMap8.put(2, weekdays[3].substring(0, 1));
            hashMap8.put(3L, weekdays[4].substring(0, 1));
            hashMap8.put(4L, weekdays[5].substring(0, 1));
            hashMap8.put(5L, weekdays[6].substring(0, 1));
            hashMap8.put(6L, weekdays[7].substring(0, 1));
            hashMap8.put(7L, weekdays[1].substring(0, 1));
            hashMap6.put(TextStyle.NARROW, hashMap8);
            String[] shortWeekdays = instance2.getShortWeekdays();
            HashMap hashMap9 = new HashMap();
            hashMap9.put(1L, shortWeekdays[2]);
            hashMap9.put(2, shortWeekdays[3]);
            hashMap9.put(3L, shortWeekdays[4]);
            hashMap9.put(4L, shortWeekdays[5]);
            hashMap9.put(5L, shortWeekdays[6]);
            hashMap9.put(6L, shortWeekdays[7]);
            hashMap9.put(7L, shortWeekdays[1]);
            hashMap6.put(TextStyle.SHORT, hashMap9);
            return createLocaleStore(hashMap6);
        } else if (temporalField2 == ChronoField.AMPM_OF_DAY) {
            DateFormatSymbols instance3 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap10 = new HashMap();
            String[] amPmStrings = instance3.getAmPmStrings();
            HashMap hashMap11 = new HashMap();
            hashMap11.put(0L, amPmStrings[0]);
            hashMap11.put(1L, amPmStrings[1]);
            hashMap10.put(TextStyle.FULL, hashMap11);
            hashMap10.put(TextStyle.SHORT, hashMap11);
            return createLocaleStore(hashMap10);
        } else if (temporalField2 == ChronoField.ERA) {
            DateFormatSymbols instance4 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap12 = new HashMap();
            String[] eras = instance4.getEras();
            HashMap hashMap13 = new HashMap();
            hashMap13.put(0L, eras[0]);
            hashMap13.put(1L, eras[1]);
            hashMap12.put(TextStyle.SHORT, hashMap13);
            if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                HashMap hashMap14 = new HashMap();
                hashMap14.put(0L, "Before Christ");
                hashMap14.put(1L, "Anno Domini");
                hashMap12.put(TextStyle.FULL, hashMap14);
            } else {
                hashMap12.put(TextStyle.FULL, hashMap13);
            }
            HashMap hashMap15 = new HashMap();
            hashMap15.put(0L, eras[0].substring(0, 1));
            hashMap15.put(1L, eras[1].substring(0, 1));
            hashMap12.put(TextStyle.NARROW, hashMap15);
            return createLocaleStore(hashMap12);
        } else if (temporalField2 != IsoFields.QUARTER_OF_YEAR) {
            return "";
        } else {
            HashMap hashMap16 = new HashMap();
            HashMap hashMap17 = new HashMap();
            hashMap17.put(1L, "Q1");
            hashMap17.put(2, "Q2");
            hashMap17.put(3L, "Q3");
            hashMap17.put(4L, "Q4");
            hashMap16.put(TextStyle.SHORT, hashMap17);
            HashMap hashMap18 = new HashMap();
            hashMap18.put(1L, "1st quarter");
            hashMap18.put(2, "2nd quarter");
            hashMap18.put(3L, "3rd quarter");
            hashMap18.put(4L, "4th quarter");
            hashMap16.put(TextStyle.FULL, hashMap18);
            return createLocaleStore(hashMap16);
        }
    }

    /* access modifiers changed from: private */
    public static <A, B> Map.Entry<A, B> createEntry(A a, B b) {
        return new AbstractMap.SimpleImmutableEntry(a, b);
    }

    private static LocaleStore createLocaleStore(Map<TextStyle, Map<Long, String>> map) {
        map.put(TextStyle.FULL_STANDALONE, map.get(TextStyle.FULL));
        map.put(TextStyle.SHORT_STANDALONE, map.get(TextStyle.SHORT));
        if (map.containsKey(TextStyle.NARROW) && !map.containsKey(TextStyle.NARROW_STANDALONE)) {
            map.put(TextStyle.NARROW_STANDALONE, map.get(TextStyle.NARROW));
        }
        return new LocaleStore(map);
    }

    static final class LocaleStore {
        private final Map<TextStyle, List<Map.Entry<String, Long>>> parsable;
        private final Map<TextStyle, Map<Long, String>> valueTextMap;

        LocaleStore(Map<TextStyle, Map<Long, String>> map) {
            this.valueTextMap = map;
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (TextStyle next : map.keySet()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry entry : map.get(next).entrySet()) {
                    Object put = hashMap2.put(entry.getValue(), SimpleDateTimeTextProvider.createEntry(entry.getValue(), entry.getKey()));
                }
                ArrayList arrayList2 = new ArrayList(hashMap2.values());
                Collections.sort(arrayList2, SimpleDateTimeTextProvider.COMPARATOR);
                hashMap.put(next, arrayList2);
                arrayList.addAll(arrayList2);
                hashMap.put((Object) null, arrayList);
            }
            Collections.sort(arrayList, SimpleDateTimeTextProvider.COMPARATOR);
            this.parsable = hashMap;
        }

        /* access modifiers changed from: package-private */
        public String getText(long j, TextStyle textStyle) {
            Map map = this.valueTextMap.get(textStyle);
            if (map != null) {
                return (String) map.get(Long.valueOf(j));
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<String, Long>> getTextIterator(TextStyle textStyle) {
            List list = this.parsable.get(textStyle);
            if (list != null) {
                return list.iterator();
            }
            return null;
        }
    }
}
