package com.hill.cucumber;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static TestContext tc;
    private TestContext() {
        this.storage = new HashMap<>();
    }

    public static TestContext instance(){
        if(tc == null) tc = new TestContext();
        return tc;
    }

    private final Map<String,Object> storage;

    public boolean putData(String key, Object value){
        return storage.put(key, value) == null;
    }

    public Object getData(String key){
        return storage.get(key);
    }
}
