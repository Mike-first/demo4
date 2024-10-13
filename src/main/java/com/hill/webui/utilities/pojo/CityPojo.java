package com.hill.webui.utilities.pojo;

public class CityPojo {

    public CityPojo() {
    }
    public CityPojo(String city, String code, String guid) {
        this.city = city;
        this.code = code;
        this.guid = guid;
    }
    private String city;
    private String code;
    private String guid;
    public String getCode() {
        return code;
    }
    public String getCity() {
        return city;
    }
    public String getGuid() {
        return guid;
    }
}
