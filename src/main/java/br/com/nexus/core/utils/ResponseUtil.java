package br.com.nexus.core.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, String> createSuccessfullMessageDeleted(Number id) {
        Map<String, String> successfullMessageDeleted = new HashMap<>();
        successfullMessageDeleted.put("id", id.toString());
        successfullMessageDeleted.put("description", "Entity deleted successfull");
        successfullMessageDeleted.put("timestamp", LocalDateTime.now().toString());
        return successfullMessageDeleted;
    }
}
