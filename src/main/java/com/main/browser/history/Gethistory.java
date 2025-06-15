package com.main.browser.history;

import com.google.gson.Gson;
import com.main.browser.ENV;

import java.io.File;
import java.util.List;

public class Gethistory {
    private List<HistoryItem> historyItems;

    public List<HistoryItem> getHistoryItems(ENV env) {
        String filePath = env.jsonfile();
        File file = new File(filePath);
        Gson gson = new Gson();
        if (file.exists()) {
            try {
                String json = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
                if (!json.isEmpty()) {
                    historyItems = gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<HistoryItem>>(){}.getType());
                } else {
                    historyItems = new java.util.ArrayList<>();
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
                historyItems = new java.util.ArrayList<>();
            }
        } else {
            historyItems = new java.util.ArrayList<>();
        }
        if (historyItems == null) {
            historyItems = new java.util.ArrayList<>();
        }
        return historyItems;
    }
}
