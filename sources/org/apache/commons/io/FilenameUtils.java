package org.apache.commons.io;

import com.facebook.appevents.AppEventsConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.description.type.TypeDescription;

public class FilenameUtils {
    private static final int BASE_16 = 16;
    private static final String EMPTY_STRING = "";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final char EXTENSION_SEPARATOR = '.';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final int IPV4_MAX_OCTET_VALUE = 255;
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
    private static final int IPV6_MAX_HEX_GROUPS = 8;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    private static final int NOT_FOUND = -1;
    private static final char OTHER_SEPARATOR;
    private static final Pattern REG_NAME_PART_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    private static boolean isSeparator(char c) {
        return c == '/' || c == '\\';
    }

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    public static String normalize(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, true);
    }

    public static String normalize(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', true);
    }

    public static String normalizeNoEndSeparator(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', false);
    }

    private static String doNormalize(String str, char c, boolean z) {
        boolean z2;
        String str2 = str;
        char c2 = c;
        if (str2 == null) {
            return null;
        }
        failIfNullBytePresent(str);
        int length = str.length();
        if (length == 0) {
            return str2;
        }
        int prefixLength = getPrefixLength(str);
        if (prefixLength < 0) {
            return null;
        }
        int i = length + 2;
        char[] cArr = new char[i];
        str2.getChars(0, str.length(), cArr, 0);
        char c3 = SYSTEM_SEPARATOR;
        if (c2 == c3) {
            c3 = OTHER_SEPARATOR;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (cArr[i2] == c3) {
                cArr[i2] = c2;
            }
        }
        if (cArr[length - 1] != c2) {
            cArr[length] = c2;
            length++;
            z2 = false;
        } else {
            z2 = true;
        }
        int i3 = prefixLength + 1;
        int i4 = i3;
        while (i4 < length) {
            if (cArr[i4] == c2) {
                int i5 = i4 - 1;
                if (cArr[i5] == c2) {
                    System.arraycopy(cArr, i4, cArr, i5, length - i4);
                    length--;
                    i4--;
                }
            }
            i4++;
        }
        int i6 = i3;
        while (i6 < length) {
            if (cArr[i6] == c2) {
                int i7 = i6 - 1;
                if (cArr[i7] == '.' && (i6 == i3 || cArr[i6 - 2] == c2)) {
                    if (i6 == length - 1) {
                        z2 = true;
                    }
                    System.arraycopy(cArr, i6 + 1, cArr, i7, length - i6);
                    length -= 2;
                    i6--;
                }
            }
            i6++;
        }
        int i8 = prefixLength + 2;
        int i9 = i8;
        while (i9 < length) {
            if (cArr[i9] == c2 && cArr[i9 - 1] == '.' && cArr[i9 - 2] == '.' && (i9 == i8 || cArr[i9 - 3] == c2)) {
                if (i9 != i8) {
                    if (i9 == length - 1) {
                        z2 = true;
                    }
                    int i10 = i9 - 4;
                    while (true) {
                        if (i10 < prefixLength) {
                            int i11 = i9 + 1;
                            System.arraycopy(cArr, i11, cArr, prefixLength, length - i9);
                            length -= i11 - prefixLength;
                            i9 = i3;
                            break;
                        } else if (cArr[i10] == c2) {
                            int i12 = i10 + 1;
                            System.arraycopy(cArr, i9 + 1, cArr, i12, length - i9);
                            length -= i9 - i10;
                            i9 = i12;
                            break;
                        } else {
                            i10--;
                        }
                    }
                } else {
                    return null;
                }
            }
            i9++;
        }
        if (length <= 0) {
            return "";
        }
        if (length <= prefixLength) {
            return new String(cArr, 0, length);
        }
        if (!z2 || !z) {
            return new String(cArr, 0, length - 1);
        }
        return new String(cArr, 0, length);
    }

    public static String concat(String str, String str2) {
        int prefixLength = getPrefixLength(str2);
        if (prefixLength < 0) {
            return null;
        }
        if (prefixLength > 0) {
            return normalize(str2);
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return normalize(str2);
        }
        if (isSeparator(str.charAt(length - 1))) {
            return normalize(str + str2);
        }
        return normalize(str + '/' + str2);
    }

    public static boolean directoryContains(String str, String str2) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Directory must not be null");
        } else if (str2 != null && !IOCase.SYSTEM.checkEquals(str, str2)) {
            return IOCase.SYSTEM.checkStartsWith(str2, str);
        } else {
            return false;
        }
    }

    public static String separatorsToUnix(String str) {
        return (str == null || str.indexOf(92) == -1) ? str : str.replace('\\', '/');
    }

    public static String separatorsToWindows(String str) {
        return (str == null || str.indexOf(47) == -1) ? str : str.replace('/', '\\');
    }

    public static String separatorsToSystem(String str) {
        if (str == null) {
            return null;
        }
        return isSystemWindows() ? separatorsToWindows(str) : separatorsToUnix(str);
    }

    public static int getPrefixLength(String str) {
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return -1;
        }
        if (length == 1) {
            if (charAt == '~') {
                return 2;
            }
            return isSeparator(charAt) ? 1 : 0;
        } else if (charAt == '~') {
            int indexOf = str.indexOf(47, 1);
            int indexOf2 = str.indexOf(92, 1);
            if (indexOf == -1 && indexOf2 == -1) {
                return length + 1;
            }
            if (indexOf == -1) {
                indexOf = indexOf2;
            }
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            return Math.min(indexOf, indexOf2) + 1;
        } else {
            char charAt2 = str.charAt(1);
            if (charAt2 == ':') {
                char upperCase = Character.toUpperCase(charAt);
                if (upperCase < 'A' || upperCase > 'Z') {
                    if (upperCase == '/') {
                        return 1;
                    }
                    return -1;
                } else if (length == 2 || !isSeparator(str.charAt(2))) {
                    return 2;
                } else {
                    return 3;
                }
            } else if (!isSeparator(charAt) || !isSeparator(charAt2)) {
                return isSeparator(charAt) ? 1 : 0;
            } else {
                int indexOf3 = str.indexOf(47, 2);
                int indexOf4 = str.indexOf(92, 2);
                if ((indexOf3 == -1 && indexOf4 == -1) || indexOf3 == 2 || indexOf4 == 2) {
                    return -1;
                }
                if (indexOf3 == -1) {
                    indexOf3 = indexOf4;
                }
                if (indexOf4 == -1) {
                    indexOf4 = indexOf3;
                }
                int min = Math.min(indexOf3, indexOf4) + 1;
                if (isValidHostName(str.substring(2, min - 1))) {
                    return min;
                }
                return -1;
            }
        }
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static int indexOfExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return -1;
        }
        if (!isSystemWindows() || str.indexOf(58, getAdsCriticalOffset(str)) == -1) {
            int lastIndexOf = str.lastIndexOf(46);
            if (indexOfLastSeparator(str) > lastIndexOf) {
                return -1;
            }
            return lastIndexOf;
        }
        throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
    }

    public static String getPrefix(String str) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength > str.length()) {
            failIfNullBytePresent(str + '/');
            return str + '/';
        }
        String substring = str.substring(0, prefixLength);
        failIfNullBytePresent(substring);
        return substring;
    }

    public static String getPath(String str) {
        return doGetPath(str, 1);
    }

    public static String getPathNoEndSeparator(String str) {
        return doGetPath(str, 0);
    }

    private static String doGetPath(String str, int i) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        int indexOfLastSeparator = indexOfLastSeparator(str);
        int i2 = i + indexOfLastSeparator;
        if (prefixLength >= str.length() || indexOfLastSeparator < 0 || prefixLength >= i2) {
            return "";
        }
        String substring = str.substring(prefixLength, i2);
        failIfNullBytePresent(substring);
        return substring;
    }

    public static String getFullPath(String str) {
        return doGetFullPath(str, true);
    }

    public static String getFullPathNoEndSeparator(String str) {
        return doGetFullPath(str, false);
    }

    private static String doGetFullPath(String str, boolean z) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength >= str.length()) {
            return z ? getPrefix(str) : str;
        }
        int indexOfLastSeparator = indexOfLastSeparator(str);
        if (indexOfLastSeparator < 0) {
            return str.substring(0, prefixLength);
        }
        int i = indexOfLastSeparator + (z ? 1 : 0);
        if (i == 0) {
            i++;
        }
        return str.substring(0, i);
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        failIfNullBytePresent(str);
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    private static void failIfNullBytePresent(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (str.charAt(i) != 0) {
                i++;
            } else {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String getBaseName(String str) {
        return removeExtension(getName(str));
    }

    public static String getExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return null;
        }
        int indexOfExtension = indexOfExtension(str);
        if (indexOfExtension == -1) {
            return "";
        }
        return str.substring(indexOfExtension + 1);
    }

    private static int getAdsCriticalOffset(String str) {
        int lastIndexOf = str.lastIndexOf(SYSTEM_SEPARATOR);
        int lastIndexOf2 = str.lastIndexOf(OTHER_SEPARATOR);
        if (lastIndexOf == -1) {
            if (lastIndexOf2 == -1) {
                return 0;
            }
            return lastIndexOf2 + 1;
        } else if (lastIndexOf2 == -1) {
            return lastIndexOf + 1;
        } else {
            return Math.max(lastIndexOf, lastIndexOf2) + 1;
        }
    }

    public static String removeExtension(String str) {
        if (str == null) {
            return null;
        }
        failIfNullBytePresent(str);
        int indexOfExtension = indexOfExtension(str);
        if (indexOfExtension == -1) {
            return str;
        }
        return str.substring(0, indexOfExtension);
    }

    public static boolean equals(String str, String str2) {
        return equals(str, str2, false, IOCase.SENSITIVE);
    }

    public static boolean equalsOnSystem(String str, String str2) {
        return equals(str, str2, false, IOCase.SYSTEM);
    }

    public static boolean equalsNormalized(String str, String str2) {
        return equals(str, str2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String str, String str2) {
        return equals(str, str2, true, IOCase.SYSTEM);
    }

    public static boolean equals(String str, String str2, boolean z, IOCase iOCase) {
        if (str == null || str2 == null) {
            return str == null && str2 == null;
        }
        if (z) {
            str = normalize(str);
            str2 = normalize(str2);
            Objects.requireNonNull(str, "Error normalizing one or both of the file names");
            Objects.requireNonNull(str2, "Error normalizing one or both of the file names");
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        return iOCase.checkEquals(str, str2);
    }

    public static boolean isExtension(String str, String str2) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (str2 != null && !str2.isEmpty()) {
            return getExtension(str).equals(str2);
        }
        if (indexOfExtension(str) == -1) {
            return true;
        }
        return false;
    }

    public static boolean isExtension(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (strArr != null && strArr.length != 0) {
            String extension = getExtension(str);
            for (String equals : strArr) {
                if (extension.equals(equals)) {
                    return true;
                }
            }
            return false;
        } else if (indexOfExtension(str) == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isExtension(String str, Collection<String> collection) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (collection != null && !collection.isEmpty()) {
            String extension = getExtension(str);
            for (String equals : collection) {
                if (extension.equals(equals)) {
                    return true;
                }
            }
            return false;
        } else if (indexOfExtension(str) == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean wildcardMatch(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatchOnSystem(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SYSTEM);
    }

    public static boolean wildcardMatch(String str, String str2, IOCase iOCase) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        String[] splitOnTokens = splitOnTokens(str2);
        ArrayDeque arrayDeque = new ArrayDeque(splitOnTokens.length);
        boolean z = false;
        int i = 0;
        int i2 = 0;
        do {
            if (!arrayDeque.isEmpty()) {
                int[] iArr = (int[]) arrayDeque.pop();
                i2 = iArr[0];
                i = iArr[1];
                z = true;
            }
            while (i2 < splitOnTokens.length) {
                if (splitOnTokens[i2].equals(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
                    i++;
                    if (i > str.length()) {
                        break;
                    }
                } else if (splitOnTokens[i2].equals("*")) {
                    if (i2 == splitOnTokens.length - 1) {
                        i = str.length();
                    }
                    z = true;
                    i2++;
                } else {
                    if (!z) {
                        if (!iOCase.checkRegionMatches(str, i, splitOnTokens[i2])) {
                            break;
                        }
                    } else {
                        i = iOCase.checkIndexOf(str, i, splitOnTokens[i2]);
                        if (i == -1) {
                            break;
                        }
                        int checkIndexOf = iOCase.checkIndexOf(str, i + 1, splitOnTokens[i2]);
                        if (checkIndexOf >= 0) {
                            arrayDeque.push(new int[]{i2, checkIndexOf});
                        }
                    }
                    i += splitOnTokens[i2].length();
                }
                z = false;
                i2++;
            }
            if (i2 == splitOnTokens.length && i == str.length()) {
                return true;
            }
        } while (!arrayDeque.isEmpty());
        return false;
    }

    static String[] splitOnTokens(String str) {
        if (str.indexOf(63) == -1 && str.indexOf(42) == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        int i = 0;
        char c = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 == '?' || c2 == '*') {
                if (sb.length() != 0) {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                if (c2 == '?') {
                    arrayList.add(TypeDescription.Generic.OfWildcardType.SYMBOL);
                } else if (c != '*') {
                    arrayList.add("*");
                }
            } else {
                sb.append(c2);
            }
            i++;
            c = c2;
        }
        if (sb.length() != 0) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(EMPTY_STRING_ARRAY);
    }

    private static boolean isValidHostName(String str) {
        return isIPv6Address(str) || isRFC3986HostName(str);
    }

    private static boolean isIPv4Address(String str) {
        Matcher matcher = IPV4_PATTERN.matcher(str);
        if (!matcher.matches() || matcher.groupCount() != 4) {
            return false;
        }
        for (int i = 1; i <= 4; i++) {
            String group = matcher.group(i);
            if (Integer.parseInt(group) > 255) {
                return false;
            }
            if (group.length() > 1 && group.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r9v6, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isIPv6Address(java.lang.String r9) {
        /*
            java.lang.String r0 = "::"
            boolean r1 = r9.contains(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0014
            int r3 = r9.indexOf(r0)
            int r4 = r9.lastIndexOf(r0)
            if (r3 == r4) goto L_0x0014
            return r2
        L_0x0014:
            java.lang.String r3 = ":"
            boolean r4 = r9.startsWith(r3)
            if (r4 == 0) goto L_0x0022
            boolean r4 = r9.startsWith(r0)
            if (r4 == 0) goto L_0x002e
        L_0x0022:
            boolean r4 = r9.endsWith(r3)
            if (r4 == 0) goto L_0x002f
            boolean r4 = r9.endsWith(r0)
            if (r4 != 0) goto L_0x002f
        L_0x002e:
            return r2
        L_0x002f:
            java.lang.String[] r3 = r9.split(r3)
            if (r1 == 0) goto L_0x0062
            java.util.ArrayList r4 = new java.util.ArrayList
            java.util.List r3 = java.util.Arrays.asList(r3)
            r4.<init>(r3)
            boolean r3 = r9.endsWith(r0)
            if (r3 == 0) goto L_0x004a
            java.lang.String r9 = ""
            r4.add(r9)
            goto L_0x0059
        L_0x004a:
            boolean r9 = r9.startsWith(r0)
            if (r9 == 0) goto L_0x0059
            boolean r9 = r4.isEmpty()
            if (r9 != 0) goto L_0x0059
            r4.remove(r2)
        L_0x0059:
            java.lang.String[] r9 = EMPTY_STRING_ARRAY
            java.lang.Object[] r9 = r4.toArray(r9)
            r3 = r9
            java.lang.String[] r3 = (java.lang.String[]) r3
        L_0x0062:
            int r9 = r3.length
            r0 = 8
            if (r9 <= r0) goto L_0x0068
            return r2
        L_0x0068:
            r9 = 0
            r4 = 0
            r5 = 0
        L_0x006b:
            int r6 = r3.length
            r7 = 1
            if (r9 >= r6) goto L_0x00af
            r6 = r3[r9]
            int r8 = r6.length()
            if (r8 != 0) goto L_0x007b
            int r5 = r5 + r7
            if (r5 <= r7) goto L_0x00a9
            return r2
        L_0x007b:
            int r5 = r3.length
            int r5 = r5 - r7
            if (r9 != r5) goto L_0x0092
            java.lang.String r5 = "."
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L_0x0092
            boolean r5 = isIPv4Address(r6)
            if (r5 != 0) goto L_0x008e
            return r2
        L_0x008e:
            int r4 = r4 + 2
            r5 = 0
            goto L_0x00ab
        L_0x0092:
            int r5 = r6.length()
            r7 = 4
            if (r5 <= r7) goto L_0x009a
            return r2
        L_0x009a:
            r5 = 16
            int r5 = java.lang.Integer.parseInt(r6, r5)     // Catch:{ NumberFormatException -> 0x00ae }
            if (r5 < 0) goto L_0x00ae
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r5 <= r6) goto L_0x00a8
            goto L_0x00ae
        L_0x00a8:
            r5 = 0
        L_0x00a9:
            int r4 = r4 + 1
        L_0x00ab:
            int r9 = r9 + 1
            goto L_0x006b
        L_0x00ae:
            return r2
        L_0x00af:
            if (r4 > r0) goto L_0x00b6
            if (r4 >= r0) goto L_0x00b5
            if (r1 == 0) goto L_0x00b6
        L_0x00b5:
            r2 = 1
        L_0x00b6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.FilenameUtils.isIPv6Address(java.lang.String):boolean");
    }

    private static boolean isRFC3986HostName(String str) {
        String[] split = str.split("\\.", -1);
        int i = 0;
        while (i < split.length) {
            if (split[i].length() == 0) {
                if (i == split.length - 1) {
                    return true;
                }
                return false;
            } else if (!REG_NAME_PART_PATTERN.matcher(split[i]).matches()) {
                return false;
            } else {
                i++;
            }
        }
        return true;
    }
}
