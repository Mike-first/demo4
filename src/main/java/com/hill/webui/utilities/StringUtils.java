package com.hill.webui.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtils {
    protected static final Logger log = LoggerFactory.getLogger(StringUtils.class);
    public static <T> T parseJson(String json, Class<T> clazz) {
        T res = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.readValue(json, clazz);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException");
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException");
        }
        return res;
    }

    public static String mapStrListStrToString(Map<String, List<String>> map) {
        return String.format("'%s'", map.entrySet().stream()
                .map(entry -> entry.getKey() + "'\t'" + String.join("'\t'", entry.getValue()))
                .collect(Collectors.joining("'\n'")));
    }

    public static Map<String, List<String>> stringToMap(String str) {
        Map<String, List<String>> map = new HashMap<>();
        String[] lines = str.split("\n");
        for (String line : lines) {
            String[] parts = line.replace("'", "").split("\t", -1);
            if (parts.length > 0) {
                String key = parts[0];
                List<String> values = new ArrayList<>();
//                не гарантирует порядок
//                List<String> values = new ArrayList<>(Arrays.asList(parts));
//                values.addAll(Arrays.asList(parts));
                for (int i = 1; i < parts.length; i++) {
                    values.add(parts[i]);
                }
                map.put(key, values);
            }
        }
        return map;
    }
}
