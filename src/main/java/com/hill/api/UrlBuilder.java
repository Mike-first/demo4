package com.hill.api;

import java.util.ArrayList;
import java.util.List;

public class UrlBuilder {
    private String url;
    private final List<String> parameters = new ArrayList<>();

    private UrlBuilder() {
    }

    public static UrlBuilder start(){
        return new UrlBuilder();
    }

    public UrlBuilder withUrl(String url) {
        this.url = url;
        return this;
    }


    public UrlBuilder withParameter(String parameter, String value) {
        this.parameters.add(parameter(parameter, value));
        return this;
    }

    public String build() {
        StringBuilder res = new StringBuilder(url);
        if(!parameters.isEmpty()){
            res.append('?');
            parameters.forEach(par -> res.append(par));
        }
        return res.toString();
    }

    private static String parameter(String header, String value) {
        return String.format("%s=%s", header, value);
    }
}
