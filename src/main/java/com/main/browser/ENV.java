package com.main.browser;

import java.io.File;

public class ENV {
    // Environment variables for the browser
    private String browserName = "Newton";
    private String browserVersion = "1.0.0";

    public String jsonfile(){
        String appData = System.getenv("APPDATA");
        String dirPath = appData + File.separator + getBrowserName();
        String filePath = dirPath + File.separator + "history.json";
        return filePath;
    }
    public String getAppDataPath() {
        return System.getenv("APPDATA") + File.separator + getBrowserName();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }
}
