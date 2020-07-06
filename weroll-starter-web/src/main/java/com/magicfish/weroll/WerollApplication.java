package com.magicfish.weroll;

import com.magicfish.weroll.annotation.AnnotationRegister;
import com.magicfish.weroll.utils.StartupArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class WerollApplication {

    public static final String ENV_ARG_NAME = "spring.profiles.active";
    public static final String DEFAULT_ENV = "localdev";

    private static void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    private static void preInitialize(String[] args) throws Exception {
        AnnotationRegister.initialize();
    }

    public static ConfigurableApplicationContext run(Class<?> primarySource, String[] args) throws Exception {
        StartupArguments.parse(args);

        String env = System.getProperty(ENV_ARG_NAME);
        if (env == null || env.isEmpty()) {
            env = DEFAULT_ENV;
            setProperty(ENV_ARG_NAME, env);
        }

        preInitialize(args);

        ConfigurableApplicationContext applicationContext = SpringApplication.run(primarySource, args);
        return applicationContext;
    }
}
