package com.main.browser;

import com.main.browser.history.HistoryItem;
import com.main.browser.history.SaveHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab; // Import Tab
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Webcontroller implements Initializable {
    @FXML
    private WebView webView;
    private WebEngine webEngine;

    @FXML
    private TextField urlField;

    private Tab parentTab;

    private List<String> urls;
    // Add a field to hold the reference to the parent Tab

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = webView.getEngine();


        webEngine.titleProperty().addListener((observable, oldValue, newValue) -> {
            if (parentTab != null && newValue != null && !newValue.isEmpty()) {
                parentTab.setText(newValue);
            } else if (parentTab != null) {
                parentTab.setText("New Tab");
            }
        });

        // Add a listener to the WebEngine's location property for URL updates in TextField
        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                urlField.setText(newValue);
            }
        });
        urls = new ArrayList<String>();
        loadPage("https://www.google.com"); // Load a default page
    }

    // Setter method to inject the parent Tab
    public void setParentTab(Tab tab) {
        this.parentTab = tab;
    }

    public void loadPage(String url) {
        if(url.contains(".")){
            url = url.replaceAll(" ", "");
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
        } else {
            if(url.startsWith("chrome://")){
                return;
            }else{
                url = "https://www.google.com/search?q=" + url; // Fallback to Google search if no domain is provided
            }

        }
        urls.add(url); // Store the URL in the list
        save_history(); // Save the history item
        webEngine.load(url);
    }


    @FXML
    private void onEnterPressed() {
        String url = urlField.getText();
        loadPage(url);
    }

    public void home(ActionEvent actionEvent) {
        loadPage("https://www.google.com"); // Load the home page
    }

    public void goBack(ActionEvent actionEvent) {
        if (webEngine.getHistory().getCurrentIndex() > 0) {
            webEngine.getHistory().go(-1); // Go back in history
        }
    }

    public void goForward(ActionEvent actionEvent) {
        if (webEngine.getHistory().getCurrentIndex() < webEngine.getHistory().getEntries().size() - 1) {
            webEngine.getHistory().go(1); // Go forward in history
        }
    }

    public void refreshPage(ActionEvent actionEvent) {
        webEngine.reload(); // Reload the current page
    }

    public void save_history(){
        String title = webEngine.getTitle();
        String url = webEngine.getLocation();
        String date = java.time.LocalDateTime.now().toString(); // Get current date and time

        if(url == "google.com" || url == "https://www.google.com" || url == "https://www.google.com/"){
            return; // Do not save Google search page
        }
        // Create a new HistoryItem
        HistoryItem item = new HistoryItem(title, url, date);

        // Save the history item using SaveHistory class
        SaveHistory.saveHistoryItem(item);
    }
}