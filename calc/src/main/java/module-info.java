module stat {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;

    opens stat to javafx.fxml;
    exports stat;
}
