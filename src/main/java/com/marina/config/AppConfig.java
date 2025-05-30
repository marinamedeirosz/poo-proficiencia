package com.marina.config;

public class AppConfig {
    private static AppConfig instance;
    private final String baseUrl;
    private final int connectionTimeout;
    private final int readTimeout;

    private AppConfig() {
        this.baseUrl = "https://apex.oracle.com/pls/apex/mmcp/nutri/";
        this.connectionTimeout = 5000;
        this.readTimeout = 5000;
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
    
    public int getReadTimeout() {
        return readTimeout;
    }
}
