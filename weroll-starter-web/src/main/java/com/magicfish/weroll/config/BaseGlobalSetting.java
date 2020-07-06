package com.magicfish.weroll.config;

import com.magicfish.weroll.config.property.*;

public class BaseGlobalSetting {

    public String name;

    private APIProperties api;

    private RouterProperties router;

    private AuthProperties auth;

    private ThreadProperties thread = new ThreadProperties();

    private SessProperties sess;

    private ResProperties res;

    private SiteProperties site;
}