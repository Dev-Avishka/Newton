package com.main.browser.history;

public class HistoryItem {
    public String title;
    public String url;
    public String date;

    public HistoryItem(String title, String url, String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
