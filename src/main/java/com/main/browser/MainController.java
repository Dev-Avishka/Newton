package com.main.browser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TabPane tabPane;

    private Tab plusTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize with default tabs FIRST
        createAndAddTab("https://www.google.com", true);


        // Create the plus tab AFTER regular tabs
        createPlusTab();

        // Select the first real tab by default
        if (tabPane.getTabs().size() > 1) {
            tabPane.getSelectionModel().select(0);
        }
    }

    private void createPlusTab() {
        plusTab = new Tab();
        plusTab.setText("+");
        plusTab.setClosable(false);

        // Add the style class
        plusTab.getStyleClass().add("plus-tab");

        // Handle selection - prevent actual selection and create new tab instead
        plusTab.setOnSelectionChanged(event -> {
            if (plusTab.isSelected()) {
                // Create new tab
                Tab newTab = addNewTab();

                // Select the newly created tab instead of the plus tab
                if (newTab != null) {
                    tabPane.getSelectionModel().select(newTab);
                }
            }
        });

        // Add the plus tab to the end
        tabPane.getTabs().add(plusTab);
    }

    @FXML
    public Tab addNewTab() {
        Tab newTab = createAndAddTab("https://www.google.com", true);

        // Insert before the plus tab
        if (plusTab != null && tabPane.getTabs().contains(plusTab)) {
            int plusIndex = tabPane.getTabs().indexOf(plusTab);
            tabPane.getTabs().remove(newTab);
            tabPane.getTabs().add(plusIndex, newTab);
        }

        return newTab;
    }

    public void addTab(){
        addNewTab();
        Tab newTab = addNewTab();

        if (newTab != null) {
            tabPane.getSelectionModel().select(newTab);
        }
    }


    // Helper method to create and add a tab with a given URL and closable status
    private Tab createAndAddTab(String defaultUrl, boolean closable) {
        Tab newTab = new Tab("Loading...");
        newTab.setClosable(closable);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/main/browser/web-view.fxml"));
        try {
            VBox webViewContent = loader.load();
            Webcontroller webController = loader.getController();

            webController.setParentTab(newTab);
            newTab.setContent(webViewContent);

            // Add the tab (will be repositioned later if needed)
            tabPane.getTabs().add(newTab);

            // Load the default page for the new tab
            webController.loadPage(defaultUrl);

            return newTab;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}