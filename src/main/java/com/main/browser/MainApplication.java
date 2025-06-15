package com.main.browser;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

import java.io.IOException;

public class MainApplication extends Application {
    public static ENV env;
    @Override
    public void start(Stage stage) throws IOException {
        env = new ENV();


        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        MainController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
                if (keyEvent.getCode() == KeyCode.T && keyEvent.isControlDown() && !keyEvent.isConsumed()) {
                    controller.addTab();
                    keyEvent.consume();
                }
            }
        });

        stage.setTitle(env.getBrowserName());
//        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/main/browser/LOGO.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}