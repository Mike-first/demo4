package com.hill.webui.utilities.scripts;


import com.beust.jcommander.internal.Lists;
import com.hill.webui.utilities.PathStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ScriptRunner {

    protected static final Logger log = LoggerFactory.getLogger(ScriptRunner.class);

    private static String runScript(String path, String... args) {
        String name = scriptName(path);
        boolean isOk = true;
        String result = null;
        int exitCode = -1;

        ProcessBuilder builder = new ProcessBuilder("sh", path);
        builder.command().addAll(Lists.newArrayList(args));

        try {
            Process process = builder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                exitCode = process.waitFor();
                result = output.toString();
                isOk = exitCode == 0;
            }
        } catch (IOException | InterruptedException e) {
            log.error(String.format("script '%s' was not executed successfully due to %s: %n%s", name, e.getClass().getSimpleName(), e));
            isOk = false;
        } finally {
            log.info(String.format("script '%s' was%s executed successfully", name, (isOk ? "" : " not")));
            if (!isOk) log.info(String.format("script '%s' exit code %d", name, exitCode));
        }

        return result;
    }

    private static String scriptName(String path) {
        return Arrays.stream(path.split("/")).filter(sss -> sss.contains(".sh")).collect(Collectors.toList()).get(0);
    }

    private static String scriptPath(String name) {
        return String.format(PathStorage.SCRIPT_BY_NAME, name);
    }

    public static String generateAllureReport() {
        return runScript(scriptPath("generate_report"), PathStorage.ROOT);
    }

    public static String archiveReport() {
        String reportSrcPath = PathStorage.ALLURE_REPORT_DIR + "/index.html";
        return runScript(scriptPath("archive_report"),
                reportSrcPath, PathStorage.REPORT_ARCHIVE_DIR);
    }

}
