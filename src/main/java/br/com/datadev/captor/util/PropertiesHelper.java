package br.com.datadev.captor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Fabrício
 */
public class PropertiesHelper {

    private final String arquivo;

    public PropertiesHelper(String arquivo) throws FileNotFoundException {
        if (new File(arquivo).exists()) {
            this.arquivo = arquivo;
        } else {
            throw new FileNotFoundException(arquivo);
        }
    }

    public HashMap<String, String> getPropertiesMap() throws IOException {
        Properties props = new Properties();
        HashMap<String, String> map = new HashMap<>();
        try (InputStream resourceStream = new FileInputStream(arquivo)) {
            props.load(resourceStream);
            for (final String name : props.stringPropertyNames()) {
                map.put(name, props.getProperty(name));
            }
        }
        return map;
    }

    public void setPropertiesMap(HashMap<String, String> map) throws IOException {
        Properties props = new Properties();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            props.put(entry.getKey(), entry.getValue());
        }
        try (OutputStream resourceStream = new FileOutputStream(arquivo)) {
            props.store(resourceStream, null);
        }
    }
}
