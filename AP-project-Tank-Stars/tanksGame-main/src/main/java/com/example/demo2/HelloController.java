package com.example.demo2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void resumeGame(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Resume-game.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Resume Game");
            stage.setScene(scene);
            stage.show();
            HelloApplication.stg.close();
        } catch (IOException ignored) {

        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void openGame(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("InGameOptions.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
            HelloApplication.stg.close();
        } catch (IOException ignored) {
            System.out.println(ignored);
            System.out.println("error found");
        }
    }


    public void exitGame(MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }

    public void selectTank(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("selectTank.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Select Tanks");
            stage.setScene(scene);
            stage.show();
            HelloApplication.stg.close();
        } catch (IOException ignored) {
            System.out.println(ignored);
            System.out.println("error found");
        }
    }
}