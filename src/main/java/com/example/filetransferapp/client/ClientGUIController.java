package com.example.filetransferapp.client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClientGUIController {
    final FileChooser fileChooser = new FileChooser();
    @FXML
    private Button chooseFile;

    @FXML
    private Button sendButton;

    @FXML
    void chooseFileClicked(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(new Stage());

        String fileFullName = new String(file.getName());

        String fileType = new String(fileFullName.substring(fileFullName.lastIndexOf(".")+1,fileFullName.length()));
        String fileName = new String(fileFullName.substring(0,fileFullName.lastIndexOf(".")));


        UDPFileSender.sendData(Files.readAllBytes(file.toPath()), InetAddress.getLocalHost().getHostAddress(),1234);
    }

}

