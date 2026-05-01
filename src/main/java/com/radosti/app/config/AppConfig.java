package com.radosti.app.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private boolean allowStatusChange;
    private String dataFile;

    public boolean allowStatusChange() {
        return allowStatusChange;
    }
    public String getDataFile() {
        return dataFile;
    }

    public AppConfig() {
        Properties properties = new Properties();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(is == null){
                System.out.println("Config.properties isn't found");
                return;
            }
            properties.load(is);

        this.allowStatusChange = Boolean.parseBoolean(properties.getProperty("allowStatusChange"));
        this.dataFile = properties.getProperty("dataFile");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
}
