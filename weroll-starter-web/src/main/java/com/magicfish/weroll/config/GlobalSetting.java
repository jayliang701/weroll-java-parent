package com.magicfish.weroll.config;

import com.magicfish.weroll.config.property.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Data
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration("globalSetting")
@ConfigurationProperties(prefix="setting")
public class GlobalSetting {

    private static GlobalSetting instance;

    public static GlobalSetting getInstance() {
        return instance;
    }

    public GlobalSetting() throws Exception {
        instance = this;
    }

    public String env;

    private APIProperties api;

    private RouterProperties router;

    private AuthProperties auth;

    private ThreadProperties thread = new ThreadProperties();

    private SessProperties sess;

    private ResProperties res;

    private SiteProperties site;
}