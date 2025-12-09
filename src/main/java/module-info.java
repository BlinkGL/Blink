module com.example.blink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.base;


    // Allow FXML to access the main package
    opens com.example.blink to javafx.fxml;

    // Allow FXML to access controllers package
    opens com.example.blink.Controllers to javafx.fxml;

    // Export packages if needed (for other modules)
    exports com.example.blink;
    exports com.example.blink.Controllers;
    exports com.example.blink.DAO;
    opens com.example.blink.DAO to javafx.fxml;
}
