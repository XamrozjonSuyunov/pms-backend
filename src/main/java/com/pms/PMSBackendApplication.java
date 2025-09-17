package com.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import static com.pms.util.LogUtil.logApplicationStartup;

@SpringBootApplication
public class PMSBackendApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PMSBackendApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

}
