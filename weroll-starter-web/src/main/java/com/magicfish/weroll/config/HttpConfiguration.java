package com.magicfish.weroll.config;

import com.magicfish.weroll.controller.APIProcessor;
import com.magicfish.weroll.controller.IHttpProcessor;
import com.magicfish.weroll.net.HttpMessageConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class HttpConfiguration {

    Logger logger = LoggerFactory.getLogger(HttpConfiguration.class);

    @ConditionalOnProperty(value = {"setting.api.custom-message-converters"}, havingValue = "false", matchIfMissing = true)
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        HttpMessageConverter<?> json = HttpMessageConverterFactory.createJSONConverter();
        HttpMessageConverter<?> text = HttpMessageConverterFactory.createTextConverter();
        return new HttpMessageConverters(json, text);
    }

    @Bean("api")
    public IHttpProcessor apiController(ApplicationContext applicationContext) throws Exception {
        IHttpProcessor processor = new APIProcessor(applicationContext);
        return processor;
    }
}
