package com.hill.webui.core;

public enum Pages {
    HOME(baseUrl(), "home page"),
    RADIOLOGY(baseUrl() + "radiology/", "radiology"),
    ANALYZES(baseUrl() + "analizes/for-doctors/", "analysis");


    private final String link;
    private final String name;

    private static String baseUrl() {
        return PropertyReader.get("base.url");
    }

    Pages(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public Pages getByName(String name){

        return Pages.valueOf(name);
    }

    public String link() {
        return link;
    }
}
