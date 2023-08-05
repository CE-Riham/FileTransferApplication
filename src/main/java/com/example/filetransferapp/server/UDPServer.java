package com.example.filetransferapp.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketException;

public class UDPServer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UDPServer.class.getResource("server.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("server side");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws SocketException {
        UDPServerThread server = new UDPServerThread();
        server.run();
        launch();
    }
}
