package com.hill.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurlBuilder {
    private String method = "GET";
    private String url;
    private final List<String> options = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();

    private CurlBuilder() {
    }

    public static CurlBuilder start() {
        return new CurlBuilder();
    }

    public CurlBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public CurlBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public CurlBuilder withUserAgent(String userAgent) {
        withHeader("User-Agent", userAgent);
        return this;
    }

    public CurlBuilder withCookies(String cookies) {
        withHeader("Cookie", cookies);
        return this;
    }

    public CurlBuilder withHeader(String header, String value) {
        this.headers.add(header(header, value));
        return this;
    }

    public CurlBuilder withOptions(String... options) {
        this.options.addAll(Arrays.asList(options));
        return this;
    }

    public String build() {
        StringBuilder cmd = new StringBuilder("curl");
        options.forEach(opt -> cmd.append(" ").append(opt));
        cmd.append(" -X ").append(method).append(" '").append(url).append("'");
        headers.forEach(hdr -> cmd.append(" ").append(hdr));
        return cmd.toString();
    }

    private static String header(String header, String value) {
        return String.format("-H '%s: %s'", header, value);
    }
}

