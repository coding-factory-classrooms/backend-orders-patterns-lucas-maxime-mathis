package org.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Set of helpers function to manipulate URLs.
 */
public class URLUtils {

    /**
     * Extract query params for an URL. This function does NOT support multiple
     * values for the same key.
     *
     * @param query the input query (e.g. https://www.google.com?q=hello&tab=2)
     * @return a Map of query keys to query values.
     */
    public static Map<String, String> decodeQuery(String query) {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            for (String param : query.split("&")) {
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                if (!key.isEmpty()) {
                    params.put(key, value);
                }
            }
            return params;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e); // Cannot happen with UTF-8 encoding.
        }
    }
}
