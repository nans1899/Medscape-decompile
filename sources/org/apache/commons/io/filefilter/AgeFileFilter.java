package org.apache.commons.io.filefilter;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class AgeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -2132740084016138541L;
    private final boolean acceptOlder;
    private final long cutoff;

    public AgeFileFilter(long j) {
        this(j, true);
    }

    public AgeFileFilter(long j, boolean z) {
        this.acceptOlder = z;
        this.cutoff = j;
    }

    public AgeFileFilter(Date date) {
        this(date, true);
    }

    public AgeFileFilter(Date date, boolean z) {
        this(date.getTime(), z);
    }

    public AgeFileFilter(File file) {
        this(file, true);
    }

    public AgeFileFilter(File file, boolean z) {
        this(file.lastModified(), z);
    }

    public boolean accept(File file) {
        return this.acceptOlder != FileUtils.isFileNewer(file, this.cutoff);
    }

    public String toString() {
        String str = this.acceptOlder ? "<=" : HtmlObject.HtmlMarkUp.CLOSE_BRACKER;
        return super.toString() + "(" + str + this.cutoff + ")";
    }
}
