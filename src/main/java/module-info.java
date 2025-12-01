module com.example.blink {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    // Allow FXML to access the main package
    opens com.example.blink to javafx.fxml;

    // Allow FXML to access controllers package
    opens com.example.blink.Controllers to javafx.fxml;

    // Export packages if needed (for other modules)
    exports com.example.blink;
    exports com.example.blink.Controllers;
}
