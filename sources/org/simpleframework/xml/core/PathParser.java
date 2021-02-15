package org.simpleframework.xml.core;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.pool.TypePool;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class PathParser implements Expression {
    protected boolean attribute;
    protected Cache<String> attributes = new ConcurrentCache();
    protected StringBuilder builder = new StringBuilder();
    protected String cache;
    protected int count;
    protected char[] data;
    protected Cache<String> elements = new ConcurrentCache();
    protected List<Integer> indexes = new ArrayList();
    protected String location;
    protected List<String> names = new ArrayList();
    protected int off;
    protected String path;
    protected List<String> prefixes = new ArrayList();
    protected int start;
    protected Style style;
    protected Type type;

    private boolean isSpecial(char c) {
        return c == '_' || c == '-' || c == ':';
    }

    public PathParser(String str, Type type2, Format format) throws Exception {
        this.style = format.getStyle();
        this.type = type2;
        this.path = str;
        parse(str);
    }

    public boolean isEmpty() {
        return isEmpty(this.location);
    }

    public boolean isPath() {
        return this.names.size() > 1;
    }

    public boolean isAttribute() {
        return this.attribute;
    }

    public int getIndex() {
        return this.indexes.get(0).intValue();
    }

    public String getPrefix() {
        return this.prefixes.get(0);
    }

    public String getFirst() {
        return this.names.get(0);
    }

    public String getLast() {
        return this.names.get(this.names.size() - 1);
    }

    public String getPath() {
        return this.location;
    }

    public String getElement(String str) {
        if (isEmpty(this.location)) {
            return this.style.getElement(str);
        }
        String fetch = this.elements.fetch(str);
        if (fetch == null && (fetch = getElementPath(this.location, str)) != null) {
            this.elements.cache(str, fetch);
        }
        return fetch;
    }

    /* access modifiers changed from: protected */
    public String getElementPath(String str, String str2) {
        String element = this.style.getElement(str2);
        if (isEmpty(element)) {
            return str;
        }
        if (isEmpty(str)) {
            return element;
        }
        return str + "/" + element + "[1]";
    }

    public String getAttribute(String str) {
        if (isEmpty(this.location)) {
            return this.style.getAttribute(str);
        }
        String fetch = this.attributes.fetch(str);
        if (fetch == null && (fetch = getAttributePath(this.location, str)) != null) {
            this.attributes.cache(str, fetch);
        }
        return fetch;
    }

    /* access modifiers changed from: protected */
    public String getAttributePath(String str, String str2) {
        String attribute2 = this.style.getAttribute(str2);
        if (isEmpty(str)) {
            return attribute2;
        }
        return str + "/@" + attribute2;
    }

    public Iterator<String> iterator() {
        return this.names.iterator();
    }

    public Expression getPath(int i) {
        return getPath(i, 0);
    }

    public Expression getPath(int i, int i2) {
        int size = (this.names.size() - 1) - i2;
        if (size >= i) {
            return new PathSection(i, size);
        }
        return new PathSection(i, i);
    }

    private void parse(String str) throws Exception {
        if (str != null) {
            int length = str.length();
            this.count = length;
            char[] cArr = new char[length];
            this.data = cArr;
            str.getChars(0, length, cArr, 0);
        }
        path();
    }

    private void path() throws Exception {
        char[] cArr = this.data;
        int i = this.off;
        if (cArr[i] != '/') {
            if (cArr[i] == '.') {
                skip();
            }
            while (this.off < this.count) {
                if (!this.attribute) {
                    segment();
                } else {
                    throw new PathException("Path '%s' in %s references an invalid attribute", this.path, this.type);
                }
            }
            truncate();
            build();
            return;
        }
        throw new PathException("Path '%s' in %s references document root", this.path, this.type);
    }

    private void build() {
        int size = this.names.size();
        int i = size - 1;
        for (int i2 = 0; i2 < size; i2++) {
            String str = this.prefixes.get(i2);
            String str2 = this.names.get(i2);
            int intValue = this.indexes.get(i2).intValue();
            if (i2 > 0) {
                this.builder.append('/');
            }
            if (!this.attribute || i2 != i) {
                if (str != null) {
                    this.builder.append(str);
                    this.builder.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                }
                this.builder.append(str2);
                this.builder.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                this.builder.append(intValue);
                this.builder.append(']');
            } else {
                this.builder.append('@');
                this.builder.append(str2);
            }
        }
        this.location = this.builder.toString();
    }

    private void skip() throws Exception {
        char[] cArr = this.data;
        if (cArr.length > 1) {
            int i = this.off;
            if (cArr[i + 1] == '/') {
                this.off = i + 1;
            } else {
                throw new PathException("Path '%s' in %s has an illegal syntax", this.path, this.type);
            }
        }
        int i2 = this.off + 1;
        this.off = i2;
        this.start = i2;
    }

    private void segment() throws Exception {
        char c = this.data[this.off];
        if (c != '/') {
            if (c == '@') {
                attribute();
            } else {
                element();
            }
            align();
            return;
        }
        throw new PathException("Invalid path expression '%s' in %s", this.path, this.type);
    }

    private void element() throws Exception {
        int i = this.off;
        int i2 = 0;
        while (true) {
            int i3 = this.off;
            if (i3 >= this.count) {
                break;
            }
            char[] cArr = this.data;
            this.off = i3 + 1;
            char c = cArr[i3];
            if (isValid(c)) {
                i2++;
            } else if (c == '@') {
                this.off--;
            } else if (c == '[') {
                index();
            } else if (c != '/') {
                throw new PathException("Illegal character '%s' in element for '%s' in %s", Character.valueOf(c), this.path, this.type);
            }
        }
        element(i, i2);
    }

    private void attribute() throws Exception {
        char c;
        int i = this.off + 1;
        this.off = i;
        do {
            int i2 = this.off;
            if (i2 < this.count) {
                char[] cArr = this.data;
                this.off = i2 + 1;
                c = cArr[i2];
            } else if (i2 > i) {
                this.attribute = true;
                attribute(i, i2 - i);
                return;
            } else {
                throw new PathException("Attribute reference in '%s' for %s is empty", this.path, this.type);
            }
        } while (isValid(c));
        throw new PathException("Illegal character '%s' in attribute for '%s' in %s", Character.valueOf(c), this.path, this.type);
    }

    private void index() throws Exception {
        int i;
        if (this.data[this.off - 1] == '[') {
            i = 0;
            while (true) {
                int i2 = this.off;
                if (i2 >= this.count) {
                    break;
                }
                char[] cArr = this.data;
                this.off = i2 + 1;
                char c = cArr[i2];
                if (!isDigit(c)) {
                    break;
                }
                i = ((i * 10) + c) - 48;
            }
        } else {
            i = 0;
        }
        char[] cArr2 = this.data;
        int i3 = this.off;
        this.off = i3 + 1;
        if (cArr2[i3 - 1] == ']') {
            this.indexes.add(Integer.valueOf(i));
        } else {
            throw new PathException("Invalid index for path '%s' in %s", this.path, this.type);
        }
    }

    private void truncate() throws Exception {
        int i = this.off;
        int i2 = i - 1;
        char[] cArr = this.data;
        if (i2 >= cArr.length) {
            this.off = i - 1;
        } else if (cArr[i - 1] == '/') {
            this.off = i - 1;
        }
    }

    private void align() throws Exception {
        if (this.names.size() > this.indexes.size()) {
            this.indexes.add(1);
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isValid(char c) {
        return isLetter(c) || isSpecial(c);
    }

    private boolean isLetter(char c) {
        return Character.isLetterOrDigit(c);
    }

    private void element(int i, int i2) {
        String str = new String(this.data, i, i2);
        if (i2 > 0) {
            element(str);
        }
    }

    private void attribute(int i, int i2) {
        String str = new String(this.data, i, i2);
        if (i2 > 0) {
            attribute(str);
        }
    }

    private void element(String str) {
        String str2;
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            str2 = null;
        }
        String element = this.style.getElement(str);
        this.prefixes.add(str2);
        this.names.add(element);
    }

    private void attribute(String str) {
        String attribute2 = this.style.getAttribute(str);
        this.prefixes.add((Object) null);
        this.names.add(attribute2);
    }

    public String toString() {
        int i = this.off - this.start;
        if (this.cache == null) {
            this.cache = new String(this.data, this.start, i);
        }
        return this.cache;
    }

    private class PathSection implements Expression {
        private int begin;
        private List<String> cache = new ArrayList();
        private int end;
        private String path;
        private String section;

        public PathSection(int i, int i2) {
            this.begin = i;
            this.end = i2;
        }

        public boolean isEmpty() {
            return this.begin == this.end;
        }

        public boolean isPath() {
            return this.end - this.begin >= 1;
        }

        public boolean isAttribute() {
            if (!PathParser.this.attribute || this.end < PathParser.this.names.size() - 1) {
                return false;
            }
            return true;
        }

        public String getPath() {
            if (this.section == null) {
                this.section = getCanonicalPath();
            }
            return this.section;
        }

        public String getElement(String str) {
            String path2 = getPath();
            return path2 != null ? PathParser.this.getElementPath(path2, str) : str;
        }

        public String getAttribute(String str) {
            String path2 = getPath();
            return path2 != null ? PathParser.this.getAttributePath(path2, str) : str;
        }

        public int getIndex() {
            return PathParser.this.indexes.get(this.begin).intValue();
        }

        public String getPrefix() {
            return PathParser.this.prefixes.get(this.begin);
        }

        public String getFirst() {
            return PathParser.this.names.get(this.begin);
        }

        public String getLast() {
            return PathParser.this.names.get(this.end);
        }

        public Expression getPath(int i) {
            return getPath(i, 0);
        }

        public Expression getPath(int i, int i2) {
            return new PathSection(this.begin + i, this.end - i2);
        }

        public Iterator<String> iterator() {
            if (this.cache.isEmpty()) {
                for (int i = this.begin; i <= this.end; i++) {
                    String str = PathParser.this.names.get(i);
                    if (str != null) {
                        this.cache.add(str);
                    }
                }
            }
            return this.cache.iterator();
        }

        private String getCanonicalPath() {
            int i = 0;
            int i2 = 0;
            while (i < this.begin) {
                i2 = PathParser.this.location.indexOf(47, i2 + 1);
                i++;
            }
            int i3 = i2;
            while (i <= this.end) {
                i3 = PathParser.this.location.indexOf(47, i3 + 1);
                if (i3 == -1) {
                    i3 = PathParser.this.location.length();
                }
                i++;
            }
            return PathParser.this.location.substring(i2 + 1, i3);
        }

        private String getFragment() {
            int i = PathParser.this.start;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 > this.end) {
                    break;
                } else if (i >= PathParser.this.count) {
                    i++;
                    break;
                } else {
                    int i4 = i + 1;
                    if (PathParser.this.data[i] == '/' && (i2 = i2 + 1) == this.begin) {
                        i = i4;
                        i3 = i;
                    } else {
                        i = i4;
                    }
                }
            }
            return new String(PathParser.this.data, i3, (i - 1) - i3);
        }

        public String toString() {
            if (this.path == null) {
                this.path = getFragment();
            }
            return this.path;
        }
    }
}
