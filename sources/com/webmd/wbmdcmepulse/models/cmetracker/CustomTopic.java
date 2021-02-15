package com.webmd.wbmdcmepulse.models.cmetracker;

import com.webmd.wbmdcmepulse.models.Topic;
import java.io.Serializable;

public class CustomTopic extends Topic implements Serializable {
    public String creditAmount;
    public String creditType;
}
