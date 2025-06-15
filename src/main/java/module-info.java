module com.main.browser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires com.google.gson;

    opens com.main.browser to javafx.fxml;
    exports com.main.browser;
    exports com.main.browser.history;
    opens com.main.browser.history to javafx.fxml;
}