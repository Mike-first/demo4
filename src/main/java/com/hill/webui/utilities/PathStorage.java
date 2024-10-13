package com.hill.webui.utilities;

import com.hill.webui.core.PropertyReader;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathStorage {
    public static final String ROOT = changeDelimiter(Paths.get("src").toAbsolutePath().getParent().toString());
    public static final String SCRIPT_BY_NAME = String.join("",
            changeDelimiter(Paths.get("src/main/java/com/hill/webui/utilities/scripts/")
                    .toAbsolutePath().toString()), "/%s.sh");
    public static final Path ALLURE_RESULTS_DIR = Paths.get("target/allure-results").toAbsolutePath();
    public static final String TMP_DIR = Paths.get("src/main/resources/tmp").toAbsolutePath().toString();
    public static final Path ALLURE_REPORT_DIR_W = Paths.get("allure-report").toAbsolutePath();
    public static final String ALLURE_REPORT_DIR = changeDelimiter(ALLURE_REPORT_DIR_W.toString());
    public static final String REPORT_ARCHIVE_DIR = changeDelimiter(PropertyReader.get("archive.location"));

    private static String changeDelimiter(String path) { //??
        return path.replace("\\", "/").replace("D:", "/d");
    }
}
