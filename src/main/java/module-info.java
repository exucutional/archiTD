module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens controllers to javafx.fxml;
    exports org;
}