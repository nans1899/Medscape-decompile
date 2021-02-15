package com.adobe.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class Messages {
    protected static final String MESSAGE_ACTION_NAME = "In-App Message";
    protected static final String MESSAGE_BUTTON_ID = "a.message.button.id";
    protected static final String MESSAGE_CANCEL_ID = "ADBMessageCancelButton";
    protected static final String MESSAGE_CLICKED = "a.message.clicked";
    protected static final String MESSAGE_CLICK_THROUGH_ID = "ADBMessageClickThroughButton";
    protected static final String MESSAGE_ID = "a.message.id";
    protected static final String MESSAGE_JSON_USER_INFO_KEY = "userData";
    protected static final String MESSAGE_LOCAL_ADB_CODE = "adbMessageCode";
    protected static final Integer MESSAGE_LOCAL_IDENTIFIER = 750183;
    protected static final String MESSAGE_LOCAL_PAYLOAD = "alarm_message";
    protected static final String MESSAGE_LOCAL_REQUEST_CODE = "requestCode";
    protected static final String MESSAGE_TOKEN_LIFETIME_VALUE = "{lifetimeValue}";
    protected static final String MESSAGE_TOKEN_MESSAGE_ID = "{messageId}";
    protected static final String MESSAGE_TOKEN_TRACKING_ID = "{trackingId}";
    protected static final String MESSAGE_TOKEN_USER_ID = "{userId}";
    protected static final String MESSAGE_TRIGGERED = "a.message.triggered";
    protected static final String MESSAGE_VIEWED = "a.message.viewed";
    private static Message _currentMessage = null;
    private static final Object _currentMessageMutex = new Object();
    private static int _largeIconResourceId = -1;
    private static MessageFullScreen _messageFullScreen = null;
    private static final Object _messageFullScreenMutex = new Object();
    private static int _smallIconResourceId = -1;

    Messages() {
    }

    protected enum MessageShowRule {
        MESSAGE_SHOW_RULE_UNKNOWN(0),
        MESSAGE_SHOW_RULE_ALWAYS(1),
        MESSAGE_SHOW_RULE_ONCE(2),
        MESSAGE_SHOW_RULE_UNTIL_CLICK(3);
        
        private final int value;

        private MessageShowRule(int i) {
            this.value = i;
        }

        /* access modifiers changed from: protected */
        public int getValue() {
            return this.value;
        }
    }

    protected static HashMap<String, Object> lowercaseKeysForMap(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>(map.size());
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(((String) next.getKey()).toLowerCase(), next.getValue());
        }
        return hashMap;
    }

    protected static void block3rdPartyCallbacksQueueForReferrer() {
        StaticMethods.getThirdPartyCallbacksExecutor().execute(new Runnable() {
            public void run() {
                while (!ReferrerHandler.getReferrerProcessed()) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        StaticMethods.logWarningFormat("Data Callback - Data Callback Queue Is Interrupted(%s)", e.getMessage());
                    }
                }
            }
        });
    }

    protected static void checkFor3rdPartyCallbacks(final Map<String, Object> map, final Map<String, Object> map2) {
        StaticMethods.getThirdPartyCallbacksExecutor().execute(new Runnable() {
            public void run() {
                ArrayList<Message> callbackTemplates = MobileConfig.getInstance().getCallbackTemplates();
                if (callbackTemplates != null && callbackTemplates.size() > 0) {
                    HashMap hashMap = new HashMap(Lifecycle.getContextDataLowercase());
                    HashMap<String, Object> lowercaseKeysForMap = Messages.lowercaseKeysForMap(map2);
                    HashMap<String, Object> lowercaseKeysForMap2 = Messages.lowercaseKeysForMap(map);
                    Iterator<Message> it = callbackTemplates.iterator();
                    while (it.hasNext()) {
                        Message next = it.next();
                        if (next.shouldShowForVariables(lowercaseKeysForMap2, lowercaseKeysForMap, hashMap)) {
                            next.show();
                        }
                    }
                }
            }
        });
    }

    protected static void checkForPIIRequests(final Map<String, Object> map) {
        StaticMethods.getPIIExecutor().execute(new Runnable() {
            public void run() {
                ArrayList<Message> piiRequests = MobileConfig.getInstance().getPiiRequests();
                if (piiRequests != null && piiRequests.size() > 0) {
                    FutureTask futureTask = new FutureTask(new Callable<Map<String, Object>>() {
                        public Map<String, Object> call() throws Exception {
                            return new HashMap(Lifecycle.getContextDataLowercase());
                        }
                    });
                    StaticMethods.getAnalyticsExecutor().execute(futureTask);
                    try {
                        Map map = (Map) futureTask.get();
                        HashMap<String, Object> lowercaseKeysForMap = Messages.lowercaseKeysForMap(map);
                        Iterator<Message> it = piiRequests.iterator();
                        while (it.hasNext()) {
                            Message next = it.next();
                            if (next.shouldShowForVariables(lowercaseKeysForMap, (Map<String, Object>) null, map)) {
                                next.show();
                            }
                        }
                    } catch (Exception e) {
                        StaticMethods.logErrorFormat("Lifecycle - Unable to get context data (%s)", e.getMessage());
                    }
                }
            }
        });
    }

    protected static void checkForInAppMessage(final Map<String, Object> map, final Map<String, Object> map2, final Map<String, Object> map3) {
        StaticMethods.getMessagesExecutor().execute(new Runnable() {
            public void run() {
                ArrayList<Message> inAppMessages = !StaticMethods.isWearableApp() ? MobileConfig.getInstance().getInAppMessages() : null;
                if (inAppMessages != null && inAppMessages.size() > 0) {
                    Map map = map;
                    if (map == null || !map.containsKey("pev2") || !map.get("pev2").toString().equals("ADBINTERNAL:In-App Message")) {
                        HashMap<String, Object> lowercaseKeysForMap = Messages.lowercaseKeysForMap(map2);
                        HashMap<String, Object> lowercaseKeysForMap2 = Messages.lowercaseKeysForMap(map);
                        Iterator<Message> it = inAppMessages.iterator();
                        while (it.hasNext()) {
                            Message next = it.next();
                            if (next.shouldShowForVariables(lowercaseKeysForMap2, lowercaseKeysForMap, map3)) {
                                next.show();
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    protected static MessageFullScreen getFullScreenMessageById(String str) {
        ArrayList<Message> inAppMessages = !StaticMethods.isWearableApp() ? MobileConfig.getInstance().getInAppMessages() : null;
        if (inAppMessages == null || inAppMessages.size() <= 0) {
            return null;
        }
        Iterator<Message> it = inAppMessages.iterator();
        while (it.hasNext()) {
            Message next = it.next();
            if (next.messageId != null && next.messageId.equals(str) && (next instanceof MessageFullScreen)) {
                return (MessageFullScreen) next;
            }
        }
        return null;
    }

    protected static void setCurrentMessageFullscreen(MessageFullScreen messageFullScreen) {
        synchronized (_messageFullScreenMutex) {
            _messageFullScreen = messageFullScreen;
        }
    }

    protected static MessageFullScreen getCurrentFullscreenMessage() {
        MessageFullScreen messageFullScreen;
        synchronized (_messageFullScreenMutex) {
            messageFullScreen = _messageFullScreen;
        }
        return messageFullScreen;
    }

    protected static void resetAllInAppMessages() {
        StaticMethods.getMessagesExecutor().execute(new Runnable() {
            public void run() {
                ArrayList<Message> inAppMessages = MobileConfig.getInstance().getInAppMessages();
                if (inAppMessages != null && inAppMessages.size() > 0) {
                    Iterator<Message> it = inAppMessages.iterator();
                    while (it.hasNext()) {
                        it.next().isVisible = false;
                    }
                }
            }
        });
    }

    protected static void setSmallIconResourceId(int i) {
        _smallIconResourceId = i;
    }

    protected static int getSmallIconResourceId() {
        return _smallIconResourceId;
    }

    protected static void setLargeIconResourceId(int i) {
        _largeIconResourceId = i;
    }

    protected static int getLargeIconResourceId() {
        return _largeIconResourceId;
    }

    protected static Message getCurrentMessage() {
        Message message;
        synchronized (_currentMessageMutex) {
            message = _currentMessage;
        }
        return message;
    }

    protected static void setCurrentMessage(Message message) {
        synchronized (_currentMessageMutex) {
            _currentMessage = message;
        }
    }
}
