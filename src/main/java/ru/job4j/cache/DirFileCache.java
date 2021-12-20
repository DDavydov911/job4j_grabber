package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder sb = new StringBuilder();
        String path;
        try {
            path = Files.readString(Path.of(cachingDir, key));
            try (FileReader reader = new FileReader(path)
            ) {
                int charCode;
                while ((charCode = reader.read()) != -1) {
                    sb.append((char) charCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}