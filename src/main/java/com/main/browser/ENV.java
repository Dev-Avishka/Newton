package com.main.browser;

public class ENV {
    // Environment variables for the browser
    private String browserName = "Newton";
    private String browserVersion = "1.0.0";


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
