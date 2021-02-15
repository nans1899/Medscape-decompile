package org.apache.commons.io.filefilter;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.File;
import java.io.Serializable;

public class SizeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 7388077430788600069L;
    private final boolean acceptLarger;
    private final long size;

    public SizeFileFilter(long j) {
        this(j, true);
    }

    public SizeFileFilter(long j, boolean z) {
        if (j >= 0) {
            this.size = j;
            this.acceptLarger = z;
            return;
        }
        throw new IllegalArgumentException("The size must be non-negative");
    }

    public boolean accept(File file) {
        return this.acceptLarger != ((file.length() > this.size ? 1 : (file.length() == this.size ? 0 : -1)) < 0);
    }

    public String toString() {
        String str = this.acceptLarger ? ">=" : HtmlObject.HtmlMarkUp.OPEN_BRACKER;
        return super.toString() + "(" + str + this.size + ")";
    }
}
