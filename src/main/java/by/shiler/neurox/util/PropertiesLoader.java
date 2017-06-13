package by.shiler.neurox.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class PropertiesLoader {

    public static Properties load(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName));
        return properties;
    }

}
