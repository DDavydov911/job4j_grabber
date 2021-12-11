package ru.job4j.cache;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder sb = new StringBuilder();
        String separator = File.separator;
        Path path = Paths.get(cachingDir + separator + key);
        try (BufferedReader reader = new BufferedReader(new FileReader(
                path.toString())
        )) {
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return sb.toString();
    }
}