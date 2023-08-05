module com.example.filetransferapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.filetransferapp to javafx.fxml;
    exports com.example.filetransferapp.server;
    exports com.example.filetransferapp.client;
    opens com.example.filetransferapp.server to javafx.fxml;
    opens com.example.filetransferapp.client to javafx.fxml;
}