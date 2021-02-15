package bo.app;

import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

public interface g {
    JSONObject a(URI uri, Map<String, String> map);

    JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject);
}
