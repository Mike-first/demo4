package com.hill.webui.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readText(Path path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return lines;
    }

    public static boolean cleanDir(Path path) {
        if (!isDirNotEmpty(path)) return true;
        File dir = path.toFile();
        boolean result = true;
        for (File file : dir.listFiles()) {
            result &= file.delete();
        }
        return result;
    }

    public static boolean isDirNotEmpty(Path path) {
        File dir = path.toFile();
        return dir.listFiles() != null && dir.listFiles().length > 0;
    }

    public static void textToFile(String content) {
        String filePath = PathStorage.TMP_DIR.concat("\\saved.txt");
        try {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Content saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
