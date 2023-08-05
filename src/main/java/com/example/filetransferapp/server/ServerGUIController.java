package com.example.filetransferapp.server;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerGUIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label serverIP ;
    @FXML
    private URL location;

    @FXML
    void initialize() throws UnknownHostException {
        if (serverIP != null)
            serverIP.setText(String.valueOf(InetAddress.getLocalHost().getHostAddress()));

    }

}
