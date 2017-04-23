package demo.quartz.cluster;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by root on 4/23/17.
 */
public class Utils {

    public static Properties readConfig(String classpathResource) {

        try {
            Resource resource = new ClassPathResource(classpathResource);
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);
    }

}
