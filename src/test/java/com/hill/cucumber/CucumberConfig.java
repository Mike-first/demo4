package com.hill.cucumber;

import com.hill.webui.utilities.pojo.CityPojo;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CucumberConfig {
    @DataTableType
    public CityPojo convertToCityPojo(Map<String, String> entry) {
        return new CityPojo(entry.get("city"), entry.get("code"), entry.get("guid"));
    }
}
