package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentPropertiesReader {

    public static String getProperties(String input) throws IOException {


        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/environment.properties");


            Properties properties = new Properties();

            properties.load(fileInputStream);

            Object object = properties.get(input);

            return (String) object;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load environment.properties file");
        }


    }

}
