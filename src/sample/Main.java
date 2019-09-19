package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Semaphore;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Brincadeira de Criança");
        primaryStage.setScene(new Scene(root, 700, 420));
        //primaryStage.setScene(new Scene(root, 922, 568));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}