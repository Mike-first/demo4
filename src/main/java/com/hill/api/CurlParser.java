package com.hill.api;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlParser {
    public static String getCurlOutputCode(List<String> curlOutput) {
        return curlOutput.stream()
                .filter(s -> s.startsWith("< HTTP/1.1 "))
                .map(s -> s.replace("< HTTP/1.1 ", "").replaceAll("[^0-9]+", ""))
                .findFirst()
                .orElse(null);
    }

    public static String getCurlOutputBody(List<String> curlOutput) {
        return curlOutput.stream()
                .filter(s -> s.matches(".+\\{.*?\\}.?"))
                .map(s -> s.substring(s.indexOf('{')))
                .findFirst()
                .orElse(null);
    }

    public static String getCurlOutputCode(String str) {
        return extractUsingRegex(str, "(< HTTP\\/1.1 )(\\d{3})", 2);
    }

    public static String getCurlOutputBody(String str) {
        return extractUsingRegex(str, ".+(\\{.*?\\}).?", 1);
    }

    private static String extractUsingRegex(String input, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(group) : null;
    }
}
