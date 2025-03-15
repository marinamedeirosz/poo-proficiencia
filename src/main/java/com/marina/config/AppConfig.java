package com.marina.config;

public class AppConfig {
    private static AppConfig instance;
    private final String baseUrl;
    private final int ConnectionTimeout;
    private final int ReadTimeout;

    private AppConfig() {
        this.baseUrl = "https://your-apex-server/ords/your-schema/";
        this.ConnectionTimeout = 5000;
        this.ReadTimeout = 5000;
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
        return ConnectionTimeout;
    }
    
    public int getReadTimeout() {
        return ReadTimeout;
    }
}
