package com.main.browser.history;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.main.browser.ENV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveHistory {

    public static ENV env;
    public static void saveHistoryItem(HistoryItem item,ENV env) {
        try {
            env = env;
            String appData = System.getenv("APPDATA");
            String dirPath = appData + File.separator + env.getBrowserName();
            String filePath = dirPath + File.separator + "history.json";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            List<HistoryItem> historyList = new ArrayList<>();
            File file = new File(filePath);
            Gson gson = new Gson();

            if (file.exists()) {
                String json = new String(Files.readAllBytes(Paths.get(filePath)));
                if (!json.isEmpty()) {
                    historyList = gson.fromJson(json, new TypeToken<List<HistoryItem>>(){}.getType());
                }
            }

            historyList.add(item);

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(historyList, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
