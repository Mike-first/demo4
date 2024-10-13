package com.hill.webui.utilities.scripts;

import com.hill.api.CurlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CommandRunner {
    protected static final Logger log = LoggerFactory.getLogger(CommandRunner.class);
    private static final String BASH_EXE = "C:/Program Files/Git/bin/bash.exe";

    public static List<String> runBash(String cmd) {
        String[] fullCmd = {BASH_EXE, "-c", cmd};
        return runComm(fullCmd);
    }

    public static List<String> runCurl(CurlBuilder cmd) {
        return runBash(cmd.build());
    }

    public static List<String> runComm(String[] fullCmd) {
        int exitCode = -1;
        boolean isOk = true;
        List<String> results = new ArrayList<>();
        String cmd = String.join(" ", fullCmd);

        ProcessBuilder processBuilder = new ProcessBuilder(fullCmd);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    results.add(line);
                }
            }
            exitCode = process.waitFor();
            isOk = exitCode == 0;
        } catch (IOException | InterruptedException e) {
            log.info(String.format("command '%s' was not executed successfully due to %s: %n%s", cmd, e.getClass().getSimpleName(), e));
            isOk = false;
        } finally {
            log.info(String.format("command '%s' was%s executed successfully", cmd, (isOk ? "" : " not")));
            if (!isOk) log.info(String.format("command '%s' exit code %d", cmd, exitCode));
        }
        return results;
    }
}
