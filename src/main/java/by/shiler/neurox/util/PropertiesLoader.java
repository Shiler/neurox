package by.shiler.neurox.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class PropertiesLoader {

    public static Properties load(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("classpath:" + fileName));
        return properties;
    }

}
