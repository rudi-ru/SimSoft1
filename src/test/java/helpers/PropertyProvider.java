package helpers;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Извлекаем данные из local_env.properties
public class PropertyProvider {
    private static PropertyProvider instance;
    private Properties properties;

    private PropertyProvider() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/local_env.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

