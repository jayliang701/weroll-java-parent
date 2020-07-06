package com.magicfish.weroll.config;

import com.magicfish.weroll.controller.IHttpProcessor;
import com.magicfish.weroll.controller.RouterProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {

    @Bean("router")
    public IHttpProcessor routerController(ApplicationContext applicationContext) throws Exception {
        IHttpProcessor processor = new RouterProcessor(applicationContext);
        return processor;
    }
}
