package com.magicfish.weroll.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartupArguments {
    public static void parse(String[] args) {
        for (String arg : args) {
            String[] kv = parsePropertyValue(arg);
            setProperty(kv[0], kv[1]);
        }
    }

    private static String[] parsePropertyValue(String arg) {
        if (arg == null || arg.length() < 1) return null;

        if (arg.startsWith("--") || arg.startsWith("-D")) {
            int sep = arg.indexOf("=");
            String key = arg.substring(2, sep);
            String value = arg.substring(sep + 1).trim();
            return new String[]{ key, value };
        }
        return null;
    }

    private static void setProperty(String key, String value) {
        System.setProperty(key, value);
        log.info("set property: {} ---> {}", key, value);
    }
}
