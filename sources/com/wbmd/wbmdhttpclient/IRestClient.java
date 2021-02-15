package com.wbmd.wbmdhttpclient;

import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public interface IRestClient {
    void flushQueue();

    JSONObject get(String str, Map<String, String> map) throws InterruptedException, ExecutionException, TimeoutException;

    void get(String str, int i, ICallbackEvent iCallbackEvent);

    void get(String str, int i, Map<String, String> map, ICallbackEvent iCallbackEvent);

    void post(String str, Map<String, Object> map, ICallbackEvent iCallbackEvent);

    void post(String str, Map<String, Object> map, Map<String, String> map2, ICallbackEvent iCallbackEvent);

    void post(String str, JSONObject jSONObject, ICallbackEvent iCallbackEvent);

    void post(String str, JSONObject jSONObject, Map<String, String> map, ICallbackEvent iCallbackEvent);
}
