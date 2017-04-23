package demo.quartz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class App {

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = SpringApplication.run(new Object[]{AppConfig.class}, args);
    }

}

