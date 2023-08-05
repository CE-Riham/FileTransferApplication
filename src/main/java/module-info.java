module com.example.filetransferapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.example.filetransferapp.server;
    exports com.example.filetransferapp.client;
    opens com.example.filetransferapp.server to javafx.fxml;
    opens com.example.filetransferapp.client to javafx.fxml;
}