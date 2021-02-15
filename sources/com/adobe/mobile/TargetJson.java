package com.adobe.mobile;

class TargetJson {
    static final String AAM_PARAMETERS = "aamParameters";
    static final String CLIENT = "client";
    static final String CONTENT_AS_JSON = "contentAsJson";
    static final String ENVIRONMENT_ID = "environmentId";
    static final String ID = "id";
    static final String ID_CUSTOMER_IDS = "customerIds";
    static final String ID_MARKETING_CLOUD_VISITOR_ID = "marketingCloudVisitorId";
    static final String ID_THIRD_PARTY_ID = "thirdPartyId";
    static final String ID_TNT_ID = "tntId";
    static final String MBOXES = "mboxes";
    static final String MBOX_RESPONSES = "mboxResponses";
    static final String NOTIFICATIONS = "notifications";
    static final String PREFETCH_MBOXES = "prefetch";
    static final String PREFETCH_MBOX_RESPONSES = "prefetchResponses";
    static final String PROFILE_PARAMETERS = "profileParameters";

    TargetJson() {
    }

    class CustomerIds {
        static final String AUTHENTICATION_STATE = "authenticatedState";
        static final String ID = "id";
        static final String INTEGRATION_CODE = "integrationCode";

        CustomerIds() {
        }
    }

    class AAMParameters {
        static final String BLOB = "blob";
        static final String DPID = "dataPartnerId";
        static final String DP_USER_ID = "dataPartnerUserId";
        static final String LOCATION_HINT = "dcsLocationHint";
        static final String UUID = "uuid";

        AAMParameters() {
        }
    }

    class Mbox {
        static final String A4T = "clientSideAnalyticsLoggingPayload";
        static final String CLICK_TOKEN = "clickToken";
        static final String CONTENT = "content";
        static final String ERROR_TYPE = "errorType";
        static final String EVENT_TOKENS = "eventTokens";
        static final String INDEX_ID = "indexId";
        static final String MBOX = "mbox";
        static final String NOTIFICATION_TYPE = "type";
        static final String ORDER = "order";
        static final String PARAMETERS = "parameters";
        static final String PRODUCT = "product";
        static final String PROFILE_SCRIPT_TOKEN = "profileScriptToken";
        static final String TIMESTAMP = "timestamp";

        Mbox() {
        }

        class Product {
            static final String CATEGORY_ID = "categoryId";
            static final String ID = "id";

            Product() {
            }
        }

        class Order {
            static final String ID = "id";
            static final String PURCHASED_PRODUCT_IDS = "purchasedProductIds";
            static final String TOTAL = "total";

            Order() {
            }
        }
    }

    class Values {
        static final String NOTIFICATION_TYPE_CLICKED = "click";
        static final String NOTIFICATION_TYPE_VIEWED = "hit";

        Values() {
        }
    }
}
