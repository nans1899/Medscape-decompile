package com.medscape.android.updater.model;

import java.io.Serializable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UpdateProcess implements Serializable {
    private AdPlaceHolderData mAdPlaceHolder;
    private Data mData;
    private AdSegvarsData mSingleAdSegvarData;
    private SilentUpdateData mSingleDrugData;

    public SilentUpdateData getSingleDrugData() {
        return this.mSingleDrugData;
    }

    public void setSingleDrugData(SilentUpdateData silentUpdateData) {
        this.mSingleDrugData = silentUpdateData;
    }

    public AdSegvarsData getSingleAdSegvarData() {
        return this.mSingleAdSegvarData;
    }

    public void setSingleAdSegvarData(AdSegvarsData adSegvarsData) {
        this.mSingleAdSegvarData = adSegvarsData;
    }

    public Data getData() {
        return this.mData;
    }

    public void setData(Data data) {
        this.mData = data;
    }

    public AdPlaceHolderData getAdPlaceHolder() {
        return this.mAdPlaceHolder;
    }

    public void setAdPlaceHolder(AdPlaceHolderData adPlaceHolderData) {
        this.mAdPlaceHolder = adPlaceHolderData;
    }

    public static class Data {
        private String mDescription;
        private String mMajorUpdateTitle;
        private String mMandatoryMessage;
        private String mMarketingUrl;
        private String mMessageFlag;
        private double mMinVersion;
        private String mOptionalMessage;
        private String mUrl;
        private double mVersion;

        public double getVersion() {
            return this.mVersion;
        }

        public double getMinVersion() {
            return this.mMinVersion;
        }

        public String getMessageFlag() {
            return this.mMessageFlag;
        }

        public String getOptionalMessage() {
            return this.mOptionalMessage;
        }

        public String getMajorUpdateTitle() {
            return this.mMajorUpdateTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public String getMandatoryMessage() {
            return this.mMandatoryMessage;
        }

        public String getMarketingUrl() {
            return this.mMarketingUrl;
        }

        public static Data createFromNodeList(NodeList nodeList, boolean z) {
            Data data = new Data();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node item = nodeList.item(i);
                String nodeName = item.getNodeName();
                if (nodeName.equalsIgnoreCase("version")) {
                    data.mVersion = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("minversion")) {
                    data.mMinVersion = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("messageflag")) {
                    data.mMessageFlag = item.getTextContent();
                } else if (nodeName.equalsIgnoreCase("optionalmessage")) {
                    data.mOptionalMessage = item.getTextContent();
                } else if (nodeName.equalsIgnoreCase("mandatorymessage")) {
                    data.mMandatoryMessage = item.getTextContent();
                } else if (nodeName.equalsIgnoreCase("marketingurl")) {
                    data.mMarketingUrl = item.getTextContent();
                } else if (nodeName.equalsIgnoreCase("majorupdate")) {
                    NodeList childNodes = item.getChildNodes();
                    int length2 = childNodes.getLength();
                    for (int i2 = 0; i2 < length2; i2++) {
                        Node item2 = childNodes.item(i2);
                        String nodeName2 = item2.getNodeName();
                        if (nodeName2.equalsIgnoreCase("title")) {
                            data.mMajorUpdateTitle = item2.getTextContent();
                        } else if (nodeName2.equalsIgnoreCase("description")) {
                            data.mDescription = item2.getTextContent();
                        } else if (nodeName2.equalsIgnoreCase("url")) {
                            data.mUrl = item2.getTextContent();
                        }
                    }
                }
                if (z) {
                    item.getParentNode().removeChild(item);
                }
            }
            return data;
        }
    }

    public static class AdPlaceHolderData {
        private String mUrl;
        private double mVersion;

        public double getVersion() {
            return this.mVersion;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public static AdPlaceHolderData createFromNodeList(NodeList nodeList, boolean z) {
            AdPlaceHolderData adPlaceHolderData = new AdPlaceHolderData();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node item = nodeList.item(i);
                String nodeName = item.getNodeName();
                if (nodeName.equalsIgnoreCase("version")) {
                    adPlaceHolderData.mVersion = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("url")) {
                    adPlaceHolderData.mUrl = item.getTextContent();
                }
                if (z) {
                    item.getParentNode().removeChild(item);
                }
            }
            return adPlaceHolderData;
        }
    }

    public static class AdSegvarsData {
        private String mUrl;
        private double mVersion;

        public double getVersion() {
            return this.mVersion;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public static AdSegvarsData createFromNodeList(NodeList nodeList, boolean z) {
            AdSegvarsData adSegvarsData = new AdSegvarsData();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node item = nodeList.item(i);
                String nodeName = item.getNodeName();
                if (nodeName.equalsIgnoreCase("version")) {
                    adSegvarsData.mVersion = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("url")) {
                    adSegvarsData.mUrl = item.getTextContent();
                }
                if (z) {
                    item.getParentNode().removeChild(item);
                }
            }
            return adSegvarsData;
        }
    }

    public static class SilentUpdateData {
        private double mDataVersion;
        private double mUpdate;
        private String mUrl;

        public double getUpdate() {
            return this.mUpdate;
        }

        public double getDataVersion() {
            return this.mDataVersion;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public static SilentUpdateData createFromNodeList(NodeList nodeList, boolean z) {
            SilentUpdateData silentUpdateData = new SilentUpdateData();
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node item = nodeList.item(i);
                String nodeName = item.getNodeName();
                if (nodeName.equalsIgnoreCase("dataversion")) {
                    silentUpdateData.mDataVersion = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("update")) {
                    silentUpdateData.mUpdate = Double.parseDouble(item.getTextContent());
                } else if (nodeName.equalsIgnoreCase("url")) {
                    silentUpdateData.mUrl = item.getTextContent();
                }
                if (z) {
                    item.getParentNode().removeChild(item);
                }
            }
            return silentUpdateData;
        }
    }
}
