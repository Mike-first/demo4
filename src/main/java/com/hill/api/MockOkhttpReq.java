package com.hill.api;

import com.hill.webui.utilities.FileUtils;

import java.nio.file.Paths;

public class MockOkhttpReq {
    public static String cityReq(String code) {
        return FileUtils.readText(Paths.get("src/main/resources/tmp/cityRespMock.txt").toAbsolutePath()).stream()
                .filter(s -> s.contains(code)).findFirst().get();
    }
}
